/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.contact.agency;

import naming.PDSecurityConstants;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import pd.codetable.Code;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DepartmentResponseCreatorImpl implements ResponseCreator{
     
	/**
	 * Creates Department response event from entity.
	 * @param department
	 * @return DepartmentResponseEvent
	 */
	public Object create(Object object)
	{
		Department department = (Department) object;
		DepartmentResponseEvent dre = (DepartmentResponseEvent) this.createThin(department);
		
		dre.setLabelInd(department.getLabelInd());
		dre.setComments(department.getComments());
		dre.setGritsInd(department.getGritsInd());
		dre.setCreateOfficerProfileInd(department.getCreateOfficerProfileInd());

		Code code = null;

		if ((department.getCountyId() != null) && (!(department.getCountyId().equals(""))))
		{
			code = department.getCounty();
			if (code != null)
			{
				dre.setCountyId(code.getCode());
				dre.setCountyName(code.getDescription());
			}
		}

		// Agency Type
		if ((department.getAgencyTypeId() != null) && (!(department.getAgencyTypeId().equals(""))))
		{
			code = department.getAgencyType();
			if (code != null)
			{
				dre.setAgencyTypeId(code.getCode());
				dre.setAgencyType(code.getDescription());
			}
		}
		if ((department.getAccessTypeId() != null) && (!(department.getAccessTypeId().equals(""))))
		{
			code = department.getAccessType();
			if (code != null)
			{
				dre.setAccessTypeId(code.getCode());
				dre.setAccessType(code.getDescription());
			}
		}
		
		if (department.getFax() != null)
		{
			dre.setFax(department.getFax());
		}
		if (department.getActivationDate() != null)
		{
			dre.setActivationDate(department.getActivationDate());
		}
		if (department.getTerminationDate() != null)
		{
			dre.setTerminationDate(department.getTerminationDate());
		}
		if (department.getOriginatingAgencyId() != null)
		{
			dre.setOriginatingAgencyId(department.getOriginatingAgencyId());
		}
		if (department.getSetcicDate() != null)
		{
			dre.setSetcicDate(department.getSetcicDate());
		}

		if (department.getSetcicRenewDate() != null)
		{
			dre.setSetcicRenewDate(department.getSetcicRenewDate());
		}

		if (department.getSetcicInactiveDate() != null)
		{
			dre.setSetcicInactiveDate(department.getSetcicInactiveDate());
		}
		if (department.getWarrantConfirmationPhone() != null)
		{
			dre.setWarrantConfirmationPhone(department.getWarrantConfirmationPhone());
		}
		if (department.getWarrantConfirmationPhoneExt() != null)
		{
			dre.setWarrantConfirmationPhoneExt(department.getWarrantConfirmationPhoneExt());
		}

		// Get Subcriber Access Information
		if (department.getSubscriberCivilActivationDate() != null)
		{
			dre.setSubscriberCivilActivationDate(department.getSubscriberCivilActivationDate());
		}
		if (department.getSubscriberCriminalTerminationDate() != null)
		{
			dre.setSubscriberCivilTerminationDate(department.getSubscriberCivilTerminationDate());
		}
		if (department.getSubscriberCriminalActivationDate() != null)
		{
			dre.setSubscriberCriminalActivationDate(department.getSubscriberCriminalActivationDate());
		}
		if (department.getSubscriberCriminalTerminationDate() != null)
		{
			dre.setSubscriberCriminalTerminationDate(department.getSubscriberCriminalTerminationDate());
		}
		return dre;
	}

	/**
	  * @param department
	 * @return DepartmentResponseEvent
	 */
	public Object createThin(Object object)
	{
		Department department = (Department) object;
		DepartmentResponseEvent dre = (DepartmentResponseEvent) this.createThinest(department);
		dre.setOrgCode(department.getOrgCode());
		dre.setOriginatingAgencyId(department.getOriginatingAgencyId());

		String setcicAccessId = department.getSetcicAccessId();
		if (setcicAccessId != null && setcicAccessId.equals("") == false)
		{
			dre.setSetcicAccessId(department.getSetcicAccessId());
			dre.setSetcicAccess(department.getSetcicAccess().getDescription());
		}

		String status = department.getStatusId();
		if (status != null && status.equals("") == false)
		{
			dre.setStatusId(status);
			dre.setStatus(department.getStatus().getDescription());
		}

		if (department.getInUseInd() != null && department.getInUseInd().equalsIgnoreCase("Y"))
		{
			dre.setInUse(true);
		}
		else
		{
			dre.setInUse(false);
		}
		return dre;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		Department department = (Department) object;
		DepartmentResponseEvent dre = new DepartmentResponseEvent();

		String agencyId = department.getAgencyId();
		dre.setAgencyId(department.getAgencyId());
		dre.setAgencyName(department.getAgencyName());
		/*if(agencyId != null && !agencyId.equals("")){
			dre.setAgencyId(department.getAgencyId());
			Agency agency = Agency.find(agencyId);
			if(agency != null){
				dre.setAgencyName(agency.getAgencyName());
			}
		}*/
		
		dre.setDepartmentId(department.getDepartmentId());
		dre.setDepartmentName(department.getDepartmentName());
		dre.setUserCount(department.getDepartmentUserCount());
		dre.setTopic(PDSecurityConstants.DEPARTMENT_EVENT_TOPIC);
		return dre;
	}
}
