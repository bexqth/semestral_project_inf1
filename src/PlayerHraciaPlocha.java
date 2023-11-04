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
 * Trieda vytvorí prostredie pre hranie hry pre jedného hráča
 */
public class PlayerHraciaPlocha extends JPanel implements ActionListener {
    private Karta[] karty;
    private String[] znaky;
    private JPanel buttonPanel;
    private JButton restartButton;
    private JButton homeButton;
    private Random random;
    private JPanel score1Panel;
    private JLabel score1Label;
    private JPanel score2Panel;
    private JLabel score2Label;
    private JPanel turnPanel;
    private JLabel turnLabel;
    private JPanel leftPanel;
    private boolean playerTurn;
    private int score1;
    private int score2;
    private int index1;
    private int[] indexy;
    private Timer timer;
    private Nastavenia nastavenia;
    private boolean mozesIst;
    private boolean homeButtonClicked;
    private String playerName;
    private String pcName;

    /**
     * Parametrický konštruktor, ktorý vytvorí hraciu plochu
     * @param nastavenia, pre zmenu vlastností objektov použítaveľom
     */
    public PlayerHraciaPlocha(Nastavenia nastavenia) {
        this.mozesIst = true;
        this.nastavenia = nastavenia;
        this.karty = new Karta[16];
        this.buttonPanel = new JPanel();
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
        this.playerName = this.nastavenia.getPlayerName();
        this.pcName = this.nastavenia.getPcName();

        this.setSize(860, 580);
        this.setBackground(new Color(33, 33, 33));
        this.setLayout(null);

        this.restartButton.setBounds(15, 390, 200, 50);
        this.restartButton.setFocusable(false);
        this.restartButton.setText("Restart Game");
        this.restartButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 20));
        this.restartButton.setForeground(new Color(116, 196, 223));
        this.restartButton.setBackground(new Color(33, 33, 33));
        this.restartButton.addActionListener(this);
        this.restartButton.setEnabled(false);

        this.homeButton.setBounds(225, 390, 60, 50);
        this.homeButton.setFocusable(false);
        this.homeButton.setText("◄");
        this.homeButton.setFont(new Font("PT Sans Narrow", Font.PLAIN, 30));
        this.homeButton.setForeground(new Color(116, 196, 223));
        this.homeButton.setBackground(new Color(33, 33, 33));
        this.homeButton.addActionListener(this);

        this.buttonPanel.setBounds(20, 60, 500, 460);
        this.buttonPanel.setBackground(new Color(30, 30, 30));
        this.buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        this.leftPanel.setBackground(new Color(30, 30, 30));
        this.leftPanel.setBounds(530, 60, 300, 460);
        this.leftPanel.setLayout(null);

        this.turnPanel.setBounds(10, 10, 280, 60);
        this.turnPanel.setBackground(new Color(161, 170, 231));
        this.turnLabel.setForeground(Color.WHITE);
        this.turnLabel.setFont(new Font("PT Sans Narrow", Font.PLAIN, 30));

        this.score1Panel.setBounds(10, 90, 280, 50);
        this.score1Panel.setBackground(new Color(124, 201, 207));
        this.score1Label.setText("Score " + this.playerName + "  " + String.valueOf(this.score1));
        this.score1Label.setForeground(Color.white);
        this.score1Label.setFont(new Font("PT Sans Narrow", Font.PLAIN, 25));

        this.score2Panel.setBounds(10, 150, 280, 50);
        this.score2Panel.setBackground(new Color(255, 161, 158));
        this.score2Label.setText("Score " + this.pcName + "  " + String.valueOf(this.score2));
        this.score2Label.setForeground(Color.white);
        this.score2Label.setFont(new Font("PT Sans Narrow", Font.PLAIN, 25));


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
        this.leftPanel.add(this.restartButton);
        this.leftPanel.add(this.homeButton);
        this.turnPanel.add(this.turnLabel);
        this.score1Panel.add(this.score1Label);
        this.score2Panel.add(this.score2Label);
        this.add(this.leftPanel);
        this.add(this.buttonPanel);
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
     * Nastaví array-u karty znaky po porade
     */
    public void pridajZnaky(String[] znaky) {
        for (int i = 0; i < this.indexy.length; i++) {
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
                this.indexy[pocet++] = cislo;
            }

            for (int j = 0; j < this.indexy.length; j++) {
                this.buttonPanel.add(this.karty[this.indexy[j]]);
            }
        }
    }

    /**
     * Randomne určí akú hodnotu bude mať playerTurn (či je hráč na rade alebo nie)
     */
    public void firstTurn() {
        this.playerTurn = true;
        this.turnText();
    }

    /**
     * Počas hry mení text, kto je práve na rade
     */
    public void turnText() {
        if (this.playerTurn) {
            this.turnLabel.setText("Turn: " + this.playerName);
        } else {
            this.turnLabel.setText("Turn: " + this.pcName);
        }
    }

    /**
     * Aktualizuje skóre hráča
     */
    public void score1Update() {
        this.score1 += 1;
        this.score1Label.setText("Score " + this.playerName + " :  " + String.valueOf(this.score1));
    }

    /**
     * Aktualizuje skóre počítača
     */
    public void scorePcUpdate() {
        this.score2 += 1;
        this.score2Label.setText("Score " + this.pcName + " :  " + String.valueOf(this.score2));
    }

    /**
     * Nastaví skóre na nulové
     */
    public void scoreReset() {
        this.score1 = 0;
        this.score1Label.setText("Score " + this.playerName + "  " + String.valueOf(this.score1));
        this.score2 = 0;
        this.score2Label.setText("Score " + this.pcName + "  " + String.valueOf(this.score2));
    }

    /**
     * Definuje správanie počítača, ktorý si náhodne vyberie dve karty pexesa
     */
    public void computerMove() {
        if (!this.playerTurn) {
            this.timer = new Timer(1600, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int randomNum1 = PlayerHraciaPlocha.this.random.nextInt(16);
                    int randomNum2 = PlayerHraciaPlocha.this.random.nextInt(16);

                    while (randomNum1 == randomNum2 || !PlayerHraciaPlocha.this.karty[randomNum1].isEnabled() || !PlayerHraciaPlocha.this.karty[randomNum2].isEnabled()) {
                        randomNum1 = PlayerHraciaPlocha.this.random.nextInt(16);
                        randomNum2 = PlayerHraciaPlocha.this.random.nextInt(16);
                    }

                    PlayerHraciaPlocha.this.karty[randomNum1].turnOn();
                    PlayerHraciaPlocha.this.karty[randomNum2].turnOn();

                    PlayerHraciaPlocha.this.checkFinal(randomNum1, randomNum2);
                    PlayerHraciaPlocha.this.restartButton.setEnabled(true);
                }

            });

            PlayerHraciaPlocha.this.timer.setRepeats(false);
            PlayerHraciaPlocha.this.timer.start();
        }
    }

    /**
     * Kontroluje, či hra ešte neskončila
     */
    public boolean hraSkoncila() {
        int i = 0;
        while (i < this.karty.length) {
            if (this.karty[i].isEnabled()) {
                return false;
            }
            i++;
        }
        this.timer.stop();
        return true;
    }

    /**
     * Kontoruluje, podľa výšky skóre hráčov, ktorý z nich vyhral
     */
    public void win() {
        if (this.hraSkoncila()) {
            if (this.score1 > this.score2) {
                this.turnLabel.setText(this.playerName + " won");
            } else if (this.score1 < this.score2) {
                this.turnLabel.setText(this.pcName + " won");
            } else {
                this.turnLabel.setText("Draw");
            }
        }
    }

    /**
     * Porovnáva hodnoty znakov kliknutých kariet
     * - ak sa rovnajú, hráč ide znova, ak nie, mení sa poradie medzi hráčom a počítačom
     * - zabraňuje hráčovi otočiť karty ak nie je na rade alebo ak si počítač vyberá karty
     */
    public void checkFinal(int value1, int value2) {
        if (this.karty[value1] != this.karty[value2]) {
            this.setMozesIst(false);
            if (this.karty[value1].getText().equals(this.karty[value2].getText())) {
                this.karty[value1].setEnabled(false);
                this.karty[value2].setEnabled(false);
                this.index1 = -1;
                this.setMozesIst(true);

                if (this.playerTurn) {
                    this.score1Update();
                    this.playerTurn = true;
                    this.turnText();
                    this.win();

                } else {
                    this.scorePcUpdate();
                    this.playerTurn = false;
                    this.turnText();
                    this.computerMove();
                    this.win();
                }

            } else {
                if (this.playerTurn) {
                    this.playerTurn = false;
                    this.computerMove();
                    this.turnText();
                } else {

                    this.playerTurn = true;
                    this.turnText();
                }
                this.pouziCasovac(value1, value2);
                this.index1 = -1;
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
                PlayerHraciaPlocha.this.karty[num1].turnOff();
                PlayerHraciaPlocha.this.karty[num2].turnOff();
                PlayerHraciaPlocha.this.setMozesIst(true);
            }
        });
        PlayerHraciaPlocha.this.timer.setRepeats(false);
        PlayerHraciaPlocha.this.timer.start();
    }

    /**
     * Reštartuje hru
     */
    public void restartGame() {
        this.restartButton.setEnabled(false);
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
