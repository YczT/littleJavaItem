package com.itheima.riggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.riggie.dto.DishDto;
import com.itheima.riggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表:dish、dish_flavor

    void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品和口味
    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息和对应口味信息
    void updateWithFlavor(DishDto dishDto);
}

