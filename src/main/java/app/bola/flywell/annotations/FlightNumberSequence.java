package app.bola.flywell.annotations;

import app.bola.flywell.generator.FlightNumberSequenceGenerator;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to specify the configuration for generating unique flight numbers.
 * The flight numbers are generated using a sequence defined in the database.
 * The sequence is incremented by a specified value and prefixed with a specified string.
 *
 * @see FlightNumberSequenceGenerator
 */
@ValueGenerationType(generatedBy = FlightNumberSequenceGenerator.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD, LOCAL_VARIABLE})
public @interface FlightNumberSequence {

    /**
     * The name of the sequence.
     *
     * @return the name of the sequence.
     */
    String name();

    /**
     * The prefix to be added to the generated flight number.
     * Default is "FWF-".
     *
     * @return the prefix for the flight number.
     */
    String prefix() default "FWF-";

    /**
     * The starting value of the sequence.
     * Default is 1.
     *
     * @return the starting value of the sequence.
     */
    int startWith() default 1;

    /**
     * The increment value for the sequence.
     * Default is 1.
     *
     * @return the increment value for the sequence.
     */
    int incrementBy() default 1;
}