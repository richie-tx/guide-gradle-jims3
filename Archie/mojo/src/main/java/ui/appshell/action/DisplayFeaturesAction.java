package ui.appshell.action;

import java.util.Iterator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mojo.ui.menu.JIMSMenuComponent;
import ui.appshell.nav.INavProperties;
import ui.appshell.nav.NavItem;
import ui.appshell.nav.UILeftNavProperties;

import mojo.struts.AbstractAction;

import com.fgm.web.menu.MenuRepository;

/**
 * @author dapte
 * 
 * Handles the creation/display/expansion of the features tree. Also handles the launching of the appropriate application when a
 * node on the feature tree is clicked.
 */
public class DisplayFeaturesAction extends AbstractAction
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.struts.AbstractAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Builds the menu tree based off the users features that are granted to them.
     * 
     * @param session
     * @throws Exception
     */
    private void buildMenuTree(HttpSession session) throws Exception
    {
        Map menu = new Hashtable();

        // If the tree alreadty exists, do not populate it again
        // get the user information from the session

        MenuRepository repository = null;
        if (null != session.getAttribute(MenuRepository.MENU_REPOSITORY_KEY))
        {
            repository = (MenuRepository) session.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
        }
        else
        {
            repository = new MenuRepository();
            session.setAttribute(MenuRepository.MENU_REPOSITORY_KEY, repository);
        }
        // If the menu repository is null, create it

        if (null == repository.getMenu("features"))
        {
            INavProperties leftNavProps = UILeftNavProperties.getInstance();

            JIMSMenuComponent mainMenu = new JIMSMenuComponent();
            mainMenu.setName("features");
            mainMenu.setTitle("JIMS2");
            menu.put("root", mainMenu);                        
            
            List items = leftNavProps.getNavItems2();
            int count = 0;
            int len = items.size();
            for(int i=0;i<len;i++)
            {
                NavItem item = (NavItem) items.get(i);
                JIMSMenuComponent mc = this.createMenuComponent(item, mainMenu, count);
                mainMenu.addMenuComponent(mc);
            }
            repository.addMenu(mainMenu);
        }
    }

    /**
     * Creates the menu components.
     * 
     * @param navItem
     * @param menu
     * @return MenuComponent with tree populated
     */
    private JIMSMenuComponent createMenuComponent(NavItem navItem, JIMSMenuComponent menu, int count)
    {
        JIMSMenuComponent mc = new JIMSMenuComponent();

        mc.setTitle(navItem.getName());
        count++;
        mc.setComponentId(String.valueOf(count + navItem.getName() + navItem.getUrl()));
        boolean isUrl = false;
        if (navItem.getUrl() != null)
        {
            isUrl = true;
            mc.setTarget("content");
            mc.setCssClass("menuItem");
            mc.setStyle("\"" + " class=\"menuItem\" id=\"" + mc.getComponentId() + "\"");
            mc.setLocation(navItem.getUrl());
        }
        if (navItem.hasChildren())
        {
            if (isUrl)
            {
                mc.setStyle("\"" + " class=\"menuItem\" id=\"" + mc.getComponentId() + "\"");
            }
            else
            {
                mc.setStyle("\"" + " class=\"headerMenuItem");
            }

            count++;
            Iterator i = navItem.getChildren().iterator();
            while (i.hasNext())
            {
                NavItem item = (NavItem) i.next();
                JIMSMenuComponent childMenu = createMenuComponent(item, mc, count);
                mc.addMenuComponent(childMenu);
            }
        }

        return mc;
    }
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        buildMenuTree(request.getSession());
        return mapping.findForward("displayNavigationBody");
    }
}
