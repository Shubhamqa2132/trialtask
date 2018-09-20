/**
 * 
 */
package testproject.qa.utils;

import java.util.List;

import org.testng.Reporter;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

/**
 * @author utripathi
 *
 */
public class GeocodeClient {

	public String getCountry(String zip) throws Exception {
        
		Geocoder geocoder = new Geocoder();
		//System.out.println("abc");
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(zip).getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		List<GeocoderResult> results = geocoderResponse.getResults();
		//System.out.println("GeocoderResult::"+results);
		List<GeocoderAddressComponent> addressComponents = results.get(0).getAddressComponents();
		//System.out.println("addressComponents::"+addressComponents);
		String country = addressComponents.get(5).getLongName();
		Reporter.log("<br>getCountry::" + country, true);
		return country;
	}
}
