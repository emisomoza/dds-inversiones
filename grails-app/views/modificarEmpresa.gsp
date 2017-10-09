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
<g:form params="[empresa:params.empresa]" class="form-horizontal" role="form" controller="modificarEmpresa" action="save_periodo">
    <div>
        <h2 class="panel-title">Modificando empresa: ${empresa.nombre}</h2></br>
    </div>
    <div class="form-group">
        <label for="inputPeriodoDesde" class="col-sm-1 control-label">Desde</label>
        <div class="col-sm-10">
            <g:datePicker id="inputPeriodoDesde" name="fechaDesde" value="${new Date()}" precision="month" years="${1970..2020}"/>
        </div>
    </div>
    <div class="form-group">
        <div>
            <label for="inputPeriodoHasta" class="col-sm-1 control-label">Hasta</label>
        </div>
        <div class="col-sm-10">
            <g:datePicker id="inputPeriodoHasta" name="fechaHasta" value="${new Date()}" precision="month" years="${1970..2020}"/>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Agregar</button>
    </div>
</g:form>

<g:form class="form-horizontal" role="form" controller="ModificarEmpresa" action="modificarPeriodo">
    <div class="form-group">
        <label for="selectPeriodo" class="col-sm-1 control-label">Periodos</label>
        <div class="col-sm-10">
            <select id="selectPeriodo" name = "periodo" class="form-control">
                <option value="" disabled selected hidden>Seleccione...</option>
                <g:each in="${periodos}">
                    <option value="${it?.nombre}">${it?.nombre}</option>
                </g:each>
            </select>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Modificar periodo</button>
    </div>
</g:form>
</body>
</html>