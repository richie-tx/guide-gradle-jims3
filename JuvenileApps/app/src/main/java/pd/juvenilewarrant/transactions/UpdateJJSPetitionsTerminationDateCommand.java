/*
 * Created on Aug 2, 2005
 *
 */
package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.juvenilewarrant.UpdateJJSPetitionsTerminationDateEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.reply.JuvenileReferralPetitionBean;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;

/**
 * @author rYoung
 */
public class UpdateJJSPetitionsTerminationDateCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {

	UpdateJJSPetitionsTerminationDateEvent evt = (UpdateJJSPetitionsTerminationDateEvent) event;
	JuvenileReferralPetitionBean bean = null;
	
	List<JuvenileReferralPetitionBean> list = new ArrayList<JuvenileReferralPetitionBean>();
	List<JuvenileReferralPetitionBean> filedlist = new ArrayList<JuvenileReferralPetitionBean>();
	
	HashMap<String, java.util.Date> reopened = new HashMap<String, java.util.Date>();
	Iterator<JJSPetition> petition = JJSPetition.findAll("juvenileNum", evt.getJuvenileNum());

	while (petition.hasNext())
	{

	    JJSPetition pet = (JJSPetition) petition.next();

	    if (!"0".equals(pet.getSeverity()))
	    {

		bean = new JuvenileReferralPetitionBean();
		bean.setPetitionNum(pet.getPetitionNum());
		bean.setPetitionOid(pet.getOID());
		bean.setSequenceNum(pet.getSequenceNum());
		bean.setPetitionStatus(pet.getStatus());
		bean.setReferralNum(pet.getReferralNum());
		bean.setSeverityLevel(pet.getSeverity());

		GetJJSReferralEvent getEvent = new GetJJSReferralEvent();
		getEvent.setJuvenileNum(pet.getJuvenileNum());
		getEvent.setReferralNum(pet.getReferralNum());

		Iterator<JJSReferral> referrals = JJSReferral.findAll(getEvent);

		while (referrals.hasNext())
		{
		    JJSReferral referral = referrals.next();
		    bean.setClosedDate(referral.getCloseDate());		   
		}
		 list.add(bean);
	    }

	}

	ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
	sortFields.add(new ReverseComparator(new BeanComparator("petitionNum")));
	sortFields.add(new ReverseComparator(new BeanComparator("closedDate")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort(list, multiSort);

	for (int i = 0; i < list.size(); i++)
	{

	    JuvenileReferralPetitionBean petBean = list.get(i);
	    if (!reopened.containsKey(petBean.getPetitionNum()))
	    {
		reopened.put(petBean.getPetitionNum(), petBean.getClosedDate());
	    }
	}

	for( int x= 0; x<list.size(); x++) {
	    
	    JuvenileReferralPetitionBean fBean = list.get(x);
	    if( "F".equalsIgnoreCase( fBean.getPetitionStatus()) ) {
		
		filedlist.add(fBean) ;
	    }	    
	}
	
	ArrayList<ReverseComparator> sortFieldz = new ArrayList<ReverseComparator>();
	sortFieldz.add(new ReverseComparator(new BeanComparator("petitionNum")));
	sortFieldz.add(new ReverseComparator(new BeanComparator("sequenceNum")));	
	ComparatorChain multi = new ComparatorChain(sortFieldz);
	Collections.sort(filedlist, multi);
	
	String petitionNum = "";
	for (int x = 0; x < filedlist.size(); x++)
	{

	    JuvenileReferralPetitionBean xBean = filedlist.get(x);
	    if (!petitionNum.equalsIgnoreCase(xBean.getPetitionNum()))
	    {

		JJSPetition _obj = JJSPetition.findById(xBean.getPetitionOid());

		if ("F".equalsIgnoreCase(_obj.getStatus()))
		{

		    java.util.Date reopenedDate = reopened.get(xBean.getPetitionNum());
		    java.util.Date terminationCreateDate = DateUtil.getCurrentDate();

		    if (reopenedDate != null)
		    {
			_obj.setTerminationDate(reopenedDate);
		    }
		    else
		    {
			_obj.setTerminationDate(xBean.getClosedDate());
		    }

		    //US 179842 - add termination create date
		    if (_obj.getTerminationCreateDate() == null || "".equals(_obj.getTerminationCreateDate()))
		    {
			_obj.setTerminationCreateDate(terminationCreateDate);
		    }
		    petitionNum = xBean.getPetitionNum();		    
		    Iterator<Map.Entry<String, String>> itr = evt.getReffDps().entrySet().iterator();
		    while (itr.hasNext())
		    {
			Map.Entry<String, String> entry = itr.next();
			//if (_obj.getDPSCode()!=null && entry.getKey().equalsIgnoreCase(xBean.getReferralNum())&& !_obj.getDPSCode().equalsIgnoreCase("643"))
			if (entry.getKey()!=null && entry.getKey().equalsIgnoreCase(xBean.getReferralNum()))
			{
			    if(_obj.getDPSCode()!=null && _obj.getDPSCode().equalsIgnoreCase("643"))
			    {}
			    else
				_obj.setDPSCode(entry.getValue());
			}
		    }
		}
	    }

	    // }
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
	// TODO Auto-generated method stub

    }

}
