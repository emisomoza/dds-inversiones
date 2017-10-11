<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 31/07/2017
  Time: 20:09
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Consultas</title>
</head>

<body>
    <g:form class="form-horizontal" role="form" controller="Consultas" action="comparar">
        <div>
            <h2 class="panel-title">Comparar Empresas</h2></br>
        </div>
        <div class="form-group">
            <label for="selectEmpresa1" class="col-sm-1 control-label">Empresa 1</label>
            <div class="col-sm-2">
                <select id="selectEmpresa1" name = "empresa1" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
                    <g:each in="${empresas}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="selectEmpresa2" class="col-sm-1 control-label">Empresa 2</label>
            <div class="col-sm-2">
                <select id="selectEmpresa2" name = "empresa2" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
                    <g:each in="${empresas}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="selectMetodologia" class="col-sm-1 control-label">Metodología</label>
            <div class="col-sm-2">
                <select id="selectMetodologia" name="metodologia" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
                    <g:each in="${metodologias}">
                        <option value="${it}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </div>
        <div class="form-group-lg">
            <button type="submit" class="btn btn-primary">Comparar</button>
        </div>
    </g:form>
</body>
</html>