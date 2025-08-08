/*
 * Created on September 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.administercontract;

import java.text.DecimalFormat;
import java.util.Iterator;

import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractResponseCreatorImpl implements ResponseCreator{
     
	/**
	 * Creates Contract response event from entity.
	 * @param object
	 * @return object ContractResponseEvent
	 */
	public Object create(Object object){
		Contract contract = (Contract) object;
		ContractResponseEvent resp = new ContractResponseEvent();
		resp.setContractId(contract.getOID().toString());
		resp.setContractName(contract.getContractName());
		if ((contract.getContractTypeId() != null) && (!(contract.getContractTypeId().equals(""))))
		{
			Code code = contract.getContractType();
			if (code != null)
			{
				resp.setContractTypeId(code.getCode());
				resp.setContractType(code.getDescription());
			}
		} 
		if ((contract.getGlAccountkey() != null) && (!(contract.getGlAccountkey().equals(""))))
		{
			Code code = contract.getGlAccountKeyCode();
			if (code != null)
			{
				resp.setGlAccountKeyId(code.getCode());
				resp.setGlAccountKeyDesc(code.getDescription());
			}
		} 
		resp.setStartDate(contract.getStartDate());
		resp.setEndDate(contract.getEndDate());
		resp.setProgramFundingDescription(contract.getProgramFundingDescription());
		resp.setNumber(contract.getContractNumber());
		Double totalValue = contract.getTotalValue();
		if(totalValue != null){
			resp.setTotalValue(this.formatDecimal(totalValue.doubleValue()));
		}
		resp.setRenewalNum(contract.getContractRenewalNumber());
		resp.setAgencyId(contract.getAgencyId());
		resp.setTracerNumberFrom(contract.getTracerNumberFrom());
		resp.setTracerNumberTo(contract.getTracerNumberTo());
		if(contract.getEndDate() != null && DateUtil.getCurrentDate().after(contract.getEndDate())){
			resp.setExpired(true);
		}
		return resp;
	}

	/**
	 * @param d
	 * @return String
	 */
	private String formatDecimal(double d) {
		DecimalFormat myFormatter = new DecimalFormat("######0.00");
		return myFormatter.format(d);
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
