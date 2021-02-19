package com.spring.demo.dao;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.demo.entity.IdGenerator;


@Repository
public class IdGenratorDaoImpl implements IdGeneratorDao {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(IdGenratorDaoImpl.class);
	@Override
	public int doIncrementWithLock() {

		logger.info(" saving increment ");
		Session session = getSessionFactory().openSession();
		int data = 0;
		try {
			
			session.beginTransaction();
			 
			IdGenerator idg = (IdGenerator) session.get(IdGenerator.class, 1,LockOptions.UPGRADE);
			if(idg==null) {
				idg=new IdGenerator();
				idg.setNumber(1);
				idg.setId(1);
				session.save(idg);
			}
			else{
			logger.info(" idg number is"+idg.getNumber());
			idg.setNumber(idg.getNumber() + 1);
			}
			idg = (IdGenerator) session.merge(idg);
			data = idg.getNumber();
			session.getTransaction().commit();
		} catch (org.hibernate.StaleObjectStateException e) {
			 session.getTransaction().rollback();
			throw e;
		}
		catch (Exception e) {
			throw e;
		}finally {
			session.close();
		}

		return data;

	}

}
