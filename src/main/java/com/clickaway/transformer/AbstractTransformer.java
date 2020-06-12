package com.clickaway.transformer;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

abstract class AbstractTransformer {
    protected URI createUri(Long id, String entityType) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        URI uri = builder.build().toUri();
        // path_segments = api,v1,campaign,1
        List<String> path_segments = builder.build().getPathSegments();
        if (path_segments.size() <= 3) {
            uri = builder.path("/{id}")
                    .buildAndExpand(id)
                    .toUri();
        }
        return uri;
    }

}
