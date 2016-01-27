package ro.sok.geolocation.dao;

import java.util.List;

import ro.sok.geolocation.entity.GeoEntity;

public interface SearchDAO {

	void addEntry(GeoEntity ge);

	void reindex();

	List<GeoEntity> search(double latitude, double longitude, int firstResult, int maxResults, String key);

}
