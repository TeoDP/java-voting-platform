package Tema1;

// clasa pentru persoanele care sunt candidati
public class Candidat extends Persoana{
    // fiecare candidat are ca proprietate in plus alegerile pentru care candideaza
    public String idAlegeri;

    public Candidat(String idAlegeri, String CNP, int varsta, String nume){
        // apelam constructorul pentru obiectul de tip persoana ca sa setam variabilele de baza
        super(CNP, varsta, nume);
        this.idAlegeri = idAlegeri;
        // notificam utilizatorul ca s-a adaugat candidatul cu succes
        System.out.println("S-a adaugat candidatul " + nume);
    }

    // suprascriem functia toString ca sa fie mai facila printarea candidatilor
    @Override
    public String toString() {
        return getNume() + ' ' + getCNP() + ' ' + getVarsta();
    }
}
