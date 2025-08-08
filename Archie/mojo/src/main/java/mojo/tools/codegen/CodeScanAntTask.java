/*
 * Created on Apr 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.tools.codegen;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import mojo.tools.code.CompilationUnit;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;
import org.apache.tools.ant.DirectoryScanner;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
abstract public class CodeScanAntTask extends Task
{
    private Map options;

    public CodeScanAntTask()
    {
        super();
    }

    public void setOptions(Map theOptions)
    {
        this.options = theOptions;
    }

    public abstract void executeScan(CompilationUnit aUnit);

    public abstract void executeSave();

    protected List filesets = new Vector();

    protected String sourcePath = "../../../app/src";

    protected String alternateSourcePaths = "../../../framework/mojo-jims2/mojo.java/src";

    protected String configFile = null;

    protected String importFile = null;

    public void setSourcePath(String aSourcePath)
    {
        sourcePath = aSourcePath;
    }

    public void setAlternateSourcePaths(String anAlternateSourcePaths)
    {
        alternateSourcePaths = anAlternateSourcePaths;
    }

    public void setConfigFile(String aConfigFile)
    {
        configFile = aConfigFile;
    }

    public void setImportFile(String aImportFile)
    {
        importFile = aImportFile;
    }

    public void addFileset(FileSet fileset)
    {
        filesets.add(fileset);
    }

    /* (non-Javadoc)
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
            System.setProperty("alternate.source.paths", alternateSourcePaths);
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
                        ICodeParser parser = CodeParser.getParser();
                        parser.setParseStatements(false);
                        parser.setOptions(this.options);
                        CompilationUnit aUnit = parser.parseFile(filename);
                        if (aUnit.getMainType() == null)
                        {
                            System.out.println("\nno main type: " + filename + "\n");
                        }
                        else
                        {
                            executeScan(aUnit);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Could not scan " + filename + " ::Error " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            executeSave();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

}
