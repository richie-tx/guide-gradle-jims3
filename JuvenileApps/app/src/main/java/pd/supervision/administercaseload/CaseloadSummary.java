package pd.supervision.administercaseload;

import pd.contact.user.UserProfile;
import naming.CaseloadConstants;
import messaging.administercaseload.domintf.ICaseloadSummary;
import messaging.administercaseload.to.CaseloadSummaryTO;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;

public class CaseloadSummary extends PersistentObject
{
    private String cjadNum;

    private int indCount;

    private int los0Count;

    private int los1Count;

    private int los2Count;

    private int los3Count;

    private int los4Count;

    private String officerPositionId;

    private UserProfile officerUserProfile;

    private String officerUserProfileId;

    private String positionName;

    private String probationOfficerInd;

    private String supervisorPositionId;

    /**
     * @roseuid 46435FF30138
     */
    public CaseloadSummary()
    {

    }

    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum()
    {
		fetch();
        return cjadNum;
    }

    /**
     * @return Returns the indCount.
     */
    public int getIndCount()
    {
		fetch();
        return indCount;
    }

    /**
     * @return Returns the los0Count.
     */
    public int getLos0Count()
    {
		fetch();
        return los0Count;
    }

    /**
     * @return Returns the los1Count.
     */
    public int getLos1Count()
    {
		fetch();
        return los1Count;
    }

    /**
     * @return Returns the los2Count.
     */
    public int getLos2Count()
    {
		fetch();
        return los2Count;
    }

    /**
     * @return Returns the los3Count.
     */
    public int getLos3Count()
    {
		fetch();
        return los3Count;
    }

    /**
     * @return Returns the los4Count.
     */
    public int getLos4Count()
    {
		fetch();
        return los4Count;
    }

    public String getOfficerPositionId()
    {
		fetch();
        return this.officerPositionId;
    }

    public UserProfile getOfficerUserProfile()
    {
        initOfficerUserProfile();
        return officerUserProfile;
    }

    /**
     * @return Returns the officerUserProfileId.
     */
    public String getOfficerUserProfileId()
    {
		fetch();
        return officerUserProfileId;
    }

    /**
     * @return Returns the positionName.
     */
    public String getPositionName()
    {
		fetch();
        return positionName;
    }

    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd()
    {
		fetch();
        return probationOfficerInd;
    }

    /**
     * @return Returns the supervisorPositionId.
     */
    public String getSupervisorPositionId()
    {
		fetch();
        return supervisorPositionId;
    }

    /**
     *  
     */
    private void initOfficerUserProfile()
    {
        if (officerUserProfile == null)
        {
            officerUserProfile = (UserProfile) new Reference(officerUserProfileId, UserProfile.class).getObject();
        }
    }

    /**
     * @param cjadNum
     *        The cjadNum to set.
     */
    public void setCjadNum(String cjadNum)
    {
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum)) {
			markModified();
		}
        this.cjadNum = cjadNum;
    }

    /**
     * @param indCount
     *        The indCount to set.
     */
    public void setIndCount(int indCount)
    {
		if (this.indCount != indCount) {
			markModified();
		}
        this.indCount = indCount;
    }

    /**
     * @param los0Count
     *        The los0Count to set.
     */
    public void setLos0Count(int los0Count)
    {
		if (this.los0Count != los0Count) {
			markModified();
		}
        this.los0Count = los0Count;
    }

    /**
     * @param los1Count
     *        The los1Count to set.
     */
    public void setLos1Count(int los1Count)
    {
		if (this.los1Count != los1Count) {
			markModified();
		}
        this.los1Count = los1Count;
    }

    /**
     * @param los2Count
     *        The los2Count to set.
     */
    public void setLos2Count(int los2Count)
    {
		if (this.los2Count != los2Count) {
			markModified();
		}
        this.los2Count = los2Count;
    }

    /**
     * @param los3Count
     *        The los3Count to set.
     */
    public void setLos3Count(int los3Count)
    {
		if (this.los3Count != los3Count) {
			markModified();
		}
        this.los3Count = los3Count;
    }

    /**
     * @param los4Count
     *        The los4Count to set.
     */
    public void setLos4Count(int los4Count)
    {
		if (this.los4Count != los4Count) {
			markModified();
		}
        this.los4Count = los4Count;
    }

    public void setOfficerPositionId(String officerPositionId)
    {
		if (this.officerPositionId == null || !this.officerPositionId.equals(officerPositionId)) {
			markModified();
		}
        this.officerPositionId = officerPositionId;
    }

    /**
     * @param officerUserProfileId
     *        The officerUserProfileId to set.
     */
    public void setOfficerUserProfileId(String officerUserProfileId)
    {
		if (this.officerUserProfileId == null || !this.officerUserProfileId.equals(officerUserProfileId)) {
			markModified();
		}
        this.officerUserProfileId = officerUserProfileId;
    }

    /**
     * @param positionName
     *        The positionName to set.
     */
    public void setPositionName(String positionName)
    {
		if (this.positionName == null || !this.positionName.equals(positionName)) {
			markModified();
		}
        this.positionName = positionName;
    }

    /**
     * @param probationOfficerInd
     *        The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd)
    {
		if (this.probationOfficerInd == null || !this.probationOfficerInd.equals(probationOfficerInd)) {
			markModified();
		}
        this.probationOfficerInd = probationOfficerInd;
    }

    /**
     * @param supervisorPositionId
     *        The supervisorPositionId to set.
     */
    public void setSupervisorPositionId(String supervisorPositionId)
    {
		if (this.supervisorPositionId == null || !this.supervisorPositionId.equals(supervisorPositionId)) {
			markModified();
		}
        this.supervisorPositionId = supervisorPositionId;
    }

    /**
     * @return
     */
    public ICaseloadSummary valueObject(double[] multipliers)
    {
        ICaseloadSummary bean = new CaseloadSummaryTO();

        bean.setSupervisorPositionId(this.getSupervisorPositionId());

        bean.setCjad(this.getCjadNum());

        if (this.getOfficerUserProfileId() != null && this.getOfficerUserProfileId().equals("") == false)
        {
            UserProfile officerProfile = this.getOfficerUserProfile();
            if (officerProfile != null)
            {
                bean.setOfficerName(officerProfile.getName());
            }
        } else {
        	IName nameBean = new NameBean();
        	nameBean.setLastName("No Officer Assigned");
        	bean.setOfficerName(nameBean);
        }
        
        bean.setOfficerPositionId(this.getOfficerPositionId());

        bean.setPositionName(this.getPositionName());
        
        bean.setProbationOfficerInd(this.getProbationOfficerInd());

        double workload = 0.0;
        
        int countTotal = 0;

        int count = this.getLos0Count();
        bean.setLos0Count(count);
        double value = (double) count * (double) multipliers[CaseloadConstants.LOS0_INDEX];
        bean.setLos0(value);
        workload += value;
        countTotal += count;

        count = this.getLos1Count();
        bean.setLos1Count(count);
        value = (double) count * (double) multipliers[CaseloadConstants.LOS1_INDEX];
        bean.setLos1(value);
        workload += value;
        countTotal += count;

        count = this.getLos2Count();
        bean.setLos2Count(count);
        value = (double) count * (double) multipliers[CaseloadConstants.LOS2_INDEX];
        bean.setLos2(value);
        workload += value;
        countTotal += count;

        count = this.getLos3Count();
        bean.setLos3Count(count);
        value = (double) count * (double) multipliers[CaseloadConstants.LOS3_INDEX];
        bean.setLos3(value);
        workload += value;
        countTotal += count;

        count = this.getLos4Count();
        bean.setLos4Count(count);
        value = (double) count * (double) multipliers[CaseloadConstants.LOS4_INDEX];
        bean.setLos4(value);
        workload += value;
        countTotal += count;
        
        count = this.getIndCount();
        bean.setIndCount(count);
        value = (double) count * (double) multipliers[CaseloadConstants.IND_INDEX];
        bean.setInd(value);
        workload += value;
        countTotal += count;
        
        bean.setWorkload(workload);
        bean.setCountTotal(countTotal);

        return bean;
    }
}
