package Task3;

class TankAssemblyLine implements IAssemblyLine {
    private ILineStep bodyStep;
    private ILineStep engineStep;
    private ILineStep turretStep;

    public TankAssemblyLine(ILineStep bodyStep, ILineStep engineStep, ILineStep turretStep) {
        this.bodyStep = bodyStep;
        this.engineStep = engineStep;
        this.turretStep = turretStep;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        System.out.println("Starting assembly of the tank");

        IProductPart body = bodyStep.buildProductPart();
        product.installFirstPart(body);

        IProductPart engine = engineStep.buildProductPart();
        product.installSecondPart(engine);

        IProductPart turret = turretStep.buildProductPart();
        product.installThirdPart(turret);

        System.out.println("Tank assembly complete");
        return product;
    }
}