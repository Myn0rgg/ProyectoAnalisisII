/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Pojos.TbFunciones;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
    public static List<TbFunciones>universo(){
        
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List<TbFunciones>lista=null;
        try{
        session.beginTransaction();
        Criteria criteria=session.createCriteria(TbFunciones.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.addOrder(Order.asc("nomFuncion"));       
        lista=criteria.list();
        }catch(HibernateException e){
           System.err.println("error"+e); 
        }finally{
            session.getTransaction().commit();

        }
        return lista;
    }   
    
     public static boolean update(Integer idFuncion, String nomFuncion){
      boolean bandera=false;
      Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
      Criteria criteria=session.createCriteria(TbFunciones.class);
      criteria.add(Restrictions.eq("idFuncion", idFuncion));
      TbFunciones update=(TbFunciones)criteria.uniqueResult();
      Transaction transaccion=null;
      try{
      transaccion=session.beginTransaction();
      if(update!=null){
          update.setNomFuncion(nomFuncion);
          session.update(update);
          bandera=true;
      }
      transaccion.commit();
      }catch(HibernateException e){
           System.err.println("error"+e); 
      }finally{
          session.close();
      }
              
           return bandera;
        
    }
     
      public static boolean anular(Integer idFuncion){
      boolean bandera=false;
      Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
      Criteria criteria=session.createCriteria(TbFunciones.class);
      criteria.add(Restrictions.eq("idFuncion", idFuncion));
      TbFunciones anular=(TbFunciones)criteria.uniqueResult();
      Transaction transaccion=null;
      try{
      transaccion=session.beginTransaction();
      if(anular!=null){
          anular.setEstado(false);
          session.save(anular);
          bandera=true;
      }
      transaccion.commit();
      }catch(HibernateException e){
           System.err.println("error"+e); 
      }finally{
          session.close();
      }
         
           return bandera;
        
    }
       
    
}
