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

import commons.Record;


public class DbManager {
	private static DbManager instance = new DbManager();

	public static DbManager getInstance() {
		return instance;
	}

	private SessionFactory factory;

	private DbManager() {
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
			System.out.println(params.getLevelId().isEmpty());
			System.out.println(params.getUserName().isEmpty());
			System.out.println(params.getOrderBy());
			if(params.getLevelId().equals("null")&&params.getUserName().equals("null")){
				System.out.println("yyyyy");
				query=session.createQuery("from Records as rec ORDER BY rec."+params.getOrderBy());
			}
			else if(!params.getLevelId().equals("null")){
				System.out.println("ssss");
				query=session.createQuery("from Records as rec where rec.levelId=:levelId "+
										  "ORDER BY rec."+params.getOrderBy());
				query.setParameter("levelId", params.getLevelId());
			}
			else if(!params.getUserName().equals("null")){
				System.out.println("tttt");
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
