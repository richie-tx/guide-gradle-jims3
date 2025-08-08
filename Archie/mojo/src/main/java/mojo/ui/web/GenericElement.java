package mojo.ui.web;

import java.util.*;
import mojo.ui.exception.*;
import mojo.ui.*;
/**
* @author Egan Royal
* @modelguid {8E5F1F8F-4D4F-4D49-96BA-F784258F0C42}
*/
public abstract class GenericElement implements IComponent
{
	/** @modelguid {A3F9A0CF-6896-41A8-BE3D-860D06DBBD69} */
	public static final String  CLOSE_COMMENT   = "// -->"; //	
	/** @modelguid {1317E481-0169-4E46-836D-BF91C43AFD83} */
	public static final String  EMPTY_STRING    = "";			  //make sure empty string is only created once  
	/** @modelguid {CAD7FE40-D6F3-44A6-BC9C-B8FEEF62FFEB} */
	public static final char    EOL             = '\n';		  //  
	/** @modelguid {384B8DEA-B480-4BC2-902E-297CA106FCF2} */
	public static final char    LT_ANGLE        = '<';      //  
	/** @modelguid {EA9A4834-D193-4611-A510-89820625B681} */
	public static final String  OPEN_COMMENT    = "<!--";   //  
	/** @modelguid {DF6C1589-B126-4CA2-923E-E3E0C517501D} */
	public static final char    PERCENT         = '%';      //  
	/** @modelguid {98B7468B-24C5-4A03-A583-A94A6240581A} */
	public static final char    RT_ANGLE        = '>';      //  
	/** @modelguid {CC14502D-DCE6-415E-8307-538638F36E26} */
	public static final char    SLASH           = '/';      //  
	/** @modelguid {902EF472-35D5-428C-8488-4D6E12ED89BC} */
	public static final String  TAB             = "  ";     //
	/** @modelguid {98D1C081-C4ED-4EFF-B36D-7CB06E61085E} */
  protected String            _elementType    = null;                 // element type/name
	/** @modelguid {F67B7478-4389-4B88-A59A-8C63EF3B32F6} */
  protected boolean           _hasElements    = true;                 // default true
	/** @modelguid {5CB006B6-478E-480A-BB28-8CEA5C55CC4D} */
  private List                _params         = new ArrayList();  // list of parameters
	/** @modelguid {2CAFDB06-83FE-4AB9-8233-666A6AC8012B} */
  protected ListMap             _elementList    = new HashVector(); // list of elements
	/** @modelguid {2E215485-B741-401E-9EBA-CAE9FA4947C2} */
  protected char              _quoteType      = '\"';                 // default quotation char
	/** @modelguid {AC9C6120-9E20-4DCE-A51A-D59EBB7F1409} */
  protected BrowserKind       _bkind          = BrowserKind.ALL;      // default ALL 
	/** @modelguid {EE53DE1E-13EC-4373-BDDF-C7FF940EC2A5} */
  protected boolean           _hasStringValue = false;                // default false
	/** @modelguid {08976B2C-22A5-40F4-B44A-3711788FA35F} */
  protected StringBuffer      _stringvalue    = new StringBuffer(EMPTY_STRING); // default empty-string
	/** @modelguid {6BD147A5-79DB-4467-B124-CD713FE36920} */
  protected boolean           _isClosable     = true;                 // default true
  
	/** @modelguid {9C9592EB-99F4-48C6-BE43-18E978ABDCA3} */
  protected GenericElement    _parent         = null;
	/** @modelguid {8CE55B60-91EA-4F0B-9FD4-F44C19686904} */
  protected GenericElement    _doc            = null;
	
	/** @modelguid {B7985B35-5287-47B4-A74A-B45070FF7F71} */
  private static final String EXC_ELEMENT  = "This element cannot contain elements";

//----------------------------- Public Constructors -------------------------
/** Basic default constructor 
 * @modelguid {7A8DECC4-D4F0-48A2-850C-718BE37247AD}
 */
protected GenericElement()
{
}

//-------------------------------- Mutator Methods ---------------------------    

/** 
 * Sets a single parameter given the parameter's name and value 
 * param value may be "", e.g. NORESIZE
 * @modelguid {FD180C2B-8E94-407F-8702-ABD9BBC35DAD}
 */
protected void setParam(String paramName, String paramVal)
{
  boolean found = false;
  for (int i = 0; i < _params.size(); i++)
  {
	if (((ParamNode) _params.get(i)).name.equals(paramName))
	{
	  ((ParamNode) _params.get(i)).value = paramVal;
	  found = true;
	  break;
	} //end if
  } //end for
  if (!found)
	_params.add(new ParamNode(paramName, paramVal));
}

/** Removes the parammeter given the parameter name 
 * @modelguid {0BE0DFA1-18A7-4CA7-AC90-1F65B513F153}
 */
protected void removeParam(String paramName)
{
  for (int i = _params.size() - 1; i >= 0; --i)
	if (((ParamNode) _params.get(i)).name.equals(paramName))
	  _params.remove(i);
}

/** Returns parameter value given parameter name 
 * @modelguid {90636697-D118-4690-AE8D-099402105D4F}
 */
protected String getParam(String paramName)
{
  for (int i = 0; i < _params.size(); i++)
	if (((ParamNode) _params.get(i)).name.equals(paramName))
	  return ((ParamNode) _params.get(i)).value;
  return null;
} // end getParam()    
  
	/** @modelguid {9D26910D-A8D2-4E11-89F1-0AF69C32D55C} */
  private class ParamNode
  {
		/** @modelguid {93A07DD9-4504-4DCC-83D1-2FF91FDDFF84} */
		String name;
		/** @modelguid {B77E972C-5C13-497C-986B-BA1806161145} */
		String value;
		
		/** @modelguid {13FB4EF4-8B1A-4126-AA2E-0AFD00D44FA9} */
		public ParamNode(String n, String v)
		{ name = n; value = v; }
  }//end nested class

/** 
   * Sets a single parameter given the parameter's name and value 
   * param value may be "", e.g. NORESIZE
   * @modelguid {CD6BA56F-DB76-4FF2-B3D5-F08A1DF62A1B}
   */
protected void appendParam(String paramName, String paramVal)
{
  boolean found = false;
  for (int i = 0; i < _params.size(); i++)
  {
	if (((ParamNode) _params.get(i)).name.equals(paramName))
	{
	  ((ParamNode) _params.get(i)).value += paramVal;
	  found = true;
	  break;
	} //end if
  } //end for
  if (!found)
	_params.add(new ParamNode(paramName, paramVal));
}

	/** @modelguid {BBF2F692-2E4E-4C28-951F-EE6E01A4F72D} */
public void init()
{
  ;
}

/** Adds multiple GenericElements to _elementVector 
 * @modelguid {337A3C0D-010C-4601-8F33-CF0375D12E75}
 */
protected void addElements(ArrayList newElementsList) throws UIException {
  if (_hasElements)
  {
	// iterates through new element list, adds to _elementList
	Iterator itr = newElementsList.iterator();
	while (itr.hasNext())
	  addElement((GenericElement) itr.next());
  } //end if
  else
	throw new UIException(EXC_ELEMENT);
}

//------------------------------- Accessor Methods --------------------------- 

/**
 * Returns the HTML tags for this element, including start tag with parameters, 
 * contained elements, and end tag.
 * @modelguid {2C041E63-700C-4DB0-8850-C86F8B8B76CE}
 */
public String toString()
{
  return toString(EMPTY_STRING);
} //     

	/** @modelguid {5D8FCB64-0A89-41F6-AFAE-EA6431B9648D} */
protected String toString(String indent)
{
  StringBuffer sbuff = new StringBuffer();
  sbuff.append(EOL + indent + this.getStartTag());

  if (_hasStringValue)
	sbuff.append(this.getStringValue());

  if (_hasElements)
	sbuff.append(this.getElements(indent));

  if (_isClosable)
	sbuff.append(EOL + indent + this.getEndTag());
  return sbuff.toString();
}

	/** @modelguid {E4DABAE1-F9A9-45DE-A083-A200A435D6D7} */
protected String format(String str)
{
  return str;
}

/** Returns entire HTML start tag 
 * @modelguid {D193913C-C4BE-4EF0-A561-A1C4E1E5A144}
 */
public String getStartTag()
{
  StringBuffer sbuff = new StringBuffer();
  sbuff.append(LT_ANGLE);
  sbuff.append(_elementType);
  sbuff.append(this.getParams()); // inserts tag params
  if (!_isClosable) //well-formed 
	sbuff.append(SLASH);
  sbuff.append(RT_ANGLE); // closes tag
  return sbuff.toString();
} // end getStartTag()    

/** Returns entire HTML end tag 
 * @modelguid {EEAA3E51-E826-4345-9E1E-C14B7911A256}
 */
public String getEndTag()
{
  StringBuffer sbuff = new StringBuffer();
  sbuff.append(LT_ANGLE);
  sbuff.append(SLASH);
  sbuff.append(_elementType);
  sbuff.append(RT_ANGLE);
  return sbuff.toString();
} // end getEndTag()    

/** Returns HTML for all params 
 * @modelguid {58AD3D8F-A0C8-4818-A070-8E6DFBA283CB}
 */
public String getParams()
{
  StringBuffer sbuff = new StringBuffer();
  // iterate through params
  for (int i = 0; i < _params.size(); i++)
  {
	String paramName = ((ParamNode) _params.get(i)).name;
	sbuff.append(' ');
	sbuff.append(paramName);
	String paramValue = ((ParamNode) _params.get(i)).value;
	if (paramValue != null && paramValue.length() != 0)
	{
	  sbuff.append('=');
	  sbuff.append(_quoteType);
	  sbuff.append(paramValue);
	  sbuff.append(_quoteType);
	} //end if
  } //end while
  return sbuff.toString();
} //end getParams()     

/** Returns HTML for all elements.  note: recursive call to toString() 
 * @modelguid {F10167E4-2221-433E-B4F6-10E2F830A480}
 */
public String getElements()
{
  return getElements(EMPTY_STRING);
} // end getElements    

	/** @modelguid {EA0B2729-E211-4DE6-95E5-DA7D44DCC2C6} */
protected String getElements(String indent)
{
  StringBuffer sbuff = new StringBuffer();
  // iterate through element list
  Iterator itr = _elementList.iterator();
  while (itr.hasNext())
  {
	sbuff.append(((GenericElement) itr.next()).toString(indent + TAB));
  }
  return sbuff.toString();
} //end getElements

	/** @modelguid {43085E50-4417-4B3E-AB1C-8433EB49749E} */
public void clearElements()
{
  _elementList.clear();
}

	/** @modelguid {0F97B6A4-F9F0-4677-BE41-613D362C19A8} */
public void clearParameters()
{
  _params.clear();
}

/**
  *
  * @modelguid {CDACA1A4-1FE6-4BC4-B9B2-77E83C5EB530}
  */
public BrowserKind getBrowserKind()
{
  return _bkind;
}

/**
  *
  * @modelguid {648BE51E-4199-4399-AEE0-6AAC1F397D3E}
  */
public String getStringValue()
{
  return _stringvalue.toString();
}

/** Adds a single GenericElement to _elementVector 
 * @modelguid {2B40E5D9-F17A-47F0-BF25-F168021070A1}
 */
protected void addElement(GenericElement ge)
{
  ge.setDocObject(getDocObject());
  ge.setParentObject(this);
  ge.setBrowserKind(getBrowserKind());
  ge.init();
  _elementList.add(ge);
}

	/** @modelguid {0D73C3D2-B024-4FA5-AD9B-AA47B03129D0} */
public GenericElement getDocObject()
{
  return _doc;
}

	/** @modelguid {122D77A9-D6DD-4E55-8CA2-D36000D2854E} */
public GenericElement getParentObject()
{
  return _parent;
}

/**
  *
  * @modelguid {BAD81550-C2E9-42ED-A3CC-79546BA4346A}
  */
public GenericElement setBrowserKind(BrowserKind bk)
{
  _bkind = bk;
  return this;
}

	/** @modelguid {87763810-3429-440A-A557-77C9E068EEFF} */
public void setDocObject(GenericElement obj)
{
  _doc = obj;
}

	/** @modelguid {5FD69987-26F0-466F-B31E-3D2215244CCE} */
public void setParentObject(GenericElement obj)
{
  _parent = obj;
}

/** Adds a single GenericElement to _elementVector 
 * @modelguid {73403F3A-E22C-4B39-ABE4-9D0A49103307}
 */
protected GenericElement addElement(Class cls)
{
	GenericElement lGE = null;
	try {
		lGE = (GenericElement)cls.newInstance();
	} catch (Exception exp) {}
  addElement(lGE);
  return lGE;
}
	/** @modelguid {BE62F46E-4462-44C3-86DC-25FD42BBF946} */
 	 	 	private String 		          name					  = null;

/**
 * Insert the method's description here.
 * Creation date: (11/21/00 1:39:16 PM)
 * @return java.lang.String
 * @modelguid {03604244-8183-4ACE-B478-29109A66871F}
 */
public String getName() {
	return name;
}

/**
 * Insert the method's description here.
 * Creation date: (11/21/00 1:49:25 PM)
 * @return boolean
 * @modelguid {5C6000DA-24B1-4E54-938A-70F1EA3B8293}
 */
public Object removeElement(GenericElement aGenericElement) {
	return _elementList.remove(aGenericElement);
}

/**
 * Insert the method's description here.
 * Creation date: (11/22/00 11:32:26 AM)
 * @param aName java.lang.String
 * @modelguid {D569BF69-87C5-4336-B75A-D7AC9656F97B}
 */
public void setElementName(String aName) {
	name = aName;
}  
}