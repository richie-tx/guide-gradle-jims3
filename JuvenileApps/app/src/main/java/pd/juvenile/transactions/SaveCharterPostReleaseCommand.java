package pd.juvenile.transactions;

import java.util.Iterator;
import pd.juvenile.JuvenileCharterPostReleaseContinuingEducation;
import pd.juvenile.JuvenileCharterPostReleaseTracking;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.SaveCharterPostReleaseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.context.ICommand;

public class SaveCharterPostReleaseCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveCharterPostReleaseEvent request = (SaveCharterPostReleaseEvent) anEvent;
		JuvenileCharterPostReleaseTracking	charterPR = new JuvenileCharterPostReleaseTracking();
		charterPR.setComments(request.getComments());
		charterPR.setEmployedCodeId(request.getEmployedCodeId());
		charterPR.setJuvenileNum(request.getJuvenileNum());
		charterPR.setStatusDate(request.getStatusDate());
		IHome home = new Home();
		home.bind(charterPR);
		String juvenileCharterPostReleaseTrackingId = charterPR.getOID();
		Iterator charterCEs = request.getContinuingEducationCodes().iterator();
		while (charterCEs.hasNext())
		{
			CodeResponseEvent ceRespEvt = (CodeResponseEvent) charterCEs.next();
			JuvenileCharterPostReleaseContinuingEducation charterCE = new JuvenileCharterPostReleaseContinuingEducation();
			charterCE.setContinuingEducationCodeId(ceRespEvt.getCode());
			charterCE.setJuvenileCharterPostReleaseTrackingId(juvenileCharterPostReleaseTrackingId);
		}
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
