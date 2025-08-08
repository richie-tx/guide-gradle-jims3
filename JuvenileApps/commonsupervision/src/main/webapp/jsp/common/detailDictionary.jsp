<!DOCTYPE HTML>
<!-- Manages Tabs for Manage Features-->
<!-- 07/29/2005		awidjaja	Create JSP -->
<%-- 10/13/2015  RYoung          - #30612 MJCW: IE11 conversion of Common Supervision link on UILeftNav (UI)--%>

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
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
	<html:base />
	<title>Common Supervision - common/detailDictionary.jsp</title>
	<SCRIPT TYPE="text/javascript">
	function submitenter(form,e)
	{
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;
		
		if (keycode == 13)
			{
				var filterButton2 = document.getElementById("filterButton");
				filterButton2.click()
				return false;
			}
		else
			return true;
	}
	</SCRIPT>
</head> 

<%int RecordCounter = 0; 
  String bgcolor = ""; 
  String dictionaryClass = ""; %> 
  
<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0">

<tiles:useAttribute id="actionPath" name="actionPath" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="includedIn" name="includedIn" ignore="true" />
<tiles:useAttribute id="isSpecialCondition" name="isSpecialCondition" ignore="true" />
<tiles:importAttribute name="ddbean"/>


<logic:notEmpty name="ddbean" property="detailDictionary">
	
</logic:notEmpty>	

	<table width="100%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
		<tr>
			<td class="detailHead">Detail Dictionary</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="2">
					<tr>
						<td class="formDeLabel">Name</td>
						<td class="formDe">
							<html:text name="ddbean" property="ddName" onkeypress="return submitenter(this,event)"/>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel">
							Description
						</td>
						<td class="formDe">
							<!--<input name="ddDescription" type=text style=width:100%>-->
							<html:text name="ddbean" property="ddDescription" style="width:100%" onkeypress="return submitenter(this,event)"/>
						</td>
					</tr>
					<tr>
					<td class="formDeLabel">Type</td>
					<td class="formDe">
					
					<logic:equal name="isSpecialCondition" value="true">
					<html:hidden name="ddbean" property="ddType"  value="Variable"/>
						Variable
					</logic:equal>

					<logic:notEqual name="isSpecialCondition" value="true">
						<html:select name="ddbean" property="ddType" onkeypress="return submitenter(this,event)">	
							<html:option value="All">All</html:option>
							<html:option value="Reference">Reference</html:option>
							<html:option value="Variable">Variable</html:option>
						</html:select>
					</logic:notEqual>
						
					</td>									
				</tr>
					<tr>
						<td class="formDe">
						</td>
						<td class="formDe">
							<!--<input type=button value=Search onclick="filter(this.form);">-->
							<html:submit styleId="filterButton" property="submitAction" onclick="return filterDetailDictionary(this.form)"><bean:message key="button.filter"/></html:submit>
							<html:submit property="submitAction" onclick="resetTarget(this.form);"><bean:message key="button.showAll"/></html:submit>
						</td>
					</tr>
				</table>
				<bean:size id="dictionarySize" name="ddbean" property="filteredDetailDictionary"/>
				<logic:greaterThan name="dictionarySize" value="20">
					<logic:equal name="includedIn" value="condition">						
						<logic:equal name="supervisionConditionForm" property="inUse" value="true">
						<div class="scrollingDiv400">
						</logic:equal>
						<logic:equal name="supervisionConditionForm" property="inUse" value="false">
						<div class="scrollingDiv600">
						</logic:equal>				
					</logic:equal>					
					<logic:equal name="includedIn" value="courtPolicy">
						<div class="scrollingDiv400">
					</logic:equal>
				</logic:greaterThan>
				<logic:lessEqual name="dictionarySize" value="20">
					<div class="scrollingDiv200">
				</logic:lessEqual>
				
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<tr colspan="2" height="100%">
							<td class="formDeLabel">
								Name
							</td>
							<td class="formDeLabel"  width='1%' nowrap="nowrap">
								Description
							</td>
						</tr>
						
						<logic:iterate id="detailDictionaryIter" name="ddbean" property="filteredDetailDictionary">
							<logic:equal name="isSpecialCondition" value="true">
								<logic:equal name="detailDictionaryIter" property="isReference" value="false">
									<tr 
											<% RecordCounter++;
											dictionaryClass = "detailDictionaryName";
											if (RecordCounter % 2 == 1)
											{
												out.println("bgcolor=#f0f0f0");
												dictionaryClass = "detailDictionaryNameAlternateRow";
											}
											%>								
									height="100%"><!-- ending TR tag -->
									
										<td>
											<b><u>{{<bean:write name="detailDictionaryIter" property="name"/>}}</u></b>
										</td>
										<td><bean:write name="detailDictionaryIter" property="description"/></td>
									</tr>
								</logic:equal>		
							</logic:equal>	
							<logic:equal name="isSpecialCondition" value="false">
								<tr 
									<logic:equal name="detailDictionaryIter" property="isReference" value="true">	
										class="referenceField"
									</logic:equal>
									<logic:equal name="detailDictionaryIter" property="isReference" value="false">
										<% RecordCounter++;
										dictionaryClass = "detailDictionaryName";
										if (RecordCounter % 2 == 1)
										{
											out.println("bgcolor=#f0f0f0");
											dictionaryClass = "detailDictionaryNameAlternateRow";
										}
										%>								
									</logic:equal>		
											
								
								height="100%"> <!-- ending TR tag -->
								
									<td>
											<logic:equal name="detailDictionaryIter" property="isReference" value="true">	
												<b><u>[<bean:write name="detailDictionaryIter" property="name"/>]</u></b>
											</logic:equal>
											<logic:equal name="detailDictionaryIter" property="isReference" value="false">	
												<b><u>{{<bean:write name="detailDictionaryIter" property="name"/>}}</u></b>
											</logic:equal>
										
										
									</td>
									<td><bean:write name="detailDictionaryIter" property="description"/></td>
								</tr>
							</logic:equal>	
						</logic:iterate>
					</table>
				</div>
			</td>
		</tr>
	</table>

</body>

</html:html> 