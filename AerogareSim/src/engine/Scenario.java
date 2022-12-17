package engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ScenarioId;

public abstract class Scenario extends SimEntity {

    public LogicalDateTime getDebut(){
        return ((ScenarioInitData) getInitData()).getDebut();
    }
    public LogicalDateTime getFin() {
        return ((ScenarioInitData) getInitData()).getFin();
    }



    public Scenario(SimEngine engine,ScenarioInitData initSc) {
        super(engine, initSc);
        idSc= new ScenarioId(initSc.getId(),initSc.getGraine());
        graine = ((ScenarioInitData) getInitData()).getGraine();
    }

    /**
     * Creation des entites à simuler
     */
    public abstract void createSimEntity();

    /**
     * Gestion de la graine
     *
     */
    private int graine;
    public abstract int getGraine();
    public void setGraine(){
        idSc.setRepliqueNumber(graine);
        this.graine = graine;
    }

    /**
     * Num du scenario
     */
    ScenarioId idSc;
    ScenarioId getNum(){
        return idSc;
    }

}
