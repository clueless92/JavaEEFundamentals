<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <title>Create Issues</title>
    </head>
    <body>
        <html:form action="/issue" styleId="formAdd">
            <html:hidden property="dispatch"/>
            <table>
                <tr>
                    <td>
                        Issue name*
                    </td>
                    <td>
                        <html:text property="name" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue project
                    </td>
                    <td>
                        <html:text property="project" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue priority
                    </td>
                    <td>
                        <html:text property="priority" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue type
                    </td>
                    <td>
                        <html:text property="type" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue state
                    </td>
                    <td>
                        <html:text property="state" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue sprint
                    </td>
                    <td>
                        <html:text property="sprint" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Issue story points
                    </td>
                    <td>
                        <html:text property="storyPoints" size="20"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <html:button value="Create" property="" onclick="postForm('addIssue')"/>
                    </td>
                </tr>
            </table>
        </html:form>

        <p style="color: green; size: 2pt">
            <logic:present name="success">
                Issue added successfully!
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
                var form = document.getElementById('formAdd');
                form.elements["dispatch"].value = method;
                form.submit();
            }
        </script>
    </body>
</html>