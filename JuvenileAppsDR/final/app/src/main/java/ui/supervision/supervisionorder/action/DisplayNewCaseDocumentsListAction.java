package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supplementaldocuments.GetSupplementalDocumentsEvent;
import messaging.supplementaldocuments.reply.SupplementalDocumentsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.SupplementalDocumentsControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.supervision.supplementalDocuments.form.SupplementalDocumentsForm;

/**
 * @author rcapestani
 *  
 */
public class DisplayNewCaseDocumentsListAction extends JIMSBaseAction {

	public static final String SPN = "&SPN=";

	public static final String CDI = "&CDI=";

	public static final String CASENUMBER = "&CASE=";

	public static final String COURT = "&COURT=";

	private static final String POI = "&POI=";

	private static final String OFFICER = "&OFFICER=";
	
	/**
     * @roseuid 438F240E01BC
     */
    public DisplayNewCaseDocumentsListAction() {

    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    public void addButtonMapping(Map keyMap) {
   
    }
    
    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
    	
		SupplementalDocumentsForm suppDocForm =(SupplementalDocumentsForm) aForm;
		
		StringBuffer parameters = new StringBuffer();
		if ( StringUtils.isNotEmpty( suppDocForm.getSpn() ) ) {
			String spn = suppDocForm.getSpn();
			for ( int z = 0; z < spn.length(); z++ ) {
				if ( !spn.substring(z, z+1).equals("0") ) {
					spn = spn.substring(z);
					break;
				} else if ( z == spn.length()-1 ) {
					spn = "";
				}
			}
			parameters.append(SPN);
			parameters.append(spn);
		}
		if ( StringUtils.isNotEmpty( suppDocForm.getCdi() ) ) {
			parameters.append(CDI);
			parameters.append(suppDocForm.getCdi());
		}
		if ( StringUtils.isNotEmpty( suppDocForm.getCaseNumber() ) ) {
			parameters.append(CASENUMBER);
			parameters.append(suppDocForm.getCaseNumber());
		}
		if ( StringUtils.isNotEmpty( suppDocForm.getCourt() ) ) {
			parameters.append(COURT);
			parameters.append(suppDocForm.getCourt());
		}
		if ( StringUtils.isNotEmpty( suppDocForm.getPoi() ) ) {
			parameters.append(POI);
			parameters.append(suppDocForm.getPoi());
		}
		if ( StringUtils.isNotEmpty( suppDocForm.getOfficer() ) ) {
			parameters.append(OFFICER);
			parameters.append(suppDocForm.getOfficer());
		}
		GetSupplementalDocumentsEvent requestEvent = (GetSupplementalDocumentsEvent) EventFactory
		.getInstance(SupplementalDocumentsControllerServiceNames.GETSUPPLEMENTALDOCUMENTS);

		ArrayList newCaseDocsList = new ArrayList();
		List suppDocList = MessageUtil.postRequestListFilter( requestEvent, SupplementalDocumentsResponseEvent.class );
		if ( suppDocList != null ) {
			for (int l = 0; l < suppDocList.size(); l++) {
				StringBuffer urlParameters = new StringBuffer();
				SupplementalDocumentsResponseEvent newCaseDocument = (SupplementalDocumentsResponseEvent) suppDocList.get(l);
				if ("New Case".equalsIgnoreCase(newCaseDocument.getFormGroup())) {
					if ( StringUtils.isNotEmpty( newCaseDocument.getUrl() ) ) {
						urlParameters.append(newCaseDocument.getUrl());
						urlParameters.append(parameters.toString());
						newCaseDocument.setUrl(urlParameters.toString());
					}
					newCaseDocsList.add(newCaseDocument);
				}
			}

		}	
		if (newCaseDocsList != null) {
			suppDocForm.setNewCaseList(newCaseDocsList);
		}
        return aMapping.findForward(UIConstants.NEW_CASE_DOCS_SUCCESS);
    }	
}