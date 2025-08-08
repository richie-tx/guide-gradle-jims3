package pd.supervision.administercompliance;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/*
  * @author ryoung
 * This class updates the CSCD Urinalysis system in SQLServer
 */
/**
 * 
 * @roseuid 47DA99E00329
 */
public class Urinalysis extends PersistentObject
{
	
	private String pushDate;
	private int spn;
	private String fName;
	private String mName;
	private String lName;
	private String poi;
	private String dob;
	private String sex;
	private String race;
	private String crt;
	
	
	
	
	public String getPushDate() {
		return pushDate;
	}
	public void setPushDate(String pushDate) {
		
		if (this.pushDate == null || !this.pushDate.equals( pushDate ))
		{
			markModified();
		}
		//		supervisionPeriod = null;
		this.pushDate = pushDate;
	}
	public int getSpn() {
		return spn;
	}
	public void setSpn(int spn) {
		
		if ( this.spn  > 0 || !(this.spn == spn) )
		{
			markModified();
		}
		
		this.spn = spn;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		
		if (this.fName == null || !this.fName.equals( fName ))
		{
			markModified();
		}
		this.fName = fName;
	}
	
	public String getmName() {
		return mName;
	}	
	public void setmName(String mName) {
		if (this.mName == null || !this.mName.equals( mName ))
		{
			markModified();
		}
		this.mName = mName;
	}	
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		
		if (this.lName == null || !this.lName.equals( lName ))
		{
			markModified();
		}
		
		this.lName = lName;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		
		if (this.poi == null || !this.poi.equals( poi ))
		{
			markModified();
		}
		this.poi = poi;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		
		if (this.dob == null || !this.dob.equals( dob ))
		{
			markModified();
		}
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		
		if (this.sex == null || !this.sex.equals( sex ))
		{
			markModified();
		}
		this.sex = sex;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		
		if (this.race == null || !this.race.equals( race ))
		{
			markModified();
		}
		this.race = race;
	}
	public String getCrt() {
		return crt;
	}
	public void setCrt(String crt) {
		
		if (this.crt == null || !this.crt.equals( crt ))
		{
			markModified();
		}
		this.crt = crt;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, Urinalysis.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, Urinalysis.class);
    }
    
}
