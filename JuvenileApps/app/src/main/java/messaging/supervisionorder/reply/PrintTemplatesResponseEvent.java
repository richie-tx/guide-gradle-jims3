/*
 * Created on Dec 5, 2005
 */
package messaging.supervisionorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
  */
public class PrintTemplatesResponseEvent extends ResponseEvent implements Comparable
{
	private String courtCategory;
	private String courtId;
	private String versionDate;
	private String printTemplateId;
	private String agencyID;
	private String versionNumber;
	private String orderType;
	private String versionType;
	private String orderTitle;
	private String templateLang;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		PrintTemplatesResponseEvent cre = (PrintTemplatesResponseEvent) arg0;
		/*int comparisonResult = 0;
		String newString = "";
		if (this.getPrintTemplateId() == null && cre.getPrintTemplateId().equals(printTemplateId))
		return 0;
		else 
		return 1;*/
        return this.getOrderTitle().compareTo(cre.getOrderTitle());
		
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
	public String getAgencyID()
	{
		return agencyID;
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
	public String getOrderType()
	{
		return orderType;
	}

	/**
	 * @return
	 */
	public String getPrintTemplateId()
	{
		return printTemplateId;
	}

	/**
	 * @return
	 */
	public String getTemplateLang()
	{
		return templateLang;
	}

	/**
	 * @return
	 */
	public String getOrderTitle()
	{
		return orderTitle;
	}

	/**
	 * @return
	 */
	public String getVersionDate()
	{
		return versionDate;
	}

	/**
	 * @return
	 */
	public String getVersionNumber()
	{
		return versionNumber;
	}

	/**
	 * @return
	 */
	public String getVersionType()
	{
		return versionType;
	}

	/**
	 * @param string
	 */
	public void setCourtCategory(String string)
	{
		courtCategory = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyID(String string)
	{
		agencyID = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderType(String string)
	{
		orderType = string;
	}

	/**
	 * @param string
	 */
	public void setPrintTemplateId(String string)
	{
		printTemplateId = string;
	}

	/**
	 * @param string
	 */
	public void setTemplateLang(String string)
	{
		templateLang = string;
	}

	/**
	 * @param string
	 */
	public void setOrderTitle(String string)
	{
		orderTitle = string;
	}

	/**
	 * @param string
	 */
	public void setVersionDate(String string)
	{
		versionDate = string;
	}

	/**
	 * @param string
	 */
	public void setVersionNumber(String string)
	{
		versionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string)
	{
		versionType = string;
	}

}
