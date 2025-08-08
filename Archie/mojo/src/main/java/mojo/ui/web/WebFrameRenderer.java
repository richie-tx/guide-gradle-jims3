package mojo.ui.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import mojo.ui.exception.UIException;
import mojo.ui.IFrame;
import mojo.ui.IFrameRenderer;
import java.io.*;
import mojo.km.messaging.*;

/** @modelguid {43022A6A-035A-47FE-8E30-151C501B603E} */
public class WebFrameRenderer implements IFrameRenderer {
	/** @modelguid {BD5C3FBE-DAB4-43E7-A563-EE4EEF210593} */
    private HttpServletRequest request;
	/** @modelguid {870A95EB-AF7B-40C1-8A50-C13B94AA1BDD} */
    private HttpServletResponse response;
	/** @modelguid {53A58961-92A4-4FC3-BFCC-CFFD1984B28B} */
    private ServletContext servletContext;

    /**
     * Insert the method's description here.
     * Creation date: (11/15/2000 8:57:03 AM)
     * @modelguid {19ADC33C-7EC1-4199-AAF1-44C6FC067746}
     */
    public WebFrameRenderer() {
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/15/2000 8:57:03 AM)
     * @modelguid {19BFC301-B931-4899-97F1-FC83DD382DF2}
     */
    public WebFrameRenderer(ServletContext aServletContext, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        if (aServletContext == null || aRequest == null || aResponse == null) {
            throw new IllegalArgumentException("Argument(s) cannot be null");
        }
        servletContext = aServletContext;
        request = aRequest;
        response = aResponse;
    }

	/** @modelguid {08AD4A55-8DD6-4AD8-B044-CAE40D6E128E} */
    private HttpServletRequest getHttpRequest() {
        return request;
    }

	/** @modelguid {B9A71FF5-0A05-4270-8737-2A5D2532395E} */
    private HttpServletResponse getHttpResponse() {
        return response;
    }

	/** @modelguid {DE893252-D974-4EA3-A0C7-48973E3B4907} */
    private ServletContext getServletContext() {
        return servletContext;
    }

	/** @modelguid {C8A9A4A8-7777-4780-84E5-233F7B4603ED} */
    public void renderFrame(IFrame aFrame, IEvent aMessage) throws UIException {
        try {
            if (aFrame instanceof IStringFrame) {
                renderFrame((IStringFrame)aFrame, aMessage);
            } else {
                if (aFrame instanceof IFileFrame) {
                    renderFrame((IFileFrame)aFrame, aMessage);
                }
            }
        } catch (UIException e) {
            throw new UIException("Could not render view '" + aFrame.getName() + "'\n" + e.getMessage());
        }
    }

	/** @modelguid {61EFB472-422D-4208-8F78-E4E6B67E044A} */
    private void renderFrame(IFileFrame aFrame, IEvent aMessage) throws UIException {
        RequestDispatcher lDispatcher = null;
        if (getHttpRequest().getAttribute("Event") != null)
            getHttpRequest().removeAttribute("Event");
        getHttpRequest().setAttribute("Event", aMessage);
        try {
            lDispatcher = getServletContext().getRequestDispatcher(aFrame.getLocation());
        } catch (Throwable e) {
            throw new UIException("Could not access the request dispatcher\n" + e);
        }
        if (lDispatcher == null) {
            throw new UIException("Could not forward the request.\n The given URL '" + aFrame.getLocation() + "' is invalid");
        }
        try {
            lDispatcher.forward(getHttpRequest(), getHttpResponse());
        } catch (Throwable e) {
            throw new UIException("Could not forward the request\n" + e);
        }
    }

	/** @modelguid {B023834C-2D12-416F-A9A5-DFB891A313AD} */
    private void renderFrame(IStringFrame aFrame, IEvent aMessage) throws UIException {
        try {
            PrintWriter lOut = response.getWriter();
            ((IFrame)aFrame).initialize(aMessage);
            lOut.println(aFrame.toString());
        } catch (Throwable exp) {
            throw new UIException("Could not render StringFrame: " + exp);
        }
    }
}
