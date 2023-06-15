package lt.code.academy.data;

import org.bson.types.ObjectId;

public class Destytojas {

    private ObjectId destytojoID;
    private String destytojoVartotojoVardas;
    private String destytojoVardas;
    private String destytojoSlaptazodis;

    public Destytojas() {
    }

    public Destytojas(ObjectId destytojoID, String destytojoVartotojoVardas, String destytojoVardas, String destytojoSlaptazodis) {
        this.destytojoID = destytojoID;
        this.destytojoVartotojoVardas = destytojoVartotojoVardas;
        this.destytojoVardas = destytojoVardas;
        this.destytojoSlaptazodis = destytojoSlaptazodis;
    }

    public Destytojas(String destytojoVartotojoVardas, String destytojoVardas, String destytojoSlaptazodis) {
        this.destytojoVartotojoVardas = destytojoVartotojoVardas;
        this.destytojoVardas = destytojoVardas;
        this.destytojoSlaptazodis = destytojoSlaptazodis;
    }

    public Destytojas(ObjectId destytojoID, String destytojoVardas) {
        this.destytojoID = destytojoID;
        this.destytojoVardas = destytojoVardas;
    }

    public ObjectId getDestytojoID() {
        return destytojoID;
    }

    public void setDestytojoID(ObjectId destytojoID) {
        this.destytojoID = destytojoID;
    }

    public String getDestytojoVardas() {
        return destytojoVardas;
    }

    public void setDestytojoVardas(String destytojoVardas) {
        this.destytojoVardas = destytojoVardas;
    }

    public String getDestytojoSlaptazodis() {
        return destytojoSlaptazodis;
    }

    public void setDestytojoSlaptazodis(String destytojoSlaptazodis) {
        this.destytojoSlaptazodis = destytojoSlaptazodis;
    }

    public String getDestytojoVartotojoVardas() {
        return destytojoVartotojoVardas;
    }

    public void setDestytojoVartotojoVardas(String destytojoVartotojoVardas) {
        this.destytojoVartotojoVardas = destytojoVartotojoVardas;
    }

    @Override
    public String toString() {
        return "Destytojas{" +
                "destytojoID=" + destytojoID +
                ", destytojoVartotojoVardas='" + destytojoVartotojoVardas + '\'' +
                ", destytojoVardas='" + destytojoVardas + '\'' +
                ", destytojoSlaptazodis='" + destytojoSlaptazodis + '\'' +
                '}';
    }
}
