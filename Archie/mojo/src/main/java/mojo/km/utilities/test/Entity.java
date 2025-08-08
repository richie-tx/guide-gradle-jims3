package mojo.km.utilities.test;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

// Class that represents a simple business entity
/** @modelguid {5947DB71-B9CC-4D57-A99C-CCC415880726} */
public class Entity extends Object implements Serializable {
	/** @modelguid {0240AF21-104A-4ED7-B8A5-C5CA2669C827} */
    private String OID = null;
	/** @modelguid {24969E2C-2E9A-4081-9113-294C54529CC3} */
    private Date testDate = null;
	/** @modelguid {3AA39E01-3F63-4A79-8A93-100A101A0AE2} */
    private Timestamp testTS = null;
	/** @modelguid {D6EF10F1-03E3-46BA-886D-A1943D39BCF6} */
    private Collection testColl = null;
	/** @modelguid {FAB71124-9D95-41A5-BE23-AA63BE0E6A75} */
    private Integer testInteger = null;
	/** @modelguid {A8D3F6AA-42BC-4B80-88C6-619E47E5BED5} */
    private boolean testBoolean = false;
	/** @modelguid {D33D18F2-605D-473B-8CED-8EA46D7AC166} */
    private int testInt;
    
	/** @modelguid {6A4B8F65-4BD0-4C76-9D16-F4780C0914BD} */
    public void setOID(String value) {
        this.OID = value;
    }
	/** @modelguid {8B9A0985-ACE5-4A89-ADF3-50090C02EA95} */
    public String getOID() {
        return this.OID;
    }

	/** @modelguid {F7C83DCD-8892-4992-AED7-596B9CAD90A2} */
    public void setTestDate(Date value) {
        this.testDate = value;
    }
	/** @modelguid {2337C7C6-6149-455F-9F9D-921E7C6B2796} */
    public Date getTestDate() {
        return this.testDate;
    }

	/** @modelguid {9AE08792-FBE3-412D-9754-F5B998A8BD15} */
    public void setTestTS(Timestamp value) {
        this.testTS = value;
    }
	/** @modelguid {92A01D26-E2D9-40AD-BB78-C0FB5E922235} */
    public Timestamp getTestTS() {
        return this.testTS;
    }

	/** @modelguid {425CDD87-620A-4177-A4F0-0DD1D213B94C} */
    public void setTestColl(Collection value) {
        this.testColl = value;
    }
	/** @modelguid {C64FA210-8765-4A74-BC9B-B8A3ECC20267} */
    public Collection getTestColl() {
        return this.testColl;
    }

	/** @modelguid {C1035637-1330-4ECB-BFFD-97E74715A939} */
    public void setTestInteger(Integer value) {
        this.testInteger = value;
    }
	/** @modelguid {5A9B1E90-ECD4-47E0-AE35-E557B151F046} */
    public Integer getTestInteger() {
        return this.testInteger;
    }

	/** @modelguid {3BDD50EA-B723-4C20-8C9E-E0A6F221FE73} */
    public void setTestInt(int value) {
        this.testInt = value;
    }
	/** @modelguid {D9523470-2998-469A-9BF8-394067F1F915} */
    public int getTestInt() {
        return this.testInt;
    }

	/** @modelguid {35BAF736-B494-46DB-B8B1-AAEF3C1CBF88} */
    public void setTestBoolean(boolean value) {
        this.testBoolean = value;
    }
	/** @modelguid {98BCD42B-359B-401E-93C8-2CC52D22BE91} */
    public boolean getTestBoolean() {
        return this.testBoolean;
    }

	/** @modelguid {2D930664-D49D-4B42-807C-7C0F8DFA1123} */
    public String toString() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("OID: "+ OID + "\n" ) ;
        strBuf.append("testDate: "+ testDate + "\n" ) ;
        strBuf.append("testTS: "+ testTS + "\n" ) ;
        strBuf.append("testInt: "+ testInt + "\n" ) ;
        strBuf.append("testColl: "+ testColl + "\n" ) ;
        strBuf.append("testBoolean: "+ testBoolean + "\n" ) ;
        strBuf.append("testInteger: "+ testInteger + "\n" ) ;

        return strBuf.toString() ;
    }
}
