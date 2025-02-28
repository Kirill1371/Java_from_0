package Task3;


public class Task3 {
    public static void main(String[] args) {
        ILineStep bodyStep = new BodyStep();
        ILineStep engineStep = new EngineStep();
        ILineStep turretStep = new TurretStep();

        IAssemblyLine assemblyLine = new TankAssemblyLine(bodyStep, engineStep, turretStep);
        IProduct tank = new Tank();

        assemblyLine.assembleProduct(tank);
    }
}
