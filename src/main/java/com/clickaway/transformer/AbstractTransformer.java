package com.clickaway.transformer;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

abstract class AbstractTransformer {

    protected URI createUri(Long id, String entityType) {
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().build();
        List<String> pathSegments = uriComponents.getPathSegments();

        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.newInstance();
        uriBuilder.scheme(uriComponents.getScheme())
                .host(uriComponents.getHost())
                .port(uriComponents.getPort())
                .pathSegment("api", "v1", entityType)
                .build();

        return uriBuilder.path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
