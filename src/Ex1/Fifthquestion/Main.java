package Ex1.Fifthquestion;

/**
 * @author 15328
 * *5、已知XYZ+YZZ=532，
 * 其中X、Y和Z为数字，
 * 编程求出X，Y和Z的值。
 */
public class Main {
    public static void main(String[] args) {
        /*
          x*100+y*110+z*12=532
          x00+yy0+z0+2*z=532
          (x+y) (y+z+2*z/10) (2*z%10)
          (x+y) (y+z+z/5) (2*z%10)
          (x+y)*100 + (y+z*6/5)*10 + (2*z%10) == 532
           x>0 y>0 (x、y都做过百位数，必须大于零)
           z>0(z*12是532的末尾数2的唯一来源
           x+y<=5
           x>=1
           y>=1
           x<=4
           y<=4
           (532-2*z) % 10 == 0
           1=<z<10
         */
        int sum ;
        for(int z = 1; z < 10; z++) {
            if((532 - 2 * z) % 10 == 0) {
                for(int x = 1; x <= 4; x++) {
                    for(int y = 1; y + x <= 5; y++) {
                        sum = x*100+y*100;
                        sum += (y+z+z/5)*10 + (2*z)%10;
                        if(sum == 532) {
                            System.out.println("x: " + x + " y: " + y + " z: " + z);
                        }
                    }
                }
            }
        }
    }
}
