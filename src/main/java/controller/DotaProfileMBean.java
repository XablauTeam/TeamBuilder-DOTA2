package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import business.PlayerBean;
import business.PlayerStatus;
import business.dota.DotaRole;
import business.dota.DotaTeamBean;
import business.dota.LolRegioes;
import business.exceptions.BusinessException;
import model.entities.DotaPlayer;
import model.entities.DotaTeam;
import model.entities.User;

@ManagedBean(name = "dotaProfileMBean")
@SessionScoped
public class DotaProfileMBean extends GenericMBean {

	@ManagedProperty("#{loginMBean.user}")
	private User user;

	private DotaPlayer player;

	private DotaTeam team;

	@EJB
	PlayerBean playerBean;

	@EJB
	DotaTeamBean teamBean;

	@PostConstruct
	public void onLoad() {
		try {
			this.player = playerBean.findById(user.getIdUsuario());
			team = teamBean.findTeamByID(this.player.getTeamID());
		} catch (BusinessException e) {
			player = new DotaPlayer();
		}
	}

	public String cadastrarPlayer() {
		try {
			player.setStatus(PlayerStatus.SEARCHING_TEAM);
			playerBean.incluirPlayer(user, player);
			user.setPlayer(player);
		} catch (Exception e) {
			incluirErro(e.getMessage());
		}
		return "";
	}

	public String procurarTime() {
		try {
			if(user.getPlayer().getTeamID() != 0)
				teamBean.removePlayerFromTeam(player);
			team = (DotaTeam) teamBean.findTeam(player);
			if(team == null)
				team = (DotaTeam) teamBean.createNewTeam(player);
			teamBean.insertPlayerInTeam(player, team);
		} catch (Exception e) {
			incluirErro(e.getMessage());
		}
		return "";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DotaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(DotaPlayer player) {
		this.player = player;
	}

	public DotaTeam getTeam() {
		return team;
	}

	public void setTeam(DotaTeam team) {
		this.team = team;
	}

	public List<PlayerStatus> getPlayerStatus() {
		List<PlayerStatus> itens = new ArrayList<PlayerStatus>();
		for (PlayerStatus stat : PlayerStatus.values()) {
			itens.add(stat);
		}
		return itens;
	}

	public List<DotaRole> getRoles() {
		List<DotaRole> itens = new ArrayList<DotaRole>();
		for (DotaRole role : DotaRole.values()) {
			itens.add(role);
		}
		return itens;
	}

	public List<LolRegioes> getRegioes() {
		List<LolRegioes> itens = new ArrayList<LolRegioes>();
		for (LolRegioes regiao : LolRegioes.values()) {
			itens.add(regiao);
		}
		return itens;
	}

}