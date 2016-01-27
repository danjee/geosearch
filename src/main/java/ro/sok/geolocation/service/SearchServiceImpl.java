package ro.sok.geolocation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.sok.geolocation.dao.SearchDAO;
import ro.sok.geolocation.entity.GeoEntity;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDAO searchDAO;

	@Override
	public List<GeoEntity> search(double latitude, double longitude, String key) {
		return searchDAO.search(latitude, longitude, key);
	}

	@Override
	public void addEntry(GeoEntity ge) {
		searchDAO.addEntry(ge);
	}

	@Override
	public void reindex() {
		searchDAO.reindex();
	}
}
