package app.bola.flywell.annotations;

import app.bola.flywell.generator.FlightNumberSequenceGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ValueGenerationType(generatedBy = FlightNumberSequenceGenerator.class)
@Retention(RUNTIME)
@Target({METHOD,FIELD,LOCAL_VARIABLE})
public @interface FlightNumberSequence {

    String name();
    String prefix() default "FWF-";
    int startWith() default 1;
    int incrementBy() default 1;
}
