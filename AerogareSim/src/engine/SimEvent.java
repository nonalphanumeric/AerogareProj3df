package engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.SortedList;

public abstract class SimEvent implements Comparable<SimEvent>{
    protected SimEntity entitePorteuseEvenement;
    /*faire l'echeancier*/

    private LogicalDateTime d;
    protected LogicalDateTime getDateOccurence()
    {
        return d;
    }

    //replanification de l'�v�nement
    protected void rescheduleAt(LogicalDateTime d)
    {
        this.d=d;
    }
    public SimEvent(LogicalDateTime d) {
        this.d=d;
    }

    @Override
    public int compareTo(SimEvent ev) {
        return this.d.compareTo(ev.d);
    }

    //m�thode � surcharger pour ex�cuter une action
    public abstract void process();

}
