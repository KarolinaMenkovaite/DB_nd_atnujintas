package lt.code.academy;

import com.github.javafaker.Faker;
import lt.code.academy.data.Destytojas;
import lt.code.academy.data.Studentas;

public class GeneravimasStudDest {
    Faker faker;
    MongoDuomBaze mongoDuomBaze;

    public GeneravimasStudDest(Faker faker, MongoDuomBaze mongodb )
    {
        this.faker = faker;
        this.mongoDuomBaze = mongodb;
    }
    void GeneruotiStudentus() {
        int Numeris = 1;
        for(int i=0; i<=19; i++){
            String vartotojoVardas = String.valueOf(Numeris);
            String vardas = faker.name().fullName();
            String slaptazodis = "1235";
            mongoDuomBaze.iterptiStudenta(new Studentas(vartotojoVardas, vardas, slaptazodis));
            Numeris++;
            System.out.println(vartotojoVardas + " " + vardas + " " + slaptazodis );
        }
    }
    void GeneruotiDestytojus() {
        int Numeris = 1;
        for (int i = 0; i<=2; i++){
            String vartotojoVardas = String.valueOf(Numeris);
            String vardas = faker.name().fullName();
            String slaptazodis = "1235";
            mongoDuomBaze.iterptiDestytoja(new Destytojas(vartotojoVardas,vardas,slaptazodis));
            System.out.println(vartotojoVardas + " " + vardas + " " + slaptazodis );
            Numeris++;
        }
    }
}



