<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 31/07/2017
  Time: 20:09
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cuentas</title>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="Consultas" action="comparar">
        <div>
            <h2 class="panel-title">Cuentas de la empresa ${empresa.nombre}</h2></br>
        </div>
        <div class="form-group">
            <g:each in="${cuentas}">
                <option value="${it?.tipo.id}">${it?.tipo.descripcion}</option>
            </g:each>
        </div>
    </g:form>
</body>
</html>