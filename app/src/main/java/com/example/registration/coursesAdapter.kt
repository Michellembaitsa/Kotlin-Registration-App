package com.example.registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesAdapter(var courseList: List<Course>) : RecyclerView.Adapter<CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list_item, parent, false) //inflate function creates a view out of a single layout view
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CoursesViewHolder,
        position: Int
    ) { //this position points to the current index
        var currentCourse = courseList.get(position)
        holder.tvCourseName.text = currentCourse.courseName
        holder.tvCourseCode.text = currentCourse.courseCode
        holder.tvDecsription.text = currentCourse.description
        holder.tvInstructor.text = currentCourse.instructor
    }

    override fun getItemCount(): Int{
        return courseList.size
    }
}

class CoursesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvCourseName = itemView.findViewById<TextView>(R.id.tvCoursename)
    var tvCourseCode = itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvDecsription = itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)

}
