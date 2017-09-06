<%@page contentType='text/html' pageEncoding='UTF-8' %>
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

<h1>${titel}</h1>

<c:url value='/board.htm' var='new_random_game_url'>
  <c:param name='new' value='1'/>
</c:url>

<a href='${new_random_game_url}'>New random game</a>

<c:url value='/custom.htm' var='new_custom_game_url'/>
<a href='${new_custom_game_url}'>New custom game</a>

<c:url value='/board.htm' var='current_game_url'/>
<a href='${current_game_url}'>Current game</a>

<vdab:footer/>
</body>
</html>