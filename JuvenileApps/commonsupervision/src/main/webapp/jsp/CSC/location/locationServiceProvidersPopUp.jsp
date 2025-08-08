<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- MODIFICATIONS --%>
<!-- 12/20/2007	 Bhavani Jangay - Create JSP (ER JIMS200047257)-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>

<!-- Begin Pagination Header Tag-->

<!-- End Pagination header stuff -->

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
<title><bean:message key="title.heading" /> - CSC/location/locationServiceProviderPopUp.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head>
<body>
<!--form name="searchOrder"-->
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
      </tr>
      <tr>

      <td valign="top">
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
        </tr>
        <tr>

        <td valign="top" align="center">
           <!-- BEGIN INSTRUCTION TABLE -->
          <table width="98%" border="0">
            <tr>
              <td>
                <ul>
                	<li>Page through the service providers.</li>
                  <li>Click Close button to close this window.</li>
                </ul>
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
          <table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
            <tr class="detailHead">
              <td><bean:message key="prompt.serviceProvidersForCSC" />
              </td>
              <td>Program Identifier</td>
              <td>Program Name</td>
              <td><bean:message key="prompt.programGroup" /></td>
              <td><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.status" /></td>
              <td><bean:message key="prompt.inHouse" /></td>
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
					  <td><bean:write name="serviceProvider" property="programIdentifier" /></td>
					  <td><bean:write name="serviceProvider" property="programName" /></td>
					  <td><bean:write name="serviceProvider" property="programGroupDesc" /></td>
					  <td>
					  <bean:write name="serviceProvider" property="programStatusDesc" />
					  </td>
					  <td><jims2:displayBoolean name="serviceProvider" property="inHouse" trueValue="YES" falseValue="NO"/></td>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
