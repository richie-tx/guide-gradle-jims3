/*
 * Created on Dec 11, 2006
 *
 */
package ui.juvenilewarrant.helper;

import java.util.Comparator;

import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;

/**
 * @author Jim Fisher
 * 
 */
public class JuvenileWarrantNumberComparator implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        String key1 = ((JuvenileWarrantResponseEvent) o1).getWarrantNum();
        String key2 = ((JuvenileWarrantResponseEvent) o2).getWarrantNum();
        if(key1!=null)
        	key1=key1.trim();
        else{
        	key1="0";
        }
        if(key2!=null)
        	key2=key2.trim();
        else{
        	key2="0";
        }
        if(key1.equals("")){
        	key1="0";
        }
        if(key2.equals("")){
        	key2="0";
        }
        Integer keyNum1 = new Integer(Integer.parseInt(key1));
        Integer keyNum2 = new Integer(Integer.parseInt(key2));
        if(keyNum1==null){
        	keyNum1=new Integer(0);
        }
        if(keyNum2==null){
        	keyNum2=new Integer(0);
        }
        return keyNum1.compareTo(keyNum2);
    }
}
