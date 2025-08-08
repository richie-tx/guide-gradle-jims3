/*
 * Created on Feb 14, 2005
 *
 */
package mojo.km.config.xml;

import java.util.Iterator;

import mojo.km.config.CallbackProperties;
import mojo.km.config.DependencyProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.logging.LogUtil;

import org.apache.log4j.Level;
import org.jdom.Element;

/**
 * @author eamundson
 *  
 */
public class MappingUtility
{
    static public String getPackage(String className)
    {
        return className.substring(0, className.lastIndexOf("."));
    }

    static public EventQueryProperties readQueryCallbackProperties(Element pElement, EntityMappingProperties aParent)
    {
        EventQueryProperties callback = new EventQueryProperties();
        callback.setMappingClassName(pElement.getAttributeValue(EventQueryProperties.MAPPINGCLASSNAME));
        callback.setMappingMethodName(pElement.getAttributeValue(EventQueryProperties.MAPPINGMETHODNAME));
        callback.setStoredProcedureName(pElement.getAttributeValue(EventQueryProperties.STOREDPROCEDURENAME));
        callback.setCicsProgramName(pElement.getAttributeValue(EventQueryProperties.CICSPROGRAMNAME));
        callback.setSequenceNumber(pElement.getAttributeValue(EventQueryProperties.SEQUENCENUMBER));
        callback.setSubclassSequence(pElement.getAttributeValue(EventQueryProperties.SUBCLASSSEQUENCE));
        callback.setSubclassRecord(pElement.getAttributeValue(EventQueryProperties.SUBCLASSRECORD));
        callback.setTranId(pElement.getAttributeValue(EventQueryProperties.TRANID));
        callback.setFileName(pElement.getAttributeValue(EventQueryProperties.FILENAME));
        callback.setConnectionName(pElement.getAttributeValue(EventQueryProperties.CONNECTIONNAME));
        callback.setSource(pElement.getAttributeValue(EventQueryProperties.SOURCE));
        callback.setName(pElement.getAttributeValue(EventQueryProperties.NAME));
        callback.setEventName(pElement.getAttributeValue(EventQueryProperties.EVENTNAME));
        callback.setWhereClause(pElement.getAttributeValue(EventQueryProperties.WHERECLAUSE));
        callback.setWhereClauseGenerator(pElement.getAttributeValue(EventQueryProperties.WHERECLAUSEGENERATOR));
        callback.setWhereClauseGeneratorMethod(pElement
                .getAttributeValue(EventQueryProperties.WHERECLAUSEGENERATORMETHOD));
        callback.setMappingComplete(pElement.getAttributeValue(EventQueryProperties.MAPPINGCOMPLETE));

        callback.setParent(aParent);

        Element lElement = pElement.getChild("FieldMappings");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("FieldMapping").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                FieldMappingProperties lProps = readFieldProperties(lEntityElement, callback);
                callback.addFieldMap(lProps);
            }
        }
        lElement = pElement.getChild("ParmMappings");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("ParmMapping").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                ParmMappingProperties lProps = readParmProperties(lEntityElement, callback);
                callback.addParmMapping(lProps);
            }
        }

        return callback;
    }

    public static EventQueryProperties getQueryProperties(Class aClass, Class queryEventClass, String connectionName)
    {
        EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(aClass.getName());
        EventQueryProperties eqProps = eProps.getEventQueryProperties(queryEventClass, connectionName);
        return eqProps;
    }

    public static EntityMappingProperties readEntityMapping(Element entityMapping)
    {
        EntityMappingProperties entityMap = new EntityMappingProperties();
        entityMap.setEntity(entityMapping.getAttributeValue(EntityMappingProperties.ENTITY));
        entityMap.setUndoable(entityMapping.getAttributeValue(EntityMappingProperties.UNDOABLE));
        if (entityMapping.getAttributeValue(EntityMappingProperties.MEMBERNAME) != null)
        {
            entityMap.setMemberName(entityMapping.getAttributeValue(EntityMappingProperties.MEMBERNAME));
        }

        if (entityMapping.getAttributeValue(EntityMappingProperties.CONTEXTKEY) != null
                && !entityMapping.getAttributeValue(EntityMappingProperties.CONTEXTKEY).equals(""))
        {
            entityMap.setContextKey(entityMapping.getAttributeValue(EntityMappingProperties.CONTEXTKEY));
        }
        else
        {
            entityMap.setContextKey(entityMap.getEntity());
        }

        //Element lElement = doc.getRootElement().getChild("SaveCallbacks");
        Element lElement = entityMapping.getChild("SaveCallbacks");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("SaveCallback").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                SaveCallbackProperties sProps = readSaveCallbackProperties(lEntityElement, entityMap);

                String conName = sProps.getConnectionName();

                try
                {
                    sProps.optimize();
                }
                catch (Exception e)
                {
                    outputError(e, entityMap, sProps);
                }

                entityMap.addSaveCallback(sProps);
            }
        }
        lElement = entityMapping.getChild("QueryCallbacks");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("QueryCallback").iterator();
            while (i.hasNext())
            {
                Element entityElement = (Element) i.next();
                EventQueryProperties eProps = readQueryCallbackProperties(entityElement, entityMap);

                try
                {
                    eProps.optimize();
                }
                catch (Exception e)
                {
                    outputError(e, entityMap, eProps);
                }

                entityMap.addQueryCallbacks(eProps);
            }
        }
        lElement = entityMapping.getChild("CompositeEntities");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("EntityMapping").iterator();
            String contextKey = "";
            while (i.hasNext())
            {
                Element entityElement = (Element) i.next();
                EntityMappingProperties entity = readEntityMapping(entityElement);

                contextKey = MojoProperties.getInstance().addEntityMapping(entity, contextKey);
            }
        }

        return entityMap;
    }

    /**
     * @param message
     * @param entityMap
     * @param props
     */
    private static void outputError(Exception e, EntityMappingProperties entityMap, CallbackProperties aProps)
    {
        StringBuffer buffer = new StringBuffer(200);        
        buffer.append("\nEntity mapping could not be optimized: ");
        buffer.append(entityMap.getEntity());
        buffer.append("::");
        buffer.append(entityMap.getContextKey());
        buffer.append("\ncallbackName: ");
        buffer.append(aProps.getName());
        buffer.append("\nconnectionName: ");
        buffer.append(aProps.getConnectionName());
        buffer.append("\n");
        if (aProps.getErrorFieldMappingProperties() != null)
        {
            buffer.append(aProps.getErrorFieldMappingProperties().getClass().getName());
            buffer.append(" with error from: ");
            buffer.append(aProps.getErrorFieldMappingProperties());
            buffer.append("\n");
        }
        buffer.append("Exception: " + e.getMessage());        
        LogUtil.log(Level.WARN, buffer.toString());
        LogUtil.log(Level.WARN, e);
    }

    static public SaveCallbackProperties readSaveCallbackProperties(Element pElement, EntityMappingProperties aParent)
    {
        SaveCallbackProperties saveCallback = new SaveCallbackProperties();
        saveCallback.setMappingClassName(pElement.getAttributeValue(SaveCallbackProperties.MAPPINGCLASSNAME));
        saveCallback.setMappingMethodName(pElement.getAttributeValue(SaveCallbackProperties.MAPPINGMETHODNAME));
        saveCallback.setStoredProcedureName(pElement.getAttributeValue(SaveCallbackProperties.STOREDPROCEDURENAME));
        saveCallback.setCicsProgramName(pElement.getAttributeValue(SaveCallbackProperties.CICSPROGRAMNAME));
        saveCallback.setSequenceNumber(pElement.getAttributeValue(SaveCallbackProperties.SEQUENCENUMBER));
        saveCallback.setSubclassSequence(pElement.getAttributeValue(SaveCallbackProperties.SUBCLASSSEQUENCE));
        saveCallback.setSubclassRecord(pElement.getAttributeValue(SaveCallbackProperties.SUBCLASSRECORD));
        saveCallback.setTranId(pElement.getAttributeValue(SaveCallbackProperties.TRANID));
        saveCallback.setFileName(pElement.getAttributeValue(SaveCallbackProperties.FILENAME));
        saveCallback.setConnectionName(pElement.getAttributeValue(SaveCallbackProperties.CONNECTIONNAME));
        saveCallback.setWhereClause(pElement.getAttributeValue(EventQueryProperties.WHERECLAUSE));
        saveCallback.setSource(pElement.getAttributeValue(SaveCallbackProperties.SOURCE));
        saveCallback.setName(pElement.getAttributeValue(SaveCallbackProperties.NAME));
        saveCallback.setMappingComplete(pElement.getAttributeValue(EventQueryProperties.MAPPINGCOMPLETE));
        saveCallback.setParent(aParent);
        Element lElement = pElement.getChild("FieldMappings");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("FieldMapping").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                FieldMappingProperties lProps = readFieldProperties(lEntityElement, saveCallback);
                saveCallback.addFieldMap(lProps);
            }
        }
        lElement = pElement.getChild("ParmMappings");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("ParmMapping").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                ParmMappingProperties lProps = readParmProperties(lEntityElement, saveCallback);
                saveCallback.addParmMapping(lProps);
            }
        }

        lElement = pElement.getChild("Dependencies");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("Dependency").iterator();
            while (i.hasNext())
            {
                Element lEntityElement = (Element) i.next();
                DependencyProperties lProps = readDependencyProperties(lEntityElement, saveCallback);
                saveCallback.addDependency(lProps);
            }
        }

        return saveCallback;
    }

    /**
     * @param entityElement
     * @param saveCallback
     * @return
     */
    private static DependencyProperties readDependencyProperties(Element pElement, SaveCallbackProperties saveCallback)
    {
        DependencyProperties dProps = new DependencyProperties();
        dProps.setClassName(pElement.getAttributeValue(DependencyProperties.CLASSNAME));
        dProps.setDependencyType(pElement.getAttributeValue(DependencyProperties.DEPENDENCYTYPE));
        return dProps;
    }

    static public FieldMappingProperties readFieldProperties(Element pElement, CallbackProperties aCallback)
    {
        FieldMappingProperties fieldMapping = new FieldMappingProperties();
        fieldMapping.setDataItemName(pElement.getAttributeValue(FieldMappingProperties.DATAITEMNAME));
        fieldMapping.setParmIndex(pElement.getAttributeValue(FieldMappingProperties.PARMINDEX));
        fieldMapping.setFormat(pElement.getAttributeValue(FieldMappingProperties.FORMAT));
        fieldMapping.setPosition(pElement.getAttributeValue(FieldMappingProperties.POSITION));
        fieldMapping.setAppendFiller(pElement.getAttributeValue(FieldMappingProperties.APPENDFILLER));
        fieldMapping.setPadChar(pElement.getAttributeValue(FieldMappingProperties.PADCHARACTER));
        fieldMapping.setPropertyName(pElement.getAttributeValue(FieldMappingProperties.PROPERTYNAME));
        fieldMapping.setPropertyType(pElement.getAttributeValue(FieldMappingProperties.PROPERTYTYPE));
        fieldMapping.setText(pElement.getAttributeValue(FieldMappingProperties.TEXT));
        fieldMapping.setName(pElement.getAttributeValue(FieldMappingProperties.NAME));
        fieldMapping.setLength(pElement.getAttributeValue(FieldMappingProperties.LENGTH));
        fieldMapping.setFormatString(pElement.getAttributeValue(FieldMappingProperties.FORMATSTRING));
        fieldMapping.setCallback(aCallback);
        return fieldMapping;

    }

    static public ParmMappingProperties readParmProperties(Element pElement, CallbackProperties aCallback)
    {
        ParmMappingProperties lParmProperties = new ParmMappingProperties();
        lParmProperties.setDataItemName(pElement.getAttributeValue(FieldMappingProperties.DATAITEMNAME));
        lParmProperties.setParmIndex(pElement.getAttributeValue(FieldMappingProperties.PARMINDEX));
        lParmProperties.setFormat(pElement.getAttributeValue(FieldMappingProperties.FORMAT));
        lParmProperties.setPosition(pElement.getAttributeValue(FieldMappingProperties.POSITION));
        lParmProperties.setCardPosition(pElement.getAttributeValue(FieldMappingProperties.CARDPOSITION));
        lParmProperties.setAppendFiller(pElement.getAttributeValue(FieldMappingProperties.APPENDFILLER));
        lParmProperties.setPadChar(pElement.getAttributeValue(FieldMappingProperties.PADCHARACTER));
        lParmProperties.setText(pElement.getAttributeValue(FieldMappingProperties.TEXT));
        lParmProperties.setPropertyName(pElement.getAttributeValue(FieldMappingProperties.PROPERTYNAME));
        lParmProperties.setPropertyType(pElement.getAttributeValue(FieldMappingProperties.PROPERTYTYPE));
        lParmProperties.setName(pElement.getAttributeValue(FieldMappingProperties.NAME));
        lParmProperties.setLength(pElement.getAttributeValue(FieldMappingProperties.LENGTH));
        lParmProperties.setFormatString(pElement.getAttributeValue(FieldMappingProperties.FORMATSTRING));
        lParmProperties.setConvertWildcard(pElement.getAttributeValue(FieldMappingProperties.CONVERTWILDCARD));
        lParmProperties.setCallback(aCallback);
        return lParmProperties;
    }

    static public void writeEntityMap(EntityMappingProperties aProperties, Element anElement)
    {

        Element lEntityElement = new Element("EntityMapping");

        anElement.addContent(lEntityElement);

        // Set name of entity being mapped
        String lName = aProperties.getEntity();
        if (lName != null)
        {
            lEntityElement.setAttribute(EntityMappingProperties.ENTITY, lName);
        }

        // Set member name of relation.
        String lMemberName = aProperties.getMemberName();

        if (lMemberName != null)
        {
            lEntityElement.setAttribute(EntityMappingProperties.MEMBERNAME, lMemberName);
        }

        // Set context of relation.
        String lContext = aProperties.getContextKey();
        if (lContext != null)
        {
            lEntityElement.setAttribute(EntityMappingProperties.CONTEXTKEY, lContext);
        }

        Iterator qMaps = aProperties.getQueryCallbacks();

        if (qMaps.hasNext())
        {
            Element queryElement = new Element("QueryCallbacks");
            lEntityElement.addContent(queryElement);
            while (qMaps.hasNext())
            {
                EventQueryProperties lProperties = (EventQueryProperties) qMaps.next();
                writeQueryCallbacks(lProperties, queryElement);
            }
        }

        Iterator sMaps = aProperties.getSaveCallbacks();

        if (sMaps.hasNext())
        {
            Element saveElement = new Element("SaveCallbacks");
            lEntityElement.addContent(saveElement);
            while (sMaps.hasNext())
            {
                SaveCallbackProperties lProperties = (SaveCallbackProperties) sMaps.next();
                writeSaveCallbacks(lProperties, saveElement);
            }
        }

    }

    static public void writeQueryCallbacks(EventQueryProperties lProperties, Element queryElement)
    {
        Element lqueryElement = new Element("QueryCallback");

        queryElement.addContent(lqueryElement);
        String lMapClassName = lProperties.getMappingClassName();
        String lMappingMethodName = lProperties.getMappingMethodName();
        String lStoredProcedureName = lProperties.getStoredProcedureName();
        String lCicsProgramName = lProperties.getCicsProgramName();
        String lSeqNum = lProperties.getSequenceNumber();
        String lSubclassSeqNum = lProperties.getSubclassSequence();
        String lSubclassRecord = lProperties.getSubclassRecord();
        String lTranId = lProperties.getTranId();
        String lFileName = lProperties.getFileName();
        String lConnectionName = lProperties.getConnectionName();
        String lEventName = lProperties.getEventName();
        String lSource = lProperties.getSource();
        String lWhereClause = lProperties.getWhereClause();
        String lWhereClauseGenerator = lProperties.getWhereClauseGenerator();
        String lWhereClauseGeneratorMethod = lProperties.getWhereClauseGeneratorMethod();
        String lMappingComplete = lProperties.getMappingComplete();
        String lName = lProperties.getName();

        if (lName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.NAME, lName);
        }
        if (lMapClassName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.MAPPINGCLASSNAME, lMapClassName);
        }
        if (lMappingMethodName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.MAPPINGMETHODNAME, lMappingMethodName);
        }
        if (lStoredProcedureName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.STOREDPROCEDURENAME, lStoredProcedureName);
        }

        if (lCicsProgramName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.CICSPROGRAMNAME, lCicsProgramName);
        }

        if (lSeqNum != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.SEQUENCENUMBER, lSeqNum);
        }

        if (lSubclassSeqNum != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.SUBCLASSSEQUENCE, lSubclassSeqNum);
        }

        if (lSubclassRecord != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.SUBCLASSRECORD, lSubclassRecord);
        }

        if (lTranId != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.TRANID, lTranId);
        }

        if (lFileName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.FILENAME, lFileName);
        }

        if (lConnectionName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.CONNECTIONNAME, lConnectionName);
        }

        if (lEventName != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.EVENTNAME, lEventName);
        }
        if (lSource != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.SOURCE, lSource);
        }
        if (lWhereClause != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.WHERECLAUSE, lWhereClause);
        }
        if (lWhereClauseGenerator != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.WHERECLAUSEGENERATOR, lWhereClauseGenerator);
        }
        if (lWhereClauseGeneratorMethod != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.WHERECLAUSEGENERATORMETHOD, lWhereClauseGeneratorMethod);
        }
        if (lMappingComplete != null)
        {
            lqueryElement.setAttribute(EventQueryProperties.MAPPINGCOMPLETE, lMappingComplete);
        }
        Iterator sParms = lProperties.getParmsIterator();

        if (sParms.hasNext())
        {
            Element parmElement = new Element("ParmMappings");
            lqueryElement.addContent(parmElement);
            while (sParms.hasNext())
            {
                ParmMappingProperties parmProperties = (ParmMappingProperties) sParms.next();
                writeFieldParmMapping(parmProperties, parmElement);
            }
        }

        Iterator sFields = lProperties.getFieldsIterator();

        if (sFields.hasNext())
        {
            Element fieldElement = new Element("FieldMappings");
            lqueryElement.addContent(fieldElement);
            while (sFields.hasNext())
            {
                FieldMappingProperties fieldProperties = (FieldMappingProperties) sFields.next();
                writeFieldParmMapping(fieldProperties, fieldElement);
            }
        }

    }

    static public void writeSaveCallbacks(SaveCallbackProperties lProperties, Element saveElement)
    {
        Element lsaveElement = new Element("SaveCallback");

        saveElement.addContent(lsaveElement);
        String lMapClassName = lProperties.getMappingClassName();
        String lMappingMethodName = lProperties.getMappingMethodName();
        String lStoredProcedureName = lProperties.getStoredProcedureName();
        String lCicsProgramName = lProperties.getCicsProgramName();
        String lSeqNum = lProperties.getSequenceNumber();
        String lSubclassSeqNum = lProperties.getSubclassSequence();
        String lSubclassRecord = lProperties.getSubclassRecord();
        String lTranId = lProperties.getTranId();
        String lFileName = lProperties.getFileName();
        String lConnectionName = lProperties.getConnectionName();
        String lWhereClause = lProperties.getWhereClause();
        String lMappingComplete = lProperties.getMappingComplete();
        String lSource = lProperties.getSource();
        String lName = lProperties.getName();
        if (lName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.NAME, lName);
        }

        if (lMapClassName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.MAPPINGCLASSNAME, lMapClassName);
        }
        if (lMappingMethodName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.MAPPINGMETHODNAME, lMappingMethodName);
        }
        if (lStoredProcedureName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.STOREDPROCEDURENAME, lStoredProcedureName);
        }

        if (lCicsProgramName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.CICSPROGRAMNAME, lCicsProgramName);
        }

        if (lSeqNum != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.SEQUENCENUMBER, lSeqNum);
        }

        if (lSubclassSeqNum != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.SUBCLASSSEQUENCE, lSubclassSeqNum);
        }

        if (lSubclassRecord != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.SUBCLASSRECORD, lSubclassRecord);
        }

        if (lTranId != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.TRANID, lTranId);
        }

        if (lFileName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.FILENAME, lFileName);
        }

        if (lConnectionName != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.CONNECTIONNAME, lConnectionName);
        }
        if (lWhereClause != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.WHERECLAUSE, lWhereClause);
        }
        if (lMappingComplete != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.MAPPINGCOMPLETE, lMappingComplete);
        }

        if (lSource != null)
        {
            lsaveElement.setAttribute(EventQueryProperties.SOURCE, lSource);
        }
        Iterator sParms = lProperties.getParmsIterator();

        if (sParms.hasNext())
        {
            Element parmElement = new Element("ParmMappings");
            lsaveElement.addContent(parmElement);
            while (sParms.hasNext())
            {
                ParmMappingProperties parmProperties = (ParmMappingProperties) sParms.next();
                writeFieldParmMapping(parmProperties, parmElement);
            }
        }

        Iterator sFields = lProperties.getFieldsIterator();

        if (sFields.hasNext())
        {
            Element fieldElement = new Element("FieldMappings");
            lsaveElement.addContent(fieldElement);
            while (sFields.hasNext())
            {
                FieldMappingProperties fieldProperties = (FieldMappingProperties) sFields.next();
                writeFieldParmMapping(fieldProperties, fieldElement);
            }
        }
    }

    static public void writeFieldParmMapping(FieldMappingProperties lProperties, Element anElement)
    {
        Element lElement = null;
        if (lProperties instanceof ParmMappingProperties)
        {
            lElement = new Element("ParmMapping");
        }
        else if (lProperties instanceof FieldMappingProperties)
        {
            lElement = new Element("FieldMapping");
        }
        if (lElement != null)
        {

            anElement.addContent(lElement);
            String lAssociationType = lProperties.getAssociationType();
            String lDataItemName = lProperties.getDataItemName();
            String lParmIndex = lProperties.getParmIndex();
            String lFormat = lProperties.getFormat();
            String lPosition = lProperties.getPosition();
            String lCardPosition = lProperties.getCardPosition();
            String lFiller = lProperties.getAppendFiller();
            String lPadChar = lProperties.getPadChar();
            String lText = lProperties.getText();
            String lLength = lProperties.getLength();
            String lPropertyName = lProperties.getPropertyName();
            String lPropertyType = lProperties.getPropertyType();
            String lName = lProperties.getName();
            String lFormatString = lProperties.getFormatString();
            String lConvertWildcard = lProperties.getConvertWildcard();

            if (lName != null)
            {
                lElement.setAttribute(FieldMappingProperties.NAME, lName);
            }

            if (lAssociationType != null)
            {
                lElement.setAttribute(FieldMappingProperties.ASSOCIATIONTYPE, lAssociationType);
            }

            if (lDataItemName != null)
            {
                lElement.setAttribute(FieldMappingProperties.DATAITEMNAME, lDataItemName);
            }
            if (lParmIndex != null)
            {
                lElement.setAttribute(FieldMappingProperties.PARMINDEX, lParmIndex);
            }
            if (lFormat != null)
            {
                lElement.setAttribute(FieldMappingProperties.FORMAT, lFormat);
            }
            if (lPosition != null)
            {
                lElement.setAttribute(FieldMappingProperties.POSITION, lPosition);
            }

            if (lCardPosition != null)
            {
                lElement.setAttribute(FieldMappingProperties.CARDPOSITION, lCardPosition);
            }

            if (lFiller != null)
            {
                lElement.setAttribute(FieldMappingProperties.APPENDFILLER, lFiller);
            }
            if (lPadChar != null)
            {
                lElement.setAttribute(FieldMappingProperties.PADCHARACTER, lPadChar);
            }
            if (lLength != null)
            {
                lElement.setAttribute(FieldMappingProperties.LENGTH, lLength);
            }
            if (lText != null)
            {
                lElement.setAttribute(FieldMappingProperties.TEXT, lText);
            }
            if (lPropertyName != null)
            {
                lElement.setAttribute(FieldMappingProperties.PROPERTYNAME, lPropertyName);
            }
            if (lPropertyType != null)
            {
                lElement.setAttribute(FieldMappingProperties.PROPERTYTYPE, lPropertyType);
            }

            if (lFormatString != null)
            {
                lElement.setAttribute(FieldMappingProperties.FORMATSTRING, lFormatString);
            }

            if (lConvertWildcard != null)
            {
                lElement.setAttribute(FieldMappingProperties.CONVERTWILDCARD, lConvertWildcard);
            }

        }
    }

}
