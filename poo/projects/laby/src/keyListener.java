import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener implements KeyListener {
    public static boolean Up = false;
    public static boolean Down = false;
    public static boolean Left = false;
    public static boolean Right = false;
    public static boolean Run = false;
    public static boolean Pause = false;
    public static boolean Ouvrir = false;
    public static boolean Attack = false;
    public static boolean Take = false;
    public static boolean Interact = false;
    public static boolean Inventory = false;
    public static boolean Item1 = false;
    public static boolean Item2 = false;
    public static boolean Item3 = false;
    public static boolean Item4 = false;
    public static boolean Item5 = false;
    public static boolean Item6 = false;

    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == 90) Up = true;            // Z
        if (arg0.getKeyCode() == 81) Left = true;        // Q
        if (arg0.getKeyCode() == 83) Down = true;        // S
        if (arg0.getKeyCode() == 68) Right = true;        // D
        if (arg0.getKeyCode() == 16) Run = true;            // Shift
        if (arg0.getKeyCode() == 27) Pause = !Pause;        // Echap
        if (arg0.getKeyCode() == 80) Ouvrir = true;        // P
        if (arg0.getKeyCode() == 32) Attack = true;        // Space
        if (arg0.getKeyCode() == 65) Take = true;        // A
        if (arg0.getKeyCode() == 10) Interact = true;    // Enter
        if (arg0.getKeyCode() == 69) Inventory = !Inventory;    // E
        if (arg0.getKeyCode() == 97) Item1 = true;        // Clav.Num 1
        if (arg0.getKeyCode() == 98) Item2 = true;        // Clav.Num 2
        if (arg0.getKeyCode() == 99) Item3 = true;        // Clav.Num 3
        if (arg0.getKeyCode() == 100) Item4 = true;        // Clav.Num 4
        if (arg0.getKeyCode() == 101) Item5 = true;        // Clav.Num 5
        if (arg0.getKeyCode() == 102) Item6 = true;        // Clav.Num 6
    }

    public void keyReleased(KeyEvent arg0) {
        if (arg0.getKeyCode() == 90) Up = false;
        if (arg0.getKeyCode() == 81) Left = false;
        if (arg0.getKeyCode() == 83) Down = false;
        if (arg0.getKeyCode() == 68) Right = false;
        if (arg0.getKeyCode() == 16) Run = false;
        if (arg0.getKeyCode() == 80) Ouvrir = false;
        if (arg0.getKeyCode() == 32) Attack = false;
        if (arg0.getKeyCode() == 65) Take = false;
        if (arg0.getKeyCode() == 97) Item1 = false;
        if (arg0.getKeyCode() == 98) Item2 = false;
        if (arg0.getKeyCode() == 99) Item3 = false;
        if (arg0.getKeyCode() == 100) Item4 = false;
        if (arg0.getKeyCode() == 101) Item5 = false;
        if (arg0.getKeyCode() == 102) Item6 = false;
    }

    public void keyTyped(KeyEvent arg0) {}
}