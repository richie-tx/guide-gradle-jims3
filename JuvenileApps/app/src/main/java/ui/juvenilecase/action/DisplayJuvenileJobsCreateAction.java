/*
 * Created on Jun 24, 2005
 *
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.AbusePerpetrator;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileJobForm;

/**
 * @author sprakash
 *
 */
public class DisplayJuvenileJobsCreateAction extends Action
{

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.SUCCESS;

		JuvenileJobForm juvjobForm = (JuvenileJobForm) aForm;
		juvjobForm.clear();
		// Get family members
		GetActiveFamilyConstellationEvent event = (GetActiveFamilyConstellationEvent) EventFactory
            .getInstance(JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
		event.setJuvenileNum(juvjobForm.getJuvenileId());
		
    // Getting PD Response Event
    CompositeResponse response = UIJuvenileHelper.getCompositeResponse(event);
    // Perform Error handling
    MessageUtil.processReturnException(response);
    Map dataMap = MessageUtil.groupByTopic(response);
    ArrayList myList=new ArrayList();
    juvjobForm.setActiveFamMembers(myList);
    if (dataMap != null)
    {
        Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
        if (families != null && families.size() > 0)
        {
            Iterator myIter = families.iterator();
            while (myIter.hasNext())
            {
                FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
                if (myFamily.isActive())
                {
                	                  
                    Collection currentFamMembers = (Collection) dataMap
                            .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
                    if(currentFamMembers!=null && currentFamMembers.size()>0){
                    	Iterator currFamMemIter=currentFamMembers.iterator();
                    	while(currFamMemIter.hasNext()){AbusePerpetrator myAbusePerp=new AbusePerpetrator();
                    		FamilyConstellationMemberListResponseEvent myRespEvent1=(FamilyConstellationMemberListResponseEvent)currFamMemIter.next();
		                    AbusePerpetrator myAbusePerp1=new AbusePerpetrator();
		    				myAbusePerp1.setMemberId(myRespEvent1.getMemberNum());
		    				Name myName=new Name();
		    				myName.setFirstName(myRespEvent1.getFirstName());
		    				myName.setLastName(myRespEvent1.getLastName());
		    				myName.setMiddleName(myRespEvent1.getMiddleName());
		    				myAbusePerp1.setName(myName);
		    				myAbusePerp1.setRelationshipToJuvId(myRespEvent1.getRelationToJuvenileId());
		    				myAbusePerp1.setRelationshipToJuv(myRespEvent1.getRelationToJuvenile());
		    				myList.add(myAbusePerp1);
                    	}
                    }
                }
            }
        }
    }
    	Collections.sort((List)myList);
		Collection employeeCodes = CodeHelper.getEmploymentStatusCodes();
		juvjobForm.setEmpolymentStatus(employeeCodes);
		Collection salaryRateCodes = CodeHelper.getSalaryRateCodes();
		juvjobForm.setSalaryRate(salaryRateCodes);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
