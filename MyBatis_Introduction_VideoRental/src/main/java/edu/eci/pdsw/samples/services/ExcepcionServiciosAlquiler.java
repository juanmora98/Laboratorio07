package edu.eci.pdsw.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception {
	public ExcepcionServiciosAlquiler(String message) {
		super(message);
	}

	public ExcepcionServiciosAlquiler(String string, PersistenceException ex) {
		super(string, ex);
	}

}
