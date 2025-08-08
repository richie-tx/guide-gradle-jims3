//Source file: C:\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\km\\mapping\\BufferMapping.java

package mojo.km.mapping;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.utilities.Reflection;
import mojo.km.utilities.TextUtil;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.lang.reflect.Method;

/**
 * Responsible for handling mapping of Object property values to replacement values in a output buffer.
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BufferMapping implements IBufferMapping
{

	/**
	 * @roseuid 437DBF850193
	 */
	public BufferMapping()
	{

	}

	/**
	 * Handles mapping for String output buffers.
	 * @param buffer
	 * @param valueObject
	 * @return String
	 * @roseuid 437DBF8501A3
	 */
	public String updateBuffer(String buffer, Object valueObject)
	{
		EntityMappingProperties eProps =
			MojoProperties.getInstance().getEntityMap(BUFFERMAPPING + "::" + valueObject.getClass().getName());
		if (eProps == null)
		{
			return buffer;
		}
		Iterator i = eProps.getQueryCallbacks();
		CallbackProperties cProps = null;
		if (!i.hasNext())
		{
			return buffer;
		}
		cProps = (CallbackProperties) i.next();
		Iterator fields = cProps.getFieldsIterator();
		String outBuff = buffer;
		while (fields.hasNext())
		{
			FieldMappingProperties fMaps = (FieldMappingProperties) fields.next();
			String replacedValue = fMaps.getName();
			if (outBuff.indexOf(replacedValue) > -1)
			{
				String itemName = fMaps.getDataItemName();
				String replacementVal = "";
				StringTokenizer sTok = new StringTokenizer(itemName, ".");
				Object tempValObject = valueObject;
				while (sTok.hasMoreTokens())
				{
					String propertyName = sTok.nextToken();
					Method accessor = Reflection.getAccessorMethod(tempValObject.getClass(), propertyName);
					if (accessor == null)
					{
						continue;
					}
					Object valOut = Reflection.invokeAccessorMethod(tempValObject, propertyName);
					if (valOut == null)
					{
						replacementVal = fMaps.getText();
						break;
					}
					else
					{
						replacementVal = valOut.toString();

					}
					tempValObject = valOut;
				}
				byte[] byteBuf = TextUtil.searchAndReplace(outBuff.getBytes(), replacedValue, replacementVal);
				outBuff = new String(byteBuf);
			}
		}
		return outBuff;
	}

	static public BufferMapping getInstance()
	{

		return instance;
	}

	static private BufferMapping instance = new BufferMapping();

	static public String BUFFERMAPPING = "BUFFER_MAPPING";
}
