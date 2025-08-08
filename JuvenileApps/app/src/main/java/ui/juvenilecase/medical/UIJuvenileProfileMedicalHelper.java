/*
 * Created on Feb 5, 2007
 *
 */
package ui.juvenilecase.medical;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import messaging.codetable.GetJuvenileAdmissionTypeCdEvent;
import messaging.codetable.GetJuvenileMedicationTypeCodesEvent;
import messaging.codetable.criminal.reply.HospitalizationAdmissionTypeCdResponseEvent;
import messaging.codetable.criminal.reply.JuvenileMedicationTypeResponseEvent;
import messaging.juvenile.GetJuvenileMedicalHealthIssuesListEvent;
import messaging.juvenile.GetJuvenileMedicalHospitalizationListEvent;
import messaging.juvenile.GetJuvenileMedicalMedicationListEvent;
import messaging.juvenile.reply.JuvenileHealthIssueResponseEvent;
import messaging.juvenile.reply.JuvenileHospitalizationResponseEvent;
import messaging.juvenile.reply.JuvenileMedicationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class UIJuvenileProfileMedicalHelper{
	public static Collection getHealthIssuesList(String juvenileNum)
	{
		GetJuvenileMedicalHealthIssuesListEvent reqEvent = (GetJuvenileMedicalHealthIssuesListEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEMEDICALHEALTHISSUESLIST);
   		reqEvent.setJuvenileNum(juvenileNum);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(reqEvent);	   		
   		CompositeResponse response = (CompositeResponse) dispatch.getReply();
   		Collection coll = MessageUtil.compositeToCollection(response, JuvenileHealthIssueResponseEvent.class);
   		Iterator iter = coll.iterator();
   		Collection issues = new ArrayList();
   		while(iter.hasNext())
   		{
   			JuvenileHealthIssueResponseEvent resp = (JuvenileHealthIssueResponseEvent)iter.next();
   			String issue = SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE", resp.getIssueId());
   			String issueStatus = SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE_STATUS", resp.getIssueStatusId());
   			String healthStatus = SimpleCodeTableHelper.getDescrByCode("HEALTH_STATUS", resp.getHealthStatusId());
   			resp.setIssueId(issue);
   			resp.setIssueStatusId(issueStatus);
   			resp.setHealthStatusId(healthStatus);
   			if (healthStatus.equals("HIPAA PROTECTED")){
   				resp.setHealthStatusFull("Health Insurance Portability and Accountability Act");
   			}
   			issues.add(resp);
   			
   		}
   		Collections.sort((List)issues);
		return issues;
	}
	
	public static Collection getMedicationList(String juvenileNum)
	{
		GetJuvenileMedicalMedicationListEvent reqEvent = (GetJuvenileMedicalMedicationListEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEMEDICALMEDICATIONLIST);
   		reqEvent.setJuvenileNum(juvenileNum);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(reqEvent);	   		
   		CompositeResponse response = (CompositeResponse) dispatch.getReply();
   		Collection coll = MessageUtil.compositeToCollection(response, JuvenileMedicationResponseEvent.class);
   		Iterator medicationsIter = coll.iterator();
   		while(medicationsIter.hasNext())
   		{
   			JuvenileMedicationResponseEvent resp = (JuvenileMedicationResponseEvent)medicationsIter.next();   			
   			String currentlyTakeingMed = SimpleCodeTableHelper.getDescrByCode("MEDICATION_CURRENT", resp.getCurrentlyTakingMedication()); 
   			resp.setCurrentlyTakingMedication(currentlyTakeingMed); 
   			if (currentlyTakeingMed.equals("HIPAA PROTECTED")){
   				resp.setCurrentlyTakingMedicationFull("Health Insurance Portability and Accountability Act");
   			}
   			Collection codes = getMedicationCodesById(resp.getMedicationTypeId());
   			Iterator codesIter = codes.iterator();
   			while(codesIter.hasNext())
   			{
   				JuvenileMedicationTypeResponseEvent codeRespEvent = (JuvenileMedicationTypeResponseEvent) codesIter.next();
   				if(codeRespEvent.getMedicationTypeId().equals(resp.getMedicationTypeId()))
   				{
   					//resp.setMedicationName(codeRespEvent.getTradeName()+ "; " + codeRespEvent.getDosageAdmin() + "; " + codeRespEvent.getStrength());
   					//resp.setMedicationName(codeRespEvent.getMedication());
   					resp.setMedicationName(codeRespEvent.getTradeName()); //Added to show only the medication tradename from JCMEDICATIONVIEW table ERJIMS200076779
   				}
   			}
   			
   			
   		}
   		Collections.sort((List)coll);
		return coll;
	}
	
	public static Collection getHospitalizationList(MedicalForm mdf)
	{
		GetJuvenileMedicalHospitalizationListEvent mEvent = (GetJuvenileMedicalHospitalizationListEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEMEDICALHOSPITALIZATIONLIST);
		mEvent.setJuvenileNum(mdf.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(mEvent);	   		
   		CompositeResponse response = (CompositeResponse) dispatch.getReply();
   		Collection coll = MessageUtil.compositeToCollection(response, JuvenileHospitalizationResponseEvent.class);
   		Iterator iter = coll.iterator();
   		Collection hospitalizations = new ArrayList();
   		while(iter.hasNext())
   		{
   			JuvenileHospitalizationResponseEvent resp = (JuvenileHospitalizationResponseEvent)iter.next();
   			String admissionType = getAdmissionType(mdf, resp.getAdmissionTypeId());
   			resp.setAdmissionTypeId(admissionType);
   			hospitalizations.add(resp);
   		}
   		Collections.sort((List)hospitalizations);
		return hospitalizations;
	}
	
	/**
	 * Returns a name as pd.km.util.Name with the given full, formatted name.
	 * @param formattedName
	 * @return
	 */
	public static Name getNameFromString(String formattedName)
	{
		Name nameObj = new Name();

		if (formattedName != null && !formattedName.equals("") && !formattedName.equals("NOT AVAILABLE"))
		{
			try
			{
				StringTokenizer strTok = new StringTokenizer(formattedName, " ");
				
				String lastName = strTok.nextToken();
				lastName = lastName.substring(0, lastName.length()-1);
				String firstName = strTok.nextToken();
				String middleName = "";
				if (strTok.hasMoreTokens())
				{
					middleName = strTok.nextToken();
				}
				nameObj.setLastName(lastName);
				nameObj.setFirstName(firstName);
				nameObj.setMiddleName(middleName);
			}
			catch (Exception e)
			{
				// do something
			}
		}
		return nameObj;
	}
	
	 public static String getAdmissionType(MedicalForm mdf, String admissionTypeId)   
	   {
	 	   	Collection coll = mdf.getAdmissionTypes();
	 	   	if(coll.size()==0)
	 	   		coll = getAdmissionTypes();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				HospitalizationAdmissionTypeCdResponseEvent resp = ( HospitalizationAdmissionTypeCdResponseEvent)iter.next();
				 if(resp.getAdmissionTypeCode().equals(admissionTypeId))
				 {
				 	return resp.getDescription();
				 }
			}
	   	 return null;
	   }
	 
	  public static Collection getAdmissionTypes()
	   {
	   		GetJuvenileAdmissionTypeCdEvent dataEvent = (GetJuvenileAdmissionTypeCdEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEADMISSIONTYPECD);
			dataEvent.setCategoryId("MD");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(dataEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Collection admTypes = MessageUtil.compositeToCollection(compositeResponse, HospitalizationAdmissionTypeCdResponseEvent.class);
	   		return admTypes;
	   }
	  
	  public static Collection getMedicationCodesById(String medicTypeId)
	  {
	  	GetJuvenileMedicationTypeCodesEvent reqEvent = (GetJuvenileMedicationTypeCodesEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEMEDICATIONTYPECODES);
	  	reqEvent.setMedicationTypeId(medicTypeId);
	  	reqEvent.setFlagfind(true);// Added
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection codes = MessageUtil.compositeToCollection(compositeResponse, JuvenileMedicationTypeResponseEvent.class);
	  	return codes;
	  }
	  
	  public static Collection getMedicationCodes(MedicalForm.Medication medic)
	  {
	  	GetJuvenileMedicationTypeCodesEvent reqEvent = (GetJuvenileMedicationTypeCodesEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEMEDICATIONTYPECODES);
	  	//reqEvent.setTradeName(medic.getTradeName());
	  	reqEvent.setDosageAdmin(medic.getDelivery());
	  	reqEvent.setStrength(medic.getStrength());
	  	reqEvent.setMedicationTypeId(medic.getMedCode());
	  	reqEvent.setUsage(medic.getUsage());
	  	reqEvent.setMedication(medic.getMedication());
	  	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection admTypes = MessageUtil.compositeToCollection(compositeResponse, JuvenileMedicationTypeResponseEvent.class);
	  	return admTypes;
	  	
	  }
}