<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 29 oct 2007 - mjt - create JSP --%>

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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationAttributeListUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/codetableRegistration.js"></script>
<html:javascript formName="codetableRegistrationForm"/>
<script type='text/javascript'>
var _tableSimple = "<%out.print(naming.PDCodeTableConstants.SIMPLE_CODE);%>";
var _tableComplex = "<%out.print(naming.PDCodeTableConstants.COMPLEX_CODE);%>";
var _tableCompound = "<%out.print(naming.PDCodeTableConstants.COMPOUND_CODE);%>";
var _simpleTableEntity = "<%out.print(naming.PDCodeTableConstants.CONTEXTS_CLASS);%>";
var attributeMap = new Object();
attributeMap[""] = "";
<logic:iterate indexId='attIterIndex' id='attrIter' name='codetableRegistrationForm' property='addAttributeList'>
	attributeMap["<bean:write name='attrIter' property='dbItemName'/>"] = "<bean:write name='attrIter' property='type'/>";	
</logic:iterate>

function attributeCallback(el){
	//alert(attributeMap[el.attributeSelected.value]);
	el.attributeDataType.value = attributeMap[el.attributeSelected.value];	
	el.attributeDataType.disabled = true;
	el.attrDisplayName.value="";
}


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



function 
	evalCompound(el) {
		//alert(el.attrCompound.checked);
		if (el.attrCompound != null) {

			if (el.attrVerification.checked == true
					&& el.attrCompound.checked == true) {
				alert("Either Compound or verification can be checked");
				return false;
			}

			if (el.attrCompound.checked == true) {

				showHide('compoundMain2', 1);
				showHide('context3', 1);
				showHide('context4', 1);
				showHide('typeMain1', 1);
				showHide('typeMain2', 1);
				showHide('context1', 1);
				showHide('context2', 1);

			} else {

				showHide('compoundMain2', 0);
				showHide('context3', 0);
				showHide('context4', 0);
				showHide('typeMain1', 0);
				showHide('typeMain2', 0);
				showHide('context1', 0);
				showHide('context2', 0);

			}
			evalType(document.forms.codetableRegistrationForm);

		}
		if (el.attrVerification != null) {
			if (el.attrVerification.checked == true) {
				if (el.attrVerification.checked == true) {

					showHide('context1', 1);
					showHide('context2', 1);
					showHide('compoundMain2', 1);

				} else {

					showHide('context1', 0);
					showHide('context2', 0);
					showHide('compoundMain2', 0);
				}
			}
		}
	}

	function validateCodetableRegAttributeFields(el) {
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
		} else if (el.attrCompound.checked) { //else if (el.attrCompound.checked==true || el.attrCompound.checked==checked) {		
			if (el.attrCodetableType.options[el.attrCodetableType.selectedIndex].value == "") {
				msg += "Compound Type is required.";
				el.attrCodetableType.focus();
			} else if (el.compoundAttrID.value == "") {
				msg += "Compound ID is required.";
				el.compoundAttrID.focus();
			}/* else if (el.compoundAttrName.value == "") {
						msg += "Compound Name is required.";
			  			el.compoundAttrName.focus();
					} */else if (el.attrCodetableType.options[el.attrCodetableType.selectedIndex].value == _tableSimple) {
				if (el.compoundAttrContextKey.value == "") {
					msg += "Compound ContextKey is required.";
					el.compoundAttrContextKey.focus();
				}

			} else if (el.compoundAttrEntityName.value == "") {
				msg += "Compound EntityName is required.";
				el.compoundAttrEntityName.focus();
			}
		} else if (el.attrVerification.checked) {
			if (el.compoundAttrID.value == "") {
				msg += "Compound ID is required.";
				el.compoundAttrID.focus();
			}

			else if (el.compoundAttrEntityName.value == "") {
				msg += "Compound EntityName is required.";
				el.compoundAttrEntityName.focus();
			}
		}

		if (msg == "") {
			return validateCodetableRegistrationForm(el);
		}
		alert(msg);
		return false;
	}
</script>
</head>

<body topmargin=0 leftmargin='0' >
<html:form action="/displayCodetableRegistrationAttributesSummary" target="content"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr id='detailsHeading' > 
    <td align="center" class="header">Manage <bean:message key="prompt.codeTableRegistration" /> - 
 			<logic:equal name="opType" value="create">
			  Create
			</logic:equal>
 			<logic:equal name="opType" value="update">
  		  Update
			</logic:equal>
  		<bean:message key="prompt.codeTableRegistration" /> Attributes
  	</td> 
  </tr> 

  <tr id='confirmMessage' > 
    <td align="center" class="confirm" >
 			<logic:equal name="opType" value="create">
  			<bean:message key="prompt.codeTableRegistration" /> record has been  created.
			</logic:equal>
 			<logic:equal name="opType" value="update">
  			<bean:message key="prompt.codeTableRegistration" /> record has been  updated.
			</logic:equal>
	  </td> 
  </tr> 
  <tr>
	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<br> 
<table width="98%" border="0"> 
  <tr> 
    <td> 
		  <ul> 
        <li>Enter Code Table Registration Attribute information, then select the Add Attribute button to add new Attribute.</li> 
        <li>Select a radio button next to a Code Table Registration Attribute to update that Attribute's information.</li> 
        <li>Select the Submit button to view the Summary screen.</li> 
      </ul>
		</td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%-- Begin Pagination Header Tag (attribute matrix) --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
  maxIndexPages="10"  export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN DETAIL TABLE --%> 
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

     			
		  <%-- Attribute data entry table --%> 
		  <logic:equal name="codetableRegistrationForm" property="showAddAttributes" value="true">
			<div class='spacer'></div>
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
               <td width='1%' nowrap id="typeMain1" class='hidden' style="BACKGROUND-COLOR: #cccccc;"><bean:message key="prompt.diamond"/><b><bean:message key="prompt.compound" />  <bean:message key="prompt.type" /></b></td>
                <td id="typeMain2" class='hidden' style="BACKGROUND-COLOR: #f0f0f0;">
						<html:select property="attrCodetableType" styleId="attrCodetableTypeId">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection property="codetableTypeList" value="code" label="description" /> 
						</html:select>
				</td>
              </tr>
             <tr id="contextMain" class="hidden">
			   <td width='1%' id="context1" nowrap class='hidden' style="BACKGROUND-COLOR: #cccccc;" ><bean:message key="prompt.diamond"/><b><bean:message key="prompt.compoundId" /></b></td>
                <td class='hidden' style="BACKGROUND-COLOR: #f0f0f0;" id="context2"><html:text property='compoundAttrID' maxlength='50' size='30'/></td>
                <td width='1%' nowrap id="context3" class='hidden' style="BACKGROUND-COLOR: #cccccc;"><b><bean:message key="prompt.diamond"/><bean:message key="prompt.compoundName" /></b></td>
                <td class='hidden' style="BACKGROUND-COLOR: #f0f0f0;" nowrap id="context4" ><html:text property='compoundAttrName' maxlength='50' size='30'/></td>
              </tr>
			  
			  <tr id="compoundMain2" class='hidden' >				
                 <td width='1%' nowrap id="contextMain1" class='hidden' style="BACKGROUND-COLOR: #cccccc;"><b> <bean:message key="prompt.diamond"/><bean:message key="prompt.compoundContextKey" /> </b> </td>
                <td id="contextMain2" class='hidden' style="BACKGROUND-COLOR: #f0f0f0;"><html:text property='compoundAttrContextKey' maxlength='50' size='30'/></td>				
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.compoundEntityName" /></td>
                <td class=formDe><html:text property='compoundAttrEntityName' maxlength='90' size='90'/></td>
              </tr>

            </table>
          </td>
        </tr>
				<tr>
          <td align=center>
		  <html:submit property="submitAction" onclick="return validateCodetableRegAttributeFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.addAttr"/></html:submit>
		  </td> 
				</tr>
      </table>
	  </logic:equal>
		  <%-- end Attribute data entry table --%> 
			

			<%-- the section that follows is for the Attribute definition --%>      
			<div class='spacer'></div>
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
        <tr> 
          <td class=detailHead><bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.attributes" /></td> 
        </tr> 
        <tr> 
          <td valign=top align=center> 
      			<div class=scrollingDiv100> 
					  <table  cellpadding="0" cellspacing="1" width='100%'> 
							<%-- column titles row for attribute matrix --%>
              <tr height="100%" bgcolor='#cccccc' class=subHead>
                <td><bean:message key="prompt.edit" /></td>
                 <td><bean:message key="prompt.order" /></td>
                  <td><bean:message key="prompt.attribute" /> <bean:message key="prompt.name" /></td>
                  <td><bean:message key="prompt.display" /> <bean:message key="prompt.name" /></td>
                  <td><bean:message key="prompt.type" /></td>
                  <td><bean:message key="prompt.required" />?</td>
                  <td><bean:message key="prompt.audit" />?</td>
                  <td><bean:message key="prompt.unique" />?</td>
                  <td><bean:message key="prompt.minLength" /></td>
                  <td><bean:message key="prompt.maxLength" /></td>
      			  </tr>

							<%-- repeating rows for attribute matrix --%>
              <% int RecordCounter = 0; %>
      				<logic:iterate indexId="idx" id="codetableDataIter" name="codetableRegistrationForm" property="codetableAttributes">
                <pg:item>
            			<tr height="100%" class="
            				<% RecordCounter++;
            					if (RecordCounter % 2 == 1)
            						out.print("normalRow");
            					else
            						out.print("alternateRow");
            				%>"
        					>		 	
                    <td><input type=radio id="updateAttrButtonRowId" name='editAttribute' value='<bean:write name="codetableDataIter" property="dbColumn" />'/></td>
                    <td><bean:write name="codetableDataIter" property="displayOrder" /></td>
                    <td><bean:write name="codetableDataIter" property="dbColumn" /></td>					
                    <td><bean:write name="codetableDataIter" property="displayName" /></td>
                    <td><bean:write name="codetableDataIter" property="type" /></td>
                    <td><bean:write name="codetableDataIter" property="required" /></td>
                    <td><bean:write name="codetableDataIter" property="audit" /></td>
                    <td><bean:write name="codetableDataIter" property="unique" /></td>
                    <td><bean:write name="codetableDataIter" property="minLength" /></td>
                    <td><bean:write name="codetableDataIter" property="maxLength" /></td>
								  </tr>
          			</pg:item>
      				</logic:iterate>
            </table>
      			</div>
					</td> 
        </tr> 
				<tr id='updateAttrButtonRow' class='hidden' >
          <td align="center">
					  <html:submit property="submitAction"><bean:message key="button.removeAttr"/></html:submit>
					  <html:submit property="submitAction"><bean:message key="button.updateAAttr"/></html:submit>
					</td>
				</tr>

      </table>			
		</td>
  </tr> 
</table> 

<%-- END DETAIL TABLE --%> 

<%-- Begin Pagination navigation Row--%>
<table align="center">
  <tr>
    <td>
    	<pg:index>
    		<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
    			<tiles:put name="pagerUniqueName" value="pagerSearch"/>
    			<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
    		</tiles:insert>
    	</pg:index>
    </td>
  </tr>
</table>

<br> 
<table border="0" cellpadding=1 cellspacing=1 align=center> 
  <tr id='editButtons'> 
    <td align="center"> 
	
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.submit"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td> 
  </tr> 
</table> 
</pg:pager>
</html:form> 

<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span> 

</body>
</html:html>

