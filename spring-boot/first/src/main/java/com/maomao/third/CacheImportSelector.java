package com.maomao.third;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/9 19:35
 */
public class CacheImportSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        Map<String,Object> attributes =
                annotationMetadata.getAnnotationAttributes(EnableDefineService.class.getName());

        return new String[]{CacheService.class.getName()};
    }
}
