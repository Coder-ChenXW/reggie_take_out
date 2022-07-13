package com.cxw.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxw.reggie.common.CustomException;
import com.cxw.reggie.entity.Category;
import com.cxw.reggie.entity.Dish;
import com.cxw.reggie.entity.Setmeal;
import com.cxw.reggie.mapper.CategoryMapper;
import com.cxw.reggie.service.CategoryService;
import com.cxw.reggie.service.DishServer;
import com.cxw.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishServer dishServer;

    @Autowired
    private SetmealService setmealService;

    //根据id删除分类，删除前进行判断
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();

        //添加查询条件根据分类id
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishServer.count(dishLambdaQueryWrapper);

        //是否关联菜品
        if (count1>0){
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();

        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);

        int count2=setmealService.count();

        if (count2>0){
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }


        //删除分类
        super.removeById(id);
    }

}
