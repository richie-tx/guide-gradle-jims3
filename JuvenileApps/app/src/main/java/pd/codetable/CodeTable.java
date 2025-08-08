package pd.codetable;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author unknown
 *
 * CodeTable entity.
 */
public class CodeTable extends PersistentObject
{

	/**
	 * @param codeTableId
	 * @return CodeTable
	 * @roseuid 410FBB32035A
	 */
	static public CodeTable find(String codeTableName)
	{
		IHome home = new Home();
		CodeTable codeTable = null;
		codeTable = (CodeTable) home.find(codeTableName, CodeTable.class);
		return codeTable;
	}

	/**
	 * @roseuid 410FBB320346
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CodeTable.class);
	}
	/**
	 * Collection of codes
	 * @referencedType pd.codetable.Code
	 */
	private Collection codes = null;
	private String dataSource = null;
	private String fileName = null;
	private String recordType = null;

	/**
	 * @roseuid 410FBB6D0341
	 */
	public CodeTable()
	{
	}

	/**
	 * Clears all pd.codetable.Code from class relationship collection.
	 */
	public void clearCodes()
	{
		initCodes();
		codes.clear();
	}
	/**
	 * returns a collection of pd.codetable.Code
	 * @return a collection of pd.codetable.Code
	 */
	public Collection getCodes()
	{
		fetch();
		initCodes();
		return codes;
	}

	/**
	 * Access method for the codeTableName property.
	 * @return the current value of the codeTableName property
	 */
	public String getCodeTableName()
	{
		fetch();
		return this.getOID().toString();
	}

	/**
	 * Access method for the dataSource property.
	 * @return the current value of the dataSource property
	 */
	public String getDataSource()
	{
		fetch();
		return dataSource;
	}

	/**
	 * Access method for the fileName property.
	 * @return the current value of the fileName property
	 */
	public String getFileName()
	{
		fetch();
		return fileName;
	}

	/**
	 * Access method for the recordType property.
	 * @return the current value of the recordType property
	 */
	public String getRecordType()
	{
		fetch();
		return recordType;
	}

	/**
	 * Initialize class relationship implementation for pd.codetable.Code
	 */
	private void initCodes()
	{
		if (codes == null)
		{
			codes = new java.util.ArrayList();
			try
			{
				codes =
					new mojo.km.persistence.ArrayList(
						Code.class,
						"codeTableName",
						getCodeTableName(),
						getCodeTableName());
			}
			catch (Throwable t)
			{
				codes = new java.util.ArrayList();
			}
		}
	}
	/**
	 * insert a Code into class relationship collection.
	 */
	public void insertCode(Object obj)
	{
		Code aCode = (Code) obj;
		initCodes();
		codes.add(aCode);
	}

	/**
	 * Removes the Code from the codes collection.
	 */
	public void removeCode(Code code)
	{
		initCodes();
		code.setDeleted();
		codes.remove(code);
	}

	/**
	 * Sets the value of the codeTableName property.
	 * @param aCodeTableName the new value of the codeTableName property
	 */
	public void setCodeTableName(String aCodeTableName)
	{
		super.setOID(aCodeTableName);
	}

	/**
	 * Sets the value of the dataSource property.
	 * @param aDataSource the new value of the dataSource property
	 */
	public void setDataSource(String aDataSource)
	{
		this.dataSource = aDataSource;
	}

	/**
	 * Sets the value of the fileName property.
	 * @param aFileName the new value of the fileName property
	 */
	public void setFileName(String aFileName)
	{
		this.fileName = aFileName;
	}

	/**
	 * Sets the value of the recordType property.
	 * @param aRecordType the new value of the recordType property
	 */
	public void setRecordType(String aRecordType)
	{
		this.recordType = aRecordType;
	}
}
