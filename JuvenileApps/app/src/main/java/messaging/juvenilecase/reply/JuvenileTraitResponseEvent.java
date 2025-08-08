/*
 * Created on Jun 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author rcarter
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileTraitResponseEvent extends ResponseEvent implements Comparable
{
	private String juvenileTraitId;
	private String traitTypeName;
	private String traitTypeDescription;
	private Date entryDate;
	private String traitComments;
	private String traitTypeId;
	private String parentTypeId;
	private String juvenileNum;
	private String dispositionNum;
	private String supervisionNum;
	private String statusId;
	private String status;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	//added for US 42660 - Facility
	private String facilityAdmitOID;
	//added for task 128545
	private String transferAdmitOID;
	

	//added for US 40635
	private String informationSrcCd;
	private String informationSrcDesc;

	/**
	 * @return
	 */
	public String getDispositionNum()
	{
		return dispositionNum;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	public String getEntryDateString()
	{
		String dateString = "";
		SimpleDateFormat fmt = new SimpleDateFormat("M/d/yyyy");
		if (this.entryDate != null)
		{
			dateString = fmt.format(this.entryDate);
		}
		return dateString;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @return
	 */
	public String getTraitComments()
	{
		return traitComments;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @return
	 */
	public String getTraitTypeName()
	{
		return traitTypeName;
	}

	/**
	 * @param string
	 */
	public void setDispositionNum(String string)
	{
		dispositionNum = string;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}

	/**
	 * @param string
	 */
	public void setTraitComments(String string)
	{
		traitComments = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeName(String string)
	{
		traitTypeName = string;
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
	public String getTraitTypeDescription()
	{
		return traitTypeDescription;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeDescription(String string)
	{
		traitTypeDescription = string;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.entryDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		JuvenileTraitResponseEvent evt = (JuvenileTraitResponseEvent)o;
		return evt.getEntryDate().compareTo(entryDate);
	}
	
	/**
	 * @return the parentTypeId
	 */
	public String getParentTypeId() {
		return parentTypeId;
	}

	/**
	 * @param parentTypeId the parentTypeId to set
	 */
	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public String getFacilityAdmitOID() {
		return facilityAdmitOID;
	}

	public void setFacilityAdmitOID(String facilityAdmitOID) {
		this.facilityAdmitOID = facilityAdmitOID;
	}

	public String getInformationSrcCd() {
		return informationSrcCd;
	}

	public void setInformationSrcCd(String informationSrcCd) {
		this.informationSrcCd = informationSrcCd;
	}

	public String getInformationSrcDesc() {
		return informationSrcDesc;
	}

	public void setInformationSrcDesc(String informationSrcDesc) {
		this.informationSrcDesc = informationSrcDesc;
	}
	public String getTransferAdmitOID()
	{
	    return transferAdmitOID;
	}

	public void setTransferAdmitOID(String transferAdmitOID)
	{
	    this.transferAdmitOID = transferAdmitOID;
	}
	/**
	 * sort based on the oid
	 */
	public static Comparator JuvenileTraitIdComparator = new Comparator() {
		public int compare(Object trait, Object alternativeTrait){
			int compareResult = 0;
			JuvenileTraitResponseEvent traitEvent1 = ((JuvenileTraitResponseEvent)trait);
			JuvenileTraitResponseEvent traitEvent2 = ((JuvenileTraitResponseEvent)alternativeTrait);
			if(traitEvent1 == null || traitEvent2 == null){
				return compareResult;
			}
			String traitId1 = traitEvent1.getJuvenileTraitId();
			String traitId2 = traitEvent2.getJuvenileTraitId();
			Integer traitIntegerId1 = Integer.valueOf(traitId1);
			Integer traitIntegerId2 = Integer.valueOf(traitId2);

			compareResult = traitIntegerId1.compareTo(traitIntegerId2);

			return compareResult;
		}
	};
}
