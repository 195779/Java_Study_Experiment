package Algorithms.A_First;

public class Matrix {
    public static double dot(double[]x,double[]y){
        /*向量点乘函数*/
        if(x.length!=y.length){
            System.out.println("元素个数不同，分属于不同维的向量，无法点乘，返回-1(double类型)");
            return -1;
        }
        double answer = 0.0;
        for(int i=0;i<x.length;i++){
            answer += x[i]*y[i];
        }
        return answer;
    }
    public static double[][] mult(double[][] a, double[][] b){
        /*求解矩阵与矩阵之积: a*b
         * a:   m*p
         * b:   p*n
         * a*b: m*n */
        if(a[0].length == b.length){
            double [][]number = new double[a.length][b[0].length];
            double mult_x = 0.0;
            for(int i=0;i<a.length;i++){
                for(int j=0;j<b[0].length;j++){
                    for(int k=0;k<b.length;k++){
                        mult_x += a[i][k]*b[k][j];
                    }
                    number[i][j] = mult_x;
                    mult_x = 0.0;
                }
            }
            return number;
        }
        else{
            return null;
        }
    }

    public static double[][] transpose (double [][] a){
        /*矩阵转置函数*/
        double [][] number = new double[a[0].length][a.length];
        for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a.length;j++){
                number[i][j] = a[j][i];
            }
        }
        return number;
    }

    public static void test_for_transpose (){
        /*测试矩阵转置函数*/
        double[][] a = new double[][]{{1,2},{2,1},{1,3}};
        double[][] c = transpose(a);
        System.out.println("矩阵A：");
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.printf("%5.2f\t ",a[i][j]);
            }
            System.out.println();
        }
        System.out.println("矩阵B：");
        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[0].length;j++){
                System.out.printf("%5.2f\t ",c[i][j]);
            }
            System.out.println();
        }
    }

    public static void test_for_mult() {
        /*测试矩阵乘法函数*/
        double[][] a = new double[][]{{1,2},{2,1},{1,3}};
        double[][] b = new double[][]{{1,2,3,4},{4,3,2,1}};
        System.out.println("矩阵A：");
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.printf("%5.2f\t ",a[i][j]);
            }
            System.out.println();
        }
        System.out.println("矩阵B：");
        for(int i=0;i<b.length;i++){
            for(int j=0;j<b[0].length;j++){
                System.out.printf("%5.2f\t ",b[i][j]);
            }
            System.out.println();
        }
        double[][] c = mult(a,b);
        System.out.println("矩阵c：");
        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[0].length;j++){
                System.out.printf("%5.2f\t ",c[i][j]);
            }
            System.out.println();
        }
    }

    public static void test_for_dot(){
        double [] a = new double[] {1,2,3,4,5,6};
        double [] b = new double[] {6,5,4,3,2,1};
        System.out.println("向量A：");
        for(int i=0;i<a.length;i++){
            System.out.printf("%5.2f\t ",a[i]);
        }
        System.out.println();
        System.out.println("向量B：");
        for(int i=0;i<b.length;i++){
            System.out.printf("%5.2f\t ",b[i]);
        }
        System.out.println();
        System.out.println("向量点乘结果：" + dot(a,b));
    }
    public static void main(String[] args) {
        //test_for_mult();
        //test_for_transpose();
        test_for_dot();
    }
}
