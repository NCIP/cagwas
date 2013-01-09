<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<div class="divHeader">
	Criteria Warning
</div>
<div class="divMain" style="height:200px;">

<div align="center">
None of the filter criteria is selected on query page.
If you would like to get the entire dataset, please click the “bulk download” link below. Otherwise, go back and resubmit the query with filter criteria.
<br><br>
<% String bulkUrl = (String)request.getAttribute("bulkUrl"); %>
<a href="#" onClick="javascript:history.go(-2); return false;">Go Back to Browse</a> | <a href="downloadSetup.do">Bulk Data Download</a>
<div>

</div>