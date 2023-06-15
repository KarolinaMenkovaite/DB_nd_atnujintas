package lt.code.academy;

import lt.code.academy.data.Egzaminas;
import lt.code.academy.data.KlausimuStatistika;
import lt.code.academy.data.Statistika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatistikosSkaiciavimas {

    double getVidurkis(Egzaminas egzaminas)
    {
        Map <String, String> ivertinimai = egzaminas.getIvertinimas();
        double numeris = 0;
        if(ivertinimai==null){
            return 0;
        }
        for(Map.Entry<String, String > ivertinimas : ivertinimai.entrySet()){
            numeris+= Integer.parseInt(ivertinimas.getValue());
        }
        return numeris/ ivertinimai.size();
    }
    double getAuksciausiasIvertinimas(Egzaminas egzaminas)
    {
        Map <String, String> ivertinimai = egzaminas.getIvertinimas();
        double auskciausiasIvertinimas = 0;
        for(Map.Entry<String,String> ivertinimas : ivertinimai.entrySet()){
            double studentoIvertinimas = Double.parseDouble(ivertinimas.getValue());
            if(studentoIvertinimas > auskciausiasIvertinimas){
                auskciausiasIvertinimas = studentoIvertinimas;
            }
        }
        return auskciausiasIvertinimas;
    }
    double getZemiausiasIvertinimas(Egzaminas egzaminas){
        Map <String, String> ivertinimai = egzaminas.getIvertinimas();
        double zemiausiasIvertinimas = 10;
        for(Map.Entry <String,String> ivertinimas : ivertinimai.entrySet()){
            double studentoIvertinimas = Double.parseDouble(ivertinimas.getValue());
            if(studentoIvertinimas < zemiausiasIvertinimas){
                zemiausiasIvertinimas = studentoIvertinimas;
            }
        }
        return zemiausiasIvertinimas;
    }
    List<KlausimuStatistika> generuotiKlausimuStatistika(Egzaminas egzaminas)
    {
        List<KlausimuStatistika>klausimuStatistikas=new ArrayList<>();
        Map <String, String> teisingiAtsakymai = egzaminas.getTeisingiAtsakymai();
        Map <String, Map <String, String> > studentoAtsakymai =  egzaminas.getStudentoAtsakymai();
        int klNumeris=1;
        for(int i=0; i < teisingiAtsakymai.size();i++)
        {
            KlausimuStatistika klausimuStatistika = klausimuStatistikosSkaiciavimas(teisingiAtsakymai,studentoAtsakymai,klNumeris);
            klausimuStatistikas.add(klausimuStatistika);
            klNumeris++;
        }
        return klausimuStatistikas;
    }
    KlausimuStatistika klausimuStatistikosSkaiciavimas(Map <String, String> teisingiAtsakymai, Map <String, Map <String, String>> studAtsakymai, int klNumeris )
    {
        KlausimuStatistika klausimuStatistika = new KlausimuStatistika();
        double ats1=0, ats2=0, ats3=0;
        double visistudentai = studAtsakymai.size();
        for(Map.Entry<String,Map <String, String> > studAts1 : studAtsakymai.entrySet()){
            Map <String, String> studentoAtsakymas = studAts1.getValue();
            switch (studentoAtsakymas.get(String.valueOf(klNumeris))){
                case "1" -> ats1++;
                case "2" -> ats2++;
                case "3" -> ats3++;
            }
        }
        double atsPirmasProc = (ats1/visistudentai)*100;
        int a = (int) atsPirmasProc;
        double atsAntrasProc = (ats2/visistudentai)*100;
        int b = (int) atsAntrasProc;
        double atsTreciasProc = (ats3/visistudentai)*100;
        int c = (int) atsTreciasProc;
        Map <String, Integer> procentasPaspaudimoStudentuAntKlausimo = new HashMap<>();
        procentasPaspaudimoStudentuAntKlausimo.put("1", a );
        procentasPaspaudimoStudentuAntKlausimo.put("2", b);
        procentasPaspaudimoStudentuAntKlausimo.put("3", c);
        String klausimoID = String.valueOf(klNumeris);
        int teisinguAtsProcentas = 0;
        switch (teisingiAtsakymai.get(String.valueOf(klNumeris))){
            case "1" -> teisinguAtsProcentas = a;
            case "2" -> teisinguAtsProcentas = b;
            case "3" -> teisinguAtsProcentas = c;
        }
        klausimuStatistika = new KlausimuStatistika(klausimoID,teisinguAtsProcentas, procentasPaspaudimoStudentuAntKlausimo);
        return klausimuStatistika;
    }
    Statistika generuotiStatistika(Egzaminas egzaminas){
        String egzaminoID=String.valueOf(egzaminas.getEgzaminoID());
        String ID =egzaminoID + "statistika";
        double ivertinimoVidurkis = getVidurkis(egzaminas);
        double auksciausiasIvertinimas = getAuksciausiasIvertinimas(egzaminas);
        double zemiausiasIvertinimas = getZemiausiasIvertinimas(egzaminas);
        int studentuSk = egzaminas.getStudentoAtsakymai().size();
        List < KlausimuStatistika> klausimuStatistikas = generuotiKlausimuStatistika(egzaminas);
        return new Statistika(ID, egzaminoID, ivertinimoVidurkis,auksciausiasIvertinimas,zemiausiasIvertinimas,studentuSk,klausimuStatistikas);

    }


}
