/*
 * Created on Feb 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.code;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodeRespEvtCodeIdComparator implements java.util.Comparator{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		if(arg0==null)
			return -1;
		else if(arg1==null)
			return 1;
		CodeResponseEvent incomingCodeRespEvt1=(CodeResponseEvent)arg0;
		CodeResponseEvent incomingCodeRespEvt2=(CodeResponseEvent)arg1;
		if(incomingCodeRespEvt1.getCodeId()==null)
			return -1;
		else if(incomingCodeRespEvt2.getCodeId()==null)
						return 1;
		return incomingCodeRespEvt1.getCodeId().compareTo(incomingCodeRespEvt2.getCodeId());
	}

}
