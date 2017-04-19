package com.project.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.project.model.User;


@Repository
public class UserDAO implements ApplicationContextAware  {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void addUser(User user)
	{
		System.out.println("inside user dao:"+user);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		 
	}
	
	public User getUser(User user)
	{
		System.out.println("inside user dao:"+user);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		//Query query=  session.createQuery("from User where email_id=?");
		
		//user=(User)query.setString(1,user.getEmail_id()).uniqueResult();
		//user = (User) session.get(User.class, user.getVerification());
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email_id", user.getEmail_id()));
		User u=(User)criteria.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return u;
		 
	}
	
	
	

	
	public int searchUser(User user)
	{
		System.out.println("inside user dao:"+user);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		/*List<User> result = session.createQuery("from User").list();
		for(User u:result)
		{
			if((u.getEmail_id()).equals(user.getEmail_id()))
			{
				if((u.getPassword()).equals(user.getPassword()))
				{
					System.out.println("User u:"+u);
				}
				else
				{
					System.out.println("Password incorrect");
				}
			}
		}*/
		int flag;
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email_id", user.getEmail_id()));
		User u=(User)criteria.uniqueResult();
		if(u!=null)
		{
			if(u.getPassword().equals(user.getPassword()))
			{
				flag=1;
				System.out.println(u.getEmail_id());
			}
			else
				flag=0;
				
		}
		else
		{
			flag=0;
		}
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return flag;
		 
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
