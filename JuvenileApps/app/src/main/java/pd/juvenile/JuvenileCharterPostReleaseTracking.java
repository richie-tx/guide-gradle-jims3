package pd.juvenile;

import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.reply.CharterPostReleaseResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class JuvenileCharterPostReleaseTracking extends PersistentObject
{
	private Date statusDate;
	/**
	 * 
	 * @contextKey POST_RELEASE_EMPLOYED
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 */
	private Code employedCode = null;
	/**
	 * 
	 * @referencedType pd.juvenile.JuvenileCharterPostReleaseContinuingEducation
	 * @detailerDoNotGenerate false
	 */
	private Collection continuingEducationCodes;
	private String juvenileNum;
	private String comments;
	private String employedCodeId;

	public static Iterator findAll(IEvent event){
		IHome home = new Home();
		return home.findAll(event, JuvenileCharterPostReleaseTracking.class);
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator charters = home.findAll(attrName, attrValue, JuvenileCharterPostReleaseTracking.class);
		return charters;
	}

	static public JuvenileCharterPostReleaseTracking find(String oid)
	{
		IHome home = new Home();
		JuvenileCharterPostReleaseTracking juvenileCharterPostReleaseTracking = (JuvenileCharterPostReleaseTracking) home.find(oid, JuvenileCharterPostReleaseTracking.class);
		return juvenileCharterPostReleaseTracking;
	}

	public Date getStatusDate()
	{
		fetch();
		return statusDate;
	}

	public void setStatusDate(Date statusDate)
	{
		if (this.statusDate == null || !this.statusDate.equals(statusDate))
		{
			markModified();
		}
		this.statusDate = statusDate;
	}

	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	public String getComments()
	{
		fetch();
		return comments;
	}

	public void setComments(String comments)
	{
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setEmployedCodeId(String employedCodeId)
	{
		if (this.employedCodeId == null || !this.employedCodeId.equals(employedCodeId))
		{
			markModified();
		}
		employedCode = null;
		this.employedCodeId = employedCodeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getEmployedCodeId()
	{
		fetch();
		return employedCodeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEmployedCode()
	{
		if (employedCode == null)
		{
			employedCode = (Code) new mojo.km.persistence.Reference(employedCodeId,
					Code.class, "POST_RELEASE_EMPLOYED").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getEmployedCode()
	{
		initEmployedCode();
		return employedCode;
	}

	/**
	 * set the type reference for class member employedCode
	 */
	public void setEmployedCode(Code employedCode)
	{
		if (this.employedCode == null || !this.employedCode.equals(employedCode))
		{
			markModified();
		}
		setEmployedCodeId("" + employedCode.getOID());
		employedCode.setContext("POST_RELEASE_EMPLOYED");
		this.employedCode = (Code) new mojo.km.persistence.Reference(employedCode).getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenile.JuvenileCharterPostReleaseContinuingEducation
	 */
	private void initContinuingEducationCodes()
	{
		if (continuingEducationCodes == null)
		{
			continuingEducationCodes = new mojo.km.persistence.ArrayList(
					JuvenileCharterPostReleaseContinuingEducation.class,
					"juvenileCharterPostReleaseTrackingId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenile.JuvenileCharterPostReleaseContinuingEducation
	 */
	public Collection getContinuingEducationCodes()
	{
		initContinuingEducationCodes();
		return continuingEducationCodes;
	}

	/**
	 * insert a pd.juvenile.JuvenileCharterPostReleaseContinuingEducation into class relationship collection.
	 */
	public void insertContinuingEducationCodes(JuvenileCharterPostReleaseContinuingEducation anObject)
	{
		initContinuingEducationCodes();
		continuingEducationCodes.add(anObject);
	}

	/**
	 * Removes a pd.juvenile.JuvenileCharterPostReleaseContinuingEducation from class relationship collection.
	 */
	public void removeContinuingEducationCodes(JuvenileCharterPostReleaseContinuingEducation anObject)
	{
		initContinuingEducationCodes();
		continuingEducationCodes.remove(anObject);
	}

	/**
	 * Clears all pd.juvenile.JuvenileCharterPostReleaseContinuingEducation from class relationship collection.
	 */
	public void clearContinuingEducationCodes()
	{
		initContinuingEducationCodes();
		continuingEducationCodes.clear();
	}
	
	public CharterPostReleaseResponseEvent getResponseEvent(){
		CharterPostReleaseResponseEvent myRespEvt=new CharterPostReleaseResponseEvent();
		myRespEvt.setCharterPostRelId(this.getOID());
		myRespEvt.setJuvenileNum(this.getJuvenileNum());
		myRespEvt.setComments(this.getComments());
		myRespEvt.setEmployedCodeId(this.getEmployedCodeId());
		myRespEvt.setEmployedCodeDesc(PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.POSTRELEASEEMPLOYED, false), this.getEmployedCodeId()));
		myRespEvt.setStatusDate(this.getStatusDate());
		List juvenileCharterCEs = new ArrayList();
		Iterator charterCEs = this.getContinuingEducationCodes().iterator();
		while (charterCEs.hasNext())
		{
			JuvenileCharterPostReleaseContinuingEducation charterCE = (JuvenileCharterPostReleaseContinuingEducation) charterCEs.next();
			CodeResponseEvent ceRespEvt = new CodeResponseEvent();
			ceRespEvt.setCode(charterCE.getContinuingEducationCodeId());
			ceRespEvt.setDescription((PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.POSTRELEASECONTINUINGED, false), charterCE.getContinuingEducationCodeId())));
			juvenileCharterCEs.add(ceRespEvt);
		}
		myRespEvt.setContinuingEducationCodes(juvenileCharterCEs);
		return myRespEvt;
	}
}
