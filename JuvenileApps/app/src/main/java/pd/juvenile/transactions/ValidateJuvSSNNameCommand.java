/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import pd.juvenile.Juvenile;
import messaging.juvenile.ValidateJuvSSNNameEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.utilities.DateUtil;

/**
 * @author jjose
 *
 */
public class ValidateJuvSSNNameCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		ValidateJuvSSNNameEvent requestEvent = (ValidateJuvSSNNameEvent) event;

		if (requestEvent.getSsn() == null || requestEvent.getSsn().equals(PDConstants.BLANK)){
			return;
		}
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	 	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	 	if(!grantedFeature)
	 	   requestEvent.setJuvRecType("JUVENILE");
	 	else
	 	   requestEvent.setJuvRecType("");
	 	  
		Iterator juvenileIterator = Juvenile.findAll(requestEvent);
		List juvenileList = CollectionUtil.iteratorToList(juvenileIterator);
		List replies = new ArrayList();
		Juvenile juvenile = null;
		JuvenileProfilesResponseEvent respEvent = null;

		for (int i = 0; i < juvenileList.size(); i++) {
			juvenile = (Juvenile) juvenileList.get(i);
			if (requestEvent.getJuvenileNum().equals(juvenile.getActualJuvenileNum())){
				continue;
			}
			else{
				respEvent = new JuvenileProfilesResponseEvent();
//				respEvent.setJuvenileNum(juvenile.getJuvenileNum()); // return juvenileNum with OID value
				respEvent.setJuvenileNum(juvenile.getActualJuvenileNum());
				respEvent.setFirstName(juvenile.getFirstName());
				respEvent.setMiddleName(juvenile.getMiddleName());
				respEvent.setLastName(juvenile.getLastName());
				
				respEvent.setRace(juvenile.getRaceId());
				respEvent.setSex(juvenile.getSexId());
				if(juvenile.getRace()!=null && juvenile.getRace().getDescription()!=null){
					respEvent.setRaceDesc(juvenile.getRace().getDescription());
				}
				if(juvenile.getSex()!=null && juvenile.getSex().getDescription()!=null){
					respEvent.setSexDesc(juvenile.getSex().getDescription());
				}
				respEvent.setSSN(juvenile.getSSN());
				respEvent.setDateOfBirth("");
				if(juvenile.getDateOfBirth()!=null){
					try{
						String myDateStr=DateUtil.dateToString(juvenile.getDateOfBirth(),DateUtil.DATE_FMT_1);
						respEvent.setDateOfBirth(myDateStr);
						myDateStr = null;
					}
					catch(Exception e){
						
					}
				}
				replies.add(respEvent);
			}
		}
		MessageUtil.postReplies(replies);
		
		juvenileIterator = null;
		juvenileList = null;
		replies =null;
		juvenile = null;
		respEvent = null;
		requestEvent = null;
	}

		/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}
}

