/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author zsobe31
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
       try (
            PrintWriter out = response.getWriter()) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BoltPU");
            EntityManager em = emf.createEntityManager();
            
            if(request.getParameter("task").equals("betoltVitamin")){
                List<Vitamin> elemek = Vitamin.getAllVitamin(em);
                JSONArray valasz = new JSONArray();
                for(Vitamin v : elemek){
                    JSONObject o = new JSONObject();
                    o.put("id", v.getId());
                    o.put("nev", v.getNev());
                    o.put("leiras", v.getLeiras());
                    valasz.put(o);
                }
                out.print(valasz.toString());
            }
            if(request.getParameter("task").equals("ujVitaminFelvitele")){
                String nevF = request.getParameter("nev");
                String leirasF = request.getParameter("leiras");
                
                Vitamin.addNewVitamin(em, nevF, leirasF);
                out.print("OK");
            }
            
            if(request.getParameter("task").equals("torolVitaminid")){
                int idT = Integer.parseInt(request.getParameter("id"));
                
                Vitamin.deleteVitamin(em, idT);
                out.print("OK");
            }
            
            if(request.getParameter("task").equals("modositVitamin")){
                String nevM = request.getParameter("nev");
                String leirasM = request.getParameter("leiras");
                int idM = Integer.parseInt(request.getParameter("id"));
                
                Vitamin.updateVitamin(em, nevM, leirasM, idM);
                out.print("OK");
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
