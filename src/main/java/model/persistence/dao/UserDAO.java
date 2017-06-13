package model.persistence.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.entities.LolPlayer;
import model.entities.User;

public class UserDAO implements GenericDAO<User, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public UserDAO() {
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(LolPlayer.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public User findById(Integer id) {
		User usuario = (User) getCurrentSession().get(User.class, id);
		return usuario;
	}

	public void insert(User user) {
		getCurrentSession().save(user);
	}

	public void delete(User user) {
		getCurrentSession().delete(user);
	}

	public void update(User user) {
		getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> usuarios = (List<User>) getCurrentSession().createQuery("FROM User").list();
		return usuarios;
	}
	
	public void deleteAll(){
		List<User> usuarios = findAll();
		for (User usuario : usuarios)
			delete(usuario);
	}
	
	@SuppressWarnings("unchecked")
	public User login(String login, String password) {
		List<User> usuarios = (List<User>) getCurrentSession()
				.createQuery("FROM User WHERE login =:login AND password =:password")
				.setParameter("login", login).setParameter("password", password).list();
		if(usuarios.size() > 0)
			return usuarios.get(0);
		else{
			return null;
		}
	}
}
