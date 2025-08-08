<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/07/2007		AWidjaja Create JSP--%>

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

<%@ page import="messaging.codetable.reply.CodetableCompoundListResponseEvent" %>
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

<title><bean:message key="title.heading"/>&nbsp;Manage Code Table&nbsp;- codetableDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript'>
function changeFormAction(myForm, URL, doSubmit)
{
	myForm.action = URL;
	if(doSubmit)
	{
		myForm.submit();
	}
	return true;
}
</script>

</head>


<body topmargin=0 leftmargin='0'>

<html:form action="/handleCodeSelection" target="content"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr id='detailsHeading'> 
		<td align="center" class="header">Manage Code Table - Code Table Record Details
			<logic:equal name="opStatus" value="confirm">
				Confirmation
			</logic:equal>
			<logic:equal name="opStatus" value="summary">
				Summary
			</logic:equal>
  	</td> 
  </tr> 
  <%-- INACTIVATE IS FEATURE ON HOLD
  <tr id='deleteHeading' class='hidden'>
    <td align="center" class="header" >Manage Code Table - Inactivate Code Table Record
		
  		<script type="text/javascript">
  			if( action == 'deleteSummary' )
  				document.write( ' Summary' );
  			else if( action == 'deleteConf' )
  				document.write( ' Confirmation' );
  		</script>
		</td>
  </tr>
  --%> 
	<logic:equal name="opStatus" value="confirm">
		<tr> 
			<td align="center" class="confirm" >Code table record has been 
				<logic:equal name="codetableForm" property="opType" value="create">
					created.
				</logic:equal>
				<logic:equal name="codetableForm" property="opType" value="update">
					updated.
				</logic:equal>
			</td> 
		</tr> 
	</logic:equal>
    <tr id='deleteConfirmMessage' class='hidden'> 
		<td align="center" class="confirm" >Code table record has been inactivated.</td> 
    </tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<br> 
<table width="98%" border="0"> 
  <tr> 
		<td> 
			<ul>
        <logic:equal name="codetableForm" property="opType" value="view">
  				<li>Select the Update button to change the data for this Code Table Record.</li> 
  				<%--<li>Select the Inactivate button to inactive this Code Table entry.</li>--%> 
  			</logic:equal>

        <logic:equal name="opStatus" value="summary">
  				<li>Review the information and select the Finish button to save the information.</li> 
  				<li>Select the Back button to return to the previous screen to change information.</li> 
  				<li>Select the Cancel button to return to the Code Table Data List screen.</li> 
  			</logic:equal>

        <logic:equal name="opStatus" value="confirm">
  				<li>Select the Code Table Data List button to return to that screen.</li> 
  			</logic:equal>

			<ul id='deleteSummary' class='hidden'> 
				<li>Select the Inactivate button to inactivate the Code from the Code Table.</li> 
			</ul>
		</td> 
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%--BEGIN DETAIL TABLE--%> 
<div class='spacer'></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
		<td valign=top> 
			<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
				<tr> 
					<td class=detailHead>Code Table:&nbsp;<bean:write name="codetableForm" property="selectedCodeTable.codetableName"/></td> 
				</tr> 
				<tr> 
					<td valign=top align=center> 
						<table cellpadding="4" cellspacing="1" width='100%'>
							<logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
								<logic:notEqual name="codetableHeaderIter" property="audit" value="true">
									<tr>
										<td class='formDeLabel' width='1%' nowrap><bean:write name="codetableHeaderIter" property="displayName"/></td>
										<td class='formDe'>
											<logic:iterate id="dataIter" name="codetableForm" property="currentCode.valueMap">	
												<bean:define id="displayOrder" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
												<logic:equal name="dataIter" property="key" value="<%=displayOrder%>">
													<logic:equal name="codetableHeaderIter" property="compound" value="true">
														<%String codetableIdentifier = "";%>
														<logic:notEmpty name="codetableHeaderIter" property="contextKey">
																<bean:define id="contextKey" name="codetableHeaderIter" property="contextKey" type="java.lang.String"/>
																<%codetableIdentifier = contextKey;%>
														</logic:notEmpty>
														<logic:empty name="codetableHeaderIter" property="contextKey">
																<bean:define  id="entityName" name="codetableHeaderIter" property="entityName" type="java.lang.String"/>
																<%codetableIdentifier = entityName;%>
														</logic:empty>

														<bean:define id="codeId" name="dataIter" property="value" type="java.lang.String"/>
														<bean:define id="listFn">compoundListResponseEvent(<%=codetableIdentifier%>)</bean:define>
														<bean:define id="list" name="codetableForm" property="<%=listFn%>" 	type="messaging.codetable.reply.CodetableCompoundListResponseEvent"/>
														<bean:define id="txtFieldName">descriptionFromCode(<%=codeId%>)</bean:define>
														<bean:write name="list" property="<%=txtFieldName%>"/>
													</logic:equal>
													<logic:notEqual name="codetableHeaderIter" property="compound" value="true">
														<bean:write name="dataIter" property="value"/>
													</logic:notEqual>
												</logic:equal>
											</logic:iterate>	
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


<logic:equal name="codetableForm" property="opType" value="view">	  
	<br> 
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
		<tr> 
			<td valign=top> 
				<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue"> 
					<tr> 
						<td class=detailHead>Auditing Information</td> 
					</tr> 
					<tr> 
						<td valign=top align=center>
							<table cellpadding="4" cellspacing="1" width='100%'>
								<logic:iterate id="codetableHeaderIter" name="codetableForm" property="codetableAttributes">
									<logic:equal name="codetableHeaderIter" property="audit" value="true">
										<tr>
											<td class='formDeLabel' width='1%' nowrap><bean:write name="codetableHeaderIter" property="displayName"/></td>
											<td class='formDe'>
												<logic:iterate id="dataIter" name="codetableForm" property="currentCode.valueMap">	
													<bean:define id="displayOrder" name="codetableHeaderIter" property="displayOrder" type="java.lang.String"/>
													<logic:equal name="dataIter" property="key" value="<%=displayOrder%>">
														<logic:equal name="codetableHeaderIter" property="type" value="Date">
															<bean:write name="dataIter" property="value" formatKey="date.format.mmddyyyy"/>
														</logic:equal>
														<logic:notEqual name="codetableHeaderIter" property="type" value="Date">
															<bean:write name="dataIter" property="value"/>
														</logic:notEqual>
													</logic:equal>
												</logic:iterate>
											</td>
										</tr>
									</logic:equal>
								</logic:iterate>
							</table>
						</td>
					</tr> 
				</table> 
			</td>
		</tr>
	</table>
</logic:equal>
<%-- END DETAIL TABLE --%> 

<br> 
<table border="0" cellpadding=1 cellspacing=1 align=center> 
    <tr id='editButtons'> 
		<td align='center'>
			<logic:equal name="codetableForm" property="opType" value="view">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
				<jims2:isAllowed requiredFeatures='<%=Features.CF_UPDATE_CODETABLE%>'>
			        <html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
				</jims2:isAllowed>
		        <%--<input type=button value='Inactivate' onclick="goNav('codeTableDetails.htm?' + sourcePage + '&action=deleteSummary&sourcePage=' + sourcePage );">--%> </td> 
			</logic:equal>
		</td>
    </tr> 

    <tr id='summaryButtons'>
		<td align='center'>
			<logic:equal name="opStatus" value="summary">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
					<input type="submit" name="submitAction" value="<bean:message key="button.finish"/>" onclick="javascript:changeFormAction(this.form, '/<msp:webapp/>submitCodetableRecordUpdate.do', false);"> 
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
		</td>
	</tr> 

	<logic:equal name="opStatus" value="confirm">
	    <tr id='confButtons'> 
			<td align='center'> 
				<input type="submit" name="submitAction" value="<bean:message key="button.codeTableDataList"/>" onclick="javascript:changeFormAction(this.form, '/<msp:webapp/>submitCodetableRecordUpdate.do', false);">
				<input type="submit" name="submitAction" value="<bean:message key="button.returnToSearchResults"/>" onclick="javascript:changeFormAction(this.form, '/<msp:webapp/>submitCodetableRecordUpdate.do', false);">  
			</td> 
	    </tr> 
	</logic:equal>
	
    <tr id='deleteTypeSummary' class='hidden'> 
      <td nowrap align="center"> <input type=button value='Back' onclick="goNav('codeTableRecordDetails.htm?none&action=none&sourcePage=' + sourcePage);"> 
        <%--<input type=button value='Inactivate' onclick="goNav('codeTableDetails.htm?' + sourcePage + '&action=deleteConf&sourcePage=' + sourcePage);"> --%>
        <input type=button value="Cancel" onclick="goNav('codeTableDataList.htm?' + sourcePage + '&action=detail&sourcePage=' + sourcePage );" > </td> 
    </tr> 
</table> 
<%-- BEGIN NOTES TABLE --%> 
  
  <%-- END NOTES TABLE --%> 
</html:form> 
<span align=center><script type="text/javascript">renderBackToTop()</script></span> 
</body>
</html:html>
