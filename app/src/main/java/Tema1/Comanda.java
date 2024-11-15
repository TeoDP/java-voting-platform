package Tema1;

import java.util.ArrayList;

public class Comanda {
    public int iD;
    ArrayList<String> arguments = new ArrayList<>();

    public void addArgs(String arguments) {
    }

    public Comanda() {}

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
    }
}
