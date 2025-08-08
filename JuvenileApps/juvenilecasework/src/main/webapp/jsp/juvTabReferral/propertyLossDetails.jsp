<!DOCTYPE HTML>

<%-- 07/05/2007	Uma Gopinath Create JSP --%>
<%-- 09/25/2007 C Shimek     added formatKey and align right to amount field, found testing defect 45520  --%>
<%-- 08/27/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - propertyLossDetails.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
</head>
<%--END HEAD TAG--%>

<body topmargin=0 leftmargin="0">
<html:form action="/handleJuvenileCasefilePetitionDetails" target="content">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Juvenile Profile - Assigned Referral Property Loss Details</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 



<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
       <li>Select Close Window button to close this window.</li>
      </ul>
	  </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>

  		<!-- begin property loss table list -->
			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class=detailHead>Property Loss List</td>
				</tr>
				<tr>
  				<td>
  					<table cellpadding=2 cellspacing=1 width='100%'>
  						<tr bgcolor='#cccccc'>
  							<td align='left' class=subHead><bean:message key="prompt.itemDescription"/></td>
  							<td align='left' class=subHead>Amount</td>
  					  </tr>
  					  <% int RecordCounter = 0; %>	
							<logic:iterate id="resultsIndex" name="petitionDetailsForm" property="propertyLosses" >
																																
		                    	<tr
									class=<%RecordCounter++;
								    String className = "alternateRow";
								    if (RecordCounter % 2 == 1)
								    	className = "normalRow";
								        out.print(className);%>>
							                   <td valign=top><bean:write name="resultsIndex" property="description"/></td>
							                   <td valign=top><bean:message key="currency.prefix"/><bean:write name="resultsIndex" property="value" formatKey="currency.format" /></td>																								                    																			                 
						
                            </logic:iterate>
  					</table>
  				
										
					</td>
				</tr>
			</table><br>
  		<!-- end property loss table list -->
  
 			<!-- begin button table -->
      <table border="0" cellpadding=1 cellspacing=1 align=center>
      
        <tr>
          <td align="center"><input type=button value='Close Window' onclick="window.close();"></td>
        </tr>
       
      </table><br>
 			<!-- end button table -->

		</td>
	</tr>
</table>


<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

