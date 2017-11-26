<html>
<head>
    <meta name="layout" content="main"/>
    <title>Indicadores</title>
    <style media="screen" type="text/css">

    </style>
</head>

<body>
    <main>
    <g:form class="form-horizontal" role="form">
        <h1 class="panel-title" style="font-size: 20px">Indicadores cargados:</h1>
        </br>

        <div class="row" style="margin-left: 10px">
            <div class="col-md-2" style="font-size: 18px">
                <strong>Nombre</strong>
            </div>
            <div class="col-md-2" style="font-size: 18px">
                <strong>Expresión</strong>
            </div>
        </div>

        <div style="margin-left: 22px">
            <g:each in="${indicadores}">
                <div class="row">
                    <div class="col-md-2" style="font-size: 15px">
                        <option value="">${it?.nombre}</option>
                    </div>
                    <div class="col-md-2" style="font-size: 15px">
                        <option value="">${it?.expresion}</option>
                    </div>
                </div>
            </g:each>
        </div>
        <br/>
        <div>
            <a href="javascript:history.back()" class="btn btn-primary">Volver</a>
        </div>
    </g:form>
    </main>
</body>
</html>