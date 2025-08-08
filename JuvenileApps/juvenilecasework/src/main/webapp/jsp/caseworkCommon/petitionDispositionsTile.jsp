<!DOCTYPE HTML>

<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 06/28/2007	Uma Gopinath Create JSP --%>
<%-- 09/01/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

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
<div class=spacer></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
  	<td class=detailHead>Dispositions</td>
  </tr>
  <tr>
		<td>
      <table cellpadding=2 cellspacing=1 width='100%'>
      	<logic:empty name="petitionDetailsForm" property="dispositions"> 
          <tr class=detailHead>
        		<td colspan="4">No Dispositions available for this Petition.</td>
          </tr>
      	</logic:empty>
      	
				<logic:notEmpty name="petitionDetailsForm" property="dispositions"> 
      	  <tr class=formDeLabel>  
            <td valign=top><bean:message key="prompt.judgement"/> <bean:message key="prompt.date"/></td>
            <td valign=top><bean:message key="prompt.disposition"/></td>
            <td valign=top><bean:message key="prompt.dispositionDate"/></td>
            <td valign=top><bean:message key="title.dispositionOrder"/></td>			                
          </tr>			                      

  				<logic:iterate indexId="index" id="resultsIndex" name="petitionDetailsForm" property="dispositions" >
					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
		        <logic:present name="type">
							<logic:equal name="type" value="profile">					       
                <td valign=top><a href="/<msp:webapp/>displayJuvenileProfileDispositionDetails.do?submitAction=View&selectedSeqNum=<bean:write name="resultsIndex" property="seqNum"/>&selectedDALogNum=<bean:write name="resultsIndex" property="daLogNum"/>">
                   <bean:write name="resultsIndex" property="judgementDate" formatKey="date.format.mmddyyyy" /></a>
								</td>
              </logic:equal>
            </logic:present>

            <logic:notPresent name="type">
              <logic:notEqual name="type" value="profile">	
                <td valign=top><a href="/<msp:webapp/>displayJuvenileCasefileDispositionDetails.do?submitAction=View&selectedSeqNum=<bean:write name="resultsIndex" property="seqNum"/>&selectedDALogNum=<bean:write name="resultsIndex" property="daLogNum"/>">
                   <bean:write name="resultsIndex" property="judgementDate" formatKey="date.format.mmddyyyy" /></a>
								</td>
							</logic:notEqual>
            </logic:notPresent>
            <td valign=top><bean:write name="resultsIndex" property="disposition" /></td>
            <td valign=top><bean:write name="resultsIndex" property="dispositionDate" formatKey="date.format.mmddyyyy"/></td>
            <td valign=top><bean:write name="resultsIndex" property="seqNum" /></td>																								                    																			                 
          </tr>
         </logic:iterate>
				</logic:notEmpty>
			</table>
			<div class=spacer></div>
		</td>
	</tr>
</table>

 <!-- begin button table -->
<div class=spacer></div>
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr>
    <td align="center">
      <input type=button value='Back' onclick="goNav('back')">
      <logic:present name="type">
        <logic:equal name="type" value="profile">	
          <input type=button value=Cancel onClick="goNav('/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&notDetailed=true')">
        </logic:equal>
       </logic:present>
       <logic:notPresent name="type">
       		<input type=button value=Cancel onClick="goNav('/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&notDetailed=true')">
       </logic:notPresent>
    </td>
  </tr>
</table>
<div class=spacer></div>
<!-- end button table -->

	
            					

