package messaging.juvenile;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class SaveCharterGEDEvent extends RequestEvent
{
	/** Changes for ER: JIMS200077481 starts **/
	/*private Date readingTestDate;
	private int readingScore;
	private boolean readingBeforePlacement;
	private boolean readingAfterPlacement;
	private boolean readingRetest;
	private boolean readingPassFailIndicator;
	private Date writingTestDate;
	private int writingScore;
	private boolean writingBeforePlacement;
	private boolean writingAfterPlacement;
	private boolean writingRetest;
	private boolean writingPassFailIndicator;*/
	private Date rlaTestDate;
	private int rlaScore;
	private boolean rlaBeforePlacement;
	private boolean rlaAfterPlacement;
	private boolean rlaRetest;
	private boolean rlaPassFailIndicator;
	/** Changes for ER: JIMS200077481 ends **/
	
	private Date mathTestDate;
	private int mathScore;
	private boolean mathBeforePlacement;
	private boolean mathAfterPlacement;
	private boolean mathRetest;
	private boolean mathPassFailIndicator;
	private Date scienceTestDate;
	private int scienceScore;
	private boolean scienceBeforePlacement;
	private boolean scienceAfterPlacement;
	private boolean scienceRetest;
	private boolean sciencePassFailIndicator;
	private Date socialStudiesTestDate;
	private int socialStudiesScore;
	private boolean socialStudiesBeforePlacement;
	private boolean socialStudiesAfterPlacement;
	private boolean socialStudiesRetest;
	private boolean socialStudiesPassFailIndicator;
	private boolean lockStatus;
	private String juvenileNum;
	private int version;
	private int totalScore;
	private boolean gedPassFailIndicator;
	private String totalIncomplete;
	private String gedProgramCodeId;
	private String otherProgram;

	public Date getMathTestDate() {
		return mathTestDate;
	}
	public void setMathTestDate(Date mathTestDate) {
		this.mathTestDate = mathTestDate;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	public boolean isMathBeforePlacement() {
		return mathBeforePlacement;
	}
	public void setMathBeforePlacement(boolean mathBeforePlacement) {
		this.mathBeforePlacement = mathBeforePlacement;
	}
	public boolean isMathAfterPlacement() {
		return mathAfterPlacement;
	}
	public void setMathAfterPlacement(boolean mathAfterPlacement) {
		this.mathAfterPlacement = mathAfterPlacement;
	}
	public boolean isMathRetest() {
		return mathRetest;
	}
	public void setMathRetest(boolean mathRetest) {
		this.mathRetest = mathRetest;
	}
	public boolean isMathPassFailIndicator() {
		return mathPassFailIndicator;
	}
	public void setMathPassFailIndicator(boolean mathPassFailIndicator) {
		this.mathPassFailIndicator = mathPassFailIndicator;
	}
	public Date getScienceTestDate() {
		return scienceTestDate;
	}
	public void setScienceTestDate(Date scienceTestDate) {
		this.scienceTestDate = scienceTestDate;
	}
	public int getScienceScore() {
		return scienceScore;
	}
	public void setScienceScore(int scienceScore) {
		this.scienceScore = scienceScore;
	}
	public boolean isScienceBeforePlacement() {
		return scienceBeforePlacement;
	}
	public void setScienceBeforePlacement(boolean scienceBeforePlacement) {
		this.scienceBeforePlacement = scienceBeforePlacement;
	}
	public boolean isScienceAfterPlacement() {
		return scienceAfterPlacement;
	}
	public void setScienceAfterPlacement(boolean scienceAfterPlacement) {
		this.scienceAfterPlacement = scienceAfterPlacement;
	}
	public boolean isScienceRetest() {
		return scienceRetest;
	}
	public void setScienceRetest(boolean scienceRetest) {
		this.scienceRetest = scienceRetest;
	}
	public boolean isSciencePassFailIndicator() {
		return sciencePassFailIndicator;
	}
	public void setSciencePassFailIndicator(boolean sciencePassFailIndicator) {
		this.sciencePassFailIndicator = sciencePassFailIndicator;
	}
	public Date getSocialStudiesTestDate() {
		return socialStudiesTestDate;
	}
	public void setSocialStudiesTestDate(Date socialStudiesTestDate) {
		this.socialStudiesTestDate = socialStudiesTestDate;
	}
	public int getSocialStudiesScore() {
		return socialStudiesScore;
	}
	public void setSocialStudiesScore(int socialStudiesScore) {
		this.socialStudiesScore = socialStudiesScore;
	}
	public boolean isSocialStudiesBeforePlacement() {
		return socialStudiesBeforePlacement;
	}
	public void setSocialStudiesBeforePlacement(boolean socialStudiesBeforePlacement) {
		this.socialStudiesBeforePlacement = socialStudiesBeforePlacement;
	}
	public boolean isSocialStudiesAfterPlacement() {
		return socialStudiesAfterPlacement;
	}
	public void setSocialStudiesAfterPlacement(boolean socialStudiesAfterPlacement) {
		this.socialStudiesAfterPlacement = socialStudiesAfterPlacement;
	}
	public boolean isSocialStudiesRetest() {
		return socialStudiesRetest;
	}
	public void setSocialStudiesRetest(boolean socialStudiesRetest) {
		this.socialStudiesRetest = socialStudiesRetest;
	}
	public boolean isSocialStudiesPassFailIndicator() {
		return socialStudiesPassFailIndicator;
	}
	public void setSocialStudiesPassFailIndicator(
			boolean socialStudiesPassFailIndicator) {
		this.socialStudiesPassFailIndicator = socialStudiesPassFailIndicator;
	}
	public boolean isLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(boolean lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public boolean isGedPassFailIndicator() {
		return gedPassFailIndicator;
	}
	public void setGedPassFailIndicator(boolean gedPassFailIndicator) {
		this.gedPassFailIndicator = gedPassFailIndicator;
	}
	public String getGedProgramCodeId() {
		return gedProgramCodeId;
	}
	public void setGedProgramCodeId(String gedProgramCodeId) {
		this.gedProgramCodeId = gedProgramCodeId;
	}
	public String getOtherProgram() {
		return otherProgram;
	}
	public void setOtherProgram(String otherProgram) {
		this.otherProgram = otherProgram;
	}
	public void setRlaTestDate(Date rlaTestDate) {
		this.rlaTestDate = rlaTestDate;
	}
	public Date getRlaTestDate() {
		return rlaTestDate;
	}
	public void setRlaScore(int rlaScore) {
		this.rlaScore = rlaScore;
	}
	public int getRlaScore() {
		return rlaScore;
	}
	public void setRlaBeforePlacement(boolean rlaBeforePlacement) {
		this.rlaBeforePlacement = rlaBeforePlacement;
	}
	public boolean isRlaBeforePlacement() {
		return rlaBeforePlacement;
	}
	public void setRlaAfterPlacement(boolean rlaAfterPlacement) {
		this.rlaAfterPlacement = rlaAfterPlacement;
	}
	public boolean isRlaAfterPlacement() {
		return rlaAfterPlacement;
	}
	public void setRlaRetest(boolean rlaRetest) {
		this.rlaRetest = rlaRetest;
	}
	public boolean isRlaRetest() {
		return rlaRetest;
	}
	public void setRlaPassFailIndicator(boolean rlaPassFailIndicator) {
		this.rlaPassFailIndicator = rlaPassFailIndicator;
	}
	public boolean isRlaPassFailIndicator() {
		return rlaPassFailIndicator;
	}
	public String getTotalIncomplete() {
		return totalIncomplete;
	}
	public void setTotalIncomplete(String totalIncomplete) {
		this.totalIncomplete = totalIncomplete;
	}
	
}
