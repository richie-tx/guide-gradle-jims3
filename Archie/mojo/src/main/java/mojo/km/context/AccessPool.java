package mojo.km.context;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.Stack;

/** Responsible for handling multiple invocations of an object on threaded VM execution of servelets an session beans 
 * @modelguid {5C7A3D85-18A0-42E3-A3BC-197C063C620C}
 */
public class AccessPool {    
    /**
     * @ Check if pool item is busy.
     * @return   
     * @modelguid {DC93830F-EF87-46AE-9654-90F5F5A5DC2F}
     */
    public boolean isBusy() {
        return busy;
    }

    /** Clear all pool items and restart pool. 
     * @modelguid {4A0C0F8B-25FB-43F4-9B73-BCAF6E9C42E6}
     */
    static public void clear() {
        poolHash.clear();
    }

	/** @modelguid {1AEAC9E0-399A-496B-B0A1-DD67005E4D20} */
    public void reset() {
        busy = false;
    }

	/** @modelguid {405F2A76-A105-4CF1-9190-C537841674A5} */
    public Object getSource() {
        return source;
    }

	/** @modelguid {06E78E5B-B3A4-4396-897F-4BFE1BEC239F} */
    public void setSource( Object source ) {
        this.source = source;
    }
    /**
     * @ Deletes a pool item.
     * @param source - object source to relate to pool
     * @modelguid {C0C0C53B-D2A2-45B9-A8EE-0899FADA8877}
     */
    public static synchronized void removePool(Object source) {
        Vector pool = (Vector) poolHash.get( source );
        poolHash.remove( source );
        unusedPool.push( pool );
    }
    /**
     * @ Returns a busy instance of a pool item and sets the flag of item to busy.
     * @param source - object source to relate to pool
     * @return  pool Object 
     * @modelguid {BC65130E-D5AE-4E30-80AB-8BBDDF7B242B}
     */
    public static synchronized AccessPool getInstance(Object source) {
        Vector pool = null;
        if (poolHash.containsKey(source)) {
            pool = (Vector) poolHash.get( source );
            boolean found = false;
            for (Iterator i = pool.iterator(); i.hasNext(); ) {
                AccessPool item = (AccessPool)i.next();
                if (!item.isBusy()) {
                    item.busy = true;
                    return item;
                }
            }
        } else {
            if (unusedPool.size() > 0) {
                pool = (Vector) unusedPool.pop();
            } else {
           	 	pool = new Vector();
            }
            poolHash.put(source, pool);
        }
        addToPool(pool, source);
        return getInstance(source);
    }

	/** @modelguid {41B0A577-78DC-4EFD-87EE-DA24DF7524A9} */
    private static void addToPool(Vector pool, Object source) {
        AccessPool poolItem = new AccessPool();
        poolItem.setSource( source );
        pool.add(new AccessPool());
    }

	/** @modelguid {DFFD3AD4-4534-47B0-928C-B89D0F9A7C03} */
    private boolean busy = false;

	/** @modelguid {07708875-85F4-4EA3-A7A7-519DAC864764} */
    private Object source = null;

    /**
     * @associates Vector 
     * @modelguid {BADB85C5-DE44-434C-9B0F-6D3F6456BD53}
     */
    private static Hashtable poolHash = new Hashtable();
	/** @modelguid {31E7B472-4360-4B09-8284-57D7D5007EE8} */
    private static Stack unusedPool = new Stack();
}
