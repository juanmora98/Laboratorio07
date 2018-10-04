/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.SQLException;
/**
 *
 * @author 2124203
 */
public class MyBATISTipoItemDAO {
      @Inject
  private TipoItemMapper tipoItemMapper;    

  @Override
  public void save(TipoItem it) throws PersistenceException{
  try{
      tipoItemMapper.insertarTipoItem(it);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
  }        

  }

  @Override
  public TipoItem load(int id) throws PersistenceException {
  try{
      return TipoItemMapper.consultarTipoItem(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+id,e);
  }


  }
}
