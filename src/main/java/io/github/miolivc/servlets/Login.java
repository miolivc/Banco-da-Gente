package io.github.miolivc.servlets;

import io.github.miolivc.entities.Cliente;
import io.github.miolivc.manager.ClienteManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/Home"})
public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        ClienteManager manager = new ClienteManager();
//        Cliente cliente = manager.find("000.000.000-00");
//        if(cliente != null){
//            if(cliente.getEmail().equals(request.getParameter("user")) && cliente.getSenha().equals(request.getParameter("pass"))){
//                out.print("<!DOCTYPE hmtl><html lang='pt-br'><body><h1>Login efetudo com sucesso!</h1></body></html>");
//            }
//        }
//        out.print("<!DOCTYPE hmtl><html lang='pt-br'><body><h1>Login não efetudo!</h1></body></html>");
    }
}
