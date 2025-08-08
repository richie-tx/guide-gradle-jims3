package pd.supervision.administercaseload;



import java.util.Iterator;



import pd.codetable.criminal.OffenseCode;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class DisposedCase extends PersistentObject {

	private String criminalCaseId;
	private String defendantId;

	private String offenseCd;

	private String offenseDate;

	private String cdi;
	
	private String dispositionCd;
	

	private OffenseCode offense = null;

	
	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

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
	
	public String getOffenseDate() {
		fetch();
		return offenseDate;
	}

	public void setOffenseDate(String offenseDate) {
		if (this.offenseDate == null || !this.offenseDate.equals(offenseDate)) {
			markModified();
		}
		this.offenseDate = offenseDate;
	}

	public String getOffenseCd() {
		fetch();
		return offenseCd;
	}

	public void setOffenseCd(String offenseCd) {
		if (this.offenseCd == null || !this.offenseCd.equals(offenseCd)) {
			markModified();
		}		
		this.offenseCd = offenseCd;
	}
	
	public String getCdi() {
		fetch();
		return cdi;
	}

	public void setCdi(String cdi) {
		if (this.cdi == null || !this.cdi.equals(cdi)) {
			markModified();
		}
		this.cdi = cdi;
	}
	
	public String getDispositionCd() {
		fetch();
		return dispositionCd;
	}

	public void setDispositionCd(String dispositionCd) {
		if (this.dispositionCd  == null || !this.dispositionCd .equals(dispositionCd)) {
			markModified();
		}
		this.dispositionCd = dispositionCd;
	}
	
	
	public OffenseCode getOffense()
	{
		fetch();
		initOffense();
		return offense;
	}

	private void initOffense()
	{
		if (offense == null)
		{
			offense = (OffenseCode) new mojo.km.persistence.Reference(offenseCd,
					OffenseCode.class).getObject();
		}
	}


	static public Iterator findAll(IEvent event) {
		IHome home = new Home();		
		Iterator iter = home.findAll(event, DisposedCase.class);
		return iter;
	}

}
