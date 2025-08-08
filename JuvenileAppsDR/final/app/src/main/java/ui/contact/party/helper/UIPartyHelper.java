/*
 * Created on May 9, 2006
 */
package ui.contact.party.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import messaging.contact.party.reply.OfficerCapacityResponseEvent;
import naming.PDCodeTableConstants;
import ui.common.CodeHelper;
import ui.common.FormCollectionsHelper;
import ui.common.form.CommonCollectionsForm;

/**
 * @author jmcnabb
 *
 *	Helper class for Party UI.
 */
public class UIPartyHelper
{
	// collections
	private Collection languages = null;
	private Collection usCitizens = null;
	private Collection maritalStatuses = null;
	private Collection employmentStatuses = null;
	private Collection relationsToParty = null;
	private Collection placesOfBirth = null;
	private Collection licenseClasses = null;
	private Collection nameSources = null;
	private Collection nameTypes = null;
	private Collection miscIdTypes = null;
	private Collection capacities = null;

	// officer collections
	private Collection officerIdTypes;
	private Collection ranks;

	// maps for quick description lookup
	private Map languagesMap = null;
	private Map usCitizensMap = null;
	private Map maritalStatusesMap = null;
	private Map employmentStatusesMap = null;
	private Map relationsToPartyMap = null;
	private Map placesOfBirthMap = null;
	private Map licenseClassesMap = null;
	private Map nameSourcesMap = null;
	private Map nameTypesMap = null;
	private Map capacitiesMap = new HashMap();
	private Map miscIdTypesMap = null;

	// officer collections
	private Map officerIdTypesMap;
	private Map ranksMap;
	
	private static UIPartyHelper instance = null;

	private UIPartyHelper()
	{
	}

	public static UIPartyHelper getInstance()
	{
		if (instance == null)
		{
			instance = new UIPartyHelper();
			// comment the following to allow lazy initialization for all collections
			// KEEP IN MIND that if you comment the following, there has to be a call
			// made to get the collection before you can get a description from the collection.
			instance.initialize();
		}
		return instance;
	}

	/**
	 * @return Collection
	 */
	public Collection getCapacities()
	{
		if (capacities == null)
		{
			capacities = CodeHelper.getCodes(PDCodeTableConstants.CAPACITY, true);
			capacitiesMap = CodeHelper.buildCodeMap(capacities);
		}
		return capacities;
	}

	/**
	 * @return Collection
	 */
	public Collection getPlacesOfBirth()
	{
		if (placesOfBirth == null)
		{
			placesOfBirth = CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH, true);
			placesOfBirthMap = CodeHelper.buildCodeMap(placesOfBirth);
		}
		return placesOfBirth;
	}

	/**
	 * @return Collection
	 */
	public Collection getMiscIdTypes()
	{
		if (miscIdTypes == null)
		{
			miscIdTypes = new java.util.ArrayList();
			miscIdTypesMap = new HashMap();
		}
		return miscIdTypes;
	}

	/**
	 * @return Collection
	 */
	public Collection getLicenseClasses()
	{
		if (licenseClasses == null)
		{
			licenseClasses = CodeHelper.getCodes(PDCodeTableConstants.DRIVERS_LICENSE_CLASS, true);
			licenseClassesMap = CodeHelper.buildCodeMap(licenseClasses);
		}
		return licenseClasses;
	}

	/**
	 * @return Collection
	 */
	public Collection getOfficerIdTypes()
	{
		if (officerIdTypes == null)
		{
			officerIdTypes = CodeHelper.getCodes(PDCodeTableConstants.OFFICER_ID_TYPE, true);
			officerIdTypesMap = CodeHelper.buildCodeMap(officerIdTypes);
		}
		return officerIdTypes;
	}

	/**
	 * @return Collection
	 */
	public Collection getNameSources()
	{
		if (nameSources == null)
		{
			nameSources = CodeHelper.getCodes(PDCodeTableConstants.NAME_SOURCE, true);
			nameSourcesMap = CodeHelper.buildCodeMap(nameSources);
		}
		return nameSources;
	}

	/**
	 * @return Collection
	 */
	public Collection getNameTypes()
	{
		if (nameTypes == null)
		{
			nameTypes = CodeHelper.getCodes(PDCodeTableConstants.ALIAS_NAME_TYPE, true);
			nameTypesMap = CodeHelper.buildCodeMap(nameTypes);
		}
		return nameTypes;
	}

	/**
	 * @return Collection
	 */
	public Collection getLanguages()
	{
		if (languages == null)
		{
			languages = CodeHelper.getCodes(PDCodeTableConstants.LANGUAGE, true);
			languagesMap = CodeHelper.buildCodeMap(languages);
		}
		return languages;
	}

	/**
	 * @return Collection
	 */
	public Collection getMaritalStatuses()
	{
		if (maritalStatuses == null)
		{
			maritalStatuses = CodeHelper.getCodes(PDCodeTableConstants.MARITAL_STATUS, true);
			maritalStatusesMap = CodeHelper.buildCodeMap(maritalStatuses);
		}
		return maritalStatuses;
	}

	/**
	 * @return Collection
	 */
	public Collection getUsCitizens()
	{
		if (usCitizens == null)
		{
			usCitizens = CodeHelper.getCodes(PDCodeTableConstants.CITIZENSHIP, true);
			usCitizensMap = CodeHelper.buildCodeMap(usCitizens);
		}
		return usCitizens;
	}

	/**
	 * @return Collection
	 */
	public Collection getDescriptorSources()
	{
		return getNameSources();
	}

	/**
	 * @return Collection
	 */
	public Collection getEmploymentStatuses()
	{
		if (employmentStatuses == null)
		{
			employmentStatuses = CodeHelper.getCodes(PDCodeTableConstants.EMPLOYMENT_STATUS, true);
			employmentStatusesMap = CodeHelper.buildCodeMap(employmentStatuses);
		}
		return employmentStatuses;
	}

	/**
	 * @return Collection
	 */
	public Collection getRelationsToParty()
	{
		if (relationsToParty == null)
		{
			relationsToParty = CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_TO_PARTY, true);
			relationsToPartyMap = CodeHelper.buildCodeMap(relationsToParty);
		}
		return relationsToParty;
	}

	/**
	 * @return Collection
	 */
	public Collection getRanks()
	{
		if (ranks == null)
		{
			ranks = CodeHelper.getCodes(PDCodeTableConstants.RANK, true);
			ranksMap = CodeHelper.buildCodeMap(ranks);
		}
		return ranks;
	}

	public String getLanguageDescr(String id)
	{
		return FormCollectionsHelper.getDescription(languagesMap, id);
	}

	public String getUsCitizenDescr(String id)
	{
		return FormCollectionsHelper.getDescription(usCitizensMap, id);
	}

	public String getMaritalStatusDescr(String id)
	{
		return FormCollectionsHelper.getDescription(maritalStatusesMap, id);
	}

	public String getDescriptorSourceDescr(String id)
	{
		return getNameSourceDescr(id);
	}

	public String getEmploymentStatusDescr(String id)
	{
		return FormCollectionsHelper.getDescription(employmentStatusesMap, id);
	}

	public String getRelationToPartyDescr(String id)
	{
		return FormCollectionsHelper.getDescription(relationsToPartyMap, id);
	}

	public String getLicenseClassDescr(String id)
	{
		return FormCollectionsHelper.getDescription(licenseClassesMap, id);
	}

	public String getNameSourceDescr(String id)
	{
		return FormCollectionsHelper.getDescription(nameSourcesMap, id);
	}

	public String getNameTypeDescr(String id)
	{
		return FormCollectionsHelper.getDescription(nameTypesMap, id);
	}

	public String getMiscIdTypeDescr(String id)
	{
		return FormCollectionsHelper.getDescription(miscIdTypesMap, id);
	}

	public String getCapacityDescr(String id)
	{
		return FormCollectionsHelper.getDescription(capacitiesMap, id);
	}

	/**
	 * @return String
	 */
	public String getPlaceOfBirthDesc(String id)
	{
		return FormCollectionsHelper.getDescription(placesOfBirthMap, id);
	}

	/**
	 * @return String
	 */
	public String getRankDesc()
	{
		OfficerCapacityResponseEvent officer = getOfficer();
		if (officer != null)
		{
			return FormCollectionsHelper.getDescription(ranksMap, officer.getRankId());
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getOfficerIdTypeDesc()
	{
		OfficerCapacityResponseEvent officer = getOfficer();
		if (officer != null)
		{
			return FormCollectionsHelper.getDescription(officerIdTypesMap, officer.getOfficerIdTypeId());
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getScarsAndMarksDescription(String[] selectedScars)
	{
		if (selectedScars != null)
		{
			StringBuffer temp = new StringBuffer("");
			String scarsAndMarksDescription = "";
			for (int i = 0; i < selectedScars.length; i++)
			{
				temp.append(
					CommonCollectionsForm.getInstance().getScarsTattooDescription(selectedScars[i], true)).append(
					", ");
			}
			scarsAndMarksDescription = temp.toString();
			// if atleast one was selected
			if (!scarsAndMarksDescription.equals(""))
			{
				// leave out the last ", " that gets appended in the last round for the for loop.
				return scarsAndMarksDescription.substring(0, scarsAndMarksDescription.length() - 2);
			}
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getTattooDescription(String[] selectedTattoos)
	{
		if (selectedTattoos != null)
		{
			StringBuffer temp = new StringBuffer("");
			String tattooDescription = "";
			for (int i = 0; i < selectedTattoos.length; i++)
			{
				temp.append(
					CommonCollectionsForm.getInstance().getScarsTattooDescription(selectedTattoos[i], false)).append(
					", ");
			}
			tattooDescription = temp.toString();
			// if atleast one was selected
			if (!tattooDescription.equals(""))
			{
				// leave out the last ", " that gets appended in the last round for the for loop.
				return tattooDescription.substring(0, tattooDescription.length() - 2);
			}
		}
		return "";
	}

	/**
	 * @return OfficerCapacityResponseEvent
	 */
	public OfficerCapacityResponseEvent getOfficer()
	{
		OfficerCapacityResponseEvent officerEvt = (OfficerCapacityResponseEvent) capacitiesMap.get("Officer");
		return officerEvt;
	}

	private void initialize()
	{
		//Get Code Table Lists for Drop Downs
		nameTypes = CodeHelper.getCodes(PDCodeTableConstants.ALIAS_NAME_TYPE, true);
		nameTypesMap = CodeHelper.buildCodeMap(nameTypes);
		nameSources = CodeHelper.getCodes(PDCodeTableConstants.NAME_SOURCE, true);
		nameSourcesMap = CodeHelper.buildCodeMap(nameSources);
		capacities = CodeHelper.getCodes(PDCodeTableConstants.CAPACITY, true);
		capacitiesMap = CodeHelper.buildCodeMap(capacities);
		maritalStatuses = CodeHelper.getCodes(PDCodeTableConstants.MARITAL_STATUS, true);
		maritalStatusesMap = CodeHelper.buildCodeMap(maritalStatuses);
		placesOfBirth = CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH, true);
		placesOfBirthMap = CodeHelper.buildCodeMap(placesOfBirth);
		licenseClasses = CodeHelper.getCodes(PDCodeTableConstants.DRIVERS_LICENSE_CLASS, true);
		licenseClassesMap = CodeHelper.buildCodeMap(licenseClasses);
		//miscIdTypes = CodeHelper.getCodes(PDCodeTableConstants.MISCELLANEOUS_NUMBER_TYPE, true);
		//miscIdTypesMap = CodeHelper.buildCodeMap(miscIdTypes);
		miscIdTypes = new java.util.ArrayList();
		miscIdTypesMap = new HashMap();
		officerIdTypes = CodeHelper.getCodes(PDCodeTableConstants.OFFICER_ID_TYPE, true);
		officerIdTypesMap = CodeHelper.buildCodeMap(officerIdTypes);
		ranks = CodeHelper.getCodes(PDCodeTableConstants.RANK, true);
		ranksMap = CodeHelper.buildCodeMap(ranks);
		employmentStatuses = CodeHelper.getCodes(PDCodeTableConstants.EMPLOYMENT_STATUS, true);
		employmentStatusesMap = CodeHelper.buildCodeMap(employmentStatuses);
		relationsToParty = CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_TO_PARTY, true);
		relationsToPartyMap = CodeHelper.buildCodeMap(relationsToParty);
		languages = CodeHelper.getCodes(PDCodeTableConstants.LANGUAGE, true);
		languagesMap = CodeHelper.buildCodeMap(languages);
	}
}
