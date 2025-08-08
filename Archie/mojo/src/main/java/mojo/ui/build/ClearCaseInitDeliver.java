/*
 * Created on Aug 31, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.ui.build;

import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import clearcase.ClearTool;
import clearcase.*;
import java.io.*;
import javax.swing.*;

import com.ibm.bridge2java.ComException;

import java.util.StringTokenizer;
import java.util.*;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ClearCaseInitDeliver extends JFrame
{

	private JPanel jContentPane = null;

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton continueButton = null;
	private JButton cancelButton = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JLabel jLabel = null;
	private JScrollPane jScrollPane = null;
	private JList deliverableList = null;
	private JLabel jLabel1 = null;
	private JScrollPane jScrollPane1 = null;
	private JList selectedList = null;
	private JButton addButton = null;
	private JButton addAllButton = null;
	private JButton removeAllButton = null;

	private ClearTool cTool = null;
	private clearcase.Application cApp = null;

	private Hashtable deliverables = new Hashtable();
	private Hashtable selected = new Hashtable();
	private Thread depThread = null;
	private Thread sThread = null;

	static private String devViewPath = null;
	static private String intViewPath = null;
	private String intViewTag = null;
	/**
	 * 
	 */
	public ClearCaseInitDeliver()
	{
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 */
	public ClearCaseInitDeliver(GraphicsConfiguration arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 */
	public ClearCaseInitDeliver(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ClearCaseInitDeliver(String arg0, GraphicsConfiguration arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.err.println("ClearCaseDeliverInit:  <dev view path> <integration view path>");
		}
		else
		{
			devViewPath = args[0];
			intViewPath = args[1];
			ClearCaseInitDeliver init = new ClearCaseInitDeliver();

		}
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(465, 425);
		this.setContentPane(getJContentPane());
		this.setTitle("Deliver");

		initializeDeliver();
	}

	private void initializeDeliver()
	{
		try
		{
			// Initialize the Java2Com Environment
			com.ibm.bridge2java.OleEnvironment.Initialize();
			cApp = new clearcase.Application();
			cTool = new ClearTool();
			String result = cTool.CmdExec("cd " + intViewPath);
			System.err.println("intViewPath= " + intViewPath);
			
			intViewTag = initIntegrationViewTag(cTool.CmdExec("lsview -cview -properties -short"));
			result = cTool.CmdExec("cd " + devViewPath);
			System.err.println("devViewPath= " + devViewPath);
			initializeDeliverableList(cTool.CmdExec("deliver -preview -s"));
			
			this.setVisible(true);
		}
		catch (Exception e)
		{
			System.err.println(
				"Delivery or Rebase may be in progress with current stream.");
			this.dispose();
			System.exit(1);
		}
	}
	
	private String initIntegrationViewTag(String result) throws Exception
	{
		StringReader sReader = new StringReader(result);
		BufferedReader lineReader = new BufferedReader(sReader);
		return lineReader.readLine();
	}
	
	private void initializeDeliverableList(String result) throws Exception
	{
		StringReader sReader = new StringReader(result);
		BufferedReader lineReader = new BufferedReader(sReader);
		deliverableList.setModel(new DefaultListModel());
		selectedList.setModel(new DefaultListModel());
		DependencyStatus stat = new DependencyStatus(this);
		stat.setVisible(true);
		for (String line = lineReader.readLine();
			line != null;
			line = lineReader.readLine())
		{
			if (line.indexOf("activity:") > -1)
			{
				Activity activity = buildActivity(line);
				deliverables.put(activity.getName(), activity);
				((DefaultListModel) deliverableList.getModel()).addElement(
					activity);
			}
		}
		stat.dispose();
	}

	private Activity buildActivity(String line)
	{
		//StringTokenizer sTok = new StringTokenizer("\t", line);
		String name = line.substring(1);
		clearcase.ICCActivity cActivity = cApp.get_Activity(name);
		Activity retVal =
			new Activity(name, cActivity.get_Owner(), cActivity.get_Headline());
		return (Activity) retVal;
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJPanel(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getJPanel1(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			jPanel = new JPanel();
			java.awt.GridBagConstraints consGridBagConstraints6 =
				new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints7 =
				new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints8 =
				new java.awt.GridBagConstraints();
			consGridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
			consGridBagConstraints6.weighty = 1.0;
			consGridBagConstraints6.weightx = 1.0;
			consGridBagConstraints7.fill = java.awt.GridBagConstraints.BOTH;
			consGridBagConstraints7.weighty = 1.0;
			consGridBagConstraints7.weightx = 0.3D;
			consGridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
			consGridBagConstraints8.weighty = 1.0;
			consGridBagConstraints8.weightx = 1.0;
			jPanel.setLayout(new java.awt.GridBagLayout());
			jPanel.add(getJPanel2(), consGridBagConstraints6);
			jPanel.add(getJPanel3(), consGridBagConstraints7);
			jPanel.add(getJPanel4(), consGridBagConstraints8);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1()
	{
		if (jPanel1 == null)
		{
			jPanel1 = new JPanel();
			jPanel1.add(getContinueButton(), null);
			jPanel1.add(getCancelButton(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes continueButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getContinueButton()
	{
		if (continueButton == null)
		{
			continueButton = new JButton();
			continueButton.setText("Continue");
			continueButton
				.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					if (((DefaultListModel) selectedList.getModel()).size()
						== 0)
					{
						System.err.println(
							"No activities selected for delivery");
						System.exit(1);
					}
					else
					{
						try
						{
							final ProgressMonitor pMon =
								new ProgressMonitor(
									ClearCaseInitDeliver.this,
									"Executing delivery",
									"",
									0,
									100 * selected.size());
							pMon.setProgress(0);
							pMon.setMillisToPopup(0);
							pMon.setMillisToDecideToPopup(2000);
							Runnable timer = new Runnable()
							{
								public void run()
								{
									try
									{
										while (true)
										{
											for (int i = 0;
												i < 100 * selected.size();
												i++)
											{
												if (pMon.isCanceled())
												{
													System.exit(1);
												}
												pMon.setProgress(i);
												Thread.sleep(1000);
											}
										}
									}
									catch (Exception t)
									{
									}
								}
							};
							new Thread(timer).start();
							Runnable execDeliver = new Runnable()
							{
								public void run()
								{
									String msg = null;
									try
									{
										String dstring =
											"deliver -force -to " + intViewTag + " -activities ";
										for (Iterator i =
											selected.values().iterator();
											i.hasNext();
											)
										{
											dstring += ""
												+ ((Activity) i.next()).getName();
											if (i.hasNext())
											{
												dstring += ",";
											}
										}
										System.err.println("dString=" + dstring);
										dstring = "cleartool " + dstring;
										Process proc = Runtime.getRuntime().exec(dstring);
										System.err.println("dString=" + dstring);
									//	msg = cTool.CmdExec(dstring);
										pMon.close();

									}
									catch (Exception t)
									{
										System.err.println(t.getMessage());
										t.printStackTrace();
										System.exit(1);
									}
									System.exit(0);
								}
							};
							new Thread(execDeliver).start();
						}
						catch (Exception ec)
						{
							System.err.println(ec.getMessage());
							System.exit(1);
						}
					}
				}
			});
		}
		return continueButton;
	}
	/**
	 * This method initializes cancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelButton()
	{
		if (cancelButton == null)
		{
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					System.err.println("Canceled delivery.");
					System.exit(1);
					// TODO Auto-generated stub actionPerformed()
				}
			});
		}
		return cancelButton;
	}
	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2()
	{
		if (jPanel2 == null)
		{
			jPanel2 = new JPanel();
			jPanel2.setLayout(new java.awt.BorderLayout());
			jPanel2.add(getJLabel(), java.awt.BorderLayout.NORTH);
			jPanel2.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jPanel2;
	}
	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3()
	{
		if (jPanel3 == null)
		{
			jPanel3 = new JPanel();
			java.awt.GridBagConstraints consGridBagConstraints12 =
				new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints13 =
				new java.awt.GridBagConstraints();
			consGridBagConstraints13.gridx = -1;
			consGridBagConstraints13.gridy = 3;
			consGridBagConstraints12.gridy = 2;
			jPanel3.setLayout(new java.awt.GridBagLayout());
			jPanel3.add(getAddButton(), new java.awt.GridBagConstraints());
			jPanel3.add(getAddAllButton(), consGridBagConstraints12);
			jPanel3.add(getRemoveAllButton(), consGridBagConstraints13);
		}
		return jPanel3;
	}
	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel4()
	{
		if (jPanel4 == null)
		{
			jPanel4 = new JPanel();
			jPanel4.setLayout(new java.awt.BorderLayout());
			jPanel4.add(getJLabel1(), java.awt.BorderLayout.NORTH);
			jPanel4.add(getJScrollPane1(), java.awt.BorderLayout.CENTER);
		}
		return jPanel4;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private JLabel getJLabel()
	{
		if (jLabel == null)
		{
			jLabel = new JLabel();
			jLabel.setText("   Deliverable Activities:");
		}
		return jLabel;
	}
	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane()
	{
		if (jScrollPane == null)
		{
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getDeliverableList());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes deliverableList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getDeliverableList()
	{
		if (deliverableList == null)
		{
			deliverableList = new JList();
		}
		return deliverableList;
	}
	/**
	 * This method initializes jLabel1
	 * 
	 * @return javax.swing.JLabel
	 */
	private JLabel getJLabel1()
	{
		if (jLabel1 == null)
		{
			jLabel1 = new JLabel();
			jLabel1.setText("   Selected Activities:");
		}
		return jLabel1;
	}
	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1()
	{
		if (jScrollPane1 == null)
		{
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getSelectedList());
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes selectedList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getSelectedList()
	{
		if (selectedList == null)
		{
			selectedList = new JList();
		}
		return selectedList;
	}
	/**
	 * This method initializes addButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddButton()
	{
		if (addButton == null)
		{
			addButton = new JButton();
			addButton.setText("Add >>");
			addButton.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					addSelected();
					// TODO Auto-generated stub actionPerformed()
				}
			});
		}
		return addButton;
	}

	private void addSelected()
	{
		final Object[] selectedA = deliverableList.getSelectedValues();
		final ProgressMonitor pMon =
			new ProgressMonitor(
				this,
				"Checking activity dependencies",
				"",
				0,
				100);
		pMon.setProgress(0);
		pMon.setMillisToPopup(0);
		pMon.setMillisToDecideToPopup(1000);
		Runnable checkDRunner = new Runnable()
		{
			public void run()
			{
				for (int i = 0; i < selectedA.length; i++)
				{
					double value =
						100.0
							* (((double) i + 1)
								/ ((double) selectedA.length + 1));
					int iVal = (int) value;
					Activity act = (Activity) selectedA[i];
					if (!selected.containsKey(act.getName()))
					{
						selected.put(act.getName(), act);
					}
					findDependencies(act);
				}
				//((DefaultListModel) selectedList.getModel()).clear();
				for (Iterator i = selected.values().iterator(); i.hasNext();)
				{
					Activity act = (Activity) i.next();
					if (!((DefaultListModel) selectedList.getModel()).contains(act)) {
						((DefaultListModel) selectedList.getModel()).addElement(act);
					}
				}
				sThread.stop();
				pMon.close();
			}
		};
		depThread = new Thread(checkDRunner);
		Runnable statusDependency = new Runnable()
		{
			public void run()
			{
				while (true)
				{
					try
					{
						for (int i = 0; i < 100; i++)
						{
							pMon.setProgress(i);
							Thread.sleep(1000);
						}
					}
					catch (Exception t)
					{
					}
				}
			}
		};
		sThread = new Thread(statusDependency);
		sThread.start();
		depThread.start();
	}

	private void findDependencies(Activity activity)
	{
		String preview = "";
		Hashtable undelivered = new Hashtable();
		Hashtable otherUndelivered = new Hashtable();
		try
		{
//			ICCActivity ccActivity = cApp.get_Activity(activity.getName());
//			ICCVersions ccVersions =
//				ccActivity.get_ChangeSet(
//					ccActivity.get_NameResolverView(),
//					false);
//			for (int k = 1; k <= ccVersions.get_Count(); k++)
//			{
//				ICCVersion version = ccVersions.get_Item(k);
//				if (isDeliverable(version))
//				{
//					String elementOID = version.get_Element().get_OID();
//					if (!undelivered.containsKey(elementOID))
//					{
//						undelivered.put(elementOID, elementOID);
//					}
//				}
//			}
//			for (Iterator i = deliverables.values().iterator(); i.hasNext();)
//			{
//				Activity other = (Activity) i.next();
//				if (!other.getName().equals(activity.getName()))
//				{
//					ICCActivity otherCCActivity =
//						cApp.get_Activity(other.getName());
//					ICCVersions otherCCVersions =
//						otherCCActivity.get_ChangeSet(
//							otherCCActivity.get_NameResolverView(),
//							false);
//					for (int k = 1; k <= otherCCVersions.get_Count(); k++)
//					{
//						ICCVersion otherVersion = otherCCVersions.get_Item(k);
//						if (isDeliverable(otherVersion))
//						{
//							if (otherUndelivered.containsKey(other)) {
//								Hashtable anOther = (Hashtable) otherUndelivered.get(other);
//								String otherOID = otherVersion.get_Element().get_OID();
//								anOther.put(otherOID, otherOID);
//								
//							} else {
//								Hashtable anOther = new Hashtable();
//								String otherOID = otherVersion.get_Element().get_OID();
//								anOther.put(otherOID, otherOID);
//								otherUndelivered.put(other, anOther);
//							}
//						}
//					}
//
//				}
//			}
//			for (Iterator un = undelivered.values().iterator(); un.hasNext();) {
//				String eleOID = (String) un.next();
//				for (Enumeration list = otherUndelivered.keys(); list.hasMoreElements();) {
//					Activity anActivity = (Activity) list.nextElement();
//					Hashtable anOther = (Hashtable) otherUndelivered.get(anActivity);
//					if (anOther.containsKey(eleOID)) {
//						selected.put(anActivity.getName(), anActivity);
//					}
//				}
//			}
//			
			Process proc = Runtime.getRuntime().exec("cleartool deliver -preview -activities " + activity.getName());
			InputStream input = proc.getErrorStream();
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(new BufferedInputStream(input)));
			for (String out = reader.readLine(); out != null; out = reader.readLine())
			{
				if (out.endsWith("must be added to activity list."))
				{
					String name = out.substring(out.indexOf("\"")+1);
					name = name.substring(0, name.indexOf("\""));
					name = "activity:" + name + "@\\j2pvob";
					for (Iterator i = deliverables.values().iterator(); i.hasNext();)
					{
						Activity anActivity = (Activity) i.next();
						if (anActivity.getName().equals(name)) {
							System.out.println("Selecting also " + anActivity);
							selected.put(anActivity.getName(), anActivity);
							
							break;
						}
					}
				}	
				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	private boolean isDeliverable(ICCVersion aVersion)
	{
		ICCVersion currentVersion = aVersion.get_Branch().get_LatestVersion();
		for (currentVersion = aVersion.get_Branch().get_LatestVersion(); !currentVersion.get_Identifier().equals(aVersion.get_Identifier()); currentVersion = currentVersion.get_Predecessor()) {
		
			ICCHyperlinks hyperlinks = currentVersion.get_Hyperlinks("Merge");
			
			if ((hyperlinks != null
				&& hyperlinks.get_Count() > 0))
			{
				return false;
			}
		}
		ICCHyperlinks hyperlinks = aVersion.get_Hyperlinks("Merge");
			
		if ((hyperlinks != null
			&& hyperlinks.get_Count() > 0))
		{
			return false;
		}
		return true;
	}
	/**
	 * This method initializes addAllButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddAllButton()
	{
		if (addAllButton == null)
		{
			addAllButton = new JButton();
			addAllButton.setText("Add All >>");
			addAllButton.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					((DefaultListModel) selectedList.getModel()).clear();
					selected.clear();
					for (Iterator i = deliverables.values().iterator();
						i.hasNext();
						)
					{
						Activity act = (Activity) i.next();
						(
							(DefaultListModel) selectedList
								.getModel())
								.addElement(
							act);
						selected.put(act.getName(), act);
					}
				}
			});
		}
		return addAllButton;
	}
	/**
	 * This method initializes removeAllButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRemoveAllButton()
	{
		if (removeAllButton == null)
		{
			removeAllButton = new JButton();
			removeAllButton.setText("<< Remove All");
			removeAllButton
				.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					((DefaultListModel) selectedList.getModel()).clear();
					selected.clear();
				}
			});
		}
		return removeAllButton;
	}

	static public class Activity
	{
		public Activity(String name, String user, String headline)
		{
			this.name = name;
			this.user = user;
			this.headline = headline;

		}

		public String getName()
		{
			return name;
		}

		public String toString()
		{
			return headline + " - (" + user + ")";
		}

		private String name;
		private String user;
		private String headline;
	}

} //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
