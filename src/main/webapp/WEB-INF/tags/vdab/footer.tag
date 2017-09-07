<%@tag description='footer' pageEncoding='UTF-8' %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename='resourceBundles.teksten'/>
<footer>
    <fmt:message key='footer' var="footer"/>
    <p>${footer}</p>
</footer>