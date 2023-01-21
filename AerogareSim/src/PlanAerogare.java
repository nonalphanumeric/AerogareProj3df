import application.Scenario.ScenarioSimple;
import application.Scenario.ScenarioSimpleInitData;
import engine.Plan;
import engine.Scenario;
import engine.ScenarioInitData;
import engine.SimEngine;
import enstabretagne.base.time.LogicalDateTime;

import java.util.LinkedList;

public class PlanAerogare extends Plan {
    LogicalDateTime debut;
    LogicalDateTime fin;
    LinkedList<ScenarioSimple> aeroSC;

    public PlanAerogare(int nbReplique, LogicalDateTime debut, LogicalDateTime fin){
        super(nbReplique);
        aeroSC = new LinkedList<>();
        this.debut = debut;
        this.fin = fin;
    }


    @Override
    public void initScenarii() {
        //on cr�e autant d'instance d'un sc�nario donn� que de r�pliques souhait�es
        //on prend l'entier du num�ro de r�plique comme graine

        //premier sc�nario: sc�nario � un seul �tudiant
        for (int i = 0; i < getNbReplique(); i++)
            aeroSC.add(
                    new ScenarioSimple(getEngine(), new ScenarioSimpleInitData("ident", i, debut, fin, 3)));

    }
    @Override
    public boolean hasNextScenario() {
        if (aeroSC.size()>0) {
            return true;
        }
        return false;
    }

    @Override
    public Scenario nextScenario() {
        if (hasNextScenario()) {
            //on recupère le dernier scenario
            Scenario sc = aeroSC.pop();
            //on donne le sc�nario suivant au moteur
            engine.setCurrentScenario(sc);
            return sc;
        }
        return null;
    }
}
