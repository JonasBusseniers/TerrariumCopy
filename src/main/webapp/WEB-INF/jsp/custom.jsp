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
<form method="post" id="customForm">
<h3><fmt:message key="board" /></h3>
<label><span class="text"><fmt:message key='rows'/> (6-10)</span><input type="number" min="6" max="10" value="${empty param.rows ? 6 : param.rows}" name="rows" step="1" required></label>
<label><span class="text"><fmt:message key='cols'/> (6-10)</span><input type="number" min="6" max="10" value="${empty param.cols ? 6 : param.cols}" name="cols" step="1" required></label>
<br>
<h3><fmt:message key="plants" /></h3>
<label><span class="text"><fmt:message key='plantsStart' /></span><input type="number" value="${empty param.plantsstart ? 3 : param.plantsstart}" min="0" step="1" name="plantsstart"><span class="error">${fouten.boardIsFull}</span></label>
<label><span class="text"><fmt:message key='plantsEveryDay' /> (0-5)</span><input type="number"value="${empty param.plantseveryday ? 1 : param.plantseveryday}" min="0" max="5" step="1" name="plantseveryday"></label>
<br>
<h3><fmt:message key='herbivores' /></h3>
<label><span class="text"><fmt:message key='numberStart'/></span><input type="number"value="${empty param.herbivoresstart ? 3 : param.herbivoresstart}" min="0" step="1" name="herbivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<label><span class="text"><fmt:message key='minLife'/></span><input type="number"value="${empty param.herbivoresminlife ? 0 : param.herbivoresminlife}" min="0" step="1" name="herbivoresminlife"><span class="error">${fouten.herbMinMax}</span></label>
<label><span class="text"><fmt:message key='maxLife'/></span><input type="number"value="${empty param.herbivoresmaxlife ? 10 : param.herbivoresmaxlife}" min="0" step="1" name="herbivoresmaxlife"><span class="error">${fouten.herbMinMax}</span></label>
<br>
<h3><fmt:message key='carnivores' /></h3>
<label><span class="text"><fmt:message key='numberStart' /></span><input type="number"value="${empty param.carnivoresstart ? 3 : param.carnivoresstart}" min="0" step="1" name="carnivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<label><span class="text"><fmt:message key='minLife'/></span><input type="number"value="${empty param.carnivoresminlife ? 0 : param.carnivoresminlife}" min="0" step="1" name="carnivoresminlife"><span class="error">${fouten.carnMinMax}</span></label>
<label><span class="text"><fmt:message key='maxLife'/></span><input type="number"value="${empty param.carnivoresmaxlife ? 10 : param.carnivoresmaxlife}" min="0" step="1" name="carnivoresmaxlife"><span class="error">${fouten.carnMinMax}</span></label>
<br>
<input type="submit" name="play" value="<fmt:message key='play' />" id="customSubmit">
</form>

<script language="JavaScript">
    document.getElementsById(costumForm).onSubmit = function () {
        document.getElementsById(customSubmit).disabled = true;
    }
</script>

<vdab:footer/>

</body>
</html>