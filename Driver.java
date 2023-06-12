import java.io.File;
public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,5};
        int [] e1 = {3,4};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-6, 4};
        int [] e2= {3, 2};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = new Polynomial(new File("pol.txt"));
        s.saveToFile("pol.txt");
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}