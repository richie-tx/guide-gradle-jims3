package mojo.tools.code.audit.checkstyle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;

/**
 * Simple XML logger.
 * 
 * It outputs everything in UTF8 (default XML encoding is UTF8) in case
 * we want to localize error messages or simply that filenames are
 * localized and takes care about escaping as well.
 */
public class AuditXMLLogger extends AutomaticBean implements AuditListener
{

    /** decimal radix */
    private static final int BASE_10 = 10;

    /** hex radix */
    private static final int BASE_16 = 16;

    /** some known entities to detect */
    private static final String[] ENTITIES =
    { "gt", "amp", "lt", "apos", "quot", };

    private Map filesByName;

    /** helper writer that allows easy encoding and printing */
    private PrintWriter mWriter;

    private File outputFile;

    private static final int RULE_VIOLATION_WINDOW_HEIGHT = 900;

    private static final int RULE_VIOLATION_WINDOW = 500;

    private static Map violationMap;

    /**
     * Creates a new <code>XMLLogger</code> instance.
     * Sets the output to a defined stream.
     * @param aOS the stream to write logs to.
     * @param aCloseStream close aOS in auditFinished
     */
    public AuditXMLLogger(File aFile)
    {
        this.outputFile = aFile;
        if (violationMap == null)
        {
            this.initViolationMap();
        }
    }

    /**
     * 
     */
    private void initViolationMap()
    {
        // TODO Put in lookup file
        violationMap = new HashMap();
        violationMap.put("mojo.tools.code.audit.checkstyle.check.imports.PackageControlCheck",
                "ruleViolation1.html#packagePartitioning");
        violationMap.put("mojo.tools.code.audit.checkstyle.check.coding.ServiceMethodThrowsCheck",
                "ruleViolation4.html#businessExceptions");
    }

    /** {@inheritDoc} */
    public void addError(AuditEvent aEvt)
    {
        if (!SeverityLevel.IGNORE.equals(aEvt.getSeverityLevel()))
        {
            String fileName = aEvt.getFileName();
            fileName = fileName.replace('\\', '/');
            List errors = (List) this.filesByName.get(fileName);
            errors.add(aEvt);
        }
    }

    /** {@inheritDoc} */
    public void addException(AuditEvent aEvt, Throwable aThrowable)
    {
        // TODO add support for addException in output

        /*final StringWriter sw = new StringWriter();
         final PrintWriter pw = new PrintWriter(sw);
         pw.println("<exception>");
         pw.println("<![CDATA[");
         aThrowable.printStackTrace(pw);
         pw.println("]]>");
         pw.println("</exception>");
         pw.flush();
         mWriter.println(encode(sw.toString()));*/
        aThrowable.printStackTrace();
    }

    private String attachSuffix(File aFile, String aSuffix)
    {
        String fileName = aFile.getName();
        String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf("."));
        String fileNameExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        fileName = fileNameWithoutExt + aSuffix + fileNameExt;

        return fileName;
    }

    /** {@inheritDoc} */
    public void auditFinished(AuditEvent aEvt)
    {
    }

    /** {@inheritDoc} */
    public void auditStarted(AuditEvent aEvt)
    {
        this.filesByName = new TreeMap();
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

    /** {@inheritDoc} */
    public void fileFinished(AuditEvent aEvt)
    {
    }

    /** {@inheritDoc} */
    public void fileStarted(AuditEvent aEvt)
    {
        List errors = new ArrayList();
        String fileName = aEvt.getFileName();
        fileName = fileName.replace('\\', '/');
        this.filesByName.put(fileName, errors);
    }

    private List getErrors(String aKey, Map aMap)
    {
        List errors;
        if (aMap.containsKey(aKey))
        {
            errors = (List) aMap.get(aKey);
        }
        else
        {
            errors = new ArrayList();
        }
        return errors;
    }

    /**
     * @param evt
     * @return
     */
    private String getRuleViolation(AuditEvent evt)
    {
        String ruleFile = (String) violationMap.get(evt.getSourceName());
        if (ruleFile == null)
        {
            ruleFile = "#";
        }
        String rulePath = this.computeRuleRelPath(evt) + "codingguidelines/" + ruleFile;

        StringBuffer buffer = new StringBuffer();
        
        buffer.append(rulePath);

        return buffer.toString();
    }

    private void initPrintWriter(File outputFile)
    {
        try
        {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
            final OutputStreamWriter osw = new OutputStreamWriter(out, "UTF8");
            this.mWriter = new PrintWriter(osw);
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Handle exception
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
        catch (FileNotFoundException e)
        {
            // TODO Handle exception
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
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

    /** {@inheritDoc} */
    public void outputAddError(AuditEvent aEvt)
    {
        if (!SeverityLevel.IGNORE.equals(aEvt.getSeverityLevel()))
        {
            mWriter.print("<error" + " line=\"" + aEvt.getLine() + "\"");
            if (aEvt.getColumn() > 0)
            {
                mWriter.print(" column=\"" + aEvt.getColumn() + "\"");
            }
            mWriter.print(" severity=\"" + aEvt.getSeverityLevel().getName() + "\"");
            mWriter.print(" message=\"" + encode(aEvt.getMessage()) + "\"");
            mWriter.print(" source=\"" + encode(aEvt.getSourceName()) + "\"");

            String ruleViolation = this.getRuleViolation(aEvt);
            mWriter.print(" ruleViolation=\"" + encode(ruleViolation) + "\"/>");
        }
    }

    /**
     * @param evt
     * @return
     */
    private String computeRuleRelPath(AuditEvent evt)
    {
        String name = evt.getFileName();
        String fileSep = System.getProperty("file.separator");
        char fileSepChar = fileSep.charAt(0);
        byte[] byteChars = name.getBytes();
        int pathCharCount = 1;
        for (int i = 0; i < byteChars.length; i++)
        {
            char ch = (char) byteChars[i];
            if (ch == fileSepChar)
            {
                pathCharCount++;
            }
        }
        StringBuffer pathBuffer = new StringBuffer();
        for (int i = 0; i < pathCharCount; i++)
        {
            pathBuffer.append("../");
        }
        return pathBuffer.toString();
    }

    /** {@inheritDoc} */
    public void outputAuditFinished()
    {
        mWriter.println("</checkstyle>");
        mWriter.close();
    }

    /** {@inheritDoc} */
    private void outputAuditStarted()
    {
        mWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        final ResourceBundle compilationProperties = ResourceBundle.getBundle("checkstylecompilation");
        final String version = compilationProperties.getString("checkstyle.compile.version");

        mWriter.println("<checkstyle version=\"" + version + "\">");
    }

    private void outputFileFinished()
    {
        mWriter.println("</file>");
    }

    private void outputFileStarted(FileSetSummaryRecord summary)
    {
        mWriter.println("<file name=\"" + summary.getName() + "\"" + " errorCount=\"" + summary.getErrorCount() + "\""
                + " warningCount=\"" + summary.getWarningCount() + "\"" + " infoCount=\"" + summary.getInfoCount()
                + "\"" + ">");
    }

    private void saveOutput(File anOutputFile, Map aFilesMap, List aFileList)
    {
        this.initPrintWriter(anOutputFile);
        this.outputAuditStarted();

        Iterator i = aFileList.iterator();
        while (i.hasNext())
        {
            FileSetSummaryRecord summary = (FileSetSummaryRecord) i.next();
            this.outputFileStarted(summary);
            List errors = (List) filesByName.get(summary.getName());
            Iterator j = errors.iterator();
            while (j.hasNext())
            {
                AuditEvent anErrorEvt = (AuditEvent) j.next();
                this.outputAddError(anErrorEvt);
            }
            this.outputFileFinished();
        }

        this.outputAuditFinished();
    }
}

class FileSetSummaryRecord
{

    private int errorCount;

    private int infoCount;

    private String name;

    private int warningCount;

    public FileSetSummaryRecord()
    {

    }

    public boolean equals(Object obj)
    {
        FileSetSummaryRecord fileSet = (FileSetSummaryRecord) obj;
        boolean equals;
        if (this.getErrorCount() != fileSet.getErrorCount() || this.getWarningCount() != fileSet.getWarningCount()
                || this.getInfoCount() != fileSet.getInfoCount())
        {
            equals = false;
        }
        else
        {
            equals = true;
        }
        return equals;
    }

    /**
     * @return Returns the errorCount.
     */
    public int getErrorCount()
    {
        return errorCount;
    }

    /**
     * @return Returns the infoCount.
     */
    public int getInfoCount()
    {
        return infoCount;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    public int getRankCount(SeverityLevel aLevel)
    {
        int severityCount;
        if (aLevel.equals(SeverityLevel.ERROR))
        {
            severityCount = this.errorCount;
        }
        else if (aLevel.equals(SeverityLevel.WARNING))
        {
            severityCount = this.warningCount;
        }
        else if (aLevel.equals(SeverityLevel.INFO))
        {
            severityCount = this.infoCount;
        }
        else
        {
            severityCount = 0;
        }
        return severityCount;
    }

    /**
     * @return Returns the warningCount.
     */
    public int getWarningCount()
    {
        return warningCount;
    }

    /**
     * @param errorCount The errorCount to set.
     */
    public void setErrorCount(int errorCount)
    {
        this.errorCount = errorCount;
    }

    /**
     * @param infoCount The infoCount to set.
     */
    public void setInfoCount(int infoCount)
    {
        this.infoCount = infoCount;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param warningCount The warningCount to set.
     */
    public void setWarningCount(int warningCount)
    {
        this.warningCount = warningCount;
    }

    public String toString()
    {
        return "name: " + this.name + " errorCount: " + this.errorCount + " warningCount: " + this.warningCount
                + " infoCount: " + this.infoCount;
    }
}