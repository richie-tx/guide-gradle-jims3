package messaging.codetable.criminal.reply;

import java.util.Comparator;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

public class ServiceDeliveryWithoutFeeResponseEvent  extends ResponseEvent implements Comparable, ICode{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	private String category;
	private String inactiveInd;
	//added after carlas review comments.
	private String descriptionWithCode;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getInactiveInd() {
		return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}
	
	
	@Override
	public int compareTo(Object o) {
		return this.code.compareTo(((ServiceDeliveryWithoutFeeResponseEvent) o).getCode());
	}
	/**
	 * @return the descriptionWithCode
	 */
	public String getDescriptionWithCode() {
		return descriptionWithCode;
	}
	/**
	 * @param descriptionWithCode the descriptionWithCode to set
	 */
	public void setDescriptionWithCode(String descriptionWithCode) {
		this.descriptionWithCode = descriptionWithCode;
	}
}
