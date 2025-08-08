
/*
 * Created on May 15, 2014
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;
import java.util.Comparator;

public class JuvenileAdmitReasonsResponseEvent extends ResponseEvent implements Comparable, ICode
{
	
	
	private String code;
	private String description;
	private String juvenileAdmitReasonsId;
	private String genDetHearingChain;
	private String probCauseHearingDays; 
	private String detentionType;	
	private String inactiveInd;
	private String descriptionWithCode;
	
	//added for facility admit
	private String codeWithDetType;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the juvenileAdmitReasonsId
	 */
	public String getJuvenileAdmitReasonsId() {
		return juvenileAdmitReasonsId;
	}



	/**
	 * @param juvenileAdmitReasonsId the juvenileAdmitReasonsId to set
	 */
	public void setJuvenileAdmitReasonsId(String juvenileAdmitReasonsId) {
		this.juvenileAdmitReasonsId = juvenileAdmitReasonsId;
	}



	/**
	 * @return the genDetHearingChain
	 */
	public String getGenDetHearingChain() {
		return genDetHearingChain;
	}



	/**
	 * @param genDetHearingChain the genDetHearingChain to set
	 */
	public void setGenDetHearingChain(String genDetHearingChain) {
		this.genDetHearingChain = genDetHearingChain;
	}



	/**
	 * @return the probCauseHearingDays
	 */
	public String getProbCauseHearingDays() {
		return probCauseHearingDays;
	}



	/**
	 * @param probCauseHearingDays the probCauseHearingDays to set
	 */
	public void setProbCauseHearingDays(String probCauseHearingDays) {
		this.probCauseHearingDays = probCauseHearingDays;
	}



	/**
	 * @return the detentionType
	 */
	public String getDetentionType() {
		return detentionType;
	}



	/**
	 * @param detentionType the detentionType to set
	 */
	public void setDetentionType(String detentionType) {
		this.detentionType = detentionType;
	}



	/**
	 * @return the inactiveInd
	 */
	public String getInactiveInd() {
		return inactiveInd;
	}



	/**
	 * @param inactiveInd the inactiveInd to set
	 */
	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}



	/**
	 * @return the descriptionWithCode
	 */
	public String getDescriptionWithCode() {
		return descriptionWithCode;
	}



	/**
	 * @param descriptionWithCode the descriptionWithCode to set
	 */
	public void setDescriptionWithCode(String descriptionWithCode) {
		this.descriptionWithCode = descriptionWithCode;
	}

	public void setCodeWithDetType(String codeWithDetType) {
		this.codeWithDetType = codeWithDetType;
	}



	public String getCodeWithDetType() {
		return codeWithDetType;
	}



	public int compareTo(Object obj) throws ClassCastException {
		JuvenileFacilityResponseEvent evt = (JuvenileFacilityResponseEvent)obj;
		return description.compareTo(evt.getDescription());
	}

	public static Comparator CodeComparator = new Comparator(){
		public int compare(Object obj1, Object obj2){
			
			if(obj1==null || !(obj1 instanceof JuvenileAdmitReasonsResponseEvent))
				return 0;
			if(obj2==null || !(obj2 instanceof JuvenileAdmitReasonsResponseEvent))
				return 0;
			
			String code1= ((JuvenileAdmitReasonsResponseEvent)obj1).getDescription();
			String code2= ((JuvenileAdmitReasonsResponseEvent)obj2).getDescription();
			if(code1==null) return 1;
			if(code2==null) return -1;
			
			return code1.compareTo(code2);
			
		}
	};
	
}
