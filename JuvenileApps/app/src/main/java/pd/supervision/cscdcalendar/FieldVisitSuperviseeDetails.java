package pd.supervision.cscdcalendar;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class FieldVisitSuperviseeDetails extends PersistentObject {

	private String defendantId;

	private String addressDesc;

	private String caution;

	private String comments;

	public String getDefendantId() {
		fetch();
		return defendantId;
	}

	public void setDefendantId(String defendantId) {

		if (this.defendantId == null || !this.defendantId.equals(defendantId)) {
			markModified();
		}
		this.defendantId = defendantId;
	}

	public String getAddressDesc() {
		fetch();
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {

		if (this.addressDesc == null || !this.addressDesc.equals(addressDesc)) {
			markModified();
		}
		this.addressDesc = addressDesc;
	}

	public String getCaution() {
		fetch();
		return caution;
	}

	public void setCaution(String caution) {
		if (this.caution == null || !this.caution.equals(caution)) {
			markModified();
		}
		this.caution = caution;
	}

	public String getComments() {
		fetch();
		return comments;
	}

	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments)) {
			markModified();
		}
		this.comments = comments;
	}

	static public Iterator findAll(IEvent event) {
		IHome home = new Home();		
		Iterator iter = home.findAll(event, FieldVisitSuperviseeDetails.class);
		return iter;
	}

}
