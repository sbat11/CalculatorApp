import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    // Swing variables
    JFrame jFrame;
    JTextField jTextField;
    JPanel jPanel;

    Font fieldFont;
    Font buttonFont;

    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[8];

    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;

    JButton dotButton;
    JButton eqButton;
    JButton negButton;
    JButton clearButton;

    // Logic variables
    double accumulator = 0.0;
    String selectedOperation = "";

    public Calculator() {
        // Main Frame
        jFrame = new JFrame("Simple Calculator");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(460, 460);
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        // Interface font
        fieldFont = new Font("Cambria Math", Font.BOLD, 40);
        buttonFont = new Font("Cambria Math", Font.PLAIN, 25);

        // Main text field
        jTextField = new JTextField();
        jTextField.setBounds(40,40,370,60);
        jTextField.setFont(fieldFont);
        jTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextField.setEditable(false);
        jTextField.setBorder(new LineBorder(new Color(157, 159, 170), 2));
        jTextField.setText("");
        jFrame.add(jTextField);

        // Buttons instantiation
        for (int i = 0; i < numButtons.length; i++) {
            String buttonName = String.valueOf(i);
            JButton newJButton = new JButton(buttonName);
            newJButton.setFont(buttonFont);
            newJButton.addActionListener(this);
            newJButton.setFocusable(false);

            numButtons[i] = newJButton;
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("÷");

        dotButton = new JButton(".");
        eqButton = new JButton("=");
        negButton = new JButton("+/-");
        clearButton = new JButton("C");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;

        funcButtons[4] = dotButton;
        funcButtons[5] = eqButton;
        funcButtons[6] = negButton;
        funcButtons[7] = clearButton;

        for(int i = 0; i < funcButtons.length; i++){
            funcButtons[i].setFont(buttonFont);
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
        }

        // Buttons placement
        jPanel = new JPanel();
        jPanel.setBounds(40, 120, 370, 210);
        jPanel.setLayout(new GridLayout(4, 4, 10, 10));

        jPanel.add(numButtons[7]);
        jPanel.add(numButtons[8]);
        jPanel.add(numButtons[9]);
        jPanel.add(clearButton);
        jPanel.add(numButtons[4]);
        jPanel.add(numButtons[5]);
        jPanel.add(numButtons[6]);
        jPanel.add(divButton);
        jPanel.add(numButtons[1]);
        jPanel.add(numButtons[2]);
        jPanel.add(numButtons[3]);
        jPanel.add(mulButton);
        jPanel.add(negButton);
        jPanel.add(numButtons[0]);
        jPanel.add(dotButton);
        jPanel.add(subButton);

        eqButton.setBounds(40, 340, 275, 50);
        jFrame.add(eqButton);

        addButton.setBounds(325, 340, 85, 50);
        jFrame.add(addButton);

        jFrame.add(jPanel);

        // Show frame
        jFrame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        for(int i = 0; i < numButtons.length; i++){
            if(source == numButtons[i]){
                String number = source.getText();

                jTextField.setText(jTextField.getText() + number);
            }
        }

        if(source == dotButton && !jTextField.getText().contains(".")){
            jTextField.setText(jTextField.getText()+".");
        }

        //Operations (+, -, /, *)
        if(source == addButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "+";
            jTextField.setText("");
        }
        if(source == subButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "-";
            jTextField.setText("");
        }
        if(source == mulButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "*";
            jTextField.setText("");
        }
        if(source == divButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "÷";
            jTextField.setText("");
        }
    
        //Negate Button
        if(source == negButton && !jTextField.getText().equals("")){
            double newValue = Double.parseDouble(jTextField.getText());
            String newText = String.valueOf(newValue);

            jTextField.setText(newText);

        }
        //Clear Calculator
        if(source == clearButton){
            jTextField.setText("");
            accumulator = 0.0;
            selectedOperation = "";
        }

        //Equals Button
        if(source == eqButton && !selectedOperation.equals("") && !jTextField.getText().equals("")){
            double textFieldValue = Double.parseDouble(jTextField.getText());

            double newValue = 0.0;
            switch (selectedOperation){
                case "+": newValue = accumulator + textFieldValue; break;
                case "-": newValue = accumulator - textFieldValue; break;
                case "*": newValue = accumulator * textFieldValue; break;
                case "÷": newValue = accumulator / textFieldValue; break;
            }
            accumulator = 0.0;
            selectedOperation = "";
            jTextField.setText(String.valueOf(newValue));
        }
    }
    
}
