package Tema1;

public class Votant extends Persoana{
    String numeCircumscriptie;
    boolean indemanare;
    private Vot vot;

    public Vot getVot() {
        return vot;
    }

    public void setVot(Alegeri alegere, String CNP) {
        if (this.vot != null) {
            fraude.add(new Frauda(this.getCNP()));
        } else {
            vot = new Vot(numeCircumscriptie, this.getCNP(), CNP);
            Candidat candidat = alegere.findCandidat(CNP);
            if (candidat != null) {
                System.out.println(this.getNume() + " a votat pentru " + candidat.getNume());
            }
        }
    }

    public Votant() {}

    public Votant(String numeCircumscriptie, String CNP, int varsta, boolean indemanare, String nume) {
        super(CNP, varsta, nume);
//        if (this.getInvalid() == false) {
//            return;
//        }
        this.numeCircumscriptie = numeCircumscriptie;
        this.indemanare = indemanare;
        this.vot = null;
        System.out.println("S-a adaugat votantul " + nume);

    }

    public String toString() {
        return getNume() + ' ' + getCNP() + ' ' + getVarsta();
    }
}
