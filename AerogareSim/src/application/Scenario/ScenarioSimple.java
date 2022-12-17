package application.Scenario;

import application.Airport;
import application.InitAvion;
import application.Piste;
import application.avion.Avion;
import engine.Scenario;
import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

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
        Piste piste = new Piste(getEngine(),scenarioSimpleInitData);
    }

    @Override
    protected void init() {
        super.init();
        Post(new CreerAvion(getEngine().SimulationDate()));
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

}
