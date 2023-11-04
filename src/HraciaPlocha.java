import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Trieda vytvorí prostredie pre hranie hry pre dvoch hráčov
 */
public class HraciaPlocha extends JPanel implements ActionListener {
    private JButton restartButton;
    private JButton homeButton;
    private Karta[] karty;
    private Random random;
    private JPanel score1Panel;
    private JLabel score1Label;
    private JPanel score2Panel;
    private JLabel score2Label;
    private JPanel turnPanel;
    private JLabel turnLabel;
    private JPanel buttonPanel;
    private JPanel leftPanel;
    private boolean player1Turn;
    private int score1;
    private int score2;
    private int index1;
    private int[] indexy;
    private Timer timer;

    private Color grey;
    private Color darkGrey;
    private Font scoreFont;
    private Nastavenia nastavenia;
    private boolean homeButtonClicked;
    private boolean mozesIst;
    private String player1Name;
    private String player2Name;

    /**
     * Parametrický konštruktor, ktorý vytvorí hraciu plochu
     * @param nastavenia, pre zmenu vlastností objektov použítaveľom
     */
    public HraciaPlocha(Nastavenia nastavenia) {
        this.mozesIst = true;
        this.homeButtonClicked = false;
        this.nastavenia = nastavenia;
        this.grey = new Color(33, 33, 33);
        this.darkGrey = new Color(30, 30, 30);
        this.scoreFont = new Font("PT Sans Narrow", Font.PLAIN, 25);

        this.score1 = 0;
        this.score2 = 0;
        this.index1 = -1;
        this.random = new Random();
        this.karty = new Karta[16];
        this.indexy = new int[16];
        this.restartButton = new JButton();
        this.homeButton = new JButton();
        this.buttonPanel = new JPanel();
        this.score1Panel = new JPanel();
        this.score1Label = new JLabel();
        this.score2Panel = new JPanel();
        this.score2Label = new JLabel();
        this.turnPanel = new JPanel();
        this.turnLabel = new JLabel();
        this.leftPanel = new JPanel();
        this.setSize(860, 580);
        this.setBackground(this.grey);
        this.setLayout(null);

        this.player1Name = this.nastavenia.getPlayer1Name();
        this.player2Name = this.nastavenia.getPlayer2Name();

        this.buttonPanel.setBounds(20, 60, 500, 460);
        this.buttonPanel.setBackground(this.darkGrey);
        this.buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        this.restartButton.setBounds(15, 390, 200, 50);
        this.restartButton.setFocusable(false);
        this.restartButton.setText("Restart Game");
        this.restartButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 20));
        this.restartButton.setForeground(new Color(116, 196, 223));
        this.restartButton.setBackground(this.grey);
        this.restartButton.addActionListener(this);

        this.homeButton.setBounds(225, 390, 60, 50);
        this.homeButton.setFocusable(false);
        this.homeButton.setText("◄");
        this.homeButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 30));
        this.homeButton.setForeground(new Color(116, 196, 223));
        this.homeButton.setBackground(this.grey);
        this.homeButton.addActionListener(this);

        this.leftPanel.setBackground(this.darkGrey);
        this.leftPanel.setBounds(530, 60, 300, 460);
        this.leftPanel.setLayout(null);

        this.turnPanel.setBounds(10, 10, 280, 60);
        this.turnPanel.setBackground(new Color(161, 170, 231));
        this.turnLabel.setForeground(Color.WHITE);
        this.turnLabel.setFont(new Font("PT Sans Narrow", Font.PLAIN, 30));

        this.score1Panel.setBounds(10, 90, 280, 50);
        this.score1Panel.setBackground(new Color(124, 201, 207));
        this.score1Label.setText("Score " + this.player1Name + " :  " + String.valueOf(this.score1));
        this.score1Label.setForeground(Color.white);
        this.score1Label.setFont(this.scoreFont);

        this.score2Panel.setBounds(10, 150, 280, 50);
        this.score2Panel.setBackground(new Color(255, 161, 158));
        this.score2Label.setText("Score " + this.player2Name + " :  " + String.valueOf(this.score2));
        this.score2Label.setForeground(Color.white);
        this.score2Label.setFont(this.scoreFont);

        for (int i = 0; i < this.karty.length; i++) {
            this.karty[i] = new Karta();
            this.karty[i].setFarbaZnaku(this.nastavenia.getVybranaFarba());
            this.karty[i].addActionListener(this);
            this.karty[i].turnOff();
            this.buttonPanel.add(this.karty[i]);
        }

        this.firstTurn();
        this.shuffle();
        this.pridajZnaky(nastavenia.getVybraneZnaky());

        this.leftPanel.add(this.score1Panel);
        this.leftPanel.add(this.score2Panel);
        this.leftPanel.add(this.turnPanel);
        this.leftPanel.add(this.homeButton);
        this.leftPanel.add(this.restartButton);
        this.turnPanel.add(this.turnLabel);
        this.score1Panel.add(this.score1Label);
        this.score2Panel.add(this.score2Label);
        this.add(this.leftPanel);
        this.add(this.buttonPanel);
        this.setVisible(true);
    }

    /**
     * Mení hodnotu premennej this.mozesIst
     * @param value - hodnota, na ktorú to zmení
     */
    public void setMozesIst(boolean value) {
        this.mozesIst = value;
    }

    /**
     * Vráti hodnotu premennej this.mozesIst
     */
    public boolean getMozesIst() {
        return this.mozesIst;
    }

    /**
     * Mení hodnotu premennej this.homeButtonClicked
     * @param value - hodnota, na ktorú to zmení
     */
    public void setButtonClicked(boolean value) {
        this.homeButtonClicked = value;
    }

    /**
     * Vráti hodnotu premennej this.homeButtonClicked
     */
    public boolean getButtonClicked() {
        return this.homeButtonClicked;
    }

    /**
     * Pridá array-u karty znaky po porade
     */
    public void pridajZnaky(String[] znaky) {
        for (int i = 0; i < znaky.length; i++) {
            this.karty[i].setZnak(znaky[i]);
        }
    }

    /**
     * Uloží karty na náhodné pozície v paneli
     */
    public void shuffle() {
        int pocet = 0;

        while (pocet < this.indexy.length) {
            int cislo = this.random.nextInt(16);
            boolean existuje = false;
            for (int i = 0; i < pocet; i++) {
                if (this.indexy[i] == cislo) {
                    existuje = true;
                    break;
                }
            }

            if (!existuje) {
                this.indexy[pocet] = cislo;
                pocet++;
            }

            for (int j = 0; j < this.indexy.length; j++) {
                this.buttonPanel.add(this.karty[this.indexy[j]]);
            }

        }
    }

    /**
     * Randomne určí akú hodnotu bude mať player1Turn (či je hráč na rade alebo nie)
     */
    public void firstTurn() {
        this.player1Turn = this.random.nextBoolean();
        this.turnText();
    }

    /**
     * Počas hry mení text, ktorý hráč je práve na rade
     */
    public void turnText() {
        if (this.player1Turn) {
            this.turnLabel.setText("Turn: " + this.player1Name);
        } else {
            this.turnLabel.setText("Turn: " + this.player2Name);
        }
    }

    /**
     * Aktualizuje skóre prvého hráča
     */
    public void score1Update() {
        this.score1 += 1;
        this.score1Label.setText("Score " + this.player1Name + " :  " + String.valueOf(this.score1));
    }

    /**
     * Aktualizuje skóre druhého hráča
     */
    public void score2Update() {
        this.score2 += 1;
        this.score2Label.setText("Score " + this.player2Name + " :  " + String.valueOf(this.score2));
    }

    /**
     * Nastaví skóre na nulové
     */
    public void scoreReset() {
        this.score1 = 0;
        this.score1Label.setText("Score " + this.player1Name + " : " + String.valueOf(this.score1));
        this.score2 = 0;
        this.score2Label.setText("Score " + this.player2Name + " : " + String.valueOf(this.score2));
    }

    /**
     * Kontroluje, či hra ešte neskončila
     */
    public boolean hraSkoncila() {
        int i = 0;
        while (i < 16) {
            if (this.karty[i].isEnabled()) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Kontoruluje, podľa výšky skóre, ktorý z hráčov vyhral
     */
    public void win() {
        if (this.hraSkoncila()) {
            if (this.score1 > this.score2) {
                this.turnLabel.setText(this.player1Name + " won");
            } else if (this.score1 < this.score2) {
                this.turnLabel.setText(this.player2Name + " won");
            } else {
                this.turnLabel.setText("Draw");
            }
        }
    }

    /**
     * Porovnáva hodnoty znakov kliknutých kariet
     * - ak sa rovnajú, hráč ide znova, ak nie, mení sa poradie
     * - zabraňuje hráčovi otočiť karty ak nie je narade
     */
    public void checkFinal(int value1, int value2) {
        if (this.karty[value1] != this.karty[value2] ) {
            this.setMozesIst(false);
            if (this.karty[value1].getText().equals(this.karty[value2].getText())) {
                this.karty[value1].setEnabled(false);
                this.karty[value2].setEnabled(false);
                this.index1 = -1;
                this.setMozesIst(true);

                if (this.player1Turn) {
                    this.score1Update();
                    this.player1Turn = true;
                    this.turnText();
                    this.win();

                } else {
                    this.score2Update();
                    this.player1Turn = false;
                    this.turnText();
                    this.win();
                }

            } else {
                if (this.player1Turn) {
                    this.player1Turn = false;
                    this.turnText();
                } else {
                    this.player1Turn = true;
                    this.turnText();
                }
                this.index1 = -1;
                this.pouziCasovac(value1, value2);
            }
        }
    }

    /**
     * Vytvorí časovač aby boli karty otočené nejaký čas
     */
    public void pouziCasovac(int num1, int num2) {
        this.timer = new Timer(1400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HraciaPlocha.this.karty[num1].turnOff();
                HraciaPlocha.this.karty[num2].turnOff();
                HraciaPlocha.this.setMozesIst(true);
            }

        });
        HraciaPlocha.this.timer.setRepeats(false);
        HraciaPlocha.this.timer.start();
    }

    /**
     * Reštartuje hru
     */
    public void restartGame() {
        this.firstTurn();
        this.shuffle();
        this.setMozesIst(true);
        this.scoreReset();

        for (int i = 0; i < this.karty.length; i++) {
            this.karty[i].turnOff();
            this.karty[i].setEnabled(true);
        }
    }

    @Override
    /**
     * Fukcia, ktorá reaguje na správanie používateľa
     * - pri kliknutí na políčko karty sa daná karta otočí a následne sa skontrolujú jej znaky
     * - reštartuje hru
     * - vráti sa na uvítaciu plochu hry
     */
    public void actionPerformed(ActionEvent e) {
        if (this.getMozesIst()) {
            for (int i = 0; i < this.karty.length; i++) {
                if (e.getSource() == this.karty[i]) {
                    this.karty[i].turnOn();
                    if (this.index1 < 0) {
                        this.index1 = i;
                    } else {
                        this.checkFinal(this.index1, i);
                        break;
                    }
                }
            }
        }

        if (e.getSource() == this.restartButton) {
            this.restartGame();
        }

        if (e.getSource() == this.homeButton) {
            this.setButtonClicked(true);
        }
    }
}
