package Task3;

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