/*
 * TextReporting.java
 *
 * Created on March 23, 2006, 8:32 PM
 *
 */

package mojo.km.reporting.text;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.naming.ReportingConstants;
import mojo.km.reporting.IReport;
import mojo.km.reporting.exception.BeanNotFoundException;
import mojo.km.utilities.Reflection;

/**
 * 
 * @author Jim Fisher
 */
public class TextReporting implements IReport
{
    private static final String TEMPLATE = "My name is #{mojo.km.reporting.text.TesterBean.firstName} #{mojo.km.reporting.text.TesterBean.lastName}.";

    private final String BEGIN_FIELD_CHAR = "#{";

    private final int BEGIN_FIELD_CHAR_LEN = BEGIN_FIELD_CHAR.length();

    private final String END_FIELD_CHAR = "}";

    /** Creates a new instance of TextReporting */
    public TextReporting()
    {
    }

    private String parseFields(String template, Map beanMap) throws ParseException
    {
        boolean done = false;
        int beginIndex = 0;
        int endIndex = 0;

        StringBuffer buffer = new StringBuffer(template);

        while (done == false)
        {
            // TODO Convert to buffer.indexOf when JDK1.4 is ready in the IDE
            beginIndex = buffer.toString().indexOf(BEGIN_FIELD_CHAR, beginIndex);
            endIndex = buffer.toString().indexOf(END_FIELD_CHAR, beginIndex);

            if (beginIndex == -1 || endIndex == -1 || beginIndex >= endIndex)
            {
                done = true;
            } else
            {
                String field = buffer.substring(beginIndex + BEGIN_FIELD_CHAR_LEN, endIndex);

                int endClassNameIndex = field.lastIndexOf('.');
                String beanName = field.substring(0, endClassNameIndex);
                String property = field.substring(endClassNameIndex + 1, field.length());

                Object bean = beanMap.get(beanName);

                Object value = Reflection.invokeAccessorMethod(bean, property);
                if (value == null)
                {
                    // value = " {" + beanName + "." + property + " is null} ";
                    String msg = "bean: " + beanName;
                    throw new BeanNotFoundException(msg);
                }
                buffer.replace(beginIndex, endIndex + 1, value.toString());
            }
        }
        return buffer.toString();
    }

    public byte[] getByteOutput(ReportRequestEvent event) throws Exception
    {
        Map beanMap = new HashMap();

        TextReportTemplate textReportTemplate = TextReportTemplate.findByName(event.getReportName());
        String template = textReportTemplate.getTemplate();
        if (template == null)
        {
            template = TEMPLATE;
        }

        // build bean map for lookup in parsing
        Iterator i = event.getDataObjects();
        while (i.hasNext())
        {
            Object bean = i.next();
            String beanClassName = bean.getClass().getName();
            beanMap.put(beanClassName, bean);
        }

        String text = this.parseFields(template, beanMap);

        return text.getBytes();
    }

    public String getContentType()
    {
        return ReportingConstants.TEXT_MIME_TYPE;
    }

    public String getTemplate()
    {
        return null;
    }
}
