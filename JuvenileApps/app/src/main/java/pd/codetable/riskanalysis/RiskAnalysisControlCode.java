package pd.codetable.riskanalysis;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.CollectionUtil;

import java.util.Iterator;
import java.util.List;

import pd.codetable.ICodetable;

/**
 * Properties for type
 * @detailerDoNotGenerate false
 * @referencedType pd.codetable.Code
 * @contextKey ACTIVITY_TYPE
 */
public class RiskAnalysisControlCode extends PersistentObject implements ICodetable, ICode
{
	private String code;
	private String answerSource;
	private String description;
	private String name;

	/**
	* @roseuid 418FD7D9021D
	*/
	public RiskAnalysisControlCode()
	{
	}


	/**
	* Access method for the category property.
	* @return the current value of the category property
	*/
	public String getAnswerSource()
	{
		fetch();
		return answerSource;
	}
	/**
	* Sets the value of the category property.
	* @param aCategory the new value of the category property
	*/
	public void setAnswerSource(String aAnswerSource)
	{
		if (this.answerSource == null || !this.answerSource.equals(aAnswerSource))
		{
			markModified();
		}
		answerSource = aAnswerSource;
	}
	
	/**
	* Access method for the category property.
	* @return the current value of the category property
	*/
	public String getName()
	{
		fetch();
		return name;
	}
	/**
	* Sets the value of the category property.
	* @param aCategory the new value of the category property
	*/
	public void setName(String aName)
	{
		if (this.name == null || !this.answerSource.equals(aName))
		{
			markModified();
		}
		name = aName;
	}
	
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode()
	{	
		fetch();
		return code;
	}
	/**
	* Sets the value of the code property.
	* @param aCode the new value of the code property
	*/
	public void setCode(String aCode)
	{
		if (this.code == null || !this.code.equals(aCode))
		{
			markModified();
		}
		code = aCode;

	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription()
	{
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}
	/**
	* @roseuid 418FCF8E026F
	*/
	public void find()
	{
		fetch();
	}
	/**
	 * @return RiskAnalysisControlCode
	 * @param code
	 * @roseuid 410FA34B0329
	 */
	static public RiskAnalysisControlCode find(String code)
	{
		RiskAnalysisControlCode riskAnalysisControlCode = null;
		IHome home = new Home();
		List <RiskAnalysisControlCode> aList = CollectionUtil.iteratorToList(home.findAll("code", code, RiskAnalysisControlCode.class));
		if (aList.size() > 0){
			riskAnalysisControlCode = aList.get(0);
		}
		home = null;
		aList = null;
		return riskAnalysisControlCode;
	}
	/**
	 * Find all RiskAnalysisControlCode objects
	 * @return java.util.Iterator
	 */
	public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(RiskAnalysisControlCode.class);
		return iter;
	}
	
	/*
	 * @param event
	 * @return Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, RiskAnalysisControlCode.class);
	}
	
	
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}

}

