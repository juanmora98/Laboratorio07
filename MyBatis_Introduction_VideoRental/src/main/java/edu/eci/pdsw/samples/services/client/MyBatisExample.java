/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     * @throws ExcepcionServiciosAlquiler 
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        
        //Crear el mapper y usarlo: 
        //ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        //cm...
        /*ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        for (Cliente c: cm.consultarClientes()){
            System.out.println(c.toString());
        }*/
        /*System.out.println();
        System.out.println("Cliente consultado: ");
        System.out.println(cm.consultarCliente(12345));*/
        //System.out.println("Agregamos item rentado a cliente");
        //cm.agregarItemRentadoACliente(9843, 935, new Date(0001,01,01), new Date(0002,02,02));
        
        
        /*System.out.println("Agregado nuevo item");*/
        //ItemMapper im=sqlss.getMapper(ItemMapper.class);
        //im.insertarItem(new Item(new TipoItem(1, "Video"), 2137560, "Cienpies humano 2", "Personas encoladas", new Date(0001,01,01), 5000, "DVD", "Familiar"));
        /*for( Item i : im.consultarItems()) {
        	System.out.println(i.toString());
        }*/
        /*System.out.println(im.consultarItem(2137560).toString());
        sqlss.commit();*/
        ServiciosAlquiler sa = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler(); 
        /*A continuzao pruebas para el services impl*/
        //System.out.println(sa.valorMultaRetrasoxDia(5));
        System.out.println("El cliente a consultar es aquel que tiene como documento 12345");
        Cliente cliente = sa.consultarCliente(12345);
        System.out.println("Cliente consultado:");
        System.out.println(cliente.toString());
        /*List<ItemRentado> itemsRentados = sa.consultarItemsCliente(12345);
        for(ItemRentado it : itemsRentados) {
        	System.out.println(it.toString());
        }*/
        /*List<Cliente> clientes = sa.consultarClientes();
        for (Cliente c : clientes) {
        	System.out.println(c.toString());
        }*/
        /*Item item = sa.consultarItem(2137559);
        Sytem.out.println(item.toString());*/
        /*List<Item> items = sa.consultarItemsDisponibles();
        for(Item i : items) {
        	System.out.println(i.toString());
        }*/
        //System.out.println(sa.consultarMultaAlquiler(5, Date.valueOf("2017-03-25")));
        //System.out.println(sa.consultarTipoItem(1).toString());
        /*List<TipoItem> tiposdeitems = sa.consultarTiposItem();
        for(TipoItem ti: tiposdeitems) {
        	System.out.println(ti.toString());
        }*/
        /*sa.registrarAlquilerCliente(Date.valueOf("2018-10-19"), 12345, sa.consultarItem(2137561), 5);
        List<ItemRentado> itemsRentadosPorElJavi = sa.consultarItemsCliente(12345);
        for(ItemRentado ir: itemsRentadosPorElJavi) {
        	System.out.println(ir.toString());
        }*/
        /*sa.registrarCliente(new Cliente("Ana deja de mencionar Redes pls", 1234567894, "1234567894", "   ", "anadejademencionarredespls@e.e",true, new ArrayList<ItemRentado>()));
        Cliente ana = sa.consultarCliente(1234567894);
        System.out.println(ana.toString());*/
        /*sa.actualizarTarifaItem(777, 2000);
        Item item = sa.consultarItem(777);
        System.out.println(item.toString());*/
        /*Item i = new Item(new TipoItem(1, "Video"), 1234567894, "Fiesta", "Video realizado por Mister Jagger", Date.valueOf("2016-10-21"), 5000, "DVD", "+18");
        sa.registrarItem(i);
        Item it = sa.consultarItem(1234567894);
        System.out.println(it.toString());*/
        /*sa.vetarCliente(1234567894, true);
        Cliente a = sa.consultarCliente(1234567894);
        System.out.println(a.toString());*/
        sqlss.close();
        

        
        
    }


}
