package pd.juvenilecase.casefile;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;

/**
 * @roseuid 4521319D004C
 */
public class GoalJournal extends PersistentObject {

	private String description;

	private Date entryDate;

	private String endRecommendation;

	private String casegoalId;

	private String caseplanId;

	private String progressNotes;

	/**
	 * @roseuid 4521319D004C
	 */
	public GoalJournal() {
	}

	public String getCasegoalId() {
		fetch();
		return casegoalId;
	}

	public void setCasegoalId(String casegoalId) {
		this.casegoalId = casegoalId;
	}

	public String getCaseplanId() {
		fetch();
		return caseplanId;
	}

	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}

	public String getDescription() {
		fetch();
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndRecommendation() {
		fetch();
		return endRecommendation;
	}

	public void setEndRecommendation(String endRecommendation) {
		this.endRecommendation = endRecommendation;
	}

	public Date getEntryDate() {
		fetch();
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, GoalJournal.class);
	}

	public String getProgressNotes() {
		fetch();
		return progressNotes;
	}

	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}

}
