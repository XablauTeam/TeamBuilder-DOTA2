package model.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.entities.LolPlayer;

public class LolPlayerDAO implements GenericDAO<LolPlayer, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public LolPlayerDAO() {
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

	public LolPlayer findById(Integer id) {
		LolPlayer player = (LolPlayer) getCurrentSession().get(LolPlayer.class, id);
		return player;
	}

	public void insert(LolPlayer player) {
		getCurrentSession().save(player);
	}

	public void delete(LolPlayer player) {
		getCurrentSession().delete(player);
	}

	public void update(LolPlayer player) {
		getCurrentSession().update(player);
	}

	@SuppressWarnings("unchecked")
	public List<LolPlayer> findAll() {
		List<LolPlayer> players = (List<LolPlayer>) getCurrentSession().createQuery("FROM lolplayer").list();
		return players;
	}
	
	public void deleteAll(){
		List<LolPlayer> players = findAll();
		for (LolPlayer player : players)
			delete(player);
	}
}
