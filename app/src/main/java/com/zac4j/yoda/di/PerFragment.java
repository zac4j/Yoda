package com.zac4j.yoda.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * A scoping annotation to permit objects with activity lifecycle
 * Created by zac on 16-7-3.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
