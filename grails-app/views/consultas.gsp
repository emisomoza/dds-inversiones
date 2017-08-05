<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 31/07/2017
  Time: 20:09
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Consultas</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>

<body>
<h1>
    <g:form controller = "Consultas" action ="comparar" >
        <div>
            <label>Empresa 1: </label>
            <select id="owner" name="owner">
                <g:each in="${empresas}">
                    <option value="${it?.nombre}">${it?.nombre}</option>
                </g:each>
            </select>
        </div>
        <div>
            <label>Empresa 2: </label>
            <select id="owner" name="owner">
                <g:each in="${empresas}">
                    <option value="${it?.nombre}">${it?.nombre}</option>
                </g:each>
            </select>
        </div>
        <div>
            <label>&nbsp;</label><input type="submit" value="Comparar"/>
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