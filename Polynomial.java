public class Polynomial {
    double arr[];

    //constructor: no return type, same name as class
    public Polynomial() {
        double[] newarr = {0.0};
        arr = newarr;
    }
    public Polynomial(double[] input) {
        arr = input;
    }
    public Polynomial add(Polynomial y) {
        int arrLength = arr.length;
        int yLength = y.arr.length;
        double[] newArr = new double[Math.max(arrLength, yLength)];

        for(int i = 0; i < newArr.length; i++){
            int sum = 0;
            if(i < arrLength){
                sum += arr[i];
            }
            if(i < yLength){
                sum += y.arr[i];
            }
            newArr[i] = sum;
        }
        Polynomial newPoly = new Polynomial(newArr);

        return newPoly;
    }
    public double evaluate(double x) {
        double result = 0;
        for(int i = 0; i < arr.length; i++){
            result += arr[i] * Math.pow(x, i);
        }
        return result;
    }
    public boolean hasRoot(double x) {
        if(evaluate(x) == 0){
            return true;
        }
        return false;
    }
}
