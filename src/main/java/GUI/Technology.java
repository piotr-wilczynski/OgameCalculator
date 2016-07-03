/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import enums.SideEnum;
import lang.GUI_Lang;
import enums.ResearchEnum;
import simulation.BattleTechnologies;
import utilities.IO_Utilities;

/**
 *
 * @author Piotr
 */
public class Technology extends ImagePanel {
    private static final long serialVersionUID = 8230957842425927537L;
    public static int Attacker_Side = 0;
    public static int Defender_Side = 1;
    UnitPanel[] attacker, defender;
    private JLabel title;
    private JLabel label_agressor, label_defender;

    public Technology(String text) {
        super(IO_Utilities.getImage("Research.jpg"));
        title = new JLabel(text);
        initComponents();
    }

    public UnitPanel get(ResearchEnum research, SideEnum side) {
        if (side == SideEnum.Agressor) {
            for (UnitPanel u : attacker) {
                if (u.getObject().equals(research)) {
                    return u;
                }
            }
        } else if (side == SideEnum.Defender) {
            for (UnitPanel u : defender) {
                if (u.getObject().equals(research)) {
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
        attacker[0] = new UnitPanel(ResearchEnum.WeaponsTechnology);
        attacker[1] = new UnitPanel(ResearchEnum.ShieldingTechnology);
        attacker[2] = new UnitPanel(ResearchEnum.ArmourTechnology);
        attacker[3] = new UnitPanel(ResearchEnum.CombustionDrive);
        attacker[4] = new UnitPanel(ResearchEnum.ImpulseDrive);
        attacker[5] = new UnitPanel(ResearchEnum.HyperspaceDrive);
        defender[0] = new UnitPanel(ResearchEnum.WeaponsTechnology);
        defender[1] = new UnitPanel(ResearchEnum.ShieldingTechnology);
        defender[2] = new UnitPanel(ResearchEnum.ArmourTechnology);
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

    public BattleTechnologies getTechnologies(SideEnum side) {
        BattleTechnologies tech = null;
        switch (side) {
            case Agressor: {

                int wt = get(ResearchEnum.WeaponsTechnology, side).getNumber();
                int st = get(ResearchEnum.ShieldingTechnology, side).getNumber();
                int at = get(ResearchEnum.ArmourTechnology, side).getNumber();
                int cd = get(ResearchEnum.CombustionDrive, side).getNumber();
                int id = get(ResearchEnum.ImpulseDrive, side).getNumber();
                int hd = get(ResearchEnum.HyperspaceDrive, side).getNumber();
                tech = new BattleTechnologies(wt, st, at, cd, id, hd);
            }
            break;
            case Defender: {
                int wt = get(ResearchEnum.WeaponsTechnology, side).getNumber();
                int st = get(ResearchEnum.ShieldingTechnology, side).getNumber();
                int at = get(ResearchEnum.ArmourTechnology, side).getNumber();
                tech = new BattleTechnologies(wt, st, at);
            }
            break;
        }
        return tech;
    }
}
