/*
 * Created on Jun 23, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.tools.code.audit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jim Fisher
 *  
 */
public class AuditXMLOutputter
{
    private File outputFile;

    private PrintWriter writer;

    private List results;

    public AuditXMLOutputter(File anOutputFile, List aResults)
    {
        this.outputFile = anOutputFile;
        this.results = aResults;
    }

    private void initPrintWriter()
    {
        try
        {
            this.outputFile.createNewFile();

            OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
            final OutputStreamWriter osw = new OutputStreamWriter(out, "UTF8");
            this.writer = new PrintWriter(osw);
        }
        catch (UnsupportedEncodingException e)
        {
            // unlikely to happen...
            throw new ExceptionInInitializerError(e);
        }
        catch (FileNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
        catch (IOException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    private String concatAttr(String name, String value)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(" ");
        buffer.append(name);
        buffer.append("=\"");
        buffer.append(value);
        buffer.append("\"");
        return buffer.toString();
    }

    public void writeResult(AuditResult aResult)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\t");
        buffer.append("<file");

        String fileName = aResult.getFileName();
        String compName = aResult.getQualifiedName();

        buffer.append(this.concatAttr("name", fileName));
        buffer.append(this.concatAttr("componentName", compName));
        buffer.append(this.concatAttr("errorCount", String.valueOf(aResult.getErrorCount())));
        buffer.append(this.concatAttr("warningCount", String.valueOf(aResult.getWarningCount())));
        buffer.append(this.concatAttr("infoCount", String.valueOf(aResult.getInfoCount())));

        buffer.append(">");

        Iterator i = aResult.getAllErrors().iterator();
        while (i.hasNext())
        {
            AuditError error = (AuditError) i.next();
            String errorString = this.concatError(error);
            buffer.append("\n\t\t");
            buffer.append(errorString);
            buffer.append("\n");
        }

        buffer.append("\t");
        buffer.append("</file>");
        writer.println(buffer.toString());
    }

    private String concatError(AuditError anError)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<error");
        buffer.append(this.concatAttr("line", anError.getLineNumber()));
        buffer.append(this.concatAttr("column", ""));
        buffer.append(this.concatAttr("severity", anError.getSeverity()));
        buffer.append(this.concatAttr("message", anError.getMessage()));
        buffer.append(this.concatAttr("source", anError.getSource()));

        //String ruleViolation = this.getRuleViolation(anError);
        buffer.append(this.concatAttr("ruleViolation", "#"));
        buffer.append(" />");
        return buffer.toString();
    }

    /**
     * Only records audit results for items that have errors/warnings/info to report
     *
     */
    public void saveResults()
    {
        this.initPrintWriter();

        // TODO Separate XML format from walking results tree

        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        writer.println("<audit type=\"Code\">");

        Iterator r = this.results.iterator();
        while (r.hasNext())
        {
            AuditResult result = (AuditResult) r.next();
            if (result.getAllResultsCount() > 0)
            {
                this.writeResult(result);
            }
        }

        writer.println("</audit>");

        writer.close();

        System.out.println("audit output saved to: " + this.outputFile.getAbsolutePath());
    }
}
