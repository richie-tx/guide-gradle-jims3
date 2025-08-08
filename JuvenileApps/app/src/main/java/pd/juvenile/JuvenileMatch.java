/*
 * Created on May 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenile;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileMatch extends mojo.km.persistence.PersistentObject
{
	private String juvMatchId;
	private String juvA;
	private String juvB;
	private String status;
	private String notes;
	private Date entryDate;
	private Date updateDate;
	
	public JuvenileMatch() {
	}
	/**
	* @roseuid 431F1802007D
	*/
	public void bind() {
		markModified();
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileMatch.class);
	}
	
	public String getJuvMatchId()
		{
			return "" + getOID();
		}
		
	public void setJuvMatchId(String aJuvilyMemberMatchId)
		{
			if (this.juvMatchId == null || !this.juvMatchId.equals(aJuvilyMemberMatchId)) {
				markModified();
			}
			juvMatchId = aJuvilyMemberMatchId;
		}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.Juvily.JuvilyConstellation
	* @param JuvilyConstellationId The Juvily constellation id.
	*/
	public void setJuvA(String aMemberA) {
		if (this.juvA == null || !this.juvA.equals(aMemberA)) {
			markModified();
		}
		this.juvA = aMemberA;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.Juvily.JuvilyConstellation
	* @return The Juvily constellation id.
	*/
	public String getJuvA() {
		fetch();
		return juvA;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.Juvily.JuvilyConstellation
	* @param JuvilyConstellationId The Juvily constellation id.
	*/
	public void setJuvB(String aMemberB) {
		if (this.juvB == null || !this.juvB.equals(aMemberB)) {
			markModified();
		}
		this.juvB = aMemberB;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.Juvily.JuvilyConstellation
	* @return The Juvily constellation id.
	*/
	public String getJuvB() {
		fetch();
		return juvB;
	}
	
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatus(String status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
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
	* Finds all Juvily traits by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator juvMatches = home.findAll(attributeName, attributeValue, JuvenileMatch.class);
		return juvMatches;
	}
	/**
	* @roseuid 431F18020070
	* @param traitNum The traitNum num.
	*/
	static public JuvenileMatch find(String aJuvMatchValId) {
		IHome home = new Home();
		JuvenileMatch juvMatch = (JuvenileMatch) home.find(aJuvMatchValId, JuvenileMatch.class);
		return juvMatch;
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
		
		
}
