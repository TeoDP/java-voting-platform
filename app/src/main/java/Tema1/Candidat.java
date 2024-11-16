package Tema1;

public class Candidat extends Persoana{
    public String idAlegeri;

    public Candidat(){}
    public Candidat(String idAlegeri, String CNP, int varsta, String nume){
        super(CNP, varsta, nume);
        this.idAlegeri = idAlegeri;
        System.out.println("S-a adaugat candidatul " + nume);
    }
}
