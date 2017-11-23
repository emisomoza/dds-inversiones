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
    <script language="JavaScript">
        function checkIt(evt) {
            evt = (evt) ? evt : window.event
            var charCode = (evt.which) ? evt.which : evt.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                status = "Acepta solo numeros"
                return false
            }
            status = ""
            return true
        }
        function validateForm()
        {
            var a=document.forms["Form"]["nomCuenta"].value;
            var b=document.forms["Form"]["valor"].value;
            if (a==null || a=="",b==null || b=="")
            {
                alert("Completa los campos requeridos");
                return false;
            }
        }
    </script>
</head>

<body>
<g:form params="[empresa:params.empresa]" method="post" class="form-horizontal" role="form" controller="modificarEmpresa" action="save_cuenta_empresa" onsubmit="return validateForm()">
    <div>
        <g:if test="${saveCuentaText}">
            <h2 class="panel-title">${saveCuentaText}</h2></br>
        </g:if>
    </div>

    <div>
        <h2 class="panel-title">Agregar cuenta para: ${empresa.nombre}</h2></br>
    </div>

    <div class="form-group">
        <label for="inputCuenta" class="col-sm-1 control-label">Nombre</label>
        <div class="col-sm-3">
            <input id="inputCuenta" name = "nomCuenta" class="form-control" placeholder="Cuenta">
        </div>
    </div>

    <div class="form-group">
        <label for="inputValor" class="col-sm-1 control-label">Valor</label>
        <div class="col-sm-3">
            <input id="inputValor" name = "valor" class="form-control" maxlength="10" placeholder="Valor" onKeyPress="return checkIt(event)">
        </div>
    </div>

    <div>
    <h2 class="panel-title">Período</h2></br>
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
        <button type="submit" class="btn btn-primary" style="margin-left:40px">Agregar</button>
        <a href="${createLink(controller:'Index', action:'agregarCuentas')}" class="btn btn-primary" style="margin-left:130px">Volver</a>
    </div>
</g:form>
</body>
</html>