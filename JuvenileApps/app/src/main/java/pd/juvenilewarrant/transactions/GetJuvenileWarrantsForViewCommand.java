package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsByJuvenileNameEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsByResponsibleAdultEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForViewEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantForViewEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;

import mojo.km.messaging.IEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenile.Juvenile;
import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

/**
 * @author asrvastava
 *  
 */
public class GetJuvenileWarrantsForViewCommand implements ICommand
{

    /**
     * @roseuid 421B84900119
     */
    public GetJuvenileWarrantsForViewCommand()
    {

    }

    /**
     * @param event
     * @roseuid 421B755D01D6
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForViewEvent warEvent = (GetJuvenileWarrantsForViewEvent) event;

        String warrantNum = warEvent.getWarrantNum();
        String associateLastName = warEvent.getAssociateLastName();
        String juvenileLastName = warEvent.getLastName();
        String officerId = warEvent.getOfficerId();
        String originatorId = warEvent.getOriginatorId();
        String warrantTypeId = warEvent.getWarrantTypeId();
        
        List warrantCollection = new ArrayList();

        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();

        Set features = mgr.getFeatures();

        boolean viewActive = features.contains("JW-VIEWACTIVE");
        boolean viewExecuted = features.contains("JW-VIEWEXECUTED");
        boolean viewInactive = features.contains("JW-VIEWINACT");
        boolean viewOpenActive = features.contains("JW-VIEWOPENACTIVE");
        boolean viewPendingNotActive = features.contains("JW-VIEWPENDNOTACTIVE");
        boolean viewRecalled = features.contains("JW-VIEWRECALL");
        boolean viewUnsend = features.contains("JW-VIEWUNSEND");
        
        
        boolean view = mgr.isAllowed("JW-VIEW");

        boolean errorFound = false;
        // do not send if the user cannot JW-VIEW
        if (view == true)
        {
            if ( StringUtils.isNotEmpty(warrantNum) && warrantNum.length()< 10 )
            {
                JuvenileWarrant warrant = JuvenileWarrant.find(warEvent.getWarrantNum());
                if (warrant != null)
                {
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {
                        warrantCollection.add(warrant);
                    }
                }
            }
            else if ( StringUtils.isNotEmpty(associateLastName) )
            {
                GetJuvenileWarrantsByResponsibleAdultEvent getWarrants = new GetJuvenileWarrantsByResponsibleAdultEvent();
                getWarrants.setAssociateFirstName(warEvent.getAssociateFirstName());
                getWarrants.setAssociateLastName(warEvent.getAssociateLastName());
                Iterator i = JuvenileWarrant.findAll(getWarrants);
                Map warrantMap = new HashMap();
                while (i.hasNext())
                {
                    JuvenileWarrant warrant = (JuvenileWarrant) i.next();
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {
                        Object oid = warrant.getOID();
                        if (warrantMap.containsKey(oid) == false)
                        {
                            warrantMap.put(oid, warrant);
                        }
                    }
                }
                warrantCollection.addAll(warrantMap.values());
            }
            else if( StringUtils.isNotEmpty(juvenileLastName) )
            {
                GetJuvenileWarrantsByJuvenileNameEvent requestEvent = new GetJuvenileWarrantsByJuvenileNameEvent();
                requestEvent.setLastName(warEvent.getLastName());
                requestEvent.setFirstName(warEvent.getFirstName());
                Iterator juvWarrants = JuvenileWarrant.findAll(requestEvent);
                while (juvWarrants.hasNext())
                {
                    JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.next();
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {               	
                        warrantCollection.add(warrant);                      
                    }
                }
            }
            else if( StringUtils.isNotEmpty(officerId) )
            {
                SearchJuvenileWarrantForViewEvent requestEvent = new SearchJuvenileWarrantForViewEvent();
                requestEvent.setOfficerId(officerId);
                Iterator juvWarrants = JuvenileWarrant.findAll(requestEvent);
                while (juvWarrants.hasNext())
                {
                    JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.next();
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {
                    	warrantCollection.add(warrant);
                    }
                }
            }
            else if( StringUtils.isNotEmpty(originatorId) )
            {
                SearchJuvenileWarrantForViewEvent requestEvent = new SearchJuvenileWarrantForViewEvent();
                requestEvent.setOriginatorId(warEvent.getOriginatorId());                     
                Iterator juvWarrants = JuvenileWarrant.findAll(requestEvent);
                while (juvWarrants.hasNext())
                {
                    JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.next();
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {
                        warrantCollection.add(warrant);
                    }
                }
            }
            else if( StringUtils.isNotEmpty(warrantTypeId) )
            {
                SearchJuvenileWarrantForViewEvent requestEvent = new SearchJuvenileWarrantForViewEvent();
                requestEvent.setWarrantTypeId(warEvent.getWarrantTypeId());
                if ( StringUtils.isNotEmpty(warEvent.getWarrantStatusId()) ) 
                {
                	requestEvent.setWarrantStatusId(warEvent.getWarrantStatusId());
                }
                if ( warEvent.getSearchDate1() != null && warEvent.getSearchDate2() != null ) 
                {
                	requestEvent.setSearchDate1(warEvent.getSearchDate1());
                	requestEvent.setSearchDate2(warEvent.getSearchDate2());
                }
                Iterator juvWarrants = JuvenileWarrant.findAll(requestEvent);
                while (juvWarrants.hasNext())
                {
                    JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.next();
                    if (warrant.isViewableByFeature(userInfo, viewActive, viewOpenActive, viewInactive, viewExecuted,viewRecalled,viewPendingNotActive, viewUnsend))
                    {
                        warrantCollection.add(warrant);
                    }
                }
           }
           // 66852 check if number of warrants is greater than 2K limit
	       if ( warrantCollection.size() > 2000 ) 
	       {
	    	   CountInfoMessage infoEvent = new CountInfoMessage();
	           infoEvent.setMessage("Record count exceeded - total records found = " + warrantCollection.size());
	           infoEvent.setCount(warrantCollection.size());
	           errorFound = true;
	           MessageUtil.postReply(infoEvent);
	       } 
           if( !errorFound ) 
           {
               this.sendWarrants(warrantCollection);
           }
        }
    }

    /**
     * @param event
     * @roseuid 421B755D01E4
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 421B755D01E6
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 421B84900128
     */
    public void update(Object updateObject)
    {

    }

    /**
     * 
     * @return boolean
     * 
     * ER#19981 CW - pre-condition updated to read: If the warrant status is
     * pending and warrant activation is not active, only the Warrant Originator
     * can view
     * 
     * If warrant status is recall, only Juvenile Probation staff can view.
     * 
     * If the warrant status is open and warrant activiation status is active,
     * anyone with the Officer capacity, ADA role or Juvenile Priobation Officer
     * role can view the warrant.
     * 
     * If warrant activation status is inactive, anyone with ADA role or
     * Juvenile Probation Officer role can view.
     * 
     * If warrant status is executed, ADA role, Probation Officer role, Judge
     * role, District Clerk Juvenile Intake role, Officer capacity can view.
     * 
     *  
     */

    private void sendWarrants(List warrants)
    {
        if (warrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(0);          
            this.sendSingleWarrant(warrant);
        }
        else
        {
            this.sendMultipleWarrants(warrants);
        }
    }
    
    /**
     * @param warrants
     */
    private void sendMultipleWarrants(List warrants)
    {

        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();

        int len = warrants.size();
        for (int i = 0; i < len; i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            builder.setWarrant(warrant);
            builder.build();
            builder.setWarrantOriginator();
            builder.setWarrantActivationStatus();
            builder.setWarrantStatus();
            builder.setWarrantType();
            JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();
            //US 87990
            if(warrant.getJuvenileNum()!=null && !warrant.getJuvenileNum().toString().isEmpty())
            {
                JuvenileProfileDetailResponseEvent response = null;
                SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();
                pEvent.setJuvenileNum(warrant.getJuvenileNum().toString());		
                Iterator iter = Juvenile.findAll(pEvent);
                if (iter != null && iter.hasNext()) 
                {
            	Juvenile juvenile = (Juvenile) iter.next();
    		response = juvenile.getCoreJuvenileProfileResponse();
                }
                warrantEvent.setJuvRectype(response.getRecType());
            }
            //
            MessageUtil.postReply(warrantEvent);
        }
    }

    private void sendSingleWarrant(JuvenileWarrant warrant)
    {
        //this.sendAssociatesFields(warrant);
        PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());
        PDJuvenileWarrantHelper.postResponses(warrant.getServiceResponses());
        JuvenileWarrantResponseEvent respEvt=warrant.valueObject();
      //US 87990
        if(warrant.getJuvenileNum()!=null && !warrant.getJuvenileNum().toString().isEmpty())
        {
            JuvenileProfileDetailResponseEvent response = null;
            SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();
            pEvent.setJuvenileNum(warrant.getJuvenileNum().toString());		
            Iterator iter = Juvenile.findAll(pEvent);
            if (iter != null && iter.hasNext()) 
            {
        	Juvenile juvenile = (Juvenile) iter.next();
		response = juvenile.getCoreJuvenileProfileResponse();
            }
            respEvt.setJuvRectype(response.getRecType());
        }
        //
        MessageUtil.postReply(respEvt);
        if (warrant.isJOT() == true)
        {
            this.sendOfficerValues(warrant);
        }
    }

    private void sendAssociatesFields(JuvenileWarrant juvWarrant)
    {
        Collection juvAssociates = juvWarrant.getJuvenileAssociates();
        if (juvAssociates != null)
        {
            Iterator i = juvAssociates.iterator();
            while (i.hasNext())
            {
                JuvenileAssociate associate = (JuvenileAssociate) i.next();
                JuvenileAssociateResponseEvent associateEvent = this.getSimpleJuvenileAssociateResponseEvent(associate);

                MessageUtil.postReply(associateEvent);
            }
        }
    }

    private JuvenileAssociateResponseEvent getSimpleJuvenileAssociateResponseEvent(JuvenileAssociate juvAssociate)
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_EVENT_TOPIC);
        jare.setAssociateNum(juvAssociate.getAssociateNum());
        jare.setFirstName(juvAssociate.getFirstName());
        jare.setLastName(juvAssociate.getLastName());
        jare.setMiddleName(juvAssociate.getMiddleName());
        jare.setRelationshipToJuvenileId(juvAssociate.getRelationshipToJuvenileId());
        Code relationshipCode = juvAssociate.getRelationshipToJuvenile();
        if (relationshipCode != null)
        {
            jare.setRelationshipToJuvenile(relationshipCode.getDescription());
        }
        jare.setReleasedTo(juvAssociate.getReleasedTo());
        jare.setWarrantNum(juvAssociate.getWarrantNum());
        return jare;
    }

    /**
     * @param dispatch
     * @param warrant
     */
    private void sendOfficerValues(JuvenileWarrant warrant)
    {
        OfficerProfile officer = warrant.getOfficer();
        OfficerProfileResponseEvent officerResponse = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officer);
        MessageUtil.postReply(officerResponse);

    }

}