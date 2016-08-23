package io.github.miolivc.servlets;

import io.github.miolivc.manager.ClienteManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroCliente extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteManager manager = new ClienteManager();
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String nome = request.getParameter("nome");
        String rg = request.getParameter("rg");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        LocalDate dataNasc = null;
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String uf = request.getParameter("uf");
        String numero = request.getParameter("numero");
        String cnpj = request.getParameter("cnpj");
        String foto = null;
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html><html><body>");
        if(manager.add(cpf, telefone, nome, rg, dataNasc, email, senha, rua, bairro, cidade, cep, uf, numero, cnpj, foto)){
            out.print("<h1>Adicionado!</h1>");
        } else {
            out.print("<h1>Não foi possível atualizar! :(</h1>");
        }
        out.print("</body></html>");
    }
}
