<!DOCTYPE HTML>
<!-- 10/04/2006	 Debbie Williamson - Create JSP -->
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - location/locationServiceProviderPopUp.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body>
<!--form name="searchOrder"-->
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign=top><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
      </tr>
      <tr>

      <td valign=top>
      
      <table width=100% border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
        </tr>
        <tr>

        <td valign=top align=center>
           <!-- BEGIN INSTRUCTION TABLE -->
          <table width="98%" border="0">
            <tr>
              <td>
                <ul>
                	<li>Page through the service providers.</li>
                  <li>Click Close button to close this window.</li>
                </ul>
              </td>
              <td>
              	<logic:notEmpty name="locationForm" property="isLocationUnitPage">
					<logic:equal name="locationForm" property="isLocationUnitPage" value="true">
							<table>
								<tr class="header">
								<td align="center" class="header">Location Unit - <bean:write name="locationForm" property="locationUnitName" /></td>
								</tr>
							</table>
					</logic:equal>
				</logic:notEmpty>
              </td>
            </tr>
          </table>
          <!-- END INSTRUCTION TABLE -->	
			<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

		<pg:pager
			index="center"
			maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			maxIndexPages="10"
			export="offset,currentPageNumber=pageNumber"
			scope="request">
			<input type="hidden" name="pager.offset" value="<%= offset %>">		  
          <!-- BEGIN DETAIL TABLE -->
          <table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
            <tr class="detailHead">
              <td><bean:message key="prompt.serviceProviders" />
              <jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="serviceProviderName"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
              </td>              
              <td><bean:message key="prompt.programName" />
              	<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="programName"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
              </td>
              <td>
              	<bean:message key="prompt.serviceName" />
              	<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="serviceName"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
              </td>
			 
				<logic:notEmpty name="locationForm" property="isLocationUnitPage">
					<logic:equal name="locationForm" property="isLocationUnitPage" value="false">
						  <td>Service Type
						  	<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="serviceType"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
						  </td>
					</logic:equal>
				</logic:notEmpty>
              <td title="Service Status"><bean:message key="prompt.service"/>&nbsp;<bean:message key="prompt.status" />
              	<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="status"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
              </td>
              <td>
              	
				<logic:notEmpty name="locationForm" property="isLocationUnitPage">
					<logic:equal name="locationForm" property="isLocationUnitPage" value="false">

						  	<bean:message key="prompt.inHouse" />
							<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="isInHouse"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />

					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="locationForm" property="isLocationUnitPage">
					<logic:equal name="locationForm" property="isLocationUnitPage" value="true">

						  	<bean:message key="prompt.inHouse" />
							<jims:sortResults beanName="locationForm"
									results="associatedSPList" primaryPropSort="inHouse"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />

					</logic:equal>
				</logic:notEmpty>
              </td>
            </tr>
			<%int RecordCounter = 0;
			String bgcolor = "";%>  
			<logic:notEmpty name="locationForm" property="associatedSPList">	
				<logic:iterate id="serviceProvider" name="locationForm" property="associatedSPList">
<!-- Begin Pagination item wrap -->
				  <pg:item>
					<tr
					    class=<%RecordCounter++;
						bgcolor = "alternateRow";
						if (RecordCounter % 2 == 1)
							bgcolor = "normalRow";
						out.print(bgcolor);%>>
						  <td><bean:write name="serviceProvider" property="serviceProviderName" /></td>
						  <td><bean:write name="serviceProvider" property="programName" /></td>
							<td>
								<logic:notEmpty name="serviceProvider" property="serviceName">
									<bean:write name="serviceProvider" property="serviceName" />
								</logic:notEmpty>
							</td>
							<logic:notEmpty name="locationForm" property="isLocationUnitPage">
							  	<logic:equal name="locationForm" property="isLocationUnitPage" value="false">
							  		<td><bean:write name="serviceProvider" property="serviceType" /></td>
							  	</logic:equal>				  	
						  	</logic:notEmpty>
						  <td><bean:write name="serviceProvider" property="status" /></td>
						  <td>
						  	 
						  	 <logic:notEmpty name="locationForm" property="isLocationUnitPage">
							  	<logic:equal name="locationForm" property="isLocationUnitPage" value="true">
									<jims:displayBoolean name="serviceProvider" property="inHouse" trueValue="YES" falseValue="NO"/>
							  	</logic:equal>						  	
					  		</logic:notEmpty>
					  		
					  		<logic:notEmpty name="locationForm" property="isLocationUnitPage">
							  	<logic:equal name="locationForm" property="isLocationUnitPage" value="false">
									<jims:displayBoolean name="serviceProvider" property="isInHouse" trueValue="YES" falseValue="NO"/>
							  	</logic:equal>						  	
					  		</logic:notEmpty>
						  	 
						  	 
						  </td>
						</td>
					</tr>
                 </pg:item>
<!-- End Pagination item wrap -->
               </logic:iterate>
            </logic:notEmpty>
          </table>
          <!-- END DETAIL TABLE -->
          <br>
<!-- Begin Pagination navigation Row-->
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
			</table>						
<!-- End Pagination navigation Row-->
<!-- Begin Pagination Closing Tag -->
</pg:pager>
<!-- End Pagination Closing Tag -->
          <!--table width="98%" cellpadding="0" cellspacing="0">
          	<tr>
          		<td align=center>
          			<table cellpadding="2" cellspacing="0">
										<tr class=boldText>
										<td>Page</td>
										<td><input type="text" size=2 value=1></td>
										<td>of 2</td>
										<td><a href="#"><img src="/<msp:webapp/>images/blue_next.gif border=0"></a></td>
									</tr>
					  </table>
          		</td>
          	</tr>
          </table-->
          <!-- BEGIN BUTTON TABLE -->
          <table border="0" width="100%">
            <tr>
              <td align="center">
                <input type="button" value="Close" onClick="window.close()">                
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
<!--/form-->
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
