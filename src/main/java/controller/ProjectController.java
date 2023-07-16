/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Charlesson
 */
public class ProjectController {
    
    public void save(Project project) {
    
        String sql = "INSERT INTO PROJECTS (NAME, DESCRIPTION, CREATEDDATE, "
                + "UPDATEDDATE) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Estabelecendo conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando o statment com a string sql
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new Date(project.getUpdatedDate().getTime()));
            
            //Executando a query
            statement.execute();
        
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro ao salvar o projeto ", e);
            
        } finally {
            
            //Fechando a conexão e o statement
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    
    }
    
    public void update(Project project) {
    
        String sql = "UPDATE PROJECTS SET NAME = ?, DESCRIPTION = ?, "
                + "CREATEDDATE = ?, UPDATEDDATE = ? WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Setando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores da query
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new Date(project.getUpdatedDate().getTime()));
            statement.setInt(5, project.getId());
            
            //Executando a query
            statement.execute();
            
        } catch(SQLException e) {
            
            throw new RuntimeException("Erro ao atualizar projeto " + e.getMessage(), e);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement);
            
        }
    
    }
    
    public void removeById(int idProject) {
    
        //Criando variável com o comando sql
        String sql = "DELETE FROM PROJECTS WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
        
            //Estabelecendo conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando o statment com a query sql
            statement = connection.prepareStatement(sql);
            
            //Setando valores na query
            statement.setInt(1, idProject);
            
            //Executando a query
            statement.execute();
            
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao deletar o projeto " + e.getMessage(), e);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    
    }
    
    public List<Project> getAll() {
        
        //Variável com o comando sql
        String sql = "SELECT * FROM PROJECTS";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de projetos que será retornada
        List<Project> projects = new ArrayList<Project>();
        
        
        try {
            
            //Estabelecendo conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando o statement com a query sql
            statement = connection.prepareStatement(sql);
            
            //Atribuindo valor retornado pela execução da query a resultSet
            resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
            
                Project project = new Project();
                
                project.setId(resultSet.getInt("ID"));
                project.setName(resultSet.getString("NAME"));
                project.setDescription(resultSet.getString("DESCRIPTION"));
                project.setCreatedDate(resultSet.getDate("CREATEDDATE"));
                project.setUpdatedDate(resultSet.getDate("UPDATEDDATE"));
                
                projects.add(project);
            }
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao buscar lista de projetos " + e.getMessage(), e);
            
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement, resultSet);
            
        }
        
        
        
        return projects;
    }
    
}
