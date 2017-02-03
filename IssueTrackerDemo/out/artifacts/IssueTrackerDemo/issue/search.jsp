<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <title>Search Issues</title>
    </head>
    <body>
        <html:form action="/issue" styleId="formSearch">
            <html:hidden property="dispatch" />
            <table>
                <tr>
                    <td>
                        Issue name:
                    </td>
                    <td>
                        <html:text property="name" size="20" />
                    </td>
                    <td >
                        <html:button value="Search" property="" onclick="postForm('searchIssues')" />
                    </td>
                </tr>
            </table>
        </html:form>

        <br/>
        <logic:notEmpty name="issues" >
            <table width="100%" border="2px">
                <tr>
                    <th><b>Name</b></th>
                    <th><b>Project</b></th>
                    <th><b>Priority</b></th>
                    <th><b>Type</b></th>
                    <th><b>State</b></th>
                    <th><b>Sprint</b></th>
                    <th><b>Story Points</b></th>
                    <th><b>Assignee Count</b></th>
                </tr>
                <logic:iterate name="issues" id="issue">
                    <tr>
                        <td>
                            <bean:write name="issue" property="name" />
                        </td>
                        <td>
                            <bean:write name="issue" property="project" />
                        </td>
                        <td>
                            <bean:write name="issue" property="priority" />
                        </td>
                        <td>
                            <bean:write name="issue" property="type" />
                        </td>
                        <td>
                            <bean:write name="issue" property="state" />
                        </td>
                        <td>
                            <bean:write name="issue" property="sprint" />
                        </td>
                        <td>
                            <bean:write name="issue" property="storyPoints" />
                        </td>
                        <td>
                            <bean:write name="issue" property="assigneeCount" />
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