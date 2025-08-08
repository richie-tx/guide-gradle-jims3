package pd.juvenilecase.casefile;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;

/**
 * @roseuid 4521319D004C
 */
public class TraitJournal extends PersistentObject 
{
 
    private String traitsId;

    private String comments;

    private String statusId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey FAMILY_TRAIT_STATUS
	*/
	private Code status = null;
    
    private String traitName;
    
    private Date createdDate;

    /**
     * @roseuid 4521319D004C
     */
    public TraitJournal()
    {
    }



	public String getComments() {
		 fetch();
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getTraitName() {
		 fetch();
		return traitName;
	}



	public void setTraitName(String traitName) {
		this.traitName = traitName;
	}



	public String getTraitsId() {
		 fetch();
		return traitsId;
	}



	public void setTraitsId(String traitsId) {
		this.traitsId = traitsId;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, PDCodeTableConstants.FAMILY_TRAIT_STATUS).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		setStatusId("" + status.getOID());
		status.setContext(PDCodeTableConstants.FAMILY_TRAIT_STATUS);
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, TraitJournal.class);
    }



	public Date getCreatedDate() {
		fetch();
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
  
}
