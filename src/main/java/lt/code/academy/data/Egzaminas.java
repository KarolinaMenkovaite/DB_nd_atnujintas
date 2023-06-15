package lt.code.academy.data;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Map;

public class Egzaminas extends Destytojas {
    private ObjectId egzaminoID;
    private String egzaminoPavadinimas;
    private LocalDate egzaminoData;
    private Map <String, String> egzaminoKlausymai;
    private Map <String, String> klausimuPasirinkimas;
    private Map <String, String> teisingiAtsakymai;
    private Map <String, Map<String,String>> studentoAtsakymai;
    private Map <String, String> Ivertinimas;
    private Statistika egzaminoStatistika;

    public Egzaminas(ObjectId destytojoID, String destytojoVardas, String egzaminoPavadinimas, LocalDate egzaminoData, Map<String, String> klausimai, Map<String, String> egzaminoAtsakymai, Map<String, String> teisingiAtsaklymai) {
    }

    public Egzaminas(ObjectId egzaminoID, String egzaminoPavadinimas, LocalDate egzaminoData, Map<String, String> egzaminoKlausymai, Map<String, String> klausimuPasirinkimas, Map<String, String> teisingiAtsakymai, Map<String, Map<String, String>> studentoAtsakymai, Map<String, String> ivertinimas, Statistika egzaminoStatistika) {
        this.egzaminoID = egzaminoID;
        this.egzaminoPavadinimas = egzaminoPavadinimas;
        this.egzaminoData = egzaminoData;
        this.egzaminoKlausymai = egzaminoKlausymai;
        this.klausimuPasirinkimas = klausimuPasirinkimas;
        this.teisingiAtsakymai = teisingiAtsakymai;
        this.studentoAtsakymai = studentoAtsakymai;
        this.Ivertinimas = ivertinimas;
        this.egzaminoStatistika = egzaminoStatistika;
    }

    public Egzaminas(String egzaminoPavadinimas, LocalDate egzaminoData, Map<String, String> egzaminoKlausymai, Map<String, String> klausimuPasirinkimas, Map<String, String> teisingiAtsakymai) {
        this.egzaminoPavadinimas = egzaminoPavadinimas;
        this.egzaminoData = egzaminoData;
        this.egzaminoKlausymai = egzaminoKlausymai;
        this.klausimuPasirinkimas = klausimuPasirinkimas;
        this.teisingiAtsakymai = teisingiAtsakymai;
    }

    public ObjectId getEgzaminoID() {
        return egzaminoID;
    }

    public void setEgzaminoID(ObjectId egzaminoID) {
        this.egzaminoID = egzaminoID;
    }

    public String getEgzaminoPavadinimas() {
        return egzaminoPavadinimas;
    }

    public void setEgzaminoPavadinimas(String egzaminoPavadinimas) {
        this.egzaminoPavadinimas = egzaminoPavadinimas;
    }

    public LocalDate getEgzaminoData() {
        return egzaminoData;
    }

    public void setEgzaminoData(LocalDate egzaminoData) {
        this.egzaminoData = egzaminoData;
    }

    public Map<String, String> getEgzaminoKlausymai() {
        return egzaminoKlausymai;
    }

    public void setEgzaminoKlausymai(Map<String, String> egzaminoKlausymai) {
        this.egzaminoKlausymai = egzaminoKlausymai;
    }

    public Map<String, String> getKlausimuPasirinkimas() {
        return klausimuPasirinkimas;
    }

    public void setKlausimuPasirinkimas(Map<String, String> klausimuPasirinkimas) {
        this.klausimuPasirinkimas = klausimuPasirinkimas;
    }

    public Map<String, String> getTeisingiAtsakymai() {
        return teisingiAtsakymai;
    }

    public void setTeisingiAtsakymai(Map<String, String> teisingiAtsakymai) {
        this.teisingiAtsakymai = teisingiAtsakymai;
    }

    public Map<String, Map<String, String>> getStudentoAtsakymai() {
        return studentoAtsakymai;
    }

    public void setStudentoAtsakymai(Map<String, Map<String, String>> studentoAtsakymai) {
        this.studentoAtsakymai = studentoAtsakymai;
    }

    public Map<String, String> getIvertinimas() {
        return Ivertinimas;
    }

    public void setIvertinimas(Map<String, String> ivertinimas) {
        Ivertinimas = ivertinimas;
    }

    public Statistika getEgzaminoStatistika() {
        return egzaminoStatistika;
    }

    public void setEgzaminoStatistika(Statistika egzaminoStatistika) {
        this.egzaminoStatistika = egzaminoStatistika;
    }

    @Override
    public String toString() {
        return "Egzaminas{" +
                "egzaminoID=" + egzaminoID +
                ", egzaminoPavadinimas='" + egzaminoPavadinimas + '\'' +
                ", egzaminoData=" + egzaminoData +
                ", egzaminoKlausymai=" + egzaminoKlausymai +
                ", klausimuPasirinkimas=" + klausimuPasirinkimas +
                ", teisingiAtsakymai=" + teisingiAtsakymai +
                ", studentoAtsakymai=" + studentoAtsakymai +
                ", Ivertinimas=" + Ivertinimas +
                ", egzaminoStatistika=" + egzaminoStatistika +
                '}';
    }
}
