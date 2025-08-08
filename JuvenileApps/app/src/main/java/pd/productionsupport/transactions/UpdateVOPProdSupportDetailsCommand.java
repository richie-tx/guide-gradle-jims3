package pd.productionsupport.transactions;

import pd.juvenilecase.referral.JCVOP;
import messaging.productionsupport.UpdateVOPProdSupportDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class UpdateVOPProdSupportDetailsCommand implements ICommand
{
	    @Override
	    public void execute(IEvent event) throws Exception
	    {
		UpdateVOPProdSupportDetailsEvent evt = (UpdateVOPProdSupportDetailsEvent) event;
		//IHome home = new Home();
		
		JCVOP vop = JCVOP.find(evt.getVopOID());
		vop.setJuvenileNumber(evt.getJuvenileNum());
		vop.setReferralNumber(evt.getReferralNum());
		vop.setReferralDate(evt.getReferralDate());
		vop.setVOPOffenseCode(evt.getVopOffenseCode());
		vop.setOffenseCharge(evt.getOffenseCharge());
		vop.setOffenseChargeDate(evt.getOffenseChargeDate());
		vop.setInCCountyOrigPetitionedRefNum(evt.getInCCountyOrigPetitionedRefNum());
		if(evt.getAdultIndicatorStr()!= null && !evt.getAdultIndicatorStr().isEmpty()){
		    vop.setAdultIndicatorStr(evt.getAdultIndicatorStr());
		}else {
		    vop.setAdultIndicatorStr(null);
		} 
		if (evt.getLocationIndicator().equalsIgnoreCase("IIC")){
		    vop.setLocationIndicator("IN COUNTY");
		}else if (evt.getLocationIndicator().equalsIgnoreCase("OOC")){
		    vop.setLocationIndicator("OUT OF COUNTY");
		}else if (evt.getLocationIndicator().equalsIgnoreCase("OOS")){
		    vop.setLocationIndicator("OUT OF STATE");
		}
		//home.bind(vop);
	}
		/**
		 * @param event
		 * @roseuid 4306266C0232
		 */
		public void onRegister(IEvent event)
		{

		}

		/**
		 * @param event
		 * @roseuid 4306266C0234
		 */
		public void onUnregister(IEvent event)
		{

		}

		/**
		 * @param anObject
		 * @roseuid 4306266C023F
		 */
		public void update(Object anObject)
		{

		}
}
