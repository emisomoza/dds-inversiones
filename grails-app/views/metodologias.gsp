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
    <script language="JavaScript">
        var indicadorFiltroCounter = 0;

        function checkIt(evt) {
            evt = (evt) ? evt : window.event
            var charCode = (evt.which) ? evt.which : evt.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                status = "Acepta solo numeros"
                return false
            }
            status = ""
            return true
        }

        function validateForm() {
            var a=document.forms["Form"]["nomMetodologia"].value;
            var b=document.forms["Form"]["indicadorOrden"].value;
            var c=document.forms["Form"]["condicion"].value;
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
                "<select name='operadoresfiltro." + indicadorFiltroCounter + ".indicador' class='form-control'>" +
                "<option value='' disabled selected hidden>Indicador</option>" +
                <g:each in='${indicadores}'>
                "<option value='${it}'>${it}</option>" +
                </g:each>
                "</select>\n" +
                "<select name='operadoresfiltro." + indicadorFiltroCounter + ".operador' class='form-control'>" +
                "<option value='' disabled selected hidden>Operador</option>" +
                "<option value='<'>Menor</option>" +
                "<option value='>'>Mayor</option>" +
                "</select>\n" +
                "<input name='operadoresfiltro." + indicadorFiltroCounter + ".comparador' placeholder='Valor' onKeyPress='return checkIt(event)'>" +
                "</div>";
            document.getElementById('indicadores-filtro').appendChild(newdiv);
            indicadorFiltroCounter ++;
        }
    </script>
</head>

<body>
<g:form class="form-horizontal" method="post" name="Form" controller="Metodologias" action="save" onsubmit="return validateForm()">
    <div>
        <div>
            <g:if test="${text}">
                <h2 class="panel-title">${text}</h2></br>
            </g:if>
        </div>

        <div>
            <h1 class="panel-title">Agregar Metodología</h1></br>
        </div>
        <div class="form-group">
            <div class="col-sm-2">
                <input id="inputNombre" name="nombre" class="form-control" placeholder="Nombre">
            </div>
        </div>
    </div>
    <br/>
    <div>
        <h1 class="panel-title">Seleccionar indicador de ordenamiento</h1><br/>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <select name="operadororden.indicador" class="form-control">
                <option value="" disabled selected hidden>Indicador</option>
                <g:each in="${indicadores}">
                    <option value="${it}">${it}</option>
                </g:each>
            </select>
            <br/>
            <select name = "operadororden.operador" class="form-control">
                <option value="" disabled selected hidden>Operador</option>
                <option value="<">Menor</option>
                <option value=">">Mayor</option>
            </select>
            <br/>
            <select name = "operadororden.modificador" class="form-control">
                <option value="" disabled selected hidden>Modificador</option>
                <option value="promedio">Promedio</option>
                <option value="suma">Suma</option>
            </select>
        </div>
        <br/>
    </div>
    <br/>
    <div>
        <div>
            <h1 class="panel-title">Seleccionar indicadores de filtro</h1><br/>
        </div>
        <div id="indicadores-filtro" class="d-inline">
        </div>
        <br/>
        <button type="button" class="btn btn-default" onclick="agregarCampoIndicadorFiltro()">Agregar</button>
    </div>
    <br/>
    <div>
        <h1 class="panel-title">Visibilidad</h1><br/>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <select class="form-control" name="visibilidad">
                <option value="ROLE_NULL">Privado</option>
                <g:if test="${userRoles.contains("ROLE_ADMIN")}">
                    <g:each var="rol" in="${userRoles}">
                        <option>${rol}</option>
                    </g:each>
                </g:if>
            </select>
        </div>
    </div>
    <br/>
    <div class="form-group-lg">
        <button type="submit" class="btn btn-primary">Crear</button>
    </div>
</g:form>
</body>
</html>