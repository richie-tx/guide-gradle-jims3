package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSHeaderBySupervisionNumEvent extends RequestEvent
{
    private String bookingSupervisionNum;

    public String getBookingSupervisionNum()
    {
	return bookingSupervisionNum;
    }

    public void setBookingSupervisionNum(String bookingSupervisionNum)
    {
	this.bookingSupervisionNum = bookingSupervisionNum;
    }

}
