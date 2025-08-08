package pd.supervision.supervisionorder;

import java.util.Iterator;
import messaging.supervisionorder.GenerateSpecialConditionNameEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 44184CAE03B9
*/
public class CourtSequenceGenerator extends PersistentObject {
	private String courtId;
	private String agencyId;
	private String type;
	private int sequenceNum;
	
	/**
	* @roseuid 44184CAE03B9
	*/
	public CourtSequenceGenerator() {
	}
	/**
	* PRECONDITION:
	* Properties agentId and groupId must be set with correct values.
	* @return Iterator
	* @param event
	*/
	static public CourtSequenceGenerator find(IEvent event) {
		IHome home = new Home();
		Iterator iter = home.findAll(event, CourtSequenceGenerator.class);
		if (iter.hasNext()) {
			return (CourtSequenceGenerator) iter.next();
		} else {
			GenerateSpecialConditionNameEvent genEvt = (GenerateSpecialConditionNameEvent) event;
			CourtSequenceGenerator gen = new CourtSequenceGenerator();
			gen.agencyId = genEvt.getAgencyId();
			gen.courtId = genEvt.getCourtId();
			gen.setType(genEvt.getType());
			gen.sequenceNum = 1;
			return gen;
		}
	}
	/**
	*/
	public int nextSequence() {
		int tmp = sequenceNum;
		setSequenceNum(sequenceNum + 1);
		return tmp;
	}
	/**
	*/
	public String nextSequenceAsString(int maxPaddedLength) {
		int seq = nextSequence();
		String str = Integer.toString(seq);
		StringBuffer sb = new StringBuffer();
		while(sb.length() + str.length() < maxPaddedLength) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}
	/**
	* Access method for the sequenceNum property.
	* @return the current value of the sequenceNum property
	*/
	public int getSequenceNum() {
		fetch();
		return sequenceNum;
	}
	/**
	* Sets the value of the sequenceNum property.
	* @param aSequenceNum the new value of the sequenceNum property
	*/
	public void setSequenceNum(int aSequenceNum) {
		if (this.sequenceNum != aSequenceNum) {
			markModified();
		}
		sequenceNum = aSequenceNum;
	}
	/**
	* Access method for the agencyId property.
	* @return the current value of the agencyId property
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Sets the value of the agencyId property.
	* @param aAgencyId the new value of the agencyId property
	*/
	public void setAgencyId(String aAgencyId) {
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId)) {
			markModified();
		}
		agencyId = aAgencyId;
	}
	/**
	* Access method for the courtId property.
	* @return the current value of the courtId property
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	* Sets the value of the courtId property.
	* @param aCourtId the new value of the courtId property
	*/
	public void setCourtId(String aCourtId) {
		if (this.courtId == null || !this.courtId.equals(aCourtId)) {
			markModified();
		}
		courtId = aCourtId;
	}
	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

}
