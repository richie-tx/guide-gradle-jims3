//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateCasefileClosingEvent.java

package messaging.casefile;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateResidentialExitPlanEvent extends RequestEvent 
{
	 private String facilityReleaseReasonId;
	 private String facilityId;
	 private String levelOfCareId;
	 private String casefileClosingStatus;
	 private String permanencyPlanId;
	 private Date expectedReleaseDate = null;
	 private String casefileClosingInfoId;
	 private String specialNotes;
	 private String exitPlanTemplateLocation;
	 
	 private Collection responses;

   
   /**
    * @roseuid 439601E001BB
    */
   public UpdateResidentialExitPlanEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getCasefileClosingInfoId()
	{
		return casefileClosingInfoId;
	}

	/**
	 * @return
	 */
	public String getCasefileClosingStatus()
	{
		return casefileClosingStatus;
	}

	/**
	 * @return
	 */
	public Date getExpectedReleaseDate()
	{
		return expectedReleaseDate;
	}

	/**
	 * @return
	 */
	public String getFacilityId()
	{
		return facilityId;
	}

	/**
	 * @return
	 */
	public String getFacilityReleaseReasonId()
	{
		return facilityReleaseReasonId;
	}

	/**
	 * @return
	 */
	public String getLevelOfCareId()
	{
		return levelOfCareId;
	}

	/**
	 * @return
	 */
	public String getPermanencyPlanId()
	{
		return permanencyPlanId;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingInfoId(String string)
	{
		casefileClosingInfoId = string;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingStatus(String string)
	{
		casefileClosingStatus = string;
	}

	/**
	 * @param date
	 */
	public void setExpectedReleaseDate(Date date)
	{
		expectedReleaseDate = date;
	}

	/**
	 * @param string
	 */
	public void setFacilityId(String string)
	{
		facilityId = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityReleaseReasonId(String string)
	{
		facilityReleaseReasonId = string;
	}

	/**
	 * @param string
	 */
	public void setLevelOfCareId(String string)
	{
		levelOfCareId = string;
	}

	/**
	 * @param string
	 */
	public void setPermanencyPlanId(String string)
	{
		permanencyPlanId = string;
	}

	/**
	 * @return
	 */
	public String getExitPlanTemplateLocation()
	{
		return exitPlanTemplateLocation;
	}

	/**
	 * @return
	 */
	public String getSpecialNotes()
	{
		return specialNotes;
	}

	/**
	 * @param string
	 */
	public void setExitPlanTemplateLocation(String string)
	{
		exitPlanTemplateLocation = string;
	}

	/**
	 * @param string
	 */
	public void setSpecialNotes(String string)
	{
		specialNotes = string;
	}

	/**
	 * @return
	 */
	public Collection getResponses()
	{
		return responses;
	}

	/**
	 * @param collection
	 */
	public void setResponses(Collection collection)
	{
		responses = collection;
	}

}
