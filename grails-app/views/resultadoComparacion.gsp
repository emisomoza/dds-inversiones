<%--
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Comparacion Empresas</title>
</head>

<body>
        <div>
            <h2 class="panel-title">Empresas Ordenadas:</h2></br>
        </div>
        <div class="form-group">
            <div class="col-sm-3">
                <g:each in="${empresas}">
                    <option value="${it?.id}">${it?.nombre}</option><br/>
                </g:each>
            </div>
        </div>
</body>
</html>