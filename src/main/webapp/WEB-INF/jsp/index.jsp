<%@page contentType='text/html' pageEncoding='UTF-8' session='true' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename='teksten'/>

<!DOCTYPE html>
<html>
<head>

</head>
<body>
 <c:url value='/board.htm' var='new_random_game_url'/>
 <a href='${new_random_game_url}'>New random game</a>

 <c:url value='/custom.htm' var='new_custom_game_url'/>
 <a href='${new_random_game_url}'>New custom game</a>

 <c:url value='/current.htm' var='current_game_url'/>
 <a href='${current_game_url}'>Current game</a>

</body>
</html>