package api;

import api.exceptions.ConnectionException;

public interface InterfaceAPI {
	public AbstractProfile getProfile(String summoner, String region) throws ConnectionException;
}