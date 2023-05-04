package Main;


import GUI.GameWindow;
import GUI.GameArea;
import Variables.Constant;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
       GameWindow window = new GameWindow();
       window.startGameThread();
    }
    }
