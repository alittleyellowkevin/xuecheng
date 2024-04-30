package com.kevin.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kevin.content.mapper.CourseTeacherMapper;
import com.kevin.content.model.po.CourseTeacher;
import com.kevin.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> getCourseTeacherById(long courseTeacherId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseTeacherId);
        return courseTeacherMapper.selectList(queryWrapper);
    }

    @Override
    public void saveCourseTeacher(CourseTeacher courseTeacher) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<CourseTeacher>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseTeacher.getCourseId());
        List<CourseTeacher> list = courseTeacherMapper.selectList(queryWrapper);
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == courseTeacher.getId()){
                flag = true;
            }
        }
        if(flag){
            courseTeacherMapper.updateById(courseTeacher);
        }else {
            courseTeacherMapper.insert(courseTeacher);
        }
    }

    @Override
    public void deleteCourseTeacherById(long teacherId) {
        courseTeacherMapper.deleteById(teacherId);
    }
}
