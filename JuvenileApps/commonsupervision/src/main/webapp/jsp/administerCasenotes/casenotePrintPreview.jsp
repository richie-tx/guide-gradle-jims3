<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/03/2010	 CShimek  - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>

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
<title><bean:message key="title.heading" /> - administerCasenotes/casenotePrintPreview.jsp</title>

</head>
<body topmargin="0" leftmargin="0">
<bean:define id="webPath" type="java.lang.String">/<msp:webapp/></bean:define>
<html:form action="/displayAdministerCasenotesJournal" target="content">
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
    		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  		</tr>
  		<tr>
			<td valign="top" align="center">
<!-- BEGIN TITLE TABLE -->
				<table width="100%">
					<tr>							
						<td align="center" class="header"><U>CASENOTES</U></td>
					</tr>
				</table>
<!-- END TITLE TABLE -->				
			</td>
		</tr>
		<tr>
			<td valign="top" align="center">			
<!-- BEGIN HEADING TABLE -->					
				<table width="100%" cellpadding="2" cellspacing="0" border="1" borderColor="black">
					<tr>	
						<td class="formDeLabel" colspan="6">Supervisee Information</td>
					</tr>
					<tr>
						<td class="headerLabel"><bean:message key="prompt.name"/></td>
						<td colspan="5" class="headerData">
							<msp:formatter name="superviseeInfoHeaderForm" property="superviseeName" format="L, F M"/>
						</td>
					</tr>
					<tr>
						<td class="headerLabel"><bean:message key="prompt.officer"/></td>
						<td colspan="5" class="headerData"><msp:formatter name="superviseeInfoHeaderForm" property="officerName"  format="L,F"/></td>
					</tr>
					<tr>
						<td class="headerLabel"><bean:message key="prompt.SPN"/></td>
						<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="spn"/>&nbsp;</td>
						<td class="headerLabel"><bean:message key="prompt.CON"/></td>
						<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="con"/>&nbsp;</td>
						<td class="headerLabel"><bean:message key="prompt.dob"/></td>
						<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="dateOfBirthAsStr"/>&nbsp;</td>
					</tr>
					<tr>
						<td class="headerLabel" width="17%"><bean:message key="prompt.race"/></td>
						<td class="headerData" width="17%"><bean:write name="superviseeInfoHeaderForm" property="race"/>&nbsp;</td>
						<td class="headerLabel" width="17%"><bean:message key="prompt.sex"/></td>
						<td class="headerData" width="17%"><bean:write name="superviseeInfoHeaderForm" property="sex"/>&nbsp;</td>
						<td class="headerLabel" width="17%"><bean:message key="prompt.programUnit"/></td>
						<td class="headerData" width="25%"><bean:write name="superviseeInfoHeaderForm" property="unit"/>&nbsp;</td>
					</tr>
					<tr>
						<td class="headerLabel">Next <bean:message key="prompt.contact"/></td>
						<td class="headerData">
							<bean:write name="superviseeInfoHeaderForm" property="nextContactDate" formatKey="date.format.mmddyyyy"/>&nbsp;
							<bean:write name="superviseeInfoHeaderForm" property="nextContactTime" formatKey="time.format.hhmma"/>
						</td>
						<td class="headerLabel"><bean:message key="prompt.contact"/></td>
						<td class="headerData" ><bean:write name="superviseeInfoHeaderForm" property="contactMethod"/>&nbsp;</td>					
						<td class="headerLabel">&nbsp;</td>
						<td class="headerData" bgcolor="#cccccc">&nbsp;</td>
					</tr>
					<tr>
						<td class="headerLabel"><bean:message key="prompt.court"/></td>
						<td class="headerData">
							<nest:nest property="searchCasenote">
								<nest:iterate id="casesList" property="casesList">
									<div><nest:write property="court"/></div>
								</nest:iterate>	
							</nest:nest>&nbsp;
						</td>
						<td class="headerLabel"><bean:message key="prompt.case"/></td>
						<td class="headerData">
							<nest:nest property="searchCasenote">
								<nest:iterate id="casesList" property="casesList">
									<div><nest:write property="caseNum"/></div>
								</nest:iterate>	
							</nest:nest>&nbsp;
						</td>
						<td class="headerLabel"><bean:message key="prompt.supervisionPeriod"/></td>
						<td class="headerData">
							<nest:nest property="searchCasenote">
								<nest:iterate id="casesList" property="casesList">
									<div><nest:write property="supPeriodId"/></div>
								</nest:iterate>	
							</nest:nest>&nbsp;
						</td>
					</tr>
				</table>
<!-- END HEADING TABLE -->	
			</td>
		</tr>
		<tr>
			<td align="center">	
<!-- BEGIN CASENOTE DISPLAY -->
	 			<nest:nest property="searchCasenote">
					<nest:empty property="casenoteResults">
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="detailHead">No Casenotes to Display</td>
							</tr>
						</table>
					</nest:empty>
										
					<nest:notEmpty property="casenoteResults">
						<nest:iterate id="casenoteList" property="casenoteResults" indexId="index">		
							<div class="spacer4px"></div> 
							<thead width="98%" border="1" cellpadding="0" cellspacing="0" borderColor="black">
								<table class="<%out.print((index.intValue() == 0) ? "visible" : "hidden"); %>">		
									<tr>							
										<td align="center" class="header"> <%out.print((index.intValue() == 0) ? "" : ""); %></td>
									</tr>
								</table>
								<table width="98%" border="1" cellpadding="0" cellspacing="0" borderColor="black" >	
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formDeLabel">										
											<tr>
												<td class="formDelabel"><bean:message key="prompt.date"/></td>
												<td class="formDelabel"><bean:message key="prompt.time"/></td>
												<td class="formDelabel"><bean:message key="prompt.subject"/></td>
												<td class="formDelabel"><bean:message key="prompt.contactMethod"/></td> 
												<td class="formDelabel">Author</td>
											</tr>
											<tr bgcolor="#FFFFFF">
												<td>
										 			<nest:write property="casenoteDate" formatKey="date.format.mmddyyyy" /> 
												</td>
												<td>	
													<nest:write property="casenoteDate" formatKey="time.format.hhmma" /> 
												</td>	
												<td><nest:write property="subjects"/></td>
												<td><nest:write property="contactMethod"/></td>
												<td>
													<nest:equal  property="generatedById" value="MI">
												       <nest:write property="migrateCreator"/>
												   	</nest:equal>
													<nest:notEqual property="generatedById" value="MI">
														<nest:write property="createdByName.formattedName"/>
													</nest:notEqual>
											 		<nest:equal property="autoSaved" value="true">
														<img src="../../images/clip_image001.gif" title="This casenote was autosaved">
													</nest:equal>  
												</td>
											</tr>
											<tr bgcolor="#FFFFFF">
												<td colspan="5">
										 		    <nest:write property="collateralInfo" filter="false" /> 
													<nest:write property="casenoteText" filter="false" />
												</td>
											</tr>				
										</table>
									</td>
								</tr>
								</table>
							</thead>
						 	<table  width="98%" align="center" style="page-break-after: always" >
								<tr>
									<td></td>
								</tr>
							</table> 
						</nest:iterate>
					</nest:notEmpty>
				</nest:nest> 
<!-- END CASENOTE DISPLAY -->			
			</td>	
		</tr>
	</table					
<!-- END  TABLE -->
</div>
</html:form>
</body>
</html:html>