package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class TrackingControlNumberResponseEvent  extends ResponseEvent implements Comparable
{
    private String nextTrackingNumberControl;

    public String getNextTrackingNumberControl()
    {
	return nextTrackingNumberControl;
    }

    public void setNextTrackingNumberControl(String nextTrackingNumberControl)
    {
	this.nextTrackingNumberControl = nextTrackingNumberControl;
    }

    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

}
