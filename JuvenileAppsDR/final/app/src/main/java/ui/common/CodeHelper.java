package ui.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import messaging.administerlocation.GetJuvLocationUnitsByAgencyEvent;
import messaging.administerlocation.GetLocationsByAgencyEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.agency.GetLawEnforcementDepartmentsEvent;
import messaging.codetable.GetCodeEvent;
import messaging.codetable.GetCodesEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.GetJuvenileRuleCompletionStatusCodesEvent;
import messaging.codetable.GetMagistratesEvent;
import messaging.codetable.GetOffenseCodeEvent;
import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.GetRaceEthnicityCodesEvent;
import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.person.reply.RaceEthnicityCodeResponseEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.codetable.reply.JuvenileRuleCompletionStatusCodeResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.cscdstaffposition.GetStaffPositionsForSupervisionOrderEvent;
import messaging.juvenile.reply.PlacementYearsResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionorder.GetPrintTemplatesEvent;
import messaging.supervisionorder.reply.MagistrateResponseEvent;
import messaging.supervisionorder.reply.PrintTemplatesResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;
import pd.codetable.Code;
import ui.juvenilecase.UIJuvenileHelper;
import ui.security.SecurityUIHelper;

/**
 * @author Jim Fisher
 * 
 * This class is used to give a level of convenience for retrieving codes (CodeResponseEvents) for
 * the UI. Each get(code) method will return codes of the type asked for or an empty collection if
 * no codes exist for that type.
 */
public final class CodeHelper
{
	public static Map buildCodeMap(Collection codes)
	{
		Map codeMap = new HashMap();
		if (codes != null)
		{
			Iterator i = codes.iterator();
			while (i.hasNext())
			{
				ICode code = (ICode) i.next();
				codeMap.put(code.getCode(), code);
			}
		}
		return codeMap;
	}

	static public String[] extractCodes(List codes)
	{
		String[] codeIds = null;
		if (codes != null && codes.size() > 0)
		{
			int len = codes.size();
			codeIds = new String[len];
			for (int i = 0; i < len; i++)
			{
				ICode code = (ICode) codes.get(i);
				codeIds[i] = code.getCode();
			}
		}

		return codeIds;
	}

	public static List extractCodes(String[] selectedCodes, String delim)
	{
		List descriptions = new ArrayList();
		if (selectedCodes != null)
		{
			int delimLength = delim.length();
			for (int i = 0; i < selectedCodes.length; i++)
			{
				CodeResponseEvent codeEvent = new CodeResponseEvent();
				String value = selectedCodes[i];
				int index = value.indexOf(delim);
				String code = value.substring(0, index);
				String description = value.substring(index + delimLength, value.length());
				codeEvent.setCode(code);
				codeEvent.setDescription(description);
				descriptions.add(codeEvent);
			}
		}

		return descriptions;
	}

	public static String formatCodeDescription(Collection data, String delim)
	{
		StringBuffer buffer = new StringBuffer();
		int dataSize = data.size();
		if (data != null)
		{
			int count = 0;
			Iterator i = data.iterator();
			while (i.hasNext())
			{
				ICode code = (ICode) i.next();
				buffer.append(code.getDescription());
				if (delim != null && count + 1 < dataSize)
				{
					buffer.append(delim);
				}
				count++;
			}
		}
		return buffer.toString();
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static Collection getActivityCategory(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ACTIVITY_CATEGORY, sort);
	}
	
	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static Collection getActivityPermission(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ACTIVITY_PERMISSION, sort);
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static Collection getActivityCode(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ACTIVITY_CODE, sort);
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static Collection getActivityType(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ACTIVITY_TYPE, sort);
	}

	public static List getAddressTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE, true);
	}

	/**
	 * Return Address Type Codes
	 * 
	 * @return Collection
	 */
	public static List getAddressTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE, sort);
	}

	/**
	 * Returns Agency Access Type Codes
	 * 
	 * @return Collection
	 */
	public static List getAgencyAccessTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.AGENCY_ACCESS_TYPE, true);
	}

	/**
	 * Returns Agency Status Codes
	 * 
	 * @return Collection
	 */
	public static List getAgencyStatusCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS, true);
	}

	/**
	 * Returns Agency Type Codes
	 * 
	 * @return Collection
	 */
	public static List getAgencyTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.AGENCY_TYPE, true);
	}

	/**
	 * @param sort
	 * @return
	 */
	public static List getAllSupervisionStaff(boolean sort, String courtId, boolean isStaffIDInOID)
	{
		String agencyId = SecurityUIHelper.getUserAgencyId();
		List staff = null;

		if (agencyId.equals(PDCodeTableConstants.PRETRIAL_AGENCY))
		{
			staff = CodeHelper.getPTSSupervisionStaff();
		}
		else if (agencyId.equals(PDCodeTableConstants.CSCD_AGENCY))
		{

			staff = CodeHelper.getCSCSupervisionStaff(courtId);
		}

		IUserInfo userInfo = SecurityUIHelper.getUser();
		String userFirstName = userInfo.getFirstName();
		String userLastName = userInfo.getLastName();

		if (userFirstName != null && userLastName != null)
		{
			Iterator iter = staff.iterator();
			SupervisionStaffResponseEvent ssre = null;
			boolean addUserToStaff = true;
			String userId = userInfo.getJIMSLogonId();
            String staffOid = "";
			while (iter.hasNext())
			{
				ssre = (SupervisionStaffResponseEvent) iter.next();
				if (ssre.getLogonId() != null && ssre.getLogonId().equals(userId))
				{
					addUserToStaff = false;
					staffOid = ssre.getSupervisionStaffId();
					break;
				}
			}
			if (addUserToStaff)
			{
				ssre = new SupervisionStaffResponseEvent();
				ssre.setFirstName(userFirstName);
				ssre.setMiddleName(userInfo.getMiddleName());
				ssre.setLastName(userLastName);
				ssre.setLogonId(userInfo.getJIMSLogonId());
				if(!isStaffIDInOID){
				    ssre.setSupervisionStaffId(userInfo.getJIMSLogonId());
			    }else{
			    	ssre.setSupervisionStaffId(staffOid);
			    }
				staff.add(ssre);

			}
		}

		if (sort)
		{
			Collections.sort(staff);
		}
		return staff;
	}

	public static List getAppropriateGradeLevelsCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.APPROPRIRATE_GRADE_LEVEL, true);
	}

	public static Collection getBenefitsAssessmentSourceCodes(boolean sort)
	{
		// TODO reuse similar sort methods already employed in CodeHelper
		return UIUtil.sortCodesByCodeId(CodeHelper.getCodes(PDCodeTableConstants.BENEFITS_ASSESSMENT_SOURCE));
	}

	public static List getBuildCodes()
	{
		return getBuildCodes(true);
	}

	/**
	 * Returns Build Codes
	 * 
	 * @return Collection
	 */
	public static List getBuildCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.BUILD, sort);
	}

	/**
	 * Returns Capacity Codes
	 * 
	 * @return Collection
	 */
	public static List getCapacityCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CAPACITY, sort);
	}

	/**
	 * For Administer Casenotes Returns Casenote Subject Codes
	 * 
	 * @return Collection
	 */
	public static List getCasenoteSubjectCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CASENOTE_SUBJECT, true);
	}

	/**
	 * Return Caution Codes
	 * 
	 * @return Collection
	 */
	public static List getCautionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CAUTIONS, sort);
	}

	/**
	 * @return
	 */
	public static List getCharacterDescription()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CHARACTER_DESCRIPTION, true);
	}

	/**
	 * Return Citizenship Codes
	 * 
	 * @return Collection
	 */
	public static List getCitizenShipCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CITIZENSHIP, true);
	}

	/**
	 * Returns City Codes
	 * 
	 * @return Collection
	 */
	public static List getCityCodes()
	{
		return CodeHelper.getCityCodes(true);
	}

	/**
	 * Returns City Codes
	 * 
	 * @return Collection
	 */
	public static List getCityCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CITY, sort);
	}

	public static ICode getCode(Collection codes, String code)
	{
		ICode retValue = null;
		if (codes != null)
		{
			Iterator i = codes.iterator();
			boolean done = false;
			while (done == false && i.hasNext())
			{
				ICode tempCode = (ICode) i.next();
				if (tempCode.getCode().equals(code))
				{
					retValue = tempCode;
					done = true;
				}
			}
		}
		return retValue;
	}

	public static ICode getCode(String codeTableName, String code)
	{
		GetCodeEvent codeEvent = (GetCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODE);
		codeEvent.setCodeTableName(codeTableName);
		codeEvent.setCode(code);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(codeEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ICode codeResponse = (ICode) MessageUtil.filterComposite(response, ICode.class);
		return codeResponse;
	}

	public static String getCodeDescription(Collection codes, String codeId)
	{
		String description = null;
		if (codes != null)
		{
			Iterator i = codes.iterator();
			boolean done = false;
			while (done == false && i.hasNext())
			{
				Object codeObj = i.next();
				CodeResponseEvent code = (CodeResponseEvent) codeObj;
				if (code.getCodeId().equals(codeId))
				{
					description = code.getDescription();
					done = true;
				}

			}
		}
		return description;
	}

	public static String getCodeDescription(String codeTableName, String code)
	{
	    String description = null;
	    Code currentCode   = null;
	    if ( codeTableName != null
		    && codeTableName.length() > 0
		    && code != null
		    && code.length() > 0)  {
	    	currentCode = Code.find(codeTableName, code);
	    }
	    	if ( currentCode != null) {
	    	   description = currentCode.getDescription();
	    	}
		return description;
	}

	public static String getCodeDescriptionByCode(Collection codes, String code)
	{
		String description = null;
		boolean done = false;
		if ((codes != null) && (code != null))
		{
			Iterator i = codes.iterator();
			while (done == false && i.hasNext())
			{
				Object codeObj = i.next();
				if (codeObj instanceof ICode)
				{
					ICode codeEvent = (ICode) codeObj;
					if (codeEvent.getCode().equals(code))
					{
						//<KISHORE>:JIMS200060662 : Modify Casefile Closing Rules reqs and data(PD)-KK
						if(codeEvent instanceof JuvenileRuleCompletionStatusCodeResponseEvent){
							JuvenileRuleCompletionStatusCodeResponseEvent status = (JuvenileRuleCompletionStatusCodeResponseEvent)codeEvent;
							description = status.getDescriptionType();
						}
						else
							description = codeEvent.getDescription();
						description = (description == null) ? "" : description.trim();
						done = true;
					}
				}
			}
		}
		return description;
	}
	/**
	 * 
	 * @param codes
	 * @param code
	 * @return
	 */
	public static String getCodeRaceDescriptionByCode(Collection<RaceEthnicityCodeResponseEvent> codes, String code)
	{
		String description = null;
		Iterator iter = codes.iterator();
		while( iter.hasNext()){
		   
		    RaceEthnicityCodeResponseEvent raceCd = (RaceEthnicityCodeResponseEvent) iter.next();
		    if("L".equalsIgnoreCase( code )){
			//Multiracial has been Inactivated
			description = "WHITE";
			break;

		    }else
		    if( code.equalsIgnoreCase( raceCd.getJjsRaceCode())){
			
			description = raceCd.getDescription();
			break;
		    }		    
		}
		return description;
	}

	/**
	 * @param collection
	 * @param collection2
	 * @return
	 */
	public static List getCodeDescriptions(Collection codes)
	{
		List descriptions = new ArrayList();
		if (codes != null)
		{
			Iterator i = codes.iterator();
			while (i.hasNext())
			{
				ICode code = (ICode) i.next();
				descriptions.add(code.getDescription());
			}
		}
		return descriptions;
	}

	/**
	 * Will return a collection of CodeResponseEvents for a particular code table. Will return an
	 * empty collection if no codes exist for that code table.
	 * 
	 * @param codeTableName
	 * @return Collection
	 */
	public static List<CodeResponseEvent> getCodes(String codeTableName)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETCODES);

		codesRequestEvent.setCodeTableName(codeTableName);

		List<CodeResponseEvent> codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
		
		List<CodeResponseEvent> newCodes = new ArrayList<CodeResponseEvent>();
		
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < codes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
			cre.setDescriptionWithCode(cre.getCode() +"-"+cre.getDescription());
			
			if ( !temp.contains( cre.getCode() ) ){
			    newCodes.add(cre);
			    temp.add(cre.getCode());
			}
			
		}
		
		//filter for active race codes -- from JJS.RACE.CODE table only - AE
		if("JJS.RACE.CODE".equalsIgnoreCase(codeTableName)){
				    
		    newCodes = getActiveRaceCodes(newCodes);
		}

		return newCodes;
	}
	
	public static List<CodeResponseEvent> getActiveRaceCodes(List<CodeResponseEvent> raceCodes)
	{
	    List<CodeResponseEvent> newCodes = new ArrayList<CodeResponseEvent>();
	    
	    List<String> temp = new ArrayList<String>();
	    
		for (int i = 0; i < raceCodes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) raceCodes.get(i);
			String status = cre.getStatus();
			
			if (!"INACTIVE".equalsIgnoreCase(status)){
			    newCodes.add(cre);
			    temp.add(cre.getCode());
			}
			
		}
		
		return newCodes;
	}

	public static List getCodes(String codeTableName, boolean sort)
	{
		List codes = getCodes(codeTableName);
		if (sort == true)
		{
			Collections.sort(codes);
		}
		return codes;
	}
	public static List getActiveCodes(String codeTableName, boolean sort)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETCODES);

		codesRequestEvent.setCodeTableName(codeTableName);
		codesRequestEvent.setThin(false);

		List codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
		List filteredCodes = new ArrayList();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date currentDate = cal.getTime();

		for (int i = 0; i < codes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
			if (cre.getInactiveEffectiveDate() != null){ 
				if (cre.getInactiveEffectiveDate().after(currentDate)){
					cre.setDescriptionWithCode(cre.getCode() +"-"+cre.getDescription());
						filteredCodes.add(cre);}
				}
			else {
				cre.setDescriptionWithCode(cre.getCode() +"-"+cre.getDescription());
				filteredCodes.add(cre);
			}
		}

		if (sort == true && filteredCodes.size() > 0)
		{
			Collections.sort(filteredCodes);
		}
		return filteredCodes;
	}
	
	public static List getActiveCodesByStatus(String codeTableName, boolean sort)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETCODES);

		codesRequestEvent.setCodeTableName(codeTableName);
		codesRequestEvent.setThin(false);

		List codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
		List filteredCodes = new ArrayList();
		for (int i = 0; i < codes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
			if ("A".equals(cre.getStatus()) ||
					"ACTIVE".equals(cre.getStatus()) ||
					"Active".equals(cre.getStatus()))
			{
				cre.setDescriptionWithCode(cre.getCode() +"-"+cre.getDescription());
				filteredCodes.add(cre);}
		}

		if (sort == true && filteredCodes.size() > 0)
		{
			Collections.sort(filteredCodes);
		}
		return filteredCodes;
	}
	
	public static List getActiveCodeDescrptionOnlyByStatus(String codeTableName, boolean sort)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETCODES);

		codesRequestEvent.setCodeTableName(codeTableName);
		codesRequestEvent.setThin(false);

		List codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
		List filteredCodes = new ArrayList();
		for (int i = 0; i < codes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
			if ("A".equals(cre.getStatus()) ||
					"ACTIVE".equals(cre.getStatus()) ||
					"Active".equals(cre.getStatus()))
			{
				cre.setDescriptionWithCode(cre.getDescription()); //actually only description here
				filteredCodes.add(cre);}
		}

		if (sort == true && filteredCodes.size() > 0)
		{
			Collections.sort(filteredCodes);
		}
		return filteredCodes;
	}
	
	public static List getActiveCodesInM204(String codeTableName, boolean sort)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETCODES);

		codesRequestEvent.setCodeTableName(codeTableName);
		codesRequestEvent.setThin(false);

		List codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
		List filteredCodes = new ArrayList();
		for (int i = 0; i < codes.size(); i++) {
			CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
			if (!"Y".equals(cre.getStatus()))
			{
				cre.setDescriptionWithCode(cre.getCode() +"-"+cre.getDescription());
				filteredCodes.add(cre);
			}
		}

		if (sort == true && filteredCodes.size() > 0)
		{
			Collections.sort(filteredCodes);
		}
		return filteredCodes;
	}

	public static List getCompletionStatusCodes()
	{
		//return CodeHelper.getCodes(PDCodeTableConstants.COMPLETION_STATUS);
		//<KISHORE>JIMS200060662 : Modify Casefile Closing Rules reqs and data(PD)-KK
		GetJuvenileRuleCompletionStatusCodesEvent codesRequestEvent = (GetJuvenileRuleCompletionStatusCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETJUVENILERULECOMPLETIONSTATUSCODES);
		List codes = MessageUtil.postRequestListFilter(codesRequestEvent, JuvenileRuleCompletionStatusCodeResponseEvent.class);
		
		return codes;
	}

	public static List getComplexionCodes()
	{
		return getComplexionCodes(true);
	}

	/**
	 * @return
	 */
	public static List getComplexionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SKIN_TONE, sort);
	}

	/**
	 * Return condition status codes
	 * 
	 * @param sort
	 * @return
	 */
	public static List getConditionStatusCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CONDITION_STATUS, sort);
	}

	/**
	 * Return condition type codes (standard, non-standard, standard & non-standard)
	 * 
	 * @param sort
	 * @return
	 */
	public static List getConditionTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CONDITION_TYPE);
	}

	/**
	 * For Administer Casenotes Returns Contact Method Codes
	 * 
	 * @return Collection
	 */
	public static List getContactMethodCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CONTACT_METHOD, true);
	}

	/**
	 * Returns contact relationships to juvenile used in juvenile contact
	 * 
	 * @return List
	 */
	public static List getContactRelationshipsToJuvenile()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.CONTACT_RELATIONSHIP, true);
	}

	/**
	 * For Administer Casenotes Returns Contact With Codes
	 * 
	 * @return Collection
	 */
	public static List getContactWithCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CONTACT_WITH, true);
	}

	/**
	 * For Administer Casenotes Returns Casenote Subject Codes
	 * 
	 * @return Collection
	 */
	public static List getContractTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CONTRACTTYPE, true);
	}

	public static List getCountyCodes()
	{
		// TODO Remove once other forms have been optimized
		return CodeHelper.getCodes(PDCodeTableConstants.COUNTY, true);
	}

	/**
	 * Return County Codes
	 * 
	 * @return Collection
	 */
	public static List getCountyCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.COUNTY, sort);
	}

	/**
	 * Return Court Codes
	 * 
	 * @return Collection
	 */
	public static List getCourtCodes(boolean sort)
	{
		RequestEvent courtEvent = (RequestEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
		List codes = MessageUtil.postRequestListFilter(courtEvent, JuvenileCourtResponseEvent.class);
		if (sort == true)
		{
			Collections.sort(codes);
		}
		return codes;
	}

	/**
	 * Return Court Division Codes
	 * 
	 * @return Collection
	 */
	public static List getCourtDivisionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.COURT_DIVISION, sort);
	}

	public static List getCriminalCodes(String category, boolean sort)
	{
		// TODO Change event name to be more generic
		GetScarsMarksTattoosCodesEvent codeEvent = (GetScarsMarksTattoosCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETSCARSMARKSTATTOOSCODES);

		codeEvent.setCategory(category);
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(codeEvent);
		CompositeResponse response = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST)
				.getReply();

		List codes = MessageUtil.compositeToList(response, ScarsMarksTattoosCodeResponseEvent.class);
		if (codes != null && sort == true)
		{
			Collections.sort(codes);
		}

		return codes;
	}

	/**
	 * @param sort
	 * @return
	 */
	private static List getCSCSupervisionStaff(String courtId)
	{
		GetStaffPositionsForSupervisionOrderEvent event = (GetStaffPositionsForSupervisionOrderEvent) EventFactory
				.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONSFORSUPERVISIONORDER);
		event.setCourtId(courtId);
		event.setAgencyId(SecurityUIHelper.getUserAgencyId());

		CompositeResponse response = MessageUtil.postRequest(event);

		List staff = MessageUtil.compositeToList(response, SupervisionStaffResponseEvent.class);

		return staff;
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getDegrees()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.DRUG_DEGREE, true);
	}

	public static List getDocuments(String agencyId)
	{
		return CodeHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.DOCUMENTS);
	}

	public static List getDriverLicenseClassCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.DRIVERS_LICENSE_CLASS, sort);
	}

	public static List getDrugTypes()
	{
		return getDrugTypes(true);
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getDrugTypes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.DRUG_TYPE, sort);
	}
	
	public static List geActivetDrugTypes(boolean sort)
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.DRUG_TYPE, sort);
	}

	/**
	 * Return Employment Status Codes
	 * 
	 * @return Collection
	 */
	public static List getEmploymentStatusCodes()
	{
		// TODO Update Juvenile Profile to pass in sort
		return CodeHelper.getCodes(PDCodeTableConstants.EMPLOYMENT_STATUS, true);
	}

	/**
	 * Return Ethnicity Codes
	 * 
	 * @return Collection
	 */
	public static List getEthnicityCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ETHNICITY, true);
	}

	public static List getEventTypes(String agencyId)
	{
		return CodeHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.EVENT_TYPE);
	}

	/**
	 * @return
	 */
	public static List getExitTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.EXIT_TYPE, true);
	}

	public static List getEyeColorCodes()
	{
		return getEyeColorCodes(true);
	}

	/**
	 * Return Eye Color Codes
	 * 
	 * @return Collection
	 */
	public static List getEyeColorCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.EYE_COLOR, sort);
	}

	/**
	 * @return
	 */
	public static List getFacilityReleaseReasonCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.FACILITY_RELEASON_REASON, true);
	}

	public static String getFastCodeDescription(String codeTableKey, String code)
	{
		String description = null;
		if (code != null && code.equals("") == false)
		{
			// description = CodeHelper.getCodeDescription(codeTableKey, code);
			List codes = CodeHelper.getCodes(codeTableKey, false);
			description = CodeHelper.getCodeDescriptionByCode(codes, code);
		}
		return description;
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getFrequencies()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.DRUG_FREQUENCY, true);
	}

	/**
	 * @return
	 */
	public static List getGlAccountKeys()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.GLACCOUNTKEY, true);
	}

	public static List getGradeLevelsCodes()
	{
		List gradeLevels = CodeHelper.getCodes(PDCodeTableConstants.GRADE_LEVEL);
		List numericGradeLevels = new ArrayList();
		List charGradeLevels = new ArrayList();
		int len = gradeLevels.size();
		int nCode = 0;
		for (int i = 0; i < len; i++)
		{
			CodeResponseEvent cre = (CodeResponseEvent) gradeLevels.get(i);
			try
			{
				nCode = Integer.parseInt(cre.getCode());
				numericGradeLevels.add(cre);
			} 
			catch (Exception e)
			{
				charGradeLevels.add(cre);
			}
//			if (cre.getCode().equals(PDCodeTableConstants.KINDERGARTEN))
//			{
//				gradeLevels.remove(i);
//				break;
//			}
		} 
		Collections.sort(numericGradeLevels, new GradeLevelComparator());
		len = charGradeLevels.size();
		if (len > 0)
		{
			for (int i = 0; i < len; i++)
			{
				CodeResponseEvent cre = (CodeResponseEvent) charGradeLevels.get(i);
				numericGradeLevels.add(cre);
			}	
		}
		return numericGradeLevels;
	}

	public static List getHairColorCodes()
	{
		return getHairColorCodes(true);
	}

	/**
	 * Return Hair Color Codes
	 * 
	 * @return Collection
	 */
	public static List getHairColorCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.HAIR_COLOR, sort);
	}

	/**
	 * For Administer Casenotes Returns How Generated Codes
	 * 
	 * @return Collection
	 */
	public static List getHowGeneratedCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.HOW_GENERATED, true);
	}

	public static Collection getIncomeSourceCodes(boolean sort)
	{
		// 
		return UIUtil.sortCodesByCodeId(CodeHelper.getCodes(PDCodeTableConstants.INCOME_SOURCE));
	}

	/**
	 * 
	 * @return The isUSCitizenList
	 */
	public static List getIsUSCitizenList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ISUSCITIZEN, true);
	} // end of ui.juvenilecase.form.JuvenileMainForm.getNationalities

	/**
	 * Return Codes
	 * 
	 * @return Collection
	 */
	public static List getJIMS2Subsystems()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JIMS2_SUBSYSTEMS, true);
	}

	/**
	 * 
	 * @return Collection
	 */
	public static List getJJSRaces(boolean sort)
	{
		GetRaceEthnicityCodesEvent codeEvent = (GetRaceEthnicityCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETRACEETHNICITYCODES);

		List races = MessageUtil.postRequestListFilter(codeEvent, RaceEthnicityCodeResponseEvent.class);

		return races;
	}

	public static List getJJSSexCodes()
	{
		return getJJSSexCodes(true);
	}

	/**
	 * Returns Sex Codes
	 * 
	 * @return Collection
	 */
	public static List getJJSSexCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JJS_SEX, sort);
	}

	/**
	 * Return Codes
	 * 
	 * @return Collection
	 */
	public static List getJmcRep()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JMCREP, true);
	}

	/**
	 * Return Jurisdiction Codes
	 * 
	 * @return Collection
	 */
	public static List getJurisdictionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JURISDICTION, sort);
	}

	public static List getJurisdictions()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JURISDICTION, true);
	}

	public static List getJuvenileAbuseLevelCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ABUSE_LEVEL, true);
	}
	public static Collection getPlacementYears()
	{
	    	Date dtToday=DateUtil.getCurrentDate();
	    	Calendar newDate = Calendar.getInstance();
		newDate.setTime( dtToday );		
		Integer curYear = newDate.get( Calendar.YEAR );
		List placementYears = new ArrayList();
		Integer year;
		for (int i = 0; i < 18; i++) 
		{
		    	PlacementYearsResponseEvent plcYear=new PlacementYearsResponseEvent();
			year= curYear-i;
			plcYear.setCode(year.toString());
			plcYear.setDesc(year.toString());
			placementYears.add(plcYear);
		}		
		return placementYears;
	}

	public static List getJuvenileAbuseTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ABUSE_TYPE, true);
	}

	/**
	 * Returns juvenile contact agency names used in juvenile contact
	 * 
	 * @return Collection
	 */
	public static List getJuvenileAgencies()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_AGENCY, true);
	}

	/**
	 * Returns Case File Search Types for Casefiles
	 * 
	 * @return Collection
	 */
	public static List getJuvenileCaseSearchTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SEARCH, true);
	}

	public static List getJuvenileCaseStatuses()
	{
		return getJuvenileCaseStatuses(true);
	}

	/**
	 * Returns Juvenile Case Status Codes
	 * 
	 * @return Collection
	 */
	public static List getJuvenileCaseStatuses(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, sort);
	}

	/**
	 * Return Country Codes
	 * 
	 * @return Collection
	 */
	public static List getJuvenileCountryCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH, sort);
	}

	/**
	 * Return Juvenile Ethnicity Codes
	 * 
	 * @return Collection
	 */
	public static List getJuvenileEthnicityCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_ETHNICITY, true);
	}

	/**
	 * Return Juvenile Reason for Referral Codes
	 * 
	 * @return Collection
	 */
	public static List getReasonForReferralCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.REASON_FOR_REFERRAL, true);
	}
	
	//Er:GANG-JIMS200074578 entry starts
	public static List getGangCliqueCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_CLIQUE, true);
	}
	
	public static List getGangStatusCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_STATUS, true);
	}

	
	/**
	 * Return Juvenile Gang Name Codes
	 * 
	 * @return Collection
	 */
	public static List getGangNameCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_NAME, true);
	}
	
	/**
	 * Return Juvenile Gang Association Type Codes
	 * 
	 * @return Collection
	 */
	public static List getGangAssociationTypeCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_ASSOCIATION_TYPE, true);
	}
	
	/**
	 * Return Juvenile Clique Set Names Codes
	 * 
	 * @return Collection
	 */
	public static List getCliqueSetNameCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.CLIQUE_SET_NAME, true);
	}
	//Er:GANG-JIMS200074578 entry ends
	
	//ER GANG ASSESSMENT STARTS
	public static List getGangAssessmentReasonForReferralCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_ASSMNT_REASONFORREFERRAL, true);
	}
	
	public static List getGangAssessmentLevelOfGangInvolvementCodes()
	{
		List codes = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GANG_ASSESSMENT_LEVELOFGANGINVOLVEMENT, true);
		//Bug fix:15323 swap to get the right order
		//after sort the order is high,low,medium.
		Collections.swap(codes, 0, 2);//medium,low,high
		Collections.swap(codes, 0, 1);//low,medium,high
		return codes;
	}
	
	public static List getRecommendationsCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.RECOMMENDATIONS, true);
	}
	
	public static List getAssessmentReferralTypeCodes()
	{
		return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.ASSESSMENT_REFERRAL_TYPE, true);
	}
	
	//ER GANG ASSESSMENT ENDS
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

	/**Facility Code table Entries starts**/
	public static List getJuvenileFacilityStatus()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.FACILITY_STATUS, true);
	}
	
	public static List getTempReleaseReason()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.TEMP_RELEASE_REASON, true);
	}
	
	/**
	 * Returns Profile Search Types for profiles
	 * 
	 * @return Collection
	 */
	public static List getJuvenileProfileSearchTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_PROFILE_SEARCH, true);
	}

	/**
	 * Returns Juvenile profile Status Codes
	 * 
	 * @return Collection
	 */
	public static List getJuvenileProfileStatuses()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_PROFILE_STATUS, true);
	}

	/**
	 * @return
	 */
	public static List getJuvLocationCodes()
	{
		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.JUVLOCATION, true);
	}

	/**
	 * @return
	 */
	public static List getJuvUnitCodes()
	{

		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.JUVUNIT, true);
	}

	/**
	 * @return
	 */
	public static List getLanguageCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.LANGUAGE, true);
	}

	public static List getLawEnforcementDepartments(boolean sort)
	{
		GetLawEnforcementDepartmentsEvent departmentEvent = (GetLawEnforcementDepartmentsEvent) EventFactory
				.getInstance(AgencyControllerServiceNames.GETLAWENFORCEMENTDEPARTMENTS);

		departmentEvent.setLawEnforcementInd(UIConstants.YES);
		List departments = MessageUtil.postRequestListFilter(departmentEvent, DepartmentResponseEvent.class);

		if (sort == true)
		{
			Collections.sort(departments);
		}

		return departments;
	}

	/**
	 * Returns MAYSI Locations for Casefiles To be used when requesting a New MAYSI Assessment.
	 * 
	 * @return Collection
	 */
	public static List getLengthsOfStay()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MAYSI_LENGTH_OF_STAY, true);
	}

	/**
	 * @return
	 */
	public static List getLevelOfCareCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.LEVELOFCARE, true);
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

		List locationResponses = MessageUtil.postRequestListFilter(locationsEvent, LocationResponseEvent.class);
		List activeLocations = new ArrayList();

		// remove inactive locations
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
	public static List getLocationCodes(String agencyId)
	{
		GetLocationsByAgencyEvent locationsEvent = new GetLocationsByAgencyEvent();
		locationsEvent.setAgencyId(agencyId);

		CompositeResponse compositeResponse = MessageUtil.postRequest(locationsEvent);
		List activeLocations = new ArrayList();
		List locationResponses = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
		// remove inactive locations
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
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getLocationOfUses(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.DRUG_LOCATION_OF_USE, sort);
	}

	/**
	 * @return
	 */
	public static List getLocationUnitCodes()
	{
		GetJuvLocationUnitsByAgencyEvent locationsEvent = new GetJuvLocationUnitsByAgencyEvent();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		IUserInfo userInfo = securityManager.getIUserInfo();
		locationsEvent.setAgencyId(userInfo.getAgencyId());

		List locations = MessageUtil.postRequestListFilter(locationsEvent, LocationResponseEvent.class);
		List locationResponses = new ArrayList();

		int len = locations.size();

		for (int i = 0; i < len; i++)
		{
			LocationResponseEvent resp = (LocationResponseEvent) locations.get(i);
			if ("A".equals(resp.getStatusId()))
			{
				locationResponses.add(resp);
			}
		}
		Collections.sort(locationResponses, LocationResponseEvent.JuvUnitNameComparator);
		return locationResponses;
	}

	/**
	 * @param sort
	 * @return
	 */
	public static List getMagistrateCodes(boolean sort)
	{
		GetMagistratesEvent codeEvent = (GetMagistratesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETMAGISTRATES);

		List magistrates = MessageUtil.postRequestListFilter(codeEvent, MagistrateResponseEvent.class);

		if (sort)
		{
			Collections.sort(magistrates);
		}

		return magistrates;
	}

	/**
	 * @return
	 */
	public static List getMaritalStatusCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MARITAL_STATUS, true);
	}

	/**
	 * Returns MAYSI Locations for Casefiles To be used when requesting a New MAYSI Assessment.
	 * 
	 * @return Collection
	 */
	public static List getMAYSILocations()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MAYSI_LOCATION, true);
	}

	/**
	 * Returns MAYSI Placement Types for Casefiles. To be used when requesting a New MAYSI
	 * Assessment.
	 * 
	 * @return Collection
	 */
	public static List getMAYSIPlacementTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MAYSI_PLACEMENT_TYPES, true);
	}

	/**
	 * @return
	 */
	public static List getNationalityCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH, true);
	}

	/**
	 * @param offenseCode
	 * @return
	 */
	public static String getOffenseCodeDescription(String offenseCode)
	{
		GetOffenseCodeEvent codeEvent = (GetOffenseCodeEvent) EventFactory.getInstance
										(CodeTableControllerServiceNames.GETOFFENSECODE);
		codeEvent.setOffenseCode(offenseCode);
		CompositeResponse response = MessageUtil.postRequest(codeEvent);
		OffenseCodeResponseEvent ocre = (OffenseCodeResponseEvent) MessageUtil.filterComposite(response,
		OffenseCodeResponseEvent.class);
			
		String descr = PDConstants.BLANK;
		if (ocre != null)
		{
			descr = ocre.getDescription();
		}
		return descr;
	}

	/**
	 * @param offenseCode
	 * @return
	 */
	public static OffenseCodeResponseEvent getOffenseCode(String offenseCode)
	{
		GetOffenseCodeEvent codeEvent = (GetOffenseCodeEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODE);
		codeEvent.setOffenseCode(offenseCode);
		CompositeResponse response = MessageUtil.postRequest(codeEvent);
		OffenseCodeResponseEvent ocre = (OffenseCodeResponseEvent) MessageUtil.filterComposite(response,
				OffenseCodeResponseEvent.class);
		return ocre;
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

	public static List getOfficerIdTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.OFFICER_ID_TYPE, sort);
	}

	/**
	 * @return
	 */
	public static List getOfficerStatuses()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.OFFICER_STATUS, true);
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getOfficerSubTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.OFFICER_SUBTYPE, true);
	}

	public static List getOfficerTypeCodes()
	{
		// TODO Remove once forms have been optimized
		return CodeHelper.getCodes(PDCodeTableConstants.OFFICERTYPE, true);
	}

	/**
	 * @return
	 */
	public static List getOfficerTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.OFFICERTYPE, sort);
	}

	/**
	 * Return order status codes
	 * 
	 * @return Collection
	 */
	public static List getOrderStatusCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ORDER_STATUS, true);
	}

	public static List getParticipationCodes()
	{
		List participation = CodeHelper.getCodes(PDCodeTableConstants.SCHOOL_PARTICIPATION, true);
		return participation;
	}
	
	public static List getSplEduCategoryCode()
	{
		List splEduCategory = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.JUV_SPL_EDU_HANDICAPPING_COND, true);
		return splEduCategory;
	}

	public static List getPasswordQuestionCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PASSWORD_QUESTION);
	}

	public static List getPeriods()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PERIOD, true);
	}

	/**
	 * @return
	 */
	public static List getPermanencyPlanCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PERMANENCYPLAN, true);
	}

	/**
	 * @return
	 */
	public static List getPolicyStatuses()
	{
		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.POLICY_STATUS, true);
	}

	/**
	 * Returns title prefixes for name
	 * 
	 * @return Collection
	 */
	public static List getPrefixes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PREFIX, true);
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

	public static List getProgramAttendingCodes()
	{
		List programAttendingCodes = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SCHOOL_PROGRAM, true);
		return programAttendingCodes;
	}

	public static List getProviderTypeReferred()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MAYSI_PROVIDER_TYPE_REFERRED, true);
	}
	//added for U.S #11099
	public static List getProgramScheduleType()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE, true);
	}
	

	/**
	 * @param sort
	 */
	private static List getPTSSupervisionStaff()
	{
		// GetAllSupervisionStaffEvent getStaffEvent =
		// (GetAllSupervisionStaffEvent) EventFactory.getInstance(
		// SupervisionStaffControllerServiceNames.GETALLSUPERVISIONSTAFF);
		//
		// EventManager.getSharedInstance(EventManager.REQUEST).postEvent(getStaffEvent);
		//
		// CompositeResponse response =
		// (CompositeResponse)
		// EventManager.getSharedInstance(EventManager.REQUEST).getReply();
		//
		// Collection staff = MessageUtil.compositeToCollection(response,
		// SupervisionStaffResponseEvent.class);
		//
		// if (staff != null && staff.size() > 0 && sort)
		// {
		// Collections.sort((List) staff);
		// }

		return new ArrayList();

	}

	/**
	 * Returns Capacity Codes
	 * 
	 * @return Collection
	 */
	public static List getRaceCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RACE, sort);
	}
	
	public static List getAltRaceCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RACE_ALT, sort);
	}

	public static List getRaces()
	{
		return getRaceCodes(true);
	}

	/**
	 * @return
	 */
	public static List getRankCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RANK, true);
	}

	/**
	 * Return Recall Reason Codes
	 * 
	 * @return Collection
	 */
	public static List getReasonForUpdateCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.REASON_FOR_UPDATE, sort);
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static Collection getReasonNotDoneCode(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.REASON_NOT_DONE, sort);
	}

	public static List getReasonsNotDone()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.MAYSI_REASON_NOT_DONE, true);
	}

	/**
	 * Return Recall Reason Codes
	 * 
	 * @return Collection
	 */
	public static List getRecallReasonCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RECALL_REASON, sort);
	}

	/**
	 * Returns RelationshipsToJuvenile Codes
	 * 
	 * @return Collection
	 */
	public static List getRelationshipsToJuvenileCodes()
	{
		// TODO Update Juvenile Profile to pass in sort
		// TODO Remove once Juvenile Profile has been updated to pass in sort
		return CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE, true);
	}

	/**
	 * Returns RelationshipsToJuvenile Codes
	 * 
	 * @return Collection
	 */
	public static List getRelationshipsToJuvenileCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE, sort);
	}

	/**
	 * Returns Capacity Codes
	 * 
	 * @return Collection
	 */
	public static List getReleaseDecisionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RELEASE_DECISION, sort);
	}

	public static List getReligionCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RELIGION, sort);
	}

	/**
	 * Return Codes
	 * 
	 * @return Collection
	 */
	public static List getResponseContextLocator()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.RESPONSE_CONTEXT_LOCATOR, true);
	}

	public static List getRuleInfractionCodes()
	{
		List ruleInfractionCodes = CodeHelper.getCodes(PDCodeTableConstants.SCHOOL_RULEINFRACTION, true);
		return ruleInfractionCodes;
	}

	/**
	 * Return Salary Rate Codes
	 * 
	 * @return Collection
	 */
	public static List getSalaryRateCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SALARY_RATE, true);
	}

	/**
	 * Return Admission Type Codes
	 * 
	 * @return Collection
	 */
	public static List getAdmissionTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ADMISSION_TYPE, true);
	}

	public static List getScarMarks(boolean sort)
	{		
		RequestEvent requestEvent = (RequestEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETSCARMARKCODES);
		List codes = MessageUtil.postRequestListFilter(requestEvent, ICode.class);
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
		return CodeHelper.getCriminalCodes("", sort);
	}

	public static JuvenileSchoolDistrictCodeResponseEvent getSchool(Collection districts, String schoolDistrictId,
			String schoolId)
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

	public static List getSchoolAttendanceStatusCodes()
	{
		List schoolAttendanceStatusCodes = CodeHelper.getCodes(PDCodeTableConstants.SCHOOL_ATTENDANCESTATUS, true);
		return schoolAttendanceStatusCodes;
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

	public static List getSchoolExitTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SCHOOL_EXIT_CODES, true);
	}

	/**
	 * Return Codes
	 * 
	 * @return Collection
	 */
	public static List getSecurityInquires()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SECURITY_INQUIRE_BY, true);
	}

	public static List getSelectedCodeDescriptions(Collection codes, String[] selectedValues)
	{
		List selected = new ArrayList();
		if (selectedValues != null && selectedValues.length > 0)
		{
			for (int i = 0; i < selectedValues.length; i++)
			{
				Iterator j = codes.iterator();
				while (j.hasNext())
				{
					ICode code = (ICode) j.next();
					if (code != null && code.getCode().equals(selectedValues[i]))
					{
						selected.add(code.getDescription());
					}
				}
			}
		}
		return selected;
	}

	public static List getSelectedCodeDescriptions(String codeTableName, String[] selectedValues)
	{
		List selected = new ArrayList();
		if (selectedValues != null && selectedValues.length > 0)
		{
			for (int i = 0; i < selectedValues.length; i++)
			{
				ICode code = CodeHelper.getCode(codeTableName, selectedValues[i]);
				if (code != null)
				{
					selected.add(code.getDescription());
				}
			}
		}
		return selected;
	}

	/**
	 * Returns Codes
	 * 
	 * @return Collection
	 */
	public static List getServiceLocations(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SERVICE_LOCATION, sort);
	}

	/**
	 * Returns Service Statuses
	 * 
	 * @return Collection
	 */
	public static List getServiceStatuses(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SERVICE_STATUS, sort);
	}

	/**
	 * @return
	 */
	public static List getSetcicAccessCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SETCIC_ACCESS, true);
	}

	public static List getSeverityLevels(String agencyId)
	{
		return CodeHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.SEVERITY_LEVEL);
	}

	public static List getSexCodes()
	{
		return getSexCodes(true);
	}

	/**
	 * Returns Sex Codes
	 * 
	 * @return Collection
	 */
	public static List getSexCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SEX, sort);
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

	public static List getStateCodes()
	{
		return getStateCodes(true);
	}

	/**
	 * Return State Codes
	 * 
	 * @return Collection
	 */
	public static List getStateCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR, sort);
	}

	public static List getStreetTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, true);
	}

	/**
	 * Return Street Type Codes
	 * 
	 * @return Collection
	 */
	public static List getStreetTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, sort);
	}

	public static List getStreetNumSuffixCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX, true);
	}

	/**
	 * Return StreetNumSuffix Codes
	 * 
	 * @return Collection
	 */
	public static List getStreetNumSuffixCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX, sort);
	}

	/**
	 * Will return a collection of CodeResponseEvents for a particular code table. Will return an
	 * empty collection if no codes exist for that code table.
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

	/**
	 * @return
	 */
	public static List getSupervisionConditionStatuses()
	{
		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.CONDITION_STATUS, true);
	}

	/**
	 * Return Court Division Codes for supervision courts.
	 * 
	 * @return Collection
	 */
	public static List getSupervisionCourtDivisionCodes(boolean sort)
	{
		List aList = CodeHelper.getCourtDivisionCodes(sort);
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
					String agencyId = SecurityUIHelper.getUserAgencyId();
					if (agencyId.equals(PDCodeTableConstants.CSCD_AGENCY))
					{

						if ((courtDiv.intValue() > 1 && courtDiv.intValue() < 4)
								|| (courtDiv.intValue() > 9 && courtDiv.intValue() < 11))
						{
							supCourtDivs.add(cre);
						}

					}
					else
					{

						if ((courtDiv.intValue() > 0 && courtDiv.intValue() < 4)
								|| (courtDiv.intValue() > 6 && courtDiv.intValue() < 11))
						{
							supCourtDivs.add(cre);
						}

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
	 * @return
	 */
	public static List getSupervisionOutcomeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.SUPERVISIONOUTCOME, true);
	}

	/**
	 * Returns Supervison Types for Casefiles
	 * 
	 * @return Collection
	 */
	public static List getSupervisionTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, true);
	}
	
	public static List getActiveSupervisionTypes()
	{
	    return CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, true);
	}

	/**
	 * Returns Supervison Catgeory for Casefiles
	 * 
	 * @return Collection
	 */
	public static List getSupervisionCategories()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, true);
	}
	
	/**
	 * @deprecated - Use getTattoos(boolean).
	 */
	public static List getTattooCodes(boolean sort)
	{
		// TODO Change to not use literal
		return CodeHelper.getCriminalCodes(PDCodeTableConstants.TATTOO_CATEGORY, sort);
	}

	public static List getTattoos(boolean sort)
	{
		RequestEvent requestEvent = (RequestEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETTATTOOCODES);
		List codes = MessageUtil.postRequestListFilter(requestEvent, ICode.class);
		if (sort == true)
		{
			Collections.sort(codes);
		}
		return codes;
	}

	public static List getTransferLocationCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.TRANSFER_LOCATION, sort);
	}

	/**
	 * Return Codes
	 * 
	 * @return Collection
	 */
	public static List getUserTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE, true);
	}

	/**
	 * Return order version types
	 * 
	 * @return Collection
	 */
	public static List getVersionTypes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.VERSION_TYPE, true);
	}

	/**
	 * @return
	 */
	public static List getWarrantAcknowledgeStatusCodes(boolean sort)
	{
		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.WARRANT_ACKNOWLEDGE_STATUS, sort);
	}

	/**
	 * @return
	 */
	public static List getWarrantActivationStatusCodes(boolean sort)
	{
		// TODO Auto-generated method stub
		return CodeHelper.getCodes(PDCodeTableConstants.WARRANT_ACTIVATION_STATUS, sort);
	}

	/**
	 * @return
	 */
	public static List getWarrantStatusCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.WARRANT_STATUS, sort);
	}

	public static List getWarrantTypeCodes(boolean sort)
	{
		return CodeHelper.getCodes(PDCodeTableConstants.WARRANT_TYPE, sort);
	}

	/**
	 * @return
	 */
	public static Collection getWorkDayCodes()
	{
		// TODO reuse similar sort methods already employed in CodeHelper
		return UIUtil.sortCodesByStringCodeId(CodeHelper.getCodes(PDCodeTableConstants.WORKDAY, true));
	}
	
	public static Collection getCauseOfDeathCodes()
	{
	    return CodeHelper.getCodes(PDCodeTableConstants.CAUSE_OF_DEATH, true);
	}
	
	public static Collection getDeathVerificationCodes(){
	    return CodeHelper.getCodes(PDCodeTableConstants.DEATH_VERIFICATION, true);
	}
	

	/**
	 * @return Collection
	 */
	public static List getTjpcDsmivDiagnosisCodes(boolean sort){
		return CodeHelper.getCodes(PDCodeTableConstants.TJPC_DSMIV_DIAGNOSIS, sort);
	}
	
	
	/**
	 * Private constructor
	 */
	private CodeHelper()
	{
	}

	/**
	 * @return
	 */
	public static Collection getIssues()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ISSUE, true);
	}

	/**
	 * @return
	 */
	public static Collection getOptions()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.OPTION, true);
	}

	/**
	 * @return
	 */
	public static Collection getICatogories()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CATEGORY, true);
	}

	/**
	 * Returns Code table type Codes
	 * 
	 * @return Collection
	 */

	public static List getCodetableTypeCodes()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.CODE_TABLE_TYPE_NAME, true);
	}
	
	public static List getDrugTestResults(){
	    return CodeHelper.getCodes(PDCodeTableConstants.DRUG_TEST_RESULTS, true);
	}
	
	public static List getDrugTestAdministered(){
	    return CodeHelper.getCodes(PDCodeTableConstants.DRUG_TEST_ADMINISTERED, true);
	}
	
	public static List getHospitalLengthOfStays(){
	    List<CodeResponseEvent> lengthStays = CodeHelper.getCodes(PDCodeTableConstants.HOSPITAL_LENGTH_OF_STAY, true);
	    if (lengthStays != null
		    && lengthStays.size() > 0 ) {
		Collections.sort(lengthStays, new Comparator<CodeResponseEvent>(){
		    @Override
		    public int compare(CodeResponseEvent c1, CodeResponseEvent c2){
			return c1.getCodeId().compareTo( c2.getCodeId() );
		    }
		});
	    }
	    
	    return lengthStays;
	}
	
	public static List getTjjdSubstanceAbuseCodes(){
	    return CodeHelper.getCodes(PDCodeTableConstants.TJJD_SUBSTANCE_ABUSE, true);
	}
	
	

	/**
	 * Returns a copy of the object, or null if the object cannot be serialized.
	 */
	public static Object replica(Object orig)
	{
		Object obj = null;
		try
		{
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			obj = in.readObject();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		return obj;
	}
}
