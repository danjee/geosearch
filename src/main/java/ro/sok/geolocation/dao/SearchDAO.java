package ro.sok.geolocation.dao;

import java.util.List;

import ro.sok.geolocation.entity.GeoEntity;

public interface SearchDAO {

	List<GeoEntity> search(double latitude, double longitude, String key);

	void addEntry(GeoEntity ge);

	void reindex();

}
