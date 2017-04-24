package us.bojie.appstorebo.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
