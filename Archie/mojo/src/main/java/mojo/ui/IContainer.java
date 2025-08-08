package mojo.ui;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import java.util.Collection;
import mojo.ui.exception.*;

/** @modelguid {A77C632A-B689-4377-BC36-B70F7ADE262C} */
public interface IContainer extends IComponent {
    /**
     *   @modelguid {DB480E77-BED2-4881-B534-B1D643617250}
     */
    public IPanel getPanel(String aPanelName);

    /**
     *   @modelguid {004EE7D0-EF7E-4FEB-BF95-39131877815F}
     */
    public void insertPanel(IPanel aPanel);

    /**
     *   @modelguid {EE73E339-1F55-431F-9B48-A2DF650AEF78}
     */
    public void deletePanel(IPanel aPanel);

    /**
     *   @modelguid {7A4DCBDA-AEEA-449B-BEEB-F24D6ADEF0CA}
     */
    public void insertWidget(IWidget aWidget);

    /**
     *   @modelguid {C0B9653B-A8A9-4B49-B22E-9B71FE9E6CF9}
     */
    public void deleteWidget(IWidget aWidget);

    /**
     *   @modelguid {6E8281C4-56F4-4937-AD16-05C04AF04F56}
     */
	public void initialize(IEvent aMessage) throws UIException;

    /**
     *   @modelguid {9AA80CA3-DB53-499C-B278-A12DFCE232F7}
     */
    public void reset();

    /**
     *   @modelguid {04A4211C-0F16-40ED-A94B-CFBD39EAF193}
     */
    public Collection getPanels();

    /**
     *   @modelguid {787E96A0-EEBC-40AE-A4AC-57A12821024E}
     */
    public Collection getWidgets();
}
