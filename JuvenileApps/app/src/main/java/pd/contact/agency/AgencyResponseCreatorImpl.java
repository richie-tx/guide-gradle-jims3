/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.contact.agency;

import messaging.contact.agency.reply.AgencyResponseEvent;
import naming.PDContactConstants;
import pd.codetable.Code;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgencyResponseCreatorImpl implements ResponseCreator{
     
    /**
	 * Creates Agency response event from entity.
	 * @param object
	 * @return object AgencyResponseEvent
	 */
	public Object create(Object object)
	{
		Agency agency = (Agency) object;
		AgencyResponseEvent are = (AgencyResponseEvent) this.createThin(agency);
		if ((agency.getAgencyTypeId() != null) && (!(agency.getAgencyTypeId().equals(""))))
		{
			Code code = agency.getAgencyType();
			if (code != null)
			{
				are.setAgencyTypeId(code.getCode());
				are.setAgencyType(code.getDescription());
			}
		}
//87191		//are.setProjectAnalystInd(agency.getProjectAnalystInd());
		are.setTopic(PDContactConstants.AGENCY_EVENT_TOPIC);
		return are;
	}
	
	/**
	 * Used to return simple AgencyResponseEvents that do not return the Code relationship
	 * values.  For example, it would be used for an Agency Drop Down.
	 * @param object
	 * @return object AgencyResponseEvent
	 */
	public Object createThin(Object object)
	{
		Agency agency = (Agency) object;
		AgencyResponseEvent are = new AgencyResponseEvent();
		are.setAgencyId(agency.getAgencyId());
		are.setAgencyName(agency.getAgencyName());
		return are;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
