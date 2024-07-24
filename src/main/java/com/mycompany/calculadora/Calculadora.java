package com.mycompany.calculadora;

import javax.swing.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener {
    private final JTextField textField;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private double result = 0;
    private String operator = "";

    public Calculadora() {
        setTitle("Calculadora Ermenigildo");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 230, 30);
        add(textField);
// se agrega mas teclado
        String[] buttonLabels = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
                
        };

        int x = 30, y = 80;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 50, 40);
            add(button);
            button.addActionListener(this);
            x += 60;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 50;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            textField.setText(textField.getText() + command);
        } else if (command.charAt(0) == '=') {
            secondNumber = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
            }
            textField.setText(String.valueOf(result));
            operator = "";
        } else {
            if (operator.isEmpty()) {
                firstNumber = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }
}
