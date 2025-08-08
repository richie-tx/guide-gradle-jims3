package pd.productionsupport.transactions;

import java.util.logging.Logger;

import messaging.productionsupport.SaveProductionSupportJuvenileSchoolHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileSchoolHistory;

public class SaveProductionSupportJuvenileSchoolHistoryCommand implements ICommand
{
    private Logger log = Logger.getLogger("SaveProdSupportJuvenileSchoolHistoryCommand");

    /**
     * @roseuid 42B18DC600AB
     */
    public SaveProductionSupportJuvenileSchoolHistoryCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B18B3E032E
     */
    public void execute(IEvent event)
    {
	SaveProductionSupportJuvenileSchoolHistoryEvent schoolEvent = (SaveProductionSupportJuvenileSchoolHistoryEvent) event;
	JuvenileSchoolHistory schoolHistory = JuvenileSchoolHistory.find(schoolEvent.getSchoolHistoryId());
	saveSchoolHistory(schoolHistory, schoolEvent);
    }

    /**
     * @param event
     * @roseuid 42B18B3E0330
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42B18B3E033C
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 42B18DC600CB
     */
    public void update(Object updateObject)
    {

    }

    /**
     * @param schoolHistory
     * @param schoolEvent
     */
    private void saveSchoolHistory(JuvenileSchoolHistory schoolHistory, SaveProductionSupportJuvenileSchoolHistoryEvent schoolEvent)
    {
	if (schoolHistory != null)
	{
	    schoolHistory.setSchoolHistoryId(schoolEvent.getSchoolHistoryId());
	    schoolHistory.setJuvenileNum(schoolEvent.getJuvenileNum());

	    if (!nvl(schoolHistory.getExitTypeId()).trim().equalsIgnoreCase(nvl(schoolEvent.getExitTypeCode()).trim())
		    && schoolEvent.getExitTypeCode() != null)
	    {
		schoolHistory.setExitTypeId(schoolEvent.getExitTypeCode());
		log.info("Performed a JCSCHOOL EXITTYPECD change for SCHOOLHIST_ID="
			+ schoolEvent.getSchoolHistoryId()
			+ " New Value:"
			+ schoolEvent.getExitTypeCode());

	    }
	    if (!nvl(schoolHistory.getGradeLevelId()).trim().equalsIgnoreCase(nvl(schoolEvent.getGradeLevelCode()).trim())
		    && nvl(schoolEvent.getGradeLevelCode()).trim() != "")
	    {
		schoolHistory.setGradeLevelId(schoolEvent.getGradeLevelCode());
		log.info("Performed a JCSCHOOL CURRENTGRADECD change for SCHOOLHIST_ID="
			+ schoolEvent.getSchoolHistoryId()
			+ " New Value:"
			+ schoolEvent.getGradeLevelCode());
	    }
	    if (!nvl(schoolHistory.getGradesRepeatedId()).trim().equalsIgnoreCase(nvl(schoolEvent.getGradesRepeatedCode()).trim())
		    && schoolEvent.getGradesRepeatedCode() != null)
	    {
		schoolHistory.setGradesRepeatedId(schoolEvent.getGradesRepeatedCode());
		log.info("Performed a JCSCHOOL GRADESREPEATCD change for SCHOOLHIST_ID="
			+ schoolEvent.getSchoolHistoryId()
			+ " New Value:"
			+ schoolEvent.getGradesRepeatedCode());
	    }
	    if (schoolHistory.getLastAttendedDate() != null
		    && schoolEvent.getLastAttendedDate() != null
		    && !schoolHistory.getLastAttendedDate().equals(schoolEvent.getLastAttendedDate()))
	    {
		schoolHistory.setLastAttendedDate(schoolEvent.getLastAttendedDate());
		log.info("Performed a JCSCHOOL LASTATTENDED DATE change for SCHOOLHIST_ID="
			+ schoolEvent.getSchoolHistoryId()
			+ " New Value:"
			+ schoolEvent.getLastAttendedDate());
	    }
	    if (!nvl(schoolHistory.getAppropriateLevelId()).equals(nvl(schoolEvent.getAppropriateLevelCode()))
		    && schoolEvent.getAppropriateLevelCode() != null)
	    {
		schoolHistory.setAppropriateLevelId(schoolEvent.getAppropriateLevelCode());
		log.info("Performed a JCSCHOOL APPROPGRADECD change for SCHOOLHIST_ID="
			+ schoolEvent.getSchoolHistoryId()
			+ " New Value:"
			+ schoolEvent.getAppropriateLevelCode());
	    }
	}
    }

    String nvl(String value)
    {
	return (value != null && value.length() > 0) ? value : "";
    }

}
