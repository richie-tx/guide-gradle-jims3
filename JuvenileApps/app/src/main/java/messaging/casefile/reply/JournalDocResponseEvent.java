/*
 * Created on Oct 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author rcarter
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JournalDocResponseEvent extends ResponseEvent implements Comparable{
	public static final String JOURNAL_CASE_REVIEW = "JCR";
	
	private String journalDocId;
	private Object document;
	private String casefileId;
	private String docTypeCd;
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	
	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Object getDocument() {
		return document;
	}
	public void setDocument(Object document) {
		this.document = document;
	}
	public String getJournalDocId() {
		return journalDocId;
	}
	public void setJournalDocId(String journalDocId) {
		this.journalDocId = journalDocId;
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
		if (o == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.createDate == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        JournalDocResponseEvent evt = (JournalDocResponseEvent) o;
        return evt.getCreateDate().compareTo(createDate);
	}
}
