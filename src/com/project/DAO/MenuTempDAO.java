package com.project.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.model.Menu_temp;
import com.project.model.User;

public class MenuTempDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Menu_temp> getAllMenu()
	{
		System.out.println("inside menu temp dao:");
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		List<Menu_temp> result = session.createQuery("from Menu_temp").list();
		for(Menu_temp m:result)
		{
			System.out.println(m);
		}
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return result;
		 
	}
}
