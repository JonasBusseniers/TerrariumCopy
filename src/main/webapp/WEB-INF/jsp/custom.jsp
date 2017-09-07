<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>
<fmt:message key='createCustomGame' var="title" />
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="${title}"/>
</head>
<body>
<vdab:menu/>

<h1>${title}</h1>

<div id="msg">${fouten.number}</div>
<form method="post">
<label><span class="text"><fmt:message key='rows'/> (6-10)</span><input type="number" min="6" max="10" value="${empty param.rows ? 6 : param.rows}" name="rows" step="1" required></label>
<label><span class="text"><fmt:message key='cols'/> (6-10)</span><input type="number" min="6" max="10" value="${empty param.cols ? 6 : param.cols}" name="cols" step="1" required></label>
<br>
<label><span class="text"><fmt:message key='plantsStart' /></span><input type="number" value="${empty param.plantsstart ? 3 : param.plantsstart}" min="0" step="1" name="plantsstart"><span class="error">${fouten.boardIsFull}</span></label>
<label><span class="text"><fmt:message key='herbivoresStart'/></span><input type="number"value="${empty param.herbivoresstart ? 3 : param.herbivoresstart}" min="0" step="1" name="herbivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<label><span class="text"><fmt:message key='carnivoresStart' /></span><input type="number"value="${empty param.carnivoresstart ? 3 : param.carnivoresstart}" min="0" step="1" name="carnivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<br>
<label><span class="text"><fmt:message key='plantsEveryDay' /> (0-5)</span><input type="number"value="${empty param.plantseveryday ? 1 : param.plantseveryday}" min="0" max="5" step="1" name="plantseveryday"></label>
<input type="submit" name="play" value="<fmt:message key='play' />">
</form>

<vdab:footer/>

</body>
</html>