package com.example.vn008xw.reddit.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Qualifier defining a data source as being remote
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Remote {
}