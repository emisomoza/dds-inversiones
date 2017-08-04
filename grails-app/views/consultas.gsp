<%--
  Created by IntelliJ IDEA.
  User: palunni
  Date: 31/07/2017
  Time: 20:09
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Consultas</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>

<body>
<h1>
    <g:form controller = "Consultas" action ="comparar" >
        <g:form controller = "Consultas" action ="mostrarEmpresas" >
            <div>
                <label>Empresa 1: </label>
                <select id="owner" name="owner">
                    <g:each in="${empresas}">
                        <option value="${it?.nombre}">${it?.nombre}</option>
                    </g:each>
                </select>
            </div>
        </g:form>
        <div>
            <label>Empresa 2: </label>
            <select id="owner" name="owner">
                <g:each in="${empresas}">
                    <option value="${it?.nombre}">${it?.nombre}</option>
                </g:each>
            </select>
        </div>
        <div>
            <label>&nbsp;</label><input type="submit" value="Comparar"/>
        </div>
    </g:form>
    <div>
        <g:form controller = "Cuentas" action ="volver" >
            <label>&nbsp;</label><input type="submit" value="Cancelar"/>
        </g:form>
    </div>
</h1>
</body>
</html>