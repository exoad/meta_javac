package com.jackmeng.compiler;

import javax.lang.model.util.ElementScanner6;

import com.jackmeng.compiler.internal.se08_MethodBodyVisitor;
import com.jackmeng.compiler.internal.se09_MethodBodyVisitor;
import com.jackmeng.compiler.internal.se14_MethodBodyVisitor;
import com.jackmeng.jmcc.const_BaseVersion;

final class use_JMCCHelper
{
  private use_JMCCHelper()
  {
  }

  public static ElementScanner6< Void, StringBuilder > method_body_visitor(const_BaseVersion e)
  {
    switch (e) {
      case SE08:
        return new se08_MethodBodyVisitor();
      case SE09:
        return new se09_MethodBodyVisitor();
      case SE14:
        return new se14_MethodBodyVisitor();
      default:
        return new se08_MethodBodyVisitor();
    }
  }
}
