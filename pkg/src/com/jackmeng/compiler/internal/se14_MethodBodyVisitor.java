package com.jackmeng.compiler.internal;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementScanner14;

import com.jackmeng.compiler.internal.markers.__METHODBODYVISITOR__;

public class se14_MethodBodyVisitor
    extends ElementScanner14< Void, StringBuilder >
    implements
    __METHODBODYVISITOR__
{
  @Override public Void visitExecutable(ExecutableElement e, StringBuilder modifiedMethod)
  {
    super.visitExecutable(e, modifiedMethod);
    StringBuilder sj = new StringBuilder(System.lineSeparator());
    e.getEnclosedElements().forEach(el -> sj.append(el.toString()));
    modifiedMethod.append(sj.toString());
    return null;
  }
}
