//Source file: C:\\VIEWS\\SECURITY\\APP\\SRC\\ui\\security\\authentication\\action\\DisplayCreateJIMS2AccountSummaryAction.java

package ui.notification.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.notification.GetNoticeEvent;
import messaging.notification.reply.NotificationResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.form.LoginForm;

public class DisplayNoticeMessageTaskAction extends LookupDispatchAction
{
   private static Pattern DATE_PATTERN = Pattern.compile("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d ([at]{0,1}) (0[0-9]|1[0-9]|2[0-3])[:{1}]([0-5][0-9])");
   private static Pattern TIME_PATTERN = Pattern.compile("([0-9]|0[0-9]|1[0-9]|2[0-3])[:{1}]([0-5][0-9]) (PM|AM)");	   
   
   /**
    * @roseuid 4399BB120155
    */
   protected Map getKeyMethodMap()
	{
	 Map keyMap = new HashMap();
	 keyMap.put("button.link", "showMessage");
	 return keyMap;
	}
   public DisplayNoticeMessageTaskAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A90022
    */
   public ActionForward showMessage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		LoginForm loginForm = (LoginForm)aForm;
		GetNoticeEvent notice = new GetNoticeEvent();
		notice.setNotificationId(loginForm.getNotificationId());
	    List list = MessageUtil.postRequestListFilter(notice, NotificationResponseEvent.class);
	    if(list != null && !list.isEmpty()){
		    NotificationResponseEvent resp = (NotificationResponseEvent) list.get(0);
		    loginForm.setNoticeMessage(resp.getNotification().getMessage());
			//loginForm.setNoticeMessage(formatMessage(resp.getNotification().getMessage()));
		}
		return aMapping.findForward("success");
   }
   
   /**
    * Helper method to format notifications before they are displayed
    * @param message
    * @return
    */
   private static String formatMessage(String message){
	   if(message == null){
		   return "";
	   }
	   Matcher dateMatcher = null; 
	   Matcher timeMatcher = null;
	   String[] strArray = message.split("\\.");
	   StringBuilder sb = new StringBuilder();
	   sb.append("<ol type=\"A\">");
	   for(int i=0; i<strArray.length; i++){
		   strArray[i] = strArray[i].trim();
		   dateMatcher = DATE_PATTERN.matcher(strArray[i]);
		   timeMatcher = TIME_PATTERN.matcher(strArray[i]);
		   if((strArray[i].indexOf("TO:") > -1) && (strArray[i].indexOf("Please") > -1)){
			   String tempStr = strArray[i].substring(0,strArray[i].indexOf("Please"));
			   sb.delete(0, sb.length());
			   sb.append("<h4>"+tempStr+"</h4><br/>");
			   sb.append("<ol type=\"1\">");
			   sb.append("<li>"+strArray[i].substring(strArray[i].indexOf("Please"),strArray[i].length()) +"</li><br/>");
			   
		   } else if(strArray[i].indexOf(":") > -1 && dateMatcher.find() == false && timeMatcher.find() == false){
			   
			   String tempStr = strArray[i].substring(strArray[i].indexOf(":")+1,strArray[i].length());
			   strArray[i] = strArray[i].substring(0, strArray[i].indexOf(":"));
			   
			   sb.append("<li>"+strArray[i]+"</li><br/>");
			   sb.append("<ol><li>"+tempStr+"</li><br/>");
			   // Read ahead to make sure that there aren't any more sub-lists
			   for(int j=(i+1); j<strArray.length; j++){
				   if(strArray[j].indexOf("Please") > -1){
					   break;
				   } else {
					   i = i+1;
					   sb.append("<li>"+strArray[j]+"</li><br/>");
				   }
			   }
			   sb.append("</ol><br/>");				   
			   
		   } else {
			   sb.append("<li>"+strArray[i]+"</li><br/>");
		   }
		   dateMatcher = null;
		   timeMatcher = null;
		   

	   }
	   sb.append("</ol>");
	   return sb.toString();
	   
   }
}
