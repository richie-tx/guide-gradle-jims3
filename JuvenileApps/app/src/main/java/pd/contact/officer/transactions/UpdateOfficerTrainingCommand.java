package pd.contact.officer.transactions;

import pd.contact.officer.OfficerTraining;
import messaging.officer.UpdateOfficerTrainingEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class UpdateOfficerTrainingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateOfficerTrainingEvent officerTrainingEvent = (UpdateOfficerTrainingEvent) event;
	OfficerTraining officerTrainingObj = new OfficerTraining();
	
	officerTrainingObj.setOfficerProfileId(officerTrainingEvent.getOfficerId());
	officerTrainingObj.setTrainingTopicsId(officerTrainingEvent.getTrainingTopicsId());
	officerTrainingObj.setTrainingBeginDate(officerTrainingEvent.getTrainingBeginDate());
	officerTrainingObj.setTrainingEndDate(officerTrainingEvent.getTrainingEndDate());
	officerTrainingObj.setTrainingHours(officerTrainingEvent.getTrainingHours());
	IHome home = new Home();
	home.bind(officerTrainingObj);
	
    }

}
