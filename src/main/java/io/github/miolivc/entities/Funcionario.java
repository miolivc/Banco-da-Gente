package io.github.miolivc.entities;

public class Funcionario extends Pessoa {
    private String tipo;
    private Agencia agencia;    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

}
