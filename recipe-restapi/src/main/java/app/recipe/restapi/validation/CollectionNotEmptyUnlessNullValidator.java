package app.recipe.restapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Class CollectionNotEmptyUnlessNullValidator.
 */
public class CollectionNotEmptyUnlessNullValidator implements ConstraintValidator<NotEmptyUnlessNull, Collection<?>> {
    
    /**
     * Checks if is valid.
     *
     * @param value the value
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final Collection<?> value, final ConstraintValidatorContext context) {
        return value == null || value.size() > 0;
    }
}
