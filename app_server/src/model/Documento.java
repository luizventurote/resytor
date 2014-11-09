package model;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Documento {
    
    private String conteudo;
    private double similaridade;
    private double[] representacao;
    private boolean cluster = false;

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
        
}
