//Source file: Z:/java.vob/SystemServiceLayer/Request/QueueStrategy.java

package mojo.km.dispatch.Logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;

import mojo.km.config.AppProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.noop.NoReply;

/**
 * Responsible for sending events to a logging service.
 * These events will then be posted to server that manages a logging service.
 * @author Eric A Amundson
 * @modelguid {74FA4984-08B2-4491-9DEA-E16F7EC2B589}
 */
public class LoggingMessageStrategy extends EventManager {
    /** Default constructor.   Makes connection to log service via interprocess comm. 
     * @modelguid {560822F2-951C-4D5B-A3F1-FD91AB807528}
     */
    public LoggingMessageStrategy() {
        url = AppProperties.getInstance().getProperty("loggingHost");
    }

    /**
     * Use string url for log location definition.
     * @modelguid {4F26E718-468B-4E95-A4A4-F860F4184221}
     */
    public LoggingMessageStrategy(String aURL) {
        url = aURL;
    }

	/** @modelguid {A8C214C6-A176-4154-B3C6-A3E0ECF53659} */
    private void checkSocket() {
        if (group == null) {
            try {
                URL aURL = new URL(url);
                group = InetAddress.getByName(aURL.getHost());
                port = aURL.getPort();
                theSocket = new DatagramSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get log reply.  N/A
     *    @return no reply
     * @modelguid {E8B54BF2-B9E9-4669-8789-2350134FE0B0}
     */
    public IEvent getReply() {
        return new NoReply();
    }

    /**
     *     Post log event to logging server.
     *     @param event - sent log event.
     * @modelguid {76DC35EC-47B7-4774-BFDD-D177D019100F}
     */
    public synchronized void postEvent(IEvent event) {
        checkSocket();
        try {
            byte[] buf = event.toString().getBytes();
            thePacket = new DatagramPacket(buf, buf.length, group, port);
            theSocket.send(thePacket);
        } catch (Exception e) {
            ByteArrayOutputStream oStream = new ByteArrayOutputStream();
            PrintStream pOStream = new PrintStream(oStream);
            e.printStackTrace(pOStream);
        }
    }

    /**
     * No implementation.
     *    @param service - n/a
     *    @param event - n/a
     * @modelguid {4FF745EA-DF29-498D-9B6F-5FEA15993B58}
     */
    public void postEvent(String service, byte[] event) {
        // Get a publisher flow for the channel.

/*
        try {
            Flow f = null;
            if (!flows.containsKey(oesClientPath + service)) {
                f = JctLib.publisher(oesClientPath + service );
                f.startEvents();
                flows.put( oesClientPath + service, f );
            } else {
                f = (Flow) flows.get( oesClientPath + service );
            }
            if (f != null) {
            // Start allowing events to flow.
	            f.push(JctLib.createEventBody("IEvent", event));
	        }
	    } catch (Exception x) {
	        x.printStackTrace();
            throw new RuntimeException(x.getMessage());
        }
        */
    }

    /** Cleanup host connection. 
     * @modelguid {C7E248DD-86AD-451D-8AF1-EE0180343832}
     */
    protected void finalize() {
        try {
            super.finalize();
            if (theSocket != null) {
                theSocket.close();
            }
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }

	/** @modelguid {0E4ECA50-E880-420B-A282-CCDA6239F471} */
    int port = 8090;
	/** @modelguid {199C7633-E0B9-46BD-9C0B-67FE0B9AF02C} */
    int ackPort = 8091;
	/** @modelguid {A7215419-901A-4243-ADBF-6490B4EE3D9D} */
    DatagramSocket theSocket = null;
	/** @modelguid {EC6B5C00-A797-45A3-A73E-B4BA5F93B27D} */
    DatagramSocket theAckSocket = null;
	/** @modelguid {36296771-9051-4F21-BBBC-9784931A44B9} */
    DatagramPacket thePacket = null;
	/** @modelguid {2F78A167-7F27-40E0-8060-163837FDA7B6} */
    InetAddress group = null;
	/** @modelguid {EEF9EB87-4F5C-4DCD-B468-CC3BB6C44241} */
    String url = null;
}
