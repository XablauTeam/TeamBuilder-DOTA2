package business.dota;

import javax.ejb.Stateless;

import business.exceptions.CannotInsertException;
import model.entities.AbstractPlayer;
import model.entities.AbstractTeam;
import model.entities.DotaPlayer;
import model.entities.DotaTeam;

@Stateless
public class DotaTeamProcessor {

	public void insertPlayerInTeam(AbstractPlayer player, AbstractTeam team) throws CannotInsertException {
		DotaPlayer dotaPlayer = (DotaPlayer)player;
		DotaTeam dotaTeam = (DotaTeam)team;
		if (isRoleNeeded(dotaTeam, dotaPlayer)){
			switch (dotaPlayer.getRole()){
			case OFFLANE:
				dotaTeam.setOfflane(dotaPlayer);
				break;
			case JUNGLER:
				dotaTeam.setJungler(dotaPlayer);
				break;
			case MID:
				dotaTeam.setMid(dotaPlayer);
				break;
			case SUPPORT:
				dotaTeam.setSupport(dotaPlayer);
				break;
			case CARRY:
				dotaTeam.setCarry(dotaPlayer);
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
		DotaPlayer dotaPlayer = (DotaPlayer)player;
		DotaTeam dotaTeam = (DotaTeam)team;
		switch (dotaPlayer.getRole()){
		case OFFLANE:
			dotaTeam.setOfflane(null);
			break;
		case JUNGLER:
			dotaTeam.setJungler(null);
			break;
		case MID:
			dotaTeam.setMid(null);
			break;
		case SUPPORT:
			dotaTeam.setSupport(null);
			break;
		case CARRY:
			dotaTeam.setCarry(null);
			break;
		default:
			break;
		}

	}

	
	public boolean isRoleNeeded(AbstractTeam team, AbstractPlayer player) {
		DotaPlayer dotaPlayer = (DotaPlayer)player;
		DotaTeam dotaTeam = (DotaTeam)team;
		switch (dotaPlayer.getRole()){
			case OFFLANE:
				return dotaTeam.getOfflane() == null;
			case JUNGLER:
				return dotaTeam.getJungler() == null;
			case MID:
				return dotaTeam.getMid() == null;
			case SUPPORT:
				return dotaTeam.getSupport() == null;
			case CARRY:
				return dotaTeam.getCarry() == null;
			default:
				return false;
		}
	}


	public boolean isComplete(AbstractTeam team) {
		DotaTeam dotaTeam = (DotaTeam)team;
		return (dotaTeam.getOfflane() != null &&
				dotaTeam.getJungler() != null &&
				dotaTeam.getMid() != null &&
				dotaTeam.getSupport() != null &&
				dotaTeam.getCarry() != null);
	}
}