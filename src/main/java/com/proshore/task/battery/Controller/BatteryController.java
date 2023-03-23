package com.proshore.task.battery.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proshore.task.battery.Battery;
import com.proshore.task.battery.Repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    @Autowired
    BatteryRepository batteryRepository;

    @PostMapping
    public ResponseEntity<?> addBatteries(@RequestBody(required = false) List<Battery> batteries) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Battery> savedBatteries = null;
        if (batteries != null && !batteries.isEmpty()) {
            savedBatteries = batteryRepository.saveAll(batteries);
        } else {
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/proshore/task/battery/Data/Data.json");
            List<Battery> batteryList = objectMapper.readValue(inputStream, new TypeReference<List<Battery>>() {});
            savedBatteries = batteryRepository.saveAll(batteryList);
        }
        return ResponseEntity.ok(savedBatteries);
    }
    @GetMapping("/get-postcode/{postcodeRange}")
    public ResponseEntity<?> getBatteriesWithinPostcodeRange(@PathVariable String postcodeRange) {
        String[] postcodes = postcodeRange.split("-");
        String startPostcode = postcodes[0];
        String endPostcode = postcodes[1];
        List<Battery> batteriesWithinRange = batteryRepository.findByPostcodeBetween(startPostcode, endPostcode);
        List<String> batteryNames = batteriesWithinRange.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());
        int totalWattCapacity = batteriesWithinRange.stream()
                .mapToInt(Battery::getCapacity)
                .sum();

        double averageWattCapacity = batteriesWithinRange.stream()
                .mapToInt(Battery::getCapacity)
                .average()
                .orElse(0);

        Map<String, Object> response = new HashMap<>();
        response.put("batteryNames", batteryNames);
        response.put("totalWattCapacity", totalWattCapacity);
        response.put("averageWattCapacity", averageWattCapacity);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(response);
    }
}

