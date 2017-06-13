package business.lol;

public enum LolRole {
	TOP("TOP"),
	JUNGLER("JUNGLER"),
	MID("MID"),
	SUPPORT("SUPPORT"),
	BOTTOM("BOTTOM");
	
	private final String name;
	
	private LolRole(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	
}
