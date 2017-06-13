package model.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import business.PlayerStatus;


@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_player")
	private Integer idPlayer;
	
	@Column(name = "playerName")
	private String playerName;
	
	@Column(name = "playerLevel")
	private long playerLevel;
	
	@Column(name = "playerStatus")
	@Enumerated(EnumType.STRING)
	private PlayerStatus status;
	
	//Identificado do player no jogo
	@Column(name="gamePlayerID")
	private long gamePlayerID;
	
	@Column(name="teamID")
	private Integer teamID = 0;
	
	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public long getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(long playerLevel) {
		this.playerLevel = playerLevel;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public long getGamePlayerID() {
		return gamePlayerID;
	}

	public void setGamePlayerID(long gamePlayerID) {
		this.gamePlayerID = gamePlayerID;
	}

	public Integer getTeamID() {
		return teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	
	
}