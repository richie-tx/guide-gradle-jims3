package mojo.km.messaging;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import mojo.km.messaging.exception.*;
import java.math.BigDecimal;
import java.sql.Date;

/** @modelguid {8A070D9A-F757-4AE7-808B-1F5E010A69C3} */
public class Message extends Object implements IMessage, Serializable {
	/** @modelguid {A9FD9366-74D7-4C7B-8667-1DADC0C71DFD} */
    private short recordNumber;
	/** @modelguid {87A1A871-7DAF-4CA7-B4E9-907C76AE06C3} */
    private int tupleIndex = 0;
	/** @modelguid {5951B196-A909-45EC-9DD6-E25CA0776121} */
    private boolean hasMoreTuples = false;
	/** @modelguid {0DA8125E-1560-439D-8035-6678247C8C5E} */
    private Vector fields = null;

    /**
     * @associates Vector 
     * @modelguid {4168206C-78E5-4D9F-9DBD-A02A6492621E}
     */
    private Vector columns = null;

    /**
     * @associates Short 
     * @modelguid {A58D02D0-12CD-4AFA-98A8-CFD1CB078D08}
     */
    private Hashtable fieldIndex = null;

    /**
     * 	 Default constructor used to initialize message buffers.
     * 	 @modelguid {A30E6D4B-DF99-4C35-BD21-3EDD25D6B945}*/
    public Message() {
        fieldIndex = new Hashtable();
        fields = new Vector();
        columns = new Vector();
    }

    /**
     * 	  This constructor creates a Message object which has null
     * 	  Strings for its field values.
     *
     * 	  @param recordNumber The unique number for this record.
     * 	  @param theFields The array of FieldInfoImpl objects
     * 	         which represent the names and lenghts of the fields in this
     * 	         Message object.
     * 	  @modelguid {CAE0A275-1039-4D3E-8C6F-F41946C83362}*/
    public Message(short recordNumber, FieldInfo[] theFields) {
        this();
        if (recordNumber < 0) {
            throw new IllegalArgumentException("Record number given in constructor must not be negative");
        }
        if (theFields == null) {
            throw new IllegalArgumentException("Null FieldInfo object given in constructor");
        } else if (theFields.length < 1) {
            throw new IllegalArgumentException("FieldInfo object array length must be greater than zero");
        } else if (theFields[0] == null) {
            throw new IllegalArgumentException("Null FieldInfo object given in constructor");
        }
        this.recordNumber = recordNumber;
        fieldIndex = new Hashtable();
        for (short i = 0; i < theFields.length; i++) {
            Short index = new Short(i);
            this.fields.addElement(theFields[i]);
            fieldIndex.put(theFields[i].getName(), index);
            columns.addElement(
                new Vector());
        }
    }

    /**
     * Adds a java.math.BigDecimal value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {A3EF4E11-366D-4CB3-8270-DF8E4C81408A}
     */
    public void addBigDecimal(String aFieldName, BigDecimal aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a boolean value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {63AD96E0-6944-403E-8462-6CAC778361E5}
     */
    public void addBoolean(String aFieldName, boolean aValue) {
        addValue(aFieldName,
            new Boolean(aValue));
    }

    /**
     * Adds a char value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {26C2CFE6-AF7D-490D-B1F5-DEEEB138BB9F}
     */
    public void addChar(String aFieldName, char aValue) {
        addValue(aFieldName,
            new Character(aValue));
    }

    /**
     * Adds a double value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {5210AAAC-D98C-400C-8345-E1E6376ACBBB}
     */
    public void addDouble(String aFieldName, double aValue) {
        addValue(aFieldName,
            new Double(aValue));
    }

    /**
     * Adds a single column definition to the message.
     *
     * @param aFieldName The name of the column to create.
     * @param aSize The max size of data allowed in the column
     * @modelguid {817A864A-ED89-402B-954A-9E0BF6974A77}
     */
    public void addField(String aFieldName, int aSize) {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            return;
        }
        this.fields.addElement(
            new FieldInfo(aFieldName, (short)aSize));
        this.columns.addElement(
            new Vector());
        Short index = new Short((short)(fields.size() - 1));
        fieldIndex.put(((FieldInfo)fields.elementAt(index.shortValue())).getName(), index);
    }

    /**
     *  Adds a single column definition to the message.
     *
     *  <P><B>Note:</B> This is the normal way of adding a field to a message.
     *
     *  <P><B>Example:</B> Adding a field to a message<BR>
     *  <CODE>
     *  &nbsp;&nbsp;exampleMessage.addField("ExampleField",(short)0);
     *  </CODE>
     *
     *  @param aFieldName The name of the column.
     *  @param aSize The max aSize of data allowed in the column (usually 0 - zero - unlimited)
     *  @see #addField(mojo.business.String, short, boolean)
     *  @see #addValue(mojo.business.String, mojo.business.String)
     *  @deprecated As of 8/5/2000, replaced by addField(String, int)
     * @modelguid {BD1CC707-286C-48B2-968E-E7DBF2ADD87F}
     */
    public void addField(String aFieldName, short aSize) {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            return;
        }
        this.fields.addElement(
            new FieldInfo(aFieldName, aSize));
        this.columns.addElement(
            new Vector());
        Short index = new Short((short)(fields.size() - 1));
        fieldIndex.put(((FieldInfo)fields.elementAt(index.shortValue())).getName(), index);
    }

    /**
     *  Adds a single column defintion to the message and indicates that the field name is also a
     *  message name.
     *
     *  @param fieldName The name of the column
     *  @param size The max size of data allowed in the column (usually 0 - zero - unlimited)
     *  @param isMessageName True if column is also the message name, False if not
     *  @see #addField(mojo.business.String, short)
     *  @see #addValue(mojo.business.String, mojo.business.String)
     * @modelguid {AEA03B4D-AA21-482E-9306-6BBDE13455C3}
     */
    public void addField(String fieldName, short size, boolean isMessageName) {
        if (fieldName == null || fieldName.trim().length() < 1) {
            return;
        }
        FieldInfo tempFI = new FieldInfo(fieldName, size);
        tempFI.setIsMessageNameFlag(isMessageName);
        this.fields.addElement(tempFI);
        this.columns.addElement(
            new Vector());
        Short index = new Short((short)(fields.size() - 1));
        fieldIndex.put(((FieldInfo)fields.elementAt(index.shortValue())).getName(), index);
    }

    /**
     * Adds a float value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {87C69DCB-5FF4-430F-8B06-9832458242AD}
     */
    public void addFloat(String aFieldName, float aValue) {
        addValue(aFieldName,
            new Float(aValue));
    }

    /**
     * Adds an int value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {F3BDF8D8-52BB-4D38-B84B-E717C6DAFCFC}
     */
    public void addInt(String aFieldName, int aValue) {
        addValue(aFieldName,
            new Integer(aValue));
    }

    /**
     * Adds a long value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {579DD087-287F-498B-9BB3-BED89FCDE9B3}
     */
    public void addLong(String aFieldName, long aValue) {
        addValue(aFieldName,
            new Long(aValue));
    }

    /**
     * Adds an IMessage value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {A2DE8A9D-4681-4C0D-BFCC-A88A7E341799}
     */
    public void addMessage(String aFieldName, IMessage aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a short value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {69363788-CA93-4033-BA61-E11B8FE596C3}
     */
    public void addShort(String aFieldName, short aValue) {
        addValue(aFieldName,
            new Short(aValue));
    }

    /**
     * Adds a java.sql.Date value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {458CB561-0C3B-400F-9101-5B9CCFD45BBC}
     */
    public void addSQLDate(String aFieldName, Date aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a String value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {8D7FFA04-4D86-46E9-B62A-5D3AD2F3E813}
     */
    public void addString(String aFieldName, String aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a java.sql.Timestamp value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {3688CA72-B91D-483A-9EAE-CFC9368DEF8A}
     */
    public void addTimestamp(String aFieldName, Timestamp aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a java.util.Date value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {81B1916C-1C48-4235-8AC5-6F36670EC893}
     */
    public void addUtilDate(String aFieldName, java.util.Date aValue) {
        addValue(aFieldName, aValue);
    }

    /**
     * Adds a new value to the specified field.
     *
     *  @param aFieldName The name of the field recieving the value.
     *  @param aValue The value of the field. Null values will not be added.
     * @modelguid {FB4D400B-4EEC-42E4-A462-D614B7AF91DB}
     */
    public void addValue(String aFieldName, Object aValue) {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            throw new IllegalArgumentException("aFieldName cannot be null");
        }
        Short lIndex = (Short)fieldIndex.get(aFieldName);
        if (lIndex == null) {
            addField(aFieldName, 0);
            lIndex = (Short)fieldIndex.get(aFieldName);
        }
        if (aValue == null) {
            ((Vector)columns.elementAt(lIndex.shortValue())).addElement(NULL_ELEMENT_VALUE);
        } else {
            ((Vector)columns.elementAt(lIndex.shortValue())).addElement(aValue);
        }
    }

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
     * @modelguid {24B4E34E-0AB0-4D91-B4E6-3667B0CC4CDE}
     */
    public BigDecimal getBigDecimal(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (BigDecimal) lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type java.math.BigDecimal\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

    /**
     * Returns a boolean value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex
     * @return
     * @throws MessagingException
     * @int aRowIndex  The index of the row for for which the value is to be added
     * @exception MessagingException  Thrown if the underlying value is not of type boolean or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {B90F579C-7C94-4659-B06C-E2E9A7D4A5D8}
     */
    public boolean getBoolean(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Boolean)lRetValue).booleanValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type boolean\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {50D03572-ECE4-4289-AE84-8F457081F9E2}
     */
    public char getChar(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Character)lRetValue).charValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type char\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {B50F1D39-74B2-4270-88AB-ED2F44449A9B}
     */
    public double getDouble(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Double)lRetValue).doubleValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type double\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

    /**
     * Get the names of all of the columns of the message.
     *
     * @return Vector The names of all of the fields within the message.
     * @modelguid {1245309F-33A3-420D-91B4-074C20F4E532}
     */
    public Vector getFieldNames() {
        Vector theRetValue = new Vector();
        for (int i = 0; i < fields.size(); i++) {
            theRetValue.addElement((String)((FieldInfo)fields.elementAt(i)).getName());
        }
        return theRetValue;
    }

    /**
     * This method returns a Vector of column (field) objects. These
     * describe the database schema in terms of the name and
     * width of each field.
     *
     * @return Vector All the columns (fields) of this Message.
     * @modelguid {91C1AB16-1EEA-4206-B9EB-BAFBCA88DFC3}
     */
    public Vector getFields() {
        return fields;
    }

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
     * @modelguid {EDA29C7F-BC3F-4AB5-A05D-F49963AC418A}
     */
    public float getFloat(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Float)lRetValue).floatValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type float\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {4F1EA798-067D-49AB-B8BA-B4DCE6BF10BF}
     */
    public int getInt(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Integer)lRetValue).intValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type integer\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {6F469A32-6F50-4858-9D17-606EC45F64F3}
     */
    public long getLong(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Long)lRetValue).longValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type long\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {2CDAB385-9454-4BD4-9552-3F6586EE3A1E}
     */
    public IMessage getMessage(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (IMessage)lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type IMessage\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

    /**
     * Return the value for the field specified by the input name as a
     * <code>Object</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return boolean The value of the column requested.
     * @modelguid {F0395E7C-D986-41C0-8EBD-AE9123662675}
     */
    public Object getObjectValue(String fieldName, short aIndex) {
        Object theRetValue = null;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return theRetValue;
        }
        if (aIndex < 0) {
            return theRetValue;
        }
        Short index = (Short)fieldIndex.get(fieldName);
        if (index != null) {
            try {
                theRetValue = ((Vector)columns.elementAt(index.shortValue())).elementAt(aIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                theRetValue = null;
            }
        }
        return theRetValue;
    }

    /**
     * 	  This method returns the record number associated with this Message.
     *
     * 	  @return short The unique record number of this Message
     * 	  @modelguid {CC7E94A0-48E0-4D00-B5EF-A6D6CC1E7A94}*/
    public short getRecordNumber() {
        return recordNumber;
    }

    /**
     * Returns the value index of the first occurance of the value requested in the
     * containing field.
     *
     * @param fieldName The column name of the value to get its index.
     * @param value The value to get the index for.
     * @return short - The index of the value in the containing field.
     * @modelguid {1FFE5B8C-4542-4448-808A-55D8C2218372}
     */
    public int getRowIndex(String fieldName, String value) {
        return 0;
    }

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
     * @modelguid {A8F588DE-EA43-4534-B038-6077A04A783B}
     */
    public short getShort(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Short)lRetValue).shortValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type short\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {E0AB682A-2718-49DE-BBE7-28F68C7691F2}
     */
    public Date getSQLDate(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (Date) lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type java.sql.Date\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {4A6ACF32-995C-4603-98F7-AB578D938C51}
     */
    public String getString(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (String)lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type String\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {EBDB7D7E-8D98-44E5-880F-7CFFEC5AD366}
     */
    public Timestamp getTimestamp(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (Timestamp) lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type java.sql.Timestamp\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {C6000AC4-D90E-4269-A898-F1EFBB846B8A}
     */
	public java.util.Date getUtilDate(String aFieldName, int aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return (java.util.Date) lRetValue;
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type java.util.Date\n" +
                "The underlying type is " + lRetValue.getClass());
        } catch (MessagingException e) {
            throw e;
        }
    }

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
     * @modelguid {6111CE46-AD08-4359-AC21-F88AF1F87B72}
     */
    public Object getValue(String aFieldName, int aRowIndex) throws MessagingException {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            throw new IllegalArgumentException("FieldName cannot be null");
        }
        if (aRowIndex < 0) {
            throw new IllegalArgumentException("The row index cannot be less than 0");
        }
        Object lRetValue = null;
        Short lIndex = (Short)fieldIndex.get(aFieldName);
        if (lIndex != null) {
            try {
                lRetValue = ((Vector)columns.elementAt(lIndex.shortValue())).elementAt(aRowIndex);
                // Check to see if the value is null, if so return null
                if (lRetValue instanceof String && lRetValue.equals(NULL_ELEMENT_VALUE)) {
                    return null;
                } else {
                    return lRetValue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (getValues(aFieldName).size() == 0) {
                    // There is no data defined for this column, return null
                    return null;
                }
                throw new MessagingException("mojo.km.messaging.Message.getValue()\nThe requested row index of " + aRowIndex +
                    " is out of range for field '" + aFieldName + "'.\n" + "The current number of rows in this message for '" +
                    aFieldName + "' is " + getValues(aFieldName).size());
            }
        } else {
            return null;
        }
    }

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
     *  @param anIndex Index of the row to get the value from (usually 0 - zero).
     *  @return String The value of the column requested.
     * @modelguid {9218431D-6FE6-4E3F-8625-29A66DC4626C}
     */
    public String getValue(String aFieldName, short anIndex) {
        String theRetValue = null;
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            return theRetValue;
        }
        if (anIndex < 0) {
            return theRetValue;
        }
        Short index = (Short)fieldIndex.get(aFieldName);
        if (index != null) {
            try {
                theRetValue = (String)((Vector)columns.elementAt(index.shortValue())).elementAt(anIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                theRetValue = null;
            }
        }
        return theRetValue;
    }

    /**
     * 	 Returns the value index of the first occurance of the value requested in the
     * 	 containing field.
     *
     * 	 @param fieldName The column name of the value to get its index.
     * 	 @param value The value to get the index for.
     * 	 @return short - The index of the value in the containing field. Returns -1 if value not found.
     * @modelguid {99609C08-6D8A-4F41-A014-E187C8CD69DD}
     */
    public short getValueIndex(String fieldName, String value) {
        short theRetValue = -1;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return theRetValue;
        }
        if (value == null || value.trim().length() < 1) {
            return theRetValue;
        }
        Short index = (Short)fieldIndex.get(fieldName);
        if (index != null) {
            try {
                Vector colVector = (Vector)columns.elementAt(index.shortValue());
                theRetValue = (short)colVector.indexOf(value);
            } catch (ArrayIndexOutOfBoundsException e) { }
        }
        return theRetValue;
    }

    /**
     * 	  Returns a Vector which reflect the values of the fields in this DataItem.
     *
     * 	  @param fieldName The name of the column
     * 	  @return
     * @see #getValue(mojo.business.String, short)
     * @returns Vector All the field values of this Message.
     * 	  @modelguid {75B38237-DD44-4307-B431-B5B701A3D516}*/
    public Vector getValues(String fieldName) {
        Vector theRetValue = null;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return theRetValue;
        }
        Short index = (Short)fieldIndex.get(fieldName);
        if (index != null) {
            int aIndex = index.shortValue();
            int i = 0;
            theRetValue = new Vector();
            for (Enumeration e = ((Vector)columns.elementAt(aIndex)).elements(); e.hasMoreElements(); ) {
                theRetValue.addElement(e.nextElement());
                i++;
            }
        }
        return theRetValue;
    }

    /**
     *  Checks if there are more rows to iterate upon.
     *
     *  @return boolean True if there are more rows, False if not
     * @modelguid {91E89C70-5BDC-4C24-93BD-1AAF2002AB24}
     */
    public boolean hasMoreRows() {
        return false;
    }

    /**
     * 	 Initialize a Message object which has null
     * 	 Strings for its field values.
     *
     * 	 @param recordNumber The unique number for this record.
     * 	 @param theFields The array of FieldInfoImpl objects
     * 	        which represent the names and lengths of the fields in this
     * 	        Message object.
     * 	 @modelguid {19905BF1-2306-445A-87A6-5CA5996C3521}*/
    public void initialize(short recordNumber, FieldInfo[] theFields) {
        if (theFields == null || theFields.length < 1) {
            return;
        } else if (theFields[0] == null) {
            return;
        }
        if (recordNumber < 0) {
            return;
        }
        this.recordNumber = recordNumber;
        fieldIndex = new Hashtable();
        for (short i = 0; i < theFields.length; i++) {
            Short index = new Short(i);
            this.fields.addElement(theFields[i]);
            fieldIndex.put(theFields[i].getName(), index);
            columns.addElement(
                new Vector());
        }
    }

    /**
     * Removes a value from a field in the message.
     *
     * @param aFieldName The name of the field containting the value to remove
     * @param anIndex The index of the value to remove in the field
     * @modelguid {73F330F6-93F8-4589-859E-0C5637ABEF4B}
     */
    public void removeValue(String aFieldName, int anIndex) {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            return;
        }
        if (anIndex < 0) {
            return;
        }
        Short lIndex = (Short)fieldIndex.get(aFieldName);
        if (lIndex != null) {
            try {
                ((Vector)columns.elementAt(lIndex.shortValue())).removeElementAt(anIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    /**
     * Removes a value from a field in the message.
     *
     * @param fieldName The name of the field containting the value to remove
     * @param aIndex The index of the value to remove in the field
     * @modelguid {256A23FE-586B-4B09-AE83-AC6A3BE79EB4}
     */
    public void removeValue(String fieldName, short aIndex) {
        if (fieldName == null || fieldName.trim().length() < 1) {
            return;
        }
        if (aIndex < 0) {
            return;
        }
        Short index = (Short)fieldIndex.get(fieldName);
        if (index != null) {
            try {
                ((Vector)columns.elementAt(index.shortValue())).removeElementAt(aIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    /**
     * Replaces a value in a field in the message. If the field requested
     * does not exist, nothing will happen. If there is a value in the
     * indicated index, it will be replaced. If there is no value in the
     * indicated index, the value supplied will be placed there.
     *
     * @param fieldName The name of the field containting the value to replace
     * @param aIndex The index of the value to replace in the field
     * @param value The value to replace the existing value with
     * @deprecated As of 9/19/00, use replaceValue(String, int, Object)
     * @modelguid {98259A51-4C57-4ADD-8D95-AEE893001F31}
     */
    public void replaceValue(String fieldName, short aIndex, String value) {
        if (fieldName == null || fieldName.trim().length() < 1) {
            return;
        }
        if (aIndex < 0) {
            return;
        }
        if (value == null) {
            return;
        }
        Short index = (Short)fieldIndex.get(fieldName);
        if (index != null) {
            try {
                String prevValue = getValue(fieldName, aIndex);
                if (prevValue != null) {
                    removeValue(fieldName, aIndex);
                }
                ((Vector)columns.elementAt(index.shortValue())).insertElementAt(value, aIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    /**
     * 	  Resets the row cursor to the first row of data in the message.
     *
     * 	  @modelguid {318A3DE2-0F55-4B35-AB1D-ADFC13051A0D}*/
    public void reset() {
        tupleIndex = 0;
        hasMoreTuples = true;
    }

    /**
     * 	 Produces formatted output of all of the contents in this message.
     * 	 Each field is printed along with all of the field's element data.
     *
     * 	 @return String - The contents of this message. If there is no data
     * 	 in the message, an empty <code>String</code> is returned.
     * @modelguid {5B7057DE-6837-45C9-8738-5AC8ECFF1117}
     */
    public String toString() {
        Object[] lFields = getFieldNames().toArray();
        int lLength = lFields.length;
        if (lLength == 0) {
            return "Message has no fields";
        }
        StringBuffer lReturn = new StringBuffer();
        Object lElementValue = null;
        int lRowCount = 0;
        try {
            //lRowCount = getValues((String) getFieldNames().elementAt(0)).size();
            lRowCount = getValues(lFields[0].toString()).size();
        } catch (NullPointerException e) {
            return "Empty Message";
        }
        try {
            for (int i = 0; i < lRowCount; i++) {
                lReturn.append("  Row [");
                lReturn.append(i);
                lReturn.append("]\n");
                for (int j = 0; j < lLength; j++) {
                    lReturn.append("    ");
                    lReturn.append(lFields[j].toString());
                    lReturn.append(": ");
                    lElementValue = getValue(lFields[j].toString(), i);
                    if (lElementValue == null) {
                        lReturn.append("[null]");
                    } else if (lElementValue instanceof IMessage) {
                        lReturn.append("\n\nBegin Nested Message Values\n");
                        lReturn.append(lElementValue.toString());
                        lReturn.append("End Nested Message Values");
                    } else {
                        lReturn.append(lElementValue.toString());
                    }
                    //if (lElementValue instance
                    //lReturn.append(
                    //lElementValue == null
                    //? "[null]"
                    //: lElementValue.toString());
                    lReturn.append("\n");
                }
            }
        } catch (MessagingException e) {
            return "Message.toString() error\n" + e;
        } catch (Throwable e) {
            return "Message.toString() error\n" + e;
        }
        return lReturn.toString();
    }

	/** @modelguid {6FBA87A6-CB69-40E8-AD50-27D63859664D} */
    private final String NULL_ELEMENT_VALUE = "NULL_MESSAGE_ELEMENT_VALUE";

	/** @modelguid {36225129-1B37-443D-B412-30DB40F1A5EA} */
    private class RowIterator implements Iterator {
		/** @modelguid {B8898D88-4199-4F6E-A139-D0DAC10B9046} */
        private int currentIndex;
		/** @modelguid {3AAFB857-1288-449D-98A5-857B2783891F} */
        private int rowCount;
		/** @modelguid {3C9A2E3D-D2C1-48BD-A035-FD8BDFA6B348} */
        private IMessage message;

        /**
         * Initializes this iterator from a message
         *
         * @param aMessage  The message wrapped by this iterator
         * @modelguid {9C11B496-5047-404A-9C48-72828E89F8CF}
         */
        private RowIterator(IMessage aMessage) {
            // Initialize this Iterator
            message = aMessage;
            currentIndex = -1;
            rowCount = message.getRowCount();
        }

        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next would return an element rather than throwing an exception)
         *
         * @return boolean true if the iterator has more elements
         * @modelguid {1979FA01-A6F8-425A-AC58-B34C6B592899}
         */
        public boolean hasNext() {
            return currentIndex < rowCount - 1;
        }

        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next would return an element rather than throwing an exception)
         *
         * @return Object The next element in the iteration.  The acutal type of this element is of <code>mojo.km.messaging.IMessage</code>
         * @throws NoSuchElementException
         * @exception NoSuchElementException  Thrown if this iteration has no more elements
         * @modelguid {45589C6F-F261-4A04-A513-6657F875C6D6}
         */
        public Object next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ++currentIndex;
            IMessage lMsg = message.getEmptyRow();
            String lFieldName = null;
            try {
                for (Iterator i = message.getFieldNames().iterator(); i.hasNext(); ) {
                    lFieldName = (String)i.next();
                    lMsg.addValue(lFieldName, message.getValue(lFieldName, currentIndex));
                }
            } catch (Exception e) {
                throw new NoSuchElementException("Could not create new row\n" + e);
            }
            return lMsg;
        }

        /**
         * This method is not supported and will throw <code>UnsupportedOperationException</code> if called.
         *
         * @throws UnsupportedOperationException
         * @exception UnsupportedOperationException  Thrown if this method is called
         * @modelguid {03239283-6F3D-46B2-B5E9-5C4450D108E4}
         */
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Adds a char value to the next row of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be added
     * @param aValue  The value to add
     * @modelguid {3BCC7163-7F6E-4576-AE7C-2EA1C8F28730}
     */
    public void addCharVaue(String aFieldName, char aValue) {
        addValue(aFieldName,
            new Character(aValue));
    }

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
     * @modelguid {68448BF5-107E-4D3D-AECF-02BFD7B99D0E}
     */
    public boolean getBooleanValue(String fieldName, short aIndex) {
        if (fieldName == null || fieldName.trim().length() < 1) {
            return false;
        }
        if (aIndex < 0) {
            return false;
        }
        try {
            String theValue = getValue(fieldName, aIndex).toUpperCase();
            if (theValue == null) {
                return false;
            }
            return (theValue.equals("TRUE") || theValue.equals("T") || theValue.equals("1") || theValue.equals("Y") ||
                theValue.equals("YES"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns a char value from a given row index of a given field
     *
     * @param aFieldName  The name of the field/column for which the value is to be returned
     * @param aRowIndex  The index of the row for for which the value is to be added
     * @return
     * @throws MessagingException
     * @exception MessagingException  Thrown if the underlying value is not of type char or
     *                                if aRowIndex exceeds the number of rows present in this message.
     * @modelguid {CB63FD6B-9AE4-4021-AE39-AC403D7AF539}
     */
    public char getCharValue(String aFieldName, short aRowIndex) throws MessagingException {
        Object lRetValue = null;
        try {
            lRetValue = getValue(aFieldName, aRowIndex);
            return ((Character)lRetValue).charValue();
        } catch (ClassCastException e) {
            throw new MessagingException("The underlying type of the value requested is not of type char\n" +
                "The underlying type is " + lRetValue.getClass());
        }
    }

    /**
     * Return the value for the field specified by the input name as a
     * <code>Date</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Date The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getSqlDate
     * @modelguid {FE2919E9-60FC-4C8E-92A6-23F7340EA9FC}
     */
    public Date getDateValue(String fieldName, short aIndex) {
        Date returnVal = null;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return returnVal;
        }
        if (aIndex < 0) {
            return returnVal;
        }
        String theValue = getValue(fieldName, aIndex);
        if (theValue == null) {
            return returnVal;
        }
        try {
            returnVal = Date.valueOf(theValue);
        } catch (Exception ne) {
        }
        return returnVal;
    }

    /**
     * Return the value for the field specified by the input name as a
     * <code>Double</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Double The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getDouble
     * @modelguid {42DD97A8-278C-4F8C-BADC-D00B127150C9}
     */
    public Double getDoubleValue(String fieldName, short aIndex) {
        Double returnVal = null;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return returnVal;
        }
        if (aIndex < 0) {
            return returnVal;
        }
        String theValue = getValue(fieldName, aIndex);
        if (theValue == null) {
            return returnVal;
        }
        try {
            returnVal = new Double(theValue);
        } catch (Exception ne) {
        }
        return returnVal;
    }

    /**
     * Helper method used to create an empty message complete with fields of this.  The message can be populated and
     * then inserted as a row.
     *
     * @return IMessage Contains no rows but has the same fields as this message
     * @modelguid {06335A75-CB7C-4623-B1C8-053BA005EAF2}
     */
    public IMessage getEmptyRow() {
        IMessage lMsg = new Message();
        for (Iterator i = getFieldNames().iterator(); i.hasNext(); ) {
            lMsg.addField((String)i.next(), 0);
        }
        return lMsg;
    }

    /**
     * Return the value for the field specified by the input name as a
     * <code>Integer</code>.
     *
     * @param fieldName The column name of the value to retrieve.
     * @param aIndex Index of the row to get the value from (usually 0 - zero).
     * @return Integer The value of the column requested.
     * @deprecated  As of 9/20/2000, replaced by getInteger
     * @modelguid {2AFA98BD-6CFF-4697-BF35-C421C415B1BA}
     */
    public Integer getIntegerValue(String fieldName, short aIndex) {
        Integer returnVal = null;
        if (fieldName == null || fieldName.trim().length() < 1) {
            return new Integer(0);
        }
        if (aIndex < 0) {
            return new Integer(0);
        }
        String theValue = getValue(fieldName, aIndex);
        if (theValue == null) {
            return new Integer(0);
        }
        try {
            if (theValue.indexOf(".") > -1) {
                Double dblObj = new Double(theValue);
                returnVal = new Integer(dblObj.intValue());
            }
            returnVal = new Integer(theValue);
        } catch (Exception ne) {
        }
        return returnVal;
    }

    /**
     * Returns the number of data rows within this message.
     *
     * @return int Number of data rows in this message.
     * @modelguid {403E4BFD-F031-41E3-A7F9-CDECC44E4959}
     */
    public int getRowCount() {
        int lCount = 0;
        // Check the number of values in the first field of the message
        try {
            lCount = getValues((String)getFieldNames().elementAt(0)).size();
        } catch (Exception e) {
            // Probably no field exist.  Return 0
        }
        return lCount;
    }

    /**
     * Returns an Iterator of read-only rows in this message. The rows are said to be read only because modifications to
     * data within the rows will NOT be reflected in this message.  This method is most usefull for message retrieval.
     *
     * @return Iterator Iterator of rows in this message.  Note: Each element in the Iterator is of type IMessage.  The multiplicity
     *                  of each message returned is 1.  Also take caution as modifications to rows will NOT be relflected in
     *                  this message.
     * @modelguid {7D0ABEFF-79F2-4551-B95A-C89B89685F36}
     */
    public Iterator getRows() {
        return new RowIterator(this);
    }

    /**
     * Inserts a row into this message.
     *
     * @param aMsg The multiplicity of aMsg message is assumed to be 1.  The fields of aMsg must be exactly the same as
     *             fields defined in this message.  Data in aMsg will be added as a row in this message.
     * @throws MessagingException
     * @exception MessagingException  Thrown if the fields of aMsg are not the same as that of this message.
     * @modelguid {7B30F185-2E1D-4EAE-8211-47BE8F2D55E2}
     */
    public void insertRow(IMessage aMsg) throws MessagingException {
        String lFieldName = null;
        try {
            for (Iterator i = aMsg.getFieldNames().iterator(); i.hasNext(); ) {
                lFieldName = (String)i.next();
                if (getFieldNames().contains(lFieldName)) {
                    addValue(lFieldName, aMsg.getValue(lFieldName, 0));
                } else {
                    throw new MessagingException(lFieldName + " from incomming row not found in message");
                }
            }
        } catch (MessagingException e) {
            throw e;
        } catch (Exception e) {
            throw new MessagingException("Unexcpected exception inserting row into message\n" + e);
        }
    }

    /**
     * Replaces a value in a field in the message.
     *
     * @param aFieldName The name of the field containting the value to replace
     * @param anIndex The index of the value to replace in the field
     * @param aValue The value to replace the existing value with
     * @throws MessagingException
     * @modelguid {81BACCF2-5BFC-4729-8A43-B48A932759A1}
     */
    public void replaceValue(String aFieldName, int anIndex, Object aValue) throws MessagingException {
        if (aFieldName == null || aFieldName.trim().length() < 1) {
            return;
        }
        if (anIndex < 0) {
            return;
        }
        if (aValue == null) {
            return;
        }
        Short lIndex = (Short)fieldIndex.get(aFieldName);
        if (lIndex != null) {
            try {
                Object prevValue = getValue(aFieldName, anIndex);
                prevValue = ((Vector)columns.elementAt(lIndex.shortValue())).elementAt(anIndex);
                if (prevValue != null) {
                    removeValue(aFieldName, anIndex);
                }
                ((Vector)columns.elementAt(lIndex.shortValue())).insertElementAt(aValue, anIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }


}
