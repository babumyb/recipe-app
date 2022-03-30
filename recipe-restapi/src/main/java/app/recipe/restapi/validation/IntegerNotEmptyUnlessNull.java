package app.recipe.restapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: Auto-generated Javadoc
/**
 */
public class IntegerNotEmptyUnlessNull implements ConstraintValidator<NotEmptyUnlessNull, Integer> {
    
    /**
     * Checks if is valid.
     *
     * @param value the value
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final Integer value, final ConstraintValidatorContext context) {
        return value == null || value > 0;
    }
}
