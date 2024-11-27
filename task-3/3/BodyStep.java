package Task3;

class BodyStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Body");
        return new TankBody();
    }
}