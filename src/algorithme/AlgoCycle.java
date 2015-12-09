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
        System.out.println("soluce : " + affiche(soluce));
        for(int i = 0; i < c-1; i++){
            soluceTmp = chercherCycle();
            System.out.println(affiche(soluceTmp));
            if(soluceTmp.size() > soluce.size())
                System.out.println("changement " + soluce.size() + " < " + soluceTmp.size());
                soluce = soluceTmp;
        }
        return soluce;
    }

    public List<Integer> chercherCycle(){

        List<Integer> cycle = new ArrayList();
        List<Integer> ListeVoisinSuivant = new ArrayList();
        int firstSeed, seed, voisinSuivant;
        firstSeed = alea(G.size());

        //Ajout du premier sommet du cycle
        cycle.add(firstSeed);

        seed = firstSeed;
        do{
            //Récupération des voisins du dernier seed
            ListeVoisinSuivant = G.get(seed);
            System.out.println("do -> " + affiche(ListeVoisinSuivant) + ", seed : " + seed);
            //test si il reste des voisins
            if(ListeVoisinSuivant.isEmpty()) {
                System.out.println("null");
                return null;
            } else {
                //nouvel aléa sur ces voisins
                seed = alea(ListeVoisinSuivant.size()-1)+1;
                System.out.println("-> seed : " + seed);
                //récupération du nouveau sommet
                voisinSuivant = ListeVoisinSuivant.get(seed);
                System.out.println("-> " + voisinSuivant);
                //test si le sommet appartient déjà au cycle
                if(cycle.contains(voisinSuivant)){
                    if(cycle.get(0) == voisinSuivant){
                        System.out.println("cycle");
                        return cycle;
                    } else {
                        ListeVoisinSuivant.remove(seed);
                    }
                } else {
                    cycle.add(cycle.size(),voisinSuivant);
                }
            }
            seed = voisinSuivant;
            System.out.println("->-> " + affiche(cycle) + "new turn");
        }while(cycle.contains(voisinSuivant));
        System.out.println("sortie ?");
        return cycle;
    }

    public String affiche(List<Integer> cycle){
        String s = "Nouveau cycle :\n[";
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
