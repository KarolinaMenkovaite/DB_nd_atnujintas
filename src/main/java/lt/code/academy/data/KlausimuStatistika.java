package lt.code.academy.data;

import java.util.Map;

public class KlausimuStatistika {

    private String klausymoID;
    private int teisinguAtsakymuProcentas;
    private Map<String, Integer> StudentuPaspaudimasAntKlausymo;

    public KlausimuStatistika() {
    }

    public KlausimuStatistika(String klausymoID, int teisinguAtsakymuProcentas, Map<String, Integer> studentuPaspaudimasAntKlausymo) {
        this.klausymoID = klausymoID;
        this.teisinguAtsakymuProcentas = teisinguAtsakymuProcentas;
        StudentuPaspaudimasAntKlausymo = studentuPaspaudimasAntKlausymo;
    }

    public String getKlausymoID() {
        return klausymoID;
    }

    public void setKlausymoID(String klausymoID) {
        this.klausymoID = klausymoID;
    }

    public int getTeisinguAtsakymuProcentas() {
        return teisinguAtsakymuProcentas;
    }

    public void setTeisinguAtsakymuProcentas(int teisinguAtsakymuProcentas) {
        this.teisinguAtsakymuProcentas = teisinguAtsakymuProcentas;
    }

    public Map<String, Integer> getStudentuPaspaudimasAntKlausymo() {
        return StudentuPaspaudimasAntKlausymo;
    }

    public void setStudentuPaspaudimasAntKlausymo(Map<String, Integer> studentuPaspaudimasAntKlausymo) {
        StudentuPaspaudimasAntKlausymo = studentuPaspaudimasAntKlausymo;
    }

    @Override
    public String toString() {
        return "KlausimuStatistika{" +
                "klausymoID='" + klausymoID + '\'' +
                ", teisinguAtsakymuProcentas=" + teisinguAtsakymuProcentas +
                ", StudentuPaspaudimasAntKlausymo=" + StudentuPaspaudimasAntKlausymo +
                '}';
    }
}
