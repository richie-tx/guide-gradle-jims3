/*
 * Created on Aug 21, 2008
 *
  */
package pd.supervision.legacyupdates;

import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 */
public interface ILegacyUpdates {
    public String create(IEvent reqEvent, String recType) throws Exception;
    public void delete(String sourceOid, String recType, String spn) throws Exception;
}
