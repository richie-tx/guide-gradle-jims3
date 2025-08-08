package mojo.struts.taglib.layout.util;

import com.fgm.web.menu.MenuComponent;
/**
 * interface that should implement a menu or menuItem tag so that menuItems cab e added
 * @author: JeanNoël Ribette
 */
public interface MenuInterface {
	public void addItem(MenuComponent item);
}
