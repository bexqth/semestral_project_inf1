import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Trieda vytvárajúca prostredie pre používateľa zmeniť dostupné vlastnosti objektov hry
 */
public class Nastavenia extends JPanel implements ActionListener {
    private JButton okButton;
    private JPanel panel;
    private JPanel colorPanel;
    private Color yellow;
    private Color pink;
    private Color blue;
    private Color purple;

    private Color green;
    private Color backroundColor;
    private Color panelColor;
    private Color textColor;
    private Font font;
    private Font vyberFont;
    private JRadioButton[] ikony;
    private JRadioButton[] farby;
    private Color vybranafarba;
    private JButton resetSettingsButton;

    private JRadioButton vyberSport;
    private JRadioButton vyberZvierata;
    private JRadioButton vyberJedlo;
    private JRadioButton vyberHudba;
    private JRadioButton vyberRandom;

    private JRadioButton vyberPink;
    private JRadioButton vyberBlue;
    private JRadioButton vyberYellow;
    private JRadioButton vyberPurple;
    private JRadioButton vyberGreen;

    private String randomIcon;
    private String sportIcon;
    private String zvierataIcon;
    private String jedloIcon;
    private String hudbaIcon;

    private JTextField firstAPlayer;
    private JTextField secAPlayer;
    private JTextField firstBPlayer;
    private JTextField pcPlayer;

    private JLabel firstALabel;
    private JLabel secALabel;
    private JLabel firstBLabel;
    private JLabel pcLabel;
    private Font playerFont;

    private JLabel text;
    private JPanel labelPanel;
    private JPanel namePanel;
    private ButtonGroup group1;
    private ButtonGroup group2;
    private JPanel groupPanel;
    private JLabel labelPriklad;
    private boolean buttonClicked;

    private String[] randomZnaky;
    private String[] zvierataZnaky;
    private String[] sportZnaky;
    private String[] hudbaZnaky;
    private String[] jedloZnaky;
    private String[] vybraneZnaky;

    /**
     * Neparametrický koštruktor vytvárajúci plochu pre používateľa k použitiu
     */
    public Nastavenia() {
        this.randomZnaky = new String[]{"★", "★", "✿", "✿", "☀", "☀", "♫", "♫", "♛", "♛", "♣", "♣", "✐", "✐", "☁", "☁"};
        this.zvierataZnaky = new String[]{"🦌", "🦌", "🐠", "🐠", "🐢", "🐢", "🐘", "🐘", "🐌", "🐌", "🐬", "🐬", "🐇", "🐇", "🐳", "🐳"};
        this.sportZnaky = new String[]{"🎯", "🎯", "🎱", "🎱", "🏀", "🏀", "🏈", "🏈", "🏓", "🏓", "🏹", "🏹", "🎳", "🎳", "🏒", "🏒"};
        this.hudbaZnaky = new String[]{"🎤", "🎤", "🎹", "🎹", "🎻", "🎻", "🎺", "🎺", "🎷", "🎷", "🎧", "🎧", "🎸", "🎸", "🎼", "🎼"};
        this.jedloZnaky = new String[]{"🍏", "🍏", "🍭", "🍭", "🍕", "🍕", "🍒", "🍒", "🍓", "🍓", "🍰", "🍰", "🍗", "🍗", "🍷", "🍷"};
        this.vybraneZnaky = randomZnaky;

        this.firstAPlayer = new JTextField();
        this.secAPlayer = new JTextField();
        this.firstBPlayer = new JTextField();
        this.pcPlayer = new JTextField();
        this.playerFont = new Font("PT Sans Narrow", Font.PLAIN, 18);

        this.firstALabel = new JLabel();
        this.secALabel = new JLabel();
        this.firstBLabel = new JLabel();
        this.pcLabel = new JLabel();

        this.buttonClicked = false;
        this.labelPanel = new JPanel();
        this.resetSettingsButton = new JButton();
        this.ikony = new JRadioButton[5];
        this.farby = new JRadioButton[5];
        this.okButton = new JButton();
        this.panel = new JPanel();
        this.namePanel = new JPanel();
        this.colorPanel = new JPanel();
        this.groupPanel = new JPanel();
        this.labelPriklad = new JLabel();
        this.group1 = new ButtonGroup();
        this.group2 = new ButtonGroup();
        this.text = new JLabel();
        this.textColor = Color.white;
        this.backroundColor = new Color(33, 33, 33);
        this.panelColor = new Color(30, 30, 30);
        this.yellow = new Color(253, 220, 162);
        this.pink = new Color(255, 161, 158);
        this.blue = new Color(124, 201, 207);
        this.purple = new Color(161, 170, 231);
        this.green = new Color(169, 224, 163);
        this.font = new Font("PT Sans Narrow", Font.PLAIN, 55);
        this.vyberFont = new Font("PT Sans Narrow", Font.PLAIN, 20);
        this.vybranafarba = this.yellow;

        this.randomIcon = "☁";
        this.sportIcon = "️🏈";
        this.zvierataIcon = "🐶";
        this.jedloIcon = "🍕";
        this.hudbaIcon = "🎸";

        this.vyberSport = new JRadioButton("Sport");
        this.vyberZvierata = new JRadioButton("Animals");
        this.vyberJedlo = new JRadioButton("Food");
        this.vyberHudba = new JRadioButton("Music");
        this.vyberRandom = new JRadioButton("Random");

        this.ikony[0] = this.vyberSport;
        this.ikony[1] = this.vyberZvierata;
        this.ikony[2] = this.vyberJedlo;
        this.ikony[3] = this.vyberHudba;
        this.ikony[4] = this.vyberRandom;

        this.vyberPink = new JRadioButton("Pink");
        this.vyberBlue = new JRadioButton("Blue");
        this.vyberYellow = new JRadioButton("Yellow");
        this.vyberPurple = new JRadioButton("Purple");
        this.vyberGreen = new JRadioButton("Green");

        this.farby[0] = this.vyberPink;
        this.farby[1] = this.vyberBlue;
        this.farby[2] = this.vyberYellow;
        this.farby[3] = this.vyberPurple;
        this.farby[4] = this.vyberGreen;

        this.setSize(860, 580);
        this.setBackground(this.backroundColor);
        this.setLayout(null);

        this.text.setText("Settings");
        this.text.setFont(this.font);
        this.text.setForeground(this.textColor);

        this.resetSettingsButton.setBounds(770, 420, 60, 50);
        this.resetSettingsButton.setFocusable(false);
        this.resetSettingsButton.setBackground(this.panelColor);
        this.resetSettingsButton.setForeground(this.textColor);
        this.resetSettingsButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 27));
        this.resetSettingsButton.setText("↻");
        this.resetSettingsButton.addActionListener(this);

        this.okButton.setBounds(770, 480, 60, 50);
        this.okButton.setFocusable(false);
        this.okButton.setBackground(this.panelColor);
        this.okButton.setForeground(this.textColor);
        this.okButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 27));
        this.okButton.setText("✓");
        this.okButton.addActionListener(this);

        this.labelPanel.setBounds(140, 110, 100, 90);
        this.labelPanel.setBackground(this.panelColor);
        this.labelPanel.add(this.labelPriklad);

        //NAMES
        this.firstALabel.setText("Player 1 name : ");
        this.firstALabel.setBounds(50, 20, 130, 20);
        this.firstALabel.setForeground(this.textColor);
        this.firstALabel.setFont(this.playerFont);

        this.firstAPlayer.setBounds(50, 50, 130, 30);
        this.firstAPlayer.setText("Player1");
        this.firstAPlayer.setBackground(this.backroundColor);
        this.firstAPlayer.setForeground(this.blue);
        this.firstAPlayer.setFont(this.playerFont);

        this.secALabel.setText("Player 2 name : ");
        this.secALabel.setBounds(50, 90, 130, 20);
        this.secALabel.setForeground(this.textColor);
        this.secALabel.setFont(this.playerFont);

        this.secAPlayer.setBounds(50, 120, 130, 30);
        this.secAPlayer.setText("Player2");
        this.secAPlayer.setBackground(this.backroundColor);
        this.secAPlayer.setForeground(this.pink);
        this.secAPlayer.setFont(this.playerFont);

        this.pcLabel.setText("Computer name : ");
        this.pcLabel.setBounds(240, 20, 150, 20);
        this.pcLabel.setForeground(this.textColor);
        this.pcLabel.setFont(this.playerFont);

        this.pcPlayer.setBounds(240, 50, 130, 30);
        this.pcPlayer.setText("Computer");
        this.pcPlayer.setEditable(false);
        this.pcPlayer.setBackground(this.backroundColor);
        this.pcPlayer.setForeground(this.blue);
        this.pcPlayer.setFont(this.playerFont);

        this.firstBLabel.setBounds(240, 90, 130, 20);
        this.firstBLabel.setText("Player name : ");
        this.firstBLabel.setForeground(this.textColor);
        this.firstBLabel.setFont(this.playerFont);

        this.firstBPlayer.setBounds(240, 120, 130, 30);
        this.firstBPlayer.setText("Player");
        this.firstBPlayer.setBackground(this.backroundColor);
        this.firstBPlayer.setForeground(this.pink);
        this.firstBPlayer.setFont(this.playerFont);

        this.namePanel.setLayout(null);
        this.namePanel.setBounds(260, 330, 420, 170);
        this.namePanel.setBackground(this.panelColor);
        this.namePanel.add(this.firstALabel);
        this.namePanel.add(this.firstAPlayer);
        this.namePanel.add(this.secALabel);
        this.namePanel.add(this.secAPlayer);
        this.namePanel.add(this.firstBLabel);
        this.namePanel.add(this.firstBPlayer);
        this.namePanel.add(this.pcLabel);
        this.namePanel.add(this.pcPlayer);

        this.panel.setBackground(this.panelColor);
        this.panel.setBounds(0, 0, this.getWidth(), 90);
        this.panel.add(this.text);

        this.labelPriklad.setText(this.randomIcon);
        this.labelPriklad.setFont(this.font);
        this.labelPriklad.setForeground(this.yellow);

        //COLORS
        this.colorPanel.setBackground(this.panelColor);
        this.colorPanel.setBounds(480, 110, 200, 200);
        this.colorPanel.setLayout(new GridLayout(5, 1, 0, 0));
        this.vyberYellow.setSelected(true);

        for (int i = 0; i < this.farby.length; i++) {
            this.farby[i].setFocusable(false);
            this.farby[i].setFont(this.vyberFont);
            this.farby[i].setForeground(this.textColor);
            this.farby[i].setBackground(this.panelColor);
            this.farby[i].addActionListener(this);
            this.colorPanel.add(this.farby[i]);
            this.group2.add(this.farby[i]);
        }

        // ICONS
        this.groupPanel.setBounds(260, 110, 200, 200);
        this.groupPanel.setLayout(new GridLayout(5, 1, 0, 0));
        this.vyberRandom.setSelected(true);

        for (int i = 0; i < this.ikony.length; i++) {
            this.ikony[i].setFocusable(false);
            this.ikony[i].setFont(this.vyberFont);
            this.ikony[i].setForeground(this.textColor);
            this.ikony[i].setBackground(this.panelColor);
            this.ikony[i].addActionListener(this);
            this.groupPanel.add(this.ikony[i]);
            this.group1.add(this.ikony[i]);
        }

        this.add(this.namePanel);
        this.add(this.labelPanel);
        this.add(this.okButton);
        this.add(this.resetSettingsButton);
        this.add(this.panel);
        this.add(this.colorPanel);
        this.add(this.groupPanel);
        this.setVisible(true);
    }

    /**
     * Vráti meno prvého hráča
     */
    public String getPlayer1Name() {
        return this.firstAPlayer.getText();
    }

    /**
     * Vráti meno druhého hráča
     */
    public String getPlayer2Name() {
        return this.secAPlayer.getText();
    }

    /**
     * Vráti meno hráča (možnosť - hra pre jedného hráča)
     */
    public String getPlayerName() {
        return this.firstBPlayer.getText();
    }

    /**
     * Vráti názov počítača
     */
    public String getPcName() {
        return this.pcPlayer.getText();
    }

    /**
     * Prenastaví hodnotu premennej this.buttonClicked
     * @param value - nová hodnota premennej
     */
    public void setButtonClicked(boolean value) {
        this.buttonClicked = value;
    }

    /**
     * Vráti hodnotu premennej this.buttonClicked
     */
    public boolean getButtonClicked() {
        return this.buttonClicked;
    }

    /**
     * Vráti farbu vybranú používateľom
     */
    public Color getVybranaFarba() {
        return this.vybranafarba;
    }

    /**
     * Vráti znaky vybrané používateľom
     */
    public String[] getVybraneZnaky() {
        return this.vybraneZnaky;
    }

    /**
     * Funkcia nastaví nastavenia na pôvodné
     */
    public void resetSettings() {
        this.firstAPlayer.setText("Player1");
        this.secAPlayer.setText("Player2");
        this.firstBPlayer.setText("Player");
        this.vybranafarba = this.yellow;
        this.vybraneZnaky = this.randomZnaky;
        this.labelPriklad.setForeground(this.yellow);
        this.labelPriklad.setText(this.randomIcon);
        this.vyberYellow.setSelected(true);
        this.vyberRandom.setSelected(true);
    }

    @Override
    /**
     * Fukcia, ktorá reaguje na správanie používateľa
     * - vyberie a tak prenastaví farbu znakov
     * - vyberie a tak prenastaví kategóriu znakov
     * - vráti sa na uvítaciu plochu
     * - zmení nastavenia na pôvodné
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vyberRandom) {
            this.labelPriklad.setText(this.randomIcon);
            this.vybraneZnaky = this.randomZnaky;
        }

        if (e.getSource() == this.vyberHudba) {
            this.labelPriklad.setText(this.hudbaIcon);
            this.vybraneZnaky = this.hudbaZnaky;
        }

        if (e.getSource() == this.vyberJedlo) {
            this.labelPriklad.setText(this.jedloIcon);
            this.vybraneZnaky = this.jedloZnaky;
        }

        if (e.getSource() == this.vyberSport) {
            this.labelPriklad.setText(this.sportIcon);
            this.vybraneZnaky = this.sportZnaky;
        }

        if (e.getSource() == this.vyberZvierata) {
            this.labelPriklad.setText(this.zvierataIcon);
            this.vybraneZnaky = this.zvierataZnaky;
        }

        if (e.getSource() == this.vyberBlue) {
            this.labelPriklad.setForeground(this.blue);
            this.vybranafarba = this.blue;
        }

        if (e.getSource() == this.vyberPink) {
            this.labelPriklad.setForeground(this.pink);
            this.vybranafarba = this.pink;
        }

        if (e.getSource() == this.vyberYellow) {
            this.labelPriklad.setForeground(this.yellow);
            this.vybranafarba = this.yellow;
        }

        if (e.getSource() == this.vyberPurple) {
            this.labelPriklad.setForeground(this.purple);
            this.vybranafarba = this.purple;
        }

        if (e.getSource() == this.vyberGreen) {
            this.labelPriklad.setForeground(this.green);
            this.vybranafarba = this.green;
        }

        if (e.getSource() == this.okButton) {
            this.setButtonClicked(true);
        }

        if (e.getSource() == this.resetSettingsButton) {
            this.resetSettings();
        }
    }
}
