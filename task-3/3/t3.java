package Task33;

interface IAssemblyLine {
    IProduct assembleProduct(IProduct product);
}

interface IProduct {
    void installFirstPart(IProductPart part);
    void installSecondPart(IProductPart part);
    void installThirdPart(IProductPart part);
}

interface IProductPart {

}

interface ILineStep {
    IProductPart buildProductPart();
}


class TankBody implements IProductPart {

}

class TankEngine implements IProductPart {

}

class TankTurret implements IProductPart {

}


class BodyStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Body");
        return new TankBody();
    }
}

class EngineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Engine");
        return new TankEngine();
    }
}

class TurretStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Turret");
        return new TankTurret();
    }
}


class Tank implements IProduct {
    @Override
    public void installFirstPart(IProductPart part) {
        System.out.println("Installing Tank Body");
    }

    @Override
    public void installSecondPart(IProductPart part) {
        System.out.println("Installing Tank Engine");
    }

    @Override
    public void installThirdPart(IProductPart part) {
        System.out.println("Installing Tank Turret");
    }
}


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

public class t3 {
    public static void main(String[] args) {
        ILineStep bodyStep = new BodyStep();
        ILineStep engineStep = new EngineStep();
        ILineStep turretStep = new TurretStep();

        IAssemblyLine assemblyLine = new TankAssemblyLine(bodyStep, engineStep, turretStep);
        IProduct tank = new Tank();

        assemblyLine.assembleProduct(tank);
    }
}
