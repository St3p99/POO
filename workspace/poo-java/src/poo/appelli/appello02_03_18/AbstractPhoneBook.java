package poo.appelli.appello02_03_18;

import java.util.Iterator;

public abstract class AbstractPhoneBook implements PhoneBook{
    public int hashCode(){
        int hc = 0;
        final int M = 43;
        for(Persona p: this) hc = hc*M + p.hashCode();
        return hc;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        sb.append('[');
        Iterator<Persona> it = iterator();
        while(it.hasNext()){
            sb.append(it.next());
            if(it.hasNext()) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PhoneBook)) return false;
        PhoneBook pb = (PhoneBook) o;
        if (pb.size() != size()) return false;
        Iterator<Persona> it1 = iterator(), it2 = pb.iterator();
        while (it1.hasNext())
            if (!it1.next().equals(it2.next())) return false;
        return true;
    }
}
