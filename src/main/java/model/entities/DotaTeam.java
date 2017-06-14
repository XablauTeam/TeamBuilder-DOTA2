package model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dotateam")
public class DotaTeam extends AbstractTeam {
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "offlane")
	private DotaPlayer offlane;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jungler")
	private DotaPlayer jungler;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mid")
	private DotaPlayer mid;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "support")
	private DotaPlayer support;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "carry")
	private DotaPlayer carry;
	
	public DotaTeam(){
		super();
	}

	public DotaPlayer getOfflane() {
		return offlane;
	}

	public void setOfflane(DotaPlayer top) {
		this.offlane = top;
	}

	public DotaPlayer getJungler() {
		return jungler;
	}

	public void setJungler(DotaPlayer jungler) {
		this.jungler = jungler;
	}

	public DotaPlayer getMid() {
		return mid;
	}

	public void setMid(DotaPlayer mid) {
		this.mid = mid;
	}

	public DotaPlayer getSupport() {
		return support;
	}

	public void setSupport(DotaPlayer support) {
		this.support = support;
	}

	public DotaPlayer getCarry() {
		return carry;
	}

	public void setCarry(DotaPlayer bottom) {
		this.carry = bottom;
	}
}