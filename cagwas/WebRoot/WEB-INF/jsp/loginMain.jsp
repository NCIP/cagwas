<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
//how did we get here?
if(session.getAttribute("ref")==null)	{
	//session.setAttribute("ref", request.getHeader("Referer"));
}
//out.print("ref: " + session.getAttribute("ref"));
%>
<div class="divHeader">
<bean:message key="header.login"/>
</div>

<div class="divMain">
<span class="er"><html:errors/></span>

<html:form action="login.do">
<table cellpadding="15">
	<tr><td style="width:350px;vertical-align:top;">
	<script type="text/javascript">Help.insertHelp("login_help", "", "padding:8px;float:right;");</script>
	
	<b>Registered Users:</b><br/>
	<table style="margin-left:20px;">
		<tr>
			<td><bean:message key="label.login.username"/></td>
			<td><html:text property="username"/></td>
		<tr>
		<tr>
			<td colspan="3"><span class="er"><html:errors property="username"/></span></td>
		</tr>
		<tr>
			<td><bean:message key="label.login.password"/></td>
			<td><html:password property="password"/></td>
		<tr>
		<tr>
			<td colspan="3"><span class="er"><html:errors property="password"/></span></td>
		</tr>
		<tr>
			<td colspan="3"><a href="forgottenPassword.jsp" class="title">Forgotten Password</a></td>
		</tr>
	</table>
	<div class="buttonsBar" style="text-align:left; margin-left:20px">
		<html:button property="btnReset" onclick="clearLoginForm()"><bean:message key="button.reset"/></html:button>
		<html:submit/>
	</div>
	</td>
	<td style="padding-left:20px;border-left:1px dashed black;vertical-align:top;">
	<script type="text/javascript">Help.insertHelp("registration_help", "", "padding:8px;float:right;");</script>
	
	<b>New Users:</b><br/><br/>
		<% String regUrl = (String)session.getAttribute("registrationUrl"); %>
		<input type="button" class="btn" value="Register for access" onclick="window.open('<%=regUrl%>');"/><br/>
		
<%/*
		<div id="reg" style="padding:20px;">
			Since the process for registered access to the raw genotype data and limited co-variates has not been formally approved by NIH, it is not yet possible currently to accept requests for registered access. We are working with the appropriate officials to resolve the issues. Please try back in the near future. If you want to send us your e-mail address to be notified when the solution is found, please send it to <a href="mailto:ncicb@pop.nci.nih.gov?subject=CaGWAS Registration">ncicb@pop.nci.nih.gov</a>
		</div>
*/%>
	</td>
</tr>
</table>
</html:form>
</div>