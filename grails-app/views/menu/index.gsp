<%@ page import="ar.edu.utn.dds.Indicador" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
</head>
<body>
<g:set var="metodologias" value="${[[titulo: "Metodología 1"],[titulo: "Metodología 2"]]}"/>
<div style="width: 90%; padding: 15pt;">
    <g:render template="titulo" model="['titulo': 'Metodologías']"/>
    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>
    <div class="panel-group" id="accordion1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <a data-toggle="collapse"
                   data-parent="#accordion1" href="#collapseOne">
                    Parámetros de búsqueda
                </a>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                    <form>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Título</label>
                                <input type="text" name="titulo" id="titulo" class="form-control" placeholder="Contiene..." value="${libroBusqueda?.titulo}">
                            </div>
                            <div class="col-md-12">
                                <br />
                                <button type="submit" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-search"></span>
                                    Buscar
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="panel-group" id="accordion2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a class="accordion-toggle" data-toggle="collapse"
                       data-parent="#accordion2" href="#collapseTwo">Resultados:
                    </a>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div id="list-libro" class="content scaffold-list">
                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <thead>
                                <g:sortableColumn property="titulo" title="Título" />
                                </thead>
                                <tbody>
                                <g:each in="${metodologias}" status="i"
                                        var="metodologia">
                                    <tr class="${(i % 2) == 0 ? 'info' : ''}">
                                        <td><g:link action="verMetodologia" id="${metodologia.titulo}">
                                            ${metodologia.titulo}
                                        </g:link></td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-group" id="accordion3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a class="accordion-toggle" data-toggle="collapse"
                       data-parent="#accordion3" href="#collapseThree">Acciones
                    </a>
                </div>
                <div id="collapseThree" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search"></span>
                            Crear Metodología
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>