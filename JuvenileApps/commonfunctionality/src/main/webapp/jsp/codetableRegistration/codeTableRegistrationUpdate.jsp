<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 26 oct 2007 - mjt - create JSP --%>

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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationUpdate.jsp</title>
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

function evalType(el){
	if (el.codetableType.options[el.codetableType.selectedIndex].value == _tableSimple){
		showHide('contextInstruct', 1);
		showHide('entityInstruct', 0);
		showHide('contextMain', 1);		
		document.getElementsByName("codetableEntityName")[0].value = _simpleTableEntity;
		document.getElementsByName("codetableEntityName")[0].readOnly=true;
		document.getElementsByName("codetableContextKey")[0].focus();
	}
	else {
		showHide('contextInstruct', 0);
		showHide('entityInstruct', 1);
		showHide('contextMain', 0);		
		document.getElementsByName("codetableEntityName")[0].value = document.getElementsByName("origEntityName")[0].value;
		document.getElementsByName("codetableEntityName")[0].readOnly=false;
		document.getElementsByName("codetableEntityName")[0].focus();			
	}
}

function validateCodetableRegCreateFields(el) 
{
  	var regexp = /^[a-zA-Z0-9_\- ]*$/;
   	var msg = "";
   	var focusSet = false;  
  	 
  	if (el.codetableName.value == "") {
		msg += "Name is required.";
  		el.codetableName.focus();
	} else if (el.codetableDescription.value == "") {
		msg += "Description is required.";
  		el.codetableDescription.focus();
	} else if (el.codetableType.options[el.codetableType.selectedIndex].value == "") {
		msg += "Type is required.";
  		el.codetableType.focus();
	} else if (el.codetableType.options[el.codetableType.selectedIndex].value == _tableSimple)
			{
  		if (el.codetableContextKey.value == "") { 
  			msg += "Context Key is required.";
  			el.codetableContextKey.focus();
  		}  		
  				
  	} else if(el.codetableEntityName.value == "") {
		msg += "Entity Name is required.";
  		el.codetableEntityName.focus();
	}
  	
    if (msg == "")
			{    	
      return validateCodetableRegistrationForm(el);
    }
    alert(msg);    
	return false;
}
function checkAgency()
{
	var tableAgency = document.codetableRegistrationForm.agencyName;
	var tableAgencyCode =document.codetableRegistrationForm.agencyCode;
	
	var elem1 = tableAgency.value;
	var elem2 = tableAgencyCode.value;	
	
	if((elem1==null||elem1=="") && (elem2==null||elem2=="")){
	alert("Enter At least one Search field Agency Name/Code");	
	return false;
	}	
	
	if(!(elem1==null||elem1=="")&& elem1.length<2){
	alert("Enter At least 2 characters for Agency Name");
	tableAgency.focus();
	return false;
	}
	if(!(elem2==null||elem2=="")&& elem2.length<2){
	alert("Enter At least 2 characters for Agency Code");
	tableAgencyCode.focus();
	return false;
	}
	else
	return true;
}

</script>

</head>

<body topmargin=0 leftmargin='0' onKeyDown="return checkEnterKeyAndSubmit(event,true)" onLoad="evalType(document.forms.codetableRegistrationForm); document.forms.codetableRegistrationForm.codetableName.focus();">
<html:form action="/displayCodeTableRegistrationSummary" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage <bean:message key="prompt.codeTableRegistration" /> - 
		<logic:equal name="codetableRegistrationForm" property="opType" value="update">
			Update
		</logic:equal>
		<logic:equal name="codetableRegistrationForm" property="opType" value="create">
			Create
		</logic:equal>
 		<bean:message key="prompt.codeTableRegistration" />
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
        <li>Enter all required information.</li>
        <span id="contextInstruct" class='hidden'>
			<li>Enter 2 characters in the Context Key field to simulate a partial-name search.</li>
		</span>	
		<span id="entityInstruct" class='hidden'>
			<li>Enter 2 characters in the Entity Name field to simulate a partial-name search.</li>
		</span>			
		<li>Enter an Agency Name or Code and select the Find button to associate one or more Agencies</li>		
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
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
        <tr>
          <td class=detailHead><bean:message key="prompt.codeTableRegistration" /></td>
        </tr>
        <tr>
          <td valign=top align=center>
            <table cellpadding="4" cellspacing="1" width='100%'>
              <tr>
                <td class=formDeLabel nowrap><bean:message key="prompt.diamond"/> <bean:message key="prompt.name" /></td>
                <td class=formDe><html:text property="codetableName" size="50" maxlength="50"/></td>
      			  </tr>
      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/> <bean:message key="prompt.description" /></td>
                <td class=formDe><html:text property="codetableDescription" size="50" maxlength="100" /></td>
              </tr>

      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/> <bean:message key="prompt.type" /></td>
                <td class=formDe>
						<html:select property="codetableType" onchange="evalType(this.form)" styleId="codetableTypeId">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection property="codetableTypeList" value="code" label="description" /> 
						</html:select>
				</td>
      		 </tr>

              <tr id="contextMain" class='hidden'>
                <td class=formDeLabel nowrap><bean:message key="prompt.diamond"/> <bean:message key='prompt.context' /> <bean:message key='prompt.key' /></td>
                <td class=formDe><html:text property="codetableContextKey" size="50" maxlength="50" /></td>
              </tr>

      			  <tr>
                <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.diamond"/> <bean:message key='prompt.entity' /> <bean:message key='prompt.name' /></td>
                <td class=formDe><html:text property="codetableEntityName" size="50" maxlength="50" />
                <input type="hidden" name="origEntityName" value="<bean:write name='codetableRegistrationForm' property='codetableEntityName'/>"/></td>
              </tr>
              
              <tr>
	  <td colspan='2'>
      <table class=borderTableBlue cellpadding=4 cellspacing=0 border=0 width='100%'>
        <tr class=detailHead>
          <td class=detailHead>Select Agency to Associate to Code Table Registration</td>
        </tr>
        <tr>
          <td align=center>
            <table border=0 cellspacing=1 cellpadding=2 width='100%'>
              <tr>
                <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.agencyName" /> </td>
                <td class=formDe><html:text name="codetableRegistrationForm" property="agencyName" size='60' maxlength='60' /></td>
              </tr>
              <tr>
                <td class=formDeLabel><bean:message key="prompt.agencyCode" /></td>
                <td class=formDe><html:text name="codetableRegistrationForm" property="agencyCode" size='5' /></td>
              </tr>                    
              <tr>
                <td class=formDeLabel></td>
                <td class=formDe>
                               
                    <html:submit property="submitAction" onclick="return checkAgency()  && disableSubmit(this, this.form)"><bean:message key="button.find"></bean:message></html:submit> 
                    <html:submit property="submitAction"><bean:message key="button.clear"></bean:message>	</html:submit>
                </td>
              </tr>
            </table>
          </td>
        </tr>
              
                 <logic:notEmpty name="codetableRegistrationForm" property="availableAgencies">
						<tr height="100%">
							<td colspan=2 align=center>
							   <bean:write name="codetableRegistrationForm" property="agencySearchResultSize"/> agency matches found.
                        <%--		   <logic:notEqual name="codetableRegistrationForm" property="agencyName" value="">
			 					   		<bean:message key="prompt.agencyName"/>:<bean:write name="codetableRegistrationForm" property="AgencyName"/> 
								   </logic:notEqual>
            		               <logic:notEqual name="codetableRegistrationForm" property="agencyType" value="">						   
									   <bean:message key="prompt.agencyType"/>:<bean:write name="codetableRegistrationForm" property="agencyType"/>
   						   			</logic:notEqual> 
            		               <logic:notEqual name="codetableRegistrationForm" property="jmcRep" value="">						   
									   <bean:message key="prompt.jmcRep"/>:<bean:write name="codetableRegistrationForm" property="jmcRep"/>
   						   			</logic:notEqual> --%>
					</td>
				</tr>              
							<tr bgcolor=#cccccc>
								<td colspan=2 class=boldText><bean:message key="prompt.agencyName"/></td>
							</tr>                
							<tr height="100%">
								<td colspan=2> 
					<!-- VARIABLES NEEDED FOR DISPLAY -->
								<%int RecordCounter = 0; 
								  String bgcolor = ""; %> 
								<div class=scrollingDiv200>
							<!-- agency checkbox selection start-->
									<table border=0 width=100% cellspacing=1 cellpadding=2>
										<jims2:sortResults beanName="codetableRegistrationForm" results="availableAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" hideMe="true" /> 						   						   													
								        <logic:iterate id="agenciesIndex" name="codetableRegistrationForm" property="availableAgencies"> 
											<tr height="100%" class= <% RecordCounter++;
				                    			 bgcolor = "normalRow";                      
    	           				    			 if (RecordCounter % 2 == 1)
							                         bgcolor = "alternateRow";
            				   				     out.print(bgcolor);  %>  >
												<td class=boldText>
                                    			   <input type="checkbox" name="selectedAgencies" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
                                    			       <a href="javascript: openWindow('/<msp:webapp/>displaySecurityInquiriesAgencyDetails.do?agencyId=<bean:write name="agenciesIndex" property="agencyId"/>&action=view')">
				                                        <bean:write name='agenciesIndex' property='agencyName'/> </a>
												</td>
											</tr>
									    </logic:iterate>		
									</table>
						 <!-- agency checkbox selection end -->	
								</div>
								</td>
							</tr>
							<tr>
					           <td align=center colspan=2>
					           
					           <html:submit property="submitAction" styleId="addSelectedBtn">
									<bean:message key="button.addSelected"></bean:message>
								</html:submit>
                				   	<%-- <input type="submit" name="submitAction" styleId="addSelected" onclick="changeFormActionURL('codetableRegistrationForm', '/<msp:webapp/>displayCodeTableRegistrationSummary.do?action=create&submitAction=<bean:message key="button.addSelected"/>', true);" value="<bean:message key="button.addSelected"/>"> --%>
               			 			           
					           </td>							    
				            </tr> 
						</logic:notEmpty>
						
						<logic:notEmpty name="codetableRegistrationForm" property="currentAgencies"> 
       			<tr><td>&nbsp;</td></tr>
       			<tr>
				   <td colspan=2 class=formDeLabel>Selected Agencies</td>
				</tr>
				<tr height="100%">
       				<td colspan=2 align=center>	
   					<div class="scrollingDiv100">
					<table width=100% cellspacing=0 cellpadding=4>
						<% int RecordCounter = 0; 
						   String bgcolor = ""; %> 	
						<jims2:sortResults beanName="codetableRegistrationForm" results="currentAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" hideMe="true" /> 		   						   				
						<logic:iterate id="currentIndex" name="codetableRegistrationForm" property="currentAgencies">
			            <tr height="100%" class= <% RecordCounter++;
				           			  bgcolor = "normalRow";                      
    	           			    	  if (RecordCounter % 2 == 1)
						                  bgcolor = "alternateRow";
            			   			   out.print(bgcolor);  %>  >
							<td width=1% valign=top>
							    <a href="/<msp:webapp/>handleCodeTableAgencyRemove.do?agencyId=<bean:write name="currentIndex" property="agencyId"/>" title='Remove <bean:write name="currentIndex" property="agencyName"/>'>Remove</a>
							</td>
							<td class=boldText>
							    <bean:write name="currentIndex" property="agencyName"/>
							    <input type="hidden" name="agencySelected">
							</td>
						</tr>
						</logic:iterate>
						</table>	
					</div>  
					</td>
				</tr> 	
  			    </logic:notEmpty>	
  			  				
            </table>
          </td>
        </tr>
      </table>			     
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- begin button table --%>
<br>
<table border="0" cellpadding=1 cellspacing=1 align=center>
	<tr>
		<td align='center'>
			<html:submit property="submitAction" onclick="return validateCodetableRegCreateFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.reset"/></html:submit>
	        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
  </tr>

</table>
<%-- end button table --%>

</html:form>
<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

