package com.teradonburi.hanabi.inject.lifecycle;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by daiki on 2017/09/03.
 */


@Retention(RUNTIME)
@Qualifier
public @interface ForActivity {
}
