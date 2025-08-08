package messaging.appshell;

import mojo.km.config.FolderProperties;
import mojo.km.messaging.RequestEvent;

/**
 * This Event class encapsulates the information used to build a Node on the Tree or a FavoritesList entry. <pre>
 * Change History:
 * Author        Date        	Explanation
 * </pre>
 */

public class NodeEvent extends RequestEvent {
    private String URL;
    private String ID;
    private String parentID;
    private String alias;
    private String confirmationMessage;
    private boolean selected;
    private int level;
    private String popupName;
    private String popupOptions;
    private String displayType;
    private boolean deactivated;
    private String type;
    private String imageName;

	public static final String FOLDER = "FOLDER";
	public static final String LEAF = "LEAF";
	
    /** Constructor */
    public NodeEvent() {
        super();
    }

	public NodeEvent(String id, String parentid, String name, String url, String type) { 
		setID(id);         
		setParentID(parentid);         
		setAlias(name);      
		setURL(url);
		setType(type);   
	} 

    public NodeEvent(String id, String parentid, String name, String type) { 
	    setID(id);         
		setParentID(parentid);         
		setAlias(name);         
		setType(type);
		if (FolderProperties.getInstance().getProperty(name) != null) 
		{        
			URL = FolderProperties.getInstance().getProperty(name);     
		}
    } 

	public NodeEvent(String id, String parentid, String name) { 
		setID(id);         
		setParentID(parentid);         
		setAlias(name);
		         
		if (FolderProperties.getInstance().getProperty(name) != null) 
		{        
			URL = FolderProperties.getInstance().getProperty(name);     
		}
	} 
	
	
	public void setType(String type) {
		this.type = type; 
		if (type==NodeEvent.FOLDER) {
			imageName = "images/tree/GenericFolder"; 
		} else { 
			imageName = "images/tree/imgItem";
		}
	
	}

    public String getURL(){ return URL; }

    public void setURL(String URL){ this.URL = URL; }

    public String getID(){ return ID; }

    public void setID(String ID){ this.ID = ID; }

    public String getParentID(){ return parentID; }

    public void setParentID(String parentID){ this.parentID = parentID; }

    public String getAlias(){ return alias; }

    public void setAlias(java.lang.String alias){ this.alias = alias; }

    public String getConfirmationMessage(){
            return confirmationMessage;
        }

    public void setConfirmationMessage(String confirmationMessage){
            this.confirmationMessage = confirmationMessage;
        }

    public boolean getSelected(){ return selected; }

    public void setSelected(boolean selected){ this.selected = selected; }

    public int getLevel(){ return level; }

    public void setLevel(int level){ this.level = level; }

    public String getPopupName(){
            return popupName;
        }

    public void setPopupName(String popupName){
            this.popupName = popupName;
        }

    public String getPopupOptions(){
            return popupOptions;
        }

    public void setPopupOptions(String popupOptions){
            this.popupOptions = popupOptions;
        }

    public String getDisplayType(){
            return displayType;
        }

    public void setDisplayType(String aDisplayType){
            this.displayType = aDisplayType;
        }

    public boolean getDeactivated(){ return deactivated; }

    public void setDeactivated(boolean deactivated){ this.deactivated = deactivated; }
	/**
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return type;
	}


//	/**
//	 * Sets the type.
//	 * @param type The type to set
//	 */
//	public void setType(String type) {
//		this.type = type;
//	}

	/**
	 * @return
	 */
	public String getImageName() {
		return imageName;
	}
	

	/**
	 * @param string
	 */
	public void setImageName(String string) {
		imageName = string;
	}

}