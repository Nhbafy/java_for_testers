public record Triangle (double sideA,double sideB, double sideC) {

    public Triangle {
        if (sideA<=0 || sideB <=0 || sideC<=0 || sideA+sideB<sideC || sideB+sideC<sideA || sideC+sideA<sideB) throw new IllegalArgumentException("Некорректное значение стороны треугольника");
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
