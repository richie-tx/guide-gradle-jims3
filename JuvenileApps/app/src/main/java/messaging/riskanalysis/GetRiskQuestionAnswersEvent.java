//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetRiskQuestionAnswersEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetRiskQuestionAnswersEvent extends RequestEvent 
{
   
	private String assessmentType = "";
	private String formulaId = "";
	
   /**
    * @roseuid 433D83C901F3
    */
   public GetRiskQuestionAnswersEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(final String string)
	{
		assessmentType = string;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public String getFormulaId() {
		return formulaId;
	}

}
