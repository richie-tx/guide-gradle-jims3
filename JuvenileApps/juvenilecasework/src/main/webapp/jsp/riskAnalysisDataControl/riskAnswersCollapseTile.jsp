<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- DWilliamson  12/10/2010	Create JSP Tile --%>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile collapses --%>
<%-- CShimek  04/24/2012	#73272 removed duplicate taglibs --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

<title><bean:message key="title.heading" /> - riskAnswerCollapseTile.jsp</title>

<tiles:useAttribute id="answerBoxTitle" name="answerBoxTitle"/>

<!-- BEGIN CURRENT ANSWER TABLE -->
  <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  	<tr>
    	<td class="detailHead">
	   		<table width="100%" cellpadding="2" cellspacing="0">
	          <tr>
	            <td class="detailHead">
			        <a href="javascript:showHideMulti('Answers', 'ans', 1, '/<msp:webapp/>')">
			   			<img border='0' src="/<msp:webapp/>images/expand.gif" name="Answers"/>
			   		</a>        
			   		<bean:write name="answerBoxTitle"/>
				</td>
	          </tr>
	        </table>
	   	</td>
	</tr>
    <tr id="ans0" class='hidden'>
    	<td>
		  <!-- BEGIN QUESTION TABLE -->
		    <tiles:useAttribute id="formName" name="formName"/>
		    <tiles:useAttribute id="answerListName" name="answerListName"/>
		    <tiles:useAttribute id="showAnswerButtons" name="showAnswerButtons"/>
		    <tiles:useAttribute id="showAnswerRadioButtons" name="showAnswerRadioButtons"/>
		    <tiles:useAttribute id="showAnswerNameAsReferenceLinks" name="showAnswerNameAsReferenceLinks"/>
		  	<tiles:insert page="riskAnswersInnerTile.jsp" flush="true">
		  	    <tiles:put name="formName" type="String" value="${formName}" />
			  	<tiles:put name="answerListName" type="String" value="${answerListName}" />
			  	<tiles:put name="showAnswerButtons" type="String" value="${showAnswerButtons}" />
			  	<tiles:put name="showAnswerRadioButtons" type="String" value="${showAnswerRadioButtons}" />
			  	<tiles:put name="showAnswerNameAsReferenceLinks" type="String" value="${showAnswerNameAsReferenceLinks}" />
			</tiles:insert>
			<%-- END QUESTION TABLE --%>
  		</td>
     </tr>
   </table>
   <!-- END ANSWER TABLE -->