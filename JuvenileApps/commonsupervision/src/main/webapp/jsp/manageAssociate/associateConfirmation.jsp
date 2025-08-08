<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--03/04/2008	LDeen	Defect #49819 integrate help    --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - manageAssociate/associateConfirmation.htm</title>

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<!-- BEGIN FORM -->
<html:form action="/displayAssociatesList" target="content">
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		  </tr>
		  <tr>
		  <td valign="top">
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" >
			    <tr>
			      <td valign="top">
			      	<!--tabs start-->
			        <tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab"/>
					</tiles:insert>
			        <!--tabs end-->
			      </td>
			    </tr>
			    <tr>
			      <td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			    </tr>
			  </table>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			    <tr>
			      <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			    </tr>
			    <tr>
			
				    <td valign="top" align="center">
				    
					    <!--header area start-->
					    <table width="98%" border="0" cellpadding="0" cellspacing="0">
					      <tr>
					        <td bgcolor="#cccccc" colspan="2">
					        	<!--header start-->
								<tiles:insert page="../common/superviseeInfoForManageAssociateHeader.jsp" flush="true">
								</tiles:insert>
								<!--header end-->
					        </td>
					      </tr>
					      <tr>
					        <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					      </tr>
					    </table>
					    <!--header area end-->
					    
					    <!--casefile tabs start-->
					    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
					      <tr>
					        <td valign="top">
					        	<!--tabs start-->
								<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
									<tiles:put name="tab" value="AssociatesTab"/>
								</tiles:insert>		
								<!--tabs end-->
					        </td>
					      </tr>
					      <tr>
					        <td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					      </tr>
					    </table>
					    
					    <!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header">
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
														<bean:message key="prompt.create" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.confirmation" />										
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|8">
													</logic:equal>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
														<bean:message key="prompt.update" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.confirmation" />											
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|10">
													</logic:equal>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.DELETE%>">
														<bean:message key="prompt.delete" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.confirmation" />
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|9">									
													</logic:equal>
												</td>		
											</tr>
										</table>
										<!-- END HEADING TABLE -->
					    <table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
					      <tr>
					        <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					      </tr>
					      <tr>
						      <td valign="top" align="center">
								
								<!-- confirm start -->
					     		<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
									<tr>
									  <td class="confirm"> 
									  	<br />

									  	<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
											Associate successfully updated.
										</logic:equal>
										<logic:equal name="associateForm" property="action" value="<%=UIConstants.DELETE%>">
											Associate successfully deleted.
										</logic:equal>
										<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
											Associate successfully created.
										</logic:equal>

				           				<br/>
							            <br/>
				            		  </td>
				            	    </tr>
				          	    </table>
					            <!-- confirm end -->
							    
							    <br/>
							    
							    <!-- BEGIN BUTTON TABLE -->
							    <table border="0" width="100%">
							 		<tr>
								   		<td align="center">
									 	 <!-- <input type="button" value="Associates List"  onClick="goNav('associatesList.htm')">-->
									 	 <html:submit property="submitAction">
											<bean:message key="button.associatesList" />
										</html:submit> 
								   		</td>
									</tr>
							 	</table>
							 	<!-- END BUTTON TABLE -->	
						 	
						 	 </td>
				  		</tr>
					  </table>
					  <br/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
