/*
 * Created on May 19, 2006
 *
 */
package mojo.km.config;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import mojo.km.utilities.TestDataUtil;

/**
 * @author Jim Fisher
 *
 */
public class TestDataClassificationProperties extends GenericProperties
{
    static public final String TEST_DATA_DICTIONARY_FILES = "TestDataDictionaryFiles";
    static public final String TEST_DATA_DICTIONARY_FILE = "TestDataDictionaryFile";
    	
    static public final String CLASSIFICATION = "classification";
	static public final String RECORDS = "records";
	static public final String RECORD = "record";
	static public final String GENERATE = "generate";
	
	static public final String NAME = "name";

	private List records;
	private TestClassificationRangeProperties rProps;

	public TestDataClassificationProperties()
	{
		this.records = new ArrayList();
	}

	public String getName()
	{
		return getProperty(NAME);
	}

	public void setName(String value)
	{
		this.setProperty(NAME, value);
	}

	public String getRecord()
	{
		String string = null;
		if (records.size() > 0)
		{
			int index = TestDataUtil.createInt(records.size());
			string = (String) records.get(index);
		}
		else if (rProps != null)
		{
			string = TestDataUtil.createInt(rProps.getBegin(), rProps.getEnd());
		}
		return string;
	}

	public void addRecord(String value)
	{
		this.records.add(value);
	}

	public List getRecords()
	{
		return this.records;
	}

	/**
	 * @param rangeElement
	 */
	public void setGenerateProperties(Element rangeElement)
	{
		rProps = new TestClassificationRangeProperties();
		rProps.setBegin(rangeElement.getAttributeValue(TestClassificationRangeProperties.MIN));
		rProps.setEnd(rangeElement.getAttributeValue(TestClassificationRangeProperties.MAX));
		rProps.setType(rangeElement.getAttributeValue(TestClassificationRangeProperties.TYPE));
		rProps.setFormat(rangeElement.getAttributeValue(TestClassificationRangeProperties.FORMAT));
		rProps.setDifference(rangeElement.getAttributeValue(TestClassificationRangeProperties.DIFFERENCE));
		rProps.setDifference(rangeElement.getAttributeValue(TestClassificationRangeProperties.DIFFERENCE));
		rProps.setIncrement(rangeElement.getAttributeValue(TestClassificationRangeProperties.INCREMENT));
	}

	public TestClassificationRangeProperties getGenerateProperties()
	{
		return rProps;
	}
}
