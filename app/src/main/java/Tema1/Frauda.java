package Tema1;

public class Frauda {
    private String idAlegeri;
    private String CNP;
    private String nume;
    private String numeCircumscriptie;

    // getter pentru ID-ul alegerilor
    public String getIdAlegeri() {
        return idAlegeri;
    }

    // constructor care are ca parametru votantul care a comis frauda
    public Frauda(Votant votant) {
        this.CNP = votant.getCNP();
        this.nume = votant.getNume();
        this.numeCircumscriptie = votant.numeCircumscriptie;
        this.idAlegeri = votant.idAlegeri;
        System.out.println("FRAUDA: Votantul cu CNP-ul " + CNP + " a incercat sa comita o frauda. Votul a fost anulat");
    }

    // suprascriem metoda toString ca sa usuram printarea fraudelor
    public String toString() {
        return "In " + numeCircumscriptie + ": " + CNP + " " + nume;
    }
}
