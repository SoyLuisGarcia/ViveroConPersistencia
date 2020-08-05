package com.liy.Vivero.Model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOproducto {
    private static SessionFactory factory;
    
    public DAOproducto(SessionFactory factory) {
		DAOproducto.factory = factory;
    }
    
    public void creaProducto(Producto producto) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(producto);
        session.getTransaction().commit();
        session.close();
    }
    
    public void actualizaProd(Producto prod) {
    	Session session = factory.openSession();
        session.beginTransaction();
        session.update(prod);
        session.getTransaction().commit();
        session.close();
    }
    
    public void borrarProd(Producto prod) {
    	Session session = factory.openSession();
        session.beginTransaction();
        session.delete(prod);
        session.getTransaction().commit();
        session.close();
    }
    
    public boolean validarProd(int id) {
    	Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Producto").list(); 
        for (Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Producto dao = (Producto) iterator.next();
        	if (dao.getId() == id) return true;
        }
        return false;
    }
    
    public List<?> mostrarProductos() {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Producto").list(); 
        return empList1;
    }
    
    public Producto mostararProducto(int id) {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Producto").list(); 
        for (Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Producto dao = (Producto) iterator.next();
        	if (dao.getId() == id) return dao;
        }
        return null;
    }
}
