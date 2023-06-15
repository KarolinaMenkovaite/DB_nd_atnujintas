package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Studentas;
import lt.code.academy.data.Destytojas;

import java.util.*;

public class Menu {

    List<Studentas> studentas;
    List<Destytojas> destytojas;

    public Menu(List<Studentas> studentas, List<Destytojas> teachers) {
        this.studentas = studentas;
        this.destytojas = teachers;
    }

    Faker faker = new Faker();
    MongoDuomBaze duombaze = new MongoDuomBaze();
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    StatistikosSkaiciavimas statistikosSkaiciavimas = new StatistikosSkaiciavimas();
    Egzaminavimas egzaminavimas = new Egzaminavimas(scanner, faker, random, statistikosSkaiciavimas);

    void mainMenu(List <Studentas> studentas, List <Destytojas> destytojai) {
        String pasirinkimas;
        do {
            System.out.println("""
                    [1] -> Destytojo prisijungimas
                    [2] -> Studento prisijungimas
                    [0] -> Baigti darba
                    """);
            pasirinkimas = scanner.nextLine();
            menuPasirinkimas(pasirinkimas, studentas, destytojai);
        } while (!pasirinkimas.equals("0"));
    }

    void menuPasirinkimas(String pasirinkimas, List<Studentas> studentas, List<Destytojas> destytojas) {
        switch (pasirinkimas) {
            case "1" -> destytojoPrisijungimas();
            case "2" -> studenoPrisijungimas();
            case "0" -> System.out.println("Baigti darba");
            default -> System.out.println("Blogas pasirinkimas");
        }
    }

    void studenoPrisijungimas() {
        System.out.println("Iveskite savo vartotojo Varda");
        String vartotojoVardas = scanner.nextLine();
        System.out.println("Iveskite Slaptazodi");
        String slaptazodis = scanner.nextLine();
        Studentas Studentas = duombaze.getStudentas(vartotojoVardas, slaptazodis);
        if (Studentas == null) {
            System.out.println("Neteisingas slaptazodis!");
            return;
        }
        System.out.printf("Laba diena %s %n", Studentas.getStudentoVardas());
        studentoMenu(Studentas);
    }

    void studentoMenu(Studentas studentas) {
        String pasirinkimas;
        do {
            System.out.println("""
                    [1] -> Laikyti egzamina
                    [2] -> Gauti rezultatus
                    [0] -> Baigti darba
                    """);
            pasirinkimas = scanner.nextLine();
            studentoMenuPasirinkimas(pasirinkimas, studentas);
        } while (!pasirinkimas.equals("0"));
    }

    void studentoMenuPasirinkimas(String pasirinkimas, Studentas studentas) {
        switch (pasirinkimas) {
            case "1" -> egzaminavimas.laikytiEgzamina(studentas);
            case "2" -> egzaminavimas.ParodytiStudentoIvertinimus(studentas);
            case "0" -> System.out.println("Darbas baigtas");
            default -> System.out.println("Blogas pasirinkimas");
        }
    }

    void destytojoPrisijungimas() {
        System.out.println("Iveskite savo vartotojo varda");
        String vartotojoVardas = scanner.nextLine();
        System.out.println("Iveskite slaptazodi");
        String slaptazodis = scanner.nextLine();
        Destytojas destytojas = duombaze.getDestytojas(vartotojoVardas, slaptazodis);
        if (destytojas == null) {
            System.out.println("Blogas slaptazodis arba tokio vartotojo neegzistuoja");
            return;
        }
        System.out.printf("Laba diena %s %n", destytojas.getDestytojoVardas());

        destytojoMenu(destytojas);
    }

    void destytojoMenu(Destytojas destytojas) {
        String pasirinkimas;
        do {
            System.out.println("""
                    [1] -> Sukurti egzamina
                    [2] -> Atnaujnti egzamino klausimus
                    [3] -> Gauti studentu rezultatus
                    [4] -> Atspausdinti egzaminmu sarasas
                    [5] -> Egzamino statistika
                    [0] -> Baigti darba
                    """);
            pasirinkimas = scanner.nextLine();
            destytojoMenuPasirinkimas(pasirinkimas, destytojas);
        } while (!pasirinkimas.equals("0"));
    }

    void destytojoMenuPasirinkimas(String pasirinkimas, Destytojas destytojas) {
        switch (pasirinkimas) {
            case "1" -> egzaminavimas.generuotiEgzamina(destytojas);
            case "2" -> egzaminavimas.atnaujinti(destytojas);
            case "3" -> egzaminavimas.rodytiVisusIvertinimus(studentas);
            case "4" -> duombaze.rodytiVisusEgzaminus();
            case "5" -> egzaminavimas.rodytiEgzaminoSTatistika();
            case "0" -> System.out.println("Darbas baigtas");
            default -> System.out.println("Blogas pasrinkimas");
        }
    }
}