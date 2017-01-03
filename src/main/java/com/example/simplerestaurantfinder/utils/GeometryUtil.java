package com.example.simplerestaurantfinder.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;

/**
 * Created by BERM-PC on 3/1/2560.
 */
public class GeometryUtil {

    public static Geometry createCircle(double x, double y, final double RADIUS) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(x, y));//there are your coordinates
        shapeFactory.setSize(RADIUS * 2);//this is how you set the radius
        return shapeFactory.createCircle();
    }
}
