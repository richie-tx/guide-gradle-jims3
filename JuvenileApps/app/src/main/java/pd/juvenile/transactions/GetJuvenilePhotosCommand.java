package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.List;

import messaging.juvenile.GetJuvenilePhotosEvent;
import messaging.juvenile.reply.JuvenilePhotoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenilePhoto;
import pd.juvenile.JuvenileTattooPhoto;

public class GetJuvenilePhotosCommand implements ICommand
{
    /**
     * @roseuid 42B18DC4035B
     */
    public GetJuvenilePhotosCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B18307012A
     */
    public void execute(IEvent event)
    {
        GetJuvenilePhotosEvent evt = (GetJuvenilePhotosEvent) event;
        if (evt.getPhotoName() == null || evt.getPhotoName().equalsIgnoreCase(""))
            getAllThumbPhotos(event);
        else
        {
            getAPhoto(event);
        }

    }
    
    /**
     * Get Thumb pictures 
     * @param event
     */
    private void getAPhoto(IEvent event)
    {

        GetJuvenilePhotosEvent evt = (GetJuvenilePhotosEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        JuvenilePhoto photo =null;
        //task 11051 starts
        JuvenileTattooPhoto	 tattos = null;
        if(evt.isTattoo){ //task 11051
        	tattos = JuvenileTattooPhoto.find(evt.getPhotoName());
        } //task 11051 ends
        else
        {
        	photo = JuvenilePhoto.find(evt.getPhotoName());
        }
        if (photo != null)
        {
            JuvenilePhotoResponseEvent reply = createReply(photo, 0);
            dispatch.postEvent(reply);
        }
        if (tattos != null) //task 11051 starts
        {
            JuvenilePhotoResponseEvent reply = createReply(tattos, 0);
            dispatch.postEvent(reply);
        }  //task 11051 starts
    }
    
    /**
     * Get All Thumb Photos
     * @param event
     */
    private void getAllThumbPhotos(IEvent event)
    {
        GetJuvenilePhotosEvent evt = (GetJuvenilePhotosEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        int totalCount =0;
        
        //task 11051 starts
        List<JuvenileTattooPhoto> tattoos = new ArrayList<JuvenileTattooPhoto>();
        if(evt.isTattoo){
        	tattoos = JuvenileTattooPhoto.findAll(evt.getJuvenileNum());
        	totalCount = tattoos.size();

            for (int i = 0; i < totalCount; i++)
            {
            	JuvenileTattooPhoto tattoo = (JuvenileTattooPhoto) tattoos.get(i);
                JuvenilePhotoResponseEvent reply = createReply(tattoo, totalCount);
                dispatch.postEvent(reply);
            }
        } //task 11051 ends
        else
        {
        	 List<JuvenilePhoto> photos = JuvenilePhoto.findAll(evt.getJuvenileNum());
             totalCount = photos.size();
             for (int i = 0; i < totalCount; i++)
             {
                 JuvenilePhoto photo = (JuvenilePhoto) photos.get(i);
                 JuvenilePhotoResponseEvent reply = createReply(photo, totalCount);
                 dispatch.postEvent(reply);
             }
        }

    }
    
    /**
     * task 11051
     * Create Reply fpr photos
     * @param aPhoto
     * @param aTotalPhotos
     * @return JuvenilePhotoResponseEvent
     */
    private JuvenilePhotoResponseEvent createReply(JuvenilePhoto aPhoto, int aTotalPhotos)
    {

        JuvenilePhotoResponseEvent reply = new JuvenilePhotoResponseEvent();

        reply.setPhoto(aPhoto.getPicture());
        if (aPhoto.getOID() != null)
        {
            reply.setPhotoName(aPhoto.getOID());

        }
        reply.setThumbNail(aPhoto.getThumbPic());
        reply.setJuvenileNum(aPhoto.getJuvenileId());
        reply.setEntryDate(aPhoto.getEntryDate());
        reply.setTotalPhotoCount(aTotalPhotos);
        return reply;
    }
    
    /**
	 * ER-11051 GANG TATTOO
     * create Reply for tattoos    
     * OverLoaded method for tattoo
     * @param aPhoto
     * @param aTotalPhotos
     * @return
     */
    private JuvenilePhotoResponseEvent createReply(JuvenileTattooPhoto aPhoto, int aTotalPhotos)
    {

        JuvenilePhotoResponseEvent reply = new JuvenilePhotoResponseEvent();

        reply.setPhoto(aPhoto.getPicture());
        if (aPhoto.getOID() != null)
        {
            reply.setPhotoName(aPhoto.getOID());

        }
        reply.setThumbNail(aPhoto.getThumbPic());
        reply.setJuvenileNum(aPhoto.getJuvenileId());
        reply.setEntryDate(aPhoto.getEntryDate());
        reply.setTotalPhotoCount(aTotalPhotos);
        return reply;
    }

    /**
     * @param event
     * @roseuid 42B18307012C
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42B183070138
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42B18307013A
     */
    public void update(Object anObject)
    {

    }

}
