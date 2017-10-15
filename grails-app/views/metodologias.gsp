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
        function validateForm()
        {
            var a=document.forms["Form"]["nomMetodologia"].value;
            var b=document.forms["Form"]["indicadorOrden"].value;
            var c=document.forms["Form"]["condicion"].value;
            if (a==null || a=="",b==null || b=="",c==null || c=="")
            {
                alert("Completa los campos requeridos");
                return false;
            }
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
            <label for="inputNombre" class="col-sm-2 control-label">Nombre metodología</label>
            <div class="col-sm-2">
                <input id="inputNombre" name="nomMetodologia" class="form-control" placeholder="Metodologia">
            </div>
        </div>
    </div>
    <br>
    <div>
        <div class="form-group">
            <label for="selectIndicadorOrden" class="col-sm-2 control-label">Indicador ordenamiento</label>
            <div class="col-sm-2">
                <select id="selectIndicadorOrden" name="indicadorOrden" class="form-control">
                <option value="" disabled selected hidden>Seleccione...</option>
                <g:each in="${indicadores}">
                    <option value="${it?.expresion}">${it?.nombre}</option>
                </g:each>
                </select>
            </div>
        </div>
        <div class="form-group">
                <label for="selectCondicion" class="col-sm-2 control-label">Condicion ordenamiento</label>
                <div class="col-sm-2">
                    <select id="selectCondicion" name = "condicion" class="form-control">
                        <option value="" disabled selected hidden>Seleccione...</option>
                        <option value="<">Menor</option>
                        <option value=">">Mayor</option>
                    </select>
                </div>
        </div>
    </div>
    <br>
    <div>
        <div>
            <h1 class="panel-title">Seleccionar indicadores de filtro</h1></br>
        </div>
        <div class="d-inline">
            <g:each in="${indicadores}">
                <label><input type="checkbox" name="indicadores2" value="${it?.expresion}">    ${it?.nombre}</label>
                <select id="selectCondicion2" name = "condicion2">
                    <option value="" disabled selected hidden>Condicion..</option>
                    <option value="=">Igual</option>
                    <option value="<">Menor</option>
                    <option value=">">Mayor</option>
                </select>
                    <input id="inputCondicion" name = "valCondicion" placeholder="Valor" onKeyPress="return checkIt(event)">
                <br>
            </g:each>
        </div>
    </div>
    <br>
    <div class="form-group-lg">
        <button type="submit" class="btn btn-primary">Agregar</button>
    </div>
    </g:form>
</body>
</html>