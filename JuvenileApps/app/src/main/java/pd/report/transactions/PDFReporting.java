package pd.report.transactions;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.reporting.IReport;

import org.ujac.print.DocumentPrinter;
import org.ujac.util.io.CachedResourceLoader;
import org.ujac.util.io.ClassResourceLoader;
import org.ujac.util.template.DefaultTemplateInterpreterFactory;
import org.ujac.util.template.TemplateContext;
import org.ujac.util.template.TemplateInterpreter;
import org.ujac.util.template.TemplateInterpreterFactory;
import org.ujac.util.text.FormatHelper;


public class PDFReporting implements IReport
{
	private Logger log = Logger.getLogger("PDFReporting");
    private static PDFReporting reportingService;
    private String templateName;
    private static boolean serviceInitialized = false;
    
    /**
	 * 
	 */
	private PDFReporting() {
	}
	
	public static PDFReporting getInstance() {
		if ( reportingService == null ) {
			synchronized ( PDFReporting.class ) {
				
				if (!serviceInitialized) {
					reportingService = new PDFReporting();
					serviceInitialized = true;
				}
			}
		}
		return reportingService;
	}
    
    public byte[] getByteOutput(ReportRequestEvent event )throws Exception 
    {
        EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(event.getReportName());
        if (eProps == null)
        {
            throw new Exception("Report mapping configuration not found for: " + event.getReportName());
        }

        String resourceName = eProps.getEntity();
        setTemplate(resourceName.substring(0, resourceName.indexOf(".xml")));
        return generateReport(resourceName, event);
    }
    
    public String getContentType()
    {
    	return "PDF";
    }
    
    public String getTemplate()
    {
        return this.templateName;
    }

    /**
     * @param templateName The templateName to set.
     */
    public void setTemplate(String templateName) {
        this.templateName = templateName;
    }

    
	public byte[] generateReport(String resourceName, ReportRequestEvent event) throws Exception
	{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try
		{
			Map documentProperties = new HashMap();
			StringReader templateStream = new StringReader(loadTemplate(resourceName, event));
			DocumentPrinter documentPrinter = new DocumentPrinter(templateStream,documentProperties);
			documentPrinter.setResourceLoader(new CachedResourceLoader(new ClassResourceLoader()));
			
			Iterator dataObjs = event.getDataObjects();
			dataObjs.next();
			if(dataObjs.hasNext()){
				Map imageMap = (Map) dataObjs.next();
				Set set= imageMap.keySet() ;
				Iterator iter = set.iterator() ; 
				while (iter.hasNext()){
					String key = (String) iter.next();
					documentProperties.put(key , imageMap.get(key));
				}
			}
			documentPrinter.setProperties(documentProperties);
			documentPrinter.printDocument(byteOut);
			log.info("PDF Created");
		} 
		catch(Exception e)
		{
			throw e;
		}

		return byteOut.toByteArray();

	}
	
    private String loadTemplate(String resourceName, ReportRequestEvent event) throws Exception
    {
        return buildTemplate(new String(new ClassResourceLoader().loadResource(resourceName)), event);
    }

	public String buildTemplate(String resource, ReportRequestEvent event) throws Exception
	{
		  TemplateInterpreterFactory tif = null;
		  // creating template interpreter
		  TemplateInterpreter interpreter = null;
		  // creating expression context
		  TemplateContext ctx = null;
		  InputStream templateStream = null;
		  String template = null;
		try {
			  // creating factory
			   tif = new DefaultTemplateInterpreterFactory();
			  // creating template interpreter
			   interpreter = tif.createTemplateInterpreter();
			  // creating expression context
			   ctx = tif.createTemplateContext(new HashMap(), FormatHelper.createInstance(Locale.ENGLISH));
			      
                Iterator i = event.getDataObjects();

                if (i.hasNext())
                {
                	ctx.setBean(i.next());
                }
                
				template = interpreter.execute(resource.toString(), ctx);
				// '-./\();& 
				template = template.replaceAll("&#39;","'").
									replaceAll("&#40;","(").
									replaceAll("&#41;",")").
									replaceAll("&#47;","/").
									replaceAll("&#59;",";").
									replaceAll("&#150;","-").
									replaceAll("&#160;", " ").
									replaceAll("&#38;amp;", "&#38;").
									replaceAll("&#60;br /&#62;", "<br/>").
									replaceAll("&#38;lt;", "&#60;").
									replaceAll("&#38;gt;", "&#62;").
									replaceAll("&mdash;", "-").
									replaceAll("-&nbsp;", "- ").
									replaceAll(":&nbsp;", ": ").
									replaceAll("&#38;nbsp;", " ").
									replaceAll("&ndash;", "&#45;").
									replaceAll("&lsquo;", "&#39;").
									replaceAll("&#38;lsquo;", "&#39;").
									replaceAll("&rsquo;", "&#39;").
									replaceAll("&#38;rsquo;", "&#39;").
									replaceAll("&#38;ldquo;", "\"").
									replaceAll("&#38;rdquo;", "\"").
									replaceAll("<>", "&#60;&#62;" ).
									replaceAll("<=", "&#60;&#61;" ).
									replaceAll("> <", "><space/><").
									replaceAll("<BR/>", "<br/>").
									replaceAll("<BR>", "<br/>").
									replaceAll("</BR>", "<br/>").
									replaceAll("<br>", "<br/>").
									replaceAll("</br>", "<br/>").
									replaceAll("<em>", "<i>").
									replaceAll("</em>", "</i>").
									replaceAll("<li>", "&#8226;").
									replaceAll("</li>", "<br/>").
									replaceAll("<p>", "<br/>").
									replaceAll("</p>", "<br/>").
									replaceAll("<strong>", "<b>").
									replaceAll("</strong>", "</b>").
									replaceAll("<sup>", "<super>").
									replaceAll("</sup>", "</super>").
									replaceAll("<ul>", "<br/>").
									replaceAll("</ul>", "<br/>").
									replaceAll("\\\\", "&#92;&#92;").
									replaceAll("&#92;&#92;t", "<space/><space/><space/>").
									replaceAll("&#92;&#92;n", "\n");
			} catch (Exception ex) {
				// Oops, something went wrong ...
//				log.info("expression result: " + template);
				throw ex;
			}
			return template;
	}


	public static void main(String []args)
	{
		//PDFReporting.generateReport();
	}
}