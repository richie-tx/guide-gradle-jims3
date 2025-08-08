package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;

import pd.juvenilecase.districtCourtHearings.CTSServiceRecord;
import pd.security.PDSecurityHelper;
import messaging.districtCourtHearings.UpdateCTSServiceRecordEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdateCTSServiceRecordCommand implements ICommand
{
    public UpdateCTSServiceRecordCommand() {}
    
    @Override
    public void execute(IEvent event) {
	UpdateCTSServiceRecordEvent updateRecordEvent = (UpdateCTSServiceRecordEvent) event;
	Home home= new Home();
	CTSServiceRecord record = new CTSServiceRecord();
	record.setJuvenileNumber(updateRecordEvent.getJuvenileNumber());
	record.setPetitionNumber(updateRecordEvent.getPetitionNumber());
	record.setCourtDate(updateRecordEvent.getCourtDate());
	record.setPaperExpirationDate(updateRecordEvent.getPaperExpirationDate());
	record.setCourtNumber(updateRecordEvent.getCourtNumber());
	record.setNameType(updateRecordEvent.getNameType());
	record.setSubpoenaFor(updateRecordEvent.getSubpoenaFor());
	record.setPhone(updateRecordEvent.getPhone());
	record.setPlaintiff(updateRecordEvent.getPlaintiff());
	record.setState(updateRecordEvent.getState());
	record.setStreetNumber(updateRecordEvent.getStreetNumber());
	record.setStreet(updateRecordEvent.getStreet());
	record.setServName(updateRecordEvent.getServName());
	record.setDefendant(updateRecordEvent.getDefendant());
	record.setTrackingNumber(updateRecordEvent.getTrackingNumber());
	record.setZip(updateRecordEvent.getZip());
	record.setCrossRegionUpdate(updateRecordEvent.getCrossRegionUpdate());
	record.setLcDate(DateUtil.getCurrentDate());
	record.setLcUser(PDSecurityHelper.getLogonId());
	record.setLcTime(Calendar.getInstance().getTime());
	home.bind(record);
	
	
    }
}
