package pd.productionsupport.transactions;

import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import messaging.productionsupport.DeleteProductionSupportFacilityDetentionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class DeleteProductionSupportFacilityDetentionCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	DeleteProductionSupportFacilityDetentionEvent deleteEvent = (DeleteProductionSupportFacilityDetentionEvent) event;
	String detentionId = deleteEvent.getDetentionId();
	String lastSeqNum  = deleteEvent.getLastSequenceNumber();
	String newLastSeqNum = deleteEvent.getNewLastSequenceNumber();
	
	if (detentionId != null && detentionId.length() > 0)
	{
	    JJSFacility jjsDetention = JJSFacility.find("OID", detentionId); //this is the JJS_DETENTION record
	    if (jjsDetention != null)
	    {
		if (lastSeqNum.equals(jjsDetention.getFacilitySequenceNumber()))//if true here means it is current admission
		{
		    JJSHeader jjsHeader = JJSHeader.find(jjsDetention.getJuvenileNumber()); //this is the JJS_HEADER record
		    if (jjsDetention.getReleaseDate() == null || jjsDetention.getReleaseDate().equals(""))
		    {
			if (newLastSeqNum != null && newLastSeqNum != "")
			{
			    JJSFacility previousFacility = JuvenileFacilityHelper.getJJSDetentionJuvNumSeqNum(jjsDetention.getJuvenileNumber(), newLastSeqNum);
			    if (previousFacility != null && previousFacility.getReleaseTo().equalsIgnoreCase("NTS"))
			    {
				JJSJuvenile juv = JJSJuvenile.find(jjsDetention.getJuvenileNumber());
				juv.setDetentionFacilityId(previousFacility.getDetainedFacility()); //JJS_MS_JUVENILE.detentionfacility will becomes the facility from the previous seqnum JJS_DETENTION record.
				juv.setDetentionStatusId("N"); //JJS_MS_JUVENILE.detentionstatus will be N.
				jjsHeader.setFacilityCode(previousFacility.getDetainedFacility());
				jjsHeader.setFacilityStatus("N"); //JJS_HEADER.detentionstatus will be N.
				IHome home = new Home();
				home.bind(juv);
			    }
			    else
			    {
				if (previousFacility != null)
				{
				    JJSJuvenile juv = JJSJuvenile.find(jjsDetention.getJuvenileNumber());
				    juv.setDetentionStatusId(null);
				    juv.setDetentionFacilityId(previousFacility.getDetainedFacility());
				    jjsHeader.setFacilityStatus(null); //JJS_HEADER.detentionstatus will be NULL.
				    jjsHeader.setFacilityCode(previousFacility.getDetainedFacility());
				    IHome home = new Home();
				    home.bind(juv);
				}
			    }
			}
		    }
		    if (jjsHeader != null && newLastSeqNum != null && newLastSeqNum.length() > 0)
		    {
			jjsHeader.setLastSequenceNumber(newLastSeqNum);
			IHome home = new Home();
			home.bind(jjsHeader);
		    }

		    if (jjsHeader != null && newLastSeqNum == "") //only one record for this kid in JJS_Detention, then delete the header record too
		    {
			jjsHeader.delete(); // JJS_HEADER
			IHome home = new Home();
			home.bind(jjsHeader);
			JJSJuvenile juv = JJSJuvenile.find(jjsDetention.getJuvenileNumber());
			juv.setDetentionStatusId(null);
			IHome home2 = new Home();
			home2.bind(juv);
		    }
		} //code for if selected detention record is current admission ends
		jjsDetention.delete(); //JJS_DETENTION
		IHome home = new Home();
		home.bind(jjsDetention);

	    }
	}
    }
}
