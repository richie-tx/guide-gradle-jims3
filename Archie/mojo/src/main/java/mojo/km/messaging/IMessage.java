package mojo.km.messaging;

//import java.util.*;
import java.sql.*;
import java.util.Vector;
import java.util.Iterator;
import mojo.km.messaging.exception.*;
import java.math.BigDecimal;

/** @modelguid {0D587873-85D3-4E19-ADCC-B2B5A99D3968} */
public interface IMessage {
    /**
     * Adds a java.math.BigDecimal value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {701B1496-2408-4695-899D-BFB9A522A206}
     */
    public void addBigDecimal(String aFieldName, BigDecimal aValue) throws MessagingException;

    /**
     * Adds a boolean value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {BBD28AC4-6D76-4B04-998E-4EDA18B9F543}
     */
    public void addBoolean(String aFieldName, boolean aValue) throws MessagingException;

    /**
     * Adds a char value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {1843E18B-25B7-4E57-BCE1-B3F8407D89EA}
     */
    public void addChar(String aFieldName, char aValue) throws MessagingException;

    /**
     * Adds a double value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {F74F519A-005D-4ED5-9393-EE2FEF09AEEF}
     */
    public void addDouble(String aFieldName, double aValue) throws MessagingException;

    /**
     * Adds a single column definition to the message.
     *
     * @param aFieldName
     * @param aSize
     * @modelguid {49EF1DEA-CF48-4195-B811-BE4568017238}
     */
    public void addField(String aFieldName, int aSize);

    /**
     * Adds a single column definition to the message.
     *
     *  @param arg0
     * @param arg1
     *  @deprecated As of 8/5/2000, replaced by addField(String, int)
     * @modelguid {CCBDD746-D047-43E2-9E0B-8B759FAA3CBC}
     */
    public void addField(String arg0, short arg1);

    /**
     * Adds a float value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {3B64211C-6BC2-416F-B5D1-24147253C68B}
     */
    public void addFloat(String aFieldName, float aValue) throws MessagingException;

    /**
     * Adds an int value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {0B7C3C3C-685A-44C1-A933-EBAC88AD3F97}
     */
    public void addInt(String aFieldName, int aValue) throws MessagingException;

    /**
     * Adds a long value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {FF3B5AB1-B3B9-43E5-AE1E-C17254BCA8DD}
     */
    public void addLong(String aFieldName, long aValue) throws MessagingException;

    /**
     * Adds an IMessage value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {C1FE0105-7E78-4A12-AB09-410FBCF4DA74}
     */
    public void addMessage(String aFieldName, IMessage aValue);

    /**
     * Adds a short value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {DCB3D860-B276-4EBB-BC05-44927A94059B}
     */
    public void addShort(String aFieldName, short aValue) throws MessagingException;

    /**
     * Adds a java.sql.Date value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {28F22F5D-3769-444E-9CC0-830E5B32E0F2}
     */
    public void addSQLDate(String aFieldName, Date aValue) throws MessagingException;

    /**
     * Adds a String value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {8F2D8897-48F8-48C8-A88E-074C2C97657E}
     */
    public void addString(String aFieldName, String aValue) throws MessagingException;

    /**
     * Adds a java.sql.Timestamp value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {6215AF29-E5C7-40E9-AB14-EB1BC5222134}
     */
    public void addTimestamp(String aFieldName, Timestamp aValue) throws MessagingException;

    /**
     * Adds a java.util.Date value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @throws MessagingException
     * @modelguid {F3051FB9-9EA4-429A-89D3-1603EF687623}
     */
    public void addUtilDate(String aFieldName, java.util.Date aValue) throws MessagingException;

    /**
     *  Convienience method that adds a new generic value to the specified field.
     *
     *  @param aFieldName The name of the field receiving the value.
     *  @param aValue The value of the field.
     * @throws MessagingException
     * @modelguid {F7EF129A-AFD5-4634-834C-FABA9C16AFC1}
     */
    public void addValue(String aFieldName, Object aValue) throws MessagingException;

    /**
     * Returns a java.math.BigDecimal value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type java.math.BigDecimal or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {02908AD3-8074-4767-B686-C5BF35CEAE9C}
     */
    public BigDecimal getBigDecimal(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Return the value for the field specified by the input name as a
     * <code>boolean</code>.
     *
     * <p><b>NOTE:</b> This method will return <code>true</code> for the following
     * case insensitive String Values: 'true', 't', '1', 'yes', 'y'.<br>
     * This method will return <code>false</code> for the following
     * case insensitive String Values: 'false', 'f', '0', 'no', 'n'.
     *
     * @param aFieldName
     * @param anIndex
     * @return boolean The value of the column requested.
     * @throws MessagingException
     * @modelguid {2307568A-6D42-4004-983A-93F879701099}
     */
    public boolean getBoolean(String aFieldName, int anIndex) throws MessagingException;

    /**
     * Returns a char value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type char or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {D286B7EA-BAEA-4474-B3CD-3A3EDE12379E}
     */
    public char getChar(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a double value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type double or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {1529D62F-23A9-4739-8E3C-1621CA21D9DF}
     */
    public double getDouble(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Get the names of all of the columns of the message.
     *
     *  @return Vector The names of all of the fields within the message.
     * @modelguid {8F5CF6B0-1CDF-4CE9-A259-24AB828817E7}
     */
    public Vector getFieldNames();

    /**
     *  Returns a Vector of column (field) objects. These
     *  describe the database schema in terms of the name and
     *  width of each field.
     *
     *  @return Vector All the columns (fields) of this Message.
     *  @modelguid {3E7A8B07-F378-4403-AC6F-0600A1C9CB41}
     */
    public Vector getFields();

    /**
     * Returns a float value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type float or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {DD50E1A6-231C-46DB-B1D7-81F9381E979E}
     */
    public float getFloat(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a int value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type int or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {0C8553B7-AC56-43FF-94FE-E5E9637DF5C5}
     */
    public int getInt(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a long value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type long or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {51435DF2-3C6E-4BB7-8E39-2755F31CA671}
     */
    public long getLong(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns an IMessage value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type IMessage or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {28569A4D-050B-4726-950C-B32B12CEA9B4}
     */
    public IMessage getMessage(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns the value index of the first occurance of the value requested in the
     * containing field.
     *
     * @param aFieldName
     * @param aValue
     * @return short - The index of the value in the containing field.
     * @modelguid {EBBE41C2-A487-484F-A27C-C925DC13DA40}
     */
    public int getRowIndex(String aFieldName, String aValue);

    /**
     * Returns a short value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type short or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {8CE3C40A-8FD5-4095-B8BE-6AFB3894D662}
     */
    public short getShort(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a java.sql.Date value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type java.sql.Date or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {E31528EB-AD16-4042-8799-D01EFA00B842}
     */
    public Date getSQLDate(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a String value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type String or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {22A1E302-744B-4925-BFEB-800C1B87118F}
     */
    public String getString(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a java.sql.Timestamp value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type java.sql.Timestamp or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {C2067D89-D569-4B4E-B46F-05469C91DA5F}
     */
    public Timestamp getTimestamp(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Returns a java.util.Date value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type java.util.Date or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {643B7827-C5AB-4126-8F4C-934C65B09D1E}
     */
    public java.util.Date getUtilDate(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     *  Return the value for the field specified by the input name.
     *
     *  @param arg0
     * @param arg1
     *  @return String The value of the column requested.
     *  @deprecated  As of 8/5/2000, replaced by typed get methods.
     * @modelguid {13FC7047-50E9-4C9A-87D2-1AAB7D869A48}
     */
    public String getValue(String arg0, short arg1);

    /**
     *  Returns a Vector which reflect the values of the fields in this DataItem.
     *
     *  @param arg0
     *  @return
     * @returns Vector All the field values of this Message.
     * @modelguid {FCBB22D8-ED1B-4C58-B907-2985CAB0D821}
     */
    public Vector getValues(String arg0);

    /**
     * Removes a value from a field in the message.
     *
     * @param aFieldName
     * @param anIndex The index of the value to remove in the field
     * @modelguid {19C760EC-C1E9-4180-AE89-598188A99155}
     */
    public void removeValue(String aFieldName, int anIndex);

    /**
     * Removes a value from a field in the message.
     *
     * @param fieldName The name of the field containting the value to remove
     * @param aIndex The index of the value to remove in the field
     * @deprecated As of 8/5/2000, replaced by removeValue with a row index argument
     *             of type int.
     * @modelguid {BE9603A4-4A10-4D9C-83F2-A0DFE3BA2EA2}
     */
    public void removeValue(String fieldName, short aIndex);

    /**
     * Replaces a value in a field in the message.
     *
     * @param fieldName The name of the field containting the value to replace
     * @param aIndex The index of the value to replace in the field
     * @param value The value to replace the existing value with
     * @deprecated As of 8/5/2000, replaced by replace value with a row index argument
     *             of type int.
     * @modelguid {91623461-D637-447D-A993-65BCB0BF113C}
     */
    public void replaceValue(String fieldName, short aIndex, String value);

    /**
     *  Resets the row cursor to the first row of data in the message.
     * @modelguid {51A7549E-F376-4C37-8498-E20494EF728F}
     */
    public void reset();

    /**
     * Returns a boolean value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @deprecated As of 9/20/2000, replaced by getBoolean
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type boolean or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {0DDC59CA-65F1-4423-8A50-F3BEC3961925}
     */
    public boolean getBooleanValue(String aFieldName, short aRowIndex) throws MessagingException;

    /**
     * Return the value for the field specified by the input name as a
     * <code>Date</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Date The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getSqlDate
     * @modelguid {79ADF8ED-BBB1-44D4-AEB1-531E03A68F6B}
     */
    public Date getDateValue(String fieldName, short aIndex);

    /**
     * Return the value for the field specified by the input name as a
     * <code>Double</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Double The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getDouble
     * @modelguid {0B7FADA5-A4F2-4706-8B5E-2C3060FB43CD}
     */
    public Double getDoubleValue(String fieldName, short aIndex);

    /**
     * Helper method used to create an empty message complete with fields of this.  The message can be populated and
     * then inserted as a row.
     *
     * @return IMessage Contains no rows but has the same fields as this message
     * @modelguid {11596846-B16D-4521-B00A-7A2566B6EED4}
     */
    public IMessage getEmptyRow();

    /**
     * Return the value for the field specified by the input name as a
     * <code>Integer</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Integer The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getInteger
     * @modelguid {254EB7B1-D17E-4C4A-8F9E-0B405A644F47}
     */
    public Integer getIntegerValue(String fieldName, short aIndex);

    /**
     * Returns the number of data rows within this message.
     *
     * @return int Number of data rows in this message.
     * @modelguid {8945290D-564C-4AA1-B81D-2C7C203DA484}
     */
    public int getRowCount();

    /**
     * Returns an Iterator of read-only rows in this message. The rows are said to be read only because modifications to
     * data within the rows will NOT be reflected in this message.  This method is most usefull for message retrieval.
     *
     * @return Iterator Iterator of rows in this message.  Note: Each element in the Iterator is of type
     * IMessage.  The multiplicity
     *                  of each message returned is 1.  Also take caution as modifications to rows will NOT be relflected in
     *                  this message.
     * @modelguid {8C2EB82E-12CF-4FB3-9265-76D36248A5CA}
     */
    public Iterator getRows();

    /**
     *  Return the value for the field specified by the input name.
     *  There could potentially be more than one value for a
     *  particular field. In almost all cases, the index of the value
     *  you may need is the first one which is index 0.
     *
     *  <P><B>Example:</B> Normal call to get a value for a field named ExampleField<BR>
     *  <CODE>
     *  &nbsp;&nbsp;exampleMessage.getValue("ExampleField",(short)0);
     *  </CODE>
     *
     *  @param aFieldName The column name of the value to retrieve.
     *  @param aRowIndex
     *  @return Object The value of the column requested.
     * @throws MessagingException
     * @modelguid {BD5DA3D2-67FA-4E69-98DE-20E28F04BDEC}
     */
    public Object getValue(String aFieldName, int aRowIndex) throws MessagingException;

    /**
     * Inserts a row into this message.
     *
     * @param aMsg The multiplicity of aMsg message is assumed to be 1.  The fields of aMsg must be exactly the same as
     *             fields defined in this message.  Data in aMsg will be added as a row in this message.
     * @throws MessagingException
     * @exception MessagingException  Thrown if the fields of aMsg are not the same as that of this message.
     * @modelguid {6C88C380-20B5-455C-902F-C8E4016618E4}
     */
    public void insertRow(IMessage aMsg) throws MessagingException;

    /**
     * Replaces a value in a field in the message.
     *
     * @param aFieldName The name of the field containting the value to replace
     * @param anIndex The index of the value to replace in the field
     * @param aValue The value to replace the existing value with
     * @throws MessagingException
     * @modelguid {59C0C73E-92EF-4ED9-AD46-EF47DF4D662E}
     */
    public void replaceValue(String aFieldName, int anIndex, Object aValue) throws MessagingException;
}
