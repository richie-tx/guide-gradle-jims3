<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - caseplanCasefileList.jsp</title>
<%--END HEADER TAG--%>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvProfileCaseplanList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|215">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Caseplan List</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>

<%-- END HEADING TABLE --%>
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select <b>Supervision Number</b> hyperlink to view additional details.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER TABLE --%>
<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td><%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> <%--header info end--%>
		</td>
	</tr>
</table>
<%-- END HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign='top'> <%--tabs start--%> 
           			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="goalstab" />
						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
					</tiles:insert> <%--tabs end--%>
  				</td>
  			</tr>
  			<tr>
			  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
  			</tr>
  		</table>
			
 		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">

            <%-- BEGIN Activities TABLE --%>
            		<div class='spacer'></div>
            			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              				<tr>
                				<td valign="top" class="detailHead" colspan="6">
                  					<table width='100%' cellpadding="0" cellspacing="0">
                    					<tr>
											<logic:notEmpty name="caseplanForm" property="profileActivityList">
	                      						<td width='1%'><a href="javascript:showHideMulti('JPO Activity List', 'acChar', 1, '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="JPO Activity List"></a></td>
										  	</logic:notEmpty>
                      						<td class="detailHead" colspan="6">&nbsp;Caseplan&nbsp;<bean:message key="prompt.activities" />
												<logic:empty name="caseplanForm" property="profileActivityList">
												  	(No Caseplan Activities)
												</logic:empty>
                      						</td>
                    					</tr>
                  					</table>
                				</td>
   				           </tr>

          	  				<tr id="acChar0" class="hidden">
              					<td>
	              					<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
								    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
							    		<input type="hidden" name="pager.offset" value="<%= offset %>">
										<%-- End Pagination header stuff --%>

              						<table cellpadding="2" cellspacing="1" width='100%'>
										<logic:notEmpty name="caseplanForm" property="profileActivityList">

				              			<tr bgcolor='#cccccc'>
				              				<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.supervision"/>&nbsp;<bean:message key="prompt.number"/></td>
				              				<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.activityDate"/></td>
				              				<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.activity"/></td>
				              			</tr>
					                    <logic:iterate id="activityIndex" name="caseplanForm" property="profileActivityList" indexId="indexer">
										<%-- Begin Pagination item wrap --%>
											<pg:item>
		  										<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	
													<td valign="top"><a href="/<msp:webapp/>displayProfileActivitySummary.do?submitAction=Link&activityId=<bean:write name='activityIndex' property='activityId' />&casefileId=<bean:write name='activityIndex' property='casefileId' />">
														<bean:write name="activityIndex" property="casefileId"/></a>
													</td>
													<td valign="top"><bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy"/></td>
													<td><bean:write name="activityIndex" property="comments"/></td>
			              						</tr>
											  </pg:item>
									<%-- End Pagination item wrap --%>
	                    				</logic:iterate>
									</logic:notEmpty>      
              					</table>
                			</pg:pager>
              			</td>
            		</tr>
          		</table>

				  <%-- BEGIN Caseplan TABLE --%>
          		<div class='spacer'></div>
 				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
 					<tr>
 						<td class="detailHead">Caseplans
							  <logic:empty name="caseplanForm" property="caseplanList">
						  		(No Caseplans)
							  </logic:empty>
						</td>
 					</tr>
 					<tr>
 						<td>
							<table cellpadding="2" cellspacing="1" width='100%'>
								<logic:notEmpty name="caseplanForm" property="caseplanList">
									<tr bgcolor='#cccccc'>
										<td class="subHead"><bean:message key="prompt.supervision"/>&nbsp;<bean:message key="prompt.number"/></td>
										<td class="subHead"><bean:message key="prompt.caseplanCreateDate"/></td>
										<td class="subHead"><bean:message key="prompt.status"/></td>
										<td class="subHead"><bean:message key="prompt.reviewDate"/></td>
									</tr>

				  					<logic:iterate id="cpIndex" name="caseplanForm" property="caseplanList" indexId="indexer">
										<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	  										<td><a href="/<msp:webapp/>displayCaseplanDetails.do?action=JUVPROFILEVIEW&submitAction=<bean:message key='button.link'/>&casefileId=<bean:write name="cpIndex" property="supervisionNumber" />">
				                               <bean:write name="cpIndex" property="supervisionNumber"/></a>
                							</td>
											<td valign="top"><bean:write name="cpIndex" property="createDate" formatKey="date.format.mmddyyyy"/></td>
											<td valign="top"><bean:write name="cpIndex" property="status"/></td>
											<td valign="top"><bean:write name="cpIndex" property="reviewDate" formatKey="date.format.mmddyyyy"/></td>
		  								</tr>
			              			</logic:iterate>
                  				</logic:notEmpty>      
  							</table>
  						</td>
  					</tr>
  				</table>
          <%-- END casefile TABLE --%>
</html:form>

			<%-- BEGIN BUTTON TABLE --%>
				<table border="0" align="center">
					<tr>
						<td align="center">
							<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
									<bean:message key="button.back" />
							</html:button>&nbsp;
						</td>
								
						<html:form action="/displayJuvenileMasterInformation.do">
								<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
						</html:form>
					</tr>
				</table>
			<%-- END BUTTON TABLE --%>
          </td>
        </tr>
	</table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>