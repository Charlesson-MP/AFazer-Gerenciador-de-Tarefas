/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author Charlesson
 */
public class TaskTableModel extends AbstractTableModel {
    
    String[] colums = {"Nome", "Descri��o", "Prazo", "Tarefa Conclu�da", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList();

    @Override
    public int getRowCount() {
        return this.tasks.size();
    }

    @Override
    public int getColumnCount() {
        return colums.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colums[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        if(tasks.isEmpty()) {
            return Object.class;
        }
        
        return this.getValueAt(0, columnIndex).getClass();        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
                return dateFormat.format(tasks.get(rowIndex).getDeadLine());
            case 3:
                return tasks.get(rowIndex).isIsCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados n�o encontrados.";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);
    }

    public String[] getColums() {
        return colums;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
