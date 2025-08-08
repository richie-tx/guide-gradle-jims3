/*
 * Created on Oct 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.agency.helper;

import java.util.Iterator;

import naming.PDContactConstants;

import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;

import pd.contact.agency.Agency;
import pd.pattern.IBuilder;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AgencySimpleListBuilder implements IBuilder
{
	private Iterator agencies;
	private CompositeResponse response;

	public AgencySimpleListBuilder(Iterator agencies)
	{
		response = new CompositeResponse();
		this.agencies = agencies;
	}

	public void build()
	{
		if (agencies != null)
		{
			while (agencies.hasNext())
			{
				Agency agency = (Agency) agencies.next();
				AgencyResponseEvent agencyResponse = new AgencyResponseEvent();
				response.setTopic(PDContactConstants.AGENCY_LISTITEM_EVENT_TOPIC);
				agencyResponse.setAgencyId(agency.getAgencyId());
				agencyResponse.setAgencyName(agency.getAgencyName());
				this.response.addResponse(agencyResponse);
			}
		}
	}

	public Object getResult()
	{
		return this.response;
	}

}
