package pd.supervision.administercaseload.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.CaseloadConstants;

import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import pd.supervision.administercaseload.CaseloadSummary;

import messaging.administercaseload.GetCaseloadSummaryEvent;
import messaging.administercaseload.reply.CaseloadSummaryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

public class GetCaseloadSummaryCommand implements ICommand
{

    /**
     * @roseuid 46436035003F
     */
    public GetCaseloadSummaryCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1900E2
     */
    public void execute(IEvent anEvent)
    {
        GetCaseloadSummaryEvent event = (GetCaseloadSummaryEvent) anEvent;
        CaseloadSummaryResponseEvent response = new CaseloadSummaryResponseEvent();

        double[] multipliers = this.buildMultipliers();

        IHome home = new Home();
        Iterator i = home.findAll(anEvent, CaseloadSummary.class);

        while (i.hasNext())
        {
            CaseloadSummary caseload = (CaseloadSummary) i.next();
            response.addCaseload(caseload.valueObject(multipliers));
        }

        MessageUtil.postReply(response);
    }

    private double[] buildMultipliers()
    {
        double[] multipliers = new double[CaseloadConstants.MULTIPLIER_COUNT];

        // TODO put in codetable constants
        Collection codes = PDCodeHelper.getCodes("LOS_MULTIPLIER", false);
        Iterator i = codes.iterator();
        while (i.hasNext())
        {
            Code code = (Code) i.next();
            if (CaseloadConstants.LOS0.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.LOS0_INDEX] = Double.parseDouble(code.getDescription());
            }
            else if (CaseloadConstants.LOS1.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.LOS1_INDEX] = Double.parseDouble(code.getDescription());
            }
            else if (CaseloadConstants.LOS2.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.LOS2_INDEX] = Double.parseDouble(code.getDescription());
            }
            else if (CaseloadConstants.LOS3.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.LOS3_INDEX] = Double.parseDouble(code.getDescription());
            }
            else if (CaseloadConstants.LOS4.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.LOS4_INDEX] = Double.parseDouble(code.getDescription());
            }
            else if (CaseloadConstants.IND.equals(code.getCode()))
            {
                multipliers[CaseloadConstants.IND_INDEX] = Double.parseDouble(code.getDescription());
            }
        }

        return multipliers;
    }

    /**
     * @param event
     * @roseuid 46433E190100
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 46433E19010F
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 46433E190111
     */
    public void update(Object anObject)
    {

    }

}
