package engine;

/*Les entitees simulees seront :
 * Liste ordonnee d'avion qui atterissent (sur 1 jour : 7h:22h)
 * 4 portes (on pourra en faire 6 ou 8 apres)
 * TW1 et TW2
 * la Piste
 * */

import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;

import java.util.List;
import java.util.function.Predicate;

public abstract class SimEntity {


    enum EtatEntity {NONE,INIT,DEAD};
    private InitData initData;
    private SimEngine engine;
    private EtatEntity etat;


    public SimEntity(SimEngine engine, InitData initData) {
        this.etat = EtatEntity.NONE;
        this.engine = engine;
        this.initData = initData;
    }

    public InitData getInitData(){return initData;}
    public SimEngine getEngine(){return engine;}
    public EtatEntity getEtatEntity(){return etat; }

    public void Post(SimEvent ev) {
        ev.entitePorteuseEvenement = this;
        engine.post(ev);
    }

    public LogicalDateTime Now() {
        return engine.getCurrentDate();
    }

    public List<SimEntity> recherche(Predicate<SimEntity> query) {
        return engine.recherche(query);
    }

    public MoreRandom getRandomGenerator() {
        return engine.getRandomGenerator();
    }

    /**seule m�thode vraiment publique
     elle �vite de pouvoir appeler plusieurs fois l'initialisation
     attention: quand une entit� parente cr�e une entit� fille c'est � elle qu'incombe
     de demander son initialisation via le requestInit().
     en effet, le moteur ne peut pas savoir quand il est opportun de d�clencher l'init pour cette entit�*/

    public void requestInit() {
        if(etat==EtatEntity.NONE) init();
    }

    //m�thode � surcharger
    protected void init() {
        etat = EtatEntity.INIT;
    }
    //m�thode � surcharger
    //lors de cet appel dans la surcharge on vide proprement les listes
    //on met � null tous les objets de mani�re � aider le garbage collector
    protected void terminate() {
        etat = EtatEntity.DEAD;
    }
}
