/*
 * Created on Feb 8, 2007
 *
 *
 */
package ui.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import messaging.administerlocation.GetJuvLocationUnitsByAgencyEvent;
import messaging.administerlocation.GetLocationsByAgencyEvent;
import messaging.administerlocation.GetLocationsForStaffPositionEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.GetSPNamesEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.agency.GetLawEnforcementDepartmentsEvent;
import messaging.codetable.GetJuvenileCodeTableChildCodesEvent;
import messaging.codetable.GetJuvenileDSM5CodeEvent;
import messaging.codetable.GetJuvenileDSM5CodesWithTJJDIDEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.GetJuvenileRefAssignmentTypeEvent;
import messaging.codetable.GetJuvenileReleasedFromDetentionEvent;
import messaging.codetable.GetJuvenileTechnicalVOPCodesEvent;
import messaging.codetable.GetJuvenileVOPSanctionCodesEvent;
import messaging.codetable.GetMagistratesEvent;
import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.GetOverrideOptionCodesEvent;
import messaging.codetable.GetRaceEthnicityCodesEvent;
import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileRefAssgnmtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReleasedFromResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileVOPSanctionCodesResponseEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.codetable.drug.reply.DrugTestResultCodeResponseEvent;
import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.person.reply.RaceEthnicityCodeResponseEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.cscdcalendar.GetCSEventTypesEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.csserviceprovider.GetProgramHierarchyCodesEvent;
import messaging.csserviceprovider.reply.CSProgramHierarchyResponseEvent;
import messaging.juvenilecase.GetAllSupervisionCategoryTypesEvent;
import messaging.juvenilecase.reply.OverrideOptionsResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import messaging.riskanalysis.GetRiskResultGroupCodesEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionorder.GetPrintTemplatesEvent;
import messaging.supervisionorder.reply.MagistrateResponseEvent;
import messaging.supervisionorder.reply.PrintTemplatesResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.CSEventControllerServiceNames;
import naming.CaseloadControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;
import ui.juvenilecase.UIJuvenileHelper;
//import messaging.riskanalysis.GetRiskResultGroupCodesEvent;

/**
 * @author jjose
 *
 * This class is responsible for getting all the more complex code tables and is allowed to contain custom methods.
 */
public class ComplexCodeTableHelper extends CodeTableHelper{

	 public static List getCSCServiceProviderProgramHeirarchy(){
	 	IEvent request = new GetProgramHierarchyCodesEvent();

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		
		
		List respEvts = MessageUtil.compositeToList(compositeResponse, CSProgramHierarchyResponseEvent.class);
		Collections.sort(respEvts);
		return respEvts;
	 }
	 
	 public static String getCourtDivision(String cdi) {
		if (cdi != null && !cdi.equals("")) {
			if (cdi.equals(PDCodeTableConstants.CSCD)) {
				return "Courtesy Supervision Cases - CSCD";
			} else if (cdi.equals(PDCodeTableConstants.PTS)) {
				return "Pre-trial Services - PTS";
			}
		}
		return "";
	}

	 public static List getDispositionTypes() {
		ArrayList dispositionTypes=new ArrayList();

	 	//			request =
			// EventFactory.getInstance(OutOfCountyCaseControllerServiceNames.GETDISPOSITIONTYPES);
			//
			//			dispatch.postEvent(request);
			//
			//			compositeResponse = (CompositeResponse) dispatch.getReply();
			//			MessageUtil.processReturnException(compositeResponse);
			//
			//			Collection values =
			// MessageUtil.compositeToCollection(compositeResponse,
			// CodeResponseEvent.class);
			// HARD CODING THE DISPOSITION TYPES
			CodeResponseEvent code = new CodeResponseEvent();
			code.setCode("DADJ");
			code.setCodeId("DADJ");
			code.setDescription("DEFERRED ADJUDICATION OF GUILT");
			dispositionTypes.add(code);
			code = new CodeResponseEvent();
			code.setCode("PTIN");
			code.setCodeId("PTIN");
			code.setDescription("PRETRIAL INTERVENTION CSCD");
			dispositionTypes.add(code);
			code = new CodeResponseEvent();
			code.setCode("PROB");
			code.setCodeId("PROB");
			code.setDescription("PROBATION");
			dispositionTypes.add(code);
			return dispositionTypes;
		}

	 public static List getClosedDispositionTypes() {
		ArrayList closedTypes=new ArrayList();
		// Also need these types for closed cases
				// HARD CODING THE CLOSED TYPES
			CodeResponseEvent closedCode = new CodeResponseEvent();
			closedCode.setCode("CABS");
			closedCode.setCodeId("CABS");
			closedCode.setDescription("CLOSED - ABSCONDED");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CDTH");
			closedCode.setCodeId("CDTH");
			closedCode.setDescription("CLOSED - DEATH OF PROBATIONER");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CETR");
			closedCode.setCodeId("CETR");
			closedCode.setDescription("CLOSED - EARLY TERMINATION");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CEXP");
			closedCode.setCodeId("CEXP");
			closedCode.setDescription("CLOSED - PROBATION EXPIRED");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CLAW");
			closedCode.setCodeId("CLAW");
			closedCode.setDescription("CLOSED - LAW VIOLATION");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CMOV");
			closedCode.setCodeId("CMOV");
			closedCode.setDescription("CLOSED - MOVED FROM HARRIS COUNTY");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("COTH");
			closedCode.setCodeId("COTH");
			closedCode.setDescription("CLOSED - OTHER REASON");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CREJ");
			closedCode.setCodeId("CREJ");
			closedCode.setDescription("CLOSED - SUPERVISION REJECTED");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CREQ");
			closedCode.setCodeId("CREQ");
			closedCode.setDescription("CLOSED - REQUEST OF ORIGINAL JURISDICTION");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CRVK");
			closedCode.setCodeId("CRVK");
			closedCode.setDescription("CLOSED - PROBATION REVOKED");
			closedTypes.add(closedCode);
			closedCode = new CodeResponseEvent();
			closedCode.setCode("CTEC");
			closedCode.setCodeId("CTEC");
			closedCode.setDescription("CLOSED - TECHNICAL VIOLATION");
			closedTypes.add(closedCode);

			return closedTypes;
	}

	 public static List getCaseTypes() {
			IEvent request = EventFactory.getInstance(OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASETYPES);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);

			List caseTypes = MessageUtil.compositeToList(compositeResponse, CourtResponseEvent.class);
			Collections.sort((List)caseTypes);
			
			return caseTypes;
	}
	 
	 public static List getSupervisionLevelOfServiceCodes() {
			IEvent request = EventFactory.getInstance(CaseloadControllerServiceNames.GETLEVELOFSUPERVISIONCODES);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);

			List supervisionLevelOfServiceCodes = MessageUtil.compositeToList(compositeResponse, CodeResponseEvent.class);
			Collections.sort((List)supervisionLevelOfServiceCodes);
			
			return supervisionLevelOfServiceCodes;
	} 
	 public static List getProgramTrackerCodes() {
		 
		 return ComplexCodeTableHelper.getSupervisionCodes("CSC", PDCodeTableConstants.PROGRAM_TRACKER);	
	 
	 } 
	 public static List getConnectionCodes() {
	 	ArrayList myList=new ArrayList();

			CodeResponseEvent code = null;
			//			Collection codes =
			// CodeHelper.getCodes(PDCodeTableConstants.CONNECTION_CODE, true);
			//			for (Iterator i = codes.iterator(); i.hasNext();)
			//			{
			//				code = (CodeResponseEvent)i.next();
			//				String codeId = code.getCode();
			//				if (codeId.equals("WID") || codeId.equals("WIP") ||
			// codeId.equals("AWD") || codeId.equals("AWP"))
			//				{
			//				}
			//				else
			//				{
			//					connections.put(codeId, code);
			//				}
			//			}
			code = new CodeResponseEvent();
			code.setCode("DEF");
			code.setCodeId("DEF");
			code.setDescription("DEFENDANT");
			myList.add(code);
			code = new CodeResponseEvent();
			code.setCode("WIP,WID,AWD,AWP");
			code.setCodeId("WIP,WID,AWD,AWP");
			code.setDescription("WITNESS");
			myList.add(code);
			return myList;
	}

	 /**
     * Return Court Codes
     *
     * @return Collection
     */
    public static List getCourtCodes(boolean sort)
    {
        RequestEvent courtEvent = (RequestEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
        CompositeResponse response = MessageUtil.postRequest(courtEvent);
        List codes = MessageUtil.compositeToList(response, JuvenileCourtResponseEvent.class);
        if (sort == true)
        {
            Collections.sort(codes);
        }
        return codes;
    }

    public static void getSchoolCodes(Collection districts, String schoolDistrictId, Collection schoolDistricts,
            String schoolCodeId, Collection schools)
    {
        // TreeMap was used to get sorted values
        Map schoolDistrictMap = new TreeMap();
        if (districts != null)
        {
            Iterator i = districts.iterator();
            while (i.hasNext())
            {
                JuvenileSchoolDistrictCodeResponseEvent school = (JuvenileSchoolDistrictCodeResponseEvent) i.next();
                schoolDistrictMap.put(school.getDistrictDescription(), school);
                if (school.getDistrictCode().equals(schoolDistrictId))
                {
                    schools.add(school);
                }
            }
            Collections.sort((List) schools);
        }
        schoolDistricts.addAll(schoolDistrictMap.values());
    }


    /**
     *
     * @return Collection signedStatusCodes
     */
    public static List getSignatureOptions(boolean sort)
    {
        List warrantSignedStatuses = new ArrayList();
        CodeResponseEvent codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_RETURN);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_RETURN_DESCRIPTION);
        warrantSignedStatuses.add(codeEvent);

        codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_SIGNED);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_SIGN_DESCRIPTION);
        warrantSignedStatuses.add(codeEvent);

        codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED_DESCRIPTION);
        warrantSignedStatuses.add(codeEvent);

        if (sort == true)
        {
            Collections.sort(warrantSignedStatuses);
        }

        return warrantSignedStatuses;
    }

    /**
     * @deprecated - Use getTattoos(boolean).
     */
    public static List getTattooCodes(boolean sort)
    {
        // TODO Change to not use literal
        return ComplexCodeTableHelper.getCriminalCodes(PDCodeTableConstants.TATTOO_CATEGORY, sort);
    }

    public static List getTattoos(boolean sort)
    {
        RequestEvent requestEvent = (RequestEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETTATTOOCODES);
        CompositeResponse response = MessageUtil.postRequest(requestEvent);
        List codes = MessageUtil.compositeToList(response, ICode.class);
        if (sort == true)
        {
            Collections.sort(codes);
        }
        return codes;
    }

    public static List getDocuments(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.DOCUMENTS);
    }

    public static List getSeverityLevels(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.SEVERITY_LEVEL);
    }

    public static List getSupervisionStaffJobTitles(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.STAFF_JOB_TITLE);
    }

    public static List getSupervisionStaffPositionTypes(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.STAFF_POSITION_TYPE);
    }

    public static List getSupervisionStaffStatus(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.STAFF_STATUS);
    }

    public static List getCSTSOfficerTypes(String agencyId)
    {
        return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CSTS_OFFICER_TYPE);
    }

    /**
     * Return Court Division Codes for supervision courts.
     *
     * @return Collection
     */
    public static List getSupervisionCourtDivisionCodes(boolean sort)
    {
        List aList = SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.COURT_DIVISION);
        List supCourtDivs = new ArrayList();

        if (aList != null)
        {
            CodeResponseEvent cre = null;
            Integer courtDiv = null;
            Iterator iter = aList.iterator();
            while (iter.hasNext())
            {
                cre = (CodeResponseEvent) iter.next();
                try
                {
                    courtDiv = new Integer(cre.getCodeId());
                    if ((courtDiv.intValue() > 0 && courtDiv.intValue() < 4)
                            || (courtDiv.intValue() > 6 && courtDiv.intValue() < 11))
                    {
                        supCourtDivs.add(cre);
                    }
                }
                catch (NumberFormatException e)
                {
                    courtDiv = null;
                }
            }
        }
        return supCourtDivs;
    }

    /**
     * Will return a collection of CodeResponseEvents for a particular code table. Will
     * return an empty collection if no codes exist for that code table.
     *
     * @param codeTableName
     * @param agencyId
     * @return Collection
     */
    public static List getSupervisionCodes(String agencyId, String codeTableName)
    {
        GetSupervisionCodesEvent codesRequestEvent = (GetSupervisionCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);

        codesRequestEvent.setCodeTableName(codeTableName);
        codesRequestEvent.setAgencyId(agencyId);

        CompositeResponse response = MessageUtil.postRequest(codesRequestEvent);

        List codes = MessageUtil.compositeToList(response, CodeResponseEvent.class);

        Collections.sort(codes);

        return codes;
    }
    
    public static String getDescrBySupervisionCode(List codes, String code) {
    	if(codes == null || code == null) {
    		return null;
    	}
    	
    	for(Iterator codesIter = codes.iterator();codesIter.hasNext();) {
    		CodeResponseEvent codeRE = (CodeResponseEvent)codesIter.next();
    		if(code.equals(codeRE.getSupervisionCode())) {
    			return codeRE.getDescription();
    		}
    	}
    	
    	return null;
    }
    
    public static String getDescrBySupervisionCode(String agencyId, String codeTableName, String code) {
    	List codeList = getSupervisionCodes(agencyId, codeTableName);
    	return getDescrBySupervisionCode(codeList, code);
    	
    }
    
    /**
     * 
     * @param Alias Codes
     * @param code
     * @return
     */
    public static String getDescrByAliasCode(Collection codes, String code) {
    	if(codes == null || code == null) {
    		return null;
    	}
    	
    	for( Iterator codesIter = codes.iterator();codesIter.hasNext();) {
    		CodeResponseEvent codeResp = (CodeResponseEvent)codesIter.next();
    		if(code.equals( codeResp.getCode() )) {
    			return codeResp.getDescription();
    		}
    	}
    	
    	return null;
    }


    /**
     *
     * @return Collection signedStatusCodes
     */
    public static List getSignatureCommands(boolean sort)
    {
        List signatureCommands = new ArrayList();
        CodeResponseEvent codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_RETURN);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_RETURN_DESCRIPTION);
        signatureCommands.add(codeEvent);

        codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_SIGNED);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_SIGN_DESCRIPTION);
        signatureCommands.add(codeEvent);

        codeEvent = new CodeResponseEvent();
        codeEvent.setCode(PDJuvenileWarrantConstants.WARRANT_UNSEND);
        codeEvent.setDescription(PDJuvenileWarrantConstants.WARRANT_UNSEND_DESCRIPTION);
        signatureCommands.add(codeEvent);

        if (sort == true)
        {
            Collections.sort(signatureCommands);
        }

        return signatureCommands;
    }

    public static JuvenileSchoolDistrictCodeResponseEvent getSchool(Collection districts, String schoolDistrictId, String schoolId)
    {
        JuvenileSchoolDistrictCodeResponseEvent school = new JuvenileSchoolDistrictCodeResponseEvent();
        if (schoolDistrictId != null && schoolId != null && districts != null)
        {
            Iterator i = districts.iterator();
            while (i.hasNext())
            {
                school = (JuvenileSchoolDistrictCodeResponseEvent) i.next();
                if (school.getDistrictCode().equals(schoolDistrictId) && school.getSchoolCode().equals(schoolId))
                {
                    break;
                }
            }
        }
        return school;
    }

    public static List getSchoolCodes(String schoolDistrictId, boolean sort)
    {
        Collection districts = UIJuvenileHelper.fetchSchoolDistricts();

        List schools = new ArrayList();

        Iterator i = districts.iterator();
        while (i.hasNext())
        {
            JuvenileSchoolDistrictCodeResponseEvent school = (JuvenileSchoolDistrictCodeResponseEvent) i.next();

            // Add school district as the key for locating the school collection
            if (school.getDistrictCode().equals(schoolDistrictId))
            {
                schools.add(school);
            }
        }

        if (sort == true)
        {
            Collections.sort(schools);
        }
        return schools;
    }

    public static List getScarMarks(boolean sort)
    {
        RequestEvent requestEvent = (RequestEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSCARMARKCODES);
        CompositeResponse response = MessageUtil.postRequest(requestEvent);
        List codes = MessageUtil.compositeToList(response, ICode.class);
        if (sort == true)
        {
            Collections.sort(codes);
        }
        return codes;
    }

    /**
     * @deprecated - Use getScarMarks(boolean).
     */
    public static List getScarsMarksCodes(boolean sort)
    {
        return ComplexCodeTableHelper.getCriminalCodes("", sort);
    }


    /**
     * Will return a collection of PrintTemplateResponseEvents.
     *
     * @return Collection
     */
    public static List getPrintTemplatesDetails(String agencyId)
    {
        GetPrintTemplatesEvent printTmplRequestEvent = new GetPrintTemplatesEvent();

        printTmplRequestEvent.setAgencyId(agencyId);

        CompositeResponse response = MessageUtil.postRequest(printTmplRequestEvent);

        List printTmpls = MessageUtil.compositeToList(response, PrintTemplatesResponseEvent.class);

        Collections.sort(printTmpls);

        return printTmpls;
    }


    public static List getGradeLevelsCodesWoKindergarten()
    {
        List gradeLevels = SimpleCodeTableHelper.getUnsortedCodeRespEvts(PDCodeTableConstants.GRADE_LEVEL);
        int len = gradeLevels.size();
        for (int i = 0; i < len; i++)
        {
            CodeResponseEvent cre = (CodeResponseEvent) gradeLevels.get(i);
            if (cre.getCode().equals(PDCodeTableConstants.KINDERGARTEN))
            {
                gradeLevels.remove(i);
                break;
            }
        }
        Collections.sort(gradeLevels, new GradeLevelComparator());
        return gradeLevels;
    }


    public static List getLawEnforcementDepartments(boolean sort)
    {
        GetLawEnforcementDepartmentsEvent departmentEvent = (GetLawEnforcementDepartmentsEvent) EventFactory
                .getInstance(AgencyControllerServiceNames.GETLAWENFORCEMENTDEPARTMENTS);

        departmentEvent.setLawEnforcementInd(UIConstants.YES);
        CompositeResponse response = MessageUtil.postRequest(departmentEvent);

        List departments = MessageUtil.compositeToList(response, DepartmentResponseEvent.class);

        if (sort == true)
        {
            Collections.sort(departments);
        }

        return departments;
    }

    /**
     * @return
     */
    public static List getAllServiceEventTypes()
    {
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETSERVICETYPECD);
		serviceTypeEvent.setCodeTableName("SERVICE_TYPE");
		CompositeResponse compositeResponse = MessageUtil.postRequest(serviceTypeEvent);
		List serviceTypeResponses = MessageUtil.compositeToList(compositeResponse,
				ServiceTypeCdResponseEvent.class);
		Collections.sort((List) serviceTypeResponses);
        return serviceTypeResponses;
    }

    /**
     * Gets all the supervision Categorys and the supervision types for those categories
     * ther should be a one to one relationship of type to category and a one to many of category to type
     * 
     * @return
     */
    public static List getAllSupervisionCatTypes()
    {
		GetAllSupervisionCategoryTypesEvent event = (GetAllSupervisionCategoryTypesEvent) EventFactory
		.getInstance(JuvenileCaseControllerServiceNames.GETALLSUPERVISIONCATEGORYTYPES);
		CompositeResponse compositeResponse = MessageUtil.postRequest(event);
		List responses = MessageUtil.compositeToList(compositeResponse,
				SupervisionTypeMapResponseEvent.class);
		HashMap myMap=new HashMap();
		if(responses!=null && responses.size()>0){
			Iterator myRespIter=responses.iterator();
			while(myRespIter.hasNext()){
				SupervisionTypeMapResponseEvent myRespVal=(SupervisionTypeMapResponseEvent)myRespIter.next();
				if(!"Y".equalsIgnoreCase(myRespVal.getInactiveInd()) && myMap.containsKey(myRespVal.getSupervisionTypeId())){
					SupervisionTypeMapResponseEvent myMapVal=(SupervisionTypeMapResponseEvent)myMap.get(myRespVal.getSupervisionTypeId());
					if(myMapVal.getSupervisionCatId()==null || myMapVal.getSupervisionCatId().equals("")){
						myMap.put(myRespVal.getSupervisionTypeId(),myRespVal);
					}
				}
				else{
					myMap.put(myRespVal.getSupervisionTypeId(),myRespVal);
				}
			}
		}
		ArrayList myList=new ArrayList();
		Collection myColl=myMap.values();
		Iterator myIter=myColl.iterator();
		while(myIter.hasNext()){
			myList.add(myIter.next());
		}
        return myList;
    }
    
    public static List getAllSupervisionCategoryTypes()
    {
		GetAllSupervisionCategoryTypesEvent event = (GetAllSupervisionCategoryTypesEvent) EventFactory
		.getInstance(JuvenileCaseControllerServiceNames.GETALLSUPERVISIONCATEGORYTYPES);
		CompositeResponse compositeResponse = MessageUtil.postRequest(event);
		List responses = MessageUtil.compositeToList(compositeResponse,
				SupervisionTypeMapResponseEvent.class);
		HashMap myMap=new HashMap();
		if(responses!=null && responses.size()>0){
			Iterator myRespIter=responses.iterator();
			while(myRespIter.hasNext()){
				SupervisionTypeMapResponseEvent myRespVal=(SupervisionTypeMapResponseEvent)myRespIter.next();
				
				myMap.put(myRespVal.getSupervisionTypeId(),myRespVal);
			}
		}
		ArrayList myList=new ArrayList();
		Collection myColl=myMap.values();
		Iterator myIter=myColl.iterator();
		while(myIter.hasNext()){
			myList.add(myIter.next());
		}
        return myList;
    }

    /**
     * @return
     */
    public static List getAllServiceProviders()
    {
		GetServiceProvidersEvent getServiceProvidersEvent = (GetServiceProvidersEvent) EventFactory
		.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERS);

		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo user = securityManager.getIUserInfo();
   		getServiceProvidersEvent.setAgencyId(user.getAgencyId());
		getServiceProvidersEvent.setAllServiceProviders(true);

		CompositeResponse compositeResponse = MessageUtil.postRequest(getServiceProvidersEvent);
		List serviceProviderResponses = MessageUtil.compositeToList(compositeResponse,
				ServiceProviderResponseEvent.class);
		Collections.sort((List) serviceProviderResponses);
        return serviceProviderResponses;
    }
    
    /**
     * Get the service providers only which have programs asociated with them It uses CSSERVICEPROVIDERS view.
     * @return
     */
    public static List getServiceProvidersWithPrograms()
    {
		GetSPNamesEvent getSPEvent = (GetSPNamesEvent) EventFactory
		.getInstance(ServiceProviderControllerServiceNames.GETSPNAMES);
   		CompositeResponse compositeResponse = MessageUtil.postRequest(getSPEvent);
		List serviceProviderResponses = MessageUtil.compositeToList(compositeResponse , ServiceProviderResponseEvent.class);
		Collections.sort((List) serviceProviderResponses);
        return serviceProviderResponses;
    }
    

    /**
     * @return
     */
    public static List getAllJuvenileLocationUnits()
    {
        GetJuvLocationUnitsByAgencyEvent locationsEvent = new GetJuvLocationUnitsByAgencyEvent();
        ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = securityManager.getIUserInfo();
        locationsEvent.setAgencyId(userInfo.getAgencyId());
        CompositeResponse compositeResponse = MessageUtil.postRequest(locationsEvent);
        List locationResponses = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
        Collections.sort(locationResponses,LocationResponseEvent.JuvUnitNameComparator);
        return locationResponses;
    }

    /**
     * @return
     */
    public static List getActiveJuvenileLocationUnits()
    {
        GetJuvLocationUnitsByAgencyEvent locationsEvent = new GetJuvLocationUnitsByAgencyEvent();
        ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = securityManager.getIUserInfo();
        locationsEvent.setAgencyId(userInfo.getAgencyId());
        locationsEvent.setUnitStatusId("A");
        locationsEvent.setLocStatusCd("A");
        CompositeResponse compositeResponse = MessageUtil.postRequest(locationsEvent);
        List locationResponses = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
        Collections.sort(locationResponses,LocationResponseEvent.JuvUnitNameComparator );
        return locationResponses;
    }
    /**
     * @param sort
     * @return
     */
    public static List getOffenseCodes(boolean sort)
    {
        GetOffenseCodesEvent codeEvent = (GetOffenseCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETOFFENSECODES);

        CompositeResponse response = MessageUtil.postRequest(codeEvent);

        List offenseCodes = MessageUtil.compositeToList(response, OffenseCodeResponseEvent.class);

        if (sort)
        {
            Collections.sort(offenseCodes);
        }

        return offenseCodes;
    }

    /**
     * @param sort
     * @return
     */
    public static List getMagistrateCodes(boolean sort)
    {
        GetMagistratesEvent codeEvent = (GetMagistratesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETMAGISTRATES);

        CompositeResponse response = MessageUtil.postRequest(codeEvent);

        List magistrates = MessageUtil.compositeToList(response, MagistrateResponseEvent.class);

        if (sort)
        {
            Collections.sort(magistrates);
        }

        return magistrates;
    }
    public static List getCSInhouseLocationCodes(){
    	
		GetLocationsForStaffPositionEvent reqEvent = (GetLocationsForStaffPositionEvent) EventFactory.getInstance(LocationControllerServiceNames.GETLOCATIONSFORSTAFFPOSITION);
	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    IUserInfo userInfo = securityManager.getIUserInfo();
	    reqEvent.setAgencyId(userInfo.getAgencyId());
	    reqEvent.setStatusId(PDCodeTableConstants.STATUS_ACTIVE);
	    //reqEvent.setInHouse(true);
	    
	    CompositeResponse compositeResponse = MessageUtil.postRequest(reqEvent);
	    List locationResponses = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
	    Collections.sort(locationResponses);
	    return locationResponses;
    }


    /**
     * @return
     */
    public static List getLocationCodes()
    {
        GetLocationsByAgencyEvent locationsEvent = new GetLocationsByAgencyEvent();
        ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = securityManager.getIUserInfo();
        locationsEvent.setAgencyId(userInfo.getAgencyId());

        CompositeResponse compositeResponse = MessageUtil.postRequest(locationsEvent);
        List activeLocations = new ArrayList();
        List locationResponses = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
        //remove inactive locations
        Iterator iter = locationResponses.iterator();
        while (iter.hasNext())
        {
            LocationResponseEvent resp = (LocationResponseEvent) iter.next();
            if ("A".equals(resp.getStatusId()))
            {
                activeLocations.add(resp);
            }
        }
        Collections.sort(activeLocations);
        return activeLocations;
    }

    /**
     * @return
     */
    public static String getLocationDesc(List locationResponses, String aLocationId)
    {
    	String locationName="";
       if(locationResponses!=null && locationResponses.size()>0 && aLocationId!=null && !aLocationId.equals("")){
	        Iterator iter = locationResponses.iterator();
	        while (iter.hasNext())
	        {
	            LocationResponseEvent resp = (LocationResponseEvent) iter.next();
	            if (aLocationId.equals(resp.getLocationId()))
	            {
	                locationName=resp.getLocationName();
	                break;
	            }
	        }
       }
        return locationName;
    }

    /**
     *
     * @return Collection
     */
    public static List getJJSRaces(boolean sort)
    {
        // TODO Change event name to be more generic
        GetRaceEthnicityCodesEvent codeEvent = (GetRaceEthnicityCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETRACEETHNICITYCODES);

        CompositeResponse response = MessageUtil.postRequest(codeEvent);

        List races = MessageUtil.compositeToList(response, RaceEthnicityCodeResponseEvent.class);

        //		if (sort)
        //		{
        //			Collections.sort((List) codes);
        //		}

        return races;
    }

    /**
     * Return Court Codes
     *
     * @return Collection
     */
    public static List getJuvenileFacilities(boolean sort)
    {
        GetJuvenileFacilitiesEvent facilityEvent = new GetJuvenileFacilitiesEvent();
        facilityEvent.setDepartment("H");
        CompositeResponse response = MessageUtil.postRequest(facilityEvent);
        List facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);
        if (sort == true)
        {
            Collections.sort(facilityColl);
        }
        return facilityColl;
    }

    /**
     * @deprecated
     */
    public static ICode getCriminalCode(String category, String code)
    {
        GetScarsMarksTattoosCodesEvent codeEvent = (GetScarsMarksTattoosCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETSCARSMARKSTATTOOSCODES);

        codeEvent.setCategory(category);
        codeEvent.setCode(code);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(codeEvent);
        CompositeResponse response = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST).getReply();
        ICode codeResponse = (ICode) MessageUtil.filterComposite(response, ICode.class);
        return codeResponse;
    }

    public static List getCriminalCodes(String category, boolean sort)
    {
        // TODO Change event name to be more generic
        GetScarsMarksTattoosCodesEvent codeEvent = (GetScarsMarksTattoosCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETSCARSMARKSTATTOOSCODES);

        codeEvent.setCategory(category);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(codeEvent);
        CompositeResponse response = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST).getReply();

        List codes = MessageUtil.compositeToList(response, ScarsMarksTattoosCodeResponseEvent.class);
        if (codes != null && sort == true)
        {
            Collections.sort(codes);
        }

        return codes;
    }
    
    
    public static List getEventTypesByContext(String context, boolean sort) {
    	GetCSEventTypesEvent codeEvent = 
			(GetCSEventTypesEvent)EventFactory.getInstance(
				CSEventControllerServiceNames.GETCSEVENTTYPES);
    	codeEvent.setContext(context);
		EventManager.getSharedInstance(
				EventManager.REQUEST).postEvent(codeEvent);
        CompositeResponse response = 
        	(CompositeResponse) EventManager.getSharedInstance(
        			EventManager.REQUEST).getReply();
        
        List codes = MessageUtil.compositeToList(
        		response, CSEventTypeResponseEvent.class);
        if (codes != null && sort == true)
        {
            Collections.sort(codes);
        }

        return codes;
    }
    
    
    public static String getEventTypeDesc(String context, String eventTypeCd) {
    	List eventTypeList = getEventTypesByContext(context, true);
    	if(eventTypeList != null) {
    		for(Iterator iter = eventTypeList.iterator();iter.hasNext();) {
    			CSEventTypeResponseEvent eventType = 
    				(CSEventTypeResponseEvent)iter.next();
    			if(eventType.getEventType().equals(eventTypeCd)) {
    				return eventType.getDescription();
    			}
    		}
    	}
    	return null;
    }
    
    public static List getActiveHealthIssues()
    {
        List allHealthIssues = SimpleCodeTableHelper.getUnsortedCodeRespEvts("HEALTH_ISSUE");
        List healthIssues = new ArrayList();
        
        Iterator iter = allHealthIssues.iterator();
        while (iter.hasNext())
        {
            CodeResponseEvent resp = (CodeResponseEvent) iter.next();
            if (("Active".equalsIgnoreCase(resp.getStatus())) || ("ACTIVE".equalsIgnoreCase(resp.getStatus())))
            {
                healthIssues.add(resp);
            }
        }
  
        Collections.sort(healthIssues);
        return healthIssues;
    }
    
    /**
     * @return
     */
    public static List getTechnicalVOPs()
    {
    	GetJuvenileTechnicalVOPCodesEvent jtvEvent = 
    		(GetJuvenileTechnicalVOPCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILETECHNICALVOPCODES);
		
    	List techCodeResponses = MessageUtil.postRequestListFilter( jtvEvent,JuvenileTechnicalVOPCodesResponseEvent.class );
		Collections.sort((List) techCodeResponses);
        return techCodeResponses;
    }
    
    /**
     * @return
     */
    public static List getJuvenileVOPSanctions()
    {
    	GetJuvenileVOPSanctionCodesEvent jscEvent = 
    		(GetJuvenileVOPSanctionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEVOPSANCTIONCODES);
		
    	List sanctionCodeResponses = MessageUtil.postRequestListFilter( jscEvent,JuvenileVOPSanctionCodesResponseEvent.class );
    	
		Collections.sort((List) sanctionCodeResponses);
        return sanctionCodeResponses;
    }
    /**
     * @return
     */
    public static List getRiskResultGroups()
    {
    	GetRiskResultGroupCodesEvent rrgEvent = 
    		(GetRiskResultGroupCodesEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKRESULTGROUPCODES);
		
    	List rrgCodeResponses = MessageUtil.postRequestListFilter( rrgEvent,RiskResultGroupCodesResponseEvent.class );
    	Collections.sort((List) rrgCodeResponses);
        return rrgCodeResponses;
    }
    
    /**
     * @return
     */
    public static List getJuvenileCodeTableChildCodes(String codeTableName)
    {
    	GetJuvenileCodeTableChildCodesEvent josEvent = 
    		(GetJuvenileCodeTableChildCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECODETABLECHILDCODES);
    	josEvent.setCodeTableName(codeTableName);
    	List outsubCodeResponses = MessageUtil.postRequestListFilter( josEvent,JuvenileCodeTableChildCodesResponseEvent.class );
    	Collections.sort((List) outsubCodeResponses);
        return outsubCodeResponses;
    }   
    
    /**
     * @return
     */
    public static List getRefAssmntTypeCodes()
    {
	GetJuvenileRefAssignmentTypeEvent dsmEvent = 
    		(GetJuvenileRefAssignmentTypeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEREFASSIGNMENTTYPE);
		
    	List assmntTypeResponses = MessageUtil.postRequestListFilter( dsmEvent,JuvenileRefAssgnmtResponseEvent.class );
    	
		Collections.sort((List) assmntTypeResponses);
        return assmntTypeResponses;
    }
    
    /**
     * @return
     */
    public static List getReleasedFromDetentionCodes()
    {
	GetJuvenileReleasedFromDetentionEvent relEvent = 
    		(GetJuvenileReleasedFromDetentionEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILERELEASEDFROMDETENTION);
		
    	List releasedFromResponse = MessageUtil.postRequestListFilter( relEvent,JuvenileReleasedFromResponseEvent.class );
    	
	Collections.sort((List) releasedFromResponse);
        return releasedFromResponse;
    }
    
    /**
     * @return
     * Only records without inactive ind =y
     */
    public static List getActiveReleasedFromDetentionCodes()
    {
	GetJuvenileReleasedFromDetentionEvent relEvent = 
    		(GetJuvenileReleasedFromDetentionEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILERELEASEDFROMDETENTION);
	
	List<JuvenileReleasedFromResponseEvent> activeList = new ArrayList();
    	List releasedFromResponse = MessageUtil.postRequestListFilter( relEvent,JuvenileReleasedFromResponseEvent.class );
    	for( int x=0;x< releasedFromResponse.size();x++){
    	    
    	    JuvenileReleasedFromResponseEvent codeResp = (JuvenileReleasedFromResponseEvent) releasedFromResponse.get(x);
    	    if("Y".equalsIgnoreCase( codeResp.getStatus())){
        	  
    	    }else{
        	    // add to list
    		activeList.add(codeResp);
    	    }
    	}
	Collections.sort((List) activeList);
        return activeList;
    }
    
    /**
     * @return
     */
    public static List getJuvenileDSM5Codes()
    {
    	GetJuvenileDSM5CodesWithTJJDIDEvent dsmEvent = 
    		(GetJuvenileDSM5CodesWithTJJDIDEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDSM5CODESWITHTJJDID);
		
    	List dsmCodeResponses = MessageUtil.postRequestListFilter( dsmEvent,JuvenileDSM5CodesResponseEvent.class );
    	
		Collections.sort((List) dsmCodeResponses);
        return dsmCodeResponses;
    }
    
    
    /**
     * @return
     */
    public static String getJuvenileDSM5CodeDescription(String code)
    {
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    	GetJuvenileDSM5CodeEvent dsmEvent = 
    		(GetJuvenileDSM5CodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDSM5CODE);    	
    	dsmEvent.setCode(code);
    	dispatch.postEvent(dsmEvent);
    	
    	CompositeResponse response = (CompositeResponse)dispatch.getReply();
    	JuvenileDSM5CodesResponseEvent dsm5CodeResp =(JuvenileDSM5CodesResponseEvent)MessageUtil.filterComposite(response,JuvenileDSM5CodesResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		if(dsm5CodeResp!=null)
			return dsm5CodeResp.getDescription();
		else
			return "";
       
    }
    
    public static String getJuvenileDSM5CodeStatus(String code)
    {
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    	GetJuvenileDSM5CodeEvent dsmEvent = 
    		(GetJuvenileDSM5CodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDSM5CODE);    	
    	dsmEvent.setCode(code);
    	dispatch.postEvent(dsmEvent);
    	
    	CompositeResponse response = (CompositeResponse)dispatch.getReply();
    	JuvenileDSM5CodesResponseEvent dsm5CodeResp =(JuvenileDSM5CodesResponseEvent)MessageUtil.filterComposite(response,JuvenileDSM5CodesResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		if(dsm5CodeResp!=null)
			return dsm5CodeResp.getStatus();
		else
			return "";
       
    }
    
    public static List<DrugTypeCodeResponseEvent> getDrugTypeCodes(){
	RequestEvent drugTypeCodeEvent = (RequestEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETDRUGTYPECODES);
        CompositeResponse response = MessageUtil.postRequest(drugTypeCodeEvent);
        List<DrugTypeCodeResponseEvent> filtereDrugTypeCodes = new ArrayList<>();
        List<DrugTypeCodeResponseEvent> drugTypeCodes = MessageUtil.compositeToList(response, DrugTypeCodeResponseEvent.class);
        if (drugTypeCodes != null
        	&& drugTypeCodes.size() > 0 ) {
            for (DrugTypeCodeResponseEvent drugTypeCode : drugTypeCodes ){
        	if ("YES".equals( drugTypeCode.getDrugTest() ) ) {
        	    filtereDrugTypeCodes.add( drugTypeCode );
        	}
            }
        }
        return filtereDrugTypeCodes;
    }
    
    public static String getDrugTypeCodeDescription(List<DrugTypeCodeResponseEvent>drugTypeCodes, String code){
	String codeDescription = "";
	for (DrugTypeCodeResponseEvent drugTypeCode : drugTypeCodes ) {
	    if ( code.equals(drugTypeCode.getCode()) ) {
		codeDescription = drugTypeCode.getDescription();
	    }
	}
	
	return codeDescription;
    }
    
    public static List<DrugTestResultCodeResponseEvent> getDrugTestResultCodes(){
	RequestEvent drugTestResultCodeEvent = (RequestEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETDRUGTESTRESULTCODES);
        CompositeResponse response = MessageUtil.postRequest(drugTestResultCodeEvent);
        List<DrugTestResultCodeResponseEvent> drugTestResultCodes = MessageUtil.compositeToList(response, DrugTestResultCodeResponseEvent.class);
        return drugTestResultCodes;
    }
    
    public static String getDrugTestResultDescription(List<DrugTestResultCodeResponseEvent>drugTestResultCodes, String code){
	String codeDescription = "";
	for (DrugTestResultCodeResponseEvent drugTestResultCode : drugTestResultCodes  ) {
	    if ( code.equals( drugTestResultCode.getCode() ) ) {
		codeDescription = drugTestResultCode.getDescription();
	    }
	}
	
	return codeDescription;
    }
    
    public static String getDrugTestResultsResult(List<DrugTestResultCodeResponseEvent>drugTestResultCodes, String code){
	String codeResult = "";
	for (DrugTestResultCodeResponseEvent drugTestResultCode : drugTestResultCodes  ) {
	    if ( code.equals( drugTestResultCode.getCode() ) ) {
		codeResult = drugTestResultCode.getResult();
	    }
	}
	
	return codeResult;
    }
    
    public static List<OverrideOptionsResponseEvent>getOverrideOptionCodes(){
	GetOverrideOptionCodesEvent overrideOptionCodeEvent = (GetOverrideOptionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETOVERRIDEOPTIONCODES);
        CompositeResponse response = MessageUtil.postRequest(overrideOptionCodeEvent);
        List<OverrideOptionsResponseEvent> overrideOptionCodes = MessageUtil.compositeToList(response, OverrideOptionsResponseEvent.class);
        List<OverrideOptionsResponseEvent> filteredOverrideOptionCodes = new ArrayList<>();
        if ( overrideOptionCodes != null
        	&& overrideOptionCodes.size() > 0 ) {
            for (OverrideOptionsResponseEvent overrideOptionCode : overrideOptionCodes ) {
        	if ( "ACTIVE".equals( overrideOptionCode.getStatus().toUpperCase()) ){
        	    filteredOverrideOptionCodes.add(overrideOptionCode);
        	}
        	
        	
            }
        }
        return filteredOverrideOptionCodes;
    }
    
    
}// END CLASS
