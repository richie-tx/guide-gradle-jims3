/*
 * Created on Nov 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CheckProgressPreConditionsEvent  extends RequestEvent
{
	private String caseFileId;
	private String juvenileNumber;
	private Date assessmentDate;
	private boolean bypassPreconditionEdit;
	private String formulaId;
	private String formula;
	   
   /**
	* @roseuid 4357DCB40125
	*/
   public CheckProgressPreConditionsEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getCaseFileId()
	{
		return caseFileId;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setCaseFileId(final String string)
	{
		caseFileId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(final String string)
	{
		juvenileNumber = string;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}
	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public boolean isBypassPreconditionEdit() {
		return bypassPreconditionEdit;
	}

	public void setBypassPreconditionEdit(boolean bypassPreconditionEdit) {
		this.bypassPreconditionEdit = bypassPreconditionEdit;
	}		
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}
