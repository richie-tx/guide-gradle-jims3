package pd.juvenilewarrant;

import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileActivityCode;
import java.util.Date;
import java.util.Iterator;

/**
 * @roseuid 467FBB560281
 */
public class JuvenileOffenderTrackingDisposition extends PersistentObject
{
	private String assignedSanction;
	
	private int dispositionDate;
	private String guidelineSanction;
	private int judgementDate;
	private String probationInd;
	private String probationTime;
	private String tYCInd;
	private String tYCTime;
	private JuvenileOffenderTrackingDisposition[] dispositions;
	
	private Code deviationReason;
	private String deviationReasonId;
	
	/**
	 * Properties for needSupervision
	 */
	private JuvenileActivityCode needSupervision = null;
	/**
	 * Properties for tycWithheld
	 */
	private JuvenileActivityCode tycWithheld = null;
	/**
	 * Properties for placement
	 */
	private JuvenileActivityCode placement = null;
	/**
	 * Properties for judgement
	 */
	private JuvenileActivityCode judgement = null;
	private String needSupervisionId;
	private String tycWithheldId;
	private String placementId;
	private String judgementId;
	/**
	 * Properties for Assigned Referrals
	 */
	private String delConduct;
	private String petitionNum;
	private String seqNum;
	private String juvenileNum;
	private String daLogNum;

	/**
	 * @roseuid 467FBB560281
	 */
	public JuvenileOffenderTrackingDisposition()
	{
	}

	/**
	 * @return Returns the assignedSanction.
	 */
	public String getAssignedSanction()
	{
		fetch();
		return assignedSanction;
	}

	/**
	 * @param assignedSanction The assignedSanction to set.
	 */
	public void setAssignedSanction(String assignedSanction)
	{
		if (this.assignedSanction == null || !this.assignedSanction.equals(assignedSanction))
		{
			markModified();
		}
		this.assignedSanction = assignedSanction;
	}

	/**
	 * @return Returns the deviationReason.
	 */
	public String getDeviationReasonId()
	{
		fetch();
		return deviationReasonId;
	}

	/**
	 * @param deviationReason The deviationReason to set.
	 */
	public void setDeviationReasonId(String adeviationReasonId)
	{
		if (this.deviationReasonId == null || !this.deviationReasonId.equals(adeviationReasonId))
		{
			markModified();
		}
		deviationReason=null;
		this.deviationReasonId = adeviationReasonId;
	}

	 /**
     *  
     */
    public void initDeviationReason()
    {
        if (deviationReason == null && this.deviationReasonId !=null)
        {
        	deviationReason = (Code) new mojo.km.persistence.Reference(this.deviationReasonId,
                    Code.class, "SANCTION_DEVIATION").getObject();
        }
    }

    /**
     * @return Returns the deviationReason.
     */
    public Code getDeviationReason()
    {
        initDeviationReason();
        return deviationReason;
    }
    
    /**
     * @param deviationReasonId
     * The deviationReasonId to set.
     */
    public void setDeviationReason(Code aDeviationReason)
    {
    	setDeviationReasonId(aDeviationReason.getCode());
    }
	/**
	 * @return Returns the dispositionDate.
	 */
	public int getDispositionDate()
	{
		fetch();
		return dispositionDate;
	}

	/**
	 * @param dispositionDate The dispositionDate to set.
	 */
	public void setDispositionDate(int dispositionDate)
	{
		if (this.dispositionDate != 0 || this.dispositionDate != dispositionDate)
		{
			markModified();
		}
		this.dispositionDate = dispositionDate;
	}

	/**
	 * @return Returns the dispositions.
	 */
	public JuvenileOffenderTrackingDisposition[] getDispositions()
	{
		fetch();
		return dispositions;
	}

	/**
	 * @param dispositions The dispositions to set.
	 */
	public void setDispositions(JuvenileOffenderTrackingDisposition[] dispositions)
	{
		if (this.dispositions == null || !this.dispositions.equals(dispositions))
		{
			markModified();
		}
		this.dispositions = dispositions;
	}

	/**
	 * @return Returns the guidelineSanction.
	 */
	public String getGuidelineSanction()
	{
		fetch();
		return guidelineSanction;
	}

	/**
	 * @param guidelineSanction The guidelineSanction to set.
	 */
	public void setGuidelineSanction(String guidelineSanction)
	{
		if (this.guidelineSanction == null || !this.guidelineSanction.equals(guidelineSanction))
		{
			markModified();
		}
		this.guidelineSanction = guidelineSanction;
	}

	/**
	 * @return Returns the judgement.
	 */
	public JuvenileActivityCode getJudgement()
	{
		fetch();
		initJudgement();
		return judgement;
	}

	/**
	 * @return Returns the judgementDate.
	 */
	public int getJudgementDate()
	{
		fetch();
		return judgementDate;
	}

	/**
	 * @param judgementDate The judgementDate to set.
	 */
	public void setJudgementDate(int judgementDate)
	{
		if (this.judgementDate > 0 || this.judgementDate != judgementDate )
		{
			markModified();
		}
		this.judgementDate = judgementDate;
	}

	/**
	 * @return Returns the needSupervision.
	 */
	public JuvenileActivityCode getNeedSupervision()
	{
		fetch();
		initNeedSupervision();
		return needSupervision;
	}

	/**
	 * @return Returns the placement.
	 */
	public JuvenileActivityCode getPlacement()
	{
		fetch();
		initPlacement();
		return placement;
	}

	/**
	 * @return Returns the probationInd.
	 */
	public String getProbationInd()
	{
		fetch();
		return probationInd;
	}

	/**
	 * @param probationInd The probationInd to set.
	 */
	public void setProbationInd(String probationInd)
	{
		if (this.probationInd == null || !this.probationInd.equals(probationInd))
		{
			markModified();
		}
		this.probationInd = probationInd;
	}

	/**
	 * @return Returns the probationTime.
	 */
	public String getProbationTime()
	{
		fetch();
		return probationTime;
	}

	/**
	 * @param probationTime The probationTime to set.
	 */
	public void setProbationTime(String probationTime)
	{
		if (this.probationTime == null || !this.probationTime.equals(probationTime))
		{
			markModified();
		}
		this.probationTime = probationTime;
	}

	/**
	 * @return Returns the tYCInd.
	 */
	public String getTYCInd()
	{
		fetch();
		return tYCInd;
	}

	/**
	 * @param ind The tYCInd to set.
	 */
	public void setTYCInd(String ind)
	{
		if (this.tYCInd == null || !this.tYCInd.equals(ind))
		{
			markModified();
		}
		tYCInd = ind;
	}

	/**
	 * @return Returns the tYCTime.
	 */
	public String getTYCTime()
	{
		fetch();
		return tYCTime;
	}

	/**
	 * @param time The tYCTime to set.
	 */
	public void setTYCTime(String time)
	{
		if (this.tYCTime == null || !this.tYCTime.equals(time))
		{
			markModified();
		}
		tYCTime = time;
	}

	/**
	 * @return Returns the tycWithheld.
	 */
	public JuvenileActivityCode getTycWithheld()
	{
		fetch();
		initTycWithheld();
		return tycWithheld;
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public void setNeedSupervisionId(String needSupervisionId)
	{
		if (this.needSupervisionId == null || !this.needSupervisionId.equals(needSupervisionId))
		{
			markModified();
		}
		needSupervision = null;
		this.needSupervisionId = needSupervisionId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public String getNeedSupervisionId()
	{
		fetch();
		return needSupervisionId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileActivityCode
	 */
	private void initNeedSupervision()
	{
		if (needSupervision == null)
		{
			needSupervision = (JuvenileActivityCode) new mojo.km.persistence.Reference(
					needSupervisionId, JuvenileActivityCode.class).getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public void setTycWithheldId(String tycWithheldId)
	{
		if (this.tycWithheldId == null || !this.tycWithheldId.equals(tycWithheldId))
		{
			markModified();
		}
		tycWithheld = null;
		this.tycWithheldId = tycWithheldId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public String getTycWithheldId()
	{
		fetch();
		return tycWithheldId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileActivityCode
	 */
	private void initTycWithheld()
	{
		if (tycWithheld == null)
		{
			tycWithheld = (JuvenileActivityCode) new mojo.km.persistence.Reference(tycWithheldId,
					JuvenileActivityCode.class).getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public void setPlacementId(String placementId)
	{
		if (this.placementId == null || !this.placementId.equals(placementId))
		{
			markModified();
		}
		placement = null;
		this.placementId = placementId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public String getPlacementId()
	{
		fetch();
		return placementId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileActivityCode
	 */
	private void initPlacement()
	{
		if (placement == null)
		{
			placement = (JuvenileActivityCode) new mojo.km.persistence.Reference(placementId,
					JuvenileActivityCode.class).getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public void setJudgementId(String judgementId)
	{
		if (this.judgementId == null || !this.judgementId.equals(judgementId))
		{
			markModified();
		}
		judgement = null;
		this.judgementId = judgementId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileActivityCode
	 */
	public String getJudgementId()
	{
		fetch();
		return judgementId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileActivityCode
	 */
	private void initJudgement()
	{
		if (judgement == null)
		{
			judgement = (JuvenileActivityCode) new mojo.km.persistence.Reference(judgementId,
					JuvenileActivityCode.class).getObject();
		}
	}

	/**
	 * 
	 * @return Returns the delConduct.
	 */
	public String getDelConduct()
	{
		fetch();
		return delConduct;
	}

	/**
	 * 
	 * @param delConduct The delConduct to set.
	 */
	public void setDelConduct(String delConduct)
	{
		if (this.delConduct == null || !this.delConduct.equals(delConduct))
		{
			markModified();
		}
		this.delConduct = delConduct;
	}

	/**
	 * 
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * 
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	/**
	 * 
	 * @return Returns the petitionNum.
	 */
	public String getPetitionNum()
	{
		fetch();
		return petitionNum;
	}

	/**
	 * 
	 * @param petitionNum The petitionNum to set.
	 */
	public void setPetitionNum(String petitionNum)
	{
		if (this.petitionNum == null || !this.petitionNum.equals(petitionNum))
		{
			markModified();
		}
		this.petitionNum = petitionNum;
	}

	/**
	 * 
	 * @return Returns the seqNum.
	 */
	public String getSeqNum()
	{
		fetch();
		return seqNum;
	}

	/**
	 * 
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(String seqNum)
	{
		if (this.seqNum == null || !this.seqNum.equals(seqNum))
		{
			markModified();
		}
		this.seqNum = seqNum;
	}

	/**
	 * set the type reference for class member needSupervision
	 */
	public void setNeedSupervision(JuvenileActivityCode needSupervision)
	{
		if (this.needSupervision == null || !this.needSupervision.equals(needSupervision))
		{
			markModified();
		}
		if (needSupervision.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(needSupervision);
		}
		setNeedSupervisionId("" + needSupervision.getOID());
		this.needSupervision = (JuvenileActivityCode) new mojo.km.persistence.Reference(
				needSupervision).getObject();
	}

	/**
	 * set the type reference for class member tycWithheld
	 */
	public void setTycWithheld(JuvenileActivityCode tycWithheld)
	{
		if (this.tycWithheld == null || !this.tycWithheld.equals(tycWithheld))
		{
			markModified();
		}
		if (tycWithheld.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(tycWithheld);
		}
		setTycWithheldId("" + tycWithheld.getOID());
		this.tycWithheld = (JuvenileActivityCode) new mojo.km.persistence.Reference(tycWithheld)
				.getObject();
	}

	/**
	 * set the type reference for class member placement
	 */
	public void setPlacement(JuvenileActivityCode placement)
	{
		if (this.placement == null || !this.placement.equals(placement))
		{
			markModified();
		}
		if (placement.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(placement);
		}
		setPlacementId("" + placement.getOID());
		this.placement = (JuvenileActivityCode) new mojo.km.persistence.Reference(placement)
				.getObject();
	}

	/**
	 * set the type reference for class member judgement
	 */
	public void setJudgement(JuvenileActivityCode judgement)
	{
		if (this.judgement == null || !this.judgement.equals(judgement))
		{
			markModified();
		}
		if (judgement.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(judgement);
		}
		setJudgementId("" + judgement.getOID());
		this.judgement = (JuvenileActivityCode) new mojo.km.persistence.Reference(judgement)
				.getObject();
	}

	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JuvenileOffenderTrackingDisposition.class);
	}	
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		fetch();
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		if (this.daLogNum == null || !this.daLogNum.equals(daLogNum))
		{
			markModified();
		}
		this.daLogNum = daLogNum;
	}
}
