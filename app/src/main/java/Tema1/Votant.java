package Tema1;

public class Votant extends Persoana{
    String idAlegeri;
    String numeCircumscriptie;
    String numeRegiune;
    boolean indemanare;
    private Vot vot;

    public Vot getVot() {
        return vot;
    }

    public void setVot(Alegeri alegere, String CNP) {
        if (this.vot != null) {
            fraude.add(new Frauda(this));
        } else {
            vot = new Vot(numeCircumscriptie, this.getCNP(), CNP);
            Candidat candidat = alegere.findCandidat(CNP);
            if (candidat != null) {
                System.out.println(this.getNume() + " a votat pentru " + candidat.getNume());
            }
        }
    }

    public Votant() {}

    public Votant(String idAlegeri, String numeCircumscriptie, String numeRegiune, String CNP, int varsta, boolean indemanare, String nume) {
        super(CNP, varsta, nume);
//        if (this.getInvalid() == false) {
//            return;
//        }
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.numeRegiune = numeRegiune;
        this.indemanare = indemanare;
        this.vot = null;
        System.out.println("S-a adaugat votantul " + nume);

    }

    public String toString() {
        return getNume() + ' ' + getCNP() + ' ' + getVarsta();
    }
}
