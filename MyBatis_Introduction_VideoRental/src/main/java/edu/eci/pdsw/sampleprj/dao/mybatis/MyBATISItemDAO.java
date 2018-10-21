package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISItemDAO implements ItemDAO{

  @Inject
  private ItemMapper itemMapper;    

  @Override
  public void save(Item it) throws PersistenceException{
  try{
      itemMapper.insertarItem(it);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
  }        

  }

  @Override
  public Item load(int id) throws PersistenceException {
  try{
      return itemMapper.consultarItem(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+id,e);
  }


  }
  
  public List<Item> loadAll() throws PersistenceException{
	  try {
		  return itemMapper.consultarItems();
	  }catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al consultar los items");
	  }
  }
  
  public void actualizarTarifaItem(int item, long tarifa) throws PersistenceException{
	  try {
		  itemMapper.actualizarTarifaItem(item, tarifa);
	  }catch(PersistenceException ex) {
		  throw new PersistenceException("Error al actualizar la tarifa del item");
	  }
  }

  }
