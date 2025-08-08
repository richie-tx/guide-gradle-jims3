package pd.codetable;

import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import naming.PDCodeTableConstants;

/**
 * @author dgibler
 * Utility class to handle Code object retrievals and creation of Code response events.
 */
public final class PDCodeTableHelper
{

	/**
	 * Constructor
	 */
	private PDCodeTableHelper()
	{
		super();
	}

	/**
	 * Prepends code table name to code.
	 * @param codeTableName
	 * @param code
	 * @return java.lang.String
	 */
	public static String getCodeOID(String codeTableName, String code)
	{
		if (codeTableName == null
			|| code == null
			|| code.indexOf(PDCodeTableConstants.CODE_ID_SEPARATOR + "null") != -1)
		{
			return null;
		}
		if (code.indexOf(PDCodeTableConstants.CODE_ID_SEPARATOR) == -1)
		{
			code = codeTableName + PDCodeTableConstants.CODE_ID_SEPARATOR + code;
		}
		return code;
	}

	/**
	 * The method is used to retrieve a code for a specific code
	 * table.  It expects a codetablename and a codeId.  The 
	 * codeId can come in 2 forms either as a code or as a OID.  The
	 * OID is a concatenation of the tablename and the code with a
	 * set delimiter in between.  If the codeId is the OID
	 * then it finds on just the codeId if not, it concatenates
	 * the tablename and codeId with the delimiter and then
	 * finds the code.  THIS IS TEMPORARY.
	 * @param codeTableName
	 * @param codeId
	 * @return java.lang.String
	 */
	public static String getCodeId(String theCode, Class theParentType, String aPropertyName)
	{
		// must determine codeTableName from Mapping.xml
		SaveCallbackProperties saveProps =
			(SaveCallbackProperties) MojoProperties
				.getInstance()
				.getEntityMap(theParentType.getName())
				.getSaveCallbacks()
				.next();
		FieldMappingProperties fieldProps = (FieldMappingProperties) saveProps.getFieldMap(aPropertyName);
		if (fieldProps != null)
		{
			String codeTableName = fieldProps.getCodeTableName();
			Code code = getCode(codeTableName, theCode);
			if (code != null)
			{
				return code.getCodeId();
			}
		}
		return null;
	}

	public static boolean isCodeValid(String codeId, Code code)
	{
		boolean valid = false;
//		if ((codeId != null && codeId.equals("") == false && (code != null)))
		if ((codeId != null && !codeId.equals("") && (code != null)))

		{
			valid = true;
		}
		return valid;
	}

	/**
	 * Retrieves code value for a given code table and code value.
	 * @param codeTableName
	 * @param codeValue
	 * @return Code object
	 */
	public static Code getCode(String aCodeTableName, String aCodeValue)
	{
		if (aCodeTableName == null || aCodeValue == null)
		{
			return null;
		}
		if (aCodeValue.indexOf(PDCodeTableConstants.CODE_ID_SEPARATOR) == -1)
		{
			aCodeValue = PDCodeTableHelper.getCodeOID(aCodeTableName, aCodeValue);
		}
		return Code.find(aCodeValue);
	}

	/**
	 * Retrieves code value by codeId.
	 * @param codeId
	 * @return pd.codetable.Code
	 */
	public static Code getCode(String aCodeId)
	{
		if (aCodeId == null || aCodeId.indexOf(PDCodeTableConstants.CODE_ID_SEPARATOR) == -1)
		{
			return null;
		}
		return Code.find(aCodeId);
	}

	/**
	 * Will post a CodeResponseEvent corresponding to the Code passed in the parameter
	 * and set the topic of the response event to the passed Topic parameter
	 * @param code
	 * @param topic
	 */
	public static void postCodeResponseEvent(Code code, String topic)
	{
		if (code != null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			CodeResponseEvent response = new CodeResponseEvent();
			response.setCodeId(code.getCodeId());
			response.setCode(code.getCode());
			response.setDescription(code.getDescription());
			response.setTopic(topic);
			dispatch.postEvent(response);
		}
	}
}
