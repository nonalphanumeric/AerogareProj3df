package application.Scenario;

import engine.InitData;
import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class ScenarioSimpleInitData extends ScenarioInitData {
     int nbAvions;
    int graine;
    LogicalDateTime debut;
    LogicalDateTime fin;

    public ScenarioSimpleInitData(String id, int graine, LogicalDateTime debut, LogicalDateTime fin, int nbAvions) {
        super(id);
        this.debut=debut;
        this.fin=fin;
        this.graine = graine;
        this.nbAvions = nbAvions;
    }
    public int getGraine() {
        return graine;
    }
    public LogicalDateTime getDebut() {
        return debut;
    }
    public LogicalDateTime getFin() {
        return fin;
    }
    //double frequenceArriveeAvion = 1/20;
}
