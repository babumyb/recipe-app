package app.recipe.restapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementNotNullValidator.
 */
public class ElementNotNullValidator implements ConstraintValidator<ElementsNotNull, Collection<?>> {
    
    /**
     * Checks if is valid.
     *
     * @param value the value
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final Collection<?> value, final ConstraintValidatorContext context) {
        return value == null || value.stream().allMatch(Objects::nonNull);
    }
}
