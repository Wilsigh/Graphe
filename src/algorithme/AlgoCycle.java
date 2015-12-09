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
        List<Integer> soluceTmp = new ArrayList();
        soluce = chercherCycle();
        System.out.println("soluce : " + affiche(soluce) + "\n");
        for(int i = 0; i < c-1; i++){
            soluceTmp = chercherCycle();
            System.out.println(affiche(soluceTmp));
            System.out.println("changement ? " + soluce.size() + " ## " + soluceTmp.size());
            if(soluceTmp.size() > soluce.size())
                System.out.println("changement");
                soluce = soluceTmp;
        }
        return soluce;
    }


    public List<Integer> chercherCycle(){

        List<Integer> cycle = new ArrayList();
        List<Integer> ListeVoisinSuivant;
        boolean boucle = false;
        int premierSeedIndex, seedIndex, voisinSuivant;
        premierSeedIndex = alea(G.size());

        //Ajout du premier sommet du cycle
        cycle.add(premierSeedIndex);
        System.out.println("Début de recherche cycle : " + affiche(cycle));
        seedIndex = premierSeedIndex;

        do{
            //Récupération des voisins du dernier seed
            ListeVoisinSuivant = G.get(seedIndex);
            System.out.println("\tdo -> " +affiche(ListeVoisinSuivant) + "seed : " + seedIndex);
            //test si il reste des voisins
            if(ListeVoisinSuivant.isEmpty()){
                System.out.println("\tnull");
                return null;
            } else {
                boucle = true;
                do {
                    //nouvel aléa sur ces voisins
                    seedIndex = alea(ListeVoisinSuivant.size() - 1) + 1;
                    System.out.println("\t-> seed : " + seedIndex);
                    //récupération du nouveau sommet
                    voisinSuivant = ListeVoisinSuivant.get(seedIndex);
                    System.out.println("\t-> " + voisinSuivant);
                    //test si le sommet appartient déjà au cycle
                    if(ListeVoisinSuivant.isEmpty()){
                        System.out.println("vide, on enlève le début");
                        return elagCycle(cycle);
                    } else if (cycle.contains(voisinSuivant)) {
                        if (cycle.get(0) == voisinSuivant) {
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
            System.out.println("\t\t->-> " + affiche(cycle) + "new turn");
        }while(cycle.contains(voisinSuivant));
        System.out.println("sortie ?");
        return cycle;
    }

    public List<Integer> elagCycle(List<Integer> cycle){
        int fin = cycle.get(cycle.size()-1);
        System.out.println("\t\tfin : " + fin + " - " + affiche(cycle));
        while(cycle.get(0)!=fin){
            cycle.remove(0);
        }
        cycle.remove(0);
        System.out.println("\t\tnew cycle -> " + affiche(cycle));
        return cycle;
    }

    public String affiche(List<Integer> cycle){
        String s = "Nouveau cycle :\n\t\t[";
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
