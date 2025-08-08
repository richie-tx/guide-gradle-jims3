package pd.supervision.administercompliance;

import java.util.Iterator;

import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import messaging.administercompliance.GetNCResponsesEvent;
import messaging.administercompliance.GetVRCommentsEvent;
import messaging.administercompliance.GetVRPreviousCourtCommentsEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import mojo.km.utilities.MessageUtil;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class ViolationReportUtility
{
    protected void postComments(String requestType, String ncResponseId) {
	    GetVRCommentsEvent vEvent = new GetVRCommentsEvent();
	    vEvent.setType(requestType);
	    vEvent.setNcResponseId(ncResponseId);
		Iterator vIter = Comment.findAll(vEvent);
		while(vIter.hasNext()){
			Comment c = (Comment) vIter.next();
			NCResponseResponseEvent response = c.getResponse();
			MessageUtil.postReply(response);					
		}

    }
    
    protected Comment getComments(String requestType, String ncResponseId) {
	    GetVRCommentsEvent vEvent = new GetVRCommentsEvent();
	    vEvent.setType(requestType);
	    vEvent.setNcResponseId(ncResponseId);
		Iterator vIter = Comment.findAll(vEvent);
		if(vIter.hasNext()){
			Comment c = (Comment) vIter.next();
			return c;		
		}
        return null;
    }
    
    protected Comment getComments(String requestType, String ncResponseId, String commentType) {
    	GetVRPreviousCourtCommentsEvent pEvent = new GetVRPreviousCourtCommentsEvent();
	    pEvent.setRequestType(requestType);
	    pEvent.setNcResponseId(ncResponseId);
	    pEvent.setCommentType(commentType);
		Iterator vIter = Comment.findAll(pEvent);
		if(vIter.hasNext()){
			Comment c = (Comment) vIter.next();
			return c;		
		}
        return null;
    }
    
    protected void setComments (String requestType, String ncResponseId, UpdateNCResponseEvent reqEvent) {
    	Comment comments = getComments(requestType, ncResponseId);
        if(comments == null){
        	comments = new Comment();
        }
   	    comments.setComments(reqEvent);	
    }  
    
	protected UpdateNCResponseEvent createViolationReport(UpdateNCResponseEvent reqEvent) {
		if(reqEvent.getNcResponseId() == null || reqEvent.getNcResponseId().equals("")){
			GetNCResponsesEvent gEvent =  new GetNCResponsesEvent();
			gEvent.setCriminalcaseId(reqEvent.getCaseId());
			gEvent.setReportType(reqEvent.getReportType());
			ViolationReport vr = null;
			Iterator iter = ViolationReport.findAll(gEvent);
			while(iter.hasNext()){
				vr = (ViolationReport) iter.next();
				if( "CD".equalsIgnoreCase(vr.getStatusId())){
					vr = null;
				}
				else if(!"FL".equalsIgnoreCase(vr.getStatusId())){
					break;
				}else{
					vr = null;
				}
			}			
			
			if(vr == null){
				vr = new ViolationReport();
				reqEvent.setNcResponseId(vr.create(reqEvent));
			}else{
				reqEvent.setNcResponseId(vr.getOID());
			}
			MessageUtil.postReply(vr.getResponseEvent());	
		}
		return reqEvent;
	}

	public void setComments(String requestType, String ncResponseId, String comments, String commentsType) {
	    	Comment c = getComments(requestType, ncResponseId,commentsType);
	        if(c == null){
	        	c = new Comment();
	        }else if(StringUtils.isNotEmpty(commentsType)){
	        	c = new Comment();
	        }
	        c.setComments(ncResponseId,requestType,comments,commentsType);
    }
}
