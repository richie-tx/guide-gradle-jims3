package mojo.km.messaging;

import java.io.*;

/**
 *   This class embodies the name of a field and the maximum width
 *   that it may have.
 *
 *    <PRE>
 *    Change History:
 *    Author             Date      Explanation
 *    ================ ==========  ===============================================
 *    Shannon Ewing   12/10/1999   Deleted unused class variables and doc cleanup.
 *    Shannon Ewing     2/1/2000   Removed FieldInfo() constructor.
 *    |                            Mod FieldInfo(String,short) to handle null name.
 *    Shannon Ewing    3/29/2000   Fix some JavaDoc tags.
 *    </PRE>
 *
 *  @author Eric A Amundson 9/11/1997
 * @modelguid {92F2151D-4DE1-40E4-8846-79423737E556}
 */
public class FieldInfo extends Object implements Serializable {
	/** @modelguid {984A2A88-492D-4100-8171-EB518F6FC30D} */
    private String name;
	/** @modelguid {98A55434-DAFF-4E2F-85E9-9DCE16E5462D} */
    private short length;
    //Determines if the values in the column vector referenced by
    //this field name are all the names of other messages
	/** @modelguid {C7B0412F-A226-4E87-8238-3A406A62EC88} */
    private boolean isMessageName = false;

    /**
     *    Do nothing constructor.
     */
    //public FieldInfo( ) { }

    /**
     * This constructs an initialized FieldInfo object.
     *
     * @param name The name of the field.
     * @param length The length of the field.
     * @modelguid {3FB5E5E5-666F-44A8-A94C-6EF05E414FEE}
     */
    public FieldInfo(String name, short length) {
        if (name == null || name.trim().length() < 1) {
            throw new IllegalArgumentException("Field name given can not be null.");
        }
        this.name = name;
        this.length = length;
    }

    /**
     *    Returns the length of the field.
     *
     *    @return short - the length of the field.
     * @modelguid {F7A4B69D-55B4-4AF8-A8F7-DC4085AB040D}
     */
    public short getLength() {
        return length;
    }

    /**
     *    Returns the name of the field.
     *
     *    @return String - the name of the field.
     * @modelguid {AC06F757-1446-4177-BB79-826BA88FC981}
     */
    public String getName() {
        return name;
    }

    /**
     *    Initializes/replaces the field name and the length for this object.
     *
     *    @param name The new name of the field.
     *    @param length The new length of the field.
     * @modelguid {5D225E95-8BE9-41AE-A1A4-AC66AE9AC758}
     */
    public void initialize(String name, short length) {
        this.name = name;
        this.length = length;
    }

    /**
     * This method returns the value of the flag to indicate if a
     * instance of this type is the name of another message field.
     *
     * @return boolean - True if field is another message name.
     * @modelguid {D7F4383A-253A-4EAD-B159-53973AF6FC91}
     */
    public boolean isMessageName() {
        return isMessageName;
    }

    /**
     * This method set the value to determine if field represents a message name
     *
     * @param arg1 The value of the flag to indicate the message name flag.
     * @modelguid {657F4158-BB9F-46CF-A9A2-8EEE09C43F4A}
     */
    public void setIsMessageNameFlag(boolean arg1) {
        isMessageName = arg1;
    }
}
