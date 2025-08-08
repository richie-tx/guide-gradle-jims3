<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>
<%-- 09/01/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="ui.common.UIUtil" %>

<tiles:importAttribute name="familyFinancialInfo"/>

<table width="100%" cellpadding="2" cellspacing="1">
   <tr>
		 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.entryDate"/></td>
		 <td class="formDe" colspan=3><bean:write name="familyFinancialInfo" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
  </tr>
  <tr>
		 <td class="formDeLabel" nowrap><bean:message key="prompt.numOfDependents"/></td>
		 <td class="formDe" colspan="3"><bean:write name="familyFinancialInfo" property="numberOfDependents"/></td>
  </tr>
  <tr>
		 <td class="formDeLabel" nowrap><bean:message key="prompt.numLivingInHome"/></td>
     <td class="formDe"><bean:write name="familyFinancialInfo" property="numberLivingInHome"/></td>
     <td class="formDeLabel" nowrap><bean:message key="prompt.familyConstNum"/></td>
     <td class="formDe"><bean:write name="familyFinancialInfo" property="numberInFamily"/></td>
  </tr>
  <tr>
		 <td class="formDeLabel" nowrap><bean:message key="prompt.childSupportPayor"/></td>
		 <td class="formDe" colspan="3"><bean:write name="familyFinancialInfo" property="childSupportPayorName.formattedName"/></td>      
  </tr>

  <tr><td></td></tr>
  <tr><td></td></tr>

  <tr bgcolor="#AAAAAA">
    <td class="subhead" colspan="2"><bean:message key="prompt.assets" /></td>
    <td class="subhead" colspan="2"><bean:message key="prompt.liabilities" /></td>          
  </tr>                        
  <tr>	
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlyFoodStamps"/></td>
    <td class="formDe">
      <bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="foodStamps" formatKey="currency.format"/>
    </td>
    
    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.monthlyUtilities"/></td>	
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="utilitiesExpenses" formatKey="currency.format"/></td>
  </tr>
  <tr>
    <td class="formDeLabel" nowrap><bean:message key="prompt.realProperty"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="propertyValue" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlyLifeInsurancePremiums"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="lifeInsurancePremium" formatKey="currency.format"/></td>
  </tr>
  <tr>
    <td class="formDeLabel" nowrap><bean:message key="prompt.intangibleProperty"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="intangibleValue" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlySchoolSupplies"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="schoolExpenses" formatKey="currency.format"/></td>
  </tr>
  <tr>  
    <td class="formDeLabel" colspan="1"><bean:message key="prompt.monthlyTANF"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="tanfAfdc" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlyRent"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="rentExpenses" formatKey="currency.format"/></td>
  </tr>
  <tr>  
    <td class="formDeLabel" nowrap><bean:message key="prompt.otherIncome"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="otherIncome" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlyGroceryExpenses"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="groceryExpenses" formatKey="currency.format"/></td>
  </tr>
  <tr>
    <td class="formDeLabel"><bean:message key="prompt.savingsAccountBalances"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="savings" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.monthlyMedicalExpenses"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="medicalExpenses" formatKey="currency.format"/></td>
  </tr>
  <tr>
    <td class="formDeLabel"><bean:message key="prompt.monthlyChildSupportReceived"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="childSupportReceived" formatKey="currency.format"/></td>      
    <td class="formDeLabel"><bean:message key="prompt.monthlyChildSupportPaid"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="childSupportPaid" formatKey="currency.format"/></td>               
  </tr>
  <tr>
    <td class="formDeLabel" width="1%" nowrap>SSI</td>
    <td class="formDe"> <bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="ssi" formatKey="currency.format"/></td>
    <td class="formDeLabel" nowrap><bean:message key="prompt.totalMonthlyExpenses"/></td>
    <td class="formDe"><bean:message key="prompt.dollarSign"/>&nbsp;<bean:write name="familyFinancialInfo" property="totalExpenses" formatKey="currency.format"/></td>
  </tr>
</table>

