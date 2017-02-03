<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <title>Search Users</title>
    </head>
    <body>
        <html:form action="/user" styleId="formSearch">
            <html:hidden property="dispatch" />
            <table>
                <tr>
                    <td>
                        User name:
                    </td>
                    <td>
                        <html:text property="name" size="20" />
                    </td>
                    <td >
                        <html:button value="Search" property="" onclick="postForm('searchUsers')" />
                    </td>
                </tr>
            </table>
        </html:form>

        <br/>
        <logic:notEmpty name="users" >
            <table width="25%" border="2px">
                <tr>
                    <th><b>Name</b></th>
                    <th><b>Issue Count</b></th>
                </tr>
                <logic:iterate name="users" id="user">
                    <tr>
                        <td>
                            <bean:write name="user" property="name" />
                        </td>
                        <td>
                            <bean:write name="user" property="issueCount" />
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>

        <br/><br/>
        <html:link href="${pageContext.request.contextPath}/">Home</html:link>
        <script type="text/javascript">
            function postForm(method) {
                var form = document.getElementById('formSearch');
                form.elements["dispatch"].value = method;
                form.submit();
            }
        </script>
    </body>
</html>