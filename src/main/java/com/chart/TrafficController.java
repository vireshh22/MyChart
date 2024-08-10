package com.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chart.modal.TrafficData;
import com.chart.repositories.TrafficRepository;

import java.util.List;

@Controller
@RequestMapping("/traffic")
@CrossOrigin(origins = "http://localhost:8081")
public class TrafficController {

    @Autowired
    private TrafficRepository trafficRepository;

    @GetMapping
    public String index(Model model) {
        List<TrafficData> trafficDataList = trafficRepository.findAll();
        model.addAttribute("trafficDataList", trafficDataList);
        return "index1";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<TrafficData> getAllTrafficData() {
        return trafficRepository.findAll();
    }

    @PostMapping("/data")
    @ResponseBody
    public TrafficData createTrafficData(@RequestBody TrafficData trafficData) {
        return trafficRepository.save(trafficData);
    }

    @PutMapping("/data/{id}")
    @ResponseBody
    public TrafficData updateTrafficData(@PathVariable Long id, @RequestBody TrafficData updatedTrafficData) {
        return trafficRepository.findById(id)
                .map(trafficData -> {
                    trafficData.setSource(updatedTrafficData.getSource());
                    trafficData.setCount(updatedTrafficData.getCount());
                    return trafficRepository.save(trafficData);
                }).orElseGet(() -> {
                    updatedTrafficData.setId(id);
                    return trafficRepository.save(updatedTrafficData);
                });
    }

    @DeleteMapping("/data/{id}")
    @ResponseBody
    public void deleteTrafficData(@PathVariable Long id) {
        trafficRepository.deleteById(id);
    }
}
