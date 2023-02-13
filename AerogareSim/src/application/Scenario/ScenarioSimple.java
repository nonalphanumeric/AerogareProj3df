package application.Scenario;

import application.*;
import application.avion.Avion;
import engine.Scenario;
import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScenarioSimple extends Scenario {

    ScenarioSimpleInitData scenarioSimpleInitData ;
    List<Piste> pistes = new ArrayList<>();
    List<CreerAvion> listAvions = new ArrayList<>();
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
        airport.requestInit();

        //creation des pistes
        for(int i=0;i<getNbPistes();i++) {
            Piste piste = new Piste(getEngine(), new InitPiste("piste n°"+i), i, true);
            piste.requestInit();
            pistes.add(piste);

        }

        TW1 tw1 = new TW1(getEngine(),scenarioSimpleInitData, 1);
        TW2 tw2 = new TW2(getEngine(),scenarioSimpleInitData, 1);
        TourDeControl tour = new TourDeControl(getEngine(),new InitTour("tour de control "));


        tw1.requestInit();
        tw2.requestInit();
        tour.requestInit();

        Logger.Information(this, "creerentite", "nombre d'avions =" + getNbAvions());


        System.out.println(getNbAvions());
        //creation des avions
        for(int i=0;i<getNbAvions();i++) {
            CreerAvion creerAvion = new CreerAvion(scenarioSimpleInitData.debut);
            creerAvion.process();
            listAvions.add(creerAvion);

        }
        System.out.println(listAvions.get(1));
        Avion currentAvion = listAvions.get(0).getAvion();
        currentAvion.demandeAtterissage(pistes.get(0));
    }

    @Override
    protected void init() {
        super.init();
        createSimEntity();
    }

    int nbAvions;
    //modifer la methode, faire evoluer selon les depart et arrivées
    public int getNbAvions(){
        return 10;
    }

    public int getNbPistes(){
        return 1;
    }
    public int numAvion;
    public class CreerAvion extends SimEvent{
        public Avion avion;
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
        public Avion getAvion(){
            return this.avion;
        }

    }


        @Override
        public void terminate() {

        }


}
