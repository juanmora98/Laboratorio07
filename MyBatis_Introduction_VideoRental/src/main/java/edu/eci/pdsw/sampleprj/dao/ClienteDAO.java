/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;

/**
 *
 * @author 2124203
 */
public interface ClienteDAO {
    public void save(Cliente it) throws PersistenceException;
    public Cliente load(long docu) throws PersistenceException;
    public List<Cliente> loadAll() throws PersistenceException;
    public void agregarItemRentadoACliente(long cliente_id, int item_id, Date fechaInicio, Date fechaFIn) throws PersistenceException;

}
