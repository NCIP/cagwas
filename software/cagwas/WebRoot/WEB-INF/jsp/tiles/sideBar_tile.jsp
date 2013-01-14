<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<script type='text/javascript' src='js/common/SidebarHelper.js'/></script>

<style>
	#sidebar div b {
		border-bottom: 1px solid #0101A9;
	}
</style>

<div id="sidebar">
	<div style="text-align:left; margin-top:20px;">
		<ul>
			<li><a href="about.jsp"><b>About</b></a></li>
			<li><a href="browseSetup.do"><b>Browse Data</b></a></li>
			<li><a href="downloadSetup.do"><b>Bulk Data Download</b></a></li>
			<li><a href="mailto:cagwasfeedback@mail.nih.gov"><b>Feedback</b></a></li>
		</ul>
	</div>
</div>
<script language="javascript">
	SidebarHelper.loadSidebar();
</script>