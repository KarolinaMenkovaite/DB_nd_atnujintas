package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Studentas;
import lt.code.academy.data.Destytojas;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        MongoDuomBaze mongoDuomBaze = new MongoDuomBaze();
        GeneravimasStudDest generuoti = new GeneravimasStudDest(faker, mongoDuomBaze);

        //generuoti.GeneruotiDestytojus();
        //generuoti.GeneruotiStudentus();

        List<Studentas> studentai = mongoDuomBaze.getStudentuSarasas();
        List<Destytojas> destytojai = mongoDuomBaze.getDestytojuSarasas();

      // studentai.forEach(System.out::println);
        //destytojai.forEach(System.out::println);

        Menu menu = new Menu(studentai,destytojai);
        menu.mainMenu(studentai,destytojai);

    }
}