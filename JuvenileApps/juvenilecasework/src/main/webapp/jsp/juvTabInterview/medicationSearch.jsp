<!DOCTYPE HTML>
<!-- User selects the "Add Medication" button on Medical List page -->
<%--MODIFICATIONS --%>
<%--05/05/07 Uma Gopinath CREATE JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>



<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - medicationSearch.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/medical.js"></script>
<!-- Javascript for emulated navigation -->
<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script>

/* function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedMed");
	if (rbs.length == 1){
		rbs[0].checked = true;
		show( 'select', 1 , "row") ;
		show( 'noselect', 0 , "row") ;
	}
}

function clearSelects()
{
	for (x=0; x< document.forms[0].length; x++)
	{
		if (document.forms[0].elements[x].type == "radio")
		{
			document.forms[0].elements[x].checked = false;
		}	
	}		
} */

/* function displaySelectButton()
{
	//alert(document.getElementById("select").class);
	show( 'select', 1 , "row") ; 
	show( 'noselect', 0 , "row") ; 		
} */
/* function checkFields()
{
var theForm = document.forms[0];
var medication = theForm['searchMedication.medication'].value;
var delivery = theForm['searchMedication.delivery'].value;
var strength = theForm['searchMedication.strength'].value;
var medicationCode = theForm['searchMedication.medCode'].value;
var usage = theForm['searchMedication.usage'].value;
	if(theForm['searchMedication.medication'].value=="" && theForm['searchMedication.delivery'].value=="" && theForm['searchMedication.strength'].value==""
	 && theForm['searchMedication.medCode'].value=="" && theForm['searchMedication.usage'].value=="")
	 {
	 	alert("At least one field is required for search");
	 	theForm['searchMedication.medication'].focus;
	 	return false;
	}
	
	if(medication.indexOf("%") != -1){
		alert('Invalid Medication.Please use * for partial search');
		return false;
	}
	if(delivery.indexOf("%") != -1){
		alert('Invalid Delivery.Please use * for partial search');
		return false;
	}
	if(strength.indexOf("%") != -1){
		alert('Invalid Strength.Please use * for partial search');
		return false;
	}
	if(medicationCode.indexOf("%") != -1){
		alert('Invalid Medication Code.Please use * for partial search');
		return false;
	}
	if(usage.indexOf("%") != -1){
		alert('Invalid Usage.Please use * for partial search');
		return false;
	}
	return true;
} */
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|2">
<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header">Juvenile Casework - Juvenile Profile - Search Medication</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE  -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE  -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width=100%>
	<tr>
    	<td>
            <ul>
                <li>Enter search criteria and then select "Find Medications" button to perform search.</li>
				<li>Select Medication and then click "Select" button.</li>
            </ul>
		</td>
	</tr> 
  <tr>
    <td class="required">&nbsp;At least one field is required for search.</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<html:form action="/displayMedicalMedicationSearchResults"  target="content">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>	
		<td width=98% align=center valign=top>
			<table class=borderTableBlue cellpadding=0 cellspacing=0 border=0 width=98%>						
				<tr>
					<td align=center>
						<table border=0 cellspacing=1 cellpadding=2 width=100%>
							<tr class=detailHead>
								<td colspan=2 class=detailHead><bean:message key="prompt.searchForMedications"/></td>
							</tr>
							<tr>
								<td class=formDeLabel nowrap width=1%><bean:message key="prompt.medication"/></td>
								<td class=formDe><html:text name='medicalForm' property='searchMedication.medication' size="68" maxlength="68"/></td>
							</tr>
							<tr>
								<td class=formDeLabel nowrap width=1%><bean:message key="prompt.delivery"/></td>
								<td class=formDe><html:text name='medicalForm' property='searchMedication.delivery' size="43" maxlength="43"/></td>
							</tr>
							<tr>
								<td class=formDeLabel nowrap width=1%><bean:message key="prompt.strength"/></td>
								<td class=formDe><html:text name='medicalForm' property='searchMedication.strength' size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td class=formDeLabel nowrap width=1%><bean:message key="prompt.medication"/> <bean:message key="prompt.code"/></td>
								<td class=formDe><html:text name='medicalForm' property="searchMedication.medCode" size="10" maxlength="10"/></td>
							</tr>
							<tr>
								<td class=formDeLabel nowrap width=1%><bean:message key="prompt.usage"/></td>
								<td class=formDe><html:text name='medicalForm' property="searchMedication.usage" size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td class=formDeLabel></td>
								<td class=formDe>
									 <html:submit property="submitAction" styleId="findMedication">
										<bean:message key="button.findMedications"></bean:message>
									 </html:submit>		
									 <html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>		
								</td>
							</tr>
							<tr>
								<td align=center style="padding:2px" colspan=2><br>
									<logic:notEmpty name="medicalForm" property="medications">
									<bean:size id="collSize" name="medicalForm" property="medications"/>
										<bean:write name="collSize"/> search results found.
										<table border=0 width=100% cellspacing=1 cellpadding=2>				
											<tr bgcolor=#cccccc>
												<td width="1%"></td>
												
												<td class=subhead>
                 				 				  <bean:message key="prompt.name" />
								                  <jims2:sortResults beanName="medicalForm" results="medications" primaryPropSort="tradeName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
								                </td>
												
												
												<td class=subhead><bean:message key="prompt.delivery" /><jims2:sortResults beanName="medicalForm" results="medications" primaryPropSort="dosageAdmin" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" /></td>
												<td class=subhead><bean:message key="prompt.strength" /><jims2:sortResults beanName="medicalForm" results="medications" primaryPropSort="strength" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" /></td>
												
											</tr>
	
											<%
			  								  int RecordCounter = 0;
			  								  String bgcolor = "";
			  								%>	
			  								<%-- Begin Pagination Header Tag--%>
					                            <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
					                            <pg:pager
					                           		 index="center"
					                           		 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					                           		 maxIndexPages="10"
					                           		 export="offset,currentPageNumber=pageNumber"
					                           		 scope="request">
					                           			 <input type="hidden" name="pager.offset" value="<%= offset %>">
					                         <%-- End Pagination header stuff --%>
			  								<logic:iterate id="medicationIndex" name="medicalForm" property="medications">
			  									<%-- Begin Pagination item wrap --%>
                              					<pg:item>
			  								<bean:define id="medId" name="medicationIndex" property="medicationTypeId" type="java.lang.String"/>
												<tr
												  class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
	
														<td align="center">																																		
															<html:radio name="medicalForm" property="selectedMed" value='<%=medId%>' />
															
														</td>
														<td><bean:write name='medicationIndex' property='tradeName'/></td>
														<td><bean:write name='medicationIndex' property='dosageAdmin'/></td>
													<td><bean:write name='medicationIndex' property='strength'/></td>															
												</tr>
												    </pg:item>
                        	   					<%-- End Pagination item wrap --%>
											</logic:iterate>
													
										</table>
										 <%-- Begin Pagination navigation Row--%>
							                <table align="center">
							             		  <tr>
							              			<td>
							            		   		<pg:index>
							                  				<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
							                      		<tiles:put name="pagerUniqueName"    value="pagerSearch"/>
							                     		 <tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
							              					</tiles:insert>
							           	   	   		</pg:index>
							                  		</td>
							          	  	   </tr>
							                </table>
							                     <%-- End Pagination navigation Row--%>
							                     <%-- Begin Pagination Header Closing Tag --%>
							                </pg:pager>
							                <%-- End Pagination Header Closing Tag --%>
									</logic:notEmpty>
									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<br>
 <table border="0" width="100%">
		<TBODY>
			<tr id='select' class='hidden'>
				<td align="center">
			
				<html:submit property="submitAction">
					<bean:message key="button.back"></bean:message>			
				</html:submit> 				
				<span >
				<logic:notEmpty  name="medicalForm" property="medications">
					<html:submit property="submitAction">
						<bean:message key="button.select"></bean:message>
					</html:submit>
				</logic:notEmpty>
				</span>
				<html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
				</td>
			</tr>
			<tr id='noselect' class='visible' align="center">
				<td align="center">				
					<html:submit property="submitAction">
						<bean:message key="button.back"></bean:message>			
					</html:submit> 				
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</td>
			</tr>
		</TBODY>
	</table>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>