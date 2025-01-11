package app.bola.flywell.generator;

import app.bola.flywell.annotations.FlightNumberSequence;
import app.bola.flywell.data.model.flight.FlightInstance;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.id.IdentifierGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EnumSet;

/**
 * Generates unique flight numbers based on a sequence defined in the database.
 * The sequence is incremented by a specified value and prefixed with a specified string.
 *
 * @see FlightNumberSequence
 */
public class FlightNumberSequenceGenerator implements BeforeExecutionGenerator {

    private final String prefix;
    private final int incrementBy;
    final Logger logger= LoggerFactory.getLogger(FlightNumberSequenceGenerator.class);


    /**
     * Constructs a FlightNumberSequenceGenerator with the specified configuration.
     *
     * @param config the configuration for the flight number sequence.
     */
    public FlightNumberSequenceGenerator(FlightNumberSequence config) {
        this.prefix = config.prefix();
        this.incrementBy = config.incrementBy();
    }

    /**
     * Generates a unique flight number based on the sequence defined in the database.
     * The sequence is incremented by the specified value and prefixed with the specified string.
     *
     * @param session the Hibernate session.
     * @param owner the entity for which the flight number is being generated.
     * @param currentValue the current value of the flight number.
     * @param eventType the event type triggering the generation.
     * @return the generated flight number.
     * @throws IdentifierGenerationException if there is an error generating the flight number.
     */
    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return session.doReturningWork(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT next_val FROM flight_number_sequence FOR UPDATE");
                if (resultSet.next()) {
                    int nextVal = resultSet.getInt("next_val");
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE flight_number_sequence SET next_val = ? WHERE next_val = ?");
                    preparedStatement.setInt(1, nextVal + incrementBy);
                    preparedStatement.setInt(2, nextVal);
                    preparedStatement.executeUpdate();
                    String generatedValue = prefix + nextVal;
                    if (owner instanceof FlightInstance){
                        ((FlightInstance)owner).setFlightNumber(generatedValue);
                        logger.warn("flightNumber field explicitly set: FlightInstance.flightNumber explicitly set to {}", generatedValue);
                    }
                    return generatedValue;
                } else {
                    throw new IdentifierGenerationException("Unable to fetch the next sequence value.");
                }
            } catch (Exception e) {
                throw new IdentifierGenerationException("Error generating flight number.", e);
            }
        });
    }

    /**
     * Specifies that this generator should be triggered only on insert events.
     *
     * @return the set of event types that trigger this generator.
     */
    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }
}
