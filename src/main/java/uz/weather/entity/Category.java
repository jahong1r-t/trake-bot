package uz.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private String id;
    private String name;
    private String userId;
    private ArrayList<String> spendIds;
}
