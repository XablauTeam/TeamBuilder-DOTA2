package api.smite.dto;

import api.AbstractProfile;
import api.exceptions.ConnectionException;
import api.smite.SmiteAPI;

public class SmiteProfile extends AbstractProfile
{
	public SmiteProfile()
	{
		super();
	}

	public SmiteProfile(String name, long id, long level)
	{
		super(name, id, level);
	}

	public AbstractProfile byName(String[] strings, String region) throws ConnectionException
	{
		return SmiteAPI.getInstance().buildSmiteProfile(strings[0]);
	}
}
