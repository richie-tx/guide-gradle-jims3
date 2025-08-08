/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit;

/**
 * @author Jim Fisher
 *
 */
public class AuditError
{
    public static final String ERROR = "error";

    public static final String IGNORE = "ignore";

    public static final String INFO = "info";

    public static final String WARNING = "warning";

    private String columnNumber;

    private String lineNumber;

    private String message;

    private String messageKey;

    private String name;

    private String severity;

    private String source;

    public AuditError(String aSource, String aSeverity)
    {
        this.source = aSource;
        this.severity = aSeverity;
        this.lineNumber = "";
        this.columnNumber = "";
        this.name = "";
        this.message = "";
    }

    public AuditError(String aMessage, String aSource, String aSeverity)
    {
        this.message = aMessage;
        this.source = aSource;
        this.severity = aSeverity;
        this.lineNumber = "";
        this.columnNumber = "";
        this.name = "";
    }

    /**
     * 
     */
    public AuditError()
    {
    }

    /**
     * @return Returns the columnNumber.
     */
    public String getColumnNumber()
    {
        return columnNumber;
    }

    /**
     * @return Returns the lineNumber.
     */
    public String getLineNumber()
    {
        return lineNumber;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @return Returns the messageKey.
     */
    public String getMessageKey()
    {
        return messageKey;
    }

    /**
     * @return Returns the fileName.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Returns the severity.
     */
    public String getSeverity()
    {
        return severity;
    }

    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param columnNumber The columnNumber to set.
     */
    public void setColumnNumber(String columnNumber)
    {
        this.columnNumber = columnNumber;
    }

    /**
     * @param lineNumber The lineNumber to set.
     */
    public void setLineNumber(String lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @param messageKey The messageKey to set.
     */
    public void setMessageKey(String messageKey)
    {
        this.messageKey = messageKey;
    }

    /**
     * @param fileName The fileName to set.
     */
    public void setName(String aName)
    {
        this.name = aName;
    }

    /**
     * @param severity The severity to set.
     */
    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    public String toString()
    {
        return this.severity + ": " + this.message;
    }
}
