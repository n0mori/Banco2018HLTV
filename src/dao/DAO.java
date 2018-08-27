/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dskaster
 */
public abstract class DAO<T> {

    protected Connection connection;
    
    DAO(Connection connection) {
        this.connection = connection;
    }
    
    public abstract void create(T t) throws SQLException;
    public abstract T read(Integer id) throws SQLException;
    public abstract void update(T t) throws SQLException;
    public abstract void delete(Integer id) throws SQLException;

    public abstract List<T> all() throws SQLException;
}