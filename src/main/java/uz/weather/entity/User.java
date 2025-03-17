package uz.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.weather.entity.enums.State;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Builder
public class User {
    private Long chatId;
    private String fullName;
    private String userName;
    private State state;
    private ArrayList<String> categoryIds;
}
