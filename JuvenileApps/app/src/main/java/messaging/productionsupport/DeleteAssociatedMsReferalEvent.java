package messaging.productionsupport;

import ui.juvenilecase.form.ProdSupportForm;
import mojo.km.messaging.RequestEvent;

public class DeleteAssociatedMsReferalEvent extends RequestEvent
{

    private ProdSupportForm prodSupportForm;

    public ProdSupportForm getProdSupportForm()
    {
        return prodSupportForm;
    }

    public void setProdSupportForm(ProdSupportForm prodSupportForm)
    {
        this.prodSupportForm = prodSupportForm;
    }
    
    
}
