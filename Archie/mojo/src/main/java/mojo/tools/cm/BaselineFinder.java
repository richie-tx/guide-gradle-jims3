/*
 * Created on Jan 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.tools.cm;

import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
//import clearcase.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BaselineFinder extends Task

{
	private String baseline = "BUILT";
	private String integrationView = "y:\\";
	private String compositeBaselineLabel = "";
	private String baselineDependencies = "";
	private String baselinePrefix = "BL";

	public static void main(String[] args)
	{
		BaselineFinder bF = new BaselineFinder();
		try {
			bF.baseline = args[0];
			bF.execute();
		} catch (BuildException bE)
		{
			
		}

	}
	
	public static class LabelHelper {
		private String timeStamp;
		private String label;
		
		public LabelHelper(String line) {
			StringTokenizer sTok = new StringTokenizer(line, ",");
			label = sTok.nextToken();
			timeStamp = sTok.nextToken();
			
		}
		
		public String toString()
		{
			return label;
		}
		
		public String getTimeStamp()
		{
			return timeStamp;
		}
	}
	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException
	{
		// TODO Auto-generated method stub
		try
		{
			// Initialize the Java2Com Environment
//			com.ibm.bridge2java.OleEnvironment.Initialize();
//			clearcase.Application cApp = new clearcase.Application();
//			ClearTool cTool = new ClearTool();
//			String result = cTool.CmdExec("cd y:\\");
//			String baselineList = cTool.CmdExec("lsbl -fmt \"%En,%Nd\\n\" -component jims2build@\\j2pvob -level " + baseline);
//			baselineList += cTool.CmdExec("lsbl -fmt \"%En,%Nd\\n\" -component jims2build@\\j2pvob -gtlevel " + baseline);
			Process proc = Runtime.getRuntime().exec("cleartool lsbl -fmt \"%En,%Nd\\n\" -component jims2build@\\j2pvob -level " + baseline);
			InputStream input = proc.getInputStream();
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(new BufferedInputStream(input)));
			SortedMap sMap = new TreeMap();
			for (String line = reader.readLine(); line != null; line = reader.readLine())
			{
				if (line != null && line.startsWith(baselinePrefix)) {
					LabelHelper lHelper = new LabelHelper(line);
					sMap.put(lHelper.getTimeStamp(), lHelper);
				}
			}
			proc = Runtime.getRuntime().exec("cleartool lsbl -fmt \"%En,%Nd\\n\" -component jims2build@\\j2pvob -gtlevel " + baseline);
			input = proc.getInputStream();
			reader = new LineNumberReader(new InputStreamReader(new BufferedInputStream(input)));
			for (String line = reader.readLine(); line != null; line = reader.readLine())
			{
				if (line != null && line.startsWith(baselinePrefix)) {
					LabelHelper lHelper = new LabelHelper(line);
					sMap.put(lHelper.getTimeStamp(), lHelper);
				}
			}
			String baselineLabel = "" + sMap.get(sMap.lastKey());
			proc = Runtime.getRuntime().exec("cleartool lsbl -fmt \"%[depends_on]p\" " + baselineLabel + "@\\j2pvob");
			input = proc.getInputStream();
			reader = new LineNumberReader(new InputStreamReader(new BufferedInputStream(input)));
			String baselineDep = reader.readLine();
			if (!compositeBaselineLabel.equals(""))
			{
				if (this.getProject().getProperties().containsKey(compositeBaselineLabel))
				{
					this.getProject().setProperty(compositeBaselineLabel, baselineLabel);
				} else 
				{
					this.getProject().setNewProperty(compositeBaselineLabel, baselineLabel);
				}
			}
			baselineDep = baselineDep.replace(' ',',');
			baselineDep = baselineDep.substring(1);
			if (!baselineDependencies.equals(""))
			{
				if (this.getProject().getProperties().containsKey(baselineDependencies))
				{
					this.getProject().setProperty(baselineDependencies, baselineDep);
				} else 
				{
					this.getProject().setNewProperty(baselineDependencies, baselineDep);
				}
			}
			System.out.println(baselineDep);
			
		}
		catch (Exception e)
		{
			throw new BuildException(e.getMessage());
		}
		System.out.println("Baselines found.");
	}

	/**
	 * @param string
	 */
	public void setBaseline(String string)
	{
		baseline = string;
	}

	/**
	 * @param string
	 */
	public void setIntegrationView(String string)
	{
		integrationView = string;
	}


	/**
	 * @param string
	 */
	public void setBaselineDependencies(String string)
	{
		baselineDependencies = string;
	}

	/**
	 * @param string
	 */
	public void setCompositeBaselineLabel(String string)
	{
		compositeBaselineLabel = string;
	}

	/**
	 * @param string
	 */
	public void setBaselinePrefix(String string)
	{
		baselinePrefix = string;
	}

}
