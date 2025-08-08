<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/12/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 04/27/2009  C Shimek       - #59091 revised to allow multiple condition inputs -->
<!-- 09/29/2009  C Shimek       - #62238 correct js error in validateCompleteInputs -->
<!-- 12/11/2009  C Shimek       - #63059 revised validation message on condition for 1000 character max. -->
<!-- 02/02/2010  C Shimek       - #63339 corrected alert issue by changing tinyMCECustomInitMSO.js to tinyMCECustomInit.js. -->
<!-- 08/23/2010  D Williamson   - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>


<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - processSupervisionOrder/specialConditionCreate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm2" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<%--  <script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>  --%>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script> 

<script type="text/javascript">
function validateCompleteInputs(el ,theForm){
	var errMsg = "";  // line 618
	var desc = "";
	var inputFound = 0;
	for (var x = 0; x <theForm.length; x++) {
		if(theForm.elements[x].type == "textarea" && theForm.elements[x].name.indexOf("description") > 0){
			desc = theForm.elements[x].value;
			if (desc == ""){
			    if (theForm.elements[x+ 1].selectedIndex > 0 || theForm.elements[x+ 2].value != ""){
			    	indxVal = getIndxVal(theForm.elements[x].name) + 1;
			    	errMsg += "Condition literal #" + indxVal + " missing for Group/Notes entry.\n"; 
			    	inputFound = 1;
			    }
			} else {
				inputFound = 1;
				indxVal = getIndxVal(theForm.elements[x].name);
				result = validateDesc(desc, indxVal + 1);
				if (result != ""){
					errMsg += result + "\n";
				}
			}	
		}	   	
	}	
// Next value trapped in Action	
	if (inputFound == 0 ){
		errMsg = "At least 1 Condition entry required to continue.";
	}	
	if (errMsg == ""){
		return true;
	}
	alert(errMsg);
	return false;		
}
function getIndxVal(theValue){
	 descfld1 = theValue.split("[");
	 descfld2 = descfld1[1].split("]");
	 indxVal = parseInt(descfld2[0]);
	 return indxVal;
}
function validateDesc(el, x){
	var result = "";
	var descRegExp = /^[^%_]*$/;
	if (el.length < 10){
		result = "Condition Literal #" + x + " must be at least 10 characters.";
	} else if (el.length > 1000){
		result = "Condition Literal #" + x + " exceeded maximum input length.";
	} 
	if (result == "" && descRegExp.test(el,descRegExp) == false){
		result = "Condition Literal #" + x + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).";
	}	
	return result;		

}
</script>

</head>

<bean:define id="group1Caption" name="supervisionOrderForm" property="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="supervisionOrderForm" property="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="supervisionOrderForm" property="group3Caption" type="java.lang.String"/>
<bean:define id="literalCaption" name="supervisionOrderForm" property="conditionLiteralCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSpecialConditionSummary" target="content" onsubmit="myTinyMCEFix()">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|10">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width="98%">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.create" />&nbsp;<bean:message key="title.specialSupervisionCondition" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Enter Conditions. Click Next when done or click Add More Conditions if this order has more conditions.</li>
									</ul></td>
								</tr>
								<tr>
									<td class="required">At least 1 condition required.</td>												
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN SUPERVISION CONDITION TABLE -->                      
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead" colspan="2">
										<bean:message key="prompt.supervisionCondition" />
									</td>
								</tr>
							 	<% int x = 1; %>
							 	<tr>
							 		<td>
								<!-- BEGIN INPUT CONDITIONS TABLE -->		
										<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="true">
											<tiles:put name="agencyCode" beanName="supervisionOrderForm" beanProperty="agencyId"/>
										</tiles:insert>					 		
							 			<table width="100%" cellpadding="2" cellspacing="1">
							 				<nest:iterate id="displayConditionList" name="supervisionOrderForm" property="displayConditionList">
												<tr>
													<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message key="prompt.condition" /></td>
													<td class="formDe" align="center">
													<style>
														.mceEditor{															
															height: "130"
														}
													</style>  
														<nest:textarea  styleClass="mceEditor" style="width:100%" property="description" ondblclick="myReverseTinyMCEFix(this)"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
													<td class="formDe">
														<html:select name="displayConditionList" property="group1Id" size="1" indexed="true">
															<html:option value=""><bean:message key="select.generic" /></html:option>
															<html:optionsCollection name="supervisionOrderForm" property="group1List" value="groupId" label="name" />
														</html:select>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" valign="top"><bean:message key="prompt.notes" /></td>
													<td class="formDe"><nest:textarea rows="2" style="width:100%" name="displayConditionList" property="notes" indexed="true" onmouseout="textLimit( this, 1000 )" onkeyup="textLimit( this, 1000 )"/></td>
												</tr>
												<script>
													addDefinedTinyMCEFieldMask('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
													customValMinLength('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  must be at least 10 characters',10);
													customValMaxLength('<nest:writeNesting property="description"/>','Condition Literal # <%=x%>  exceeded maximum input length.',1000);
												</script>
												<% 	x++;  %>
											</nest:iterate>
										</table>	
						<!-- END INPUT CONDITIONS TABLE -->
									</td>
								</tr>
							 </table>
						<!-- END SUPERVISION CONDITION TABLE -->							 
							<br>                     
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">											
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateCustomStrutsBasedJS(this.form) && validateCompleteInputs(this, this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp; 
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>											
							</table>
						<!-- END BUTTON TABLE -->
								
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
         
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>