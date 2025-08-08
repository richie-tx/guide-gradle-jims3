package pd.supervision.administerserviceprovider.transactions;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.administerserviceprovider.CreateContactFundSourceEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.administerserviceprovider.ContactSourceFund;

public class CreateContactFundSourceCommand implements ICommand
{
    public CreateContactFundSourceCommand()
    {
    }

    public void execute(IEvent event)
    {
	CreateContactFundSourceEvent createEvent = (CreateContactFundSourceEvent) event;
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(createEvent.getFundStartDate());
	calendar.add(Calendar.DATE, -1);
	Date prevDay = calendar.getTime();

	Iterator<ContactSourceFund> sourceFunds = ContactSourceFund.findAll("profileId", createEvent.getProfileId());
	while (sourceFunds.hasNext())
	{
	    ContactSourceFund sourceFund = sourceFunds.next();
	    if ("ACTIVE".equalsIgnoreCase((sourceFund.getFundStatus())))
	    {
		sourceFund.setFundStatus("INACTIVE");
		sourceFund.setFundEndDate(prevDay);
		new Home().bind(sourceFund);
	    }
	}

	ContactSourceFund contactSourceFund = new ContactSourceFund();
	contactSourceFund.setProfileId(createEvent.getProfileId());
	contactSourceFund.setFundSource(createEvent.getFundSource());
	contactSourceFund.setFundEntryDate(new Date());
	contactSourceFund.setFundStartDate(createEvent.getFundStartDate());
	contactSourceFund.setFundEndDate(createEvent.getFundEndDate());
	contactSourceFund.setFundStatus("ACTIVE");
	contactSourceFund.setComments(createEvent.getComments());
	IHome home = new Home();
	home.bind(contactSourceFund);

    }
}
