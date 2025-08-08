//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\Contract.java

package pd.supervision.administerserviceprovider.administercontract;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.administercontract.UpdateContractEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import pd.codetable.Code;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * A Provider Program may be funded through a contract. Some service providers
 * have contracts to be paid in CSCD. The contracts are traced to the funding
 * source to make payments accordingly.
 */
public class Contract extends PersistentObject {
	/**
	 * Properties for contract
	 */

	private Date endDate;
	private String contractName;
	private String contractNumber;
	private Date startDate;
	private String tracerNumberFrom;
	private String tracerNumberTo;
	private Double totalValue;
	private String contractRenewalNumber;
	private String contractTypeId;
	private String programFundingDescription;
	private String glAccountkey;
	private String contractId;
	private String agencyId;

	/**
	 * Properties for serviceContractValues
	 * 
	 * @referencedType pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	 */
	Collection serviceContractValues = null;

	/**
	 * Properties for serviceContract
	 * 
	 * @useParent true
	 * @detailerDoNotGenerate false
	 */
	private ServiceContractValue serviceContract;

	/**
	 * Properties for contractType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey CONREACT_TYPE
	 * @detailerDoNotGenerate false
	 */
	private Code contractType;

	/**
	 * Properties for glAccountKey
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GLACCOUNTKEY
	 * @detailerDoNotGenerate false
	 */
	private Code glAccountKeyCode;

	/**
	 * @roseuid 451D2A44038E
	 */
	public Contract() {

	}

	/**
	* @return pd.supervision.administerserviceprovider.administercontract.Contract
	* @param contractId
	* @roseuid 4107B06D01B5
	*/
	static public Contract find(String contractId)
	{
		IHome home = new Home();
		return (Contract) home.find(contractId, Contract.class);
	}
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, Contract.class);
	}
	
	/**
	* @return java.util.Iterator
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(Contract.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName,attrValue,Contract.class);
	}
		
	/**
	* @roseuid 4107B06D01BB
	*/
	public void create(){
		IHome home = new Home();
		home.bind(this);
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		fetch();
		return contractId;
	}

	/**
	 * @param contractId
	 *            The contractId to set.
	 */
	public void setContractId(String contractId) {
		if (this.contractId == null || !this.contractId.equals(contractId)) {
			markModified();
		}
		this.setOID(contractId);
		this.contractId = contractId;
	}

	/**
	 * @return Returns the contractName.
	 */
	public String getContractName() {
		fetch();
		return contractName;
	}

	/**
	 * @param contractName
	 *            The contractName to set.
	 */
	public void setContractName(String contractName) {
		if (this.contractName == null || !this.contractName.equals(contractName)) {
			markModified();
		}
		this.contractName = contractName;
	}

	/**
	 * @return Returns the contractNumber.
	 */
	public String getContractNumber() {
		fetch();
		return contractNumber;
	}

	/**
	 * @param contractNumber
	 *            The contractNumber to set.
	 */
	public void setContractNumber(String contractNumber) {
		if (this.contractNumber == null || !this.contractNumber.equals(contractNumber)) {
			markModified();
		}
		this.contractNumber = contractNumber;
	}

	/**
	 * @return Returns the contractRenewalNumber.
	 */
	public String getContractRenewalNumber() {
		fetch();
		return contractRenewalNumber;
	}

	/**
	 * @param contractRenewalNumber
	 *            The contractRenewalNumber to set.
	 */
	public void setContractRenewalNumber(String contractRenewalNumber) {
		if (this.contractRenewalNumber == null || !this.contractRenewalNumber.equals(contractRenewalNumber)) {
			markModified();
		}
		this.contractRenewalNumber = contractRenewalNumber;
	}

	/**
	 * @return Returns the contractTypeId.
	 */
	public String getContractTypeId() {
		fetch();
		return contractTypeId;
	}

	/**
	 * @param contractTypeId
	 *            The contractTypeId to set.
	 */
	public void setContractTypeId(String contractTypeId) {
		if (this.contractTypeId == null || !this.contractTypeId.equals(contractTypeId)) {
			markModified();
		}
		this.contractTypeId = contractTypeId;
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		fetch();
		return endDate;
	}

	/**
	 * @param endDate
	 *            The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		if (this.endDate == null || !this.endDate.equals(endDate))
		{
			markModified();
		}
		this.endDate = endDate;
	}

	/**
	 * @return Returns the glAccountkey.
	 */
	public String getGlAccountkey() {
		fetch();
		return glAccountkey;
	}

	/**
	 * @param glAccountkey
	 *            The glAccountkey to set.
	 */
	public void setGlAccountkey(String glAccountkey) {
		if (this.glAccountkey == null || !this.glAccountkey.equals(glAccountkey)) {
			markModified();
		}
		this.glAccountkey = glAccountkey;
	}

	/**
	 * @return Returns the programFundingDescription.
	 */
	public String getProgramFundingDescription() {
		fetch();
		return programFundingDescription;
	}

	/**
	 * @param programFundingDescription
	 *            The programFundingDescription to set.
	 */
	public void setProgramFundingDescription(String programFundingDescription) {
		if (this.programFundingDescription == null || !this.programFundingDescription.equals(programFundingDescription)) {
			markModified();
		}
		this.programFundingDescription = programFundingDescription;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		fetch();
		return startDate;
	}

	/**
	 * @param startDate
	 *            The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		if (this.startDate == null || !this.startDate.equals(startDate))
		{
			markModified();
		}
		this.startDate = startDate;
	}

	/**
	 * @return Returns the tracerNumberFrom.
	 */
	public String getTracerNumberFrom() {
		fetch();
		return tracerNumberFrom;
	}

	/**
	 * @param tracerNumberFrom
	 *            The tracerNumberFrom to set.
	 */
	public void setTracerNumberFrom(String tracerNumberFrom) {
		if (this.tracerNumberFrom == null || !this.tracerNumberFrom.equals(tracerNumberFrom)) {
			markModified();
		}
		this.tracerNumberFrom = tracerNumberFrom;
	}

	/**
	 * @return Returns the tracerNumberTo.
	 */
	public String getTracerNumberTo() {
		fetch();
		return tracerNumberTo;
	}

	/**
	 * @param tracerNumberTo
	 * The tracerNumberTo to set.
	 */
	public void setTracerNumberTo(String tracerNumberTo) {
		if (this.tracerNumberTo == null || !this.tracerNumberTo.equals(tracerNumberTo)) {
			markModified();
		}
		this.tracerNumberTo = tracerNumberTo;
	}
	
	/**
	* Clears all pd.supervision.administerserviceprovider.administercontract.ServiceContractValue from class relationship collection.
	* @roseuid 4107DFB603E7
	*/
	public void clearServiceContractValues()
	{
		initServiceContractValues();
		serviceContractValues.clear();
	}

	/**
	* returns a collection of pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	* @return java.util.Collection
	* @roseuid 4107DFB603A1
	*/
	public Collection getServiceContractValues()
	{
		fetch();
		initServiceContractValues();
		return serviceContractValues;
	}
	
	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	* @roseuid 4107DFB60397
	*/
	private void initServiceContractValues()
	{
		if (serviceContractValues == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				serviceContractValues =
					new mojo.km.persistence.ArrayList(ServiceContractValue.class, "contractId", "" + getOID());
			}
			catch (Throwable t)
			{
				serviceContractValues = new java.util.ArrayList();
			}
		}
	}
	
	/**
	* insert a pd.supervision.administerserviceprovider.administercontract.ServiceContractValue into class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603B5
	*/
	public void insertServiceContractValues(ServiceContractValue anObject)
	{
		initServiceContractValues();
		serviceContractValues.add(anObject);
	}

	/**
	* Removes a pd.supervision.administerserviceprovider.administercontract.ServiceContractValue from class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603C9
	*/
	public void removeServiceContractValues(ServiceContractValue anObject)
	{
		initServiceContractValues();
		serviceContractValues.remove(anObject);
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	* @roseuid 4107DFB60134
	*/
	public Code getContractType()
	{
		fetch();
		initContractType();
		return contractType;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 4107DFB6012A
	*/
	private void initContractType()
	{
		if (contractType == null)
		{
			try
			{
				contractType =
					(Code) new mojo
						.km
						.persistence
						.Reference(contractTypeId, Code.class, "CONTRACT_TYPE")
						.getObject();
			}
			catch (Throwable t)
			{
				contractType = null;
			}
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	* @roseuid 4107DFB60134
	*/
	public Code getGlAccountKeyCode()
	{
		fetch();
		initGlAccountKeyCode();
		return glAccountKeyCode;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 4107DFB6012A
	*/
	private void initGlAccountKeyCode()
	{
		if (glAccountKeyCode == null)
		{
			try
			{
				glAccountKeyCode =
					(Code) new mojo
						.km
						.persistence
						.Reference(glAccountkey, Code.class, "GLACCOUNTKEY")
						.getObject();
			}
			catch (Throwable t)
			{
				glAccountKeyCode = null;
			}
		}
	}

	/**
	* @param contractEvent
	*/
	private void saveContract(UpdateContractEvent contractEvent)
	{
		this.setContractTypeId(contractEvent.getContractTypeId());
		this.setStartDate(contractEvent.getStartDate());
		this.setEndDate(contractEvent.getEndDate());
		this.setProgramFundingDescription(contractEvent.getProgramFundingDescription());
		this.setContractName(contractEvent.getContractName());
		this.setTracerNumberFrom(contractEvent.getTracerRangeFrom());
		this.setTracerNumberTo(contractEvent.getTracerRangeTo());
		this.setAgencyId(contractEvent.getAgencyId());
		if(contractEvent.getTotalValue() == null || contractEvent.getTotalValue().equals("")){
			this.setTotalValue(new Double("0.00"));
		}else{
			this.setTotalValue(new Double(contractEvent.getTotalValue()));
		}
		this.setGlAccountkey(contractEvent.getGlAccountKey());
		if(contractEvent.getRenewalNum() == null || contractEvent.getRenewalNum().equals("")){
			this.setContractRenewalNumber("0");
		}else{
			this.setContractRenewalNumber(contractEvent.getRenewalNum());
		}
		if(contractEvent.getNumber() == null || contractEvent.getNumber().equals("")){
			this.setContractNumber("0");
		}else{
			this.setContractNumber(contractEvent.getNumber());
		}
	}

	/**
	* @param contractEvent
	*/
	public void updateContract(UpdateContractEvent contractEvent)
	{
		this.saveContract(contractEvent);
	}

	/**
	* @param contractEvent
	*/
	public void renewContract(UpdateContractEvent contractEvent)
	{
		this.setContractRenewalNumber("" + (Integer.parseInt(contractRenewalNumber) + 1));
		this.setEndDate(contractEvent.getEndDate());
	}

	public void createOID()
	{
		IHome home = new Home();
		home.bind(this);
	}	

	/**
	 * @return Returns the totalValue.
	 */
	public Double getTotalValue() {
		fetch();
		return totalValue;
	}
	/**
	 * @param totalValue The totalValue to set.
	 */
	public void setTotalValue(Double totalValue) {
		if(this.totalValue == null || !this.totalValue.equals(totalValue)){
			markModified();
		}
		this.totalValue = totalValue;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		if(this.agencyId == null || !this.agencyId.equals(agencyId)){
			markModified();
		}
		this.agencyId = agencyId;
	}
	
	/**
	 * @return Returns the availableTotalValue.
	 */
	public String getAvailableTotalValue() {
		double availableContractValue = this.getTotalValue().doubleValue();
		Iterator valIter = ServiceContractValue.findAll("contractId",this.getOID().toString());
		while(valIter.hasNext()){
			ServiceContractValue serviceVal = (ServiceContractValue) valIter.next();
			if(serviceVal != null){
				availableContractValue = availableContractValue - serviceVal.getServiceProviderValue().doubleValue();
			}
		}
        return "" + availableContractValue;
	}
}
