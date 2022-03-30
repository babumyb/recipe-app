package app.recipe.restapi.dto;

import app.recipe.restapi.entity.Enums.Status;
import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class ActionStatus.
 */
public class ActionStatus {
	
	/** The id. */
	@ApiModelProperty(value = MessageUtils.TEXT_STATUS_ID)
	private Integer id;
	
	/** The status. */
	@ApiModelProperty(value = MessageUtils.TEXT_STATUS)
	private Status status;
	

	/**
	 * Instantiates a new action status.
	 *
	 * @param id the id
	 * @param status the status
	 */
	public ActionStatus(final Integer id, final Status status) {
		super();
		this.id = id;
		this.status = status;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	
}
