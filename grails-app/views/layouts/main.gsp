<!DOCTYPE html>
<html>
<head>
	<title><g:layoutTitle default="Inversiones"/></title>
	<asset:stylesheet src="application.css"/>
	<g:layoutHead/>
</head>
<body>
	<g:if test="${request.user != null}">
		<nav class="navbar navbar-inverse sidebar" role="navigation">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a onclick="this.parentNode.submit()" href="${createLink(controller:'Logout')}">Logout ${request.user.username}<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-off"></span></a></li>
						<li><a href="${createLink(controller:'Index', action:'home')}">Página principal<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
						<li><a href="${createLink(controller:'Index', action:'compararEmpresas')}">Comparar Empresas<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-usd"></span></a></li>
						<li><a href="${createLink(controller:'Index', action:'agregarCuentas')}">Gestión Empresas<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-plus"></span></a></li>
						<li><a href="${createLink(controller:'Index', action:'agregarIndicadores')}">Gestión Indicadores<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a></li>
						<li><a href="${createLink(controller:'Metodologias', action:'crear')}">Gestión Metodologías<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-check"></span></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</g:if>
	<g:layoutBody/>
</body>
</html>
