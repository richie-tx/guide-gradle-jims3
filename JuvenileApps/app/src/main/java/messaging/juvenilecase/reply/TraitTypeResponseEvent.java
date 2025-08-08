/*
 * Created on Jun 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TraitTypeResponseEvent  extends ResponseEvent implements Comparable
{
	private String parentTraitId;
	private String parentName;
	private String traitTypeId;
	private String traitName;
	private String riskPoints;
	private String status;

	/**
	 * @return
	 */
	public String getParentTraitId()
	{
		return parentTraitId;
	}

	/**
	 * @return
	 */
	public String getTraitName()
	{
		return traitName;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @param string
	 */
	public void setParentTraitId(String string)
	{
		parentTraitId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitName(String string)
	{
		this.traitName = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @return
	 */
	public String getRiskPoints()
	{
		return riskPoints;
	}

	/**
	 * @param string
	 */
	public void setRiskPoints(String string)
	{
		riskPoints = string;
	}

	/**
	 * @return
	 */
	public String getParentName()
	{
		return parentName;
	}

	/**
	 * @param string
	 */
	public void setParentName(String string)
	{
		parentName = string;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		if(arg0==null)
			return 1;
		TraitTypeResponseEvent aIncomingTraitObj=(TraitTypeResponseEvent)arg0;
		return this.getTraitName().compareTo(aIncomingTraitObj.getTraitName());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
