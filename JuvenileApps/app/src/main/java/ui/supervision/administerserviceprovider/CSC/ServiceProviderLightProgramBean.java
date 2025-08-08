/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC;

import naming.PDCodeTableConstants;
import ui.common.SimpleCodeTableHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderLightProgramBean implements Comparable{
	
	private String programId="";
	private String programIdentifier="";
	private String programName="";
	private String programGroupId="";
	private String programGroupDesc="";
	private String programStatusId="";
	private String programStatusDesc="";
	private String serviceProviderId="";

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		ServiceProviderLightProgramBean myIn=(ServiceProviderLightProgramBean)o;
		try{
			return this.programIdentifier.compareTo(myIn.getProgramIdentifier());
		}
		catch(Exception e){
			return 0;
		}
	}
	/**
	 * @return Returns the programGroupDesc.
	 */
	public String getProgramGroupDesc() {
		return programGroupDesc;
	}
	/**
	 * @param programGroupDesc The programGroupDesc to set.
	 */
	public void setProgramGroupDesc(String programGroupDesc) {
		this.programGroupDesc = programGroupDesc;
	}
	/**
	 * @return Returns the programGroupId.
	 */
	public String getProgramGroupId() {
		return programGroupId;
	}
	/**
	 * @param programGroupId The programGroupId to set.
	 */
	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
		this.programGroupDesc="";
		programGroupDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP,programGroupId);
	}
	/**
	 * @return Returns the programId.
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
		
	}
	/**
	 * @return Returns the programIdentifier.
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programIdentifier The programIdentifier to set.
	 */
	public void setProgramIdentifier(String programIdentifier) {
		this.programIdentifier = programIdentifier;
	}
	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return Returns the programStatusDesc.
	 */
	public String getProgramStatusDesc() {
		return programStatusDesc;
	}
	/**
	 * @param programStatusDesc The programStatusDesc to set.
	 */
	public void setProgramStatusDesc(String programStatusDesc) {
		this.programStatusDesc = programStatusDesc;
	}
	/**
	 * @return Returns the programStatusId.
	 */
	public String getProgramStatusId() {
		return programStatusId;
	}
	/**
	 * @param programStatusId The programStatusId to set.
	 */
	public void setProgramStatusId(String programStatusId) {
		
		this.programStatusId = programStatusId;
		this.programStatusDesc="";
		programStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_STATUS,programStatusId);
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
}
