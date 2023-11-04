import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

/**
 * Vytvorí a definuje vlastnosti jednej karty, s ktorou sa následne v programe pracuje
 */

public class Karta extends JButton {

    private JButton button;
    private String znak;

    /**
     * Neparametrický konštruktor, ktorý nastaví nemeniteľné vlastnosti karty
     */
    public Karta() {
        this.setFont(new Font("PT Sans Narrow", Font.PLAIN, 45));
        this.setBackground(new Color(30, 30, 30));
        this.setFocusable(false);
    }

    /**
     * Nastaví farbu znaku
     * @param novaFarba - farba na ktorú sa povôdná zmení
     */
    public void setFarbaZnaku(Color novaFarba) {
        this.setForeground(novaFarba);
    }

    /**
     * Vráti hodnotu premennej this.znak
     */
    public String getZnak() {
        return this.znak;
    }

    /**
     * Zmení hodnotu premennej this.znak
     * @param znak - nový znak
     */
    public void setZnak(String znak) {
        this.znak = znak;
    }

    /**
     * Karta sa otočí a ukáže znak
     */
    public void turnOn() {
        this.setText(this.getZnak());
    }

    /**
     * Karta sa otočí sa ukáže sa zadná strana karty
     */
    public void turnOff() {
        this.setText("❓");
    }
}
