import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Trieda WelcomePlocha, reprezentuje uvítaciu plochu hry.
 */

public class WelcomePlocha extends JFrame implements ActionListener {
    private JLabel textLabel;
    private JButton playerOption;
    private JButton computerOption;
    private JButton settingsOption;
    private JPanel panel;
    private Nastavenia nastavenia;
    private HraciaPlocha hraciaPlocha;
    private PlayerHraciaPlocha playerHraciaPlocha;
    private Color grey;
    private Color darkGrey;
    private Color blue;
    private Color pink;
    private Color purple;
    private boolean hraciaPlochaExistuje;
    private boolean playerHraciaPlochaExistuje;

    /**
     * Neparametrický konštruktor, ktorý vytvorí panel ako uvítaciu plochu
     */
    public WelcomePlocha() {
        this.hraciaPlochaExistuje = false;
        this.playerHraciaPlochaExistuje = false;
        this.nastavenia = new Nastavenia();
        this.textLabel = new JLabel();
        this.playerOption = new JButton();
        this.computerOption = new JButton();
        this.panel = new JPanel();
        this.settingsOption = new JButton();

        this.setSize(860, 580);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.panel.setBounds(0, 0, 860, 580);
        this.panel.setBackground(new Color(33, 33, 33));
        this.panel.setLayout(null);

        this.textLabel.setBounds(310, 20, 800, 100);
        this.textLabel.setForeground(Color.WHITE);
        this.textLabel.setFont(new Font("PT Sans Narrow", Font.PLAIN, 75));
        this.textLabel.setText("Pexeso");

        this.computerOption.setBounds(330, 190 , 200, 50);
        this.computerOption.setText("👤1 Player   ");

        this.computerOption.setBackground(new Color(161, 170, 231));
        this.computerOption.setForeground(Color.white);

        this.computerOption.setFont(new Font("PT Sans Narrow", Font.PLAIN, 25));
        this.computerOption.setFocusable(false);
        this.computerOption.addActionListener(this);

        this.playerOption.setBounds(330, 260, 200, 50);
        this.playerOption.setText("👥 2 Players");

        this.playerOption.setBackground(new Color(92, 189, 200));
        this.playerOption.setForeground(Color.white);

        this.playerOption.setFont(new Font("PT Sans Narrow", Font.PLAIN, 25));
        this.playerOption.setFocusable(false);
        this.playerOption.addActionListener(this);

        this.settingsOption.setBounds(330, 330, 200, 50);
        this.settingsOption.setFocusable(false);

        this.settingsOption.setBackground(new Color(236, 112, 99));
        this.settingsOption.setForeground(Color.white);

        this.settingsOption.setFont(new Font("PT Sans Narrow", Font.PLAIN, 25));
        this.settingsOption.setText("❂ Settings");
        this.settingsOption.addActionListener(this);

        this.nastavenia.setVisible(false);

        this.panel.add(this.textLabel);
        this.panel.add(this.computerOption);
        this.panel.add(this.playerOption);
        this.panel.add(this.settingsOption);
        this.add(this.panel);
        this.add(this.nastavenia);
        this.setVisible(true);

        while (true) {
            System.out.print("");
            this.zmenaNastavenia();

            if (this.hraciaPlochaExistuje) {
                this.zmenaHraciaPlocha();
            }

            if (this.playerHraciaPlochaExistuje) {
                this.zmenaPlayerHraciaPlocha();
            }
        }

    }

    /**
     * Zmení uvítaciu plochu za Nastavenia
     */
    public void zmenaNastavenia() {
        if (this.nastavenia.getButtonClicked() && !this.panel.isVisible()) {
            this.nastavenia.setVisible(false);
            this.panel.setVisible(true);
            this.nastavenia.setButtonClicked(false);
        }
    }

    /**
     * Zmení uvítaciu plochu za hraciu plochu pre jedného hráča
     */
    public void zmenaHraciaPlocha() {
        if (this.hraciaPlocha.getButtonClicked() && !this.panel.isVisible()) {
            this.hraciaPlocha.setVisible(false);
            this.panel.setVisible(true);
            this.hraciaPlocha.setButtonClicked(false);
        }
    }

    /**
     * Zmení uvítaciu plochu za hraciu plochu pre dvoch hráčov
     */
    public void zmenaPlayerHraciaPlocha() {
        if (this.playerHraciaPlocha.getButtonClicked() && !this.panel.isVisible()) {
            this.playerHraciaPlocha.setVisible(false);
            this.panel.setVisible(true);
            this.playerHraciaPlocha.setButtonClicked(false);
        }
    }

    @Override
    /**
     * Fukcia, ktorá reaguje na správanie používateľa
     * - vytvorí prostredie pre jedného hráča
     * - vytvorí prostredie pre dvoch hráčov
     * - zobrazí nastavenia
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.computerOption) {   // 1 Player
            this.playerHraciaPlocha = new PlayerHraciaPlocha(this.nastavenia);
            this.playerHraciaPlochaExistuje = true;
            this.playerHraciaPlocha.setVisible(true);
            this.add(this.playerHraciaPlocha);
            this.panel.setVisible(false);
        }

        if (e.getSource() == this.playerOption) {    // 2 Players
            this.hraciaPlocha = new HraciaPlocha(this.nastavenia);
            this.hraciaPlochaExistuje = true;
            this.hraciaPlocha.setVisible(true);
            this.add(this.hraciaPlocha);
            this.panel.setVisible(false);
        }

        if (e.getSource() == this.settingsOption) { //Nastavenia
            this.nastavenia.setVisible(true);
            this.panel.setVisible(false);
        }
    }
}
