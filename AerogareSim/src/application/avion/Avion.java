package application.avion;

import application.*;
import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.Logger;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Avion extends SimEntity{

    public String etapeDuVol;
    public TourDeControl tour;
    public Avion(SimEngine engine, InitAvion initAvion) {
        super(engine, initAvion);
        System.out.println("Id de l'avion " +initAvion.getId());
        init();
        //int numVol;

    }

    @Override
    protected void init() {
        super.init();

        this.etapeDuVol = String.valueOf(EtapeDuVol.EN_VOL);

        //Log post apres init
        Logger.Detail(this, "Avion.init","Avion initialisé: " + getInitData().getId() );
    }

    public void decollage(){
        //Decollage decollage = new Decollage()
        Runnable behaviour = this::decollage; // syntax 1, method reference
        getEngine().post(getEngine().SimulationDate(),behaviour);
/*        behaviour = () -> this.atterissage(); // syntax 2, lambda

        behaviour = new Runnable() {
            @Override
            public void run() {
                Avion.this.atterissage();
            }
        };*/


    }

    public void atterissage(){
        //Atterissage atterissage = new Atterissage();
        //this::decollage un runnable ?
        Runnable behaviour = this::atterissage;
        getEngine().post(getEngine().SimulationDate(), behaviour);

    }

    public void demandeAtterissage(Piste piste){



        //si l'avion est en vol il fait demande d'atterissage
        //si il a l'autorisation, il commence son atterissage
        if (tour.autorisationEnterPiste(this)) {
            Post(new Atterissage(getEngine().SimulationDate(), this));
            System.out.println("L'avion n°"+ getInitData().getId() + " fait une demande d'atterisage sur la piste "+ piste.getNumPiste());
            Logger.Detail(this, "Avion.demandeAtterissage","Avion fait demande d'atterissage & atterissage autorisée pour l'avion " + getInitData().getId() );

        } else {
                Post(new Retard(getEngine().SimulationDate(), this));
                System.out.println("La piste n'est pas disponible pour faire atterir l'avion "+getInitData().getId());
                Logger.Detail(this, "Avion.demandeAtterissage","Avion fait demande d'atterissage & atterissage refuséepour l'avion "+getInitData().getId());

        }
    }

    public void FinAtterrissage(TourDeControl tour) {
        // La piste est libre
        getEngine().getPiste().setDispoPiste(true);
    }


}


