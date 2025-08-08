package ui.supervision.posttrial.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
public class DisplaySupplementalDocumentsListAction extends JIMSBaseAction {
	
	public static final String SPN = "&SPN=";

	public static final String CDI = "&CDI=";

	public static final String CASENUMBER = "&CASE=";

	public static final String COURT = "&COURT=";

	private static final String POI = "&POI=";

	private static final String OFFICER = "&OFFICER=";

    /**
     * @roseuid 438F240E01BC
     */
    public DisplaySupplementalDocumentsListAction() {

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

		List suppDocList = MessageUtil.postRequestListFilter( requestEvent, SupplementalDocumentsResponseEvent.class );
		if ( suppDocList != null ) {
//Create Form Group lists of Supplemental Document
			ArrayList correspondenceList = new ArrayList();
			ArrayList ctiList = new ArrayList();
			ArrayList defendantFormsList = new ArrayList();
			ArrayList elmFormsList = new ArrayList();
			ArrayList financialList = new ArrayList();
			ArrayList miscellaneousList = new ArrayList();
			ArrayList newCaseList = new ArrayList();
			ArrayList probationerMonthlyReportList = new ArrayList();
			ArrayList referralFormsList = new ArrayList();
			ArrayList sexOffenderList = new ArrayList();
			ArrayList specialProgramsList = new ArrayList();
			ArrayList transferUnitList = new ArrayList();
			ArrayList travelPermitsList = new ArrayList();
			ArrayList waiversList = new ArrayList();
			for (int l = 0; l < suppDocList.size(); l++) {
				SupplementalDocumentsResponseEvent supplementalDocument = (SupplementalDocumentsResponseEvent) suppDocList.get(l);
				StringBuffer urlParameters = new StringBuffer();
				if(StringUtils.isNotEmpty(supplementalDocument.getFormGroup())){
					if ("Correspondence".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						correspondenceList.add(supplementalDocument);
					} else if ("CTI".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						ctiList.add(supplementalDocument);
					} else if ("Defendant Forms".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						defendantFormsList.add(supplementalDocument);
					} else if ("ELM Forms".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						elmFormsList.add(supplementalDocument);
					} else if ("Financial".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						financialList.add(supplementalDocument);
					} else if ("Miscellaneous".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						miscellaneousList.add(supplementalDocument);
					} else if ("New Case".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						newCaseList.add(supplementalDocument);
					} else if ("Probationer Monthly Report".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						probationerMonthlyReportList.add(supplementalDocument);
					} else if ("Referral Forms".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						referralFormsList.add(supplementalDocument);
					} else if ("Sex Offender".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						sexOffenderList.add(supplementalDocument);
					} else if ("Special Programs".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						specialProgramsList.add(supplementalDocument);
					} else if ("Transfer Unit".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						transferUnitList.add(supplementalDocument);
					} else if ("Travel Permits".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						travelPermitsList.add(supplementalDocument);
					} else if ("Waivers".equalsIgnoreCase(supplementalDocument.getFormGroup().trim())) {
						if ( StringUtils.isNotEmpty( supplementalDocument.getUrl() ) ) {
							urlParameters.append(supplementalDocument.getUrl());
							urlParameters.append(parameters.toString());
							supplementalDocument.setUrl(urlParameters.toString());
						}
						waiversList.add(supplementalDocument);
					}
				}
			}
			if (correspondenceList != null) {
				suppDocForm.setCorrespondenceList(sortSupplementalDocumentList(correspondenceList));
			}
			if (ctiList != null) {
				suppDocForm.setCtiList(sortSupplementalDocumentList(ctiList));
			}
			if (defendantFormsList != null) {
				suppDocForm.setDefendantFormsList(sortSupplementalDocumentList(defendantFormsList));
			}
			if (elmFormsList != null) {
				suppDocForm.setElmFormsList(sortSupplementalDocumentList(elmFormsList));
			}
			if (financialList != null) {
				suppDocForm.setFinancialList(sortSupplementalDocumentList(financialList));
			}
			if (miscellaneousList != null) {
				suppDocForm.setMiscellaneousList(sortSupplementalDocumentList(miscellaneousList));
			}
			if (newCaseList != null) {
				suppDocForm.setMiscellaneousList(sortSupplementalDocumentList(newCaseList));
			}
			if (probationerMonthlyReportList != null) {
				suppDocForm.setProbationerMonthlyReportList(sortSupplementalDocumentList(probationerMonthlyReportList));
			}
			if (referralFormsList != null) {
				suppDocForm.setReferralFormsList(sortSupplementalDocumentList(referralFormsList));
			}
			if (sexOffenderList != null) {
				suppDocForm.setSexOffenderList(sortSupplementalDocumentList(sexOffenderList));
			}
			if (specialProgramsList != null) {
				suppDocForm.setSpecialProgramsList(sortSupplementalDocumentList(specialProgramsList));
			}
			if (transferUnitList != null) {
				suppDocForm.setTransferUnitList(sortSupplementalDocumentList(transferUnitList));
			}
			if (travelPermitsList != null) {
				suppDocForm.setTravelPermitsList(sortSupplementalDocumentList(travelPermitsList));
			}
			if (waiversList != null) {
				suppDocForm.setWaiversList(sortSupplementalDocumentList(waiversList));
			}
		}
        return aMapping.findForward(UIConstants.SUPP_REPORT_SUCCESS);
    }
	
	/**
	 * @param supplementalDocumentList
	 * @return List
	 * sorts supplementalDocumentList by formTitle in alphabetical order ascending 
	 */
	public static List sortSupplementalDocumentList(List sdList){
		if (sdList.size() > 0){
			SortedMap map = new TreeMap();
			for (int x = 0; x < sdList.size(); x++){
				SupplementalDocumentsResponseEvent sdevent = (SupplementalDocumentsResponseEvent) sdList.get(x);	
				map.put(sdevent.getFormTitle(), sdevent);
			}
			sdList = new ArrayList(map.values());
		}
		return sdList;
	} 
}