package Tema1;

public class Votant extends Persoana{
    String numeCircumscriptie;
    boolean indemanare;

    public Votant() {}

    public Votant(String numeCircumscriptie, String CNP, int varsta, boolean indemanare, String nume) {
        super(CNP, varsta, nume);
//        if (this.getInvalid() == false) {
//            return;
//        }
        this.numeCircumscriptie = numeCircumscriptie;
        this.indemanare = indemanare;
        System.out.println("S-a adaugat votantul " + nume);

    }
}
