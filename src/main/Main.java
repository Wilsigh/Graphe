package main;

import algorithme.*;
import core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilsigh on 09/12/2015.
 */
public class Main {
    public static void main(String[] args) {

        ListeDouble face = new Face();
        ListeDouble listeAdjacence = new ListeAdjacence();

        List listTemp = new ArrayList();

        //0
        listTemp.add(0);
        listTemp.add(1);
        listTemp.add(2);
        listeAdjacence.addListe(listTemp);

        //1

        listTemp = new ArrayList();
        listTemp.add(1);
        listTemp.add(0);
        listTemp.add(4);
        listTemp.add(7);
        listeAdjacence.addListe(listTemp);

        //2
        listTemp = new ArrayList();
        listTemp.add(2);
        listTemp.add(0);
        listTemp.add(3);
        listTemp.add(4);
        listeAdjacence.addListe(listTemp);

        //3
        listTemp = new ArrayList();
        listTemp.add(3);
        listTemp.add(2);
        listTemp.add(4);
        listTemp.add(7);
        listeAdjacence.addListe(listTemp);

        //4
        listTemp = new ArrayList();
        listTemp.add(4);
        listTemp.add(1);
        listTemp.add(2);
        listTemp.add(3);
        listTemp.add(5);
        listeAdjacence.addListe(listTemp);

        //5
        listTemp = new ArrayList();
        listTemp.add(5);
        listTemp.add(4);
        listTemp.add(8);
        listeAdjacence.addListe(listTemp);

        //6
        listTemp = new ArrayList();
        listTemp.add(6);
        listTemp.add(8);
        listeAdjacence.addListe(listTemp);

        //7
        listTemp = new ArrayList();
        listTemp.add(7);
        listTemp.add(1);
        listTemp.add(3);
        listeAdjacence.addListe(listTemp);

        //8
        listTemp = new ArrayList();
        listTemp.add(8);
        listTemp.add(5);
        listTemp.add(6);
        listeAdjacence.addListe(listTemp);

        System.out.println(listeAdjacence.toString());
        AlgoCycle algo = new AlgoCycle(listeAdjacence.getListeD());
        algo.probaCycle(10);

    }
}
