package example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;






@Path("/")
public class HelloWorld {

  @GET
  @Path("test/")
  @Produces(MediaType.TEXT_XML)
  public String sayHelloWorldFrom(@QueryParam("s") String from) {

    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }
}
