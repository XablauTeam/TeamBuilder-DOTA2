package business.lol;

import javax.ejb.Stateless;

import business.exceptions.CannotInsertException;
import model.entities.AbstractPlayer;
import model.entities.AbstractTeam;
import model.entities.LolPlayer;
import model.entities.LolTeam;

@Stateless
public class LolTeamProcessor {

	public void insertPlayerInTeam(AbstractPlayer player, AbstractTeam team) throws CannotInsertException {
		LolPlayer lolPlayer = (LolPlayer)player;
		LolTeam lolTeam = (LolTeam)team;
		if (isRoleNeeded(lolTeam, lolPlayer)){
			switch (lolPlayer.getRole()){
			case TOP:
				lolTeam.setTop(lolPlayer);
				break;
			case JUNGLER:
				lolTeam.setJungler(lolPlayer);
				break;
			case MID:
				lolTeam.setMid(lolPlayer);
				break;
			case SUPPORT:
				lolTeam.setSupport(lolPlayer);
				break;
			case BOTTOM:
				lolTeam.setBottom(lolPlayer);
				break;
			default:
				break;
			}	
		}
		else{
			throw new CannotInsertException("Não foi possivel inserir o jogador no time");
		}
	}

	
	public void removePlayer(AbstractPlayer player, AbstractTeam team) {
		LolPlayer lolPlayer = (LolPlayer)player;
		LolTeam lolTeam = (LolTeam)team;
		switch (lolPlayer.getRole()){
		case TOP:
			lolTeam.setTop(null);
			break;
		case JUNGLER:
			lolTeam.setJungler(null);
			break;
		case MID:
			lolTeam.setMid(null);
			break;
		case SUPPORT:
			lolTeam.setSupport(null);
			break;
		case BOTTOM:
			lolTeam.setBottom(null);
			break;
		default:
			break;
		}

	}

	
	public boolean isRoleNeeded(AbstractTeam team, AbstractPlayer player) {
		LolPlayer lolPlayer = (LolPlayer)player;
		LolTeam lolTeam = (LolTeam)team;
		switch (lolPlayer.getRole()){
			case TOP:
				return lolTeam.getTop() == null;
			case JUNGLER:
				return lolTeam.getJungler() == null;
			case MID:
				return lolTeam.getMid() == null;
			case SUPPORT:
				return lolTeam.getSupport() == null;
			case BOTTOM:
				return lolTeam.getBottom() == null;
			default:
				return false;
		}
	}


	public boolean isComplete(AbstractTeam team) {
		LolTeam lolTeam = (LolTeam)team;
		return (lolTeam.getTop() != null &&
				lolTeam.getJungler() != null &&
				lolTeam.getMid() != null &&
				lolTeam.getSupport() != null &&
				lolTeam.getBottom() != null);
	}
}