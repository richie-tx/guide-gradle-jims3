/*
 * Created on May 25, 2006
 *
 */
package mojo.tools.codegen.patterns;

import java.util.List;

import mojo.tools.code.Class;
import mojo.tools.code.Method;

/**
 * @author jfisher
 *  
 */
public abstract class AbstractTestCasePattern implements IPattern
{
    // TODO Move to naming
    static protected final int MULTIPLE = 2;

    static protected final int ONE = 1;

    static protected final int ZERO = 0;

    protected List imports;

    protected Class testCaseClass;

    /**
     *  
     */
    public AbstractTestCasePattern(Class aClass)
    {
        this.testCaseClass = aClass;
    }

    abstract protected String createAssertions();

    abstract protected String getTestCaseMethodName();

    abstract protected String getTestCaseMethodComment();

    abstract protected Method scanForExistingTest();

    abstract protected String getTestType();
    
    abstract protected String getTestNameExtension();    
    
    abstract protected void setAssertExceptions(boolean anAssertExcceptions);
}
