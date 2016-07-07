/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package gui;

import enums.LangEnum;
import utilities.IOUtilities;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Locale;

/**
 *
 * @author Piotr Wilczynski
 */
public class TopBar extends JPanel {
    private BufferedImage background;
    private JComboBox<JLabel> countries;
    private JComboBox<String> servers;
    private JComboBox<String> users;

    public TopBar() {
        background = IOUtilities.getImageMatrix(IOUtilities.getImage("topBarBackground.png"), 2, 1)[0][0];
        initComponents();
    }

    private void initComponents() {
        JLabel[] labels = new JLabel[LangEnum.values().length];

        BufferedImage[][] image = IOUtilities.getImageMatrix(IOUtilities.getImage("flags.png"), 63, 1);
        for (int i = 0; i < labels.length; i++) {
            Locale locale = LangEnum.values()[i].toLocale();
            labels[i] = new JLabel(locale.getDisplayCountry(locale), new ImageIcon(image[0][LangEnum.values()[i].getFlagId()]), JLabel.LEFT);
        }

        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(padding);
        servers = new JComboBox<>();
        users = new JComboBox<>();
        //countries.setOpaque(false);
        countries = new JComboBox<>(labels);
        countries.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = ((JLabel) countries.getSelectedItem()).getText();
                Locale locale = Locale.ENGLISH;
                for (int i = 0; i < LangEnum.values().length; i++) {
                    Locale l = LangEnum.values()[i].toLocale();
                    if (l.getDisplayCountry(l).matches(text)) {
                        locale = l;
                        break;
                    }
                }
                LangEnum lang = LangEnum.fromLocale(locale);
                /*Universes unis = api.api.getInstance().getUniverses(lang.getServerCountryCode());
                servers.removeAllItems();
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < unis.getUniverse().size(); i++) {
                    try {
                        ServerData serverData = api.api.getInstance().getServerData(unis.getUniverse().get(i), false);
                        if (serverData.getName()==null) {
                            servers.addItem(serverData.getNumber() + ". Universum");
                        } else {
                            servers.addItem(serverData.getName());
                        }
                    } catch (MalformedURLException | JAXBException | FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

*/            }
        });
        countries.setRenderer(new ListCellRenderer<JLabel>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel p = value;
                p.setBackground(isSelected ? new Color(168, 221, 255) : Color.GREEN);
                p.setOpaque(isSelected);
                return p;
            }
        });
        GroupLayout l = new GroupLayout(this);
        //l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        this.setLayout(l);
        l.setHorizontalGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(l.createSequentialGroup()
                        .addComponent(countries, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(servers, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                ));
        l.setVerticalGroup(
                l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(countries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(servers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                ));
    }

    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
