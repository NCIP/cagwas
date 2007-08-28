

<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/jsp/layoutNoSidebarThirdNav.jsp" flush="true">
  <tiles:put name="title" value="CaGWAS Support Page"/>
  <tiles:put name="htmlHead" value="/WEB-INF/jsp/tiles/htmlHead_tile.jsp" /> 
  <tiles:put name="overlib" value="/WEB-INF/jsp/tiles/overlib_tile.jsp" /> 
  <tiles:put name="header" value="/WEB-INF/jsp/header.jsp" /> 
  <tiles:put name="crumbMenu" value="/WEB-INF/jsp/tiles/crumbMenu_tile.jsp" /> 
  <tiles:put name="sideBar" value="/WEB-INF/jsp/tiles/sideBar_tile.jsp" /> 
  <tiles:put name="footer" value="/WEB-INF/jsp/footer.jsp" />
  <tiles:put name="secondNav" value="/WEB-INF/jsp/tiles/aboutSecondNav_tile.jsp" />	
  <tiles:put name="thirdNav" value="/WEB-INF/jsp/tiles/contactThirdNav_tile.jsp" />	
  <tiles:put name="mainForm" value="/WEB-INF/jsp/supportMain.jsp" />
</tiles:insert>