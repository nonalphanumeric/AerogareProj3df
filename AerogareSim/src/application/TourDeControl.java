package application;

import application.avion.Avion;
import engine.InitData;
import engine.SimEngine;
import engine.SimEntity;

public class TourDeControl extends SimEntity {

    //List<Gate> gates = new ArrayList<>();

    //Constructeur
    public TourDeControl(SimEngine engine, InitData ini) {
        super(engine, ini);
    }



    public Boolean autorisationEnterPiste(Avion avion) {
        if (!this.getEngine().getPiste().getDispoPiste()) {
            // Si la piste est libre, l'avion peut atterir mais la piste n'est plus dispo
            this.getEngine().getPiste().setDispoPiste(false);
            return true;
        } else {
            // Si la piste est occupée, l'avion ne peut pas atterir
            return false;
        }
    }
}
