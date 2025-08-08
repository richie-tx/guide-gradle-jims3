package pd.juvenile;

import java.util.Iterator;

import pd.codetable.Code;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @stereotype entity
 * @author dnikolis
 */
public class JuvenileCharterPostReleaseContinuingEducation extends PersistentObject
{
	/**
	 * @contextKey POST_RELEASE_CONTINUING_ED
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 */
	private Code continuingEducationCode = null;
	private String juvenileCharterPostReleaseTrackingId;
	private String continuingEducationCodeId;

	public static Iterator findAll(IEvent event){
		IHome home = new Home();
		return home.findAll(event, JuvenileCharterPostReleaseContinuingEducation.class);
	}

	public String getJuvenileCharterPostReleaseTrackingId()
	{
		fetch();
		return juvenileCharterPostReleaseTrackingId;
	}

	public void setJuvenileCharterPostReleaseTrackingId(String juvenileCharterPostReleaseTrackingId)
	{
		if (this.juvenileCharterPostReleaseTrackingId == null
				|| !this.juvenileCharterPostReleaseTrackingId.equals(juvenileCharterPostReleaseTrackingId))
		{
			markModified();
		}
		this.juvenileCharterPostReleaseTrackingId = juvenileCharterPostReleaseTrackingId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setContinuingEducationCodeId(String continuingEducationCodeId)
	{
		if (this.continuingEducationCodeId == null || !this.continuingEducationCodeId.equals(continuingEducationCodeId))
		{
			markModified();
		}
		continuingEducationCode = null;
		this.continuingEducationCodeId = continuingEducationCodeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getContinuingEducationCodeId()
	{
		fetch();
		return continuingEducationCodeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initContinuingEducationCode()
	{
		if (continuingEducationCode == null)
		{
			continuingEducationCode = (Code) new mojo.km.persistence.Reference(continuingEducationCodeId,
					Code.class, "POST_RELEASE_CONTINUING_ED").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getContinuingEducationCode()
	{
		initContinuingEducationCode();
		return continuingEducationCode;
	}

	/**
	 * set the type reference for class member continuingEducationCode
	 */
	public void setContinuingEducationCode(Code continuingEducationCode)
	{
		if (this.continuingEducationCode == null || !this.continuingEducationCode.equals(continuingEducationCode))
		{
			markModified();
		}
		setContinuingEducationCodeId("" + continuingEducationCode.getOID());
		continuingEducationCode.setContext("POST_RELEASE_CONTINUING_ED");
		this.continuingEducationCode = (Code) new mojo.km.persistence.Reference(continuingEducationCode)
				.getObject();
	}
}
