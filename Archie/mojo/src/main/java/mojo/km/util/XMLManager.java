package mojo.km.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;

import mojo.tools.code.KeyWord;
import mojo.tools.logging.LogUtil;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import java.io.FileInputStream;

/**
 * @author sshafi
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 * @modelguid {3252B004-F34F-493B-8312-1E21A2223F1A}
 */
public class XMLManager
{
    /** @modelguid {A1D6947A-B13C-4704-BD8D-FFE95FCEDA70} */
    private XMLManager()
    {
    }

    /** @modelguid {C7A3D97F-B528-4159-B2B8-31332C57DC0B} */
    public static Document readXMLStream(InputStream anInput) throws Exception
    {
        return new SAXBuilder().build(anInput);
    }

    public static Document readXMLFile(String aFileName) throws Exception
    {
        Document lDoc = null;
        InputStream inStream = new BufferedInputStream(new FileInputStream(aFileName));
        if (inStream != null)
        {
            InputSource input = new InputSource(inStream);
            input.setSystemId(aFileName);
            lDoc = new SAXBuilder().build(input);
            inStream.close();
        }
        else
        {
            throw new Exception("Could not find " + aFileName + ".");
        }
        return lDoc;
    }

    /** @modelguid {E1D3B31B-1A50-43E3-AF0D-FBA2FA19C6E9} */
    public static Document readXMLResource(String aResourceName) throws Exception
    {
        Document lDoc = null;
        ClassLoader lLoader = null;
        if (lLoader == null)
        {
            lLoader = XMLManager.class.getClassLoader();
        }
        Enumeration lResources = lLoader.getResources(aResourceName);
        URL lURL = null;
        if (lResources.hasMoreElements())
        {
            lURL = (URL) lResources.nextElement();
            StringBuffer buffer = new StringBuffer();
            if (lResources.hasMoreElements())
            {                
                buffer.append("There are multiple ");
                buffer.append(aResourceName);
                buffer.append(" files in the classpath at the following locations:");
                buffer.append(KeyWord.NEWLINE);
                buffer.append(lURL.getPath());
                buffer.append(KeyWord.NEWLINE);
                while (lResources.hasMoreElements())
                {
                    buffer.append(((URL) lResources.nextElement()).getPath());
                    buffer.append(KeyWord.NEWLINE);
                }
                buffer.append("-- Only the first one will be read.");
            }
            buffer = new StringBuffer();
            buffer.append("Loading ");
            buffer.append(aResourceName);
            buffer.append(" from: ");
            buffer.append(lURL.getPath());       
            
            InputStream inStream = lURL.openStream();

            InputSource input = new InputSource(inStream);
            String lFilename = lURL.getPath();
            if (lFilename.startsWith("/"))
            {
                lFilename = "file://" + lFilename;
            }
            input.setSystemId(lFilename);
            lDoc = new SAXBuilder().build(input);
            inStream.close();
        }
        else
        {
            throw new Exception("Could not find " + aResourceName + " in the classpath");
        }
        return lDoc;
    }

    public static void writeXMLToFile(Document aDocument, String aFileName) throws Exception
    {
        LogUtil.log(LogUtil.INFO, "Writing to " + aFileName);
        File lFile = new File(aFileName);

        OutputStream lFileOS = new FileOutputStream(lFile.getAbsolutePath());
        writeXMLToStream(aDocument, lFileOS);
        lFileOS.close();
    }

    /** @modelguid {1AF09659-087C-4357-836A-6B3361D9B198} */
    public static void writeXMLToResource(Document aDocument, String aResourceName) throws Exception
    {
        ClassLoader lLoader = Thread.currentThread().getContextClassLoader();
        URL lFileURL = lLoader.getResource(aResourceName);
        if (lFileURL == null)
        {
            throw new Exception("Resource " + aResourceName
                    + " could not be resolved to a valid file location so it could not be saved.");
        }
        String lFileName = lFileURL.getPath();
        if (lFileName == null)
        {
            lFileName = aResourceName;
        }
        else
        {
            if (lFileName.startsWith("/"))
            {
                lFileName = lFileName.substring(1);
            }
        }
        LogUtil.log(LogUtil.INFO, "Writing to " + aResourceName + " at: " + lFileName);
        writeXMLToFile(aDocument, lFileName);
    }

    /** @modelguid {C595D963-CA41-4CB2-9C2F-C19A18B770D1} */
    public static void writeXMLToStream(Document aDocument, OutputStream anOut) throws Exception
    {
        XMLOutputter lOut = new XMLOutputter();
        lOut.setNewlines(true);
        lOut.setTextNormalize(true);
        lOut.setIndent(true);
        lOut.output(aDocument, anOut);
    }
}
