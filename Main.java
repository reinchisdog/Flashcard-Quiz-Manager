import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static void Open_File(){
        FCOpen open_state = new FCOpen();
        open_state.setSize(1280, 720);
        open_state.setResizable(false);
        open_state.setDefaultCloseOperation(open_state.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Open_File();
    }

}
