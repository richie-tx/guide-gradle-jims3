//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\SavePlacementInformationCommand.java

package pd.juvenilecase.caseplan.transactions;

import naming.CasePlanConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.Placement;
import messaging.caseplan.SavePlacementInformationEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

public class SavePlacementInformationCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B84A0190
    */
   public SavePlacementInformationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE431004A
    */
   public void execute(IEvent event) 
   {
   		SavePlacementInformationEvent request = (SavePlacementInformationEvent)event;
   		String casefileID = request.getCasefileID();
   		String caseplanID = request.getCaseplanID();
   		CasePlan cp = null;
   		//find Caseplan from id
   		if(caseplanID == null || caseplanID.equals("0")) { 
   			// if no caseplan, create it
   			cp = new CasePlan();
   			cp.setStatusId(CasePlanConstants.CP_STATUS_PENDING);
   	   		JuvenileCasefile casefile = JuvenileCasefile.find(casefileID); 
   	   		cp.setSupervisionNumber(casefileID);
   	   		new Home().bind(cp);
   	   		caseplanID = cp.getCaseplanID();
   			casefile.setCaseplanId(caseplanID);
   		} else {
   			cp = CasePlan.find(caseplanID);
   			if(cp.getStatusId().equals(CasePlanConstants.CP_STATUS_REVIEWED) || 
   	   				cp.getStatusId().equals(CasePlanConstants.CP_STATUS_ACKNOWLEDGED)) { //17325 bug fix
   	   					cp.setStatusId(CasePlanConstants.CP_STATUS_PENDING);
   	   			}
   		}
   		
   		Placement placement = cp.getThePlacement();
   		if(placement == null) {
   			placement = new Placement();
   			placement.setSupervisionNumber(casefileID);
   			placement.setCaseplanId(caseplanID);
   		}
   		placement.setFacilityReleaseReasonId(request.getFacilityRelReasonId());
   		placement.setFacilityId(request.getFacilityId());
   		placement.setLevelOfCareId(request.getLevelOfCareId());
   		placement.setPermanencyPlanId(request.getPermanencyPlanId());
   		placement.setReasonForPlacement(request.getReasonPlacementReqd());
   		placement.setSpecialnotes(request.getSpecialNotes());
   		placement.setSpecificServices(request.getSpecificServices());
   		placement.setWhyOutsideTexas(request.getWhyOutsideTexas());
   		placement.setProximityToSchoolDist(request.getProxToChildsSD());
   		placement.setLeaseRestrEnvAvailable(request.getLeastRestrEnvAvail());
   		placement.setClosestFacilityAvailable(request.getClosestFacilityAvail());
   		placement.setExpectedReleaseDate(request.getExpReleaseDate());
   		placement.setEntryDate(request.getEntryDate());
   		placement.setSupervisionNumber(casefileID);
		cp.setThePlacement(placement);
		CaseplanDetailsResponseEvent cpResponse = new CaseplanDetailsResponseEvent();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		
   		if(cp == null) {// the caseplan has not been created yet!
   			
   			return;
   		}
   		cpResponse.setCaseplanID(cp.getOID().toString());
   		cpResponse.setCreateDate(cp.getCreateTimestamp());
   		cpResponse.setStatusId(cp.getStatusId());
   		dispatch.postEvent(cpResponse);

   }
   
   /**
    * @param event
    * @roseuid 452FE4310052
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE4310054
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 452FE431005D
    */
   public void update(Object anObject) 
   {
    
   }
   
}
