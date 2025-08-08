/*
 * Created on Sep 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author bschwartz
 */
public class StringSet extends HashSet
{

	public static interface Stringizer
	{
		public String toString( Object obj ); 
	}


	public StringSet()
	{
	}
	
	public StringSet( Collection coll )
	{
		Iterator iter = coll.iterator();
		while ( iter.hasNext() )
		{
			add( iter.next().toString() );
		}
	}
	
	public StringSet( Iterator iter )
	{
		while ( iter.hasNext() )
		{
			add( iter.next().toString() );
		}
	}

	public StringSet( Collection coll, Stringizer stringizer )
	{
		Iterator iter = coll.iterator();
		while ( iter.hasNext() )
		{
			String str = stringizer.toString(iter.next());
			if ( str != null )
			{
				add( str );
			}
		}
	}

	public StringSet( Iterator iter, Stringizer stringizer )
	{
		while ( iter.hasNext() )
		{
			String str = stringizer.toString(iter.next());
			if ( str != null )
			{
				add( str );
			}
		}
	}
	
	public boolean add( Object obj )
	{
		return super.add( obj.toString() );
	}


	public StringSet intersection( Set s2 )
	{
		StringSet set = new StringSet();

		Iterator iter = iterator();
		while ( iter.hasNext() )
		{
			String str = (String)iter.next();
			if ( s2.contains(str) )
			{
				set.add( str );
			}
		}
		return set;
	}

	public StringSet union( Set s2 )
	{
		StringSet set = new StringSet();
		set.addAll( this );
		set.addAll( s2 );
		return set;
	}

	// returns items that are in s1 but not in s2
	public StringSet complement( Set s2 )
	{
		StringSet set = new StringSet();
		Iterator iter = iterator();
		while ( iter.hasNext() )
		{
			String str = (String)iter.next();
			if ( ! s2.contains(str) )
			{
				set.add( str );
			}
		}
		return set;
	}

	
}
