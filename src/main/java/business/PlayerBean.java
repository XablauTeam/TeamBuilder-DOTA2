package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import api.AbstractProfile;
import api.FacadeAPI;
import api.exceptions.ConnectionException;
import business.exceptions.BusinessException;
import model.entities.DotaPlayer;
import model.entities.User;
import model.persistence.service.DotaPlayerService;
import model.persistence.service.UserService;

@Stateless
public class PlayerBean {
	
	@EJB
	private DotaPlayerService dotaPlayerService;
	@EJB
	private UserService userService;

	public DotaPlayer findById(Integer id) throws BusinessException {
		DotaPlayer ab = dotaPlayerService.findById(id);
		if (ab != null)
			return ab;
		else
			throw new BusinessException("Player não encontrado.");
	}

	public void incluirPlayer(User user, DotaPlayer dotaPlayer) throws ConnectionException {
				
		FacadeAPI api = new FacadeAPI();
		AbstractProfile absProfile = api.getProfile(dotaPlayer.getPlayerName());
		
		if(absProfile != null){
			
			dotaPlayer.setGamePlayerID(absProfile.getId());
			dotaPlayer.setPlayerLevel(absProfile.getLevel());
			dotaPlayer.setPlayerName(absProfile.getName());
			
			User auxUser = userService.findById(user.getIdUsuario());
			auxUser.setPlayer(dotaPlayer);
			userService.update(auxUser);
			
		}else{
			throw new ConnectionException("Game Profile não localizado.");
		}
		
	}
	
	public void atualizarPlayer(DotaPlayer player){
		dotaPlayerService.update(player);
	}

}
