package io.nlopez.smartadapters.builders;

import android.content.Context;

import java.lang.reflect.Method;

import io.nlopez.smartadapters.utils.Mapper;
import io.nlopez.smartadapters.utils.Reflections;
import io.nlopez.smartadapters.views.BindableLayout;

public class AABindableLayoutBuilder extends BindableLayoutBuilder {

    public AABindableLayoutBuilder(Mapper mapper) {
        super(mapper);
    }

    @Override
    public BindableLayout build(Context context, Class aClass, Object item) {
        try {
            Class modelClass = (item == null) ? aClass : item.getClass();
            Class viewClass = getMapper().asMap().get(modelClass);
            Method method = Reflections.method(viewClass, "build", Context.class);
            return (BindableLayout) method.invoke(null, context);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong creating the views", e);
        }
    }
}