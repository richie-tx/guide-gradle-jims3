package pd.appshell.logintransactions;

import mojo.km.context.ICommand;
import mojo.km.transaction.ReadOnlyTransactional;
import mojo.km.messaging.IEvent;
import mojo.km.dispatch.EventManager;
import mojo.naming.*;
import java.util.Iterator;
import java.util.Collection;
import pd.appshell.*;
import messaging.appshell.*;

/**
 * @author Egan Royal
 * @modelguid {C7EC1DD0-89FD-4E44-A50C-BBB6577EA8A7}
 */
public class CCGetFavoritesCommand implements ICommand, ReadOnlyTransactional {

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {7C1002EB-94E5-4AA4-A907-83EA7751994B}
     */
    public void execute(IEvent event) throws Exception {
        FavoritesEvent lFavoritesEvent = new FavoritesEvent();
        for(Iterator i = getFavorites(); i.hasNext(); ) {
        	Favorites lFavorites = (Favorites)i.next();
            FavoritesFeatureListEvent lFeatures = new FavoritesFeatureListEvent();
            mojo.km.utilities.PropertyCopier.copyProperties(lFavorites,lFeatures);
            for(Iterator j = lFavorites.getFeatures(); j.hasNext(); ) {
                Feature lFeature = (Feature)j.next();
                DisplayFeatureEvent lFeatureEvent = new DisplayFeatureEvent();
                mojo.km.utilities.PropertyCopier.copyProperties(lFeature,lFeatureEvent);
                //lFeatures.insertFeature(lFeatureEvent);
            }
            lFavoritesEvent.insertFeatureList(lFeatures);
        }
        EventManager.getSharedInstance(EventManager.REPLY).postEvent(lFavoritesEvent);
    }

    /**
    * hardcoded
    * @modelguid {A8C25F15-BB28-45EB-B61A-F1D6A4BAF168}
    */
    private Iterator getFavorites() {
        Collection lFavoritesList = new java.util.Vector();
        Favorites lFavorites = new Favorites();
        lFavorites.setModified(true);
        lFavorites.setName("Plans");
        Feature lFeature = new Feature();
        lFeature.setModified(true);
        lFeature.setName("Plan Selection");
        lFavorites.insertFeature(lFeature);
        lFeature = new Feature();
        lFeature.setModified(true);
        lFeature.setName("Plan Detail");
        lFavorites.insertFeature(lFeature);
        lFavoritesList.add(lFavorites);

        lFavorites = new Favorites();
        lFavorites.setModified(true);
        lFavorites.setName("Accounts");
        lFeature = new Feature();
        lFeature.setModified(true);
        lFeature.setName("X Accounts");
        lFavorites.insertFeature(lFeature);
        lFavoritesList.add(lFavorites);

        lFavorites = new Favorites();
        lFavorites.setModified(true);
        lFavorites.setName("Ledgers");
        lFeature = new Feature();
        lFeature.setModified(true);
        lFeature.setName("X Ledgers");
        lFavorites.insertFeature(lFeature);
        lFeature = new Feature();
        lFeature.setModified(true);
        lFeature.setName("Y Ledgers");
        lFavorites.insertFeature(lFeature);
        lFavoritesList.add(lFavorites);

        return lFavoritesList.iterator();
    }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {5683C3BE-780F-4AE9-856D-F867FD9750F5}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {B1848EB0-5B88-414B-BE4C-95E2C1DE0341}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {9156EBD4-BA25-462E-9C91-6BF41B7C9E12}
     */
    public void update(Object uObject) { }
}
