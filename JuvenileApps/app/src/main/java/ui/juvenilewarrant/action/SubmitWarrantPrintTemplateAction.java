package ui.juvenilewarrant.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.juvenilewarrant.ChargeRequestEvent;
import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.LengthBean;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenilePhotoHelper;
import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilewarrant.ReportingBean;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.SecurityUIHelper;
import ui.supervision.supervisionorder.NumberSuffixUtil;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @version 1.0
 * @author Rajib Das Sharma
 */
public class SubmitWarrantPrintTemplateAction extends JIMSBaseAction
{

	public static final String WARRANTTYPE_OIC = "OIC";
	
    public static final String DTAWARRANT_AFFIDAVIT_REPORT_NAME = "REPORTING::DTAWARRANT_AFFIDAVIT";

    public static final String DTAWARRANT_RETURN_REPORT_NAME = "REPORTING::DTARETURN_WARRANT";

    public static final String ARRESTWARRANT_REPORT_NAME = "REPORTING::ARRESTWARRANT";
    
    public static final String GENERICWARRANT_REPORT_NAME = "REPORTING::GENERICWARRANTNOTICE";
    
    public static final String DTAWARRANT_REPORT_NAME = "REPORTING::DTAWARRANT";

    public static final String ARRESTWARRANT_RETURN_REPORT_NAME = "REPORTING::ARRESTRETURN_WARRANT";

    public static final String OICWARRANT_REPORT_NAME = "REPORTING::OIC_WARRANT";

    public static final String OICWARRANT_RETURN_REPORT_NAME = "REPORTING::OICRETURN_WARRANT";
    
    public static final String FILE_SEPARATOR = File.separator;
    
    public static final String DIRECTORY_IMAGES_NAME = "images";
    
    public static final String DIRECTORY_BFO_NAME = "bfo";


    
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printDTAWarrant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        
    	 processReport(form, DTAWARRANT_REPORT_NAME, response, false);

         // Finish with
         return ( null );

    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws Exception
     */
    public ActionForward printGenericWarrant(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
    	
    	JuvenileWarrantForm warrantForm = (JuvenileWarrantForm) aForm;
    	
    	JuvenilePhotoForm  photoForm = (JuvenilePhotoForm) 
    		getSessionForm(aMapping, aRequest, "juvenilePhotoForm", true);
    	
    	photoForm.setJuvenileNumber(warrantForm.getJuvenileNum());
    	UIJuvenilePhotoHelper.getMostRecentPhotoInit(photoForm);
    	
		Map imageMap = new HashMap();
		
		if ( photoForm.getMostRecentPhoto() != null && photoForm.getMostRecentPhoto().getPhoto() != null ){
			
			imageMap.put("photos", photoForm.getMostRecentPhoto().getPhoto());
			
		}else {
			
			imageMap.put("photos", "/images/photo_na.gif");
		
		}
    	 			
		GenericPrintRequestEvent genericWarrantEvent = new GenericPrintRequestEvent();
	    genericWarrantEvent.addDataObject(prepareBean(warrantForm, true));
	    genericWarrantEvent.addDataObject(imageMap);
	    genericWarrantEvent.setReportName(GENERICWARRANT_REPORT_NAME);
	 
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);   
	    dispatch.postEvent(genericWarrantEvent);
	    
	    CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
   	 
   	    MessageUtil.processReturnException(compResponse);
   	   
   	    ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
   	    aResponse.setContentType("application/x-file-download");
   	    aResponse.setHeader("Content-disposition", "attachment; filename="
   	    							+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
   	    aResponse.setHeader("Cache-Control", "max-age=" + 120);   
   	    aResponse.setContentLength(aRespEvent.getContent().length);   
   	    aResponse.resetBuffer();   
   	    OutputStream os;
   	    os = aResponse.getOutputStream();
   	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
   	    os.flush();   
   	    os.close();

        return null;
    }
    


    public ActionForward printDTAAffidavit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {

    	 processReport(form, DTAWARRANT_AFFIDAVIT_REPORT_NAME, response, false);

         // Finish with
         return ( null );
    }

    public ActionForward printDTAWarrantReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	processReport(form, DTAWARRANT_RETURN_REPORT_NAME, response, false);

        // Finish with
        return ( null );

    }

    public ActionForward printArrestWarrantReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	processReport(form, ARRESTWARRANT_RETURN_REPORT_NAME, response, false);

        // Finish with
        return ( null );

    }

    public ActionForward printArrestWarrant(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
    	 processReport(aForm, ARRESTWARRANT_REPORT_NAME, aResponse, false);

         // Finish with
         return ( null );
    	
    }

    public ActionForward printOICWarrant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	 processReport(form, OICWARRANT_REPORT_NAME, response, false);

         // Finish with
         return ( null );

    }

    public ActionForward printOICWarrantReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	 processReport(form, OICWARRANT_RETURN_REPORT_NAME, response, false);

         // Finish with
         return ( null );

    }
    
    // new BFO reports methods
 
	/**
	 * Print the Generic Warrant PDF report using the BFO framework
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward printBFOGenericWarrant(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
		
		JuvenileWarrantForm warrantForm = (JuvenileWarrantForm) aForm;
		
		JuvenilePhotoForm  photoForm = (JuvenilePhotoForm) 
			getSessionForm(aMapping, aRequest, "juvenilePhotoForm", true);		
		photoForm.setJuvenileNumber(warrantForm.getJuvenileNum());
		UIJuvenilePhotoHelper.getMostRecentPhotoInit(photoForm);
		
		ReportingBean reportingInfo = prepareBean(warrantForm, true);
		reportingInfo.setReportName(PDFReport.GENERICWARRANT_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		
		if ( photoForm.getMostRecentPhoto() != null){ 
			ByteArrayInputStream photo = null;
			boolean isPhoto = false;
			if(photoForm.getMostRecentPhoto().getPhoto() != null ){
				photo = new ByteArrayInputStream(photoForm.getMostRecentPhoto().getPhoto());
				isPhoto = true;
			}else if(photoForm.getMostRecentPhoto().getThumbNail() != null){
				photo = new ByteArrayInputStream(photoForm.getMostRecentPhoto().getThumbNail());
				isPhoto = true;
			}
			if(isPhoto){	
				
				log.error("$$$$$$$$$$$$PROD HOT FIX_ PHOTO TRY 1 $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				BufferedImage image = ImageIO.read(photo);
				String photoFileName = warrantForm.getJuvenileNum() + ".jpg";			
				String bfoDirectoryPath = FILE_SEPARATOR + DIRECTORY_BFO_NAME; 
				String relativeFolderPath = DIRECTORY_IMAGES_NAME + bfoDirectoryPath;
				String relativeFilePath = relativeFolderPath + FILE_SEPARATOR + photoFileName;
				//String absoluteFolderPath = aRequest.getSession().getServletContext().getRealPath(DIRECTORY_IMAGES_NAME) + bfoDirectoryPath;
			//	String absoluteFilePath = aRequest.getSession().getServletContext().getRealPath(DIRECTORY_IMAGES_NAME) + bfoDirectoryPath + FILE_SEPARATOR + photoFileName;
				String absoluteFolderPath = "c:\\BFOImages\\images\\bfo\\";
				String absoluteFilePath = "c:\\BFOImages\\images\\bfo\\"+photoFileName;
				log.error("$$$$$$$$$$$$PROD HOT FIX_ PHOTO TRY 2 $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				File bfoFolder = new File(absoluteFolderPath);
				File file = new File(absoluteFilePath); 
				
				// check if folder exists but file does not... if true then write file and save path to session
				if(!file.exists()){ 
					ImageIO.write(image, "jpg", file);
					aRequest.getSession().setAttribute("printWarrantPhotoPath", "images\\bfo\\"+photoFileName);
				// if folder does not exist - try to create folder and if created write file and save path to session, otherwise, save null
				}else if(!bfoFolder.exists()){
						boolean fileCreated = true;
					try{
						fileCreated = bfoFolder.mkdir();
					}catch(Exception ex){
						fileCreated = false;
						log.error("The folder under warrant web-app did not exist and could not be created: " + bfoDirectoryPath);
					}
					if(fileCreated){
						ImageIO.write(image, "jpg", file);	// has to be written for jsp img path to pick up in jsp
						aRequest.getSession().setAttribute("printWarrantPhotoPath", "images\\bfo\\"+photoFileName);
					}else{ 
						aRequest.getSession().setAttribute("printWarrantPhotoPath", null);
					}
					// file and folder exist - save the path to the session
				}else{ 
					aRequest.getSession().setAttribute("printWarrantPhotoPath", "images\\bfo\\"+photoFileName);
				}
			}else{	// there is no photo - either thumbnail nor higher resolution
				aRequest.getSession().setAttribute("printWarrantPhotoPath", null);
			}
		}else{
			// there was no photo object to begin with.
			aRequest.getSession().setAttribute("printWarrantPhotoPath", null);
		}
		
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.GENERICWARRANT_REPORT_NAME);

	    return null;
	}
	
    /**
     * Print the Arrest Warrant PDF report using the BFO framework
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printBFOArrestWarrant(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
    	reportingInfo.setReportName(PDFReport.ARRESTWARRANT_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ARRESTWARRANT_REPORT_NAME);

	    return null;
    	
    }
    
    /**
     * Print the Arrest Warrant Return PDF report using the BFO framework
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printBFOArrestWarrantReturn(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
    	reportingInfo.setReportName(PDFReport.ARRESTWARRANTRETURN_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ARRESTWARRANTRETURN_REPORT_NAME);

	    return null;
    }
    
    /**
     * Print the Directive To Apprehend (DTA) Warrant PDF report using the BFO framework
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printBFODTAWarrant(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
    	reportingInfo.setReportName(PDFReport.DTAWARRANT_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.DTAWARRANT_REPORT_NAME);

	    return null;
    }
    
    /**
     * Print the Directive To Apprehend (DTA) Affidavit PDF report using the BFO framework
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws Exception
     */
    public ActionForward printBFODTAAffidavit(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	    {
	    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
	    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
	    	reportingInfo.setReportName(PDFReport.DTAAFFIDAVIT_REPORT_NAME.getReportName());
			aRequest.getSession().setAttribute("reportInfo", reportingInfo);

			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.DTAAFFIDAVIT_REPORT_NAME);

		    return null;
	    }

    /**
     * Print the Directive To Apprehend (DTA) Warrant Return PDF report using the BFO framework
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws Exception
     */
    public ActionForward printBFODTAWarrantReturn(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	    {
	    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
	    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
	    	reportingInfo.setReportName(PDFReport.DTAWARRANTRETURN_REPORT_NAME.getReportName());
			aRequest.getSession().setAttribute("reportInfo", reportingInfo);

			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.DTAWARRANTRETURN_REPORT_NAME);

		    return null;
	    }
   
    /**
     * Print the Order Of Immediate Custody (OIC) Warrant PDF report using the BFO framework
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws Exception
     */
    public ActionForward printBFOOICWarrant(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	    {
	    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
	    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
	    	reportingInfo.setReportName(PDFReport.OICWARRANT_REPORT_NAME.getReportName());
			aRequest.getSession().setAttribute("reportInfo", reportingInfo);

			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.OICWARRANT_REPORT_NAME);

		    return null;
	    }

    /**
     * Print the Order Of Immediate Custody (OIC) Warrant Return PDF report using the BFO framework
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws Exception
     */
    public ActionForward printBFOOICWarrantReturn(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	    {
	    	JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) aForm;
	    	ReportingBean reportingInfo = prepareBean(warrantAffidavitForm, false);
	    	reportingInfo.setReportName(PDFReport.OICWARRANTRETURN_REPORT_NAME.getReportName());
			aRequest.getSession().setAttribute("reportInfo", reportingInfo);

			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.OICWARRANTRETURN_REPORT_NAME);

		    return null;
	    }

    /**
     * 
     * @param form
     * @param reportName
     * @param response
     * @param viewWarrant
     * @throws Exception
     */
    private void processReport(ActionForm form, String reportName, HttpServletResponse response, boolean viewWarrant)
            throws Exception
    {

        JuvenileWarrantForm warrantAffidavitForm = (JuvenileWarrantForm) form;
        ReportResponseEvent aRespEvent = null;

        GenericPrintRequestEvent warrantEvent = new GenericPrintRequestEvent();

        warrantEvent.addDataObject(prepareBean(warrantAffidavitForm, viewWarrant));
        warrantEvent.setReportName(reportName);

        CompositeResponse compResponse = MessageUtil.postRequest(warrantEvent);
        aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

        if (aRespEvent != null)
        {
        	response.setContentType("application/x-file-download");
       	    response.setHeader("Content-disposition", "attachment; filename="
       	    							+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
       	    response.setHeader("Cache-Control", "max-age=" + 120);   
       	    response.setContentLength(aRespEvent.getContent().length);   
       	    response.resetBuffer();   
       	    OutputStream os;
       	    os = response.getOutputStream();
       	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
       	    os.flush();   
       	    os.close();
        }
    }
    
    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();

//        map.put("button.printGenericWarrant", "printGenericWarrant");
//        map.put("button.printArrestWarrant", "printArrestWarrant");
//        map.put("button.printArrestWarrantReturn", "printArrestWarrantReturn");
//        map.put("button.printDTAWarrant", "printDTAWarrant");
//        map.put("button.printDTAAffidavit", "printDTAAffidavit");
//        map.put("button.printDTAWarrantReturn", "printDTAWarrantReturn");
//        map.put("button.printArrestWarrantAffidavit", "printArrestWarrantAffidavit");
//        map.put("button.printOICWarrant", "printOICWarrant");
//        map.put("button.printOICWarrantReturn", "printOICWarrantReturn");
        // BFO Reports
        map.put("button.printGenericWarrant", "printBFOGenericWarrant");
        map.put("button.printArrestWarrant", "printBFOArrestWarrant");
        map.put("button.printArrestWarrantReturn", "printBFOArrestWarrantReturn");
        map.put("button.printDTAWarrant", "printBFODTAWarrant");
        map.put("button.printDTAWarrantReturn", "printBFODTAWarrantReturn");
        map.put("button.printDTAAffidavit", "printBFODTAAffidavit");
        map.put("button.printOICWarrant", "printBFOOICWarrant");      
        map.put("button.printOICWarrantReturn", "printBFOOICWarrantReturn");
        return map;
    }

    private ReportingBean prepareBean(JuvenileWarrantForm form, boolean viewWarrant)
    {
        ReportingBean reqBean = new ReportingBean();
        reqBean.setAffidavitStatement(form.getAffidavitStatement());
        reqBean.setAgency(form.getOfficerAgencyName());
        // Needed to set the Juvenile's age
        reqBean.setDateOfBirth(form.getDateOfBirth());

        /*
         * IF the request is for View Generic Warrant then get all the Juvenile
         * Associate Addresses since only the Generic Warrant.xml has to list
         * all the Associate Addresses
         */

        if ( viewWarrant )
        {
            reqBean.setAssociateAddresses(retrieveGuardianAddress(form.getJuvenileNum()));
        }

        if ((WARRANTTYPE_OIC).equalsIgnoreCase(form.getWarrantTypeId()))
        {
            reqBean.setWarrantOriginatorJudge(form.getWarrantOriginatorJudge());
        }
        
        List cautions = form.getCautionsSelected();
        int len = cautions.size();
        if (len > 0)
        {
            for (int i = 0; i < len; i++)
            {
                CodeResponseEvent code = (CodeResponseEvent) cautions.get(i);
                if (code.getCode().equals("OT"))
                {
                    // 04/04/2007 LDeen - Defect #36990
                	//code.setDescription(form.getCautionsSelected().toUpperCase());
                	code.setDescription("OTHER");
                }
            }
            cautions = CodeHelper.getCodeDescriptions(cautions);
            String cautionDescription = UIUtil.separateByDelim(cautions, ", ");
            reqBean.setCautionsSelected(cautionDescription);
            if (StringUtils.isNotEmpty(form.getCautionComments()))
            {
                reqBean.setCautionComments(form.getCautionComments());
            }
        }

        reqBean.setChargeCode(form.getChargeCode());
        reqBean.setChargeDescription(form.getChargeDescription());
        List charges = form.getChargesSelected();
        if (charges != null)
        {
            int chargeLen = charges.size();
            for (int i = 0; i < chargeLen; i++)
            {
                Object chargeObj = charges.get(i);
                if (chargeObj instanceof ChargeResponseEvent)
                {
                    ChargeResponseEvent chargeSelected = (ChargeResponseEvent) chargeObj;
                    reqBean.setChargeDescription(chargeSelected.getOffense());
                    reqBean.setDegree(chargeSelected.getDegree());
                    reqBean.setLevel(chargeSelected.getLevel());                    
                    String dateString = DateUtil.dateToString(chargeSelected.getOffenseDate(), "EEEE, MMMM dd, yyyy 'at' hh:mm");
                    reqBean.setOffenseDateAsString(dateString);      
                }
                else if (chargeObj instanceof ChargeRequestEvent)
                {
                    ChargeRequestEvent chargeSelected = (ChargeRequestEvent) chargeObj;
                    reqBean.setChargeDescription(chargeSelected.getChargeDescription());
                    reqBean.setDegree(chargeSelected.getDegree());
                    reqBean.setLevel(chargeSelected.getLevel());
                    String dateString = DateUtil.dateToString(chargeSelected.getOffenseDate(), "EEEE, MMMM dd, yyyy 'at' hh:mm");
                    reqBean.setOffenseDateAsString(dateString);  
                }
            }
        }

        List scars = (List) form.getScarsAndMarksSelected();
        String scarDesc = "";
        List scarsList = new ArrayList();
        
        for ( int z = 0; z < scars.size(); z++ ){
        	
        	ScarsMarksTattoosCodeResponseEvent respEvent = (ScarsMarksTattoosCodeResponseEvent) scars.get(z);
        		scarDesc = respEvent.getDescription();
        		scarsList.add(scarDesc);
        	
        }
        
        List tattoos = (List) form.getTattoosSelected();
        String tattooDesc = "";
        List tattooList = new ArrayList();
        
        for ( int y = 0; y < tattoos.size(); y++ ){
        	
        	ScarsMarksTattoosCodeResponseEvent respEvent = (ScarsMarksTattoosCodeResponseEvent) tattoos.get(y);
        		tattooDesc = respEvent.getDescription();
        		tattooList.add(tattooDesc);
        	
        }
        
        String scarsDesc = UIUtil.separateByDelim(scarsList, ",");
        String tattoosDesc = UIUtil.separateByDelim(tattooList, ",");
        
        reqBean.setScars(scarsDesc);
        reqBean.setTattoos(tattoosDesc);
        
        
        reqBean.setHairColor(form.getHairColor());
        reqBean.setEyeColor(form.getEyeColor());
        reqBean.setComplexionId(form.getComplexion());
        reqBean.setCourtId(form.getCourtId());
        reqBean.setCourtWithOrdinal(NumberSuffixUtil.getSuffixForString(form.getCourtId()));
        reqBean.setCourtDescription(form.getCourt());
        reqBean.setCurrentServiceDate(form.getCurrentServiceDate());
        reqBean.setExecutorName(form.getExecutorName().getFirstName() + " " + form.getExecutorName().getMiddleName() + " " + form.getExecutorName().getLastName());
        reqBean.setExecutorDepartmentName(form.getExecutorDepartmentName());
        reqBean.setFileStampDate(form.getFileStampDate());
        reqBean.setFirstName(form.getJuvenileName().getFirstName());

        LengthBean lengthBean = new LengthBean();
        lengthBean.setLength(form.getHeight());
        Integer inches = lengthBean.getInches();
        if (inches != null)
        {
            String inchesString;
            if (inches.intValue() == 1)
            {
                inchesString = "inch";
            }
            else
            {
                inchesString = "inches";
            }
            String formattedHeight = lengthBean.getFeetString() + " feet " + lengthBean.getInchesString() + " "
                    + inchesString;
            reqBean.setHeight(formattedHeight);
        }

        reqBean.setLastName(form.getJuvenileName().getLastName());
        reqBean.setMiddleName(form.getJuvenileName().getMiddleName());
        reqBean.setOfficerAgencyName(form.getOfficerAgencyName());
        reqBean.setOfficerName(form.getOfficerName().getCompleteFullNameFirst());
        reqBean.setRace(form.getRace());
        reqBean.setSexId(form.getSex());
        reqBean.setSuffix(form.getJuvenileName().getSuffix());
        reqBean.setWarrantActivationDate(form.getWarrantActivationDate());
        String warrantId = form.getWarrantNum();
        reqBean.setWarrantNum(warrantId);
        reqBean.setWarrantOriginatorName(this.getWarrantOriginatorName(form));
        reqBean.setWarrantType(form.getWarrantType());
        reqBean.setPetitionNum(form.getPetitionNum());
        reqBean.setWeight(form.getWeight());
        reqBean.setDateOfBirthString("NONE");
        Date birthDate = form.getDateOfBirth();
        if ( birthDate != null ){
        	
        	reqBean.setDateOfBirthString(DateUtil.dateToString(birthDate, DateUtil.DATE_FMT_1));
        } 
        
        reqBean.setAgeVerification(form.getDateOfBirthSource());
        return reqBean;
    }

    public String getWarrantOriginatorName(JuvenileWarrantForm form)
    {
        String originatorId = form.getWarrantOriginatorId();
        String originatorName = null;
        if (originatorId != null && originatorId.length() > 0)
        {
            UserResponseEvent userInfo = SecurityUIHelper.getUser(originatorId);
            if (userInfo != null)
            {
                originatorName = userInfo.getFirstName() + " " + userInfo.getLastName();
            }
        }
        return originatorName;

    }

    private String retrieveAssociateAddress(JuvenileWarrantForm form)
    {
        JuvenileAssociateBean associateBean = null;
        StringBuffer assocAddress = new StringBuffer();

        GetJuvenileAssociateDataEvent requestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

        List associates = form.getAssociates();
        int len = associates.size();
        for (int i = 0; i < len; i++)
        {
            associateBean = (JuvenileAssociateBean) associates.get(i);
            requestEvent.setAssociateNumber(associateBean.getAssociateNum());
            assocAddress.append(fetchAssociateData(requestEvent)).append("<br/>");
        }

        return assocAddress.toString();

    }
    
    private String retrieveGuardianAddress(String juvenileNum)
    {
	JuvenileAssociateBean associateBean = null;
        StringBuffer assocAddress = new StringBuffer();
        
	 List<JuvenileAssociateBean> familyMemberList = new ArrayList();
	        familyMemberList = UIJuvenileWarrantHelper.getAssociates(juvenileNum);
	
        for (int i = 0; i < familyMemberList.size(); i++)
        {
            associateBean = (JuvenileAssociateBean) familyMemberList.get(i);
    	List addressList = UIJuvenileWarrantHelper.getFamilyMemberAddresses(associateBean.getAssociateNum());      	
      	//famMember.setAssociateAddresses(UIJuvenileHelper.sortMemberAddressList(addressList));
          	for(int y = 0; y< addressList.size();y++){
          	   
          	  MemberAddress fmAddress = (MemberAddress) addressList.get(y);
          	  assocAddress.append(fmAddress.getStreetNum() + " " + fmAddress.getStreetName() + " " +  fmAddress.getCityStateZip()).append("<br/>");
          	}
            
        }

        return assocAddress.toString();

    }

    /**
     * 
     * @param event
     * @return
     */
    private String fetchAssociateData(GetJuvenileAssociateDataEvent event)
    {
        CompositeResponse response = MessageUtil.postRequest(event);

        List addressRecords = MessageUtil.compositeToList(response, JuvenileAssociateAddressResponseEvent.class);

        int len = addressRecords.size();

        StringBuffer assocAddress = new StringBuffer();

        for (int i = 0; i < len; i++)
        {
            JuvenileAssociateAddressResponseEvent addressResponseEvent = (JuvenileAssociateAddressResponseEvent) addressRecords
                    .get(i);
            assocAddress.append(addressResponseEvent.toString());
        }

        return assocAddress.toString();
    }

	@Override
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		
	}

}