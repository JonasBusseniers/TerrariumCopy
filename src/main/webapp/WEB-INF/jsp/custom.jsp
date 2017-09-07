<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>
<fmt:message key='Create custom game' var="title" />
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
<label>Rows (6-10) <input type="number" min="6" max="10" value="6" name="rows" step="1" required></label>
<label>Columns (6-10) <input type="number" min="6" max="10" value="6" name="cols" step="1" required></label>
<br>
<label>Plants at start <input type="number"value="3" min="0" step="1" name="plantsstart"><span class="error">${fouten.boardIsFull}</span></label>
<label>Herbivores at start <input type="number"value="3" min="0" step="1" name="herbivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<label>Carnivores at start <input type="number"value="3" min="0" step="1" name="carnivoresstart"><span class="error">${fouten.boardIsFull}</span></label>
<br>
<label>Plants every day (0-5) <input type="number"value="1" min="0" max="5" step="1" name="plantseveryday"></label>
<input type="submit" name="play" value="Pay">
</form>

<vdab:footer/>

</body>
</html>