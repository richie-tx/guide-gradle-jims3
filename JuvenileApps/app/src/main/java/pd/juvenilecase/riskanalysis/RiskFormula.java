/**
 * 
 */
package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pd.codetable.Code;

import naming.PDConstants;
import naming.RiskAnalysisConstants;

import messaging.riskanalysis.GetNewFormulaVersionEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.CollectionUtil;

/** 
 * @author palcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskFormula extends PersistentObject 
{

	private String statusCd;
	private Code status;
	private Date activateDate;
	private String assessmentType;
	private String version;
	private String modificationReason;
	
	public RiskFormula() 
	{
		super();
	}

	public RiskFormula(RiskFormula rf){
		this.setAssessmentType(rf.getAssessmentType());
		this.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING);
		this.setCreateTimestamp(new Timestamp(new Date().getTime()));
		this.setVersion(rf.getNextVersion());
	}
	
	public String getStatusCd() 
	{
		fetch();
		return statusCd;
	}

	public void setStatusCd(String string) 
	{
		if (this.statusCd == null || !this.statusCd.equals(string)) 
		{
			markModified();
		}
		statusCd = string;
	}

	public Date getActivateDate() {
		fetch();
		return activateDate;
	}

	public void setActivateDate(Date date) 
	{
		if (this.activateDate == null || !this.activateDate.equals(date)) 
		{
			markModified();
		}
		activateDate = date;
	}
	

	public String getAssessmentType() 
	{
		fetch();
		return assessmentType;
	}
	
	public void setAssessmentType(String string) 
	{ 
		if (this.assessmentType == null || !this.assessmentType.equals(string)) 
		{
			markModified();
		}
		assessmentType = string;
	}

	public String getVersion() 
	{
		fetch();
		return version;
	}

	public void setVersion(String string) 
	{
		if (this.version == null || !this.version.equals(string)) 
		{
			markModified();
		}
		version = string;
	}
	public String getModificationReason() 
	{
		fetch();
		return modificationReason;
	}

	public void setModificationReason(String string) 
	{
		if (this.modificationReason == null || !this.modificationReason.equals(string)) 
		{
			markModified();
		}
		modificationReason = string;
	}
	
	private void initStatus() {
		if (status == null) {
			try {
				status = (Code) new mojo.km.persistence.Reference(
						statusCd, Code.class, RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusCd(PDConstants.BLANK + status.getOID());
		status.setContext(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS);
		this.status = (Code) new mojo.km.persistence.Reference(
				status).getObject();
	}

	
	public static List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter =  home.findAll(anEvent, RiskFormula.class);
		return CollectionUtil.iteratorToList(iter);
	}
	
	public FormulaResponseEvent getResponseEvent(){
		FormulaResponseEvent re = new FormulaResponseEvent();
		re.setActivateDate(this.getActivateDate());
		re.setAssessmentTypeCd(this.getAssessmentType());
		re.setModificationReason(this.getModificationReason());
		re.setStatus(this.getStatusCd());
		if (this.getStatus() != null){
			re.setStatusDesc(this.getStatus().getDescription());
		} 
		re.setUpdatable(this.isUpdatable());
		re.setVersion(this.getVersion());
		re.setFormulaId(this.getOID());
		re.setCategories(new ArrayList());
		re.setEntryDate(this.getCreateTimestamp());
		
		return re;
	}
	public boolean isUpdatable(){
		
		boolean isUpdatable = false;
		
		if (this.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE)){
			GetNewFormulaVersionEvent reqEvent = new GetNewFormulaVersionEvent();
			reqEvent.setAssessmentTypeCd(this.getAssessmentType());
			List pendingFormulas = RiskFormula.findAll(reqEvent);
			if (pendingFormulas.size() > 0){
				isUpdatable =  false;
			} else {
				isUpdatable = true;
			}
		} else if (this.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING)){
			isUpdatable = true;
		} else {
			isUpdatable = false;
		}
		
		return isUpdatable;
	}
	
	public String getNextVersion() {
		
		int newVersionNum = 1;
		
		if (this.version != null && !this.version.equals(PDConstants.BLANK)){
			int thisVersionNum = Integer.parseInt(this.version);
			newVersionNum = thisVersionNum + 1;
		}
		
		return Integer.toString(newVersionNum);
	}
	
	public static RiskFormula find(String oid){
		IHome home = new Home();
		return (RiskFormula) home.find(oid, RiskFormula.class);
	}

}