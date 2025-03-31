package Task3;

class TurretStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building Tank Turret");
        return new TankTurret();
    }
}