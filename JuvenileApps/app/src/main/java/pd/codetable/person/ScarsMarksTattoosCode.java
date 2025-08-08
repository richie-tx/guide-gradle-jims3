package pd.codetable.person;

import java.util.Collection;
import java.util.Iterator;

import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CriminalCodeResponseEvent;
import messaging.codetable.reply.ICode;
import mojo.km.persistence.ArrayList;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import naming.PDCodeTableConstants;

/**
 * @author dgibler
 * 
 */
public class ScarsMarksTattoosCode extends PersistentObject implements ICode
{

    private static final String SCARSMARKS_CONTEXT = "SCARS.MARKS.TATTOOS";

    /**
     * @return pd.codetable.ScarsMarksTattoosCode
     * @param code
     * @roseuid 410FA34B0329
     */
    static public ScarsMarksTattoosCode find(String code)
    {
        IHome home = new Home();
        return (ScarsMarksTattoosCode) home.find(code, ScarsMarksTattoosCode.class);
    }
    
    static public ScarsMarksTattoosCode findScarMark(String code)
    {
        IHome home = new Home();
        Reference reference = new Reference(code, ScarsMarksTattoosCode.class, SCARSMARKS_CONTEXT);
        ScarsMarksTattoosCode scarMarkCode = (ScarsMarksTattoosCode) reference.getObject();
        return scarMarkCode;
    }

    static public Iterator findAll(GetScarsMarksTattoosCodesEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, ScarsMarksTattoosCode.class);
    }

    /**
     * @return
     */
    public static Collection findAllScarsMarks()
    {
        IHome home = new Home();
        Collection codes = new ArrayList(ScarsMarksTattoosCode.class, "category", PDCodeTableConstants.SCAR_CATEGORY);
        return codes;
    }

    /**
     * @return
     */
    public static Collection findAllTattoos()
    {
        IHome home = new Home();
        Collection codes = new ArrayList(ScarsMarksTattoosCode.class, "category", PDCodeTableConstants.TATTOO_CATEGORY);
        return codes;
    }
    
    public static Iterator findAll()
    {
        IHome home = new Home();
        return home.findAll(ScarsMarksTattoosCode.class);
    }

    private String category;
    private String code;

    private String description;

    /**
     * @roseuid 418FD7D9021D
     */
    public ScarsMarksTattoosCode()
    {
    }

    public void fill(ScarsMarksTattoosCodeResponseEvent code)
    {
        if (code != null)
        {
            code.setCategory(this.getCategory());
            code.setCode(this.getCode());
            code.setDescription(this.getDescription());
        }
    }

    /**
     * @return Returns the category.
     */
    public String getCategory()
    {
        fetch();
        return category;
    }

    /**
     * Access method for the code property.
     * 
     * @return the current value of the code property
     */
    public String getCode()
    {
        fetch();
        return code;
    }

    /**
     * Access method for the description property.
     * 
     * @return the current value of the description property
     */
    public String getDescription()
    {
        fetch();
        return description;
    }

    /**
     * @param category
     *            The category to set.
     */
    public void setCategory(String aCategory)
    {
        if (this.category == null || !this.category.equals(aCategory))
        {
            markModified();
        }
        this.category = aCategory;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param aCode
     *            the new value of the code property
     */
    public void setCode(String aCode)
    {
        if (this.code == null || !this.code.equals(aCode))
        {
            markModified();
        }
        code = aCode;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param aDescription
     *            the new value of the description property
     */
    public void setDescription(String aDescription)
    {
        if (this.description == null || !this.description.equals(aDescription))
        {
            markModified();
        }
        description = aDescription;
    }

    public CriminalCodeResponseEvent valueObject()
    {
        CriminalCodeResponseEvent event = new CriminalCodeResponseEvent();
        if (code != null)
        {
            event.setCode(this.getCode());
            event.setDescription(this.getDescription());
        }
        return event;
    }

    public CriminalCodeResponseEvent valueObject(String aTopic)
    {
        CriminalCodeResponseEvent event = this.valueObject();
        event.setTopic(aTopic);
        return event;
    }
}
