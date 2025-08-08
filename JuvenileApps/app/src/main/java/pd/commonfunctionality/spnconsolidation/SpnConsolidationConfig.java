package pd.commonfunctionality.spnconsolidation;

import java.util.Comparator;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 452BA26200F1
 */
public class SpnConsolidationConfig extends PersistentObject{
	private int sequenceNum;

	private String handler;

	/**
	 * @roseuid 452BA26200F1
	 */
	public SpnConsolidationConfig() {
	}

	/**
	 * @roseuid 4526B08301B7
	 * @methodInvocation fetch
	 */

	static public Iterator findAll() {
		IHome home = new Home();
		Iterator iter = home.findAll(SpnConsolidationConfig.class);
		return iter;
	}

	/**
	 * @return Returns the handler.
	 */
	public String getHandler() {
		fetch();
		return handler;
	}

	/**
	 * @param handler
	 *            The handler to set.
	 */
	public void setHandler(String handler) {
		if (this.handler == null || !this.handler.equals(handler)) {
			markModified();
		}
		this.handler = handler;
	}

	/**
	 * @return Returns the sequenceNum.
	 */
	public int getSequenceNum() {
		fetch();
		return sequenceNum;
	}

	/**
	 * @param sequenceNum
	 *            The sequenceNum to set.
	 */
	public void setSequenceNum(int sequenceNum) {
		if (this.sequenceNum != sequenceNum) {
			markModified();
		}
		this.sequenceNum = sequenceNum;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public static Comparator SeqNumComparator = new Comparator() {
		public int compare(Object suggestedOrderCondition, Object otherSuggestedOrderCondition) {
		  int seqNum = ((SpnConsolidationConfig)suggestedOrderCondition).getSequenceNum();
		  int otherSeqNum = ((SpnConsolidationConfig)otherSuggestedOrderCondition).getSequenceNum();
		  
		  Integer seqNumInt = new Integer(seqNum);
		  Integer otherSeqNumInt = new Integer(otherSeqNum);
		  return seqNumInt.compareTo(otherSeqNumInt);
		}	
	};
}
