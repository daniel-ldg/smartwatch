/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class SmartWatchApi {
    
    private String serverUrl;
    private HttpCredentials credentials;
    private ObjectMapper mapper;

    public SmartWatchApi(String serverUrl, String username, String password) {
        this.serverUrl = serverUrl;
        this.credentials = new HttpCredentials(username, password);
        this.mapper = new ObjectMapper();
    }
    
    public TrainingData sendEntrenamiento(TrainingData entrenamiento) throws ApiException {
        try {
            
            String json = this.mapper.writeValueAsString(entrenamiento);
            
            String responseJson = HttpClient.postRequestJson(
                    this.serverUrl + "api/smartwatch",
                    this.credentials,
                    json);
            
            return this.mapper.readValue(responseJson, TrainingData.class);
            
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }

}
