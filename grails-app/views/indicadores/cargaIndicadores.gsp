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
            <h2 class="panel-title">Agregar Indicador</h2></br>
        </div>
        <div class="form-group">
            <label for="nombre" class="col-sm-1 control-label">Nombre</label>
            <div class="col-sm-10">
                <input id="nombre" class="form-control" id="inputNombre" placeholder="Nombre">
            </div>
        </div>
        <div class="form-group">
            <label for="expresion" class="col-sm-1 control-label">Expresion</label>
            <div class="col-sm-10">
                <input id="expresion" class="form-control" id="inputExpresion" placeholder="Expresion">
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </g:form>
</body>
</html>