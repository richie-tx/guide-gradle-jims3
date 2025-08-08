/*
 * Created on May 25, 2006
 *
 */
package mojo.tools.codegen.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Class;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.IfStatement;
import mojo.tools.code.JavaComment;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;

/**
 * @author Jim Fisher
 *  
 */
public class ElseStatementTestCasePattern extends MethodInvocationTestCasePattern
{
    // TODO Move literals to naming

    private IfStatement ifStatement;

    /**
     * @param aClass
     */
    public ElseStatementTestCasePattern(Class aTestClass, Stack aCallStack, int aCounter, Map aSignatureMap,
            IfStatement aStatement, boolean anAssertExceptions)
    {
        super(aTestClass, aCallStack, aCounter, aSignatureMap, anAssertExceptions);
        this.ifStatement = aStatement;
    }

    public void apply()
    {
        super.apply();
        this.addImport("junit.framework.Assert");
        this.addImport("mojo.km.utilities.MessageUtil");
        this.addImport("java.util.Collection");
    }

    protected String getInitComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.getInitComment());
        buffer.append(KeyWord.NEWLINE);

        // TODO Show all not if conditions

        buffer.append("// for expression: ");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.INDENTION);
        buffer.append("not if( ");
        buffer.append(this.getElseExpression());
        buffer.append(" )");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.NEWLINE);

        return buffer.toString();
    }

    // TODO Put these in a utility

    protected String createAssertions()
    {
        StringBuffer buffer = new StringBuffer();
        Iterator r = this.ifStatement.getProperties("EventManager.REPLY");

        while (r.hasNext())
        {
            String replyType = (String) r.next();
            super.addImport(replyType);
            replyType = super.getClassName(replyType);
            buffer.append(this.createAssertReplyByCount(replyType, AbstractTestCasePattern.ONE));
        }

        return buffer.toString();
    }

    private String createAssertReplyByCount(String classType, int multiplicity)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("//");
        buffer.append(KeyWord.NEWLINE);
        buffer.append("// TODO confirm assertions");
        buffer.append(KeyWord.NEWLINE);
        buffer.append("//");
        buffer.append(KeyWord.NEWLINE);
        buffer.append("// get service response");
        buffer.append(KeyWord.NEWLINE);
        buffer.append("CompositeResponse compositeResponse = ");
        buffer.append("(CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST).getReply();");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("Collection replies = MessageUtil.compositeToCollection(compositeResponse, ");
        buffer.append(classType);
        buffer.append(".class);");
        buffer.append(KeyWord.NEWLINE);

        buffer.append(classType);
        buffer.append(" responseEvent = ");
        buffer.append("(");
        buffer.append(classType);
        buffer.append(") ");
        buffer.append("MessageUtil.filterComposite(compositeResponse, ");
        buffer.append(classType);
        buffer.append(".class);");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// assertions");
        buffer.append(KeyWord.NEWLINE);
        buffer.append("//");
        buffer.append(KeyWord.NEWLINE);
        if (multiplicity == ZERO)
        {
            buffer.append("Assert.assertNull(\"replies found for type: " + classType + "\", responseEvent);");
            buffer.append(KeyWord.NEWLINE);
        }
        else if (multiplicity == ONE)
        {
            buffer.append("Assert.assertTrue(\"should be only ONE (found \"+replies.size()+\") reply for type: "
                    + classType + "\",replies.size() == 1);");
            buffer.append(KeyWord.NEWLINE);
        }
        else if (multiplicity == MULTIPLE)
        {
            buffer.append("Assert.assertTrue(\"should be MULTIPLE (found \"+replies.size()+\") replies for type: "
                    + classType + "\",replies.size() > 1);");
            buffer.append(KeyWord.NEWLINE);
        }
        return buffer.toString();
    }

    protected String getTestCaseMethodComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.getTestCaseMethodComment());

        buffer.append("\n");
        buffer.append("* ");
        buffer.append(TestCodeGenConstants.ELSE_STATEMENT_TAG);
        buffer.append(TestCodeGenConstants.BEGIN_ELSE_COMMENT);
        buffer.append("(");

        String elseExpression = this.getElseExpression();
        buffer.append(elseExpression);

        buffer.append(")");

        return buffer.toString();
    }

    private String getElseExpression()
    {
        StringBuffer buffer = new StringBuffer();
        List parentIfStatements = this.getParentIfStatements();
        Iterator i = parentIfStatements.iterator();

        int size = parentIfStatements.size();

        while (i.hasNext())
        {
            if (size > 1)
            {
                buffer.append("(");
            }
            IfStatement parentIf = (IfStatement) i.next();
            buffer.append(parentIf.getExpression().toString());
            if (size > 1)
            {
                buffer.append(")");
            }
            if (i.hasNext())
            {
                buffer.append(" && ");
            }
        }

        return buffer.toString();
    }

    /**
     * @return
     */
    private List getParentIfStatements()
    {
        List parentStatements = new ArrayList();

        // every else-statement block has at least one parent if-statement
        parentStatements.add(this.ifStatement);

        IfStatement myIfStatement = this.ifStatement;

        boolean done = false;
        while (done == false)
        {
            CodeElement parent = myIfStatement.getParent();
            if (parent instanceof IfStatement)
            {
                parentStatements.add(parent);
                myIfStatement = (IfStatement) parent;
            }
            else
            {
                done = true;
            }
        }

        Collections.reverse(parentStatements);
        return parentStatements;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.patterns.AbstractTestCasePattern#getTestType()
     */
    protected String getTestType()
    {
        // TODO Auto-generated method stub
        return TestCodeGenConstants.ELSE_STATEMENT_TYPE;
    }

    protected Method scanForExistingTest()
    {
        Method foundMethod = null;

        List methods = this.testCaseClass.getMethods();

        boolean done = false;

        Stack callStackAsStrings = CodeUtil.getCallStackForScan(this.callStack);

        int methodLen = methods.size();
        for(int i=0;i<methodLen && done == false;i++)
        {
            // search each method in this test case class for a potential match
            // of this ifStatement and callStack
            Method method = (Method) methods.get(i);

            JavaComment comment = (JavaComment) method.getComment();

            if (comment != null)
            {

                String testType = comment.getTagValue(TestCodeGenConstants.TEST_TYPE_TAG);

                if (TestCodeGenConstants.ELSE_STATEMENT_TYPE.equals(testType))
                {
                    // only need to compare other if-statements

                    String expression = CodeUtil.parseLongTagComment(TestCodeGenConstants.ELSE_STATEMENT_TAG, comment);
                    final String BEGIN_ELSE_COMMENT = TestCodeGenConstants.BEGIN_ELSE_COMMENT + "(";
                    final String END_ELSE_COMMENT = ")";
                    int beginIndex = expression.indexOf(BEGIN_ELSE_COMMENT);
                    int endIndex = expression.lastIndexOf(END_ELSE_COMMENT);
                    expression = expression.substring(beginIndex + BEGIN_ELSE_COMMENT.length(), endIndex);
                    StringBuffer expressionBuffer = new StringBuffer(expression);
                    expression = CodeUtil.formatStatementForComparison(expressionBuffer);

                    String ifExpressionString = this.getElseExpression();
                    StringBuffer ifExpressionBuffer = new StringBuffer(ifExpressionString);
                    ifExpressionString = CodeUtil.formatStatementForComparison(ifExpressionBuffer);

                    if (ifExpressionString.equals(expression))
                    {
                        // if-statements match (possibly still a different test
                        // path)

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
        }
        return foundMethod;
    }

    protected String getTestCaseMethodName()
    {
        return super.getTestCaseMethodName() + "_" + TestCodeGenConstants.ELSE_STATEMENT;
    }

    protected String getTestNameExtension()
    {
        return TestCodeGenConstants.ELSE_STATEMENT;
    }
}
