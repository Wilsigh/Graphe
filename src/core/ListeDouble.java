package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Wilsigh on 09/12/2015.
 */
public class ListeDouble {
    List<List<Integer>> listeD;

    public ListeDouble(){
        listeD = new ArrayList();
    }

    public void addFirst(int index){
        List<Integer> nl = new ArrayList();
        nl.add(index);
        listeD.add(listeD.size(),nl);
    }

    /*public void addVoisin(int index, int sommet){
        if(listeD.isEmpty() || listeD.get(index).isEmpty()){
            this.addFirst(index);
        }
        else{
            List<Integer> nl = new ArrayList();
            nl = listeD.get(index);
            nl.add(sommet);
            listeD.set(index,nl);
        }
    }*/

    public void addListe(List<Integer> voisin){
        int sommet = voisin.get(0);
        if(listeD.isEmpty()) {
            this.addFirst(sommet);
        }

        int index = rechercheListe(sommet);
        if(index == -1){
            listeD.add(listeD.size(), voisin);
        } else {
            listeD.set(index, voisin);
        }
    }

    public int rechercheListe(int sommet){
        Iterator<List<Integer>> it = listeD.iterator();

        while(it.hasNext()){
            List<Integer> tmp = it.next();
            if(tmp.get(0) == sommet){
                return listeD.indexOf(tmp);
            }
        }
        return -1;
    }

    public String toString(){
        if(listeD.isEmpty()){
            return "vide";
        } else {
            String s = "listeD.toString()\n";
            Iterator<List<Integer>> itL = listeD.iterator();
            while (itL.hasNext()) {
                Iterator<Integer> it = itL.next().iterator();
                while (it.hasNext()) {
                    s += it.next() + ", ";
                }
                s += "\n";
            }
            s += "\n";
            return s;
        }
    }

    public List<List<Integer>> getListeD() {
        return listeD;
    }
}
