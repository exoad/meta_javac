package com.jackmeng.compiler;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.jackmeng.jmcc._ReadOnly;

public class jmcc_ReadOnly
    extends AbstractProcessor
{
  @Override public boolean process(Set< ? extends TypeElement > annotations, RoundEnvironment roundEnv)
  {
    for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(_ReadOnly.class))
    {
      if (annotatedElement.getKind() != ElementKind.FIELD)
        throw new IllegalArgumentException("@ReadOnly can only be applied to fields");
      TypeElement classElement = (TypeElement) annotatedElement.getEnclosingElement();
      List< ? extends Element > enclosedElements = classElement.getEnclosedElements();
      for (Element enclosedElement : enclosedElements)
      {
        if (enclosedElement.getKind() == ElementKind.METHOD)
        {
          ExecutableElement method = (ExecutableElement) enclosedElement;
          List< ? extends VariableElement > parameters = method.getParameters();
          for (VariableElement parameter : parameters)
            if (parameter.asType().toString().equals("java.util.List")
                && parameter.getSimpleName().toString().equals(annotatedElement.getSimpleName().toString()))
              if (!method.getModifiers().contains(Modifier.FINAL))
                throw new IllegalStateException("List " + annotatedElement.getSimpleName()
                    + " is annotated as @ReadOnly but is being modified in " + method.getSimpleName());
        }
      }
    }
    return true;
  }
}
