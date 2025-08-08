/*
 * Created on Dec 11, 2006
 *
 */
package ui.juvenilewarrant.helper;

import java.util.Comparator;

import messaging.contact.domintf.INameKey;

/**
 * @author Jim Fisher
 * 
 */
public class JuvenileNameComparator implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        String key1 = ((INameKey) o1).getNameKey();
        String key2 = ((INameKey) o2).getNameKey();
        return key1.compareTo(key2);
    }
}
