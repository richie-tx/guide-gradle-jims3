package mojo.tools.codegen.patterns;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Class;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.IfStatement;
import mojo.tools.code.JavaComment;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;

/**
 * @author Jim Fisher
 *  
 */
public class IfStatementTestCasePattern extends MethodInvocationTestCasePattern
{
    protected IfStatement ifStatement;

    /**
     * @param aClass
     */
    public IfStatementTestCasePattern(Class aTestClass, Stack aCallStack, int aCounter, Map aSignatureMap,
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

    protected String getTestCaseMethodName()
    {
        return super.getTestCaseMethodName() + "_" + TestCodeGenConstants.IF_STATEMENT;
    }

    protected String getInitComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.getInitComment());
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// for expression: ");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.INDENTION);
        buffer.append("if( ");
        buffer.append(ifStatement.getExpression().getBody());
        buffer.append(" )");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.NEWLINE);

        return buffer.toString();
    }

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
        buffer.append(TestCodeGenConstants.IF_STATEMENT_TAG);
        buffer.append(" ");
        buffer.append(this.ifStatement.getExpression().toString());

        return buffer.toString();
    }

    protected Method scanForExistingTest()
    {
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

                if (TestCodeGenConstants.IF_STATEMENT_TYPE.equals(testType))
                {
                    // only need to compare other if-statements

                    String expression = CodeUtil.parseLongTagComment(TestCodeGenConstants.IF_STATEMENT_TAG, comment);

                    StringBuffer expressionBuffer = new StringBuffer(expression);
                    expression = CodeUtil.formatStatementForComparison(expressionBuffer);

                    String ifExpressionString = ifStatement.getExpression().getBody().trim();
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

    protected String getTestType()
    {
        return TestCodeGenConstants.IF_STATEMENT_TYPE;
    }

    protected String getTestNameExtension()
    {
        return TestCodeGenConstants.IF_STATEMENT;
    }
}
