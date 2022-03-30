package app.recipe.restapi.dto;

/**
 * The Class IngredientDTO.
 */
public class IngredientDTO {
    
    /** The id. */
    private final Integer id;
    
    /** The name. */
    private final String name;

    /**
     * Instantiates a new ingredient DTO.
     *
     * @param id the id
     * @param name the name
     */
    public IngredientDTO(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
