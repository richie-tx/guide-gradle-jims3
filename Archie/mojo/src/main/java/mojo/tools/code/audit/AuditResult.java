/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jim Fisher
 *
 */
public class AuditResult
{
    private String fileSize;
    
    private String lineCount;
    
    private List childErrors;

    private List childIgnore;

    private List childInfo;

    private List childWarnings;

    private String componentName;
    
    private String message;

    private List errors;

    private String fileName;

    private List ignore;

    private List info;

    private String packageName;

    private List warnings;

    /**
     * 
     */
    public AuditResult()
    {
        this.errors = new ArrayList();
        this.childErrors = new ArrayList();
        this.childWarnings = new ArrayList();
        this.childInfo = new ArrayList();
        this.childIgnore = new ArrayList();
        this.warnings = new ArrayList();
        this.info = new ArrayList();
        this.ignore = new ArrayList();
    }

    public void addChildErrors(List theChildErrors)
    {
        Iterator i = theChildErrors.iterator();
        while (i.hasNext())
        {
            AuditError error = (AuditError) i.next();
            if (AuditError.ERROR.equals(error.getSeverity()))
            {
                this.childErrors.add(error);
            }
            else if (AuditError.WARNING.equals(error.getSeverity()))
            {
                this.childWarnings.add(error);
            }
            else if (AuditError.INFO.equals(error.getSeverity()))
            {
                this.childInfo.add(error);
            }
            else
            {
                this.childErrors.add(error);
            }
        }
    }

    public void addError(AuditError anError)
    {
        if (AuditError.ERROR.equals(anError.getSeverity()))
        {
            this.errors.add(anError);
        }
        else if (AuditError.WARNING.equals(anError.getSeverity()))
        {
            this.warnings.add(anError);
        }
        else if (AuditError.INFO.equals(anError.getSeverity()))
        {
            this.info.add(anError);
        }
        else
        {
            this.ignore.add(anError);
        }
    }

    /**
     * @param result
     */
    public void addResult(AuditResult result)
    {
        List rErrors = result.getErrors();
        this.errors.addAll(rErrors);

        List rWarnings = result.getWarnings();
        this.warnings.addAll(rWarnings);

        List rInfo = result.getInfo();
        this.info.addAll(rInfo);
    }

    /**
     * Gets the errors at this level, not including child errors
     * @return
     */
    public List getAllErrors()
    {
        List allErrors = new ArrayList();
        allErrors.addAll(this.errors);
        allErrors.addAll(this.warnings);
        allErrors.addAll(this.info);
        return allErrors;
    }

    /**
     * @return Returns the componentName.
     */
    public String getComponentName()
    {
        return componentName;
    }

    /**
     * @return Returns the errorCount.
     */
    public int getErrorCount()
    {
        return errors.size() + childErrors.size();
    }

    /**
     * @return Returns the errors.
     */
    public List getErrors()
    {
        return errors;
    }

    /**
     * @return Returns the fileName.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @return Returns the ignore.
     */
    public List getIgnore()
    {
        return this.ignore;
    }

    /**
     * @return Returns the info.
     */
    public List getInfo()
    {
        return info;
    }

    /**
     * @return Returns the infoCount.
     */
    public int getInfoCount()
    {
        return info.size() + this.childInfo.size();
    }

    /**
     * @return Returns the packageName.
     */
    public String getPackageName()
    {
        return packageName;
    }
    
    public String getQualifiedName()
    {
        StringBuffer buffer = new StringBuffer();
        if(this.packageName != null)
        {
            buffer.append(this.packageName);
            buffer.append(".");
        }
        buffer.append(this.componentName);
        return buffer.toString();
    }

    /**
     * @return Returns the warningCount.
     */
    public int getWarningCount()
    {
        return warnings.size() + this.childWarnings.size();
    }

    /**
     * @return Returns the warnings.
     */
    public List getWarnings()
    {
        return warnings;
    }
    
    public int getAllResultsCount()
    {
        return this.getErrorCount() + this.getWarningCount() + this.getInfoCount();
    }
    
    public boolean hasResults()
    {
        return this.getAllResultsCount() > 0;
    }

    /**
     * @param componentName The componentName to set.
     */
    public void setComponentName(String componentName)
    {
        this.componentName = componentName;
    }

    /**
     * @param errors The errors to set.
     */
    public void setErrors(List errors)
    {
        this.errors = errors;
    }

    /**
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @param info The info to set.
     */
    public void setInfo(List info)
    {
        this.info = info;
    }

    /**
     * @param packageName The packageName to set.
     */
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    /**
     * @param warnings The warnings to set.
     */
    public void setWarnings(List warnings)
    {
        this.warnings = warnings;
    }
    
    /**
     * @return Returns the message.
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * @param message The message to set.
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
}
