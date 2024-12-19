import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField productNameField, unitPriceField, quantityField;
    private Invoice invoice;

    public InvoiceApp() {
        frame = new JFrame("Invoice Application");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JButton addButton = new JButton("Add Line Item");
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton calculateButton = new JButton("Calculate Total");
        addButton.setPreferredSize(new Dimension(150, 30));
        frame.add(calculateButton, BorderLayout.SOUTH);

        invoice = new Invoice();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                double unitPrice = Double.parseDouble(unitPriceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Product product = new Product(productName, unitPrice);
                LineItem lineItem = new LineItem(product, quantity);
                invoice.addLineItem(lineItem);

                textArea.append(productName + " - " + quantity + " @ $" + unitPrice + "\n");
                productNameField.setText("");
                unitPriceField.setText("");
                quantityField.setText("");
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = invoice.calculateTotal();
                textArea.append("\n----------------------------\n");
                textArea.append("Total Amount Due: $" + total + "\n");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new InvoiceApp();
    }
}
