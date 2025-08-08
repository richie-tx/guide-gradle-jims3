/*
 * Created on Oct 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetOrdersForSupervisionPeriodIdsEvent extends mojo.km.messaging.RequestEvent {

	private List periodIds = new ArrayList();

    /**
     * @return Returns the orderIds.
     */
    public List getPeriodIds() {
        return periodIds;
    }
    /**
     * @param orderIds The orderIds to set.
     */
    public void setPeriodIds(List periodIds) {
        this.periodIds = periodIds;
    }

    /**
     * @param orderIds The orderIds to set.
     */
    public void insertPeriodId(String periodId) {
        this.periodIds.add(periodId);
    }
}
