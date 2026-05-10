/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

/**
 *
 * @author aleksandra
 */
@Service
public class LambdaStatisticsServ {

    //Подсчёт по возрасту с условием (older, younger, between, outside)
    public long countByAgeCondition(List<Viking> vikings, int age, String condition) {
        return switch (condition.toLowerCase()) {
            case "older" -> vikings.stream().filter(v -> v.getAge() > age).count();
            case "younger" -> vikings.stream().filter(v -> v.getAge() < age).count();
            case "between" -> vikings.stream().filter(v -> v.getAge() >= age && v.getAge() <= age + 10).count();
            case "outside" -> vikings.stream().filter(v -> v.getAge() < age || v.getAge() > age + 10).count();
            default -> 0;
        };
    }

    //Подсчёт по бороде И цвету волос
    public long countByBeardAndHair(List<Viking> vikings, BeardStyle beard, HairColor hair) {
        return vikings.stream()
                .filter(v -> v.getBeardStyle() == beard && v.getHairColor() == hair)
                .count();
    }

    //Количество викингов, имеющих ровно axesCount топоров (в названии есть "Axe" без учёта регистра)
    public long countByExactAxes(List<Viking> vikings, int axesCount) {
        return vikings.stream()
                .filter(v -> countAxesInEquipment(v.getEquipment()) == axesCount)
                .count();
    }

    private long countAxesInEquipment(List<EquipmentItem> equipment) {
        return equipment.stream()
                .filter(item -> item.name().toLowerCase().contains("axe"))
                .count();
    }

    //Случайный викинг выше заданного роста
    public Viking pickRandomTallViking(List<Viking> vikings, int minHeight) {
        List<Viking> tall = vikings.stream()
                .filter(v -> v.getHeightCm() > minHeight)
                .toList();
        if (tall.isEmpty()) return null;
        Random rand = new Random();
        return tall.get(rand.nextInt(tall.size()));
    }

    //Все викинги с легендарным снаряжением
    public List<Viking> extractLegendaryArmed(List<Viking> vikings) {
        return vikings.stream()
                .filter(v -> v.getEquipment().stream()
                        .anyMatch(eq -> "Legendary".equalsIgnoreCase(eq.quality())))
                .toList();
    }

    //Рыжебородые, отсортированные по возрасту
    public List<Viking> getRedBeardedSortedByAge(List<Viking> vikings) {
        return vikings.stream()
                .filter(v -> v.getBeardStyle() == BeardStyle.LONG && v.getHairColor() == HairColor.Red)
                .sorted(Comparator.comparingInt(Viking::getAge))
                .toList();
    }

    //Максимальный ID (последняя запись)
    public Long findMaxId(List<Viking> vikings) {
        return vikings.stream()
                .map(Viking::getId)
                .filter(Objects::nonNull)
                .max(Long::compareTo)
                .orElse(-1L);
    }

    //Все чётные ID
    public List<Long> getEvenIds(List<Viking> vikings) {
        return vikings.stream()
                .map(Viking::getId)
                .filter(id -> id != null && id % 2 == 0)
                .toList();
    }  
}
