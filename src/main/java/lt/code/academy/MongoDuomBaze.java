package lt.code.academy;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import lt.code.academy.data.Destytojas;
import lt.code.academy.data.Egzaminas;
import lt.code.academy.data.Statistika;
import lt.code.academy.data.Studentas;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDuomBaze {

    private final MongoCollection<Studentas> studentuKolekcija;
    private final MongoCollection<Destytojas> destytojuKolekcija;
    private final MongoCollection<Egzaminas> egzaminuKolekciaj;
    private Studentas studentas;
    private Destytojas destytojas;
    private Egzaminas egzaminas;

    public MongoDuomBaze() {
        MongoClient client = TiekimasIMongo.getKlientas();
        MongoDatabase duombaze = client.getDatabase("DB_nd");


        studentuKolekcija = duombaze.getCollection("studentas", Studentas.class);
        destytojuKolekcija = duombaze.getCollection("destytojas", Destytojas.class);
        egzaminuKolekciaj = duombaze.getCollection("egzaminas", Egzaminas.class);
    }
    List<Studentas> getStudentuSarasas() {
        List<Studentas> studentai = new ArrayList<>();
        FindIterable<Studentas> studentuSarasas = studentuKolekcija.find();
        for (Studentas studentas : studentuSarasas) {
            studentai.add(studentas);
        }
        return studentai;
    }
    List<Destytojas> getDestytojuSarasas() {
        List<Destytojas> destytojai = new ArrayList<>();
        FindIterable<Destytojas> destytojuSarasas = destytojuKolekcija.find();
        for (Destytojas destytojas : destytojuSarasas) {
            destytojai.add(destytojas);
        }
        return destytojai;
    }

    void iterptiStudenta(Studentas studentas) {
        studentuKolekcija.insertOne(studentas);
    }

    void iterptiDestytoja(Destytojas destytojas) {
        destytojuKolekcija.insertOne(destytojas);
    }

    void atsiskaitytiEgzamina(Egzaminas egzaminas) {
        egzaminuKolekciaj.insertOne(egzaminas);
    }

    Studentas getStudentas(String vartotojoVardas, String slaptazodis) {
        return studentas = studentuKolekcija.find(and(eq("studentoVartotojoVardas",vartotojoVardas ), eq("studentoSlaptazodis", slaptazodis))).first();
    }

    Destytojas getDestytojas(String vartotojoVardas, String slaptazodis) {
        return destytojas = destytojuKolekcija.find(and(eq("destytojoVartotojoVardas", vartotojoVardas), eq("destytojoSlaptazodis", slaptazodis))).first();
    }

    Egzaminas getEgzaminas(String egzaminoPavadinimas, LocalDate data) {
        return egzaminas = egzaminuKolekciaj.find(and(eq("egzaminoPavadinimas", egzaminoPavadinimas), eq("egzaminoData", data))).first();
    }

    Egzaminas getEgzaminaPagalID(ObjectId id) {
        return egzaminas = egzaminuKolekciaj.find(eq("id", id)).first();
    }

    Egzaminas getEgzaminasPagalPavadinima(String egzaminoPavadinimas) {
        return egzaminas = egzaminuKolekciaj.find(eq("egzaminoPavadinimas", egzaminoPavadinimas)).first();
    }


    void atnaujintiEgzaminoKlausimus(Egzaminas egzaminas, Map<String, String> klausimai) {
        egzaminuKolekciaj.updateOne(eq("id", egzaminas.getEgzaminoID()), Updates.set("klausimai", klausimai));
    }

    void atnaujintiStudentuAtsakymus(Egzaminas egzaminas, Map<String, Map<String, String>> studentuAtsakymai, Map<String, String> ivertinimai) {
        egzaminuKolekciaj.updateOne(eq("id", egzaminas.getEgzaminoID()), Updates.set("studentoAtsakymai", studentuAtsakymai));
        egzaminuKolekciaj.updateOne(eq("id", egzaminas.getEgzaminoID()), Updates.set("ivertinimai", ivertinimai));
    }

    void atnaujintiStudentuIvertinimus(Studentas studentas, List<String> ivertinimas) {
        studentuKolekcija.updateOne(eq("id", studentas.getStudentoID()), Updates.set("ivertinimas", ivertinimas));
    }

    void atnaujintiEgzaminuStatistika(Egzaminas egzaminas, Statistika statistikosSkaiciavimas) {
        egzaminuKolekciaj.updateOne(eq("id", egzaminas.getEgzaminoID()), Updates.set("egzaminoStatistika", statistikosSkaiciavimas));
    }

    void rodytiVisusEgzaminus() {
        FindIterable<Egzaminas> egzaminai = egzaminuKolekciaj.find();
        for (Egzaminas egzaminas : egzaminai) {
            System.out.println(egzaminas);
        }
    }

}

