package pd.supervision.managetask.adhocs;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.task.Task;


public class FixTaskAdhoc {

	public FixTaskAdhoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixTaskAdhoc fixit = new FixTaskAdhoc();
		
		Task aTask = Task.find("260011");
		System.out.println("BEFORE=" + aTask.getTaskStateXml());
		if (aTask != null){
		    IHome home = new Home();
		    home.bind(aTask);
			System.out.println("AFTER=" + aTask.getTaskStateXml());
		}
		//Fix update user when finished
		//update jims2.nttask set updateuser='JUSPT' where task_id=260009
	}

}
