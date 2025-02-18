package Task3;

class EngineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Engine");
        return new TankEngine();
    }
}