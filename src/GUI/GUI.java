/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUI;

import Utilities.IO_Utilities;
import Utilities.Strings;
import Simulation.Simulation_SWING;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import Lang.GUI_Lang;
import Enums.*;
import Statistics.*;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1105636786932301235L;

    private Shipyard attacker_shipyard, defender_shipyard;
    private Defense defense;
    private Technology technology;
    private Clipboard clipboard;
    private Options options;
    private Result result;
    private TopBar topBar;
    private boolean processing = false;
    private Simulation_SWING sim;

    public GUI() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void initComponents() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPanel p = new ImagePanel(IO_Utilities.getImage("background.png"));
        p.setOpaque(true);
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        options = new Options();
        result = new Result();
        options.getSimulationStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        //options.setOpaque(false);
        attacker_shipyard = new Shipyard(GUI_Lang.getInstance().shipyard_of_argessor);
        defender_shipyard = new Shipyard(GUI_Lang.getInstance().shipyard_of_defender);
        defense = new Defense(GUI_Lang.getInstance().defense_of_defender);
        attacker_shipyard.get(UnitEnum.SolarSatellite).setEnabled(false);
        defense.get(UnitEnum.AntiBallisticMissiles).setEnabled(false);
        defense.get(UnitEnum.InterplanetaryMissiles).setEnabled(false);
        technology = new Technology(GUI_Lang.getInstance().research);
        topBar = new TopBar();
        GroupLayout l = new GroupLayout(p);
        p.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addComponent(topBar,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
            .addGroup(
                l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                    .addComponent(attacker_shipyard)
                    .addComponent(technology)
                    .addComponent(result))
                .addGap(20)
                .addGroup(l.createParallelGroup()
                    .addComponent(defender_shipyard)
                    .addComponent(defense)
                    .addComponent(options, GroupLayout.Alignment.CENTER)))
            );
        l.setVerticalGroup(l.createSequentialGroup()
            .addComponent(topBar)
            .addGroup(
                l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                    .addComponent(attacker_shipyard)
                    .addComponent(technology)
                    .addComponent(result))
                .addGroup(l.createSequentialGroup()
                    .addComponent(defender_shipyard)
                    .addComponent(defense)
                    .addComponent(options)))
            );

        attacker_shipyard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        defender_shipyard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        technology.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        clipboard = new Clipboard();
        clipboard.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                for (UnitEnum unit : UnitEnum.values()) {
                    UnitPanel s = defender_shipyard.get(unit);
                    UnitPanel d = defense.get(unit);
                    if (s != null) {
                        s.setNumber("" + clipboard.getUnits().getOrDefault(unit, 0));
                    }
                    if (d != null) {
                        d.setNumber("" + clipboard.getUnits().getOrDefault(unit, 0));
                    }
                }
                for (ResearchEnum reserches : ResearchEnum.values()) {
                    UnitPanel t = technology.get(reserches, Side_Enum.Defender);
                    if (t != null) {
                        t.setNumber("" + clipboard.getResearches().getOrDefault(reserches, 0));
                    }
                }
                result.getPlanet().setMetal(clipboard.getResources().getOrDefault(Resources_Enum.Metal, 0));
                result.getPlanet().setCrystal(clipboard.getResources().getOrDefault(Resources_Enum.Crystal, 0));
                result.getPlanet().setDeuterium(clipboard.getResources().getOrDefault(Resources_Enum.Deuterium, 0));
            }
        });
        setIconImage(Utilities.IO_Utilities.getImage("icon.png"));
        new Thread(clipboard).start();
    }

    private int[][] getUnits() {
        int[][] units = new int[2][UnitEnum.values().length];
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[i].length; j++) {
                units[i][j] = 0;
            }
        }
        for (UnitEnum u : UnitEnum.values()) {
            UnitPanel a = attacker_shipyard.get(u);
            UnitPanel d = defender_shipyard.get(u);
            UnitPanel dd = defense.get(u);

            if (a != null) {
                units[Side_Enum.Agressor.ordinal()][u.ordinal()] = a.getNumber();
            }
            if (d != null) {
                units[Side_Enum.Defender.ordinal()][u.ordinal()] = d.getNumber();
            }
            if (dd != null) {
                if (u != Enums.UnitEnum.AntiBallisticMissiles && u != Enums.UnitEnum.InterplanetaryMissiles) {
                    units[Side_Enum.Defender.ordinal()][u.ordinal()] = dd.getNumber();
                }
            }
        }
        return units;
    }

    private void Action() {
        if (processing) {
            setGUIEnable(processing);
            processing = false;
        } else {
            setGUIEnable(processing);
            processing = true;
            sim = new Simulation_SWING();
            final int count = options.getNumberOfSimulations();
            options.getProgressBar().setMaximum(count);
            options.getProgressBar().setValue(0);
            sim.setListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    String PropertyName = evt.getPropertyName();
                    if (PropertyName.matches("done")) {
                        publish_results(sim.getStatistics());
                    } else if (PropertyName.matches("state")) {
                        if (((SwingWorker.StateValue) evt.getNewValue()).equals(SwingWorker.StateValue.DONE)) {
                            publish_results(sim.getStatistics());
                            setGUIEnable(processing);
                            processing = false;
                        }
                    }
                }
            });
            Simulation.Battle_Technologies tech_agressor = technology.getTechnologies(Side_Enum.Agressor);
            Simulation.Battle_Technologies tech_defender = technology.getTechnologies(Side_Enum.Defender);
            sim.setTechnologies(Side_Enum.Agressor, tech_agressor);
            sim.setTechnologies(Side_Enum.Defender, tech_defender);
            int[][] units = getUnits();
            sim.setUnits(units);
            sim.simulate(count);
        }
    }

    private void setGUIEnable(boolean value) {
        //options.getSimulationStartButton().setEnabled(value);
        for (UnitEnum unit : UnitEnum.values()) {
            UnitPanel ap = attacker_shipyard.get(unit);
            UnitPanel dp = defender_shipyard.get(unit);
            UnitPanel d = defense.get(unit);
            if (ap != null) {
                ap.setEdtable(value);
            }
            if (dp != null) {
                dp.setEdtable(value);
            }
            if (d != null) {
                d.setEdtable(value);
            }
        }
        for (ResearchEnum res : ResearchEnum.values()) {
            UnitPanel tp1 = technology.get(res, Side_Enum.Agressor);
            UnitPanel tp2 = technology.get(res, Side_Enum.Defender);
            if (tp1 != null) {
                tp1.setEdtable(value);
            }
            if (tp2 != null) {
                tp2.setEdtable(value);
            }

        }
    }

    private void publish_results(Statistics statistics) {
        
        int precision = 2;
        Coordinates start = options.getCoordinates();
        Coordinates end = result.getPlanet().getCoordinates();
        int universeSpeed = 4;
        double derbisFleet = 0.5;
        double derbisDefense = 0;
        int percent = options.getFleetSpeedPercent();
        
        statistics.setStart(start);
        statistics.setEnd(end);
        statistics.setCircularUniverses(false);
        statistics.setUniverseSpeed(universeSpeed);
        statistics.setStartUnits(sim.getUnits());
        statistics.setAggressorTechnologies(sim.getTechnologies(Side_Enum.Agressor));
        statistics.setFleetSpeedPercent(percent);
        statistics.setDerbisDefense(derbisDefense);
        statistics.setDerbisFleet(derbisFleet);
        statistics.setPlanetResources(result.getPlanet().getResources());
        statistics.setPlayerStatus(Player_Status_Enum.Neutral);
        
        
        options.getProgressBar().setValue(statistics.getDone());
        options.getProgressBar().setString("" + statistics.getDone());

        result.setBattlePlace(result.getPlanet().getName(), result.getPlanet().getCoordinates());

        //set winner
        result.setWinner((int) Math.round(100 * statistics.getResult(Side_Enum.Agressor)), (int) Math.round(100 * statistics.getResult(Side_Enum.Defender)), (int) Math.round(100 * statistics.getResult(Side_Enum.Remis)), 1);

        //set tactical retreat
        double[] tactical_retreat = statistics.getTactitalRetreat();
        result.setTacticalRetreat(tactical_retreat);

        //set derbis            
        long[] derb = statistics.getDerbis();
        result.setDerbis(derb);

        //set chance for moon
        long chance = (derb[Resources_Enum.Metal.ordinal()] + derb[Resources_Enum.Crystal.ordinal()]) / 100000;
        result.setChanceForMoon((int) chance);

        //set agressor loss
        long[] aloss = statistics.getLosses(Side_Enum.Agressor);
        result.setLosses(Side_Enum.Agressor, aloss);

        //set defender loss        
        long[] dloss = statistics.getLosses(Side_Enum.Defender);
        result.setLosses(Side_Enum.Defender, dloss);

        long[] teorplund = statistics.getTeoreticalPlunder();
        result.setTeoreticalPlunder(teorplund, UnitEnum.LargeCargo);

        long[] realplund = statistics.getRealPlunder();
        result.setRealPlunder(realplund, teorplund);

        long duration = statistics.getDuration();
        int rec[][] = new int[2][UnitEnum.values().length];
        for (int i = 0; i < rec.length; i++) {
            for (int j = 0; j < rec[i].length; j++) {
                rec[i][j] = 0;
            }
        }
        rec[Side_Enum.Agressor.ordinal()][UnitEnum.Recycler.ordinal()] = 1;
        
        statistics.setStartUnits(rec);
        long durationRec = statistics.getDuration();
        statistics.setStartUnits(sim.getUnits());

        result.setTime(duration, durationRec);

        result.setFuel(statistics.getFuel());

        for (UnitEnum u : UnitEnum.values()) {
            UnitPanel a = attacker_shipyard.get(u);
            UnitPanel d = defender_shipyard.get(u);
            UnitPanel dd = defense.get(u);

            if (a != null) {
                a.setLabel(Strings.precision(statistics.getShip(Side_Enum.Agressor, u), precision));
            }
            if (d != null) {
                d.setLabel(Strings.precision(statistics.getShip(Side_Enum.Defender, u), precision));
            }
            if (dd != null) {
                dd.setLabel(Strings.precision(statistics.getShip(Side_Enum.Defender, u), precision));
            }
        }
    }
}
