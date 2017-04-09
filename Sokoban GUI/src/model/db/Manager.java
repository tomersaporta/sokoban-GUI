package model.db;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Manager {
	private static Manager instance = new Manager();

	public static Manager getInstance() {
		return instance;
	}

	private SessionFactory factory;

	private Manager() {
		// to show the severe msg
		Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

		// reading the xml so he can connect to the Db
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	public void add(Object obj) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			session.save(obj);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Record> recordsQuery(QueryParams params){
		Session session = null;
		Record record=null;
		Query query=null;
		List<Record>list=null;
		
		try {
			session = factory.openSession();
			if(params.getLevelId()!=null){
				query=session.createQuery("from Records as rec where rec.levelId=:levelId "+
										  "ORDER BY rec."+params.getOrderBy());
				query.setParameter("levelId", params.getLevelId());
			}
			else if(params.getUserName()!=null){
				query=session.createQuery("from Records as rec where rec.userName=:userName "+
										  "ORDER BY rec."+params.getOrderBy());
				query.setParameter("userName", params.getUserName());
			}
			
			list=query.getResultList();
			Iterator<Record>it=list.iterator();
			
			while(it.hasNext()){
				record=it.next();
				System.out.println(record);
			}
			
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	
	}
	
	public void close() {
		factory.close();
	}

}
