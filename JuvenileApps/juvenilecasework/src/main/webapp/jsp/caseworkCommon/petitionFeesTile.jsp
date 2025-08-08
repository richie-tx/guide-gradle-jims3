<!DOCTYPE HTML>

<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>
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

	<!-- begin fee summary table -->
								
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
  	<td class=detailHead>Petition Fee Summary</td>
  </tr>
  <tr>
 		<td>
 			<table cellpadding=2 cellspacing=1 width='100%'>
 				<logic:empty name="petitionDetailsForm" property="feePayments"> 
          <tr class=detailHead>
        		<td colspan="4">No Fee Payments available for this Petition.</td>
           </tr>
        </logic:empty>

    		<logic:notEmpty name="petitionDetailsForm" property="feePayments"> 
          <tr class=formDeLabel>  
            <td class=subHead><bean:message key="prompt.feeType"/><jims:sortResults beanName="petitionDetailsForm" results="feePayments" primaryPropSort="feeType" primarySortType="STRING" sortId="2" /></td>
            <td class=subHead><bean:message key="prompt.dueDate"/><jims:sortResults beanName="petitionDetailsForm" results="feePayments" primaryPropSort="dueDate" secondPropSort="feeType" primarySortType="DATE" secondarySortType="STRING" sortId="1" defaultSort="true" defaultSortOrder="DESC"/></td>
            <td class=subHead><bean:message key="prompt.totalDue"/></td>
            <td class=subHead><bean:message key="prompt.status"/></td>
            <td class=subHead><bean:message key="prompt.currentBalance"/></td>
            <td class=subHead><bean:message key="prompt.totalPaid"/></td>
            <td class=subHead><bean:message key="prompt.date"/> <bean:message key="prompt.received"/></td>		                
          </tr>			                      

          <% int RecordCounter = 0; %>	
          <logic:iterate id="resultsIndex" name="petitionDetailsForm" property="feePayments" >
          <!-- Begin Pagination item wrap -->																										
    				<tr class=<%RecordCounter++;
						  String className = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
			        out.print(className);%>>
              <logic:present name="type">
                <logic:equal name="type" value="profile">
                  <td valign=top><a href="/<msp:webapp/>handleJuvenileProfileFeeSelection.do?submitAction=View&selectedTransactionNum=<bean:write name="resultsIndex" property="transactionNum"/>&selectedCaseNum=<bean:write name="resultsIndex" property="caseNum"/>&selectedCodeId=<bean:write name="resultsIndex" property="codeId"/>&viewAllFeePayments=No">  <bean:write name="resultsIndex" property="feeType"/></a></td>
                </logic:equal>
              </logic:present>

              <logic:notPresent name="type">
                <td valign=top><a href="/<msp:webapp/>handleJuvenileCasefileFeeSelection.do?submitAction=View&selectedTransactionNum=<bean:write name="resultsIndex" property="transactionNum"/>&selectedCaseNum=<bean:write name="resultsIndex" property="caseNum"/>&selectedCodeId=<bean:write name="resultsIndex" property="codeId"/>&viewAllFeePayments=No">  <bean:write name="resultsIndex" property="feeType"/></a></td>
              </logic:notPresent>
              <td valign=top><bean:write name="resultsIndex" property="dueDate" formatKey="date.format.mmddyyyy"/></td>
              <td valign=top><bean:message key="prompt.dollarSign"/>
                <bean:write name="resultsIndex" property="totalDue" formatKey="currency.format"/>
							</td>
              <td valign=top><bean:write name="resultsIndex" property="status" /></td>	
              <td valign=top><bean:message key="prompt.dollarSign"/>
                <bean:write name="resultsIndex" property="currentBalance" formatKey="currency.format"/>
							</td>	
              <td valign=top><bean:message key="prompt.dollarSign"/>
                <bean:write name="resultsIndex" property="totalPaid" formatKey="currency.format"/>
							</td>
              <td valign=top><bean:write name="resultsIndex" property="receivedDate" formatKey="date.format.mmddyyyy"/></td>																												                    																			                 
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </table>
      <div class=spacer></div>
    </td>
  </tr>                							
</table>
<!-- end fee summary table -->           					

<!-- begin button table -->
<div class=spacer></div>		
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr>
    <td align="center">
      <input type=button value='Back' onclick="goNav('back');">
      <logic:present name="type">
        <logic:equal name="type" value="profile">					                 
          <input type=button value='View All Payments' onClick="goNav('/<msp:webapp/>handleJuvenileProfileFeeSelection.do?submitAction=View&viewAllFeePayments=Yes')">
          <input type=button value='Cancel' onClick="goNav('/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&notDetailed=true')">
        </logic:equal>
      </logic:present>

      <logic:notPresent name="type">
        <input type=button value='View All Payments' onClick="goNav('/<msp:webapp/>handleJuvenileCasefileFeeSelection.do?submitAction=View&viewAllFeePayments=Yes')">
        <input type=button value='Cancel' onClick="goNav('/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&notDetailed=true')">
      </logic:notPresent>                             
    </td>
  </tr>
</table>
<!-- end button table -->
            					

