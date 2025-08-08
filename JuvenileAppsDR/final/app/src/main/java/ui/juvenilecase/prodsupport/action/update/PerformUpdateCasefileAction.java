package ui.juvenilecase.prodsupport.action.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.UpdateJuvenileCasefileEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.UpdateProductionSupportCasefileClosingEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.RegionType;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 */

public class PerformUpdateCasefileAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateCasefileAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		
		String logonid = SecurityUIHelper.getLogonId();		
		String casefileId = regform.getCasefileId();
		Date changedCreateDate = null;
		boolean isChange = false;

		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("PerformUpdateCasefileAction.java - Casefile ID was null.");
			return (mapping.findForward("error"));
		}
		
		UpdateJuvenileCasefileEvent updateEvent = (UpdateJuvenileCasefileEvent)
		EventFactory.getInstance( JuvenileCasefileControllerServiceNames.UPDATEJUVENILECASEFILE );
		updateEvent.setSupervisionNumber( regform.getCasefileId() );
		
		//US 153685 - CasefileClosing update event - update controllingReferral for casefileclosing if controllingReferral changes in casefile
		UpdateProductionSupportCasefileClosingEvent updateCasefileClosingEvent = (UpdateProductionSupportCasefileClosingEvent)
			EventFactory.getInstance( ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEFILECLOSING);
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileId);	
		
		// activation date
		String actdate = regform.getActdate();
		boolean isActDateChanged = checkIfTwoValuesChanged(actdate, DateUtil.dateToString(regform.getCasefileDet().getActivationDate(),DateUtil.DATE_FMT_1));
		if (isActDateChanged)
		{
			Date activationDate=DateUtil.stringToDate(actdate, DateUtil.DATE_FMT_1);
			updateEvent.setActivationDate(activationDate);
			regform.setOldActivationDate(DateUtil.dateToString(regform.getCasefileDet().getActivationDate(),DateUtil.DATE_FMT_1));
			isChange = true;
		}	
		else{
			updateEvent.setActivationDate(regform.getCasefileDet().getActivationDate());
			regform.setOldActivationDate("");
		}
		
		// supervision end date
		String enddate = regform.getEnddate();
		boolean isEndDateChanged = checkIfTwoValuesChanged(enddate, DateUtil.dateToString(regform.getCasefileDet().getSupervisionEndDate(),DateUtil.DATE_FMT_1));
		if (isEndDateChanged)
		{
			Date supEndDate=DateUtil.stringToDate(enddate, DateUtil.DATE_FMT_1);
			updateEvent.setSupervisionEndDate( supEndDate );
			regform.setOldSupervisionEndDate(regform.getCasefileDet().getSupervisionEndDate());
			isChange = true;
		}	
		else{
			updateEvent.setSupervisionEndDate(regform.getCasefileDet().getSupervisionEndDate());
			regform.setOldSupervisionEndDate(null);
		}
		// create date
		String createdDate =regform.getCreatedate();
		boolean isCreatedDateChanged = checkIfTwoValuesChanged(createdDate,DateUtil.dateToString(regform.getCasefileDet().getCreateDate(),DateUtil.DATE_FMT_1));
		if (isCreatedDateChanged)
		{	System.err.println("inside createdate changed");
			Date newCreateDate = DateUtil.stringToDate(createdDate, DateUtil.DATE_FMT_1);
			updateEvent.setCreateDate(newCreateDate);
			regform.setOldCreateDate(DateUtil.dateToString(regform.getCasefileDet().getCreateDate(),DateUtil.DATE_FMT_1));
			changedCreateDate = newCreateDate;
			isChange = true;
		}	
		else{
			updateEvent.setCreateDate(null);
			regform.setOldCreateDate("");
		}
		
		// maysi needed  flag
		if(regform.getNewMaysineeded()!=null && !regform.getNewMaysineeded().equals("")){
				isChange = true;
				if(regform.getNewMaysineeded().equals("1")){
					updateEvent.setMAYSINeeded(true);
				}
				else{
					updateEvent.setMAYSINeeded(false);
				}
		}else if(regform.getNewMaysineeded()!=null && regform.getNewMaysineeded().equals("")){
			if(regform.getCasefileDet().getIsMAYSINeeded()){
				updateEvent.setMAYSINeeded(true);
			}
			else{
				updateEvent.setMAYSINeeded(false);
			}
		}
		
		// pact risk needed flag
		if ( regform.getNewPactRiskNeeded() != null
			&&  regform.getNewPactRiskNeeded().length() > 0   ) {
		    isChange = true;
		    if ( "1".equalsIgnoreCase( regform.getNewPactRiskNeeded() ) ){
			updateEvent.setPactRiskNeeded(true);
		    } else {
			updateEvent.setPactRiskNeeded(false);
		    }
		} else {
		    if ( regform.getCasefileDet().getRiskNeed() ){
			updateEvent.setPactRiskNeeded(true);
		    } else {
			updateEvent.setPactRiskNeeded(false);
		    }
		}
		
		// Hispanic needed flag
		if ( regform.getNewHispanicIndicatorNeeded() != null
			&&  regform.getNewHispanicIndicatorNeeded().length() > 0   ) {
		    isChange = true;
		    if ( "1".equalsIgnoreCase( regform.getNewHispanicIndicatorNeeded() ) ){
			updateEvent.setHispanicIndicatorNeeded(true);
		    } else {
			updateEvent.setHispanicIndicatorNeeded(false);
		    }
		} else {
		    if ( regform.getCasefileDet().getHispanic() ){
			updateEvent.setHispanicIndicatorNeeded(true);
		    } else {
			updateEvent.setHispanicIndicatorNeeded(false);
		    }
		}
		
		//School history needed flag
		if ( regform.getNewSchoolHistoryNeeded() != null
			&&  regform.getNewSchoolHistoryNeeded().length() > 0   ) {
		    isChange = true;
		    if ( "1".equalsIgnoreCase( regform.getNewSchoolHistoryNeeded() ) ){
			updateEvent.setSchoolHistoryNeeded(true);
		    } else {
			updateEvent.setSchoolHistoryNeeded(false);
		    }
		} else {
		    if ( regform.getCasefileDet().getSchool() ){
			updateEvent.setSchoolHistoryNeeded(true);
		    } else {
			updateEvent.setSchoolHistoryNeeded(false);
		    }
		}
		
		// Vop Entry needed flag
		if ( regform.getNewVopEntryNeeded() != null
			&&  regform.getNewVopEntryNeeded().length() > 0   ) {
		    isChange = true;
		    if ( "1".equalsIgnoreCase( regform.getNewVopEntryNeeded() ) ){
			updateEvent.setVopEntryNeeded(true);
		    } else {
			updateEvent.setVopEntryNeeded(false);
		    }
		} else {
		    if ( regform.getCasefileDet().getVop() ){
			updateEvent.setVopEntryNeeded(true);
		    } else {
			updateEvent.setVopEntryNeeded(false);
		    }
		}
		
		//Substance abuse needed flag
		if ( regform.getNewSubstanceAbuseNeeded() != null
			&& regform.getNewSubstanceAbuseNeeded().length() > 0  ){
		    isChange = true;
		    if ( "1".equalsIgnoreCase( regform.getNewSubstanceAbuseNeeded()) ){
			updateEvent.setSubstanceAbuseNeeded( true ); 
		    } else {
			updateEvent.setSubstanceAbuseNeeded( false );
		    }
		} else {
		    if ( regform.getCasefileDet().getSubabuse() ) {
			updateEvent.setSubstanceAbuseNeeded( true ); 
		    } else {
			updateEvent.setSubstanceAbuseNeeded( false ); 
		    }
		}
		
		// juvseqnum
		if(regform.getNewJuvseqnum()!=null && !regform.getNewJuvseqnum().equals("")){
			isChange = true;
			updateEvent.setSequenceNum(regform.getNewJuvseqnum());
			regform.setOldJuvseqnum(regform.getCasefileDet().getSequenceNum());
		}
		else{
			updateEvent.setSequenceNum(regform.getCasefileDet().getSequenceNum());
			regform.setOldJuvseqnum("");
			
		}
		//juvenileNum added for US 180414
		if(regform.getToJuvenileId()!=null && !regform.getToJuvenileId().equals("")){
			isChange = true;
			updateEvent.setJuvenileNum(regform.getToJuvenileId());
			regform.setFromJuvenileId(regform.getCasefileDet().getJuvenileNum());
		}
		else{
			updateEvent.setJuvenileNum(regform.getCasefileDet().getJuvenileNum());
			regform.setFromJuvenileId("");
			
		}// added for US 180414 ENDS
		// supervision type cd
		if(regform.getSupervisionBox()!=null && !regform.getSupervisionBox().equals("")){
			isChange = true;
			updateEvent.setSupervisionTypeId(regform.getSupervisionBox());
			regform.setSupervisionBox(regform.getCasefileDet().getSupervisionTypeId());
		}
		else{
			updateEvent.setSupervisionTypeId(regform.getCasefileDet().getSupervisionTypeId());
			regform.setSupervisionBox("");
		}
		
		// controlling referral
		if(regform.getNewControllingReferral()!= null && !regform.getNewControllingReferral().equals("")){
			isChange = true;
			updateEvent.setControllingReferralId(regform.getNewControllingReferral());
			regform.setOldControllingReferral(regform.getCasefileDet().getControllingReferralId());						
			
			//casefile closing 
			if(respEvent != null && (respEvent.getCasefileClosingInfoId() != null || !respEvent.getCasefileClosingInfoId().equals(""))){
			    updateCasefileClosingEvent.setCasefileClosingInfoId(respEvent.getCasefileClosingInfoId());
			    updateCasefileClosingEvent.setControllingReferralId(regform.getNewControllingReferral());
			    updateCasefileClosingEvent.setUserID(logonid);
			}			

		}
		else{
			updateEvent.setControllingReferralId(regform.getCasefileDet().getControllingReferralId());
			regform.setOldControllingReferral("");
		}
		// update values
		updateEvent.setReferralRiskNeeded(regform.getCasefileDet().isReferralRiskNeeded());
		updateEvent.setInterviewRiskNeeded(regform.getCasefileDet().isInterviewRiskNeeded());
		updateEvent.setUpdateDate(DateUtil.getCurrentDate());
		updateEvent.setUpdateUser(logonid);

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( updateEvent );

		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( composite );		
		
		//================================= casefileclosing - execute after casefile is successful =================================
		if(respEvent != null && (respEvent.getCasefileClosingInfoId() != null || !respEvent.getCasefileClosingInfoId().equals(""))){
        		IDispatch dispatchCasefileClosing = EventManager.getSharedInstance( EventManager.REQUEST );
        		dispatchCasefileClosing.postEvent( updateCasefileClosingEvent );
        		
        		CompositeResponse compositeCasefileClosing = (CompositeResponse)dispatchCasefileClosing.getReply();
        		MessageUtil.processReturnException( compositeCasefileClosing );
		}
		//==============================================================================================
		
		// get the changed data fresh from the database
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
			getCasefileEvent.setSupervisionNumber( casefileId ) ;
		
		// check that a value changed before updating
		if(!isChange){
			regform.setMsg("At least one value needs to be changed.");
			return (mapping.findForward("error"));
		}
		IDispatch updatedDispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		updatedDispatch.postEvent( getCasefileEvent );
		
		CompositeResponse updatedResponse = (CompositeResponse)updatedDispatch.getReply( ) ;

		// add a separate call to update CreateDate separately in direct call to database (bypass MOJO)
		updateCreateDateWithoutMojo(updateEvent.getCreateDate(), regform.getCasefileId());
		
		// get updated record from database
		JuvenileCasefileResponseEvent updatedCasefile = (JuvenileCasefileResponseEvent)
				MessageUtil.filterComposite( updatedResponse, JuvenileCasefileResponseEvent.class ) ;
		log.info("Casefile Query ID: " + casefileId + " LogonId: " + SecurityUIHelper.getLogonId().toUpperCase());		
		// Populate updated casefile
		if(changedCreateDate != null){
			updatedCasefile.setCreateDate(changedCreateDate);
		}
		if( updatedCasefile != null )
		{
			regform.setCasefileDet(updatedCasefile);
		}else{
			regform.setCasefileDet(null);
		}
		log.info("Performed update of casefile for CASEFILE_ID=" + casefileId + " LogonId: " + logonid);
		return mapping.findForward("success");
	}
	
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}
	
	
	/**
	 * get sql connection for manual update of Create Date field (had to bypass mojo due to sharing in insert/update mapping of 
	 * CreateDate which is an audit field
	 * @param createDate
	 * @param casefileId
	 */
    public void updateCreateDateWithoutMojo(Date createDate, String casefileId) {
    	
    	if(createDate != null){
	    	Statement stmt = null;
	 	    Connection con = null;
	 	    StringBuffer sb = new StringBuffer();
	 	    sb = new StringBuffer("");
		    
		    RegionType regionType = new RegionType();
		    String region = regionType.getRegion();
		    /* search based on following
		    region = "PROD";
		    region = "PERF";
			region = "TR";
			region = "D2";
			*/	    
		    if(region.equalsIgnoreCase("PROD")){
	                  sb = new StringBuffer("jdbc:sqlserver://SVPJIMS2SQL01:1433;databaseName=JIMS2"); //which was - "jdbc:jtds:sqlserver://SVPJIMS2SQL01/JIMS2/"
	                }else if(region.equalsIgnoreCase("QA")){
	                  sb = new StringBuffer("jdbc:sqlserver://SVQJIMS2SQL1:1433;databaseName=JIMS2"); //which was - "jdbc:jtds:sqlserver://SVQJIMS2SQL1/JIMS2/"
	                }else if(region.equalsIgnoreCase("PT")){
	                  sb = new StringBuffer("jdbc:sqlserver://SVQJIMS2SQL1:1433;databaseName=JIMS2"); //which was - "jdbc:jtds:sqlserver://SVQJIMS2SQL1/JIMS2/"
	                }else if(region.equalsIgnoreCase("TR")){
	                  sb = new StringBuffer("jdbc:sqlserver://SVTJIMS2SQL01:1433;databaseName=JIMS2"); //which was - "jdbc:jtds:sqlserver://SVTJIMS2SQL01/JIMS2/"
	                }else{ //dev
	                  sb = new StringBuffer("jdbc:sqlserver://SVDJIMS2SQL1:1433;databaseName=JIMS2"); //which was - "jdbc:jtds:sqlserver://SVDJIMS2SQL1/JIMS2/"
	                }
	    	
	 	    	
	    	try{
	    		con = getMySqlConnection();	 			
				stmt = con.createStatement();
				DateFormat fullDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0000000");
				String createDateString = fullDateTimeFormatter.format(createDate);
				String updateString = "UPDATE JIMS2.JCCASEFILE SET CREATEDATE = '" + createDateString + "' WHERE CASEFILE_ID=" + casefileId;
				stmt.execute( updateString);
				log.info("Updated the following: " + updateString);
			}catch(Exception e){
				
				System.err.println("Exception :" + e.getMessage() );
			}finally{		
					try{
					stmt.close();
					con.close();
					}catch(Exception e){	
						System.out.println(e);
					}
			}
    	}
    }
    
    /**
     * retrieve a valid connection to the database
     * @param url
     * @return
     * @throws Exception
     */
	public static Connection getMySqlConnection() throws Exception {
	    
	    ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties("JDBCMSSQL");

            String driver = cProp.getDriver();
	    String username = "jims2user";
	    String password = "jims2userpassword";

	    
	    try
	    {
		Class.forName( driver ).getConstructor().newInstance();
	    }
	    catch (Exception e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    Connection conn = DriverManager.getConnection(cProp.getTestUrl(), username, password);
	    return conn;
	  }
	
}
