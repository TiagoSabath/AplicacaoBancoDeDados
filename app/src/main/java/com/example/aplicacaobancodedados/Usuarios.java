
package com.example.aplicacaobancodedados;
public class Usuarios {
    private String nome;
    private String telefone;
    private String email;
    private int numreg;

    public Usuarios(String nome, String telefone, String email, int numreg) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.numreg = numreg;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public int getNumreg() {
        return numreg;
    }

    public int getId() {
        return numreg;
    }

}
