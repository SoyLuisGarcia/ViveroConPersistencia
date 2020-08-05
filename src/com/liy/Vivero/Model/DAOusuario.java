package com.liy.Vivero.Model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOusuario {
    private static SessionFactory factory;
    
    public DAOusuario(SessionFactory factory) {
		DAOusuario.factory = factory;
    }
    
    public void creaUsuario(Usuario usuario) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<?> mostrarUsuarios() {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Usuario").list(); 
        return empList1;
    }
    
    
    public boolean validarUsuario(String usuario) {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Usuario").list();
        for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Usuario dao = (Usuario) iterator.next();
        	if (usuario.compareTo(dao.getUsuario())==0) {
        		return true;
        	}
        }
        return false;
    }
    
    public boolean validarContrasenia(String usuario, String contrasenia) {
        Session session = factory.openSession();
        List<?> empList1 = session.createQuery(" from Usuario").list();
        for(Iterator<?> iterator = empList1.iterator(); iterator.hasNext();) {
        	Usuario dao = (Usuario) iterator.next();
        	if (usuario.compareTo(dao.getUsuario())==0 && contrasenia.compareTo(dao.getContrasenia())==0) {
        		return true;
        	}
        }
        return false;
    }
}
