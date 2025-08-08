package ui.juvenilewarrant.helper;

import java.util.Collection;
import ui.common.CodeHelper;
import ui.contact.UIContactHelper;
import ui.juvenilecase.UIJuvenileHelper;

/**
 * @author Jim Fisher
 *
 */
public class JuvenileWarrantListHelper
{
	public JuvenileWarrantListHelper()
	{
	}

	/**
	 * @return address types
	 */
	public Collection getAddressTypes()
	{
		return CodeHelper.getAddressTypeCodes(true);
	}

	public Collection getAgencies()
	{
		// TODO Change to not use literal
		return UIContactHelper.fetchAgencies("N", true);
	}

	/**
	 * @return Collection
	 */
	public Collection getBuilds()
	{
		return CodeHelper.getBuildCodes(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getCautions()
	{
		return CodeHelper.getCautionCodes(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getComplexions()
	{
		return CodeHelper.getComplexionCodes(true);
	}

	/**
	 * @return Collection countyCodes
	 */
	public Collection getCounties()
	{
		return CodeHelper.getCountyCodes(true);
	}

	public Collection getCourts()
	{
		return CodeHelper.getCourtCodes(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getEyeColors()
	{
		return CodeHelper.getEyeColorCodes(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getHairColors()
	{
		return CodeHelper.getHairColorCodes(true);
	}

	public Collection getLawEnforcementDepartments()
	{
		return CodeHelper.getLawEnforcementDepartments(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getOfficerIdTypes()
	{
		return CodeHelper.getOfficerIdTypeCodes(true);
	}

	/**
	 * @return races
	 */
	public Collection getRaces()
	{
		return CodeHelper.getRaceCodes(true);
	}

	/**
	 * @return String
	 */
	public Collection getRecallReasons()
	{
		return CodeHelper.getRecallReasonCodes(true);
	}

	/**
	 * @return Collection
	 */
	public Collection getReleaseDecisions()
	{
		return CodeHelper.getReleaseDecisionCodes(true);
	}

	public Collection getSchoolDistricts()
	{
		return UIJuvenileHelper.fetchSchoolDistricts();
	}

	/**
	 * @return sexes
	 */
	public Collection getSexes()
	{
		return CodeHelper.getSexCodes(true);
	}

	/**
	 * @return String
	 */
	public Collection getServiceStatuses()
	{
		return CodeHelper.getServiceStatuses(true);
	}

	/**
	* 
	* @return Collection signedStatusCodes
	*/
	public Collection getSignatureOptions()
	{
		return CodeHelper.getSignatureOptions(true);
	}
	
	/**
	* 
	* @return Collection signatureCommands
	*/
	public Collection getSignatureCommands()
	{
		return CodeHelper.getSignatureCommands(true);
	}	

	/**
	 * @return states
	 */
	public Collection getStates()
	{
		return CodeHelper.getStateCodes(true);
	}

	/**
	 * @return street types
	 */
	public Collection getStreetTypes()
	{
		return CodeHelper.getStreetTypeCodes(true);
	}

	/**
	 * @return transferLocations
	 */
	public Collection getTransferLocations()
	{
		return CodeHelper.getTransferLocationCodes(true);
	}

	public Collection getWarrantStatuses()
	{
		return CodeHelper.getWarrantStatusCodes(true);
	}

	/** 
	 * @return warrantTypes
	 */
	public Collection getWarrantTypes()
	{
		return CodeHelper.getWarrantTypeCodes(true);
	}

	public Collection getRelationshipsToJuvenile()
	{
		return CodeHelper.getRelationshipsToJuvenileCodes(true);
	}
}
