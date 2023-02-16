package model;

import javax.swing.*;
import java.awt.*;

public class AddComponentsToPanel {

    public static void addComponentsToPanel(JPanel panel, Component[] components){
        for (int i = 0; i < components.length; i++){
            panel.add(components[i]);
        }
    }
}
