/*
 * Created on Jun 23, 2006
 *
 */
package mojo.tools.code.audit.checkstyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;

/**
 * @author Jim Fisher
 *
 */
public class CheckstyleListener extends AutomaticBean implements AuditListener
{
    /** decimal radix */
    private static final int BASE_10 = 10;

    /** hex radix */
    private static final int BASE_16 = 16;

    /** some known entities to detect */
    private static final String[] ENTITIES =
    { "gt", "amp", "lt", "apos", "quot", };

    private Map filesByName;

    private List results;

    private Map violationMap;

    /**
     * 
     */
    public CheckstyleListener()
    {
        this.results = new ArrayList();
        this.violationMap = new HashMap();
        this.filesByName = new HashMap();
    }

    public void addError(AuditEvent aEvt)
    {
        String fileName = aEvt.getFileName();
        String severity = aEvt.getSeverityLevel().getName();
        String source = aEvt.getSourceName();
        String message = this.encode(aEvt.getMessage());
        String lineNo = String.valueOf(aEvt.getLine());

        String ruleViolation = (String) this.violationMap.get(source);
        if (ruleViolation == null)
        {
            ruleViolation = "";
        }

        AuditError error = new AuditError();
        error.setName(fileName);
        error.setSeverity(severity);
        error.setSource(source);
        error.setMessage(message);
        error.setLineNumber(lineNo);

        AuditResult result = (AuditResult) this.filesByName.get(fileName);

        result.addError(error);
    }

    public void addException(AuditEvent aEvt, Throwable aThrowable)
    {
        System.out.println("exception: " + aEvt.getSourceName() + " auditing: " + aEvt.getFileName());
        aThrowable.printStackTrace(System.out);
    }

    public void auditFinished(AuditEvent aEvt)
    {

    }

    public void auditStarted(AuditEvent aEvt)
    {
    }

    /**
     * Escape &lt;, &gt; &amp; &apos; and &quot; as their entities.
     * @param aValue the value to escape.
     * @return the escaped value if necessary.
     */
    public String encode(String aValue)
    {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < aValue.length(); i++)
        {
            final char c = aValue.charAt(i);
            switch (c)
            {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '\'':
                sb.append("&apos;");
                break;
            case '\"':
                sb.append("&quot;");
                break;
            case '&':
                final int nextSemi = aValue.indexOf(";", i);
                if ((nextSemi < 0) || !isReference(aValue.substring(i, nextSemi + 1)))
                {
                    sb.append("&amp;");
                }
                else
                {
                    sb.append('&');
                }
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }

    public void fileFinished(AuditEvent aEvt)
    {
    }

    public void fileStarted(AuditEvent aEvt)
    {
        AuditResult result = new AuditResult();
        result.setFileName(aEvt.getFileName());
        this.filesByName.put(aEvt.getFileName(), result);
    }

    /**
     * @return Returns the filesByName.
     */
    public Map getFilesByName()
    {
        return filesByName;
    }

    private void initViolationMap()
    {
        // TODO Put in lookup file
        violationMap = new HashMap();
        violationMap.put("mojo.tools.code.audit.checkstyle.check.imports.PackageControlCheck",
                "ruleViolation1.html#packagePartitioning");
        violationMap.put("mojo.tools.code.audit.checkstyle.check.coding.ServiceMethodThrowsCheck",
                "ruleViolation4.html#businessExceptions");
    }

    /**
     * @return whether the given argument a character or entity reference
     * @param aEnt the possible entity to look for.
     */
    public boolean isReference(String aEnt)
    {
        if (!(aEnt.charAt(0) == '&') || !aEnt.endsWith(";"))
        {
            return false;
        }

        if (aEnt.charAt(1) == '#')
        {
            int prefixLength = 2; // "&#"
            int radix = BASE_10;
            if (aEnt.charAt(2) == 'x')
            {
                prefixLength++;
                radix = BASE_16;
            }
            try
            {
                Integer.parseInt(aEnt.substring(prefixLength, aEnt.length() - 1), radix);
                return true;
            }
            catch (NumberFormatException nfe)
            {
                return false;
            }
        }

        final String name = aEnt.substring(1, aEnt.length() - 1);
        for (int i = 0; i < ENTITIES.length; i++)
        {
            if (name.equals(ENTITIES[i]))
            {
                return true;
            }
        }
        return false;
    }
}
