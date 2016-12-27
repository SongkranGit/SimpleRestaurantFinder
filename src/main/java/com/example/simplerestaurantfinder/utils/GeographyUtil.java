package com.example.simplerestaurantfinder.utils;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * Created by BERM-PC on 25/12/2559.
 */
public class GeographyUtil {

    public  static Point createPoint(double latitude, double longitude){
      // Point point = new Point()
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint( new Coordinate( latitude, longitude ) );
        return  point;
    }

//    public static Geometry createGeometryFromGeoJson(double latitude , double longitude) throws JsonParseException, IOException {
//
//        String geoJsonString = "{\"type\":\"Point\",\"coordinates\":["+latitude+","+longitude+"],\"crs\":\"EPSG:4326\"}";
//
//        MapGeometry mapGeom = OperatorImportFromGeoJson.local().execute(GeoJsonImportFlags.geoJsonImportDefaults, Geometry.Type.Point, geoJsonString, null);
//
//        return mapGeom.getGeometry();
//    }
//
//    public static Point createPointFromJson(double latitude , double longitude) throws JsonParseException, IOException {
//
//        String jsonString = "{\"x\":"+latitude+",\"y\":"+longitude+",\"spatialReference\":{\"wkid\":4326}}";
//
//        //String jsonString = "{\"x\":-106.4453583,\"y\":39.11775,\"spatialReference\":{\"wkid\":4326}}";
//
//        MapGeometry mapGeom = OperatorImportFromJson.local().execute(Geometry.Type.Point, jsonString);
//
//        return (Point)mapGeom.getGeometry();
//    }

}
