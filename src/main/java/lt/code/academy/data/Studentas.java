package lt.code.academy.data;

import org.bson.types.ObjectId;

import java.util.List;

public class Studentas  {

    private ObjectId studentoID;
    private String studentoVartotojoVardas;
    private String studentoVardas;
    private Studentas studentoSlaptazodis;
    private List <String> Ivertinimas;

    public Studentas() {
    }

    public Studentas(String vardas, String vartotojoVardas, String slaptazodis) {
       // this.studentoVardas////!!!!
    }

    public Studentas(ObjectId studentoID, String studentoVartotojoVardas, String studentoVardas, Studentas studentoSlaptazodis, List<String> ivertinimas) {
        this.studentoID = studentoID;
        this.studentoVartotojoVardas = studentoVartotojoVardas;
        this.studentoVardas = studentoVardas;
        this.studentoSlaptazodis = studentoSlaptazodis;
        this.Ivertinimas = ivertinimas;
    }

    public Studentas(String studentoVartotojoVardas, String studentoVardas, Studentas studentoSlaptazodis, List<String> ivertinimas) {
        this.studentoVartotojoVardas = studentoVartotojoVardas;
        this.studentoVardas = studentoVardas;
        this.studentoSlaptazodis = studentoSlaptazodis;
        this.Ivertinimas = ivertinimas;
    }

    public ObjectId getStudentoID() {
        return studentoID;
    }

    public void setStudentoID(ObjectId studentoID) {
        this.studentoID = studentoID;
    }

    public String getStudentoVartotojoVardas() {
        return studentoVartotojoVardas;
    }

    public void setStudentoVartotojoVardas(String studentoVartotojoVardas) {
        this.studentoVartotojoVardas = studentoVartotojoVardas;
    }

    public String getStudentoVardas() {
        return studentoVardas;
    }

    public void setStudentoVardas(String studentoVardas) {
        this.studentoVardas = studentoVardas;
    }

    public Studentas getStudentoSlaptazodis() {
        return studentoSlaptazodis;
    }

    public void setStudentoSlaptazodis(Studentas studentoSlaptazodis) {
        this.studentoSlaptazodis = studentoSlaptazodis;
    }

    public List<String> getIvertinimas() {
        return Ivertinimas;
    }

    public void setIvertinimas(List<String> ivertinimas) {
        Ivertinimas = ivertinimas;
    }

    @Override
    public String toString() {
        return "Studentas{" +
                "studentoID=" + studentoID +
                ", studentoVartotojoVardas='" + studentoVartotojoVardas + '\'' +
                ", studentoVardas='" + studentoVardas + '\'' +
                ", studentoSlaptazodis=" + studentoSlaptazodis +
                ", Ivertinimas=" + Ivertinimas +
                '}';
    }
}
