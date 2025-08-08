/*
 * Created on Jun 2, 2006
 *
 */
package mojo.tools.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Class;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.IElementVisitor;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.Type;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.codegen.ContractTestCaseVisitor;
import mojo.tools.codegen.CoverageTestCaseVisitor;
import mojo.tools.code.parsers.ICodeParser;

/**
 * @author Jim Fisher
 *  
 */
public class TestCaseGenerator extends CodeScanAntTask
{
    private static final String FILE_NAME = "mojo/struts/taglib/layout/PanelTag.java";

    private static final String SOURCE_DIR = "C:/views/rsa/framework/mojo-jims2/mojo.java/src/";

    static private final String SUPER_TEST_CASE_CLASS_NAME = "test.TestCase";

    private static final String TEST_SOURCE_PATH = "C:/views/rsa/app/generatedtest/";

    public static void main(String[] args)
    {
        System.setProperty("mojo.config", "test.xml");

        ICodeParser parser = CodeParser.getParser();
        try
        {
            CompilationUnit compUnit = parser.parseFile(SOURCE_DIR + FILE_NAME);

            if (compUnit != null)
            {
                TestCaseGenerator generator = new TestCaseGenerator();
                generator.setOutDir(TEST_SOURCE_PATH);
                generator.setTestGenerationType(TestCodeGenConstants.COVERAGE_TEST);
                generator.executeScan(compUnit);
                generator.executeSave();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private List components;

    private String outDir;

    private String testGenerationType;

    public TestCaseGenerator()
    {
        components = new ArrayList();
        this.testGenerationType = TestCodeGenConstants.COVERAGE_TEST;
        Map codeParserOptions = this.getCodeParserOptions();
        super.setOptions(codeParserOptions);
    }

    /**
     * @return
     */
    private Map getCodeParserOptions()
    {
        Map options = new HashMap();
        return options;
    }

    /**
     * @return
     */
    private Method createTestCaseConstructor(String name)
    {
        Method constructor = new Method(name);
        constructor.setScope(KeyWord.PUBLIC);
        constructor.setReturnType("");
        Parameter stringArg = new Parameter("String", "name");
        String constructorBody = "super(name);";
        constructor.setBody(constructorBody);
        constructor.addParameter(stringArg);
        return constructor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.CodeScanAntTask#executeSave()
     */
    public void executeSave()
    {
        Iterator c = this.components.iterator();
        while (c.hasNext())
        {
            CompilationUnit unit = (CompilationUnit) c.next();
            unit.save();
        }
    }

    public void executeScan(CompilationUnit aUnit)
    {
        // Either read in the old file OR create a new test case

        super.setSourcePath(this.getOutDir());
        System.setProperty("source.path", this.getOutDir());

        CompilationUnit testUnit = this.testFileFactory(aUnit);        

        if (testUnit != null)
        {
            Class testCaseClass = (Class) testUnit.getMainType();
                        
            IElementVisitor visitor = null;

            if (TestCodeGenConstants.CONTRACT_TEST.equals(this.testGenerationType))
            {
                visitor = new ContractTestCaseVisitor(testCaseClass);
            }
            else if (TestCodeGenConstants.COVERAGE_TEST.equals(this.testGenerationType))
            {                
                visitor = new CoverageTestCaseVisitor(testCaseClass);
            }

            if (visitor != null)
            {
                aUnit.accept(visitor);

                this.components.add(testUnit);
            }
        }
    }

    /**
     * @return Returns the outDir.
     */
    public String getOutDir()
    {
        return outDir;
    }

    /**
     * @return
     */
    private String getTestClassName(String componentName)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(componentName);
        if (TestCodeGenConstants.CONTRACT_TEST.equals(this.testGenerationType))
        {
            buffer.append("ContractTest");
        }
        else if (TestCodeGenConstants.COVERAGE_TEST.equals(this.testGenerationType))
        {
            buffer.append("CoverageTest");
        }

        return buffer.toString();
    }

    /**
     * @return Returns the testGenerationType.
     */
    public String getTestGenerationType()
    {
        return testGenerationType;
    }

    /**
     * @param outDir The outDir to set.
     */
    public void setOutDir(String outDir)
    {
        this.outDir = outDir;
    }

    /**
     * @param testGenerationType
     *            The testGenerationType to set.
     */
    public void setTestGenerationType(String testGenerationType)
    {
        this.testGenerationType = testGenerationType;
    }

    /**
     * Factories a Test Case file based on an original class component.
     * 
     * If the test file already exists, then it will add onto the existing Test
     * Case class.
     * 
     * @param aUnit
     * @return
     */
    private CompilationUnit testFileFactory(CompilationUnit aUnit)
    {
        Type mainType = aUnit.getMainType();

        String testClassName = this.getTestClassName(mainType.getName());

        String testFileName = testClassName + KeyWord.JAVA_EXTENSION;

        String testDirectory = this.getOutDir();

        String packageName = aUnit.getPackageName();

        if (packageName != null)
        {
            testDirectory += packageName.replace('.', '/') + '/';
        }

        if (testDirectory != null && !testDirectory.endsWith(KeyWord.FORWARD_SLASH)
                && !testDirectory.endsWith(KeyWord.BACK_SLASH))
        {
            testDirectory += '/';
        }

        // create test compilation unit
        CompilationUnit testUnit = new CompilationUnit();

        Class testClass = null;

        File testDir = new File(testDirectory);
        testDir.mkdirs();
        File testFile = new File(testDirectory, testFileName);

        if (testFile.exists() == true)
        {
            // *** if test file DOES exist            
            try
            {
                ICodeParser parser = CodeParser.getParser();
                CompilationUnit testCaseUnit = parser.parseFile(testFile.getAbsolutePath());
                testClass = (Class) testCaseUnit.getMainType();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        { // *** if test file DOES NOT exist
            testClass = new Class(testClassName);
            testClass.setScope(KeyWord.PUBLIC);
            testClass.setExtendsClass(SUPER_TEST_CASE_CLASS_NAME);

            Method constructor = this.createTestCaseConstructor(testClassName);

            testClass.addMethod(constructor);
        }

        testUnit.setPackageName(packageName);
        testUnit.addType(testClass);

        return testUnit;
    }
}
