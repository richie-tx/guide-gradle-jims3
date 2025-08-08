/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class CourtResponseEvent extends ResponseEvent implements ICode,Comparable
{
	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	
	private static final String CC = "County Criminal Courts";
	private static final String CR = "District Courts";
	private static final String OC = "Other Courts";
	private static final String CV = "Civil Courts";
	private static final String FAM = "Family Courts";
	private static final String JP = "JP Courts";
	private static final String JUV = "Juvenile Courts";
	
	 
	private String courtCategory;
	private String courtCategoryDesc;
	private String courtId;
	private String courtNumber;
	private String description;
	private boolean exceptionCourt;
	private boolean isSelected;
	private String judgeFirstName;
	private String judgeLastName;
	private boolean courtInUse=false;

	public String getCode() {
		// TODO Auto-generated method stub
		return courtNumber;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		CourtResponseEvent cre = (CourtResponseEvent) arg0;
		if(this.courtCategory.equals(cre.getCourtCategory())){
			String court1 = this.prependWithZeros(this.getCourtNumber(), 3);
			String court2 = this.prependWithZeros(cre.getCourtNumber(), 3);
			return court1.compareTo(court2);
		}
		else{
			return courtCategory.compareTo(cre.getCourtCategory());
		}
	}

	/**
	 * @return
	 */
	public String getCourtCategory()
	{
		return courtCategory;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @return
	 */
	public String getCourtNumber()
	{
		return courtNumber;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @return
	 */
	public boolean getIsSelected()
	{
		return isSelected;
	}

	/**
	 * @return
	 */
	public String getJudgeFirstName()
	{
		return judgeFirstName;
	}

	/**
	 * @return
	 */
	public String getJudgeLastName()
	{
		return judgeLastName;
	}

	/**
	 * @return
	 */
	public boolean isExceptionCourt()
	{
		return exceptionCourt;
	}
	/**
	 * @param cre
	 * @return
	 */
	private String prependWithZeros(String theField, int newFieldLength)
	{

		StringBuffer sb = new StringBuffer(theField);
		if (theField.length() < newFieldLength)
		{
			for (int i = 0; i < newFieldLength - theField.length(); i++)
			{
				sb.insert(i, "0");
			}
		}
		return sb.toString();
	}

	/**
	 * @param string
	 */
	public void setCourtCategory(String string)
	{
		courtCategory = string;
		// set  court desc
		if(courtCategory.equals("CC"))
		{
			courtCategoryDesc = CC;
		}else if(courtCategory.equals("CR"))
		{
			courtCategoryDesc = CR;
		}else if(courtCategory.equals("OC"))
		{
			courtCategoryDesc = OC;
		}else if(courtCategory.equals("CV"))
		{
			courtCategoryDesc = CV;
		}else if(courtCategory.equals("FAM"))
		{
			courtCategoryDesc = FAM;
		}else if(courtCategory.equals("JP"))
		{
			courtCategoryDesc = JP;
		}else if(courtCategory.equals("JUV"))
		{
			courtCategoryDesc = JUV;
		} 
	}

	/**
	 * @param aCourtId
	 */
	public void setCourtId(String aCourtId)
	{
		courtId = aCourtId;
	}

	/**
	 * @param string
	 */
	public void setCourtNumber(String string)
	{
		courtNumber = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @param b
	 */
	public void setExceptionCourt(boolean b)
	{
		exceptionCourt = b;
	}

	/**
	 * @param b
	 */
	public void setIsSelected(boolean b)
	{
		isSelected = b;
	}

	/**
	 * @param string
	 */
	public void setJudgeFirstName(String string)
	{
		judgeFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setJudgeLastName(String string)
	{
		judgeLastName = string;
	}

	/**
	 * @return
	 */
	public String getCourtCategoryDesc()
	{
		return courtCategoryDesc;
	}

	/**
	 * @param string
	 */
	public void setCourtCategoryDesc(String string)
	{
		courtCategoryDesc = string;
	}

	public boolean isCourtInUse() {
		return courtInUse;
	}
	
	public void setCourtInUse(boolean courtInUse) {
		this.courtInUse = courtInUse;
	}
}