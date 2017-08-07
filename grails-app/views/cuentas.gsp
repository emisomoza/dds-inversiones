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
    <g:form class="form-horizontal" role="form" controller="Cuentas" action="save">
        <div>
            <h2 class="panel-title">Gestionar Cuentas</h2></br>
        </div>
        <div class="form-group">
            <label for="inputEmpresa" class="col-sm-1 control-label">Nombre Empresa</label>
            <div class="col-sm-10">
                <input id="inputEmpresa" name = "empresa" class="form-control" placeholder="Empresa">
            </div>
        </div>
        <div class="form-group">
            <label for="inputcuenta" class="col-sm-1 control-label">Nombre Cuenta</label>
            <div class="col-sm-10">
                <input id="inputCuenta" name = "cuenta" class="form-control" placeholder="Cuenta">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPeriodo" class="col-sm-1 control-label">Períodos</label>
            <div class="col-sm-10">
                <input id="inputPeriodo" name = "periodo" class="form-control" placeholder="Períodos">
            </div>
        </div>
        <div class="form-group">
            <label for="inputImporte" class="col-sm-1 control-label">Importe</label>
            <div class="col-sm-10">
                <input id="inputImporte" name = "importe" class="form-control" placeholder="Importe">
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </g:form>
</body>
</html>