package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSDetentionBySupervisionNumEvent extends RequestEvent
{
    private String bookingSupervisionNum;
    private String currentSupervisionNum;

    public String getBookingSupervisionNum()
    {
	return bookingSupervisionNum;
    }

    public void setBookingSupervisionNum(String bookingSupervisionNum)
    {
	this.bookingSupervisionNum = bookingSupervisionNum;
    }

    public String getCurrentSupervisionNum()
    {
	return currentSupervisionNum;
    }

    public void setCurrentSupervisionNum(String currentSupervisionNum)
    {
	this.currentSupervisionNum = currentSupervisionNum;
    }

}
