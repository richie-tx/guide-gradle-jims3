/*
 * Created on Nov 18, 2005
 *
 */
package messaging.supervisionorder.reply;

import messaging.contact.party.reply.PartyListResponseEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *
 */
public class SuperviseeListResponseEvent extends PartyListResponseEvent
{
	private String courtNum;
	private Name officerName;
	private String supervisionPeriodId;
	
	public SuperviseeListResponseEvent(){}
	
	public SuperviseeListResponseEvent(PartyListResponseEvent plre)
	{
		this.setDateOfBirth(plre.getDateOfBirth());
		this.setSpn(plre.getSpn());
		this.setFirstName(plre.getFirstName());
		this.setMiddleName(plre.getMiddleName());
		this.setLastName(plre.getLastName());
		this.setRaceId(plre.getRaceId());
		this.setSexId(plre.getSexId());
		this.setJailInd(plre.getJailInd());
		this.setName(plre.getLastName()+", "+plre.getFirstName()+" "+plre.getMiddleName());
	}
	public String getCasenotesId(){
		StringBuffer sb = new StringBuffer(this.getSpn());
		sb.append("||");
		sb.append(this.getSupervisionPeriodId());
		return sb.toString();
	}

	/**
	 * @return
	 */
	public String getCourtNum()
	{
		return courtNum;
	}

	/**
	 * @return
	 */
	public Name getOfficerName()
	{
		return officerName;
	}
	
	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	/**
	 * @param aCourtNum
	 */
	public void setCourtNum(String aCourtNum)
	{
		courtNum = aCourtNum;
	}
	
	/**
	 * @param aName
	 */
	public void setOfficerName(Name aName)
	{
		officerName = aName;
	}
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
}
