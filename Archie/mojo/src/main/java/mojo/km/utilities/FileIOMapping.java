/*
 * Created on Aug 4, 2004
 *
 */
package mojo.km.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.km.config.AppProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.context.multidatasource.IMapping;
import mojo.km.context.multidatasource.IStatement;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.PersistentObject;
import java.net.Socket;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author eamundson
 * 
 */
public class FileIOMapping implements IMapping
{

    public FileIOMapping()
    {

    }

    public void setUseFile()
    {
        useFile = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#doInsert(mojo.km.persistence.PersistentObject,
     *      mojo.km.context.multidatasource.IConnection)
     */
    public void doInsert(PersistentObject arg0, IConnection arg1)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#init()
     */
    public void init()
    {
        // TODO Auto-generated method stub

    }

    public List retrieve(IEvent anEvent, Class pType, Map retVal)
    {
        if(anEvent instanceof mojo.km.persistence.OIDEvent)
        {
            this.retrieveWithOID(anEvent, pType, retVal);
        }
        else
        {
            this.retrieveEventQuery(anEvent, pType, retVal);
        }
        
        return null;
    }
    
    /**
     * @param anEvent
     * @param type
     * @param retVal
     * @return
     */
    private Map retrieveEventQuery(IEvent anEvent, Class type, Map retVal)
    {
        //String eventString = TestDataUtil.serializeEvent(anEvent);
        Session session = ContextManager.getSession();
        Object result = session.get(anEvent.toString());
        
        if(result != null)
        {
            List results = (List) result;
            Iterator i = results.iterator();
            while(i.hasNext())
            {
                PersistentObject obj = (PersistentObject) i.next();                
                retVal.put(obj.getOID(), obj);
            }
        }
        
        return retVal;
    }

    public Map retrieveWithDynamicSQL(IEvent arg0, Class arg1, Map retVal)
    {
        return retVal;
    }

    public Map retrieveOringParms(IEvent anEvent, Class pType, Map retVal)
    {
        initType(pType);
        if (objectHash.containsKey(pType))
        {
            Hashtable objectType = ((TypeHelper) objectHash.get(pType)).returnHash;
            for (Iterator i = objectType.values().iterator(); i.hasNext();)
            {
                PersistentObject pObj = (PersistentObject) i.next();
                try
                {
                    EntityMappingProperties entityProps = MojoProperties.getInstance().getEntityMap(pType.getName());
                    if (entityProps != null)
                    {
                        EventQueryProperties eventProps = entityProps.getEventQueryProperties(anEvent, "STUB");
                        Iterator j = eventProps.getParmsIterator();
                        while (j.hasNext())
                        {
                            ParmMappingProperties parmProps = (ParmMappingProperties) j.next();
                            String propName = parmProps.getPropertyName();
                            String pVal = ("" + Reflection.invokeAccessorMethod(pObj, propName)).trim();
                            String eVal = ("" + Reflection.invokeAccessorMethod(anEvent, propName)).trim();
                            if (pVal.equals(eVal))
                            {
                                retVal.put(pObj.getOID(), pObj);
                                break;
                            }
                        }
                    }
                } catch (Exception query)
                {
                }
            }
        }
        return retVal;
    }

    public Map retrieveAndingParms(IEvent anEvent, Class pType, Map retVal)
    {
        initType(pType);
        if (objectHash.containsKey(pType))
        {
            Hashtable objectType = ((TypeHelper) objectHash.get(pType)).returnHash;
            for (Iterator i = objectType.values().iterator(); i.hasNext();)
            {
                PersistentObject pObj = (PersistentObject) i.next();
                try
                {
                    // statement.clearParameters();
                    boolean doNotAdd = false;
                    List methods = Reflection.getAccessors(anEvent.getClass());
                    int len = methods.size();
                    for(int j=0;j<len;j++)
                    {
                        Method getter = (Method) methods.get(j);
                        String propName = Reflection.getPropertyName(getter);
                        List mutators = Reflection.getMutatorMethods(pObj.getClass(), propName);
                        if (mutators.size() > 0)
                        {
                            String pVal = ("" + Reflection.invokeAccessorMethod(pObj, propName)).trim();
                            String eVal = ("" + Reflection.invokeAccessorMethod(anEvent, propName)).trim();
                            if (eVal.equals("*") || eVal.equals("") || eVal.equals("null"))
                            {
                                continue;
                            }
                            if (!pVal.equals(eVal))
                            {
                                doNotAdd = true;
                                break;
                            }
                        }
                    }
                    if (!doNotAdd)
                    {
                        retVal.put(pObj.getOID(), pObj);
                    }
                } catch (Exception query)
                {
                }
            }
        }
        return retVal;
    }

    public Map retrieveAttributeEvent(IEvent arg0, Class arg1, Map retVal)
    {
        mojo.km.persistence.AttributeEvent attEvent = (mojo.km.persistence.AttributeEvent) arg0;
        initType(arg1);
        if (objectHash.containsKey(arg1))
        {
            Hashtable objectType = ((TypeHelper) objectHash.get(arg1)).returnHash;
            for (Iterator i = objectType.values().iterator(); i.hasNext();)
            {
                PersistentObject pObj = (PersistentObject) i.next();
                String val = "" + Reflection.invokeAccessorMethod(pObj, attEvent.getAttributeName());
                if (val.equals("" + attEvent.getAttributeValue()) || "*".equals(attEvent.getAttributeValue()))
                {
                    retVal.put(pObj.getOID(), pObj);
                }
            }
        }
        return retVal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent,
     *      java.lang.Class)
     */
    public Map retrieveWithOID(IEvent arg0, Class arg1, Map retVal)
    {
        mojo.km.persistence.OIDEvent oidEvent = (mojo.km.persistence.OIDEvent) arg0;
        initType(arg1);
        if (objectHash.containsKey(arg1))
        {
            Hashtable objectType = ((TypeHelper) objectHash.get(arg1)).returnHash;
            if (objectType.containsKey(oidEvent.getOID()))
            {
                retVal.put(oidEvent.getOID(), objectType.get(oidEvent.getOID()));
            }
        }
        // TODO Auto-generated method stub
        return retVal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#save(mojo.km.persistence.PersistentObject)
     */
    public void save(PersistentObject arg0)
    {

        initType(arg0.getClass());
        if (arg0.getOID() == null)
        {
            arg0.setOID(getOID(arg0.getClass()));
        }

        // TODO Auto-generated method stub
        Socket aSocket = getSocket();
        if (aSocket == null || useFile)
        {
            try
            {
                saveWFile(arg0);
            } catch (Exception t)
            {
                t.printStackTrace();
            }
        } else
        {
            saveSocket(arg0);
        }

    }

    private void saveWFile(PersistentObject thePObj) throws Exception
    {
        String fileName = AppProperties.getInstance().getProperty("stubDir");
        Stack dirStack = new Stack();
        Class cType = thePObj.getClass();
        for (String parentName = cType.getName(); !parentName.endsWith("PersistentObject");)
        {
            dirStack.push(parentName);
            cType = cType.getSuperclass();
            parentName = cType.getName();
        }
        while (!dirStack.isEmpty())
        {
            fileName += "/" + dirStack.pop();
            File aFile = new File(fileName);
            if (!aFile.exists())
            {
                aFile.mkdir();
            }
        }
        if (thePObj.isDeleted())
        {
            File pObjFile = new File(fileName + "/" + thePObj.getOID() + ".store");
            if (pObjFile.exists())
            {
                pObjFile.delete();
                if (objectHash.containsKey(thePObj.getClass()))
                {
                    TypeHelper tHelper = (TypeHelper) objectHash.get(thePObj.getClass());
                    tHelper.returnHash.remove(thePObj);
                }
            }
        } else
        {
            FileOutputStream oFile = new FileOutputStream(fileName + "/" + thePObj.getOID() + ".store");
            ObjectOutputStream objStream = new ObjectOutputStream(oFile);
            objStream.writeObject((Object) thePObj);
            objStream.close();
        }
        //addPersistentObject(thePObj);

    }

    private void saveSocket(PersistentObject arg0)
    {
        // TODO Auto-generated method stub
        OutputStream socketOutputStream = null;
        try
        {
            socketOutputStream = getSocket().getOutputStream();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayOutputStream objectStream = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream objOutStream = new ObjectOutputStream(objectStream);
            objOutStream.writeUTF("save");
            objOutStream.writeObject(arg0);
            DataOutputStream oStream = new DataOutputStream(outStream);
            int size = objectStream.toByteArray().length;
            System.out.println("size = " + size);
            oStream.writeInt(size);
            oStream.write(objectStream.toByteArray(), 0, size);
            socketOutputStream.write(outStream.toByteArray(), 0, outStream.toByteArray().length);
            outStream.flush();
            //addPersistentObject(arg0);
        } catch (Exception exception)
        {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setConnectionName(java.lang.String)
     */
    public void setConnectionName(String arg0)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setParameter(java.sql.PreparedStatement,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.Object, java.lang.String)
     */
    public void setParameter(PreparedStatement arg0, String arg1, String arg2, String arg3, Object arg4, String arg5)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setPersistentObject(mojo.km.persistence.PersistentObject,
     *      java.sql.ResultSet, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void setPersistentObject(PersistentObject arg0, ResultSet arg1, String arg2, String arg3, String arg4, String arg5,
            String arg6)
    {
        // TODO Auto-generated method stub

    }

    protected String getOID(Class pType)
    {
        if (!objectHash.containsKey(pType))
        {
            return "0";
        }
        Hashtable typeHash = ((TypeHelper) objectHash.get(pType)).returnHash;
        long max = 0;
        for (Iterator i = typeHash.values().iterator(); i.hasNext();)
        {
            PersistentObject pObj = (PersistentObject) i.next();
            long current = convertLong("" + pObj.getOID());
            if (current > max)
            {
                max = current;
            }
        }

        return "" + (max + 1);
    }

    protected void addPersistentObject(PersistentObject pObj)
    {
        if (!objectHash.containsKey(pObj.getClass()))
        {
            TypeHelper tHelper = new TypeHelper();
            tHelper.returnHash = new Hashtable();
            objectHash.put(pObj.getClass(), tHelper);
            tHelper.returnHash.put(pObj.getOID(), pObj);
        } else
        {

            TypeHelper tHelper = (TypeHelper) objectHash.get(pObj.getClass());
            tHelper.returnHash.put(pObj.getOID(), pObj);
        }

    }

    protected long convertLong(String oid)
    {
        long retVal = 0;
        try
        {
            retVal = Long.parseLong(oid);
        } catch (Exception e)
        {

        }
        return retVal;
    }

    private void initTypeSocket(Class pType) throws Exception
    {
        OutputStream socketOutputStream = null;
        InputStream socketInputStream = null;
        try
        {
            socketOutputStream = getSocket().getOutputStream();
            socketInputStream = getSocket().getInputStream();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        ByteArrayOutputStream obStream = new ByteArrayOutputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DataOutputStream oStream = new DataOutputStream(outStream);
        ObjectOutputStream objStream = new ObjectOutputStream(obStream);
        objStream.writeUTF(pType.getName());
        TypeHelper tHelp = (TypeHelper) objectHash.get(pType);
        if (!objectHash.containsKey(pType))
        {
            objStream.writeInt(0);
        } else
        {
            objStream.writeInt(tHelp.lastSize);
        }
        objStream.close();
        oStream.writeInt(obStream.toByteArray().length);
        oStream.write(obStream.toByteArray(), 0, obStream.toByteArray().length);
        oStream.close();
        socketOutputStream.write(outStream.toByteArray(), 0, outStream.toByteArray().length);
        socketOutputStream.flush();
        DataInputStream dIStream = new DataInputStream(socketInputStream);

        int noBytes = dIStream.readInt();
        //System.out.println( "Reading bytes = " + noBytes);

        if (noBytes > 0)
        {
            byte[] returnBuff = new byte[noBytes];

            dIStream.readFully(returnBuff, 0, noBytes);
            ByteArrayInputStream bIStream = new ByteArrayInputStream(returnBuff);
            ObjectInputStream oIStream = new ObjectInputStream(bIStream);
            int currentSize = oIStream.readInt();
            Hashtable returnPObj = (Hashtable) oIStream.readObject();
            tHelp = new TypeHelper();
            tHelp.returnHash = returnPObj;
            tHelp.lastSize = currentSize;
            objectHash.put(pType, tHelp);
        }

    }

    private void initTypeWFile(Class pType)
    {
        TypeHelper tHelper = null;
        String sDirName = AppProperties.getInstance().getProperty("stubDir");
        if (sDirName == null)
        {
            sDirName = "/stubDB";
        }
        File sDir = new File(sDirName);
        if (!sDir.exists())
        {
            sDir.mkdir();
        }
        if (!objectHash.containsKey(pType))
        {
            String fileName = AppProperties.getInstance().getProperty("stubDir");
            Stack dirStack = new Stack();
            Class cType = pType;
            for (String parentName = cType.getName(); !parentName.endsWith("PersistentObject");)
            {
                dirStack.push(parentName);
                cType = cType.getSuperclass();
                parentName = cType.getName();
            }
            File pDir = null;
            while (!dirStack.isEmpty())
            {
                fileName += "/" + dirStack.pop();
                pDir = new File(fileName);
                if (!pDir.exists())
                {
                    pDir.mkdir();
                }
            }
            tHelper = new TypeHelper();
            tHelper.dir = pDir;
            tHelper.lastSize = -1;
            tHelper.returnHash = new Hashtable();
            //tTracker.setLastLength(pDir.length());
            objectHash.put(pType, tHelper);

        } else
        {
            tHelper = (TypeHelper) objectHash.get(pType);
        }
        File tDir = tHelper.dir;
        File[] files = tDir.listFiles();
        if (files == null)
        {
            return;
        }

        int size = getDirSize(tDir);
        if (tDir.isDirectory() && size != tHelper.lastSize)
        {
            tHelper.returnHash.clear();
            if (files.length == 0)
            {
                return;
            }
            loadAllOfType(tDir, tHelper);
            tHelper.lastSize = size;
        }

    }

    private int getDirSize(File tDir)
    {
        int size = 0;
        File[] files = tDir.listFiles();
        for (int j = 0; j < files.length; j++)
        {
            File objFile = files[j];
            if (objFile.isDirectory())
            {
                size += getDirSize(objFile);
            } else
            {
                size += objFile.length();
            }
        }
        return size;
    }

    private void loadAllOfType(File tDir, TypeHelper tHelper)
    {
        File[] files = tDir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            try
            {
                if (files[i].isDirectory())
                {
                    loadAllOfType(files[i], tHelper);
                } else
                {
                    FileInputStream iStream = new FileInputStream(files[i]);
                    ObjectInputStream oiStream = new ObjectInputStream(iStream);
                    PersistentObject pObj = (PersistentObject) oiStream.readObject();
                    tHelper.returnHash.put("" + pObj.getOID(), pObj);
                    iStream.close();
                }
            } catch (Exception io)
            {
                try
                {
                    files[i].delete();
                } catch (Exception dE)
                {

                }
            }
        }

    }

    protected void initType(Class pType)
    {
        //if (!objectHash.containsKey(pType)) {

        try
        {
            Socket aSocket = getSocket();
            if (aSocket == null || useFile)
            {
                initTypeWFile(pType);
            } else
            {
                initTypeSocket(pType);
            }
        } catch (Exception io)
        {
            io.printStackTrace();
        }
        //}
    }

    static protected Socket getSocket()
    {
        Socket socket = null;
        if (AppProperties.getInstance().getProperty("MapServer") == null)
        {
            return null;
        }
        if (triedSockets.containsKey(ContextManager.currentContext()))
        {
            return null;
        }

        if (!sockets.containsKey(ContextManager.currentContext()))
        {
            if (socket == null)
            {
                try
                {
                    URL serverURL = new URL(AppProperties.getInstance().getProperty("MapServer"));
                    int serverPort = serverURL.getPort();
                    socket = new Socket(serverURL.getHost(), serverPort);
                    sockets.put(ContextManager.currentContext(), socket);
                } catch (Exception e)
                {
                    triedSockets.put(ContextManager.currentContext(), ContextManager
                            .currentContext());
                }

            }

        } else
        {
            socket = (Socket) sockets.get(ContextManager.currentContext());
        }
        return socket;

    }

    class TypeHelper
    {
        int lastSize = 0;

        Hashtable returnHash = null;

        File dir = null;
    }

    static Hashtable objectHash = new Hashtable();

    /** @modelguid {33A38B55-9355-49D1-B7E4-CB16DF0A68E6} */
    static Hashtable sockets = new Hashtable();

    static Hashtable triedSockets = new Hashtable();

    private boolean useFile = false;

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setCallback(mojo.km.config.CallbackProperties)
     */
    public void setCallback(CallbackProperties callBack)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getCallback()
     */
    public CallbackProperties getCallback()
    {
        // TODO Auto-generated method stub
        return null;
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
     * @see mojo.km.context.multidatasource.IMapping#getUpdateStatement()
     */
    public IStatement getUpdateStatement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getInsertStatement()
     */
    public IStatement getInsertStatement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getQueryStatement()
     */
    public IStatement getQueryStatement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#init(java.lang.String)
     */
    public void init(String key)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setConnection(mojo.km.context.multidatasource.IConnection)
     */
    public void setConnection(IConnection aConnection)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#setEntityMap(mojo.km.config.EntityMappingProperties)
     */
    public void setEntityMap(EntityMappingProperties entityMap)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getEntityMap()
     */
    public EntityMappingProperties getEntityMap()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IMapping#getConnectionName()
     */
    public String getConnectionName()
    {
        // TODO Auto-generated method stub
        return null;
    }

	/* (non-Javadoc)
	 * @see mojo.km.context.multidatasource.IMapping#retrieveMeta(mojo.km.messaging.IEvent, java.lang.Class, java.util.Map)
	 */
	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal) {
		throw new UnsupportedOperationException("mojo.km.utilities.FileIOMapping.retreiveMeta not supported ");
	}

    public List retrieve(IEvent anEvent, Class pType)
    {
        throw new UnsupportedOperationException("List " + this.getClass().getName()
                + ".retrieve(IEvent, Class) has not been implemented.");
    }
	

}
