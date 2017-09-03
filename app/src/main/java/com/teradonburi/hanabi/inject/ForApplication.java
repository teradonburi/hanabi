package com.teradonburi.hanabi.inject;

/**
 * Created by daiki on 2017/09/03.
 */

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Qualifier
public @interface ForApplication {
}
