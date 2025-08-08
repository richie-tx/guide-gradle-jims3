<!DOCTYPE HTML>
<%-- Used in listing journal entries for a casefile --%>
<%-- TODO - NEEDS TO BE CONVERTED TO LATER VERSION OF JQUERY DURING CONVERSION --%>
<%--MODIFICATIONS --%>
<%-- 04/30/2008	Uma Gopinath Created JSP --%>
<%-- 10/30/2008	C Shimke       defect#55169 added js for Created By section refresh button and removed unneeded onclick from reset button at bottom of page --%>
<%-- 08/31/2015	RCarter	   #29425 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">


<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<html:base />

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/journal.js"></script>

<title><bean:message key="title.heading"/> - journalSearch.jsp</title>
<html:javascript formName="journalSearchForm"/>

<script type="text/javascript">

function checkFind()
{
	if(document.forms[0].lastName.value == "")
	{
		if(document.forms[0].firstName.value != "")
		{
			alert("Last Name is required to use first name search.");
			document.forms[0].lastName.focus();
			return false;
		}
		
		if(document.forms[0].middleName.value != "")
		{
			alert("Last Name is required to use middle name search.");
			document.forms[0].lastName.focus();
			return false;
		}
		alert("Last Name is required for name search.");
		document.forms[0].lastName.focus();

		return false;
	}

	return true ;
}

function checkSubmit(theForm)
{	
	var chk1 = compareDates(document.forms[0].fromDate.value,'<bean:message key="date.format.mmddyyyy"/>',document.forms[0].endDate.value,'<bean:message key="date.format.mmddyyyy"/>');
	var date1 = getCurrentDate();
	
	if( chk1 == 1)	
	{
		alert("From date cannot be later than the To date.");
		document.forms[0].fromDate.focus();
		return false;
	}
	else
		spinner();
		
	//return validateJournalSearchForm(theForm);
}

function resetCreatedBySection()
{
	var rb = document.getElementsByName("searchType");
	
	if (rb.length > 0)
	{
		for (x = 0; x < rb.length; x++)
		{
			rb[x].checked = false;
		}		
	}	

	document.forms[0].lastName.value = "";
	document.forms[0].firstName.value= "";
	document.forms[0].middleName.value = "";	
}
</script>

</head> 
<%--END HEAD TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileCasefileJournalSearch" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|199">
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - Search Journal Entries</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Search by Journal Category (specific or All) or Date/Date Range or Created By or all sections.</li>
        <li>Enter 'Created by' search criteria as required and select a 'Name search is of type' radio.</li>       
      </ul>
    </td>
  </tr>
 <tr>
		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction" /></td>
  </tr>  

</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN Juv Header TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader"/>
	</tiles:insert>
<!-- End Header TABLE -->


<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>

      <!-- Casefiles (1st row) tabs start -->
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="casefiledetailstab"/>
        <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>		
      <!-- Casefiles tabs end -->

  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign='top' align='center'>
				
            <!-- Begin Casefile info (2nd row) Tabs -->
						<div class='spacer'></div>
        		<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
   						<tr>
   							<td valign='top'>
   								<!-- tabs start -->
   								<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  									<tiles:put name="tabid" value="journaltab"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  								</tiles:insert>	
   								<!-- tabs end -->
     						</td>
     					</tr>
     				  <tr>
       			 		<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
      				</tr>
      			</table>
            <!-- End Casefile info (2nd row) Tabs -->
				
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
            	<tr>
                <td valign='top' align='center'>

       						<!-- start Search table -->
      						<div class='spacer'></div>
        					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
        						<tr> <!-- table title bar -->
        							<td valign='top' colspan='2' class='detailHead'>Journal Category Search</td>
        						</tr>
        						<tr>
        							<td colspan='2'>
											  <!-- begin search criteria section -->
     										<table width='100%' border="0" cellpadding="2" cellspacing="1">
	        								<tr>
	        									<td width='1%' nowrap='nowrap' class='formDeLabel'>&nbsp;Journal Category</td>
	        									<td class="formDe">
	        									<html:select styleId="journalCategory" name="journalForm" property="journalCategoryCd" size="1" >
					        				<html:option value="">Please Select</html:option>
					        					<jims2:codetable codeTableName="JOURNAL_CATEGORY" />
					        				</html:select>
	        									</td>
	        								</tr>
	        								<tr>
	    										  <td colspan='2'>
	                            <table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
	                            	 <tr>
	                                 	<td colspan='4' class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredForThisSection" /></td>								                               
	                             	</tr>
	                              	<tr>
	                                	<td colspan='4' class='detailHead'> Date or Date Range</td>
	                              	</tr>
           							<tr>
              								<td valign='top' class="formDeLabel" width='5%'>From</td>
   											<td valign='top' class="formDe">																                                   
                            					<html:text name="journalForm" property="fromDate" size="10" maxlength="10" styleId="fromDate"/>&nbsp;
				      						</td>
		             						<td valign='top' class="formDeLabel" width='5%'>To</td>
		   									<td valign='top' class="formDe">
		   										<html:text name="journalForm" property="endDate" size="10" maxlength="10" styleId="endDate"/>&nbsp;
			      						 	</td>
           							</tr>
	              				</table>
	            				</td>
	            				</tr>
	            				<tr>
	    							<td colspan='2'>
	                            <table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
	                              <tr>
				                         <td colspan='4' class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredForThisSection" /></td>								                               
					              </tr>
					              <tr><td colspan='4' align="left">+ indicates Last Name is required to use this search field.</td></tr>
	                               <tr>
	                                 <td colspan='4' class='detailHead'> Created by</td>
	                               </tr>
	                               <tr>
	                                 <td class="formDeLabel" nowrap='nowrap'>Name search is of type</td>                                 
	                                 <td class="formDe" colspan='3'>
										<html:radio name="journalForm" property="searchType" value="jpoNameSearchType" styleId="searchType"/>JPO Name
										<html:radio name="journalForm" property="searchType" value="caseloadManagerNameSearchType" styleId="searchType"/>Caseload Manager Name
									</td>
	                               </tr>
	                               <tr>
	                                 <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/>&nbsp;Last Name</td>
	                                 <td class="formDe" colspan='3'><html:text name="journalForm" property="lastName" size="30" maxlength="100"/></td>
	                               </tr>
	                               <tr>
	                                 <td class="formDeLabel" nowrap='nowrap'>+First Name</td>
	                                 <td class="formDe" colspan='3'><html:text name="journalForm" property="firstName" size="30" maxlength="100"/></td>
	                               </tr>
	                               <tr>
	                                 <td class="formDeLabel" nowrap='nowrap' width='1%'>+Middle Name</td>
	                                 <td class="formDe" colspan='3'><html:text name="journalForm" property="middleName" size="30" maxlength="100"/></td>
	                               </tr>
	                 
	                               <tr>
	                                 <td class="formDeLabel"></td>
	                                 <td class="formDe" colspan='3'>
	                 					<html:submit property="submitAction" onclick="return validateRadios(null, 'searchType', 'Name search type is required.  Select either JPO or Caseload Manager.') && checkFind();"><bean:message key="button.find"/></html:submit>
	                 					<input type="button" value="Refresh" name="refresh" onClick="resetCreatedBySection();">
	                 				</td>
	                               </tr>
                              </table>
      						</td>
      					</tr>
          			</table>
											  <!-- end search criteria section -->
        							</td>
        						</tr>
        						<logic:notEqual name="journalForm" property="searchType" value="">
        						<tr align="center">
									<td colspan='6' align="left"> <bean:write name="journalForm" property="listCount" /> search results found.</td>
								</tr>
     						<tr>
				        	<td>
			       	      <table width='100%' cellpadding="2" cellspacing="1" class="borderTableBlue">
			                <tr>
			                   <td class="detailHead" colspan='7'><bean:message key="prompt.searchResults" /></td>
			                 </tr>

			                 <logic:empty name="journalForm" property="nameSearchResults">
	    									<tr bgcolor="#cccccc">
													<td colspan='6' class="subHead"  align="left">No name(s) found.</td>
												</tr>
		     							 </logic:empty>

	     							 	<logic:notEmpty name="journalForm" property="nameSearchResults">
											  <%-- static header title row --%>
                 							<tr bgcolor='#cccccc' class='subhead'>
														<td width='1%'> </td> <%-- holder for radio button column --%>
														<td  align="left">
															<bean:message key="prompt.officerName" />
															<jims2:sortResults beanName="journalForm" results="nameSearchResults" primaryPropSort="lastName" primarySortType="STRING" defaultSort="true" secondPropSort="firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
														</td>
														<td  align="left">
															<bean:message key="prompt.otherIdNumber" />
															<jims2:sortResults beanName="journalForm" results="nameSearchResults" primaryPropSort="otherIdNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
														</td>
														<td  align="left">
															<bean:message key="prompt.badge#" />
															<jims2:sortResults beanName="journalForm" results="nameSearchResults" primaryPropSort="badgeNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
														</td>
														<td  align="left">
															<bean:message key="prompt.userId" />
															<jims2:sortResults beanName="journalForm" results="nameSearchResults" primaryPropSort="userId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
														</td>
														<td  align="left">
															<bean:message key="prompt.status" />
															<jims2:sortResults beanName="journalForm" results="nameSearchResults" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
														</td>
													</tr>
													
													<logic:iterate id="namesIndex" name="journalForm" property="nameSearchResults" indexId="index">
														<bean:define id="userId" name="namesIndex" property="userId" type="java.lang.String"/> 
														<pg:item>
															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																<td ><html:radio name="journalForm" property="selectedOfficerId" value='<%=userId%>'/></td>
																<td><bean:write name="namesIndex" property="lastName" />, <bean:write name="namesIndex" property="firstName" /> <bean:write name="namesIndex" property="middleName" /></td>
																<td><bean:write name="namesIndex" property="otherIdNum"/></td>
																<td><bean:write name="namesIndex" property="badgeNum"/></td>
																<td><bean:write name="namesIndex" property="userId"/></td>  <!-- badge number -->
																<td><bean:write name="namesIndex" property="status"/></td>                  					
															</tr>
														</pg:item>
													</logic:iterate>
												</logic:notEmpty> 
				              </table>
				             </td>
					         </tr>
				         </logic:notEqual>
						   </table>
    							<!-- end search table -->

                	<!-- begin button table -->
                  <div class='spacer'></div> 
              		<table border="0" cellpadding='1' cellspacing='1' align='center'>
              		  <tr>
                      <td align="center">
                       <html:submit property="submitAction" styleId="submit" onclick="return checkSubmit(this.form);"><bean:message key="button.submit"/></html:submit>                       
                        <input type="reset" value='Refresh' name='Refresh'>
                        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>    
                      </td>
              		  </tr>
              		</table>
              		<!-- end button table -->
                </td>
              </tr>
            </table><div class='spacer'></div>
      		</td>
      	</tr>
      </table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>
</html:html>
