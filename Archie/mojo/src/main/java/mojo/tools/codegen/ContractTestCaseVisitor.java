/*
 * Created on May 24, 2006
 *
 */
package mojo.tools.codegen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import java.util.Map;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Attribute;
import mojo.tools.code.Block;
import mojo.tools.code.BlockComment;
import mojo.tools.code.Class;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.Comment;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Expression;
import mojo.tools.code.IElementVisitor;
import mojo.tools.code.IfStatement;
import mojo.tools.code.ImportDeclaration;
import mojo.tools.code.Initializer;
import mojo.tools.code.Interface;
import mojo.tools.code.JavaComment;
import mojo.tools.code.KeyWord;
import mojo.tools.code.LineComment;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.Statement;
import mojo.tools.code.ThrowStatement;
import mojo.tools.code.Type;
import mojo.tools.code.VariableDeclarationStatement;
import mojo.tools.codegen.patterns.IPattern;
import mojo.tools.codegen.patterns.PreConditionTestCasePattern;

/**
 * @author Jim Fisher
 *  
 */
public class ContractTestCaseVisitor implements IElementVisitor
{
    static private final String SUPER_TEST_CASE_CLASS_NAME = "test.TestCase";

    private Map blockCounter;

    private Map signatureMap;

    private Class testCaseClass;

    /** counter for all methods on tested class including private methods */
    private int testedClassMethodCounter;

    private static final String CONTRACT_FAIL_ERROR_CLASS = "mojo.km.contract.ContractFailError";

    private static final String JUNIT_ASSERT_CLASS = "junit.framework.Assert";

    public ContractTestCaseVisitor(Class aTestCaseClass)
    {
        this.blockCounter = new HashMap();
        this.testCaseClass = aTestCaseClass;
        this.signatureMap = new HashMap();
    }

    /**
     * @param method2
     * @param class1
     */
    private void addTagEntry(Method aMethod, Class aClass)
    {
        String signature = aMethod.getMethodSignature();

        Comment comment = this.testCaseClass.getComment();

        if (comment == null)
        {
            this.testCaseClass.setComment("");
        }

        String value = this.testCaseClass.getProperty(signature);

        if (value == null)
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append(KeyWord.AT);
            buffer.append(signature);

            Integer counter = new Integer(++this.testedClassMethodCounter);

            this.testCaseClass.setProperty(signature, counter.toString());
            this.signatureMap.put(counter.toString(), signature);
        }
    }

    private void createPreConditionTestCase(Method aMethod, String aPreCondition)
    {
        int counter = this.getCounter(TestCodeGenConstants.PRE_CONDITION);

        aMethod.addThrows(CONTRACT_FAIL_ERROR_CLASS);
        
        Stack callStack = CodeUtil.getCallStack(aMethod);

        boolean assertExceptions = true;

        IPattern testCasePattern = new PreConditionTestCasePattern(this.testCaseClass, callStack, counter,
                signatureMap, aPreCondition, assertExceptions);
        testCasePattern.apply();

        this.testCaseClass.getCompilationUnit().addImport(JUNIT_ASSERT_CLASS);
        this.testCaseClass.getCompilationUnit().addImport(CONTRACT_FAIL_ERROR_CLASS);
    }

    private int getCounter(String key)
    {
        Integer counter = (Integer) this.blockCounter.get(key);
        if (counter == null)
        {
            counter = new Integer(1);
            this.blockCounter.put(key, counter);
        }
        else
        {
            int counterInt = counter.intValue();
            counterInt++;
            this.blockCounter.put(key, new Integer(counterInt));
            counter = (Integer) this.blockCounter.get(key);
        }
        return counter.intValue();
    }

    public void visit(Attribute attribute)
    {

    }

    public void visit(BlockComment comment)
    {

    }

    public void visit(Class aClass)
    {
        // Create tag lookup for computing method callstack
        List methods = aClass.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            this.addTagEntry(method, aClass);
        }

        // generate test cases from class methods
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }
    }

    public void visit(Comment comment)
    {
    }

    public void visit(CompilationUnit aCompUnit)
    {
        // TODO Add support for non-main classes in future

        Type mainType = aCompUnit.getMainType();
        mainType.accept(this);
    }

    public void visit(Initializer init)
    {
    }

    public void visit(Interface anInterface)
    {
    }

    public void visit(JavaComment javaComment)
    {
    }

    public void visit(LineComment lineComment)
    {
    }

    public void visit(Method aMethod)
    {
        JavaComment comment = (JavaComment) aMethod.getComment();

        List preConditions = CodeUtil.parseLongTagComments(TestCodeGenConstants.PRE_CONDITION_TAG, comment);

        Iterator i = preConditions.iterator();
        while (i.hasNext())
        {
            String preCondition = (String) i.next();
            this.createPreConditionTestCase(aMethod, preCondition);
        }
    }

    public void visit(Parameter aParameter)
    {
    }

    public void visit(Statement aStatement)
    {

    }

    public void visit(Type aType)
    {
        List methods = aType.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }
    }

    public void visit(Block aBlock)
    {
    }

    /* (non-Javadoc)
     * @see mojo.tools.code.IElementVisitor#visit(mojo.tools.code.Expression)
     */
    public void visit(Expression anExpression)
    {
    }

    public void visit(IfStatement anIfStatement)
    {
    }

    public void visit(ThrowStatement aThrowStatement)
    {

    }

    public void visit(VariableDeclarationStatement aVariableStatement)
    {     
    }

    public void visit(ImportDeclaration anImport)
    {        
    }

}
