/*
 * Created on Jun 22, 2006
 *
 */
package mojo.tools.code.audit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CompilationUnit;

/**
 * This class is designed to be stateless.
 * 
 * 
 * @author Jim Fisher
 *
 */
public abstract class Check
{
    protected AuditCheckProperties checkProps;

    private Properties msgBundle;

    private static final String MESSAGES_FILE_NAME = "messages.properties";

    public Check(AuditCheckProperties aCheckProps)
    {
        this.checkProps = aCheckProps;
        this.loadProperties();
    }

    abstract public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement);

    abstract public String[] getCodeElementTypes();
    
    protected void init() {}

    private void loadProperties()
    {
        this.msgBundle = new Properties();

        String packageName = this.getClass().getPackage().getName();
        String path = packageName.replace('.', '/');
        path += "/" + MESSAGES_FILE_NAME;

        java.net.URL url = Check.class.getClassLoader().getResource(path);
        InputStream is = null;

        try
        {
            is = url.openStream();
            msgBundle.load(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            this.msgBundle = new Properties();
            System.out.println("message bundle not found at: " + path);
        }
        finally
        {
            try
            {
                if (is != null)

                {
                    is.close();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    protected void log(AuditVisitor aVisitor, String aLineNo, String aKey)
    {
        this.log(aVisitor, aLineNo, aKey, null);
    }

    protected void log(AuditVisitor aVisitor, String aLineNo, String aKey, Object[] anArgs)
    {
        String source = this.checkProps.getCheckName();

        String severity = this.checkProps.getSeverity();

        if (severity == null)
        {
            severity = AuditError.ERROR;
        }

        if (aLineNo == null)
        {
            aLineNo = "";
        }

        AuditError error = new AuditError(source, severity);

        String msg = (String) this.msgBundle.get(aKey);

        if (msg != null)
        {
            msg = MessageFormat.format(msg, anArgs);
            error.setMessage(msg);
        }
        else
        {
            error.setMessage("");
        }

        error.setLineNumber(aLineNo);
        aVisitor.addError(error);
    }

}
