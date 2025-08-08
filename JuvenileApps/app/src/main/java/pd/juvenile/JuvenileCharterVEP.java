package pd.juvenile;

import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import messaging.juvenile.reply.CharterVEPResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;

import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class JuvenileCharterVEP extends PersistentObject
{
	private Date startDate;
	private boolean completed;
	private Date completionDate;
	/**
	 * 
	 * @contextKey VEP_PROGRAM
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 */
	private Code programCode = null;
	private String juvenileNum;
	private String programCodeId;

	static public JuvenileCharterVEP find(String oid)
	{
		IHome home = new Home();
		JuvenileCharterVEP juvenileCharterVEP = (JuvenileCharterVEP) home.find(oid, JuvenileCharterVEP.class);
		return juvenileCharterVEP;
	}

	public static Iterator findAll(IEvent event){
		IHome home = new Home();
		return home.findAll(event, JuvenileCharterVEP.class);
	}

	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator charters = home.findAll(attrName, attrValue, JuvenileCharterVEP.class);
		return charters;
	}
	
	public Date getStartDate()
	{
		fetch();
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		if (this.startDate == null || !this.startDate.equals(startDate))
		{
			markModified();
		}
		this.startDate = startDate;
	}

	public boolean isCompleted()
	{
		fetch();
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		if (this.completed != completed)
		{
			markModified();
		}
		this.completed = completed;
	}

	public Date getCompletionDate()
	{
		fetch();
		return completionDate;
	}

	public void setCompletionDate(Date completionDate)
	{
		if (this.completionDate == null || !this.completionDate.equals(completionDate))
		{
			markModified();
		}
		this.completionDate = completionDate;
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

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setProgramCodeId(String programCodeId)
	{
		if (this.programCodeId == null || !this.programCodeId.equals(programCodeId))
		{
			markModified();
		}
		programCode = null;
		this.programCodeId = programCodeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getProgramCodeId()
	{
		fetch();
		return programCodeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initProgramCode()
	{
		if (programCode == null)
		{
			programCode = (Code) new mojo.km.persistence.Reference(programCodeId, Code.class,
					"VEP_PROGRAM").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getProgramCode()
	{
		initProgramCode();
		return programCode;
	}

	/**
	 * set the type reference for class member programCode
	 */
	public void setProgramCode(Code programCode)
	{
		if (this.programCode == null || !this.programCode.equals(programCode))
		{
			markModified();
		}
		setProgramCodeId("" + programCode.getOID());
		programCode.setContext("VEP_PROGRAM");
		this.programCode = (Code) new mojo.km.persistence.Reference(programCode).getObject();
	}
	
	public CharterVEPResponseEvent getResponseEvent(){
		CharterVEPResponseEvent myRespEvt=new CharterVEPResponseEvent();
		myRespEvt.setJuvenileNum(this.getJuvenileNum());
		myRespEvt.setCompleted(this.isCompleted());
		myRespEvt.setCompletionDate(this.getCompletionDate());
		myRespEvt.setJuvenileCharterVEPId(this.getOID());
		myRespEvt.setProgramCodeId(this.getProgramCodeId());
		myRespEvt.setProgramName(PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.VEPPROGRAM, false), this.getProgramCodeId()));
		myRespEvt.setStartDate(this.getStartDate());
		return myRespEvt;
	}
}
