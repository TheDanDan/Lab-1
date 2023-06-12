import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
    double coef[];
    int exp[];

    //constructor: no return type, same name as class
    public Polynomial() {
        double[] newCoef = {};
        int[] newExp = {};
        this.exp = newExp;
        this.coef = newCoef;
    }
    public Polynomial(double[] coef, int[] exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public Polynomial(File file){
        String data = "";
        try {
            Scanner myReader = new Scanner(file);

            data = myReader.nextLine();

            myReader.close();
            if(data == ""){
                double[] newCoef = {};
                int[] newExp = {};
                this.exp = newExp;
                this.coef = newCoef;
            }
            else{
                int ind = 0;
                String[] arr = data.split("[+-]");

                double[] coef = new double[arr.length];
                int[] exp = new int[arr.length];

                int neg = data.indexOf("-");
                int pos = data.indexOf("+");

                int i = 0;
                for (String term : arr) {
                    String[] coex = term.split("x");
                    coef[i] = Double.parseDouble(coex[0]);
                    exp[i] = Integer.parseInt(coex[1]);

                    if(neg == 0 || (neg != -1 && neg < pos)){
                        coef[i] *= -1;
                    }
                    ind += term.length() + 1;

                    neg = data.indexOf("-", ind);
                    pos = data.indexOf("+", ind);

                    i++;
                }
                this.coef = coef;
                this.exp = exp;
            }
        } catch (FileNotFoundException e) {
            double[] newCoef = {};
            int[] newExp = {};
            exp = newExp;
            coef = newCoef;
        }
    }

    public Polynomial add(Polynomial y) {
        int xLength = exp.length;
        int yLength = y.exp.length;
        if(xLength == 0 || yLength == 0){
            if (xLength > 0){
                return this;
            }
            else{
                return y;
            }
        }
        int newLength = 0;
        for(int i = 0; i < xLength; i++){
            for(int j = 0; j < yLength; j++){
                if(exp[i] == y.exp[j]){
                    if(this.coef[i] + y.coef[j] == 0)
                        newLength += 2;
                }
            }
        }

        newLength = xLength + yLength - newLength;

        double[] newCoef = new double[newLength];
        int[] newExp = new int[newLength];

        int xInd = 0;
        for(int i = 0; i < xLength; i++){
            boolean match = false;
            for(int j = 0; j < yLength; j++){
                if(exp[i] == y.exp[j]){
                    match = true;
                    if(this.coef[i] + y.coef[j] != 0){
                        newExp[xInd] = this.exp[i];
                        newCoef[xInd] = this.coef[i] + y.coef[j];
                        xInd++;
                    }
                }
            }
            if(match == false){
                newExp[xInd] = this.exp[i];
                newCoef[xInd] = this.coef[i];
                xInd++;
            }
        }

        for(int i = 0; i < yLength; i++){
            boolean match = false;
            for(int j = 0; j < xLength; j++){
                if(y.exp[i] == this.exp[j]){
                    match = true;
                    break;
                }
            }
            if(match == false){
                newExp[xInd] = y.exp[i];
                newCoef[xInd] = y.coef[i];
                xInd++;
            }
        }

        Polynomial newPoly = new Polynomial(newCoef, newExp);

        return newPoly;
    }

    public double evaluate(double x) {
        double result = 0;
        for(int i = 0; i < coef.length; i++){
            result += coef[i] * Math.pow(x, exp[i]);
        }
        return result;
    }
    public boolean hasRoot(double x) {
        if(evaluate(x) == 0){
            return true;
        }
        return false;
    }

    public Polynomial multiply(Polynomial that){
        Polynomial newPoly = new Polynomial();


        for(int i = 0; i < that.coef.length; i++){
            Polynomial temp = new Polynomial(this.coef.clone(), this.exp.clone());
            for(int j = 0; j < this.coef.length; j++){
                temp.coef[j] *= that.coef[i];
                temp.exp[j] += that.exp[i];
            }
            newPoly = newPoly.add(temp);
        }
        return newPoly;
    }

    public void saveToFile(String name){
        try {
            File file = new File(name);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(name);
            String poly = "";
            for(int i = 0; i < this.coef.length; i++){
                if(coef[i] > 0 && i != 0){
                    poly = poly + "+";
                }
                poly = poly + String.valueOf(this.coef[i]) + "x" + String.valueOf(this.exp[i]);
            }
            myWriter.write(poly);
            myWriter.close();
        } catch (IOException e) {
        }
    }
}
