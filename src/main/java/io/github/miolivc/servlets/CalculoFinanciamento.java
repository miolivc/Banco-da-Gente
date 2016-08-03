package io.github.miolivc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculoFinanciamento extends HttpServlet {

    private static final double TAXA_JUROS = 0.015;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
        int qtdeParcelas = Integer.parseInt(request.getParameter("qtdeParcelas"));
        double coefFinanciamento = TAXA_JUROS / (1 - Math.pow((1 + TAXA_JUROS),(-qtdeParcelas)));
        double valorParcela = valorTotal * coefFinanciamento;
        
        DecimalFormat df = new DecimalFormat("########.###");
        
        PrintWriter out =  response.getWriter();
        out.print("<!DOCTYPE html><hmtl lang='pt-br'><body>");
        out.print("<h1>Financiamentos</h1><p>O seu financiamento no valor de " + valorTotal + "</p>");
        out.print("será dividido em " + qtdeParcelas + " no valor de " + df.format(valorParcela) + " com juros de ");
        out.print( TAXA_JUROS * 100 + "% ao mês</p>");
        out.print("</body></html>");
    }
}
