package model.persistence.service;

import java.util.List;

import javax.ejb.Stateless;

import model.entities.DotaPlayer;
import model.persistence.dao.DotaPlayerDAO;

@Stateless
public class DotaPlayerService {
	
	private static DotaPlayerDAO lolplayerDAO;
	
	
	public DotaPlayerService() {
		lolplayerDAO = new DotaPlayerDAO();
	}

	public void insert(DotaPlayer player) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.insert(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public void update(DotaPlayer player) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.update(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public DotaPlayer findById(Integer id) {
		lolplayerDAO.openCurrentSession();
		DotaPlayer player = lolplayerDAO.findById(id);
		lolplayerDAO.closeCurrentSession();
		return player;
	}

	public void delete(Integer id) {
		lolplayerDAO.openCurrentSessionwithTransaction();
		DotaPlayer player = lolplayerDAO.findById(id);
		lolplayerDAO.delete(player);
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}

	public List<DotaPlayer> findAll() {
		lolplayerDAO.openCurrentSession();
		List<DotaPlayer> players = lolplayerDAO.findAll();
		lolplayerDAO.closeCurrentSession();
		return players;
	}

	public void deleteAll() {
		lolplayerDAO.openCurrentSessionwithTransaction();
		lolplayerDAO.deleteAll();
		lolplayerDAO.closeCurrentSessionwithTransaction();
	}



	public DotaPlayerDAO usuarioDAO() {
		return lolplayerDAO;
	}
}