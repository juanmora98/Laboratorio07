/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jfmor
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "AlquilerItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean{
    
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private long documento;
    
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
            
    
}
