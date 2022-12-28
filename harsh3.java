import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class harsh3 extends JApplet implements ActionListener{
    private JButton[] buttons;
    private String[] ops = {"CE", "/", "*", "-", ".", "+", "MEM", "MRC",
            "SIN", "TAN", "ASIN", "COS","ATAN","X","SQRT","EXP","PI",
            "%", "="};
    private JLabel output, blank;
    private Container contentP;
    private String operation;
    private double number1, number2, result;
    private boolean cleared = false;

    public void init(){
        contentP = getContentPane();
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        output = new JLabel("");
        output.setBorder(new MatteBorder(2, 2, 2, 2, Color.gray));
        output.setPreferredSize(new Dimension(1, 26));
        contentP.setBackground(Color.white);
        contentP.add("North", output);
        contentP.add("Center", container);

        blank = new JLabel(" ");
        container.add(blank);

        buttons = new JButton[29];
        for (int i = 0; i < 10; ++i){
            buttons[i] = new JButton((new Integer(i)).toString());
        }
        for (int i = 10; i < buttons.length; ++i){
            buttons[i] = new JButton(ops[i - 10]);
        }
        for (int i = 0; i < buttons.length; ++i){
            buttons[i].addActionListener(this);
        }
        JButton[] alignButtons = new JButton[29];
        alignButtons[0] = buttons[10]; // CE
        alignButtons[4] = buttons[11]; // /
        alignButtons[8] = buttons[12]; // *
        alignButtons[12] = buttons[13]; // -
        alignButtons[1] = buttons[7]; // 7
        alignButtons[2] = buttons[8]; // 8
        alignButtons[3] = buttons[9]; // 9
        alignButtons[5] = buttons[4]; // 4
        alignButtons[6] = buttons[5]; // 5
        alignButtons[7] = buttons[6]; // 6
        alignButtons[9] = buttons[1]; // 1
        alignButtons[10] = buttons[2]; // 2
        alignButtons[11] = buttons[3]; // 3
        alignButtons[13] = buttons[0]; // 0
        for (int i = 14; i < buttons.length; ++i){
            alignButtons[i] = buttons[i];
        } addButtons(alignButtons, container);
    } // init()
    void addButtons(JButton[] btns, Container cont){
        for (int i = 0; i < btns.length; ++i){
            cont.add(btns[i]);
        }
    }
    public static void main(String args[]){
        JFrame applicationWindow = new JFrame("calculator");
        applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        harsh3 appletObject = new harsh3();
        applicationWindow.getContentPane().add(appletObject, BorderLayout.CENTER);
        applicationWindow.setBounds(0,0, 500, 200);
        applicationWindow.setVisible(true);
        appletObject.init();
        appletObject.start();
    } // main()
    public void actionPerformed(ActionEvent ae){
        JButton but = (JButton)ae.getSource();
        String op = but.getText();

        if (op.equals(".")){
            String temp = output.getText();
            if(temp.indexOf('.') == -1){
                output.setText(output.getText() + op);
            }
        }
        else if(op.equals("CE")){
            output.setText("");
            operation = "";
            number1 = 0.0;
            number2 = 0.0;
        }
        else if("+-*/".indexOf(op) != -1){
            operation = op;
            try{
                number1 = Double.parseDouble(output.getText());
            }
            catch(Exception e){
                e.printStackTrace();
                number1 = 0.0;
            }
            finally{
                cleared = true;
            }
        }
        else if(op.equals("=")){
            try{
                number2 = Double.parseDouble(output.getText());
            }
            catch(Exception e){
                e.printStackTrace();
                number2 = 0.0;
            }
            if(operation.equals("+")){
                result = number1 + number2;
            }
            else if(operation.equals("-")){
                result = number1 - number2;
            }
            else if(operation.equals("*")){
                result = number1 * number2;
            }
            else if(operation.equals("/")){
                result = number1 / number2;
            }
            output.setText(String.valueOf(result));
            cleared = true;
            operation = "";
        }
        else{
            if(cleared == true){
                output.setText("");
                cleared = false;
            }
            output.setText(output.getText() + but.getText());
        }
    }
}