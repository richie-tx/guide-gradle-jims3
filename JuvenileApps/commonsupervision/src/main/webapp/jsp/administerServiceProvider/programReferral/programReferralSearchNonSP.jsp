<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 03/12/2010	DWilliamson		create jsp --%>
<!-- 10/13/2015 RCapestani #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.PDCalendarConstants" %>


<html:base />

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- programReferralSearch.jsp</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<%-- <html:form action="/displayProgramReferralList" target="content"> --%>
<input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|">

<div align="center">
	
<!-- BEGIN HEADING TABLE -->
<div class='spacer'></div>
<table width='100%'>
    <tr>
        <td align="center" class="header"><bean:message key="title.serviceProvider" />&nbsp;-&nbsp;<bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.search" /></td>
    </tr>
</table>
<table width="100%">
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
    <tr>
        <td>&nbsp;
        </td>
    </tr>
</table>

<!-- BEGIN  TABLE -->
<br>
<span id="spSearch">

<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
    <tr class="detailHead">
        <td><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.search" /></td>
    </tr>
    <tr>
        <td>
            <table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">
                <tr>
                    <td class="detailHead" colspan="4"><bean:message key="prompt.programReferral" /></td>   
                </tr>
                <tr>
                    <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.programReferral" /> Status</td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>                
                <tr>
                    <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.programName" /></td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>
                <tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
                <tr>
                    <td class="detailHead" colspan="4"><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.beginDate" /></td>   
                </tr>
                <tr>
                    <td class="formDe" colspan="4">&nbsp;</td>
                </tr>
                
                <tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
                <tr>
                    <td class="detailHead" colspan="4">Juvenile Number</td>   
                </tr>              
                <tr>
                    <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.juvenileNumber" /></td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>
                <tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
                <tr>
                    <td class="detailHead" nowrap="nowrap" colspan="4"><bean:message key="prompt.jpo"/>&nbsp;<bean:message key="prompt.name"/>&nbsp;or&nbsp;<bean:message key="prompt.juvenileName"/></td>                   
                </tr>
                <tr>
                    <td class="formDeLabel" nowrap="nowrap">Name search is of type</td>
                    <td colspan="3" class="formDe" nowrap="nowrap">&nbsp;</td>                   
                </tr>
                <tr>
                    <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.lastName" /></td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>
                <tr>
                    <td class="formDeLabel" nowrap="nowrap">+<bean:message key="prompt.firstName" /></td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>
                <tr>
                    <td class="formDeLabel" nowrap="nowrap">+<bean:message key="prompt.middleName" /></td>
                    <td class="formDe" colspan="3">&nbsp;</td>
                </tr>                											
            </table>
        </td>
    </tr>
                       
<!-- SEARCH END-->
</table>
</span>
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
    <tr>
        <td align="center">
            <input type="button" value="Cancel" onClick="goNav('/appshell/displayHome.do')">						
        </td>
    </tr>
</table>
<!-- END BUTTON TABLE -->
<br>

</div>

<%-- </html:form> --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>

</html:html>

