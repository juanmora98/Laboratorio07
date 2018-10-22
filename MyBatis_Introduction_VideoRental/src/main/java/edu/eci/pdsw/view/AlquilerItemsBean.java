/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.view;



import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


/**
 *
 * @author jfmor
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "AlquilerItemsBean")
@RequestScoped
public class AlquilerItemsBean extends BasePageBean{
    
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    @ManagedProperty(value="#{param.documento}")
    private long documento;
    private long costo;
    private List<ItemRentado> items;
    
    public List<Cliente> getClientes() throws ExcepcionServiciosAlquiler{
        try {
            return serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {  
            throw ex;
        }
    }
    
    public void crearCliente(String NombreC, long DocumentoC, String TelefonoC, String DireccionC, String EmailC) throws ExcepcionServiciosAlquiler{
        Cliente clienteN = new Cliente(NombreC, DocumentoC, TelefonoC, DireccionC, EmailC);
        serviciosAlquiler.registrarCliente(clienteN);
    }
    
    
    public void setDocumento(long InputDocumento){
        this.documento = InputDocumento;
    }
    
    public void calcularCosto(int id, int dias) {
    	try {
    		Item item = serviciosAlquiler.consultarItem(id);
    		long valor = item.getTarifaxDia();
    		costo = valor*dias;
    	}catch(Exception e) {
    		costo=0;
    	}
    	
    }
    
    public void setCosto(long costo) {
    	this.costo = costo;
    }
    
    public long getCosto() {
    	return costo;
    }
    
    public void registrarItemRentado(int iditem, int numDias) throws ExcepcionServiciosAlquiler {
    	System.out.println("Hola k tal el documento es: "+documento);
    	Date fechaRegistro = new Date(System.currentTimeMillis());
    	Item item = serviciosAlquiler.consultarItem(iditem);
    	
    	serviciosAlquiler.registrarAlquilerCliente(fechaRegistro, documento, item, numDias);
    }
    
    public List<ItemRentado> itemsNoDevueltos() throws ExcepcionServiciosAlquiler{
    	System.out.println(documento);
    	Cliente cliente = serviciosAlquiler.consultarCliente(documento);
    	List<ItemRentado> items= new ArrayList<ItemRentado>();
    	for(ItemRentado ir : cliente.getRentados()) {
    		if(ir.getFechafinrenta().getTime()<System.currentTimeMillis()) {
    			items.add(ir);
    		}
    	}
    	this.items=items;
    	return items;
    }
    
    
    public long multas() throws ExcepcionServiciosAlquiler {
    	long valorMultas=0;
    	for(ItemRentado ir : items) {
    		valorMultas+= serviciosAlquiler.consultarMultaAlquiler(ir.getItem().getId(), new Date(System.currentTimeMillis()));
    	}
    	return valorMultas;
    }
    
    public long getDocumento() {
    	return documento;
    }
            
    
}
