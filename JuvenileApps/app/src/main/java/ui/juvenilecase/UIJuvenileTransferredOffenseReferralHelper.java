/*
 * Created on June 26, 2013
 * common code for Juvenile Transferred Offense Referral flows in Casefile and Profile  
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import pd.juvenilecase.interviewinfo.InterviewHelper;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetTransferredOffenseReferralsEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;

/**
 * @author cShimek
 * 
 */
public class UIJuvenileTransferredOffenseReferralHelper {
	/**
	 * getCounties
	 * @return
	 */
	public static List<CodeResponseEvent> getCounties() {
		List temp1 = CodeHelper.getActiveCodesInM204(PDCodeTableConstants.JUVENILE_COUNTY,true); //#33358 changes
		List temp2 = new ArrayList();
	// remove HARRIS from list - code = 101
		String desc = UIConstants.EMPTY_STRING;
		int idx = 0;
		if (temp1 != null){
			for (int x=0; x<temp1.size(); x++ ) {
				CodeResponseEvent cre = (CodeResponseEvent) temp1.get(x);
				if (!"101".equalsIgnoreCase(cre.getCode()) ) { //#33358 changes
					desc = cre.getDescription();
					idx = desc.indexOf(PDCodeTableConstants.COUNTY);
					if (idx > 2) {	
						desc = desc.substring(0, idx);
					}
					cre.setDescription(desc.trim());
					temp2.add(cre);
				}
			}
		}
		return temp2;
	}
	
	/**
	 * loadAvailableTransferredOffenseReferrals
	 * @param transferredOffensesList
	 * @param juvenileNum
	 * @return
	 */
	public static List loadAvailableTransferredOffenseReferrals(List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffensesList, String juvenileNum){
		List temp = new ArrayList();
		int seq = 0;
		boolean noMatchFound = true;
		String offCode = UIConstants.EMPTY_STRING;
		
		//GetJuvenileCasefileOffensesEvent jcoEvent = new GetJuvenileCasefileOffensesEvent();
		//jcoEvent.setJuvenileNum( juvenileNum ); 
		//MessageUtil.postRequestListFilter(jcoEvent, JJSOffenseResponseEvent.class);
		//commented above for BUG 87254
		List<JJSOffenseResponseEvent> offenses = InterviewHelper.getOffensesForJuvenile(juvenileNum);//BUG 87254
		
		JuvenileCasefileTransferredOffenseResponseEvent jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
		for (int x=0; x<offenses.size(); x++)
		{
			JJSOffenseResponseEvent jjsOffenseRespEvent =  offenses.get(x);
			offCode = jjsOffenseRespEvent.getOffenseCodeId();
			//if ("TRNSIN".equals(offCode) || "ISCOIN".equals(offCode) || "TRNDSP".equals(offCode) || "TRNALL".equals(offCode) ) // added for user-story #32226
			if(jjsOffenseRespEvent!=null && jjsOffenseRespEvent.getSeveritySubtype().equals("T")) // added for user-story #32226
			{	
				noMatchFound = true;
				// if existing transferred offense referral records exist with matching
				// referral number, do not add to list of available offenses
				if (transferredOffensesList != null && !transferredOffensesList.isEmpty())
				{
					for (int t=0; t<transferredOffensesList.size(); t++)
					{
						JuvenileCasefileTransferredOffenseResponseEvent transferredOffRespEvt = (JuvenileCasefileTransferredOffenseResponseEvent) transferredOffensesList.get(t);
						if(jjsOffenseRespEvent!=null && transferredOffRespEvt!=null){
							if ((jjsOffenseRespEvent.getReferralNum()!=null && jjsOffenseRespEvent.getReferralNum().equals(transferredOffRespEvt.getReferralNum()))) { 
								noMatchFound = false;
								break;
							}/*else if((transferredOffRespEvt.getSeveritySubType()!=null && transferredOffRespEvt.getSeveritySubType().equals("T")) && (jjsOffenseRespEvent.getOffenseCode()!=null && jjsOffenseRespEvent.getOffenseCode().equals(transferredOffRespEvt.getOffenseCode()))) { 
								//added for user-story #32226
								noMatchFound = false;
								break;
							}*/ // bug fix added for transferred offenses issue on 6/28/2016 meeting #37956.
						}
					}
				}
				if (noMatchFound)
				{	
					jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
					jcEvent.setReferralNum(jjsOffenseRespEvent.getReferralNum());
					jcEvent.setOffenseCode(jjsOffenseRespEvent.getOffenseCodeId());
					jcEvent.setOffenseShortDesc(jjsOffenseRespEvent.getOffenseDescription());
					jcEvent.setSeveritySubType(jjsOffenseRespEvent.getSeveritySubtype()); // added for user-story #32226
					jcEvent.setAvailable("Y");
					jcEvent.setSeqNum(seq++);
					temp.add(jcEvent);
				}	
			}	
		}
		offCode = null;
		jcEvent = null;
		return sortOffenses(temp);
	}
	
	/**
	 * loadExistingTransferredOffenses
	 * @param juvenileNum
	 * @return
	 */
	public static List<JuvenileCasefileTransferredOffenseResponseEvent> loadExistingTransferredOffenses(String juvenileNum) {
		List<JuvenileCasefileTransferredOffenseResponseEvent> etos = new ArrayList<JuvenileCasefileTransferredOffenseResponseEvent>(); 
		GetTransferredOffenseReferralsEvent torEvent = new  GetTransferredOffenseReferralsEvent();
		torEvent.setJuvenileNum(juvenileNum);
		etos = MessageUtil.postRequestListFilter(torEvent, JuvenileCasefileTransferredOffenseResponseEvent.class);
		return addCountyDescription(sortOffenses(etos));
	}
	
	/**
	 * addCountyDescription
	 * @param tors
	 * @return List<JuvenileCasefileTransferredOffenseResponseEvent>
	 */
	public static List<JuvenileCasefileTransferredOffenseResponseEvent> addCountyDescription(List<JuvenileCasefileTransferredOffenseResponseEvent> tors) {
		String desc = UIConstants.EMPTY_STRING;
		for (int x=0; x<tors.size(); x++)
		{
			JuvenileCasefileTransferredOffenseResponseEvent jcrEvent = (JuvenileCasefileTransferredOffenseResponseEvent) tors.get(x);
			if (jcrEvent.getCountyId() != null)
			{
				desc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_COUNTY, jcrEvent.getCountyId()); //#33358 changes
				int idx = desc.indexOf(PDCodeTableConstants.COUNTY);
				if (idx > 2)
				{	
					desc = desc.substring(0, idx);
				}
				jcrEvent.setCountyDesc(desc.trim());
				jcrEvent.setSeqNum(x);
			}
		}
		desc = null;
		return tors;
	}
	
	/**
	 * sortOffenses
	 * @param offList
	 * @return
	 */
	public static List<JuvenileCasefileTransferredOffenseResponseEvent> sortOffenses(List offList) {
		if (offList.size() > 1) {
			SortedMap map = new TreeMap();
			// sort by referral and seq numbers
			for (int x = 0; x < offList.size(); x++) {
				JuvenileCasefileTransferredOffenseResponseEvent jctore = 
					(JuvenileCasefileTransferredOffenseResponseEvent) offList.get(x);
				map.put(jctore.getReferralNum() + jctore.getSeqNum(), jctore);
			}
			List temp = new ArrayList(map.values());
			offList = new ArrayList(temp);
		}
		return offList;
	}
}
