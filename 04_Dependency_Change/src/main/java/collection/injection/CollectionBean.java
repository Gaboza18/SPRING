package collection.injection;

import java.util.*;

public class CollectionBean {

	private List<String> addressList;
	private Set<String> movieList;
	private Map<String, String> prefList;
	private Properties propList;

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public Set<String> getMovieList() {
		return movieList;
	}

	public void setMovieList(Set<String> movieList) {
		this.movieList = movieList;
	}

	public Map<String, String> getPrefList() {
		return prefList;
	}

	public void setPrefList(Map<String, String> prefList) {
		this.prefList = prefList;
	}

	public Properties getPropList() {
		return propList;
	}

	public void setPropList(Properties propList) {
		this.propList = propList;
	}

}
