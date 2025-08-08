package mojo.km.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Add ListModel and ComboBoxModel interfaces to an ArrayList.  Allows
 * an ArrayList to be used as a model for a JList or JComboBox.
 * @modelguid {9D62A28D-CA7B-4440-B499-A14961D10E16}
 */
public class ArrayListModel extends ArrayList implements ComboBoxModel  
{
	/** @modelguid {C86A0E99-4F89-4FB9-9619-5086B677F3E1} */
    private Collection mListDataListeners;
	/** @modelguid {659FB5AC-1A8B-4EDF-BB35-080D16A288BE} */
    private Object mSelected;
    
	/** @modelguid {69FEA0FD-2733-497E-889D-D86359498D7E} */
    private void setListeners( Collection listeners )
    {
        listeners          = Collections.synchronizedCollection( listeners );
        mListDataListeners = Collections.unmodifiableCollection( listeners );
    }
    
	/** @modelguid {44CEB448-51E9-43F4-AF65-FE231AD3370E} */
    public ArrayListModel()
    {
        setListeners( new ArrayList() );
    }
    
	/** @modelguid {11123DA8-5FDC-465E-B51E-3E9B09F71ADF} */
    public ArrayListModel( Collection collection )
    {
        super(collection);
        setListeners( new ArrayList() );
    }
    
	/** @modelguid {765C5F0F-1E84-49E6-BB6A-27E032257987} */
    public ArrayListModel( int initialCapacity )
    {
        super(initialCapacity);
        setListeners( new ArrayList() );
    }
    
	/** @modelguid {F7440E48-B312-4D09-AE6C-C1570EAA7621} */
    protected void fireEntireContentsChanged()
    {
        Collection    listeners = mListDataListeners;
        Iterator      iter      = listeners.iterator();
        ListDataEvent event     = new ListDataEvent( this, 
                                                     ListDataEvent.CONTENTS_CHANGED, 
                                                     0, size()-1 );
        
        while (iter.hasNext())
        {
            ListDataListener listener = (ListDataListener) iter.next();
            listener.contentsChanged(event);
        }
    }

	/** @modelguid {484A0A28-4638-4DA7-BCFB-43BDFC48658E} */
    public void addListDataListener(ListDataListener l)
    {
        synchronized(mListDataListeners)
        {
            Collection listeners = new ArrayList(mListDataListeners);
            listeners.add(l);
            setListeners(listeners);
        }
    }

	/** @modelguid {F555E398-63FD-47EA-9B34-E323BD937E21} */
    public void removeListDataListener(ListDataListener l)
    {
        synchronized(mListDataListeners)
        {
            Collection listeners = new ArrayList(mListDataListeners);
            listeners.remove(l);
            setListeners(listeners);
        }
    }
    
	/** @modelguid {720FA6F2-3D2B-498D-AD93-A65826228005} */
    public void setSelectedItem( Object item )
    {
        mSelected = item;
    }
    
	/** @modelguid {76292950-134B-4F04-A618-22A66D8DFFBA} */
    public Object getSelectedItem()
    {
        return mSelected;
    }

	/** @modelguid {10A8E4D1-6856-4008-9B31-E278219F1AD4} */
    public Object getElementAt(int index)
    {
        return get(index);
    }

	/** @modelguid {43CD0D09-20A9-43F4-B221-B7BB0AC67F35} */
    public int getSize()
    {
        return size();
    }

	/** @modelguid {880314CB-F8D3-4ADB-8772-EF3A5AF65C79} */
    public void add(int index, Object element)
    {
        super.add(index,element);
        fireEntireContentsChanged();
    }

	/** @modelguid {9CD033EA-BA1B-4A01-8C9D-4CBD8F633040} */
    public boolean add(Object o)
    {
        boolean retval;
        retval = super.add(o);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {A93C2A1A-31CB-4B97-B371-1B1783FFFAAD} */
    public boolean addAll(Collection c)
    {
        boolean retval;
        retval = super.addAll(c);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {4B9CC8BE-3097-485E-8DE1-303CBABE405E} */
    public boolean addAll(int index, Collection c)
    {
        boolean retval;
        retval = super.addAll(index,c);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {2B5BC29D-FD71-4D13-AD57-64F9E4626AFC} */
    public void clear()
    {
        super.clear();
        fireEntireContentsChanged();
    }

	/** @modelguid {3226463A-83B5-4BD9-A113-E589EE70A5A6} */
    public boolean remove(Object o)
    {
        boolean retval;
        retval = super.remove(o);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {C8DF52B2-E788-4775-ABFA-A68042889E3C} */
    public Object remove(int index)
    {
        Object retval;
        retval = super.remove(index);
        fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {A0AD806D-C37C-441C-B7E5-8B942437AAAB} */
    public boolean removeAll(Collection c)
    {
        boolean retval;
        retval = super.removeAll(c);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {0D873D4D-ABF9-41F6-9B33-B238DF7A4244} */
    public void removeRange(int fromIndex, int toIndex)
    {
        super.removeRange(fromIndex,toIndex);
        fireEntireContentsChanged();
    }

	/** @modelguid {86E22BBD-9F9F-4966-A70C-E5F3631FEE67} */
    public boolean retainAll(Collection c)
    {
        boolean retval;
        retval = super.retainAll(c);
        if (retval) fireEntireContentsChanged();
        return retval;
    }

	/** @modelguid {4A09476F-FBED-4C2F-9DAC-572FDC3574AF} */
    public Object set(int index, Object element)
    {
        Object retval;
        retval = super.set(index,element);
        fireEntireContentsChanged();
        return retval;
    }
}