package model.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RecordDBManager extends AbstractDBManager{

	@Override
	public void add(Object o) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			session.save(o);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
	}

}
