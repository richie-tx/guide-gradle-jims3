/*
 * Created on Apr 24, 2007
 */
package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.ICodetable;

/**
 * @author dnikolis
 */
public class JuvenileHearingTypeCode extends PersistentObject implements ICodetable {
	private String categoryInd;
	private String code;
	private String description;
	private String inactiveInd;
	private String issueInd;
	private String optionInd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public Iterator findAll() {
		return new Home().findAll(JuvenileHearingTypeCode.class);
	}
	
	/*
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue,JuvenileHearingTypeCode.class);
	}
	
	/*
	 * @param event
	 * @return Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, JuvenileHearingTypeCode.class);
	}
	
	/*
	 * @param juvenileHearingTypeCodeId
	 * @return JuvenileHearingTypeCode
	 */
	public static JuvenileHearingTypeCode find(String juvenileHearingTypeCodeId)
	{
		return (JuvenileHearingTypeCode) new Home().find(juvenileHearingTypeCodeId,JuvenileHearingTypeCode.class);
	}

	/**
	 * @return Returns the categoryInd.
	 */
	public String getCategoryInd() {
		fetch();
		return categoryInd;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		fetch();
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		fetch();
		return description;
	}

	/**
	 * @return Returns the inactiveInd.
	 */
	public String getInactiveInd() {
		fetch();
		if(this.inactiveInd == null ){
		    
		    this.inactiveInd ="";
		}
		return inactiveInd;
	}

	/**
	 * @return Returns the issueInd.
	 */
	public String getIssueInd() {
		return issueInd;
	}

	/**
	 * @return Returns the optionInd.
	 */
	public String getOptionInd() {
		fetch();
		return optionInd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
	}

	/**
	 * @param categoryInd
	 *            The categoryInd to set.
	 */
	public void setCategoryInd(String categoryInd) {
		if (this.categoryInd == null || !this.categoryInd.equals(categoryInd))
		{
			markModified();
		}
		this.categoryInd = categoryInd;
	}

	/**
	 * @param code
	 *            The code to set.
	 */
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	/**
	 * @param inactiveInd
	 *            The inactiveInd to set.
	 */
	public void setInactiveInd(String inactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		if(this.inactiveInd == null ){
		    
		    this.inactiveInd ="";
		}
		this.inactiveInd = inactiveInd;
	}

	/**
	 * @param issueInd
	 *            The issueInd to set.
	 */
	public void setIssueInd(String issueInd) {
		if (this.issueInd == null || !this.issueInd.equals(issueInd))
		{
			markModified();
		}
		this.issueInd = issueInd;
	}

	/**
	 * @param optionInd
	 *            The optionInd to set.
	 */
	public void setOptionInd(String optionInd) {
		if (this.optionInd == null || !this.optionInd.equals(optionInd))
		{
			markModified();
		}
		this.optionInd = optionInd;
	}
}
