package pd.juvenile.transactions;

import messaging.juvenile.UpdateJuvenileServiceCTSInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.helper.WebServiceHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateJuvenileServiceCTSInfoCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileServiceCTSInfoEvent evt = (UpdateJuvenileServiceCTSInfoEvent) event;
	/*Home home= new Home();
	
	JuvenileServiceCTSInfo juvenileServiceCTSInfo = new JuvenileServiceCTSInfo();
	
	juvenileServiceCTSInfo.setCaseNum(evt.getCaseNum());
	juvenileServiceCTSInfo.setCourtDate(evt.getCourtDate());
	juvenileServiceCTSInfo.setCourtNum(evt.getCourtNum());
	juvenileServiceCTSInfo.setCrossRegionUpdate(evt.getCrossRegionUpdate());
	juvenileServiceCTSInfo.setDefendant(evt.getDefendant());
	juvenileServiceCTSInfo.setJuvenileNum(evt.getJuvenileNum());
	juvenileServiceCTSInfo.setNameType(evt.getNameType());
	juvenileServiceCTSInfo.setPaperExpirationDate(evt.getPaperExpirationDate());
	juvenileServiceCTSInfo.setPhone(evt.getPhone());
	juvenileServiceCTSInfo.setPltName(evt.getPltName());
	juvenileServiceCTSInfo.setState(evt.getState());
	juvenileServiceCTSInfo.setStreetName(evt.getStreetName());
	juvenileServiceCTSInfo.setStreetNum(evt.getStreetNum());
	juvenileServiceCTSInfo.setSubpoenaForInd(evt.getSubpoenaForInd());
	juvenileServiceCTSInfo.setTrackingNum(evt.getTrackingNum());
	juvenileServiceCTSInfo.setZip(evt.getZip()); ////83496
	juvenileServiceCTSInfo.setServeName(evt.getServeName()); ////83496
	juvenileServiceCTSInfo.setJims2LcUser(PDSecurityHelper.getUser().getJIMS2LogonId());
	juvenileServiceCTSInfo.setLcTime(Calendar.getInstance().getTime());
	juvenileServiceCTSInfo.setLcDate(DateUtil.getCurrentDate());
	juvenileServiceCTSInfo.setLcUser(PDSecurityHelper.getLogonId());
	
	home.bind(juvenileServiceCTSInfo);*/
	
	WebServiceHelper helper = new WebServiceHelper();
	helper.updateJuvServiceCTSInfo(evt);
    }

}
