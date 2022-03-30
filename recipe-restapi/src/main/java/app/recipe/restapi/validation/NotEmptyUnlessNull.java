package app.recipe.restapi.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface NotEmptyUnlessNull.
 */
@Documented
@Constraint(validatedBy = { StringNotEmptyUnlessNullValidator.class, CollectionNotEmptyUnlessNullValidator.class, IntegerNotEmptyUnlessNull.class })
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyUnlessNull {
    
    /**
     * Message.
     *
     * @return the string
     */
    String message() default "Cannot be empty";
    
    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};
    
    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};
}
