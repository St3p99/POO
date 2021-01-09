package poo.geometria;

public interface FiguraSolida extends FiguraPiana{//estensione di interfaccia
    //Di FiguraPiana il metodo area() ci va bene, mentre dobbiamo neutralizzare perimetro()
    double areaDiBase();
    double areaLaterale();
    double volume();
    

}