package mojo.km.utilities.test;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

// Class that represents an event
//class Event extends RequestEvent {
/** @modelguid {E95E4D88-7FC7-4D7F-893F-1B3D25ED4763} */
class Event extends Object implements Serializable {    
	/** @modelguid {22EAEE5B-8594-407B-B20C-5CA53D2C5332} */
    private Properties props = new Properties();
	/** @modelguid {607A7A09-7342-48D4-AC86-C3B151E59B0D} */
    private Collection testColl = null;
  
	/** @modelguid {DFA0E315-D9AB-4280-A743-03ECA9401605} */
    public void setOID(String value) {
        props.setProperty("OID", value);
    }
	/** @modelguid {9876ED46-540B-4DBF-9805-3335DEEF0F6F} */
    public String getOID() {
        return props.getProperty("OID");
    }

	/** @modelguid {DED0972F-9A9A-4C0B-B013-3788E675C000} */
    public void setTestDate(Date value) {
        props.setProperty("testDate", ""+value);
    }
	/** @modelguid {C15EB7F4-01C2-4C25-AE69-EE5F7E45D4E4} */
    public Date getTestDate() {
        return Date.valueOf(props.getProperty("testDate"));
    }

	/** @modelguid {2AAE5739-3266-4F32-A83E-D08B54FFFF00} */
    public void setTestTS(Timestamp value) {
        props.setProperty("testTS", ""+value);
    }
	/** @modelguid {05BDD3C7-735E-44FD-BF08-8D56D2AC9304} */
    public Timestamp getTestTS() {
        return Timestamp.valueOf(props.getProperty("testTS"));
    }

	/** @modelguid {8E6EB8B6-86BF-4C87-B9EE-5841EC1D1D53} */
    public void setTestColl(Collection value) {
        this.testColl = value;
    }
	/** @modelguid {7855C6C4-7DF4-4EE4-BB40-7FCDA01E1E31} */
    public Collection getTestColl() {
        return (Collection) this.testColl;
    }

	/** @modelguid {DFE2B9EC-3994-479D-8C6C-C3267A651543} */
    public void setTestBoolean(boolean value) {
        props.setProperty("testBoolean", ""+value);
    }
	/** @modelguid {CCD0AD3D-A27A-4268-B81D-726C137CB670} */
    public boolean getTestBoolean() {
        Boolean temp = new Boolean(props.getProperty("testBoolean"));
        return temp.booleanValue();
    }

	/** @modelguid {9265632B-BE37-4963-AB1A-53B15F66D830} */
    public void setTestInteger(Integer value) {
        props.setProperty("testInteger", ""+value);
    }
	/** @modelguid {3D4ACC6F-51D9-4724-8389-C56B13E662E8} */
    public Integer getTestInteger() {
        return new Integer(props.getProperty("testInteger"));
    }

	/** @modelguid {2563082F-581C-4557-8ABF-15409F4FDA2A} */
    public void setTestInt(int value) {
        props.setProperty("testInt", ""+value);
    }
	/** @modelguid {01AEE440-5751-4793-A6B4-E41ED46264FD} */
    public int getTestInt() {
        Integer temp = new Integer(props.getProperty("testInt"));
        return temp.intValue();
    }

	/** @modelguid {28B551FC-E41D-4076-8F2A-8CAD46D254A8} */
    public String toString() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("OID: "+ this.getOID() + "\n" ) ;
        strBuf.append("testDate: "+ this.getTestDate() + "\n" ) ;
        strBuf.append("testTS: "+ this.getTestTS() + "\n" ) ;
        strBuf.append("testInt: "+ this.getTestInt() + "\n" ) ;
        strBuf.append("testColl: "+ this.getTestColl() + "\n" ) ;
        strBuf.append("testBoolean: "+ this.getTestBoolean() + "\n" ) ;
        strBuf.append("testInteger: "+ this.getTestInteger() + "\n" ) ;

        return strBuf.toString() ;
    }
}