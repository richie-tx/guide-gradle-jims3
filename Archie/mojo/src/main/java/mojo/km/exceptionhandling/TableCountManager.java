package mojo.km.exceptionhandling;

import java.util.Hashtable;

public class TableCountManager
{
	private Hashtable<String, SourceCount> tableCount;

	private int countThreshold;

	public TableCountManager(int aCountThreshold)
	{
		this.tableCount = new Hashtable<String, SourceCount>();
		this.countThreshold = aCountThreshold;
	}

	public SourceCount getTableCounter(String aTable)
	{
		SourceCount counter = tableCount.get(aTable);
		if (counter == null)
		{
			counter = new SourceCount(this.countThreshold);
			tableCount.put(aTable, counter);
		}		
		return counter;
	}

	public void addTable(String aTable)
	{
		SourceCount counter = this.getTableCounter(aTable);
		counter.count++;
	}
}
