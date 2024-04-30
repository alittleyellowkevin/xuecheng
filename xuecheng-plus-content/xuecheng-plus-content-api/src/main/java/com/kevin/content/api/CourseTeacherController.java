package com.kevin.content.api;

import com.kevin.content.model.dto.CourseCategoryTreeDto;
import com.kevin.content.model.po.CourseTeacher;
import com.kevin.content.service.CourseCategoryService;
import com.kevin.content.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;



@RestController
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @GetMapping("/courseTeacher/list/{courseTeacherId}")
    public List<CourseTeacher> GetCourseTeacherById(@PathVariable long courseTeacherId){
        return courseTeacherService.getCourseTeacherById(courseTeacherId);
    }
    @PostMapping("/courseTeacher")
    public void saveCourseTeacherById(@RequestBody CourseTeacher courseTeacher){
        courseTeacherService.saveCourseTeacher(courseTeacher);
    }
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void saveCourseTeacherById(@PathVariable long teacherId){
        courseTeacherService.deleteCourseTeacherById(teacherId);
    }
}
