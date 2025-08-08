/*
 * Created on Feb 10, 2005
 */
package mojo.tools.requirements;

import java.util.Comparator;
/**
 * @author mlawles
 * Used to sort lists of MappingFields based on the "position" attribute.
 */
public class MappingFieldComparator implements Comparator
{

	public int compare(Object arg0, Object arg1)
	{
		MappingField mapfield1 = (MappingField) arg0;
		MappingField mapfield2 = (MappingField) arg1;

		if (mapfield1.getPosition() < mapfield2.getPosition())
			return -1;
		if (mapfield1.getPosition() > mapfield2.getPosition())
			return 1;
		else
			return 0;
	}
	
	public boolean equals(Object arg0){
		return true;
	}

}
