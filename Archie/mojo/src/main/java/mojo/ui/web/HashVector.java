package mojo.ui.web;

import java.util.*;

/** @modelguid {1BBE7CE5-AAB8-4693-A6C5-33C460B49EF4} */
public class HashVector implements ListMap
{

	/** @modelguid {D7EAC0BE-2890-4AB0-85C2-7BD2E82403BB} */
  private List _list;
	/** @modelguid {61766EBE-8E0B-4F5F-B498-8B755C983F74} */
  private Map _map;

	/** @modelguid {4BD675FD-ED78-4BD8-954D-F9FD638810D5} */
  public HashVector()
  {
	clear();
  }      

	/** @modelguid {59855DF7-8972-4093-8D6C-1354CC68BA1A} */
  public Map map() { return _map; }      

  // Map methods
	/** @modelguid {598C500F-759D-4F44-8A99-869B3C03249B} */
  public boolean containsKey(Object key) { return _map.containsKey(key); }      

	/** @modelguid {A9A6A3F8-D7E1-4394-8650-B3A140A6FF01} */
  public boolean containsValue(Object value) { return _map.containsValue(value); }      

	/** @modelguid {0280218C-B716-41C5-A577-AC8DED6630E1} */
  public Set entrySet() { return _map.entrySet(); }      

	/** @modelguid {6580AD4F-9B3C-4C17-BCBB-B7CB2A756E81} */
  public Object get(Object key) { return _map.get(key); }      

	/** @modelguid {258D6685-6B91-40B0-BB60-EC632B2A3EE4} */
  public Set keySet() { return _map.keySet(); }      

	/** @modelguid {600B7746-71FE-462B-AB7A-1649A1AED4D4} */
  public Collection values() { return _map.values(); }      

  // List methods
	/** @modelguid {3CE635BA-7601-4C6F-A68F-FF60B50CCAEF} */
  public boolean containsAll(Collection c) { return _list.containsAll(c); }      

	/** @modelguid {AE4DB1C0-5FC1-4BB7-8E03-1101FE7C670D} */
  public Object get(int index) { return _list.get(index); }      

	/** @modelguid {48DBC456-F74E-42E7-A5FA-15BC0A85090E} */
  public int indexOf(Object o) { return _list.indexOf(o); }      

	/** @modelguid {606E8CA8-E05D-410E-BC43-414CE38F9CF1} */
  public Iterator iterator() { return _list.iterator(); }      

	/** @modelguid {C0049DC3-CE5F-4CA8-910F-266815059A19} */
  public int lastIndexOf(Object o) { return _list.lastIndexOf(o); }      

	/** @modelguid {3283FC3B-7B1B-4A8E-9EF0-90B6F09DE27A} */
  public ListIterator listIterator() { return _list.listIterator(); }      

	/** @modelguid {AB8AFE36-62D7-4F60-A116-F40E563A9B14} */
  public ListIterator listIterator(int index) { return _list.listIterator(index); }      

	/** @modelguid {1A9E01B6-14F5-4F74-BE96-C097486E2E56} */
  public List subList(int fromIndex, int toIndex) { return _list.subList(fromIndex,toIndex); }      

	/** @modelguid {B49A89D5-4787-4874-839A-8D5F96395BE3} */
  public Object[] toArray() { return _list.toArray(); }      

	/** @modelguid {C69180F2-C834-46E1-BFAD-52CE1A53EC00} */
  public Object[] toArray(Object[] a) { return _list.toArray(a); }      

  // Common methods
  
	/** @modelguid {3A850E88-D58F-4AC9-94AF-AE0A100E8B15} */
  public void add(int index, Object element)
  {
	_list.add(index,element);
	_map.put(element,element);
  }      

	/** @modelguid {6247BE53-995E-40AB-AB66-7A5A4D762D63} */
  public boolean add(Object o)
  {
	_map.put(o,o);
	return _list.add(o);
  }      

	/** @modelguid {BEA05FB5-3ADC-4AFE-8636-EAF7D81C5A25} */
  public void clear()
  {
	_list = new Vector();
	_map = new HashMap();
  }      

	/** @modelguid {0CFF8B4A-4E2E-4CD8-8E87-1CDA70AB117A} */
  public boolean equals(Object o)
  {
	return false;  // FIXME
  }      

	/** @modelguid {EE552487-3377-43E1-936D-0C2EA1D899FC} */
  public int hashCode()
  {
	return _list.hashCode();
  }      

	/** @modelguid {CE79B8FC-75DA-42A6-85E3-1CE196E00F6A} */
  public boolean isEmpty()
  {
	return _list.isEmpty();
  }      

	/** @modelguid {C524E1E7-9E65-455C-906B-263C76167A5F} */
  public Object put(Object key, Object value)
  {
	_list.add(value);
	return _map.put(key,value);
  }      

	/** @modelguid {BA84CD3F-484E-473A-AC04-C80CFDD658BA} */
  public void putAll(Map t)
  {
	_map.putAll(t);
	_list.addAll(t.values());
  }      

	/** @modelguid {42A23B6E-5603-4ECD-854B-59B51753C357} */
  public void putAll(ListMap t)
  {
	_map.putAll(t.map());
	for (Iterator i = t.iterator(); i.hasNext(); )
	  _list.add(i.next());
  }      

	/** @modelguid {707388FB-B6CF-4C4F-8DEF-BF396260D28C} */
  public Object remove(Object key)
  {
	  Object retObj = _map.get(key);
	  if (retObj != null) {
		  int idx = _list.indexOf(retObj);
		  if (idx > -1)
		  	_list.remove(idx);
	  }
	return _map.remove(key);
  }        

	/** @modelguid {644BE6D1-E9A0-40B5-A3B7-4A7773997091} */
  public Object remove(int index)
  {
	_map.remove(_list.get(index));
	return _list.remove(index);
  }      

	/** @modelguid {58D87F73-916B-4BE6-ACB3-C9542E13497A} */
  public int size()
  {
	return _list.size();
  }      
}