package challenge2.clase;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class moneda {

    

    private String result;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;
    @SerializedName("base_code")
    private String baseCode;

    public String getResult() {
        return result;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
    
    

    
}
