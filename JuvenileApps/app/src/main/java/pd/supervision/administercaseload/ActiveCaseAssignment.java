package pd.supervision.administercaseload;

import java.util.List;

import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;

/**
 * @author Jim Fisher
 */
public class ActiveCaseAssignment extends CaseAssignment
{
    private String courtId;

    private String criminalCaseId;

    private String defendantId;

    private String orderStatusCode;

    private String programUnitName;

    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        return courtId;
    }

    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId()
    {
        return criminalCaseId;
    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @return Returns the orderStatusCode.
     */
    public String getOrderStatusCode()
    {
        return orderStatusCode;
    }

    /**
     * @return Returns the programUnitName.
     */
    public String getProgramUnitName()
    {
        return programUnitName;
    }

    /**
     * @param courtId
     *        The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        if (courtId.length() > 3)
        {
            this.courtId = courtId.substring(3);
        }
    }

    /**
     * @param criminalCaseId
     *        The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId)
    {
        this.criminalCaseId = criminalCaseId;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param orderStatusCode
     *        The orderStatusCode to set.
     */
    public void setOrderStatusCode(String orderStatusCode)
    {
        this.orderStatusCode = orderStatusCode;
    }

    /**
     * @param programUnitName
     *        The programUnitName to set.
     */
    public void setProgramUnitName(String programUnitName)
    {
        this.programUnitName = programUnitName;
    }

    public static List findAllBySupervisee(String aDefendantId)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll("defendantId", aDefendantId, ActiveCaseAssignment.class));
    }

    public static List findAllByOfficer(String aPositionId)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll("officerPositionId", aPositionId, ActiveCaseAssignment.class));
    }

    public static List findAllBySuperviseeName(GetCaseloadEvent anEvent)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll(anEvent, ActiveCaseAssignment.class));
    }

    /**
     * @return
     */
    public ICaseAssignment valueObject()
    {
        ICaseAssignment caseAssignment = super.valueObject();

        caseAssignment.setProgramUnitName(this.getProgramUnitName());

        caseAssignment.setCriminalCaseId(this.getCriminalCaseId());
        caseAssignment.setDefendantId(this.getDefendantId());

        caseAssignment.setOrderStatusCode(this.getOrderStatusCode());

        caseAssignment.setCourtId(this.getCourtId());

        return caseAssignment;
    }
}
