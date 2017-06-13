package model.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import business.TeamStatus;
import model.entities.LolTeam;
import model.persistence.dao.LolTeamDAO;

@Stateless
public class LolTeamService {

	private static LolTeamDAO lolteamDAO;

	public LolTeamService() {
			lolteamDAO = new LolTeamDAO();
		}

	public void insert(LolTeam team) {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.insert(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public void update(LolTeam team) {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.update(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public LolTeam findById(Integer id) {
		lolteamDAO.openCurrentSession();
		LolTeam team = lolteamDAO.findById(id);
		lolteamDAO.closeCurrentSession();
		return team;
	}
	
	public List<LolTeam> findIncompleteTeams(){
		lolteamDAO.openCurrentSession();
		ArrayList<LolTeam> teamList = (ArrayList<LolTeam>) lolteamDAO.findByField("status_time", TeamStatus.INCOMPLETE);
		lolteamDAO.closeCurrentSession();
		return teamList;
	}
	
	public void delete(Integer id) {
		lolteamDAO.openCurrentSessionwithTransaction();
		LolTeam team = lolteamDAO.findById(id);
		lolteamDAO.delete(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public List<LolTeam> findAll() {
		lolteamDAO.openCurrentSession();
		List<LolTeam> teams = lolteamDAO.findAll();
		lolteamDAO.closeCurrentSession();
		return teams;
	}

	public void deleteAll() {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.deleteAll();
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public LolTeamDAO usuarioDAO() {
		return lolteamDAO;
	}
}
