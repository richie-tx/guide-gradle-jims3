/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.family.GetGuardianInfoEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.juvenilecase.form.JuvenileGuardianForm;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DisplayGuardianInfoAction extends LookupDispatchAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		
		String juvenileNum = aRequest.getParameter("juvnum");
		GetGuardianInfoEvent event = new GetGuardianInfoEvent();
		event.setJuvenileId(juvenileNum);
		CompositeResponse response = MessageUtil.postRequest(event);
		
		Collection guardianList = new ArrayList();
		Collection newRespEvent = new ArrayList();
		guardianList = (Collection) MessageUtil.compositeToList(response, GuardianInfoResponseEvent.class);
		HashMap hashMap = new HashMap();
		newRespEvent.addAll(guardianList);
		Iterator iterator = guardianList.iterator();
		while (iterator.hasNext()) {
			GuardianInfoResponseEvent guardInfoRespEvent = (GuardianInfoResponseEvent) iterator.next();
			if (!(hashMap.containsKey(guardInfoRespEvent.getFamMemberId()))) {
				hashMap.put(guardInfoRespEvent.getFamMemberId(), new GuardianInfoResponseEvent());
			}
		}
		/*
		 * below code is written to display all the latest phone numbers for a
		 * specific guardian name within a single record. Record from the
		 * database has a different format when compared to the requirement in
		 * Jsp. Constructing the response objects with all the required phone
		 * numbers for the guardians of a given JuvenileNum. Picking up the
		 * latest date's guardian details from JCFAMPHONE View. Separating the
		 * FamMemberIds in to a hashmap and picking up the phone numbers from
		 * the original list in to the corresponding objects which are stored in
		 * hashmap.
		 */

		String respkey = "";
		Iterator myVeryOwnIterator = hashMap.keySet().iterator();
		while (myVeryOwnIterator.hasNext()) {
			respkey = myVeryOwnIterator.next().toString();
			Iterator newIter = newRespEvent.iterator();
			Date oldHomeEntryDate = null;
			Date oldWorkEntryDate = null;
			Date oldMobileEntryDate = null;
			Date oldJuvMobileEntryDate = null;
			Date oldAddressEntryDate = null;
			while (newIter.hasNext()) {
				GuardianInfoResponseEvent guardInfoRespEvent = (GuardianInfoResponseEvent) newIter.next();
				
				if (respkey.equals(guardInfoRespEvent.getFamMemberId())) {
					GuardianInfoResponseEvent gr = (GuardianInfoResponseEvent) hashMap.get(respkey);
					gr.setGuardianName(guardInfoRespEvent.getGuardianName());
					gr.setPrimaryContact(guardInfoRespEvent.getPrimaryContact());
					if (guardInfoRespEvent.getHomePhone() != null && !guardInfoRespEvent.getHomePhone().equals("")) {
						if (oldHomeEntryDate == null) {
							oldHomeEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setHomePhone(guardInfoRespEvent.getHomePhone());
							gr.setHomeExtn(guardInfoRespEvent.getHomeExtn());
						} else if ((oldHomeEntryDate != null)
								&& (guardInfoRespEvent.getEntryDate().after(oldHomeEntryDate))) {
							oldHomeEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setHomePhone(guardInfoRespEvent.getHomePhone());
							gr.setHomeExtn(guardInfoRespEvent.getHomeExtn());
						}

					}
					if (guardInfoRespEvent.getWorkPhone() != null && !guardInfoRespEvent.getWorkPhone().equals("")) {

						if (oldWorkEntryDate == null) {
							oldWorkEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setWorkPhone(guardInfoRespEvent.getWorkPhone());
							gr.setWorkExtn(guardInfoRespEvent.getWorkExtn());
						} else if ((oldWorkEntryDate != null)
								&& (guardInfoRespEvent.getEntryDate().after(oldWorkEntryDate))) {
							oldWorkEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setWorkPhone(guardInfoRespEvent.getWorkPhone());
							gr.setWorkExtn(guardInfoRespEvent.getWorkExtn());
						}

					}
					if (guardInfoRespEvent.getMobilePhone() != null && !guardInfoRespEvent.getMobilePhone().equals("")) {
						if (oldMobileEntryDate == null) {
							oldMobileEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setMobilePhone(guardInfoRespEvent.getMobilePhone());
						} else if ((oldMobileEntryDate != null)
								&& (guardInfoRespEvent.getEntryDate().after(oldMobileEntryDate))) {
							oldMobileEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setMobilePhone(guardInfoRespEvent.getMobilePhone());
						}
					}
					if (guardInfoRespEvent.getJuvMobilePhone() != null && !guardInfoRespEvent.getJuvMobilePhone().equals("")) {
						if (oldJuvMobileEntryDate == null) {
							oldJuvMobileEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setJuvMobilePhone(guardInfoRespEvent.getJuvMobilePhone());
						} else if ((oldJuvMobileEntryDate != null)
								&& (guardInfoRespEvent.getEntryDate().after(oldJuvMobileEntryDate))) {
							oldJuvMobileEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setJuvMobilePhone(guardInfoRespEvent.getJuvMobilePhone());
						}
					}
					if (guardInfoRespEvent.getAddressId() != null && !guardInfoRespEvent.getAddressId().equals("")) {
						if (oldAddressEntryDate == null) {
							oldAddressEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setAddressId(guardInfoRespEvent.getAddressId());
							gr.setAddressType(guardInfoRespEvent.getAddressType());
							gr.setAddressTypeId(guardInfoRespEvent.getAddressTypeId());
							gr.setStreetNumber(guardInfoRespEvent.getStreetNumber());
							gr.setStreetNumSuffix(guardInfoRespEvent.getStreetNumSuffix());
							gr.setStreetNumSuffixId(guardInfoRespEvent.getStreetNumSuffixId());
							gr.setStreetName(guardInfoRespEvent.getStreetName());
							gr.setStreetType(guardInfoRespEvent.getStreetType());
							gr.setStreetTypeId(guardInfoRespEvent.getStreetTypeId());
							//<KISHORE>JIMS200061024 : MJCW - Guardian apartment number is missing
							gr.setAptNumber(guardInfoRespEvent.getAptNumber());
							gr.setCity(guardInfoRespEvent.getCity());
							gr.setState(guardInfoRespEvent.getState());
							gr.setStateId(guardInfoRespEvent.getStateId());
							gr.setZipCode(guardInfoRespEvent.getZipCode());
							gr.setValidated(guardInfoRespEvent.getValidated());
						} else if ((oldAddressEntryDate != null)
								&& (guardInfoRespEvent.getEntryDate().after(oldAddressEntryDate))) {
							oldAddressEntryDate = guardInfoRespEvent.getEntryDate();
							gr.setAddressId(guardInfoRespEvent.getAddressId());
							gr.setAddressType(guardInfoRespEvent.getAddressType());
							gr.setAddressTypeId(guardInfoRespEvent.getAddressTypeId());
							gr.setStreetNumber(guardInfoRespEvent.getStreetNumber());
							gr.setStreetNumSuffix(guardInfoRespEvent.getStreetNumSuffix());
							gr.setStreetNumSuffixId(guardInfoRespEvent.getStreetNumSuffixId());
							gr.setStreetName(guardInfoRespEvent.getStreetName());
							gr.setStreetType(guardInfoRespEvent.getStreetType());
							gr.setStreetTypeId(guardInfoRespEvent.getStreetTypeId());
							//<KISHORE>JIMS200061024 : MJCW - Guardian apartment number is missing
							gr.setAptNumber(guardInfoRespEvent.getAptNumber());
							gr.setCity(guardInfoRespEvent.getCity());
							gr.setState(guardInfoRespEvent.getState());
							gr.setStateId(guardInfoRespEvent.getStateId());
							gr.setZipCode(guardInfoRespEvent.getZipCode());
							gr.setValidated(guardInfoRespEvent.getValidated());
						}
					}
				}
			}

		}

		guardianList = new ArrayList();
		guardianList.addAll(hashMap.values());
		JuvenileGuardianForm guardForm = (JuvenileGuardianForm) aForm;
		guardForm.setGuardianList(guardianList);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
