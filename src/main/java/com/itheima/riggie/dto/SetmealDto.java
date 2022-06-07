package com.itheima.riggie.dto;

import com.itheima.riggie.entity.Setmeal;
import com.itheima.riggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
