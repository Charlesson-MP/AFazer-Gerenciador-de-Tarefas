package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Charlesson
 */
public class ButtonColumnCellRenderer extends DefaultTableCellRenderer {
    
    private String buttonType;
    
    public ButtonColumnCellRenderer(String buttonType) {
    
        this.buttonType = buttonType;
        
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                          boolean isSelected, boolean hasFocus, int row, int column) {
    
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        
        label.setHorizontalAlignment(CENTER);

        label.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/" + buttonType + ".png")));
        
        return label;
    }
    
}
