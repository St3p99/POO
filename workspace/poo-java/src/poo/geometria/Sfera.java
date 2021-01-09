package poo.geometria;

public class Sfera extends Disco implements FiguraSolida{
    //ereditiamo raggio da Disco e il centro da Punto

    public Sfera(double r){//centro == origine
        super(r);
    }

    public Sfera(double x, double y, double r){
        super(x,y,r);
    }

    public Sfera(Punto p, double r){
        super(p, r);
    }

    public Sfera(Sfera s){
        super(s.getX(), s.getY(), s.getRaggio());
    }
    
    @Override
    public double perimetro() {
        throw new UnsupportedOperationException("Il perimetro non é definito per una sfera");
    }

    @Override
    public double area(){//area totale
        double r = getRaggio();
        return 4*Math.PI*r*r;
    }

    
    @Override
    public double areaDiBase() {
        return super.area();//area del disco individuato dal piano passante per il centro
    }

    @Override
    public double areaLaterale() {
        return area(); // coincide con l'area totale
    }

    public String toString(){
        return "Sfera di raggio="+String.format("% 1.3f", getRaggio())+
        " con centro= ("+getX()+";"+getY()+")";
    }

    public boolean equals(Object o){
        if(!(o instanceof Sfera)) return false;
        if(o==this) return true;
        Sfera s = (Sfera) o;
        return this.getRaggio()==s.getRaggio();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public double volume() {
        double r = getRaggio();
        return (4/3)*Math.PI*Math.pow(r, 3);
    }

    public static void main(String ... args){
        Sfera s = new Sfera(1,3,18.3);
        System.out.println(s);
    }
    
}