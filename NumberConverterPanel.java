import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberConverterPanel extends JPanel {
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JTextField fromValue;
    private JLabel resultLabel;

    public NumberConverterPanel() {
        setLayout(new BorderLayout());

        // Background Panel with Image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("background.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Converter Panel
        JPanel converterPanel = new JPanel();
        converterPanel.setLayout(new GridBagLayout());
        converterPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.WHITE);
        converterPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        fromValue = new JTextField(10);
        converterPanel.add(fromValue, gbc);

        String[] units = {"Binary", "Decimal", "Hexadecimal", "Octal"};
        fromUnit = new JComboBox<>(units);
        gbc.gridx = 2;
        converterPanel.add(fromUnit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.WHITE);
        converterPanel.add(toLabel, gbc);

        gbc.gridx = 1;
        toUnit = new JComboBox<>(units);
        converterPanel.add(toUnit, gbc);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertActionListener());
        gbc.gridx = 2;
        converterPanel.add(convertButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        converterPanel.add(resultLabel, gbc);

        backgroundPanel.add(converterPanel);

        add(backgroundPanel, BorderLayout.CENTER);
    }

    private class ConvertActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int value = Integer.parseInt(fromValue.getText(), getBase((String) fromUnit.getSelectedItem()));
                String to = (String) toUnit.getSelectedItem();
                String result = convertNumber(value, getBase(to));
                resultLabel.setText(String.format("Result: %s", result));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        }

        private int getBase(String unit) {
            switch (unit) {
                case "Binary":
                    return 2;
                case "Decimal":
                    return 10;
                case "Hexadecimal":
                    return 16;
                case "Octal":
                    return 8;
                default:
                    return 10;
            }
        }

        private String convertNumber(int value, int base) {
            switch (base) {
                case 2:
                    return Integer.toBinaryString(value);
                case 8:
                    return Integer.toOctalString(value);
                case 16:
                    return Integer.toHexString(value).toUpperCase();
                default:
                    return Integer.toString(value);
            }
        }
    }
}
