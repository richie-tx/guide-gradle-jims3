/*
 * Created on Aug 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.utilities;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Vector;
import mojo.km.utilities.WorkQueue;
import mojo.km.config.*;
import java.util.Hashtable;
import mojo.km.messaging.noop.NoReply;
import mojo.km.persistence.PersistentObject;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MapServer
{
	public static void main(String[] args) {
		try {
			new MapServer().startConnectionManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Initialize all of the threads that will handle client and server connections.
	 * @modelguid {47F2BF2E-C372-4FD1-BC73-92E18C72FABF}
	 */
	public void startConnectionManager() {
		ClientConnectionManager cManager = new ClientConnectionManager();
		Thread cThread = new Thread(cManager);
		cThread.start();
	}

	/** Helper class used to handle all of the client connections provided by routing server
	 * @modelguid {EC2462EE-C5D6-42CD-998A-81FBBD5843CD}
	 */
	class ClientConnectionManager implements Runnable {
		/** Implement Runnable interface. Provides event loop for accepting client connections.
		 * @modelguid {198ED021-8058-46EA-9839-AD66A729F822}
		 */
		public void run() {
			try {
				URL aURL = new URL(AppProperties.getInstance().getProperty("MapServer"));
				int aPort = aURL.getPort();
				theServerSocket = new ServerSocket(clientPort);
			} catch (Exception e) {
				return;
			}
			while (true) {
				try {
					Socket aSocket = theServerSocket.accept();
					System.out.println("ClientConnectionManager :: Accepted connection.");
					ClientConnection sConnection = new ClientConnection(aSocket);
					Thread aThread = new Thread(sConnection);
					aThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/** @modelguid {ED37BE27-2A79-4784-93E9-15516ED0A386} */
		ServerSocket theServerSocket = null;
	}
	/** Helper class that manages to and from communications with a client process.
	  * @modelguid {D4D87B6F-8323-4B7E-9C3F-2418D3103FAA}
	  */
	 class ClientConnection implements Runnable {
		 /** @modelguid {14C724DC-3694-40F8-B81C-0B9018FFC1BA} */
		 Socket theSocket = null;

		 /**
		  * Constructor.
		  * @param aSocket - that socket used to communicate with the client.
		  * @modelguid {98D76799-A1AE-4258-A1E6-EBE52A636764}
		  */
		 ClientConnection(Socket aSocket) {
			 theSocket = aSocket;
		 }

		 /**
		  * Send any server process reply back to the client.
		  * @param aEvent - the buffer housing the serialized event.
		  * @param size - size of event buffer.
		  * @throws Exception if there is an error in client communications.
		  * @modelguid {89EA4ED5-FBD7-4AAD-9828-738202B6A9D2}
		  */
		 public void sendReply(byte[] pObjList, int size) throws Exception {
			 OutputStream oStream = theSocket.getOutputStream();
			 ByteArrayOutputStream eBStream = new ByteArrayOutputStream();
			 DataOutputStream dOStream = new DataOutputStream(eBStream);
			 dOStream.writeInt(size);
			 dOStream.write(pObjList, 0, size);
			 //eBStream.write(0);
			 oStream.write(eBStream.toByteArray(), 0, eBStream.toByteArray().length);
			 //System.out.println(" Sending to client connection. " );
			 oStream.flush();
		 }

		 /** Implements Runnable interface. Provides event loop to handle the to and from communications with the client.
		  * @modelguid {47B12AA9-9AEB-4031-95DC-E9A650A2AE15}
		  */
		 public void run() {
			 try {
				 byte[] buffer = new byte[1000000];
				 while (true) {
					 InputStream iStream = theSocket.getInputStream();
					 //int noBytes = iStream.read(buffer);
					 //ByteArrayInputStream bStream = new ByteArrayInputStream(buffer);
					 DataInputStream objStream = new DataInputStream(iStream);
					 int size = objStream.readInt();
					 byte[] pObj = null;
					 String actionType = null;

					 if (size > 0) {
						pObj = new byte[size];
						objStream.readFully(pObj, 0, size);
						ByteArrayInputStream bStream = new ByteArrayInputStream(pObj);
						ObjectInputStream oStream = new ObjectInputStream(bStream);
						actionType = oStream.readUTF();
					 }
					 IOWork sWork = new IOWork( pObj, size, this, actionType );
					 WorkQueue wQueue = (WorkQueue) wQueues.get( this );
					 if (wQueue != null){
						 wQueue.submitWork( sWork );
					 }
					 else {
					 	wQueue = new WorkQueue("" + this);
					 	wQueues.put(this, wQueue);
					 	wQueue.submitWork(sWork);
					 }
				 }
			 } catch (Throwable t) {
				 try {
					 theSocket.close();
				 } catch (Exception ex) { }
				 t.printStackTrace();
				 wQueues.remove(this);
			 }
		 }


		 /** @modelguid {8B620E2A-A14B-4474-AA2A-C6FD29A38843} */
		 public void postReturnObjs(Hashtable pObjs, int clientSize) {
			 ByteArrayOutputStream objectStream = new ByteArrayOutputStream();
			 try {
				 ObjectOutputStream objOutStream = new ObjectOutputStream(objectStream);
				 objOutStream.writeInt(clientSize);
				 objOutStream.writeObject(pObjs);
				 if (pObjs.size() == 0) {
				 	byte[] buff = new byte[0];
				 	sendReply(buff, 0);
				 } else {
					 int size = objectStream.toByteArray().length;
					 byte[] buff = objectStream.toByteArray();
					 sendReply(buff, size);
				 }
			 } catch (Throwable t) {
				 t.printStackTrace();
			 }
		 }
	 }
	/** @modelguid {BEEA0755-D880-4FB3-95F8-D0B58A45B005} */
	class IOWork implements Runnable {
		/** @modelguid {ED5379A4-19A3-40ED-97A4-F7F99AC988BF} */
		byte[] thePersistentData = null;
		/** @modelguid {4A9C4897-4625-417F-AC5C-29F53985EC80} */
		int size = 0;
		/** @modelguid {EF4A3382-C4F3-4285-BD45-56FED2098E76} */
		ClientConnection cConnection = null;
		/** @modelguid {DFE15E56-E327-471E-ABA2-8A63EBEBDEAD} */
		String theActionType = null;

		/** @modelguid {5CCECD1A-C173-4291-9F3C-47D629FB7FBA} */
		public IOWork(byte[] persistentData, int size, ClientConnection cConnection, String actionType) {
			thePersistentData = persistentData;
			this.size = size;
			this.cConnection = cConnection;
			this.theActionType = actionType;
		}

		/** @modelguid {6BF34A60-F75A-4888-848F-A444A5C3A2AE} */
		public void run() {
			boolean sentFlag = false;
			if (theActionType.equals("save")) {
				writePObj();
			} else {
				getPObjs();
			}
		}

		public void writePObj() {
			PersistentObject thePObj = null;
			try {
				ByteArrayInputStream oBStream = new ByteArrayInputStream(thePersistentData);
				ObjectInputStream aOStream = new ObjectInputStream(oBStream);
				aOStream.readUTF();
				thePObj = (PersistentObject) aOStream.readObject();
				aOStream.close();
				File aFile = new File(AppProperties.getInstance().getProperty("stubDir") + "/" +
				thePObj.getClass().getName());
				if (!aFile.exists()) {
					aFile.mkdir();
				}
				FileOutputStream oFile = new FileOutputStream(AppProperties.getInstance().getProperty("stubDir") + "/" +
				thePObj.getClass().getName() + "/" + thePObj.getOID() + ".store");
				ObjectOutputStream objStream = new ObjectOutputStream(oFile);
				objStream.writeObject((Object) thePObj);
				objStream.close();

			} catch (Exception io) {
				io.printStackTrace();
				System.err.println("Unable to save PersistentType");
			}

		}

		public void getPObjs() {
			TypeTracker tTracker = null;
			ByteArrayInputStream oBStream = new ByteArrayInputStream(thePersistentData);
			int clientSize = 0;
			try {
				ObjectInputStream aOStream = new ObjectInputStream(oBStream);
				aOStream.readUTF();
				clientSize = aOStream.readInt();
			} catch (Exception io) {}
			if (!typeList.containsKey(theActionType)) {
				File pDir = new File(AppProperties.getInstance().getProperty("stubDir") + "/" +
				theActionType);
				tTracker = new TypeTracker(pDir);
				//tTracker.setLastLength(pDir.length());
				typeList.put(theActionType, tTracker);

			} else {
				tTracker = (TypeTracker) typeList.get(theActionType);
			}

			File[] files = tTracker.getDir().listFiles();
			if (files == null) {
				cConnection.postReturnObjs(tTracker.getTypeHash(), 0);
				return;
			}
			int size = 0;
			for (int j = 0; j < files.length; j++) {
				File objFile = files[j];
				size += objFile.length();
			}
			if (tTracker.getDir().isDirectory() && size != clientSize) {
				tTracker.getTypeHash().clear();
				if (files.length == 0) {
					cConnection.postReturnObjs(tTracker.getTypeHash(), 0);
					return;
				}

				for (int i = 0; i < files.length; i++) {
					try {
						FileInputStream iStream = new FileInputStream(files[i]);
						ObjectInputStream oiStream = new ObjectInputStream(iStream);
						PersistentObject pObj = (PersistentObject) oiStream.readObject();
						tTracker.getTypeHash().put("" + pObj.getOID(), pObj);
						iStream.close();
					} catch (Exception io) {
						try {
							files[i].delete();
						} catch (Exception dE) {
						
						}
					}
				}
				tTracker.setLastLength(size);
			}
			cConnection.postReturnObjs(tTracker.getTypeHash(), size);
		}


	}

	/** @modelguid {39F62BBA-C9DC-403A-9B7F-94F782804A6C} */
	private int clientPort = 5551;
	/** @modelguid {13D9D8C7-56CE-45DB-977C-9B14E018821E} */
	Hashtable wQueues = new Hashtable();

	Hashtable typeList = new Hashtable();


	class TypeTracker {
		File typeDir = null;
		Hashtable typeHash = new Hashtable();
		long lastLength = 0;

		public TypeTracker(File aDir)
		{
			typeDir = aDir;
		}

		public File getDir()
		{
			return typeDir;
		}

		public Hashtable getTypeHash()
		{
			return typeHash;
		}

		public void setLastLength(long length)
		{
			lastLength = length;
		}

		public long getLastLength()
		{
			return lastLength;
		}
	}

}
