import com.jackmeng.jmcc._Debug;

public final class test_Emits
{
  @_Debug(message = "Hello World") static void func()
  {
    return;
  }

  public static void main(String[] args)
  {
    func();
  }
}
