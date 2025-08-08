/*
 * Created on Jan 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import messaging.codetable.reply.CodetableAttributeResponseEvent;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodetableAttributeResponseCreatorImpl implements ResponseCreator{
     
	/**
	  * @param object
	  * @returns object.
	  */
	public Object create(Object object)
	{
		CodetableRegAttribute attr = (CodetableRegAttribute) object;
		CodetableAttributeResponseEvent aRespEvent = new CodetableAttributeResponseEvent();
		aRespEvent.setCodetableRegAttributeId(attr.getOID().toString());
		aRespEvent.setDbColumn(attr.getDbcolumn());
		aRespEvent.setDisplayName(attr.getDisplayName());
		aRespEvent.setDisplayOrder(attr.getDisplayOrder());
		aRespEvent.setType(attr.getType());
		aRespEvent.setAudit(attr.isAudit());
		aRespEvent.setRequired(attr.isRequired());	
		aRespEvent.setEntityName(attr.getCompEntityName());
		aRespEvent.setContextKey(attr.getCompContextKey());
		aRespEvent.setUpdatable(attr.isUpdatable());
		if(attr.getCompoundAttributeName() != null && !attr.getCompoundAttributeName().equals("")){
			aRespEvent.setCompound(true);
		}
		aRespEvent.setNumeric(attr.isNumeric());
		aRespEvent.setMaxLength(attr.getMaxLength());
		aRespEvent.setMinLength(attr.getMinLength());
		aRespEvent.setUnique(attr.isUnique());
		aRespEvent.setValidCheckRequired(attr.isValidCheckRequired());
		aRespEvent.setCompundType(attr.getCompoundType());
		aRespEvent.setCompoundName(attr.getCompoundAttributeName());
		aRespEvent.setCompoundId(attr.getCompoundAttributeId());
		return aRespEvent;
	}

	/* (non-Javadoc)
	 * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		// TODO Auto-generated method stub
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
