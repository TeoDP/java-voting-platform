package Tema1;

public class Votant extends Persoana{
    String idAlegeri;
    String numeCircumscriptie;
    String numeRegiune;
    boolean indemanare;
    private Vot vot;

    // getter pentru votul candidatului, variabila privata
    public Vot getVot() {
        return vot;
    }

    // setter pentru votul candidatului, variabila privata
    public void setVot(Alegeri alegere, String CNP) {
        // daca votantul are deja un vot pus, atunci e frauda
        if (this.vot != null) {
            fraude.add(new Frauda(this));
        } else {
            // creem votul si vedem daca si exista candidatul
            // chiar daca nu exista candidatul, daca incearca iar sa voteze o sa fie frauda
            vot = new Vot(numeCircumscriptie, this.getCNP(), CNP);
            Candidat candidat = alegere.findCandidat(CNP);
            if (candidat != null) {
                System.out.println(this.getNume() + " a votat pentru " + candidat.getNume());
            }
        }
    }

    // constructorul pentru votant, cu toate informatiile necesare
    public Votant(String idAlegeri, String numeCircumscriptie,
                  String numeRegiune, String CNP, int varsta, boolean indemanare, String nume) {
        // apelam constructorul clasei parinte Persoana
        super(CNP, varsta, nume);
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.numeRegiune = numeRegiune;
        this.indemanare = indemanare;
        this.vot = null;
        System.out.println("S-a adaugat votantul " + nume);

    }

    // suprascriem functai toString ca sa putem printa mai usor votantii
    public String toString() {
        return getNume() + ' ' + getCNP() + ' ' + getVarsta();
    }
}
