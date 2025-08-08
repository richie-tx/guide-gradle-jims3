package pd.juvenilecase.riskanalysis;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 433D86E401BA
*/
public class RiskResponse extends PersistentObject {
	
	private int riskResponseId;
	private String riskAnalysisId;
	private String weightedResponseID;
	private String text;
	
	/**
	* @roseuid 433D86E401BA
	*/
	public RiskResponse() {
	}
	/**
	* @return RiskResponse
	* @param questionId
	*/
	static public RiskResponse find(String assessmentId) {
		IHome home = new Home();
		RiskResponse riskQues = (RiskResponse) home.find(assessmentId, RiskQuestions.class);
		return riskQues;
	}
	/**
	* Finds RiskResponse by an event
	* @return Iterator of RiskResponse
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator responses = home.findAll(event, RiskResponse.class);
		return responses;
	}
	/**
	* Finds all RiskResponse by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator responses = home.findAll(attributeName, attributeValue, RiskResponse.class);
		return responses;
	}
	/**
	* @return 
	*/
	public String getText() {
		fetch();
		return text;
	}
	/**
	* @param string
	*/
	public void setText(String string) {
		if (this.text == null || !this.text.equals(string)) {
			markModified();
		}
		text = string;
	}
	/**
	* @return 
	*/
	public int getRiskResponseId() {
		return Integer.parseInt((String)this.getOID());
	}
	/**
	* @param i
	*/
	public void setRiskResponseId(int i) {
		if (this.riskResponseId != i) {
			markModified();
		}
		riskResponseId = i;
	}
	/**
	 * @return
	 */
	public String getRiskAnalysisId()
	{
		return riskAnalysisId;
	}

	/**
	 * @param string
	 */
	public void setRiskAnalysisId(String string)
	{
		riskAnalysisId = string;
	}
	/**
	 * @return weightedResponseID
	 */
	public String getWeightedResponseID() {
		return weightedResponseID;
	}
	/**
	 * @param weightedResponseID
	 */
	public void setWeightedResponseID(String weightedResponseID) {
		this.weightedResponseID = weightedResponseID;
	}
	
}
