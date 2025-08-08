/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.contact.agency;

import java.util.Collection;
import java.util.Iterator;

import naming.PDContactConstants;
import naming.PDSecurityConstants;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import pd.codetable.Code;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DepartmentContactResponseCreatorImpl implements ResponseCreator{
     
	/**
	 * Creates DepartmentContact response event from entity.
	 * @param object
	 * @return DepartmentResponseEvent
	 */
	public Object create(Object object)
	{
		DepartmentContact departmentContact = (DepartmentContact) object;
		
		DepartmentContactResponseEvent cRE = new DepartmentContactResponseEvent();
		if (departmentContact != null)
		{
			cRE = new DepartmentContactResponseEvent();
			cRE.setDepartmentId(departmentContact.getDepartmentId());
			cRE.setFirstName(departmentContact.getFirstName());
			cRE.setLastName(departmentContact.getLastName());
			cRE.setMiddleName(departmentContact.getMiddleName());
			cRE.setTitle(departmentContact.getTitle());
			cRE.setUserId(departmentContact.getLogonId());
			cRE.setPhone(departmentContact.getPhoneNum());
			cRE.setPhoneExt(departmentContact.getPhoneExt());
			cRE.setLiasonTrainingInd(departmentContact.getLiaisonTrainingInd());
			if (departmentContact.getLiaisonTrainingInd() != null
				&& departmentContact.getLiaisonTrainingInd().equals(PDSecurityConstants.Y))
			{
				cRE.setLiasonTraining(PDSecurityConstants.YES);
			}
			else
			{
				cRE.setLiasonTraining(PDSecurityConstants.NO);
			}
			cRE.setPrimaryContact(departmentContact.getPrimaryContact());
			cRE.setEmail(departmentContact.getEmail());
			cRE.setContactTypeId(departmentContact.getContactTypeId());
			cRE.setContactId(departmentContact.getContactId());
		}
		return cRE;
	}

	/**
	  * @param object
	 * @return DepartmentContactResponseEvent
	 */
	public Object createThin(Object object)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		return null;
	}
}
