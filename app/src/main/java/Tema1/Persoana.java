package Tema1;

public class Persoana extends App {
    private String CNP;
    private int varsta;
    private String nume;
    private boolean invalid;

    public String getCNP() {
        return CNP;
    }

    // gettere si settere pentru variabilele private ale clasei
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

    public boolean getInvalid() {
        return invalid;
    }

    // constructor cu parametrii, apelat in principal de constructorii clasei care mostenesc clasa Persoana
    // el seteaza variabilele de baza care definesc un obiect de tip Persoana
    public Persoana(String CNP, int varsta, String nume) {
        if (CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            this.invalid = true;
            return;
        }
        this.CNP = CNP;
        this.varsta = varsta;
        this.nume = nume;
    }


}
