/*
 * Created on May 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberMatch extends mojo.km.persistence.PersistentObject
{
	private String familyMemberMatchId;
	private String memberA;
	private String memberB;
	private String status;
	private String notes;
	private Date entryDate;
	private Date updateDate;
	private String matchType;//added for US 181437
	
	public FamilyMemberMatch() {
	}
	/**
	* @roseuid 431F1802007D
	*/
	public void bind() {
		markModified();
	}
	
	public String getFamilyMemberMatchId()
		{
			return "" + getOID();
		}
		
	public void setFamilyMemberMatchId(String aFamilyMemberMatchId)
		{
			if (this.familyMemberMatchId == null || !this.familyMemberMatchId.equals(aFamilyMemberMatchId))	{
				markModified();
			}
			familyMemberMatchId = aFamilyMemberMatchId;
		}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @param familyConstellationId The family constellation id.
	*/
	public void setMemberA(String aMemberA) {
		if (this.memberA == null || !this.memberA.equals(aMemberA)) {
			markModified();
		}
		this.memberA = aMemberA;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @return The family constellation id.
	*/
	public String getMemberA() {
		fetch();
		return memberA;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @param familyConstellationId The family constellation id.
	*/
	public void setMemberB(String aMemberB) {
		if (this.memberB == null || !this.memberB.equals(aMemberB)) {
			markModified();
		}
		this.memberB = aMemberB;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @return The family constellation id.
	*/
	public String getMemberB() {
		fetch();
		return memberB;
	}
	
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatus(String status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		//status = null;
		this.status = status;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatus() {
		fetch();
		return status;
	}

	/**
	* Finds all family traits by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator familyMemberMatches = home.findAll(attributeName, attributeValue, FamilyMemberMatch.class);
		return familyMemberMatches;
	}
	/**
	* @roseuid 431F18020070
	* @param traitNum The traitNum num.
	*/
	static public FamilyMemberMatch find(String aFamMatchValId) {
		IHome home = new Home();
		FamilyMemberMatch famMemberMatch = (FamilyMemberMatch) home.find(aFamMatchValId, FamilyMemberMatch.class);
		return famMemberMatch;
	}
	/**
	* @return 
	*/
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	
	/**
	* @param date
	*/
	public void setEntryDate(Date date) {
		if (this.entryDate == null || !this.entryDate.equals(date)) {
			markModified();
		}
		entryDate = date;
	}
	
	/**
		* @return 
		*/
		public String getNotes() {
			fetch();
			return notes;
		}

		/**
		* @param string
		*/
		public void setNotes(String string) {
			if (this.notes == null || !this.notes.equals(string)) {
				markModified();
			}
			notes = string;
		}
	/**
		* @return 
		*/
		public Date getUpdateDate() {
			fetch();
			return updateDate;
		}
	
		/**
		* @param date
		*/
		public void setUpdateDate(Date date) {
			if (this.updateDate == null || !this.updateDate.equals(date)) {
				markModified();
			}
			updateDate = date;
		}
		
		public static List findAll(IEvent anEvent){
			IHome home = new Home();

			return CollectionUtil.iteratorToList(home.findAll(anEvent, FamilyMemberMatch.class));
			
		}
		public String getMatchType()
		{
		    return matchType;
		}
		public void setMatchType(String matchType)
		{
		    this.matchType = matchType;
		}
}
