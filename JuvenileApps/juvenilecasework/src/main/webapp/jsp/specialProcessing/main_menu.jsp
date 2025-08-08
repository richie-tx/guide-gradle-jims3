<!DOCTYPE HTML>
<%-- Used to display main menu for production support --%>
<%--MODIFICATIONS --%>
<%-- mlawles	Create JSP --%>
<%-- 08/29/2013 ugopinath	modify JSP --%>
<%-- 04/24/2014 rcarter	remove new window code and change look and feel. Added changes to support Bootstrap --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
 

<head>
    <!--PAGE META DECLARATION-->
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta name="ROBOTS" content="NOINDEX">
	<title>Juvenile Casework -/specialProcessing/main_menu.jsp</title>
	<!--JQUERY FRAMEWORK-->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
     <STYLE type="text/css">    
    #pagediv {
		 max-width: 1200px;
		 margin: 0 auto;
	 }
 	</STYLE>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	<!--BOOTSTRAP CSS FRAMEWORK-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <!--FONT AWESOME FRAMEWORK-->
    <link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <!--GOOGLE FONT FRAMEWORK-->
    <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.5.2/webfont.js"></script>
    <script>
              WebFont.load({
                     google: {
                           families: ['Roboto', 'Open Sans']
                     }
              });
     </script>
     <!--GLOBAL APPLICATION JAVASCRIPT INJECTION-->
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript">
	
	function openMainPopupMenu(){	
		 var webApp = "/<msp:webapp/>";
		 var url = webApp + "displaySpecialProcessingMainPopup.do";
		 var newWin = window.open(url, "pictureWindow", "height=1300,width=1070,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
			newWin.focus();
		return false;
	}
	
	function runOnPageLoad(){
		document.getElementById('popupButton').click();
	}
	</script>
<html:base />
</head>
<body class="ContentFrameInjection" onload="runOnPageLoad();">
<br/>
<br/>
<table align="center" width="75%" border="1" cellpadding="10">
	<div id='pagediv' class="panel panel-default vertical-center">
		<h2 align="center">
		Special Processing Main Menu
		</h2>
		<div id='pagediv' class="panel panel-default vertical-center">
			<div class="panel-body">
				<div class="row"> &nbsp;
				</div>
				<div class="row"> &nbsp;
				</div>
				<div class="row">  
					<div class="panel-heading text-center">
						<html:form method="post" action="/displaySpecialProcessingMainPopup.do">
							<html:submit styleId="popupButton" value="Open Special Processing Screen" onclick="return openMainPopupMenu()"/>
						</html:form>
					</div>
				</div>
			</div> 
		</div> 
	</div>
</table>
</body>
</html:html>
