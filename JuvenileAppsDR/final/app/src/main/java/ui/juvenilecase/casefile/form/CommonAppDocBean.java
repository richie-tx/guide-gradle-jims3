/*
 * Created on Nov 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import java.util.Date;

import messaging.casefile.reply.CommonAppDocResponseEvent;

import ui.common.CodeHelper;
import ui.common.CodeTableHelper;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonAppDocBean {
	private String commonAppDocId;
	private Date createDate;
	private Object document;
	private String casefileId;
	private String docTypeCd;
	
	public CommonAppDocBean(CommonAppDocResponseEvent re) {
		setValues(re);
	}
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public String getCommonAppDocId() {
		return commonAppDocId;
	}
	public void setCommonAppDocId(String commonAppDocId) {
		this.commonAppDocId = commonAppDocId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public Object getDocument() {
		return document;
	}
	public void setDocument(Object document) {
		this.document = document;
	}
	
	public String getDocTypeDescription() {
		return CodeHelper.getCodeDescription("DOCUMENT_TYPE", docTypeCd);
	}
	
	public void setValues(CommonAppDocResponseEvent re) {
		this.setCasefileId(re.getCasefileId());
		this.setCommonAppDocId(re.getCommonAppDocId());
		this.setCreateDate(re.getCreateDate());
		this.setDocTypeCd(re.getDocTypeCd());
		this.setDocument(re.getDocument());
	}
}
