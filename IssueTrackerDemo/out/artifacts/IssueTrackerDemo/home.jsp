<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
        <title>Issue Tracker Demo</title>
    </head>
    <body>
        <h2>
            <bean:write name="homeForm" property="greeting"/>
        </h2>
        <ul>
            <li>
                <html:link href="${pageContext.request.contextPath}/user.do?dispatch=goToSearchPage">
                    Search user
                </html:link>
            </li>
            <li>
                <html:link href="${pageContext.request.contextPath}/user.do?dispatch=goToAddPage">
                    Create user
                </html:link>
            </li>
            <li>
                <html:link href="${pageContext.request.contextPath}/issue.do?dispatch=goToSearchPage">
                    Search issue
                </html:link>
            </li>
            <li>
                <html:link href="${pageContext.request.contextPath}/issue.do?dispatch=goToAddPage">
                    Create issue
                </html:link>
            </li>
            <li>
                <html:link href="${pageContext.request.contextPath}/assign.do?dispatch=goToAssignPage">
                    Assign issue to user
                </html:link>
            </li>
        </ul>
    </body>
</html>
