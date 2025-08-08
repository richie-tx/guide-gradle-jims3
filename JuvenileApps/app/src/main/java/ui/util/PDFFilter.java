// $Id: PDFFilter.java 17594 2013-05-10 10:54:44Z mike $
//
package ui.util;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.faceless.pdf2.*;
import org.faceless.report.*;
import org.xml.sax.*;

/**
 * <p>
 * The <code>PDFFilter</code> class is an implementation of the Servlet 2.3 Filter
 * interface, which automatically converts an XML report into a PDF which is
 * returned to the client. For those still using the Servlet 2.2 architecture,
 * The {@link PDFProxyServlet} does a similar job.
 * </p>
 * <p>
 * More information on installing filters is available in the Servlet Specification
 * and has probably been supplied with your Servlet engine. For the impatient,
 * here's an example setup which would cause all requests to anything in the
 * <code>/pdf/</code> path of your website to be converted to a PDF. Add the following
 * lines to your <code>WEB-INF/web.xml</code> file:
 * <pre>
 *    &lt;filter&gt;
 *      &lt;filter-name&gt;bforeport&lt;/filter-name&gt;
 *      &lt;filter-class&gt;org.faceless.report.PDFFilter&lt;/filter-class&gt;
 *    &lt;/filter&gt;
 *    &lt;filter-mapping&gt;
 *      &lt;filter-name&gt;bforeport&lt;/filter-name&gt;
 *      &lt;url-pattern&gt;/pdf/*&lt;/url-pattern&gt;
 *    &lt;/filter-mapping&gt;
 * </pre>
 * <p>
 * Meta tags that aren't already known to the Report Generator and that
 * begin with "<code>HTTP-</code>" are added to the response header (minus the
 * "HTTP-" prefix). An example would be to place
 * <code>&lt;meta name="HTTP-Expires" value="Mon, 01 Jan 1999 12:00:00 GMT"&gt;</code>
 * in the head of the XML, which would set the "Expires" header in the HTTP
 * response.
 * </p>
 * <p>
 * The following custom Meta-Tags may also be used to control the behaviour of
 * the servlet.
 * <ul>
 * <li><code>Servlet-Filename</code> - ask the client browser to save the file as the specified
 * filename instead of displaying it inline. This uses the <code>Content-Disposition</code>
 * header, which <i>in theory</i> is accepted by NS4+ and IE5+, although this
 * <a href=http://support.microsoft.com/support/kb/articles/Q281/1/19.asp>known bug</a>
 * in IE5.5 may prevent the document from being viewed at all. Use with caution unless
 * you know your audiences browsers.</li>
 * </ul>
 * Some initialization parameters can be set in the <code>web.xml</code> file to further
 * control various internal aspects of the servlet:
 * <ul>
 * <li><code>org.xml.sax.driver</code> - may be set to the base class of your SAX parsers
 * <code>XMLReader</code> implementation, if the generator is unable to locate it.</li>
 * <li><code>org.faceless.report.flag.WarningUnknownTag</code> - may be set to <code>true</code> or <code>false</code> to generate warnings about unknown tags. Default is "true"</li>
 * <li><code>org.faceless.report.flag.WarningUnknownAttribute</code> - may be set to <code>true</code> or <code>false</code> to generate warnings about unknown attributes. Default is "true"</li>
 * <li><code>org.faceless.report.flag.WarningMisplacedText</code> - may be set to <code>true</code> or <code>false</code> to generate warnings about misplaced text. Default is "true"</li>
 * <li><code>cache-minsize</code> and <code>cache-prefix</code> - can be set to cache parts of the created document to disk.</li>
 * </ul>
 * </p>
 * <p>
 * These last two parameters were added in version 1.1.19 to cause parts of the document
 * to be cached to disk. This can reduce memory footprint slightly, although it may slow
 * things down a little so you must decide whether it's appropriate or not. The
 * <code>cache-prefix</code> and <code>cache-minsize</code> parameters are passed into the
 * {@link org.faceless.pdf2.DiskCache} constructor - essentially the <code>prefix</code> should
 * be a temporary directory, optionally with the first half of a filename. The <code>minsize</code>
 * parameter sets the minimum size a stream may be before it's considered to be worth caching to disk.
 * For example, to store streams greater than 8k in the "/tmp/cache" directory and call them
 * "bfo.1", "bfo.2" and so on, you could do something like this:
 * </p>
 * <pre>
 *    &lt;filter&gt;
 *      &lt;filter-name&gt;bforeport&lt;/filter-name&gt;
 *      &lt;filter-class&gt;org.faceless.report.PDFFilter&lt;/filter-class&gt;
 *      &lt;init-param&gt
 *        &lt;param-name&gt;cache-minsize&lt;/param-name&gt;
 *        &lt;param-value&gt;8192&lt;/param-value&gt;
 *      &lt;/init-param&gt
 *      &lt;init-param&gt
 *        &lt;param-name&gt;cache-prefix&lt;/param-name&gt;
 *        &lt;param-value&gt;/tmp/cache/bfo.&lt;/param-value&gt;
 *      &lt;/init-param&gt
 *    &lt;/filter&gt;
 * </pre>
 * <p>
 * This class also implements <code>org.xml.sax.ErrorHandler</code>, to deal with
 * any errors found during the XML parsing process. Currently all warnings and
 * errors are fatal, and logged to <code>System.err</code>. To change this behaviour,
 * subclass this class and override the {@link #warning warning}, {@link #error error}
 * and {@link #fatalError fatalError} methods.
 * </p>
 *
 * @version $Revision: 1.25 $
 */
public class PDFFilter implements Filter, ErrorHandler
{
    private FilterConfig conf=null;

    public void init(FilterConfig config)
        throws ServletException
    {
        this.conf = config;
	String license = conf.getInitParameter("license");
	if (license==null) license = conf.getInitParameter("License");
	if (license!=null) {
	    ReportParser.setLicenseKey(license);
	}

	String cache = conf.getInitParameter("cache-prefix");
	if (cache==null) cache = conf.getInitParameter("Cache-prefix");
	if (cache==null) cache = conf.getInitParameter("Cache-Prefix");

	String cachemin = conf.getInitParameter("cache-minsize");
	if (cachemin==null) cachemin = conf.getInitParameter("Cache-minsize");
	if (cachemin==null) cachemin = conf.getInitParameter("Cache-Minsize");
	if (cachemin==null) cachemin="8192";

	if (cache!=null) {
	    cache=cache.trim();
	    if (cache.length()>0) {
		PDF.setCache(new DiskCache(cache, Integer.parseInt(cachemin)));
	    }
	}
    }

    public void destroy() {
        this.conf = null;
    }

    /**
     * Return the Filter Config. A non-standard method required for WebLogic 6.1
     */
    public FilterConfig getFilterConfig() {
        return conf;
    }

    /**
     * Set the Filter Config. A non-standard method required for WebLogic 6.1
     */
    public void setFilterConfig(FilterConfig conf) {
        this.conf=conf;
	String license = conf.getInitParameter("license");
	if (license!=null) {
	    org.faceless.report.ReportParser.setLicenseKey(license);
	}
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException
    {
	if (conf==null || !(request instanceof HttpServletRequest && response instanceof HttpServletResponse)) return;
	// Apparantly some versions of Internet Explorer set the string "contype" as a User-Agent
	// to help IE determine the type of plugin to load. We haven't seen this behaviour here,
	// but have added some code to handle this situation just in case.
	// See http://support.microsoft.com/default.aspx?scid=kb;en-us;293792
	//
	boolean iehack = "contype".equals(((HttpServletRequest)request).getHeader("User-Agent"));
	CharResponseWrapper wrapper = null;
	wrapper = new CharResponseWrapper((HttpServletResponse)response);

	chain.doFilter((HttpServletRequest)request, wrapper);

	if (wrapper.getStatus()>=200 && wrapper.getStatus()<=299 && (wrapper.getContentType()!=null && wrapper.getContentType().startsWith("text/xml"))) {
	    if (iehack) {
		response.setContentType("application/pdf");
	    } else {
		InputSource xmlin = new InputSource();
		xmlin.setCharacterStream(new StringReader(wrapper.getString().trim()));
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		if (url!=null && url.toLowerCase().startsWith("https:")) {
		    try {
			URL temp = new URL(url);
		    } catch (Throwable e) {
			System.err.println("WARNING: HTTPS protocol not recognized by webserver (error is "+e.toString()+"). Switching to HTTP");
			url = "http:"+url.substring(6);
		    }
		}
		xmlin.setSystemId(url);

		PDF pdf=null;
		try {
		    ReportParser parser = ReportParser.getInstance((String)conf.getInitParameter("org.xml.sax.driver"));
                    parser.setErrorHandler(this);
		    parser.setMetaHandler(new MetaCallback((HttpServletRequest)request, (HttpServletResponse)response, this));
		    if ("false".equalsIgnoreCase(conf.getInitParameter("org.faceless.report.flag.WarningUnknownTag"))) {
			parser.setFlag(ReportParser.WARNING_UNKNOWN_TAG, false);
		    }
		    if ("false".equalsIgnoreCase(conf.getInitParameter("org.faceless.report.flag.WarningUnknownAttribute"))) {
			parser.setFlag(ReportParser.WARNING_UNKNOWN_ATTRIBUTE, false);
		    }
		    if ("false".equalsIgnoreCase(conf.getInitParameter("org.faceless.report.flag.WarningMisplacedText"))) {
			parser.setFlag(ReportParser.WARNING_MISPLACED_TEXT, false);
		    }
		    if ("true".equalsIgnoreCase(conf.getInitParameter("org.faceless.report.flag.Debug"))) {
			parser.setFlag(ReportParser.DEBUG_TO_STDOUT, true);
		    }
		    pdf = parser.parse(xmlin);
		} catch (SAXException e) {
		    if (e.getException()!=null) {
			throw new ServletException(e.getException());
		    } else {
			throw new ServletException(e);
		    }
		}

                modifyPDF(pdf);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		pdf.render(bout);
		// check attribute which determines if the pdf report needs to be saved into request 
		// usage: need to set "isPdfSaveNeeded" attribute in action before calling pdfManager in order to 
		// save the report as a byte array into the request as an attribute called "pdfSavedReport". In the
		// action after pdfManager returns, get byte array out as attribute from request as "pdfSavedReport".
		String isPdfSaveNeeded = (String) request.getAttribute("isPdfSaveNeeded");
		if(isPdfSaveNeeded != null && isPdfSaveNeeded.equals("true")){
			request.setAttribute("pdfSavedReport", bout.toByteArray());
		}
		response.setContentType("application/pdf");
		response.setContentLength(bout.size());
		
		bout.writeTo(response.getOutputStream());
		response.getOutputStream().close();
	    }
	} else {
            if (wrapper.getContentType()!=null) {
                response.setContentType(wrapper.getContentType());
            }

	    response.getWriter().print(wrapper.getString());
	}
    }

    /**
     * If you need to modify the PDF in any way after it has been generated,
     * you can do it by extending the PDFFilter class and overriding this method.
     * By default this method is a no-op.
     */
    protected void modifyPDF(PDF pdf) {
    }

    private class CharResponseWrapper extends HttpServletResponseWrapper {
	private static final boolean debug=false;
        private OutputStreamWrapper bout;
	private PrintWriter pout;
	private CharArrayWriter cout;
	private int status=200;
	private String ctype;

        private class OutputStreamWrapper extends ServletOutputStream {
	    ByteArrayOutputStream out;

            public OutputStreamWrapper() {
                out = new ByteArrayOutputStream();
            }

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {

			}

			public void write(int i) {
                out.write(i);
            }

            public void write(byte[] b, int off, int len) {
                out.write(b, off, len);
            }

            public void flush() throws IOException {
                out.flush();
            }

            public void close() throws IOException {
                out.close();
            }

	    public String getString(String charset) throws IOException {
		String s = new String(out.toByteArray(), charset);
	        return s;
	    }
        }

	public CharResponseWrapper(HttpServletResponse response) {
	    super(response);
	}

	public String getString() throws IOException {
	    if (bout!=null) {
		return bout.getString(getCharacterEncoding());
	    } else if (cout!=null) {
		return cout.toString();
	    } else {
	        return "";
	    }
	}

	public PrintWriter getWriter() throws IOException {
	    if (debug) System.out.println("--> getWriter()");
	    if (bout!=null) throw new IllegalStateException("Already called getOutputStream");
	    if (cout==null) {
		cout = new CharArrayWriter();
		pout = new PrintWriter(cout);
	    }
	    return pout;
	}

	public ServletOutputStream getOutputStream() throws IOException {
	    if (debug) System.out.println("--> getOutputStream()");
	    if (cout!=null) throw new IllegalStateException("Already called getWriter");
	    if (bout==null) bout = new OutputStreamWrapper();
	    return bout;
	}

	public void flushBuffer() {
	    if (debug) System.out.println("--> flushBuffer()");
        }

        public boolean isCommitted() {
	    if (debug) System.out.println("--> isCommitted()");
            return false;
        }

        public void reset() throws IllegalStateException {
	    if (debug) System.out.println("--> reset()");
            cout=null;
            bout=null;
            super.reset();
        }

        public void resetBuffer() {
	    if (debug) System.out.println("--> resetBuffer()");
            cout=null;
            bout=null;
        }

	public void sendError(int sc, String msg) throws IOException {
	    this.status=sc;
	    super.sendError(sc,msg);
	}

	public void sendError(int sc) throws IOException {
	    this.status=sc;
	    super.sendError(sc);
	}

	public void setStatus(int sc) {
	    if (debug) System.out.println("--> setStatus("+sc+")");
	    this.status=sc;
	    super.setStatus(sc);
	}

	public void sendRedirect(String loc) throws IOException {
	    if (debug) System.out.println("--> sendRedirect(\""+loc+"\")");
	    this.status=302;
	    super.sendRedirect(loc);
	}

	public int getStatus() {
	    return status;
	}

	public void setContentType(String type) {
	    if (debug) System.out.println("--> setContentType(\""+type+"\")");

            // Tomcat 4.0.4 and others in that series have a problem in that once the charset
            // is set it can't be unset - and a charset following an "application/pdf" breaks IE.
            // Here we strip if off before calling super and reinstate it later if the file isn't
            // converted to PDF after all. Unfortunately Oracle Application Server (at least 10g)
            // requires a charset to be set or it will throw an exception, so we have to test the
            // server engine to see what to do.

            this.ctype=type;
            String server = getFilterConfig().getServletContext().getServerInfo();
            if (type!=null && !server.startsWith("Oracle Application Server")) {
                int i = type.indexOf(';');
                if (i>0) type=type.substring(0,i);
            }
	    super.setContentType(type);
	}

	public void setHeader(String name, String value) {
	    if (debug) System.out.println("--> setHeader(\""+name+"\", \""+value+"\")");
	    if ("Content-Type".equalsIgnoreCase(name)) {
		setContentType(value);
	    } else {
		super.setHeader(name, value);
	    }
	}

	public void setLocale(Locale locale) {
	    if (debug) System.out.println("--> setLocale("+locale+")");
	    super.setLocale(locale);
	}

	public String getCharacterEncoding() {
	    String charset="";
	    if (ctype!=null && ctype.indexOf(";")>0 && ctype.indexOf("charset")>0) {
	        int pos = ctype.indexOf("charset")+8;
		charset = ctype.substring(pos).trim();
		if (charset.charAt(0)=='=') charset=charset.substring(1).trim();
		if (charset.indexOf(";")>=0) charset=charset.substring(0,charset.indexOf(";")).trim();
	    }
	    if (debug) System.out.println("--> getCharacterEncoding() - this="+charset+" super="+super.getCharacterEncoding());
	    if (charset.length()==0) charset=super.getCharacterEncoding();
	    return charset;
	}

	public String getContentType() {
	    if (debug) System.out.println("--> getContentType() --> \""+ctype+"\"");
	    return ctype;
	}
    }

    // A class to pass the unknown Meta tags back to a context where
    // we have a HttpServletResponse to use them
    //
    private class MetaCallback implements MetaHandler {
	private HttpServletRequest reader;
	private HttpServletResponse writer;
	private PDFFilter prox;

        public MetaCallback(HttpServletRequest reader, HttpServletResponse writer, PDFFilter prox) {
	    this.reader=reader;
	    this.writer=writer;
	    this.prox=prox;
	}

	// Whenever this is called, pass the request back to the servlet
	// to handle it (so the method can be overridden).
	//
	public void handleMetaTag(String key, String val) throws SAXException {
	    try {
		prox.metaTag(reader, writer, key, val);
	    } catch (Exception e) {
	        throw new SAXException(e);
	    }
	}
    }

    /**
     * Handle any meta tags that aren't recognised by the core Report Generator.
     * This method recognises tags begining with <code>HTTP-</code>, as well as
     * <code>Servlet-FileName</code>.
     *
     * @param request the Servlet request
     * @param response the Servlet request
     * @param name the "name" attribute from the meta tag
     * @param value the "value" attribute from the meta tag
     */
    public void metaTag(HttpServletRequest reader, HttpServletResponse writer, String name, String value)
        throws ServletException, IOException
    {
        if (name.startsWith("HTTP-")) {
	    writer.setHeader(name.substring(5), value);
	} else if (name.equalsIgnoreCase("Servlet-FileName")) {
	    writer.setHeader("Content-Disposition", "attachment; filename=\""+value+"\"");
	}
    }

    // SAX error handlers from here on

    public void warning(SAXParseException exception) throws SAXException {
	System.err.println("WARNING"+(exception.getLineNumber()>=0 ? " AT "+exception.getSystemId()+" line "+exception.getLineNumber() : "")+": "+exception.getMessage());
        throw exception;
    }

    public void error(SAXParseException exception) throws SAXException {
	System.err.println("ERROR"+(exception.getLineNumber()>=0 ? " AT "+exception.getSystemId()+" line "+exception.getLineNumber() : "")+": "+exception.getMessage());
	throw exception;
    }

    public void fatalError(SAXParseException exception) throws SAXException {
	System.err.println("FATAL ERROR"+(exception.getLineNumber()>=0 ? " AT "+exception.getSystemId()+" line "+exception.getLineNumber() : "")+": "+exception.getMessage());
	throw exception;
    }
}
