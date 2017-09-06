<%@tag description='menu' pageEncoding='UTF-8' %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<header>
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>">Index</a></li>
            <li><a href="<c:url value="/board.htm?new=1"/>">New random game</a></li>
            <li><a href="<c:url value="/custom.htm"/>">New custom game</a></li>
            <li><a href="<c:url value="/board.htm"/>">Current game</a></li>
        </ul>
    </nav>
</header>