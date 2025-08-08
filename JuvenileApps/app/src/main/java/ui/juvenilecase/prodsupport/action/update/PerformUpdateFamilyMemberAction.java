package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.family.SaveFamilyMemberEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import mojo.km.utilities.DateUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.family.FamilyMember;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

    public class PerformUpdateFamilyMemberAction extends Action {
    	private Logger log = Logger.getLogger("PerformUpdateFamilyMemberAction");
    	
    	public ActionForward execute(ActionMapping mapping, ActionForm form,
    			HttpServletRequest request, HttpServletResponse response)
    			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();

		FamilyMember fm = FamilyMember.find(regform.getFamilyMemberId());
		FamilyMember familyMember = (FamilyMember)regform.getFamilyMemberUpdate();
		
		if(familyMember != null && familyMember.getDateOfBirthString() != null && !"".equals(familyMember.getDateOfBirthString()))
		{
		    familyMember.setDateOfBirth(DateUtil.stringToDate(familyMember.getDateOfBirthString(),DateUtil.DATE_FMT_1));
		}
		
		boolean isUpdated = false;
		if(fm != null && familyMember != null)
		{
		    if(!compareStrings(fm.getFirstName(), familyMember.getFirstName()) || !compareStrings(fm.getLastName(), familyMember.getLastName()) 
			    	|| !compareDates(fm.getDateOfBirth(), familyMember.getDateOfBirth()) || !compareStrings(fm.getSSN(), familyMember.getSSN()) 
				|| !compareStrings(fm.getComments(), familyMember.getComments()) || !compareStrings(fm.getSexId(), familyMember.getSexId())
				|| !compareStrings(fm.getSidNum(), familyMember.getSidNum()) || !compareStrings(fm.getDriverLicenseNum(), familyMember.getDriverLicenseNum())
				|| !compareStrings(fm.getIdCardStateId(), familyMember.getIdCardStateId()) || !compareStrings(fm.getIdCardNum(), familyMember.getIdCardNum())
				|| !compareStrings(fm.getDriverLicenseStateId(), familyMember.getDriverLicenseStateId())
				|| (Boolean.compare(fm.isIncarcerated(), familyMember.isIncarcerated()) == 1))
			{
			    isUpdated = true;
			}
		}
					
		
		if(!isUpdated){
		    regform.setFamilyMember(familyMember);
		    regform.setFamilyMemberUpdate(familyMember);
		        
		    regform.setMsg("No changes to the record");
		    return mapping.findForward("error");
		}
		
		SaveFamilyMemberEvent saveEvent = (SaveFamilyMemberEvent)EventFactory.getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBER);
		
		saveEvent.setFirstName(familyMember.getFirstName());
		saveEvent.setLastName(familyMember.getLastName());
		saveEvent.setDateOfBirth(familyMember.getDateOfBirth());
		saveEvent.setSsn(familyMember.getSSN());
		saveEvent.setComments(familyMember.getComments());
		saveEvent.setSexId(familyMember.getSexId());
		saveEvent.setSidNum(familyMember.getSidNum());
		saveEvent.setIdCardNum(familyMember.getIdCardNum());
		saveEvent.setIdCardStateId(familyMember.getIdCardStateId());		
		saveEvent.setDriverLicenceStateId(familyMember.getDriverLicenseNum());
		saveEvent.setDriverLicenceStateId(familyMember.getDriverLicenseStateId());
		saveEvent.setIncarcerated(familyMember.isIncarcerated());
		
		IHome home = new Home();
		home.bind(familyMember);	
			
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
    	public static boolean compareStrings(String str1, String str2) {
    	    return (str1 == null ? str2 == null : str1.equals(str2));
    	}
    	
    	public static boolean compareDates(Date date1, Date date2) {
    	    return (date1 == null ? date2 == null : date1.equals(date2));
    	}

}
