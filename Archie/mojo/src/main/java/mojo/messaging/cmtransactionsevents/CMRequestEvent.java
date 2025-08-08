package mojo.messaging.cmtransactionsevents;

import mojo.km.messaging.RequestEvent;

/**
 * ReportRequestEvent is responsible for housing data, i.e. events, which will
 * be sent to control command ...
 * @modelguid {9F78679F-4100-404D-A308-96610503ED00}
 */
public class CMRequestEvent extends RequestEvent {
	/** @modelguid {E9059FA1-0D2C-4899-9787-E4A1ED327AB9} */
	private String reportOID;

	/** @modelguid {484A9E45-C809-47BE-96D7-02C3A95EB6AD} */
    public CMRequestEvent() {
    }

	/** @modelguid {440BE2B8-B566-4B61-AA4E-B7AB828C4B1C} */
    public String getReportOID() {
        return reportOID;
    }

	/** @modelguid {7F4A968D-4FF3-42B5-89A8-78ECA94E8093} */
    public void setReportOID(String reportOID) {
        this.reportOID = reportOID;
    }

}
