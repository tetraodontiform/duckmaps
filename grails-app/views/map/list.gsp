
<%@ page import="org.duckmaps.Map" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'map.label', default: 'Map')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'map.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'map.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="created" title="${message(code: 'map.created.label', default: 'Created')}" />
                        
                            <g:sortableColumn property="modified" title="${message(code: 'map.modified.label', default: 'Modified')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${mapInstanceList}" status="i" var="mapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${mapInstance.id}">${fieldValue(bean: mapInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: mapInstance, field: "name")}</td>
                        
                            <td><g:formatDate date="${mapInstance.created}" /></td>
                        
                            <td><g:formatDate date="${mapInstance.modified}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${mapInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
