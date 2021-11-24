import raytracer.*;
import raytracer.Point;
import raytracer.pigments.*;
import raytracer.shapes.Shape;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean DEBUG = false;
    public static boolean ANTI_ALIAS = false;
    public static boolean MULTI_THREAD = false;
    private static ArrayList<Light> lightsList = new ArrayList<>();
    private static ArrayList<Shape> shapesList = new ArrayList<>();
    private static final int row = 600;
    private static final int col = 600;

    public static void main(String[] args) {
        try {
            test01();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test01() throws Exception {
        // view
        Point eye = Api.createPoint(0,0,0);
        Point center = Api.createPoint(0,0,-1);
        Vector up = Api.createVector(0,1,0);
        int foxy = 30;
        Camera camera = Api.createCamera(eye, center, up, foxy, col, row);

        // lights
        Point location = Api.createPoint(0,0,0);
        Color color =  Color.magenta;
        AmbientLight ambientLight = Api.createAmbientLight(location, color,1,0,0);
        lightsList.add(ambientLight);

        Point location2 = Api.createPoint(10,100,10);
        Color color2 = Color.cyan;
        lightsList.add(Api.createLight(location2, color2, 1, 0, 0));

        Point location3 = Api.createPoint(100,100,100);
        Color color3 = Color.green;
        lightsList.add(Api.createLight(location3, color3, 1, 0, 0));

        //Pigments
        Pigment pigment0 = Api.createSolidPigment(Color.blue);
        Pigment pigment1 = Api.createSolidPigment(Color.darkGray);
        Pigment pigment2 = Api.createSolidPigment(Color.PINK);

        // Finishes
        Finish finish0 = Api.createFinish(0.4f ,0.6f, 0.0f ,1 , 0 ,0 ,0);
        Finish finish1 = Api.createFinish(0.4f ,0.6f, 0.7f ,500 , 0 ,0 ,0);

        // Shapes
        Point triangle1 = Api.createPoint(-4  , 3 , -13 );
        Point triangle2 = Api.createPoint(1  , 3 , -13 );
        Point triangle3 = Api.createPoint(2  , 1 , -13 );
        Point center2 = Api.createPoint(1  , 0 , -15 );
        Point center3 = Api.createPoint(5  , -5 , -25 );
        Point center4 = Api.createPoint(-5  , 0 , -30 );

        shapesList.add(Api.createSphere(center2, 2, pigment1, finish0));
        shapesList.add(Api.createSphere(center3, 3, pigment2, finish1));
        shapesList.add(Api.createSphere(center4, 4, pigment2, finish1));
        shapesList.add(Api.createTriangle(triangle1, triangle2,triangle3, pigment0, finish1));

        Api.render("testMain", "bmp", lightsList, shapesList, camera);
    }

}
