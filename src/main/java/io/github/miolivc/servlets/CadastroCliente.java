package io.github.miolivc.servlets;

import io.github.miolivc.manager.ClienteManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//        ClienteManager manager = new ClienteManager();
//        String cpf = request.getParameter("cpf");
//        String telefone = request.getParameter("telefone");
//        String nome = request.getParameter("nome");
//        String rg = request.getParameter("rg");
//        String email = request.getParameter("email");
//        String senha = request.getParameter("senha");
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dataNasc = LocalDate.parse(request.getParameter("dataNasc"),formatter);
//        
//        String rua = request.getParameter("rua");
//        String bairro = request.getParameter("bairro");
//        String cidade = request.getParameter("cidade");
//        String cep = request.getParameter("cep");
//        String uf = request.getParameter("uf");
//        String numero = request.getParameter("numero");
//        String cnpj = request.getParameter("cnpj");
//        String foto = null;
//        boolean sucess = manager.add(cpf, telefone, nome, rg, dataNasc, email, senha, rua, bairro, cidade, cep, uf, numero, cnpj, foto);
//        PrintWriter out = response.getWriter();
//        out.print("<!DOCTYPE html><html><body>");
//        if(sucess){
//            out.print("<h1>Usuário cadastrado com sucesso!</h1>");
//        } else {
//            out.print("<h1>Ocorreu um erro!</h1>");
//        }
//        out.print("</body></html>");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html><html><body>");
        ClienteManager mng = new ClienteManager();
        if (mng.add("000.000.000-01", "222222", "Mahsah", "12345", LocalDate.MIN, "aaaa@aaa.com",
               "123", "aaaaa", "aaaaa", "aaaaa", "1111", "ba", "23A", "123456", null)){
            out.print("<h1>Adicionado!</h1>");
       }
        out.print("</body></html>");
    }
}
