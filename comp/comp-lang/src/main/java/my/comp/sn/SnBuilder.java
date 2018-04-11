package my.comp.sn;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import my.comp.lang.StringUtils;

public class SnBuilder
{
  private String separator = "-";
  private boolean lowerCase = false;
  private List<String> elements;
  
  protected SnBuilder()
  {
    this.elements = new ArrayList();
  }
  
  public static SnBuilder create(Object... args)
  {
    SnBuilder sn = new SnBuilder();
    for (Object arg : args) {
      sn.append(arg);
    }
    return sn;
  }
  
  public SnBuilder append(Object s)
  {
    this.elements.add(s.toString());
    return this;
  }
  
  public SnBuilder setSeparator(String separator)
  {
    this.separator = separator;
    return this;
  }
  
  public boolean isLowerCase()
  {
    return this.lowerCase;
  }
  
  public SnBuilder setLowerCase(boolean lowerCase)
  {
    this.lowerCase = lowerCase;
    return this;
  }
  
  public String getSeparator()
  {
    return this.separator;
  }
  
  public String toString()
  {
    String s = StringUtils.join(this.elements, this.separator);
    if (this.lowerCase) {
      s = s.toLowerCase();
    }
    return s;
  }
  
  public static void main(String[] args)
  {
    SnBuilder sn = create(new Object[] { "a", "b", "c" }).append("d").setSeparator(".");
    System.out.println(sn);
  }
}
