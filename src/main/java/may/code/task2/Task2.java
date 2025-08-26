package may.code.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task2 {
    public static void main(String[] args) throws IOException {
        List<String> pathCoordinatesOfCenterAndRadius = Files.readAllLines(Paths.get(args[0]));
        List<String> pathCoordinatesOfPoints = Files.readAllLines(Paths.get(args[1]));

        double centerX = Double.parseDouble(pathCoordinatesOfCenterAndRadius.get(0).split(" ")[0]);
        double centerY = Double.parseDouble(pathCoordinatesOfCenterAndRadius.get(0).split(" ")[1]);
        double radiusX = Double.parseDouble(pathCoordinatesOfCenterAndRadius.get(1).split(" ")[0]);
        double radiusY = Double.parseDouble(pathCoordinatesOfCenterAndRadius.get(1).split(" ")[1]);

        for (String coords : pathCoordinatesOfPoints) {
            double x = Double.parseDouble(coords.split(" ")[0]);
            double y = Double.parseDouble(coords.split(" ")[1]);

            double answer = calculateTheEllipseFormulla(x, centerX, radiusX, y, centerY, radiusY);

            if (answer == 1.0) {
                System.out.println(0);
            } else if (answer < 1.0) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }

    private static double calculateTheEllipseFormulla(double x,
                                                      double centerX,
                                                      double radiusX,
                                                      double y,
                                                      double centerY,
                                                      double radiusY) {

        return Math.pow((x - centerX) / radiusX, 2) + Math.pow((y - centerY) / radiusY, 2);
    }
}