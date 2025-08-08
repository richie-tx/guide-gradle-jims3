package pd.codetable.criminal;

import java.util.Iterator;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author dgibler
 *
 * OffenseCode entity
 */
public class OffenseCode extends PersistentObject
{

	private String capitalInd;
	private String cjisCode;
	private String degree;
	private String description;
	private String juvOffenseCode;
	private String level;
	private String localCode;
	private String ncicCode;
	private String offenseCategory;
	private String offenseClass;
	private String offenseCodeId;
	private String penalCode;
	private String startTime;
	private String stateCodeNum;
	private String statusInd;
	private String statuteCitationCode;
	private String statuteCitationType;
	private String tjcCode;
	private String tjcLineNum;
	private String ucrCode;
	private String violentOffenseInd;
	/**
	* @roseuid 418FD7CE0356
	*/
	public OffenseCode()
	{
	}
	/**
	* Access method for the capitalInd property.
	* @return the current value of the capitalInd property
	*/
	public String getCapitalInd()
	{
		fetch();
		return capitalInd;
	}
	/**
	* Access method for the cjisCode property.
	* @return the current value of the cjisCode property
	*/
	public String getCjisCode()
	{
		fetch();
		return cjisCode;
	}
	/**
	* Access method for the degree property.
	* @return the current value of the degree property
	*/
	public String getDegree()
	{
		fetch();
		return degree;
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
	* Access method for the juvOffenseCode property.
	* @return the current value of the juvOffenseCode property
	*/
	public String getJuvOffenseCode()
	{
		fetch();
		return juvOffenseCode;
	}
	/**
	* Access method for the level property.
	* @return the current value of the level property
	*/
	public String getLevel()
	{
		fetch();
		return level;
	}
	/**
	* Access method for the localCode property.
	* @return the current value of the localCode property
	*/
	public String getLocalCode()
	{
		fetch();
		return localCode;
	}
	/**
	* Access method for the ncicCode property.
	* @return the current value of the ncicCode property
	*/
	public String getNcicCode()
	{
		fetch();
		return ncicCode;
	}
	/**
	* Access method for the offenseCategory property.
	* @return the current value of the offenseCategory property
	*/
	public String getOffenseCategory()
	{
		fetch();
		return offenseCategory;
	}
	/**
	* Access method for the offenseClass property.
	* @return the current value of the offenseClass property
	*/
	public String getOffenseClass()
	{
		fetch();
		return offenseClass;
	}
	/**
	* Access method for the penalCode property.
	* @return the current value of the penalCode property
	*/
	public String getPenalCode()
	{
		fetch();
		return penalCode;
	}
	/**
	 * Creates response event
	 * @return
	 */
	public OffenseCodeResponseEvent getResponseEvent(){
		OffenseCodeResponseEvent ocre = new OffenseCodeResponseEvent();
		ocre.setDegree(this.getDegree());
		ocre.setDescription(this.description);
		ocre.setLevel(this.getLevel());
		ocre.setLocalCode(this.getLocalCode());
		ocre.setNcicCode(this.getNcicCode());
		ocre.setOffenseCategory(this.getOffenseCategory());
		ocre.setOffenseClass(this.getOffenseClass());
		ocre.setOffenseCodeId(this.getOffenseCodeId());
		ocre.setPenalCode(this.getPenalCode());
		ocre.setStateCodeNum(this.getStateCodeNum());
		ocre.setStatusInd(this.getStatusInd());
		ocre.setViolentOffenseInd(this.getViolentOffenseInd());
		return ocre;			
	}
	/**
	* Access method for the startTime property.
	* @return the current value of the startTime property
	*/
	public String getStartTime()
	{
		fetch();
		return startTime;
	}
	/**
	* Access method for the stateCodeNum property.
	* @return the current value of the stateCodeNum property
	*/
	public String getStateCodeNum()
	{
		fetch();
		return stateCodeNum;
	}
	/**
	* Access method for the statusInd property.
	* @return the current value of the statusInd property
	*/
	public String getStatusInd()
	{
		fetch();
		return statusInd;
	}
	/**
	* Access method for the statuteCitationCode property.
	* @return the current value of the statuteCitationCode property
	*/
	public String getStatuteCitationCode()
	{
		fetch();
		return statuteCitationCode;
	}
	/**
	* Access method for the statuteCitationType property.
	* @return the current value of the statuteCitationType property
	*/
	public String getStatuteCitationType()
	{
		fetch();
		return statuteCitationType;
	}
	/**
	* Access method for the tjcCode property.
	* @return the current value of the tjcCode property
	*/
	public String getTjcCode()
	{
		fetch();
		return tjcCode;
	}
	/**
	* Access method for the tjcLineNum property.
	* @return the current value of the tjcLineNum property
	*/
	public String getTjcLineNum()
	{
		fetch();
		return tjcLineNum;
	}
	/**
	* Access method for the ucrCode property.
	* @return the current value of the ucrCode property
	*/
	public String getUcrCode()
	{
		fetch();
		return ucrCode;
	}
	/**
	* Access method for the violentOffenseInd property.
	* @return the current value of the violentOffenseInd property
	*/
	public String getViolentOffenseInd()
	{
		fetch();
		return violentOffenseInd;
	}
	/**
	* Sets the value of the capitalInd property.
	* @param aCapitalInd the new value of the capitalInd property
	*/
	public void setCapitalInd(String aCapitalInd)
	{
		if (this.capitalInd == null || !this.capitalInd.equals(aCapitalInd))
		{
			markModified();
		}
		capitalInd = aCapitalInd;
	}
	/**
	* Sets the value of the cjisCode property.
	* @param aCjisCode the new value of the cjisCode property
	*/
	public void setCjisCode(String aCjisCode)
	{
		if (this.cjisCode == null || !this.cjisCode.equals(aCjisCode))
		{
			markModified();
		}
		cjisCode = aCjisCode;
	}
	/**
	* Sets the value of the degree property.
	* @param aDegree the new value of the degree property
	*/
	public void setDegree(String aDegree)
	{
		if (this.degree == null || !this.degree.equals(aDegree))
		{
			markModified();
		}
		degree = aDegree;
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
	* Sets the value of the juvOffenseCode property.
	* @param aJuvOffenseCode the new value of the juvOffenseCode property
	*/
	public void setJuvOffenseCode(String aJuvOffenseCode)
	{
		if (this.juvOffenseCode == null || !this.juvOffenseCode.equals(aJuvOffenseCode))
		{
			markModified();
		}
		juvOffenseCode = aJuvOffenseCode;
	}
	/**
	* Sets the value of the level property.
	* @param aLevel the new value of the level property
	*/
	public void setLevel(String aLevel)
	{
		if (this.level == null || !this.level.equals(aLevel))
		{
			markModified();
		}
		level = aLevel;
	}
	/**
	* Sets the value of the localCode property.
	* @param aLocalCode the new value of the localCode property
	*/
	public void setLocalCode(String aLocalCode)
	{
		if (this.localCode == null || !this.localCode.equals(aLocalCode))
		{
			markModified();
		}
		localCode = aLocalCode;
	}
	/**
	* Sets the value of the ncicCode property.
	* @param aNcicCode the new value of the ncicCode property
	*/
	public void setNcicCode(String aNcicCode)
	{
		if (this.ncicCode == null || !this.ncicCode.equals(aNcicCode))
		{
			markModified();
		}
		ncicCode = aNcicCode;
	}
	/**
	* Sets the value of the offenseCategory property.
	* @param aOffenseCategory the new value of the offenseCategory property
	*/
	public void setOffenseCategory(String aOffenseCategory)
	{
		if (this.offenseCategory == null || !this.offenseCategory.equals(aOffenseCategory))
		{
			markModified();
		}
		offenseCategory = aOffenseCategory;
	}
	/**
	* Sets the value of the offenseClass property.
	* @param aOffenseClass the new value of the offenseClass property
	*/
	public void setOffenseClass(String aOffenseClass)
	{
		if (this.offenseClass == null || !this.offenseClass.equals(aOffenseClass))
		{
			markModified();
		}
		offenseClass = aOffenseClass;
	}
	/**
	* Sets the value of the penalCode property.
	* @param aPenalCode the new value of the penalCode property
	*/
	public void setPenalCode(String aPenalCode)
	{
		if (this.penalCode == null || !this.penalCode.equals(aPenalCode))
		{
			markModified();
		}
		penalCode = aPenalCode;
	}
	/**
	* Sets the value of the startTime property.
	* @param aStartTime the new value of the startTime property
	*/
	public void setStartTime(String aStartTime)
	{
		if (this.startTime == null || !this.startTime.equals(aStartTime))
		{
			markModified();
		}
		startTime = aStartTime;
	}
	/**
	* Sets the value of the stateCodeNum property.
	* @param aStateCodeNum the new value of the stateCodeNum property
	*/
	public void setStateCodeNum(String aStateCodeNum)
	{
		if (this.stateCodeNum == null || !this.stateCodeNum.equals(aStateCodeNum))
		{
			markModified();
		}
		stateCodeNum = aStateCodeNum;
	}
	/**
	* Sets the value of the statusInd property.
	* @param aStatusInd the new value of the statusInd property
	*/
	public void setStatusInd(String aStatusInd)
	{
		if (this.statusInd == null || !this.statusInd.equals(aStatusInd))
		{
			markModified();
		}
		statusInd = aStatusInd;
	}
	/**
	* Sets the value of the statuteCitationCode property.
	* @param aStatuteCitationCode the new value of the statuteCitationCode property
	*/
	public void setStatuteCitationCode(String aStatuteCitationCode)
	{
		if (this.statuteCitationCode == null || !this.statuteCitationCode.equals(aStatuteCitationCode))
		{
			markModified();
		}
		statuteCitationCode = aStatuteCitationCode;
	}
	/**
	* Sets the value of the statuteCitationType property.
	* @param aStatuteCitationType the new value of the statuteCitationType property
	*/
	public void setStatuteCitationType(String aStatuteCitationType)
	{
		if (this.statuteCitationType == null || !this.statuteCitationType.equals(aStatuteCitationType))
		{
			markModified();
		}
		statuteCitationType = aStatuteCitationType;
	}
	/**
	* Sets the value of the tjcCode property.
	* @param aTjcCode the new value of the tjcCode property
	*/
	public void setTjcCode(String aTjcCode)
	{
		if (this.tjcCode == null || !this.tjcCode.equals(aTjcCode))
		{
			markModified();
		}
		tjcCode = aTjcCode;
	}
	/**
	* Sets the value of the tjcLineNum property.
	* @param aTjcLineNum the new value of the tjcLineNum property
	*/
	public void setTjcLineNum(String aTjcLineNum)
	{
		if (this.tjcLineNum == null || !this.tjcLineNum.equals(aTjcLineNum))
		{
			markModified();
		}
		tjcLineNum = aTjcLineNum;
	}
	/**
	* Sets the value of the ucrCode property.
	* @param aUcrCode the new value of the ucrCode property
	*/
	public void setUcrCode(String aUcrCode)
	{
		if (this.ucrCode == null || !this.ucrCode.equals(aUcrCode))
		{
			markModified();
		}
		ucrCode = aUcrCode;
	}
	/**
	* Sets the value of the violentOffenseInd property.
	* @param aViolentOffenseInd the new value of the violentOffenseInd property
	*/
	public void setViolentOffenseInd(String aViolentOffenseInd)
	{
		if (this.violentOffenseInd == null || !this.violentOffenseInd.equals(aViolentOffenseInd))
		{
			markModified();
		}
		violentOffenseInd = aViolentOffenseInd;
	}
	/**
	* @roseuid 418FCF8E022E
	* Find OffenseCode by codeId
	* @param ncicCode
	* @return OffenseCode object
	*/
	public static OffenseCode find(String ncicCode)
	{
		OffenseCode offenseCode = null;
		IHome home = new Home();
		offenseCode = (OffenseCode) home.find(ncicCode, OffenseCode.class);
		return offenseCode;
	}
	/**
	 * Find all OffenseCode objects
	 * @return all OffenseCode objects
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(OffenseCode.class);
		return iter;

	}
	
	/*
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue,OffenseCode.class);
	}
	/**
	 * Find all OffenseCode objects that match search criteria
	 * @return all OffenseCode objects
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, OffenseCode.class);
		return iter;

	}
	/**
	 * @return
	 */
	public String getOffenseCodeId()
	{
		return this.getOID().toString();
	}

	/**
	 * @param string
	 */
	public void setOffenseCodeId(String string)
	{
		offenseCodeId = string;
	}
	/**
	 * @param deptEvent
	 * @param class1
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent anEvent) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(anEvent, OffenseCode.class);
		return iter;
	}	

}
