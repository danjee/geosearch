package ro.sok.geolocation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import org.hibernate.search.annotations.Spatial;
import org.hibernate.search.annotations.SpatialMode;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Spatial(spatialMode = SpatialMode.RANGE)
public class GeoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	private Long id;
	@Latitude
	private Double lat;
	@Longitude
	private Double lon;
	@Transient
	private Double distance;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String value;

	public GeoEntity() {
	}

	public GeoEntity(Double lat, Double lon, String value) {
		super();
		this.value = value;
		this.lat = lat;
		this.lon = lon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}