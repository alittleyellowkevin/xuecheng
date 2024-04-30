package com.kevin.content.service;

import com.kevin.content.model.po.CourseTeacher;

import java.util.List;

public interface CourseTeacherService {
    List<CourseTeacher> getCourseTeacherById(long courseTeacherId);

    void saveCourseTeacher(CourseTeacher courseTeacher);

    void deleteCourseTeacherById(long teacherId);
}
