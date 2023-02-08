import com.jackmeng.jvc._Emits;

public final class test_Emits
{
  @_Emits(message = "Hello World") static void func()
  {
    return;
  }

  public static void main(String[] args)
  {
    func();
  }
}
