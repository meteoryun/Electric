<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE>国家电力监测中心首页</TITLE>
<LINK href="/Electric/css/Font.css" type="text/css" rel="stylesheet">

<STYLE>BODY {
	SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-BASE-COLOR: #dee3f7
}
</STYLE>

<SCRIPT type="text/javascript">
function submitrequest(action){
eval("document.location='"+action+"'");
}

</SCRIPT>
</HEAD>

<FRAMESET border="0" frameSpacing="0" rows="82,*" frameborder="no" id="mainparent">
	<FRAME name=topFrame src="${pageContext.request.contextPath }/menu/title.action" noResize scrolling=no>
		<FRAMESET id="main" border="0" frameSpacing="0" frameBorder="no" cols="153,1%,*">
			<FRAME name="leftFrame" src="${pageContext.request.contextPath }/menu/left.action" noResize>
			<frame name="changeButton" src="${pageContext.request.contextPath }/menu/change.action" frameBorder="0" marginHeight="0" marginWidth="0" scrolling="no" noresize>
			<FRAME name="mainFrame" src="${pageContext.request.contextPath }/menu/loading.action" >
		</FRAMESET>
</FRAMESET>


</HTML>
