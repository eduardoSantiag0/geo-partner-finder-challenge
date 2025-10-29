package com.eduardoSantiag0.ze_code.application.services.mappers;

import com.eduardoSantiag0.ze_code.application.errors.InvalidGeoJsonException;
import com.fasterxml.jackson.databind.JsonNode;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Service;


@Service
public class GeoMapper {

    public static MultiPolygon convertCoverageToMultiPoligon(
            JsonNode coverageArea) {

        GeoJsonReader reader = new GeoJsonReader();

        try {
            return (MultiPolygon) reader.read(String.valueOf(coverageArea));
        } catch (ParseException e) {
            throw new InvalidGeoJsonException(e.getMessage());
        }

    }

    public static Point convertToPoint(JsonNode address) {

        GeoJsonReader reader = new GeoJsonReader();

        try {
            return (Point) reader.read(String.valueOf(address));
        } catch (Exception e) {
            throw new InvalidGeoJsonException(e.getMessage());
        }
    }

}
