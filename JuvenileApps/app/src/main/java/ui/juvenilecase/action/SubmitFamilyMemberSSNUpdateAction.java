package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersEvent;
import messaging.family.UpdateFamilyMemSSNEvent;
import messaging.family.UpdateFamilyMemberMatchesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;

/**
 * @author ugopinath
 *  
 */
public class SubmitFamilyMemberSSNUpdateAction extends JIMSBaseAction
{

    /**
     * @roseuid 42791FD100BB
     */
    public SubmitFamilyMemberSSNUpdateAction()
    {

    }

    protected void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.finish", "finish");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.returnToFamilyMemberUpdate", "returnToMem");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D5702D0
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
    	 UpdateFamilyMemSSNEvent updateEvent = (UpdateFamilyMemSSNEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.UPDATEFAMILYMEMSSN);
    	 
    	 Collection mySimiliarMembers = doSearchForSimiliarMember(aMapping, aForm, aRequest, aResponse);
    	 
    	 updateEvent.setFamilyMemID(myForm.getMemberNumber());
    	 updateEvent.setSsn(myForm.getCompleteSSN().getSSN());
    	 
    		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
    		dispatch.postEvent( updateEvent ) ;
    		
    		if (mySimiliarMembers != null && mySimiliarMembers.size() > 0)
    		{
    		   for (Object member : mySimiliarMembers)
    		    {
                 	JuvenileMemberSearchForm.MemberSearchResult m = (JuvenileMemberSearchForm.MemberSearchResult) member;
    			flagBothMembers(m.getMemberNumber(), myForm.getMemberNumber(), UIUtil.getCurrentUserID());
    		    }
    		}
    		
    		
		   if (myForm.getCompleteSSN().getSSN() != null && !myForm.getCompleteSSN().getSSN().equals(UIConstants.EMPTY_STRING)
			&& !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_111111111)
		        && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_222222222)
		        && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_333333333)
		        && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_444444444)
		        && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_555555555)
	                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_666666666)
	                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_777777777)
	                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_888888888)
	                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_999999999))
	        {
			   myForm.getSsn().setSSN1("XXX");
			   myForm.getSsn().setSSN2("XX");
			   myForm.getSsn().setSSN3(myForm.getCompleteSSN().getSSN3());
	        }
		   else
			   myForm.setSsn(myForm.getCompleteSSN());
		   
		   myForm.setAction("confirm");

        return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    private void flagBothMembers(String memberA, String memberB, String foundByUserId)
    {
	if (memberA != null && memberB != null && !(memberA.equalsIgnoreCase(memberB)))
	{
	    UpdateFamilyMemberMatchesEvent event = new UpdateFamilyMemberMatchesEvent();
	    event.setMemberA(memberA);
	    event.setMemberB(memberB);
	    event.setCreateUser(foundByUserId);
	    event.setStatus("UNRESOLVED");
	    event.setMatchType("SSN");
	    event.setNotes("System Generated Notes: Match found when user " + foundByUserId + " was trying to update member number " + memberB);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    
	    //added for US 183152 STARTS
		//To get the Family ID, use the Fammember_ID and join it with the JCCONSRELATION table.
		//Find the highest (most recent) CONSTELLATION_ID for the family.
	    FamilyMember famMemberA = null;
	    famMemberA = FamilyMember.find(memberA);
	    
	    FamilyMember famMemberB = null;
	    famMemberB = FamilyMember.find(memberB);
	    
		List<FamilyConstellation> constellations = new ArrayList<FamilyConstellation>();
		    
		Iterator iterConstellation = FamilyConstellationMember.findAll("theFamilyMemberId", memberA);         	    
		while(iterConstellation.hasNext())
		{
	     		FamilyConstellationMember constMem = (FamilyConstellationMember)iterConstellation.next();
	     	
	     		if(constMem != null && constMem.getFamilyConstellationId() != null && !"".equals(constMem.getFamilyConstellationId()))
	     		{   	    
	     		    constellations.add(constMem.getFamilyConstellation());
	     		}
		}
		ArrayList sortedConstList = new ArrayList();
		sortedConstList.add(new ReverseComparator(new BeanComparator("familyConstellationId")));
		ComparatorChain chain = new ComparatorChain(sortedConstList);
		Collections.sort(constellations, chain);
		String familyID = "";
		String juvenileId = null;
		if(constellations!= null &&  constellations.size()>0){
		FamilyConstellation constellation = (FamilyConstellation)constellations.get(0);
		familyID = constellation.getFamilyConstellationId();
		juvenileId = constellation.getJuvenileId();
		}
		
		List<FamilyConstellation> constellationsMem = new ArrayList<FamilyConstellation>();
		    
		Iterator iterConstellationMem = FamilyConstellationMember.findAll("theFamilyMemberId", memberB);         	    
		while(iterConstellationMem.hasNext())
		{
	     		FamilyConstellationMember constMem2 = (FamilyConstellationMember)iterConstellationMem.next();
	     	
	     		if(constMem2 != null && constMem2.getFamilyConstellationId() != null && !"".equals(constMem2.getFamilyConstellationId()))
	     		{   	    
	     		constellationsMem.add(constMem2.getFamilyConstellation());
	     		}
		}
		ArrayList sortedConstList2 = new ArrayList();
		sortedConstList2.add(new ReverseComparator(new BeanComparator("familyConstellationId")));
		ComparatorChain chain2 = new ComparatorChain(sortedConstList2);
		Collections.sort(constellationsMem, chain2);
		String familyIDMem2 = null;
		String juvenileIdMem2 = null;
		if(constellationsMem!= null &&  constellationsMem.size()>0){
		FamilyConstellation constellationMem = (FamilyConstellation)constellationsMem.get(0);
		familyIDMem2 = constellationMem.getFamilyConstellationId();
		juvenileIdMem2 = constellationMem.getJuvenileId();
		
		}
		//added for US 183152 ENDS
	    
	    try
	    {
		dispatch.postEvent(event);
		
		SendEmailEvent sendEmailEvent = new SendEmailEvent();
        	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");  
        	//String emailDataCorrections = "Eso.Ajewole@harriscountytx.gov"; //"nekha.mathew@harriscountytx.gov"; //comment me
        	//sendEmailEvent.addToAddress(emailDataCorrections); //comment me
        	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
        	//UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, emailDataCorrections);        		        	 
        	sendEmailEvent.setSubject("Suspicious Family Member Created "+ foundByUserId);
        	//sendEmailEvent.setMessage("Family Member ID: "+ memberA +" was located as a duplicate to "+ memberB +".  Please update the Suspicious Family Member.");
        	StringBuffer emailMsg = new StringBuffer("Family member id: ");
        	emailMsg.append(memberA);
        	emailMsg.append(" was located as a duplicate to ");
        	emailMsg.append(memberB);
        	emailMsg.append(".  Please update the Suspicious Family Member.\n");
        	emailMsg.append("Member " + memberA + " " + famMemberA.getLastName() + ",  " + famMemberA.getFirstName()+ ";  ");
        	emailMsg.append("Family " + familyID + "; Juvenile " + juvenileId);
        	emailMsg.append("    \n");
        	emailMsg.append("Member " + memberB + " " + famMemberB.getLastName() + ",  " + famMemberB.getFirstName()+ "  ");
        	emailMsg.append("Family " + familyIDMem2 + " Juvenile " + juvenileIdMem2 );
        	//sendEmailEvent.setMessage("Family member id: "+familyMemberMatch.getMemberA() +" was located as a duplicate to "+familyMemberMatch.getMemberB()+".  Please update the Suspicious Family Member.");
        	sendEmailEvent.setMessage(emailMsg.toString());
        	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
        	MessageUtil.processReturnException( res ) ;
		
	    }
	    catch (Exception e)
	    {
		// ignore any exception as this is not visible to the user
		System.out.println("In catch block");
	    }
	    // Getting PD Response Event
	    //CompositeResponse response = (CompositeResponse) dispatch.getReply();
	}
    }
    
    private Collection doSearchForSimiliarMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
	JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
	mySearchForm.clearSearchResults();
	if (myForm.getCompleteSSN().getSSN() != null && !myForm.getCompleteSSN().getSSN().equals(UIConstants.EMPTY_STRING) && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_666666666) && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_777777777) && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_888888888) && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_999999999))
	{
	    //IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    // Sending PD Request Event
	    GetFamilyMembersEvent event = (GetFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
	    event.setMemberSsn(myForm.getCompleteSSN().getSSN());

	    CompositeResponse response = MessageUtil.postRequest(event);

	    // Getting PD Response Event
	    //CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    // Perform Error handling
	    //MessageUtil.processReturnException(response);
	    Map dataMap = MessageUtil.groupByTopic(response);

	    if (dataMap != null)
	    {
		Collection members = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);

		UIJuvenileHelper.setJuvMemberSearchFormFROMMemberListRespEvt(mySearchForm, members);
		if (mySearchForm.getMemberSearchResults() != null && mySearchForm.getMemberSearchResults().size() > 0 && myForm.getMemberNumber() != null && !(myForm.getMemberNumber().equals("")))
		{ // removing the existing member from the search result list
		    Iterator iter = mySearchForm.getMemberSearchResults().iterator();
		    while (iter.hasNext())
		    {
			JuvenileMemberSearchForm.MemberSearchResult mySearchResult = (JuvenileMemberSearchForm.MemberSearchResult) iter.next();
			if (mySearchResult.getMemberNumber().equalsIgnoreCase(myForm.getMemberNumber()))
			    iter.remove();
		    }
		}
	    }
	}
	return mySearchForm.getMemberSearchResults();
    }
    
   
    

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
    	myForm.setAction("update");
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;
    	 jmForm.getCompleteSSN().setSSN(jmForm.getSelectedValue());
    	 jmForm.setAction("updateMember");
        return aMapping.findForward(UIConstants.CANCEL);
    }
    
    public ActionForward returnToMem(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;
    	jmForm.setAction("updateMember");
    	return aMapping.findForward(UIConstants.RETURN_SUCCESS);
    }

}
