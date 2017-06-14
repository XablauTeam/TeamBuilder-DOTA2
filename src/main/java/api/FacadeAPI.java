package api;

import api.AbstractProfile;
import api.dota2.dto.DotaProfile;
import api.exceptions.ConnectionException;

public class FacadeAPI implements InterfaceAPI {
	
	private AbstractProfile profile;

	public FacadeAPI()
	{
		profile = new DotaProfile();
	}

	public AbstractProfile getProfile(String summoner) throws ConnectionException
	{
		return profile.byName(new String[]
		{ summoner });
	}
	
}