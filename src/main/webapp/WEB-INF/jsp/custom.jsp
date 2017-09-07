<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>
<fmt:message key='Create custom game' var="titel" />
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="${titel}"/>
</head>
<body>
<vdab:menu/>

<h1>${titel}</h1>

<form method="post">
<label>Rows (6-10)<input type="number" min="6" max="10" value="6" name="rows" required></label>
<label>Columns (6-10)<input type="number" min="6" max="10" value="6" name="cols" required></label>
<br>
<label> Plants at start<input type="number"value="3" name="plantsstart"></label>
<label> Herbivores at start<input type="number"value="3" name="herbivoresstart"></label>
<label> Carnivores at start<input type="number"value="3" name="carnivoresstart"></label>
<br>
<label> Plants every day<input type="number"value="1" name="plantseveryday"></label>
<input type="submit" name="play" value="Pay">
</form>

<vdab:footer/>

</body>
</html>