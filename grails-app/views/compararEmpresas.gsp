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
    <script language="JavaScript">
        var empresasCounter = 2;

        function validateForm() {
            var a=document.forms["Form"]["empresas.1.empresa"].value;
            var b=document.forms["Form"]["empresas.2.empresa"].value;
            var c=document.forms["Form"]["metodologia"].value;
            if (a==null || a=="",b==null || b=="",c==null || c=="")
            {
                alert("Completa los campos requeridos");
                return false;
            }
        }

        function agregarCampoIndicadorFiltro() {
            var newdiv = document.createElement('div');
            newdiv.innerHTML =
                    "<div class='form-inline'>"+
                    "<select name='empresas." + empresasCounter + ".empresa' class='form-control'>" +
                    "<option value='' disabled selected hidden>Seleccione...</option>" +
                    <g:each in='${empresas}'>
                    "<option value='${it?.id}'>${it?.nombre}</option>" +
                    </g:each>
                    "</select>\n" +
                    "</div>";
            document.getElementById('comparar-empresas').appendChild(newdiv);
            empresasCounter ++;
        }
    </script>
</head>

<body>
    <g:form class="form-horizontal" method="post" name="Form" controller="Consultas" action="comparar" onsubmit="return validateForm()">
        <div>
            <h1 class="panel-title">Comparar Empresas</h1></br>
        </div>

        <div>
            <div id="comparar-empresas" class="d-inline">
                <div class='form-inline'>
                    <select name='empresas.1.empresa' class='form-control'>
                        <option value="" disabled selected hidden>Seleccione...</option>
                        <g:each in="${empresas}">
                            <option value="${it?.id}">${it?.nombre}</option>
                        </g:each>
                    </select>
                </div>

                <div class='form-inline'>
                    <select name='empresas.2.empresa' class='form-control'>
                        <option value="" disabled selected hidden>Seleccione...</option>
                        <g:each in="${empresas}">
                            <option value="${it?.id}">${it?.nombre}</option>
                        </g:each>
                    </select>
                </div>
            </div>
            <br/>
            <button type="button" class="btn btn-default" onclick="agregarCampoIndicadorFiltro()">Agregar</button>
            <br/>
            <br/>
        </div>

        <div class="form-group">
            <label for="selectMetodologia" class="col-sm-1 control-label">Metodología</label>
            <div class="col-sm-2">
                <select id="selectMetodologia" name="metodologia" class="form-control">
                    <option value="" disabled selected hidden>Seleccione...</option>
                    <g:each in="${metodologias}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
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