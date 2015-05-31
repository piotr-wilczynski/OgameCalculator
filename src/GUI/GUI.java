package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import lang.GUI_Lang;
import Enums.*;
import Statistics.*;
import simulation.*;
import utilities.*;

public class GUI extends JFrame {

    private Shipyard attacker_shipyard, defender_shipyard;
    private Defense defense;
    private Technology technology;
    private Clipboard clipboard;
    private Options options;
    private Result result;
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
        attacker_shipyard = new Shipyard(GUI_Lang.getGUI().shipyard_of_argessor);
        defender_shipyard = new Shipyard(GUI_Lang.getGUI().shipyard_of_defender);
        defense = new Defense(GUI_Lang.getGUI().defense_of_defender);
        attacker_shipyard.get(Unit_Enum.Solar_Satellite).setEnabled(false);
        defense.get(Unit_Enum.Anti_Ballistic_Missiles).setEnabled(false);
        defense.get(Unit_Enum.Interplanetary_Missiles).setEnabled(false);
        technology = new Technology(GUI_Lang.getGUI().research);

        GroupLayout l = new GroupLayout(p);
        p.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                        .addComponent(attacker_shipyard)
                        .addComponent(technology)
                        .addComponent(result))
                .addGap(20)
                .addGroup(l.createParallelGroup()
                        .addComponent(defender_shipyard)
                        .addComponent(defense)
                        .addComponent(options, GroupLayout.Alignment.CENTER)));
        l.setVerticalGroup(l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                        .addComponent(attacker_shipyard)
                        .addComponent(technology)
                        .addComponent(result))
                .addGroup(l.createSequentialGroup()
                        .addComponent(defender_shipyard)
                        .addComponent(defense)
                        .addComponent(options)));

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
                for (Unit_Enum unit : Unit_Enum.values()) {
                    UnitPanel s = defender_shipyard.get(unit);
                    UnitPanel d = defense.get(unit);
                    if (s != null) {
                        s.setNumber("" + clipboard.getUnits().getOrDefault(unit, 0));
                    }
                    if (d != null) {
                        d.setNumber("" + clipboard.getUnits().getOrDefault(unit, 0));
                    }
                }
                for (Research_Enum reserches : Research_Enum.values()) {
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
        setIconImage(utilities.IO_Utilities.getImage("icon.png"));
        new Thread(clipboard).start();
    }

    private int[][] getUnits() {
        int[][] units = new int[2][Unit_Enum.values().length];
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[i].length; j++) {
                units[i][j] = 0;
            }
        }
        for (Unit_Enum u : Unit_Enum.values()) {
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
                if (u != u.Anti_Ballistic_Missiles && u != u.Interplanetary_Missiles) {
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
            simulation.Battle_Technologies tech_agressor = technology.getTechnologies(Side_Enum.Agressor);
            simulation.Battle_Technologies tech_defender = technology.getTechnologies(Side_Enum.Defender);
            sim.setTechnologies(Side_Enum.Agressor, tech_agressor);
            sim.setTechnologies(Side_Enum.Defender, tech_defender);
            int[][] units = getUnits();
            sim.setUnits(units);
            sim.simulate(count);
        }
    }

    private void setGUIEnable(boolean value) {
        //options.getSimulationStartButton().setEnabled(value);
        for (Unit_Enum unit : Unit_Enum.values()) {
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
        for (Research_Enum res : Research_Enum.values()) {
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
        result.setTeoreticalPlunder(teorplund, Unit_Enum.Large_Cargo);

        long[] realplund = statistics.getRealPlunder();
        result.setRealPlunder(realplund, teorplund);

        long duration = statistics.getDuration();
        int rec[][] = new int[2][Unit_Enum.values().length];
        for (int i = 0; i < rec.length; i++) {
            for (int j = 0; j < rec[i].length; j++) {
                rec[i][j] = 0;
            }
        }
        rec[Side_Enum.Agressor.ordinal()][Unit_Enum.Recycler.ordinal()] = 1;
        
        statistics.setStartUnits(rec);
        long durationRec = statistics.getDuration();
        statistics.setStartUnits(sim.getUnits());

        result.setTime(duration, durationRec);

        result.setFuel(statistics.getFuel());

        for (Unit_Enum u : Unit_Enum.values()) {
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
