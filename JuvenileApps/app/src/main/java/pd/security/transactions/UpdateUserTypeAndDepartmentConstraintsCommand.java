/*
 * Project: JIMS2
 * Class:   pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand
 * Version: 0.8.15
 *
 * Date:    2005-04-28
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDSecurityConstants;

import messaging.security.UpdateDepartmentConstraintEvent;
import messaging.security.UpdateUserTypeAndDepartmentConstraintsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.User;
import mojo.km.utilities.MessageUtil;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

public class UpdateUserTypeAndDepartmentConstraintsCommand implements mojo.km.context.ICommand
{
	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42712D1C02BF
	 */
	public UpdateUserTypeAndDepartmentConstraintsCommand()
	{

	} //end of pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand.UpdateUserTypeAndDepartmentsContraintsCommand

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event @roseuid 42711B0D0264
	 */
	public void execute(IEvent event)
	{
		UpdateUserTypeAndDepartmentConstraintsEvent uEvent = (UpdateUserTypeAndDepartmentConstraintsEvent) event;
		/* Dispatch */

		UserProfile user = null;
		if (uEvent.getLogonId() != null && uEvent.getLogonId().length() > 0)
		{
			/*// find the user //87191
			user = UserProfileHelper.getUserProfileFromJUCode(uEvent.getLogonId())//User.find(uEvent.getLogonId());
			if (user != null)
			{
				// update userType
				user.setUserTypeId(uEvent.getUserTypeId());
				// update the contraints
				this.updateDivisions(uEvent, user);
			}*/ //87191
		}

	} //end of pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand.execute

	/**
	 * @param uEvent
	 * @param user
	 */
	private void updateDivisions(UpdateUserTypeAndDepartmentConstraintsEvent uEvent, User user)
	{
	//87191
/*
		// remove the previous association of agency
		Collection constraints = user.getConstraints();
		Iterator constraintsIterator = constraints.iterator();
		while (constraintsIterator.hasNext())
		{
			Constraint oldConstraint = (Constraint) constraintsIterator.next();
			user.removeConstraint(oldConstraint.getConstrainerId(), Department.class);
		}
		// get all divisions assigned by UI
		Collection divisions = MessageUtil.compositeToCollection(uEvent, UpdateDepartmentConstraintEvent.class);

		if (divisions != null)
		{
			Iterator divisionsIterator = divisions.iterator();
			while (divisionsIterator.hasNext())
			{
				//insert the new association of constraints
				UpdateDepartmentConstraintEvent dEvent = (UpdateDepartmentConstraintEvent) divisionsIterator.next();
				user.addConstraint(dEvent.getDepartmentId(), Department.class);
				// insert contact for department 
				DepartmentContact uce = new DepartmentContact();
				uce.setLogonId(uEvent.getLogonId());
				uce.setLastName(user.getLastName());
				uce.setFirstName(user.getFirstName());
				uce.setMiddleName(user.getMiddleName());
				UserProfile upf = UserProfile.find(uEvent.getLogonId());
				uce.setPhoneNum(upf.getWorkPhoneNum());
				uce.setEmail(upf.getEmail());
				uce.setDepartmentId(dEvent.getDepartmentId());
				Department de = Department.find(dEvent.getDepartmentId());
				Collection contacts = de.getContacts();
				boolean contactExists = false;
				for (Iterator iter = contacts.iterator(); iter.hasNext();)
				{
					DepartmentContact deptContact = (DepartmentContact) iter.next();
					if (deptContact.getLogonId().equals(uce.getLogonId()))
					{
						contactExists = true;
						break;
					}
				}
				if (!contactExists)
				{
					de.insertContacts(uce);
				}
			}

		}
		if (user.getUserTypeId() != null)
		{
		if (user.getUserTypeId().equals(PDSecurityConstants.USER_TYPE_SA))
		{
				String agencyId = user.getAgencyId();
				Agency agy = Agency.find(agencyId);
				Collection depts = agy.getDepartments();
				for (Iterator iter = depts.iterator(); iter.hasNext();) {
					Department dept = (Department) iter.next();
					Collection contacts = dept.getContacts();
					boolean contactExists = false;
					for (Iterator contact = contacts.iterator(); contact.hasNext();) {
						DepartmentContact deptContact = (DepartmentContact) contact.next();
						if (deptContact.getLogonId().equals(uEvent.getLogonId())) {
							contactExists = true;
							break;
						}
					}
					if (!contactExists) {
						DepartmentContact uce = new DepartmentContact();
						uce.setLogonId(uEvent.getLogonId());
						uce.setLastName(user.getLastName());
						uce.setFirstName(user.getFirstName());
						uce.setMiddleName(user.getMiddleName());
						UserProfile upf = UserProfile.find(uEvent.getLogonId());
						uce.setPhoneNum(upf.getWorkPhoneNum());
						uce.setEmail(upf.getEmail());
						uce.setDepartmentId(dept.getDepartmentId());
						dept.insertContacts(uce);
					}
				}

			}
		}*/ //87191
	}

	/**
	 *  
	 * @param event @roseuid 42711B0D0266
	 */
	public void onRegister(IEvent event)
	{

	} //end of pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand.onRegister

	/**
	 *  
	 * @param event @roseuid 42711B0D0268
	 */
	public void onUnregister(IEvent event)
	{

	} //end of pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand.onUnregister

	/**
	 *  
	 * @param anObject @roseuid 42711B0D026A
	 */
	public void update(Object anObject)
	{

	} //end of pd.security.transactions.UpdateUserTypeAndDepartmentConstraintsCommand.update

} // end UpdateUserTypeAndDepartmentConstraintsCommand
