package messaging.casefile.reply;

import java.util.Comparator;
import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author rcarter
 * This class gathers different CasefileDoc types into a common bean to show on prod support delete casefile docs screens
 */
public class CasefileDocResponse
{
	private String documentId;
	private String tableName;
	private String caseNonConNoteId;
	private String docTypeCd;
	
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;


	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the caseNonConNoteId
	 */
	public String getCaseNonConNoteId() {
		return caseNonConNoteId;
	}

	/**
	 * @param caseNonConNoteId the caseNonConNoteId to set
	 */
	public void setCaseNonConNoteId(String caseNonConNoteId) {
		this.caseNonConNoteId = caseNonConNoteId;
	}

	/**
	 * @return the docTypeCd
	 */
	public String getDocTypeCd() {
		return docTypeCd;
	}

	/**
	 * @param docTypeCd the docTypeCd to set
	 */
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * @return
	 */
	public String getUpdateUser()
	{
		return updateUser;
	}
	/**
	 * @param string
	 */
	public void setUpdateUser(String string)
	{
		updateUser = string;
	}
	
	/**
	 * @return
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}
	/**
	 * @param date
	 */
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	
	/**
	 * @return
	 */

	public String getCreateJIMS2UserID()
	{
		return createJIMS2UserID;
	}
	
	/**
	 * @param string
	 */

	public void setCreateJIMS2UserID(String string)
	{
		createJIMS2UserID = string;
	}
	
	/**
	 * @return
	 */

	public String getUpdateJIMS2UserID()
	{
		return updateJIMS2UserID;
	}
	/**
	 * @param string
	 */

	public void setUpdateJIMS2UserID(String string)
	{
		updateJIMS2UserID = string;
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
	
	public static Comparator CasefileDocResponseDocumentIdComparator = new Comparator() {
		public int compare(Object response1, Object response2) {
			if (response1==null || ! (response1 instanceof CasefileDocResponse))
				return 0;		
			if (response2==null || ! (response2 instanceof CasefileDocResponse))
				return 0;			
			
		  Integer documentId1 = new Integer(((CasefileDocResponse)response1).getDocumentId());
		  Integer documentId2 = new Integer(((CasefileDocResponse)response2).getDocumentId());
		  
		  return documentId1.compareTo(documentId2);
		}	
	};
	
}
