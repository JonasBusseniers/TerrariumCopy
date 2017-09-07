<%@tag description='menu' pageEncoding='UTF-8' %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<header>
    <div class='languageSelection'><c:import url='/WEB-INF/jsp/selectLanguage.jsp'/></div>
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>">Home</a></li>
            <li><a href="<c:url value="/board.htm?new=1"/>">New random game</a></li>
            <li><a href="<c:url value="/custom.htm"/>">New custom game</a></li>
            <c:if test='${not empty terrarium}'>
                <a href='<c:url value="/board.htm"/>'>Current game</a>
            </c:if>
    </nav>
</header>