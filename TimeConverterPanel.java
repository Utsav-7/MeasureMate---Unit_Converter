import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TimeConverterPanel extends JPanel {
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JTextField fromValue;
    private JLabel resultLabel;

    public TimeConverterPanel() {
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

        String[] units = {"Seconds", "Minutes", "Hours", "Days"};
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
        private final Map<String, Double> unitToSeconds;

        public ConvertActionListener() {
            unitToSeconds = new HashMap<>();
            unitToSeconds.put("Seconds", 1.0);
            unitToSeconds.put("Minutes", 60.0);
            unitToSeconds.put("Hours", 3600.0);
            unitToSeconds.put("Days", 86400.0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double value = Double.parseDouble(fromValue.getText());
                String from = (String) fromUnit.getSelectedItem();
                String to = (String) toUnit.getSelectedItem();

                double valueInSeconds = value * unitToSeconds.get(from);
                double convertedValue = valueInSeconds / unitToSeconds.get(to);

                resultLabel.setText(String.format("Result: %.4f %s", convertedValue, to));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        }
    }
}
