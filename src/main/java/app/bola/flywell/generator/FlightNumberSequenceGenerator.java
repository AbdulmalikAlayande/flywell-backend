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

public class FlightNumberSequenceGenerator implements BeforeExecutionGenerator {

    private final String prefix;
    private final int incrementBy;
    final Logger logger= LoggerFactory.getLogger(FlightNumberSequenceGenerator.class);


    public FlightNumberSequenceGenerator(FlightNumberSequence config) {
        System.out.println("Hello from FlightNumberSequenceGenerator");
        this.prefix = config.prefix();
        this.incrementBy = config.incrementBy();
    }


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
                    if (owner instanceof FlightInstance){
                        ((FlightInstance)owner).setFlightNumber(prefix+nextVal);
                    }
                    return prefix + nextVal;
                } else {
                    throw new IdentifierGenerationException("Unable to fetch the next sequence value.");
                }
            } catch (Exception e) {
                throw new IdentifierGenerationException("Error generating flight number.", e);
            }
        });
    }


    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }
}
