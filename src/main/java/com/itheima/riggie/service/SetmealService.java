package com.itheima.riggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.riggie.dto.SetmealDto;
import com.itheima.riggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时保存两者之间关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐的同时，删除套餐与菜品的联系
     * @param ids
     */
    void removeWithDish(String[] ids);

    /**
     * 根据id回显套餐
     * @param id
     */
    SetmealDto getByIdWithDish(Long id);
}
