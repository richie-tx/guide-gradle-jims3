package ui.juvenilecase.casefile.form;

import java.util.ArrayList;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;

public class CasefileDocumentsForm extends ActionForm{
	private ArrayList documents;
	private String juvenileId;
	private String casefileId;
	private String errorMsg;
	
	public CasefileDocumentsForm()
	{
		clearAll();
	}

	public void clearAll()
	{
		this.documents = new ArrayList();
		this.juvenileId = UIConstants.EMPTY_STRING;
		this.casefileId = UIConstants.EMPTY_STRING;
		this.errorMsg = UIConstants.EMPTY_STRING;
	}
	
	/**
	 * @return the documents
	 */
	public ArrayList getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(ArrayList documents) {
		this.documents = documents;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
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
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
