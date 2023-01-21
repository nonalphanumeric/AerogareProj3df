package application.Scenario;

import application.Airport;
import application.InitAvion;
import application.Piste;
import application.avion.Avion;
import engine.Scenario;
import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class ScenarioSimple extends Scenario {

    ScenarioSimpleInitData scenarioSimpleInitData ;
    public ScenarioSimple(SimEngine engine, ScenarioInitData initSc) {

        super(engine, initSc);
        scenarioSimpleInitData = (ScenarioSimpleInitData) initSc;

    }
    @Override
    public int getGraine() {
        return 0;
    }
    @Override
    public void createSimEntity() {

        Airport airport = new Airport(getEngine(),scenarioSimpleInitData);
        System.out.println(airport.toString());
        Piste piste = new Piste(getEngine(),scenarioSimpleInitData,1 );

        Logger.Information(this, "creerentite", "nombre d'avions =" + getNbAvions());
        System.out.println(getNbAvions());
        for(int i=0;i<getNbAvions();i++) {
            CreerAvion creerAvion = new CreerAvion(scenarioSimpleInitData.debut);
            creerAvion.process();
        }

    }

    @Override
    protected void init() {
        super.init();
        Post(new CreerAvion(getEngine().SimulationDate()));
    }
    int nbAvions;
    //modifer la methode, faire evoluer selon les depart et arrivées
    public int getNbAvions(){
        return 6;
    }
    public int numAvion;
    public class CreerAvion extends SimEvent{

        public LogicalDuration getNextDateAvionCreation(){
            //double time  = getRandomGenerator().nextExp(1/20);
            return LogicalDuration.ofMinutes(20); //creer un avion toutes les 20mn
        }
        public CreerAvion(LogicalDateTime d) {
            super(d);
        }

        @Override
        public void process() {
            Avion avion = new Avion(getEngine(), new InitAvion("A"+ numAvion++));
            avion.requestInit();

            //rescheduleAt(getEngine().SimulationDate().add(getNextDateAvionCreation()));
        }

    }
    @Override
    public void terminate(){

    }

}
