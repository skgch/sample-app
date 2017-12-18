package com.sample.helper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

public class ApplicationDialect extends AbstractDialect implements IExpressionEnhancingDialect {

    private static final Map<String, Object> EXPRESSION_OBJECTS;

    static {
      Map<String, Object> objects = new HashMap<>();
      objects.put("applicationHelper", new ApplicationUtility());
      EXPRESSION_OBJECTS = Collections.unmodifiableMap(objects);
    }

    @Override
    public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
      return EXPRESSION_OBJECTS;
    }

    @Override
    public String getPrefix() {
        return null;
    }

  }