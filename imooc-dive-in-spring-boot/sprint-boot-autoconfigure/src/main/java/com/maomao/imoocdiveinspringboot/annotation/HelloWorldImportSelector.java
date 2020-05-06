package com.maomao.imoocdiveinspringboot.annotation;

import com.maomao.imoocdiveinspringboot.configyration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/14 17:52
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                HelloWorldConfiguration.class.getName()
        };
    }
}
