<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>

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

<!-- begin fees type table -->
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td class=detailHead>Fee Payment History</td>
  </tr>
  <tr>
  <td>
    <table cellpadding=2 cellspacing=1 width='100%'>
      <% int RecordCounter = 0; %>	
      <logic:iterate id="resultsIndex" name="petitionDetailsForm" property="feeReceipts" >
        <tr>
        	<td class=formDeLabel>Fee Type</td>
        	<logic:equal name="petitionDetailsForm" property="viewAllFeePayments" value="No">
        		<td class=formDe><bean:write name="resultsIndex" property="feeType"/></td>
        	</logic:equal>

        	<logic:equal name="petitionDetailsForm" property="viewAllFeePayments" value="Yes">
        	  <logic:present name="type">
              <logic:equal name="type" value="profile">
       					<td class=formDe><a href="/<msp:webapp/>handleJuvenileProfileFeeSelection.do?submitAction=View&selectedTransactionNum=<bean:write name="resultsIndex" property="transactionNum"/>&selectedCaseNum=<bean:write name="resultsIndex" property="petitionNum"/>&selectedCodeId=<bean:write name="resultsIndex" property="codeId"/>&viewAllFeePayments=No"><bean:write name="resultsIndex" property="feeType"/></a></td>
          		</logic:equal>
          	</logic:present>
            <logic:notPresent name="type">
        		 	<td class=formDe><a href="/<msp:webapp/>handleJuvenileCasefileFeeSelection.do?submitAction=View&selectedTransactionNum=<bean:write name="resultsIndex" property="transactionNum"/>&selectedCaseNum=<bean:write name="resultsIndex" property="petitionNum"/>&selectedCodeId=<bean:write name="resultsIndex" property="codeId"/>&viewAllFeePayments=No"><bean:write name="resultsIndex" property="feeType"/></a></td>
        		</logic:notPresent>
        	</logic:equal>

        	<td class=formDeLabel>Payor Type</td>
        	<td class=formDe><bean:write name="resultsIndex" property="payorType"/></td>
       </tr>
       <tr>
        	<td class=formDeLabel>Payor</td>
        	<td class=formDe colspan=3><bean:write name="resultsIndex" property="payor"/></td>
       </tr>
       <tr>
        	<td class=formDeLabel width='1%' nowrap>Payor Address</td>
        	<td class=formDe colspan=3><bean:write name="resultsIndex" property="payorAddress"/></td>
       </tr>
       <tr>
        	<td class=formDeLabel>Payor Phone</td>
        	<td class=formDe><bean:write name="resultsIndex" property="payorPhone"/></td>
        	<td class=formDeLabel>Petition #</td>
        	<td class=formDe><bean:write name="resultsIndex" property="petitionNum"/></td>
       </tr>
       <tr>
        	<td class=formDeLabel>Date Paid</td>
        	<td class=formDe><bean:write name="resultsIndex" property="paidDate" formatKey="date.format.mmddyyyy" /></td>
        	<td class=formDeLabel>Amount Paid</td>
        	<td class=formDe><bean:message key="prompt.dollarSign"/>
       			<bean:write name="resultsIndex" property="amtPaid" formatKey="currency.format"/>
					</td>
       </tr>
       <tr>
        	<td class=formDeLabel>Receipt #</td>
        	<td class=formDe><bean:write name="resultsIndex" property="receiptNum"/></td>
        	<td class=formDeLabel>Transaction #</td>
        	<td class=formDe><bean:write name="resultsIndex" property="transactionNum"/></td>
       </tr>
       <tr>
        	<td class=formDeLabel>Fee Status</td>
        	<td class=formDe><bean:write name="resultsIndex" property="feeStatus"/></td>
        	<td class=formDeLabel width='1%' nowrap>Date Received</td>
        	<td class=formDe><bean:write name="resultsIndex" property="receivedDate" formatKey="date.format.mmddyyyy" /></td>
        </tr>
    </logic:iterate>           								
    </table>
   </td>
  </tr>
</table>

<!-- end fees table -->


<!-- begin button table -->
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr>
    <td align="center">
      <input type=button value='Back' onclick="goNav('back');">

      <logic:present name="type">
        <logic:equal name="type" value="profile">                           
      		<input type=button value='View Fee Summary' onClick="goNav('/<msp:webapp/>displayJuvenileProfileFeeList.do')">
          <logic:notEqual name="petitionDetailsForm" property="viewAllFeePayments" value="Yes">
            <input type=button value='View All Payments' onClick="goNav('/<msp:webapp/>handleJuvenileProfileFeeSelection.do?submitAction=View&viewAllFeePayments=Yes')">
          </logic:notEqual>
         <input type=button value='Cancel' onClick="goNav('/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&notDetailed=true')">
        </logic:equal>
      </logic:present>

      <logic:notPresent name="type">
        <input type=button value='View Fee Summary' onClick="goNav('/<msp:webapp/>displayJuvenileCasefileFeeList.do')">
        <logic:notEqual name="petitionDetailsForm" property="viewAllFeePayments" value="Yes">
          <input type=button value='View All Payments' onClick="goNav('/<msp:webapp/>handleJuvenileCasefileFeeSelection.do?submitAction=View&viewAllFeePayments=Yes')">
        </logic:notEqual>
          <input type=button value='Cancel' onClick="goNav('/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&notDetailed=true')">
      </logic:notPresent>
    </td>
  </tr>
</table>
<!-- end button table -->

