/*
 * Created on Jun 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.common.reply.CharacterResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;

import org.apache.struts.action.ActionForm;

/**
 * @author mchowdhury
 *
 * This form contains all the sorting collection methods
 */
public class SortedActionForm extends ActionForm
{
	private String character;
	private Collection dropDownListForSortingByCharacter;

	/**
	* Constructor for the GenericForm 
	*/
	public SortedActionForm(){
		super();
	}
	/**
	 * @param event
	 */
	public void clear(){
	   this.character = null;
	   this.dropDownListForSortingByCharacter = null;
	}

	/*
	* @return Collection dropDownList for Character sorting
	*/ 
	public Collection getDropDownListForSortingByCharacter(){
		Collection characterList = new ArrayList();
		CharacterResponseEvent event = new CharacterResponseEvent();
		event.setCharacter("-");
		event.setCharacterValue("0");
		characterList.add(event);
	
		String[] characterArray = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<characterArray.length;i++){
		   event = new CharacterResponseEvent();
		   event.setCharacter(characterArray[i]);
		   event.setCharacterValue("" + (i + 65));
		   characterList.add(event);
		}
		return characterList;
	}

	/**
	 * @return
	 */
	public String getCharacter()
	{
		return character;
	}

	/**
	 * @param string
	 */
	public void setCharacter(String string)
	{
		character = string;
	}

}
