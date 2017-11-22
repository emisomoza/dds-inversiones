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
    <style media="screen" type="text/css">

    </style>
</head>

<body>
    <main>
    <g:form class="form-horizontal" role="form" controller="Consultas" action="comparar">
        <h1 class="panel-title">Cuentas de la empresa ${empresa.nombre}</h1>
        </br>

        <div class="row">
            <div class="col-md-2">
                <strong>Cuenta</strong>
                <g:each in="${cuentas}">
                    <option value="${it?.nombre}">${it?.tipo.descripcion}</option>
                </g:each>
            </div>
            <div class="col-md-2">
                <strong>Valor</strong>
                <g:each in="${cuentas}">
                    <option value="${it?.nombre}">${it?.valor}</option>
                </g:each>
            </div>
        </div>
    </g:form>
    </main>
</body>
</html>