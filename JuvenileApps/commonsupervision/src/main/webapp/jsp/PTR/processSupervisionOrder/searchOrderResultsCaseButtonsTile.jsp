<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Courts List-->
<!-- 08/08/2005		awidjaja	Create JSP -->

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
	show("Create OrderHistorical", 0);
	show("Defendant Signature Acquired", 0);
	show("Delete Order", 0);
	show("PrepareToFile Order", 0);
	show("Print Order", 0);
	show("PrintDraft Order", 0);
	show("PrintSpanish Order", 0);
	show("Reinstate Order", 0);
	show("Update Order", 0);
	show("View Order", 0);
	show("Withdraw Order", 0);
}

function renderButtonsInGrp(orderStatus){
	clearCaseButtons();
	switch (orderStatus){
		case "":
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_INACTIVE %>":
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_ACTIVE %>":
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE %>":
			show("Create Order", 1);
			show("Reinstate Order", 1);
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE_NOREINSTATE %>":
			show("Create Order", 1);
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INCOMPLETE %>":
			show("Continue Order", 1);
			show("Delete Order", 1);
			show("View Order", 1);
		  break
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_ACTIVE %>":
			show("Update Order", 1)
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_PENDING %>":
			show("Activate Order", 1);
			show("Defendant Signature Acquired", 1);
			show("Withdraw Order", 1);
			show("Update Order", 1)
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break;
		case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_NO_ORDERS %>":
			show("Create OrderHistorical", 1);
		  break;
		 case "<%=PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE + PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR + PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_DRAFT %>":
			show("PrepareToFile Order", 1);
			show("Defendant Signature Acquired", 1);
			show("Withdraw Order", 1);
			show("Update Order", 1)
			show("Print Order", 1);
			show("PrintSpanish Order", 1);
			show("View Order", 1);
		  break; 
		default:
			clearCaseButtons();
	}
}
</script>
	
		<table border="0">
		<tr  align="center">
			<td id="Create OrderHistorical"> 
				<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.createOrder"></bean:message></html:submit>
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.createHistoricalOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="Create Order"> 
				<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.createOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="Activate Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-ACTIVATE">	
					<html:submit  property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.activateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="PrepareToFile Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-PREPTOFILE">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.prepareToFile"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="Update Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-UPDATE">	
					<html:submit  property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.updateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="Withdraw Order"> 
				<jims2:isAllowed requiredFeatures="CS-PASO-WITHDRAW">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.withdrawOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="Reinstate Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-REINSTATE">	
					<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.reinstateOrder"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			
			<td id="Continue Order">
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
			</td>
			<td id="Delete Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-DELETE">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.delete"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
			<td id="View Order">
				<jims2:isAllowed requiredFeatures="CS-PASO-VIEW">	
					<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.viewOrderVersions"></bean:message></html:submit>
				</jims2:isAllowed>
			</td>
		</tr>
	</table>
	<table cellpadding=0 cellspacing=0>
		<tr align="center">
			<td id="Print Order">
				<html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.print"></bean:message></html:submit>
			</td>
			<td id="PrintDraft Order">
				<html:submit  property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.printDraft"></bean:message></html:submit>
			</td>
			<td id="PrintSpanish Order">
				<html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.printSpanish"></bean:message></html:submit>
			</td>
			<td id="Defendant Signature Acquired">
				<html:submit   property="submitAction" onclick="changeFormSettings(this.form, '', this, 'handleSupervisionOrderSelection.do');"><bean:message key="button.defendantSignatureAcquired"></bean:message></html:submit>
			</td>	
		</tr>
	</table>
	
	<script>
		clearCaseButtons();
	</script>

			<!-- END BUTTON TABLE FOR IE-->
								
								
								
		
								
								
								