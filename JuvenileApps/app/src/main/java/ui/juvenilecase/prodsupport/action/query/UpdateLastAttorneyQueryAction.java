
package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.JJSLastAttorney;
import pd.km.util.Name;
import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdateLastAttorneyQueryAction extends Action
{
    
    private Logger log = Logger.getLogger("UpdateLastAttorneyQueryAction");
    
    public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm prodform = (ProdSupportForm) form;
	String forward = "success";
	
	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	
	if(clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{  
	   prodform.clearAllResults();
	   prodform.setMsg("");
	   return mapping.findForward("error");
	}

	String juvNum = prodform.getJuvenileId().trim();
	String chainNum = prodform.getChainNum().trim();
	System.out.println("Chain num" + chainNum   );
	
	if(juvNum == null || juvNum.equals(""))
	{
	    prodform.setMsg("Please enter a Juvenile ID");
	    return(mapping.findForward("error"));
	}

	
	//Verify valid Juvenile
	
	JJSJuvenileResponseEvent juv = retrieveJuvenile(juvNum);
	
	if(juv == null || juv.getJuvenileNum() == null || juv.getJuvenileNum().equals(""))
	{
	    prodform.setToJuvenileId("");
	    prodform.setMsg("Juvenile not found. You must enter a valid Juvenile Number");
	    return mapping.findForward("error");
	}
	prodform.clear();
	
	Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
	prodform.setJuvenileName(name.getFormattedName());
	prodform.setJuvenileSsn(juv.getJuvenileSsn());
	prodform.setBirthDate(juv.getBirthDate());
	prodform.setRectype(juv.getRectype());
	
	// master status from jjs_ms_juvenile
	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juv.getStatusId());
	prodform.setStatusId(statusDesc);
		//
	Boolean isEmpty = true;
	JJSLastAttorney jjsRecord = new JJSLastAttorney();
	Iterator it = JJSLastAttorney.findAll("juvenileNum",juvNum);
	
	while(it.hasNext()){
	    
	    JJSLastAttorney rec = (JJSLastAttorney) it.next();
	    jjsRecord=rec;    
	    isEmpty = false;
	}
	
	if(!isEmpty){
	    prodform.setAttRecord(jjsRecord);
	    prodform.setAtyBarNum(jjsRecord.getAtyBarNum());
	    prodform.setAtyName(jjsRecord.getAtyName());
	    prodform.setGalBarNum(jjsRecord.getGalBarNum());
	    prodform.setGalAttorneyName(jjsRecord.getGalName());
	    prodform.setAttorneyConnection(jjsRecord.getAttConnect());
	    prodform.setCourtId(jjsRecord.getJjclcourtId());
	    //prodform.setCourtId("864544");
	    prodform.setOriginalCourtId(jjsRecord.getJjclcourtId());
	    prodform.setDetentionId(jjsRecord.getJjcldetentionId());
	    prodform.setOriginalDetentionId(jjsRecord.getJjcldetentionId());
	    if (chainNum != null 
		    && chainNum.length() > 0 ) {
		GetProductionSupportCourtRecords(prodform, juvNum, chainNum);
	    //GetProductionSupportCourtRecords(prodform, juvNum, "864544");
		GetProductionSupportDetentionCourtRecords(prodform, juvNum, chainNum);
		if ( (prodform.getJuvDetCourtRecords() == null 
			|| prodform.getJuvDetCourtRecords().size() == 0)
			&& (prodform.getJuvDistCourtRecords() == null 
				|| prodform.getJuvDistCourtRecords().size() ==0)  ) {
		    prodform.setMsg("Chain Number not identified for current juvenile. Please verify entry.");
		    return mapping.findForward("error");
		}
	    }
	    
	    
	    forward = "success";
    	}
    	else
    	{
    	    prodform.setMsg("LAST_ATTORNEY details not found for the juvenile record " + juvNum);
    	    return mapping.findForward("error");
    	    }

	prodform.setMsg("");
	return mapping.findForward(forward);
		
    }
 

    /**
     * returns juvenile number 
     * 
     * @param juvenileNumber
     * @return
     */
    private JJSJuvenileResponseEvent retrieveJuvenile(String juvenileNumber)
    {

	/**
	 * Search for jcjuvenile.
	 */
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNumber);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	return juvenileResp;
    }
    
   private void GetProductionSupportCourtRecords( ProdSupportForm prodform, 
	   						String juvenileNumber,
	   						String chainNum){
       ArrayList<DocketEventResponseEvent> juvDistCourtRecords = null;
       prodform.getJuvDistCourtRecords();
       if ( chainNum != null ) {
           IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
           GetProductionSupportCourtRecordsEvent jjsCLCrtEvent = (GetProductionSupportCourtRecordsEvent)
    	       							EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);
           
           jjsCLCrtEvent.setJuvenileNumber(juvenileNumber);
           jjsCLCrtEvent.setChainNumber(chainNum);
           dispatch.postEvent(jjsCLCrtEvent);
           CompositeResponse resp = (CompositeResponse) dispatch.getReply();
           juvDistCourtRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);
           if (juvDistCourtRecords != null){
               Collections.sort(juvDistCourtRecords, new Comparator<DocketEventResponseEvent>(){
        	   @Override
        	   public int compare(DocketEventResponseEvent record1,
        		   		DocketEventResponseEvent record2 ){
        	       return record1.getDocketEventId().compareTo( record2.getDocketEventId());
        	   }
               });
               prodform.setJuvDistCourtRecords(juvDistCourtRecords);
           }
          
       } else {
	   prodform.setJuvDistCourtRecords(null);
       }
       
       
   }
   
   
   private void GetProductionSupportDetentionCourtRecords( ProdSupportForm prodform, 
	   							String juvenileNumber,
	   							String chainNum){
       ArrayList<DocketEventResponseEvent> juvDetCourtRecords = null;
       prodform.getJuvDetCourtRecords();
       if ( chainNum != null){
           IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
           GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent jjsCLCrtEvent = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent)
    	       							EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
           jjsCLCrtEvent.setJuvenileNumber(juvenileNumber);
           jjsCLCrtEvent.setChainNumber(chainNum);
           dispatch.postEvent(jjsCLCrtEvent);
           CompositeResponse resp = (CompositeResponse) dispatch.getReply();
           juvDetCourtRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);
           if (juvDetCourtRecords != null){
               Collections.sort(juvDetCourtRecords, new Comparator<DocketEventResponseEvent>(){
        	   @Override
        	   public int compare(DocketEventResponseEvent record1,
        		   		DocketEventResponseEvent record2 ){
        	       return record1.getDocketEventId().compareTo( record2.getDocketEventId());
        	   }
               });
               prodform.setJuvDetCourtRecords(juvDetCourtRecords);
           }
           
          
       } else {
	   prodform.setJuvDetCourtRecords(null);
       }
       
       
   }
   
   
 
    
}























