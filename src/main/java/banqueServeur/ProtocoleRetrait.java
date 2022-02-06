package banqueServeur;

import java.io.PrintStream;


/**
 * Protocole de retrait utilisé dans {@link ServeurSpecifique}.
 */
public class ProtocoleRetrait implements IProtocole{
    public int valeurDemandee;
    public ServeurTCP monServeur;
    public PrintStream os;

    public ProtocoleRetrait(int valeurDemandee, ServeurTCP monServeur, PrintStream os) {
        this.valeurDemandee = valeurDemandee;
        this.monServeur = monServeur;
        this.os = os;
    }

    @Override
    public void run(){
        System.out.println(" valeur demandee  " + valeurDemandee);
        int valeurRetrait = ((IBanque) monServeur.getObjet()).demandeRetrait(valeurDemandee);
        String valeurExpediee = "" + valeurRetrait;
        System.out.println(" Retrait dans serveur " + valeurExpediee);
        os.println(valeurExpediee);
        System.out.println(monServeur);
    }

}
