<!DOCTYPE HTML>
<%-- MODIFICATIONS --%>
<%-- 05/24/2006 Uma Gopinath Service Provider Dashboard JSP --%>
<%-- 12/08/2006 C Shimek    Defect#37651  Add status diaplay in program block  --%>
<%-- 06/18/2006 C Shimek    removed phone displaying at bottom of page, found while testing defect on another page --%>
<%-- 10/13/2015 R Capestani Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading"/> - reactivateServiceProviderSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script>
function setAction(action)
{
	document.forms[0].actionType.value = action;
}

function checkRadioFieldSelected(thisForm)
{
	var myOption = 0;
	var radioFound = false ;    
	
	for(var i = 0; i < thisForm.length; i++)
  {
	  if(thisForm.elements[i].type == 'radio')
    {
		  radioFound = true ;
      if(thisForm.elements[i].checked)
      {
        myOption = 1;
      }   
    }
  }// for
     
	if( radioFound  &&  !myOption ) 
	{
		alert("You must select a record to perform the operation.");
		return false;
	}
	
	return true;
	
}

var currentSelectedValue = "";

function setSelectedVal(programCode)
{
 currentSelectedValue = programCode;
}

function loadView(file, actionVal)
{	
	var myURL = file + "&selectedValue=" + escape(currentSelectedValue) + "&actionType=" + actionVal;

	load(myURL, top.opener);
	window.close();
}

function load(file,target) 
{
   window.location.href = file;
}

</script>
</head>
<%--END HEADER TAG--%>

<body topmargin=0 leftmargin="0">
<html:form action="/handleServiceProviderDashboard" >
<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|364">

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><bean:message key="prompt.3.spacer"/></td>
  </tr>
  <tr>
    <td><html:hidden name="serviceProviderForm" property="actionType"/></td>
  </tr>
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
					<td valign=top>
						<%--tabs start--%>
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>													
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>		
        <tr>
          <td bgcolor='#6699FF'><bean:message key="prompt.3.spacer"/></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td><bean:message key="prompt.3.spacer"/></td>
        </tr>
        <tr>
          <td valign=top align=center>
          	<%-- BEGIN HEADING TABLE --%>
            <table width='100%'>
              <tr>
                <td align="center" class="header"> Activate Service Provider Summary </td>
              </tr>
            </table>
            <%-- END HEADING TABLE --%>

            <%-- BEGIN INSTRUCTION TABLE --%>
            <table width="98%" border="0" cellpadding=0 cellspacing=0>
              <logic:notEqual name="serviceProviderForm" property="confirmMessage" value="">
              <tr>
                <td class="confirm"><bean:write name="serviceProviderForm" property="confirmMessage"/></td>
              </tr>
              </logic:notEqual>              

              <logic:equal name="serviceProviderForm" property="actionType" value="reviewServiceProviderReactivate">
              <tr>
                <td>
                  <ul>
                    <li>Review entries. Click Save and Continue</li>
                  </ul>
                </td>
              </tr>
			</logic:equal>

            </table>
            <%-- BEGIN  TABLE --%>

            <%-- BEGIN SP TABLE --%>
            <table width="98%" border="0" cellspacing=0 class=borderTableBlue>
            <!-- 
              <tr>
                <td class=detailHead>
                  <table width='100%' cellpadding=2 cellspacing=0>
                    <tr>
                      <td width='1%'><a href=""></a></td>
                       <td width='1%'><a href="javascript:showHideMulti('provider', 'sp', 1,'/<msp:webapp/>')"><img border=0 src="/<msp:webapp/>/images/expand.gif" name="provider"></a></td>
                      <td class=detailHead><bean:write name="serviceProviderForm" property="providerName"/></td>
                    </tr>
                  </table>
                </td>
              </tr>
			 -->
              <tr>
                <td>
                  <table width="100%" border=0 cellpadding="4" cellspacing="1">
                    <tr>
                    <td class=formDeLabel nowrap>Provider Name</td>
                    <td class=formDe colspan="3"><bean:write name="serviceProviderForm" property="providerName"/></td>
                    </tr>
                    <tr>
                    	<td class=formDeLabel nowrap><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
                      	<td class=formDe><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/> </td>
                    	<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
                    	<td class=formDe> <bean:write name="serviceProviderForm" property="isInHouse"/> </td>                    	
                    </tr>
                     <tr>                      
                      	<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.admin" /> <bean:message key="prompt.userId" /></td>
                      	<td class=formDe colspan="3"> <bean:write property="adminContactId" name="serviceProviderForm"/> </td>
                    </tr>                    
                 	</table>
                </td>
              </tr>
            </table>
            <%-- END SP INFORMATION TABLE --%>

            <br>
            <table width="98%" cellpadding="0" cellspacing="0" border=0>
              <tr>
                <td width='100%' valign=top>
                  <table width="100%" cellpadding="2" cellspacing="0" class=borderTableBlue>
                    <tr class=detailHead>
                      <td> Service Provider </td>
                    </tr>
                    <tr>
                      <td>
                        <table width="100%" cellpadding="2" cellspacing="1">
                          <tr class=formDeLabel>                                                     
                            <td class="subhead" valign=top> Service Provider Name </td>
                            <td class="subhead" valign=top> In House </td>
                            <td class="subhead" valign=top>Status </td>
                            <td class="subhead" valign=top>Status Change Date </td>	                                
                          </tr>
                              <td>
	                              <bean:write name="serviceProviderForm" property="providerName"/> 
                              </td>
                              <td>
                              	<bean:write name="serviceProviderForm" property="isInHouse"/>	                             
							  </td>
                              <td>
                              	<logic:notEmpty name="serviceProviderForm" property="statusId">
                              		<logic:equal name="serviceProviderForm" property="statusId" value="A">
                              			ACTIVE
                              		</logic:equal>
                              	</logic:notEmpty>
                              	<logic:notEmpty name="serviceProviderForm" property="statusId">
                              		<logic:notEqual name="serviceProviderForm" property="statusId" value="A">
                              			INACTIVE
                              		</logic:notEqual>
                              	</logic:notEmpty>		
                               </td>
                              <td><bean:write name="serviceProviderForm" property="statusChangeDate" formatKey="datetime.format.mmddyyyyHHmmAMPM" />  </td>
                            </tr>

                        </table>
                      </td>
                    </tr>
                    
                  </table>
                </td>
              </tr>
            </table>

           
          
            <%-- BEGIN BUTTON TABLE --%>
            <br>
            <table border="0">
              <tr>
                <td>
                  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
                  
						<logic:equal name="serviceProviderForm" property="statusId" value="A">
							<logic:equal name="serviceProviderForm" property="actionType" value="reviewServiceProviderReactivate">
  								<html:submit property="submitAction" onclick="setAction('saveReactivatedSP');"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
  							</logic:equal>
  						</logic:equal>
  						
  						<logic:equal name="serviceProviderForm" property="statusId" value="A">
	  						<logic:equal name="serviceProviderForm" property="actionType" value="reactivateSPSuccess">
	  							<html:submit property="submitAction" onclick="setAction('returnToSPSearch');"><bean:message key="button.returnToServiceProvider"></bean:message></html:submit>
	  						</logic:equal>
  						</logic:equal>

					<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                </td>
              </tr>
            </table>
            <%-- END BUTTON TABLE --%>
          </td>
        </tr>
      </table><br>
    </td>
  </tr>
</table><br>
<%-- END  TABLE --%>
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>

