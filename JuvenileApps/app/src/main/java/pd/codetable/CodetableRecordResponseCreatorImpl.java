/*
 * Created on Jan 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;

/**
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class CodetableRecordResponseCreatorImpl implements ResponseCreator
{

	/**
	 * @param object
	 * @returns object.
	 */
	public Object create(Object object)
	{
		CodetableReg reg = (CodetableReg) object;
		CodetableRecordResponseEvent rRespEvent = new CodetableRecordResponseEvent();
		rRespEvent.setCodetableName(reg.getCodetableName());
		rRespEvent.setCodetableDescription(reg.getCodetableDescription());
		rRespEvent.setCodetableRegId(reg.getOID().toString());
		rRespEvent.setType(reg.getType());
		rRespEvent.setEntityName(reg.getCodetableEntityName());
		rRespEvent.setContextKey(reg.getCodetableContextKey());

		Iterator agenciesIter = reg.getAgencies().iterator();
		Collection agencies = new ArrayList();
		while (agenciesIter.hasNext())
		{
			Agency agency = (Agency) agenciesIter.next();
			AgencyResponseEvent resp = pd.contact.PDContactHelper.getAgencyResponseEvent(agency, true);
			agencies.add(resp);
		}
		rRespEvent.setAgencies(agencies);
		return rRespEvent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
