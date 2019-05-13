package com.dfjinxin.cache;

import com.dfjinxin.cache.config.AutoCacheConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ace
 * @create 2017/11/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoCacheConfiguration.class)
@Documented
@Inherited
public @interface EnabledfjinxinCache {

}
