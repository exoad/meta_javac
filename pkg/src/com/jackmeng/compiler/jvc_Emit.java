package com.jackmeng.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.jackmeng.jvc._Emits;

@SupportedAnnotationTypes("com.jackmeng.compiler._Emits") public final class jvc_Emit
    extends AbstractProcessor
{
  @Override public boolean process(Set< ? extends TypeElement > annotations, RoundEnvironment roundEnv)
  {
    for (Element element : roundEnv.getElementsAnnotatedWith(_Emits.class))
    {
      ExecutableElement method = (ExecutableElement) element;
      String methodName = method.getSimpleName().toString();
      String className = method.getEnclosingElement().toString();
      String warnMessage = "System.out.println(\"_EMITS_" + methodName + " in " + className + ": "
          + method.getAnnotation(_Emits.class).message() + "\");";
      StringBuilder modifiedMethod = new StringBuilder();
      modifiedMethod.append("public ").append(method.getReturnType()).append(" ").append(methodName).append("(");
      method.getParameters().forEach(
          param -> modifiedMethod.append(param.asType()).append(" ").append(param.getSimpleName()).append(","));
      if (method.getParameters().size() > 0)
      {
        modifiedMethod.deleteCharAt(modifiedMethod.length() - 1);
      }
      modifiedMethod.append(") {").append(System.lineSeparator());
      modifiedMethod.append(warnMessage).append(System.lineSeparator());
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Modified Method: " + modifiedMethod.toString());
    }
    return true;

  }
}