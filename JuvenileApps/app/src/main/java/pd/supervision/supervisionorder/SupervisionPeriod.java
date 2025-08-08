package pd.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import messaging.supervisionorder.GetPeriodForSupervisionOrderEvent;
import messaging.supervisionorder.GetSupervisionPeriodsEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 44CFACF902E3
 */
public class SupervisionPeriod extends PersistentObject implements Comparable
{
	private Date supervisionBeginDate;
	private Date supervisionEndDate;
	private String agencyId;
	private String defendantId;
	/**
	 * Properties for previousSupervisionPeriod
	 * @referencedType pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private SupervisionPeriod previousSupervisionPeriod = null;
	private String previousSupervisionPeriodId;

	public int compareTo(Object arg0)
	{
		SupervisionPeriod sp = (SupervisionPeriod) arg0;
		Date curDate = new Date();
		if (supervisionEndDate == null)
		{
			return sp.getSupervisionEndDate().compareTo(curDate);
		}
		else if (sp.getSupervisionEndDate() == null)
		{
			return curDate.compareTo(supervisionEndDate);
		}
		else
		{
			return sp.getSupervisionEndDate().compareTo(supervisionEndDate);
		}
	}

	public static Comparator BeginDateComparator = new Comparator() {
		public int compare(Object supervisionPeriod, Object otherSupervisionPeriod) {
		  Date beginDate = ((SupervisionPeriod)supervisionPeriod).getSupervisionBeginDate();
		  Date otherBeginDate = ((SupervisionPeriod)otherSupervisionPeriod).getSupervisionBeginDate();
		  
		  return beginDate.compareTo(otherBeginDate);
		}	
	};
	
	/**
	* @return 
	* @param oid
	*/
	static public SupervisionPeriod find(String oid)
	{
		IHome home = new Home();
		SupervisionPeriod sp = (SupervisionPeriod) home.find(oid, SupervisionPeriod.class);
		return sp;
	}
	
	/**
	 * @return 
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionPeriod.class);
	}

	static public SupervisionPeriod create(SupervisionOrder order)
	{
		SupervisionPeriod supervisionPeriod = new SupervisionPeriod();
		supervisionPeriod.setAgencyId(order.getAgencyId());
		//ER 62832 - Set activation date to caseSupervisionBegin date for HC cases 
		//           set activation date to transferInDate for OOC cases.
		if (SupervisionOrderHelper.isOutOfCountyCase(order.getCriminalCaseId())){
			supervisionPeriod.setSupervisionBeginDate(order.getTransferInDate());
		} else {
			supervisionPeriod.setSupervisionBeginDate(order.getCaseSupervisionBeginDate());
		}
		
		// get the latest SupervisionPeriod
		SupervisionPeriod prevSupervisionPeriod = findLatestSupervisionPeriod(order.getDefendantId(), order
				.getAgencyId());
		if (prevSupervisionPeriod != null){
			supervisionPeriod.setPreviousSupervisionPeriod(prevSupervisionPeriod);
		}
		
		// create SupervisionPeriodredirect
		SupervisionPeriodRedirect spRedirect = SupervisionPeriodRedirect.create(supervisionPeriod);
		return supervisionPeriod;
	}

	static public SupervisionPeriod create(SupervisionSplitOrder orderPeriod)
	{
		SupervisionPeriod supervisionPeriod = new SupervisionPeriod();
		supervisionPeriod.setAgencyId(orderPeriod.getAgencyId());
		supervisionPeriod.setSupervisionBeginDate(orderPeriod.getCaseBeginDate());
		supervisionPeriod.setSupervisionEndDate(orderPeriod.getCaseEndDate());
		// get the latest SupervisionPeriod
//		SupervisionPeriod prevSupervisionPeriod = findLatestSupervisionPeriod(orderPeriod.getDefendantId(), orderPeriod.getAgencyId());
//		if (prevSupervisionPeriod != null){
//			supervisionPeriod.setPreviousSupervisionPeriod(prevSupervisionPeriod);
//		}
		// create SupervisionPeriodredirect
		SupervisionPeriodRedirect spRedirect = SupervisionPeriodRedirect.create(supervisionPeriod);
		new Home().bind(spRedirect);
		return supervisionPeriod;
	}

	static public SupervisionPeriod create(SupervisionOrderPeriod orderPeriod)
	{
		SupervisionPeriod supervisionPeriod = new SupervisionPeriod();
		supervisionPeriod.setAgencyId(orderPeriod.getAgencyId());
		supervisionPeriod.setSupervisionBeginDate(orderPeriod.getActivationDate());
		supervisionPeriod.setSupervisionEndDate(orderPeriod.getInactivationDate());

			// create SupervisionPeriodredirect
		SupervisionPeriodRedirect spRedirect = SupervisionPeriodRedirect.create(supervisionPeriod);
		new Home().bind(spRedirect);
		return supervisionPeriod;
	}
	
	/**
	 * Copy 1 supervision period to another
	 * @param originalSupervisionPeriodId
	 * @return
	 */
	static public SupervisionPeriod copy(String originalSupervisionPeriodId)
	{
		if (originalSupervisionPeriodId != null)
		{
				//retrieve target supervision period (in case of previous split or consolidation)
			SupervisionPeriodRedirect target_supervision_period = 
				SupervisionPeriodRedirect.findBySourcePeriod(originalSupervisionPeriodId);		
			
				//retrieve supervision period to copy
			SupervisionPeriod originalSupervisionPeriod = 
				SupervisionPeriod.find(target_supervision_period.getTargetSupervisionPeriodId());
			
			if (originalSupervisionPeriod != null)
			{
					//create new supervision period and copy properties of original
				SupervisionPeriod new_supervision_period = new SupervisionPeriod();
				new_supervision_period.setAgencyId(originalSupervisionPeriod.getAgencyId());
				new_supervision_period.setSupervisionBeginDate(
						originalSupervisionPeriod.getSupervisionBeginDate());
				new_supervision_period.setSupervisionEndDate(
						originalSupervisionPeriod.getSupervisionEndDate());
				new_supervision_period.bind();
		
					// create SupervisionPeriodredirect
				SupervisionPeriodRedirect spRedirect = 
					SupervisionPeriodRedirect.create(new_supervision_period);	
				new Home().bind(spRedirect);
				return new_supervision_period;
			}
			else
				return null; //return null if original supervision period not found
		}
		else
		{
			return null; //return null if original supervision period not found			
		}
	}//end of copy()
	
	static public Iterator findAll(String spn, String agencyId){
		GetSupervisionPeriodsEvent getSupervisionPeriodEvent = new GetSupervisionPeriodsEvent();
		getSupervisionPeriodEvent.setAgencyId(agencyId);
		getSupervisionPeriodEvent.setSpn(spn);
		Iterator iter = findAll(getSupervisionPeriodEvent);
		
		return iter;
	}

	static public Iterator findAll(String spn){
		GetSupervisionPeriodsEvent getSupervisionPeriodEvent = new GetSupervisionPeriodsEvent();
		getSupervisionPeriodEvent.setSpn(spn);
		Iterator iter = findAll(getSupervisionPeriodEvent);
		
		return iter;
	}

	static public SupervisionPeriod findActiveSupervisionPeriod(String spn, String agencyId)
	{
		SupervisionPeriod supervisionPeriod = null;
		// find active supervision period
		GetActiveSupervisionPeriodEvent getActiveSupervisionPeriodEvent = new GetActiveSupervisionPeriodEvent();
		getActiveSupervisionPeriodEvent.setAgencyId(agencyId);
		getActiveSupervisionPeriodEvent.setSpn(spn);
		Iterator iter = findAll(getActiveSupervisionPeriodEvent);
		if (iter.hasNext())
		{
			// get the last from this collection
			supervisionPeriod = (SupervisionPeriod) iter.next();
		}
		return supervisionPeriod;
	}

	static public SupervisionPeriod findLatestSupervisionPeriod(String spn, String agencyId)
	{
		// find latest supervision period (could be the active period)
		// sorted return list descending by End Date
	    Iterator iter = findAll(spn, agencyId);
	    
		List list = new ArrayList();
		while (iter.hasNext())
		{
			Object nextPeriod = iter.next();
			if (!list.contains(nextPeriod))
			{
				list.add(nextPeriod);
			}
		}
		SupervisionPeriod supervisionPeriod = null;
		if (list.size() > 0)
		{
			Collections.sort(list);
			supervisionPeriod = (SupervisionPeriod) list.get(0);
			
		}
		return supervisionPeriod;
	}

	static public SupervisionPeriod findPeriodForSupervisionOrder(String supervisionOrderId)
	{
		SupervisionPeriod supervisionPeriod = null;
		// find active supervision period
		GetPeriodForSupervisionOrderEvent getSupervisionPeriodEvent = new GetPeriodForSupervisionOrderEvent();
		getSupervisionPeriodEvent.setSupervisionOrderId(supervisionOrderId);
		Iterator iter = findAll(getSupervisionPeriodEvent);
		if (iter.hasNext())
		{
			// get the last from this collection
			supervisionPeriod = (SupervisionPeriod) iter.next();
		}
		return supervisionPeriod;
	}

	/**
	 * @roseuid 44CFACF902F3
	 * @methodInvocation markModified
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @roseuid 44D0C37A01B0
	 */
	public SupervisionPeriod()
	{
	}

	/**
	 * Access method for the supervisionBeginDate property.
	 * @return the current value of the supervisionBeginDate property
	 * @methodInvocation fetch
	 */
	public Date getSupervisionBeginDate()
	{
		fetch();
		return supervisionBeginDate;
	}

	/**
	 * Sets the value of the supervisionBeginDate property.
	 * @param aSupervisionBeginDate the new value of the supervisionBeginDate property
	 * @methodInvocation markModified
	 */
	public void setSupervisionBeginDate(Date aSupervisionBeginDate)
	{
		if (this.supervisionBeginDate == null || !this.supervisionBeginDate.equals(aSupervisionBeginDate))
		{
			markModified();
		}
		supervisionBeginDate = aSupervisionBeginDate;
	}

	/**
	 * Access method for the supervisionEndDate property.
	 * @return the current value of the supervisionEndDate property
	 * @methodInvocation fetch
	 */
	public Date getSupervisionEndDate()
	{
		fetch();
		return supervisionEndDate;
	}

	/**
	 * Sets the value of the supervisionEndDate property.
	 * @param aSupervisionEndDate the new value of the supervisionEndDate property
	 * @methodInvocation markModified
	 */
	public void setSupervisionEndDate(Date aSupervisionEndDate)
	{
		if (this.supervisionEndDate == null || !this.supervisionEndDate.equals(aSupervisionEndDate))
		{
			markModified();
		}
		supervisionEndDate = aSupervisionEndDate;
	}

	/**
	 * Access method for the agencyId property.
	 * @return the current value of the agencyId property
	 * @methodInvocation fetch
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * Sets the value of the agencyId property.
	 * @param aAgencyId the new value of the agencyId property
	 * @methodInvocation markModified
	 */
	public void setAgencyId(String aAgencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId))
		{
			markModified();
		}
		agencyId = aAgencyId;
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation markModified
	 */
	public void setPreviousSupervisionPeriodId(String aPreviousSupervisionPeriodId)
	{
		if (this.previousSupervisionPeriodId == null
				|| !this.previousSupervisionPeriodId.equals(aPreviousSupervisionPeriodId))
		{
			markModified();
		}
		previousSupervisionPeriod = null;
		this.previousSupervisionPeriodId = aPreviousSupervisionPeriodId;
	}

	/**
	 * @return Returns the previousSupervisionPeriod.
	 * @methodInvocation initPreviousSupervisionPeriod
	 */
	public SupervisionPeriod getPreviousSupervisionPeriod()
	{
		initPreviousSupervisionPeriod();
		return previousSupervisionPeriod;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation fetch
	 */
	public String getPreviousSupervisionPeriodId()
	{
		fetch();
		return previousSupervisionPeriodId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private void initPreviousSupervisionPeriod()
	{
		if (previousSupervisionPeriod == null)
		{
			previousSupervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
					previousSupervisionPeriodId, SupervisionPeriod.class).getObject();
		}
	}

	/**
	 * set the type reference for class member previousSupervisionPeriod
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPreviousSupervisionPeriodId
	 */
	public void setPreviousSupervisionPeriod(SupervisionPeriod aPreviousSupervisionPeriod)
	{
		if (this.previousSupervisionPeriod == null || !this.previousSupervisionPeriod.equals(aPreviousSupervisionPeriod))
		{
			markModified();
		}
		if (aPreviousSupervisionPeriod.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aPreviousSupervisionPeriod);
		}
		setPreviousSupervisionPeriodId("" + aPreviousSupervisionPeriod.getOID());
		this.previousSupervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
				aPreviousSupervisionPeriod).getObject();
	}
	
	 public SupervisionPeriodResponseEvent getResponseEvent(){
        SupervisionPeriodResponseEvent spre = new SupervisionPeriodResponseEvent();
        spre.setAgencyId(this.getAgencyId());
        spre.setSupervisionPeriodId(this.getOID());
        spre.setSupervisionBeginDate(this.getSupervisionBeginDate());
        spre.setSupervisionEndDate(this.getSupervisionEndDate());
        return spre;
    }

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}	
}

