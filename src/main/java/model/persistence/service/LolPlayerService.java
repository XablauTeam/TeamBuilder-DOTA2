package model.persistence.service;

import java.util.List;

import javax.ejb.Stateless;

import model.entities.LolPlayer;
import model.persistence.dao.LolPlayerDAO;

@Stateless
public class LolPlayerService {
	
	private static LolPlayerDAO lolplayerDAO;
	
	
	public LolPlayerService() {
		lolplayerDAO = new LolPlayerDAO();
	}

	public void insert(LolPlayer player) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.insert(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public void update(LolPlayer player) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.update(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public LolPlayer findById(Integer id) {
		lolplayerDAO.openCurrentSession();
		LolPlayer player = lolplayerDAO.findById(id);
		lolplayerDAO.closeCurrentSession();
		return player;
	}

	public void delete(Integer id) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		LolPlayer player = lolplayerDAO.findById(id);
		lolplayerDAO.delete(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public List<LolPlayer> findAll() {
		lolplayerDAO.openCurrentSession();
		List<LolPlayer> players = lolplayerDAO.findAll();
		lolplayerDAO.closeCurrentSession();
		return players;
	}

	public void deleteAll() {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.deleteAll();
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}



	public LolPlayerDAO usuarioDAO() {
		return lolplayerDAO;
	}
}