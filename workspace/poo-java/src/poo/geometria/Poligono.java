package poo.geometria;

public class Poligono implements FiguraPiana {
    private Punto[] vertici;

    public Poligono(Punto[] vertici){
        if(vertici.length < 3) throw new IllegalArgumentException("Il poligono non esiste");
        //ipotesi: i vertici formano un poligono CONVESSO e sono passati in senso antiorario risp. all'origine degli assi
        this.vertici = new Punto[vertici.length];
        for (int i = 0; i<vertici.length; i++)
            this.vertici[i]= vertici[i];
    }
    public Poligono(Poligono p){
        this.vertici = new Punto[p.vertici.length];
        for (int i = 0; i<p.vertici.length; i++)
            this.vertici[i]= p.vertici[i];
    }
    
    public double perimetro(){
        double perimetro=0;
        for (int i = 0; i<vertici.length-1; i++)
            perimetro+=vertici[i].distanza(vertici[i+1]);
        return perimetro;
    }

    public double area(){
        double area=0;
        for (int i = 1; i <vertici.length-1; i++) {
            double a = vertici[0].distanza(vertici[i]);
            double b = vertici[i].distanza(vertici[i+1]);
            double c = vertici[0].distanza(vertici[i+1]);
            double sp = (a+b+c)/2;
            area+= Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
        }
        return area;
    }

    public String toString(){
        String s=  "Poligono di vertici=\n";
        for (int i=0; i<vertici.length; i++)
            s+=(i+1)+")"+vertici[i]+"\n";
        return s;
    }

    public int hashCode(){
        int primo = 43;
        int hC=0;
        for (int i = 0; i <vertici.length; i++) {
            hC = hC*primo + vertici[i].hashCode();
        }
        return hC;
    }

    public boolean equals(Object o){
        if(!(o instanceof Poligono)) return false;
        if(o==this) return true;
        Poligono p = (Poligono) o;
        for (int i = 0; i <this.vertici.length; i++)
            if(!(this.vertici[i].equals(p.vertici[i])))
                return false;
        return true;
    }

    public static void main(String[] args){
        Punto A = new Punto(2,1);
        Punto B = new Punto(5,-3);
        Punto C = new Punto(-7,-3);
        Punto D = new Punto(-4,1);
        Poligono p = new Poligono(new Punto[]{A,B,C,D});
        Poligono p2 = new Poligono(p);
        System.out.println(p.perimetro());
        System.out.println(p.area());
        System.out.println(p);
        System.out.println(p2);
        System.out.println(p.equals(p2));
        

    }
}
