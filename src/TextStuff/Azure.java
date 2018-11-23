package TextStuff;
import java.math.BigDecimal;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Azure {
	
    private static final String subscriptionKey = "fb2468dd4de448cd9a851f36365b8217";
    private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze";
    private static String imageToAnalyze;
    private JSONArray tags;
    private BigDecimal confidence;
    
    public Azure(String image) {
    	
    	imageToAnalyze = image;
    	CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
        	
            URIBuilder builder = new URIBuilder(uriBase);
            builder.setParameter("visualFeatures", "Tags");
            builder.setParameter("language", "en");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            StringEntity requestEntity = new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);
          
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                //JSONObject description = json.getJSONObject("description");
                //tags = description.getJSONArray("tags");
                //confidence = ((JSONObject) description.getJSONArray("captions").get(0)).getBigDecimal("confidence");
                System.out.println(json.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	public JSONArray getTags() {
		return tags;
	}

	public BigDecimal getConfidence() {
		return confidence;
	}
}
