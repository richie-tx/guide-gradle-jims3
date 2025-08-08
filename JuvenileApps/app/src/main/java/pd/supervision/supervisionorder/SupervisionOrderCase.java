package pd.supervision.supervisionorder;

import java.util.Date;
import java.util.Iterator;

import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.criminalcase.CriminalCase;

/**
 * @author mchowdhury
 */
public class SupervisionOrderCase extends CriminalCase implements Comparable
{
	private String orderCourtId;
	private String criminalCaseId;
	private String orderStatusId;
	private Code versionType = null;
	private Code orderStatus = null;
	private String versionTypeId;
	private String defendantId;
	private Date orderFiledDate;	
	private String currentCourtId;
	private String supervisionPeriodId;

	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}

	public String getOrderCourtId() {
		return orderCourtId;
	}

	public void setOrderCourtId(String orderCourtId) {
		this.orderCourtId = orderCourtId;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getVersionTypeId() {
		return versionTypeId;
	}

	public void setVersionTypeId(String versionTypeId) {
		this.versionTypeId = versionTypeId;
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public Date getOrderFiledDate() {
		return orderFiledDate;
	}

	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}

	/**
	 * @return the currentCourtId
	 */
	public String getCurrentCourtId() {
		return currentCourtId;
	}

	/**
	 * @param currentCourtId the currentCourtId to set
	 */
	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initOrderStatus
	 * @methodInvocation fetch
	 * @methodInvocation initOrderStatus
	 */
	public Code getOrderStatus()
	{
		fetch();
		initOrderStatus();
		return orderStatus;
	}
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initOrderStatus()
	{
		if (orderStatus == null)
		{
			orderStatus = (Code) new mojo.km.persistence.Reference(orderStatusId, Code.class,
					"ORDER_STATUS").getObject();
		}
	}
		
	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initVersionType
	 * @methodInvocation fetch
	 * @methodInvocation initVersionType
	 */
	public Code getVersionType()
	{
		fetch();
		initVersionType();
		return versionType;
	}
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initVersionType()
	{
		if (versionType == null)
		{
			versionType = (Code) new mojo.km.persistence.Reference(versionTypeId, Code.class,
					"VERSION_TYPE").getObject();
		}
	}
	
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionOrderCase.class);
	}
	
	
	static public Iterator findAll(String attrName, String attrValue) 
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, SupervisionOrderCase.class);
	}
	
	
	/**
	 * @return SuperviseeCaseOrderResponseEvent
	 */
	public SuperviseeCaseOrderResponseEvent getSuperviseeCaseOrder() {
		SuperviseeCaseOrderResponseEvent resp = new SuperviseeCaseOrderResponseEvent();
		resp.setCourtId(this.getCurrentCourtId());
		resp.setCaseNumber(this.getCriminalCaseId()); 
		resp.setSupervisionPeriodId( this.getSupervisionPeriodId() );
		resp.setOrderStatus((this.getOrderStatusId() == null || this.getOrderStatusId().equals(""))?"":this.getOrderStatus().getDescription());
		resp.setVersion((this.getVersionTypeId() == null || this.getVersionTypeId().equals(""))?"":this.getVersionType().getDescription());
		resp.setOrderFiledDate(this.getOrderFiledDate());
		resp.setDefendantId(this.getDefendantId());
		resp.setSupervisionOrderId(this.getOID());			
		resp.setCdi(this.getCourtDivisionId());
		resp.setOffenseCodeId(this.getOffenseCodeId());
		if(this.getOffenseCodeId() != null && !"".equals(this.getOffenseCodeId().trim())){
			resp.setOffenseCodeDesc((this.getOffenseCode() == null)?"":this.getOffenseCode().getDescription());
 		}
		resp.setCaseFileDate(DateUtil.stringToDate(formatCaseFilingDate(this.getFilingDate()),DateUtil.DATE_FMT_1));
		return resp;
	}
	
	private String formatCaseFilingDate(String filingDate){
		StringBuffer formattedFilingDate = new StringBuffer();
		if(filingDate != null && !filingDate.trim().equals("")){
			String year = filingDate.substring(4,6);
			String month = filingDate.substring(0,2);
			String day = filingDate.substring(2,4);
			
			if(filingDate.length() == 6){        				
				if(60 < Integer.parseInt(year)){  // ask Mary if you need to know about the '60'
					year = "19" + year;
				}else{
					year = "20" + year;
				}
			}
			
			formattedFilingDate.append(month);
			formattedFilingDate.append("/");	        				
			formattedFilingDate.append(day);
			formattedFilingDate.append("/");
			formattedFilingDate.append(year);			
		}
		return formattedFilingDate.toString();
	}
}

