package com.liy.Vivero.Model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOtipo {
    private static SessionFactory factory;
    
    public DAOtipo(SessionFactory factory) {
		DAOtipo.factory = factory;
    }
    
    public void creaTipo(Tipo tipo) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(tipo);
        session.getTransaction().commit();
        session.close();
    }
    
    public void actualizaTipo(Tipo tipo) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(tipo);
        session.getTransaction().commit();
        session.close();
    }
    
    public void borrarTipo(Tipo tipo) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(tipo);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<?> mostrarTipo() {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Tipo").list(); 
        return empList1;
    }
    
    public boolean validaTipo(String nombre) {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Tipo").list(); 
        for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Tipo dao = (Tipo) iterator.next();
        	if (dao.getNombre().compareTo(nombre)==0)
        		return true;
        }
        return false;
    }
    
    public Tipo regresaTipo(String nombre) {
    	Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Tipo").list(); 
        for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Tipo dao = (Tipo) iterator.next();
        	if (dao.getNombre().compareTo(nombre)==0)
        		return dao;
        }
        return null;
    }
    
    public Tipo regresaTipoId(Integer id) {
    	Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Tipo").list(); 
        for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Tipo dao = (Tipo) iterator.next();
        	if (dao.getId() == id)
        		return dao;
        }
        return null;
    }
    
    public boolean validaTipoId(int id) {
	    Session session = factory.openSession();
	    List<?> empList1 = session.createQuery(" from Tipo").list(); 
	    for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
		    	Tipo dao = (Tipo) iterator.next();
		    	if (dao.getId() == id)
		    		return true;
		}
	    return false;
    }
    
    public boolean validaProdsEnTipos(int id) {
    	Tipo one = regresaTipoId(id);
    	List<Producto> lista = one.getProducto();
    	for(Iterator<?> iterator = lista.iterator(); iterator.hasNext();) {
    		return true;
    	}
    	return false;
    }
}
