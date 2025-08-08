/*
 * Created on Jul 25, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package mojo.tools.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;
import mojo.tools.code.parsers.eclipse.JavaWriterVisitor;
import mojo.tools.code.parsers.eclipse.EclipseFormatter;
import mojo.km.config.BuildProperties;
import mojo.km.utilities.TextUtil;
import mojo.tools.code.Attribute;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Method;
import mojo.tools.code.Type;
import mojo.tools.code.KeyWord;

/**
 * @author sshafi
 * 
 * @modelguid {436E258E-8438-4EF8-BB21-F4E888618E60}
 */
public class ContractGenerator extends CodeScanAntTask
{
    private static final String SOURCE_DIR = "c:/views/rsa/app/src/";

    private static final String FILE_NAME = "pd/juvenilewarrant/transactions/GetJuvenileWarrantsForAcknowledgeCommand.java";

    private static final String TEST_SOURCE_PATH = "C:/views/rsa/app/generatedtest/";

    public static void main(String[] args)
    {
        System.setProperty("mojo.config", "test.xml");
        System.setProperty("source.path", TEST_SOURCE_PATH);

        ICodeParser parser = CodeParser.getParser();
        try
        {
            CompilationUnit compUnit = parser.parseFile(SOURCE_DIR + FILE_NAME);

            if (compUnit != null)
            {
                ContractGenerator generator = new ContractGenerator();
                generator.executeScan(compUnit);
                generator.executeSave();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static final String ASSERT_CLASS = "mojo.km.contract.Assert";

    private static void processSourcePaths(String sourcePath)
    {
        System.out.println(sourcePath);
        List allClasses = CodeUtil.getDirectoryClasses(sourcePath);
        System.out.println(allClasses.size() + " classes found");

        for (int i = 0; i < allClasses.size(); i++)
        {
            CompilationUnit lUnit = (CompilationUnit) allClasses.get(i);
            try
            {
                if (lUnit.getMainType() instanceof mojo.tools.code.Class)
                {
                    generateContract((mojo.tools.code.Class) lUnit.getMainType());
                    System.out.println(lUnit);
                }
                else
                {
                    System.err.println("Type defined in file specified is not a Java class.");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void generate()
    {
        BuildProperties bProps = BuildProperties.getInstance();
        String sourcePath = bProps.getProperty("SourcePath");
        processSourcePaths(sourcePath);

        String altSrcPaths = bProps.getProperty("AlternateSourcePaths");
        StringTokenizer sTok = new StringTokenizer(altSrcPaths, ";");
        while (sTok.hasMoreTokens())
        {
            processSourcePaths(sTok.nextToken());
        }
    }

    /** @modelguid {932B48F5-5A41-4A74-9757-3CAF188CCD5A} */
    public static void generateContract(Type aType)
    {
        boolean contractFound = false;

        if (aType instanceof mojo.tools.code.Class)
        {
            // Get invariants.
            mojo.tools.code.Class myClass = (mojo.tools.code.Class) aType;
            List invariants = buildInvariants(myClass);

            // Iterate methods.
            List methods = myClass.getMethods();
            int methodLen = methods.size();
            for (int i = 0; i < methodLen; i++)
            {
                Method myMethod = (Method) methods.get(i);
                if (!myMethod.getName().equals(myClass.getName()))
                {
                    if (!myMethod.isStatic())
                    {
                        updateMethod(myMethod, invariants);
                    }
                }
            }
        }
    }

    private static List buildInvariants(mojo.tools.code.Class myClass)
    {
        List retVal = new ArrayList();
        Iterator i = myClass.getProperties("invariant");
        while (i.hasNext())
        {
            String val = (String) i.next();
            String msg = val;
            if (val.indexOf("\"") > -1)
            {
                msg = new String(TextUtil.searchAndReplace(val.getBytes(), "\"", "\\\""));
            }
            retVal.add("mojo.km.contract.Assert.invariant(\"" + msg + "\", " + val + ");");

        }
        return retVal;
    }

    private static String extractContractExpression(String anExpression)
    {
        anExpression = TextUtil.searchAndReplace(anExpression, KeyWord.DOUBLE_QUOTE, KeyWord.ESCAPED_DOUBLE_QUOTE);
        anExpression = TextUtil.searchAndReplace(anExpression, KeyWord.NEWLINE, KeyWord.EMPTY_STRING);

        return anExpression;
    }

    private static String updateMethod(Method myMethod, List invariants)
    {
        String initialBody = myMethod.getBody();

        String body = "";

        Iterator i = invariants.iterator();
        while (i.hasNext())
        {
            body += i.next() + KeyWord.NEWLINE;
        }

        i = myMethod.getProperties("pre");
        while (i.hasNext())
        {
            String val = (String) i.next();
            String msg = extractContractExpression(val);
            body += "mojo.km.contract.Assert.pre(\"" + msg + "\", " + val + ");" + KeyWord.NEWLINE;
        }

        String endBody = "";
        i = myMethod.getProperties("post");
        while (i.hasNext())
        {
            String val = (String) i.next();
            String msg = extractContractExpression(val);
            endBody += "mojo.km.contract.Assert.post(\"" + msg + "\", " + val + ");" + KeyWord.NEWLINE;
        }
        i = invariants.iterator();
        while (i.hasNext())
        {
            endBody += i.next();
        }
        if (myMethod.getReturnType().equals(KeyWord.VOID))
        {
            body += initialBody;
            body += endBody;
        }
        else
        {
            initialBody = new EclipseFormatter().format(initialBody);
            StringReader sStream = new StringReader(initialBody);
            LineNumberReader lReader = new LineNumberReader(sStream);
            try
            {
                for (String line = lReader.readLine(); line != null; line = lReader.readLine())
                {
                    if (line.indexOf(KeyWord.RETURN) > -1)
                    {
                        body += endBody;

                    }
                    body += line + KeyWord.NEWLINE;
                }
            }
            catch (IOException io)
            {
            }
        }

        return body;
    }

    static private String cleanupContracts(Method myMethod)
    {
        return myMethod.getBody();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.CodeScanAntTask#executeSave()
     */
    public void executeSave()
    {
        System.out.println("executeSave()");
        System.setProperty("source.path", outDir);
        Iterator i = processedUnits.iterator();
        while (i.hasNext())
        {
            ContractVisitor aUnit = (ContractVisitor) i.next();
            try
            {
                aUnit.save(true);
            }
            catch (Throwable t)
            {
                // TODO Handle this throwable
                System.out.println(t.getMessage());
                t.printStackTrace();
            }

        }
        processedUnits.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.CodeScanAntTask#executeScan(mojo.tools.code.CompilationUnit)
     */
    public void executeScan(CompilationUnit aUnit)
    {
        try
        {
            ContractVisitor visitor = new ContractVisitor();
            visitor.visit(aUnit);
            processedUnits.add(visitor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * internal variable to handle list of processed classes for detailing.
     */
    static ArrayList processedUnits = new ArrayList();

    private String outDir = "c:\\temp";

    /**
     * @return
     */
    public String getOutDir()
    {
        return outDir;
    }

    /**
     * @param string
     */
    public void setOutDir(String string)
    {
        outDir = string;
    }

    static public class ContractVisitor extends JavaWriterVisitor
    {
        /*
         * (non-Javadoc)
         * 
         * @see mojo.tools.code.IElementVisitor#visit(mojo.tools.code.Class)
         */
        public void visit(mojo.tools.code.Class aClass)
        {
            if (aClass.getComment() != null)
            {
                aClass.getComment().accept(this);
            }
            if (aClass.isAbstract())
            {
                file.append(KeyWord.ABSTRACT);
            }
            if (aClass.isFinal())
            {
                file.append(KeyWord.FINAL);
            }
            if (aClass.isStatic())
            {
                file.append(KeyWord.STATIC);
            }
            if (aClass.getScope() != null)
            {
                file.append(aClass.getScope()).append(KeyWord.SPACE);
            }

            file.append(KeyWord.CLASS).append(aClass.getName());

            if (aClass.getExtendsClass() != null)
            {
                file.append(KeyWord.EXTENDS).append(aClass.getExtendsClass());
            }
            if (aClass.getImplements().hasNext())
            {
                file.append(KeyWord.IMPLEMENTS);
                for (Iterator i = aClass.getImplements(); i.hasNext();)
                {
                    file.append(i.next().toString());
                    if (i.hasNext())
                    {
                        file.append(KeyWord.COMMA);
                    }
                }
            }
            file.append(KeyWord.BLOCK_OPEN).append(KeyWord.NEWLINE);

            for (Iterator i = aClass.getAttributes(); i.hasNext();)
            {
                Attribute lAttr = (Attribute) i.next();
                lAttr.accept(this);
            }

            List invariants = buildInvariants(aClass);
            List methods = aClass.getMethods();
            int methodLen = methods.size();
            for (int i = 0; i < methodLen; i++)
            {
                Method lMethod = (Method) methods.get(i);
                if (!lMethod.getName().equals(aClass.getName()))
                {
                    if (!lMethod.isStatic())
                    {
                        lMethod.setBody(updateMethod(lMethod, invariants));
                    }
                }
                lMethod.accept(this);
            }

            for (Iterator i = aClass.getInnerTypes(); i.hasNext();)
            {
                Type lType = (Type) i.next();
                lType.accept(this);
            }

            if (aClass.getInitializer() != null)
            {
                aClass.getInitializer().accept(this);
            }

            file.append(KeyWord.BLOCK_CLOSE).append(KeyWord.NEWLINE);

        }

        /**
         * Saves the current compilation unit to the given directory.
         * 
         * @param String
         *            name of the direct to save this unit in
         * @modelguid {17FB958D-A687-4B5B-B2D0-72AF6F0C715C}
         */
        public void save(boolean anOverwrite)
        {
            String lDirectory = System.getProperty("source.path");
            if (lDirectory == null || lDirectory.equals(""))
            {
                lDirectory = BuildProperties.getInstance().getProperty("SourcePath");
            }

            CompilationUnit compUnit = this.getCompilationUnit();
            Type myType = compUnit.getMainType();
            String compName = myType.getName();
            String filename = compName + KeyWord.JAVA_EXTENSION;
            if (lDirectory != null && !lDirectory.endsWith(KeyWord.FORWARD_SLASH) && !lDirectory.endsWith(KeyWord.BACK_SLASH))
            {
                lDirectory += '/';
            }
            if (getCompilationUnit().getPackageName() != null)
            {
                lDirectory += getCompilationUnit().getPackageName().replace('.', '/') + '/';
            }
            try
            {
                new File(lDirectory).mkdirs();
                File lFile = new File(lDirectory + filename);
                if (!lFile.exists() || (lFile.canWrite() && anOverwrite))
                {
                    String lCode = file.toString();
                    lCode = new EclipseFormatter().format(lCode);
                    PrintWriter out = new PrintWriter(new FileWriter(lDirectory + filename));
                    out.println(lCode);
                    out.flush();
                    out.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
