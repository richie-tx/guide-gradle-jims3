package mojo.ui.web;

import java.util.*;

/** @modelguid {CFF5EB9E-1B24-49A4-8883-F89468507E2E} */
public interface ListMap
{

	/** @modelguid {D0B7B6EB-5CD3-457E-87CF-3706A1134325} */
  public Map map();    

  // Map methods
	/** @modelguid {1E2988D1-1AA0-4F95-B4A9-208895B0B51D} */
  public boolean containsKey(Object key);    

	/** @modelguid {405DE09B-6E1B-4A85-A0CD-54CF2B962380} */
  public boolean containsValue(Object value);    

	/** @modelguid {7AF0EA84-9132-47BA-8924-67D39697CCDC} */
  public Set entrySet();    

	/** @modelguid {5BAC6A18-2F18-42AC-9DED-088036229613} */
  public Object get(Object key);    

	/** @modelguid {180E2AC2-6562-472C-8865-31510B573021} */
  public Set keySet();    

	/** @modelguid {BB9CDC86-A1E6-4A4F-83C3-92F86F2E9A6F} */
  public Collection values();    

  // List methods
	/** @modelguid {F3EBAD88-4C11-4142-96BE-CD2A4721B38F} */
  public boolean containsAll(Collection c);    

	/** @modelguid {DDEC98FA-D5FD-4ACD-8EBE-623928B506E2} */
  public Object get(int index);    

	/** @modelguid {38695354-828E-408D-BD3B-D64E11B12B1B} */
  public int indexOf(Object o);    

	/** @modelguid {E201346F-695B-4C83-8B15-3160C71EFB58} */
  public Iterator iterator();    

	/** @modelguid {22C41978-BF24-4C03-8AAE-E7C75E524E9A} */
  public int lastIndexOf(Object o);    

	/** @modelguid {D04EA20F-9E11-4A4C-8A96-E913F95AD5E3} */
  public ListIterator listIterator();    

	/** @modelguid {2D181049-E74B-4EB9-A3F8-2E48036E620E} */
  public ListIterator listIterator(int index);    

	/** @modelguid {64528B9C-2B56-4ABC-8E8D-33833E74654B} */
  public List subList(int fromIndex, int toIndex);    

	/** @modelguid {D0CE6A45-2138-4A27-89EE-CAC38A93ECD9} */
  public Object[] toArray();    

	/** @modelguid {14EC0137-3D71-4D1A-BACE-DFC9DB58F8A1} */
  public Object[] toArray(Object[] a);    

  // Common methods
	/** @modelguid {805993EE-C898-462F-AB49-097256031A73} */
  public void add(int index, Object element);    

	/** @modelguid {300E1C18-0C1C-420D-B460-D625F42AFDEB} */
  public boolean add(Object o);    

	/** @modelguid {2413F455-F046-4E90-BFC4-FC511323DCD3} */
  public void clear();    

	/** @modelguid {BE3C5491-5D35-4159-95AA-04D7369712CA} */
  public boolean equals(Object o);    

	/** @modelguid {00503AA9-0BF4-4B1B-9EF0-818DAD902818} */
  public int hashCode();    

	/** @modelguid {C5972B77-31AB-4FFD-BF53-4EFA8FF5D1D9} */
  public boolean isEmpty();    

	/** @modelguid {9BD10D21-B81A-4D67-9A6D-F22FA698D655} */
  public Object put(Object key, Object value);    

	/** @modelguid {8198D1A9-5C5C-4AF4-B0CE-435A264ED432} */
  public void putAll(Map t);    

	/** @modelguid {D2E1DC5B-8384-4705-8336-7A109A9849A5} */
  public void putAll(ListMap t);    

	/** @modelguid {B2C96188-F069-4968-87BC-9127409D7B3D} */
  public Object remove(Object key);    

	/** @modelguid {41DDBF24-4CCA-4817-A177-0EEB0213CB8E} */
  public Object remove(int index);    

	/** @modelguid {E71E8990-C72D-466D-89C1-A8EB8069ED18} */
  public int size();    
}