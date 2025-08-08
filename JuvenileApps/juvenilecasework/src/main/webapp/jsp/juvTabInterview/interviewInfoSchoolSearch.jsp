                                                                   
<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<title><bean:message key="title.heading" /> Juvenile Profile -
interviewInfoSchoolSearch.jsp</title>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- Javascript for emulated navigation -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>


<script type="text/javascript">
function onloadForm()
{
  if( location.search == "?results" )
	{
	  show( 'resultsInstructions', 1 ) ;
	  show( 'resultsTable', 1 ) ;
		showHide( 'resultsButtons', 1, 'row' ) ;
	}
	else
	{
	  show( 'searchInstructions', 1 ) ;
		showHide( 'searchButtons', 1, 'row' ) ;
	}
}

function clearName(){
var theForm = document.forms[0];
theForm['schoolSearchName'].value="";
theForm['schoolSearchName'].focus();
return true;
}

function checkFields()
{
var theForm = document.forms[0];
	if(Trim(theForm['schoolSearchName'].value)=="")
	 {
	 	alert("School Name is required for search");
	 	clearName();	  	
	 	return false;
	}
	return true;
}

</script>



<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin=0 leftmargin="0">
   <html:form action="/processJuvenileSchool" target="content" focus="schoolSearchName" >

	<!-- BEGIN HEADING TABLE -->
	<table width='100%'>
		<tr>
			<td align="center" class="header">Juvenile Casework - Juvenile
			Profile - Search for School <script type='text/javascript'>
 				if( location.search == "?results" )
 					document.write( " Results" ) ;
 			</script></td>
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
	<br>
	<table width="98%" border="0">
		<tr>
			<td>
			<ul id='searchInstructions' class='hidden'>
				<li>Enter a school name.</li>
				<li>Select Submit button to view search results</li>
			</ul>

			<ul id='resultsInstructions' class='hidden'>
				<li>Select a school radio button, then select the Select button to
				return to the Create School page.</li>
			</ul>

			</td>
		</tr>
	</table>
	<!-- END INSTRUCTION TABLE -->

	<%-- BEGIN DISPLAY PROFILE HEADER --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp"
		flush="true">
		<tiles:put name="headerType" value="profileheader" />
	</tiles:insert>
	<%-- END DISPLAY PROFILE HEADER --%>
	<br>
	<%-- BEGIN DETAIL TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign=top>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign=top><tiles:insert
						page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab" />
						<tiles:put name="juvenileNum"
							value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert></td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0"
				class="borderTableGreen">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
				<tr>
					<td valign=top align=center>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign=top><tiles:insert
								page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" beanName="juvenileTraitsForm"
									beanProperty="categoryName" />
								<tiles:put name="juvnum"
									value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert></td>
						</tr>
						<tr>
							<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
						</tr>
					</table>

					<table width='98%' border="0" cellpadding="0" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
						</tr>
						<tr>
					<td valign=top align=center>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
                    <tr>
                      <td valign="top">
                        <tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
                          <tiles:put name="tabid" value="school"/>
                          <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                        </tiles:insert>	
                      </td>
                    </tr>
                    <tr>
                      <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
                    </tr>
                  </table>
                  <table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
            				
						<tr>
							<td valign=top align=center><!-- BEGIN SCHOOL INFO TABLE -->
							<div class=spacer></div>
							<table width='98%' border="0" cellpadding="2" cellspacing="0"
								class=borderTableBlue>
								<tr>
									<td valign=top class=detailHead>School Search</td>
								</tr>

								<tr>
									<!-- begin data entry section -->
									<td>
									<table border=0 cellpadding=2 cellspacing="1" width='100%'>
										<tr>
											<td class=formDeLabel width="1%" nowrap><bean:message
												key="prompt.schoolName" /></td>
											<td class=formDe><html:text name='juvenileSchoolHistoryForm'
												property='schoolSearchName' size="30" maxlength="30"/></td>
										</tr>
										<tr>
								<td class=formDeLabel></td>
								<td colspan=3 class=formDe>
                                   <html:submit onclick="return checkFields(this.Form);" property="submitAction">
					                   <bean:message key="button.find" />
				                   </html:submit>
                                   <html:submit property="submitAction" onclick="return clearName(this.Form);">
					                   <bean:message key="button.refresh" />
				                   </html:submit>
								</td>
								
							</tr>
									</table>
									</td>
								</tr>
							</table>
							<br>
							<!-- END TABLE --> <!-- BEGIN SCHOOL Search Results TABLE --> <!-- Delete It --> 
						  	<logic:notEmpty	name="juvenileSchoolHistoryForm" property="schoolDistrictList">
								<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  												<tr class=detailHead>
  													<td valign=top>School Search Results</td>
  												</tr>
								<tr>
								<td>
								<bean:size id="collSize" name="juvenileSchoolHistoryForm"
									property="schoolDistrictList" />
								<center> <bean:write name="collSize" /> search results found.</center>
										<table border=0 width=98% cellspacing=1 cellpadding=2>
									<tr bgcolor=#cccccc>
										<td class=subhead><bean:message key="prompt.schoolName" /><jims2:sortResults
											beanName="juvenileSchoolHistoryForm"
											results="schoolDistrictList"
											primaryPropSort="schoolDescription" primarySortType="STRING"
											defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
										<td class=subhead><bean:message key="prompt.schoolDistrict" /><jims2:sortResults
											beanName="juvenileSchoolHistoryForm"
											results="schoolDistrictList"
											primaryPropSort="districtDescription"
											primarySortType="STRING" defaultSort="true"
											defaultSortOrder="ASC" sortId="2" /></td>

									</tr>

									<%int RecordCounter = 0;
						String bgcolor = "";

						%>
									<%-- Begin Pagination Header Tag--%>
									<bean:define id="paginationResultsPerPage"
										type="java.lang.String">
										<bean:message key="pagination.recordsPerPage" />
									</bean:define>
									<pg:pager index="center"
										maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
										maxIndexPages="10"
										export="offset,currentPageNumber=pageNumber" scope="request">
										<input type="hidden" name="pager.offset" value="<%= offset %>">
										<%-- End Pagination header stuff --%>
									
										<logic:iterate id="schoolIndex"	name="juvenileSchoolHistoryForm" property="schoolDistrictList">
											<%-- Begin Pagination item wrap --%>
											<pg:item>
											<bean:define id="scId" name="schoolIndex" property="oid" type="java.lang.String"/>
												<tr	class=<%RecordCounter++; bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
											<td>
											<html:radio name="juvenileSchoolHistoryForm" property="selectedSchoolId" value='<%=scId%>'/>
													<bean:write name='schoolIndex' property='schoolDescription' /></td>
													<td><bean:write name='schoolIndex' property='districtDescription' /></td>
												</tr>
											</pg:item>
											<%-- End Pagination item wrap --%>
										</logic:iterate>
								</table>
								</td>
								</tr>
						</table>
								
								<%-- Begin Pagination navigation Row--%>
								<table align="center">
									<tr>
										<td><pg:index>
											<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
												<tiles:put name="pagerUniqueName" value="pagerSearch" />
												<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
											</tiles:insert>
										</pg:index></td>
									</tr>
								</table>
								<%-- End Pagination navigation Row--%>
								<%-- Begin Pagination Header Closing Tag --%>
								</pg:pager>
								<%-- End Pagination Header Closing Tag --%>
								
								<table border="0" width="100%">
								  <tr>
								  	<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"></bean:message>
										</html:submit>
									<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedSchoolId', 'You must select a school to proceed.' );"><bean:message key="button.select"></bean:message>
										</html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
										</html:submit>
								    </td>
								  </tr>
								</table>
							</logic:notEmpty> 
							<!-- BEGIN BUTTON TABLE -->
							
						<logic:empty name="juvenileSchoolHistoryForm" property="schoolDistrictList"> 
							<table border="0" width="100%">
								<tr>
									<td align="center">
									  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
										</html:submit> 
									</td>
								</tr>
							</table>
							
						</logic:empty>
							<!-- END BUTTON TABLE --></td>
						</tr>
					</table>
					<br>
					</td>
				</tr>
			</table>
			
			<br>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!-- END DETAIL TABLE -->

</html:form>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</BODY>
</html:html>
