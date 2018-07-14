package com.github.gpor89.masters.soap;

import com.github.gpor89.masters.data.MemCache;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Initializer {

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        MemCache.load(10000);
    }

}
