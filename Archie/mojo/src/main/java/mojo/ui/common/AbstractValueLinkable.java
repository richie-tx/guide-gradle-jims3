package mojo.ui.common;

import java.util.HashMap;

import mojo.km.context.ContextManager;

/**
 * <B>AbstractSymbolLinkable</B> implements the SymbolLinkable and provides default implementations
 * for managing the list of symbol change groups. There are two ways provided in this package to use
 * this class. The first way is to extend AbstractSymbolLinkable and implement changeSymbol(). If it
 * is not possible to extend AbstractSymbolLinkable, because it is necessary to extend another class
 * instead, then the class should implement the SymbolLinkable interface by delegating to a concrete
 * subclass of AbstractSymbolLinkable.
 * <p>
 * 
 * @see ValueLinkableDelegate.
 * 
 * @author R. Ratliff
 * @modelguid {2BA95FBE-8900-492C-863B-966930B499AF}
 */
public abstract class AbstractValueLinkable implements ValueLinkable
{
	/** @modelguid {9BB6809F-AC89-400C-B2BB-66BA67B8E354} */
	AbstractValueLinkable()
	{
		mGroups = new HashMap();
	}

	/**
	 * This method is used to add the component to the specified Value change group.
	 * 
	 * @param group
	 *            is the name of the group to which the component is joining.
	 * @modelguid {2C4E5870-FC8A-40DC-867C-80DE8D358DB4}
	 */
	public synchronized void joinValueChangeGroup(String group)
	{
		ValueChangeListener listener = new ValueChangeListener(this);
		mGroups.put(group, listener);
		ContextManager context = ContextManager.currentContext();
		context.registerEventListener(listener, new ValueChangeEvent(group, null));
	}

	/**
	 * This method is used to remove the component from the specified Value change group.
	 * 
	 * @param group
	 *            is the name of the group from which the component is quitting.
	 * @modelguid {38A26A70-2FEC-4E0A-B9EF-E3B7E98CCD72}
	 */
	public synchronized void quitValueChangeGroup(String group)
	{
		ValueChangeListener listener = (ValueChangeListener) mGroups.get(group);
		ContextManager context = ContextManager.currentContext();
		mGroups.remove(group);
	}

	/**
	 * This method is used to obtain the list of all groups to which this component belongs.
	 * 
	 * @return the list of all groups to which this component is a member.
	 * @modelguid {17641BC1-42CC-4B92-94C2-9478FABB137C}
	 */
	public synchronized String[] getValueChangeGroups()
	{
		Object[] keys = mGroups.keySet().toArray();
		String[] groups = new String[keys.length];
		for (int i = 0; i < keys.length; i++)
		{
			groups[i] = (String) keys[i];
		}
		return groups;
	}

	/**
	 * This method is used to notify the component of a Value change. Each component should
	 * implement this method in such a way that this method is a noop if the Value parameter equals
	 * the component's current Value.
	 * 
	 * @param Value
	 *            is the new Value to display.
	 * @modelguid {5B6D592C-E7FF-48FF-9F27-BF051F3906A7}
	 */
	public abstract void changeValue(String Value);

	/**
	 * This holds the list of unique Value change groups to which this component belongs.
	 * 
	 * @modelguid {D867AECF-9FF3-4008-82FE-ED313EC6FF7F}
	 */
	private HashMap mGroups;
}
