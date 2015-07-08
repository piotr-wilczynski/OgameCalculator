/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Lang.GUI_Lang;
import Enums.Research_Enum;
import Enums.Side_Enum;
import Simulation.Battle_Technologies;
import Utilities.IO_Utilities;

/**
 *
 * @author Piotr
 */
public class Technology extends ImagePanel {
    private static final long serialVersionUID = 8230957842425927537L;

    private JLabel title;
    private JLabel label_agressor, label_defender;
    public static int Attacker_Side = 0;
    public static int Defender_Side = 1;
    UnitPanel[] attacker, defender;

    public Technology(String text) {
        super(IO_Utilities.getImage("Research.jpg"));
        title = new JLabel(text);
        initComponents();
    }

    public UnitPanel get(Research_Enum research, Side_Enum side) {
        if (side == Side_Enum.Agressor) {
            for (UnitPanel u : attacker) {
                if (((Research_Enum) u.getObject()).equals(research)) {
                    return u;
                }
            }
        } else if (side == Side_Enum.Defender) {
            for (UnitPanel u : defender) {
                if (((Research_Enum) u.getObject()).equals(research)) {
                    return u;
                }
            }
        }
        return null;
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        label_agressor = new JLabel(GUI_Lang.getInstance().agressor);
        label_agressor.setOpaque(true);
        label_agressor.setForeground(new Color(255, 193, 7));
        Font f = label_agressor.getFont();
        label_agressor.setBorder(new LineBorder(Color.black, 2, true));
        label_agressor.setFont(new Font(f.getName(), Font.BOLD, 26));
        label_agressor.setBackground(new Color(10, 10, 10, 150));

        label_defender = new JLabel(GUI_Lang.getInstance().defender);
        label_defender.setForeground(new Color(255, 193, 7));
        f = label_defender.getFont();
        label_defender.setOpaque(true);
        label_defender.setBorder(new LineBorder(Color.black, 2, true));
        label_defender.setFont(new Font(f.getName(), Font.BOLD, 26));
        label_defender.setBackground(new Color(10, 10, 10, 150));

        title.setForeground(Color.WHITE);
        f = title.getFont();
        title.setBorder(new EmptyBorder(-1, -1, -1, -1));
        title.setFont(new Font(f.getName(), Font.BOLD, 26));

        attacker = new UnitPanel[6];
        defender = new UnitPanel[3];
        attacker[0] = new UnitPanel(Research_Enum.Weapons_Technology);
        attacker[1] = new UnitPanel(Research_Enum.Shielding_Technology);
        attacker[2] = new UnitPanel(Research_Enum.Armour_Technology);
        attacker[3] = new UnitPanel(Research_Enum.Combustion_Drive);
        attacker[4] = new UnitPanel(Research_Enum.Impulse_Drive);
        attacker[5] = new UnitPanel(Research_Enum.Hyperspace_Drive);
        defender[0] = new UnitPanel(Research_Enum.Weapons_Technology);
        defender[1] = new UnitPanel(Research_Enum.Shielding_Technology);
        defender[2] = new UnitPanel(Research_Enum.Armour_Technology);
        int labelsize = 120;
        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                        .addComponent(label_agressor, labelsize, labelsize, labelsize)
                        .addComponent(attacker[0])
                        .addComponent(attacker[1])
                        .addComponent(attacker[2])
                        .addGap(20)
                        .addComponent(attacker[3])
                        .addComponent(attacker[4])
                        .addComponent(attacker[5]))
                .addGroup(l.createSequentialGroup()
                        .addComponent(label_defender, labelsize, labelsize, labelsize)
                        .addComponent(defender[0])
                        .addComponent(defender[1])
                        .addComponent(defender[2])));
        l.setVerticalGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                        .addComponent(label_agressor, GroupLayout.Alignment.CENTER)
                        .addComponent(attacker[0])
                        .addComponent(attacker[1])
                        .addComponent(attacker[2])
                        .addComponent(attacker[3])
                        .addComponent(attacker[4])
                        .addComponent(attacker[5]))
                .addGroup(l.createParallelGroup()
                        .addComponent(label_defender, GroupLayout.Alignment.CENTER)
                        .addComponent(defender[0])
                        .addComponent(defender[1])
                        .addComponent(defender[2])
                ));

        l = new GroupLayout(this);
        setLayout(l);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
                .addComponent(title, GroupLayout.Alignment.CENTER)
                .addComponent(panel));
        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(title)
                .addGap(20)
                .addComponent(panel));
    }

    public void addActionListener(ActionListener action) {
        for (UnitPanel u : attacker) {
            u.addActionListener(action);
        }
        for (UnitPanel u : defender) {
            u.addActionListener(action);
        }

    }

    public Battle_Technologies getTechnologies(Side_Enum side) {
        Simulation.Battle_Technologies tech = null;
        switch (side) {
            case Agressor: {

                int wt = get(Research_Enum.Weapons_Technology, side).getNumber();
                int st = get(Research_Enum.Shielding_Technology, side).getNumber();
                int at = get(Research_Enum.Armour_Technology, side).getNumber();
                int cd = get(Research_Enum.Combustion_Drive, side).getNumber();
                int id = get(Research_Enum.Impulse_Drive, side).getNumber();
                int hd = get(Research_Enum.Hyperspace_Drive, side).getNumber();
                tech = new Battle_Technologies(wt, st, at, cd, id, hd);
            }
            break;
            case Defender: {
                int wt = get(Research_Enum.Weapons_Technology, side).getNumber();
                int st = get(Research_Enum.Shielding_Technology, side).getNumber();
                int at = get(Research_Enum.Armour_Technology, side).getNumber();
                tech = new Battle_Technologies(wt, st, at);
            }
            break;
        }
        return tech;
    }
}
