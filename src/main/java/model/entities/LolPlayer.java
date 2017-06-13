package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import business.lol.LolRegioes;
import business.lol.LolRole;

@Entity
@Table(name="LOLPLAYER")
public class LolPlayer extends AbstractPlayer {
	
	@Column(name = "lolrole")
	@Enumerated(EnumType.STRING)
	private LolRole role;
	
	@Column(name = "lolregion")
	@Enumerated(EnumType.STRING)
	private LolRegioes region;

	
	public LolRole getRole() {
		return role;
	}

	public void setRole(LolRole role) {
		this.role = role;
	}

	public LolRegioes getRegion() {
		return region;
	}

	public void setRegion(LolRegioes region) {
		this.region = region;
	}

	
	
	
}
