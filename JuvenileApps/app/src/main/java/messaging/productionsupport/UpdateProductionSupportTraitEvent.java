package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportTraitEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId;
	private String juvenileTraitId;
	private String juvenileNum;
	private String supervisionNum;
	private String traitTypeId;
	private String traitStatus;
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportTraitEvent() 
   {
    
   }
   
   /**
	 * @return
	 */
	public String getJuvenileTraitId()
	{
		return juvenileTraitId;
	}

	/**
	 * @param string
	 */
	public void setJuvenileTraitId(String string)
	{
		juvenileTraitId = string;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}
   

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	
	/**
	 * @return the mergeToCasefileId
	 */
	public String getMergeToCasefileId() {
		return mergeToCasefileId;
	}
	
	/**
	 * @param mergeToCasefileId the mergeToCasefileId to set
	 */
	public void setMergeToCasefileId(String mergeToCasefileId) {
		this.mergeToCasefileId = mergeToCasefileId;
	}
	
	/**
	     * @return the traitTypeId
	     */
	    public String getTraitTypeId() {
	        return traitTypeId;
	    }

	    /**
	     * @param traitTypeId the traitTypeId to set
	     */
	    public void setTraitTypeId(String traitTypeId) {
	        this.traitTypeId = traitTypeId;
	    }
	    
	    /**
	     * @return the traitTypeId
	     */
	    public String getTraitStatus() {
	        return traitStatus;
	    }

	    /**
	     * @param traitTypeId the traitTypeId to set
	     */
	    public void setTraitStatus(String traitStatus) {
	        this.traitStatus = traitStatus;
	    }
   
}
