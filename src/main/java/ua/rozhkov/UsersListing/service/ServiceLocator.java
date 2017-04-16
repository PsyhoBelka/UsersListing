package ua.rozhkov.UsersListing.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
	
	private static ServiceLocator serviceLocator;
	Map<Class<?>,Object> servicesMap = new HashMap<>();
	
	public static ServiceLocator getInstance (){
		if (serviceLocator==null){
			serviceLocator=new ServiceLocator();
		}
		return serviceLocator;
	}
	
	public <T> void register(Class<T> clazz, T service ){
		servicesMap.put(clazz,service);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> clazz){
		return (T) servicesMap.get(clazz);
	}
}
