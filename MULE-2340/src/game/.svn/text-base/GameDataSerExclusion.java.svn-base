package game;

import java.util.Timer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GameDataSerExclusion implements ExclusionStrategy {

	@Override
	/**
	 * 
	 */
	public boolean shouldSkipClass(Class<?> arg0) {
		return (arg0.getName() == "Timer");
	}

	/**
	 * 
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		return (arg0.getName() == "timer");
	}

}
