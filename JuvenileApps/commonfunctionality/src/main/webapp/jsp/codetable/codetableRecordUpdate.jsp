<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/09/2007		AWidjaja Create JSP--%>

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


<title><bean:message key="title.heading"/>&nbsp;Manage Code Table&nbsp;- codetableRecordUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

</head>

<body topmargin=0 leftmargin='0' onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayUpdateCodetableConfirmation" target="content"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage Code Table - Code Table Record 
		<logic:equal name="codetableForm" property="opType" value="update">
			Update
		</logic:equal>
		<logic:equal name="codetableForm" property="opType" value="create">
			Create
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
        <li>Enter all required information.</li>
        <li>Select the Submit button to view the summary screen.</li>
        <li>Select the Reset button to clear the information.</li>
        <li>Select the Cancel button to return to the Code Table Data List screen.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.diamond"/>&nbsp;Required Fields</td>
  </tr>
  <tr>
    <td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <%-- complex table--%>
      <table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue">
        <tr>
          <td class=detailHead>Code Table:&nbsp;<bean:write name="codetableForm" property="codetableName"/></td>
        </tr>
		
        <tr>
          <td valign=top align=center>
            <table cellpadding="4" cellspacing="1" width='100%'>
				<logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes" indexId="idx">
					<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
						<tr>
							<td class='formDeLabel' width='1%' nowrap>
								<logic:equal name="codetableHeaderIter" property="required" value="true">
									<bean:message key="prompt.diamond"/>
								</logic:equal>
								<bean:write name="codetableHeaderIter" property="displayName"/>
							</td>
							<td class='formDe'>
								<jims2:if name="codetableHeaderIter" property="updatable" value="true" op="equal">
									<jims2:or name="codetableForm" property="opType" value="create" op="equal"/>
									<jims2:then>
										
										<%-- COMPLEX CODETABLE --%>
										<logic:equal name="codetableHeaderIter" property="compound" value="true">
											<logic:iterate id="compoundListIter" name="codetableForm" property="codetableCompoundList">
												
												<%String codetableIdentifier = "";%>
												<logic:notEmpty name="codetableHeaderIter" property="contextKey">
													<bean:define id="contextKey" name="codetableHeaderIter" property="contextKey" type="java.lang.String"/>
													<%codetableIdentifier = contextKey;%>
												</logic:notEmpty>
												<logic:empty name="codetableHeaderIter" property="contextKey">
													<bean:define  id="entityName" name="codetableHeaderIter"  property="entityName" type="java.lang.String"/>
													<%codetableIdentifier = entityName;%>
												</logic:empty>
													
												<logic:equal name="compoundListIter" property="codetableIdentifier" value="<%=codetableIdentifier%>">
													<bean:define id="mapKey" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
													<bean:define id="txtFieldName">newCodeMap(<%=mapKey%>)</bean:define>
													<html:select property="<%=txtFieldName%>"> 
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<% if ( !"pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit".equals( codetableIdentifier ) 
																&& !"TJJD_INDICATOR".equals( codetableIdentifier ) ) 
														{ %>
															<html:optionsCollection name="compoundListIter" property="list" value="codeId" label="description" />
														<% } else { %>
															<html:optionsCollection name="compoundListIter" property="activeList" value="codeId" label="description" />
														<% } %> %>
													</html:select>
													
													<!-- add validation for drop down (if required) -->
													<bean:define id="displayName" name="codetableHeaderIter" property="displayName" type="java.lang.String"/>
													<logic:equal name="codetableHeaderIter" property="required" value="true">
														<script type='text/javascript'>customValRequired('<%=txtFieldName%>','<bean:message key="errors.required" arg0="<%=displayName%>"/>');</script>
													</logic:equal>
												
													<logic:equal name="idx" value="0">
														<script type='text/javascript'>
  														//Focus on first text field
  														var focusControl = document.forms["codetableForm"].elements["<%=txtFieldName%>"];
														  if (focusControl.type != "hidden") 
															{
  															focusControl.focus();
														  }
														</script>
													</logic:equal>
												</logic:equal>
											
											</logic:iterate>
										</logic:equal>
										<%-- SIMPLE CODETABLE --%>
										<logic:notEqual name="codetableHeaderIter" property="compound" value="true">
											<bean:define id="mapKey" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
											<bean:define id="txtFieldName">newCodeMap(<%=mapKey%>)</bean:define>
											<bean:define id="displayName" name="codetableHeaderIter" property="displayName" type="java.lang.String"/>
											
											<logic:notEqual name="codetableHeaderIter" property="type" value="DATE">	
												<html:text property="<%=txtFieldName%>"/>
											</logic:notEqual>
											<logic:equal name="idx" value="0">
												<script type='text/javascript'>
												//Focus on first text field
												var focusControl = document.forms["codetableForm"].elements["<%=txtFieldName%>"];

												  if (focusControl.type != "hidden") {
													 focusControl.focus();
												  }
												</script>
											</logic:equal>
																			
											<logic:equal name="codetableHeaderIter" property="type" value="DATE">
											<html:text styleId="date" property="<%=txtFieldName%>"/>
											<script>
  												$( function() {
    											$( "#date" ).datepicker();
  												} );
  											</script>
												<%-- <% boolean dateCounter=true; %>
												<% int anchorCounter=0; %>
												<script type='text/javascript'>
													addMMDDYYYYDateValidation('<%=txtFieldName%>','<bean:message key="errors.date" arg0="<%=displayName%>"/>');
														<% if(dateCounter){ %>
														var cusVarCal = new CalendarPopup();
													cusVarCal.showYearNavigation();
													<%}
														dateCounter=false;
														 %>
												</script> --%>
												<%-- <A HREF="#" onClick="cusVarCal.select(((document.getElementsByName('<%=txtFieldName%>'))[0]),'anchor<%=anchorCounter%>','MM/dd/yyyy'); return false;" NAME="anchor<%=anchorCounter%>" ID="anchor<%=anchorCounter%>" border="0"><bean:message key="prompt.2.calendar"/></A> --%>
											</logic:equal>
											
											<logic:equal name="codetableHeaderIter" property="type" value="Double">
												<script type='text/javascript'>
													customValInteger('<%=txtFieldName%>','<bean:message key="errors.integer" arg0="<%=displayName%>"/>');
												</script>											
											</logic:equal>

											<logic:equal name="codetableHeaderIter" property="type" value="Integer">
												<script type='text/javascript'>
													customValFloat('<%=txtFieldName%>','<bean:message key="errors.float" arg0="<%=displayName%>"/>');
												</script>											
											</logic:equal>
											
    										<logic:equal name="codetableHeaderIter" property="numeric" value="true">
												<script type='text/javascript'>
													customValInteger('<%=txtFieldName%>','<bean:message key="errors.integer" arg0="<%=displayName%>"/>');
												</script>											
											</logic:equal>
											
											<logic:greaterThan name="codetableHeaderIter" property="maxLength" value="0">
												<bean:define id="maxLength" name="codetableHeaderIter" property="maxLength" type="java.lang.Integer"/>
												<script type='text/javascript'>
													customValMaxLength('<%=txtFieldName%>','<bean:message key="errors.maxlength" arg0="<%=displayName%>" arg1="<%=maxLength.toString()%>"/>', <%=maxLength.toString()%>);
												</script>
											</logic:greaterThan>
											<logic:greaterThan name="codetableHeaderIter" property="minLength" value="0">
												<bean:define id="minLength" name="codetableHeaderIter" property="minLength" type="java.lang.Integer"/>
												<script type='text/javascript'>
													customValMinLength('<%=txtFieldName%>','<bean:message key="errors.minlength" arg0="<%=displayName%>" arg1="<%=minLength.toString()%>"/>', <%=minLength.toString()%>);
												</script>
											</logic:greaterThan>
											<logic:equal name="codetableHeaderIter" property="required" value="true">
												<script type='text/javascript'>customValRequired('<%=txtFieldName%>','<bean:message key="errors.required" arg0="<%=displayName%>"/>');</script>
											</logic:equal>
										</logic:notEqual>
									</jims2:then>

									<jims2:else>
										<bean:define id="mapKey" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
										<jims2:if name="codetableHeaderIter" property="compound" value="true" op="equal">
											<jims2:then>
												<%String codetableIdentifier = "";%>
												<logic:notEmpty name="codetableHeaderIter" property="contextKey">
														<bean:define id="contextKey" name="codetableHeaderIter" property="contextKey" type="java.lang.String"/>
														<%codetableIdentifier = contextKey;%>
												</logic:notEmpty>
												<logic:empty name="codetableHeaderIter" property="contextKey">
														<bean:define id="entityName" name="codetableHeaderIter" property="entityName" type="java.lang.String"/>
														<%codetableIdentifier = entityName;%>
												</logic:empty>
												
												<bean:define id="codeIdExpr">newCodeMap(<%=mapKey%>)</bean:define>
												<bean:define id="codeId" name="codetableForm" property="<%=codeIdExpr%>" type="java.lang.String"/>
	
												<bean:define id="listFn">compoundListResponseEvent(<%=codetableIdentifier%>)</bean:define>
												<bean:define id="list" name="codetableForm" property="<%=listFn%>" 	type="messaging.codetable.reply.CodetableCompoundListResponseEvent"/>
												<bean:define id="txtFieldName">descriptionFromCode(<%=codeId%>)</bean:define>
												<bean:write name="list" property="<%=txtFieldName%>"/>
											</jims2:then>
											<jims2:else>
												<bean:define id="txtFieldName">newCodeMap(<%=mapKey%>)</bean:define>
												<nested:write property="<%=txtFieldName%>"/>
											</jims2:else>
										</jims2:if>
									</jims2:else>									
								</jims2:if>
							</td>
						</tr>
					</logic:notEqual>
				</logic:iterate>
            </table>
          </td>
        </tr>
      </table>

   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<br>
<table border="0" cellpadding=1 cellspacing=1 align=center>
	<tr>
		<td align='center'>
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="return validateCustomStrutsBasedJS(this.form);"><bean:message key="button.submit"/></html:submit>
			<input type="reset" value="Reset">
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
  </tr>

</table>


</html:form>
<span align=center><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

