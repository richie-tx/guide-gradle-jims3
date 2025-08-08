/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.util.HashMap;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.naming.MappingConstants;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 *  
 */
public class EventQueryPropertiesM204Validator extends AbstractEventQueryPropertiesValidator
{
    private static final String DERIVED_COLUMN = "DERIVED";

    private String metaDataKey;

    public EventQueryPropertiesM204Validator(EventQueryProperties aProps)
    {
        super(aProps);
    }

    public void accept(IMappingValidatorVisitor aVisitor)
    {
        this.visitor = (PersistenceMappingValidator) aVisitor;

        this.validate();

        // validate parm and field mappings
        aVisitor.visit(this);
    }

    private void setMetaData()
    {
        int count = 0;

        String source;
        if (props.getSource() == null)
        {
            source = MappingConstants.NONE_UPPER;
        }
        else
        {
            source = props.getSource().trim();
        }

        String filename = props.getFileName();

        metaDataKey = filename + source;

        Map metaData = null;

        try
        {
            System.out.println("Get metadata for: " + metaDataKey);
            metaData = visitor.getMetaData(metaDataKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (metaData == null)
        {
            metaData = new HashMap();

            String connectionName = this.props.getConnectionName();
            IConnection connection = TransactionManager.getInstance().getConnection(connectionName);

            // validate JDBC queries
            if (connection != null)
            {
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                    Connection m204Connection = (Connection) connection.getResource();

                    // j2metadata
                    // rpc.returns;function=r;rpc=j2jjsms;rectype=juvenile;eof

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("J2METADATA RPC.RETURNS;FUNCTION=R;JIMS2.LCUSER=null;rpc=");
                    buffer.append(filename);
                    buffer.append(";RECTYPE=");
                    buffer.append(source);
                    buffer.append(";EOF;");
                    String query = buffer.toString();
                    System.out.println("m204 metadata: " + query);

                    stmt = m204Connection.createStatement();

                    rs = stmt.executeQuery(query);

                    while (rs.next())
                    {
                        String record = rs.getString(1);
                        int commaPos = record.indexOf(",");
                        if (commaPos > -1)
                        {
                            String pos = record.substring(0, commaPos);
                            String columnName = record.substring(commaPos + 1);
                            metaData.put(pos, columnName);
                            count++;
                        }
                    }

                    // TODO Possibly send a null here
                    if (count > 0)
                    {
                        visitor.addMetaData(metaDataKey, metaData, new HashMap());
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (rs != null)
                    {
                        try
                        {
                            rs.close();
                            stmt.close();
                        }
                        catch (SQLException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    TransactionManager.releaseConnection(connectionName, connection);
                }
            }
        }
        else if (metaData.size() == 0)
        {
            AuditError error = new AuditError(MappingConstants.INVALID_SOURCE, AuditError.WARNING);
            StringBuffer buffer = new StringBuffer();
            buffer.append("could not find M204 metadata for: file='");
            buffer.append(filename);
            buffer.append("' source='");
            buffer.append(source);
            buffer.append("'");
            error.setMessage(buffer.toString());
            this.result.addError(error);
            this.setValidSource(false);
        }

    }

    /**
     * @return Returns the props.
     */
    public EventQueryProperties getProps()
    {
        return props;
    }

    public AuditResult getResult()
    {
        return this.result;
    }

    public void validate()
    {
        String unitName = visitor.getCompilationUnit().getMainType().getQualifiedName();
        System.out.println("VALIDATING: " + unitName + "::" + props.getParent());
        String name = props.getName();
        String mappingMethodName = props.getMappingMethodName();
        String source;
        if (props.getSource() == null)
        {
            source = MappingConstants.NONE_UPPER;
        }
        else
        {
            source = props.getSource().trim();
        }
        String connectionName = props.getConnectionName();

        StringBuffer msgBuffer = new StringBuffer();
        if (connectionName.equals(MappingConstants.M204))
        {
            msgBuffer.append(props.getFileName());
            msgBuffer.append("-->");
        }
        msgBuffer.append(source);

        msgBuffer.append(" (");
        msgBuffer.append(connectionName);
        msgBuffer.append(")");

        this.result.setMessage(msgBuffer.toString());

        // RULE: validate mapping class name
        Class mappingClazz = this.validateMappingClass(unitName);

        EntityMappingProperties eProps = props.getParent();

        Class clazz = null;
        if (this.isValidMapping() == true)
        {
            clazz = this.validateEntity(eProps.getEntity());
        }

        boolean noValidate = eProps.isBufferMapping() || eProps.isReportMapping() || props.isFileIOMapping()
                || isCacheMapping() || (isValidMapping() == false) || (isValidEntity() == false);

        if (noValidate == false)
        {
            // RULE: validate method
            this.validateMappingMethod();

            // RULE: validate source
            this.validateSource(unitName);

            // RULE: validate event name
            this.validateEventMapping(unitName);

            // RULE: validate connection name
            this.validateConnection(unitName);

            if (isValidSource() && isValidConnection())
            {
                // TODO RULE: validate ParmMappings

                // RULE: validate FieldMappings
                this.validateFieldMappings(clazz);
            }
        }
    }

    /**
     * @param positionInt
     * @param propertyType
     * @param type
     * @return
     */
    private void validateColumn(FieldMappingProperties fProps, Map metaData, int positionInt)
    {
        String columnName = fProps.getDataItemName();

        if (columnName.indexOf(DERIVED_COLUMN) > -1)
        {
            if (columnName.startsWith("MS."))
            {
                String tempColumn = "%" + columnName.substring(3, columnName.length());
                if (columnName.equals(tempColumn) == false)
                {
                    String propertyName = fProps.getPropertyName();
                    AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.INFO);
                    error.setMessage("missing field in meta data: " + fProps);
                    this.result.addError(error);
                }
            }
            else
            {
                String propertyName = fProps.getPropertyName();
                AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.INFO);
                error.setMessage("missing field in meta data: " + fProps);
                this.result.addError(error);
            }
        }
        else if (metaData.containsKey(fProps.getPosition()))
        {
            String metaColumnName = (String) metaData.get(fProps.getPosition());
            if (columnName.equals(metaColumnName) == false)
            {
                String propertyName = fProps.getPropertyName();
                AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.WARNING);
                StringBuffer buffer = new StringBuffer();
                buffer.append(MappingConstants.MISSING_COLUMN);
                buffer.append("(from M204 = ");
                buffer.append(metaColumnName);
                buffer.append("): ");
                buffer.append(fProps);
                error.setMessage(buffer.toString());
                this.result.addError(error);
            }
        }
        else if (positionInt < 500)
        {
            String propertyName = fProps.getPropertyName();
            AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.ERROR);
            error.setMessage("position (" + fProps.getPosition() + ") not in database for: " + fProps);
            this.result.addError(error);
        }
    }

    private void validateColumn(FieldMappingProperties fProps)
    {
        if (props.isFileIOMapping() == false && fProps.getPropertyTypeInt() == MappingConstants.INVALID_DATATYPE)
        {
            AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.ERROR);
            error.setMessage("invalid type mapping: " + fProps);
            this.result.addError(error);
        }
    }

    private void validateFieldMappings(Class clazz)
    {
        Set columns = new HashSet();
        Set positions = new HashSet();

        FieldMappingProperties oidProps = props.getFieldMap(MappingConstants.OID);
        if (oidProps == null)
        {
            AuditError error = new AuditError(MappingConstants.MISSING_FIELDS, AuditError.ERROR);
            error.setMessage("missing OID field mapping property");
            this.result.addError(error);
        }

        Iterator f = props.getFieldsIterator();

        if (f.hasNext() == false)
        {
            AuditError error = new AuditError(MappingConstants.MISSING_FIELDS, AuditError.ERROR);
            error.setMessage("missing field mapping properties: " + clazz.getName());
            this.result.addError(error);
        }
        else
        {
            Map metaData = visitor.getMetaData(metaDataKey);
            while (f.hasNext())
            {
                FieldMappingProperties fProps = (FieldMappingProperties) f.next();

                boolean noValidate = "pd.codetable.Code".equals(props.getParent().getEntity())
                        && "mojo.km.persistence.AttributeEvent".equals(props.getEventName())
                        && "codeTableName".equals(fProps.getName());

                if (noValidate == false)
                {

                    String index = fProps.getPosition();

                    String columnName = fProps.getDataItemName();

                    int positionInt = -1;

                    try
                    {
                        positionInt = Integer.parseInt(fProps.getPosition());
                    }
                    catch (NumberFormatException e)
                    {
                        // ignore exception
                    }

                    if (positionInt < 1)
                    {
                        String propertyName = fProps.getPropertyName();
                        AuditError error = new AuditError(MappingConstants.INVALID_FIELD_INDEX, AuditError.ERROR);
                        error.setMessage("invalid field index (field property=" + propertyName + "): "
                                + fProps.getPosition());
                        this.result.addError(error);
                    }

                    if (positions.add(fProps.getPosition()) == false)
                    {
                        String propertyName = fProps.getPropertyName();
                        String position = fProps.getPosition();
                        AuditError error = new AuditError(MappingConstants.DUP_FIELD_INDEX, AuditError.ERROR);
                        error.setMessage("duplicate position mapping (field property=" + propertyName + "): "
                                + position);
                        this.result.addError(error);
                    }

                    // RULE(info): check for dup columns
                    if (MappingConstants.NONE.equalsIgnoreCase(columnName) == false && columns.add(columnName) == false)
                    {
                        String propertyName = fProps.getPropertyName();
                        AuditError error = new AuditError(MappingConstants.DUP_COLUMN, AuditError.INFO);
                        error.setMessage("duplicate column (field property=" + propertyName + "): " + columnName);
                        this.result.addError(error);
                    }

                    this.validateProperty(clazz, fProps);

                    // RULE(error) validate database mapping
                    if (metaData != null && positionInt > -1)
                    {
                        this.validateColumn(fProps, metaData, positionInt);
                    }
                }
            }
        }
    }

    public void validateSource(String unitName)
    {
        super.validateSource(unitName);

        if (this.isValidSource())
        {
            this.setMetaData();
        }
    }
}
