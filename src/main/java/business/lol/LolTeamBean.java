package business.lol;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.PlayerBean;
import business.PlayerStatus;
import business.TeamStatus;
import business.exceptions.CannotInsertException;
import model.entities.AbstractPlayer;
import model.entities.AbstractTeam;
import model.entities.LolPlayer;
import model.entities.LolTeam;
import model.persistence.service.LolTeamService;

@Stateless
public class LolTeamBean {

	@EJB
	private LolTeamService lolTeamService;
	@EJB
	private LolTeamProcessor lolTeamProcessor;
	@EJB
	private PlayerBean playerBean;
	
	
	
	public AbstractTeam findTeam(AbstractPlayer player) {
		ArrayList<LolTeam> teamList = (ArrayList<LolTeam>) lolTeamService.findAll();
		if (!teamList.isEmpty()) {
			for (LolTeam teamAnalyzed : teamList) {
				if (teamAnalyzed.getStatus() != TeamStatus.COMPLETE && 
						lolTeamProcessor.isRoleNeeded(teamAnalyzed, player)) {
					return teamAnalyzed;
				}
			}
		}

		return null;
	}

	public AbstractTeam createNewTeam(AbstractPlayer player) {
		LolTeam lolTeam = new LolTeam();
		lolTeamService.insert(lolTeam);
		return lolTeam;
	}

	public void insertPlayerInTeam(AbstractPlayer player, AbstractTeam team) throws CannotInsertException {
		lolTeamProcessor.insertPlayerInTeam(player, team);
		lolTeamService.update((LolTeam) team);
		player.setTeamID(team.getIdTime());
		player.setStatus(PlayerStatus.IN_TEAM);
		playerBean.atualizarPlayer((LolPlayer)player);
	}

	public boolean playerHasTeam(AbstractPlayer player) {
		return player.getTeamID() != 0;
	}

	public boolean removePlayerFromTeam(AbstractPlayer player) {
		LolTeam team;
		if (player.getTeamID() != 0) {
			team = lolTeamService.findById(player.getTeamID());
			lolTeamProcessor.removePlayer(player, team);
			lolTeamService.update(team);
			return true;
		}
		return false;
	}
	
	public LolTeam findTeamByID(Integer playerID){
		return lolTeamService.findById(playerID);
	}

}