<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>

<!doctype html>
<html lang="nl">
<head>
 <fmt:message key='index.title' var="title"/>
 <vdab:head title="${title}"/>
</head>
<body>
<vdab:menu/>

<h1>${title}</h1>

<p><fmt:message key='theOrganisms'/></p>
<div class='imagesIndex'>
  <p><fmt:message key='plant'/></p>
  <img src='<c:url value='/images/plant.png'/>'/>
  <p><fmt:message key='herbivore'/></p>
  <img src='<c:url value='/images/herbivore.png'/>'/>
  <p><fmt:message key='carnivore'/></p>
  <img src='<c:url value='/images/carnivore.png'/>'/>
  <p><fmt:message key='omnivore'/></p>
  <img src='<c:url value='/images/omnivore.png'/>'/>
</div>
<p><fmt:message key='theRules'/></p>
<ul>
  <li><fmt:message key='rule1'/></li>
  <li><fmt:message key='rule2'/></li>
  <li><fmt:message key='rule3'/></li>
  <li><fmt:message key='rule4'/></li>
  <li><fmt:message key='rule5'/></li>
  <li><fmt:message key='rule6'/></li>
  <li><fmt:message key='rule7'/></li>
  <li><fmt:message key='rule8'/></li>
  <li><fmt:message key='rule9'/></li>
  <li><fmt:message key='rule10'/></li>
  <li><fmt:message key='rule11'/></li>
  <li><fmt:message key='rule12'/></li>
  <li><fmt:message key='rule13'/></li>
</ul>

<c:url value='/board.htm' var='new_random_game_url'>
  <c:param name='new' value='1'/>
</c:url>

<a href='${new_random_game_url}'><fmt:message key='newRandomGame'/></a>

<c:url value='/custom.htm' var='new_custom_game_url'/>
<a href='${new_custom_game_url}'><fmt:message key='newCustomGame'/></a>

<c:if test='${not empty board}'>
  <c:url value='/board.htm' var='current_game_url'/>
  <a href='${current_game_url}'><fmt:message key='currentGame'/></a>
</c:if>
<vdab:footer/>
</body>
</html>