<!DOCTYPE HTML>
<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>
<%-- 09/01/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

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

<tiles:importAttribute name="petitionDetailsForm"/>   
<tiles:useAttribute id="type" name="type" classname="java.lang.String" ignore="true" />

<!-- begin disposition table -->
<logic:present name="type">
  <logic:equal name="type" value="profile">
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  </logic:equal>

  <logic:notEqual name="type" value="profile">
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	</logic:notEqual>
</logic:present>

<logic:notPresent name="type">
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
</logic:notPresent>
  <tr>
    <td valign=top align='center'>
			<div class=spacer></div>
  		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  	  	<tr>
  		    <td class=detailHead>Disposition Detail - Petition #: <bean:write name="petitionDetailsForm" property="petitionNum"/></td>
        </tr>
        <tr>
  				<td valign=top align=center>
  					<table cellpadding="4" cellspacing="1" width='100%'>
  					  <tr valign=top>
  							<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.dispositionDate"/></td>
  							<td class=formDe colspan=3><bean:write name="petitionDetailsForm" property="dispositionRec.dispositionDate" formatKey="date.format.mmddyyyy"/></td>
  					  </tr>
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.disposition"/></td>
  							<td class=formDe colspan=3><bean:write name="petitionDetailsForm" property="dispositionRec.disposition"/></td>
  					  </tr>
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.judgement"/> <bean:message key="prompt.date"/></td>
  							<td class=formDe colspan=3><bean:write name="petitionDetailsForm" property="dispositionRec.judgementDate" formatKey="date.format.mmddyyyy"/></td>
  					  </tr>
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.judgement"/></td>
  							<td class=formDe colspan=3><bean:write name="petitionDetailsForm" property="dispositionRec.judgement"/></td>
  					  </tr>
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.probationMonths"/></td>
  							<td class=formDe><bean:write name="petitionDetailsForm" property="dispositionRec.probationMonths"/></td>
  							<td class=formDeLabel nowrap><bean:message key="prompt.tycMonths"/></td>
  							<td class=formDe><bean:write name="petitionDetailsForm" property="dispositionRec.tycMonths"/></td>
  					  </tr>
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.deviationReason"/></td>
  							<td class=formDe><bean:write name="petitionDetailsForm" property="dispositionRec.deviationReason"/></td>
  							<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.assignedSanction"/></td>
  							<td class=formDe><bean:write name="petitionDetailsForm" property="dispositionRec.assignedSanction"/></td>
  					  </tr>
  
  					  <tr valign=top>
  							<td class=formDeLabel nowrap><bean:message key="prompt.guidelineSanction"/></td>
  							<td class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="dispositionRec.guidelineSanction"/></td>
							</tr>
  					</table>
  				</td>
				</tr>
			</table>

      <!-- BEGIN BUTTON TABLE -->
  		<table border="0" cellpadding=1 cellspacing=1 align=center>
  		  <tr>
  		    <td align="center">
            <input type=button value='Back' onclick="goNav('back')">
            <logic:present name="type">
              <logic:equal name="type" value="profile">	
                <input type=button value=Cancel onClick="goNav('/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&nonDetailed=true')">
       				</logic:equal>
       			</logic:present>
  
     			  <logic:notPresent name="type">
     			  	<input type=button value=Cancel onClick="goNav('/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&nonDetailed=true')">
     			  </logic:notPresent>
          </td>
        </tr>
      </table>
      <!-- END BUTTON TABLE -->
    </td>
  </tr>
</table>
	
            					

