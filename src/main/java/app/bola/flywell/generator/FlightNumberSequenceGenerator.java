package app.bola.flywell.generator;

import app.bola.flywell.annotations.FlightNumberSequence;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.id.IdentifierGenerationException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EnumSet;

public class FlightNumberSequenceGenerator implements BeforeExecutionGenerator {

    private final String prefix;
    private final int incrementBy;

    public FlightNumberSequenceGenerator(FlightNumberSequence config) {
        System.out.println("Hello from FlightNumberSequenceGenerator");
        this.prefix = config.prefix();
        this.incrementBy = config.incrementBy();
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
            EventType eventType) {
        System.out.println("Hello from FlightNumberSequenceGenerator");
        return null;
        // return session.doReturningWork(connection -> {
        // try (Statement statement = connection.createStatement()) {
        // ResultSet resultSet = statement.executeQuery("SELECT next_val FROM
        // flight_number_sequence FOR UPDATE");
        // if (resultSet.next()) {
        // int nextVal = resultSet.getInt("next_val");
        // statement.executeUpdate("UPDATE flight_number_sequence SET next_val = " +
        // (nextVal + incrementBy));
        // return prefix + nextVal;
        // } else {
        // throw new IdentifierGenerationException("Unable to fetch the next sequence
        // value.");
        // }
        // } catch (Exception e) {
        // throw new IdentifierGenerationException("Error generating flight number.",
        // e);
        // }
        // });
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }
}
