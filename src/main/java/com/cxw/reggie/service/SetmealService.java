package com.cxw.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxw.reggie.dto.SetmealDto;
import com.cxw.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    //新增套餐同时保存套餐和菜品的关联关系
    public void saveWithDish(SetmealDto setmealDto);


    //删除套餐时把关联的菜品也删除
    public void removeWithDish(List<Long> ids);
}
