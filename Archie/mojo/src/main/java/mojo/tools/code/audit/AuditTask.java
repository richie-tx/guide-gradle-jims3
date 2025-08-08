/*
 * Created on Jun 22, 2006
 *
 */
package mojo.tools.code.audit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.DirectoryWalker;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import com.puppycrawl.tools.checkstyle.api.AuditListener;

import mojo.km.config.AuditCheckProperties;
import mojo.km.config.AuditProperties;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.audit.checkstyle.CheckstyleAuditor;
import mojo.tools.code.audit.checkstyle.CheckstyleListener;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

/**
 * @author Jim Fisher
 * 
 */
public class AuditTask extends Task
{
	private static final String AUDIT_CONFIG_FILE = "file:///c:/views/maint_framework_07/arch/build/configuration/dev/codeaudit.xml";

	private static final String CHECKSTYLE_CONFIG_FILE = "file:///c:/views/maint_framework_07/arch/build/configuration/dev/jimscodeaudit.xml";

	private static final String CONFIG_FILE = "multisource.xml";

	private static final String FILE_NAME[] = { "ui/juvenilewarrant/action/" };

	private static final String OUTPUT_DIR = "c:/temp/audit/code/";

	private static final String OUTPUT_FILE = "codeaudit_report.xml";

	private static final String SOURCE_DIR = "c:/views/maint_framework_07/app/src/";

	public static void main(String[] args)
	{
		AuditTask scanTask = new AuditTask();

		scanTask.setConfigFile(CONFIG_FILE);
		// System.setProperty("mojo.config","multisource.xml");
		// ContextManager.currentContext();

		scanTask.setSourcePath(SOURCE_DIR);

		scanTask.setAuditConfig(AUDIT_CONFIG_FILE);

		scanTask.setCheckstyleConfig(CHECKSTYLE_CONFIG_FILE);

		scanTask.setOutFile(OUTPUT_DIR + "/" + OUTPUT_FILE);

		scanTask.init();

		ICodeParser parser = CodeParser.getParser();

		parser.setParseStatements(true);
		
		try
		{
			for (int i = 0; i < FILE_NAME.length; i++)
			{
				Walker walker = scanTask.new Walker(SOURCE_DIR + FILE_NAME[i]);
				walker.walk();

				List results = walker.getResults();
				int len = results.size();
				for (int j = 0; j < len; j++)
				{
					File f = (File) results.get(j);
					CompilationUnit unit = parser.parseFile(f.getAbsolutePath());
					scanTask.executeScan(unit);
				}
			}
			scanTask.scanFinished();
			scanTask.executeSave();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This is purely for testing the running standalone
	 * @author jfisher
	 *
	 */
	class Walker extends DirectoryWalker
	{
		private File startDir;

		private List results;

		public Walker(String startDirName)
		{
			super();
			this.results = new ArrayList();
			this.startDir = new File(startDirName);
		}

		protected void handleFile(File file, int depth, Collection results) throws IOException
		{
			results.add(file);
		}

		protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException
		{
			return true;
		}

		public void walk() throws IOException
		{
			this.walk(this.startDir, results);
		}

		public List getResults()
		{
			return this.results;
		}
	}

	private String auditConfig;

	private Map checkLookup;

	private String checkstyleConfig;

	private List checkstyleFiles;

	protected String configFile;

	/*
	 * private List computeCount() { List fileSummary = new ArrayList();
	 * 
	 * Iterator i = this.filesByName.keySet().iterator();
	 * 
	 * while (i.hasNext()) { int errorCount = 0; int warningCount = 0; int infoCount = 0;
	 * 
	 * Object keyObj = i.next(); List errors = (List) this.filesByName.get(keyObj); Iterator j =
	 * errors.iterator(); while (j.hasNext()) { AuditEvent auditEvt = (AuditEvent) j.next(); if
	 * (auditEvt.getSeverityLevel().equals(SeverityLevel.ERROR)) { errorCount++; } else if
	 * (auditEvt.getSeverityLevel().equals(SeverityLevel.WARNING)) { warningCount++; } else if
	 * (auditEvt.getSeverityLevel().equals(SeverityLevel.INFO)) { infoCount++; } }
	 * 
	 * FileSetSummaryRecord record = new FileSetSummaryRecord(); record.setName((String) keyObj);
	 * record.setErrorCount(errorCount); record.setWarningCount(warningCount);
	 * record.setInfoCount(infoCount); fileSummary.add(record); } return fileSummary; }
	 */

	protected List filesets;

	protected String importFile;

	private String outFile;

	private Map results;

	protected String sourcePath = "../../../app/src";

	/**
	 * 
	 */
	public AuditTask()
	{
		this.results = new HashMap();
		this.checkstyleFiles = new ArrayList();
		this.filesets = new ArrayList();
	}

	/**
	 * Registers a check listener for each code element type
	 * 
	 * @param check
	 * @param types
	 */
	private void addCheckTypes(String aCheckName, Check check, String[] types)
	{
		if (types == null || types.length == 0)
		{
			// TODO Report check has no types?
			System.err.println(aCheckName + " has no listener types defined in the check subclass.");
		}
		else
		{
			for (int i = 0; i < types.length; i++)
			{
				List checks = (List) this.checkLookup.get(types[i]);
				if (checks == null)
				{
					checks = new ArrayList();
					this.checkLookup.put(types[i], checks);
				}
				checks.add(check);
			}
		}
	}

	public void addFileset(FileSet fileset)
	{
		filesets.add(fileset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException
	{
		System.out.println("Use config file: " + configFile);
		if (filesets.size() == 0)
		{
			throw new BuildException("No input source files were specified.");
		}
		try
		{
			if (configFile != null)
			{
				System.setProperty("mojo.config", configFile);
			}
			System.setProperty("source.path", sourcePath);

			for (Iterator itFSets = filesets.iterator(); itFSets.hasNext();)
			{ // 2
				FileSet fs = (FileSet) itFSets.next();
				DirectoryScanner ds = fs.getDirectoryScanner(getProject());
				System.out.println("basedir: " + ds.getBasedir().getAbsolutePath());
				String baseDir = ds.getBasedir().getAbsolutePath().replace('\\', '/'); // 3
				String[] includedFiles = ds.getIncludedFiles();
				System.out.println("included files length: " + includedFiles.length);

				for (int i = 0; i < includedFiles.length; i++)
				{
					String filename = includedFiles[i].replace('\\', '/'); // 4
					filename = baseDir + "/" + filename;
					try
					{
						System.out.println("File: " + i + " " + includedFiles[i]);
						
						ICodeParser parser = CodeParser.getParser();
						CompilationUnit aUnit = parser.parseFile(filename);
						parser.setParseStatements(true);
						
						if (aUnit.getMainType() == null)
						{
							System.out.println("\nno main type: " + filename + "\n");
						}
						else
						{
							executeScan(aUnit);
						}
					}
					catch (Throwable e)
					{
						System.out.println("Could not scan " + filename + " ::Error " + e.getMessage());
						e.printStackTrace();
					}
				}
			}

			scanFinished();

			executeSave();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

	public void executeSave()
	{
		File outputFile = new File(this.getOutFile());

		List resultsList = new ArrayList();
		System.out.println("results size: " + results.size());
		
		this.output(this.results.keySet());
		resultsList.addAll(this.results.values());

		AuditXMLOutputter outputter = new AuditXMLOutputter(outputFile, resultsList);
		outputter.saveResults();
	}

	public void executeScan(CompilationUnit aUnit)
	{
		System.out.println("Scan: " + aUnit.getFile().getAbsolutePath());
		this.initChecks();

		AuditVisitor visitor = new AuditVisitor(this.checkLookup);
		visitor.visit(aUnit);

		AuditResult result = visitor.getResult();

		this.setErrorFlag(result);

		String filePath = aUnit.getFile().getAbsolutePath();
		System.out.println("putting: " + filePath + ", " + result.getAllResultsCount());
		this.results.put(filePath, result);

		System.out.println("results: " + results.size() + " " + filePath);

		this.checkstyleFiles.add(aUnit.getFile().getAbsolutePath());
	}

	/**
	 * @return Returns the outFile.
	 */
	public String getOutFile()
	{
		return outFile;
	}

	public void initChecks()
	{
		List checkList = AuditProperties.getInstance(this.auditConfig).getChecks();

		Iterator c = checkList.iterator();

		this.checkLookup = new HashMap();

		while (c.hasNext())
		{
			AuditCheckProperties checkProps = (AuditCheckProperties) c.next();
			String checkName = checkProps.getCheckName();

			Class checkClass;
			try
			{
				checkClass = Class.forName(checkName);

				Class[] parmTypes = new Class[] { AuditCheckProperties.class };
				Constructor checkConstructor = checkClass.getConstructor(parmTypes);

				Object[] parms = new Object[] { checkProps };

				Check check = (Check) checkConstructor.newInstance(parms);

				String[] types = check.getCodeElementTypes();

				this.addCheckTypes(checkName, check, types);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void scanFinished()
	{
		if (checkstyleConfig != null)
		{
			CheckstyleListener listener = new CheckstyleListener();

			try
			{
				ClassLoader loader = AuditTask.class.getClassLoader();
				Class auditorClass = Class.forName("mojo.tools.code.audit.checkstyle.CheckstyleAuditor", false, loader);

				Class[] parmTypes = new Class[] { String.class, List.class, AuditListener.class };

				Constructor constructor = auditorClass.getConstructor(parmTypes);

				Object[] initArgs = new Object[] { checkstyleConfig, this.checkstyleFiles, listener };

				CheckstyleAuditor auditor = (CheckstyleAuditor) constructor.newInstance(initArgs);

				auditor.execute();

				Map resultMap = listener.getFilesByName();

				Iterator k = resultMap.keySet().iterator();

				this.output(this.results.keySet());
				this.output(resultMap.keySet());

				// merge results between JIMS2 audit and Checkstyle audit
				while (k.hasNext())
				{
					String fileName = (String) k.next();

					AuditResult myResult = (AuditResult) this.results.get(fileName);
					AuditResult cResult = (AuditResult) resultMap.get(fileName);
					if (myResult == null)
					{
						System.out.println("myResult is null");
						this.results.put(fileName, cResult);
					}
					else
					{
						myResult.addResult(cResult);
					}

					this.setErrorFlag(cResult);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void output(Set s)
	{
		Iterator i = s.iterator();
		System.out.println("output keySet:");
		while (i.hasNext())
		{
			System.out.println("\t" + i.next());
		}
	}

	/**
	 * @param result
	 */
	private void setErrorFlag(AuditResult aResult)
	{
		if (aResult.getErrorCount() == 0)
		{
			System.setProperty("delivery.test.complete", "true");
		}
		else
		{
			System.setProperty("delivery.test.complete", "false");
		}
	}

	public void setAuditConfig(String aConfigFile)
	{
		this.auditConfig = aConfigFile;
	}

	public void setCheckstyleConfig(String aConfigFile)
	{
		this.checkstyleConfig = aConfigFile;
	}

	public void setConfigFile(String aConfigFile)
	{
		configFile = aConfigFile;
	}

	public void setImportFile(String aImportFile)
	{
		importFile = aImportFile;
	}

	/**
	 * @param outFile
	 *            The outFile to set.
	 */
	public void setOutFile(String outFile)
	{
		this.outFile = outFile;
	}

	public void setSourcePath(String aSourcePath)
	{
		sourcePath = aSourcePath;
	}
}
