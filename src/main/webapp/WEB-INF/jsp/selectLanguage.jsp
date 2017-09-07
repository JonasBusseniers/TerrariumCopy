<%@ page contentType='text/html' pageEncoding='UTF-8' session='true' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set value='${requestScope["javax.servlet.forward.request_uri"]}?${requestScope["javax.servlet.forward.query_string"]}' var='redirectURL'/>
<c:url value='/selectLanguage.html' var='nlBEURL'>
  <c:param name='language' value='nl'/>
  <c:param name='country' value='BE'/>
  <c:param name='redirectURL' value='${redirectURL}'/>
</c:url>
<c:url value='/selectLanguage.html' var='enUSURL'>
  <c:param name='language' value='en'/>
  <c:param name='country' value='US'/>
  <c:param name='redirectURL' value='${redirectURL}'/>
</c:url>
<div class="talen">
  <a href='${nlBEURL}'><img src="<c:url value="/images/BE.png"/>"></a>
  <a href='${enUSURL}'><img src="<c:url value="/images/US.png"/>"></a>
</div>