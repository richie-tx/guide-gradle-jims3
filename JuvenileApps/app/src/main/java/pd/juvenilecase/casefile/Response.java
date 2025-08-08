package pd.juvenilecase.casefile;

import java.util.Iterator;

import messaging.casefile.UpdateResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;

/**
* @author mchowdhury
*/
public class Response extends PersistentObject {
	private String questionId;
	private String answerText;
	private String responseId;
	private String referenceId;
	private String responseType;
	
	/**
	* @roseuid 439602DC0147
	*/
	public Response() {
	}

	/**
	* @return 
	*/
	public String getAnswerText() {
		fetch();
		return answerText;
	}
	/**
	* @return 
	*/
	public String getQuestionId() {
		fetch();
		return questionId;
	}

	/**
	* @param string
	*/
	public void setAnswerText(String string) {
		if (this.answerText == null || !this.answerText.equals(string)) {
			markModified();
		}
		answerText = string;
	}
	/**
	* @param string
	*/
	public void setQuestionId(String string) {
		if (this.questionId == null || !this.questionId.equals(string)) {
			markModified();
		}
		questionId = string;
	}
	
	/**
	 * @return
	 */
	public String getReferenceId()
	{
		return referenceId;
	}

	/**
	 * @return
	 */
	public String getResponseId()
	{
		return responseId;
	}

	/**
	 * @param string
	 */
	public void setReferenceId(String string)
	{
		fetch();
		referenceId = string;
	}

	/**
	 * @param string
	 */
	public void setResponseId(String string)
	{
		if (this.responseId == null || !this.responseId.equals(string)) {
			markModified();
		}
		responseId = string;
	}

	/**
	 * @return
	 */
	public String getResponseType()
	{
		fetch();
		return responseType;
	}

	/**
	 * @param string
	 */
	public void setResponseType(String string)
	{
		if (this.responseType == null || !this.responseType.equals(string)) {
			markModified();
		}
		responseType = string;
	}
	
	/**
	 * @param oid
	 * @return
	 */
	public static Response find(String oid)
	{
		Response response = null;
		IHome home = new Home();
		response = (Response) home.find(oid, Response.class);
		return response;
	}
	
   /**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator iter = home.findAll(event, Response.class);
		return iter;
	}
	
	/**
	 * @param requestEvent
	 * @return Response
	 */
	public void setResponse(UpdateResponseEvent requestEvent)
	{
		this.setAnswerText(requestEvent.getAnswerText());
		this.setQuestionId(requestEvent.getQuestionId());
		this.setResponseType(requestEvent.getResponseType());
		this.setReferenceId(requestEvent.getReferenceId());
		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		String loginId = "";
		if(manager != null){
			loginId = manager.getIUserInfo().getJIMSLogonId();
		}
		this.setCreateUserID(loginId);
	}

}
