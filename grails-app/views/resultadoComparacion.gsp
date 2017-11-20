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
    <div class="form-group">
        <div class="col-sm-3">
            <g:each in="${empresas}">
                ${it?.nombre}<br/>
            </g:each>
        </div>
    </div>
</g:form>
</body>
</html>