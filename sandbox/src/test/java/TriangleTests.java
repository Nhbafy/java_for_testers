import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void checkPerimeter()
    {
        Assertions.assertEquals(35,new Triangle(12.0,15.0,8.0).trianglePerimeter());
    }

    @Test
    void checkArea()
    {
        Assertions.assertEquals(10.928746497197197,new Triangle(4.0,10.0,7.0).triangleArea());
    }
}
