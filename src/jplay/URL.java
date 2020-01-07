package jplay;

public class URL
{
  private static final String DIR = "src/recursos/";
  private static final String SEPARADOR = "/";
  public URL() {}
  public static String tile(String arquivo)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("src/recursos/");
    builder.append("tiles");
    builder.append("/");
    builder.append(arquivo);
    return builder.toString();
  }

  public static String sprite(String arquivo)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("src/recursos/");
    builder.append("sprites");
    builder.append("/");
    builder.append(arquivo);
    return builder.toString();
  }

  public static String audio(String arquivo)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("src/recursos/");
    builder.append("audio");
    builder.append("/");
    builder.append(arquivo);
    return builder.toString();
  }

  public static String scenario(String arquivo)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("src/recursos/");
    builder.append("scn");
    builder.append("/");
    builder.append(arquivo);
    return builder.toString();
  }
  
  public static String Animation(String arquivo)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("src/recursos/");
    builder.append("Animacoes");
    builder.append("/");
    builder.append(arquivo);
    return builder.toString();
  }
}
