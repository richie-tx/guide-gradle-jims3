/*
 * Created on Aug 31, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.ui.build;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DependencyStatus extends JDialog
{

	private javax.swing.JPanel jContentPane = null;  //  @jve:visual-info  decl-index=0 visual-constraint="25,52"

	private javax.swing.JProgressBar jProgressBar = null;
	
	public void setValue(int value)
	{
		jProgressBar.setValue(value);
		
	}
	
	static public void main(String[] args)
	{
		DependencyStatus stat = new DependencyStatus();
		stat.setVisible(true);
	}
	
	
	/**
	 * 
	 */
	public DependencyStatus()
	{
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 */
	public DependencyStatus(Frame arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DependencyStatus(Frame arg0, boolean arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DependencyStatus(Frame arg0, String arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public DependencyStatus(Frame arg0, String arg1, boolean arg2)
	{
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 */
	public DependencyStatus(Dialog arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DependencyStatus(Dialog arg0, boolean arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DependencyStatus(Dialog arg0, String arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public DependencyStatus(Dialog arg0, String arg1, boolean arg2)
	{
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(270, 48);
		this.setContentPane(getJContentPane());
		this.setTitle("Checking activity dependencies");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJProgressBar(), java.awt.BorderLayout.CENTER);
		}
		return (javax.swing.JPanel) jContentPane;
	}
	/**
	 * This method initializes jProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private javax.swing.JProgressBar getJProgressBar() {
		if(jProgressBar == null) {
			jProgressBar = new javax.swing.JProgressBar(0,100);
			//jProgressBar.setValue(30);
		}
		return jProgressBar;
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="45,10"
