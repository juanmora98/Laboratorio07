/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;



import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
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
  public Cliente load(long documento) throws PersistenceException {
	  try{
		  return clienteMapper.consultarCliente(documento);
  		}
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
		  throw new PersistenceException("Error al consultar el item "+documento,e);
  		}	


  }
  
  @Override
  public List<Cliente> loadAll() throws PersistenceException{
	  try {
		  return clienteMapper.consultarClientes();
	  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		  throw new PersistenceException("Error al consultar los clientes");
  	  }	

  }
  
  @Override
  public void agregarItemRentadoACliente(long cliente_id, int item_id, Date fechaInicio, Date fechaFin) throws PersistenceException{
	try {
		clienteMapper.agregarItemRentadoACliente(cliente_id, item_id, fechaInicio, fechaFin);
	}catch(org.apache.ibatis.exceptions.PersistenceException e){
		  throw new PersistenceException("Error al añadir alquiler"+cliente_id+" "+item_id);
	  }	
	  
  }
}

