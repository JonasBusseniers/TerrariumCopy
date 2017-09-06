<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='teksten'/>
<fmt:message key='Terrarium' var="titel"/>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="${titel}"/>
</head>
<body>
<vdab:menu/>

<h1>${titel}</h1>

<div class="container">
    <div class="raster">
			<c:forEach var='organism' items='${organismsMap}'>
				<div id='${organismsMap.key}'><img src='${organismsMap.value.url}'/></div>
			</c:forEach>
    </div>
</div>

<div id="msg"></div>  <!-- toon fouten hier -->
<input id="NextDay" type="button" value="Next day.">

<vdab:footer/>

</body>
</html>