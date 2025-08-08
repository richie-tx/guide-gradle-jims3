package mojo.km.messaging;

import java.util.Hashtable;
import java.util.Map;
/**
 * Insert the type's description here.
 * Creation date: (8/6/00 11:28:21 AM)
 * @author: 
 * @modelguid {D8D65704-4B68-4CE7-9F95-312CE137E436}
 */
public class Row extends Hashtable {
/**
 * Row constructor comment.
 * @modelguid {041701EF-5F31-4F4D-A84B-34B67D686018}
 */
public Row() {
	super();
}
/**
 * Row constructor comment.
 * @param initialCapacity int
 * @modelguid {28B3B65B-389D-4EB2-BD7B-B469C7DC523A}
 */
public Row(int initialCapacity) {
	super(initialCapacity);
}
/**
 * Row constructor comment.
 * @param initialCapacity int
 * @param loadFactor float
 * @modelguid {42BCD0B9-AB73-495A-B03A-FE9A2C26A8F2}
 */
public Row(int initialCapacity, float loadFactor) {
	super(initialCapacity, loadFactor);
}
/**
 * Row constructor comment.
 * @param t java.util.Map
 * @modelguid {8B220360-1A34-4705-9C6A-166D06A89089}
 */
public Row(Map t) {
	super(t);
}
}