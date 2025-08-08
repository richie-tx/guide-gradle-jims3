/*
 * Created on May 24, 2006
 *
 */
package mojo.tools.codegen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.AbstractStatement;
import mojo.tools.code.Attribute;
import mojo.tools.code.Block;
import mojo.tools.code.BlockComment;
import mojo.tools.code.Class;
import mojo.tools.code.CodeElement;
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
import mojo.tools.codegen.patterns.ElseStatementTestCasePattern;
import mojo.tools.codegen.patterns.IfStatementTestCasePattern;
import mojo.tools.codegen.patterns.MethodInvocationTestCasePattern;
import mojo.naming.CodeScanConstants;

/**
 * @author Jim Fisher
 *  
 */
public class CoverageTestCaseVisitor implements IElementVisitor
{
    static private final String SUPER_TEST_CASE_CLASS_NAME = "test.TestCase";

    private Map blockCounter;

    private Map signatureMap;

    private Class testCaseClass;

    private CompilationUnit testCompUnit;

    /** counter for all methods on tested class including private methods */
    private int testedClassMethodCounter;

    public CoverageTestCaseVisitor(Class aTestCaseClass)
    {
        this.testCaseClass = aTestCaseClass;
        this.testCompUnit = aTestCaseClass.getCompilationUnit();
        this.blockCounter = new HashMap();
        this.signatureMap = new HashMap();
    }

    /**
     * @param method2
     * @param class1
     */
    private void addTagEntry(Method aMethod, Class aClass)
    {
        // TODO Migrate to Class abstraction
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

    /**
     * @param myIfStatement
     * @param myElseStatement
     */
    private Method createElseTestCase(IfStatement anIfStatement)
    {
        Stack callStack = CodeUtil.getCallStack(anIfStatement);

        int counter = this.getCounter(TestCodeGenConstants.ELSE_STATEMENT);

        boolean assertExceptions = true;

        // TODO Create abstraction for call stack
        ElseStatementTestCasePattern testCasePattern = new ElseStatementTestCasePattern(this.testCaseClass, callStack,
                counter, signatureMap, anIfStatement, assertExceptions);

        testCasePattern.apply();

        return testCasePattern.getTestCaseMethod();
    }

    private Method createTestCase(IfStatement anIfStatement)
    {
        int counter = this.getCounter(TestCodeGenConstants.IF_STATEMENT);

        Stack callStack = CodeUtil.getCallStack(anIfStatement);

        boolean assertExceptions = true;

        // TODO Create abstraction for call stack
        IfStatementTestCasePattern testCasePattern = new IfStatementTestCasePattern(this.testCaseClass, callStack,
                counter, signatureMap, anIfStatement, assertExceptions);
        testCasePattern.apply();

        return testCasePattern.getTestCaseMethod();
    }

    private Method createTestCase(Method aMethod)
    {
        int counter = this.getCounter(TestCodeGenConstants.METHOD);

        Stack callStack = CodeUtil.getCallStack(aMethod);

        boolean assertExceptions = true;

        // TODO Create abstraction for call stack
        MethodInvocationTestCasePattern pattern = new MethodInvocationTestCasePattern(this.testCaseClass, callStack,
                counter, signatureMap, assertExceptions);

        pattern.apply();

        return pattern.getTestCaseMethod();
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

    /**
     * @param aCodeElement
     * @return
     */
    private Class getParentClass(CodeElement aCodeElement)
    {
        Class parent = null;
        if (aCodeElement instanceof Class)
        {
            parent = (Class) aCodeElement;
        }
        else
        {
            parent = this.getParentClass(aCodeElement.getParent());
        }

        return parent;
    }

    /**
     * @return Returns the testCaseClass.
     */
    public Class getTestCaseClass()
    {
        return testCaseClass;
    }

    private String upperFirstChar(String s)
    {
        char[] chars = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    public void visit(AbstractStatement aStatement)
    {
        System.out.println("This method should be removed");
    }

    public void visit(Attribute attribute)
    {

    }

    public void visit(Block aBlock)
    {
        Iterator s = aBlock.getStatements().iterator();
        while (s.hasNext())
        {
            AbstractStatement statement = (AbstractStatement) s.next();
            statement.accept(this);
        }
    }

    public void visit(BlockComment comment)
    {

    }

    public void visit(Class aClass)
    {
        // aClass.setProperty(TestCodeGenConstants.TEST_GENERATION_TYPE_TAG, TestCodeGenConstants.COVERAGE_TEST);

        // Create tag lookup for computing method callstack
        List methods = aClass.getMethods();
        int methodLen = methods.size();
        synchronized (aClass)
        {
            for(int i=0;i<methodLen;i++)
            {
                Method method = (Method) methods.get(i);
                this.addTagEntry(method, aClass);
            }

            // generate test cases from class methods
            //List methods = Collections.synchronizedList(aClass.getMethodList());

            for(int i=0;i<methodLen;i++)
            {
                Method method = (Method) methods.get(i);
                method.accept(this);
            }
        }
    }

    public void visit(Comment comment)
    {
    }

    public void visit(CompilationUnit aCompUnit)
    {
        // TODO Add support for non-main types
        Type mainType = aCompUnit.getMainType();

        // ensure actually testing a Class as opposed to an Interface
        if (mainType instanceof Class)
        {
        }
        mainType.accept(this);

        Iterator i = aCompUnit.getImports();
        while (i.hasNext())
        {
            ImportDeclaration imp = (ImportDeclaration) i.next();
            imp.accept(this);
        }
    }

    public void visit(Expression anExpression)
    {
    }

    public void visit(IfStatement anIfStatement)
    {
        Method ifTestMethod = this.createTestCase(anIfStatement);

        AbstractStatement thenStatement = anIfStatement.getThenStatement();
        if (thenStatement != null)
        {
            this.parseForReplyDispatch(thenStatement, ifTestMethod);
            thenStatement.accept(this);
        }

        AbstractStatement myElseStatement = anIfStatement.getElseStatement();
        if (myElseStatement != null)
        {
            if (myElseStatement instanceof IfStatement)
            {
                myElseStatement.accept(this);
            }
            else if (myElseStatement instanceof Block)
            {
                Method elseTestMethod = this.createElseTestCase(anIfStatement);
                this.parseForReplyDispatch((Block) myElseStatement, elseTestMethod);
            }
        }
    }

    private void parseForReplyDispatch(Expression anExpression, Method aTestMethod)
    {
        String body = anExpression.getBody();
        if (body.startsWith(CodeScanConstants.POST_REPLY_EXPRESSION))
        {
            this.addResponseAssertion(aTestMethod, anExpression);
        }
    }
    
    private void parseForReplyDispatch(AbstractStatement aStatement, Method aTestMethod)
    {
        if (aStatement instanceof Block)
        {
            Iterator s = ((Block) aStatement).getStatements().iterator();
            while (s.hasNext())
            {
                AbstractStatement stmt = (AbstractStatement) s.next();
                if (stmt != null && stmt instanceof Expression && ((Expression) stmt).getBody() != null)
                {
                    this.parseForReplyDispatch((Expression) stmt, aTestMethod);                    
                }
            }
        } else if(aStatement instanceof Expression)
        {
            this.parseForReplyDispatch((Expression) aStatement, aTestMethod);
        }
    }

    private VariableDeclarationStatement findVariableDeclaration(Block aBlock, String aVarName)
    {
        VariableDeclarationStatement varStmt = null;
        Iterator s = aBlock.getStatements().iterator();
        while (s.hasNext())
        {
            Object stmt = s.next();
            if (stmt instanceof VariableDeclarationStatement)
            {
                VariableDeclarationStatement candidate = (VariableDeclarationStatement) stmt;
                Iterator n = candidate.getNames();
                while (n.hasNext())
                {
                    String name = (String) n.next();
                    if (aVarName.equals(name))
                    {
                        varStmt = candidate;
                    }
                }
            }
        }
        return varStmt;
    }

    private VariableDeclarationStatement findVariableDeclaration(CodeElement aCodeElement, String aVarName)
    {
        VariableDeclarationStatement varStmt = null;
        if (aCodeElement != null)
        {
            if (aCodeElement instanceof Block)
            {
                varStmt = this.findVariableDeclaration((Block) aCodeElement, aVarName);
                if (varStmt == null)
                {
                    // keep searching...
                    CodeElement parent = aCodeElement.getParent();
                    varStmt = this.findVariableDeclaration(parent, aVarName);
                }
            }
            else
            {
                // traverse up the stack
                CodeElement parent = aCodeElement.getParent();
                varStmt = this.findVariableDeclaration(parent, aVarName);
            }
        }
        return varStmt;
    }

    private void addResponseAssertion(Method aMethod, Expression anExpr)
    {
        String dispatchExpr = anExpr.getBody();
        int length = CodeScanConstants.POST_REPLY_EXPRESSION.length();
        int startIndex = dispatchExpr.indexOf(KeyWord.OPEN_PAREN, length);
        int endIndex = dispatchExpr.lastIndexOf(KeyWord.CLOSE_PAREN);
        String dispatchVarName = dispatchExpr.substring(startIndex + 1, endIndex);
        dispatchVarName = dispatchVarName.trim();
        System.out.println(dispatchVarName);

        VariableDeclarationStatement varStmt = this.findVariableDeclaration(anExpr, dispatchVarName);

        if (varStmt != null)
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append(KeyWord.NEWLINE);

            buffer
                    .append("CompositeResponse response = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST).getReply();");
            buffer.append(KeyWord.NEWLINE);

            buffer.append("Collection messages = MessageUtil.compositeToCollection(response,");
            buffer.append(varStmt.getType());
            buffer.append(".class");
            buffer.append(KeyWord.CLOSE_PAREN);
            buffer.append(KeyWord.SEMICOLON);
            buffer.append(KeyWord.NEWLINE);

            buffer.append("Assert.assertTrue(\"expected response event(s) of type: ");
            buffer.append(varStmt.getType());
            buffer.append(KeyWord.DOUBLE_QUOTE);
            buffer.append(KeyWord.COMMA);
            buffer.append(" messages.size() > 0");
            buffer.append(KeyWord.CLOSE_PAREN);
            buffer.append(KeyWord.SEMICOLON);
            buffer.append(KeyWord.NEWLINE);

            String body = aMethod.getBody();
            body += buffer.toString();
            aMethod.setBody(body);
        }
        System.out.println("adding assertion to: " + aMethod.getName());
    }

    public void visit(ImportDeclaration anImport)
    {
        testCompUnit.addImport(anImport);
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
        String body = aMethod.getBody();

        boolean isValidTestMethod = (aMethod.isConstructor() == false && body != null && body.trim().equals("") == false);

        if (isValidTestMethod)
        {
            if (KeyWord.PUBLIC.equals(aMethod.getScope()))
            {
                this.createTestCase(aMethod);
            }
            else if (KeyWord.PRIVATE.equals(aMethod.getScope()))
            {
                // Search for uses of this private method in the parent class
                Class parentClass = this.getParentClass(aMethod);
                List methods = parentClass.getMethods();
                int methodLen = methods.size();
                for(int i=0;i<methodLen;i++)
                {
                    Method myMethod = (Method) methods.get(i);
                    Iterator thisExpressions = myMethod.getProperties(CodeScanConstants.METHOD_INVOCATION);
                    while (thisExpressions.hasNext())
                    {
                        String thisExpressionInvocations = (String) thisExpressions.next();
                        if (thisExpressionInvocations.equals(aMethod.getName()))
                        {
                            this.createTestCase(myMethod);
                        }
                    }
                }
            }

            Block block = aMethod.getBlock();
            block.accept(this);
        }
    }

    public void visit(Parameter aParameter)
    {
    }

    public void visit(Statement aStatement)
    {
        System.out.println("Unhandled statement:\n" + aStatement);
    }

    public void visit(ThrowStatement aThrowStatement)
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

    public void visit(VariableDeclarationStatement aVariableStatement)
    {
    }

}
