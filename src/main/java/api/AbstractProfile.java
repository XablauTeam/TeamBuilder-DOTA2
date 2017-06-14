package api;

import api.exceptions.ConnectionException;

public abstract class AbstractProfile {

	private String name;
	private long id;
	private long summonerLevel;

	public AbstractProfile(){}
	
	public AbstractProfile(String name, long id, long summonerLevel) {
		super();
		this.name = name;
		this.id = id;
		this.summonerLevel = summonerLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLevel() {
		return summonerLevel;
	}

	public void setLevel(long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	public abstract AbstractProfile byName(String[] strings) throws ConnectionException;
}
