/*
 * Created on Jan 18, 2006
 *
 */
package messaging.criminalcase;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSpnEvent extends RequestEvent
{
private String agencyId;
private String spn;

/**
 * @return Returns the agencyId.
 */
public String getAgencyId() {
    return agencyId;
}
/**
 * @return
 */
public String getSpn()
{
	return spn;
}
/**
 * @param agencyId The agencyId to set.
 */
public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
}

/**
 * @param aSpn
 */
public void setSpn(String aSpn)
{
	spn = aSpn;
}
}
