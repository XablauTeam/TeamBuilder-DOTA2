package api;

import api.exceptions.ConnectionException;
import api.smite.SmiteProfile;

public class FacadeAPI implements InterfaceAPI {
	
	private SmiteProfile profile;

	public FacadeAPI()
	{
		profile = new SmiteProfile();
	}

	public AbstractProfile getProfile(String summoner, String region) throws ConnectionException
	{
		return profile.byName(new String[] { summoner }, region);
	}
	
}