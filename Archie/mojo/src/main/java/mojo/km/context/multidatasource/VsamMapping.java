/*
 * Created on Jan 19, 2006
 *
 */
package mojo.km.context.multidatasource;

import java.io.IOException;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import com.ibm.ctg.client.ECIRequest;
import com.ibm.ctg.client.JavaGateway;

import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.LegacyDataFormatUtil;
import mojo.km.utilities.Reflection;

/**
 * @author Rcooper
 *  
 */
public class VsamMapping implements IMapping
{
    private static final int COMMAREA_SIZE = 24478;

    private static final byte CONTINUATION_BYTE = (byte) 'Y';

    private static final int CONTINUATION_INDEX = 18;

    private static final String INSERT_FUNCTION = "A";
	private static final String PROGRAM_NAME = "J2CICS01";
	//private static final String PROGRAM_NAME = "J2CICS04";
	private static final int PARM_AREA_SIZE = 500;
	private static final String BLANK = "";

    private static final String READ_FUNCTION = "R";

    private static final char SPACE_CHAR = ' ';
    private static final String SPACE_STRING = " ";

    private static final String UPDATE_FUNCTION = "C";

    private CallbackProperties callBack = null;

    private String connectionName;

    private EventQueryProperties eventProps = null;

    private final char STATUS_GO_CHAR = 'G';

    private final char STATUS_INFO_CHAR = 'I';
    
    //private final int STATUS_RETRIEVE_INDEX = 166;
    private final int STATUS_RETRIEVE_INDEX = 512;

    private final int STATUS_UPDATE_INDEX = 4816;

    public VsamMapping()
    {

    }

    public VsamMapping (String aConnectionName)
    {
        connectionName = aConnectionName;
    }

    private List callCTG(StringBuffer params, IConnection connection) throws Exception
    {
        long starttime = System.currentTimeMillis();

        List rSetArray = this.executeTransaction(connection, params, COMMAREA_SIZE, PROGRAM_NAME);

        long endtime = System.currentTimeMillis();

        double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

        System.err.println("save performance CICS " + elapsedtime + " seconds.\n");

        return rSetArray;
    }

    /*
     * Method to insert Case Master records into JSCASE. PLEASE DO NOT CHANGE ANY OF THIS CODE
     * WITHOUT FIRST TALKING TO M. PATINO!
     */
    public void doInsert(PersistentObject pObj, IConnection connection)
    {
        try
        {
            EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(pObj.getClass().getName());
            if (eProps != null)
            {  
                SaveCallbackProperties saveProps = (SaveCallbackProperties) getCallback();

                String spName = saveProps.getStoredProcedureName();
                //System.out.println("Stored proc name = " + spName);
                String cpName = saveProps.getCicsProgramName();
                String tranId = saveProps.getTranId();
                String file = saveProps.getFileName();

                String moreRecords = " ";

                // Source is record type + continuation. Value comes from the name
                // of the record type PF in Reqpro.
             //   String rty = saveProps.getSource();
   //             String continuation = rty.substring(2);
  //              rty = rty.substring(0, 2);

                Iterator propFields = saveProps.getFieldsIterator();
                String cdi = new String();
                String caseNum = new String();

                StringBuffer keySB = new StringBuffer();

                //String oid = (String) pObj.getOID();

                //Find fields that make up the key by looking at getter methods
                while (propFields.hasNext())
                {
                    FieldMappingProperties fieldProps = (FieldMappingProperties) propFields.next();

                    String propName = fieldProps.getPropertyName();
                    //System.out.println("Field name = " + fieldProps.getName());
                    char firstChar = propName.charAt(0);
                    firstChar = Character.toUpperCase(firstChar);
                    // Chop off first char
                    propName = propName.substring(1, propName.length());
                    propName = firstChar + propName;
                    String methodName = "get" + propName;
                    Class[] classes = {};

                    Method fieldMethod = null;
                    try
                    {
                        fieldMethod = pObj.getClass().getMethod(methodName, classes);

                    }
                    catch (SecurityException e)
                    {
                        ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage()));
                        throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());

                    }
                    catch (NoSuchMethodException e)
                    {
                        ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage()));
                        throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());
                    }

                    Object[] args = {};

                    Object getObj = null;

                   if (fieldMethod != null)
                    {
                     try
                        {
//                            if (fieldMethod.getName().equals("getCourtDivision"))
//                            {
//                                getObj = fieldMethod.invoke(pObj, args);
//                                cdi = (String) getObj;
//
//                            }
//                            if (fieldMethod.getName().equals("getCaseNumber"))
//                            {
//                                getObj = fieldMethod.invoke(pObj, args);
//                                caseNum = (String) getObj;
//
//                            }
                        }

                        catch (Exception e1)
                        {
                            ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e1.getMessage()));
                            throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e1.getMessage());
                        }

                        //check methodname to be get case or get cdi and then use values to set
                        // oid.
                    }
                }

                //Set key for card image.
             //   keySB = keySB.append(cdi).append(caseNum).append(rty).append("      0").append(continuation);

             //   String cmKey = keySB.toString();

                StringBuffer record = new StringBuffer();

              //  setControlFields(record, cmKey, INSERT_FUNCTION);
       //         record.append(INSERT_FUNCTION);
               
         //       record.append(" ");
         //       rty = rty.substring(0, 2);

                Map fieldMappings = new HashMap();
                Iterator i = saveProps.getFieldsIterator();
                String fPos = new String();

                //Put field mappings in HashMap by card image position, so fields
                //can be pulled out in order to format string for COMMHCME
              int posSave = 0 ;
                while (i.hasNext())
                {
                    FieldMappingProperties fmp = (FieldMappingProperties) i.next();
                    //fPos = fmp.getCardPosition();
                    fPos = fmp.getPosition();
                    fieldMappings.put(fPos, fmp);
                    int posCompare = new Integer(fPos).intValue();
                    if (posCompare> posSave){
                    posSave = new Integer(fPos).intValue();
                }
               }

                int mapSize = fieldMappings.size();
                String pos = new String();

                // just a placeholder, not actually being used in formatField method
                String oid = new String();

                //Pull fields out in order, format each one and create the card image string.

                for (int k = 1; k <= posSave; k++)
                {
                    pos = String.valueOf(k);

                    FieldMappingProperties fProps = (FieldMappingProperties) fieldMappings.get(pos);

                    if (fProps != null)
                    {
                        formatField(record, INSERT_FUNCTION, fProps, pObj);
                    }
                }

                StringBuffer buffer = new StringBuffer();
                buffer.append(cpName);
                buffer.append(tranId);
 //               buffer.append(file);
//                buffer.append(INSERT_FUNCTION);
//                buffer.append("000");
//                buffer.append(moreRecords);
                buffer.append(record);

                //IMPORTANT!! Commarea length passed to CTG must match exactly
                //what is in all CICS transactions executed in the chain. Errors
                //in commarea length can cause catastrophic CICS errors.
                List rSetArray = new ArrayList();
                if (spName.equals("none"))
                {
                    rSetArray = callCTG(buffer, connection);
                }
                else
                {
                    //stored procedure call would go here
                    throw new VSAMException("VSAM-CM: Stored Procedure call not available");
                }

                int size = rSetArray.size();
                for (int y = 0; y < size; y++)
                {
                    String rSet = (String) rSetArray.get(y);

                    char status = rSet.charAt(STATUS_RETRIEVE_INDEX);
  
                    System.out.println("CICS Return Status:" + status);
                    /*if (status.equals("I"))
                    {
                        String msg = "VSAM INSERT INFO FROM CICS:" + rSet.substring(4817, 4897);
                        this.sendInfoMessage(msg);
                    }
                    else if (!status.equals("G"))
                    {
                        String msg = "VSAM INSERT ERROR:BAD RETURN CODE FROM CICS" + rSet.substring(4817, 4897);
                        throw new VSAMException(msg);

                    }*/
                    if (status == STATUS_INFO_CHAR)
                    {
                        String msg = "VSAM INSERT INFO FROM CICS:";
                        this.sendInfoMessage(msg);
                    }
                    else if (status != STATUS_GO_CHAR)
                    {
                        String msg = "VSAM INSERT ERROR:BAD RETURN CODE FROM CICS:" + rSet.substring(STATUS_RETRIEVE_INDEX,560);
                        throw new VSAMException(msg);
                    }                    

                    //TODO - format OID and set on pObj - not getting SNU out of data returned
                    // from COMMHCMM.
                    //String data = rSet.substring(16);
                    //oid = data.substring(2, 23);
                    //System.out.println("New OID = " + oid);
                   // pObj.setOID(oid);
                    //
                    String recLenStr = null;
                    int recLen = 0;
                     
                     recLenStr = rSet.substring(593, 596);
                        try {
                            recLen = Integer.parseInt(recLenStr);
                        } catch (RuntimeException e1) {
                            recLen = 0;
                        }
                    //
                    String data = rSet.substring(668);                    
                    String dataTrim = data.trim();
                    boolean more = true;
                    int recCount = 0;
                    
                    if (dataTrim.equals("") || dataTrim.equals("@@@") || data.substring(2).trim().equals("@@@"))
                    {
                        more = false;
                    }
                                     
                        Iterator k =  saveProps.getFieldsIterator();
                   while (k.hasNext())
                        {
                	       
                            FieldMappingProperties fProps = (FieldMappingProperties) k.next();
                            setPersistentObject(pObj, data, fProps);
                        }
                        pObj.setNotNew();

                         

                        data = data.substring(recLen);
                        dataTrim = data.trim();
                    //    System.out.println("data:" + dataTrim);
                                            
                }
            }
           }

        catch (Exception e)
        {
            try
            {
                //jdbcConnection.rollback();
                //can we rollback transaction????
                ExceptionHandler.executeCallbacks(e);
                ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage()));
                throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());

            }
            catch (Exception sE)
            {
                ExceptionHandler.executeCallbacks(sE);
                ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + sE.getMessage()));
                throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + sE.getMessage());

            }

        }

        TransactionManager.releaseConnection(connectionName, connection);
    }

    /*
     * Update case master records in JSCASE. PLEASE DO NOT CHANGE ANY OF THIS CODE WITHOUT FIRST
     * TALKING TO M. PATINO!
     */
    //TODO Currently only have ability to do one update at a time. Talk to Eric about
    //doing multiples in one call....
    public void doUpdate(PersistentObject pObj, IConnection connection)
    {
        try
        {
            EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(pObj.getClass().getName());
            if (eProps != null)
            {
                SaveCallbackProperties saveProps = (SaveCallbackProperties) getCallback();
                String spName = saveProps.getStoredProcedureName();
                String cpName = saveProps.getCicsProgramName();
                String tranId = saveProps.getTranId();
   //             String file = saveProps.getFileName();

    //            String moreRecords = " ";

                StringBuffer record = new StringBuffer();

                 
   //             String cmKey = new String();

 //               setUpdateControlFields(record, cmKey, UPDATE_FUNCTION);

                HashMap parmMappings = new HashMap();
                Iterator x = saveProps.getParmsIterator();
                String pIdx = new String();
                HashMap fieldMappings = new HashMap();
                Iterator i = saveProps.getFieldsIterator();
                String fPos = new String();

                //Put field mappings in HashMap by card image position, so fields
                //can be pulled out in order to format string for COMMHCME
                int posSave = 0 ;
                while (i.hasNext())
                {
                    FieldMappingProperties fmp = (FieldMappingProperties) i.next();
                   // fPos = fmp.getCardPosition();
                    fPos = fmp.getPosition();
                    fieldMappings.put(fPos, fmp);
                    int posCompare = new Integer(fPos).intValue();
                    if (posCompare> posSave){
                    posSave = new Integer(fPos).intValue();
                }
                }

                int mapSize = fieldMappings.size();
                String pos = new String();

                String saveStartCol = new String();
                String saveLength = new String();
                String startCol = new String();
                String length = new String();
                String cardPos = new String();

                String oid = (String) pObj.getOID();

                //Pull fields out in order, format each one and create a string with all of them.

                for (int k = 1; k <= posSave; k++)
                {
                    Integer lInt = new Integer(k);
                    pos = lInt.toString();

                    FieldMappingProperties fProps = (FieldMappingProperties) fieldMappings.get(pos);

                    if (fProps != null)
                    {
                        formatField(record, UPDATE_FUNCTION,  fProps, pObj);
                    }
                }

                StringBuffer buffer = new StringBuffer();
                buffer.append(cpName);
                buffer.append(tranId);
 //               buffer.append(file);
                buffer.append(UPDATE_FUNCTION);
 //               buffer.append(moreRecords);
                buffer.append(record);

                //IMPORTANT!! Commarea length passed to CTG must match exactly
                //what is in all CICS transactions executed in the chain. Errors
                //in commarea length can cause catastrophic CICS errors.
                List rSetArray = new ArrayList();
                if (spName.equals("none"))
                {
                    rSetArray = callCTG(buffer, connection);
                }
                else
                {
                    //stored procedure call would go here
                    throw new VSAMException("VSAM-CM: Stored Procedure call not available");
                }

                int size = rSetArray.size();
                for (int y = 0; y < size; y++)
                {
                    String rSet = (String) rSetArray.get(y);

                    char statusChar = rSet.charAt(STATUS_UPDATE_INDEX);

                    if (statusChar == STATUS_INFO_CHAR)
                    {
                        String msg = "VSAM UPDATE INFO FROM CICS:" + rSet.substring(4817, 4897);
                        this.sendInfoMessage(msg);
                    }
                    else if (statusChar != STATUS_GO_CHAR)
                    {
                        throw new VSAMException("VSAM CM UPDATE ERROR:BAD RETURN CODE FROM CICS" + rSet.substring(4817, 4897));
                    }
                   // String data = rSet.substring(16);
                    
                
                String recLenStr = null;
                int recLen = 0;
                 
                 recLenStr = rSet.substring(593, 596);
                    try {
                        recLen = Integer.parseInt(recLenStr);
                    } catch (RuntimeException e1) {
                        recLen = 0;
                    }
                //
                String data = rSet.substring(668);                    
                String dataTrim = data.trim();
                boolean more = true;
                int recCount = 0;
                
                if (dataTrim.equals("") || dataTrim.equals("@@@") || data.substring(2).trim().equals("@@@"))
                {
                    more = false;
                }
                                 
                    Iterator k =  saveProps.getFieldsIterator();
               while (k.hasNext())
                    {
            	       
                        FieldMappingProperties fProps = (FieldMappingProperties) k.next();
                        setPersistentObject(pObj, data, fProps);
                    }
                    pObj.setNotNew();
                    data = data.substring(recLen);
                    dataTrim = data.trim();
             //       System.out.println("data:" + dataTrim);
                                 
            }
        }
       }
        catch (Exception e)
        {
        	
            ExceptionHandler.executeCallbacks(e);
            Exception e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());
            ExceptionHandler.executeCallbacks(e2);
            throw new VSAMException("VSAM-Update-Exception:" + e2);
        }
        finally
        {
            TransactionManager.releaseConnection(connectionName, connection);
        }
    }

    private List executeTransaction(IConnection connection, StringBuffer inputBuffer, int commAreaSize, String programName)
            throws Exception
    {
        //Code up to sending ECIRequest can be put in VSAMAdapter init??
        List records = new ArrayList();
        ECIRequest eciRequestObject = null;

        JavaGateway gateway = (JavaGateway) connection.getResource();

        // pad remaining bytes in commarea with spaces
        int size = commAreaSize - inputBuffer.length();
        char[] padding = new char[size];
        Arrays.fill(padding, SPACE_CHAR);
        inputBuffer.append(padding);
        String commareaString = inputBuffer.toString();

        System.out.println("CTG Input String : " + commareaString.trim());
        System.out.println("CTG Input length : " + commareaString.length());

        byte[] commarea = commareaString.getBytes();

        String region = connection.getRegion();

        eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                region, //CICS server
                null, //CICS userid
                null, //CICS password
                programName, //CICS program to be run
                null, //CICS transid to be run
                commarea, //Byte array containing the COMMAREA
                commAreaSize, //COMMAREA length
                ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                ECIRequest.ECI_LUW_NEW); //ECI LUW token

        boolean moreRecords = true;
        boolean success = true;
        String result;
        while (moreRecords == true && success == true)
        {
        	try{
        		
        	
            success = flowRequest(gateway, eciRequestObject);
        	 } catch (Exception e1) {
        		 Exception e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping:CICS TRANSACTION GATEWAY TRANSACTION ABEND:" + e1.getMessage());
                 ExceptionHandler.executeCallbacks(e2);
                 throw new VSAMException("VSAM-Exception ExecuteTransaction:" + e2);
        		 
        	 }
            if (success == true)
            {
                result = new String(commarea);
                records.add(result);
                System.out.println("CTG result = " + result.replace("@@@","").trim());

                byte contChar = commarea[CONTINUATION_INDEX];

                moreRecords = (contChar == CONTINUATION_BYTE);
            }
        }

        return records;
    }
    
    private boolean flowRequest(JavaGateway aGateway, ECIRequest requestObject) throws Exception
    {
        boolean success = true;
        if(aGateway == null){
        	
        	Exception e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping:CICS TRANSACTION GATEWAY TRANSACTION ABEND:Gateway is null");
            ExceptionHandler.executeCallbacks(e2);
        	success = false;
        }else {
        int returnCode = aGateway.flow(requestObject);

        switch (requestObject.getCicsRc())
        {
        case ECIRequest.ECI_NO_ERROR:
            success = true;
            break;

        case ECIRequest.ECI_ERR_TRANSACTION_ABEND:
            success = false;
            System.err.println("ECI returned: " + requestObject.getCicsRcString());
            System.err.println("Abend code: " + requestObject.Abend_Code);
            Exception e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping:CICS TRANSACTION GATEWAY TRANSACTION ABEND:" + "ECI returned: " + requestObject.getCicsRcString());
            ExceptionHandler.executeCallbacks(e2);
            throw e2;
            
            case ECIRequest.ECI_ERR_SECURITY_ERROR:
            success = false;
            System.err.println("\nNot authorized to run transaction.");
            System.err.println("ECI returned: " + requestObject.getCicsRcString());
            System.err.println("Abend code: " + requestObject.Abend_Code);
            e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping:CICS TRANSACTION GATEWAY SECURITY ERROR:" + "ECI returned: " + requestObject.getCicsRcString());
            ExceptionHandler.executeCallbacks(e2);
            throw e2;
         

        default:
            success = false;
            System.err.println("ECI returned: " + requestObject.getCicsRcString());
            System.err.println("Abend code: " + requestObject.Abend_Code);
            e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping:CICS TRANSACTION GATEWAY Error:" + "ECI returned: " + requestObject.getCicsRcString());
            ExceptionHandler.executeCallbacks(e2);
            throw e2;
        }
        }
        return success;
    }

    //	  PLEASE DO NOT CHANGE ANY OF THIS CODE WITHOUT FIRST TALKING TO M. PATINO!
    private void formatField(StringBuffer record, String function, FieldMappingProperties fProps,
            PersistentObject pObj)
    {
        String appendFiller = fProps.getAppendFiller();

        String format = fProps.getFormat();
        String temp = " ";
        
        if (fProps.getPropertyType().equals("String") || fProps.getPropertyType().equals("java.lang.String"))
        {
            temp = (String) Reflection.invokeAccessorMethod(pObj, fProps.getPropertyName());
            System.out.println("fprops = " + fProps.getPropertyName() + " value = " + temp);
           if ((temp != null) && (format != null) && (!format.equals("none")))
            {
                temp = LegacyDataFormatUtil.formatString(temp, format, fProps.getPadChar(), fProps.getLength());
            }
        }
        else
        {
            if (fProps.getPropertyType().equals("Date") || fProps.getPropertyType().equals("java.util.Date"))
            {
                Date date = (Date) Reflection.invokeAccessorMethod(pObj, fProps.getPropertyName());
                //reformat Date to appropriate String format for vsam record
                if (date != null)
                {
                   // temp = LegacyDataFormatUtil.formatDate(date, format);
                    temp = DateUtil.dateToString(date, format);
                    System.out.println("fprops = " + fProps.getPropertyName() +" date value = " + temp);
                    if (temp == null)
                    {
                        throw new VSAMException("VsamMapping: Invalid format requested for date field "
                                + fProps.getPropertyName());
                    }
                }
            }
            else
            {
                temp = (String) Reflection.invokeAccessorMethod(pObj, fProps.getPropertyName().toString());
                if ((temp != null) && (format != null) && (!format.equals("none")))
                {
                    temp = LegacyDataFormatUtil.formatString(temp, format, fProps.getPadChar(), fProps.getLength());
                }

            }
        }

        //fieldLen is how long data field should be
        String fieldLen = fProps.getLength();
        //System.out.println(" Max Field length = " + fieldLen);

        //tempLen is actual length of data in pObj
        int tempLen = 0;
        if (temp == null)
        {
            temp = " ";
        }
        tempLen = temp.length();
        //System.out.println(" Data length = " + tempLen);

        /*
         * If no formatting was defined for field, or a field is not present, it is still necessary
         * to make sure that it fills out the correct number of spaces in the card image, so pad
         * with trailing spaces if needed.
         */

        //lenInt is how long the field should be
        int lenInt = Integer.parseInt(fieldLen);
        int diff = 0;
        if (tempLen < lenInt)
        {
            diff = lenInt - tempLen;
            //append 'diff' number of blanks onto temp
            temp = LegacyDataFormatUtil.padField(temp, " ", diff);
            tempLen = temp.length();

        }
        //appendFiller is for blank areas in card images.
        int fillLen = Integer.parseInt(appendFiller);
        if (fillLen > 0)
        {
            temp = LegacyDataFormatUtil.padField(temp, " ", fillLen);
        }

        temp = temp.toUpperCase();
        record.append(temp);
        //System.out.println("Record length is now = " + record.length());
        //After all fields on base card have been added, set up
        //control fields for continuation card
//        if (record.length() == 80) {
//			if (function.equals(INSERT_FUNCTION)) {
//				setControlFields(record, cmKey, INSERT_FUNCTION);
//			} else if (function.equals(UPDATE_FUNCTION) || function.equals("D")) {
//				setUpdateControlFields(record, oid, function);
//			}
//
//		}

    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getCallback()
	 */
    public CallbackProperties getCallback()
    {
        return this.callBack;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getConnectionName()
     */
    public String getConnectionName()
    {

        return connectionName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getDeleteStatement()
     */
    public IStatement getDeleteStatement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getEntityMap()
     */
    public EntityMappingProperties getEntityMap()
    {

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getInsertStatement()
     */
    public IStatement getInsertStatement()
    {

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getQueryStatement()
     */
    public IStatement getQueryStatement()
    {

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getUpdateStatement()
     */
    public IStatement getUpdateStatement()
    {

        return null;
    }

    public void init()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#init(java.lang.String)
     */
    public void init(String key)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent,
     *      java.lang.Class, java.util.Map)
     */
    public List retrieve(IEvent anEvent, Class pType, Map retVal)
    {
        IConnection connection = TransactionManager.getInstance().getConnection(connectionName);
        if (retVal == null){
        	retVal = new HashMap();
        }
        EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(pType.getName());
        try
        {
            if (eProps != null)
            {
                eventProps = (EventQueryProperties) callBack;
                //TODO Need to set up to call CTG or stored procedure depending on
                //presence of spName....
                String spName = eventProps.getStoredProcedureName();
                //System.out.println("Stored proc name = " + spName);
                String cpName = eventProps.getCicsProgramName();
                String tranId = eventProps.getTranId();

                String moreRecords = " ";
                Map parmMappings = new HashMap();
                String pIndex = new String();
                Iterator parms = eventProps.getParmsIterator();

                while (parms.hasNext())
                {
                    ParmMappingProperties pmp = (ParmMappingProperties) parms.next();
                    pIndex = pmp.getParmIndex();
                    parmMappings.put(pIndex, pmp);

                }
                
                StringBuffer query = new StringBuffer();
                int mapSize = parmMappings.size();
                String parmIndex = new String();

                //Pull parms out in order and create a string with all of them.
                for (int i = 1; i <= mapSize; i++)
                {
                    parmIndex = String.valueOf(i);

                    ParmMappingProperties parmProps = (ParmMappingProperties) parmMappings.get(parmIndex);
                    String temp = "";
                    if (parmProps.getPropertyType().equals("String") || parmProps.getPropertyType().equals("java.lang.String"))
                    {
                        temp = (String) Reflection.invokeAccessorMethod(anEvent, parmProps.getPropertyName());
                    }
                    else
                    {
                        temp = (String) Reflection.invokeAccessorMethod(anEvent, parmProps.getPropertyName().toString());
                    }
                    if (parmProps != null)
                    {
                        String format = parmProps.getFormat();
                        temp = LegacyDataFormatUtil.formatString(temp, format, parmProps.getPadChar(), parmProps.getLength());
                    }
                    if (temp != null)
                    {
                        query.append(temp);
                    }

                }
                if (query.length() < PARM_AREA_SIZE){
                    while (query.length() < PARM_AREA_SIZE){
                        query.append(SPACE_STRING);
                    }
                } else if (query.length() > PARM_AREA_SIZE){
                    query = new StringBuffer(query.substring(0,PARM_AREA_SIZE));
                }
                
                String queryString = query.toString();
                List rSetArray = new ArrayList();

                //After this point logic varies for CTG vs SP - create methods that can
                //be used in retrieve, insert and update.

                if (spName.equals("none"))
                {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(cpName);
                    buffer.append(tranId);
                    buffer.append(queryString);

                    //IMPORTANT!!

                    // Commarea length passed to CTG must match exactly what is in
                    // all CICS transactions executed in the chain.

                    // Errors in commarea length can cause catastrophic CICS errors!

                    // ExecCTG class will pad commarea with spaces if needed.

                    long starttime = System.currentTimeMillis();

                    rSetArray = this.executeTransaction(connection, buffer, COMMAREA_SIZE, PROGRAM_NAME);

                    long endtime = System.currentTimeMillis();

                    double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

                    System.err.println("retrieve performance CICS " + elapsedtime + " seconds.\n");
                }
                String recLenStr = null;
                int recLen = 0;
                int size = rSetArray.size();
                System.out.println("CICS result size=" + size);

                for (int i = 0; i < size; i++)
                {
                    StringBuffer rSet = new StringBuffer((String) rSetArray.get(i));

                    recLenStr = rSet.substring(593, 596);
                    try {
                        recLen = Integer.parseInt(recLenStr);
                    } catch (RuntimeException e1) {
                        recLen = 0;
                    }

                    char statusChar = rSet.charAt(STATUS_RETRIEVE_INDEX);

                    if (statusChar == STATUS_INFO_CHAR)
                    {
                        String msg = "VSAM READ INFO FROM CICS:" + rSet.substring(513, 592);
                        this.sendInfoMessage(msg);
                    }
                    else if (statusChar != STATUS_GO_CHAR)
                    {
                        String msg = "VSAM READ INFO FROM CICS:" + rSet.substring(513, 592);
                        throw new VSAMException(msg);
                    }                    
                    
                    String data = rSet.substring(668);                    
                    String dataTrim = data.trim();
                    boolean more = true;
                    int recCount = 0;
                    
                    if (dataTrim.equals("") || dataTrim.equals("@@@") || data.substring(2).trim().equals("@@@"))
                    {
                        more = false;
                    }
                    
                    while (more == true)
                    {
                        recCount++;
                        Iterator k = eventProps.getFieldsIterator();

                        Class pClass = Class.forName(eProps.getEntity());
                        PersistentObject pObj = (PersistentObject) pClass.newInstance();
                        while (k.hasNext())
                        {
                            FieldMappingProperties fProps = (FieldMappingProperties) k.next();
                            setPersistentObject(pObj, data, fProps);
                        }
                        pObj.setNotNew();

                        retVal.put(pObj.getOID(), pObj);

                        data = data.substring(recLen);
                        dataTrim = data.trim();
                        
                        if (dataTrim.equals("@@@") || dataTrim.trim().length() < 3 || dataTrim.substring(2).trim().equals("@@@"))
                        {
                            more = false;
                        }
                    }
                }
            }
        }
        catch (VSAMException e)
        {
            ExceptionHandler.executeCallbacks(e);
            throw e;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            ExceptionHandler.executeCallbacks(e);
            VSAMException e2 = new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());
            ExceptionHandler.executeCallbacks(e2);
            throw e2;
        }
        finally
        {
            TransactionManager.releaseConnection(connectionName, connection);
        }
        ArrayList results = new ArrayList();
        if (retVal != null)
		{
			results = new ArrayList(retVal.size());
			results.addAll(retVal.values());
		}
       // return retVal;
        return results;
    }

	/* (non-Javadoc)
	 * @see mojo.km.context.multidatasource.IMapping#retrieveMeta(mojo.km.messaging.IEvent, java.lang.Class, java.util.Map)
	 */
	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Method to be registered as the save callback whenever a Persistent Object is to be saved.
     * 
     * @param pObj -
     *            the object to be saved.
     *  
     */
    public void save(PersistentObject pObj)
    {
        IConnection connection = TransactionManager.getInstance().getConnection(connectionName);

        if (pObj.isNew())
        {
            doInsert(pObj, connection);
        }
        else
        {
            doUpdate(pObj, connection);
        }
    }

    /**
     * @param msg
     */
    private void sendInfoMessage(String msg)
    {
        InfoMessageEvent infoEvent = new InfoMessageEvent();
        infoEvent.setMessage(msg);
        EventManager.getSharedInstance(EventManager.REPLY).postEvent(infoEvent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setCallback(mojo.km.config.CallbackProperties)
     */
    public void setCallback(CallbackProperties aCallBack)
    {
        this.callBack = aCallBack;

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setConnection(mojo.km.context.multidatasource.IConnection)
     */
    public void setConnection(IConnection aConnection)
    {
    }

    public void setConnectionName(String aConnectionName)
    {
        connectionName = aConnectionName;
    }

    //	  PLEASE DO NOT CHANGE ANY OF THIS CODE WITHOUT FIRST TALKING TO M. PATINO!
    private void setControlFields(StringBuffer record, String function)
    {
        if (record.length() < 80)
        {
            //C is for case master
            record.append("C");
            //record.append(cmKey.substring(0, 20));
            //record.append(cmKey);
            //System.out.println("Card Image Key = " + cmKey);
            //3 byte filler between SNU and CCN
            //record.append(" ");
            //record.append(oid.substring(20));

            //function - add, change, delete
            record.append(function);
            //continuation field
            record.append(" ");
        }
        else
        {
            //C is for case master

            record.append("C");
            //record.append(cmKey);
            record.append("                        ");
            record.append(function);
            //continuation field
            record.append("X");
        }

        //System.out.println("SetControlFields Record length is now " + record.length());

        //continuation = space for base card, X for continuation card
        //		if (record.length() < 80)
        //		{
        //			record.append(" ");
        //		}
        //		else
        //			record.append("X");

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setEntityMap(mojo.km.config.EntityMappingProperties)
     */
    public void setEntityMap(EntityMappingProperties entityMap)
    {

    }

    //Change IMapping interface to have this method??
    public void setParameter(CallableStatement statement, ParmMappingProperties pProps, Object anEvent) throws SQLException
    {
        String index = pProps.getPosition();
        String propertyType = pProps.getPropertyType();
        String propertyName = pProps.getPropertyName();

        int i = Integer.parseInt(index);

        if (propertyType.equals("String"))
        {
            statement.setString(i, (String) Reflection.invokeAccessorMethod(anEvent, propertyName));
        }
        else
        {
            statement.setString(i, Reflection.invokeAccessorMethod(anEvent, propertyName).toString());
        }
    }

    public void setParameter(PreparedStatement statement, ParmMappingProperties pProps, Object anEvent) throws SQLException
    {
        String index = pProps.getPosition();
        String propertyType = pProps.getPropertyType();
        String propertyName = pProps.getPropertyName();

        int i = Integer.parseInt(index);

        if (propertyType.equals("String"))
        {
            statement.setString(i, (String) Reflection.invokeAccessorMethod(anEvent, propertyName));

        }
        else
        {
            statement.setString(i, Reflection.invokeAccessorMethod(anEvent, propertyName).toString());
        }
    }

    public void setPersistentObject(PersistentObject pObj, ResultSet rSet, String propertyName,
                                    String propertyType, String index, String associationType, String fieldName)
    {
        int i = 1;
        try
        {
            i = Integer.parseInt(index);
        }
        catch (Exception e)
        {
            ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage()));
            throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e.getMessage());
        }

        try
        {
            if (propertyType.equals("String"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, rSet.getString(i));
            }
            else if (propertyType.equals("Date"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, rSet.getDate(i));
            }
            else if (propertyType.equals("Timestamp"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, rSet.getTimestamp(i));
            }
            else if (propertyType.equals("int"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Integer(rSet.getInt(i)));
            }
            else if (propertyType.equals("boolean"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Boolean(rSet.getBoolean(i)));
            }
            else if (propertyType.equals("long"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Long(rSet.getLong(i)));
            }
            else if (propertyType.equals("short"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Short(rSet.getShort(i)));
            }
            else if (propertyType.equals("double"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Double(rSet.getDouble(i)));
            }
            else if (propertyType.equals("float"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Float(rSet.getFloat(i)));
            }
            else if (propertyType.equals("byte"))
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, new Byte(rSet.getByte(i)));
            }
            else
            {
                Reflection.invokeMutatorMethod(pObj, propertyName, rSet.getString(i));
            }
        }
        catch (SQLException e2)
        {
            e2.printStackTrace();
            ExceptionHandler.executeCallbacks(new VSAMException("VSAM_EXCEPTION:VsamMapping " + e2.getMessage()));
            throw new VSAMException("VSAM_EXCEPTION:VsamMapping " + e2.getMessage());
        }
    }

    public void setPersistentObject(PersistentObject pObj, String data, FieldMappingProperties fProps)
    {
        String propertyName = fProps.getPropertyName();
        String propertyType = fProps.getPropertyType();
        String position = fProps.getPosition();
        String length = fProps.getLength();
        String format = fProps.getFormat(); 
        int pos = Integer.parseInt(position);
        if (pos > 0){
            pos--;
        }
        int len = Integer.parseInt(length);
        len = len + pos;

        if (data != null && propertyType.equals("String") || propertyType.equals("java.lang.String"))
        {
        	System.out.println("Setting property:" + propertyName + " with " + data.substring(pos, len));
            Reflection.invokeMutatorMethod(pObj, propertyName, data.substring(pos, len));
        }
        if (data != null && propertyType.equals("Date") || propertyType.equals("java.util.Date"))
        {
        try
        {
            Date date = DateUtil.stringToDate(data.substring(pos, len), format);
            Reflection.invokeMutatorMethod(pObj, propertyName, date);
            System.out.println("Setting property:" + propertyName + " with " + data.substring(pos, len));
        }
        catch (ParseRuntimeException p)
        {
            InfoMessageEvent infoEvent = new InfoMessageEvent();
            StringBuffer buffer = new StringBuffer(50);
            buffer.append("VSAM invalid date format (");
            buffer.append(pObj.getClass().getName());
            buffer.append("): ");
            buffer.append(fProps);
            String msg = buffer.toString();

            LogUtil.log(Level.WARN, msg);

            infoEvent.setMessage(msg);
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(infoEvent);
        }   
        }
    }

    //		PLEASE DO NOT CHANGE ANY OF THIS CODE WITHOUT FIRST TALKING TO M. PATINO!
    private void setUpdateControlFields(StringBuffer record, String oid, String function)
    {
        //C is for case master
        record.append("C");
        record.append(oid.substring(0, 20));

        //3 byte filler between SNU and CCN
        record.append("   ");
        record.append(oid.substring(20));

        //function - add, change, delete
        record.append(function);

        //continuation = space for base card, X for continuation card
        if (record.length() < 80)
        {
            record.append(" ");
        }
        else
        {
            record.append("X");
        }

    }

    /* (non-Javadoc)
     * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent, java.lang.Class)
     */
    public List retrieve(IEvent anEvent, Class pType)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
