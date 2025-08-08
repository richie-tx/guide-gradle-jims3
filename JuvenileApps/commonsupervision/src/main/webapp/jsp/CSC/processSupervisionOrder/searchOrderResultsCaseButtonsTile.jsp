<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Courts List-->
<!-- 08/08/2005	 awidjaja	Create JSP -->
<!-- 10/01/2008  Debbie Williamson - Defect#54283 Commented out Print Spanish Version button -->
<!-- 07/23/2009  C Shimek          - #61088 added edit to display withdraw order button for Pending/Amended orders  -->
<!-- 09/14/2009  C Shimek          - #61938 added edit/script to Print Order to validate existing order title on migrated order-->
<!-- 09/15/2009  C Shimek          - #61941 revised printSignature to use changeFormSettings() to action handleSupervisionOrderSelection.do -->
<!-- 10/13/2009  C Shimek          - #62421 revised isHistorical edit to positive in single Pending and single Draft case -->
<!-- 11/11/2009  C Shimek          - #62440 Historical to Pretrial Intervention -->
<!-- 10/08/2012  R Young           - #69236 PASO - set order from active to pending -->
<!-- 12/12/2012  R Capestani       - #74787 display View All Order Chain Versions when orderChainNum > 0 -->
<!-- 02/17/2014  Robert Carter     - #76655 do not show Create Order button if order is a NEW ORIGINAL order (only in CICS) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.PDCommonSupervisionConstants" %> 
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />


<script type="text/javascript">
// Kiran Krishnamurthy
// For PASO Printing functionality. 
//
//
//  THIS PAGE MUST HAVE THE FOLLOWING INCLUDED IN THE PARENT : <script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"/>
// 
//
//
function check4Migrated(theForm)
{
	var radioBtns=document.getElementsByName("primaryCaseOrderKey");
	if (radioBtns.length > 0)
	{
		for (x=0; x<radioBtns.length; x++){
			if (radioBtns[x].checked == true)
			{
				var hidfld = document.getElementsByName(radioBtns[x].value);
				if (hidfld.length > 0 && hidfld[0].value == ""){
					alert("Migrated order cannot be printed.  Create or update the order in JIMS before printing.");
					return false;
				}	
			}		
		}	
	}
	return true;	
}

function changeFormSettings(theForm, theTargetString, button, theActionString)
{
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);
	changeFormTarget(theForm,theTargetString) ;
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	disableSubmit(button, theForm);
}

function clearCaseButtons(){
	show("Activate Order", 0);
	show("Continue Order", 0);
	show("Create Order", 0);
	show("Create OrderPretrialIntervention", 0);
	show("Defendant Signature Acquired", 0);
	show("Delete Order", 0);
	show("PrepareToFile Order", 0);
	show("Print Order", 0);
	show("PrintDraft Order", 0);
	show("Print Signature", 0);
	<%-- show("PrintSpanish Order", 0);--%>
	show("Reinstate Order", 0);
	show("Update Order", 0);
	show("Reset Order", 0);
	show("View Order", 0);
	show("View All Order", 0);
	show("Withdraw Order", 0);
}

function renderButtonsInGrp(orderStatus,isPretrialIntervention,orderChainNum, outOfCounty, version){
	clearCaseButtons();
	switch (orderStatus){
		case "":
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_INACTIVE %>":
			show("Print Order", 1, "inline");
			
		<%--	show("PrintSpanish Order", 1, "inline"); --%>
	
			show("View Order", 1, "inline");
			if(orderChainNum > 1){
			  show("View All Order", 1, "inline");
			}
			
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_ACTIVE %>":
			show("Print Order", 1, "inline");
		<%-- 	show("PrintSpanish Order", 1, "inline"); --%>
			show("View Order", 1, "inline");
			if(orderChainNum > 1){
			  show("View All Order", 1, "inline");
			}
			if (version < 2){
				 show("Reset Order", 1, "inline");
			}
			
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE %>":
			show("Create Order", 1, "inline");
			show("Reinstate Order", 1, "inline");
			show("Print Order", 1, "inline");
		<%--	show("PrintSpanish Order", 1, "inline");--%>
			show("View Order", 1, "inline");
			if(orderChainNum > 1){
			  show("View All Order", 1, "inline");
			}
			
			break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE_NOREINSTATE %>":
			show("Create Order", 1, "inline");
			show("Print Order", 1, "inline");
		<%--	show("PrintSpanish Order", 1, "inline"); --%>
			show("View Order", 1, "inline");
			if(orderChainNum > 1){
			  show("View All Order", 1, "inline");
			}
			
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INCOMPLETE %>":
			show("Continue Order", 1, "inline");
			show("Delete Order", 1, "inline");
			show("View Order", 1, "inline");
			if(orderChainNum > 1){
			  show("View All Order", 1, "inline");
			}
			show("Print Signature",1,"inline");
			
		  break
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_ACTIVE %>":
			show("Create Order", 1, "inline");
			show("Update Order", 1, "inline");
			show("Print Order", 1, "inline");
		<%--	show("PrintSpanish Order", 1, "inline"); --%>
			show("View Order", 1, "inline");
			if( orderChainNum > 1 ){
			  show("View All Order", 1, "inline");
			}
			if (version < 2){
				 show("Reset Order", 1, "inline");
			}
			
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_PENDING %>":
			show("Activate Order", 1, "inline");
			if(!(isPretrialIntervention=='true' || isPretrialIntervention=='TRUE')) {
	//			if (document.forms[0].userPosition.value != "CSO") {
	//				show("Defendant Signature Acquired", 1);
	//			}
	//			if (document.forms[0].userPosition.value == "CSO" && document.forms[0].selectedValue.value == ""){	
					show("Defendant Signature Acquired", 1, "inline");
	//			}
			}
			show("Update Order", 1, "inline")
			show("Print Order", 1, "inline");
			// added this edit 07/23/09
			if (typeof(document.forms[0].hasWithdrawFeature) != "undefined"){
				show("Withdraw Order", 1, "inline");
			}
		<%--	show("PrintSpanish Order", 1, "inline"); --%>
			
			show("View Order", 1, "inline");
			if(isPretrialIntervention=='true' || isPretrialIntervention=='TRUE'){	
				if(orderChainNum > 1){
			  	show("View All Order", 1, "inline");
				}
			}
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_NO_ORDERS %>":
			show("Create OrderPretrialIntervention", 1, "inline");
		  	break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_DRAFT %>":
			if(outOfCounty=='true' || outOfCounty=='TRUE'){
				show("Activate Order", 1, "inline");
			}
			else{
				show("PrepareToFile Order", 1, "inline");
			}

			show("Withdraw Order", 1, "inline");
			show("Update Order", 1, "inline")
			show("Print Order", 1, "inline");
			show("Print Signature", 1, "inline");
		<%--	show("PrintSpanish Order", 1); --%>
			show("View Order", 1, "inline");
			if(isPretrialIntervention=='true' || isPretrialIntervention=='TRUE'){
				if (orderChainNum > 1){
			  	show("View All Order", 1, "inline");
				}
			}
		  break; 
		default:
			clearCaseButtons();
	}
}
</script>
	
		<table border="0">
		<tr>
		<td align=center>	
			<span id="Create OrderPretrialIntervention">
				<!--  Only show the CREATE ORDER BUTTON for NEW ORIGINAL orders if selectedValue of search is NOT SPN --> 
				<logic:notEqual name="supervisionOrderSearchForm" property="selectedValue" value="SPN">		
					<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
						<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.createOrder"></bean:message></html:submit>
					</jims2:isAllowed>
				</logic:notEqual>
				</span>
			<span id="Create Order"> 
				<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.createOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="Activate Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-ACTIVATE">	
					<html:submit  property="submitAction" onclick="return changeFormSettingsWithConfirm(this.form, '', this, 'handleSupervisionOrderSelection.do', 'Are you sure you want to activate this order?');"><bean:message key="button.activateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="PrepareToFile Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-PREPTOFILE">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.prepareToFile"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="Update Order">			
				<jims2:isAllowed requiredFeatures="CS-PASO-UPDATE">	
					<html:submit  property="submitAction" onclick="return changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.updateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="Reset Order">             
                <jims2:isAllowed requiredFeatures="CS-PASO-UPDATE">   
                      <html:submit  property="submitAction" onclick="return changeFormSettingsWithConfirm(this.form, '', this, 'handleSupervisionOrderSelection.do', 'Are you sure you want to change this order back to Pending?');"><bean:message key="button.setToPending"></bean:message></html:submit>
                </jims2:isAllowed>
            </span>
			<span id="Withdraw Order"> 
				<jims2:isAllowed requiredFeatures="CS-PASO-WITHDRAW">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.withdrawOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="Reinstate Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-REINSTATE">	
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.reinstateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			
			<span id="Continue Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
					<bean:define id="showCont" value="true"/> 
				</jims2:isAllowed>
				<logic:notPresent name="showCont">
					<jims2:isAllowed requiredFeatures="CS-PASO-UPDATE">
						<bean:define id="showCont" value="true"/> 
					</jims2:isAllowed>
				</logic:notPresent>
				<logic:present name="showCont">
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.continueOrder"></bean:message></html:submit>
				</logic:present>
			</span>
			<span id="Delete Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-DELETE">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.delete"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="View Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-VIEW">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.viewOrderVersions"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			<span id="View All Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-VIEW">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.viewAllOrderVersions"></bean:message></html:submit>
				</jims2:isAllowed>
			</span>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<tr>
		<td align=center>
			<span id="Print Order">				
				<html:submit property="submitAction" onclick="return check4Migrated(this.form) && changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.print"></bean:message></html:submit>
			</span>
			<span id="Print Signature">				
				<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.printSignature"></bean:message></html:submit>
			</span>
			<span id="PrintDraft Order">
				<html:submit  property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.printDraft"></bean:message></html:submit>
			</span>
			<span id="Defendant Signature Acquired">
				<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.defendantSignatureAcquired"></bean:message></html:submit>
			</span>	
			</td>
		</tr>
		
	</table>
	
	<script>
		clearCaseButtons();
	</script>

	<!-- END BUTTON TABLE FOR IE-->
								