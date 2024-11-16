package Tema1;

public class Persoana {
    private String CNP;
    private int varsta;
    private String nume;

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Persoana() {}

    public Persoana(String CNP, int varsta, String nume) {
        this.CNP = CNP;
        this.varsta = varsta;
        this.nume = nume;
    }


}
