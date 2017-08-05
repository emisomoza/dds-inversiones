<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:07
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cuentas</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>

<body>
<h1>
    <g:form controller = "Cuentas" action ="guardar" >
        <div>
            <label>Nombre Empresa: </label>
            <g:textField name="nombreEmpresa"/><br/>
        </div>
        <div>
            <label>Nombre Cuenta: </label>
            <g:textField name="nombreCuenta"/><br/>
        </div>
        <div>
            <label>Períodos: </label>
            <g:textField name="periodos"/><br/>
        </div>
        <div>
            <label>Importe: </label>
            <g:textField name="importe"/><br/>
        </div>
        <div>
            <g:actionSubmit  value="Guardar"/>
        </div>
    </g:form>
    <div>
        <g:form controller = "Cuentas" action ="volver" >
            <label>&nbsp;</label><input type="submit" value="Cancelar"/>
        </g:form>
    </div>
</h1>
</body>
</html>