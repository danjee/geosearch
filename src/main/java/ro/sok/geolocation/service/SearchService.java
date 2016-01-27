package ro.sok.geolocation.service;

import java.util.List;

import ro.sok.geolocation.entity.GeoEntity;

public interface SearchService {

	List<GeoEntity> search(double latitude, double longitude, String key);

	void addEntry(GeoEntity ge);

	void reindex();

}
