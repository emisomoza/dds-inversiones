<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 01/08/2017
  Time: 20:07
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestionar empresas</title>
    <script language="JavaScript">
        function validateFile()
        {
            if (document.forms["upload"]["file"].value == "")
            {
                alert("Selecciona un archivo.");
                return false;
            }
        }
    </script>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="EmpresaView" action="save">
        <div>
        <g:if test="${text}">
            <h2 class="panel-title">${text}</h2></br>
        </g:if>
        </div>
        <br/>
        <div>
            <h2 class="panel-title">Agregar Empresa:</h2></br>
        </div>
        <div class="form-group">
            <label for="inputEmpresa" class="col-sm-2 control-label">Nueva empresa</label>
            <div class="col-sm-3">
                <input id="inputEmpresa" name = "nomEmpresa" class="form-control" placeholder="Empresa">
            </div>
            <button type="submit" class="btn btn-primary">Agregar</button>
        </div>
    </g:form>

    <g:form class="form-horizontal" role="form" controller="ModificarEmpresaView">
        <div>
            <h2 class="panel-title">Gestionar Empresas:</h2></br>
        </div>
        <div class="form-group">
            <label for="selectEmpresa" class="col-sm-2 control-label">Empresas</label>
            <div class="col-sm-3">
                <select id="selectEmpresa" name = "empresa" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
        <g:each in="${empresas}">
            <option value="${it?.id}">${it?.nombre}</option>
        </g:each>
        </select>
    </div>
        <div>
            <g:actionSubmit class="btn btn-primary" value="Agregar cuentas" action="agregarCuenta"/>
            <g:actionSubmit class="btn btn-secondary" value="Ver cuentas" action="listarCuentas"/>
        </div>
        </div>
    </g:form>
    <g:form class="form-horizontal" role="form" name="upload" controller="EmpresaView" action="upload" method="post" enctype="multipart/form-data"  onsubmit="return validateFile()">
        <div>
            <h2 class="panel-title">Carga batch de cuentas:</h2></br>
        </div>
        <label for="uploadFile" class="col-sm-3 control-label">Subir archivo (csv/txt)</label>
        <g:uploadForm id="uploadFile" value="sarasa">
            <fieldset class="col-sm-4 btn">
                <input type="file" name="file" accept=".txt, .csv" multiple="accept"/>
            </fieldset>
            <g:actionSubmit class="btn btn-primary" value="Upload"/>
        </g:uploadForm>
    </g:form>
</body>
</html>