import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterPanel extends JPanel {
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JTextField fromValue;
    private JLabel resultLabel;

    public TemperatureConverterPanel() {
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

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
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
                double value = Double.parseDouble(fromValue.getText());
                String from = (String) fromUnit.getSelectedItem();
                String to = (String) toUnit.getSelectedItem();

                double convertedValue = convertTemperature(value, from, to);

                resultLabel.setText(String.format("Result: %.2f %s", convertedValue, to));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        }

        private double convertTemperature(double value, String from, String to) {
            if (from.equals(to)) {
                return value;
            }

            double result = value;

            // Convert from source to Celsius
            switch (from) {
                case "Fahrenheit":
                    result = (value - 32) * 5 / 9;
                    break;
                case "Kelvin":
                    result = value - 273.15;
                    break;
            }

            // Convert from Celsius to target
            switch (to) {
                case "Fahrenheit":
                    result = (result * 9 / 10) + 32;
                    break;
                case "Kelvin":
                    result = result + 273.15;
                    break;
            }

            return result;
        }
    }
}
