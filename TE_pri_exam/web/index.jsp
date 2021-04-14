<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Vacuna" %>
<%@page import="com.emergentes.modelo.GestorVacuna" %>
<%
    if (session.getAttribute("agenda") == null) {
        GestorVacuna objeto1 = new GestorVacuna();

        objeto1.insertarvacuna(new Vacuna(1, "Brunito Diaz", 25, 140, "Si"));
        objeto1.insertarvacuna(new Vacuna(2, "Juancito Pinto", 30, 152, "No"));

        session.setAttribute("agenda", objeto1);

    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - Vacunas</title>
        <style>
            div {

                margin-left: 60px;
            }
        </style>
    </head>
    <body >
        <div>
            <center><table border cellpadding="10" cellspacing="0" width="300">
                    <tr><td>
                            PRIMER PARCIAL TEM-742<BR>
                            Nombre: Marizol Siñani Aruquipa<BR>
                            Carnet:6122859 LP
                        </td></tr>
                </table></center>
            <h1><center>Registros de vacunas</center></h1>
            <a href="Controller?op=nuevo">Nuevo</a><br>
            <br>
            <table border cellpadding="8" cellspacing="0" frame="border" rules="cols">
                <thead bgcolor="DarkGray">
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Peso</th>
                        <th>Talla</th>  
                        <th>Vacuna</th> 
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                    <thead bgcolor="Gainsboro">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.nombre}</td>
                            <td>${item.peso}</td>
                            <td>${item.talla}</td>
                            <td>${item.vacuna}</td>
                            <td><a href="Controller?op=modificar&id=${item.id}">Editar</a></td>
                            <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </body>
</html>
