package com.emergentes.controlador;

import com.emergentes.modelo.GestorVacuna;
import com.emergentes.modelo.Vacuna;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacuna objvacuna = new Vacuna();
        int id;
        int pos;
        String op = request.getParameter("op");
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorVacuna agenda = (GestorVacuna) ses.getAttribute("agenda");
            objvacuna.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("mivacuna",objvacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacuna agenda = (GestorVacuna) ses.getAttribute("agenda");
            pos = agenda.ubicarvacuna(id);
            objvacuna = agenda.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("mivacuna", objvacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id=Integer.parseInt(request.getParameter("agenda"));
            HttpSession ses = request.getSession();
            GestorVacuna agenda = (GestorVacuna) ses.getAttribute("agenda");
            pos=agenda.ubicarvacuna(id);
            agenda.eliminarvacuna(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacuna objvacuna = new Vacuna();
        int pos;
        String op = request.getParameter("op");
                
        if(op.equals("grabar")){
            objvacuna.setId(Integer.parseInt(request.getParameter("id")));
            objvacuna.setNombre(request.getParameter("nombre"));
            objvacuna.setPeso(Integer.parseInt(request.getParameter("peso")));
            objvacuna.setTalla(Integer.parseInt(request.getParameter("talla")));
            objvacuna.setVacuna(request.getParameter("vacuna"));       
            
            HttpSession ses = request.getSession();
            GestorVacuna agenda = (GestorVacuna) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarvacuna(objvacuna);
            }
            else{
                pos = agenda.ubicarvacuna(objvacuna.getId());
                agenda.modificarvacuna(pos, objvacuna);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }     
}
