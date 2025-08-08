/*
 * Created on Feb 7, 2006
 *
 */
package mojo.pattern.ui;

import org.apache.struts.action.ActionForm;

import mojo.pattern.IDirector;

/**
 * @author Jim Fisher
 *
 */
public interface IFormDirector extends IDirector
{
	public ActionForm getForm();
}
