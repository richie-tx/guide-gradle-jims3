<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 22 oct 2007 - mjt - create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="messaging.codetable.reply.CodetableAttributeResponseEvent" %>
<%@ page import="messaging.codetable.reply.CodetableRegistrationAttributesAndTypesResponseEvent" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="naming.Features" %>



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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationAttributeUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/codetableRegistration.js"></script>

<html:javascript formName="codetableRegistrationForm"/>
<script type='text/javascript'>

var attributeMap = new Object();
attributeMap[""] = "";
<logic:iterate indexId='attIterIndex' id='attrIter' name='codetableRegistrationForm' property='addAttributeList'>
	attributeMap["<bean:write name='attrIter' property='dbItemName'/>"] = "<bean:write name='attrIter' property='type'/>";	
</logic:iterate>

function attributeCallback(el){
	//alert(attributeMap[el.attributeSelected.value]);
	el.attributeDataType.value = attributeMap[el.attributeSelected.value];	
	el.attributeDataType.disabled = true;
}

var _tableSimple = "<%out.print(naming.PDCodeTableConstants.SIMPLE_CODE);%>";
var _tableComplex = "<%out.print(naming.PDCodeTableConstants.COMPLEX_CODE);%>";
var _tableCompound = "<%out.print(naming.PDCodeTableConstants.COMPOUND_CODE);%>";
var _simpleTableEntity = "<%out.print(naming.PDCodeTableConstants.CONTEXTS_CLASS);%>";
function evalType(el){
	if (el.attrCodetableType.options[el.attrCodetableType.selectedIndex].value == _tableSimple){
		showHide('contextMain1', 1);	
		showHide('contextMain2', 1);		
		document.getElementsByName("compoundAttrEntityName")[0].value = _simpleTableEntity;
		document.getElementsByName("compoundAttrEntityName")[0].readOnly=true;		
	} else {
		showHide('contextMain1', 0);	
		showHide('contextMain2', 0);		
		document.getElementsByName("compoundAttrEntityName")[0].value = "";
		document.getElementsByName("compoundAttrEntityName")[0].readOnly=false;					
	}
}



function evalCompound(el){

 
	//alert(el.attrCompound.checked);
	if(el.attrCompound != null && el.attrVerification != null){
	    if (el.attrVerification.checked==true && el.attrCompound.checked == true){
			alert("Either Compound or verification can be checked");
			return false;
		}
	}
	
	
	if(el.attrCompound!=null && el.attrCompound.checked==true) {	 
		
			
			showHide('compoundMain2', 1);			
			showHide('context3', 1);
			showHide('context4', 1);
			showHide('typeMain1', 1);
			showHide('typeMain2', 1);
			showHide('context1', 1);
			showHide('context2', 1);	
			evalType(document.forms.codetableRegistrationForm);				
	} else if(el.attrCompound!=null && el.attrCompound.checked==false && el.attrVerification.checked==false) {
		
			showHide('compoundMain2', 0);				
			showHide('context3', 0);
			showHide('context4', 0);
			showHide('typeMain1', 0);
			showHide('typeMain2', 0);
			showHide('context1', 0);
			showHide('context2', 0);	
			evalType(document.forms.codetableRegistrationForm);				
	}else if (el.attrVerification.checked==true){		
		
		showHide('context1', 1);
		showHide('context2', 1);
		showHide('compoundMain2', 1);
	} else if (el.attrVerification.checked==false){
		
		showHide('context1', 0);
		showHide('context2', 0);			
		showHide('compoundMain2', 0);
	}
}

function validateCodetableRegAttributeFields(el) 
{
  	var regexp = /^[a-zA-Z0-9_\- ]*$/;
   	var msg = "";
   	var focusSet = false;   	 
  	if (el.attributeSelected.options[el.attributeSelected.selectedIndex].value == "") {
		msg += "Attribute Name is required.";
  		el.attributeSelected.focus();
	} else if (el.attrDisplayName.value == "") {
		msg += "Attribute Display Name is required.";
  		el.attrDisplayName.focus();
	} else if (el.attrMinLength.value == "") {
		msg += "Min Length is required.";
  		el.attrMinLength.focus();
	} else if (el.attrMaxLength.value == "") {
		msg += "Max Length is required.";
  		el.attrMaxLength.focus();
	} else if (el.attrCompound.checked) { 	//else if (el.attrCompound.checked==true || el.attrCompound.checked==checked) {		
		if(el.attrCodetableType.options[el.attrCodetableType.selectedIndex].value == "") {
			msg += "Compound Type is required.";
  			el.attrCodetableType.focus();
		} else if (el.compoundAttrID.value == "") {
			msg += "Compound ID is required.";
  			el.compoundAttrID.focus();
		}/* else if (el.compoundAttrName.value == "") {
			msg += "Compound Name is required.";
  			el.compoundAttrName.focus();
		} */ else if (el.attrCodetableType.options[el.attrCodetableType.selectedIndex].value == _tableSimple) {
  			if (el.compoundAttrContextKey.value == "") { 
  				msg += "Compound ContextKey is required.";
  				el.compoundAttrContextKey.focus();
  			}  	
  				
  		} else if(el.compoundAttrEntityName.value == "") {
			msg += "Compound EntityName is required.";
  			el.compoundAttrEntityName.focus();
		}
	} else if (el.attrVerification.checked){
	  if (el.compoundAttrID.value == "") {
			msg += "Compound ID is required.";
  			el.compoundAttrID.focus();
		}
		
		else if(el.compoundAttrEntityName.value == "") {
			msg += "Compound EntityName is required.";
  			el.compoundAttrEntityName.focus();
		}
	}
  	
    if (msg == "")
			{
      return validateCodetableRegistrationForm(el);
    }
    alert(msg);    
	return false;
}
</script>

</head>

<body topmargin=0 leftmargin='0' onload="evalCompound(document.forms.codetableRegistrationForm)">
<html:form action="/displayCodetableRegistrationAttributesSummary" target="content"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage <bean:message key="prompt.codeTableRegistration" /> - 
 			<logic:equal name="opType" value="create">
			  Create
			</logic:equal>
 			<logic:equal name="opType" value="update">
			  Update
			</logic:equal>
  		<bean:message key="prompt.codeTableRegistration" /> Attribute
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
        <li>Enter all required information.</li>
        <li>Select the Submit button to view the Summary screen.</li>
        <li>Select the Reset button to clear the information.</li>
        <li>Select the Cancel button to return to the Code Table Registration Search screen.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.diamond"/>&nbsp;<bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>

      <%-- base registration data (display only) --%>
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


			<%-- edit attribute table --%>
			<br>
		<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
        <tr>
          <td class=detailHead><bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.attribute" /></td>
        </tr>
        <tr>
          <td valign=top align=center>
       <table cellpadding="4" cellspacing="1" width='100%'>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.attribute" /> <bean:message key="prompt.name" /></td>
                <td class=formDe>
					<html:select property="attributeSelected"  onchange='attributeCallback(this.form);'>
                    <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
                    <html:optionsCollection property="addAttributeList" value="dbItemName" label="dbItemName" />
                  </html:select>
								</td>
                <td class=formDeLabel nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.display" /> <bean:message key="prompt.name" /></td>
                <td class=formDe><html:text name="codetableRegistrationForm" property='attrDisplayName' maxlength='50' size='30' /></td>
              </tr>
              <tr>
                <td class=formDeLabel nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.type" /></td>
                <td class=formDe>
					<html:select property="attributeDataType" >
                    <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
                    <html:options property="addAttrDataTypeList"/>
                  </html:select>
								</td>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.required" />?</td>
                <td class=formDe><html:checkbox name="codetableRegistrationForm" property="attrRequired"/></td>
      			  </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.audit" />?</td>
                <td class=formDe><html:checkbox name="codetableRegistrationForm" property="attrAudit" /></td>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.unique" />?</td>
                <td class=formDe><html:checkbox name="codetableRegistrationForm" property="attrUnique" /></td>
              </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.updatable" />?</td>
                <td class=formDe><html:checkbox name="codetableRegistrationForm"  property="attrUpdatable" /></td>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.verification" /> <bean:message key="prompt.required" />?</td>
                <td class=formDe><html:checkbox name="codetableRegistrationForm" property="attrVerification" styleId="attrVerificationId"/></td>
              </tr>
      		  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.minLength" /></td>
                <td class=formDe><html:text name='codetableRegistrationForm' property='attrMinLength' size='3' maxlength="3"/></td>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.maxLength" /></td>
                <td class=formDe><html:text name='codetableRegistrationForm' property='attrMaxLength' size='3' maxlength="3"/></td>
              </tr>
			   <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.compound" />?</td>
                <td class=formDe><html:checkbox property="attrCompound" styleId="attrCompoundId"/></td>
               <td width='1%' nowrap id="typeMain1" class='hidden' style="BACKGROUND-COLOR: #cccccc;font-weight: bold;"><bean:message key="prompt.diamond"/><bean:message key="prompt.compound" />  <bean:message key="prompt.type" /></td>
                <td id="typeMain2" class='hidden' style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: x-small; font-weight: normal; 	color: #000000; BACKGROUND-COLOR: #f0f0f0;">
						<html:select property="attrCodetableType" styleId="attrCodetableTypeId">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection property="codetableTypeList" value="code" label="description" /> 
						</html:select>
				</td>
              </tr>
			  
                 <td width='1%' nowrap id="context1" class='hidden' style="BACKGROUND-COLOR: #cccccc;font-weight: bold;"><bean:message key="prompt.diamond"/><bean:message key="prompt.compoundId" /></td>
                <td id="context2" class='hidden' style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: x-small; font-weight: normal; 	color: #000000; BACKGROUND-COLOR: #f0f0f0;"><html:text property='compoundAttrID' maxlength='50' size='30'/></td>
                <td width='1%' nowrap id="context3" class='hidden' style="BACKGROUND-COLOR: #cccccc;font-weight: bold;"><bean:message key="prompt.diamond"/><bean:message key="prompt.compoundName" /></td>
                <td id="context4" class='hidden' style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: x-small; font-weight: normal; 	color: #000000; BACKGROUND-COLOR: #f0f0f0;"><html:text property='compoundAttrName' maxlength='50' size='30'/></td>
           
			  <tr id="compoundMain2" class='hidden' >				
                 <td width='1%' nowrap id="contextMain1" class='hidden' style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: x-small; font-weight: bold; 	color: #000000; BACKGROUND-COLOR: #cccccc;"> <bean:message key="prompt.diamond"/><bean:message key="prompt.compoundContextKey" />  </td>
                <td id="contextMain2" class='hidden' style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: x-small; font-weight: normal; 	color: #000000; BACKGROUND-COLOR: #f0f0f0;"><html:text property='compoundAttrContextKey' maxlength='50' size='30'/></td>				
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.compoundEntityName" /></td>
                <td class=formDe><html:text property='compoundAttrEntityName' maxlength='90' size='90'/></td>
              </tr>

            </table>
          </td>
        </tr>
      </table>
			<%-- end edit attribute table --%>

   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<br>
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr id='editButtons'>
		<td align='center'>
		<input type="button" onclick="goNav('/<msp:webapp/>submitCodetableRegistrationAttributeUpdate.do?submitAction=ResequenceBack')" value="<bean:message key='button.back'/>"/>			
			<html:submit property="submitAction" onclick="return validateCodetableRegAttributeFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
  </tr>
</table>


</html:form>
<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

