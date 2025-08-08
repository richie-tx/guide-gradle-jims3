package messaging.juvenile.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

public class CharterGEDResponseEvent extends ResponseEvent implements Comparable{
	private Date GEDEntryDate;
	private String charterGEDId;
	private Date readingTestDate;
	private String readingScore;
	private boolean readingBeforePlacement;
	private boolean readingAfterPlacement;
	private boolean readingRetest;
	private boolean readingPassFailIndicator;
	private Date writingTestDate;
	private String writingScore;
	private boolean writingBeforePlacement;
	private boolean writingAfterPlacement;
	private boolean writingRetest;
	private boolean writingPassFailIndicator;
	/** Changes for ER: JIMS200077481 starts**/
	private Date rlaTestDate;
	private String rlaScore;
	private boolean rlaBeforePlacement;
	private boolean rlaAfterPlacement;
	private boolean rlaRetest;
	private boolean rlaPassFailIndicator;
	/**Changes for ER: JIMS200077481 ends.**/
	private Date mathTestDate;
	private String mathScore;
	private boolean mathBeforePlacement;
	private boolean mathAfterPlacement;
	private boolean mathRetest;
	private boolean mathPassFailIndicator;
	private Date scienceTestDate;
	private String scienceScore;
	private boolean scienceBeforePlacement;
	private boolean scienceAfterPlacement;
	private boolean scienceRetest;
	private boolean sciencePassFailIndicator;
	private Date socialStudiesTestDate;
	private String socialStudiesScore;
	private boolean socialStudiesBeforePlacement;
	private boolean socialStudiesAfterPlacement;
	private boolean socialStudiesRetest;
	private boolean socialStudiesPassFailIndicator;
	private boolean lockStatus;
	private String juvenileNum;
	private String version;
	private String totalScore;
	private boolean gedPassFailIndicator;
	private String gedProgramCodeId;
	private String gedProgramCodeDesc;
	private String otherProgram;
	
	private String totalIncomplete;
	
	public Date getReadingTestDate() {
		return readingTestDate;
	}
	public void setReadingTestDate(Date readingTestDate) {
		this.readingTestDate = readingTestDate;
	}
	public String getReadingScore() {
		return readingScore;
	}
	public void setReadingScore(String readingScore) {
		this.readingScore = readingScore;
	}
	public boolean isReadingBeforePlacement() {
		return readingBeforePlacement;
	}
	public void setReadingBeforePlacement(boolean readingBeforePlacement) {
		this.readingBeforePlacement = readingBeforePlacement;
	}
	public boolean isReadingAfterPlacement() {
		return readingAfterPlacement;
	}
	public void setReadingAfterPlacement(boolean readingAfterPlacement) {
		this.readingAfterPlacement = readingAfterPlacement;
	}
	public boolean isReadingRetest() {
		return readingRetest;
	}
	public void setReadingRetest(boolean readingRetest) {
		this.readingRetest = readingRetest;
	}
	public boolean isReadingPassFailIndicator() {
		return readingPassFailIndicator;
	}
	public void setReadingPassFailIndicator(boolean readingPassFailIndicator) {
		this.readingPassFailIndicator = readingPassFailIndicator;
	}
	public Date getWritingTestDate() {
		return writingTestDate;
	}
	public void setWritingTestDate(Date writingTestDate) {
		this.writingTestDate = writingTestDate;
	}
	public String getWritingScore() {
		return writingScore;
	}
	public void setWritingScore(String writingScore) {
		this.writingScore = writingScore;
	}
	public boolean isWritingBeforePlacement() {
		return writingBeforePlacement;
	}
	public void setWritingBeforePlacement(boolean writingBeforePlacement) {
		this.writingBeforePlacement = writingBeforePlacement;
	}
	public boolean isWritingAfterPlacement() {
		return writingAfterPlacement;
	}
	public void setWritingAfterPlacement(boolean writingAfterPlacement) {
		this.writingAfterPlacement = writingAfterPlacement;
	}
	public boolean isWritingRetest() {
		return writingRetest;
	}
	public void setWritingRetest(boolean writingRetest) {
		this.writingRetest = writingRetest;
	}
	public boolean isWritingPassFailIndicator() {
		return writingPassFailIndicator;
	}
	public void setWritingPassFailIndicator(boolean writingPassFailIndicator) {
		this.writingPassFailIndicator = writingPassFailIndicator;
	}
	public Date getMathTestDate() {
		return mathTestDate;
	}
	public void setMathTestDate(Date mathTestDate) {
		this.mathTestDate = mathTestDate;
	}
	public String getMathScore() {
		return mathScore;
	}
	public void setMathScore(String mathScore) {
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
	public String getScienceScore() {
		return scienceScore;
	}
	public void setScienceScore(String scienceScore) {
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
	public String getSocialStudiesScore() {
		return socialStudiesScore;
	}
	public void setSocialStudiesScore(String socialStudiesScore) {
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
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
	public String getGedProgramCodeDesc() {
		return gedProgramCodeDesc;
	}
	public void setGedProgramCodeDesc(String gedProgramCodeDesc) {
		this.gedProgramCodeDesc = gedProgramCodeDesc;
	}
	public String getCharterGEDId() {
		return charterGEDId;
	}
	public void setCharterGEDId(String charterGEDId) {
		this.charterGEDId = charterGEDId;
	}
	public Date getGEDEntryDate() {
		return GEDEntryDate;
	}
	public void setGEDEntryDate(Date entryDate) {
		GEDEntryDate = entryDate;
	}
	
	/**
	 * @param rlaTestDate the rlaTestDate to set
	 */
	public void setRlaTestDate(Date rlaTestDate) {
		this.rlaTestDate = rlaTestDate;
	}
	/**
	 * @return the rlaTestDate
	 */
	public Date getRlaTestDate() {
		return rlaTestDate;
	}
	/**
	 * @param rlaScore the rlaScore to set
	 */
	public void setRlaScore(String rlaScore) {
		this.rlaScore = rlaScore;
	}
	/**
	 * @return the rlaScore
	 */
	public String getRlaScore() {
		return rlaScore;
	}
	/**
	 * @param rlaBeforePlacement the rlaBeforePlacement to set
	 */
	public void setRlaBeforePlacement(boolean rlaBeforePlacement) {
		this.rlaBeforePlacement = rlaBeforePlacement;
	}
	/**
	 * @return the rlaBeforePlacement
	 */
	public boolean isRlaBeforePlacement() {
		return rlaBeforePlacement;
	}
	/**
	 * @param rlaAfterPlacement the rlaAfterPlacement to set
	 */
	public void setRlaAfterPlacement(boolean rlaAfterPlacement) {
		this.rlaAfterPlacement = rlaAfterPlacement;
	}
	/**
	 * @return the rlaAfterPlacement
	 */
	public boolean isRlaAfterPlacement() {
		return rlaAfterPlacement;
	}
	/**
	 * @param rlaRetest the rlaRetest to set
	 */
	public void setRlaRetest(boolean rlaRetest) {
		this.rlaRetest = rlaRetest;
	}
	/**
	 * @return the rlaRetest
	 */
	public boolean isRlaRetest() {
		return rlaRetest;
	}
	/**
	 * @param rlaPassFailIndicator the rlaPassFailIndicator to set
	 */
	public void setRlaPassFailIndicator(boolean rlaPassFailIndicator) {
		this.rlaPassFailIndicator = rlaPassFailIndicator;
	}
	/**
	 * @return the rlaPassFailIndicator
	 */
	public boolean isRlaPassFailIndicator() {
		return rlaPassFailIndicator;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.charterGEDId==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		CharterGEDResponseEvent evt = (CharterGEDResponseEvent)o;
		return evt.getCharterGEDId().compareTo(charterGEDId);
	}
	public String getTotalIncomplete() {
		return totalIncomplete;
	}
	public void setTotalIncomplete(String totalIncomplete) {
		this.totalIncomplete = totalIncomplete;
	}
	
	
}
