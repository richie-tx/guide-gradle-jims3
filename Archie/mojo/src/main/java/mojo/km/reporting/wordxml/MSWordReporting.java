/*
 * Created on Jul 22, 2005
 *
 */
package mojo.km.reporting.wordxml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.naming.ReportingConstants;
import mojo.km.reporting.IReport;

import mojo.km.utilities.Reflection;
import mojo.km.utilities.TextUtil;

/**
 * 
 * @author Eric Amundson & Jim Fisher
 */
public class MSWordReporting implements IReport
{
    static private final String LISTS_END_TAG = "</w:lists>";

    static private final String STYLES_BEGIN_TAG = "<w:styles>";

    static private final String LIST_BEGIN_TAG = "<w:list ";

    static private final String LIST_END_TAG = "</w:list>";

    static private final String LIST_DEF_BEGIN_TAG = "<w:listDef";

    static private final String ILST_TAG = "<w:ilst w:val=\"";

    static private final String BEGIN_PARAGRAPH_TAG = "<w:p>";

    static private final String BEGIN_PARAGRAPH_ATTR_TAG = "<w:p ";

    static private final String END_PARAGRAPH_TAG = "</w:p>";

    static private final String BEGIN_TEXT_TAG = "<w:t>";

    static private final String BEGIN_TABLE_ROW_TAG = "<w:tr>";

    static private final String END_TABLE_ROW_TAG = "</w:tr>";

    static private final String BEGIN_FIELD_DATA_TAG = "<w:fldData>";

    static private final String END_FIELD_DATA_TAG = "</w:fldData>";

    static private final String END_FONTS_TAG = "</w:fonts>";

    static private final String LISTS_BEGIN_TAG = "<w:lists>";

    static private final String LISTS_TAG = "<w:lists></w:lists>";

    static private final String STRING_TYPE = "String";

    static private final String LIST_TYPE = "List";

    static private final String TABLE_TYPE = "Table";

    static private final String MAP_TYPE = "Map";

    static private final String CHECKBOX_TYPE = "Checkbox";

    static private final String CHECKBOX_FALSE = BEGIN_FIELD_DATA_TAG
            + "/////2UAAAAUAAYAQwBoAGUAYwBrADEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + END_FIELD_DATA_TAG;

    static private final String CHECKBOX_TRUE = BEGIN_FIELD_DATA_TAG
            + "/////2UAAAAUAAYAQwBoAGUAYwBrADEAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + END_FIELD_DATA_TAG;

    private final int BUFFER_SIZE = 1024;

    static private final String MS_SCHEMA_NS = "http://schemas.microsoft.com/office/word/2003/wordml";

    static private final String AML_SCHEMA_NS = "http://schemas.microsoft.com/aml/2001/core";

    static private final Namespace msSchemaNS = Namespace.getNamespace("w", MS_SCHEMA_NS);

    static private final Namespace amlSchemaNS = Namespace.getNamespace("aml", AML_SCHEMA_NS);

    private String templateName;

    static private final String LIST_DEF_ID = "[LISTDEFID]";

    static private final String LIST_ITEM_CHAR = "[LISTITEMCHAR]";

    static private final String LIST_OBJ_ID = "[LISTOBJID]";

    static private final String LIST_COUNTER = "[LISTCOUNTER]";

    static private final String PERIOD_CHAR = ".";

    static private final String BULLET_LIST_DEF = "<w:listDef w:listDefId=\"[LISTDEFID]\"><w:lsid w:val=\"22677608\"/><w:plt w:val=\"Multilevel\"/><w:tmpl w:val=\"B6206630\"/><w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:nfc w:val=\"23\"/><w:lvlText w:val=\"[LISTITEMCHAR]\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"360\"/></w:tabs><w:ind w:left=\"360\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Symbol\" w:h-ansi=\"Symbol\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"1\"><w:start w:val=\"3\"/><w:lvlText w:val=\"%1.%2\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"1110\"/></w:tabs><w:ind w:left=\"1110\" w:hanging=\"390\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"2\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"2160\"/></w:tabs><w:ind w:left=\"2160\" w:hanging=\"720\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"3\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"2880\"/></w:tabs><w:ind w:left=\"2880\" w:hanging=\"720\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"4\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4.%5\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"3960\"/></w:tabs><w:ind w:left=\"3960\" w:hanging=\"1080\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"5\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4.%5.%6\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"4680\"/></w:tabs><w:ind w:left=\"4680\" w:hanging=\"1080\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"6\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4.%5.%6.%7\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"5760\"/></w:tabs><w:ind w:left=\"5760\" w:hanging=\"1440\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"7\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4.%5.%6.%7.%8\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"6480\"/></w:tabs><w:ind w:left=\"6480\" w:hanging=\"1440\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl><w:lvl w:ilvl=\"8\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.%2.%3.%4.%5.%6.%7.%8.%9\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"7200\"/></w:tabs><w:ind w:left=\"7200\" w:hanging=\"1440\"/></w:pPr><w:rPr><w:rFonts w:cs=\"Arial\" w:hint=\"default\"/></w:rPr></w:lvl></w:listDef>";

    static private final String NUMERIC_LIST_DEF_1 = "<w:listDef w:listDefId=\"[LISTDEFID]\"><w:lsid w:val=\"2B324C66\"/><w:plt w:val=\"HybridMultilevel\"/><w:tmpl w:val=\"3286AF54\"/><w:lvl w:ilvl=\"0\" w:tplc=\"0409000F\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"1080\"/></w:tabs><w:ind w:left=\"1080\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"1\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%2.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"1800\"/></w:tabs><w:ind w:left=\"1800\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"2\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%3.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"2520\"/></w:tabs><w:ind w:left=\"2520\" w:hanging=\"180\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"3\" w:tplc=\"0409000F\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%4.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"3240\"/></w:tabs><w:ind w:left=\"3240\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"4\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%5.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"3960\"/></w:tabs><w:ind w:left=\"3960\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"5\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%6.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"4680\"/></w:tabs><w:ind w:left=\"4680\" w:hanging=\"180\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"6\" w:tplc=\"0409000F\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%7.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"5400\"/></w:tabs><w:ind w:left=\"5400\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"7\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%8.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"6120\"/></w:tabs><w:ind w:left=\"6120\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"8\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%9.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"6840\"/></w:tabs><w:ind w:left=\"6840\" w:hanging=\"180\"/></w:pPr></w:lvl></w:listDef>";

    static private final String NUMERIC_LIST_DEF_SEARCH = "<w:lsid w:val=\"2B324C66\"/><w:plt w:val=\"HybridMultilevel\"/><w:tmpl w:val=\"3286AF54\"/><w:lvl w:ilvl=\"0\" w:tplc=\"0409000F\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%1.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"1080\"/></w:tabs><w:ind w:left=\"1080\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"1\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%2.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"1800\"/></w:tabs><w:ind w:left=\"1800\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"2\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%3.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"2520\"/></w:tabs><w:ind w:left=\"2520\" w:hanging=\"180\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"3\" w:tplc=\"0409000F\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%4.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"3240\"/></w:tabs><w:ind w:left=\"3240\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"4\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%5.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"3960\"/></w:tabs><w:ind w:left=\"3960\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"5\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%6.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"4680\"/></w:tabs><w:ind w:left=\"4680\" w:hanging=\"180\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"6\" w:tplc=\"0409000F\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:lvlText w:val=\"%7.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"5400\"/></w:tabs><w:ind w:left=\"5400\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"7\" w:tplc=\"04090019\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"4\"/><w:lvlText w:val=\"%8.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"6120\"/></w:tabs><w:ind w:left=\"6120\" w:hanging=\"360\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"8\" w:tplc=\"0409001B\" w:tentative=\"on\"><w:start w:val=\"1\"/><w:nfc w:val=\"2\"/><w:lvlText w:val=\"%9.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:tabs><w:tab w:val=\"list\" w:pos=\"6840\"/></w:tabs><w:ind w:left=\"6840\" w:hanging=\"180\"/></w:pPr></w:lvl>";

    static private final String LIST_ITEM_DEF = "<w:list w:ilfo=\"[LISTOBJID]\"><w:ilst w:val=\"[LISTDEFID]\"/></w:list>";

    static private final String BEFORE_LIST_ITEM = "<w:p><w:pPr><w:pStyle w:val=\"Standard\"/><w:listPr><w:ilvl w:val=\"0\"/><w:ilfo w:val=\"[LISTOBJID]\"/><wx:t wx:val=\"[LISTCOUNTER]\" wx:wTabBefore=\"1080\" wx:wTabAfter=\"240\"/><wx:font wx:val=\"Symbol\"/></w:listPr></w:pPr><w:r><w:t>";

    static private final String AFTER_LIST_ITEM = "</w:t><w:br/></w:r></w:p>";

    static private final String FONTS_ELEMENT = "fonts";

    static private final String LIST_ELEMENT = "list";

    static private final String LISTS_ELEMENT = "lists";

    static private final String LIST_DEF_ELEMENT = "listDef";

    static private final String LSID_ELEMENT = "lsid";

    static private final String VAL_ATTRIBUTE = "val";

    static private final String LIST_DEF_ID_ATTRIBUTE = "listDefId";

    static private final String ILFO_ELEMENT = "ilfo";

    static private final String ILFO_ATTRIBUTE = "ilfo";

    static private final String END_LIST_DEF_TAG = "</w:listDef>";

    private int listCounter;

    /** Creates a new instance of MSWordReporting */
    public MSWordReporting()
    {
        listCounter = 0;
    }

    private Document getDocument(URL url, StringBuffer msDoc) throws JDOMException, IOException
    {
        InputStream is = url.openStream();

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(is);

        String docStr = msDoc.toString();
        boolean found = false;
        Element root = doc.getRootElement();

        Element listsElement = root.getChild(LISTS_ELEMENT, msSchemaNS);

        if (listsElement == null)
        {
            int endFontIndex = docStr.indexOf(END_FONTS_TAG) + END_FONTS_TAG.length();
            msDoc.insert(endFontIndex, LISTS_TAG);

            is = new ByteArrayInputStream(msDoc.toString().getBytes());
            doc = builder.build(is);
        }

        return doc;
    }

    public byte[] getByteOutput(ReportRequestEvent event) throws Exception
    {
        EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(event.getReportName());
        if (eProps == null)
        {
            throw new Exception("Report mapping configuration not found for: " + event.getReportName());
        }

        String resourceName = eProps.getEntity();

        URL url = this.getResource(resourceName);

        // Used for modifying the template for inserting business data
        StringBuffer msDoc = this.loadTemplate(url);

        // Parse the WordML into a DOM object
        Document document = this.getDocument(url, msDoc);

        this.setObjects(eProps, event, msDoc, document);

        return msDoc.toString().getBytes();
    }

    /**
     * @param event
     * @param msDoc
     * @param document
     */
    private void setObjects(EntityMappingProperties eProps, ReportRequestEvent event, StringBuffer msDoc,
            Document document) throws SecurityException, NoSuchMethodException, IOException
    {
        Set tableSet = new HashSet();

        Iterator i = event.getDataObjects();

        while (i.hasNext())
        {
            Object dataBean = i.next();
            String dObjName = dataBean.getClass().getName();
            EventQueryProperties qProps = eProps.getEventQueryProperties(dObjName);
            if (qProps != null)
            {
                Iterator fProps = qProps.getFieldsIterator();
                while (fProps.hasNext())
                {
                    FieldMappingProperties fProp = (FieldMappingProperties) fProps.next();

                    // If propertyName starts with 'Table' then render a Table
                    // collection
                    // otherwise, treat property as normal field data

                    if (fProp.getPropertyType().startsWith(TABLE_TYPE))
                    {
                        String tableName = fProp.getPropertyName();
                        tableName = tableName.substring(0, tableName.indexOf(PERIOD_CHAR) + 1);

                        if (tableSet.contains(tableName) == false)
                        {
                            tableSet.add(tableName);

                            Collection fields = this.gatherFields(tableName, qProps, fProp);

                            this.renderTable(dataBean, tableName, fields, msDoc);
                        }
                    }
                    else if (fProp.getPropertyType().startsWith(MAP_TYPE))
                    {
                        Object value = this.getKeyedValue(fProp, dataBean);
                        this.setString(value, fProp, msDoc);
                    }
                    else
                    {
                        Object value = Reflection.invokeAccessorMethod(dataBean, fProp.getPropertyName());

                        if (STRING_TYPE.equalsIgnoreCase(fProp.getPropertyType()))
                        {
                            this.setString(value, fProp, msDoc);
                        }
                        else if (LIST_TYPE.equalsIgnoreCase(fProp.getPropertyType()))
                        {
                            this.setList(value, fProp, msDoc, document);
                        }
                        else if (CHECKBOX_TYPE.equalsIgnoreCase(fProp.getPropertyType()))
                        {
                            this.setCheckbox(value, fProp, msDoc, document);
                        }
                    }
                }
            }
        }
    }

    /**
     * @return
     */
    private Object getKeyedValue(FieldMappingProperties fProp, Object dataBean) throws SecurityException,
            NoSuchMethodException
    {
        String propertyName = fProp.getPropertyName();
        String key = propertyName.substring(propertyName.indexOf(PERIOD_CHAR) + 1, propertyName.length());
        propertyName = propertyName.substring(0, propertyName.indexOf(PERIOD_CHAR));
        char firstChar = propertyName.charAt(0);
        char upperFirstChar = Character.toUpperCase(firstChar);
        propertyName = "get" + upperFirstChar + propertyName.substring(1, propertyName.length());

        Class[] parmTypes =
        { String.class };
        Method method = dataBean.getClass().getMethod(propertyName, parmTypes);

        Object value = Reflection.invokeMethod(method, dataBean, key);
        return value;
    }

    /**
     * @param value
     * @param fProp
     * @param msDoc
     */
    private void setCheckbox(Object value, FieldMappingProperties fProp, StringBuffer msDoc, Document doc)
    {
        boolean booleanValue = false;

        if (value != null)
        {
            booleanValue = "true".equalsIgnoreCase(value.toString());
        }

        String searchStr = "w:type=\"Word.Bookmark.Start\" w:name=\"" + fProp.getName() + "\"";

        String docStr = msDoc.toString();

        int beginIndex = docStr.indexOf(searchStr);

        if (beginIndex != -1)
        {
            beginIndex = docStr.indexOf(BEGIN_PARAGRAPH_TAG, beginIndex);
            int endIndex = docStr.indexOf(END_PARAGRAPH_TAG, beginIndex) + END_PARAGRAPH_TAG.length();
            String checkboxBody = docStr.substring(beginIndex, endIndex);

            beginIndex = docStr.indexOf(BEGIN_FIELD_DATA_TAG, beginIndex);
            endIndex = docStr.indexOf(END_FIELD_DATA_TAG, beginIndex) + END_FIELD_DATA_TAG.length();
            String fieldData = docStr.substring(beginIndex, endIndex);

            String tempCheckboxBody = "";

            if (booleanValue == true)
            {
                tempCheckboxBody = TextUtil.searchAndReplace(checkboxBody, fieldData, CHECKBOX_TRUE);
            }
            else
            {
                tempCheckboxBody = TextUtil.searchAndReplace(checkboxBody, fieldData, CHECKBOX_FALSE);
            }

            TextUtil.searchAndReplace(msDoc, checkboxBody, tempCheckboxBody);
        }
    }

    /**
     * @param dataBean
     * @param fields
     */
    private void renderTable(Object dataBean, String tableName, Collection fieldConfig, StringBuffer msDoc)
    {
        String rowTemplate = this.getRowString(msDoc, fieldConfig);

        if (rowTemplate != null)
        {
            Collection fields = this.getRowFields(rowTemplate, fieldConfig);

            tableName = tableName.substring(0, tableName.length() - 1);

            Collection records = (Collection) Reflection.invokeAccessorMethod(dataBean, tableName);

            Iterator i = records.iterator();

            StringBuffer recordBuffer = new StringBuffer();

            while (i.hasNext())
            {
                Object record = i.next();

                String rowStr = rowTemplate;

                Iterator f = fields.iterator();
                while (f.hasNext())
                {
                    FieldMappingProperties fProp = (FieldMappingProperties) f.next();
                    String property = fProp.getPropertyName();
                    property = property.substring(property.indexOf(PERIOD_CHAR) + 1, property.length());
                    Object obj = Reflection.invokeAccessorMethod(record, property);
                    String stringValue = this.getStringValue(fProp, obj);
                    rowStr = TextUtil.searchAndReplace(rowStr, fProp.getName(), stringValue);
                }
                recordBuffer.append(rowStr);
            }

            TextUtil.searchAndReplace(msDoc, rowTemplate, recordBuffer.toString());
        }
    }

    /**
     * Converts an object to String, sets default value if value object is null,
     * and converts any special characters.
     * 
     * @param fProp
     *            field mapping properties
     * @param value
     *            value object to convert to String
     * @return
     */
    private String getStringValue(FieldMappingProperties fProp, Object value)
    {
        String stringValue;
        if (value == null)
        {
            stringValue = fProp.getText();
        }
        else
        {
            stringValue = value.toString();
        }
        stringValue = this.replaceXMLSpecial(stringValue);
        return stringValue;
    }

    /**
     * @param rowStr
     * @param fields
     * @return
     */
    private Collection getRowFields(String rowStr, Collection fieldConfig)
    {
        Collection fields = new ArrayList();

        Iterator i = fieldConfig.iterator();
        while (i.hasNext())
        {
            FieldMappingProperties field = (FieldMappingProperties) i.next();
            if (rowStr.indexOf(field.getName()) != -1)
            {
                fields.add(field);
            }
        }

        return fields;
    }

    /**
     * @param msDoc
     * @param fields
     * @return
     */
    private String getRowString(StringBuffer msDoc, Collection fieldConfig)
    {
        boolean done = false;
        String docStr = msDoc.toString();

        String rowStr = null;

        Iterator k = fieldConfig.iterator();
        while (k.hasNext() && done == false)
        {
            FieldMappingProperties fProp = (FieldMappingProperties) k.next();
            String name = fProp.getName();
            int beginIndex = docStr.indexOf(name);
            if (beginIndex != -1)
            {
                done = true;
                beginIndex = TextUtil.reverseIndexOf(msDoc, BEGIN_TABLE_ROW_TAG, beginIndex);
                int endIndex = docStr.indexOf(END_TABLE_ROW_TAG, beginIndex);
                rowStr = msDoc.substring(beginIndex, endIndex + END_TABLE_ROW_TAG.length());
            }
        }

        return rowStr;
    }

    /**
     * @param qProps
     * @param fProp
     * @return
     */
    private Collection gatherFields(String tableName, EventQueryProperties qProps, FieldMappingProperties aFieldProp)
    {
        Iterator fProps = qProps.getFieldsIterator();

        Collection fields = new ArrayList();

        while (fProps.hasNext())
        {
            FieldMappingProperties fProp = (FieldMappingProperties) fProps.next();
            if (fProp.getName().indexOf(tableName) != -1)
            {
                fields.add(fProp);
            }
        }
        return fields;
    }

    /**
     * Converts a DOM document to a StringBuffer instance
     * 
     * @param doc
     * @param msDoc
     * @throws IOException
     */
    private void documentToStringBuffer(Document doc, StringBuffer msDoc) throws IOException
    {
        XMLOutputter outputter = new XMLOutputter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        outputter.output(doc, baos);
        String docStr = new String(baos.toByteArray());
        msDoc.replace(0, msDoc.length(), docStr);
    }

    /**
     * Creates a numered list in WordML format
     * 
     * @param dataBean
     * @param fProp
     * @param msDoc
     */
    private void setList(Object value, FieldMappingProperties fProp, StringBuffer msDoc, Document doc)
            throws IOException
    {
        String listId = this.setUpListDef(msDoc);

        String beforeListItem = TextUtil.searchAndReplace(BEFORE_LIST_ITEM, LIST_OBJ_ID, listId);

        listCounter++;

        String listCounterString = String.valueOf(listCounter);
        beforeListItem = TextUtil.searchAndReplace(beforeListItem, LIST_COUNTER, listCounterString);

        if (value == null || value.equals(""))
        {
            value = fProp.getText();
            value = this.replaceXMLSpecial(value.toString());
            TextUtil.searchAndReplace(msDoc, fProp.getName(), value.toString());
        }
        else
        {
            String msDocStr = msDoc.toString();
            int beginIndex = msDocStr.indexOf(fProp.getName());

            if (beginIndex != -1)
            {
                //this.replaceFieldValue(msDoc, beginIndex, fProp.getName());

                StringBuffer replacementBuffer = new StringBuffer();
                Iterator i = ((Collection) value).iterator();
                while (i.hasNext())
                {
                    replacementBuffer.append(beforeListItem);
                    Object obj = i.next();
                    String stringValue = this.getStringValue(fProp, obj);
                    replacementBuffer.append(stringValue);
                    replacementBuffer.append(AFTER_LIST_ITEM);
                }
                value = replacementBuffer.toString();
                TextUtil.replace(msDoc, fProp.getName(), (String) value, beginIndex);
            }
        }
    }

    /**
     * @param msDoc
     * @param beginIndex
     * @param name
     */
    private void replaceFieldValue(StringBuffer aBuffer, int beginIndex, String aName)
    {
        String retVal;

        int beginIndexWithoutAttr = TextUtil.reverseIndexOf(aBuffer, BEGIN_PARAGRAPH_TAG, beginIndex);

        int beginIndexWithAttr = TextUtil.reverseIndexOf(aBuffer, BEGIN_PARAGRAPH_ATTR_TAG, beginIndex);

        if (beginIndexWithAttr < beginIndexWithoutAttr)
        {
            beginIndex = TextUtil.reverseIndexOf(aBuffer, BEGIN_PARAGRAPH_TAG, beginIndexWithoutAttr);
            int endIndex = aBuffer.indexOf(END_PARAGRAPH_TAG, beginIndexWithoutAttr) + END_PARAGRAPH_TAG.length();

            aBuffer.replace(beginIndex, endIndex, aName);
        }
        else if (beginIndexWithAttr > beginIndexWithoutAttr)
        {
            beginIndex = TextUtil.reverseIndexOf(aBuffer, BEGIN_PARAGRAPH_ATTR_TAG, beginIndexWithAttr);
            int endIndex = aBuffer.indexOf(END_PARAGRAPH_TAG, beginIndexWithAttr) + END_PARAGRAPH_TAG.length();

            aBuffer.replace(beginIndex, endIndex, aName);
        }

    }

    private String getAttributeValue(String aBuffer, int aStartIndex, String anAttribute)
    {
        aStartIndex = aBuffer.indexOf(anAttribute, aStartIndex);
        aStartIndex = aBuffer.indexOf("\"", aStartIndex);
        int endIndex = aBuffer.indexOf("\"", aStartIndex + 1);
        String value = aBuffer.substring(aStartIndex + 1, endIndex);
        return value;
    }

    private String setUpListDef(StringBuffer msDoc)
    {
        String docString = msDoc.toString();
        int numericListIndex = docString.indexOf(MSWordReporting.NUMERIC_LIST_DEF_SEARCH);

        String ilfoValue = null;

        if (numericListIndex >= 0)
        {
            // listDef exists

            // constant to go back 30 characters in the docString to get parent
            // element attribute value
            final int ELEMENT_OFFSET = 30;
            int listDefIdIndex = numericListIndex - ELEMENT_OFFSET;
            String listDefIdValue = this.getAttributeValue(docString, listDefIdIndex,
                    MSWordReporting.LIST_DEF_ID_ATTRIBUTE);

            String ilstValue = MSWordReporting.ILST_TAG + listDefIdValue + "\"/>";

            int ilfoIndex = docString.indexOf(ilstValue);
            ilfoIndex = docString.indexOf(MSWordReporting.ILST_TAG, ilfoIndex - ELEMENT_OFFSET);
            ilfoIndex -= ELEMENT_OFFSET;
            ilfoValue = this.getAttributeValue(docString, ilfoIndex, MSWordReporting.ILFO_ATTRIBUTE);
        }
        else
        {
            // listDef does not exist, create a new one
            int listDefIndex = docString.lastIndexOf(LIST_DEF_BEGIN_TAG);
            int maxListDefIdValue = 0;
            if (listDefIndex != -1)
            {
                String maxListDefId = this.getAttributeValue(docString, listDefIndex,
                        MSWordReporting.LIST_DEF_ID_ATTRIBUTE);
                maxListDefIdValue = Integer.parseInt(maxListDefId);
                maxListDefIdValue++;
            }

            int ilfoIndex = docString.lastIndexOf(LIST_BEGIN_TAG);
            int maxIlfoValue = 1;
            if (ilfoIndex != -1)
            {
                String maxIlfo = this.getAttributeValue(docString, ilfoIndex, MSWordReporting.ILFO_ATTRIBUTE);
                maxIlfoValue = Integer.parseInt(maxIlfo);
                maxIlfoValue++;
            }

            ilfoValue = String.valueOf(maxIlfoValue);

            String listElement = "<w:list w:ilfo=\"" + ilfoValue + "\"><w:ilst w:val=\"" + maxListDefIdValue
                    + "\"/></w:list>";

            int listsIndex = docString.indexOf(MSWordReporting.LISTS_BEGIN_TAG);
            if (listsIndex == -1)
            {
                String listDefElement = "<w:lists><w:listDef w:listDefId=\"0\">"
                        + MSWordReporting.NUMERIC_LIST_DEF_SEARCH + "</w:listDef>";
                int stylesIndex = docString.indexOf(STYLES_BEGIN_TAG);
                msDoc.insert(stylesIndex, listDefElement);
                int listIndex = stylesIndex + listDefElement.length();
                msDoc.insert(listIndex, listElement);
            }
            else
            {
                String listDefElement = "<w:listDef w:listDefId=\"" + maxListDefIdValue + "\">"
                        + MSWordReporting.NUMERIC_LIST_DEF_SEARCH + "</w:listDef>";
                listsIndex = docString.indexOf(LISTS_BEGIN_TAG);
                listsIndex += LISTS_BEGIN_TAG.length();
                msDoc.insert(listsIndex, listDefElement);
                docString = msDoc.toString();
                int listIndex = docString.indexOf(LISTS_END_TAG);
                msDoc.insert(listIndex, listElement);
            }
        }

        return ilfoValue;
    }

    /**
     * @param msDoc
     */
    private String setUpListDef(StringBuffer msDoc, Document doc) throws IOException
    {
        String docStr = msDoc.toString();
        boolean found = false;
        Element root = doc.getRootElement();

        Element listsElement = root.getChild(LISTS_ELEMENT, msSchemaNS);

        List listDefs = listsElement.getChildren(LIST_DEF_ELEMENT, msSchemaNS);

        if (listDefs == null)
        {
            listDefs = new ArrayList();
        }

        Iterator i = listDefs.iterator();

        int listDefId = -1;

        Element listDefElement = null;
        Element lsidElement = null;
        String lsidValue = null;
        //Attribute listDefAttr = null;

        while (i.hasNext())
        {
            listDefElement = (Element) i.next();
            lsidElement = listDefElement.getChild(LSID_ELEMENT, msSchemaNS);

            lsidValue = lsidElement.getAttributeValue(VAL_ATTRIBUTE, msSchemaNS);
            if (lsidValue == null)
            {
                lsidValue = lsidElement.getAttributeValue(VAL_ATTRIBUTE);
            }

            if (NUMERIC_LIST_DEF_SEARCH.equals(lsidValue))
            {
                found = true;
            }

            /*
             * listDefAttr = listDefElement.getAttribute(LIST_DEF_ID_ATTRIBUTE,
             * msSchemaNS); if (listDefAttr == null) { listDefAttr =
             * listDefElement.getAttribute(LIST_DEF_ID_ATTRIBUTE); }
             */

            //int ilfoValue = Integer.parseInt(listDefAttr.getValue());
        }

        Iterator lists = listsElement.getChildren(LIST_ELEMENT, msSchemaNS).iterator();

        int listId = 0;
        int ilfoValue = -1;
        Attribute listAttr = null;
        Element listElement = null;

        while (lists.hasNext())
        {
            listElement = (Element) lists.next();

            listAttr = listElement.getAttribute(ILFO_ELEMENT, msSchemaNS);
            if (listAttr == null)
            {
                listAttr = listElement.getAttribute(ILFO_ELEMENT);
            }

            ilfoValue = Integer.parseInt(listAttr.getValue());

            if (ilfoValue > listId)
            {
                listId = ilfoValue;
            }
        }

        if (found == false)
        {
            listDefId++;
            listId++;
            String listDefIdStr = String.valueOf(listDefId);
            String listIdStr = String.valueOf(listId);
            this.insertListItemDef(msDoc, NUMERIC_LIST_DEF_1, listDefIdStr, listIdStr, doc, null);
        }

        return String.valueOf(listId);
    }

    /**
     * @param listDefId
     * @param doc
     */
    private void insertListItemDef(StringBuffer msDoc, String itemDef, String listDefId, String listId, Document doc,
            String bulletText)
    {
        itemDef = TextUtil.searchAndReplace(itemDef, LIST_DEF_ID, listDefId);
        if (bulletText != null)
        {
            itemDef = TextUtil.searchAndReplace(itemDef, LIST_ITEM_CHAR, bulletText);
        }

        String docString = msDoc.toString();

        int beginIndex = docString.lastIndexOf(END_LIST_DEF_TAG);

        if (beginIndex == -1)
        {
            beginIndex = docString.lastIndexOf(LISTS_BEGIN_TAG);
            beginIndex += LISTS_BEGIN_TAG.length();
        }
        else
        {
            beginIndex += END_LIST_DEF_TAG.length();
        }

        msDoc.insert(beginIndex, itemDef);

        String listEntry = TextUtil.searchAndReplace(LIST_ITEM_DEF, LIST_OBJ_ID, listId);
        listEntry = TextUtil.searchAndReplace(listEntry, LIST_DEF_ID, listDefId);

        docString = msDoc.toString();

        beginIndex = docString.lastIndexOf(LIST_END_TAG);
        if (beginIndex == -1)
        {
            beginIndex = docString.lastIndexOf(END_LIST_DEF_TAG);
            beginIndex += END_LIST_DEF_TAG.length();
        }
        else
        {
            beginIndex += LIST_END_TAG.length();
        }

        msDoc.insert(beginIndex, listEntry);

    }

    private void setString(Object value, FieldMappingProperties fProp, StringBuffer msDoc)
    {
        String docStr = msDoc.toString();

        int beginIndex = docStr.indexOf(fProp.getName());

        if (beginIndex != -1)
        {
            String formatting = this.getReplacedStringFormating(msDoc, beginIndex);

            String stringValue = this.getStringValue(fProp, value);

            String formattedReplacement = TextUtil.searchAndReplace(stringValue, "\r\n\r\n", formatting);

            TextUtil.searchAndReplace(msDoc, fProp.getName(), formattedReplacement);
        }
    }

    public String getContentType()
    {
        return ReportingConstants.TEXT_MIME_TYPE;
    }

    public String getTemplate()
    {
        return this.templateName;
    }

    private URL getResource(String resourceName) throws IOException
    {
        URL url = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
        {
            loader = MSWordReporting.class.getClassLoader();
        }
        Enumeration resources = loader.getResources(resourceName);

        if (resources.hasMoreElements())
        {
            url = (URL) resources.nextElement();
            if (resources.hasMoreElements())
            {
                System.out.println("*** WARNING ***");
                System.out.println("There are multiple " + resourceName
                        + " files in classpath at the following locations:");
                System.out.println(url.getPath());
                while (resources.hasMoreElements())
                {
                    System.out.println(((URL) resources.nextElement()).getPath());
                }
                System.out.println("-- Only the first one will be read.");
            }
            System.out.println("Loading " + resourceName + " from: " + url.getPath());
            setTemplate(resourceName.substring(0, resourceName.indexOf(".xml")));
        }
        else
        {
            // TODO Refine exception class choice
            throw new IOException("Could not find the resource: " + resourceName + " in the classpath");
        }
        return url;
    }

    private StringBuffer loadTemplate(URL url) throws Exception
    {
        String fileString = null;

        InputStream is = url.openStream();

        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);

        byte[] bytes = new byte[BUFFER_SIZE];

        for (int size = bis.read(bytes); size > -1; size = bis.read(bytes))
        {
            bos.write(bytes, 0, size);
            bytes = new byte[BUFFER_SIZE];
        }
        bos.flush();
        fileString = new String(baos.toByteArray());
        bos.close();
        baos.close();
        is.close();
        bis.close();

        return new StringBuffer(fileString);
    }

    private String setParagraphBreaks(String in)
    {
        // TODO What is the purpose for this behavior?
        String out = in;
        out = new String(TextUtil
                .searchAndReplace(in.getBytes(), "\r\n\r\n", "</w:t></w:r></w:p><w:p/><w:p><w:r><w:t>"));

        return out;
    }

    private String getReplacedStringFormating(StringBuffer buffer, int beginIndex)
    {
        String retVal;
        int beginIndexWithoutAttr = TextUtil.reverseIndexOf(buffer, BEGIN_PARAGRAPH_TAG, beginIndex);

        int beginIndexWithAttr = TextUtil.reverseIndexOf(buffer, BEGIN_PARAGRAPH_ATTR_TAG, beginIndex);

        if (beginIndexWithAttr < beginIndexWithoutAttr)
        {
            int endIndex = buffer.toString().indexOf(BEGIN_TEXT_TAG, beginIndexWithoutAttr) + 5;
            retVal = buffer.substring(beginIndexWithoutAttr, endIndex);
        }
        else if (beginIndexWithAttr > beginIndexWithoutAttr)
        {
            int endIndex = buffer.toString().indexOf(BEGIN_TEXT_TAG, beginIndexWithAttr) + 5;
            retVal = buffer.substring(beginIndexWithAttr, endIndex);
        }
        else
        {
            retVal = buffer.toString();
        }
        return retVal;
    }

    private String replaceXMLSpecial(String string)
    {
        StringBuffer buffer = new StringBuffer(string);
        TextUtil.searchAndReplace(buffer, "&", "&amp;");
        TextUtil.searchAndReplace(buffer, "'", "&apos;");
        TextUtil.searchAndReplace(buffer, "\"", "&quot;");
        TextUtil.searchAndReplace(buffer, "<", "&lt;");
        TextUtil.searchAndReplace(buffer, ">", "&gt;");
        return buffer.toString();
    }

    public void setTemplate(String name)
    {
        this.templateName = name;
    }
}
