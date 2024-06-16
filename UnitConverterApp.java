import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverterApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public UnitConverterApp() {
        setTitle("Unit Converter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background Panel with Image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("background.jpg"); // Make sure you have a background.jpg in the project directory
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Large "MeasureMate" Label
        JLabel titleLabel = new JLabel("-: MeasureMate :-");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 24)); // Set larger font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        titleLabel.setForeground(Color.WHITE); // Set text color to white for visibility
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 2, 5, 5)); // Grid layout for 5 rows, 2 columns with spacing
        buttonPanel.setOpaque(false); // Make the button panel transparent

        String[] types = {"Length", "Weight", "Temperature", "Speed",
                           "Time", "Area", "Volume", "Energy",
                           "Pressure", "Number"};

        for (String type : types) {
            JButton button = new JButton(type);
            button.setPreferredSize(new Dimension(100, 50)); // Set fixed size for all buttons
            button.addActionListener(new OpenActionListener(type));
            buttonPanel.add(button);
        }

        // Center the button panel within the background panel
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false); // Make the center panel transparent
        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(backgroundPanel, "Selection");

        mainPanel.add(new LengthConverterPanel(), "Length");
        mainPanel.add(new WeightConverterPanel(), "Weight");
        mainPanel.add(new TemperatureConverterPanel(), "Temperature");
        mainPanel.add(new SpeedConverterPanel(), "Speed");
        mainPanel.add(new TimeConverterPanel(), "Time");
        mainPanel.add(new AreaConverterPanel(), "Area");
        mainPanel.add(new VolumeConverterPanel(), "Volume");
        mainPanel.add(new EnergyConverterPanel(), "Energy");
        mainPanel.add(new PressureConverterPanel(), "Pressure");
        mainPanel.add(new NumberConverterPanel(), "Number");

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Selection"));
        add(backButton, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private class OpenActionListener implements ActionListener {
        private final String type;

        public OpenActionListener(String type) {
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mainPanel, type);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UnitConverterApp app = new UnitConverterApp();
            app.setVisible(true);
        });
    }
}
