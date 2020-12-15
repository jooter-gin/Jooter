package sample;

public class ScooterManagement {

    public static boolean addScooter (Scooter scooter) {

       return DataSource.getInstance().insertScooters(scooter);

    }

    public static void deleteScooter (int id) {

        DataSource.getInstance().deleteScooter(id);
    }

}
