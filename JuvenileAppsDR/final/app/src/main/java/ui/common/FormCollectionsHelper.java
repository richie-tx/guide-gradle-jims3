/*
 * Created on Jun 7, 2006
 *
 */
package ui.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import messaging.codetable.InitializeCommonCodeTableDataEvent;
import messaging.codetable.reply.CommonCollectionsResponseEvent;
import messaging.codetable.reply.ICode;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;


/**
 * @author jmcnabb
 *
 * This class serves to load and cache codes common used by JIMS2 system.
 */
public final class FormCollectionsHelper
{
	private static FormCollectionsHelper instance = null;

	/* Collections for address */
	private Collection counties = null;
	private Collection states = null;
	private Collection streetTypes = null;
	private Collection addressTypes = null;

	/*code table collections */
	private Collection builds = null;
	private Collection cautions = null;
	private Collection complexions = null;
	//private Collection courts = null;
	private Collection eyeColors = null;
	private Collection ethnicities = null;
	private Collection hairColors = null;
	private Collection juvenileEthnicities = null;
	private Collection races = null;
	private Collection scarsAndMarks = null;
	private Collection sexes = null;
	private Collection tattoos = null;
	private Collection agencies = null;
	private Collection divisions = null;

	/* Collections for address */
	private Map countiesMap = null;
	private Map statesMap = null;
	private Map streetTypesMap = null;
	private Map addressTypesMap = null;

	/*code table collections */
	private Map buildsMap = null;
	private Map cautionsMap = null;
	private Map complexionsMap = null;
	//private Map courtsMap = null;
	private Map eyeColorsMap = null;
	private Map ethnicitiesMap = null;
	private Map hairColorsMap = null;
	private Map racesMap = null;
	private Map scarsAndMarksMap = null;
	private Map sexesMap = null;
	private Map tattoosMap = null;
	private Map agenciesMap = null;
	private Map divisionsMap = null;

	/**
	 * @return FormCollectionsHelper
	 */
	public static FormCollectionsHelper getInstance()
	{
		if (instance == null)
		{
			instance = new FormCollectionsHelper();
			// comment the following to allow lazy initialization for all collections
			// KEEP IN MIND that if you comment the following, there has to be a call
			// made to get the collection before you can get a description from the collection.
			instance.initialize();
		}
		return instance;
	}

	/**
	 * 
	 */
	private FormCollectionsHelper()
	{
	}

	/**
	 * @return Collection
	 */
	public Collection getBuilds()
	{
		if (builds == null)
		{
			builds = CodeHelper.getBuildCodes(true);
			buildsMap = CodeHelper.buildCodeMap(builds);
		}
		return builds;
	}

	/**
	 * @return Collection
	 */
	public Collection getCautions()
	{
		if (cautions == null)
		{
			cautions = CodeHelper.getCautionCodes(true);
			cautionsMap = CodeHelper.buildCodeMap(cautions);
		}
		return cautions;
	}

	/**
	 * @return Collection
	 */
	public Collection getComplexions()
	{
		if (complexions == null)
		{
			complexions = CodeHelper.getComplexionCodes(true);
			complexionsMap = CodeHelper.buildCodeMap(complexions);
		}
		return complexions;
	}

	/**
	 * @return Collection
	 */
//	public Collection getCourts()
//	{
//		if (courts == null)
//		{
//			courts = CodeHelper.getCourtCodes(true);
//			courtsMap = CodeHelper.buildCodeMap(courts);
//		}
//		return courts;
//	}

	/**
	 * @return Collection
	 */
	public Collection getEyeColors()
	{
		if (eyeColors == null)
		{
			eyeColors = CodeHelper.getEyeColorCodes(true);
			eyeColorsMap = CodeHelper.buildCodeMap(eyeColors);
		}
		return eyeColors;
	}

	/**
	 * @return Collection
	 */
	public Collection getHairColors()
	{
		if (hairColors == null)
		{
			hairColors = CodeHelper.getHairColorCodes(true);
			hairColorsMap = CodeHelper.buildCodeMap(hairColors);
		}
		return hairColors;
	}

	/**
	 * @return Collection
	 */
	public Collection getRaces()
	{
		if (races == null)
		{
			races = CodeHelper.getRaceCodes(true);
			racesMap = CodeHelper.buildCodeMap(races);
		}
		return races;
	}

	/**
	 * @return Collection
	 */
	public Collection getScarsAndMarks()
	{
		if (scarsAndMarks == null)
		{
			scarsAndMarks = CodeHelper.getScarMarks(true);
			scarsAndMarksMap = CodeHelper.buildCodeMap(scarsAndMarks);
		}
		return scarsAndMarks;
	}

	/**
	 * @return Collection
	 */
	public Collection getSexes()
	{
		if (sexes == null)
		{
			sexes = CodeHelper.getSexCodes(true);
			sexesMap = CodeHelper.buildCodeMap(sexes);
		}
		return sexes;
	}

	/**
	 * @return Collection
	 */
	public Collection getTattoos()
	{
		if (tattoos == null)
		{
			tattoos = CodeHelper.getTattoos(true);
			tattoosMap = CodeHelper.buildCodeMap(tattoos);
		}
		return tattoos;
	}

	/**
	 * @return Collection
	 */
	public Collection getAddressTypes()
	{
		if (addressTypes == null)
		{
			addressTypes = CodeHelper.getAddressTypeCodes(true);
			addressTypesMap = CodeHelper.buildCodeMap(addressTypes);
		}
		return addressTypes;
	}

	/**
	 * @return Collection
	 */
	public Collection getCounties()
	{
		if (counties == null)
		{
			counties = CodeHelper.getCountyCodes(true);
			countiesMap = CodeHelper.buildCodeMap(counties);
		}
		return states;
	}

	/**
	 * @return Collection
	 */
	public Collection getStates()
	{
		if (states == null)
		{
			states = CodeHelper.getStateCodes(true);
			statesMap = CodeHelper.buildCodeMap(states);
		}
		return states;
	}

	/**
	 * @return Collection
	 */
	public Collection getStreetTypes()
	{
		if (streetTypes == null)
		{
			streetTypes = CodeHelper.getStreetTypeCodes(true);
			streetTypesMap = CodeHelper.buildCodeMap(streetTypes);
		}
		return streetTypes;
	}

	/**
	 * @return String
	 */
	public Collection getEthnicities()
	{
		if (ethnicities == null)
		{
			ethnicities = CodeHelper.getEthnicityCodes();
			ethnicitiesMap = CodeHelper.buildCodeMap(ethnicities);
		}
		return ethnicities;
	}
	
	/**
	 * @return String
	 */
	public Collection getJuvenileEthnicities()
	{
		if (juvenileEthnicities == null)
		{
			juvenileEthnicities = CodeHelper.getJuvenileEthnicityCodes();
		}
		return juvenileEthnicities;
	}

	/** 
	 * @return Collection
	 */
	public Collection getAgencies()
	{
		if (agencies == null)
		{
			agencies = CodeHelper.getCodes(PDCodeTableConstants.STATE_AGENCY, true);
			agenciesMap = CodeHelper.buildCodeMap(agencies);
		}
		return agencies;
	}

	/**
	 * @return Collection
	 */
	public Collection getDivisions()
	{
		if (divisions == null)
		{
			divisions = new ArrayList();
			divisionsMap = CodeHelper.buildCodeMap(divisions);
		}
		return divisions;
	}

	public String getBuildDescr(String id)
	{
		return getDescription(buildsMap, id);
	}
	
	public String getCautionDescr(String id)
	{
		return getDescription(cautionsMap, id);
	}
	
	public String getComplexionDescr(String id)
	{
		return getDescription(complexionsMap, id);
	}
	
//	public String getCourtDescr(String id)
//	{
//		return getDescription(courtsMap, id);
//	}
	
	public String getEyeColorDescr(String id)
	{
		return getDescription(eyeColorsMap, id);
	}
	
	public String getHairColorDescr(String id)
	{
		return getDescription(hairColorsMap, id);
	}
	
	public String getRaceDescr(String id)
	{
		return getDescription(racesMap, id);
	}
	
	public String getSexDescr(String id)
	{
		return getDescription(sexesMap, id);
	}
	
	public String getStateDescr(String id)
	{
		return getDescription(statesMap, id);
	}
	
	public String getCountyDescr(String id)
	{
		return getDescription(countiesMap, id);
	}
	
	public String getAddressTypeDescr(String id)
	{
		return getDescription(addressTypesMap, id);
	}
	
	public String getStreetTypeDescr(String id)
	{
		return getDescription(streetTypesMap, id);
	}
	
	public String getEthnicityDescr(String id)
	{
		return getDescription(ethnicitiesMap, id);
	}
	
	public String getTattooDescr(String id)
	{
		return getDescription(tattoosMap, id);
	}
	
	public String getScarMarkDescr(String id)
	{
		return getDescription(scarsAndMarksMap, id);
	}
	
	public String getAgencyDescr(String id)
	{
		return getDescription(agenciesMap, id);
	}
	
	public String getDivisionDescr(String id)
	{
		return getDescription(divisionsMap, id);
	}
	
	public static String getDescription(Map values, String key)
	{
		String descr = "";

		if ((key != null) && (!key.trim().equals("")) && (values.containsKey(key)))
		{
			ICode code = (ICode) values.get(key);
			descr = code.getDescription();
		}
		else
		{
			System.out.println("Unable to find description for " + key);
		}

		return descr;
	}

	private void initialize()
	{
		// Load Code Table Lists for Drop Downs
		InitializeCommonCodeTableDataEvent ccEvent =
					(InitializeCommonCodeTableDataEvent) EventFactory.getInstance(
						CodeTableControllerServiceNames.INITIALIZECOMMONCODETABLEDATA);
	
		ArrayList commonCodeTableNames = new ArrayList();
		commonCodeTableNames.add(PDCodeTableConstants.BUILD);
		commonCodeTableNames.add(PDCodeTableConstants.CAUTIONS);
		commonCodeTableNames.add(PDCodeTableConstants.SKIN_TONE);
		//commonCodeTableNames.add(PDCodeTableConstants.COURT);
		commonCodeTableNames.add(PDCodeTableConstants.EYE_COLOR);
		commonCodeTableNames.add(PDCodeTableConstants.ETHNICITY);
		commonCodeTableNames.add(PDCodeTableConstants.HAIR_COLOR);
		commonCodeTableNames.add(PDCodeTableConstants.RACE);
		///commonCodeTableNames.add(PDCodeTableConstants.SCARS_MARKS);
		commonCodeTableNames.add(PDCodeTableConstants.SEX);
		//commonCodeTableNames.add(PDCodeTableConstants.TATTOOS);
		commonCodeTableNames.add(PDCodeTableConstants.STATE_ABBR);
		commonCodeTableNames.add(PDCodeTableConstants.STREET_TYPE);
		commonCodeTableNames.add(PDCodeTableConstants.ADDRESS_TYPE);
		
		ccEvent.setCodeTableNames(commonCodeTableNames);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(ccEvent);
		
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(replyEvent);
		CommonCollectionsResponseEvent responseEvent = 
					(CommonCollectionsResponseEvent) MessageUtil.filterComposite(
									replyEvent, CommonCollectionsResponseEvent.class);
		
		
		builds = responseEvent.getCodeTableData(PDCodeTableConstants.BUILD);
		buildsMap = CodeHelper.buildCodeMap(builds);
		cautions = responseEvent.getCodeTableData(PDCodeTableConstants.CAUTIONS);
		cautionsMap = CodeHelper.buildCodeMap(cautions);
		complexions = responseEvent.getCodeTableData(PDCodeTableConstants.SKIN_TONE);
		complexionsMap = CodeHelper.buildCodeMap(complexions);
		//courts = responseEvent.getCodeTableData(PDCodeTableConstants.COURT);
		//courtsMap = CodeHelper.buildCodeMap(courts);
		eyeColors = responseEvent.getCodeTableData(PDCodeTableConstants.EYE_COLOR);
		eyeColorsMap = CodeHelper.buildCodeMap(eyeColors);
		ethnicities = responseEvent.getCodeTableData(PDCodeTableConstants.ETHNICITY);
		juvenileEthnicities = responseEvent.getCodeTableData(PDCodeTableConstants.JUVENILE_ETHNICITY);
		ethnicitiesMap = CodeHelper.buildCodeMap(ethnicities);
		hairColors = responseEvent.getCodeTableData(PDCodeTableConstants.HAIR_COLOR);
		hairColorsMap = CodeHelper.buildCodeMap(hairColors);
		races = responseEvent.getCodeTableData(PDCodeTableConstants.RACE);
		racesMap = CodeHelper.buildCodeMap(races);
		scarsAndMarks = responseEvent.getCodeTableData(PDCodeTableConstants.SCARS_MARKS);
		scarsAndMarksMap = CodeHelper.buildCodeMap(scarsAndMarks);
		sexes = responseEvent.getCodeTableData(PDCodeTableConstants.SEX);
		sexesMap = CodeHelper.buildCodeMap(sexes);
		tattoos = responseEvent.getCodeTableData(PDCodeTableConstants.TATTOOS);
		tattoosMap = CodeHelper.buildCodeMap(tattoos);
		states = responseEvent.getCodeTableData(PDCodeTableConstants.STATE_ABBR);
		statesMap = CodeHelper.buildCodeMap(states);
		streetTypes = responseEvent.getCodeTableData(PDCodeTableConstants.STREET_TYPE);
		streetTypesMap = CodeHelper.buildCodeMap(streetTypes);
		addressTypes = responseEvent.getCodeTableData(PDCodeTableConstants.ADDRESS_TYPE);
		addressTypesMap = CodeHelper.buildCodeMap(addressTypes);

		//Map agencyDivisionData = fetchAgencyAndDivisionData();
		//String agencyKey = PDContactConstants.AGENCY_EVENT_TOPIC + "." + PDConstants.LIST_ITEM;
		//String divisionKey = PDContactConstants.DIVISION_EVENT_TOPIC + "." + PDConstants.LIST_ITEM;
		//agencies = (Collection)agencyDivisionData.get(agencyKey);
		//agenciesMap = CodeHelper.buildCodeMap(agencies);
		//divisions = (Collection)agencyDivisionData.get(divisionKey);
		//divisionsMap = CodeHelper.buildCodeMap(divisions);
	}

}
