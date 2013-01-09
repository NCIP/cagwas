<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<div class="divHeader">
	Access Warning
</div>
<div class="divMain" style="height:200px;">
<% String regUrl = (String)session.getAttribute("registrationUrl"); %>
<div align="center">
You do not have access to <strong><% out.println(session.getAttribute("studyName")); %>,	<% out.println(session.getAttribute("studyVersion")); %></strong>
<br>
If you would like to get access to this dataset, please click the <strong>Register to access raw data</strong> link below. <br>
Otherwise, go back and select another Study and Version.
<br><br>
<a href="javascript:history.back()">Go Back</a> | <a href="<%=regUrl%>" target="_blank">Register to access raw data</a>
<div>

</div>