package mojo.tools.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import mojo.tools.code.KeyWord;
import mojo.tools.code.parsers.CodeParser;

/**
 * This class represents a Java source code file.
 * 
 * @author Saleem Shafi
 */
public class CompilationUnit extends CodeElement
{
    private static class CheckFileIsWritable extends Thread
    {
        private String filename;

        public CheckFileIsWritable(String aFilename)
        {
            super();
            filename = aFilename;
            start();
        }

        public void run()
        {
            File lFile = null;
            do
            {
                try
                {
                    sleep(10);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                lFile = new File(filename);
            } while (lFile != null && lFile.exists() && !lFile.canWrite());
        }
    }

    private static final int COMP_UNIT_START_SIZE = 2048; // 2K

    private File file;

    // Using a Map here, Set does not seem to be working (imports.contains(anImport) always returns false) 
    private Map imports;

    private String packageName;

    private List typeList = new ArrayList();

    /**
     * 
     */
    public CompilationUnit()
    {
        this.imports = new HashMap();
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

    /**
     * Adds an import statement to this compilation unit.
     * 
     * @param String the class or package to import;
     */
    public void addImport(ImportDeclaration anImport)
    {
        imports.put(anImport.getName(), anImport);
        anImport.setParent(this);
    }

    /**
     * @param importClassString
     */
    public void addImport(String importClassString)
    {
        if (importClassString != null)
        {
            // TODO parse for static imports
            // for now assume non-static
            ImportDeclaration impDecl = new ImportDeclaration();
            impDecl.setName(importClassString);
            if (importClassString.endsWith(".*"))
            {
                impDecl.setOnDemand(true);
            }
            this.addImport(impDecl);
        }
    }

    /**
     * Adds the given type definition to this compilation unit.
     * 
     * @param Type the type ro add to the unit
     */
    public void addType(Type aType)
    {
        aType.setPackage(new Package(getPackageName()));
        aType.setCompilationUnit(this);
        aType.setParent(this);
        typeList.add(aType);
    }

    public File getFile()
    {
        return this.file;
    }

    /**
     * Returns the list of import statements defined for this compilation unit;
     * 
     * @return Iterator to the list of import statements
     */
    public Iterator getImports()
    {
        return imports.values().iterator();
    }

    /**
     * Returns the highest-level or first type defined in this compilation unit.
     * 
     * @return the main type in this unit
     */
    public Type getMainType()
    {
        Iterator lTypes = getTypes();
        if (lTypes.hasNext())
        {
            return (Type) lTypes.next();
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the package that this compilation unit is defined in.
     * 
     * @return the package
     */
    public Package getPackage()
    {
        return new Package(getPackageName());
    }

    /**
     * Returns the name of the package that this compilation unit is defined in.
     * 
     * @return the package name;
     */
    public String getPackageName()
    {
        return packageName;
    }

    /**
     * Returns a list of all of the hih level types defined in a compilation
     * unit.
     * 
     * @return Iterator to the list of defined types
     */
    public Iterator getTypes()
    {
        return typeList.iterator();
    }

    private void makeFileWritable(String aFilename) throws Exception
    {
        //String lWritableCommand = "attrib -h -s -r ";
        //Runtime.getRuntime().exec(lWritableCommand+" "+aFilename);
        new CheckFileIsWritable(aFilename).join();
    }

    public void makeWritable()
    {
        String lDirectory = System.getProperty("source.path");
       /* if (lDirectory == null || lDirectory.equals(""))
        {
            lDirectory = BuildProperties.getInstance().getProperty("SourcePath");
        }
        */
        String lFilename = getMainType().getName() + KeyWord.JAVA_EXTENSION;
        if (lDirectory != null && !lDirectory.endsWith(KeyWord.FORWARD_SLASH)
                && !lDirectory.endsWith(KeyWord.BACK_SLASH))
        {
            lDirectory += '/';
        }
        if (getPackageName() != null)
        {
            lDirectory += getPackageName().replace('.', '/') + '/';
        }
        new File(lDirectory).mkdirs();
        try
        {
            makeFileWritable(lDirectory + lFilename);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Saves the current compilation unit to the default source directory.
     */
    public void save()
    {
        save(true);
    }

    /**
     * Saves the current compilation unit to the given directory.
     * 
     * @param String name of the direct to save this unit in
     */
    private void save(boolean anOverwrite)
    {
        String lDirectory = System.getProperty("source.path");
        /*if (lDirectory == null || lDirectory.equals(""))
        {
            lDirectory = BuildProperties.getInstance().getProperty("SourcePath");
        }*/
        String lFilename = getMainType().getName() + KeyWord.JAVA_EXTENSION;
        if (lDirectory != null && !lDirectory.endsWith(KeyWord.FORWARD_SLASH)
                && !lDirectory.endsWith(KeyWord.BACK_SLASH))
        {
            lDirectory += '/';
        }
        if (getPackageName() != null)
        {
            lDirectory += getPackageName().replace('.', '/') + '/';
        }
        try
        {        	
            new File(lDirectory).mkdirs();
            File lFile = new File(lDirectory + lFilename);
            if (!lFile.exists() || (lFile.canWrite() && anOverwrite))
            {
                String lCode = this.toString();
                Writer out = new BufferedWriter(new FileWriter(lDirectory + lFilename));
                out.write(lCode);
                out.flush();
                out.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save(String fullFileName)
    {
        try
        {
            File lFile = new File(fullFileName);
            if (!lFile.exists() || (lFile.canWrite()))
            {
                String lCode = this.toString();
                Writer out = new BufferedWriter(new FileWriter(fullFileName));
                out.write(lCode);
                out.flush();
                out.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param absolutePath
     */
    public void setFile(File aFile)
    {
        this.file = aFile;
    }

    /**
     * Sets the name of the package that this compilation unit is defined in.
     * 
     * @param String the name of the package for this compulation unit
     */
    public void setPackageName(String aPackageName)
    {
        packageName = aPackageName;
    }

    /**
     * Retruns the Java code representation of this comiplation unit.
     * 
     * @return String representation of the compilation unit
     */
    public String toString()
    {
        StringBuffer file = new StringBuffer(COMP_UNIT_START_SIZE);
        if (getPackageName() != null)
        {
            file.append(getPackage());
        }
        file.append(KeyWord.NEWLINE);                

        Iterator i = this.getImports();
        while (i.hasNext())
        {
            file.append(KeyWord.IMPORT);
            file.append(i.next());
            file.append(KeyWord.SEMICOLON);
            file.append(KeyWord.NEWLINE);
        }
        file.append(KeyWord.NEWLINE);

        i = this.getTypes();
        while (i.hasNext())
        {
            Type type = (Type) i.next();            
            file.append(type.toString());            
        }
        
        String s = CodeParser.getFormatter().format(file.toString());
        
        return s;
    }
}