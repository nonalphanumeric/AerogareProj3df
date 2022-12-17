package engine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.IScenarioIdProvider;
import enstabretagne.simulation.basics.ISimulationDateProvider;
import enstabretagne.simulation.basics.ScenarioId;
import enstabretagne.simulation.basics.SortedList;

import java.util.*;
import java.util.function.Predicate;

public class SimEngine implements IScenarioIdProvider, ISimulationDateProvider {
    //creation de l'echeancier
    private SortedList<SimEvent> echeancier;

    private LogicalDateTime debut;
    private LogicalDateTime fin;

    protected List<SimEntity> mesSimEntities;

    private LogicalDateTime currentDate;
    protected LogicalDateTime getCurrentDate()
    {
        return currentDate;
    }


    private MoreRandom randomGenerator;
    MoreRandom getRandomGenerator() {
        return randomGenerator;
    }

    //scenario en cours d'execution
    Scenario currentscenario;
    public Scenario getCurrentscenario() {
        return currentscenario;
    }
    public void setCurrentScenario(Scenario scenario) {
        currentscenario = scenario;
        //init de la nouvelle graine
        randomGenerator = new MoreRandom(scenario.getGraine());
    }

    //constructure de l'engine
    public SimEngine() {
        echeancier = new SortedList<>();
        mesSimEntities = new ArrayList<>();
        //on initialise le journaliseur
        Logger.setDateProvider(this);
    }

    //init de la simu avec date debut et fin
    public void initSimulation(LogicalDateTime debut, LogicalDateTime fin) {
        //init des dates
        this.debut=debut;
        currentDate = this.debut;
        this.fin=fin;

        //init des simEntities
        for(SimEntity e:mesSimEntities ){
            e.requestInit(); //siEntity à NONE, l'etat passe à initialized
        }
    }

    /**
     * Il faudra ecrire une methode qui init la simulation sans debut et fin
     * Le debut et la fin devront venir du scenario
     */

    /**
     * Methode post(avec surcharges)
     */
    //ajouter un event à l'echeancier
    public void post(SimEvent event) {
        echeancier.add(event);
    }
    public void post(LogicalDateTime scheduledTime, Runnable behaviour) {
        post(new GenericSimEvent(scheduledTime, behaviour));
    }

    /**
     * Boucle de la simuation
     * simulate + terminate
     */
    //si il y a un event apres
    public boolean hasANextEvent(){
        if(echeancier.size()>0) {
            if(echeancier.first().getDateOccurence().compareTo(fin)<=0) return true;
        }
        return false;
    }
    public void simulate()
    {
        //simple parcours de l'�ch�ancier
        while(hasANextEvent())
        {
            //on prend le premier �v�nement suivant de l'�ch�ancier
            SimEvent ev = echeancier.first();
            //on l'enl�ve de l'�ch�ancier
            echeancier.remove(ev);

            //si l'entit� est DEAD on ne tire pas l'�v�nement
            if(ev.entitePorteuseEvenement.getEtatEntity()== SimEntity.EtatEntity.INIT) {
                currentDate = ev.getDateOccurence();
                ev.process(); //action de l'evenement
            }
        }
    }

    public void terminate(boolean last) {
        //on vide l'�ch�ancier.
        //il peut contenir des �v�nements restants
        //indispensable pour le prochain run
        echeancier.clear();

        //on termine les entit�s.
        //on vide la liste des entit�s
        for(SimEntity e:mesSimEntities)
        {
            //seule l'entit� sc�nario persiste d'un run � l'autre
            if(!last) {
                if(!(e instanceof Scenario)) e.terminate();
            }
            else
                e.terminate();
        }
        mesSimEntities.clear();

        //on ne fait plus r�f�rence au sc�nario pr�c�dent
        currentscenario = null;

        //on met � zero les �l�ments de temps logique
        currentDate = null;
        debut = null;
        fin = null;

        //on sollicite le garbage collector pour qu'il cleane la m�moire
        //c'est un bon moment pour le faire
        System.gc();
    }


    /**
     * Moteur de recherche pour trouver des entity
     * @param query
     * @return resultats
     */
    public List<SimEntity> recherche(Predicate<SimEntity> query) {
        List<SimEntity> resultats = new ArrayList<>();
        for (SimEntity e:mesSimEntities) {
            if(query.test(e)) resultats.add(e); //si e est bien une entity on l'ajoute à resultats
        }
        return resultats;
    }
    @Override
    public ScenarioId getScenarioId() {
        return null;
    }

    @Override
    public LogicalDateTime SimulationDate() {
        return null;
    }
}
