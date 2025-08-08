package mojo.ui.common;

import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

/**
 * <B>SymbolChangeListener</B> is used to receive SymbolChangeEvents and post them to the SymbolLinkable instance.
 * @author R. Ratliff
 * @modelguid {A9E7317C-4CC8-4A2E-A2CC-C0766493BA65}
 */
public class ValueChangeListener implements ICommand {
    /**
     * This is the only defined constructor for this class.
     * @param control is the charting control where events are to be reported.
     * @modelguid {B5EFDF77-1829-4B81-9C9B-9917477C55BD}
     */
    public ValueChangeListener(ValueLinkable target) {
        mTarget = target;
    }

    /** This method is used to process the SymbolChangeEvents. 
     * @modelguid {30077585-375B-4E2B-A018-3EB2FCD5539F}
     */
    public void execute(IEvent event) {
        mTarget.changeValue(((ValueChangeEvent)event).getValue());
    }
    /** Nothing to do when this instance is added to the list of listeners 
     * @modelguid {FEA5998C-59C7-48F1-ADA2-E074D8C5ACFE}
     */
    public void onRegister(IEvent event) {
    }
    /** Nothing to do when this instance is removed from the list of listeners 
     * @modelguid {8CECE5A6-476B-4317-9A08-F2F220A670B7}
     */
    public void onUnregister(IEvent event) {
    }
    /**     Updates any instance data on command.
     *     @param updateObject - object holding updata data. 
     * @modelguid {DC644424-7574-4C82-86DB-E1C67E5E6C6B}
     */
    public void update(Object updateObject) {}
    /** This is the control to which all symbol change events are reported. 
     * @modelguid {B3524A22-D1AD-4329-B4E9-3F054BB1C0DF}
     */
    private ValueLinkable mTarget;
}
