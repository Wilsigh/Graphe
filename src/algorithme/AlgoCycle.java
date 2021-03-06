package algorithme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Wilsigh on 09/12/2015.
 */
public class AlgoCycle {

    List<List<Integer>> G;

    public AlgoCycle(List<List<Integer>> graphe) {
        G = new ArrayList();
        G = graphe;
    }

    public List<Integer> probaCycle(int c){
        System.out.println("nombre de demande : " + c);
        List<Integer> soluce = new ArrayList();
        List<Integer> soluceTmp;
        List<List<Integer>> copyG = new ArrayList(G);
        soluce = chercherCycle(copyG);
        System.out.println("soluce : " + affiche(soluce) + "\n");
        for(int i = 0; i < c-1; i++){
            copyG = new ArrayList<>(G);
            soluceTmp = chercherCycle(copyG);
            System.out.println(affiche(soluceTmp));
            System.out.println("changement ? " + soluce.size() + " ## " + soluceTmp.size());
            if(soluceTmp.size() > soluce.size()){
                System.out.println("changement");
                soluce = soluceTmp;
            }
        }
        System.out.println("Soluce => " + affiche(soluce));
        return soluce;
    }


    public List<Integer> chercherCycle(List<List<Integer>> copyG){
        List<Integer> cycle = new ArrayList();
        List<Integer> ListeVoisinSuivant;
        boolean boucle = false;
        int premierSeedIndex, seedIndex, voisinSuivant;
        premierSeedIndex = alea(copyG.size());

        //Ajout du premier sommet du cycle
        cycle.add(premierSeedIndex);
        //System.out.println("D�but de recherche cycle : " + affiche(cycle));
        seedIndex = premierSeedIndex;

        do{
            //R�cup�ration des voisins du dernier seed
            ListeVoisinSuivant = copyG.get(seedIndex);
            //System.out.println("\tdo -> " +affiche(ListeVoisinSuivant) + "seed : " + seedIndex);
            //test si il reste des voisins
            if(ListeVoisinSuivant.isEmpty()){
                //System.out.println("\tnull");
                return null;
            } else {
                boucle = true;
                do {
                    //sortie de ligne si il ne reste plus qu'un choix possible et qu'il fait d�j� partie de cycle
                    if(ListeVoisinSuivant.size()==2 && cycle.contains(ListeVoisinSuivant.get(1))){
                        //System.out.println("vide, on enl�ve le d�but => " + ListeVoisinSuivant.get(1));
                        cycle.add(cycle.size(), ListeVoisinSuivant.get(1));
                        return elagCycle(cycle);
                    }
                    //traitement normal
                    //nouvel al�a sur ces voisins
                    seedIndex = alea(ListeVoisinSuivant.size() - 1) + 1;
                    //System.out.println("\t-> seed : " + seedIndex);
                    //r�cup�ration du nouveau sommet
                    voisinSuivant = ListeVoisinSuivant.get(seedIndex);
                    //System.out.println("\t-> " + voisinSuivant);
                    //test si le sommet appartient d�j� au cycle
                    if (cycle.contains(voisinSuivant)) {
                        if (cycle.get(0) == voisinSuivant && cycle.size()>2) {
                            System.out.println("cycle");
                            return cycle;
                        } else {
                            ListeVoisinSuivant.remove(seedIndex);
                        }
                    } else {
                        cycle.add(cycle.size(), voisinSuivant);
                        boucle = false;
                    }
                }while(boucle);
            }
            seedIndex = voisinSuivant;
            //System.out.println("\t\t->-> " + affiche(cycle) + "new turn");
        }while(cycle.contains(voisinSuivant));
        System.out.println("sortie ?");
        return cycle;
    }

    public List<Integer> elagCycle(List<Integer> cycle){
        int fin = cycle.get(cycle.size()-1);
        System.out.println("\t\tfin : " + fin + " - " + affiche(cycle));
        while(cycle.get(0)!=fin && cycle.size()>0){
            cycle.remove(0);
        }
        cycle.remove(0);
        System.out.println("elag cycle -> " + affiche(cycle));
        return cycle;
    }

    public String affiche(List<Integer> cycle){
        String s = "";
        if(cycle != null) {
            Iterator<Integer> it = cycle.iterator();
            while (it.hasNext()) {
                s += it.next() + ", ";
            }
        } else {
            s+="null T_T";
        }
        s+="]\n";
        return s;
    }

    public int alea(int taille){
        return (int)(Math.random() * taille);
    }
}
