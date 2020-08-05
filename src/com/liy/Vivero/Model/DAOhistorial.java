package com.liy.Vivero.Model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOhistorial {
    private static SessionFactory factory;
    
    public DAOhistorial(SessionFactory factory) {
		DAOhistorial.factory = factory;
    }
    
    public Integer creaHistorial(Historial his) {
    	Integer id;
        Session session = factory.openSession();
        session.beginTransaction();
        id = (Integer) session.save(his);
        session.getTransaction().commit();
        session.close();
        return id;
    }
    
    public void actualizaHistorial(Historial his) {
    	Session session = factory.openSession();
        session.beginTransaction();
        session.update(his);
        session.getTransaction().commit();
        session.close();
    }
    
    public void borrarHistorial(Historial his) {
    	Session session = factory.openSession();
        session.beginTransaction();
        session.delete(his);
        session.getTransaction().commit();
        session.close();
    }
    
    public Historial mostrarHistorial(Integer id) {
    	Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Historial").list();
        for (Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Historial dao = (Historial) iterator.next();
        	if (dao.getId() == id) return dao;
        }
        return null;
    }
}
