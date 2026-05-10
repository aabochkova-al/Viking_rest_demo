package ru.mephi.vikingdemo.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель викинга")
public class Viking{
        
        @Schema(description = "Уникальный идентификатор", example = "42")
        private Long id;
        @Schema(description = "Имя викинга", example = "Bjorn")
        String name;
        @Schema(description = "Возраст", example = "31")
        int age;
        @Schema(description = "Рост в сантиметрах", example = "184")
        int heightCm;
        @Schema(description = "Цвет волос", example = "Blond")
        HairColor hairColor;
        @Schema(description = "Форма бороды")
        BeardStyle beardStyle;
        @ArraySchema(schema = @Schema(implementation = EquipmentItem.class), arraySchema = @Schema(description = "Снаряжение викинга"))
        List<EquipmentItem> equipment;
    
    public Viking() {}
        
    public Viking(String name, int age, int heightCm, HairColor hairColor,
                  BeardStyle beardStyle, List<EquipmentItem> equipment) {
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.hairColor = hairColor;
        this.beardStyle = beardStyle;
        this.equipment = equipment;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getHeightCm() { return heightCm; }
    public void setHeightCm(int heightCm) { this.heightCm = heightCm; }

    public HairColor getHairColor() { return hairColor; }
    public void setHairColor(HairColor hairColor) { this.hairColor = hairColor; }

    public BeardStyle getBeardStyle() { return beardStyle; }
    public void setBeardStyle(BeardStyle beardStyle) { this.beardStyle = beardStyle; }

    public List<EquipmentItem> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentItem> equipment) { this.equipment = equipment; }
}
