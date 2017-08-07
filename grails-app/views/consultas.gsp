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
            <div class="col-sm-10">
                <select id="selectEmpresa1" name = "empresa1" class="form-control">
                    <g:each in="${empresas}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="selectEmpresa2" class="col-sm-1 control-label">Empresa 2</label>
            <div class="col-sm-10">
                <select id="selectEmpresa2" name = "empresa2" class="form-control">
                    <g:each in="${empresas}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </div>
        <div>
            <label>&nbsp;</label><input type="submit" value="Comparar"/>
        </div>
    </g:form>
</body>
</html>