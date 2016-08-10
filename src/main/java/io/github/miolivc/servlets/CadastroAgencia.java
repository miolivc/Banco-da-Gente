package io.github.miolivc.servlets;

import io.github.miolivc.manager.AgenciaManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroAgencia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter  out = response.getWriter();
        out.print("<!DOCTYPE html><html><body>");
        
        String nome = request.getParameter("nomeAgencia");
        String telefone = request.getParameter("telefone");
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String uf = request.getParameter("uf");
        
        AgenciaManager agencia = new AgenciaManager();
        
        if(agencia.add(1, nome, telefone, rua, bairro, cidade, cep, uf, nome)){
            out.print("<h1>você conseguiu!</h1>");
        } else {
            out.print("<h1>você não conseguiu!</h1>");
        }
        out.print("</body></html>");
    }
}
