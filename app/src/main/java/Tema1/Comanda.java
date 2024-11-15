package Tema1;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

public class Comanda extends App{
    public int iD;
    ArrayList<String> arguments = new ArrayList<>();

    public Comanda() {}

    public void addArgs(String arguments) {
    }

    public Alegeri findAlegere(String iDalegere) {
        Alegeri aux = null;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            aux = oldAlegeri.get(i);
            if (aux.getIdAlegeri().equals(iDalegere)) {
                return aux;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return null;
    }

}

class CreareAlegeri extends Comanda {
    private String iDAlegeri;
    private String numeAlegeri;

    public CreareAlegeri(String arguments) {
        iD = 0;
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
        String temp = "";
        for (int i = 1; i < split.length; i++) {
            temp += split[i] + " ";
        }
        this.arguments.add(temp);

        iDAlegeri = this.arguments.get(0);
        numeAlegeri = this.arguments.get(1);

        Alegeri alegeri = new Alegeri(iDAlegeri, numeAlegeri);
        oldAlegeri.add(alegeri);
    }
}

class StartAlegeri extends Comanda {

    public StartAlegeri() {
        iD = 1;
    }

    public StartAlegeri(String iDAlegeri) {
        iD = 1;
        Alegeri aux = findAlegere(iDAlegeri);
        if (aux == null) {
            return;
        }
        aux.startAlegeri();
    }
}

class AdaugareCircumscriptie extends Comanda {
    public String idAlegeri;

    public AdaugareCircumscriptie() {}

    public AdaugareCircumscriptie(String arguments) {
        iD = 2;
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
//        String temp[] = null;
//        for (int i = 1; i < split.length; i++) {
//            temp += split[i] + " ";
//        }
//        this.arguments.add(temp);
        idAlegeri = this.arguments.get(0);

        int bec = -1;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).equals(idAlegeri)) {
                bec = i;
            }
        }
        if (bec == -1) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        } else if (oldAlegeri.get(bec).getStatus() != 1 ) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        Circumscriptie circumscriptie = new Circumscriptie(split[0], split[1], split[2]);
        circumscriptii.add(circumscriptie);
    }
}
