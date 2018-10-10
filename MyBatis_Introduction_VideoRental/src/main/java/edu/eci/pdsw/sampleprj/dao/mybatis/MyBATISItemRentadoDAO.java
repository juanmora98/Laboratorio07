/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.SQLException;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2124203
 */
public class MyBATISItemRentadoDAO implements ItemRentadoDAO{
    
  @Inject
  private ItemMapper itemRentadoMapper;    

  @Override
  public void save(ItemRentado it) throws PersistenceException{
  try{
      itemRentadoMapper.insertarItem(it);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
  }        

  }

  @Override
  public ItemRentado load(int id) throws PersistenceException {
  try{
      return null; //itemRentadoMapper.consultarItem(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+id,e);
  }


  }
}
