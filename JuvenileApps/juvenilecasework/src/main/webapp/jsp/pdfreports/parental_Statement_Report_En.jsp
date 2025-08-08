<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reportInfo" class="messaging.interviewinfo.to.ParentalStatementReportDataTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.PARENTAL_STATEMENT_REPORT_EN.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.PARENTAL_STATEMENT_REPORT_EN.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .0in; 
		margin-bottom: .0in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:12;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
img.display
    {	display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;}
span.underline
	{	border-bottom:1px solid #555;}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="70%">
						Parental Written Statement - Page <pagenumber/> of <totalpages/>
					</td>
					<td align="left" width="30%">
						TJPC-AGE-10-04
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">		
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none"> 
		<tr>
			<td align="left"><img class="display" height="70" width="70" src="images/TexasJuvenileProbationCommisionSeal.jpg"/></td>
		</tr>
		<tr>
			<td align="center" width="100%" padding-bottom="5"><p class="arial-font-11"><b>Texas Juvenile Justice Department</b></p></td>
		</tr>
		<tr>
			<td align="center" width="100%" padding-bottom="5"><b>INSTRUCTIONS FOR PROBATION DEPARTMENTS REGARDING</b></td>
		</tr>
		<tr>
			<td align="center" width="100%" padding-bottom="15"><b>PARENTAL WRITTEN STATEMENTS</b></td>
		</tr>
	</table>	
</div>
<div class="body">
	<table width="100%" border-style="none" padding-top="5" padding-bottom="5">
		<tr align="center" padding-bottom="15">
			<td align="left" colspan="2">		
				<p class="arial-font-10"> Effective September 1, 2003, Section 61.104 of the Texas Family Code requires that a 
			        parent be given the opportunity to submit a written statement to the juvenile court 
			        judge concerning the disposition of their child&apos;s case.  The statute reads as follows:
                </p>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2">		
				<p class="arial-font-10"><b>&#167; 61.104.  PARENTAL WRITTEN STATEMENT</b></p>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%">(a)</td>
			<td align="left" width="96%">		
				<p class="arial-font-10"> When a petition for adjudication, a motion or petition to modify disposition, or a 
	                 motion or petition for discretionary transfer to criminal court is served on a parent 
	                 of the child, the parent must be provided with a form prescribed by the Texas Juvenile 
	                 Justice Department on which the parent can make a written statement about the needs of 
	                 the child or family or any other matter relevant to disposition of the case.
                </p>
    		</td>
		</tr>
		<tr align="center"  padding-bottom="15">
			<td align="left" width="4%">(b)</td>
			<td align="left" width="96%">		
   				 <p class="arial-font-10"> The parent shall return the statement to the juvenile probation department, which 
                    shall transmit the statement to the court along with the discretionary transfer report 
                    authorized by Section 54.02(e), the disposition report authorized by Section 54.04(b), 
                    or the modification of disposition report authorized by Section 54.05(e), as applicable.  
                    The statement shall be disclosed to the parties as appropriate and may be considered by 
                    the court at the disposition, modification, or discretionary transfer hearing.
                </p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-10">A parental written statement form shall be given to every parent who is summoned in a 
			        juvenile case and any other person the juvenile court feels should be given an opportunity 
			        to provide a parental written statement to the court.  This may include the child&apos;s guardian, 
			        custodian, guardian ad-litem or any other person the court determines is a necessary party 
			        to the juvenile&apos;s case.
				</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-10">Upon request by a parent, the parent must be provided with the Spanish version of the parental 
			        written statement form.  The Spanish version of the form is available from the Texas Juvenile 
			        Justice Department and may be obtained and downloaded from the Department&apos;s website 
			        at: <u>www.tjjd.texas.gov.</u>
				</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-10"> The juvenile board, juvenile court, prosecutor&apos;s office and/or the juvenile probation department 
			        should jointly develop local policies and procedures that address the following steps related 
			        to the parental written statement.
				</p>
			</td>
		</tr>
		<tr align="center"  padding-bottom="5">
			<td align="left" width="2%"> -</td>
			<td align="left" width="98%">		
				<p class="arial-font-10"> Provision of the parental written statement form to a parent or other 
		            person.  Local policy should address who in the probation department provides the form, 
		            when the form is provided and how (i.e., mail, with the summons, etc.).
                </p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="2%"> -</td>
			<td align="left" width="98%">		
				<p class="arial-font-10"> Completion of the heading of the parental written statement containing 
		            the cause number and other case identifying information.  Local policy should address 
		            who is responsible for completing the form&apos;s header information, if used.
                </p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="2%"> -</td>
			<td align="left" width="98%">		
				<p class="arial-font-10"> Provision of instructions for completion of the parental written 
		            statement, deadline for the return of the completed statement and delivery of the completed 
		            parental written statement to the proper juvenile probation department official.  
		            Local policy should address who will provide assistance and instructions to parents 
		            regarding the use of the form.
                </p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" width="2%"> -</td>
			<td align="left" width="98%">		
				<p class="arial-font-10"> Provision of copies of the completed parental written statement to 
		            the prosecutor, defense attorney and any other parties as determined appropriate by the juvenile 
		            court. Local policy should address who is responsible for ensuring all appropriate parties 
		            receive a copy of the completed statement in a timely manner.
                </p>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="2%"> -</td>
			<td align="left" width="98%">		
				<p class="arial-font-10"> Submittal of the completed parental written statement to the juvenile 
		            court judge along with any social history or other dispositional report.  Local policy should 
		            address who is responsible for providing the court a copy of the completed statement.
                </p>
    		</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none" padding-top="5" padding-bottom="5">
		<tr align="center">
			<td align="center" width="54%">
				<p align="center" class="arial-font-11"><b>Instructions for Making a Parental Written Statement</b></p>
				<p align="center" class="arial-font-11"><u><b>PARA RECIBIR ESTAS INSTRUCCIONES Y LA FORMA<br/>
	                EN ESPANOL PONGASE EN CONTACTO CON<br/>
	                EL DEPARTAMENTO JUVENIL NOMBRADO ABAJO</b></u> 
                </p>
                <p align="left" class="arial-font-9">
                	Texas Family Code Section 61.104 requires that the parent of a child who is accused of 
	                breaking the law and who will appear before a Texas juvenile court must be given the 
	                opportunity to give a parental written statement to the court.
                </p>
                <p align="left" class="arial-font-9">
                	 A parental written statement is your opportunity to tell the court information about 
	                your child&apos;s and family&apos;s needs, strengths or other important information related to 
	                your child&apos;s case.   The judge may consider this statement when making a final decision 
	                in your child&apos;s case.
                </p>
                <p align="left" class="arial-font-9">
                	A parental written statement may be submitted when any of the following legal documents 
                	have been filed in the juvenile court regarding your child:
                </p>
                <p align="left" class="arial-font-9">
                	- A petition for an adjudication hearing;<br/>
                	- A motion or petition for a modification hearing to change your child&apos;s<br/> 
                	probation conditions; or<br/>
                	- A motion or petition for a discretionary transfer hearing for possible transfer<br/>
                	of your child to a criminal court where he or she will be tried as an adult.<br/>
                	Any information you provide will NOT be used in the adjudication hearing which is a 
	                hearing to determine whether or not your child committed the offense.  It may be used 
	                in the disposition hearing which may assist the court in making a final disposition 
	                decision about your child&apos;s case that may include the following actions:
                </p>
                <p align="left" class="arial-font-9">
                	- Placing your child on probation in your home;<br/>
                	- Placing your child on probation outside your home with relatives or other persons;<br/>
                	- Placing your child on probation in a secure facility or non-secure residential<br/> 
                      facility for juveniles; or<br/>
                	- Commitment of your child to the Texas Juvenile Justice Department.<br/>
                	If both parents reside together in the home with your child, only one parental written 
	                statement per family may be submitted.  If you are divorced or separated, then both 
	                parents will be allowed to submit their own separate statements.
                </p>
                <p align="left" class="arial-font-9">
                	<u>A parental written statement is completely voluntary and you may choose not to make 
	                a statement in connection with your child&apos;s case.</u> You may choose to respond 
	                to any or all of the sections on this form.  Although examples are provided, you are not 
	                limited to those examples.  Your entire statement will be shared with the judge, the juvenile 
	                probation department, your child&apos;s defense attorney, the prosecutor and any other person 
	                the juvenile court determines should be provided with a copy.  Any information you submit 
	                to the court may be used against your child; therefore, you may want to consult with an 
	                attorney about the consequences of providing a statement to the juvenile court.  If you 
	                choose to provide a statement, you may submit it on your own paper and use any form you 
	                choose.  You may also attach sheets of paper to this form if you need additional space.  
	                When your statement is completed, please return it to the juvenile probation department 
	                at the address below.  If you return your statement to the juvenile probation department, 
	                the juvenile probation department will provide your statement to the juvenile court.
                </p>
                <p align="left" class="arial-font-9">
                	For any questions regarding the completion of the written parental statement you may 
	                contact the juvenile probation department listed below.  Upon completion of the written 
	                parental statement, please mail or hand-deliver this form on or before the deadline on 
	                the front page of the form to:
                </p>
                <p align="center" class="arial-font-9">
                	<%=reportInfo.getLocationUnitName()%><br/>
                	<%=reportInfo.getLocationAddress1()%><br/>
                	<%=reportInfo.getLocationAddress2()%><br/>             
                	<%=reportInfo.getFormattedPhoneNumber()%><br/>
                </p>
                <p align="left" class="arial-font-9">
                	<i>If you need an additional copy of these instructions or written parental statement, 
	                you may request it from the juvenile probation department.  This form is also available 
	                from the Texas Juvenile Justice Department on the Internet and may be downloaded at 
	                <u>www.tjjd.texas.gov.</u></i>
                </p>		
			</td>
			<td align="center" width="46%" border="2">		
				<p align="center" class="arial-font-10"><b>Common Terms Used in the Texas Juvenile Justice System</b></p>
				<p align="left" class="arial-font-9">
                	<b>Adjudication Hearing</b> - A hearing where the judge or jury determines 
                	whether your child committed the alleged offense or is innocent of the charge.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Defense Attorney</b> - A lawyer who represents your child and makes 
	                sure that your child&apos;s rights are protected.  The juvenile court must appoint an attorney to 
	                represent your child if the court finds that you cannot afford to hire an attorney.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Discretionary Transfer Hearing</b> - A hearing where the judge decides 
	                whether to transfer your child&apos;s case to a criminal court where your child will be prosecuted 
	                as an adult.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Disposition Hearing</b> - A separate hearing that occurs after the 
	                adjudication hearing when the final decision or result of your child&apos;s case is made by the 
	                judge or jury and where your child may be placed on probation or committed to the Texas 
	                Juvenile Justice Department.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Modification Hearing</b> - A hearing to change the previous conditions 
	                of your child&apos;s probation or to revoke his probation when your child is accused of violating 
	                the conditions of probation.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Petition/Motion</b> - A written legal document filed by the prosecutor 
	                that informs you and your child of the offense your child is alleged to have committed and 
	                provides the date, time and place of any future court dates.  You and your child must be given 
	                a copy of this document.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Prosecutor</b> - Lawyers who work for county, district or criminal district 
	                attorneys&apos; offices who handle juvenile cases.  The persons are usually referred to as District 
	                Attorney, D.A., Assistant District Attorney, County Attorney or Assistant County Attorney.
                </p>
                <p align="left" class="arial-font-9">
                	<b>Texas Juvenile Justice Department</b> - A state agency that operates secure correctional 
                	facilities for juveniles committed there by the juvenile court.
                </p>
    		</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10"><b>PLEASE COMPLETE AND RETURN THIS FORM TO THE JUVENILE PROBATION DEPARTMENT ON OR BEFORE <br/><%=reportInfo.getCourtDate()%>.</b></p>
			</td>
		</tr>
		<tr padding-bottom="10">
			<td align="center" colspan="3">		
				<p class="arial-font-10"><b>CAUSE NO. <%=reportInfo.getPetitionNumber()%></b></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b>IN THE INTEREST OF:</b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b>IN THE <%=reportInfo.getCourtName()%></b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b><%=reportInfo.getJuvenileName()%></b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b>HARRIS COUNTY, TEXAS</b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b>A JUVENILE</b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b></b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="center" colspan="3">		
				<p class="arial-font-10"><b>PARENTAL WRITTEN STATEMENT</b></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">I, ________________________________________________________________________, a parent or other 
					person responsible for the above named juvenile, submit this written statement as authorized under Texas Family Code 
					Section 61.104.  I would like to provide the following information so that the court can have my input for a better 
					understanding of the needs and strengths of my child, our family and any other information important to the disposition 
					of this case:
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">1.	<b>Background.</b>  The court should know the following information about my child&apos;s 
					background.  (Use this space to describe information such as important events, drug problems or hardships that you 
					believe have contributed to the circumstances your child is currently facing.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">2.	<b>Medical and Psychological.</b>  The court should know the following medical and psychological 
					history about my child.  (Use this space to describe any medical conditions, illnesses, physical disabilities, medication 
					needs, psychological history, psychological treatment or counseling, suicidal history, etc.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">3.	<b>Education.</b>   The court should know the following information about my child&apos;s school 
					history. (Use this space to describe information such as any achievements or problems your child may have experienced in 
					school including attendance, grades, special education, disciplinary problems, conflicts, extra-curricular activities, 
					awards, sports, special skills and work, etc.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">4.	<b>Home Environment.</b>  My child&apos;s home environment and family situation can best be 
					described as: (Use this space to tell the court about your child&apos;s home life and environment such as information 
					about how your child gets along with you, his or her brothers and sisters or any other person living at home, your 
					child&apos;s willingness to perform chores and other work around the house, behavior and discipline issues, whether your 
					child has a job that adds to the household income, positive or negative neighborhood influences, etc.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">5.	<b>Supervision.</b>  If the court places my child under my supervision, I plan to help my child 
					stay out of trouble while on probation by:  (Use this space to tell the court information such as your ability to supervise 
					your child, any additional supportive adults who are available to assist you, problems you may have providing supervision, 
					your plan to assist the probation department in making sure that your child will attend school and do everything the 
        			probation office requires, etc.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">6.	<b>Child&apos;s Positive Traits.</b>  The court should know the following good things about my 
					child. (Use this space to tell the judge information such as any special achievements, character traits, volunteer work, 
					past jobs, clubs, organizations, community or church activities in which your child participates.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">7.	<b>Recommended Outcome.</b>  When the court considers the final outcome of my child&apos;s case, 
					I recommend that the judge order the following:  (Use this space to tell the judge what you think should happen to your 
					child in this case.  What you feel your child&apos;s needs are.  You may also want to discuss what will happen to your 
					family if your child is removed from your custody and placed outside your home.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">8.	<b>Additional Information.</b> The court should know the following important information that 
					would be helpful to the court in making a final decision.  (Use this space to provide the judge with any information that 
					you feel may influence the judge when making a final decision.)
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">All the information I have provided to the court in this Parental Written Statement is true and 
					correct to the best of my knowledge and belief.
			    </p>
			</td>
		</tr>
		<tr align="center" >
			<td align="left" colspan="2">		
				<p class="arial-font-10">_________________________________________</p>
			</td>
			<td align="left">		
				<p class="arial-font-10">Date Signed: _____________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="2">		
				<p class="arial-font-10">Signature of Parent or Other Person</p>
			</td>
			<td align="left">		
				<p class="arial-font-10"></p>
			</td>
		</tr>
		<tr align="center" >
			<td align="left" colspan="2">		
				<p class="arial-font-10">_________________________________________</p>
			</td>
			<td align="left">		
				<p class="arial-font-10"></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="0">
			<td align="left" colspan="2">		
				<p class="arial-font-10">Printed Name of Parent or Other Person</p>
			</td>
			<td align="left">		
				<p class="arial-font-10"></p>
			</td>
		</tr>
	</table>
</div>
</body>
</pdf>