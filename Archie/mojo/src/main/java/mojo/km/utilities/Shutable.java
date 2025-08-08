package mojo.km.utilities;

/**
 * Interface that provides a means of shutting down an object to free resources,
 * stop threads, disconnect from data feeds, etc.  One "t" or two in "shutable"?
 * I don't know.  If I guessed wrong, sue me.
 * @modelguid {2BF7BFF2-64EB-434A-BFDF-761549A1A2C9}
 */
public interface Shutable
{
    /**
     * Shutdown the object.
     * @modelguid {42A9A6D2-530E-4149-B2BD-9AA141599089}
     */
    public void shutdown();
}