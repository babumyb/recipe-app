package app.recipe.restapi.dto;

/**
 * The Class RecipeInstructionDTO.
 */
public class RecipeInstructionDTO {
    
    /** The id. */
    private final String id;
    
    /** The instructions. */
    private final String instructions;

    /**
     * Instantiates a new recipe instruction DTO.
     *
     * @param id the id
     * @param instructions the instructions
     */
    public RecipeInstructionDTO(final String id, final String instructions) {
        this.id = id;
        this.instructions = instructions;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the instructions.
     *
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }
}
