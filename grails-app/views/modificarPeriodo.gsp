<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:07
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Periodos</title>
</head>

<body>
<g:form params="[empresa:params.empresa]" class="form-horizontal" role="form" controller="modificarCuenta" action="save_cuenta">
    <div>
        <h2 class="panel-title">Agregar Cuenta:</h2></br>
    </div>
    <div class="form-group">
        <label for="inputCuenta" class="col-sm-2 control-label">Nueva cuenta</label>
        <div class="col-sm-3">
            <input id="inputCuenta" name = "nomCuenta" class="form-control" placeholder="Cuenta">
        </div>
        <button type="submit" class="btn btn-primary">Agregar</button>
    </div>
<div>
    <button type="submit" class="btn btn-primary">Agregar</button>
</div>
</g:form>

<g:form class="form-horizontal" role="form" controller="ModificarCuenta" action="index">
    <div>
        <h2 class="panel-title">Gestionar Cuentas:</h2></br>
    </div>
    <div class="form-group">
        <label for="selectCuenta" class="col-sm-2 control-label">Cuentas</label>
        <div class="col-sm-3">
            <select id="selectCuenta" name = "cuenta" class="form-control">
                <option value="" disabled selected hidden>Seleccione...</option>
    <g:each in="${empresas}">
        <option value="${it?.id}">${it?.nombre}</option>
    </g:each>
    </select>
</div>
    <div>
        <button type="submit" class="btn btn-primary">Modificar cuenta</button>
    </div>
    </div>
</g:form>

</body>
</html>