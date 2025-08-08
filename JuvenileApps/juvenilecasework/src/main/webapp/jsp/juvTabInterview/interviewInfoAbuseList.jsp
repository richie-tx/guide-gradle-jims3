<!DOCTYPE HTML>
<%-- User selects the "Abuse" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 06/22/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/16/2007 Leslie Deen		Defect #43633 changed trait description to trait type description to match create screen --%>
<%-- 07/20/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - interviewInfoAbuseList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|181">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Abuse List</td> 
  </tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Click on hyperlinked Entry Date to see abuse detail.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>

<%-- BEGIN DETAIL TABLE --%>  
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
              <tiles:put name="tabid" value="interviewinfotab"/>
              <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
            </tiles:insert>						
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
        </tr>
      </table>  	  	
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr>
          <td valign="top" align="center">
      		<div class=spacer></div>
            <table width='98%' border="0" cellpadding="0" cellspacing="0" >
              <tr>
                <td valign="top">
                  <tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
                    <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                  </tiles:insert>
                </td>
              </tr>
              <tr>
                <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
              </tr>
            </table>
            <div class=spacer></div>
            <table width='98%' border="0" cellpadding="0" cellspacing="0" >
              <tr>
                <td valign="top">
                  <tiles:insert page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
                  	<%-- <tiles:put name="tabid" value="dualStatus"/> --%>
                   <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                  </tiles:insert>
                </td>
              </tr>
              <tr>
                <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
              </tr>
            </table>
			
      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
      				<tr>
      					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
      				</tr>
      				<tr>
      					<td valign="top" align="center">																		
										<%-- BEGIN JOB TABLE --%>
      						<% int RecordCounter = 0; %>	
      						<%-- Begin Pagination Header Tag--%>
                  <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

                  <pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
                      maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

                    <input type="hidden" name="pager.offset" value="<%= offset %>">
                    <%-- End Pagination header stuff --%>											
          						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">				
            						<%-- display detail header --%> 
            						<logic:empty name="juvenileAbuseForm" property="abuses">
            							<tr class="detailHead">
            								<td colspan="6">No Abuse Information Available</td>
            							</tr>
             						</logic:empty>
     
			      						  <logic:notEmpty name="juvenileAbuseForm" property="abuses">
            							<tr class="detailHead">
            								<td align="left"><bean:message key="prompt.entryDate" /></td>
            								<td align="left" nowrap><bean:message key="prompt.perpetratorName" /></td>
            								<td align="left"><bean:message key="prompt.relationshipToJuvenile" /></td>
            								<td align="left"><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />&nbsp;<bean:message key="prompt.description" /></td>
            								<td align="left"><bean:message key="prompt.info" />&nbsp;<bean:message key="prompt.source" /></td>	
            								<td align="left"><bean:message key="prompt.abuseLevel" /></td>
            								<td align="left"><bean:message key="prompt.abuseTreatment" /></td>								
            							</tr>
     
			           						<%-- display detail info --%>  
             							<logic:iterate id="abuseIndex" name="juvenileAbuseForm" property="abuses" indexId="abuseId">
          	  							 <%-- Begin Pagination item wrap --%>
                      		  <pg:item>
              								<tr class="<%out.print( (abuseId.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
              										
                								<td align="left"><a href="/<msp:webapp/>displayJuvenileAbuseInfo.do?abuseId=<bean:write name="abuseIndex" property="abuseId"/>&action=view">
                									<bean:write name="abuseIndex" property="entryDate" format="MM/dd/yyyy" /></a>
																	</td>
                								<td align="left">
																	 <%--  <bean:write name="abuseIndex" property="lastName" /> --%>
																		<%-- <logic:notEmpty name="abuseIndex" property="firstName">, </logic:notEmpty> --%>
                  								<bean:write name="abuseIndex" property="firstName" /> <%-- <bean:write name="abuseIndex" property="middleName" /> --%>
																	</td>
                								<td align="left"><bean:write name="abuseIndex" property="relationshipToJuvenileDescription" /></td>
                								<td align="left"><bean:write name="abuseIndex" property="traitTypeName" /></td>
                								<td align="left"><span title='<bean:write name="abuseIndex" property="informationSrcDesc"/>'><bean:write name="abuseIndex" property="informationSrcCd" /></span></td> 
                								<td align="left"><bean:write name="abuseIndex" property="abuseLevelDescription" /></td>
                								<td align="left"><bean:write name="abuseIndex" property="treatment" /></td>    
              							  </tr>
            							  </pg:item><%-- End Pagination item wrap --%>
          							  </logic:iterate>	
          							</logic:notEmpty>					
          						</table><%-- END JOB TABLE --%>

   						        <%-- Begin Pagination navigation Row--%>
                      <table align="center">
                        <tr>
                          <td>
                            <pg:index>
                              <tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
                                <tiles:put name="pagerUniqueName" value="pagerSearch"/>
                                <tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
                              </tiles:insert>
                            </pg:index>
                          </td>
                        </tr>
                      </table><%-- End Pagination navigation Row--%>
												
                  <%-- Begin Pagination Closing Tag --%>
                  </pg:pager>
                  <%-- End Pagination Closing Tag --%>		 

	                <%-- BEGIN BUTTON TABLE --%>
					<html:form action="/displayJuvenileAbuse" target="content">
						<table align="center">	
							<tr>
								<td><html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
									<logic:notEmpty name="juvenileProfileHeader" property="status">
										<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_ABUSE_U%>'>
											<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
											<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
												<html:submit property="submitAction"><bean:message key="button.addMoreAbuseInfo"/></html:submit>
											</logic:notEqual>
											<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
												<html:submit property="submitAction"><bean:message key="button.addMoreAbuseInfo"/></html:submit>
											</jims2:isAllowed>
											</logic:equal>
											</logic:equal>	
										</jims2:isAllowed>
									</logic:notEmpty>
									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>				
							    </td>
							</tr>
						</table>
					</html:form>
									<%-- END BUTTON TABLE --%>								
                </td>
              </tr>
            </table>
						<div class='spacer'></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- END FORM --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

