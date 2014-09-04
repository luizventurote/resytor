package model;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Termo {
    
    private String termo;
    private double[] frequencia;
    private double frequenciaTotal;
    private int qtdDoc;
    private double[] TF; 
    private double[] IDF;
    private double[] TFIDF; 

    public Termo(String termo) {
        this.termo = termo;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

    public double[] getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double[] frequencia) {
        this.frequencia = frequencia;
    }

    public double getFrequenciaTotal() {
        return frequenciaTotal;
    }

    public void setFrequenciaTotal(double frequenciaTotal) {
        this.frequenciaTotal = frequenciaTotal;
    }

    public int getQtdDoc() {
        return qtdDoc;
    }

    public void setQtdDoc(int qtdDoc) {
        this.qtdDoc = qtdDoc;
    }

    public double[] getTF() {
        return TF;
    }

    public void setTF(double[] TF) {
        this.TF = TF;
    }

    public double[] getIDF() {
        return IDF;
    }

    public void setIDF(double[] IDF) {
        this.IDF = IDF;
    }

    public double[] getTFIDF() {
        return TFIDF;
    }

    public void setTFIDF(double[] TFIDF) {
        this.TFIDF = TFIDF;
    }

}
