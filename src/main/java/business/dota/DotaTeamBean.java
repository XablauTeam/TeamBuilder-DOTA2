package business.dota;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.PlayerBean;
import business.PlayerStatus;
import business.TeamStatus;
import business.exceptions.CannotInsertException;
import model.entities.AbstractPlayer;
import model.entities.AbstractTeam;
import model.entities.DotaPlayer;
import model.entities.DotaTeam;
import model.persistence.service.DotaTeamService;

@Stateless
public class DotaTeamBean {

	@EJB
	private DotaTeamService dotaTeamService;
	@EJB
	private DotaTeamProcessor dotaTeamProcessor;
	@EJB
	private PlayerBean playerBean;
	
	
	
	public AbstractTeam findTeam(AbstractPlayer player) {
		ArrayList<DotaTeam> teamList = (ArrayList<DotaTeam>) dotaTeamService.findAll();
		if (!teamList.isEmpty()) {
			for (DotaTeam teamAnalyzed : teamList) {
				if (teamAnalyzed.getStatus() != TeamStatus.COMPLETE && 
						dotaTeamProcessor.isRoleNeeded(teamAnalyzed, player)) {
					return teamAnalyzed;
				}
			}
		}

		return null;
	}

	public AbstractTeam createNewTeam(AbstractPlayer player) {
		DotaTeam dotaTeam = new DotaTeam();
		dotaTeamService.insert(dotaTeam);
		return dotaTeam;
	}

	public void insertPlayerInTeam(AbstractPlayer player, AbstractTeam team) throws CannotInsertException {
		dotaTeamProcessor.insertPlayerInTeam(player, team);
		dotaTeamService.update((DotaTeam) team);
		player.setTeamID(team.getIdTime());
		player.setStatus(PlayerStatus.IN_TEAM);
		playerBean.atualizarPlayer((DotaPlayer)player);
	}

	public boolean playerHasTeam(AbstractPlayer player) {
		return player.getTeamID() != 0;
	}

	public boolean removePlayerFromTeam(AbstractPlayer player) {
		DotaTeam team;
		if (player.getTeamID() != 0) {
			team = dotaTeamService.findById(player.getTeamID());
			dotaTeamProcessor.removePlayer(player, team);
			dotaTeamService.update(team);
			return true;
		}
		return false;
	}
	
	public DotaTeam findTeamByID(Integer playerID){
		return dotaTeamService.findById(playerID);
	}

}