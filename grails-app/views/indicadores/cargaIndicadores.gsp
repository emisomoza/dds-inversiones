<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:28
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Indicadores</title>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="Indicadores" action="save">
        <div>
            <h2 class="panel-title">Agregar Indicador</h2><br/>
        </div>
        <div class="form-group">
            <label for="inputNombre" class="col-sm-1 control-label">Nombre</label>
            <div class="col-sm-10">
                <input name="nombre" class="form-control" id="inputNombre" placeholder="Nombre">
            </div>
        </div>
        <div class="form-group">
            <label for="inputExpresion" class="col-sm-1 control-label">Expresion</label>
            <div class="col-sm-10">
                <input name="expresion" class="form-control" id="inputExpresion" placeholder="root(2, 2+2^4-(-4)+3)">
            </div>
        </div>
        <div class="form-group">
            <label for="inputExpresion" class="col-sm-1 control-label">Visibilidad</label>
            <div class="col-sm-10">
                <select class="form-control" name="visibilidad">
                    <option value="ROLE_NULL">Privado</option>
                    <g:if test="${userRoles.contains("ROLE_ADMIN")}">
                        <g:each var="rol" in="${userRoles}">
                            <option>${rol}</option>
                        </g:each>
                    </g:if>
                </select>
            </div>
        </div>
        <div>
            <g:actionSubmit class="btn btn-primary" value="Guardar" action="save"/>
            <g:actionSubmit class="btn btn-secondary" value="Ver indicadores" action="listarIndicadores"/>
        </div>
    </g:form>
</body>
</html>