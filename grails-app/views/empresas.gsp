<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:07
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cuentas</title>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="Empresas" action="save">
        <div>
            <h2 class="panel-title">Gestionar Empresa:</h2></br>
        </div>
        <div class="form-group">
            <label for="inputEmpresa" class="col-sm-1 control-label">Agregar nueva empresa</label>
            <div class="col-sm-10">
                <input id="inputEmpresa" name = "nomEmpresa" class="form-control" placeholder="Empresa">
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Agregar</button>
        </div>
    </g:form>

    <g:form class="form-horizontal" role="form" controller="ModificarEmpresa" action="index">
    <div class="form-group">
        <label for="selectEmpresa" class="col-sm-1 control-label">Empresas</label>
        <div class="col-sm-10">
            <select id="selectEmpresa" name = "empresa" class="form-control">
                <option value="" disabled selected hidden>Seleccione...</option>
                <g:each in="${empresas}">
                    <option value="${it?.id}">${it?.nombre}</option>
                </g:each>
            </select>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Modificar empresa</button>
    </div>
    </g:form>
</body>
</html>