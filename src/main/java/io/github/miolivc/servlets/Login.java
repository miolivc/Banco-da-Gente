package io.github.miolivc.servlets;

import io.github.miolivc.manager.FuncionarioManager;
import io.github.miolivc.entities.Funcionario;
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
        PrintWriter out = response.getWriter();
        FuncionarioManager gerenciadorFuncionario = new FuncionarioManager();
        Funcionario funcionario = gerenciadorFuncionario.find("000.000.000-22");
        out.print("<!DOCTYPE hmtl><html><body>"+ funcionario.getEmail() + funcionario.getSenha() );
        if(funcionario.getEmail() == request.getParameter("email") && funcionario.getSenha() == request.getParameter("senha")){
            request.getSession(true);
            out.print("<h1>Logado com sucesso!</h1>" + funcionario.getEmail() + funcionario.getSenha());
        } else {
            out.print("<h1>:( um dos parametros esta errado</h1>" + funcionario.getEmail() + funcionario.getSenha());
        }
        out.print( funcionario.getEmail() + funcionario.getSenha() + "</body></html>");
    }
}
