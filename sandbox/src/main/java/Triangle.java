public record Triangle (double sideA,double sideB, double sideC) {

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
