package io.github.miolivc.entities;

import java.util.List;

public class Agencia {
    private static int codigo;
    private String nome;
    private String telefone;
    private Endereco endereco;
    private List<Conta> contas;

    public int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int codigo) {
        Agencia.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agencia other = (Agencia) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
}
