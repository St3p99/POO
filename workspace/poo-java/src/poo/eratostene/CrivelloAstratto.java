package poo.eratostene;

public abstract class CrivelloAstratto implements Crivello{
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        int c=1;
        for(int p: this){
            sb.append(String.format("%10d", p));
            if(c%8==0) sb.append("\n");
            c++;
        }
        sb.append("\n"); return sb.toString();
    }//toString
}