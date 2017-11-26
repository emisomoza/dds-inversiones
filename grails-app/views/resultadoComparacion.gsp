<%--
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Comparacion Empresas</title>
</head>

<body>
<g:form class="form-horizontal">
    <div>
        <h2 class="panel-title">Empresas Ordenadas:</h2></br>
    </div>

    <div class="row" style="margin-left: auto">
        <div class="col-md-1">
            <strong>Posición</strong><br/>
            <g:each in="${empresas}">
                ${it?.indice}<br/>
            </g:each>
        </div>
        <div class="col-md-3">
            <strong>Empresa</strong><br/>
            <g:each in="${empresas}">
                ${it?.nombre}<br/>
            </g:each>
        </div>
    </div>
</g:form>
</body>
</html>