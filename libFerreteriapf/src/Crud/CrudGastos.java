/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Pojos.TbGastos;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mynor
 */
public class CrudGastos {
    
    public static boolean insert(String nombre){
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(TbGastos.class);
        TbGastos insert=(TbGastos)criteria.uniqueResult();
        Transaction transaccion=null;
        try {
            transaccion=session.beginTransaction();
            if(insert==null){
                insert=new TbGastos();
                insert.setEstado(true);
                insert.setNomGasto(nombre);
                session.save(insert);
                bandera=true;
            }
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            System.out.println("error"+e);
        }finally{
            session.close();
        }
        return bandera;
        
    }
    
}
