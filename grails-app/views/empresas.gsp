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
    <g:form class="form-horizontal" role="form" controller="Empresa" action="save">
        <div>
            <h2 class="panel-title">Agregar Empresa:</h2></br>
        </div>
        <div class="form-group">
            <label for="inputEmpresa" class="col-sm-2 control-label">Nueva empresa</label>
            <div class="col-sm-3">
                <input id="inputEmpresa" name = "nomEmpresa" class="form-control" placeholder="Empresa">
            </div>
            <button type="submit" class="btn btn-primary">Agregar</button>
        </div>
    </g:form>

    <g:form class="form-horizontal" role="form" controller="ModificarEmpresa">
        <div>
            <h2 class="panel-title">Gestionar Empresas:</h2></br>
        </div>
        <div class="form-group">
            <label for="selectEmpresa" class="col-sm-2 control-label">Empresas</label>
            <div class="col-sm-3">
                <select id="selectEmpresa" name = "empresa" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
                        <g:each in="${empresas}">
                            <option value="${it?.id}">${it?.nombre}</option>
                        </g:each>
                </select>
            </div>
            <div>
                <g:actionSubmit class="btn btn-primary" value="Agregar cuentas" action="agregarPeriodo"/>
                <g:actionSubmit class="btn btn-secondary" value="Ver cuentas" action="delete"/>
            </div>
        </div>
    </g:form>
</body>
</html>