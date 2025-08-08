<!DOCTYPE HTML>

<%-- MODIFICATIONS --%>
<%-- 06 nov 2007 - mjt - create JSP --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="messaging.codetable.reply.CodetableAttributeResponseEvent" %>
<%@ page import="messaging.codetable.reply.CodetableRegistrationAttributesAndTypesResponseEvent" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="naming.Features" %>

<%-- LOCALE USED FOR INTERNATIONALIZATION - NOT USED YET --%>

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonfunctionality.css" />
<html:base />

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationSequenceAttributes.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/nodomws.js"></script>
<bean:size id="attrSize" name="codetableRegistrationForm" property="codetableAttributes"/>

<script LANGUAGE="JavaScript" TYPE="text/javascript">
var prevNode;

function moveUp(node) {
	var newNode = node.cloneNode("true");
	newNode.setAttribute("position", (newNode.getAttribute("position") - 1));

	var prevRow = node_before(node);
	var prevRowNode = prevRow.cloneNode("true");
	prevRowNode.setAttribute("position", (parseInt(prevRowNode.getAttribute("position")) + 1.0));
	var tableNode = node.parentNode;

	var reattachee = tableNode.replaceChild( newNode, prevRow);
	var reattachee = tableNode.replaceChild(prevRowNode, node);

	rebuild();

	newNode.className="selectedRowGreen"
}

function moveDown(node) {
	var newNode = node.cloneNode("true");
	newNode.setAttribute("position", (parseInt(newNode.getAttribute("position")) + 1.0));

	var nextRow = node_after(node);

	var nextRowNode = nextRow.cloneNode("true");
	nextRowNode.setAttribute("position", (nextRowNode.getAttribute("position") - 1));
	var tableNode = node.parentNode;

	var reattachee = tableNode.replaceChild( newNode, nextRow);

	var reattachee = tableNode.replaceChild(nextRowNode, node);

	rebuild();

	newNode.className="selectedRowGreen"
}

function rebuild() {

	//total number of things to be sequenced
	var numtasks = <%=attrSize%>;

	var count =0;
	var movedown = "<span onclick='moveDown(this.parentNode.parentNode);' onmouseover=\"this.style.cursor= 'pointer'\"><img src=\"/<msp:webapp/>images/arrow_down_large.gif\" border=0></span>";
	var moveup = "<span onclick='moveUp(this.parentNode.parentNode)' onmouseover=\"this.style.cursor= 'pointer'\"><img src=\"/<msp:webapp/>images/arrow_up_large.gif\" border=0></span>";
	var thenode;
	var prevbutton;
	var tasks = document.getElementsByTagName("TR");
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);
		
		if(thenode.getAttribute("position")) {

			//alternate row colors here
			//if (i%2 == 1){
				thenode.className = "normalRow"
			//}else thenode.className = "alternateRow"

		count++;
		
		downbutton = node_after(first_child(thenode));
		
		upbutton = node_after(downbutton);
		
		if(count == 1) {
			upbutton.innerHTML="";
		} else {
			node_before(downbutton).innerHTML = count;
			upbutton.innerHTML = moveup;
		}
		if(count  == numtasks) {
			downbutton.innerHTML="";
		} else {
			node_before(downbutton).innerHTML = count;
			downbutton.innerHTML = movedown;
		}
	}
	}
	//updateMasterList();
}

function updateMasterList() {
	var taskidlist= new Array(1);
	var tasks = document.getElementsByTagName("TR");
	var count = 0;
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);
		if(thenode.getAttribute("taskid")) {
		taskidlist[count] =  thenode.getAttribute("taskid");
		count++;
		}
	}
	var reorderedListVal=taskidlist.join(",");
	var reorderedElem=document.getElementsByName("resequencedOrderValue")[0];
	reorderedElem.value=reorderedListVal;
	//alert(reorderedElem.value);
	return true;
}

function hoverClassFlip(theRow, action){
	var tasks = document.getElementsByTagName("TR");
	for(var i=0; i<tasks.length;i++) {
		thenode = tasks.item(i);

		if(thenode.getAttribute("position")){

			if (thenode == theRow){
				flipClass(theRow, action);
			}
		}
	}

}

function flipClass(thenode, action){
	if (action == "on" && thenode.className != "selectedRowGreen"){
			thenode.className = "normalRowHover"
	}else if (action == "out" && thenode.className != "selectedRowGreen"){
			thenode.className = "normalRow"
	}
}

function changeFormSettings(theForm, theTargetString, button, theActionString)
{
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);
	changeFormTarget(theForm,theTargetString) ;
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	disableSubmit(button, theForm);
}
 </SCRIPT>
</head>

<body topmargin=0 leftmargin='0'>
<html:form action="/submitCodetableRegistrationAttributeUpdate" target="content"> 


<%-- BEGIN HEADING TABLE --%>
<%-- title string is built, depending on the 'mode', something like: --%>
<%-- "Manage Code Table Registration - Update Code Table Registration Summary" --%>
<table width='100%'> 
  <tr id='detailsHeading' class='hidden'> 
    <td align="center" class="header">Manage <bean:message key="prompt.codeTableRegistration" /> - 
			<logic:equal name="opStatus" value="create">
				Create
			</logic:equal>
			<logic:equal name="opStatus" value="update">
				Update
			</logic:equal>
      <bean:message key="prompt.codeTableRegistration" />
			<logic:equal name="opStatus" value="summary">
 				Summary
			</logic:equal>
  	</td> 
  </tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<br> 
<table width="98%" border="0"> 
  <tr> 
    <td> 
      <ul> 
        <li>Select Up and Down arrows to move row data up or down as required.</li> 
        <li>Review the information and select the Finish button to save the information.</li> 
        <li>Select the Back button to return to the previous screen to change information.</li> 
        <li>Select the Cancel button to return to the Code Table Registration Search.</li> 
      </ul> 
		</td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN DETAIL TABLE --%> 
<div class='spacer'></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
  <tr> 
    <td valign=top> 

			<div class='spacer'></div>
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
        <tr> 
          <td class=detailHead><bean:message key="prompt.codeTableRegistration" /></td> 
        </tr> 
        <tr> 
          <td valign=top align=center> 
					   <table cellpadding="4" cellspacing="1" width='100%'>

						  <%-- the attributes that follow are common to all code table types --%> 
              <tr>
                <td class=formDeLabel nowrap><bean:message key="prompt.name" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableName" /></td>
      			  </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.description" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableDescription" /></td>
              </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.type" /></td>
                <td class=formDe><logic:equal name="codetableRegistrationForm" property="codetableType" value="SL">
            		Simple&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableRegistrationForm" property="codetableType" value="CD">
            		Compound&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableRegistrationForm" property="codetableType" value="CX">
            		Complex&nbsp;
            	</logic:equal></td>
      			  </tr>
						  <%-- end common attributes  --%> 
		
						  <%-- attribute specific to simple type --%> 
        			<logic:equal name="codetableRegistrationForm" property="codetableType" value="SL">
                <tr id='contextRow'>
                  <td class=formDeLabel nowrap><bean:message key="prompt.context" /> <bean:message key="prompt.key" /></td>
                  <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableContextKey" /></td>
                </tr>
        			</logic:equal>
              <%--  end simple type --%> 

						  <%-- common attribute --%> 
              <tr>
                <td class=formDeLabel nowrap ><bean:message key="prompt.entity" /> <bean:message key="prompt.name" /></td>
                <td class=formDe><bean:write name="codetableRegistrationForm" property="codetableEntityName"/></td>
              </tr>

            </table>
  				</td> 
        </tr> 
      </table> 

			<div class='spacer'></div>
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
        <tr> 
          <td class=detailHead>Resequence <bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.attributes" /></td> 
        </tr> 
        <tr> 
          <td valign=top align=center> 
					  <table cellpadding="0" cellspacing="1" width='100%' class=sequenceTable rules="rows" > 
              <tr bgcolor='#cccccc' class=subHead >
                <td></td>
                <td></td>
                <td></td>
                <td><bean:message key="prompt.attribute" /> <bean:message key="prompt.name" /></td>
                <td><bean:message key="prompt.display" /> <bean:message key="prompt.name" /></td>
                <td><bean:message key="prompt.type" /></td>
                <td><bean:message key="prompt.required" />?</td>
                <td><bean:message key="prompt.audit" />?</td>
                <td><bean:message key="prompt.unique" />?</td>
                <td><bean:message key="prompt.minLength" /></td>
                <td><bean:message key="prompt.maxLength" /></td>
      		  </tr>
							
  						<%-- repeating attribute rows --%>            

<%int RecordCounter = 0;
	String bgcolor = "";%>
	<bean:define id="attrSizeMinus1" type="java.lang.String"><%=((attrSize.intValue())-1)%></bean:define>
	<logic:iterate id="attrListIndex" name="codetableRegistrationForm" property="codetableAttributes" indexId="attrIndexCount">
		<%RecordCounter++;%>
		<tr	id="node<%=RecordCounter%>" class="<%
            					if (RecordCounter % 2 == 1)
            						out.print("normalRow");
            					else
            						out.print("alternateRow");
            				%>" taskid=<%=RecordCounter%> position="<%=RecordCounter%>" origposition="<%=RecordCounter%>" onmouseover="hoverClassFlip(this, 'on')"  onmouseout="hoverClassFlip(this, 'out')" >			
			<td class=sequenceNumberCol width='5%'>&nbsp;<%=RecordCounter%>&nbsp;</td>
		<logic:notEqual name="attrIndexCount" value="<%=attrSizeMinus1%>">
			<td width=1%><span onclick='moveDown(this.parentNode.parentNode);' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_down_large.gif" border=0></span></td>
		</logic:notEqual>
		<logic:equal name="attrIndexCount" value="<%=attrSizeMinus1%>">
			<td width=1%>&nbsp;</td>
		</logic:equal>
		<logic:notEqual name="attrIndexCount" value="0">
			<td width=1%><span onclick='moveUp(this.parentNode.parentNode)' onmouseover="this.style.cursor= 'pointer'"><img src="/<msp:webapp/>images/arrow_up_large.gif" border=0></span></td>												
		</logic:notEqual>
		<logic:equal name="attrIndexCount" value="0">
			<td width=1%>&nbsp;</td>												
		</logic:equal>
		 <td><bean:write name="attrListIndex" property="dbColumn" /></td>					
         <td><bean:write name="attrListIndex" property="displayName" /></td>
         <td><bean:write name="attrListIndex" property="type" /></td>
         <td><bean:write name="attrListIndex" property="required" /></td>
         <td><bean:write name="attrListIndex" property="audit" /></td>
         <td><bean:write name="attrListIndex" property="unique" /></td>
         <td><bean:write name="attrListIndex" property="minLength" /></td>
         <td><bean:write name="attrListIndex" property="maxLength" /></td>
		</tr>		
	</logic:iterate>			
            </table>
					<input type="hidden" name="resequencedOrderValue"/> <input type="hidden" name="attrFinishPage" value="resequence"/> </td> 
        </tr> 		
      </table>

		</td> 
  </tr> 
</table>
<%-- END DETAIL TABLE --%> 


<%-- button table --%>
<br> 
<table border="0" cellpadding=1 cellspacing=1 align=center> 
  <tr align="center"> 
    <td> 
	  <html:submit property="submitAction" onclick="return updateMasterList() && disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit> 
	  <input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=ResequenceBack')" value="<bean:message key='button.back'/>"/>
      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table> 
<%-- end button table --%>
</html:form> 
<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span> 

</body>
</html:html>

