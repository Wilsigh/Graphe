package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Wilsigh on 09/12/2015.
 */
public class ListeDouble extends ArrayList<ArrayList<Integer>> {
    List<List<Integer>> listeD;
    List<Integer> sommetMarques;
    //List<List<Integer>> Gr;

    public ListeDouble(){
        listeD = new ArrayList();
        sommetMarques = new ArrayList<>();
   //     Gr = new ArrayList();
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
    /*
    * Cette fonction nous permet d'initilaiser le graph résiduel tel que:
    * Gr=[[0],[1],...,[n]]
    */
    public void initGraphResiduel(int size){
        ListeDouble Gr=new ListeDouble();
        for(int i=0;i<size;i++){
            List<Integer> L=new ArrayList<Integer>();
            L.add(i);
            Gr.add((ArrayList<Integer>) L);
        }
    }

    /*
    * Cette fonction nous permet de mettre de coté les sommet isolé (seulement un voisin)
    * En effet, nous n'avons pas besoin de les traiter car ne pose aucun problème pour la planarité du graphe
    * Mais nous devons les garder de coté pour la fin du programme
    */
    public void misDeCote(ListeDouble G){
        List<List<Integer>> misDeCote=new ArrayList<>();
        for (int i = 0; i<G.size();i++){
            List<Integer> couple=new ArrayList<>();
            if(G.get(i).size()==2){
                couple.add(G.get(i).get(0));
                misDeCote.add(couple);
                int tmp = G.get(i).get(0);
                G.get(i).remove(get(0));
                G.get(tmp).remove(i);
            }
        }
    }

    /*
    * Graph Résiduel obtenue à l'aide de notre tout premier cycle
    */
    public void premierGrapfResiduel(List<Integer> L,int size){
        ListeDouble Gr=new ListeDouble();
        Gr.initGraphResiduel(size);
        for(int i=0; i<L.size();i++){
            if(i==0) {
                if (!Gr.get(L.get(i)).contains(L.get(i + 1))) {
                    Gr.get(L.get(i)).add(L.get(i + 1));
                }
                if (!Gr.get(L.get(i)).contains(L.get(L.size() - 1))) {
                    Gr.get(L.get(i)).add(L.get(L.size() - 1));
                }
            }
            else if(i==L.size()-1){
                if (!Gr.get(L.get(i)).contains(L.get(0))) {
                    Gr.get(L.get(i)).add(L.get(0));
                }
                if (!Gr.get(L.get(i)).contains(L.get(i - 1))) {
                    Gr.get(L.get(i)).add(L.get(i - 1));
                }
            }
            else {
                if (!Gr.get(L.get(i)).contains(L.get(i + 1))) {
                    Gr.get(L.get(i)).add(L.get(i + 1));
                }
                if (!Gr.get(L.get(i)).contains(L.get(i - 1))) {
                    Gr.get(L.get(i)).add(L.get(i - 1));
                }
            }
        }
    }

    /*
    * Cela nous permet d'obtenir nos deux première face (identique)
    */
    public void premieresFace(List<Integer> cycle){
        List<List<Integer>> faces= new ArrayList<>();
        List<Integer> f1=new ArrayList<>();
        List<Integer> f2=new ArrayList<>();
        for(int i=0; i<cycle.size();i++){
            f1.add(cycle.get(i));
            f2.add(cycle.get(i));
        }
        faces.add(f1);
        faces.add(f2);
    }

    /*
    * Nous permet d'obtenir la liste d'adjacence des composantes connexe
    */
    public void composanteConnexes(List<List<Integer>> G,List<List<Integer>> Gr){
        ListeDouble compoCo = new ListeDouble();
        compoCo.initGraphResiduel(G.size());
        for(int i=0;i<G.size();i++){
            for(int j=0; j<G.get(i).size();j++){
                if(!Gr.get(i).contains(G.get(i).get(j))){
                    compoCo.get(i).add(G.get(i).get(j));
                }
            }
        }
    }

    /*
    * Rempli une liste nous permettant de savoir quel sont les sommet marqué
    */
    public void sommetMarque(List<Integer> sommetMarques,List<Integer> sommet){
        for(int i=0;i<sommet.size();i++){
            if(!sommetMarques.contains(sommet.get(i))){
                sommetMarques.add(sommet.get(i));
            }
        }
    }

    /*
    * Met à jour notre composante connexe à l'aide d'un fragment que l'on a déterminer dans une autre fonction
    */
    public void modifCompoCo(List<Integer> L,ListeDouble compoCo){
        for(int i = 0 ; i< L.size();i++){
            if(i==0){
                compoCo.get(L.get(i)).remove(L.get(i+1));
            }
            else if (i == L.size()-1){
                compoCo.get(L.get(i)).remove(L.get(i-1));
            }
            else {
                compoCo.get(L.get(i)).remove(L.get(i+1));
                compoCo.get(L.get(i)).remove(L.get(i-1));
            }
        }
    }

    /*
    * Sert à mettre à jour les marque sur les sommets
    */
    public void modifMarque(List<Integer> sommetMarques, List<Integer> L){
        for(int i=1; i<L.size()-2;i++){
             if(!sommetMarques.contains(L.get(i))){
                 sommetMarques.add(L.get(i));
             }
        }
    }
    //septique
    /*
    * Nous permet d'obtenir un fragment que l'on ppourra ajouter à Gr
    */
    public List trouverVoisin(ListeDouble compoCo, List<Integer> marque, List<Integer> L){
        List<Integer> tmp = compoCo.get(L.get(L.size()-1));
        int z=0;
        for(int i=1; i<tmp.size();i++) {
            if (tmp.get(i) != (L.get(L.size() - 1))) {
                if (marque.contains(z)) {
                    L.add(tmp.get(i));
                    break;
                } else {
                    L.add(tmp.get(i));
                    trouverVoisin(compoCo, marque, L);

                }
            }
        }
        return L;
    }

    /*
    * Nous permet de trouver un fragment et maj les marque ainsi que la composante connexe
    */
    public void trouverFrag(ListeDouble compoCo,List<Integer> sommetMarques){
        for(int i=0; i<sommetMarques.size();i++){
            if(compoCo.get(i).size()>1){
                int y= new Random().nextInt(compoCo.get(i).size() - 1);
                int z=y+1;
                List<Integer> L = new ArrayList<>();
                L.add(z);
                List<Integer> tmp = trouverVoisin(compoCo,sommetMarques,L);
                compoCo.modifCompoCo(tmp,compoCo);
                modifMarque(sommetMarques, tmp);
            }
        }
    }

    /*
    * modifie les faces
    */
    //Seulement le cas pour deux face traité, il faut finaliser la chose.
    public void modifFace(List<List<Integer>> face,List<Integer> frag){
        List<Integer> tmp=new ArrayList<>();
        if(face.size()==2){
            tmp=face.get(1);
            for(int i=0; i<tmp.size();i++){
                List fa=suppEntre(face.get(i),frag);
                List fb =suppAvAr(face.get(i), frag);
            }
        }
    }

    /*
    * Détermination de la face en remplacant les élément compris par les deux sommet marqué par notre fagment
    */
    public List suppEntre(List<Integer> face, List<Integer> frag){
        List<Integer> L=new ArrayList<>();
        boolean b=false;
        int cpt =0;
        for(int i=0; i<frag.size();i++){
            if (face.get(i)==frag.get(0)) {
                if(cpt==1){
                    b=false;
                }
                if(cpt==0){
                    b=true;
                    for(int j=0;j<frag.size();j++){
                        L.add(frag.get(j));
                    }
                    cpt++;
                }
            }else if (face.get(i)==frag.get(frag.size()-1)){
                if(cpt==1){
                    b=false;
                }
                if(cpt==0){
                    b=true;
                    //  L.add(face.get(i));
                    for(int j=frag.size();j>0;j--){
                        L.add(frag.get(j));
                    }
                    cpt++;
                }

            }else if (!b){
                L.add(face.get(i));
            }
        }
        return L;
    }

    /*
    * De même mais en touchant à ce avant et après les sommets marqué
    */
    //à vérifier
    public List suppAvAr(List<Integer> face,List<Integer> frag){
        List<Integer> L=new ArrayList<>();
        boolean b=false;
        int cpt =0;
        for(int i=0; i<frag.size();i++){
            if (face.get(i)==frag.get(0)) {
                if(cpt==1){
                    b=false;
                    for(int j=0;j<frag.size();j++){
                        L.add(frag.get(j));
                    }
                }
                if(cpt==0){
                    b=true;
                    cpt++;
                }
            }else if (face.get(i)==frag.get(frag.size()-1)){
                if(cpt==1){
                    b=false;
                    for(int j=frag.size();j>0;j--){
                        L.add(frag.get(j));
                    }

                }
                if(cpt==0){
                    b=true;
                    cpt++;
                }

            }else if (b){
                L.add(face.get(i));
            }
        }
        return L;
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
