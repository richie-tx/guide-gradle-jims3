<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Courts List-->
<!-- 08/08/2005		awidjaja	Create JSP -->
<!-- 03/12/2010	 Dawn Gibler - #64355 MSO - Allow Deselect of court on in-use condition -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<!--BEGIN HEADER TAG-->
<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>Common supervision - common/courtsList.jsp</title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head> 

<tiles:importAttribute name="beanName"/>
<tiles:useAttribute id="courtUpdateAllowed" name="courtUpdateAllowed" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->

<!--BEGIN BODY TAG-->

<input type="hidden" name="clearSelectedCourts" value="true">
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTable">
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="40%"><bean:message key="prompt.court" /> / <bean:message key="prompt.divisionName" /></td>
					<td class="formDeLabel" width="60%"><bean:message key="prompt.judgeDesc" /></td>
				</tr>
			</table>
			<logic:notEmpty name="beanName" property="courts">
			<bean:size id="crtSize" name="beanName" property="courts"/>
			<script>
				var courtCheckBoxArrPar= new Array(<%=crtSize%>);
				var courtCheckBoxArrChld= new Array(<%=crtSize%>);
				var counter=0;
			</script>
			</logic:notEmpty>
			<logic:iterate id="courtCategoryIter" name="beanName" property="courts">
			<table border="0" width="100%" cellspacing="0">
				<tr class="alternateRow">
					<td width="1%">
						<a href="javascript:showHide('list<bean:write name="courtCategoryIter" property="category"/>ParentExpand','','/<msp:webapp/>');" border="0">
							<img src="/<msp:webapp/>images/expand.gif" name="list<bean:write name="courtCategoryIter" property="category"/>ParentExpand" border="0">
						</a>
					</td>
					<td class="boldText">
						<bean:write name="courtCategoryIter" property="categoryDesc"/>
						<input type="checkbox" id="list<bean:write name="courtCategoryIter" property="category" />Parent" name="list<bean:write name="courtCategoryIter" property="category"/>Parent" value="test" <bean:write name="courtCategoryIter" property="isChecked"/> onclick="toggleCheckAll(this, '<bean:write name="courtCategoryIter" property="category"/>');">
					</td>
				</tr>
			</table>
			<span id="list<bean:write name="courtCategoryIter" property="category"/>ParentExpandSpan" class="hidden">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0">
							<%
								int RecordCounter = 0; 
								String bgcolor = ""; 
								RecordCounter = 0; 
								bgcolor = ""; %>
							
								<logic:iterate id="courtIter" name="courtCategoryIter" property="courts"> 
								<tr class= <% RecordCounter++;
									bgcolor = "alternateRow";                      
									if (RecordCounter % 2 == 1)
										bgcolor = "normalRow";
										out.print(bgcolor);  %>  >
								
									<td width="1%"></td>
									<td width="3%" class="boldText" align="center">
										<bean:write name="courtIter" property="courtNumber"/>
									</td>
									<td width="36%">
										<!-- this is a very rushed implementation -->
										<bean:define id="courtNo" name="courtIter" property="courtId" type="java.lang.String"/>
										<bean:define id="foundCourtVar" value="false" type="java.lang.String"/>
										<bean:define id="courtInUseVar" value="false" type="java.lang.String"/>
										<logic:notEmpty name="beanName" property="selectedCourts">
											<logic:iterate id="selCourtsCategoryIter" name="beanName" property="selectedCourts">
												<logic:iterate id="selCourtsIter" name="selCourtsCategoryIter" property="courts">
													<logic:equal name="selCourtsIter" property="courtId" value="<%=courtNo%>">
														<%foundCourtVar="true"; %>
														<logic:present name="courtUpdateAllowed">
															<logic:equal name="courtUpdateAllowed" value="false">
																<logic:equal name="selCourtsIter" property="courtInUse" value="true">
														 			<%courtInUseVar="true"; %>
																</logic:equal>
															</logic:equal>
														</logic:present>
														<logic:notPresent name="courtUpdateAllowed">
															<logic:equal name="selCourtsIter" property="courtInUse" value="true">
														 		<%courtInUseVar="true"; %>
															</logic:equal>
														</logic:notPresent>
													</logic:equal>
												</logic:iterate>
											</logic:iterate>
										</logic:notEmpty>
										
										<% if(foundCourtVar.equals("true")){%>
											<%if(!courtInUseVar.equals("true")){ %>
												<input type="checkbox" name='<bean:write name="courtCategoryIter" property="category"/>' checked value='<bean:write name="courtIter" property="courtId"/>' <bean:write name="courtIter" property="isSelected"/> onclick="parent(this,'list<bean:write name="courtCategoryIter" property="category"/>Parent');"/>
											<% }else{ %>
											<input type="hidden" name="aCheckBoxIsSelected" value="true"/>
											<input type="hidden" name='<bean:write name="courtCategoryIter" property="category"/>' value='<bean:write name="courtIter" property="courtId"/>' />
													<img src="/<msp:webapp/>images/green_check.gif" title=Selected alt="Pre checked" hspace=3 vspace=0>
											<%} %>
										<%}else{ %>
										  <input type="checkbox" name='<bean:write name="courtCategoryIter" property="category"/>' value='<bean:write name="courtIter" property="courtId"/>' <bean:write name="courtIter" property="isSelected"/> onclick="parent(this,'list<bean:write name="courtCategoryIter" property="category"/>Parent');"/>
										<%} %>
										
										
									</td>
									<td width="60%">
										<logic:notEmpty name="courtIter" property="judgeLastName">
											<bean:write name="courtIter" property="judgeLastName"/>
											<logic:notEmpty name="courtIter" property="judgeFirstName">
												, <bean:write name="courtIter" property="judgeFirstName"/>
											</logic:notEmpty>
										</logic:notEmpty>
										<bean:write name="courtIter" property="description"/>
										
									</td>
									
								</tr>
								</logic:iterate>
							</table>
						</td>
					</tr>
				</table>
			</span>
			<script>
			reverseToggleCheckAll('list<bean:write name="courtCategoryIter" property="category"/>Parent', '<bean:write name="courtCategoryIter" property="category"/>');
			courtCheckBoxArrPar[counter]='list<bean:write name="courtCategoryIter" property="category"/>Parent';
			courtCheckBoxArrChld[counter]='<bean:write name="courtCategoryIter" property="category"/>';
			counter=counter+1;
			</script>
			</logic:iterate>
		</td>
	</tr>
</table>
	<!-- END FORM -->

