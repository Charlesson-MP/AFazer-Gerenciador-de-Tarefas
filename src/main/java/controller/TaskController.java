/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import util.ConnectionFactory;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Charlesson
 */
public class TaskController {
    
    public void save(Task task) {
        
        String sql = "INSERT INTO TASKS (IDPROJECT, NAME, DESCRIPTION, " +
                                        "ISCOMPLETED, NOTES, CREATEDDATE, " +
                                        "UPDATEDDATE, DEADLINE)" +
                                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Estabelecendo conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando o statment com a string sql
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getCreatedDate().getTime()));
            statement.setDate(7, new Date(task.getUpdatedDate().getTime()));
            statement.setDate(8, new Date(task.getDeadLine().getTime()));
            
            //Executando a query
            statement.execute();
        
        } catch(Exception e) {
            
            throw new RuntimeException("Erro ao salvar a tarefa " 
                    + e.getMessage(), e);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    
    public void update(Task task) {
    
        String sql = "UPDATE TASKS SET IDPROJECT = ?, NAME = ?, DESCRIPTION = ?, " +
                                        "ISCOMPLETED = ?, NOTES = ?, CREATEDDATE = ?, " +
                                        "UPDATEDDATE = ?, DEADLINE = ?" +
                                        "WHERE ID = ?" ;
        
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getCreatedDate().getTime()));
            statement.setDate(7, new Date(task.getUpdatedDate().getTime()));
            statement.setDate(8, new Date(task.getDeadLine().getTime()));
            statement.setInt(9, task.getId());
            
            //Executando a query
            statement.execute();
            
            
        } catch(Exception e) {
            
            throw new RuntimeException("Erro ao atualizar tarefa " + e.getMessage(), e);
        
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    
    }
    
    public void removeById(int taskId) {
       
        String sql = "DELETE FROM TASKS WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Criando a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando o valor do statement
            statement.setInt(1, taskId);
            
            //Executando a query
            statement.execute();
        
        } catch(Exception e) {
            
            throw new RuntimeException("Erro ao deletar a tarefa "
                    + e.getMessage(), e);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM TASKS WHERE IDPROJECT = ?";
        
        Connection connection = null;
        PreparedStatement statement = null; 
        ResultSet resultSet = null;
        
        //Lista de tarefas que será retornada
        List<Task> tasks = new ArrayList<Task>();
        
        
        try {
            
            //Criando a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando o valor do statement
            statement.setInt(1, idProject);
            
            //Atribuindo valor retornado pela execução da query a resultSet
            resultSet = statement.executeQuery();
            
            //Enquato houverem valores a serem percorridos
            while(resultSet.next()) {
            
                Task task = new Task();
                
                task.setId(resultSet.getInt("ID"));
                task.setIdProject(resultSet.getInt("IDPROJECT"));
                task.setName(resultSet.getString("NAME"));
                task.setDescription(resultSet.getString("DESCRIPTION"));
                task.setIsCompleted(resultSet.getBoolean("ISCOMPLETED"));
                task.setNotes(resultSet.getString("NOTES"));
                task.setCreatedDate(resultSet.getDate("CREATEDDATE"));
                task.setUpdatedDate(resultSet.getDate("UPDATEDDATE"));
                task.setDeadLine(resultSet.getDate("DEADLINE"));
                
                tasks.add(task);
                
            }
            
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao inserir a tarefa " + e.getMessage(), e);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement, resultSet);
            
        }
        
        return tasks;
    }

}
