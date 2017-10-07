<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:30
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Metodologías</title>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="Empresas" action="save">
        <div>
            <h2 class="panel-title">Agregar Metodología:</h2></br>
        </div>
        <div class="form-group">
            <label for="inputNombre" class="col-sm-2 control-label">Nombre</label>
            <div class="col-sm-3">
                <input id="inputNombre" name = "nomMetodologia" class="form-control" placeholder="Metodologia">
            </div>
        </div>
        <div class="form-group">
            <label for="selectIndicador" class="col-sm-2 control-label">Indicador</label>
            <div class="col-sm-3">
                <select id="selectIndicador" name = "indicador" class="form-control">
                <option value="" disabled selected hidden>Seleccione...</option>
                <g:each in="${indicadores}">
                    <option value="${it?.expresion}">${it?.nombre}</option>
                </g:each>
                </select>
            </div>
        </div>
        <div class="form-group">
                <label for="selectCondicion" class="col-sm-2 control-label">Condicion</label>
                <div class="col-sm-3">
                    <select id="selectCondicion" name = "condicion" class="form-control">
                        <option value="" disabled selected hidden>Seleccione...</option>
                        <option value="<">Menor</option>
                        <option value=">">Mayor</option>
                    </select>
                </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Agregar</button>
        </div>
    </g:form>
</body>
</html>