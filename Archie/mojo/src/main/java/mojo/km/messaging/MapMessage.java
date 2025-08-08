   package mojo.km.messaging;


   import java.util.*;
   import mojo.km.messaging.exception.*;
   import java.lang.reflect.Array;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *    mojo implementation of a JMS MapMessage.
 *    Creation date: (5/17/00 10:45:23 AM)
 *    @author: D. Marple
 * @modelguid {6B27DC3C-435B-4AE6-9664-17A4B452F39E}
 */
   public class MapMessage implements IMapMessage, Serializable {
	/** @modelguid {4AD2E040-366B-4CA2-BFC7-6E3020432DCE} */
	  private Hashtable fields = null;
	/** @modelguid {4C2BB3DC-677D-460D-ACD8-E123D44C852D} */
	  private final static int ARRAYMAX = 10;
   /**
 * 	 MapMessage constructor.
 * @modelguid {25460204-8D99-43FA-8AB2-35C5451EC31B}
 */
	  public MapMessage() {
		 fields = new Hashtable();
	  }
   /**
 * 	 addBoolean method
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {D240482E-91AB-4DFC-BD9D-B8AD51CE242B}
 */
	  public void addBoolean(String fieldName, boolean value) throws MessagingException{
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new boolean[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Boolean.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setBoolean(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addByte method comment.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {8CF6DC57-64C2-44CF-B07C-E7A24ECA0899}
 */
	  public void addByte(String fieldName, byte value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new byte[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Byte.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setByte(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addBytes method comment.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {CB5F6901-5D4D-4D45-ACBB-6B8548BE6F99}
 */
	  public void addBytes(String fieldName, byte[] value) throws MessagingException {
		 Vector column = null;
		 try {
			column = (Vector)fields.get(fieldName);
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is already in use by a base type");
			}
		 if(column == null) {
			column = new Vector();
			fields.put(fieldName, column);
		 }
		 column.addElement(value);		
	  }
   /**
 * 	 addChar method.
 * 	 Creation date: (5/18/00 2:07:34 PM)
 * 	 @param fieldName java.lang.String
 * 	 @param value char
 * 	 @throws MessagingException
 * @exception mojo.km.messaging.exceptions.FieldInsertException The exception description.
 * @modelguid {427BD018-1138-473D-AF14-6615F30E308D}
 */
	  public void addChar(String fieldName, char value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new char[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Character.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setChar(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}	
	  }
   /**
 * 	 addColumn method.
 * 	 Creation date: (5/17/00 11:21:25 AM)
 * 	 @param fieldName java.lang.String
 * 	 @param columnValue java.util.Vector
 * @param storeAsBaseTypes
 * @throws MessagingException
 * @modelguid {D2A01CFC-2166-4917-8359-608F25E14B7A}
 */
	  public void addColumn(String fieldName, Vector columnValue, boolean storeAsBaseTypes) throws MessagingException {
		 Object element = columnValue.elementAt(0);
		 if(element == null) 
			return;
	  
		 int size = columnValue.size();
		 int type = 0;
		 int index = 0;
		 int[] iArray = null;
		 long[] lArray = null;
		 short[] sArray = null;
		 byte[] bArray = null;
		 float[] fArray = null;
		 double[] dArray = null;
		 char[] cArray = null;
		 boolean[] boArray = null;
		 boolean isNewField = false;
		 Object nArray = null;
	  
		 Object field = fields.get(fieldName);
		 if(field == null) {
			isNewField = true;
		 }
	  
		 if(element instanceof Integer) {
			type = 1;
			if(isNewField && storeAsBaseTypes) {
			   iArray = new int[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Integer.TYPE);
			   ((Field)field).setColumn(iArray);
			}
		 }
		 else if(element instanceof Long) {
			type = 2;
			if(isNewField && storeAsBaseTypes) {
			   lArray = new long[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Long.TYPE);
			   ((Field)field).setColumn(lArray);
			}
		 }
		 else if(element instanceof Short) {
			type = 3;
			if(isNewField && storeAsBaseTypes) {
			   sArray = new short[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Short.TYPE);
			   ((Field)field).setColumn(sArray);
			}
		 }
		 else if(element instanceof Byte) {
			type = 4;
			if(isNewField && storeAsBaseTypes) {
			   bArray = new byte[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Byte.TYPE);
			   ((Field)field).setColumn(bArray);
			}
		 }
		 else if(element instanceof Float) {
			type = 5;
			if(isNewField && storeAsBaseTypes) {
			   fArray = new float[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Float.TYPE);
			   ((Field)field).setColumn(fArray);
			}
		 }
		 else if(element instanceof Double) {
			type = 6;
			if(isNewField && storeAsBaseTypes) {
			   dArray = new double[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Double.TYPE);
			   ((Field)field).setColumn(dArray);
			}
		 }
		 else if(element instanceof Character) {
			type = 7;
			if(isNewField && storeAsBaseTypes) {
			   cArray = new char[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Character.TYPE);
			   ((Field)field).setColumn(cArray);
			}
		 }
		 else if(element instanceof Boolean) {
			type = 8;
			if(isNewField && storeAsBaseTypes) {
			   boArray = new boolean[size];
			   field = new Field();
			   ((Field)field).setIndex(0);
			   ((Field)field).setOriginalType(Boolean.TYPE);
			   ((Field)field).setColumn(boArray);
			}
		 }
	  
		 if(type == 0 && storeAsBaseTypes)
			throw new MessagingException("Cannot store anything but a wrapper type as a base type");
	  
		 if(type != 0 && !storeAsBaseTypes && isNewField)
			field = new Vector();
	  
		 if(type == 0 && isNewField)
			field = new Vector();
	  
		 if(field instanceof Field || (isNewField && storeAsBaseTypes)) {
			if(type == 0)
			   throw new MessagingException("Cannot insert object types into a base type field");
			Field newField = (Field)field;
			nArray = newField.getColumn();
			int offset = newField.getIndex();
			size += offset;
			int start = offset;
			try {
			   for(int i = start;i<size;i++) {
				  element = columnValue.elementAt(i);
				  if(type == 1) 
					 Array.setInt(nArray, i, ((Integer)element).intValue());
				  else if(type == 2)
					 Array.setLong(nArray, i, ((Long)element).longValue());
				  else if(type == 3)
					 Array.setLong(nArray, i, ((Short)element).shortValue());
				  else if(type == 4)
					 Array.setByte(nArray, i, ((Byte)element).byteValue());
				  else if(type == 5)
					 Array.setFloat(nArray, i, ((Float)element).floatValue());
				  else if(type == 6)
					 Array.setDouble(nArray, i, ((Double)element).doubleValue());
				  else if(type == 7)
					 Array.setChar(nArray, i, ((Character)element).charValue());
				  else if(type == 8)
					 Array.setBoolean(nArray, i, ((Boolean)element).booleanValue());
				  index = i;
			   }
			}
			   catch(ClassCastException e) {
				  throw new MessagingException("The objects in the column vector are not of the same type");
			   }
			index++;
			newField.setIndex(index);
		 }
		 else if(field instanceof Vector) {
			if(storeAsBaseTypes)
			   throw new MessagingException("Cannot convert to base types with this field, it is already established as an object field");
			Vector newVector = (Vector)field;
			for(int i = 0;i<size;i++)
			   newVector.addElement(columnValue.elementAt(i));
		 }
	  
	  }
   /**
 * 	 addCurrency method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {3705F2C2-D7F0-4355-900A-68B3E067D178}
 */
	  public void addCurrency(String fieldName, double value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new double[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Double.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setDouble(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addDate method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {9EAB416A-993D-46F6-AB79-0114FFB5D1FB}
 */
	  public void addDate(String fieldName, java.util.Date value) throws MessagingException {
		 Vector column = null;
		 try {
			column = (Vector)fields.get(fieldName);
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is already in use by a base type");
			}
		 if(column == null) {
			column = new Vector();
			fields.put(fieldName, column);
		 }
		 column.addElement(value);		
	  }
   /**
 * 	 addDouble method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {36108180-79AC-4FE5-9572-B73A59D5271C}
 */
	  public void addDouble(String fieldName, double value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new double[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Double.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setDouble(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addFloat method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {1B449AFD-A1CF-42F7-A201-3DF468A01A2A}
 */
	  public void addFloat(String fieldName, float value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new float[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Float.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setFloat(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addInt method comment.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {AB0B2692-D295-4166-B09B-AB5152215B5C}
 */
	  public void addInt(String fieldName, int value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new int[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Integer.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setInt(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addLong method
 * 	 Creation date: (5/18/00 2:06:06 PM)
 * 	 @param fieldName java.lang.String
 * 	 @param value long
 * 	 @throws MessagingException
 * @exception mojo.km.messaging.exceptions.FieldInsertException The exception description.
 * @modelguid {5B74DCC1-670F-4161-AE81-E32CF953A33D}
 */
	  public void addLong(String fieldName, long value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new long[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Long.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setLong(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}	
	  }
   /**
 * 	 addObject method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {41956C5F-FDEA-42D5-846B-E0B4A849B43E}
 */
	  public void addObject(String fieldName, Object value) throws MessagingException {
		 Vector column = null;
		 try {
			column = (Vector)fields.get(fieldName);
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is already in use by a base type");
			}
		 if(column == null) {
			column = new Vector();
			fields.put(fieldName, column);
		 }
		 column.addElement(value);	
	  }
   /**
 * 	 addShort method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {9DDFB1F4-5711-44BD-866F-64CEF55EAC3C}
 */
	  public void addShort(String fieldName, short value) throws MessagingException {
		 Field cField = (Field)fields.get(fieldName);
		 Object column = null;
		 int index = 0;
		 if(cField == null) {
			column = new short[ARRAYMAX];
			cField = new Field();
			cField.setColumn(column);
			fields.put(fieldName, cField);
			cField.setOriginalType(Short.TYPE);
		 }
		 else {
			index = cField.getIndex();
			column = cField.getColumn();		
		 }
		 if(index >= Array.getLength(column)) {
			column = growArray(column, cField.getOriginalType());
			cField.setColumn(column);
		 }	
		 try {
			Array.setShort(column, index, value);
			index++;
			cField.setIndex(index);
		 }
			catch(IllegalArgumentException e) {
			   throw new MessagingException("Field " + fieldName + " will not except a boolean type");
			}
	  }
   /**
 * 	 addString method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {0C01A757-6416-4A2E-BA96-379D0B4FC5AE}
 */
	  public void addString(String fieldName, String value) throws MessagingException {
		 Vector column = null;
		 try {
			column = (Vector)fields.get(fieldName);
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is already in use by a base type");
			}
		 if(column == null) {
			column = new Vector();
			fields.put(fieldName, column);
		 }
		 column.addElement(value);	
	  }
   /**
 * 	 addTimestamp method.
 * @param fieldName
 * @param value
 * @throws MessagingException
 * @modelguid {2B003A71-20AC-44A9-920F-BD181F591392}
 */
	  public void addTimestamp(String fieldName, Timestamp value) throws MessagingException {
		 Vector column = null;
		 try {
			column = (Vector)fields.get(fieldName);
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is already in use by a base type");
			}
		 if(column == null) {
			column = new Vector();
			fields.put(fieldName, column);
		 }
		 column.addElement(value);	
	  }
   /**
 * 	 getBoolean method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {7E9BB177-3703-4C3C-869F-C2DF0A0CA12E}
 */
	  public boolean getBoolean(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			boolean value = Array.getBoolean(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a boolean field");
			}
	  }
   /**
 * 	 getByte method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {4372E8FC-7D0F-43C9-A70F-8937F88D317F}
 */
	  public byte getByte(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			byte value = Array.getByte(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a byte field");
			}
	  }
   /**
 * 	 getBytes method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {30001359-C8F1-4C48-AD9A-4E591C69FE2E}
 */
	  public byte[] getBytes(String fieldName, int aIndex) throws MessagingException {
		 Vector column = null;
		 byte[] oValue = null;
		 try {
			column = (Vector)fields.get(fieldName);
			if(column == null) 
			   throw new MessagingException(fieldName + " does not exist");
			oValue = (byte[])column.elementAt(aIndex);
			if(oValue == null)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is null");
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is not a blob field");
			}
		 return oValue;
	  }
   /**
 * 	 getChar method.
 * 	 Creation date: (5/18/00 2:11:42 PM)
 * 	 @param fieldName java.lang.String
 * @param aIndex int
 * @return char
 * 	 @throws MessagingException
 * @exception mojo.km.messaging.exceptions.FieldRetrieveException The exception description.
 * @modelguid {838FA5C5-E3CE-4E12-8F0F-8688E1B57013}
 */
	  public char getChar(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			char value = Array.getChar(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a int field");
			}
	  }
   /**
 * 	 getColumn method.
 * 	 Creation date: (5/17/00 11:20:26 AM)
 * 	 @param fieldName java.lang.String
 * @return <{Vector}>
 * @throws MessagingException
 * @modelguid {8FEC2836-9EDC-4401-8626-6A784480065B}
 */
	  public Vector getColumn(String fieldName) throws MessagingException {
		 Object field = fields.get(fieldName);
		 if(field == null)
			throw new MessagingException(fieldName + " does not exist");
		 if(field instanceof Field) {
			Field newField = (Field)field;
			Class originalClass = newField.getOriginalType();
			Vector rVector = new Vector();
			int index = newField.getIndex();
			Object tArray = newField.getColumn();
			for(int i = 0;i<index;i++) {
			   if(originalClass == Integer.TYPE) 
				  rVector.addElement(new Integer(Array.getInt(tArray, i)));				
			   else if(originalClass == Long.TYPE) 
				  rVector.addElement(new Long(Array.getLong(tArray, i)));				
			   else if(originalClass == Short.TYPE)
				  rVector.addElement(new Short(Array.getShort(tArray, i)));				
			   else if(originalClass == Byte.TYPE)
				  rVector.addElement(new Byte(Array.getByte(tArray, i)));	 			
			   else if(originalClass == Float.TYPE)
				  rVector.addElement(new Float(Array.getFloat(tArray, i)));				
			   else if(originalClass == Double.TYPE)
				  rVector.addElement(new Double(Array.getDouble(tArray, i)));	 			
			   else if(originalClass == Boolean.TYPE)
				  rVector.addElement(new Boolean(Array.getBoolean(tArray, i)));				
			   else if(originalClass == Character.TYPE)
				  rVector.addElement(new Character(Array.getChar(tArray, i)));				
			}
			return rVector;
		 }
		 else 
			return(Vector)field;	
	  }
   /**
 * 	 getCurrency method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {31E0495B-57E6-4F39-83F5-2C26E0EDDD6E}
 */
	  public double getCurrency(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			double value = Array.getDouble(((Field)columnField).getColumn(), aIndex) * 100;
			long lValue = (long)value;
			value = lValue;
			return value / 100;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a double field");
			}
	  }
   /**
 * 	 getDate method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {BD93DF84-DD05-4FE1-A374-88C0889ECA07}
 */
	  public java.util.Date getDate(String fieldName, int aIndex) throws MessagingException {
		 Vector column = null;
		 java.util.Date oValue = null;
		 try {
			column = (Vector)fields.get(fieldName);
			if(column == null) 
			   throw new MessagingException(fieldName + " does not exist");
			oValue = (java.util.Date)column.elementAt(aIndex);
			if(oValue == null)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is null");
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is not a java.sql.Date field");
			}
		 return oValue;
	  }
   /**
 * 	 getDouble method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {B5394B12-38D3-48C0-9C48-C1C83D89B9C2}
 */
	  public double getDouble(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			double value = Array.getDouble(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a double field");
			}
	  }
   /**
 * 	 getFieldLength method
 * @param fieldName
 * @return  
 * @modelguid {9EA9C245-C5C5-4CCC-9B49-3BDB9B0BA3DE}
 */
	  public int getFieldLength(String fieldName) {
		 Object field = fields.get(fieldName);
		 if(field == null)
			return -1;
		 if(field instanceof Field)
			return ((Field)field).getIndex();
		 return ((Vector)field).size();
	  }
   /**
 * 	 getFieldNames methodt.
 * @return  
 * @modelguid {C7180DCA-F70C-453A-AB6A-508034BEEB33}
 */
	  public Vector getFieldNames() {
		 Enumeration keys = fields.keys();
		 Vector names = new Vector();
		 while(keys.hasMoreElements()) {
			names.addElement(keys.nextElement());
		 }
		 return names;
	  }
   /**
 * 	 getFloat method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {8FB4F50F-1088-43D4-90DB-C6344B911FCE}
 */
	  public float getFloat(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			float value = Array.getFloat(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a float field");
			}
	  }
   /**
 * 	 getInt method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {689D0E09-0688-4A0B-9B51-E389ACF78B60}
 */
	  public int getInt(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			int value = Array.getInt(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a int field");
			}
	  }
   /**
 * 	 getLong method.
 * 	 Creation date: (5/18/00 2:10:23 PM)
 * 	 @param fieldName java.lang.String
 * 	 @param aIndex int
 * 	 @return
 * @throws MessagingException
 * @exception mojo.km.messaging.exceptions.FieldRetrieveException The exception description.
 * @modelguid {0176D78E-FF6B-4BB9-A00E-182592F95859}
 */
	  public long getLong(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			long value = Array.getLong(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a int field");
			}	
	  }
   /**
 * 	 getObject method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {BB812DEB-53EF-4E8F-B84A-D3FE9E572E15}
 */
	  public Object getObject(String fieldName, int aIndex) throws MessagingException {
		 Vector column = null;
		 Object oValue = null;
		 try {
			column = (Vector)fields.get(fieldName);
			if(column == null) 
			   throw new MessagingException(fieldName + " does not exist");
			oValue = column.elementAt(aIndex);
			if(oValue == null)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is null");
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is not a object field");
			}
		 return oValue;
	  }
   /**
 * 	 getRow method.
 * @param rowNumber
 * @return
 * @throws MessagingException
 * @modelguid {6AB28D7D-DC1F-416E-894E-B1ECF40FCAD7}
 */
	  public Hashtable getRow(int rowNumber) throws MessagingException {
		 Enumeration kEnum = fields.keys();
		 String fieldName = null;
		 Hashtable rTable = new Hashtable();
		 Object field = null;
		 while(kEnum.hasMoreElements()) {
			fieldName = (String)kEnum.nextElement();
			field = fields.get(fieldName);
			if(field == null)
			   throw new MessagingException("No such field exists for field name " + fieldName);
			if(field instanceof Vector) {
			   try {
				  rTable.put(fieldName, ((Vector)field).elementAt(rowNumber));
			   }
				  catch(Exception e) {
					 throw new MessagingException("Invalid row index " + rowNumber + " for field " + fieldName);
				  }
			}
			else {
			   try {
				  if(((Field)field).getOriginalType() == Integer.TYPE) 
					 rTable.put(fieldName, new Integer(Array.getInt(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Long.TYPE) 
					 rTable.put(fieldName, new Long(Array.getLong(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Short.TYPE) 
					 rTable.put(fieldName, new Short(Array.getShort(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Byte.TYPE) 
					 rTable.put(fieldName, new Byte(Array.getByte(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Float.TYPE) 
					 rTable.put(fieldName, new Float(Array.getFloat(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Double.TYPE) 
					 rTable.put(fieldName, new Double(Array.getDouble(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Boolean.TYPE) 
					 rTable.put(fieldName, new Boolean(Array.getBoolean(((Field)field).getColumn(), rowNumber)));
				  else if(((Field)field).getOriginalType() == Character.TYPE) 
					 rTable.put(fieldName, new Character(Array.getChar(((Field)field).getColumn(), rowNumber)));
			   }
				  catch(Exception e1) {
					 throw new MessagingException("Invalid row index " + rowNumber + " for field " + fieldName);
				  }
			}
		 }
		 return rTable;
	  }
   /**
 * 	 getRow methodt.
 * @param rowNumber
 * @param fieldNames
 * @return
 * @throws MessagingException
 * @modelguid {4A62211D-72A9-4B86-8A14-8DAAD24D8A70}
 */
	  public Vector getRow(int rowNumber, Vector fieldNames) throws MessagingException {
		 int fSize = fieldNames.size();
		 String fieldName = null;
		 Vector rVector = new Vector();
		 Object field = null;
		 for(int i = 0;i<fSize;i++) {
			fieldName = (String)fieldNames.elementAt(i);
			field = fields.get(fieldName);
			if(field == null)
			   throw new MessagingException("No such field exists for field name " + fieldName);
			if(field instanceof Vector) {
			   try {
				  rVector.insertElementAt(((Vector)field).elementAt(rowNumber), i);
			   }
				  catch(Exception e) {
					 throw new MessagingException("Invalid row index " + rowNumber + " for field " + fieldName);
				  }
			}
			else {
			   try {
				  if(((Field)field).getOriginalType() == Integer.TYPE) 
					 rVector.insertElementAt(new Integer(Array.getInt(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Long.TYPE) 
					 rVector.insertElementAt(new Long(Array.getLong(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Short.TYPE) 
					 rVector.insertElementAt(new Short(Array.getShort(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Byte.TYPE) 
					 rVector.insertElementAt(new Byte(Array.getByte(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Float.TYPE) 
					 rVector.insertElementAt(new Float(Array.getFloat(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Double.TYPE) 
					 rVector.insertElementAt(new Double(Array.getDouble(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Boolean.TYPE) 
					 rVector.insertElementAt(new Boolean(Array.getBoolean(((Field)field).getColumn(), rowNumber)), i);
				  else if(((Field)field).getOriginalType() == Character.TYPE) 
					 rVector.insertElementAt(new Character(Array.getChar(((Field)field).getColumn(), rowNumber)), i);
			   }
				  catch(Exception e1) {
					 throw new MessagingException("Invalid row index " + rowNumber + " for field " + fieldName);
				  }
			}
		 }
		 return rVector;
	  }
   /**
 * 	 getShort method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {FEC5702D-E428-4E50-A4B8-2CF1AB3759FF}
 */
	  public short getShort(String fieldName, int aIndex) throws MessagingException {
		 Object columnField = fields.get(fieldName);
		 if(columnField == null) 
			throw new MessagingException(fieldName + " does not exist");
		 try {
			short value = Array.getShort(((Field)columnField).getColumn(), aIndex);
			return value;	
		 }
			catch(Exception e) {
			   throw new MessagingException(fieldName + " is not a short field");
			}
	  }
   /**
 * 	 getString method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {D67C6E10-13CF-4A07-8A5A-821EA0028039}
 */
	  public String getString(String fieldName, int aIndex) throws MessagingException {
		 Vector column = null;
		 String oValue = null;
		 try {
			column = (Vector)fields.get(fieldName);
			if(column == null) 
			   throw new MessagingException(fieldName + " does not exist");
			oValue = (String)column.elementAt(aIndex);
			if(oValue == null)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is null");
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is not a string field");
			}
		 return oValue;
	  }
   /**
 * 	 getTimestamp method.
 * @param fieldName
 * @param aIndex
 * @return
 * @throws MessagingException
 * @modelguid {7AC1E878-4708-413E-A5BD-BC16AE4C5ADB}
 */
	  public Timestamp getTimestamp(String fieldName, int aIndex) throws MessagingException {
		 Vector column = null;
		 Timestamp oValue = null;
		 try {
			column = (Vector)fields.get(fieldName);
			if(column == null) 
			   throw new MessagingException(fieldName + " does not exist");
			oValue = (Timestamp)column.elementAt(aIndex);
			if(oValue == null)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is null");
		 }
			catch(ClassCastException e) {
			   throw new MessagingException("Field " + fieldName + " is not a java.sql.Timestamp field");
			}
		 return oValue;
	  }
   /**
 * 	 growArray method.
 * 	 Creation date: (5/17/00 12:59:31 PM)
 * 	 @param x java.lang.reflect.Array
 * @modelguid {4D0A0C99-7D7B-40AB-9EF7-A7FA88930176}
 */
	  private Object growArray(Object x, Class newClass) {
		 int length = Array.getLength(x);
		 Object newX = Array.newInstance(newClass, length * 2);
		 System.arraycopy(x, 0, newX, 0, length);
		 return newX;
	  }
   /**
 * 	 removeValue method.
 * @param fieldName
 * @param aIndex
 * @throws MessagingException
 * @modelguid {C02823C1-25AB-4A3E-BD6D-B9F5F5E8E16E}
 */
	  public void removeValue(String fieldName, int aIndex) throws MessagingException {
		 Object field = (Vector)fields.get(fieldName);
		 if(field == null) {
			throw new MessagingException(fieldName + " does not exist");
		 }
		 if(field instanceof Vector) {
			Vector newVector = (Vector)field;
			int size = newVector.size();
			if(aIndex >= size)
			   throw new MessagingException(fieldName + " at index " + aIndex + " is out of bounds");
			newVector.removeElementAt(aIndex);
		 }
		 else {
			Field newField = (Field)field;
			int index = newField.getIndex();
			Object column = newField.getColumn();
			if(aIndex >= Array.getLength(column))
			   throw new MessagingException(fieldName + " at index " + aIndex + " is out of bounds");
			for(int i = aIndex;i<index - 1;i++) {
			   if(newField.getOriginalType() == Integer.TYPE) 
				  Array.setInt(column, i, Array.getInt(column, i + 1));
			   else if(newField.getOriginalType() == Long.TYPE) 
				  Array.setLong(column, i, Array.getLong(column, i + 1));
			   else if(newField.getOriginalType() == Short.TYPE) 
				  Array.setShort(column, i, Array.getShort(column, i + 1));
			   else if(newField.getOriginalType() == Byte.TYPE) 
				  Array.setByte(column, i, Array.getByte(column, i + 1));
			   else if(newField.getOriginalType() == Float.TYPE) 
				  Array.setFloat(column, i, Array.getFloat(column, i + 1));
			   else if(newField.getOriginalType() == Double.TYPE) 
				  Array.setDouble(column, i, Array.getDouble(column, i + 1));
			   else if(newField.getOriginalType() == Boolean.TYPE) 
				  Array.setBoolean(column, i, Array.getBoolean(column, i + 1));
			   else if(newField.getOriginalType() == Character.TYPE) 
				  Array.setChar(column, i, Array.getChar(column, i + 1));
			}
			newField.setIndex(index - 1);
		 }
	  }
   /**
 * 	 replaceValue method.
 * 	 Creation date: (5/18/00 2:14:24 PM)
 * 	 @param fieldName java.lang.String
 * 	 @param value org.omg.CORBA.Object
 * 	 @param aIndex int
 * 	 @param saveAsBaseType boolean
 * 	 @throws MessagingException
 * @exception mojo.km.messaging.exceptions.FieldInsertException The exception description.
 * @modelguid {C2163031-E94E-4B67-96D2-DCA83762D439}
 */
	  public void replaceValue(String fieldName, Object value, int aIndex, boolean saveAsBaseType) throws MessagingException {
		 Object field = fields.get(fieldName);
		 if(field == null)
			throw new MessagingException("No such field " + fieldName);
		 if(field instanceof Vector) {
			if(saveAsBaseType)
			   throw new MessagingException("Value cannot be saved in a field that is not reserved for base types");
			Vector newVector = (Vector)field;
			if(aIndex >= newVector.size())
			   throw new MessagingException("Index " + aIndex + " is outside of the range of the field " + fieldName);
			newVector.removeElementAt(aIndex);
			newVector.insertElementAt(value, aIndex);
		 }
		 else {
			if(!saveAsBaseType)
			   throw new MessagingException("Value cannot be saved in a field reserved for base types");
			Field newField = (Field)field;
			int index = newField.getIndex();
			if(aIndex >= index)
			   throw new MessagingException("Index " + aIndex + " is outside of the range of the field " + fieldName);	
			Object column = newField.getColumn();
			if(newField.getOriginalType() == Integer.TYPE) 
			   Array.setInt(column, aIndex, ((Integer)value).intValue());
			else if(newField.getOriginalType() == Long.TYPE) 
			   Array.setLong(column, aIndex, ((Long)value).longValue());
			else if(newField.getOriginalType() == Short.TYPE) 
			   Array.setShort(column, aIndex, ((Short)value).shortValue());
			else if(newField.getOriginalType() == Byte.TYPE) 
			   Array.setByte(column, aIndex, ((Byte)value).byteValue());
			else if(newField.getOriginalType() == Float.TYPE) 
			   Array.setFloat(column, aIndex, ((Float)value).floatValue());
			else if(newField.getOriginalType() == Double.TYPE) 
			   Array.setDouble(column, aIndex, ((Double)value).doubleValue());
			else if(newField.getOriginalType() == Boolean.TYPE) 
			   Array.setBoolean(column, aIndex, ((Boolean)value).booleanValue());
			else if(newField.getOriginalType() == Character.TYPE) 
			   Array.setChar(column, aIndex, ((Character)value).charValue());
			else
			   throw new MessagingException("Value cannot be converted to a base type");	
		 }
	  }
   /**
 * 	setRow method.
 * @param row
 * @param saveAsBaseTypes
 * @throws MessagingException
 * @modelguid {A0F24E8C-A638-429A-AFF1-2971A05645FC}
 */
	  public void setRow(Hashtable row, boolean saveAsBaseTypes) throws MessagingException {
	  //int fnSize = fieldNames.size();
	  //int fvSize = fieldValues.size();
	  //if(fnSize != fvSize)
	  //throw new MessagingException("The number of field names and field values does not match");
		 Enumeration kEnum = row.keys();
		 Object field = null;
		 String fieldName = null;
		 Field newField = null;
		 Object column = null;
		 Object value = null;
		 boolean isNewField = false;
		 int index = 0;
		 while(kEnum.hasMoreElements()) {
			fieldName = (String)kEnum.nextElement();
			field = fields.get(fieldName);
			value = row.get(fieldName);
			if(field == null) {
			   if(saveAsBaseTypes) {
				  field = new Field();
				  ((Field)field).setIndex(0);
				  isNewField = true;
			   } 
			   else
				  field = new Vector();
			}
		 
			if(field instanceof Vector) {
			   if(saveAsBaseTypes)
				  throw new MessagingException("Cannot save as a base type to the field " + fieldName);
			   ((Vector)field).addElement(value);			
			}
			else {
			   if(!saveAsBaseTypes)
				  throw new MessagingException("Cannot save an object to the field " + fieldName);
			   newField = (Field)field;
			   if(newField.getOriginalType() == Integer.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Integer.TYPE);
					 newField.setColumn(new int[ARRAYMAX]);
				  }
				  addInt(fieldName,((Integer)value).intValue());		
			   }
			   else if(newField.getOriginalType() == Long.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Long.TYPE);
					 newField.setColumn(new long[ARRAYMAX]);
				  }
				  addLong(fieldName,((Long)value).longValue());				
			   }
			   else if(newField.getOriginalType() == Short.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Short.TYPE);
					 newField.setColumn(new short[ARRAYMAX]);
				  }
				  addShort(fieldName,((Short)value).shortValue());	
			   }
			   else if(newField.getOriginalType() == Byte.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Byte.TYPE);
					 newField.setColumn(new byte[ARRAYMAX]);
				  }
				  addByte(fieldName,((Byte)value).byteValue());			
			   }
			   else if(newField.getOriginalType() == Float.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Float.TYPE);
					 newField.setColumn(new float[ARRAYMAX]);
				  }
				  addFloat(fieldName,((Float)value).floatValue());			
			   }
			   else if(newField.getOriginalType() == Double.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Double.TYPE);
					 newField.setColumn(new double[ARRAYMAX]);
				  }
				  addDouble(fieldName,((Double)value).doubleValue());			
			   }
			   else if(newField.getOriginalType() == Boolean.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Boolean.TYPE);
					 newField.setColumn(new boolean[ARRAYMAX]);
				  }
				  addBoolean(fieldName,((Boolean)value).booleanValue());				
			   }
			   else if(newField.getOriginalType() == Character.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Character.TYPE);
					 newField.setColumn(new char[ARRAYMAX]);
				  }
				  addChar(fieldName,((Character)value).charValue());			
			   }
			   else
				  throw new MessagingException("Type cannot be converted to base type " + value.getClass());	
			}
		 }	
	  }
   /**
 * 	setRow method.
 * @param fieldNames
 * @param fieldValues
 * @param saveAsBaseTypes
 * @throws MessagingException
 * @modelguid {F369D995-199F-4163-9035-426E75DCAE1E}
 */
	  public void setRow(Vector fieldNames, Vector fieldValues, boolean saveAsBaseTypes) throws MessagingException {
		 int fnSize = fieldNames.size();
		 int fvSize = fieldValues.size();
		 if(fnSize != fvSize)
			throw new MessagingException("The number of field names and field values does not match");
		 Object field = null;
		 String fieldName = null;
		 Field newField = null;
		 Object column = null;
		 Object value = null;
		 boolean isNewField = false;
		 int index = 0;
		 for(int i = 0;i<fnSize;i++) {
			fieldName = (String)fieldNames.elementAt(i);
			field = fields.get(fieldName);
			value = fieldValues.elementAt(i);
			if(field == null) {
			   if(saveAsBaseTypes) {
				  field = new Field();
				  ((Field)field).setIndex(0);
				  isNewField = true;
			   } 
			   else
				  field = new Vector();
			}
		 
			if(field instanceof Vector) {
			   if(saveAsBaseTypes)
				  throw new MessagingException("Cannot save as a base type to the field " + fieldName);
			   ((Vector)field).addElement(value);			
			}
			else {
			   if(!saveAsBaseTypes)
				  throw new MessagingException("Cannot save an object to the field " + fieldName);
			   newField = (Field)field;
			   if(newField.getOriginalType() == Integer.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Integer.TYPE);
					 newField.setColumn(new int[ARRAYMAX]);
				  }
				  addInt(fieldName,((Integer)value).intValue());		
			   }
			   else if(newField.getOriginalType() == Long.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Long.TYPE);
					 newField.setColumn(new long[ARRAYMAX]);
				  }
				  addLong(fieldName,((Long)value).longValue());				
			   }
			   else if(newField.getOriginalType() == Short.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Short.TYPE);
					 newField.setColumn(new short[ARRAYMAX]);
				  }
				  addShort(fieldName,((Short)value).shortValue());	
			   }
			   else if(newField.getOriginalType() == Byte.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Byte.TYPE);
					 newField.setColumn(new byte[ARRAYMAX]);
				  }
				  addByte(fieldName,((Byte)value).byteValue());			
			   }
			   else if(newField.getOriginalType() == Float.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Float.TYPE);
					 newField.setColumn(new float[ARRAYMAX]);
				  }
				  addFloat(fieldName,((Float)value).floatValue());			
			   }
			   else if(newField.getOriginalType() == Double.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Double.TYPE);
					 newField.setColumn(new double[ARRAYMAX]);
				  }
				  addDouble(fieldName,((Double)value).doubleValue());			
			   }
			   else if(newField.getOriginalType() == Boolean.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Boolean.TYPE);
					 newField.setColumn(new boolean[ARRAYMAX]);
				  }
				  addBoolean(fieldName,((Boolean)value).booleanValue());				
			   }
			   else if(newField.getOriginalType() == Character.TYPE) {
				  if(isNewField) {
					 newField.setOriginalType(Character.TYPE);
					 newField.setColumn(new char[ARRAYMAX]);
				  }
				  addChar(fieldName,((Character)value).charValue());			
			   }
			   else
				  throw new MessagingException("Type cannot be converted to base type " + value.getClass());	
			}
		 }	
	  }
   /**
 * 	 toString method for this class.
 * 	 Creation date: (6/1/00 3:15:28 PM)
 * 	 @return <{String}>
 * @modelguid {02A2BBB0-38DF-486E-86E7-FFB937FD1718}
 */
	  public String toString() {
		 Vector fieldNames = getFieldNames();
		 int size = fieldNames.size();
		 int isize = 0;
		 String rString = "";
		 String fName = null;
		 Object oField, col = null;
		 Vector vField = null;
		 Field fField = null;
		 Class oType = null;
		 for(int i = 0;i<size;i++) {
			fName = (String)fieldNames.elementAt(i);
			rString += "Field Name = " + fName + '\n';
			oField = fields.get(fName);
			if(oField instanceof Vector) {
			   vField = (Vector)oField;
			   isize = vField.size();
			   for(int j = 0;j<isize;j++) 
				  rString += " Value " + j + " = " + vField.elementAt(j).toString() + '\n';
			}
			else {
			   fField = (Field)oField;
			   isize = fField.getIndex();
			   col = fField.getColumn();
			   oType = fField.getOriginalType();
			   for(int k = 0;k<isize;k++) {
				  if(oType == Integer.TYPE) 
					 rString += " Value " + k + " = " + Array.getInt(col, k) + '\n';
				  else if(oType == Long.TYPE) 
					 rString += " Value " + k + " = " + Array.getLong(col, k) + '\n';
				  else if(oType == Short.TYPE) 
					 rString += " Value " + k + " = " + Array.getShort(col, k) + '\n';
				  else if(oType == Byte.TYPE) 
					 rString += " Value " + k + " = " + Array.getByte(col, k) + '\n';
				  else if(oType == Float.TYPE) 
					 rString += " Value " + k + " = " + Array.getFloat(col, k) + '\n';
				  else if(oType == Double.TYPE) 
					 rString += " Value " + k + " = " + Array.getDouble(col, k) + '\n';
				  else if(oType == Boolean.TYPE) 
					 rString += " Value " + k + " = " + Array.getBoolean(col, k) + '\n';
				  else if(oType == Character.TYPE) 
					 rString += " Value " + k + " = " + Array.getChar(col, k) + '\n';
			   }
			}
		 
		 }	
		 return rString;
	  }
   }