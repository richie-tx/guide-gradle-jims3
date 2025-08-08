package ui.juvenilecase.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import messaging.contact.to.Address;
import mojo.km.util.DateUtil;
import ui.common.AddressHelper;
import ui.common.UIUtil;
import ui.juvenilecase.form.JuvenileContactForm;

public class DisplayJuvenileContactCreateSummaryAction extends Action
{

    /**
     * @roseuid 42AF408E033C
     */
    public DisplayJuvenileContactCreateSummaryAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42AF3EDF02E0
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileContactForm jcForm = (JuvenileContactForm) aForm;
        jcForm.processCodeDescriptions();
        
       	displayAddress(jcForm);
        	
       	jcForm.setEntryDate(DateUtil.dateToString(new Date(), DateUtil.DATE_FMT_1));

        jcForm.setSecondaryAction(UIConstants.SUMMARY);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    private void displayAddress(JuvenileContactForm jcForm)
    {
        Address address = new Address();
        address.setStreetNum(jcForm.getStreetNum());
        address.setStreetNumSuffix(jcForm.getStreetNumSuffix());
        address.setStreetNumSuffixCode(jcForm.getStreetNumSuffixId());
        address.setStreetName(jcForm.getStreetName());
        address.setStreetType(jcForm.getStreetType());
        address.setStreetTypeId(jcForm.getStreetTypeId());
        address.setAptNum(jcForm.getApartmentNum());
        address.setCity(jcForm.getCity());
        address.setState(jcForm.getState());
        address.setStateId(jcForm.getStateId());
        address.setZipCode(jcForm.getZipCode());
        address.setAdditionalZipCode(jcForm.getAdditionalZipCode());
        address.setCountyId(jcForm.getCountyId());

        String streetNumber = jcForm.getStreetNum();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < streetNumber.length(); i++) {
     	    char ch = streetNumber.charAt(i);
   			if (Character.isDigit(ch) == false) {
   				jcForm.setValidated("N");
   				jcForm.setAddressStatus("N");
   				break;
   			}
   			else {
   				if (ch >= '0' && ch <= '9') {
   				   buffer.append(ch);
   				} 
   				   AddressHelper.validateAddress(address);
   				   jcForm.setValidated(address.getValidated());
   	               jcForm.setAddressStatus(address.getValidated());
 			}
   	   }
//        AddressHelper.validateAddress(address);
//        jcForm.setValidated(address.getValidated());
//        jcForm.setAddressStatus(address.getValidated());
/*        if (!"".equals(jcForm.getStreetTypeId()) && jcForm.getStreetTypeId() != null)
        {
            address.setStreetName(jcForm.getStreetName() + " " + jcForm.getStreetTypeId());
        }
*/        
        String add = address.getStreetAddress() + ", " + address.getCityStateZip();
        if (add.trim().equals(","))
        {
            jcForm.setAddress("");
        }
        else
        {
            jcForm.setAddress(add);
        }
    }
}
