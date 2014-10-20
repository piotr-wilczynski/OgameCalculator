
package GUI;

import java.awt.Image;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UnitPanel extends JPanel{
    private final int text_width=50;
    private final int icon_height=20;
    public UnitPanel(String name,Image image) {
        text = new JTextField();
        text.setColumns(4);
        JLabel label = new JLabel(new ImageIcon(image));        
        label.setToolTipText(name);
        GroupLayout l = new GroupLayout(this);
        setLayout(l);
        l.setAutoCreateContainerGaps(false);
        l.setAutoCreateGaps(false);
        l.setHorizontalGroup(l.createSequentialGroup()
            .addComponent(label)
            .addComponent(text,text_width,text_width,text_width));
        l.setVerticalGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(label)
            .addComponent(text,icon_height,icon_height,icon_height));    
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }
    
    private JTextField text;
    
}
