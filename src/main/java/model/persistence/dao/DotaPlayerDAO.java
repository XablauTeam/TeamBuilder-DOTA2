package model.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.entities.DotaPlayer;

public class DotaPlayerDAO implements GenericDAO<DotaPlayer, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public DotaPlayerDAO() {
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
		configuration.addAnnotatedClass(DotaPlayer.class);
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

	public DotaPlayer findById(Integer id) {
		DotaPlayer player = (DotaPlayer) getCurrentSession().get(DotaPlayer.class, id);
		return player;
	}

	public void insert(DotaPlayer player) {
		getCurrentSession().save(player);
	}

	public void delete(DotaPlayer player) {
		getCurrentSession().delete(player);
	}

	public void update(DotaPlayer player) {
		getCurrentSession().update(player);
	}

	@SuppressWarnings("unchecked")
	public List<DotaPlayer> findAll() {
		List<DotaPlayer> players = (List<DotaPlayer>) getCurrentSession().createQuery("FROM DotaPlayer").list();
		return players;
	}
	
	public void deleteAll(){
		List<DotaPlayer> players = findAll();
		for (DotaPlayer player : players)
			delete(player);
	}
}
