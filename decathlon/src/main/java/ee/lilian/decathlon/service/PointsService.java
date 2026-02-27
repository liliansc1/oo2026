package ee.lilian.decathlon.service;

import ee.lilian.decathlon.entity.Event;
import org.springframework.stereotype.Service;

@Service
public class PointsService {

    public int calculatePoints(Event event, double value) {
        return switch (event) {
            case M100 -> (int) Math.floor(25.4347 * Math.pow(18 - value, 1.81));
            case LONG_JUMP -> (int) Math.floor(0.14354 * Math.pow(value * 100 - 220, 1.4)); // m -> cm
            case SHOT_PUT -> (int) Math.floor(51.39 * Math.pow(value - 1.5, 1.05));
            case HIGH_JUMP -> (int) Math.floor(0.8465 * Math.pow(value * 100 - 75, 1.42)); // m -> cm
            case M400 -> (int) Math.floor(1.53775 * Math.pow(82 - value, 1.81));
            case HURDLES_110 -> (int) Math.floor(5.74352 * Math.pow(28.5 - value, 1.92));
            case DISCUS_THROW -> (int) Math.floor(12.91 * Math.pow(value - 4, 1.1));
            case POLE_VAULT -> (int) Math.floor(0.2797 * Math.pow(value * 100 - 100, 1.35)); // m -> cm
            case JAVELIN_THROW -> (int) Math.floor(10.14 * Math.pow(value - 7, 1.08));
            case M1500 -> (int) Math.floor(0.03768 * Math.pow(480 - value, 1.85));
        };
    }
}
