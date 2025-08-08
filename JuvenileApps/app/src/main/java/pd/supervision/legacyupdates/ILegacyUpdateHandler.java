/*
 * Created on Dec 18, 2007
 *
  */
package pd.supervision.legacyupdates;

import java.io.IOException;

import mojo.km.messaging.IEvent;


/**
 * @author dgibler
 *
 */
public interface ILegacyUpdateHandler {
    public String createLog(IEvent event) throws IOException;
    public void deleteLog(String sourceOid, String recType, String spn);
    public void updateLegacy(LegacyUpdateLog logger);
    public void createLegacy(LegacyUpdateLog logger);
    public void deleteLegacy(LegacyUpdateLog logger);
}
