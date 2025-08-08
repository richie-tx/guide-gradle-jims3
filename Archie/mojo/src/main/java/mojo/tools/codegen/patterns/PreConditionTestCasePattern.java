/*
 * Created on Jun 8, 2006
 *
 */
package mojo.tools.codegen.patterns;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mojo.naming.TestCodeGenConstants;
import mojo.tools.code.Class;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.JavaComment;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;

/**
 * @author Jim Fisher
 *  
 */
public class PreConditionTestCasePattern extends MethodInvocationTestCasePattern
{
    private String preCondition;

    /**
     * @param aTestClass
     * @param aCallStack
     * @param aCounter
     * @param aSignatureMap
     * @param aStatement
     */
    public PreConditionTestCasePattern(Class aTestClass, Stack aCallStack, int aCounter, Map aSignatureMap, String aPreCondition,
            boolean anAssertExceptions)
    {
        super(aTestClass, aCallStack, aCounter, aSignatureMap, anAssertExceptions);
        this.preCondition = aPreCondition;
    }

    protected String getTestType()
    {
        return TestCodeGenConstants.PRE_CONDITION_TYPE;
    }

    protected String getTestNameExtension()
    {
        return TestCodeGenConstants.PRE_CONDITION;
    }

    protected String getInitComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.getInitComment());
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// for violating pre-condition: ");
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.INDENTION);
        buffer.append(preCondition);
        buffer.append(KeyWord.NEWLINE);

        buffer.append("// ");
        buffer.append(KeyWord.NEWLINE);

        return buffer.toString();
    }

    protected String getInitBody(List theParms) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getInitComment());
        buffer.append(KeyWord.NEWLINE);

        // init parms
        Iterator p = super.getInvokingMethod().getParameters();

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

    /*
     * protected String createAssertions() { StringBuffer buffer = new StringBuffer(); byte[] bytes =
     * TextUtil.searchAndReplace(this.preCondition.getBytes(), KeyWord.DOUBLE_QUOTE, KeyWord.ESCAPED_DOUBLE_QUOTE);
     * String expression = new String(bytes); buffer.append("Assert.assertTrue(\"test did not fail for: ");
     * buffer.append(this.testedClass.getQualifiedName()); buffer.append(KeyWord.PERIOD);
     * buffer.append(this.methodToTest.getName()); buffer.append("\", contractFailure);");
     * buffer.append(KeyWord.NEWLINE); return buffer.toString(); }
     */

    protected String getTestCaseMethodComment()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.getTestCaseMethodComment());
        buffer.append("\n");
        buffer.append("* ");
        buffer.append(TestCodeGenConstants.PRE_CONDITION_TAG);
        buffer.append(" ");
        buffer.append(this.preCondition);

        return buffer.toString();
    }

    protected Method scanForExistingTest()
    {
        Method foundMethod = null;

        List methods = this.testCaseClass.getMethods();
        int methodLen = methods.size();
        boolean done = false;

        Stack callStackAsStrings = CodeUtil.getCallStackForScan(this.callStack);

        for (int i = 0; i < methodLen && done == false; i++)
        {
            // search each method in this test case class for a potential match
            // of this ifStatement and callStack
            Method method = (Method) methods.get(i);

            JavaComment comment = (JavaComment) method.getComment();

            if (comment != null)
            {

                String testType = comment.getTagValue(TestCodeGenConstants.TEST_TYPE_TAG);

                if (TestCodeGenConstants.PRE_CONDITION_TYPE.equals(testType))
                {
                    // only need to compare other if-statements

                    String expression = CodeUtil.parseLongTagComment(TestCodeGenConstants.PRE_CONDITION_TAG, comment);

                    StringBuffer expressionBuffer = new StringBuffer(expression);
                    expression = CodeUtil.formatStatementForComparison(expressionBuffer);

                    StringBuffer preBuffer = new StringBuffer(this.preCondition);
                    String preString = CodeUtil.formatStatementForComparison(preBuffer);

                    if (preString.equals(expression))
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

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.patterns.AbstractTestCasePattern#wrapInvocationWithCatch()
     */
    protected boolean wrapInvocationWithCatch()
    {
        return true;
    }
}
