package mojo.km.security.ldap;

import java.util.Enumeration;
import java.util.Hashtable;

/** @modelguid {8D85F239-B06C-47FD-A943-51F668F68211} */
public class DCDAttributeCollection
{
	/** @modelguid {D5546F97-5CBC-4942-8CE8-4E13FE0347E8} */
    private Hashtable attributes = new Hashtable();

	/** @modelguid {F39FF87D-2F88-4181-B1B4-92A9B925BA02} */
    public DCDAttributeCollection()
    {
        enableDCDirect(true);
        enableUserId(true);
        enableFirstName(true);
        enableLastName(true);
    }

	/** @modelguid {7ACE8873-F980-4B56-895F-6EB44AE65231} */
    public void enableDCDirect(boolean enable)
    {
        update(DCDAttribute.DCDIRECT, enable);
    }

	/** @modelguid {138E3551-5A4D-4884-8FCB-9B76B20E308A} */
    public void enableUserId(boolean enable)
    {
        update(DCDAttribute.USERID, enable);
    }

	/** @modelguid {43C430F0-7C02-4254-8E7A-804041CB019E} */
    public void enableFirstName(boolean enable)
    {
        update(DCDAttribute.FIRSTNAME, enable);
    }

	/** @modelguid {F3F73262-88E6-4B25-B5DD-502239BC7AE5} */
    public void enableLastName(boolean enable)
    {
        update(DCDAttribute.SIRNAME, enable);
    }

	/** @modelguid {4234BB23-0C8C-4D57-8A9E-B81882FE9436} */
    private void update(DCDAttribute attribute, boolean enable)
    {
        if (enable)
        {
            attributes.put(attribute.getName(), Boolean.TRUE);
        }
        else
        {
            attributes.remove(attribute);
        }
    }

	/** @modelguid {69D79BEA-CFEF-4B11-B645-BEAE5D51352B} */
    public String[] toArray()
    {
        String[] array = new String[attributes.size()];

        Enumeration keys = attributes.keys();
        String key = null;
        int index = 0;

        while (keys.hasMoreElements())
        {
            key = (String)keys.nextElement();
            array[index] = key;
            index += 1;
        }

        return array;
    }

	/** @modelguid {61BE7E4E-52C6-4C40-A464-DF3A8250BAFE} */
    public String toString()
    {
        java.util.Set set = attributes.keySet();

        return set.toArray().toString();
    }

}
