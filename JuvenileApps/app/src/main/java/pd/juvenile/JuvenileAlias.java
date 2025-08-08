package pd.juvenile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;

import messaging.juvenilecase.GetJuvenileAliasEventForJuvNumber;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import mojo.km.security.ISecurityManager;
import pd.codetable.Code;

public class JuvenileAlias extends PersistentObject {

	private String firstName;
	private String lastName;
	private String middleName;
	private String nameSuffix;
	private String raceId;
	private Date dateOfBirth;
	private String juvenileNum;
	private String juvenileType;
	
	private String sexId;
	private Date aliasEntryDate;
	private String aliasNotes;
	private Code race;
	private Code sex;
	
	private String recType;
	
	public String getFirstName() {
		fetch();
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		if (isModified(this.firstName, firstName)){
			markModified();
		}
		this.firstName = firstName;
	}
	
	public String getLastName() {
		fetch();
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if (isModified(this.lastName, lastName)){
			markModified();
		}
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		fetch();
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		if (isModified(this.middleName, middleName)){
			markModified();
		}
		this.middleName = middleName;
	}
	
	public String getRaceId() {
		fetch();
		return raceId;
	}
	
	public void setRaceId(String raceId) {
		if (raceId != null && raceId.equals("L")) {
			raceId = "W";
		}
		this.raceId = raceId;
	}

	public Date getDateOfBirth() {
		fetch();
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getJuvenileNum() {
		fetch();
		return this.juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		if (isModified(this.juvenileNum, juvenileNum)){
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}
	
	/*public String getJuvenileType() {
		fetch();
		return juvenileType;
	}
	
	public void setJuvenileType(String juvenileType) {
		if (isModified(this.juvenileType, juvenileType)){
			markModified();
		}
		this.juvenileType = juvenileType;
	}*/
	
	public String getJuvenileType()
	{
	    fetch();
	    return juvenileType;
	}

	public void setJuvenileType(String juvenileType)
	{
	    if (isModified(this.juvenileType, juvenileType)){
		markModified();
	}
	    this.juvenileType = juvenileType;
	}

	public String getSexId() {
		fetch();
		return sexId;
	}
	
	public void setSexId(String sexId) {
		if (isModified(this.sexId, sexId)){
			markModified();
		}
		this.sexId = sexId;
	}
	
	public Date getAliasEntryDate() {
		fetch();
		return aliasEntryDate;
	}
	
	public void setAliasEntryDate(Date aliasEntryDate) {
		this.aliasEntryDate = aliasEntryDate;
	}
	
	public String getAliasNotes() {
		fetch();
		return aliasNotes;
	}
	
	public void setAliasNotes(String aliasNotes) {
		if (isModified(this.aliasNotes, aliasNotes)){
			markModified();
		}	
		this.aliasNotes = aliasNotes;
	}
	
	public Code getRace() {
		initRace();
		return race;
	}
	public void setRace(Code race) {
		this.race = race;
	}
	
	
	public Code getSex() {
		initSex();
		return sex;
	}
	public void setSex(Code sex) {
		this.sex = sex;
	}
	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	/**
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @return JuvenileAlias
	 * @return pd.juvenile.JuvenileAlias
	 */
	public static List<JuvenileAlias> find(String juvNum) 
	{
		List<JuvenileAlias> juvenileAliasList = new ArrayList<JuvenileAlias>();
		GetJuvenileAliasEventForJuvNumber pEvent = new GetJuvenileAliasEventForJuvNumber();
		pEvent.setJuvenileNum(juvNum);
		//pEvent.setJuvenileType("A");
		
		Iterator iter = JuvenileAlias.findAll(pEvent);
		while (iter != null && iter.hasNext()) 
		{
			juvenileAliasList.add((JuvenileAlias) iter.next());
		}
		
		return juvenileAliasList;
	}
	
	/**
	 * @param searchEvent
	 * @return
	 */
	public static Iterator findAll(IEvent event){
		IHome home = new Home();
		//return home.findAll(event, JuvenileAlias.class);
		//BUG 174317 changes STARTS 
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
		    if (grantedFeature)
			return home.findAll(event, JuvenileAlias.class);
		    else{
			Iterator iter1 = home.findAll(event, JuvenileAlias.class);
			
			while (iter1.hasNext()){
			    
			    JuvenileAlias alias = (JuvenileAlias) iter1.next();
			   if(! "NAME".equalsIgnoreCase(alias.getRecType())){
			       iter1.remove();
			   }
			}
			return iter1;
		    }
			//BUG 174317 changes ENDS 
		}

	public String calculateStatusId(){
		String statusId  = "";
		JuvenileStatus status = JuvenileStatus.find(this.getJuvenileNum());
		if (status != null) {
			statusId = status.getStatusId();
		}
		
		return statusId;
				
	}
	public String calculatemasterStatusId()
	    {
		String masterstatusId = "";
		JuvenileMasterStatus status = JuvenileMasterStatus.find(this.getJuvenileNum());
		if (status != null)
		{
		    masterstatusId = status.getStatusId();
		}

		return masterstatusId;

	    }
	public String getRaceCodeDescription() {
		String desc = "";
		if (this.raceId != null && this.raceId.equals("") == false) {
			Code raceCode = this.getRace();
			if (raceCode != null){
				return raceCode.getDescription();
			}
		}
		return desc;

	}
	
	private void initRace(){
		if (race == null){
			race = (Code) new Reference(this.getRaceId(), Code.class, PDCodeTableConstants.RACE).getObject();
		}
	}
	
	
	public String getSexCodeDescription() {
		String desc = "";
		if (this.sexId != null && !this.sexId.equals("")) {
			Code sexCode = this.getSex();
			if (sexCode != null){
				return sexCode.getDescription();
			}
		}
		return desc;
	}
	
	private void initSex() {
		if (sex == null) {
			sex = (Code) new Reference(this.getSexId(), Code.class, "SEX").getObject();
		}
	}
	
	int getAgeInYears(Date ageDate) {
		int age = 0;
		if (ageDate != null) {
			Calendar birthdate = Calendar.getInstance();
			birthdate.setTime(ageDate);
			Calendar now = Calendar.getInstance();
			age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
			birthdate.add(Calendar.YEAR, age);
			if (now.before(birthdate)) {
				age--;	
			}
		}
		return age;
	}
	
	private boolean isModified(String var1, String var2){
		boolean returnFlag = false;
		if ((var1 == null && var2 != null) || (var1 != null && !var1.equals(var2))){
			returnFlag = true;
		}
		return returnFlag;

	}
	
	/**
	 * @roseuid 42B183080031
	 */
	public void bind(){
		markModified();
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	
}
