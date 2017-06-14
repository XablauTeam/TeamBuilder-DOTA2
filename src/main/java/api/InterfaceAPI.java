package api;

import api.exceptions.ConnectionException;

public interface InterfaceAPI {
	public AbstractProfile getProfile(String summoner) throws ConnectionException;
}