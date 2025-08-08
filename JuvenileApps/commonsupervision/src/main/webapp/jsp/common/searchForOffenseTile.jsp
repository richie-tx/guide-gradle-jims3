<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/20/2006	 Hien Rodriguez - Create JSP -->
<!-- 03/07/2008 #JIMS200049943 Richard Young Set Focus on levelId -->
<!-- 03/07/2008 #JIMS200049944 Richard Young Add sorting -->
<!-- 06/09/2008 Defect #JIMS200051828 Debbie Williamson Add validation -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title>Common Supervision - common/searchForOffenseTile.jsp</title>
<tiles:importAttribute name="levelDown"/>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">

var level = new Array()
	level["F"] = new Array();
	level["M"] = new Array();
	
	level["F"][0] = new Array();
	level["F"][0][0]="<bean:message key="prompt.selectALL" />";
	level["F"][0][1]="";

	level["F"][1] = new Array();
	level["F"][1][0]="1";
	level["F"][1][1]="1";	
	level["F"][2] = new Array();
		level["F"][2][0]="2";
		level["F"][2][1]="2";	
	level["F"][3] = new Array();
		level["F"][3][0]="3";
		level["F"][3][1]="3";
	level["F"][4] = new Array();
		level["F"][4][0]="Capital";
		level["F"][4][1]="C";
	level["F"][5] = new Array();
		level["F"][5][0]="State";
		level["F"][5][1]="S";
			
	level["M"][0] = new Array();
		level["M"][0][0]="<bean:message key="prompt.selectALL" />";
		level["M"][0][1]="";
	level["M"][1] = new Array();
		level["M"][1][0]="A";
		level["M"][1][1]="A";
	level["M"][2] = new Array();
		level["M"][2][0]="B";
		level["M"][2][1]="B";
	level["M"][3] = new Array();
		level["M"][3][0]="C";
		level["M"][3][1]="C";

var degreeSelectValue = "<bean:write name="offenseSearchForm" property="degreeId"/>"

//fills the drop down
function fillSelect(theSelect)
{	
	theForm = theSelect.form;
	var selectedVal=theSelect.options[theSelect.selectedIndex].value

	var selectSize = theForm.degreeId.options.length - 1;

		for (i=selectSize; i>=0; i--)
		{
			theForm.degreeId.options[i] = null;
		}

	if (selectedVal == "")
	{
		for (i=selectSize; i>=0; i--)
		{
			theForm.degreeId.options[i] = null;
		}
		theForm.degreeId.options[0] = new Option ("Please Select", "");
		theForm.degreeId.disabled = true;
	}else{
		theForm.degreeId.disabled = false;
		for (x=0; x<level[selectedVal].length; x++)
		{
			theForm.degreeId.options[x] = new Option (level[selectedVal][x][0], level[selectedVal][x][1]);
			
		}
		if (degreeSelectValue != ""){
			for (x=0; x<level[selectedVal].length; x++)
			{
				if(degreeSelectValue == level[selectedVal][x][1]){
					theForm.degreeId.options[x].selected = true;
				}
			}			
		}
	}
}

function performValidation(theForm){
 	clearAllValArrays();
		addNumericValidation("offenseCode","<bean:message key='errors.numeric' arg0='Offense Code'/>","");
		addAlphaNumericValidation("penalCode","<bean:message key='errors.alphanumeric' arg0='Penal Code'/>","");
		addSearchFieldValidation("offenseLiteral","<bean:message key='errors.alphanumeric' arg0='Offense Literal'/>","");
		customValMinLength("offenseLiteral","<bean:message key='errors.minlength' arg0='Offense Literal' arg1='2'/>","2");
		customValMaxLength("offenseLiteral","<bean:message key='errors.maxlength' arg0='Offense Literal' arg1='50'/>","50");
		addNumericValidation("stateOffenseCode","<bean:message key='errors.numeric' arg0='State Offense Code'/>","");
		return validateCustomStrutsBasedJS(theForm);
}
</script>


</head>

<body topmargin="0" leftmargin="0" onload="fillSelect(document.forms[0].levelId)">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message key="title.searchForOffense" /></td>
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
				<li>Enter at least 1 search criteria. Click Next.</li>
			</ul></td>
		</tr>
	</table>
<!-- END INSTRUCTION TABLE -->
	<table width=98% border="0">
		<tr>
			<logic:equal name="levelDown" value="3">
			<td class="required"><bean:message key="prompt.3.diamond"/>At Least 1 Required</td>	
			</logic:equal>
			<logic:notEqual name="levelDown" value="3">
			<td class="required"><bean:message key="prompt.2.diamond"/>At Least 1 Required</td>	
			</logic:notEqual>										
		</tr>
	</table>
<!-- BEGIN SEARCH OFFENSE TABLE -->	
             <table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
               	<tr>
                 	<td class=detailHead colspan="4">
                   		<table width=100% cellpadding=2 cellspacing=0>
                 		 	<tr>
                        	  <td class=detailHead colspan="3"><bean:message key="prompt.searchForOffenses" /></td>
                    		</tr>
                   		</table>
                 	</td>
               	</tr>				                    	
		<tr>
			<td>
				<table width=100% border="0" cellpadding="2" cellspacing="1">
                   	<tr>
						<td class="formDeLabel"><bean:message key="prompt.level" /></td>
						<td class=formDe>		
							<html:select name="offenseSearchForm" property="levelId" onchange="fillSelect(this)">
								<html:option value="">BOTH</html:option>
								<html:option value="F">FELONY</html:option>
								<html:option value="M">MISDEMEANOR</html:option>
							</html:select> 
				         </td> 
						<td class="formDeLabel"><bean:message key="prompt.degree" /></td>
						<td class=formDe>															
				       	  	<html:select disabled="true" name="offenseSearchForm" property="degreeId" >
						     	<html:option value=""><bean:message key="select.generic"/></html:option>								 								     
						  	</html:select>									
						</td>								
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
						<td class="formDe"><html:text property="offenseCode" maxlength="6" size="6"/></td>
						<td class="formDeLabel"  width=1% nowrap><bean:message key="prompt.penalCode" /></td>
						<td class="formDe"><html:text property="penalCode" maxlength="10" size="10" /></td>
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
						<td class="formDe" colspan="3"><html:text property="offenseLiteral" maxlength="50" size="50"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.stateOffenseCode" /></td>
						<td class="formDe" colspan="3"><html:text property="stateOffenseCode" maxlength="8" size="8"/></td>
					</tr>
					<tr>
						<td class="formDeLabel"></td>
						<td class="formDe" colspan="3">
							<html:submit property="submitAction" onclick="return performValidation(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit>
							<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit></td>												
					</tr>
				</table>
				</td>
			</tr>
		</table>
<!-- END SEARCH OFFENSE TABLE -->

            <!-- BEGIN SEARCH OFFENSE RESULT SECTION -->
            <logic:notEmpty name="offenseSearchForm" property="offenseResultList">
            <div><bean:size id="offenseListSize" name="offenseSearchForm" property="offenseResultList"/>
<b><bean:write name="offenseListSize"/></b> offense(s) found in search results</div>
            <table width="98%" border="0" cellspacing=1 cellpadding=2>
		<tr>
			<td>									
				<table width=100% cellpadding=2 cellspacing=0>
					<tr>
						<td class=detailHead><bean:message key="prompt.offenseSearchResults" /></td>
					</tr>
				</table>
				<bean:size id="offenseCount" name="offenseSearchForm" property="offenseResultList" />	
				<script type="text/javascript">
				renderScrollingArea(<bean:write name="offenseCount" />);									
				</script>
				<table width=100% cellpadding=2 cellspacing=1>
				<thead>
					<tr class="listHeader">
						<td width=1%></td>
						<td class="formDeLabel"><bean:message key="prompt.offenseCode" />
						<logic:equal name="levelDown" value="3">
						    <jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="offenseCodeId" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
						    <jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="offenseCodeId" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
						</logic:notEqual>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" />
							<logic:equal name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="description" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="description" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" />
						</logic:notEqual>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" />
							<logic:equal name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="stateCodeNum" primarySortType="INTEGER" defaultSort="false" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="stateCodeNum" primarySortType="INTEGER" defaultSort="false" defaultSortOrder="ASC" sortId="3" />
						</logic:notEqual>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.penalCode" />
						 	<logic:equal name="levelDown" value="3">
						 	<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="penalCode" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
						 	<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="penalCode" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" />
						</logic:notEqual>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.levelCode" />
							<logic:equal name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="level" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5"  levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="level" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5" />
						</logic:notEqual>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.degreeCode" />
							<logic:equal name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="degree" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="6"  levelDeep="3"/>
						</logic:equal>
						<logic:notEqual name="levelDown" value="3">
							<jims2:sortResults beanName="offenseSearchForm" results="offenseResultList" primaryPropSort="degree" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="6" />
						</logic:notEqual>
						</td>
					</tr>
					</thead>
					<tbody>
					<%int RecordCounter = 0;
					String bgcolor = "";%> 		
					<logic:iterate id="offenseResultListIndex" name="offenseSearchForm" property="offenseResultList">																
					<tr
						class=<%RecordCounter++;
							bgcolor = "alternateRow";
							if (RecordCounter % 2 == 1)
								bgcolor = "normalRow";
							out.print(bgcolor);%>>
						<td width=1%><input type="radio" name="selectedValue" value="<bean:write name="offenseResultListIndex" property="offenseCodeId" />"></td>
						<td><bean:write name="offenseResultListIndex" property="offenseCodeId" /></td>
						<td><bean:write name="offenseResultListIndex" property="description" /></td>
						<td><bean:write name="offenseResultListIndex" property="stateCodeNum" /></td>
						<td><bean:write name="offenseResultListIndex" property="penalCode" /></td>
						<td><bean:write name="offenseResultListIndex" property="level" /></td>
						<td><bean:write name="offenseResultListIndex" property="degree" /></td>
					</tr>
					</logic:iterate>
					</tbody>							
				</table>
				</div>
			</td>
		</tr>
	</table>
</logic:notEmpty>
<!-- END SEARCH OFFENSE RESULT SECTION -->
<!-- BEGIN BUTTON TABLE -->
	<table align=center width=98%>				
		<tr>
			<td align=center>
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
				 <logic:notEmpty name="offenseSearchForm" property="offenseResultList">
				<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select an Offense Code.') && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
				</logic:notEmpty>
				<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
			</td>
		</tr>					
	</table>						
<!-- END BUTTON TABLE -->


<script type="text/javascript" language="JavaScript">
  var focusControl = document.forms["offenseSearchForm"].elements["levelId"];

  if (focusControl.type != "hidden") {
     focusControl.focus();
  }
</script>
</body>
</html:html>


