<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja Create JSP -->
<!-- 01/20/2006  C Shimek      Defect #27425, made group1, group2 and group3 titles dynamic --> 
<!-- 03/31/2006  H Rodriguez   Defect#300113 display correct labels for each agency --> 
<!-- 10/23/2007  C Shimek      Defect #46036 added id to hidden td tags for group1, group2 and group 3 to identify focus --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<head>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/groups.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<title>Common Supervision - common/associationBody.jsp</title>
</head>

<tiles:useAttribute id="formActionName" name="formActionName" classname="java.lang.String" />
<tiles:importAttribute name="form" />

<tiles:importAttribute name="pageTitle" />
<tiles:importAttribute name="pageInstructionText" />

<tiles:importAttribute name="searchTitle" />
<tiles:importAttribute name="searchResultIter" />
<tiles:importAttribute name="searchTableHeadingName" />
<tiles:importAttribute name="viewURL" />

<tiles:importAttribute name="selectedTitle" />
<tiles:importAttribute name="selectedResultIter" />
<tiles:importAttribute name="selectedTableHeadingName" />
<tiles:importAttribute name="removeURL"/>

<tiles:importAttribute name="group1Caption" />
<tiles:importAttribute name="group2Caption" />
<tiles:importAttribute name="group3Caption" />
<bean:define id="group1Caption" name="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="group3Caption" type="java.lang.String"/>




<script language="javascript">
<logic:iterate indexId="groupIterIndex" id="groupIter" name="form" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	
	
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
		
		</logic:iterate>
	</logic:iterate>
</logic:iterate>

function validateFields(theForm){
clearAllValArrays();
customValMask("assocSearchCriteria.objName","<bean:message key='errors.alphanumericWSymbols' arg0='Name'/> ", "/^([a-zA-Z0-9\.\\\\';,()\x26\x2f\-]{1})([a-zA-Z0-9  \.\\\\';,()\x26\x2f\-]*)([*]?)$/");

return validateCustomStrutsBasedJS(theForm);
}


function callme()
{
alert();
	var group1Id = document.getElementById("group1Id").options[document.getElementById("group1Id").selectedIndex].value;
	var group2Id = document.getElementById("group2Id").options[document.getElementById("group2Id").selectedIndex].value;
	
	
	//if(document.getElementById("group2Id").selectedIndex == 0)
	//	document.getElementById("group3Id").disabled = true;
	//else
	//document.getElementById("group3Id").disabled = false;
		
	document.getElementById("group3Id").options.length = 0;
	document.getElementById("group3Id")[0] = new Option( "Please Select", "", false, false );
	
	for(i in groups)
	{
		if(groups[i].id == group1Id)
		{
			for(j in groups[i].subgroups)
			{
				if(groups[i].subgroups[j].id == group2Id)
				{
					for(k in groups[i].subgroups[j].subgroups)
					{
						document.getElementById("group3Id").options[document.getElementById("group3Id").options.length] = new Option( groups[i].subgroups[j].subgroups[k].name, groups[i].subgroups[j].subgroups[k].id);
					}
				}
			}
		}
	}
	
}
</script>

<html:form action="<%=formActionName%>" target="content">
<!-- <input type="hidden" name="submitAction" value=""> -->
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<!--  MAIN BODY TAB HEADING -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!-- MAIN TAB HEADING  -->
						<tiles:insert page="commonSupervisionTabs.jsp" flush="true"/>
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			
			<!--  MAIN BODY -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!--  CONTENT HEADING -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<bean:write name="pageTitle"/>
								</td>
							</tr>
						</table>
						<!-- END CONTENT HEADING -->
						
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul>
									<li><bean:write name="pageInstructionText"/></li>
								</ul></td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<!--  SEARCH PARAMETERS -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:write name="searchTitle"/></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="1" cellpadding="2">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=group1Caption%>" /></td>
											<td class="formDe"><bean:write name="form" property="assocSearchCriteria.group1Name"/>
											<td id="g1" class="hidden">
												<select id="group1Id" name="group1Id" size="1">
													<option value="">Please select</option>
													<option value="<bean:write name='form' property='assocSearchCriteria.group1Id'/>" selected="true"><bean:write name='form' property='assocSearchCriteria.group1Name'/></option>
												</select>
												
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=group2Caption%>" /></td>
											<td class="formDe">
												<logic:empty name="form" property="group2Name">
													<html:select styleId="group2Id" name="form" property="assocSearchCriteria.group2Id" disabled="false" onchange="updateGroup3(this.form);">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													</html:select>
													
												</logic:empty>
												<logic:notEmpty name="form" property="group2Name">
													<bean:write name="form" property="assocSearchCriteria.group2Name"/>
													<td id="g2" class="hidden">
														<select id="group2Id" name="group2Id" size="1">
															<option value="">Please select</option>
															<option value="<bean:write name='form' property='assocSearchCriteria.group2Id'/>" selected><bean:write name='form' property='assocSearchCriteria.group2Name'/></option>
														</select>
													</td>
												</logic:notEmpty>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=group3Caption%>" /></td>
											<td class="formDe">
												<logic:empty name="form" property="group3Name">
													<html:select styleId="group3Id" name="form" property="assocSearchCriteria.group3Id" disabled="false">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													</html:select>
												</logic:empty>
												<logic:notEmpty name="form" property="group3Name">
													<bean:write name="form" property="assocSearchCriteria.group3Name"/>
													<td id="g3" class="hidden">
														<select id="group3Id" name="group3Id" size="1">
															<option value="">Please select</option>
															<option value="<bean:write name='form' property='assocSearchCriteria.group3Id'/>" selected><bean:write name='form' property='assocSearchCriteria.group3Name'/></option>
														</select>
													</td>
												</logic:notEmpty>
											</td>
										</tr>
										
										<logic:empty name="form" property="group2Name">
											<script>updateGroup2(document.forms[0]);</script>
										</logic:empty>
										<logic:empty name="form" property="group3Name">
											<script>updateGroup3(document.forms[0]);</script>
										</logic:empty>
										
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.name" /></td>
											<td class="formDe"><html:text name="form" property="assocSearchCriteria.objName" size="50" maxlength="50"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap></td>
											<td class="formDe">
												<html:submit property="submitAction"  onclick="return validateFields(this.form)"><bean:message key="button.filter"/></html:submit>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>
						<!-- END SEARCH PARAMETERS -->

						<!--  SEARCH RESULTS -->
						<tiles:insert page="searchResultObjects.jsp" flush="true">
							<tiles:put name="form" beanName="form" />
							<tiles:put name="tableHeadingName" beanName="searchTableHeadingName" />
							<tiles:put name="beanIter" beanName="searchResultIter" />
							<tiles:put name="viewURL" beanName="viewURL" />
							<tiles:put name="group2Caption" beanName="group2Caption"/>
							<tiles:put name="group3Caption" beanName="group3Caption"/>
						</tiles:insert>
						
						<!--  SELECTED ITEMS -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.addSelected"/></html:submit>
								</td>
							</tr>
						</table>
						<br>
						<tiles:insert page="associateObjects.jsp" flush="true">
							<tiles:put name="form" beanName="form" />
							<tiles:put name="tableHeadingName" beanName="selectedTableHeadingName" />
							<tiles:put name="title" beanName="selectedTitle" />
							<tiles:put name="beanIter" beanName="selectedResultIter" />
							<tiles:put name="mode" value="edit" />
							<tiles:put name="removeURL" beanName="removeURL" />
							<tiles:put name="group2Caption" beanName="group2Caption"/>
							<tiles:put name="group3Caption" beanName="group3Caption"/>
						</tiles:insert>
						<!-- END SELECTED ITEMS -->
						<br>	
						
					</td>
				</tr>
				
				<tr>
					<td>
						<table border="0" width="100%" id="buttonTableComplete">
						  	<tr>
						    	<td align="center">
						    		<input type="button" value="Back" name="return" onClick="history.go(-1)">
									<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
						      		<input type="button" value="Cancel" name="return" onClick="history.go(-1)">
						    	</td>
						  	</tr>
						</table>
					</td>
				</tr>
			</table><br>
			<!-- END MAIN BODY -->
		</td>
	</tr>
</table>
</div>
</html:form>

<br>

<div align="center">
	<script type="text/javascript">renderBackToTop()</script>
</div>

