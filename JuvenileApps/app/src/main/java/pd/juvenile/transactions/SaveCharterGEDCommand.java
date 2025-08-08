package pd.juvenile.transactions;

import pd.juvenile.JuvenileCharterGED;
import messaging.juvenile.SaveCharterGEDEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

public class SaveCharterGEDCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveCharterGEDEvent request = (SaveCharterGEDEvent) anEvent;
		JuvenileCharterGED charterGED = new JuvenileCharterGED();
		charterGED.setJuvenileNum(request.getJuvenileNum());
        charterGED.setLockStatus(request.isLockStatus());
        charterGED.setMathAfterPlacement(request.isMathAfterPlacement());
        charterGED.setMathBeforePlacement(request.isMathBeforePlacement());
        charterGED.setMathPassFailIndicator(request.isMathPassFailIndicator());
        charterGED.setMathRetest(request.isMathRetest());
        charterGED.setMathScore(request.getMathScore());
        charterGED.setMathTestDate(request.getMathTestDate());
        /**Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) starts **/
        /*charterGED.setReadingAfterPlacement(request.isReadingAfterPlacement());
        charterGED.setReadingBeforePlacement(request.isReadingBeforePlacement());
        charterGED.setReadingPassFailIndicator(request.isReadingPassFailIndicator());
        charterGED.setReadingRetest(request.isReadingRetest());
        charterGED.setReadingScore(request.getReadingScore());
        charterGED.setReadingTestDate(request.getReadingTestDate());*/
        /**Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) ends **/
        charterGED.setScienceAfterPlacement(request.isScienceAfterPlacement());
        charterGED.setScienceBeforePlacement(request.isScienceBeforePlacement());
        charterGED.setSciencePassFailIndicator(request.isSciencePassFailIndicator());
        charterGED.setScienceRetest(request.isScienceRetest());
        charterGED.setScienceScore(request.getScienceScore());
        charterGED.setScienceTestDate(request.getScienceTestDate());
        charterGED.setSocialStudiesAfterPlacement(request.isSocialStudiesAfterPlacement());
        charterGED.setSocialStudiesBeforePlacement(request.isSocialStudiesBeforePlacement());
        charterGED.setSocialStudiesPassFailIndicator(request.isSocialStudiesPassFailIndicator());
        charterGED.setSocialStudiesRetest(request.isSocialStudiesRetest());
        charterGED.setSocialStudiesScore(request.getSocialStudiesScore());
        charterGED.setSocialStudiesTestDate(request.getSocialStudiesTestDate());
        charterGED.setTotalScore(request.getTotalScore());
        /**Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) starts **/
        /*charterGED.setWritingAfterPlacement(request.isWritingAfterPlacement());
        charterGED.setWritingBeforePlacement(request.isWritingBeforePlacement());
        charterGED.setWritingPassFailIndicator(request.isWritingPassFailIndicator());
        charterGED.setWritingRetest(request.isWritingRetest());
        charterGED.setWritingScore(request.getWritingScore());
        charterGED.setWritingTestDate(request.getWritingTestDate());*/
        
        charterGED.setRlaAfterPlacement(request.isRlaAfterPlacement());
        charterGED.setRlaBeforePlacement(request.isRlaBeforePlacement());
        charterGED.setRlaPassFailIndicator(request.isRlaPassFailIndicator());
        charterGED.setRlaRetest(request.isRlaRetest());
        charterGED.setRlaScore(request.getRlaScore());
        charterGED.setRlaTestDate(request.getRlaTestDate());
        /**Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) ends **/
        charterGED.setVersion(request.getVersion());
        charterGED.setGedPassFailIndicator(request.isGedPassFailIndicator());
        charterGED.setGedProgramCodeId(request.getGedProgramCodeId());
        charterGED.setOtherProgram(request.getOtherProgram());
        charterGED.setTotalIncomplete(request.getTotalIncomplete());
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
