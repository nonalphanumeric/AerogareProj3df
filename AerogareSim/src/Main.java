import engine.PlanMonitor;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.logger.Logger;

public class Main {
    //penser � utiliser cette m�thode en d�but dans le main
    //sinon un soucis de classloading est remont� par jsonb.
    //

    static public void main(String[] args) {
        //Logger.load();
        LogicalDateTime start = new LogicalDateTime("04/12/2019 14:00");
        LogicalDateTime end = new LogicalDateTime("04/12/2019 15:00");

        PlanAerogare plan = new PlanAerogare(1, start, end);

        PlanMonitor planMonitor = new PlanMonitor(plan);
        planMonitor.run();
    }

}


