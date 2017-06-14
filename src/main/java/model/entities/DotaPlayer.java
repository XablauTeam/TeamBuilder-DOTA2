package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import business.dota.DotaRole;
import business.dota.LolRegioes;

@Entity
@Table(name="DOTAPLAYER")
public class DotaPlayer extends AbstractPlayer {
	
	@Column(name = "dotarole")
	@Enumerated(EnumType.STRING)
	private DotaRole role;
	
	@Column(name = "dotaregion")
	@Enumerated(EnumType.STRING)
	private LolRegioes region;

	
	public DotaRole getRole() {
		return role;
	}

	public void setRole(DotaRole role) {
		this.role = role;
	}

	public LolRegioes getRegion() {
		return region;
	}

	public void setRegion(LolRegioes region) {
		this.region = region;
	}

	
	
	
}
