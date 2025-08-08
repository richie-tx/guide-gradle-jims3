/*
 * Created on Oct 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mapping;

import java.text.SimpleDateFormat;
import java.util.Date;

import naming.PDConstants;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WhereClauseHelper
{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
	private static final String STRING = "String";
	private static final String DATE = "Date";
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	
	public static String buildBuildClause(
		String fieldType,
		boolean ignoreCase,
		boolean formatString,
		Object value,
		String dbFieldName)
	{
		StringBuffer clause = new StringBuffer();
		boolean isString = fieldType.trim().equalsIgnoreCase(STRING);
		boolean isDate = fieldType.trim().equalsIgnoreCase(DATE);
		boolean isBoolean = fieldType.trim().equalsIgnoreCase(BOOLEAN);
		boolean isNumaric = fieldType.trim().equalsIgnoreCase(INT);
		if (value != null)
		{
			if (isBoolean)
			{
				clause.append(" ");
				clause.append(dbFieldName);
				clause.append("=");
				clause.append(value.toString());
			}
			else if (isNumaric)
			{
				if(value.toString().trim().length() == 0)
				{
					return PDConstants.BLANK;
				}
				clause.append(dbFieldName);
				clause.append("=");
				clause.append(value);
			}
			else
			{
				clause.append(" COALESCE(");
				if (isString)
				{
					String valueString = value.toString();
					if(valueString.trim().length() == 0)
					{
						return PDConstants.BLANK;
					}
					
					clause.append(modifyCase(dbFieldName, ignoreCase));
					value = formatField(modifyCase(value.toString(), ignoreCase), formatString);
				}
				else
				{
					clause.append("VARCHAR (");
					clause.append(dbFieldName);
					clause.append(")");
					if (isDate)
					{
						value = dateFormat.format((Date) value);
					}
				}
				clause.append(" , '') LIKE ");
				if (isDate)
				{
					clause.append(" UPPER(CAST('");
					clause.append(value);
					clause.append("' AS VARCHAR(20))) || '%'");

				}
				else
				{
					clause.append("'");
					clause.append(value);
					clause.append("'");
				}
			}

		}

		return clause.toString();
	}
	
	public static String buildBuildClauseWithoutCoalesce(
			String fieldType,
			boolean ignoreCase,
			boolean formatString,
			boolean isEqualStringSearch,
			Object value,
			String dbFieldName)
		{
			StringBuffer clause = new StringBuffer();
			boolean isString = fieldType.trim().equalsIgnoreCase(STRING);
			boolean isDate = fieldType.trim().equalsIgnoreCase(DATE);
			boolean isBoolean = fieldType.trim().equalsIgnoreCase(BOOLEAN);
			boolean isNumeric = fieldType.trim().equalsIgnoreCase(INT);
			if (value != null)
			{
				if (isBoolean)
				{
					clause.append(" ");
					clause.append(dbFieldName);
					clause.append("=");
					clause.append(value.toString());
				}
				else if (isNumeric)
				{
					if(value.toString().trim().length() == 0)
					{
						return PDConstants.BLANK;
					}
					clause.append(dbFieldName);
					clause.append("=");
					clause.append(value);
				}
				else
				{
					if (isString)
					{
						String valueString = value.toString();
						if(valueString.trim().length() == 0)
						{
							return PDConstants.BLANK;
						}
						
						clause.append(modifyCase(dbFieldName, ignoreCase));
						value = formatField(modifyCase(value.toString(), ignoreCase), formatString);
					}
					else
					{
						clause.append("CHAR (");
						clause.append(dbFieldName);
						clause.append(")");
						if (isDate)
						{
							value = dateFormat.format((Date) value);
						}
					}
					if (isEqualStringSearch){
						clause.append(" = ");
					} else {
						clause.append(" LIKE ");
					}
					if (isDate)
					{
						clause.append(" UPPER(CAST('");
						clause.append(value);
						clause.append("' AS VARCHAR(20))) || '%'");

					}
					else
					{
						clause.append("'");
						clause.append(value);
						clause.append("'");
					}
				}

			}

			return clause.toString();
		}

	public static String buildDateBuildClauseWithoutCoalesce(
			String fieldType,
			boolean ignoreCase,
			boolean formatString,
			boolean isEqualStringSearch,
			Object value,
			String dbFieldName)
		{
			StringBuffer clause = new StringBuffer();
			boolean isDate = fieldType.trim().equalsIgnoreCase(DATE);
			if (isDate && value != null)
			{
				SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
				String valDate=dfmt.format((Date)value);
				clause.append(dbFieldName);
				clause.append(" LIKE ");
				clause.append("'");
				clause.append(valDate);
				clause.append("'");
				clause.append(" + '%'");
				
				
			}
			
			return clause.toString();
		}
	
	public static String buildDateBuildClause(
		String fieldType,
		boolean ignoreCase,
		boolean formatString,
		boolean isEqualStringSearch,
		Object value,
		String dbFieldName)
	{
		StringBuffer clause = new StringBuffer();
		boolean isDate = fieldType.trim().equalsIgnoreCase(DATE);
		if (isDate && value != null)
		{
			SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
			String valDate=dfmt.format((Date)value);
			clause.append(dbFieldName);
			clause.append(" = ");
			clause.append("'");
			clause.append(valDate);
			clause.append("'");
		
		}
		
		return clause.toString();
	}
	
	/**
	 * @param string
	 * @param formatString
	 * @return
	 */
	private static Object formatField(String value, boolean formatString)
	{
		if (formatString)
		{
			if (value.indexOf("*") >= 0)
			{
				value = value.replace('*', '%');
			}
			else
			{
				value = value + "%";
			}
		}

		return value;
	}

	/**
	 * @param dbFieldName
	 * @param ignoreCase
	 * @return
	 */
	private static String modifyCase(String dbFieldName, boolean ignoreCase)
	{
		return ignoreCase ? dbFieldName.toUpperCase() : dbFieldName;
	}

	/**
	 * @param buf
	 * @param firstName
	 * @return
	 */
	public static StringBuffer addToClause(StringBuffer buf, String subClause)
	{
		if (subClause.length() > 0)
		{
			if (buf.toString().length() > 0)
			{
				buf.append(" AND ");
			}
			buf.append(subClause);
		}
		return buf;
	}
}
