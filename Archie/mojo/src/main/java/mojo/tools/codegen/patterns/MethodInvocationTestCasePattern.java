/*
 * Created on May 25, 2006
 *
 */
package mojo.tools.codegen.patterns;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.km.utilities.DateUtil;
import mojo.km.utilities.TextUtil;
import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Class;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.ImportDeclaration;
import mojo.tools.code.JavaComment;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;

/**
 * @author Jim Fisher
 *  
 */
public class MethodInvocationTestCasePattern extends AbstractTestCasePattern
{
    protected Map signatureMap;

    protected Stack callStack;

    private static final String EXCEPTION_VAR = "e";

    private static final String EXECUTE_TEST_COMMENT = "// execute test";

    private static final String TEST_OBJECT_NAME = "testObject";

    private static final String THROWS_PREFIX = "throws";

    private boolean assertExceptions;

    private int counter;

    private Method invokingMethod;

    private Method methodToTest;

    private Class testedClass;

    private Method testCaseMethod;

    /**
     *  
     */
    public MethodInvocationTestCasePattern(Class aTestClass, Stack aCallStack, int aCounter, Map aSignatureMap,
            boolean anAssertExceptions)
    {
        super(aTestClass);
        this.counter = aCounter;
        this.callStack = aCallStack;
        this.imports = new ArrayList();
        this.signatureMap = aSignatureMap;
        this.assertExceptions = anAssertExceptions;
    }

    /**
     *  
     */
    protected void addImport(String importClassString)
    {
        CompilationUnit compUnit = testCaseClass.getCompilationUnit();
        compUnit.addImport(importClassString);
    }

    public void apply()
    {
        try
        {
            this.invokingMethod = (Method) callStack.firstElement();

            this.methodToTest = (Method) callStack.lastElement();

            this.testedClass = (Class) this.invokingMethod.getParent();

            // counter and () are applied afterwards because child classes
            // invoke super.getTestCaseMethodName
            String testCaseMethodName = this.createTestCaseName();

            boolean doNotGenerate = this.applyDoNotGenerate();

            if (doNotGenerate == false)
            {
                this.testCaseMethod = this.createTestCaseMethod(testCaseMethodName);

                this.testCaseClass.addMethod(testCaseMethod);

                this.addImport("mojo.km.messaging.Composite.CompositeResponse");
                this.addImport("mojo.km.dispatch.EventManager");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param testMethod
     * @return
     */
    protected boolean applyDoNotGenerate()
    {
        boolean doNotGenerate = false;

        Method foundMethod = this.scanForExistingTest();

        if (foundMethod != null)
        {
            JavaComment comment = (JavaComment) foundMethod.getComment();

            if (comment != null)
            {
                String tagValue = comment.getTagValue("@doNotGenerate");
                if ("true".equals(tagValue))
                {
                    doNotGenerate = true;
                }
                else
                {
                    this.testCaseClass.removeMethod(foundMethod);
                }

            }
        }

        return doNotGenerate;

    }

    /**
     * @param buffer
     */
    private void buildCloseTryCatchBlock(StringBuffer buffer)
    {
        buffer.append(KeyWord.BLOCK_CLOSE);
        buffer.append(KeyWord.NEWLINE);
        buffer.append(this.getCatchBlock());
        buffer.append(KeyWord.NEWLINE);
    }

    private void buildComponentInstantiation(StringBuffer aBuffer)
    {
        aBuffer.append(this.testedClass.getName());
        aBuffer.append(" ");
        aBuffer.append(TEST_OBJECT_NAME);
        aBuffer.append(" = new ");
        aBuffer.append(this.testedClass.getName());
        aBuffer.append(KeyWord.OPEN_PAREN);
        // TODO Handle constructor parms?
        aBuffer.append(KeyWord.CLOSE_PAREN);
        aBuffer.append(KeyWord.SEMICOLON);
        aBuffer.append(KeyWord.NEWLINE);
    }

    private void buildExceptionVarDeclarations(Iterator t, StringBuffer aBuffer)
    {
        while (t.hasNext())
        {
            String exceptionName = (String) t.next();
            String throwsExceptionVar = this.getThrowsExceptionVar(exceptionName);
            aBuffer.append("boolean " + throwsExceptionVar + " = false;");
            aBuffer.append(KeyWord.NEWLINE);
        }
    }

    private void buildInvocation(List theParms, StringBuffer aBuffer)
    {
        aBuffer.append(TEST_OBJECT_NAME);
        aBuffer.append(KeyWord.PERIOD);
        aBuffer.append(invokingMethod.getName());

        aBuffer.append(KeyWord.OPEN_PAREN);

        Iterator p = theParms.iterator();

        while (p.hasNext())
        {
            Parameter parm = (Parameter) p.next();
            aBuffer.append(parm.getName());
            if (p.hasNext())
            {
                aBuffer.append(KeyWord.COMMA);
            }
        }

        aBuffer.append(KeyWord.CLOSE_PAREN);
        aBuffer.append(KeyWord.SEMICOLON);
        aBuffer.append(KeyWord.NEWLINE);
    }

    private void buildOpenTryCatchBlock(StringBuffer aBuffer)
    {
        aBuffer.append(KeyWord.TRY);
        aBuffer.append(KeyWord.NEWLINE);
        aBuffer.append(KeyWord.BLOCK_OPEN);
        aBuffer.append(KeyWord.NEWLINE);
    }

    protected String createAssertions()
    {
        StringBuffer buffer = new StringBuffer();

        if (this.assertExceptions == true)
        {
            Iterator t = this.invokingMethod.getThrows();

            while (t.hasNext())
            {
                String throwsException = (String) t.next();
                String thrownExceptionVar = this.getThrowsExceptionVar(throwsException);

                // sample output: junit.framework.Assert.assertTrue("SomeException was not thrown", throwsSomeException);
                buffer.append("junit.framework.Assert.assertTrue");
                buffer.append(KeyWord.OPEN_PAREN);
                buffer.append(KeyWord.DOUBLE_QUOTE);
                buffer.append(throwsException);
                buffer.append(" was not thrown.");
                buffer.append(KeyWord.DOUBLE_QUOTE);
                buffer.append(KeyWord.COMMA);
                buffer.append(thrownExceptionVar);
                buffer.append(KeyWord.CLOSE_PAREN);
                buffer.append(KeyWord.SEMICOLON);
            }
        }

        return buffer.toString();
    }

    /**
     * @param initMethod
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Method createTestCaseMethod(String aMethodName) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException
    {
        // TODO Decompose this method

        Method testCaseMethod = new Method(aMethodName);
        testCaseMethod.setScope(KeyWord.PUBLIC);

        testCaseMethod.setComment(this.getTestCaseMethodComment());

        StringBuffer buffer = new StringBuffer();

        List parms = new ArrayList();

        buffer.append(this.getInitBody(parms));
        buffer.append(KeyWord.NEWLINE);
        buffer.append(KeyWord.NEWLINE);

        buffer.append(EXECUTE_TEST_COMMENT);
        buffer.append(KeyWord.NEWLINE);

        this.addImport(this.testedClass.getQualifiedName());
        this.buildComponentInstantiation(buffer);

        Iterator t = this.invokingMethod.getThrows();
        boolean hasTryCatchBlock = t.hasNext();

        if (this.assertExceptions == true)
        {
            this.buildExceptionVarDeclarations(t, buffer);
        }

        if (hasTryCatchBlock == true)
        {
            // open try block
            this.buildOpenTryCatchBlock(buffer);
        }

        this.buildInvocation(parms, buffer);

        if (hasTryCatchBlock == true)
        {
            this.buildCloseTryCatchBlock(buffer);
        }

        if (this.assertExceptions == true)
        {
            buffer.append(this.createAssertions());
        }

        testCaseMethod.setBody(buffer.toString());

        return testCaseMethod;
    }

    /**
     * @param counter2
     * @return
     */
    private String createTestCaseName()
    {
        int maxCounter = counter;
        String testCaseName = this.getTestCaseMethodName() + counter;

        List methods = this.testCaseClass.getMethods(testCaseName);
        if (methods.size() > 0)
        {
            maxCounter = this.getMaxCounter() + 1;

            testCaseName = this.getTestCaseMethodName() + maxCounter;
        }

        return testCaseName;
    }

    protected String getCatchBlock()
    {
        StringBuffer buffer = new StringBuffer();

        Iterator t = this.invokingMethod.getThrows();

        while (t.hasNext())
        {
            String exceptionName = (String) t.next();
            String throwsExceptionVar = this.getThrowsExceptionVar(exceptionName);

            buffer.append(KeyWord.CATCH);
            buffer.append(KeyWord.OPEN_PAREN);
            buffer.append(exceptionName);
            buffer.append(KeyWord.SPACE);
            buffer.append(EXCEPTION_VAR);
            buffer.append(KeyWord.CLOSE_PAREN);
            buffer.append(KeyWord.NEWLINE);

            buffer.append(KeyWord.BLOCK_OPEN);
            buffer.append(KeyWord.NEWLINE);

            buffer.append(KeyWord.INDENTION);
            buffer.append(EXCEPTION_VAR);
            buffer.append(KeyWord.PERIOD);
            buffer.append("printStackTrace();");
            buffer.append(KeyWord.NEWLINE);

            if (this.assertExceptions == true)
            {
                buffer.append(KeyWord.INDENTION);
                buffer.append(KeyWord.NEWLINE);
                buffer.append(KeyWord.INDENTION);
                buffer.append(throwsExceptionVar);
                buffer.append("= true;");
                buffer.append(KeyWord.NEWLINE);
            }

            buffer.append(KeyWord.BLOCK_CLOSE);
            buffer.append(KeyWord.NEWLINE);

        }

        return buffer.toString();
    }

    protected String getClassName(String qualifiedName)
    {
        int endIndex = qualifiedName.lastIndexOf(".");
        String className;
        if (endIndex != -1)
        {
            className = qualifiedName.substring(endIndex + 1, qualifiedName.length());
        }
        else
        {
            className = qualifiedName;
        }
        return className;
    }

    private String getEventServiceType()
    {
        int endIndex = this.testedClass.getName().lastIndexOf("Command");
        String eventType = this.testedClass.getName().substring(0, endIndex) + "Event";
        return eventType;
    }

    protected String getInitBody(List theParms) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getInitComment());
        buffer.append(KeyWord.NEWLINE);

        // init parms
        Iterator p = invokingMethod.getParameters();

        while (p.hasNext())
        {
            Parameter parm = (Parameter) p.next();
            Parameter newParm = new Parameter("", "");
            buffer.append(this.getParmDeclaration(parm, newParm));
            buffer.append(KeyWord.NEWLINE);

            buffer.append("mojo.km.utilities.TestDataUtil.fill(");
            buffer.append(newParm.getName());
            buffer.append(");");
            buffer.append(KeyWord.NEWLINE);

            theParms.add(newParm);
        }

        return buffer.toString();
    }

    /**
     * @return Returns the initComment.
     */
    protected String getInitComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("// TODO setup contract for the following execution path:");
        buffer.append(KeyWord.NEWLINE);
        Iterator m = this.callStack.iterator();
        while (m.hasNext())
        {
            Method method = (Method) m.next();
            buffer.append("//");
            buffer.append(KeyWord.INDENTION);
            String signature = method.getMethodSignature();
            buffer.append(signature);
            buffer.append(KeyWord.NEWLINE);
        }

        buffer.append("//");
        buffer.append(KeyWord.NEWLINE);

        return buffer.toString();
    }

    private int getMaxCounter()
    {
        int maxCounter = this.counter;
        List methods = this.testCaseClass.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            String counterMethodName = method.getName();
            int beginIndex = counterMethodName.indexOf(this.getTestNameExtension());
            if (beginIndex != -1)
            {
                beginIndex += this.getTestNameExtension().length();
                String counterString = counterMethodName.substring(beginIndex, counterMethodName.length());
                int counterInt = Integer.parseInt(counterString);
                if (counterInt > maxCounter)
                {
                    maxCounter = counterInt;
                }
            }
        }
        return maxCounter;
    }

    protected String getPackageName(String qualifiedName)
    {
        int endIndex = qualifiedName.lastIndexOf(".");
        String packageName;
        if (endIndex != -1)
        {
            packageName = qualifiedName.substring(0, endIndex);
        }
        else
        {
            packageName = qualifiedName;
        }
        return packageName;
    }

    /**
     * @param parm
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    protected String getParmDeclaration(Parameter parm, Parameter newParm) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException
    {
        String parmDecl = null;

        Object targetJavaClass = java.lang.Class.forName(this.testedClass.getQualifiedName()).newInstance();
        if (targetJavaClass instanceof mojo.km.context.ICommand)
        {
            String eventType = this.getEventServiceType();

            String subsystem = this.getServiceSubsystem(this.testedClass.getQualifiedName());
            this.addImport("messaging." + subsystem + "." + eventType);

            String fieldVar = "serviceEvent";
            StringBuffer parmBuffer = new StringBuffer();
            parmBuffer.append(eventType);
            parmBuffer.append(" ");
            parmBuffer.append(fieldVar);
            parmBuffer.append(" = new ");
            parmBuffer.append(eventType);
            parmBuffer.append("();");
            parmDecl = parmBuffer.toString();

            newParm.setName(fieldVar);
            newParm.setType(this.getEventServiceType());
        }

        return parmDecl;
    }

    /**
     * @param parm
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("unused")
	private String getQualfiedParmType(Parameter parm) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException
    {
        String qualifiedName = null;
        String parmType = parm.getType();
        if (parmType.indexOf('.') != -1)
        {
            qualifiedName = parmType;
        }
        else
        {
            CompilationUnit compUnit = this.testedClass.getCompilationUnit();
            Iterator i = compUnit.getImports();
            boolean done = false;
            while (i.hasNext() && done == false)
            {
                ImportDeclaration importDecl = (ImportDeclaration) i.next();

                // onDemand imports trail with a .*
                if (importDecl.isOnDemand() == false)
                {
                    String imp = importDecl.getName();
                    int endIndex = imp.lastIndexOf('.');
                    if (endIndex != -1)
                    {
                        String className = imp.substring(endIndex + 1, imp.length());
                        if (className.equals(parmType))
                        {
                            qualifiedName = imp;
                            done = true;
                        }
                    }
                }
            }
        }

        if (qualifiedName.equals("mojo.km.messaging.IEvent"))
        {
            Object targetJavaClass = java.lang.Class.forName(this.testedClass.getQualifiedName()).newInstance();
            if (targetJavaClass instanceof mojo.km.context.ICommand)
            {
                // service test case
                String eventClass = this.getEventServiceType();
                int startIndex = this.testedClass.getPackage().getName().indexOf('.') + 1;
                int endIndex = this.testedClass.getPackage().getName().lastIndexOf('.');
                String subsystem = this.testedClass.getPackage().getName().substring(startIndex, endIndex);
                qualifiedName = "messaging." + subsystem + "." + eventClass;
            }
        }

        return qualifiedName;
    }

    /**
     * @param qualifiedName
     * @return
     */
    private String getServiceSubsystem(String qualifiedName)
    {
        // TODO Move to util
        int beginIndex = qualifiedName.indexOf('.');
        int endIndex = qualifiedName.indexOf('.', beginIndex + 1);
        return qualifiedName.substring(beginIndex + 1, endIndex);
    }

    protected String getTestCaseMethodComment()
    {
        /*
         * JavaComment javaComment = (JavaComment)
         * this.testCaseClass.getComment();
         * 
         * Map methods = new HashMap();
         * 
         * Iterator t = javaComment.getTags().iterator(); while(t.hasNext()) {
         * String tag = (String) t.next(); int endIndex = tag.indexOf(" "); }
         */

        StringBuffer buffer = new StringBuffer();
        Iterator m = this.callStack.iterator();
        buffer.append("\n");
        buffer.append("@callStack ");
        while (m.hasNext())
        {
            Method method = (Method) m.next();
            String signature = method.getMethodSignature();
            String signatureId = this.testCaseClass.getProperty(signature);
            buffer.append(signatureId);
            if (m.hasNext())
            {
                buffer.append("|");
            }
        }

        buffer.append("\n");

        buffer.append(TestCodeGenConstants.DO_NOT_GENERATE_TAG + " false");
        buffer.append("\n");
        buffer.append("* ");
        buffer.append(TestCodeGenConstants.GENERATE_DATE_TAG);
        buffer.append(" ");
        buffer.append(DateUtil.dateToString(new Date(), TestCodeGenConstants.GENERATE_DATE_FORMAT));
        buffer.append("\n");
        buffer.append("* ");
        buffer.append(TestCodeGenConstants.TEST_TYPE_TAG);
        buffer.append(" ");
        buffer.append(this.getTestType());

        return buffer.toString();
    }

    /**
     * @return
     */
    protected String getTestCaseMethodName()
    {
        StringBuffer buffer = new StringBuffer();

        Method firstMethod = (Method) this.callStack.firstElement();
        Method lastMethod = (Method) this.callStack.lastElement();

        buffer.append("test_");
        buffer.append(firstMethod.getName());

        if (this.callStack.size() > 1)
        {
            buffer.append("_");
            buffer.append(lastMethod.getName());
        }

        return buffer.toString();
    }

    /* (non-Javadoc)
     * @see mojo.tools.codegen.patterns.AbstractTestCasePattern#getTestNameExtension()
     */
    protected String getTestNameExtension()
    {
        return TestCodeGenConstants.METHOD;
    }

    protected String getTestType()
    {
        return TestCodeGenConstants.METHOD_TYPE;
    }

    private String getThrowsExceptionVar(String exceptionName)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(MethodInvocationTestCasePattern.THROWS_PREFIX);
        buffer.append(KeyWord.UNDERSCORE);
        buffer.append(exceptionName);

        TextUtil.replaceAll(buffer, KeyWord.PERIOD, KeyWord.UNDERSCORE);
        exceptionName = buffer.toString();

        return exceptionName;
    }

    /**
     * @param testMethod
     * @param anIfStatement
     * @return found if an exsting test already exists
     */
    protected Method scanForExistingTest()
    {
        // TODO grab constants from naming

        Method foundMethod = null;

        List methods = this.testCaseClass.getMethods();
        int methodLen = methods.size();

        boolean done = false;

        Stack callStackAsStrings = CodeUtil.getCallStackForScan(this.callStack);

        for(int i=0;i<methodLen && done == false;i++)
        {
            // search each method in this test case class for a potential match
            // of this ifStatement and callStack
            Method method = (Method) methods.get(i);

            JavaComment comment = (JavaComment) method.getComment();

            if (comment != null)
            {

                String testType = comment.getTagValue(TestCodeGenConstants.TEST_TYPE_TAG);

                if (TestCodeGenConstants.METHOD_TYPE.equals(testType))
                {
                    // only compare other method tests

                    String callStackString = comment.getTagValue(TestCodeGenConstants.CALL_STACK_TAG);

                    Stack thisMethodCallStack = CodeUtil.parseCallStack(callStackString, this.signatureMap);

                    if (thisMethodCallStack.equals(callStackAsStrings))
                    {
                        done = true;
                        foundMethod = method;
                    }

                }
            }
        }
        return foundMethod;
    }

    protected void setAssertExceptions(boolean anAssertExcceptions)
    {
        this.assertExceptions = anAssertExcceptions;
    }

    /**
     * @return Returns the methodToTest.
     */
    public Method getMethodToTest()
    {
        return this.methodToTest;
    }
    
    /**
     * @return Returns the methodToTest.
     */
    public Method getInvokingMethod()
    {
        return this.invokingMethod;
    }
    
    /**
     * @return Returns the testCaseMethod.
     */
    public Method getTestCaseMethod()
    {
        return testCaseMethod;
    }
}
