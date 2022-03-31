package GUI.First;

import java.awt.*;

/**
 * @author 15328
 */
public class AddFrame extends Frame {
    public static void main(String[] args) {
        new AddFrame();
    }
    public AddFrame (){
        super("加法运算");

        this.setSize(400,200);


        /*this.setLayout(new BorderLayout());
        this.add(new Button("zhong"));
        this.add(new Button("dong"),"East");*/


        /*this.setLayout(new FlowLayout());
        this.setLocation(300,240);
        this.add(new TextField("10",8));
        this.add(new Label("+"));
        this.add(new TextField("20",8));
        this.add(new Button("="));
        this.add(new TextField("10"));*/

        /*this.setLayout(new GridLayout(4,4));
        String [] str ={ "7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
        for(int i=0; i<str.length; i++){
            this.add(new Button(str[i]));
        }
        this.add(new TextField(""));*/

        this.setVisible(true);
    }
}
