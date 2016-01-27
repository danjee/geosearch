package ro.sok.geolocation.service;

import java.util.List;

import ro.sok.geolocation.entity.GeoEntity;

public interface SearchService {

		void addEntry(GeoEntity ge);

	void reindex();

	List<GeoEntity> search(double latitude, double longitude, int firstResult, int maxResults, String key);

}
