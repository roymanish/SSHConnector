<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Register</title>
</head>
<body>
 
<h2>Registration Screen</h2>
 <a href="${pageContext.request.contextPath}/login">Go Back</a>
<form:form method="post" action="addUser" commandName="user">
 
    <table>
    <tr>
        <td><form:label path="userName"><spring:message code="label.username"/></form:label></td>
        <td><form:input path="userName" /></td>
    </tr>
    <tr>
        <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td><form:label path="role"><spring:message code="label.role"/></form:label></td>
        <td><form:input path="role" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.adduser"/>"/>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>