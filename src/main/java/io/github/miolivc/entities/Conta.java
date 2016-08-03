package io.github.miolivc.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Conta {
    private static int numero;
    private List<Cliente> titulares;
    private Agencia agencia;
    private LocalDate dataAbertura;
    private BigDecimal saldo;

    public List<Cliente> getTitulares() {
        return titulares;
    }
    
    public int getNumero(){
        return this.numero = numero;
    }
    
    public void setNumero(int numero){
        this.numero++;
    }

    public void setTitulares(List<Cliente> titulares) {
        this.titulares = titulares;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
   
}
