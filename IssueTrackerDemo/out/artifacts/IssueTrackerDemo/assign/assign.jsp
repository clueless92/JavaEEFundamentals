<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <title>Assign</title>
    </head>
    <body>
        <html:form action="/assign" styleId="formAssign">
            <html:hidden property="dispatch"/>
            <table>
                <tr>
                    <td>
                        User exact name*
                    </td>
                    <td>
                        <html:text property="userName" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue exact name*
                    </td>
                    <td>
                        <html:text property="issueName" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <html:button value="Assign" property="" onclick="postForm('assignIssueToUser')"/>
                    </td>
                </tr>
            </table>
        </html:form>

        <p style="color: green; size: 2pt">
            <logic:present name="success">
                Issue assigned to user successfully!
            </logic:present>
        </p>

        <p style="color: red; size: 2pt">
            <logic:present name="failure">
                Ooops, an error occurred!!
            </logic:present>
        </p>
        <br/><br/>
        <html:link href="${pageContext.request.contextPath}/"> Home </html:link>
        <script type="text/javascript">
            function postForm(method) {
                var form = document.getElementById('formAssign');
                form.elements["dispatch"].value = method;
                form.submit();
            }
        </script>
    </body>
</html>