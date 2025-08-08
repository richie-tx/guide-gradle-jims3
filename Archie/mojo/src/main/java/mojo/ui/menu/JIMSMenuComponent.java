/*
 * Created on Aug 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.ui.menu;

import com.fgm.web.menu.MenuComponent;

/**
 * Extends the com.fgm.web.menu.MenuComponent to add a few values
 * that are needed for JIMS.  
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JIMSMenuComponent extends MenuComponent
{
	private String cssClass = null;
	private String componentId;
	
	/**
	 * Sets the css class for a menu component
	 * that is realized into a <td>
	 * @param css
	 */
	public void setCssClass(String css)
	{
		cssClass = css;
	}
	
	/**
	 * Returns the css class for this menu component.
	 * Is realized into a <td class=..> in the jsp
	 * @return String
	 */
	public String getCssClass() {
		return cssClass;
	}
	
	/**
	 * Sets an id for a menu component to give it a distinct
	 * id if needed in the jsp.
	 * @param id
	 */
	public void setComponentId(String id)
	{
		componentId = id;
	}
	
	/**
	 * Returns an id for this menu component.
	 * @return String
	 */
	public String getComponentId() {
		return componentId;
	}
}
