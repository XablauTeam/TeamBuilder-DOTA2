package model.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import business.TeamStatus;
import model.entities.DotaTeam;
import model.persistence.dao.DotaTeamDAO;

@Stateless
public class DotaTeamService {

	private static DotaTeamDAO lolteamDAO;

	public DotaTeamService() {
			lolteamDAO = new DotaTeamDAO();
		}

	public void insert(DotaTeam team) {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.insert(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public void update(DotaTeam team) {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.update(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public DotaTeam findById(Integer id) {
		lolteamDAO.openCurrentSession();
		DotaTeam team = lolteamDAO.findById(id);
		lolteamDAO.closeCurrentSession();
		return team;
	}
	
	public List<DotaTeam> findIncompleteTeams(){
		lolteamDAO.openCurrentSession();
		ArrayList<DotaTeam> teamList = (ArrayList<DotaTeam>) lolteamDAO.findByField("status_time", TeamStatus.INCOMPLETE);
		lolteamDAO.closeCurrentSession();
		return teamList;
	}
	
	public void delete(Integer id) {
		lolteamDAO.openCurrentSessionwithTransaction();
		DotaTeam team = lolteamDAO.findById(id);
		lolteamDAO.delete(team);
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public List<DotaTeam> findAll() {
		lolteamDAO.openCurrentSession();
		List<DotaTeam> teams = lolteamDAO.findAll();
		lolteamDAO.closeCurrentSession();
		return teams;
	}

	public void deleteAll() {
		lolteamDAO.openCurrentSessionwithTransaction();
		lolteamDAO.deleteAll();
		lolteamDAO.closeCurrentSessionwithTransaction();
	}

	public DotaTeamDAO usuarioDAO() {
		return lolteamDAO;
	}
}
