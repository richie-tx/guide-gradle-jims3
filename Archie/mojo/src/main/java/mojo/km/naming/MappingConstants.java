/*
 * Created on May 25, 2006
 *
 */
package mojo.km.naming;

import mojo.km.config.PropertyBundleProperties;
import mojo.km.utilities.FileIOMapping;

/**
 * @author Jim Fisher
 *  
 */
public class MappingConstants
{   
    public static final int BLOB_DATATYPE = 11;

    public static final String BLOB_TYPE = "blob";

    public static final int BOOLEAN_DATATYPE = 9;

    public static final String BOOLEAN_TYPE = "boolean";

    public static final String BUFFER_MAPPING_CONTEXT = "BUFFER_MAPPING::";

    public static final String BYTE_ARRAY_TYPE = "byte[]";

    public static final int BYTE_DATATYPE = 4;

    public static final String BYTE_TYPE = "byte";

    public static final int BYTEARRAY_DATATYPE = 17;

    public static final String CHAR_ARRAY_TYPE = "char[]";

    public static final int CHARARRAY_DATATYPE = 18;

    public static final int CLOB_DATATYPE = 10;

    public static final String CLOB_TYPE = "clob";

    public static final String CONVERT_WILDCARD_STAR_TO_PERCENT = "Convert * to %";

    public static final int DATE_DATATYPE = 1;

    public static final String DATE_TYPE = "Date";

    public static final String DB2_IDENTITY_QUERY = "SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1";

    public static final String DB2_IDENTITY_QUERY_KEY = "DB2_IDENTITY";

    public static final String DOUBLE_COLON = "::";

    public static final int DOUBLE_DATATYPE = 6;

    public static final String DOUBLE_TYPE = "double";

    public static final String DOUBLE_TYPE_LONG = "java.lang.Double";

    public static final String DUP_COLUMN = "duplicate column";

    public static final String DUP_FIELD_INDEX = "duplicate field index";

    public static final String DUP_QUERY_CALLBACK_NAMES = "duplicate query callbacks";

    public static final String EMPTY_QUERY_CALLBACK_NAME = "query callback has no name";

    public static final int EVENT_DATATYPE = 3;

    public static final String EVENT_TYPE = "event";

    public static final int FLOAT_DATATYPE = 5;

    public static final String FLOAT_TYPE = "float";

    public static final String FOR_READ_ONLY = " FOR READ ONLY";

    public static final String FROM = " FROM ";

    public static final String GREATER_THAN = ">";

    public static final int INT_DATATYPE = 14;

    public static final String INT_TYPE = "int";

    public static final String INTEGER_TYPE = "Integer";

    public static final String INVALID_CONNECTION = "invalid connection name";

    public static final String INVALID_EVENT_NAME = "invalid event name";

    public static final String INVALID_FIELD_INDEX = "invalid field index";

    public static final String INVALID_MAPPING_CLASS = "invalid mapping class name";

    public static final String INVALID_MAPPING_METHOD = "invalid mapping method";

    public static final String INVALID_PROPERTY = "invalid entity property";

    public static final String INVALID_SOURCE = "invalid source";

    public static final String INVALID_TYPE = "invalid type mapping";

    public static final String JDBC = "JDBC";

    public static final int JIMS2USER_DATATYPE = 16;

    public static final String JIMS2USER_TYPE = "jims2user";

    public static final String LESS_THAN = "<";

    public static final int LONG_DATATYPE = 8;

    public static final String LONG_TYPE = "long";

    public static final String M204 = "M204";

    public static final String MISSING_COLUMN = "missing column";

    public static final String MISSING_FIELDS = "missing field mapping properties";

    public static final String NO_ENTITY_MAPPING = "entity mapping does not exist";

    public static final String NONE = "none";

    public static final String NONE_UPPER = "NONE";

    public static final int NUMERIC_DATATYPE = 12;

    public static final String NUMERIC_TYPE = "numeric";

    public static final String OID = "OID";

    public static final String OID_DELIM = PropertyBundleProperties.getInstance().getProperty("OIDDelim");

    public static final String PERCENT_WILD_CARD = "%";

    public static final char PERCENT_WILD_CARD_CHAR = '%';

    public static final String PRESERVE_CASE = "Preserve Case";

    public static final String REPORTING_CONTEXT = "REPORTING::";

    public static final String SELECT_COUNT_FROM = "SELECT COUNT(*) FROM ";
    
    public static final String SEMICOLON = ";";

    public static final int SHORT_DATATYPE = 7;

    public static final String SHORT_TYPE = "short";

    public static final char STAR_WILD_CARD_CHAR = '*';

    public static final int STRING_ARRAY_DATATYPE = 19;

    public static final String STRING_ARRAY_TYPE = "string[]";

    public static final int STRING_DATATYPE = 2;

    public static final String STRING_TYPE = "String";

    public static final String STUB_MAPPING = FileIOMapping.class.getName();

    public static final int TIMESTAMP_DATATYPE = 13;

    public static final String TIMESTAMP_TYPE = "timestamp";

    public static final int USER_DATATYPE = 15;

    public static final String USER_TYPE = "user";

    public static final String VARCHAR = "VARCHAR";

    public static final String VARCHAR_IN_WHERE = "VARCHAR function in where clause";

    public static final String VSAM = "VSAM";

    public static final String WHERE = " WHERE ";
    
    public static final String WITH_UR_FOR_READ_ONLY = " WITH UR FOR READ ONLY";

    public static final String WILD_CARD_ALWAYS = "% Always";

    public static final String WILD_CARD_IF_BLANK = "% if Blank";

    public static final String FILE_IO_MAPPING = "Mapping is mojo.km.utilities.FileIOMapping";

    public static final String SKIPED_FIELD_INDEX = "Field mapping index has been skipped.";

    public static final String STRING_TYPE_LONG = "java.lang.String";

    public static final String DATE_TYPE_LONG = "java.util.Date";

    public static final String JDBC_VARCHAR = "VARCHAR";

    public static final String MIN_FUNC = "MIN";

    public static final String MAX_FUNC = "MAX";

    public static final String SUM_FUNC = "SUM";

    public static final String COUNT_FUNC = "COUNT";

    public static final String AVG_FUNC = "AVG";

    public static final String POSITION_500 = "500";

    public static final int OID_QUERY = 1;   

    public static final int ATTRIBUTE_QUERY = 2;

    public static final int ALL_QUERY = 3;

    public static final int EVENT_QUERY = 4;
    
    public static final int INSERT_QUERY = 5;
    
    public static final int UPDATE_QUERY = 6;

    public static final int INVALID_DATATYPE = -1;

    public static final String RETRIEVE_METHOD_NAME = "retrieve";
    
    public static final String RETRIEVE_DYNAMIC_METHOD_NAME = "retrieveDynamic";

    public static final String SAVE_METHOD = "save";

    public static final String INSERT_OPERATION = "Insert";

    public static final String UPDATE_OPERATION = "Update";

    public static final String DELETE_OPERATION = "Delete";

}
