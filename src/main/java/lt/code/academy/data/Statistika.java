package lt.code.academy.data;

import java.util.List;

public class Statistika {
    private String statistikoID;
    private String vartotojoVardas;
    private String egzaminoID;
    private Double vidurkis;
    private Double auksciausiasIvertinimas;
    private Double zemiausiasIvertinimas;
    private int studentuSkaicius;
    private List<KlausimuStatistika> klausimuStatistikaList;

    public Statistika() {
    }

    public Statistika(String statistikoID, String egzaminoID, Double vidurkis, Double auksciausiasIvertinimas, Double zemiausiasIvertinimas, int studentuSkaicius, List<KlausimuStatistika> klausimuStatistikaList) {
        this.statistikoID = statistikoID;
        this.egzaminoID = egzaminoID;
        this.vidurkis = vidurkis;
        this.auksciausiasIvertinimas = auksciausiasIvertinimas;
        this.zemiausiasIvertinimas = zemiausiasIvertinimas;
        this.studentuSkaicius = studentuSkaicius;
        this.klausimuStatistikaList = klausimuStatistikaList;
    }

    public Statistika(String id, String egzaminoID, double ivertinimoVidurkis, double zemiausiasIvertinimas, int studentuSk, List<KlausimuStatistika> klausimuStatistikas) {

    }

    public String getVartotojoVardas() {
        return vartotojoVardas;
    }

    public void setVartotojoVardas(String vartotojoVardas) {
        this.vartotojoVardas = vartotojoVardas;
    }

    public String getStatistikoID() {
        return statistikoID;
    }

    public void setStatistikoID(String statistikoID) {
        this.statistikoID = statistikoID;
    }

    public String getEgzaminoID() {
        return egzaminoID;
    }

    public void setEgzaminoID(String egzaminoID) {
        this.egzaminoID = egzaminoID;
    }

    public Double getVidurkis() {
        return vidurkis;
    }

    public void setVidurkis(Double vidurkis) {
        this.vidurkis = vidurkis;
    }

    public Double getAuksciausiasIvertinimas() {
        return auksciausiasIvertinimas;
    }

    public void setAuksciausiasIvertinimas(Double auksciausiasIvertinimas) {
        this.auksciausiasIvertinimas = auksciausiasIvertinimas;
    }

    public Double getZemiausiasIvertinimas() {
        return zemiausiasIvertinimas;
    }

    public void setZemiausiasIvertinimas(Double zemiausiasIvertinimas) {
        this.zemiausiasIvertinimas = zemiausiasIvertinimas;
    }

    public int getStudentuSkaicius() {
        return studentuSkaicius;
    }

    public void setStudentuSkaicius(int studentuSkaicius) {
        this.studentuSkaicius = studentuSkaicius;
    }

    public List<KlausimuStatistika> getKlausimuStatistikaList() {
        return klausimuStatistikaList;
    }

    public void setKlausimuStatistikaList(List<KlausimuStatistika> klausimuStatistikaList) {
        this.klausimuStatistikaList = klausimuStatistikaList;
    }

    @Override
    public String toString() {
        return "Statistika{" +
                "statistikoID='" + statistikoID + '\'' +
                ", egzaminoID='" + egzaminoID + '\'' +
                ", vidurkis=" + vidurkis +
                ", auksciausiasIvertinimas=" + auksciausiasIvertinimas +
                ", zemiausiasIvertinimas=" + zemiausiasIvertinimas +
                ", studentuSkaicius=" + studentuSkaicius +
                ", klausimuStatistikaList=" + klausimuStatistikaList +
                '}';
    }
}
