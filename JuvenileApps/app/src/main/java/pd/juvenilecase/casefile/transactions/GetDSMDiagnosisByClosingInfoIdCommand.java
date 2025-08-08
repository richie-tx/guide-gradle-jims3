/*
 * Created on Oct 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import pd.codetable.PDCodeHelper;
import pd.juvenilecase.casefile.JuvenileExitReportDSMDiagnosis;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import messaging.casefile.GetDSMDiagnosisByClosingInfoIdEvent;
import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ugopinath
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetDSMDiagnosisByClosingInfoIdCommand implements ICommand {

	/**
	 *  
	 */
	public GetDSMDiagnosisByClosingInfoIdCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void execute(IEvent event) {
		GetDSMDiagnosisByClosingInfoIdEvent recEvent = (GetDSMDiagnosisByClosingInfoIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator diagnosisResults = JuvenileExitReportDSMDiagnosis.findAll(recEvent);
		String diagnosisDesc =""; //bug fix #23314
		while(diagnosisResults.hasNext())
		{
			JuvenileExitReportDSMDiagnosis diagnosis = (JuvenileExitReportDSMDiagnosis)diagnosisResults.next();
			DSMDiagnosisResponseEvent resp = new DSMDiagnosisResponseEvent();
			resp.setClosingInfoId(diagnosis.getClosingInfoId());
			resp.setDiagnosisCd(diagnosis.getDiagnosisCd()) ; //bug fix #23314
			diagnosisDesc = SimpleCodeTableHelper.getDescrByCode("DSM_CODES",diagnosis.getDiagnosisCd());
			
			if(diagnosisDesc==null || diagnosisDesc!=null &&diagnosisDesc.isEmpty())
					//diagnosisDesc = SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES",diagnosis.getDiagnosisCd());
				diagnosisDesc=ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(diagnosis.getDiagnosisCd()); //US 40636

			resp.setDiagnosis(diagnosisDesc);
			resp.setConditionCd(diagnosis.getConditionCd());
			resp.setSeverityCd(diagnosis.getSeverityCd());
			resp.setTestSessId(diagnosis.getTestSessId());
			resp.setDsmDiagnosisId(diagnosis.getOID().toString());
			dispatch.postEvent(resp);	
		}
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
