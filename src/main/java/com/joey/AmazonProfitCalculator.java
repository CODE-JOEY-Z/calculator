package com.joey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AmazonProfitCalculator extends JFrame {
    private JComboBox<String> modeComboBox;
    private JTextField purchasePriceField;
    private JTextField sellingPriceField;
    private JTextField fbaFeeField;
    private JTextField shippingFeeField;
    private JTextField exchangeRateField;
    private JTextField vatField;
    private JTextField costField;
    private JTextField resultField;
    private JButton calculateButton;
    private Font font;

    public AmazonProfitCalculator() {
        setTitle("他姓卓的");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 2));

        // 设置字体
        font = new Font("Arial", Font.PLAIN, 16);

        modeComboBox = new JComboBox<>(new String[]{"SC Mode", "VC Mode"});
        modeComboBox.addActionListener(new ModeChangeListener());
        modeComboBox.setFont(font);

        purchasePriceField = new JTextField();
        purchasePriceField.setFont(font);
        sellingPriceField = new JTextField();
        sellingPriceField.setFont(font);
        fbaFeeField = new JTextField();
        fbaFeeField.setFont(font);
        shippingFeeField = new JTextField();
        shippingFeeField.setFont(font);
        exchangeRateField = new JTextField();
        exchangeRateField.setFont(font);
        vatField = new JTextField();
        vatField.setFont(font);
        costField = new JTextField();
        costField.setFont(font);
        resultField = new JTextField();
        resultField.setFont(font);
        resultField.setEditable(false);

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculateButton.addActionListener(new CalculateButtonListener());

        add(createLabel("Mode:"));
        add(modeComboBox);
        add(createLabel("Purchase Price (RMB):"));
        add(purchasePriceField);
        add(createLabel("Selling Price (SC Mode):"));
        add(sellingPriceField);
        add(createLabel("FBA Fee (SC Mode):"));
        add(fbaFeeField);
        add(createLabel("Shipping Fee (RMB):"));
        add(shippingFeeField);
        add(createLabel("Exchange Rate (EUR/USD to RMB):"));
        add(exchangeRateField);
        add(createLabel("VAT (VC Mode):"));
        add(vatField);
        add(createLabel("Cost (VC Mode):"));
        add(costField);
        add(createLabel("Profit and ROI:"));
        add(resultField);
        add(new JLabel());
        add(calculateButton);

        updateFieldsVisibility();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private void updateFieldsVisibility() {
        String mode = (String) modeComboBox.getSelectedItem();
        boolean isSCMode = "SC Mode".equals(mode);

        sellingPriceField.setEnabled(isSCMode);
        fbaFeeField.setEnabled(isSCMode);
        vatField.setEnabled(!isSCMode);
        costField.setEnabled(!isSCMode);
    }

    private class ModeChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateFieldsVisibility();
        }
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String mode = (String) modeComboBox.getSelectedItem();
                double purchasePriceRMB = Double.parseDouble(purchasePriceField.getText());
                double shippingFeeRMB = Double.parseDouble(shippingFeeField.getText());
                double exchangeRate = Double.parseDouble(exchangeRateField.getText());

                double profit;
                double roi;

                if ("SC Mode".equals(mode)) {
                    double sellingPrice = Double.parseDouble(sellingPriceField.getText());
                    double fbaFee = Double.parseDouble(fbaFeeField.getText());
                    profit = sellingPrice - purchasePriceRMB - fbaFee - shippingFeeRMB;
                    roi = (profit / (purchasePriceRMB + shippingFeeRMB)) * 100;
                } else {
                    double vat = Double.parseDouble(vatField.getText());
                    double cost = Double.parseDouble(costField.getText());
                    profit = cost * (1 - vat) * exchangeRate - purchasePriceRMB - shippingFeeRMB;
                    roi = (profit / (purchasePriceRMB + shippingFeeRMB)) * 100;
                }

                resultField.setText(String.format("Profit: %.2f RMB, ROI: %.2f%%", profit, roi));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AmazonProfitCalculator.this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AmazonProfitCalculator calculator = new AmazonProfitCalculator();
            calculator.setVisible(true);
        });
    }
}
