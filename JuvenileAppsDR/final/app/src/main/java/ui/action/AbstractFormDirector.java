/*
 * Created on Feb 7, 2006
 *
 */
package ui.action;

import javax.servlet.http.HttpServletRequest;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import mojo.pattern.ui.IFormDirector;

/**
 * @author Jim Fisher
 *  
 */
public abstract class AbstractFormDirector implements IFormDirector
{
    public abstract void initFromSession(HttpServletRequest aRequest);

    private boolean unrecoverableError;

    private boolean recoverableError;

    protected void addNonEditableActionError(Object fieldName, Object value, ActionErrors errors)
    {
        Object[] values = new Object[2];
        values[0] = fieldName;
        values[1] = value;
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.REQUIRED_NONEDITABLE_ERROR, values));
    }

    protected void addEditableActionError(Object fieldName, Object value, ActionErrors errors)
    {
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.REQUIRED_EDITABLE_ERROR, fieldName, value));
    }

    protected void addNonEditableMissingActionError(Object fieldName, ActionErrors errors)
    {
        Object[] values = new Object[1];
        values[0] = fieldName;
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.REQUIRED_NONEDITABLE_MISSING_ERROR, values));
    }

    protected void addFieldExceedsLengthActionError(Object fieldName, Object value, int maxLength, ActionErrors errors)
    {
        Object[] values = new Object[5];
        values[0] = fieldName;
        values[1] = value;
        values[2] = new Integer(maxLength);
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.EXCEEDS_MAX_LENGTH_ERROR, values));
    }

    /**
     * @return
     */
    public boolean hasRecoverableError()
    {
        return recoverableError;
    }

    /**
     * @return
     */
    public boolean hasUnrecoverableError()
    {
        return unrecoverableError;
    }

    /**
     * @param b
     */
    public void setRecoverableError(boolean b)
    {
        recoverableError = b;
    }

    /**
     * @param b
     */
    public void setUnrecoverableError(boolean b)
    {
        unrecoverableError = b;
    }

}
