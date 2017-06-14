package business.dota;

public enum DotaRole {
	OFFLANE("OFFLANE"),
	JUNGLER("JUNGLER"),
	MID("MID"),
	SUPPORT("SUPPORT"),
	CARRY("CARRY");
	
	private final String name;
	
	private DotaRole(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	
}
