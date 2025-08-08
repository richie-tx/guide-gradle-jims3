package mojo.km.utilities;

import java.util.LinkedList;

/** Queue a series of Runnable objects to run synchronously in an independent thread. 
 * @modelguid {ACC4D495-43EB-4615-96B4-473FC50AE166}
 */
public class WorkQueue extends Thread {
    /** List of pending work 
     * @modelguid {AEABFBFA-70BA-4DD1-9B01-7504E1DA5CE2}
     */
    private final LinkedList mWork;

    /** Constructor 
     * @modelguid {59908EB4-7D48-491F-9927-BFC71391BF22}
     */
    public WorkQueue(String name) {
        super("WorkQueue-" + name);
        mWork = new LinkedList();
        setDaemon(true);
        start();
    }

    /** Submit a new request 
     * @modelguid {62E90319-853C-4972-BCDE-7BC76612D51F}
     */
    public synchronized void submitWork(Runnable work) {
        mWork.add(work);
        notifyAll();
    }

    /** Submit a new request 
     * @modelguid {E42B815D-1621-4C3A-A47F-DCD5360EA73B}
     */
    public synchronized void pushWork(Runnable work) {
        mWork.addFirst( work );
        notifyAll();
    }

    /** Our work loop 
     * @modelguid {4E8D60E4-480F-4A66-BD4E-1D4E22A6D958}
     */
    public void run() {
        if (Thread.currentThread() != this) {
            throw new RuntimeException("run() may not be called explicitly");
        }
        for ( ; ; ) {
            Runnable work = null;
            synchronized(this) {
                if (mWork.size() > 0) {
                    work = (Runnable)mWork.removeFirst();
                }
                else {
                    try { wait(); }
                    catch (InterruptedException e) { }
                }
            }
            if (work != null) {
                try {
                    work.run();
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}
