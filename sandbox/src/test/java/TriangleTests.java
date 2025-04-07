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

    @Test
    void negativeSideA(){
        try {
            new Triangle(-4.0,10.0,7.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }
    @Test
    void negativeSideB(){
        try {
            new Triangle(4.0,-10.0,7.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }

    @Test
    void negativeSideC(){
        try {
            new Triangle(4.0,10.0,-7.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }

    @Test
    void negativeSummSides1_2(){
        try {
            new Triangle(4.0,1.0,7.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }

    @Test
    void negativeSummSides2_3(){
        try {
            new Triangle(4.0,1.0,2.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }

    @Test
    void negativeSummSides3_1(){
        try {
            new Triangle(1.0,5.0,1.0).triangleArea();
        }
        catch (IllegalArgumentException e){}
    }

    @Test
    void compareEquality(){
        var tr1 = new Triangle(3.0, 2.0, 5.0);
        var tr2 = new Triangle(2.0, 5.0, 3.0);
        Assertions.assertEquals(tr1,tr2);
    }

    @Test
    void testEquality2(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(a, c, b);
        Assertions.assertEquals(triangle, triangle1);
    }
}
