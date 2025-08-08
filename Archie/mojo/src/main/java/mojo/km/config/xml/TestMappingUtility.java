/*
 * Created on May 19, 2006
 *
 */
package mojo.km.config.xml;

import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import mojo.km.config.TestDataClassificationProperties;

/**
 * @author Jim Fisher
 *  
 */
public class TestMappingUtility
{
    /**
     * @param objElement
     * @return
     */
    public static TestDataClassificationProperties readTestDataClassificationProperties(Element objElement)
    {
        TestDataClassificationProperties cProps = new TestDataClassificationProperties();
        cProps.setName(objElement.getAttributeValue(TestDataClassificationProperties.NAME));

        Element recordsElement = objElement.getChild(TestDataClassificationProperties.RECORDS);

        if (recordsElement != null)
        {
            List records = recordsElement.getChildren(TestDataClassificationProperties.RECORD);

            Iterator r = records.iterator();

            while (r.hasNext())
            {
                Element record = (Element) r.next();
                cProps.addRecord(record.getText());
            }
        }

        Element rangeElement = objElement.getChild(TestDataClassificationProperties.GENERATE);
        if (rangeElement != null)
        {
            cProps.setGenerateProperties(rangeElement);
        }

        return cProps;
    }
}
