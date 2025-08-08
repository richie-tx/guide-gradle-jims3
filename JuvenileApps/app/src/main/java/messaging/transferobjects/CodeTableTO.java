/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.Collection;

/**
 * @author cc_mdsouza
 *
 */
public class CodeTableTO 
extends PersistentObjectTO
{
	
	private Collection codes = null;
	private String dataSource = null;
	private String fileName = null;
	private String recordType = null;

	public CodeTableTO()
	{
	}

	
	
	
	
	
	
	
	/**
	 * @return Returns the codes.
	 */
	public Collection getCodes() {
		return codes;
	}
	/**
	 * @param codes The codes to set.
	 */
	public void setCodes(Collection codes) {
		this.codes = codes;
	}
	/**
	 * @return Returns the dataSource.
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource The dataSource to set.
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return Returns the recordType.
	 */
	public String getRecordType() {
		return recordType;
	}
	/**
	 * @param recordType The recordType to set.
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
}
