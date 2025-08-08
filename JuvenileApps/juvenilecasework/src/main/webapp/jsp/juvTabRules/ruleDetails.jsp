<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the rule ID hyperlink to view the Rule Details --%>
<%-- 11/10/2005	Leslie Deen		Create JSP --%>
<%-- 06/28/2007	Leslie Deen		Defect #42784-match PT --%>
<%-- 08/06/2015 RYoung          #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<title><bean:message key="title.heading" />- ruleDetails.jsp</title>
<script type='text/javascript'>
function changeActionFormURL(myForm, URL, doSubmit)
{
	myForm.action = URL;
	if(doSubmit)
		myForm.submit();
}

$(function() {

    $("#servDate").datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        $( "#servDate" ).datepicker( "option", "minDate", selectedDate );
	    }
    });
 });
</script>
</head>
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0"> 
<html:form action="/displayJuvenileProfileRuleDetail"
	target="content"> 
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|214"> 
<%-- BEGIN HEADING TABLE --%> 
<table width=100%> 
  <tr> 
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Rule Details </td> 
  </tr> 
</table> 
<table width="100%"> 
    <tr> 
      <td align="center" class="errorAlert"><html:errors></html:errors></td> 
    </tr> 
</table> 
<%-- END HEADING TABLE --%> 
<br> 
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
  <tr> 
    <td class="bodyText"> <ul> 
        <li>Click on a hyperlinked Supervision Goal to view Goal Details.</li> 
      </ul></td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN CASEFILE HEADER TABLE --%> 
<table align="center" cellpadding="0" cellspacing="0" border="0" width="98%"> 
  <tr> 
    <td><%--header info start--%> 
      <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true"> <tiles:put name="headerType" value="profileheader"/> </tiles:insert> 
      <%--header info end--%></td> 
  </tr> 
</table> 
<%-- END CASEFILE HEADER TABLE --%> 
<br> 
<!-- BEGIN DETAIL TABLE --> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0"> 
  <tr> 
    <td valign=top> <%-- BEGIN BLUE TABS TABLE --%> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0"> 
        <tr> 
          <td valign="top"><%--tabs start--%> 
           
           
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="rulestab" />
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert> <%--tabs end--%>
					
            </td> 
        </tr> 
        <tr> 
          <td bgcolor="#33cc66"><img src="../../images/spacer.gif" width=5></td> 
        </tr> 
      </table> 
      <%--end of blue tabs--%> 
      <%-- BEGIN RULES TABLE --%> 
      <tiles:insert page="../caseworkCommon/ruleDetailsTile.jsp" flush="true">
       <tiles:put name="supervisionRulesForm" value="supervisionRulesForm" />
        <tiles:put name="isprofile" value="true" />
       </tiles:insert> 
      <%-- END RULES TABLE --%> </td> 
  </tr> 
</table> 
<%-- END DETAIL TABLE --%> 
<br> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div> 
<br> 
</html:form> 
</body>
</html:html>