package model.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.entities.DotaPlayer;
import model.entities.DotaTeam;

public class DotaTeamDAO implements GenericDAO<DotaTeam, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public DotaTeamDAO() {
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
		configuration.addAnnotatedClass(DotaTeam.class);
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

	public DotaTeam findById(Integer id) {
		DotaTeam player = (DotaTeam) getCurrentSession().get(DotaTeam.class, id);
		return player;
	}

	public List<DotaTeam> findByField(String field, Object value){
		@SuppressWarnings("unchecked")
		ArrayList<DotaTeam> arrayList = (ArrayList<DotaTeam>) getCurrentSession().get(field, (Serializable) value);
		return arrayList;
	}
	
	public void insert(DotaTeam player) {
		getCurrentSession().save(player);
	}

	public void delete(DotaTeam player) {
		getCurrentSession().delete(player);
	}

	public void update(DotaTeam player) {
		getCurrentSession().update(player);
	}

	@SuppressWarnings("unchecked")
	public List<DotaTeam> findAll() {
		List<DotaTeam> players = (List<DotaTeam>) getCurrentSession().createQuery("FROM DotaTeam").list();
		return players;
	}
	
	public void deleteAll(){
		List<DotaTeam> players = findAll();
		for (DotaTeam player : players)
			delete(player);
	}}