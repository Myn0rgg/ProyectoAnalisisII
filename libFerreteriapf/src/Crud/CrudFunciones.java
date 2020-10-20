/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Pojos.TbFunciones;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mynor
 */
public class CrudFunciones {
    public static boolean insert(String nomFuncion) {
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(TbFunciones.class);
        criteria.add(Restrictions.eq("nomFuncion", nomFuncion));
        criteria.add(Restrictions.eq("estado", true));
        TbFunciones insert=(TbFunciones)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
           transaccion=session.beginTransaction();
           if(insert==null){
               insert=new TbFunciones();
               insert.setEstado(true);
               insert.setNomFuncion(nomFuncion);
               session.save(insert);
               bandera=true;
           }
           transaccion.commit();
           
        }catch(HibernateException e){
            transaccion.rollback();
            System.err.println("error"+e);
        }finally{
            session.close();
        }
        return bandera;       
    }
    
}
