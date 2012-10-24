<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<body>

<fieldset>
<form:form id="form" method="post" modelAttribute="weatherInfo">
<span>Current City:</span>
<form:input path="city" />

<p><button type="submit">Get Weather</button></p>
<span>Current Weather:</span>
<form:input path="weather" />
</form:form>
</fieldset>
</body>
</html>