/*
 * Created on Feb 2, 2006
 *
 */
package ui.juvenilecase.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import naming.PDConstants;
import naming.UIConstants;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.pattern.ui.IFormDirector;
import ui.common.SocialSecurity;
import ui.juvenilecase.form.JuvenileMainForm;

/**
 * @author Jim Fisher
 *
 */
public class JuvenileMainFormDirector implements IFormDirector
{
	private static final String FORM_KEY = "juvenileProfileMainForm";

	private JuvenileMainForm form;

	public JuvenileMainFormDirector(HttpServletRequest aRequest)
	{
		this.initFromSession(aRequest);
	}

	public JuvenileMainFormDirector(JuvenileMainForm form)
	{
		this.form = form;
	}
	
	public ActionForm getForm()
	{
		return this.form;
	}

	/**
	 * @param detail
	 * @param juvMainForm
	 */
	public void setJuvenileProfileProperties(JuvenileProfileDetailResponseEvent juvenile)
	{
		form.setAction("");
		form.setAlienNum(juvenile.getAlienNumber());
		form.setCityId(juvenile.getBirthCity());
		form.setCountryId(juvenile.getBirthCountry());
		form.setBirthCountyId(juvenile.getBirthCounty());
		form.setStateId(juvenile.getBirthState());
		form.setComplexionId(juvenile.getComplexion());

		if (juvenile.getDateOfBirth() != null)
		{
			form.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), UIConstants.DATE_FMT_1));
		}

		if (juvenile.getDateSenttoDPS() != null)
		{
			form.setDateOfBirth(DateUtil.dateToString(juvenile.getDateSenttoDPS(), UIConstants.DATE_FMT_1));
		}

		form.setDHSNum(juvenile.getDHSNumber());
		form.setDNASampleNum(juvenile.getDNASampleNumber());
		form.setDriverLicenseNum(juvenile.getDriverLicenseNumber());
		form.setDriverLicenseStateId(juvenile.getDriverLicenseState());
		form.setEthnicityId(juvenile.getEthnicity());
		form.setFirstName(juvenile.getFirstName());
		form.setFBINum(juvenile.getFBINumber());
		form.setHispanic(juvenile.getHispanic()); //U.S 88526
		form.setLastName(juvenile.getLastName());
		form.setMiddleName(juvenile.getMiddleName());
		form.setPreferredFirstName(juvenile.getPreferredFirstName());
		form.setMultiracial(juvenile.isMultiracial() ? PDConstants.YES : PDConstants.NO);
		form.setNationalityId(juvenile.getNationality());
		form.setNaturalEyeColorId(juvenile.getNatualEyeColor());
		form.setNaturalHairColorId(juvenile.getNaturalHairColor());
		form.setPEIMSId(juvenile.getPEIMSId());
		form.setTSDSId(juvenile.getTSDSId());
		form.setIsUSCitizenId(juvenile.getIsUSCitizen());
		form.setPrimaryLanguageId(juvenile.getPrimaryLanguage());
		form.setRaceId(juvenile.getRace());
		form.setReligionId(juvenile.getReligion());		
		form.setSecondaryLanguageId(juvenile.getSecondaryLanguage());
		form.setSexId(juvenile.getSex());
		form.setSID(juvenile.getSID());
		form.setSONum(juvenile.getSONumber());
		SocialSecurity ssn = new SocialSecurity(juvenile.getSSN());
		form.setSSN(ssn);
		form.setVerifiedDOB(juvenile.isVerifiedDOB() ? PDConstants.YES : PDConstants.NO);
		form.setDetentionFacility(juvenile.getDetentionFacility());
		form.setDetentionFacilityId(juvenile.getDetentionFacilityId());
		form.setDetentionStatus(juvenile.getDetentionStatus());
		form.setDetentionStatusId(juvenile.getDetentionStatusId());
		form.setAdopted(juvenile.isAdopted());
		form.setFailedPlacements(juvenile.getFailedPlacements());
		form.setAdoptionComments(juvenile.getAdoptionComments());	
		if (juvenile.getStatus() == null || juvenile.getStatus().equals(""))
	    {
			form.setHasCasefile(false);
	    }
		else
			form.setHasCasefile(true);
	}

	/* (non-Javadoc)
	 * @see mojo.pattern.IFormDirector#getFromSession(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	public void initFromSession(HttpServletRequest aRequest)
	{
		this.form = (JuvenileMainForm) aRequest.getSession().getAttribute(FORM_KEY);
		if (this.form == null)
		{
			this.form = new JuvenileMainForm();
			aRequest.getSession().setAttribute(FORM_KEY, this.form);
		}
	}
}
