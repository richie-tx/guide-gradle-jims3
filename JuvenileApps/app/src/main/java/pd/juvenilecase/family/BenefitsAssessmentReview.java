package pd.juvenilecase.family;

import java.util.Date;

import mojo.km.persistence.PersistentObject;

public class BenefitsAssessmentReview extends PersistentObject 
{
	private String benefitsAssessmentId;
    private String comments;
    private Date entryDate;
   
   /**
    * @roseuid 4371001F03BC
    */
   public BenefitsAssessmentReview() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getBenefitsAssessmentId()
	{
		fetch();
		return benefitsAssessmentId;
	}
	
	/**
	 * @return
	 */
	public String getComments()
	{
		fetch();
		return comments;
	}
	
	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}
	
	/**
	 * @param string
	 */
	public void setBenefitsAssessmentId(String string)
	{
		markModified();
		benefitsAssessmentId = string;
	}
	
	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		markModified();
		comments = string;
		
		setEntryDate( new Date() );
	}
	
	
	
	/**
	 * PRIVATE - for internal use only.
	 * 
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		markModified();
		entryDate = date;
	}
	
}
