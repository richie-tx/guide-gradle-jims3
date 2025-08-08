<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/20/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - processSupervisionOrder/defendantSignatureSummary.jsp</title>

class=formDeLabel
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
 
</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionOrderDefendantSignature" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<bean:message key="title.defendantSignatureAcquired" />&nbsp;-&nbsp;
										<logic:notEqual name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|35">
											<bean:message key="prompt.summary" />
										</logic:notEqual>
										<logic:equal name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|36">
											<bean:message key="prompt.confirmation" />
										</logic:equal>
									</td>
								</tr>					
								<logic:equal name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">																	
										<tr>
										<td align="center" class="confirm"> Defendant Signature Acquired information saved.</td>
										</tr>
									</logic:equal>	 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align="center">															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
									<logic:notEqual name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
										<li>Click Finish to continue.</li>
									</logic:notEqual>
									<logic:equal name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
									<li>Click the appropriate button below.</li>
									</logic:equal>
									</ul></td>
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->                      
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							<br>
						<!-- BEGIN ORDER PRESENTATION TABLE -->                      
							<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
								<tr class=detailHead>
									<td class=detailHead><bean:message key="prompt.defendantSignature" /></td>
								</tr>
								<tr>
									<td colspan=2>
										<table width=100% cellpadding=2 cellspacing=1 border=0>				                        		
											<tr>				                          	
												<td class=formDeLabel><bean:message key="prompt.signedDate" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="signedDateAsString" /></td>
											</tr>
											<tr>
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.defendantSignature" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="defendantSignature" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						<!-- END ORDER PRESENTATION TABLE -->
							<br>                     
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">											
								<tr>
									<td align="center">
									<logic:notEqual name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp; 
									
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</logic:notEqual>
										<logic:equal name="supervisionOrderForm" property="action" value="defendentSigAquiredConfirmation">	
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToCaseOrderSearchResults"></bean:message></html:submit>&nbsp;
										</logic:equal>
									</td>
								</tr>											
							</table>
						<!-- END BUTTON TABLE -->
               		</td>
            	</tr>
         	</table>
        </td>
	</tr>
</table>  
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>