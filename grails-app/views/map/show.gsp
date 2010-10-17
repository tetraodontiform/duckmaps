<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'map.label', default: 'Map')}" />
    <g:if env="production">
      <g:set var="jqueryPath" value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js" />
    </g:if>
    <g:else>
      <g:set var="jqueryPath" value="${resource(dir:'js',file:'jquery-1.4.2.js')}" />
    </g:else>
    <title>${fieldValue(bean: mapInstance, field: "name")}</title>
    <g:javascript data-path="${resource(dir:'js')}" src="svg.js" />
    <g:javascript src="require.js" />
    <g:javascript>
    require(["${jqueryPath}", "${resource(dir:'js', file:'duckmaps.js')}"],function() {
      $(function() {
        window.addEventListener('SVGLoad', function() {
          var duckMap = new DuckMap("planView", 300, 300);
        }, false);
      });
    });
    </g:javascript>
  </head>
  <body>
    <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    </div>
    <div class="body">
    <h1>${fieldValue(bean: mapInstance, field: "name")}</h1>
    <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
    <table>
    <tbody>
                
    <tr class="prop">
    <td valign="top" class="name"><g:message code="map.created.label" default="Created" /></td>
                            
    <td valign="top" class="value"><g:formatDate date="${mapInstance?.created}" /></td>
                            
    </tr>
                    
    <tr class="prop">
    <td valign="top" class="name"><g:message code="map.modified.label" default="Modified" /></td>
                            
    <td valign="top" class="value"><g:formatDate date="${mapInstance?.modified}" /></td>
                            
    <tr>
                    
    <tr class="prop">
    <td valign="top" class="name"><g:message code="map.plans.label" default="Plans" /></td>
                            
    <td valign="top" style="text-align: left;" class="value">
    <div id="planView"></div>
    </td>
                            
    </tr>
                    
    </tbody>
    </table>
    </div>
    <div class="buttons">
    <g:form>
    <g:hiddenField name="id" value="${mapInstance?.id}" />
    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
    </g:form>
    </div>
    </div>
  </body>
</html>
