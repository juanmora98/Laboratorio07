package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;
   @Inject
   private ItemRentadoDAO itemrentadoDAO;
   @Inject
   private TipoItemDAO tipoitemDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
	   Item item = itemDAO.load(itemId);
	   return (int) item.getTarifaxDia();
	   
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
		   Cliente cliente = clienteDAO.load(docu);
		   return cliente;
       }
	   catch(PersistenceException ex) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar al cliente "+docu,ex); 
	   }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try{
    	   Cliente cliente = clienteDAO.load(idcliente);
    	   return cliente.getRentados();
       }
       catch(PersistenceException ex) {
		   throw new ExcepcionServiciosAlquiler("Error los items rentados del cliente "+idcliente,ex); 
	   }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
		   List<Cliente> clientes = clienteDAO.loadAll();
		   return clientes;
       }
	   catch(PersistenceException ex) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar a los clientes",ex); 
	   }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() {
	   List<Item> items = itemDAO.loadAll();
	   return items;
   }

   @Override
   /** Usado para calcular el valor de una multa por alquiler.
    * 
    * 
    */
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   try{
		   ItemRentado itemRentado = itemrentadoDAO.load(iditem);
		   Item item = itemRentado.getItem();
		   Date fechaFinalRenta= itemRentado.getFechafinrenta();
		   long valorXDia = item.getTarifaxDia();
		   long multa = valorXDia * (fechaDevolucion.getTime()/86400000 - fechaFinalRenta.getTime()/86400000);
		   return multa;
	   }catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al verificar el valor de la multa "+iditem+" con fecha "+fechaDevolucion,ex);
       }
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
		   TipoItem tipoItem = tipoitemDAO.load(id);
		   return tipoItem;
	   }catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item "+id,ex);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
	   try {
		   List<TipoItem> tiposItem = tipoitemDAO.loadAll();
		   return tiposItem;
	   }
	   catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los tipos de items",ex);
       }
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		   Date fechaFinalRenta = new Date(date.getTime()+(numdias*86400000));
		   clienteDAO.agregarItemRentadoACliente(docu, item.getId(), date, fechaFinalRenta);
	   }
	   catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar un item rentado al cliente "+docu,ex);
       }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
    	   clienteDAO.save(c);
       }catch(PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al insertar el cliente"+c.getDocumento(),ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       try{
    	   Item item = itemDAO.load(iditem);
    	   long valorxdia = item.getTarifaxDia();
    	   return valorxdia*numdias;
       }catch( PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al calcular el valor de la renta",ex);
       }
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
    	   itemDAO.actualizarTarifaItem(id, tarifa);
       }catch(PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item "+id,ex);
       }
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
    	   itemDAO.save(i);
       }catch(PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al registrar el item",ex);
       }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
    	   clienteDAO.vetar(docu, estado);
       }catch(PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al vetar al cliente "+docu,ex);
       }
   }
}