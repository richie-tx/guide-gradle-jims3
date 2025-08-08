package ui.juvenilecase.prodsupport.helpers;

/**This is used inside QueryObject.java and GetNextRow in Constants.java **/
public class QueryColumn
{
	String columnName;
	String columnValue;
	
	public String getColumnName() {
		return columnName;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setColumnValue(String columnValue) {
		if (columnValue==null)
			this.columnValue = " ";
		else
			this.columnValue = columnValue;
	}
	
	public String toString(){
		return columnName + " - " + columnValue;
	}
}

