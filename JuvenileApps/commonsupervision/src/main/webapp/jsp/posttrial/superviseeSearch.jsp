<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose	Create JSP -->
<!-- 10/19/2007  C Shimek		defect#46036 add cursor set -->
<!-- 04/22/2008  D Williamson	defect #50862 Fixed SPN field to only allow 8 numbers -->
<!-- 05/13/2008  L Deen		  	Removed CSCD from page title  -->
<!-- 06/20/2008  L Deen		  	Defect #52571 remove cancel button  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
<title><bean:message key="title.heading" /> - administerCasenotes/basicSuperviseeSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">
function renderSearch(theSelect){
	
		theSelectValue = theSelect.options[theSelect.selectedIndex].value;
		switch (theSelectValue)
		{
			case "SPN":
				document.getElementsByName("superviseeName.lastName")[0].value="";
				document.getElementsByName("superviseeName.firstName")[0].value="";
				document.getElementsByName("superviseeName.middleName")[0].value="";
				show("spSearchSPN", 1);
				show("spSearchSPNHeader", 1);
				show("caseSearchHeader",0,"row");
				show("caseSearchCaseNum",0,"row");
				show("caseSearchCDI",0,"row");
				show("spSearchNameHeader", 0);
				show("spSearchNameFirst", 0);
				show("spSearchNameMiddle", 0);
				show("spSearchNameLast", 0);
				document.getElementsByName("spn")[0].focus();
			break;

			case "NAME":
				document.getElementsByName("spn")[0].value="";
				show("spSearchSPN", 0);
				show("spSearchSPNHeader", 0);
				show("caseSearchHeader",0,"row");
				show("caseSearchCaseNum",0,"row");
				show("caseSearchCDI",0,"row");
				show("spSearchNameHeader", 1);
				show("spSearchNameFirst", 1);
				show("spSearchNameLast", 1);
				show("spSearchNameMiddle", 1);
				document.getElementsByName("superviseeName.lastName")[0].focus();
				break;

			case "CASE":
				document.getElementsByName("spn")[0].value="";
				show("spSearchSPN", 0);
				show("spSearchSPNHeader", 0);
				show("spSearchNameHeader", 0);
				show("spSearchNameFirst", 0);
				show("spSearchNameMiddle", 0);
				show("spSearchNameLast", 0);
				show("caseSearchHeader",1,"row");
				show("caseSearchCaseNum",1,"row");
				show("caseSearchCDI",1,"row");
				document.getElementsByName("caseNum")[0].focus();				
			break
		}
	
}
function validateSearch(theForm)
{	
	clearAllValArrays();
		var theSelect=document.getElementsByName("searchBy")[0];
		theSelectValue = theSelect.options[theSelect.selectedIndex].value;
		if (theSelectValue=="SPN"){
			customValRequired ("spn","SPN is required for search.","");
			customValMinLength("spn","SPN must be at least 3 characters.","3");
			customValMaxLength("spn","SPN cannot be more than 8 characters.","8");
			addNumericValidation("spn","SPN must be numeric.");
		}
		else if (theSelectValue=="CASE" && theForm.caseNum.value == "")
		{		
			customValRequired ("caseNum","Case number is required for search.","");
	   	}
		else if (theSelectValue=="CASE" && theForm.cdi.value == "")
		{		
			customValRequired("cdi","CDI is required for search.","");
	   	}
		else if (theSelectValue=="NAME") {
			customValRequired ("superviseeName.lastName","Last Name is required for search.","");
			customValMinLength("superviseeName.lastName","Last Name must be at least 2 characters.","2");
			addSearchFieldValidation("superviseeName.lastName","Last Name must consist of only alphanumeric characters and must be at least 2 characters.");
			//customValRequired ("superviseeName.firstName","First Name is required for search.","");
			customValMinLength("superviseeName.firstName","First Name must be at least 2 characters.","2");
			addSearchFieldValidation("superviseeName.firstName","First Name must consist of only alphanumeric characters and must be at least 2 characters.");
		}
		if(validateCustomStrutsBasedJS(theForm)){
			return true;
		}
		else{
			return false;
		}
   	return true;
}
</script> 

</head>
<body topmargin="0" leftmargin="0" onload="renderSearch(document.superviseeSearchForm.searchBy)">
<html:form action="/displaySuperviseeSearchResults" target="content" focus="spn">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|1">
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
						
							
						<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>							
									<td align="center" class="header">
										<bean:message key="title.CSCD" /> - <bean:message key="prompt.supervisee" />&nbsp;<bean:message key="title.search" />
									</td>						
								</tr>
							</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Select Search By to change the search type.</li>
										<li>Enter the required field(s) and click Submit to see results.</li>
									</ul></td>
								</tr>		
								<tr>
									<td class="required" colspan="2"><bean:message key="prompt.requiredFields"/>
									</td>												
								</tr>								
							</table>
						<!-- END INSTRUCTION TABLE -->																					
						<!-- BEGIN SEARCH TABLE -->									
							<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr>
									<td class="formDeLabel" width="1%" nowrap>
										<bean:message key="prompt.searchBy"/>
									</td>
									<td class="formDe">
										<html:select property="searchBy" onchange="renderSearch(this)">
											<html:option value="NAME" >NAME</html:option>
											<html:option value="SPN"><bean:message key="prompt.SPN"/></html:option>
											<html:option value="CASE" ><bean:message key="prompt.CASE"/></html:option>
										</html:select>
										&nbsp;&nbsp;&nbsp; <a href="/<msp:webapp/>displaySuperviseeSearch.do?submitAction=<bean:message key="button.advancedSuperviseeSearch"/>"><bean:message key="button.advancedSuperviseeSearch"/></a>
									</td>
									
								</tr>
							</table>
									<br>			
				   <!-- start search by spn -->
					 
			                <table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
			                 <tr id="spSearchSPNHeader" >
																	<td colspan="2" class="detailHead"><bean:message key="prompt.searchBySPN" /></td>
																</tr>
							  
			                  <tr id="spSearchSPN">
			                    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.SPN" /></td>
			                    <td class="formDe">
			                      <html:text property="spn" maxlength="8" size="8"/>
			                     </td>
			                  </tr>
			                  <tr id="caseSearchHeader">
									<td class="detailHead" colspan="2"><bean:message key="prompt.searchByCase" /></td>
								</tr>						 
		    
					
						
			                  <tr id="spSearchNameHeader">
																	<td colspan="2"  class="detailHead"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.name" /></td>
																</tr>
			                  <tr id="spSearchNameLast">
			                    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.lastName" /></td>
			                    <td class="formDe">
			                      <html:text property="superviseeName.lastName" maxlength="75" size="30"/></td>
			                  </tr>
			                  <tr id="spSearchNameFirst">
			                    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.firstName" /></td>
			                    <td class="formDe">
			                      <html:text property="superviseeName.firstName" maxlength="50" size="30"/>	</td>		                    
			                  </tr>
			                  
			                  <tr id="spSearchNameMiddle">
			                    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.middleName" /></td>
			                    <td class="formDe">
			                      <html:text property="superviseeName.middleName" maxlength="75" size="30"/></td>
			                  </tr>	
			                  
			                 <tr id="caseSearchCaseNum">
						       	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.case#" /></td>
								<td class="formDe"><html:text property="caseNum" maxlength="12" size="12"/></td>
							</tr>
						    <tr id="caseSearchCDI">
						      	<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.CDI" /></td>
								<td class="formDe"><html:text property="cdi" maxlength="3" size="3"/></td>
							</tr>
			                  		                  
							
			                 </table>
					  <input type="hidden" name="selectedValue" value="<%=UIConstants.BASIC%>"/>
					
                <!--end search by spn -->
							
							
							
							
							
							
								
						 <!-- END SEARCH TABLE -->									
							<br>
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return (validateSearch(this.form) && disableSubmit(this, this.form))"><bean:message key="button.submit"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
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