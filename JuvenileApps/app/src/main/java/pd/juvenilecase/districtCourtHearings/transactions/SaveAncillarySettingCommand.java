package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;

import messaging.districtCourtHearings.SaveAncillarySettingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

/**
 * @author nemathew
 *
 */
public class SaveAncillarySettingCommand implements ICommand{

		@Override
		public void execute(IEvent event) throws Exception {
			SaveAncillarySettingEvent evt =(SaveAncillarySettingEvent)event;
			JJSCLAncillary ancillaryCrt = new JJSCLAncillary();
			IHome home= new Home();
			ancillaryCrt.setCourtId(evt.getCourtId());
			ancillaryCrt.setCourtDate(evt.getCourtDate());
			ancillaryCrt.setCourtTime(evt.getCourtTime());
			ancillaryCrt.setPetitionNumber(evt.getPetitionNum());
			ancillaryCrt.setTypeCase(evt.getTypeCase());
			ancillaryCrt.setRespondantName(evt.getRespondentName());
			ancillaryCrt.setSettingReason(evt.getSettingReason());
			ancillaryCrt.setIssueFlag(evt.getIssueFlag());
			ancillaryCrt.setAttorneyConnection(evt.getAttorneyConnection());
			ancillaryCrt.setAttorneyName(evt.getAttorneyName());
			ancillaryCrt.setBarNumber(evt.getBarNum());
			ancillaryCrt.setFilingDate(evt.getFilingDate());
			ancillaryCrt.setSeqNumber(evt.getSeqNumber());
			ancillaryCrt.setChainNumber(evt.getChainNum());
			ancillaryCrt.setLcDate(evt.getLcDate());
			ancillaryCrt.setLcTime(evt.getLcTime());
			ancillaryCrt.setLcUser(evt.getLcUser());
			ancillaryCrt.setRectype(evt.getRecType());

			JJSChainNumControl chainNumControl = null;
			Iterator<JJSChainNumControl> chainNumIter = JJSChainNumControl.findAll();
			if(chainNumIter.hasNext())
			{
				chainNumControl = (JJSChainNumControl) chainNumIter.next();
				String nextChainNum = chainNumControl.getNextChainNum();
				if(nextChainNum!=null && !nextChainNum.equals(" "))
				{
				    int num = Integer.parseInt(nextChainNum);
				    ancillaryCrt.setChainNumber(""+(num+1));
				}
			}
			
			home.bind(ancillaryCrt);
			
		}

	}