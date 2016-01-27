package ro.sok.geolocation.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.annotations.Spatial;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.hibernate.search.spatial.DistanceSortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.sok.geolocation.entity.GeoEntity;

@Repository("search")
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GeoEntity> search(double latitude, double longitude,int firstResult, int maxResults, String key){
		List<GeoEntity> resultList = new ArrayList<>();
		FullTextSession searchSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder queryBuilder = searchSession.getSearchFactory().buildQueryBuilder().forEntity( GeoEntity.class ).get();
		BooleanJunction<BooleanJunction> bool = queryBuilder.bool();
		
		
		
		Query spatialLuceneQuery = queryBuilder.spatial()
				.within( 200, Unit.KM )
				.ofLatitude( latitude )
				.andLongitude( longitude )
				.createQuery();
		bool.must(spatialLuceneQuery);
		
		Query termLuceneQuery = queryBuilder
				  .keyword()
				  .onFields("value")
				  .matching(key)
				  .createQuery();
		bool.must(termLuceneQuery);
		
		Query both = bool.createQuery();
		
		FullTextQuery hibQuery = searchSession.createFullTextQuery( both, GeoEntity.class );
		hibQuery.setProjection( FullTextQuery.THIS, FullTextQuery.SPATIAL_DISTANCE );
		hibQuery.setSpatialParameters( latitude, longitude, Spatial.COORDINATES_DEFAULT_FIELD );
		Sort distanceSort = new Sort(
		    new DistanceSortField(latitude, longitude, Spatial.COORDINATES_DEFAULT_FIELD));
		hibQuery.setSort(distanceSort);
		hibQuery.setFirstResult(firstResult);
		hibQuery.setMaxResults(maxResults);
		List<?> tmpList = hibQuery.list();
		for ( Object obj[] : (List<Object[]>) tmpList ) {
			GeoEntity entity = (GeoEntity) obj[0];
			entity.setDistance( (Double) obj[1] );
			resultList.add( entity );
		}
		return resultList;
	}

	@Override
	public void addEntry(GeoEntity ge) {
		sessionFactory.getCurrentSession().persist(ge);
	}

	@Override
	public void reindex() {
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
