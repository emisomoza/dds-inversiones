<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Indicadores</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>

<body>
<h1>

    <div>
        <label>Nombre: </label>
        <g:textField name="nombre"/><br/>
    </div>
    <div>
        <label>Expresion: </label>
        <g:textField name="expresion"/><br/>
    </div>

    <div>
        <g:form controller = "Indicadores" action ="guardar" >
            <g:actionSubmit  value="Guardar"/>
        </g:form>
        <g:form controller = "Indicadores" action ="volver" >
            <label>&nbsp;</label><input type="submit" value="Cancelar"/>
        </g:form>
    </div>
</h1>
</div>


</body>
</html>