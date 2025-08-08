//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\GetSuggestedOrderConditionsEvent.java

package messaging.suggestedorder;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class GetConditionsForSuggestedOrderEvent extends RequestEvent 
{
   private String agencyId;
   private String conditionLiteral;
   private String conditionName;
   private String conditionSubType;
   private String conditionSubTypeDetail;
   private String conditionType; 
   private Collection courts;
   private String jurisdiction;
   private boolean limitSearchResults;
   private String standardInd;
   
   /**
    * @roseuid 433AF2760075
    */
   public GetConditionsForSuggestedOrderEvent() 
   {
    
   }

/**
 * @return
 */
public String getAgencyId()
{
	return agencyId;
}

/**
 * @return
 */
public String getConditionLiteral()
{
	return conditionLiteral;
}
   
/**
 * @return
 */
public String getConditionName()
{
	return conditionName;
}

/**
 * @return
 */
public String getConditionSubType()
{
	return conditionSubType;
}

/**
 * @return
 */
public String getConditionSubTypeDetail()
{
	return conditionSubTypeDetail;
}

/**
 * @return
 */
public String getConditionType()
{
	return conditionType;
}

/**
 * @return
 */
public Collection getCourts()
{
	return courts;
}

/**
 * @return
 */
public String getJurisdiction()
{
	return jurisdiction;
}

/**
 * @return
 */
public String getStandardInd()
{
	return standardInd;
}

public boolean isLimitSearchResults() {
	return limitSearchResults;
}

/**
 * @param anAgencyId
 */
public void setAgencyId(String anAgencyId)
{
	agencyId = anAgencyId;
}

/**
 * @param aString
 */
public void setConditionLiteral(String aString)
{
	conditionLiteral = aString;
}

/**
 * @param aString
 */
public void setConditionName(String aString)
{
	conditionName = aString;
}

/**
 * @param aString
 */
public void setConditionSubType(String aString)
{
	conditionSubType = aString;
}

/**
 * @param aString
 */
public void setConditionSubTypeDetail(String aString)
{
	conditionSubTypeDetail = aString;
}

/**
 * @param aString
 */
public void setConditionType(String aString)
{
	conditionType = aString;
}

/**
 * @param aCourts
 */
public void setCourts(Collection aCourts)
{
	courts = aCourts;
}

/**
 * @param aString
 */
public void setJurisdiction(String aString)
{
	jurisdiction = aString;
}

public void setLimitSearchResults(boolean limitSearchResults) {
	this.limitSearchResults = limitSearchResults;
}

/**
 * @param string
 */
public void setStandardInd(String string)
{
	standardInd = string;
}

}
