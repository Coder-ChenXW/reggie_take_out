package com.cxw.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxw.reggie.dto.DishDto;
import com.cxw.reggie.entity.Dish;

public interface DishServer extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息
    public void updateWithFlavor(DishDto dishDto);
}
