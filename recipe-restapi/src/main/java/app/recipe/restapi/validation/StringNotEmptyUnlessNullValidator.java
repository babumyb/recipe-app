package app.recipe.restapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: Auto-generated Javadoc
/**
 * The Class StringNotEmptyUnlessNullValidator.
 */
public class StringNotEmptyUnlessNullValidator implements ConstraintValidator<NotEmptyUnlessNull, String> {
    
    /**
     * Checks if is valid.
     *
     * @param value the value
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value == null || value.trim().length() > 0;
    }
}
