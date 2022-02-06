package banqueServeur;

import java.io.PrintStream;


/**
 * Protocole de dépôt utilisé par {@link ServeurSpecifique}.
 */
public class ProtocoleDepot implements IProtocole{
    public int valeurDemandee;
    public ServeurTCP monServeur;
    public PrintStream os;

    public ProtocoleDepot(int valeurDemandee, ServeurTCP monServeur, PrintStream os) {
        this.valeurDemandee = valeurDemandee;
        this.monServeur = monServeur;
        this.os = os;
    }

    @Override
    public void run(){
        System.out.println(" valeur demandee  " + valeurDemandee);
        int valeurDepot = ((IBanque) monServeur.getObjet()).demandeDepot(valeurDemandee);
        String valeurExpediee = "" + valeurDepot;
        System.out.println(" Depot dans serveur " + valeurExpediee);
        os.println(valeurExpediee);
        System.out.println(monServeur);
    }
}
