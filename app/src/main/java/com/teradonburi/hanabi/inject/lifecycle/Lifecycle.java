package com.teradonburi.hanabi.inject.lifecycle;

/**
 * Created by daiki on 2017/09/03.
 */

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
public @interface Lifecycle {
}
