//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRolesCommand.java

package pd.security.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.GetRoleDetailsEvent;
import messaging.security.reply.FeaturesResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.naming.SecurityConstants;
import mojo.km.security.Constraint;
import mojo.km.security.Feature;
import mojo.km.security.Role;
import pd.contact.agency.Agency;
import pd.security.PDSecurityHelper;

/**
 * 
 * 
 * @author mchowdhury
 * @description Get role details such as role name, constraints and features 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetRoleDetailsCommand implements ICommand
{

	/**
	 * @roseuid 42569402000F
	 */
	public GetRoleDetailsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800CC
	 */
	public void execute(IEvent event)
	{
	    //87191
		/*GetRoleDetailsEvent getRoleDetailsEvent = (GetRoleDetailsEvent) event;
		Role role = Role.find(getRoleDetailsEvent.getRoleId());
		
		RoleResponseEvent responseEvent = PDSecurityHelper.getRoleResponseEvent(role);
		
		//get features
		Collection features = role.getFeatures();
        features = this.convertFeatureListToResponseEventList(features);
		//add features to responseEvent
		responseEvent.setFeatures(features);
		
        // get Constrints
	    Collection constraint =  role.getConstraintsByConstrainerType(Agency.class);
	    Collection agencies = this.convertConstraintListToResponseEventList(constraint);
        //add agencies to role
		responseEvent.setAgencies(agencies);
		
		//check if agency is updatable
		if(getRoleDetailsEvent.getAction() != null && getRoleDetailsEvent.getAction().equalsIgnoreCase(naming.PDSecurityConstants.UPDATE)
		   && (role.getUserGroups() != null && !role.getUserGroups().isEmpty() 
		   || (role.getUsers() != null && !role.getUsers().isEmpty()))){
			responseEvent.setAgencyUpdatable(true);   	
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(responseEvent);*/
 	}

	/**
	 * @param constraint
	 * @return
	 */
	private Collection convertConstraintListToResponseEventList(Collection constraint)
	{
		Collection agencies = new ArrayList();
	/*	Iterator itConstraint = constraint.iterator();
		while(itConstraint.hasNext()){
			Constraint cons = (Constraint) itConstraint.next();
			Agency agency = (Agency) cons.getConstrainerObject();
			if(agency != null){
				AgencyResponseEvent agencyResponseEvent = PDSecurityHelper.getAgencyResponseEvent(agency);
				agencyResponseEvent.setAgencyId(agency.getAgencyId());
				agencyResponseEvent.setAgencyName(agency.getAgencyName());
				agencyResponseEvent.setAgencyType(agency.getAgencyTypeId());
	    		agencies.add(agencyResponseEvent);
			}
		}*/ //87191
		return agencies;
	}

	/**
	 * @param features
	 * @return Collection of Feature Response Event
	 */
	private Collection convertFeatureListToResponseEventList(Collection features)
	{
		Collection featureList = new ArrayList();
/*		FeaturesResponseEvent featureResponseEvent = null;
		Iterator featuresIterator = features.iterator();
		while(featuresIterator.hasNext()){
			Feature feature = (Feature) featuresIterator.next();
			featureResponseEvent = PDSecurityHelper.getFeaturesResponseEvent(feature); 
			featureResponseEvent.setTopic(SecurityConstants.ROLE_FEATURES_EVENT_TOPIC);
			
			Collection childFeatureCollection = feature.getChildFeatures();
			if(childFeatureCollection != null){
			   Collection childList = new ArrayList();		
			   Iterator childFeatureIt = childFeatureCollection.iterator();
			   while (childFeatureIt.hasNext()) {
				  Feature childFeature = (Feature) childFeatureIt.next();
				  FeaturesResponseEvent childResponseEvent = PDSecurityHelper.getFeaturesResponseEvent(childFeature); 
				  childList.add(childResponseEvent);
			   }
			   featureResponseEvent.setChildFeatures(childList);
			}
			featureList.add(featureResponseEvent); 
		}*/ //87191
		return featureList;
	}

	/**
	 * @param event
	 * @roseuid 425551F800CE
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800D0
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 425551F800D2
	 */
	public void update(Object anObject)
	{

	}

}
