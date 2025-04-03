import java.util.Objects;

public record Triangle (double sideA, double sideB, double sideC) {

    public Triangle {
        if (sideA<=0 || sideB <=0 || sideC<=0 || sideA+sideB<sideC || sideB+sideC<sideA || sideC+sideA<sideB) throw new IllegalArgumentException("Некорректное значение стороны треугольника");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.sideA, sideA) == 0 && Double.compare(triangle.sideB, sideB) == 0 && Double.compare(triangle.sideC, sideC) == 0
                ||
                Double.compare(triangle.sideA, sideB) == 0 && Double.compare(triangle.sideB, sideC) == 0 && Double.compare(triangle.sideC, sideA) == 0
                ||
                Double.compare(triangle.sideB, sideC) == 0 && Double.compare(triangle.sideB, sideA) == 0 && Double.compare(triangle.sideA, sideB) == 0
                ||
                Double.compare(triangle.sideC, sideA) == 0 && Double.compare(triangle.sideA, sideB) == 0 && Double.compare(triangle.sideB, sideC) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    public double trianglePerimeter()
    {
        return this.sideA+this.sideB+this.sideC;
    }

    public double triangleArea()
    {
        double p = trianglePerimeter() / 2;
        return Math.sqrt(p *(p-this.sideA)*(p-this.sideB)*(p-this.sideC));
    }


}
