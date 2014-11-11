package model;

import java.util.ArrayList;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Documento {
    
    private String conteudo;
    private double similaridade;
    private double[] representacao;
    private boolean cluster = false;
    private double[] clusterList;
    private ArrayList<Documento> listaDocumentos;
    private String classe;
    private int classificacao = -1;

    public Documento(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public double getSimilaridade() {
        return similaridade;
    }

    public void setSimilaridade(double similaridade) {
        this.similaridade = similaridade;
    }

    public double[] getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(double[] representacao) {
        this.representacao = representacao;
    }

    public boolean isCluster() {
        return cluster;
    }

    public void setCluster(boolean cluster) {
        this.cluster = cluster;
    }

    public double[] getClusterList() {
        return clusterList;
    }

    public void setClusterList(double[] clusterList) {
        this.clusterList = clusterList;
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
