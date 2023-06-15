package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Egzaminavimas {
    Scanner scanner;
    Faker faker;
    MongoDuomBaze duombaze = new MongoDuomBaze();
    Random random;
    StatistikosSkaiciavimas statistikosSkaiciavimas;

    public Egzaminavimas(Scanner scanner, Faker faker, Random random, StatistikosSkaiciavimas statistikosSkaiciavimas) {
        this.scanner = scanner;
        this.faker = faker;
        this.random = random;
        this.statistikosSkaiciavimas = statistikosSkaiciavimas;
    }
    void generuotiEgzamina(Destytojas destytojas)
    {
        String egzaminoPavadinimas = faker.programmingLanguage().name();
        LocalDate egzaminoData = getData();
        System.out.println("Irasykite koks klausimu kiekis bus egzamine ");
        int klSkaicius = Integer.parseInt(scanner.nextLine());
        Map<String,String> klausimai = generuotiEgzaminoKlausimus(klSkaicius);
        Map<String,String> egzaminoAtsakymai = generuotiEgzaminoAtsakymus(klSkaicius);
        Map<String,String> teisingiAtsaklymai = generuotiTeisingusEgzAtsakymus(klSkaicius);
        duombaze.atsiskaitytiEgzamina(new Egzaminas(destytojas.getDestytojoID(),destytojas.getDestytojoVardas(),egzaminoPavadinimas,egzaminoData,klausimai,egzaminoAtsakymai,teisingiAtsaklymai));
        System.out.printf("Egzaminas sukurtas! Pavadinimas: %s%n", egzaminoPavadinimas);
    }
    LocalDate getData()
    {
        while (true){
            try {
                System.out.println("Ivveskite egzamino data (Forma yyyy.MM.dd) :");
                String data = scanner.nextLine();
                LocalDate dabartineData = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                return  dabartineData;
            }catch (DateTimeException e){
                System.out.println("Data irasyta blogu formatu, bandykite isnaujo!");
            }
        }
    }
    private Map <String,String> generuotiEgzaminoKlausimus(int klSkaicius)
    {
        Map <String,String> klausimai = new HashMap<>();
        int numeris =1;
        for(int i=1; i<klSkaicius;i++){
            String klausimas = faker.harryPotter().book();
            klausimai.put(String.valueOf(numeris),klausimas);
            numeris++;
        }
        return klausimai;
    }
    private Map<String,String> generuotiEgzaminoAtsakymus(int klSkaicius)
    {
        Map<String,String> egzaminoAtsakymai = new HashMap<>();
        int numeris = 1;
        for(int i=0 ; i < klSkaicius ; i++){
            String atsakymai = "|1| -> Skaiciau; |2| -> Neskaiciau; |3| -> Pradejau;";
            egzaminoAtsakymai.put(String.valueOf(numeris),atsakymai);
            numeris++;
        }
    return egzaminoAtsakymai;
    }
    private Map<String,String> generuotiTeisingusEgzAtsakymus(int klSkaicius)
    {
        Map<String,String> teisingiAtsakymai = new HashMap<>();
        int numeris=1;
        for(int i=0; i<klSkaicius ; i++){
            String atsakymai = String.valueOf(random.nextInt(1,4));
            teisingiAtsakymai.put(String.valueOf(numeris),atsakymai);
            numeris++;
        }
        return teisingiAtsakymai;
    }
    void atnaujinti(Destytojas destytojas)
    {
        System.out.println("Iveskite egzamino pavadinima");
        String egzaminoPavadinimas = scanner.nextLine();
        Egzaminas egzaminas = duombaze.getEgzaminasPagalPavadinima(egzaminoPavadinimas);
        if(egzaminas==null){
            System.out.printf("Tokio egzamino neegzistuoja");
            return;
        }
        Map<String,String> atnaujintiKlausimus = atnaujintiKlausimus(egzaminas);
        duombaze.atnaujintiEgzaminoKlausimus(egzaminas,atnaujintiKlausimus);
    }
    private Map<String,String> atnaujintiKlausimus(Egzaminas egzaminas)
    {
        Map<String,String> klausimai = egzaminas.getEgzaminoKlausymai();
        System.out.println("Iveskite klausimo numeri, kuri norite pakeisti");
        String klNumeris = scanner.nextLine();
        System.out.printf("Buves klausimas: %s%n Prasau, irasykite nauja klausima", klausimai.get(klNumeris));
        String naujasisKlausimas = scanner.nextLine();
        klausimai.put(klNumeris,naujasisKlausimas);
        return klausimai;
    }
    void laikytiEgzamina(Studentas studentas)
    {
        System.out.println("Iveskite egzamino pavadinima");
        String egzaminoPav = scanner.nextLine();
        LocalDate data = getData();
        Egzaminas egzaminas=duombaze.getEgzaminas(egzaminoPav,data);
        if(egzaminas==null)
        {
            System.out.println("Egzaminas neegzistuoja");
            return;
        }
        if(!LocalDate.now().equals(egzaminas.getEgzaminoData())){
            System.out.printf("Jus negalite laikyti sio egzamino siandien! Egzamino data: %s%n", egzaminas.getEgzaminoData());
            return;
        }
        Boolean pirmasLaikymas = PatikrintiLaikKarta(studentas,egzaminas);

        if(pirmasLaikymas==true){
            return;
        }
        System.out.println("__________PRADEDAM__________");
        Map<String, String> studentoAtsakymai = generuotStudentoAtsakymus(egzaminas);
        Map<String, Map<String, String>> visiAtsakymai = gautiVisusAtsakymus(egzaminas);
        Map<String, String> ivertinimai = gautiIvertinimus(egzaminas);
        visiAtsakymai.put(studentas.getStudentoID().toString(),studentoAtsakymai);
        String ivertinimas = ivertintiStuddenta(egzaminas, studentoAtsakymai);
        System.out.println("__________EGZAMINAS BAIGTAS!__________");
        System.out.println("Jusu ivertinimas: "+ ivertinimas);
        System.out.printf("%n");
        ivertinimai.put(studentas.getStudentoID().toString(),ivertinimas);
        duombaze.atnaujintiStudentuAtsakymus(egzaminas,visiAtsakymai,ivertinimai);
        List<String> studentoIvertinimai = studentas.getIvertinimas();
        if(studentoIvertinimai == null)
        {
            studentoIvertinimai = new ArrayList<>();
        }
        StringBuilder builder = new StringBuilder();
        String studentoIvertinimas = builder.append(egzaminoPav).append("->").append(ivertinimas).toString();
        studentoIvertinimai.add(studentoIvertinimas);
        duombaze.atnaujintiStudentuIvertinimus(studentas,studentoIvertinimai);
        Egzaminas atnaujintiEgzamina = duombaze.getEgzaminaPagalID(egzaminas.getEgzaminoID());
        Statistika statistika1 = statistikosSkaiciavimas.generuotiStatistika(atnaujintiEgzamina);
        duombaze.atnaujintiEgzaminuStatistika(atnaujintiEgzamina, statistika1);
    }
    boolean PatikrintiLaikKarta(Studentas studentas, Egzaminas egzaminas)
    {
        Map<String, String> ivertinimai = egzaminas.getIvertinimas();
        boolean antrasBandimas = false;
        if(ivertinimai==null) {
            return false;
        }
        for(Map.Entry<String,String>ivertinimas : ivertinimai.entrySet())
        {
            if(ivertinimas.getKey().equals(studentas.getStudentoID().toString())){
                System.out.println("Tai jusu antras bandimas!");
                return true;
            }
        }return antrasBandimas;
    }
    Map<String, String>generuotStudentoAtsakymus(Egzaminas egzaminas)
    {
        Map<String, String> atsakymai = new HashMap<>();
        Map<String, String> klausymai = egzaminas.getEgzaminoKlausymai();
        Map<String, String> egzaminoAtsakymai = egzaminas.getKlausimuPasirinkimas();
        for(Map.Entry<String,String> klausimas : klausymai.entrySet()){
            System.out.println(klausimas.getValue());
            System.out.println(egzaminoAtsakymai.get(klausimas.getKey()));
            String atsakymas = String.valueOf(random.nextInt(1,4));
            System.out.printf("Mano atsakymas: %s %n", atsakymas);
            atsakymai.put(klausimas.getKey(),atsakymas);
        }
        return atsakymai;
    }
    private Map<String, Map<String, String>>gautiVisusAtsakymus(Egzaminas egzaminas)
    {
        Map<String, Map<String, String>> visiStudentoAtsakymai = egzaminas.getStudentoAtsakymai();
        if(visiStudentoAtsakymai == null){
            Map<String, Map<String, String>> naujasStudentuAtsakymuMapas = new HashMap<>();
            return naujasStudentuAtsakymuMapas;
        }
        return visiStudentoAtsakymai;
    }
    private Map<String,String> gautiIvertinimus(Egzaminas egzaminas){
        Map<String, String> ivertinimai = egzaminas.getIvertinimas();
        if(ivertinimai==null){
            Map<String, String> naujasIvertinimu =new HashMap<>();
            return naujasIvertinimu;
        }return ivertinimai;
    }
    private String ivertintiStuddenta(Egzaminas egzaminas, Map<String, String> studentoATsakymai)
    {
        Map<String, String> teisingiAtsakymai = egzaminas.getTeisingiAtsakymai();
        int numeris = 0, visiKlausimai = teisingiAtsakymai.size();
        for (Map.Entry<String,String> ats : teisingiAtsakymai.entrySet() ){
            if(ats.getValue().equals(studentoATsakymai.get(ats.getKey()))){
                numeris++;
            }
        }
        int ivertinimas=numeris*10/visiKlausimai;
        return String.valueOf(ivertinimas);
    }
    void ParodytiStudentoIvertinimus(Studentas studentas)
    {
        try {
            studentas.getIvertinimas().forEach(System.out::println);
        }catch (NullPointerException e)
        {
            System.out.println("Ivertinimu nera!");
        }
    }
    void rodytiVisusIvertinimus(List<Studentas> studentas)
    {
        System.out.println("Iveskite egzamino pavadinima");
        String egzPav=scanner.nextLine();

        try {
            Egzaminas egzaminas = duombaze.getEgzaminasPagalPavadinima(egzPav);
            Map<String, String> studentoIvertinimas = egzaminas.getIvertinimas();
            for(Map.Entry<String,String> ivertinimas : studentoIvertinimas.entrySet()) {


                System.out.printf(" ivertinimas: %s %n",  ivertinimas.getValue());

            }
        }catch (NullPointerException e)
        {
            System.out.println("Nėra tokio egzamino arba nėra studento pažymių");
        }
    }
    void spausdintiEgzaminoStatistika(Egzaminas egzaminas)
    {
        Statistika statistika = egzaminas.getEgzaminoStatistika();
        if(statistika==null){
            System.out.println("Egzaminas neturi statistikos");
            return;
        }
        List<KlausimuStatistika> klausimuStatistika = statistika.getKlausimuStatistikaList();
        System.out.printf("Egzamino %s statistika: %n", egzaminas.getEgzaminoPavadinimas());
        System.out.println("Ivertinimu vidurkis: " + statistika.getVidurkis());
        System.out.println("Auksciausias balas: " + statistika.getAuksciausiasIvertinimas());
        System.out.println("Zemiausias balas: " + statistika.getZemiausiasIvertinimas());
        System.out.println("Laikiusiu studentu skaicius: " + statistika.getStudentuSkaicius());
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Egzamino klausimu statistika:");
        klausimuStatistika.forEach(System.out::println);
    }
    void rodytiEgzaminoSTatistika() {
        System.out.println("Iveskite egzamino pavadinima");
        String egzaPav = scanner.nextLine();
        Egzaminas egzaminas = duombaze.getEgzaminasPagalPavadinima(egzaPav);
        spausdintiEgzaminoStatistika(egzaminas);
    }
}

