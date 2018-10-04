/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import java.sql.SQLException;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.samples.entities.Cliente;

/**
 *
 * @author 2124203
 */
public class MyBATISClienteDAO implements ClienteDAO{
   @Inject
    private ClienteMapper clienteMapper;    

  @Override
  public void save(Cliente it) throws PersistenceException{
    try{
        clienteMapper.insertarCliente(it);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al registrar el item "+it.toString(),e);
    }        

  }

  @Override
  public Cliente load(int documento) throws PersistenceException {
  try{
      return clienteMapper.consultarCliente(documento);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+documento,e);
  }


  }

  }

