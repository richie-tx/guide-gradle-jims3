//Source file: C:\\views\\framework_e2\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\tools\\requirements\\MappingXMLParser.java

/*
 * Created on Feb 4, 2005
 *
 */
package mojo.tools.requirements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;

/**
 * @author mlawles
 * This is a stand-alone program designed to read the mapping.xml file and check for gaps in the
 * parm index numbers.  If a gap is found, a message is printed giving the index and the parm name.
 * 
 */
public class MappingXMLParser
{
	private static String FILENAME1 = "C:\\XMLGaps.csv";
	private static String FILENAME2 = "C:\\XMLBlanks.csv";
	private static String FILENAME3 = "C:\\XMLParser.csv";

	private static String HEADER1 =
		"\"Entity Name\",\"Query Name\",\"Field/Parm Name\",\"Current Parm Index\",\"Proposed Parm Index\"";
	private static String HEADER2 =
		"\"Entity Name\",\"Query Name\",\"Field/Parm Name\",\"Parm Index\",\"PropertyType Value\"";

	public static void main(String[] args) throws IOException
	{
		System.setProperty("mojo.config", "multisource.xml");

		Iterator eventProps = MojoProperties.getInstance().getEntityMaps();
		List entityList = new ArrayList();
		String currentEntity;

		setupFile(FILENAME1, HEADER1);
		setupFile(FILENAME2, HEADER2);

		while (eventProps.hasNext())
		{
			EntityMappingProperties eProps = (EntityMappingProperties) eventProps.next();
			if (eProps != null)
			{
				currentEntity = eProps.getEntity();

				Iterator saveCallbacks = eProps.getSaveCallbacks();
				while (saveCallbacks.hasNext())
				{
					getSaveCallbacks(saveCallbacks, currentEntity, entityList);
				}

				Iterator queryCallbacks = eProps.getQueryCallbacks();
				while (queryCallbacks.hasNext())
				{
					getQueryCallbacks(queryCallbacks, currentEntity, entityList);
				}
			}
		}
		//concatenate output files into one file
		mergeFiles();
	}

	private static void mergeFiles() throws IOException
	{
		FileReader fr1 = null, fr2 = null;

		fr1 = new FileReader(FILENAME1);
		fr2 = new FileReader(FILENAME2);

		StringBuffer sb = new StringBuffer();

		FileWriter out = new FileWriter(FILENAME3);

		BufferedWriter bw = new BufferedWriter(out);

		while (fr1.ready())
		{
			bw.write(fr1.read());
		}
		
		bw.write("\" \",\" \",\" \",\" \",\" \",");
		bw.newLine();
		bw.write("\"+++++++++\",\"+++++++++\",\"+++++++++\",\"+++++++++\",\"+++++++++\",");
		bw.newLine();
		bw.write("\"+++++++++\",\"+++++++++\",\"+++++++++\",\"+++++++++\",\"+++++++++\",");
		bw.newLine();
		bw.write("\" \",\" \",\" \",\" \",\" \",");
		bw.newLine();
		while (fr2.ready())
		{
			bw.write(fr2.read());
		}
		bw.flush();
		bw.close();
		fr1.close();
		fr2.close();

		//	clean up
		boolean wasDeleted = false;
		
		File file = new File(FILENAME1);
		wasDeleted = file.delete();
		//System.out.println(FILENAME1+" deleted? "+wasDeleted);
		file = new File(FILENAME2);
		wasDeleted = file.delete();
		//System.out.println(FILENAME2+" deleted? "+wasDeleted);
	
	}

	private static void setupFile(String filename, String header) throws IOException
	{
		FileWriter flush = null;

		flush = new FileWriter(filename);

		BufferedWriter bw1 = new BufferedWriter(flush);

		bw1.write("");
		bw1.flush();
		bw1.close();

		FileWriter fw = null;

		fw = new FileWriter(filename, true);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(header);
		bw.newLine();

		bw.flush();
		bw.close();
	}

	private static void getSaveCallbacks(Iterator saveCallbacks, String currentEntity, List entityList)
	{
		Integer indexValue = null;

		SaveCallbackProperties saver = (SaveCallbackProperties) saveCallbacks.next();
		Iterator parms = saver.getParmsIterator();
		if (saver.getConnectionName().equals("JDBC"))
		{
			while (parms.hasNext())
			{
				MappingField callback = new MappingField();
				ParmMappingProperties parm = (ParmMappingProperties) parms.next();
				String parmindex = parm.getParmIndex();
				indexValue = Integer.valueOf(parmindex);

				callback.setEntityName(currentEntity);
				callback.setType("parm");
				callback.setFieldName(parm.getName());
				callback.setPropertyName(parm.getPropertyName());
				callback.setPosition(indexValue.intValue());
				callback.setCallBackType("saver");
				callback.setCallBackName(saver.getName());
				entityList.add(callback);
			}
			printOutput(entityList, currentEntity);

			Iterator fields = saver.getFieldsIterator();
			while (fields.hasNext())
			{
				MappingField callback = new MappingField();
				FieldMappingProperties field = (FieldMappingProperties) fields.next();
				String parmindex = field.getParmIndex();
				indexValue = Integer.valueOf(parmindex);

				callback.setEntityName(currentEntity);
				callback.setType("field");
				callback.setFieldName(field.getName());
				callback.setPropertyName(field.getPropertyType());

				callback.setPosition(indexValue.intValue());
				callback.setCallBackType("saver");
				callback.setCallBackName(saver.getName());
				entityList.add(callback);
			}
			printOutput(entityList, currentEntity);
		}
	}

	private static void getQueryCallbacks(Iterator queryCallbacks, String currentEntity, List entityList)
	{
		Integer indexValue = null;

		EventQueryProperties query = (EventQueryProperties) queryCallbacks.next();
		Iterator parms = query.getParmsIterator();
		if (query.getConnectionName().equals("JDBC"))
		{
			while (parms.hasNext())
			{
				MappingField callback = new MappingField();
				ParmMappingProperties parm = (ParmMappingProperties) parms.next();
				String parmindex = parm.getParmIndex();
				indexValue = Integer.valueOf(parmindex);

				callback.setEntityName(currentEntity);
				callback.setType("parm");
				callback.setFieldName(parm.getName());
				callback.setPropertyName(parm.getPropertyType());

				callback.setPosition(indexValue.intValue());
				callback.setCallBackType("query");
				callback.setCallBackName(query.getName());
				parm.getPropertyType();
				entityList.add(callback);
			}
			printOutput(entityList, currentEntity);

			Iterator fields = query.getFieldsIterator();
			while (fields.hasNext())
			{
				MappingField callback = new MappingField();
				FieldMappingProperties field = (FieldMappingProperties) fields.next();
				String parmindex = field.getParmIndex();
				indexValue = Integer.valueOf(parmindex);

				callback.setEntityName(currentEntity);
				callback.setType("field");
				callback.setFieldName(field.getName());
				callback.setPropertyName(field.getPropertyType());

				callback.setPosition(indexValue.intValue());
				callback.setCallBackType("query");
				callback.setCallBackName(query.getName());
				entityList.add(callback);
			}
			printOutput(entityList, currentEntity);
		}
	}

	private static boolean checkForGaps(List entityList)
	{
		boolean gapExists = false;
		/*
		 * Iterate through the entityList, checking the MappingField.position integer
		 * If difference is > 1, gap exists, so mark the FOLLOWING MappingField as "gap = X"
		 * where X equals the difference. (i.e. a jump of index 2 to index 4 would have X=2)
		 * 
		 */

		Iterator iter = entityList.iterator();
		int i = 0, current = 0, next = 0, diff = 0, nextIndex = 0;

		while (iter.hasNext() && i < ((entityList.size()) - 1))
		{
			//calculate difference
			MappingField mapfield = (MappingField) iter.next();
			nextIndex = i + 1;

			current = mapfield.getPosition();

			if (entityList.size() > 1)
			{
				mapfield = (MappingField) entityList.get(nextIndex);
				next = mapfield.getPosition();

				diff = (next - current);

				//mark gap if present
				if (diff > 1)
				{
					mapfield.setGap(diff);
					gapExists = true;
				}
			}
			i++;
		}
		return gapExists;
	}

	private static boolean checkForZero(List entityList)
	{
		boolean zero = false;
		Iterator iter = entityList.iterator();
		while (iter.hasNext())
		{
			MappingField mapfield = (MappingField) iter.next();
			int current;
			current = mapfield.getPosition();

			if (current == 0)
			{
				mapfield.setZero(true);
				zero = true;
			}
		}
		return zero;
	}

	private static boolean checkForBlankProperty(List entityList)
	{
		boolean blank = false;
		Iterator iter = entityList.iterator();
		while (iter.hasNext())
		{
			MappingField mapfield = (MappingField) iter.next();

			if (mapfield.getPropertyName().equals(""))
			{
				mapfield.setBlank(true);
				blank = true;
			}
		}
		return blank;
	}

	private static void printOutput(List entityList, String currentEntity)
	{
		boolean zeroExists = false, gapExists = false, blank = false;
		Comparator comparator = new MappingFieldComparator();

		Collections.sort(entityList, comparator);

		zeroExists = checkForZero(entityList);
		if (zeroExists == true)
		{
			List newList = new ArrayList();
			Iterator iter = entityList.iterator();
			while (iter.hasNext())
			{
				MappingField tofield = new MappingField();
				MappingField fromfield = (MappingField) iter.next();

				tofield.setCallBackName(fromfield.getCallBackName());
				tofield.setCallBackType(fromfield.getCallBackType());
				tofield.setEntityName(fromfield.getEntityName());
				tofield.setFieldName(fromfield.getFieldName());
				tofield.setGap(fromfield.getGap());
				tofield.setPosition(fromfield.getPosition());
				tofield.setType(fromfield.getType());
				tofield.setZero(fromfield.isZero());
				newList.add(tofield);
			}

			shiftZeros(newList);
			try
			{
				printList(entityList, newList);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		gapExists = checkForGaps(entityList);
		if (gapExists == true)
		{
			List newList = new ArrayList();
			Iterator iter = entityList.iterator();
			while (iter.hasNext())
			{
				MappingField tofield = new MappingField();
				MappingField fromfield = (MappingField) iter.next();

				tofield.setCallBackName(fromfield.getCallBackName());
				tofield.setCallBackType(fromfield.getCallBackType());
				tofield.setEntityName(fromfield.getEntityName());
				tofield.setFieldName(fromfield.getFieldName());
				tofield.setGap(fromfield.getGap());
				tofield.setPosition(fromfield.getPosition());
				tofield.setType(fromfield.getType());
				tofield.setZero(fromfield.isZero());
				newList.add(tofield);
			}
			shiftGaps(newList);

			try
			{
				printList(entityList, newList);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		blank = checkForBlankProperty(entityList);
		if (blank == true)
		{
			try
			{
				printList(entityList);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		entityList.clear();
	}

	private static void printList(List entityList, List shiftList) throws IOException
	{
		FileWriter fw = null;

		fw = new FileWriter(FILENAME1, true);

		BufferedWriter bw = new BufferedWriter(fw);
		//bw.write(string);

		Iterator iter = entityList.iterator();
		Iterator shiftIter = shiftList.iterator();

		while (iter.hasNext())
		{
			MappingField mapfield = (MappingField) iter.next();
			MappingField mapfield2 = (MappingField) shiftIter.next();
			bw.write("\"" + mapfield.getEntityName() + "\",");
			bw.write("\"" + mapfield.getCallBackName() + "\",");
			bw.write("\"" + mapfield.getFieldName() + "\",");
			bw.write("\"" + mapfield.getPosition() + "\",");
			if (mapfield.getPosition() != mapfield2.getPosition())
				bw.write("\"" + mapfield2.getPosition() + "\",");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	/*This method is used exclusively for printing the list of blank propertyTypes*/
	private static void printList(List entityList) throws IOException
	{
		FileWriter fw = null;

		fw = new FileWriter(FILENAME2, true);

		BufferedWriter bw = new BufferedWriter(fw);
		//Usage:  bw.write(string);

		Iterator iter = entityList.iterator();

		while (iter.hasNext())
		{
			MappingField mapfield = (MappingField) iter.next();

			if (mapfield.getPropertyName().equals(""))
			{
				bw.write("\"" + mapfield.getEntityName() + "\",");
				bw.write("\"" + mapfield.getCallBackName() + "\",");
				bw.write("\"" + mapfield.getFieldName() + "\",");
				bw.write("\"" + mapfield.getPosition() + "\",");
				bw.write("\"blank\",");
				//bw.write("\"" + mapfield.getPropertyName() + "\",");
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
	}

	private static void shiftGaps(List shiftList)
	{
		//cycle through and shift according to first gap, unmark gap, make another pass until sorted.
		//return sorted list.

		for (int i = 0; i < shiftList.size(); i++)
		{
			MappingField mapfield = (MappingField) shiftList.get(i);
			if (mapfield.getGap() > 0)
			{
				int gap = mapfield.getGap();
				for (int j = i; j < shiftList.size(); j++)
				{
					mapfield = (MappingField) shiftList.get(j);
					mapfield.setPosition((mapfield.getPosition() - (gap - 1)));
					//mapfield.setGap(0);
				}
			}

		}
	}

	private static void shiftZeros(List shiftList)
	{
		//cycle through and shift according to first gap, unmark gap, make another pass until sorted.
		//return sorted list.

		for (int i = 0; i < shiftList.size(); i++)
		{
			MappingField mapfield = (MappingField) shiftList.get(i);
			if (mapfield.isZero() == true)
			{
				for (int j = i; j < shiftList.size(); j++)
				{
					mapfield = (MappingField) shiftList.get(j);
					mapfield.setPosition((mapfield.getPosition() + 1));
					//mapfield.setGap(0);
				}
			}

		}
	}

	private static void printGaps(List entityList, String currentEntity)
	{
		Iterator iter = entityList.iterator();

		while (iter.hasNext())
		{
			MappingField mapfield = (MappingField) iter.next();
			if (mapfield.getGap() > 0)
			{
				System.out.println(mapfield.getEntityName());
				System.out.println(mapfield.getCallBackName());
				System.out.println("Gap before index: [" + mapfield.getPosition() + "]");
				System.out.println(mapfield.getCallBackType());
			}
		}
	}

	//	private static void printZeroCorrections(List entityList)
	//	{
	//		Iterator iter = entityList.iterator();
	//		int offset = 0;
	//
	//		while (iter.hasNext())
	//		{
	//			MappingField mapfield = (MappingField) iter.next();
	//
	//			System.out.print("\"" + mapfield.getEntityName() + "\",");
	//			//System.out.print("\"" + mapfield.getCallBackType() + "\",");
	//			System.out.print("\"" + mapfield.getCallBackName() + "\",");
	//			//System.out.print("\"" + mapfield.getType() +"\",");
	//			System.out.print("\"" + mapfield.getFieldName() + "\",");
	//			System.out.print("\"" + mapfield.getPosition() + "\",");
	//			if ((offset > 0) && (mapfield.isZero() == false))
	//			{
	//				//System.out.print("\"" + mapfield.getFieldName() + "\",");
	//				System.out.print("\"" + (mapfield.getPosition() + offset) + "\",");
	//			}
	//
	//			if ((mapfield.isZero() == true) /*&& (offset < 1)*/
	//				)
	//			{
	//				//System.out.print("\"" + mapfield.getFieldName() + "\",");
	//				System.out.print("\"" + (mapfield.getPosition() + (offset + 1)) + "\",");
	//				//System.out.print("\n");
	//				offset++;
	//			}
	//
	//			System.out.print("\n");
	//		}
	//	}

	//Report what the old parm index values are and what they should be changed to

	//	private static void printGapCorrections(List entityList)
	//	{
	//		Iterator iter = entityList.iterator();
	//		int offset = 0;
	//
	//		while (iter.hasNext())
	//		{
	//			MappingField mapfield = (MappingField) iter.next();
	//			//Print out the parm name and "field" or "parm" type.
	//			if (mapfield.getGap() > 0)
	//				//offset = 1;
	//				offset = mapfield.getGap();
	//
	//			if (offset > 0)
	//			{
	//				System.out.print("\"" + mapfield.getEntityName() + "\",");
	//				//System.out.print("\"" + mapfield.getCallBackType() + "\",");
	//				System.out.print("\"" + mapfield.getCallBackName() + "\",");
	//				//System.out.print("\"" + mapfield.getType() +"\",");
	//				System.out.print("\"" + mapfield.getFieldName() + "\",");
	//				System.out.print("\"" + mapfield.getPosition() + "\",");
	//				System.out.print("\"" + (mapfield.getPosition() - (offset - 1)) + "\",");
	//				System.out.print("\n");
	//			}
	//
	//			if ((mapfield.getGap() > 0) && (offset < 1))
	//			{
	//				System.out.print("\"" + mapfield.getFieldName() + "\",");
	//				System.out.print("\"" + (mapfield.getPosition() - 1) + "\",");
	//			}
	//		}
	//	}

} //end of Class
