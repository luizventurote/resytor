package model;

import java.util.ArrayList;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Cluster {

    private ArrayList<Documento> listaDocumentos;
    private String classe;
    private int classificacao;
    
    public Cluster() {
        this.listaDocumentos = new ArrayList();
    }

    public ArrayList<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(ArrayList<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
       
}
