package pd.juvenile;

import messaging.juvenile.reply.CharterGEDResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;

import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;
import pd.codetable.PDCodeHelper;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class JuvenileCharterGED extends PersistentObject
{
	private Date gedEntryDate;
	private Date readingTestDate;
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
	private boolean writingPassFailIndicator;
	/**Changes for ER: JIMS200077481  Ends**/
	private Date rlaTestDate;
	private int rlaScore;
	private boolean rlaBeforePlacement;
	private boolean rlaAfterPlacement;
	private boolean rlaRetest;
	private boolean rlaPassFailIndicator;
	/**Changes for ER: JIMS200077481 Ends**/
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
	/**
	 * 
	 * @contextKey GED_PROGRAM
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 */
	private Code gedProgramCode = null;
	private String gedProgramCodeId;
	private String otherProgram;


	static public JuvenileCharterGED find(String oid)
	{
		IHome home = new Home();
		JuvenileCharterGED juvenileCharterGED = (JuvenileCharterGED) home.find(oid, JuvenileCharterGED.class);
		return juvenileCharterGED;
	}

	public static Iterator findAll(IEvent event){
		IHome home = new Home();
		return home.findAll(event, JuvenileCharterGED.class);
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator charters = home.findAll(attrName, attrValue, JuvenileCharterGED.class);
		return charters;
	}

	public Date getReadingTestDate()
	{
		fetch();
		return readingTestDate;
	}

	public void setReadingTestDate(Date readingTestDate)
	{
		if (this.readingTestDate == null || !this.readingTestDate.equals(readingTestDate))
		{
			markModified();
		}
		this.readingTestDate = readingTestDate;
	}

	public int getReadingScore()
	{
		fetch();
		return readingScore;
	}

	public void setReadingScore(int readingScore)
	{
		if (this.readingScore != readingScore)
		{
			markModified();
		}
		this.readingScore = readingScore;
	}

	public boolean isReadingBeforePlacement()
	{
		fetch();
		return readingBeforePlacement;
	}

	public void setReadingBeforePlacement(boolean readingBeforePlacement)
	{
		if (this.readingBeforePlacement != readingBeforePlacement)
		{
			markModified();
		}
		this.readingBeforePlacement = readingBeforePlacement;
	}

	public boolean isReadingAfterPlacement()
	{
		fetch();
		return readingAfterPlacement;
	}

	public void setReadingAfterPlacement(boolean readingAfterPlacement)
	{
		if (this.readingAfterPlacement != readingAfterPlacement)
		{
			markModified();
		}
		this.readingAfterPlacement = readingAfterPlacement;
	}

	public boolean isReadingRetest()
	{
		fetch();
		return readingRetest;
	}

	public void setReadingRetest(boolean readingRetest)
	{
		if (this.readingRetest != readingRetest)
		{
			markModified();
		}
		this.readingRetest = readingRetest;
	}

	public boolean isReadingPassFailIndicator()
	{
		fetch();
		return readingPassFailIndicator;
	}

	public void setReadingPassFailIndicator(boolean readingPassFailIndicator)
	{
		if (this.readingPassFailIndicator != readingPassFailIndicator)
		{
			markModified();
		}
		this.readingPassFailIndicator = readingPassFailIndicator;
	}

	public Date getWritingTestDate()
	{
		fetch();
		return writingTestDate;
	}

	public void setWritingTestDate(Date writingTestDate)
	{
		if (this.writingTestDate == null || !this.writingTestDate.equals(writingTestDate))
		{
			markModified();
		}
		this.writingTestDate = writingTestDate;
	}

	public int getWritingScore()
	{
		fetch();
		return writingScore;
	}

	public void setWritingScore(int writingScore)
	{
		if (this.writingScore != writingScore)
		{
			markModified();
		}
		this.writingScore = writingScore;
	}

	public boolean isWritingBeforePlacement()
	{
		fetch();
		return writingBeforePlacement;
	}

	public void setWritingBeforePlacement(boolean writingBeforePlacement)
	{
		if (this.writingBeforePlacement != writingBeforePlacement)
		{
			markModified();
		}
		this.writingBeforePlacement = writingBeforePlacement;
	}

	public boolean isWritingAfterPlacement()
	{
		fetch();
		return writingAfterPlacement;
	}

	public void setWritingAfterPlacement(boolean writingAfterPlacement)
	{
		if (this.writingAfterPlacement != writingAfterPlacement)
		{
			markModified();
		}
		this.writingAfterPlacement = writingAfterPlacement;
	}

	public boolean isWritingRetest()
	{
		fetch();
		return writingRetest;
	}

	public void setWritingRetest(boolean writingRetest)
	{
		if (this.writingRetest != writingRetest)
		{
			markModified();
		}
		this.writingRetest = writingRetest;
	}

	public boolean isWritingPassFailIndicator()
	{
		fetch();
		return writingPassFailIndicator;
	}

	public void setWritingPassFailIndicator(boolean writingPassFailIndicator)
	{
		if (this.writingPassFailIndicator != writingPassFailIndicator)
		{
			markModified();
		}
		this.writingPassFailIndicator = writingPassFailIndicator;
	}

	/**
	 * Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) starts
	 **/
	public void setRlaTestDate(Date rlaTestDate) {

		if (this.rlaTestDate == null || !this.rlaTestDate.equals(rlaTestDate)) {
			markModified();
		}
		this.rlaTestDate = rlaTestDate;
	}

	public Date getRlaTestDate() {
		fetch();
		return rlaTestDate;
	}

	public void setRlaScore(int rlaScore) {
		if (this.rlaScore != rlaScore) {
			markModified();
		}
		this.rlaScore = rlaScore;
	}

	public int getRlaScore() {
		fetch();
		return rlaScore;
	}

	public void setRlaBeforePlacement(boolean rlaBeforePlacement) {
		if (this.rlaBeforePlacement != rlaBeforePlacement) {
			markModified();
		}
		this.rlaBeforePlacement = rlaBeforePlacement;
	}

	public boolean isRlaBeforePlacement() {
		fetch();
		return rlaBeforePlacement;
	}

	public void setRlaAfterPlacement(boolean rlaAfterPlacement) {
		if (this.rlaAfterPlacement != rlaAfterPlacement) {
			markModified();
		}
		this.rlaAfterPlacement = rlaAfterPlacement;
	}

	public boolean isRlaAfterPlacement() {
		fetch();
		return rlaAfterPlacement;
	}

	public void setRlaRetest(boolean rlaRetest) {
		if (this.rlaRetest != rlaRetest) {
			markModified();
		}
		this.rlaRetest = rlaRetest;
	}

	public boolean isRlaRetest() {
		fetch();
		return rlaRetest;
	}

	public boolean isRlaPassFailIndicator() {
		fetch();
		return rlaPassFailIndicator;
	}

	public void setRlaPassFailIndicator(boolean rlaPassFailIndicator) {
		if (this.rlaPassFailIndicator != rlaPassFailIndicator) {
			markModified();
		}
		this.rlaPassFailIndicator = rlaPassFailIndicator;
	}
	/**Changes for ER: JIMS200077481 MJCW: New GED Score Requirements(UI) ends **/
	
	public Date getMathTestDate()
	{
		fetch();
		return mathTestDate;
	}

	public void setMathTestDate(Date mathTestDate)
	{
		if (this.mathTestDate == null || !this.mathTestDate.equals(mathTestDate))
		{
			markModified();
		}
		this.mathTestDate = mathTestDate;
	}

	public int getMathScore()
	{
		fetch();
		return mathScore;
	}

	public void setMathScore(int mathScore)
	{
		if (this.mathScore != mathScore)
		{
			markModified();
		}
		this.mathScore = mathScore;
	}

	public boolean isMathBeforePlacement()
	{
		fetch();
		return mathBeforePlacement;
	}

	public void setMathBeforePlacement(boolean mathBeforePlacement)
	{
		if (this.mathBeforePlacement != mathBeforePlacement)
		{
			markModified();
		}
		this.mathBeforePlacement = mathBeforePlacement;
	}

	public boolean isMathAfterPlacement()
	{
		fetch();
		return mathAfterPlacement;
	}

	public void setMathAfterPlacement(boolean mathAfterPlacement)
	{
		if (this.mathAfterPlacement != mathAfterPlacement)
		{
			markModified();
		}
		this.mathAfterPlacement = mathAfterPlacement;
	}

	public boolean isMathRetest()
	{
		fetch();
		return mathRetest;
	}

	public void setMathRetest(boolean mathRetest)
	{
		if (this.mathRetest != mathRetest)
		{
			markModified();
		}
		this.mathRetest = mathRetest;
	}

	public boolean isMathPassFailIndicator()
	{
		fetch();
		return mathPassFailIndicator;
	}

	public void setMathPassFailIndicator(boolean mathPassFailIndicator)
	{
		if (this.mathPassFailIndicator != mathPassFailIndicator)
		{
			markModified();
		}
		this.mathPassFailIndicator = mathPassFailIndicator;
	}

	public Date getScienceTestDate()
	{
		fetch();
		return scienceTestDate;
	}

	public void setScienceTestDate(Date scienceTestDate)
	{
		if (this.scienceTestDate == null || !this.scienceTestDate.equals(scienceTestDate))
		{
			markModified();
		}
		this.scienceTestDate = scienceTestDate;
	}

	public int getScienceScore()
	{
		fetch();
		return scienceScore;
	}

	public void setScienceScore(int scienceScore)
	{
		if (this.scienceScore != scienceScore)
		{
			markModified();
		}
		this.scienceScore = scienceScore;
	}

	public boolean isScienceBeforePlacement()
	{
		fetch();
		return scienceBeforePlacement;
	}

	public void setScienceBeforePlacement(boolean scienceBeforePlacement)
	{
		if (this.scienceBeforePlacement != scienceBeforePlacement)
		{
			markModified();
		}
		this.scienceBeforePlacement = scienceBeforePlacement;
	}

	public boolean isScienceAfterPlacement()
	{
		fetch();
		return scienceAfterPlacement;
	}

	public void setScienceAfterPlacement(boolean scienceAfterPlacement)
	{
		if (this.scienceAfterPlacement != scienceAfterPlacement)
		{
			markModified();
		}
		this.scienceAfterPlacement = scienceAfterPlacement;
	}

	public boolean isScienceRetest()
	{
		fetch();
		return scienceRetest;
	}

	public void setScienceRetest(boolean scienceRetest)
	{
		if (this.scienceRetest != scienceRetest)
		{
			markModified();
		}
		this.scienceRetest = scienceRetest;
	}

	public boolean isSciencePassFailIndicator()
	{
		fetch();
		return sciencePassFailIndicator;
	}

	public void setSciencePassFailIndicator(boolean sciencePassFailIndicator)
	{
		if (this.sciencePassFailIndicator != sciencePassFailIndicator)
		{
			markModified();
		}
		this.sciencePassFailIndicator = sciencePassFailIndicator;
	}

	public Date getSocialStudiesTestDate()
	{
		fetch();
		return socialStudiesTestDate;
	}

	public void setSocialStudiesTestDate(Date socialStudiesTestDate)
	{
		if (this.socialStudiesTestDate == null || !this.socialStudiesTestDate.equals(socialStudiesTestDate))
		{
			markModified();
		}
		this.socialStudiesTestDate = socialStudiesTestDate;
	}

	public int getSocialStudiesScore()
	{
		fetch();
		return socialStudiesScore;
	}

	public void setSocialStudiesScore(int socialStudiesScore)
	{
		if (this.socialStudiesScore != socialStudiesScore)
		{
			markModified();
		}
		this.socialStudiesScore = socialStudiesScore;
	}

	public boolean isSocialStudiesBeforePlacement()
	{
		fetch();
		return socialStudiesBeforePlacement;
	}

	public void setSocialStudiesBeforePlacement(boolean socialStudiesBeforePlacement)
	{
		if (this.socialStudiesBeforePlacement != socialStudiesBeforePlacement)
		{
			markModified();
		}
		this.socialStudiesBeforePlacement = socialStudiesBeforePlacement;
	}

	public boolean isSocialStudiesAfterPlacement()
	{
		fetch();
		return socialStudiesAfterPlacement;
	}

	public void setSocialStudiesAfterPlacement(boolean socialStudiesAfterPlacement)
	{
		if (this.socialStudiesAfterPlacement != socialStudiesAfterPlacement)
		{
			markModified();
		}
		this.socialStudiesAfterPlacement = socialStudiesAfterPlacement;
	}

	public boolean isSocialStudiesRetest()
	{
		fetch();
		return socialStudiesRetest;
	}

	public void setSocialStudiesRetest(boolean socialStudiesRetest)
	{
		if (this.socialStudiesRetest != socialStudiesRetest)
		{
			markModified();
		}
		this.socialStudiesRetest = socialStudiesRetest;
	}

	public boolean isSocialStudiesPassFailIndicator()
	{
		fetch();
		return socialStudiesPassFailIndicator;
	}

	public void setSocialStudiesPassFailIndicator(boolean socialStudiesPassFailIndicator)
	{
		if (this.socialStudiesPassFailIndicator != socialStudiesPassFailIndicator)
		{
			markModified();
		}
		this.socialStudiesPassFailIndicator = socialStudiesPassFailIndicator;
	}

	public boolean isLockStatus()
	{
		fetch();
		return lockStatus;
	}

	public void setLockStatus(boolean lockStatus)
	{
		if (this.lockStatus != lockStatus)
		{
			markModified();
		}
		this.lockStatus = lockStatus;
	}

	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	public int getVersion()
	{
		fetch();
		return version;
	}

	public void setVersion(int version)
	{
		if (this.version != version)
		{
			markModified();
		}
		this.version = version;
	}

	public int getTotalScore()
	{
		fetch();
		return totalScore;
	}

	public void setTotalScore(int totalScore)
	{
		if (this.totalScore != totalScore)
		{
			markModified();
		}
		this.totalScore = totalScore;
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getGedProgramCode()
	{
		initGedProgramCode();
		return gedProgramCode;
	}

	/**
	 * set the type reference for class member gedProgramCode
	 */
	public void setGedProgramCode(Code gedProgramCode)
	{
		if (this.gedProgramCode == null || !this.gedProgramCode.equals(gedProgramCode))
		{
			markModified();
		}
		setGedProgramCodeId("" + gedProgramCode.getOID());
		gedProgramCode.setContext("GED_PROGRAM");
		this.gedProgramCode = (Code) new mojo.km.persistence.Reference(gedProgramCode).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setGedProgramCodeId(String gedProgramCodeId)
	{
		if (this.gedProgramCodeId == null || !this.gedProgramCodeId.equals(gedProgramCodeId))
		{
			markModified();
		}
		gedProgramCode = null;
		this.gedProgramCodeId = gedProgramCodeId;
	}
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initGedProgramCode()
	{
		if (gedProgramCode == null)
		{
			gedProgramCode = (Code) new mojo.km.persistence.Reference(gedProgramCodeId, Code.class,
					"GED_PROGRAM").getObject();
		}
	}
	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getGedProgramCodeId()
	{
		fetch();
		return gedProgramCodeId;
	}
	
	public boolean isGedPassFailIndicator() {
		fetch();
		return gedPassFailIndicator;
	}

	public void setGedPassFailIndicator(boolean gedPassFailIndicator) {
		if (this.gedPassFailIndicator != gedPassFailIndicator)
		{
			markModified();
		}
		this.gedPassFailIndicator = gedPassFailIndicator;
	}

	public String getOtherProgram() {
		fetch();
		return otherProgram;
	}

	public void setOtherProgram(String otherProgram) {
		if (this.otherProgram != otherProgram)
		{
			markModified();
		}
		this.otherProgram = otherProgram;
	}	
	
	public Date getGedEntryDate() {
		return gedEntryDate;
	}

	public void setGedEntryDate(Date gedEntryDate) {
		this.gedEntryDate = gedEntryDate;
	}
	
	public CharterGEDResponseEvent getResponseEvent(){
		CharterGEDResponseEvent myRespEvt=new CharterGEDResponseEvent();
		myRespEvt.setCharterGEDId(this.getOID());
		myRespEvt.setGEDEntryDate(this.getGedEntryDate());
		myRespEvt.setJuvenileNum(this.getJuvenileNum());
		myRespEvt.setLockStatus(this.isLockStatus());
		myRespEvt.setMathAfterPlacement(this.isMathAfterPlacement());
		myRespEvt.setMathBeforePlacement(this.isMathBeforePlacement());
		myRespEvt.setMathPassFailIndicator(this.isMathPassFailIndicator());
		myRespEvt.setMathRetest(this.isMathRetest());
		myRespEvt.setMathScore(new Integer(this.getMathScore()).toString());
		myRespEvt.setMathTestDate(this.getMathTestDate());
		myRespEvt.setReadingAfterPlacement(this.isReadingAfterPlacement());
		myRespEvt.setReadingBeforePlacement(this.isReadingBeforePlacement());
		myRespEvt.setReadingPassFailIndicator(this.isReadingPassFailIndicator());
		myRespEvt.setReadingRetest(this.isReadingRetest());
		myRespEvt.setReadingScore(new Integer(this.getReadingScore()).toString());
		myRespEvt.setReadingTestDate(this.getReadingTestDate());
		myRespEvt.setScienceAfterPlacement(this.isScienceAfterPlacement());
		myRespEvt.setScienceBeforePlacement(this.isScienceBeforePlacement());
		myRespEvt.setSciencePassFailIndicator(this.isSciencePassFailIndicator());
		myRespEvt.setScienceRetest(this.isScienceRetest());
		myRespEvt.setScienceScore(new Integer(this.getScienceScore()).toString());
		myRespEvt.setScienceTestDate(this.getScienceTestDate());
		myRespEvt.setSocialStudiesAfterPlacement(this.isSocialStudiesAfterPlacement());
		myRespEvt.setSocialStudiesBeforePlacement(this.isSocialStudiesBeforePlacement());
		myRespEvt.setSocialStudiesPassFailIndicator(this.isSocialStudiesPassFailIndicator());
		myRespEvt.setSocialStudiesRetest(this.isSocialStudiesRetest());
		myRespEvt.setSocialStudiesScore(new Integer(this.getSocialStudiesScore()).toString());
		myRespEvt.setSocialStudiesTestDate(this.getSocialStudiesTestDate());
		myRespEvt.setTotalScore(new Integer(this.getTotalScore()).toString());
		myRespEvt.setVersion(new Integer(this.getVersion()).toString());
		myRespEvt.setWritingAfterPlacement(this.isWritingAfterPlacement());
		myRespEvt.setWritingBeforePlacement(this.isWritingBeforePlacement());
		myRespEvt.setWritingPassFailIndicator(this.isWritingPassFailIndicator());
		myRespEvt.setWritingRetest(this.isWritingRetest());
		myRespEvt.setWritingScore(new Integer(this.getWritingScore()).toString());
		myRespEvt.setWritingTestDate(this.getWritingTestDate());
		/**Changes for ER: JIMS200077481 starts **/
		myRespEvt.setRlaAfterPlacement(this.isRlaAfterPlacement());
		myRespEvt.setRlaBeforePlacement(this.isRlaBeforePlacement());
		myRespEvt.setRlaPassFailIndicator(this.isRlaPassFailIndicator());
		myRespEvt.setRlaRetest(this.isRlaRetest());
		myRespEvt.setRlaScore(new Integer(this.getRlaScore()).toString());
		myRespEvt.setRlaTestDate(this.getRlaTestDate());
		/**Changes for ER: JIMS200077481 ends **/
		myRespEvt.setGedPassFailIndicator(this.isGedPassFailIndicator());
		myRespEvt.setGedProgramCodeId(this.getGedProgramCodeId());
		myRespEvt.setGedProgramCodeDesc(PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.GEDPROGRAM, false), this.getGedProgramCodeId()));
		myRespEvt.setOtherProgram(this.getOtherProgram());
		myRespEvt.setTotalIncomplete(this.getTotalIncomplete());
		return myRespEvt;
	}

	public String getTotalIncomplete() {
		fetch();
		return totalIncomplete;
	}

	public void setTotalIncomplete(String totalIncomplete) {
		this.totalIncomplete = totalIncomplete;
	}

	
}


