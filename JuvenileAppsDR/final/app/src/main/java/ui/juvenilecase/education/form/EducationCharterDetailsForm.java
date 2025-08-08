package ui.juvenilecase.education.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;

public class EducationCharterDetailsForm extends ActionForm {
	private boolean completed = false;
	private Date GEDEntryDate;
	private Date postReleaseStatusDate;
	private Date programCompletionDate;
	private Date programStartDate;
	private String GEDEntryDateStr;
	private String postReleaseStatusDateStr;
	private String programCompletionDateStr;
	private String programStartDateStr;
	private String charterGEDId;
	private boolean releasedFromFacility;
	private boolean showRetest = false;
	private String juvenileNum = "";
	private boolean lockStatus = false;
	private String otherProgramCode = "";
	private String otherProgramCodeId = "";
	private String otherText = ""; 
	private boolean passFailInd = false;
	private String program = "";
	private String programId = "";
	private String totalScore = "";
	private String version = "";
	private String mathScore = "";
	private Date mathTestDate;
	private String mathTestDateStr;
	private String mathBeforePlacementSelected;
	private String mathAfterPlacementSelected;
	private boolean mathBeforePlacement = false;
	private boolean mathAfterPlacement = false;
	private boolean mathRetest = false;
	private boolean mathPassFailInd = false;
	private boolean mathScoreNotApplicable = false;
	private boolean mathShowRetest = false;
	private String totalIncomplete = "";
	 /** Changes entries for ER: JIMS200077481 starts **/
	private boolean hasGEDEffectiveDateApplied=false;
	private String rlaScore = "";
	private Date   rlaTestDate;
	private String rlaTestDateStr;
	private String rlaBeforePlacementSelected;
	private String rlaAfterPlacementSelected;
	private boolean rlaBeforePlacement = false;
	private boolean rlaAfterPlacement = false;
	private boolean rlaRetest = false;
	private boolean rlaPassFailInd = false;
	private boolean rlaScoreNotApplicable = false;
	private boolean rlaShowRetest = false;
	/** Changes entries for ER: JIMS200077481 ends **/
	private String readingScore = "";
	private Date readingTestDate;
	private String readingTestDateStr;
	private String readingBeforePlacementSelected;
	private String readingAfterPlacementSelected;
	private boolean readingBeforePlacement = false;
	private boolean readingAfterPlacement = false;
	private boolean readingRetest = false;
	private boolean readingPassFailInd = false;
	private boolean readingScoreNotApplicable = false;
	private boolean readingShowRetest = false;
	private String scienceScore = "";
	private Date scienceTestDate;
	private String scienceTestDateStr;
	private String scienceBeforePlacementSelected;
	private String scienceAfterPlacementSelected;
	private boolean scienceBeforePlacement = false;
	private boolean scienceAfterPlacement = false;
	private boolean scienceRetest = false;
	private boolean sciencePassFailInd = false;
	private boolean scienceScoreNotApplicable = false;
	private boolean scienceShowRetest = false;
	private String socialStudiesScore = "";
	private String totalScoreScore = "";
	private String pass= "";
	private String fail= "";
	private String incomplete= "";
	private Date socialStudiesTestDate;
	private String socialStudiesTestDateStr;
	private String socialStudiesBeforePlacementSelected;
	private String socialStudiesAfterPlacementSelected;
	private boolean socialStudiesBeforePlacement = false;
	private boolean socialStudiesAfterPlacement = false;
	private boolean socialStudiesRetest = false;
	private boolean socialStudiesPassFailInd = false;
	private boolean socialStudiesScoreNotApplicable = false;
	private boolean socialStudiesShowRetest = false;
	private String writingScore = "";
	private Date writingTestDate;
	private String writingTestDateStr;
	private String writingBeforePlacementSelected;
	private String writingAfterPlacementSelected;	
	//Task 40495
	private String writingRlaPass;
	private String writingRlaFail;
	private String writingMathPass;
	private String writingMathFail;
	private String writingSciencePass;
	private String writingScienceFail;
	private String writingSocialPass;
	private String writingSocialFail;
	private String writingTotalPass;
	private String writingTotalFail;
	private String writingTotalIncomplete;
	private boolean writingBeforePlacement = false;
	private boolean writingAfterPlacement = false;
	private boolean writingRetest = false;
	private boolean writingPassFailInd = false;
	private boolean writingScoreNotApplicable = false;
	private boolean writingShowRetest = false;
	
	private String action = "";
	
	private String[] selectedIds;
	private List selectedList;
	private List selectedFromList;
	
	private static Collection emptyColl = new ArrayList();
	private Collection hsepProgramList;
	private Collection postReleaseTrackingList;
	private Collection vepProgramList;
	
	private Collection hsepProgramCodeList;
	private String hsepProgramCode = "";
	private String hsepProgramCodeId = "";
	private Collection vepProgramCodeList;
	private String vepProgramCode = "";
	private String vepProgramCodeId = "";
	private Collection postReleaseEmployedCodeList;
	private String postReleaseEmployedCode = "";
	private String postReleaseEmployedCodeId = "";
	
   
	
	
	public EducationCharterDetailsForm()
	{
		emptyColl = new ArrayList();
		action = "";
		completed = false;
		mathBeforePlacementSelected = "";
		mathAfterPlacementSelected = "";
		mathBeforePlacement = false;
		mathAfterPlacement = false;
		mathRetest = false;
		mathPassFailInd = false;
		mathScoreNotApplicable = false;
		passFailInd = false;
		setTotalIncomplete("");
		
		/**Add entries for ER: JIMS200077481 MJCW: 
		New GED Score Requirements(UI) starts **/
		rlaBeforePlacementSelected = "";
		rlaAfterPlacementSelected = "";
		rlaBeforePlacement = false;
		rlaAfterPlacement = false;
		rlaRetest = false;
		rlaPassFailInd = false;
		rlaScoreNotApplicable=false;
		/**Add entries for ER: JIMS200077481 MJCW: 
		New GED Score Requirements(UI) ends **/
		
		
		readingBeforePlacement = false;
		readingAfterPlacement = false;
		readingRetest = false;
		readingPassFailInd = false;
		readingScoreNotApplicable = false;
		scienceBeforePlacement = false;
		scienceAfterPlacement = false;
		scienceRetest = false;
		sciencePassFailInd = false;
		scienceScoreNotApplicable = false;
		socialStudiesBeforePlacement = false;
		socialStudiesAfterPlacement = false;
		socialStudiesRetest = false;
		socialStudiesPassFailInd = false;
		socialStudiesScoreNotApplicable = false;
		writingBeforePlacement = false;
		writingAfterPlacement = false;
		writingRetest = false;
		writingPassFailInd = false;
		writingScoreNotApplicable = false;
		writingRlaPass = "";
		writingRlaFail = "";
		writingMathPass = "";
		writingMathFail = "";
		writingSciencePass = "";
		writingScienceFail = "";
		writingSocialPass = "";
		writingSocialFail = "";
		writingTotalPass = "";
		writingTotalFail = "";
		writingTotalIncomplete = "";
		
		
	}
	
	public void clearAll() {
		
		GEDEntryDate = null;
		mathTestDate = null;
		postReleaseStatusDate = null;
		programCompletionDate = null;
		programStartDate = null;
		readingTestDate = null;
		scienceTestDate = null;
		socialStudiesTestDate = null;
		writingTestDate = null;
		rlaTestDate=null;
		
		juvenileNum = "";
		otherProgramCode = "";
		otherProgramCodeId = "";
		otherText = "";
		program = "";
		programId = "";
		totalScore = "";
		version = "";
		mathScore = "";
		readingScore = "";
		scienceScore = "";
		socialStudiesScore = "";
		writingScore = "";
		rlaScore="";
		totalScoreScore="";
		writingRlaPass= "";
		writingRlaFail ="";
		writingMathPass = "";
		writingMathFail = "";
		writingSciencePass = "";
		writingScienceFail = "";
		writingSocialPass ="";
		writingSocialFail = "";
		writingTotalPass ="";
		writingTotalFail = "";
		writingTotalIncomplete = "";
		totalIncomplete = "";
		
		
		
		
		
		
		
		
		
		completed = false;
		
		/**Add entries for ER: JIMS200077481 MJCW: 
		New GED Score Requirements(UI) starts **/
		rlaBeforePlacementSelected = "";
		rlaAfterPlacementSelected = "";
		rlaBeforePlacement = false;
		rlaAfterPlacement = false;
		rlaRetest = false;
		rlaPassFailInd = false;
		
		/**Add entries for ER: JIMS200077481 MJCW: 
		New GED Score Requirements(UI) ends **/
		mathBeforePlacementSelected = "";
		mathAfterPlacementSelected = "";
		mathBeforePlacement = false;
		mathAfterPlacement = false;
		mathRetest = false;
		mathPassFailInd = false;
		passFailInd = false;
		readingBeforePlacementSelected = "";
		readingAfterPlacementSelected = "";
		readingBeforePlacement = false;
		readingAfterPlacement = false;
		readingRetest = false;
		readingPassFailInd = false;
		scienceBeforePlacementSelected = "";
		scienceAfterPlacementSelected = "";
		scienceBeforePlacement = false;
		scienceAfterPlacement = false;
		scienceRetest = false;
		sciencePassFailInd = false;
		socialStudiesBeforePlacementSelected = "";
		socialStudiesAfterPlacementSelected = "";
		socialStudiesBeforePlacement = false;
		socialStudiesAfterPlacement = false;
		socialStudiesRetest = false;
		socialStudiesPassFailInd = false;
		writingBeforePlacementSelected = "";
		writingAfterPlacementSelected = "";
		writingBeforePlacement = false;
		writingAfterPlacement = false;
		writingRetest = false;
		writingPassFailInd = false;
		releasedFromFacility = false;
		selectedIds = null;
		selectedList = (List) emptyColl;
		selectedFromList = (List) emptyColl;
		hsepProgramList = emptyColl;
		postReleaseTrackingList = emptyColl;
		vepProgramList = emptyColl;
		hsepProgramCodeList = emptyColl;
		hsepProgramCode = "";
		hsepProgramCodeId = "";
        vepProgramCodeList = emptyColl;
		vepProgramCode = "";
		vepProgramCodeId = "";
		postReleaseEmployedCodeList = emptyColl;
		postReleaseEmployedCode = "";
		postReleaseEmployedCodeId = "";
	}


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the gEDEntryDate
	 */
	public Date getGEDEntryDate() {
		return GEDEntryDate;
	}

	/**
	 * @param entryDate the gEDEntryDate to set
	 */
	public void setGEDEntryDate(Date entryDate) {
		GEDEntryDate = entryDate;
	}

	/**
	 * @return the postReleaseStatusDate
	 */
	public Date getPostReleaseStatusDate() {
		return postReleaseStatusDate;
	}

	/**
	 * @param postReleaseStatusDate the postReleaseStatusDate to set
	 */
	public void setPostReleaseStatusDate(Date postReleaseStatusDate) {
		this.postReleaseStatusDate = postReleaseStatusDate;
	}

	/**
	 * @return the programCompletionDate
	 */
	public Date getProgramCompletionDate() {
		return programCompletionDate;
	}

	/**
	 * @param programCompletionDate the programCompletionDate to set
	 */
	public void setProgramCompletionDate(Date programCompletionDate) {
		this.programCompletionDate = programCompletionDate;
	}

	/**
	 * @return the programStartDate
	 */
	public Date getProgramStartDate() {
		return programStartDate;
	}

	/**
	 * @param programStartDate the programStartDate to set
	 */
	public void setProgramStartDate(Date programStartDate) {
		this.programStartDate = programStartDate;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the passFailInd
	 */
	public boolean isPassFailInd() {
		return passFailInd;
	}

	/**
	 * @param passFailInd the passFailInd to set
	 */
	public void setPassFailInd(boolean passFailInd) {
		this.passFailInd = passFailInd;
	}

	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the totalScore
	 */
	public String getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the mathScore
	 */
	public String getMathScore() {
		return mathScore;
	}

	/**
	 * @param mathScore the mathScore to set
	 */
	public void setMathScore(String mathScore) {
		this.mathScore = mathScore;
	}

	/**
	 * @return the mathBeforePlacement
	 */
	public boolean isMathBeforePlacement() {
		return mathBeforePlacement;
	}

	/**
	 * @param mathBeforePlacement the mathBeforePlacement to set
	 */
	public void setMathBeforePlacement(boolean mathBeforePlacement) {
		this.mathBeforePlacement = mathBeforePlacement;
	}

	/**
	 * @return the mathAfterPlacement
	 */
	public boolean isMathAfterPlacement() {
		return mathAfterPlacement;
	}

	/**
	 * @param mathAfterPlacement the mathAfterPlacement to set
	 */
	public void setMathAfterPlacement(boolean mathAfterPlacement) {
		this.mathAfterPlacement = mathAfterPlacement;
	} 

	public String getMathBeforePlacementSelected() {
		return mathBeforePlacementSelected;
	}

	public void setMathBeforePlacementSelected(String mathBeforePlacementSelected) {
		this.mathBeforePlacementSelected = mathBeforePlacementSelected;
	}

	public String getMathAfterPlacementSelected() {
		return mathAfterPlacementSelected;
	}

	public void setMathAfterPlacementSelected(String mathAfterPlacementSelected) {
		this.mathAfterPlacementSelected = mathAfterPlacementSelected;
	}

	/**
	 * @return the mathRetest
	 */
	public boolean isMathRetest() {
		return mathRetest;
	}
	/**
	 * @param mathRetest the mathRetest to set
	 */
	public void setMathRetest(boolean mathRetest) {
		this.mathRetest = mathRetest;
	}

	/**
	 * @return the mathPassFailInd
	 */
	public boolean isMathPassFailInd() {
		return mathPassFailInd;
	}

	/**
	 * @param mathPassFailInd the mathPassFailInd to set
	 */
	public void setMathPassFailInd(boolean mathPassFailInd) {
		this.mathPassFailInd = mathPassFailInd;
	}

	/**
	 * @return the readingScore
	 */
	public String getReadingScore() {
		return readingScore;
	}

	/**
	 * @param readingScore the readingScore to set
	 */
	public void setReadingScore(String readingScore) {
		this.readingScore = readingScore;
	}

	/**
	 * @return the readingBeforePlacement
	 */
	public boolean isReadingBeforePlacement() {
		return readingBeforePlacement;
	}

	/**
	 * @param readingBeforePlacement the readingBeforePlacement to set
	 */
	public void setReadingBeforePlacement(boolean readingBeforePlacement) {
		this.readingBeforePlacement = readingBeforePlacement;
	}

	/**
	 * @return the readingAfterPlacement
	 */
	public boolean isReadingAfterPlacement() {
		return readingAfterPlacement;
	}

	/**
	 * @param readingAfterPlacement the readingAfterPlacement to set
	 */
	public void setReadingAfterPlacement(boolean readingAfterPlacement) {
		this.readingAfterPlacement = readingAfterPlacement;
	}

	/**
	 * @return the readingBeforePlacementSelected
	 */
	public String getReadingBeforePlacementSelected() {
		return readingBeforePlacementSelected;
	}

	/**
	 * @param readingBeforePlacementSelected the readingBeforePlacementSelected to set
	 */
	public void setReadingBeforePlacementSelected(
			String readingBeforePlacementSelected) {
		this.readingBeforePlacementSelected = readingBeforePlacementSelected;
	}

	/**
	 * @return the readingAfterPlacementSelected
	 */
	public String getReadingAfterPlacementSelected() {
		return readingAfterPlacementSelected;
	}

	/**
	 * @param readingAfterPlacementSelected the readingAfterPlacementSelected to set
	 */
	public void setReadingAfterPlacementSelected(
			String readingAfterPlacementSelected) {
		this.readingAfterPlacementSelected = readingAfterPlacementSelected;
	}

	/**
	 * @return the readingRetest
	 */
	public boolean isReadingRetest() {
		return readingRetest;
	}

	/**
	 * @param readingRetest the readingRetest to set
	 */
	public void setReadingRetest(boolean readingRetest) {
		this.readingRetest = readingRetest;
	}

	/**
	 * @return the readingPassFailInd
	 */
	public boolean isReadingPassFailInd() {
		return readingPassFailInd;
	}

	/**
	 * @param readingPassFailInd the readingPassFailInd to set
	 */
	public void setReadingPassFailInd(boolean readingPassFailInd) {
		this.readingPassFailInd = readingPassFailInd;
	}

	/**
	 * @return the scienceScore
	 */
	public String getScienceScore() {
		return scienceScore;
	}

	/**
	 * @param scienceScore the scienceScore to set
	 */
	public void setScienceScore(String scienceScore) {
		this.scienceScore = scienceScore;
	}

	/**
	 * @return the scienceBeforePlacement
	 */
	public boolean isScienceBeforePlacement() {
		return scienceBeforePlacement;
	}

	/**
	 * @param scienceBeforePlacement the scienceBeforePlacement to set
	 */
	public void setScienceBeforePlacement(boolean scienceBeforePlacement) {
		this.scienceBeforePlacement = scienceBeforePlacement;
	}

	/**
	 * @return the scienceAfterPlacement
	 */
	public boolean isScienceAfterPlacement() {
		return scienceAfterPlacement;
	}

	/**
	 * @param scienceAfterPlacement the scienceAfterPlacement to set
	 */
	public void setScienceAfterPlacement(boolean scienceAfterPlacement) {
		this.scienceAfterPlacement = scienceAfterPlacement;
	}

	/**
	 * @return the scienceBeforePlacementSelected
	 */
	public String getScienceBeforePlacementSelected() {
		return scienceBeforePlacementSelected;
	}

	/**
	 * @param scienceBeforePlacementSelected the scienceBeforePlacementSelected to set
	 */
	public void setScienceBeforePlacementSelected(
			String scienceBeforePlacementSelected) {
		this.scienceBeforePlacementSelected = scienceBeforePlacementSelected;
	}

	/**
	 * @return the scienceAfterPlacementSelected
	 */
	public String getScienceAfterPlacementSelected() {
		return scienceAfterPlacementSelected;
	}

	/**
	 * @param scienceAfterPlacementSelected the scienceAfterPlacementSelected to set
	 */
	public void setScienceAfterPlacementSelected(
			String scienceAfterPlacementSelected) {
		this.scienceAfterPlacementSelected = scienceAfterPlacementSelected;
	}

	/**
	 * @return the scienceRetest
	 */
	public boolean isScienceRetest() {
		return scienceRetest;
	}

	/**
	 * @param scienceRetest the scienceRetest to set
	 */
	public void setScienceRetest(boolean scienceRetest) {
		this.scienceRetest = scienceRetest;
	}

	/**
	 * @return the sciencePassFailInd
	 */
	public boolean isSciencePassFailInd() {
		return sciencePassFailInd;
	}

	/**
	 * @param sciencePassFailInd the sciencePassFailInd to set
	 */
	public void setSciencePassFailInd(boolean sciencePassFailInd) {
		this.sciencePassFailInd = sciencePassFailInd;
	}

	/**
	 * @return the socialStudiesScore
	 */
	public String getSocialStudiesScore() {
		return socialStudiesScore;
	}

	/**
	 * @param socialStudiesScore the socialStudiesScore to set
	 */
	public void setSocialStudiesScore(String socialStudiesScore) {
		this.socialStudiesScore = socialStudiesScore;
	}

	/**
	 * @return the socialStudiesBeforePlacement
	 */
	public boolean isSocialStudiesBeforePlacement() {
		return socialStudiesBeforePlacement;
	}

	/**
	 * @param socialStudiesBeforePlacement the socialStudiesBeforePlacement to set
	 */
	public void setSocialStudiesBeforePlacement(boolean socialStudiesBeforePlacement) {
		this.socialStudiesBeforePlacement = socialStudiesBeforePlacement;
	}

	/**
	 * @return the socialStudiesAfterPlacement
	 */
	public boolean isSocialStudiesAfterPlacement() {
		return socialStudiesAfterPlacement;
	}

	/**
	 * @param socialStudiesAfterPlacement the socialStudiesAfterPlacement to set
	 */
	public void setSocialStudiesAfterPlacement(boolean socialStudiesAfterPlacement) {
		this.socialStudiesAfterPlacement = socialStudiesAfterPlacement;
	}

	/**
	 * @return the socialStudiesBeforePlacementSelected
	 */
	public String getSocialStudiesBeforePlacementSelected() {
		return socialStudiesBeforePlacementSelected;
	}

	/**
	 * @param socialStudiesBeforePlacementSelected the socialStudiesBeforePlacementSelected to set
	 */
	public void setSocialStudiesBeforePlacementSelected(
			String socialStudiesBeforePlacementSelected) {
		this.socialStudiesBeforePlacementSelected = socialStudiesBeforePlacementSelected;
	}

	/**
	 * @return the socialStudiesAfterPlacementSelected
	 */
	public String getSocialStudiesAfterPlacementSelected() {
		return socialStudiesAfterPlacementSelected;
	}

	/**
	 * @param socialStudiesAfterPlacementSelected the socialStudiesAfterPlacementSelected to set
	 */
	public void setSocialStudiesAfterPlacementSelected(
			String socialStudiesAfterPlacementSelected) {
		this.socialStudiesAfterPlacementSelected = socialStudiesAfterPlacementSelected;
	}

	/**
	 * @return the socialStudiesRetest
	 */
	public boolean isSocialStudiesRetest() {
		return socialStudiesRetest;
	}

	/**
	 * @param socialStudiesRetest the socialStudiesRetest to set
	 */
	public void setSocialStudiesRetest(boolean socialStudiesRetest) {
		this.socialStudiesRetest = socialStudiesRetest;
	}

	/**
	 * @return the socialStudiesPassFailInd
	 */
	public boolean isSocialStudiesPassFailInd() {
		return socialStudiesPassFailInd;
	}

	/**
	 * @param socialStudiesPassFailInd the socialStudiesPassFailInd to set
	 */
	public void setSocialStudiesPassFailInd(boolean socialStudiesPassFailInd) {
		this.socialStudiesPassFailInd = socialStudiesPassFailInd;
	}

	/**
	 * @return the writingScore
	 */
	public String getWritingScore() {
		return writingScore;
	}

	/**
	 * @param writingScore the writingScore to set
	 */
	public void setWritingScore(String writingScore) {
		this.writingScore = writingScore;
	}

	/**
	 * @return the writingBeforePlacement
	 */
	public boolean isWritingBeforePlacement() {
		return writingBeforePlacement;
	}

	/**
	 * @param writingBeforePlacement the writingBeforePlacement to set
	 */
	public void setWritingBeforePlacement(boolean writingBeforePlacement) {
		this.writingBeforePlacement = writingBeforePlacement;
	}

	/**
	 * @return the writingAfterPlacement
	 */
	public boolean isWritingAfterPlacement() {
		return writingAfterPlacement;
	}

	/**
	 * @param writingAfterPlacement the writingAfterPlacement to set
	 */
	public void setWritingAfterPlacement(boolean writingAfterPlacement) {
		this.writingAfterPlacement = writingAfterPlacement;
	}

	/**
	 * @return the writingBeforePlacementSelected
	 */
	public String getWritingBeforePlacementSelected() {
		return writingBeforePlacementSelected;
	}

	/**
	 * @param writingBeforePlacementSelected the writingBeforePlacementSelected to set
	 */
	public void setWritingBeforePlacementSelected(
			String writingBeforePlacementSelected) {
		this.writingBeforePlacementSelected = writingBeforePlacementSelected;
	}

	/**
	 * @return the writingAfterPlacementSelected
	 */
	public String getWritingAfterPlacementSelected() {
		return writingAfterPlacementSelected;
	}

	/**
	 * @param writingAfterPlacementSelected the writingAfterPlacementSelected to set
	 */
	public void setWritingAfterPlacementSelected(
			String writingAfterPlacementSelected) {
		this.writingAfterPlacementSelected = writingAfterPlacementSelected;
	}

	/**
	 * @return the writingRetest
	 */
	public boolean isWritingRetest() {
		return writingRetest;
	}

	/**
	 * @param writingRetest the writingRetest to set
	 */
	public void setWritingRetest(boolean writingRetest) {
		this.writingRetest = writingRetest;
	}

	/**
	 * @return the writingPassFailInd
	 */
	public boolean isWritingPassFailInd() {
		return writingPassFailInd;
	}

	/**
	 * @param writingPassFailInd the writingPassFailInd to set
	 */
	public void setWritingPassFailInd(boolean writingPassFailInd) {
		this.writingPassFailInd = writingPassFailInd;
	}

	/**
	 * @return the hsepProgramList
	 */
	public Collection getHsepProgramList() {
		return hsepProgramList;
	}

	/**
	 * @param hsepProgramList the hsepProgramList to set
	 */
	public void setHsepProgramList(Collection hsepProgramList) {
		this.hsepProgramList = hsepProgramList;
	}

	/**
	 * @return the postReleaseTrackingList
	 */
	public Collection getPostReleaseTrackingList() {
		return postReleaseTrackingList;
	}

	/**
	 * @param postReleaseTrackingList the postReleaseTrackingList to set
	 */
	public void setPostReleaseTrackingList(Collection postReleaseTrackingList) {
		this.postReleaseTrackingList = postReleaseTrackingList;
	}

	/**
	 * @return the vepProgramList
	 */
	public Collection getVepProgramList() {
		return vepProgramList;
	}

	/**
	 * @param vepProgramList the vepProgramList to set
	 */
	public void setVepProgramList(Collection vepProgramList) {
		this.vepProgramList = vepProgramList;
	}

	/**
	 * @return the mathTestDate
	 */
	public Date getMathTestDate() {
		return mathTestDate;
	}

	/**
	 * @param mathTestDate the mathTestDate to set
	 */
	public void setMathTestDate(Date mathTestDate) {
		this.mathTestDate = mathTestDate;
	}

	/**
	 * @return the readingTestDate
	 */
	public Date getReadingTestDate() {
		return readingTestDate;
	}

	/**
	 * @param readingTestDate the readingTestDate to set
	 */
	public void setReadingTestDate(Date readingTestDate) {
		this.readingTestDate = readingTestDate;
	}

	/**
	 * @return the scienceTestDate
	 */
	public Date getScienceTestDate() {
		return scienceTestDate;
	}

	/**
	 * @param scienceTestDate the scienceTestDate to set
	 */
	public void setScienceTestDate(Date scienceTestDate) {
		this.scienceTestDate = scienceTestDate;
	}

	/**
	 * @return the socialStudiesTestDate
	 */
	public Date getSocialStudiesTestDate() {
		return socialStudiesTestDate;
	}

	/**
	 * @param socialStudiesTestDate the socialStudiesTestDate to set
	 */
	public void setSocialStudiesTestDate(Date socialStudiesTestDate) {
		this.socialStudiesTestDate = socialStudiesTestDate;
	}

	/**
	 * @return the writingTestDate
	 */
	public Date getWritingTestDate() {
		return writingTestDate;
	}

	/**
	 * @param writingTestDate the writingTestDate to set
	 */
	public void setWritingTestDate(Date writingTestDate) {
		this.writingTestDate = writingTestDate;
	}

	/**
	 * @return the gEDEntryDateStr
	 */
	public String getGEDEntryDateStr() {
        if (GEDEntryDate != null)
        {
        	GEDEntryDateStr = DateUtil.dateToString(GEDEntryDate, UIConstants.DATE_FMT_1);
        }		
		return GEDEntryDateStr;
	}

	/**
	 * @param entryDateStr the gEDEntryDateStr to set
	 */
	public void setGEDEntryDateStr(String entryDateStr) {
		this.GEDEntryDateStr = entryDateStr;
		if(entryDateStr != null && entryDateStr.length() > 0)
		{
			GEDEntryDate = DateUtil.stringToDate(entryDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			GEDEntryDate = null;
		}
	}

	/**
	 * @return the postReleaseStatusDateStr
	 */
	public String getPostReleaseStatusDateStr() {
	    if (postReleaseStatusDate != null)
	    {
	  	   postReleaseStatusDateStr = DateUtil.dateToString(postReleaseStatusDate, UIConstants.DATE_FMT_1);
	    }		
		return postReleaseStatusDateStr;
	}

	/**
	 * @param postReleaseStatusDateStr the postReleaseStatusDateStr to set
	 */
	public void setPostReleaseStatusDateStr(String postReleaseStatusDateStr) {
		this.postReleaseStatusDateStr = postReleaseStatusDateStr;
		if(postReleaseStatusDateStr != null && postReleaseStatusDateStr.length() > 0)
		{
			postReleaseStatusDate = DateUtil.stringToDate(postReleaseStatusDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			postReleaseStatusDate = null;
		}
	}

	/**
	 * @return the programCompletionDateStr
	 */
	public String getProgramCompletionDateStr() {
	    if (programCompletionDate != null)
	    {
	    	programCompletionDateStr = DateUtil.dateToString(programCompletionDate, UIConstants.DATE_FMT_1);
	    }		
		return programCompletionDateStr;
	}

	/**
	 * @param programCompletionDateStr the programCompletionDateStr to set
	 */
	public void setProgramCompletionDateStr(String programCompletionDateStr) {
		this.programCompletionDateStr = programCompletionDateStr;
		if(programCompletionDateStr != null && programCompletionDateStr.length() > 0)
		{
			programCompletionDate = DateUtil.stringToDate(programCompletionDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			programCompletionDate = null;
		}
	}

	/**
	 * @return the programStartDateStr
	 */
	public String getProgramStartDateStr() {
	    if (programStartDate != null)
	    {
	    	programStartDateStr = DateUtil.dateToString(programStartDate, UIConstants.DATE_FMT_1);
	    }		
		return programStartDateStr;
	}

	/**
	 * @param programStartDateStr the programStartDateStr to set
	 */
	public void setProgramStartDateStr(String programStartDateStr) {
		this.programStartDateStr = programStartDateStr;
		if(programStartDateStr != null && programStartDateStr.length() > 0)
		{
			programStartDate = DateUtil.stringToDate(programStartDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			programStartDate = null;
		}
	}

	/**
	 * @return the mathTestDateStr
	 */
	public String getMathTestDateStr() {
	    if (mathTestDate != null)
	    {
	    	mathTestDateStr = DateUtil.dateToString(mathTestDate, UIConstants.DATE_FMT_1);
	    }
		return mathTestDateStr;
	}

	/**
	 * @param mathTestDateStr the mathTestDateStr to set
	 */
	public void setMathTestDateStr(String mathTestDateStr) {
		this.mathTestDateStr = mathTestDateStr;
		if(mathTestDateStr != null && mathTestDateStr.length() > 0)
		{
			mathTestDate = DateUtil.stringToDate(mathTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			mathTestDate = null;
		}
	}

	/**
	 * @return the rlaTestDateStr
	 */
	public String getRlaTestDateStr() {
	    if (rlaTestDate != null)
	    {
	    	rlaTestDateStr = DateUtil.dateToString(rlaTestDate, UIConstants.DATE_FMT_1);
	    }
		return rlaTestDateStr;
	}

	/**
	 * @param readingTestDateStr the readingTestDateStr to set
	 */
	public void setRlaTestDateStr(String rlaTestDateStr) {
		this.rlaTestDateStr = rlaTestDateStr;
		if(rlaTestDateStr != null && rlaTestDateStr.length() > 0)
		{
			rlaTestDate = DateUtil.stringToDate(rlaTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			rlaTestDate = null;
		}
	}
	
	/**
	 * @return the readingTestDateStr
	 */
	public String getReadingTestDateStr() {
	    if (readingTestDate != null)
	    {
	    	readingTestDateStr = DateUtil.dateToString(readingTestDate, UIConstants.DATE_FMT_1);
	    }
		return readingTestDateStr;
	}

	/**
	 * @param readingTestDateStr the readingTestDateStr to set
	 */
	public void setReadingTestDateStr(String readingTestDateStr) {
		this.readingTestDateStr = readingTestDateStr;
		if(readingTestDateStr != null && readingTestDateStr.length() > 0)
		{
			readingTestDate = DateUtil.stringToDate(readingTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			readingTestDate = null;
		}
	}

	/**
	 * @return the scienceTestDateStr
	 */
	public String getScienceTestDateStr() {
	    if (scienceTestDate != null)
	    {
	    	scienceTestDateStr = DateUtil.dateToString(scienceTestDate, UIConstants.DATE_FMT_1);
	    }
		return scienceTestDateStr;
	}

	/**
	 * @param scienceTestDateStr the scienceTestDateStr to set
	 */
	public void setScienceTestDateStr(String scienceTestDateStr) {
		this.scienceTestDateStr = scienceTestDateStr;
		if(scienceTestDateStr != null && scienceTestDateStr.length() > 0)
		{
			scienceTestDate = DateUtil.stringToDate(scienceTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			scienceTestDate = null;
		}
	}

	/**
	 * @return the socialStudiesTestDateStr
	 */
	public String getSocialStudiesTestDateStr() {
	    if (socialStudiesTestDate != null)
	    {
	    	socialStudiesTestDateStr = DateUtil.dateToString(socialStudiesTestDate, UIConstants.DATE_FMT_1);
	    }
		return socialStudiesTestDateStr;
	}

	/**
	 * @param socialStudiesTestDateStr the socialStudiesTestDateStr to set
	 */
	public void setSocialStudiesTestDateStr(String socialStudiesTestDateStr) {
		this.socialStudiesTestDateStr = socialStudiesTestDateStr;
		if(socialStudiesTestDateStr != null && socialStudiesTestDateStr.length() > 0)
		{
			socialStudiesTestDate = DateUtil.stringToDate(socialStudiesTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			socialStudiesTestDate = null;
		}
	}

	/**
	 * @return the writingTestDateStr
	 */
	public String getWritingTestDateStr() {
	    if (writingTestDate != null)
	    {
	    	writingTestDateStr = DateUtil.dateToString(writingTestDate, UIConstants.DATE_FMT_1);
	    }
		return writingTestDateStr;
	}

	/**
	 * @param writingTestDateStr the writingTestDateStr to set
	 */
	public void setWritingTestDateStr(String writingTestDateStr) {
		this.writingTestDateStr = writingTestDateStr;
		if(writingTestDateStr != null && writingTestDateStr.length() > 0)
		{
			writingTestDate = DateUtil.stringToDate(writingTestDateStr, UIConstants.DATE_FMT_1);
		}	
		else {
			writingTestDate = null;
		}
	}

	/**
	 * @return the otherText
	 */
	public String getOtherText() {
		return otherText;
	}

	/**
	 * @param otherText the otherText to set
	 */
	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public String getOtherProgramCode() {
		return otherProgramCode;
	}

	public void setOtherProgramCode(String otherProgramCode) {
		this.otherProgramCode = otherProgramCode;
	}

	public String getOtherProgramCodeId() {
		return otherProgramCodeId;
	}

	public void setOtherProgramCodeId(String otherProgramCodeId) {
		this.otherProgramCodeId = otherProgramCodeId;
	}

	public Collection getHsepProgramCodeList() {
		return hsepProgramCodeList;
	}

	public void setHsepProgramCodeList(Collection hsepProgramCodeList) {
		this.hsepProgramCodeList = hsepProgramCodeList;
	}

	public String getHsepProgramCode() {
		return hsepProgramCode;
	}

	public void setHsepProgramCode(String hsepProgramCode) {
		this.hsepProgramCode = hsepProgramCode;
	}

	public String getHsepProgramCodeId() {
		return hsepProgramCodeId;
	}

	public void setHsepProgramCodeId(String hsepProgramCodeId) {
		this.hsepProgramCodeId = hsepProgramCodeId;
	}

	public Collection getVepProgramCodeList() {
		return vepProgramCodeList;
	}

	public void setVepProgramCodeList(Collection vepProgramCodeList) {
		this.vepProgramCodeList = vepProgramCodeList;
	}

	public String getVepProgramCode() {
		return vepProgramCode;
	}

	public void setVepProgramCode(String vepProgramCode) {
		this.vepProgramCode = vepProgramCode;
	}

	public String getVepProgramCodeId() {
		return vepProgramCodeId;
	}

	public void setVepProgramCodeId(String vepProgramCodeId) {
		this.vepProgramCodeId = vepProgramCodeId;
	}

	public Collection getPostReleaseEmployedCodeList() {
		return postReleaseEmployedCodeList;
	}

	public void setPostReleaseEmployedCodeList(
			Collection postReleaseEmployedCodeList) {
		this.postReleaseEmployedCodeList = postReleaseEmployedCodeList;
	}

	public String getPostReleaseEmployedCode() {
		return postReleaseEmployedCode;
	}

	public void setPostReleaseEmployedCode(String postReleaseEmployedCode) {
		this.postReleaseEmployedCode = postReleaseEmployedCode;
	}

	public String getPostReleaseEmployedCodeId() {
		return postReleaseEmployedCodeId;
	}

	public void setPostReleaseEmployedCodeId(String postReleaseEmployedCodeId) {
		this.postReleaseEmployedCodeId = postReleaseEmployedCodeId;
	}

	public String[] getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String[] selectedIds) {
		this.selectedIds = selectedIds;
	}

	public List getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List selectedList) {
		this.selectedList = selectedList;
	}

	public List getSelectedFromList() {
		return selectedFromList;
	}

	public void setSelectedFromList(List selectedFromList) {
		this.selectedFromList = selectedFromList;
	}

	public boolean isLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(boolean lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getCharterGEDId() {
		return charterGEDId;
	}

	public void setCharterGEDId(String charterGEDId) {
		this.charterGEDId = charterGEDId;
	}

	public boolean isReleasedFromFacility() {
		return releasedFromFacility;
	}

	public void setReleasedFromFacility(boolean releasedFromFacility) {
		this.releasedFromFacility = releasedFromFacility;
	}

	public boolean isMathScoreNotApplicable() {
		return mathScoreNotApplicable;
	}

	public void setMathScoreNotApplicable(boolean mathScoreNotApplicable) {
		this.mathScoreNotApplicable = mathScoreNotApplicable;
	}

	public boolean isReadingScoreNotApplicable() {
		return readingScoreNotApplicable;
	}

	public void setReadingScoreNotApplicable(boolean readingScoreNotApplicable) {
		this.readingScoreNotApplicable = readingScoreNotApplicable;
	}

	public boolean isScienceScoreNotApplicable() {
		return scienceScoreNotApplicable;
	}

	public void setScienceScoreNotApplicable(boolean scienceScoreNotApplicable) {
		this.scienceScoreNotApplicable = scienceScoreNotApplicable;
	}

	public boolean isSocialStudiesScoreNotApplicable() {
		return socialStudiesScoreNotApplicable;
	}

	public void setSocialStudiesScoreNotApplicable(
			boolean socialStudiesScoreNotApplicable) {
		this.socialStudiesScoreNotApplicable = socialStudiesScoreNotApplicable;
	}

	public boolean isWritingScoreNotApplicable() {
		return writingScoreNotApplicable;
	}

	public void setWritingScoreNotApplicable(boolean writingScoreNotApplicable) {
		this.writingScoreNotApplicable = writingScoreNotApplicable;
	}

	public boolean isMathShowRetest() {
		return mathShowRetest;
	}

	public void setMathShowRetest(boolean mathShowRetest) {
		this.mathShowRetest = mathShowRetest;
	}

	public boolean isReadingShowRetest() {
		return readingShowRetest;
	}

	public void setReadingShowRetest(boolean readingShowRetest) {
		this.readingShowRetest = readingShowRetest;
	}

	public boolean isScienceShowRetest() {
		return scienceShowRetest;
	}

	public void setScienceShowRetest(boolean scienceShowRetest) {
		this.scienceShowRetest = scienceShowRetest;
	}

	public boolean isSocialStudiesShowRetest() {
		return socialStudiesShowRetest;
	}

	public void setSocialStudiesShowRetest(boolean socialStudiesShowRetest) {
		this.socialStudiesShowRetest = socialStudiesShowRetest;
	}

	public boolean isWritingShowRetest() {
		return writingShowRetest;
	}

	public void setWritingShowRetest(boolean writingShowRetest) {
		this.writingShowRetest = writingShowRetest;
	}

	public boolean isShowRetest() {
		return showRetest;
	}

	public void setShowRetest(boolean showRetest) {
		this.showRetest = showRetest;
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
	 * @param rlaBeforePlacementSelected the rlaBeforePlacementSelected to set
	 */
	public void setRlaBeforePlacementSelected(String rlaBeforePlacementSelected) {
		this.rlaBeforePlacementSelected = rlaBeforePlacementSelected;
	}

	/**
	 * @return the rlaBeforePlacementSelected
	 */
	public String getRlaBeforePlacementSelected() {
		return rlaBeforePlacementSelected;
	}

	/**
	 * @param rlaAfterPlacementSelected the rlaAfterPlacementSelected to set
	 */
	public void setRlaAfterPlacementSelected(String rlaAfterPlacementSelected) {
		this.rlaAfterPlacementSelected = rlaAfterPlacementSelected;
	}

	/**
	 * @return the rlaAfterPlacementSelected
	 */
	public String getRlaAfterPlacementSelected() {
		return rlaAfterPlacementSelected;
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
	 * @param rlaPassFailInd the rlaPassFailInd to set
	 */
	public void setRlaPassFailInd(boolean rlaPassFailInd) {
		this.rlaPassFailInd = rlaPassFailInd;
	}

	/**
	 * @return the rlaPassFailInd
	 */
	public boolean isRlaPassFailInd() {
		return rlaPassFailInd;
	}

	/**
	 * @param rlaShowRetest the rlaShowRetest to set
	 */
	public void setRlaShowRetest(boolean rlaShowRetest) {
		this.rlaShowRetest = rlaShowRetest;
	}

	/**
	 * @return the rlaShowRetest
	 */
	public boolean isRlaShowRetest() {
		return rlaShowRetest;
	}

	/**
	 * @param rlaScoreNotApplicable the rlaScoreNotApplicable to set
	 */
	public void setRlaScoreNotApplicable(boolean rlaScoreNotApplicable) {
		this.rlaScoreNotApplicable = rlaScoreNotApplicable;
	}

	/**
	 * @return the rlaScoreNotApplicable
	 */
	public boolean isRlaScoreNotApplicable() {
		return rlaScoreNotApplicable;
	}

	
	public void setHasGEDEffectiveDateApplied(boolean hasGEDEffectiveDateApplied) {
		this.hasGEDEffectiveDateApplied = hasGEDEffectiveDateApplied;
	}

	public boolean isHasGEDEffectiveDateApplied() {
		return hasGEDEffectiveDateApplied;
	}

	public String getTotalScoreScore() {
		return totalScoreScore;
	}

	public void setTotalScoreScore(String totalScoreScore) {
		this.totalScoreScore = totalScoreScore;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIncomplete() {
		return incomplete;
	}

	public void setIncomplete(String incomplete) {
		incomplete = incomplete;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public String getWritingRlaPass() {
		return writingRlaPass;
	}

	public void setWritingRlaPass(String writingRlaPass) {
		this.writingRlaPass = writingRlaPass;
	}

	public String getWritingRlaFail() {
		return writingRlaFail;
	}

	public void setWritingRlaFail(String writingRlaFail) {
		this.writingRlaFail = writingRlaFail;
	}

	public String getWritingMathPass() {
		return writingMathPass;
	}

	public void setWritingMathPass(String writingMathPass) {
		this.writingMathPass = writingMathPass;
	}

	public String getWritingMathFail() {
		return writingMathFail;
	}

	public void setWritingMathFail(String writingMathFail) {
		this.writingMathFail = writingMathFail;
	}

	public String getWritingSciencePass() {
		return writingSciencePass;
	}

	public void setWritingSciencePass(String writingSciencePass) {
		this.writingSciencePass = writingSciencePass;
	}

	public String getWritingScienceFail() {
		return writingScienceFail;
	}

	public void setWritingScienceFail(String writingScienceFail) {
		this.writingScienceFail = writingScienceFail;
	}

	public String getWritingSocialPass() {
		return writingSocialPass;
	}

	public void setWritingSocialPass(String writingSocialPass) {
		this.writingSocialPass = writingSocialPass;
	}

	public String getWritingSocialFail() {
		return writingSocialFail;
	}

	public void setWritingSocialFail(String writingSocialFail) {
		this.writingSocialFail = writingSocialFail;
	}

	public String getWritingTotalPass() {
		return writingTotalPass;
	}

	public void setWritingTotalPass(String writingTotalPass) {
		this.writingTotalPass = writingTotalPass;
	}

	public String getWritingTotalFail() {
		return writingTotalFail;
	}

	public void setWritingTotalFail(String writingTotalFail) {
		this.writingTotalFail = writingTotalFail;
	}

	public String getWritingTotalIncomplete() {
		return writingTotalIncomplete;
	}

	public void setWritingTotalIncomplete(String writingTotalIncomplete) {
		this.writingTotalIncomplete = writingTotalIncomplete;
	}

	public String getTotalIncomplete() {
		return totalIncomplete;
	}

	public void setTotalIncomplete(String totalIncomplete) {
		this.totalIncomplete = totalIncomplete;
	}

	

	
	
}
