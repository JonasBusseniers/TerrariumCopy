<%@tag description='menu' pageEncoding='UTF-8' %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename='resourceBundles.teksten'/>
<header>
    <div class='languageSelection'><c:import url='/WEB-INF/jsp/selectLanguage.jsp'/></div>
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>">Home</a></li>
            <li><a href="<c:url value="/board.htm?new=1"/>"><fmt:message key='newRandomGame'/></a></li>
            <li><a href="<c:url value="/custom.htm"/>"><fmt:message key='newCustomGame'/></a></li>
            <c:if test='${not empty terrarium}'>
                <a href='<c:url value="/board.htm"/>'><fmt:message key='currentGame'/></a>
            </c:if>
    </nav>
</header>