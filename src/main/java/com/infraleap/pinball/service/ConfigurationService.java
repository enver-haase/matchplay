package com.infraleap.pinball.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infraleap.pinball.data.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigurationService {
    @Value("classpath:data/config.json")
    private Resource resourceFile;

    public Configuration getConfiguration(){
        Configuration config;
        try {
            ObjectMapper mapper = new ObjectMapper();
            config = mapper.readValue(resourceFile.getInputStream(), Configuration.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return config;
    }
}
