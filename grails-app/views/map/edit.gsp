

<%@ page import="org.duckmaps.Map" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'map.label', default: 'Map')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${mapInstance}">
            <div class="errors">
                <g:renderErrors bean="${mapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${mapInstance?.id}" />
                <g:hiddenField name="version" value="${mapInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="map.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mapInstance, field: 'name', 'errors')}">
                                    <g:textArea name="name" cols="40" rows="5" value="${mapInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="created"><g:message code="map.created.label" default="Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mapInstance, field: 'created', 'errors')}">
                                    <g:datePicker name="created" precision="day" value="${mapInstance?.created}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="modified"><g:message code="map.modified.label" default="Modified" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mapInstance, field: 'modified', 'errors')}">
                                    <g:datePicker name="modified" precision="day" value="${mapInstance?.modified}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="plans"><g:message code="map.plans.label" default="Plans" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: mapInstance, field: 'plans', 'errors')}">
                                    <g:select name="plans" from="${org.duckmaps.Plan.list()}" multiple="yes" optionKey="id" size="5" value="${mapInstance?.plans*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
