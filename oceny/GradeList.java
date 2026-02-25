package oceny;

import java.util.ArrayList;


public class GradeList {
    private ArrayList<Double> oceny;
    private Double ocena;

    public GradeList(){

        oceny = new ArrayList<>();
    }
    public void dodajOcene(double wejscie){  // dodawanie nowej oceny
        oceny.add(wejscie);
    }
    public double obliczSrednia() {  // obliczanie średniej z ocen
        if (oceny.size() == 0) {
            return -1;
        } else {
            double suma = 0;
            for (double ocena : oceny) suma += ocena;
            return suma / oceny.size();
        }
    }
    public double znajdzMax() { //wyszukiwanie i wyświetlanie najwyższej oceny
        if (oceny.size() == 0){
            return -1;
        }
        else {
            double max = oceny.get(0);
            for(double ocena : oceny) {
                if (ocena > max){
                    max = ocena;
                }
            }
            return max;
        }
    }
    public double znajdzMin(){ // wyszukiwanie i wyświetlanie najniższej oceny
        if (oceny.size() == 0){
            return -1;
        } else {
            double min = oceny.get(0);
            for(double ocena : oceny) {
                if (ocena < min){
                    min = ocena;
                }
            }
            return min;
        }
    }
}












































