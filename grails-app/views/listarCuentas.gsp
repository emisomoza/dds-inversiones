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
        <h1 class="panel-title" style="font-size: 20px">Períodos de la empresa ${empresa}</h1>
        </br>

        <div class="row" style="margin-left: 10px">
            <div class="col-md-2" style="font-size: 18px">
                <strong>Inicio</strong>
            </div>
            <div class="col-md-2" style="font-size: 18px">
                <strong>Fin</strong>
            </div>
        </div>
        <br/>

        <div style="margin-left: 22px">
            <g:each in="${periodos}">
                <div class="row">
                    <div class="col-md-2" style="font-size: 15px">
                        <option value="">${it?.inicio}</option>
                    </div>
                    <div class="col-md-2" style="font-size: 15px">
                        <option value="">${it?.fin}</option>
                    </div>
                </div>

                <h1 class="panel-title" style="font-size: 18px; margin-left:30px">Cuentas:</h1>

                <div class="row" style="margin-left: 25px">
                    <div class="col-md-2" style="font-size: 15px">
                        <strong>Nombre</strong>
                    </div>
                    <div class="col-md-2" style="font-size: 15px">
                        <strong>Valor</strong>
                    </div>
                </div>

                <div style="margin-left: 45px">
                    <g:each in="${it.cuentas}">
                        <div class="row">
                            <div class="col-md-2">
                                <option value="">${it?.nombre}</option>
                            </div>
                            <div class="col-md-2">
                                <option value="">${it?.valor}</option>
                            </div>
                        </div>
                    </g:each>
                </div>
                <br/><br/>
            </g:each>
        </div>
        <div>
            <a href="javascript:history.back()" class="btn btn-primary">Volver</a>
        </div>
    </g:form>
    </main>
</body>
</html>