package com.home.chat.configrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JsonLoader {
    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;

    public <T> T loadJson(String path, Class<T> clazz) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        return mapper.readValue(resource.getInputStream(), clazz);
    }
}
