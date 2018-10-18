/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.TipoItem;

/**
 *
 * @author 2124203
 */
public interface TipoItemDAO {
    public void save(TipoItem it) throws PersistenceException;
    public TipoItem load(int id) throws PersistenceException;
    public List<TipoItem> loadAll() throws PersistenceException;

}
