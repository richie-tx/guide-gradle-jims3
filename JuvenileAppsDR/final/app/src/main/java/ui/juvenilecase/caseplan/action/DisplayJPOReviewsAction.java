/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.caseplan.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GetJPOReviewReportsEvent;
import messaging.caseplan.reply.JPOReviewReportResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * @author jjose
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayJPOReviewsAction extends JIMSBaseAction
{

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.link", "link");
        keyMap.put("button.view", "view");
    }

    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        form.setJpoReviews(null);
        GetJPOReviewReportsEvent evt = new GetJPOReviewReportsEvent();
        evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId());
        evt.setCasefileId(form.getCasefileId());        
        List reviews = MessageUtil.postRequestListFilter(evt, JPOReviewReportResponseEvent.class);
        form.setJpoReviews(reviews);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        String reviewId = form.getSelectedValue();
        if (reviewId != null && !reviewId.equals("") && form.getJpoReviews() != null && form.getJpoReviews().size() > 0)
        {
            Iterator iter = form.getJpoReviews().iterator();
            while (iter.hasNext())
            {
                JPOReviewReportResponseEvent myRespEvt = (JPOReviewReportResponseEvent) iter.next();
                if (myRespEvt.getCaseplanRevId().equals(reviewId))
                {
                    if (myRespEvt.getReport() != null)
                    {
                        String fileName = "JPOReview_" + reviewId;
                        try
                        {
                            setPrintContentResp(aResponse, (byte[]) myRespEvt.getReport(), fileName,
                                    UIConstants.PRINT_AS_PDF_DOC);
                            return null;
                        }
                        catch (Exception e)
                        {
                            return aMapping.findForward(UIConstants.SUCCESS);
                        }
                    }
                }
            }
        }

        return aMapping.findForward(UIConstants.SUCCESS);

    }

}
