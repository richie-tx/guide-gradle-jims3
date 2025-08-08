package mojo.km.messaging;

import java.util.*;
import java.sql.*;
import java.util.Vector;
import mojo.km.messaging.exception.*;

/** @modelguid {E80302CD-209E-4BA5-BC71-10BA25281B26} */
public interface IMapMessage {
    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {6B48F43F-B7C7-4EE3-83B7-B85AAA0F3B77}
     */
    public void addBoolean(String fieldName, boolean value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {D56E8FF0-C020-4113-B819-C08EC6C1BF61}
     */
    public void addByte(String fieldName, byte value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {C70BD73B-E494-40F7-9E6E-C5DEA1169851}
     */
    public void addBytes(String fieldName, byte[] value) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/18/00 2:30:51 PM)
     * @param fieldName java.lang.String
     * @param value char
     * @throws MessagingException
     * @exception mojo.km.messaging.exceptions.FieldInsertException The exception description.
     * @modelguid {47E1CC66-68EE-45A2-A3BE-0F669F3D83A9}
     */
    public void addChar(String fieldName, char value) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 11:06:40 AM)
     * @param fieldName java.lang.String
     * @param column java.util.Vector
     * @param saveAsBaseTypes
     * @throws MessagingException
     * @modelguid {41C5B4C5-54BA-4134-83F2-E9B8E4ABA3E4}
     */
    public void addColumn(String fieldName, Vector column, boolean saveAsBaseTypes) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {B7368C7A-F5AE-47BB-98D0-473506B64D20}
     */
    public void addCurrency(String fieldName, double value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {9D0AE2EE-4DE1-438B-8C42-19B9530B0A49}
     */
    public void addDate(String fieldName, java.util.Date value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {42AB9BA2-0544-482F-9A15-830AB2FF304E}
     */
    public void addDouble(String fieldName, double value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {93069C36-0FC5-4950-821E-273D75F83BA5}
     */
    public void addFloat(String fieldName, float value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {C86EBF01-1040-4930-949F-C33ED78ECC96}
     */
    public void addInt(String fieldName, int value) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/18/00 2:31:38 PM)
     * @param fieldName java.lang.String
     * @param value long
     * @throws MessagingException
     * @exception mojo.km.messaging.exceptions.FieldInsertException The exception description.
     * @modelguid {976CE069-2A4B-459F-9591-E1E781887690}
     */
    public void addLong(String fieldName, long value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {C8DBE929-DB30-4C99-A6F6-A35D7F23CF51}
     */
    public void addObject(String fieldName, Object value) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {F5D8B390-A0AD-4265-A51A-7EEC35AFF816}
     */
    public void addShort(String fieldName, short value) throws MessagingException;

    /**
     * 	 Adds a new value to the specified field.
     *
     * 	 @param arg0
     * @param arg1
     *    @throws MessagingException
     * @modelguid {A4E95710-0C0D-4B0D-8B02-A0F1A96220BA}
     */
    public void addString(String arg0, String arg1) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @throws MessagingException
     * @modelguid {C860FE69-DBE9-4FBF-98E3-3D52DD909C83}
     */
    public void addTimestamp(String fieldName, Timestamp value) throws MessagingException;

    /**
     * Return the value for the field specified by the input name as a
     * <code>boolean</code>.
     *
     * <p><b>NOTE:</b> This method will return <code>true</code> for the following
     * case insensitive String Values: 'true', 't', '1', 'yes', 'y'.<br>
     * This method will return <code>false</code> for the following
     * case insensitive String Values: 'false', 'f', '0', 'no', 'n'.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return boolean The value of the column requested.
     *    @throws MessagingException
     * @modelguid {68E35C48-E5F1-4CEC-920C-B44786F9E874}
     */
    public boolean getBoolean(String fieldName, int aIndex) throws MessagingException;

    /**
     *    @param fieldName
     * @param index
     * @return
     * @throws MessagingException
     * @modelguid {45AE515F-39E2-4142-911B-9781E76816ED}
     */
    public byte getByte(String fieldName, int index) throws MessagingException;

    /**
     *    @param fieldName
     * @param index
     * @return
     * @throws MessagingException
     * @modelguid {976A1FAC-1BD6-4795-9EF3-68C1E92FF541}
     */
    public byte[] getBytes(String fieldName, int index) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/18/00 2:32:24 PM)
     * @param fieldName java.lang.String
     * @param aIndex int
     * @return char
     * @throws MessagingException
     * @exception mojo.km.messaging.exceptions.FieldRetrieveException The exception description.
     * @modelguid {000BE34B-07C4-4234-8619-C26BB20F22A0}
     */
    public char getChar(String fieldName, int aIndex) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 11:05:39 AM)
     * @param fieldName java.lang.String
     * @return <{Vector}>
     * @throws MessagingException
     * @modelguid {26972BE0-E144-4B45-9347-5335E50C9781}
     */
    public Vector getColumn(String fieldName) throws MessagingException;

    /**
     * 	 Return the value for the field specified by the input name as a
     * 	 <code>Double</code> rounded to two decimal places.
     *
     * 	 @param fieldName The column name of the value to retrieve.
     * 	 @param aIndex Index of the row to get the value from (usually 0 - zero).
     * 	 @return Double The value of the column requested.
     *    @throws MessagingException
     * @modelguid {29A98B29-397A-4663-9ACA-D5EB219CE097}
     */
    public double getCurrency(String fieldName, int aIndex) throws MessagingException;

    /**
     * 	 Return the value for the field specified by the input name as a
     * 	 <code>Date</code>.
     *
     * 	 @param fieldName The column name of the value to retrieve.
     * 	 @param aIndex Index of the row to get the value from (usually 0 - zero).
     * 	 @return Date The value of the column requested.
     *    @throws MessagingException
     * @modelguid {8748A598-EEFA-47AE-8784-1C1023D09C36}
     */
    public java.util.Date getDate(String fieldName, int aIndex) throws MessagingException;

    /**
     * 	 Return the value for the field specified by the input name as a
     * 	 <code>Double</code>.
     *
     * 	 @param fieldName The column name of the value to retrieve.
     * 	 @param aIndex Index of the row to get the value from (usually 0 - zero).
     * 	 @return Double The value of the column requested.
     *    @throws MessagingException
     * @modelguid {96ECA253-6AE3-4485-843F-AD52C86D81D4}
     */
    public double getDouble(String fieldName, int aIndex) throws MessagingException;

    /**
     *    @param fieldName
     * @return
     * @modelguid {1D2703F6-CBD0-4F1F-B595-4B855A866BA2}
     */
    public int getFieldLength(String fieldName);

    /**
     * 	 Get the names of all of the columns of the message.
     *
     * 	 @return Vector The names of all of the fields within the message.
     *    @modelguid {B42DC4DF-7558-4528-BF4A-F98FECA635D9}
     */
    public Vector getFieldNames();

    /**
     *    @param fieldName
     * @param index
     * @return
     * @throws MessagingException
     * @modelguid {8D19F494-1994-424E-8955-D8DD236A9FB4}
     */
    public float getFloat(String fieldName, int index) throws MessagingException;

    /**
     * 	 Return the value for the field specified by the input name as a
     * 	 <code>Integer</code>.
     *
     * 	 @param fieldName The column name of the value to retrieve.
     * 	 @param aIndex Index of the row to get the value from (usually 0 - zero).
     * 	 @return Integer The value of the column requested.
     *    @throws MessagingException
     * @modelguid {A7A6157C-A904-46CB-B767-E2E8919CF0E5}
     */
    public int getInt(String fieldName, int aIndex) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (5/18/00 2:32:54 PM)
     * @param fieldName java.lang.String
     * @param aIndex int
     * @return long
     * @throws MessagingException
     * @exception mojo.km.messaging.exceptions.FieldRetrieveException The exception description.
     * @modelguid {3446A63B-0F37-42AD-A70B-C1DB0A227CAF}
     */
    public long getLong(String fieldName, int aIndex) throws MessagingException;

    /**
     * Return the value for the field specified by the input name as a
     * <code>Object</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return boolean The value of the column requested.
     *    @throws MessagingException
     * @modelguid {BA0ED95F-B5EC-4BB6-AA43-8305E3993229}
     */
    public Object getObject(String fieldName, int aIndex) throws MessagingException;

    /**
     * Insert the method's description here.
     * Creation date: (6/22/00 1:04:43 PM)
     * @param rowNumber int
     * @return <{Hashtable}>
     * @throws MessagingException
     * @modelguid {55E7EB50-C6C8-4B9E-BAD3-8B60D6A9ABAE}
     */
    public Hashtable getRow(int rowNumber) throws MessagingException;

    /**
     *    @param rowNumber
     * @param fieldNames
     * @return
     * @throws MessagingException
     * @modelguid {3BB7F520-782E-41F8-AE99-CDB45537947D}
     */
    public Vector getRow(int rowNumber, Vector fieldNames) throws MessagingException;

    /**
     *    @param fieldName
     * @param index
     * @return
     * @throws MessagingException
     * @modelguid {5B7E6E01-A62D-49B5-9085-5489A00C0D78}
     */
    public short getShort(String fieldName, int index) throws MessagingException;

    /**
     *    @param fieldName
     * @param index
     * @return
     * @throws MessagingException
     * @modelguid {A002E5F8-0308-4FD9-AAD9-91F3D2537FC2}
     */
    public String getString(String fieldName, int index) throws MessagingException;

    /**
     * Return the value for the field specified by the input name as a
     * <code>Object</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Timestamp The value of the column requested.
     *    @throws MessagingException
     * @modelguid {065C6B2A-F254-4E20-95CA-4AA874F87615}
     */
    public Timestamp getTimestamp(String fieldName, int aIndex) throws MessagingException;

    /**
     * 	Removes a value from a field in the message.
     *
     * 	@param fieldName The name of the field containting the value to remove
     * 	@param aIndex The index of the value to remove in the field
     *    @throws MessagingException
     * @modelguid {A8EFCA8D-B35B-4A76-AD8E-846997483A87}
     */
    public void removeValue(String fieldName, int aIndex) throws MessagingException;

    /**
     *    @param fieldName
     * @param value
     * @param aIndex
     * @param saveAsBaseType
     * @throws MessagingException
     * @modelguid {72A63DE5-95C5-4425-A0E5-DC9989570D32}
     */
    public void replaceValue(String fieldName, Object value, int aIndex, boolean saveAsBaseType) throws MessagingException;

    /**
     *    @param row
     * @param saveAsBaseTypes
     * @throws MessagingException
     * @modelguid {9AD2B130-89B8-43F4-B6DF-F43572846F3E}
     */
    public void setRow(Hashtable row, boolean saveAsBaseTypes) throws MessagingException;

    /**
     *    @param fieldNames
     * @param fieldValues
     * @param saveAsBaseTypes
     * @throws MessagingException
     * @modelguid {4C66D2D8-1094-4382-9032-12D76F15C85C}
     */
    public void setRow(Vector fieldNames, Vector fieldValues, boolean saveAsBaseTypes) throws MessagingException;
}
